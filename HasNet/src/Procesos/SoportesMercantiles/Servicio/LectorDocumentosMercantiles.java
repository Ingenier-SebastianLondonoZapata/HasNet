package Procesos.SoportesMercantiles.Servicio;

import Modelo.SoportesMercantiles.DocumentoMercantil;
import Modelo.SoportesMercantiles.LecturaDocumentosMercantiles;
import Utilidades.ImportacionesExcel.HojaExcel;
import Utilidades.ImportacionesExcel.LibroExcel;
import Utilidades.Utilidades;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.Normalizer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class LectorDocumentosMercantiles {

    private static final int FILAS_BUSQUEDA_ENCABEZADO = 15;
    private static final Pattern PREFIJO_FOLIO = Pattern.compile("^([^0-9]*)([0-9]+)$");
    private static final DateTimeFormatter[] FORMATOS_FECHA = {
        DateTimeFormatter.ofPattern("dd-MM-yyyy"), DateTimeFormatter.ofPattern("dd/MM/yyyy"), DateTimeFormatter.ISO_LOCAL_DATE};

    private static final Map<String, String[]> COLUMNAS_DIAN = new HashMap<>();

    static {
        COLUMNAS_DIAN.put("cufe", new String[]{"cufecude", "cufe", "cude"});
        COLUMNAS_DIAN.put("folio", new String[]{"folio", "numero"});
        COLUMNAS_DIAN.put("prefijo", new String[]{"prefijo"});
        COLUMNAS_DIAN.put("nitEmisor", new String[]{"nitemisor", "nitdelemisor"});
        COLUMNAS_DIAN.put("nombreEmisor", new String[]{"nombreemisor", "nombredelemisor", "razonsocialemisor"});
        COLUMNAS_DIAN.put("fechaEmision", new String[]{"fechaemision", "fechadeemision"});
        COLUMNAS_DIAN.put("total", new String[]{"total", "valortotal"});
        COLUMNAS_DIAN.put("tipo", new String[]{"tipodedocumento", "tipodocumento"});
        COLUMNAS_DIAN.put("grupo", new String[]{"grupo"});
    }

    public LecturaDocumentosMercantiles leerExcelDian(File archivo) throws IOException {
        LecturaDocumentosMercantiles lectura = new LecturaDocumentosMercantiles();
        Set<String> cufesVistos = new HashSet<>();
        int omitidosPorTipo = 0;
        int omitidosEmitidos = 0;

        try (LibroExcel libro = LibroExcel.abrir(archivo)) {
            HojaExcel hoja = libro.hoja(0);
            int filaEncabezado = buscarEncabezado(hoja);
            if (filaEncabezado < 0) {
                lectura.getAvisos().add("El archivo no tiene la columna 'CUFE/CUDE'. Use el Excel que descarga de la DIAN (Histórico > Descargar listado)");
                return lectura;
            }

            Map<String, Integer> columnas = leerColumnas(hoja, filaEncabezado);
            for (int fila = filaEncabezado + 1; fila < hoja.filas(); fila++) {
                String cufe = texto(hoja, fila, columnas, "cufe");
                if (cufe.isEmpty()) {
                    continue;
                }

                String tipo = normalizar(texto(hoja, fila, columnas, "tipo"));
                if (!tipo.isEmpty() && !tipo.contains("factura")) {
                    omitidosPorTipo++;
                    continue;
                }

                if (normalizar(texto(hoja, fila, columnas, "grupo")).startsWith("emitido")) {
                    omitidosEmitidos++;
                    continue;
                }

                if (!cufesVistos.add(cufe)) {
                    continue;
                }

                DocumentoMercantil documento = new DocumentoMercantil();
                documento.setCufe(cufe);
                documento.setPrefijo(texto(hoja, fila, columnas, "prefijo"));
                documento.setFolio(texto(hoja, fila, columnas, "folio"));
                documento.setNitEmisor(texto(hoja, fila, columnas, "nitEmisor"));
                documento.setNombreEmisor(texto(hoja, fila, columnas, "nombreEmisor"));
                documento.setFechaEmision(fecha(hoja, fila, columnas, "fechaEmision"));
                documento.setTotal(decimal(hoja, fila, columnas, "total"));
                documento.setOrigen(DocumentoMercantil.ORIGEN_EXCEL);
                documento.setArchivo(archivo.getName());
                lectura.getDocumentos().add(documento);
            }
        }

        if (omitidosPorTipo > 0) {
            lectura.getAvisos().add(omitidosPorTipo + " documentos se omitieron porque no son facturas (notas, documentos soporte, etc.)");
        }
        if (omitidosEmitidos > 0) {
            lectura.getAvisos().add(omitidosEmitidos + " documentos se omitieron porque son emitidos por la empresa, no recibidos");
        }
        return lectura;
    }

    public LecturaDocumentosMercantiles leerXmlOZip(File archivo) throws IOException {
        LecturaDocumentosMercantiles lectura = new LecturaDocumentosMercantiles();
        if (archivo.getName().toLowerCase().endsWith(".zip")) {
            leerZip(archivo, lectura);
        } else {
            agregarXml(Files.readAllBytes(archivo.toPath()), archivo.getName(), lectura);
        }

        if (lectura.getDocumentos().isEmpty() && lectura.getAvisos().isEmpty()) {
            lectura.getAvisos().add("El archivo " + archivo.getName() + " no contiene XML de facturas");
        }
        return lectura;
    }

    private void leerZip(File archivo, LecturaDocumentosMercantiles lectura) throws IOException {
        try (ZipInputStream zip = new ZipInputStream(new FileInputStream(archivo))) {
            ZipEntry entrada;
            while ((entrada = zip.getNextEntry()) != null) {
                String nombre = entrada.getName();
                if (!entrada.isDirectory() && nombre.toLowerCase().endsWith(".xml")) {
                    agregarXml(leerTodo(zip), archivo.getName() + "/" + new File(nombre).getName(), lectura);
                }
                zip.closeEntry();
            }
        }
    }

    private void agregarXml(byte[] contenido, String nombreArchivo, LecturaDocumentosMercantiles lectura) {
        Document xml;
        try {
            xml = parsear(contenido);
        } catch (Exception ex) {
            lectura.getAvisos().add(nombreArchivo + ": no es un XML válido (" + ex.getMessage() + ")");
            return;
        }

        String raiz = xml.getDocumentElement().getLocalName();
        DocumentoMercantil documento = new DocumentoMercantil();
        documento.setOrigen(DocumentoMercantil.ORIGEN_XML);
        documento.setArchivo(nombreArchivo);

        if ("AttachedDocument".equals(raiz)) {
            leerAttachedDocument(xml, documento);
            documento.setXmlBase64(Base64.getEncoder().encodeToString(contenido));
        } else if ("Invoice".equals(raiz)) {
            leerFactura(xml, documento);
            if (documento.getCufe().isEmpty()) {
                lectura.getAvisos().add(nombreArchivo + ": la factura no tiene CUFE");
                return;
            }
        } else {
            lectura.getAvisos().add(nombreArchivo + ": no es una factura electrónica (" + raiz + "). Suba el AttachedDocument que envía el proveedor");
            return;
        }

        lectura.getDocumentos().add(documento);
    }

    private void leerAttachedDocument(Document xml, DocumentoMercantil documento) {
        Element raiz = xml.getDocumentElement();
        Element emisor = primero(raiz, "SenderParty");
        if (emisor != null) {
            documento.setNombreEmisor(valor(emisor, "RegistrationName"));
            documento.setNitEmisor(valor(emisor, "CompanyID"));
        }

        Element referencia = primero(raiz, "ParentDocumentLineReference");
        if (referencia != null) {
            documento.setCufe(valor(referencia, "UUID"));
        }

        String numero = valorHijo(raiz, "ParentDocumentID");
        Element adjunto = primero(raiz, "Attachment");
        String facturaEmbebida = adjunto == null ? "" : valor(adjunto, "Description");
        if (!facturaEmbebida.isEmpty()) {
            try {
                leerFactura(parsear(facturaEmbebida.getBytes(StandardCharsets.UTF_8)), documento);
            } catch (Exception ex) {
                System.err.println("No se pudo leer la factura embebida del AttachedDocument: " + ex.getMessage());
            }
        }

        if (documento.getFolio().isEmpty() && !numero.isEmpty()) {
            asignarNumero(documento, numero, "");
        }
    }

    private void leerFactura(Document xml, DocumentoMercantil documento) {
        Element raiz = xml.getDocumentElement();
        if (documento.getCufe().isEmpty()) {
            documento.setCufe(valorHijo(raiz, "UUID"));
        }

        asignarNumero(documento, valorHijo(raiz, "ID"), valor(raiz, "Prefix"));

        LocalDate fecha = convertirFecha(valorHijo(raiz, "IssueDate"));
        if (fecha != null) {
            documento.setFechaEmision(fecha);
        }

        Element totales = primero(raiz, "LegalMonetaryTotal");
        if (totales != null) {
            String total = valor(totales, "PayableAmount");
            if (!total.isEmpty()) {
                try {
                    documento.setTotal(new BigDecimal(total));
                } catch (NumberFormatException ex) {
                    System.err.println("Total no numérico en la factura: " + total);
                }
            }
        }

        Element proveedor = primero(raiz, "AccountingSupplierParty");
        if (proveedor != null) {
            if (documento.getNombreEmisor().isEmpty()) {
                documento.setNombreEmisor(valor(proveedor, "RegistrationName"));
            }
            if (documento.getNitEmisor().isEmpty()) {
                documento.setNitEmisor(valor(proveedor, "CompanyID"));
            }
        }
    }

    private void asignarNumero(DocumentoMercantil documento, String numero, String prefijo) {
        if (numero.isEmpty()) {
            return;
        }
        if (!prefijo.isEmpty() && numero.startsWith(prefijo)) {
            documento.setPrefijo(prefijo);
            documento.setFolio(numero.substring(prefijo.length()));
            return;
        }
        Matcher partes = PREFIJO_FOLIO.matcher(numero);
        if (partes.matches()) {
            documento.setPrefijo(partes.group(1));
            documento.setFolio(partes.group(2));
        } else {
            documento.setFolio(numero);
        }
    }

    private Document parsear(byte[] contenido) throws Exception {
        DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
        fabrica.setNamespaceAware(true);
        fabrica.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        fabrica.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        fabrica.setFeature("http://xml.org/sax/features/external-general-entities", false);
        fabrica.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
        fabrica.setExpandEntityReferences(false);
        DocumentBuilder constructor = fabrica.newDocumentBuilder();
        constructor.setErrorHandler(null);
        return constructor.parse(new ByteArrayInputStream(contenido));
    }

    private Element primero(Element padre, String nombreLocal) {
        NodeList nodos = padre.getElementsByTagNameNS("*", nombreLocal);
        return nodos.getLength() == 0 ? null : (Element) nodos.item(0);
    }

    private String valor(Element padre, String nombreLocal) {
        Element elemento = primero(padre, nombreLocal);
        return elemento == null ? "" : elemento.getTextContent().trim();
    }

    private String valorHijo(Element padre, String nombreLocal) {
        for (org.w3c.dom.Node hijo = padre.getFirstChild(); hijo != null; hijo = hijo.getNextSibling()) {
            if (hijo instanceof Element && nombreLocal.equals(hijo.getLocalName())) {
                return hijo.getTextContent().trim();
            }
        }
        return "";
    }

    private static byte[] leerTodo(InputStream entrada) throws IOException {
        ByteArrayOutputStream salida = new ByteArrayOutputStream();
        byte[] buffer = new byte[8192];
        int leidos;
        while ((leidos = entrada.read(buffer)) != -1) {
            salida.write(buffer, 0, leidos);
        }
        return salida.toByteArray();
    }

    private int buscarEncabezado(HojaExcel hoja) {
        int limite = Math.min(FILAS_BUSQUEDA_ENCABEZADO, hoja.filas());
        for (int fila = 0; fila < limite; fila++) {
            for (int columna = 0; columna < hoja.columnas(); columna++) {
                String encabezado = normalizar(hoja.contenido(columna, fila));
                for (String alias : COLUMNAS_DIAN.get("cufe")) {
                    if (encabezado.equals(alias)) {
                        return fila;
                    }
                }
            }
        }
        return -1;
    }

    private Map<String, Integer> leerColumnas(HojaExcel hoja, int filaEncabezado) {
        Map<String, Integer> encontradas = new HashMap<>();
        for (int columna = 0; columna < hoja.columnas(); columna++) {
            encontradas.putIfAbsent(normalizar(hoja.contenido(columna, filaEncabezado)), columna);
        }

        Map<String, Integer> columnas = new HashMap<>();
        for (Map.Entry<String, String[]> campo : COLUMNAS_DIAN.entrySet()) {
            for (String alias : campo.getValue()) {
                Integer indice = encontradas.get(alias);
                if (indice != null) {
                    columnas.put(campo.getKey(), indice);
                    break;
                }
            }
        }
        return columnas;
    }

    private String texto(HojaExcel hoja, int fila, Map<String, Integer> columnas, String campo) {
        Integer indice = columnas.get(campo);
        if (indice == null) {
            return "";
        }
        Double numero = hoja.numero(indice, fila);
        if (numero != null && !"total".equals(campo)) {
            return new BigDecimal(numero).toBigInteger().toString();
        }
        return hoja.contenido(indice, fila).trim();
    }

    private BigDecimal decimal(HojaExcel hoja, int fila, Map<String, Integer> columnas, String campo) {
        Integer indice = columnas.get(campo);
        if (indice == null) {
            return null;
        }
        Double numero = hoja.numero(indice, fila);
        if (numero != null) {
            return BigDecimal.valueOf(numero);
        }
        String contenido = hoja.contenido(indice, fila).replaceAll("[\\s$]", "");
        return contenido.isEmpty() ? null : Utilidades.convertirBigDecimal(contenido);
    }

    private LocalDate fecha(HojaExcel hoja, int fila, Map<String, Integer> columnas, String campo) {
        Integer indice = columnas.get(campo);
        if (indice == null) {
            return null;
        }
        LocalDate fecha = hoja.fecha(indice, fila);
        return fecha != null ? fecha : convertirFecha(hoja.contenido(indice, fila));
    }

    static LocalDate convertirFecha(String valor) {
        String texto = valor == null ? "" : valor.trim();
        if (texto.length() > 10) {
            texto = texto.substring(0, 10);
        }
        for (DateTimeFormatter formato : FORMATOS_FECHA) {
            try {
                return LocalDate.parse(texto, formato);
            } catch (RuntimeException ex) {
            }
        }
        return null;
    }

    private static String normalizar(String valor) {
        String sinTildes = Normalizer.normalize(valor == null ? "" : valor, Normalizer.Form.NFD).replaceAll("\\p{M}", "");
        return sinTildes.toLowerCase().replaceAll("[^a-z0-9]", "");
    }
}
