package Utilidades.ImportacionesExcel;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

class LibroExcelXlsx implements LibroExcel {

    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final String NS_RELACIONES = "http://schemas.openxmlformats.org/officeDocument/2006/relationships";

    private final ZipFile zip;
    private final List<String> rutasHojas = new ArrayList<>();
    private final List<String> textosCompartidos = new ArrayList<>();
    private final List<Boolean> estilosFecha = new ArrayList<>();
    private boolean fecha1904;

    LibroExcelXlsx(File archivo) throws IOException {
        zip = new ZipFile(archivo);
        try {
            leerHojas();
            leerTextosCompartidos();
            leerEstilos();
        } catch (XMLStreamException | RuntimeException ex) {
            close();
            throw new IOException("El archivo .xlsx está dañado o no es válido", ex);
        } catch (IOException ex) {
            close();
            throw ex;
        }
    }

    @Override
    public int cantidadHojas() {
        return rutasHojas.size();
    }

    @Override
    public HojaExcel hoja(int indice) throws IOException {
        if (indice < 0 || indice >= rutasHojas.size()) {
            throw new IOException("El archivo no tiene la hoja " + (indice + 1));
        }
        ZipEntry entradaHoja = zip.getEntry(rutasHojas.get(indice));
        if (entradaHoja == null) {
            throw new IOException("El archivo .xlsx no tiene la hoja " + (indice + 1));
        }
        try (InputStream entrada = zip.getInputStream(entradaHoja)) {
            return leerHoja(entrada);
        } catch (XMLStreamException | RuntimeException ex) {
            throw new IOException("La hoja " + (indice + 1) + " del archivo .xlsx está dañada o no es válida", ex);
        }
    }

    @Override
    public void close() {
        try {
            zip.close();
        } catch (IOException ex) {
        }
    }

    private static XMLStreamReader lector(InputStream entrada) throws XMLStreamException {
        XMLInputFactory fabrica = XMLInputFactory.newInstance();
        fabrica.setProperty(XMLInputFactory.SUPPORT_DTD, false);
        fabrica.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, false);
        return fabrica.createXMLStreamReader(entrada);
    }

    private void leerHojas() throws IOException, XMLStreamException {
        ZipEntry libro = zip.getEntry("xl/workbook.xml");
        if (libro == null) {
            throw new IOException("El archivo no es un Excel .xlsx válido");
        }

        List<String> idsRelacion = new ArrayList<>();
        try (InputStream entrada = zip.getInputStream(libro)) {
            XMLStreamReader xml = lector(entrada);
            while (xml.hasNext()) {
                if (xml.next() != XMLStreamConstants.START_ELEMENT) {
                    continue;
                }
                String nombre = xml.getLocalName();
                if ("workbookPr".equals(nombre)) {
                    String valor = xml.getAttributeValue(null, "date1904");
                    fecha1904 = "1".equals(valor) || "true".equalsIgnoreCase(valor);
                } else if ("sheet".equals(nombre)) {
                    idsRelacion.add(atributoRelacion(xml));
                }
            }
            xml.close();
        }

        Map<String, String> destinos = new HashMap<>();
        ZipEntry relaciones = zip.getEntry("xl/_rels/workbook.xml.rels");
        if (relaciones != null) {
            try (InputStream entrada = zip.getInputStream(relaciones)) {
                XMLStreamReader xml = lector(entrada);
                while (xml.hasNext()) {
                    if (xml.next() == XMLStreamConstants.START_ELEMENT && "Relationship".equals(xml.getLocalName())) {
                        destinos.put(xml.getAttributeValue(null, "Id"), xml.getAttributeValue(null, "Target"));
                    }
                }
                xml.close();
            }
        }

        for (int i = 0; i < idsRelacion.size(); i++) {
            String destino = destinos.get(idsRelacion.get(i));
            if (destino == null) {
                rutasHojas.add("xl/worksheets/sheet" + (i + 1) + ".xml");
            } else {
                rutasHojas.add(destino.startsWith("/") ? destino.substring(1) : "xl/" + destino);
            }
        }
    }

    private static String atributoRelacion(XMLStreamReader xml) {
        String valor = xml.getAttributeValue(NS_RELACIONES, "id");
        if (valor != null) {
            return valor;
        }
        for (int i = 0; i < xml.getAttributeCount(); i++) {
            if ("id".equals(xml.getAttributeLocalName(i))) {
                return xml.getAttributeValue(i);
            }
        }
        return null;
    }

    private void leerTextosCompartidos() throws IOException, XMLStreamException {
        ZipEntry entradaTextos = zip.getEntry("xl/sharedStrings.xml");
        if (entradaTextos == null) {
            return;
        }
        try (InputStream entrada = zip.getInputStream(entradaTextos)) {
            XMLStreamReader xml = lector(entrada);
            while (xml.hasNext()) {
                if (xml.next() == XMLStreamConstants.START_ELEMENT && "si".equals(xml.getLocalName())) {
                    textosCompartidos.add(leerTextoEnriquecido(xml, "si"));
                }
            }
            xml.close();
        }
    }

    private static String leerTextoEnriquecido(XMLStreamReader xml, String elementoFin) throws XMLStreamException {
        StringBuilder texto = new StringBuilder();
        int profundidadFonetica = 0;
        while (xml.hasNext()) {
            int evento = xml.next();
            if (evento == XMLStreamConstants.START_ELEMENT) {
                String nombre = xml.getLocalName();
                if ("rPh".equals(nombre)) {
                    profundidadFonetica++;
                } else if ("t".equals(nombre) && profundidadFonetica == 0) {
                    texto.append(xml.getElementText());
                }
            } else if (evento == XMLStreamConstants.END_ELEMENT) {
                String nombre = xml.getLocalName();
                if ("rPh".equals(nombre)) {
                    profundidadFonetica--;
                } else if (elementoFin.equals(nombre)) {
                    break;
                }
            }
        }
        return texto.toString();
    }

    private void leerEstilos() throws IOException, XMLStreamException {
        ZipEntry entradaEstilos = zip.getEntry("xl/styles.xml");
        if (entradaEstilos == null) {
            return;
        }
        Map<Integer, String> formatosPropios = new HashMap<>();
        try (InputStream entrada = zip.getInputStream(entradaEstilos)) {
            XMLStreamReader xml = lector(entrada);
            boolean enCellXfs = false;
            while (xml.hasNext()) {
                int evento = xml.next();
                if (evento == XMLStreamConstants.START_ELEMENT) {
                    String nombre = xml.getLocalName();
                    if ("numFmt".equals(nombre)) {
                        formatosPropios.put(Integer.parseInt(xml.getAttributeValue(null, "numFmtId")), xml.getAttributeValue(null, "formatCode"));
                    } else if ("cellXfs".equals(nombre)) {
                        enCellXfs = true;
                    } else if ("xf".equals(nombre) && enCellXfs) {
                        String id = xml.getAttributeValue(null, "numFmtId");
                        int formato = id == null ? 0 : Integer.parseInt(id);
                        estilosFecha.add(esFormatoFecha(formato, formatosPropios.get(formato)));
                    }
                } else if (evento == XMLStreamConstants.END_ELEMENT && "cellXfs".equals(xml.getLocalName())) {
                    enCellXfs = false;
                }
            }
            xml.close();
        }
    }

    private static boolean esFormatoFecha(int id, String codigo) {
        if ((id >= 14 && id <= 22) || (id >= 27 && id <= 36) || (id >= 45 && id <= 47) || (id >= 50 && id <= 58)) {
            return true;
        }
        if (codigo == null) {
            return false;
        }
        String limpio = codigo.replaceAll("\"[^\"]*\"", "").replaceAll("\\[[^\\]]*\\]", "").replaceAll("\\\\.", "").toLowerCase();
        if (limpio.contains("general")) {
            return false;
        }
        return limpio.matches(".*[dmyhs].*");
    }

    private HojaExcelXlsx leerHoja(InputStream entrada) throws XMLStreamException {
        HojaExcelXlsx hoja = new HojaExcelXlsx();
        XMLStreamReader xml = lector(entrada);
        int filaActual = -1;
        int columnaActual = -1;
        while (xml.hasNext()) {
            if (xml.next() != XMLStreamConstants.START_ELEMENT) {
                continue;
            }
            String nombre = xml.getLocalName();
            if ("row".equals(nombre)) {
                String r = xml.getAttributeValue(null, "r");
                filaActual = r == null ? filaActual + 1 : Integer.parseInt(r) - 1;
                columnaActual = -1;
            } else if ("c".equals(nombre)) {
                String referencia = xml.getAttributeValue(null, "r");
                columnaActual = referencia == null ? columnaActual + 1 : indiceColumna(referencia);
                int fila = filaActual;
                if (referencia != null && fila < 0) {
                    fila = indiceFila(referencia);
                }
                leerCelda(xml, hoja, Math.max(fila, 0), columnaActual);
            }
        }
        xml.close();
        return hoja;
    }

    private void leerCelda(XMLStreamReader xml, HojaExcelXlsx hoja, int fila, int columna) throws XMLStreamException {
        String tipo = xml.getAttributeValue(null, "t");
        String estilo = xml.getAttributeValue(null, "s");
        String valor = null;
        String textoEnLinea = null;
        while (xml.hasNext()) {
            int evento = xml.next();
            if (evento == XMLStreamConstants.START_ELEMENT) {
                String nombre = xml.getLocalName();
                if ("v".equals(nombre)) {
                    valor = xml.getElementText();
                } else if ("is".equals(nombre)) {
                    textoEnLinea = leerTextoEnriquecido(xml, "is");
                }
            } else if (evento == XMLStreamConstants.END_ELEMENT && "c".equals(xml.getLocalName())) {
                break;
            }
        }

        if ("inlineStr".equals(tipo)) {
            hoja.agregar(fila, columna, textoEnLinea, null, null);
            return;
        }
        if (valor == null) {
            return;
        }
        if ("s".equals(tipo)) {
            int indice = Integer.parseInt(valor.trim());
            hoja.agregar(fila, columna, indice < textosCompartidos.size() ? textosCompartidos.get(indice) : "", null, null);
            return;
        }
        if ("b".equals(tipo)) {
            hoja.agregar(fila, columna, "1".equals(valor.trim()) ? "TRUE" : "FALSE", null, null);
            return;
        }
        if ("d".equals(tipo)) {
            String texto = valor.trim();
            if (texto.length() >= 10) {
                LocalDate fecha = LocalDate.parse(texto.substring(0, 10));
                hoja.agregar(fila, columna, fecha.format(FORMATO_FECHA), null, fecha);
            } else {
                hoja.agregar(fila, columna, texto, null, null);
            }
            return;
        }
        if ("str".equals(tipo) || "e".equals(tipo)) {
            hoja.agregar(fila, columna, valor, null, null);
            return;
        }

        double numero;
        try {
            numero = Double.parseDouble(valor.trim());
        } catch (NumberFormatException ex) {
            hoja.agregar(fila, columna, valor, null, null);
            return;
        }

        if (esEstiloFecha(estilo)) {
            LocalDate base = fecha1904 ? LocalDate.of(1904, 1, 1) : LocalDate.of(1899, 12, 30);
            LocalDate fecha = base.plusDays((long) Math.floor(numero));
            hoja.agregar(fila, columna, fecha.format(FORMATO_FECHA), null, fecha);
            return;
        }

        hoja.agregar(fila, columna, new BigDecimal(valor.trim()).stripTrailingZeros().toPlainString(), numero, null);
    }

    private boolean esEstiloFecha(String estilo) {
        if (estilo == null) {
            return false;
        }
        int indice = Integer.parseInt(estilo.trim());
        return indice < estilosFecha.size() && estilosFecha.get(indice);
    }

    private static int indiceColumna(String referencia) {
        int indice = 0;
        for (int i = 0; i < referencia.length(); i++) {
            char letra = Character.toUpperCase(referencia.charAt(i));
            if (letra < 'A' || letra > 'Z') {
                break;
            }
            indice = indice * 26 + (letra - 'A' + 1);
        }
        return indice - 1;
    }

    private static int indiceFila(String referencia) {
        String digitos = referencia.replaceAll("[^0-9]", "");
        return digitos.isEmpty() ? 0 : Integer.parseInt(digitos) - 1;
    }
}
