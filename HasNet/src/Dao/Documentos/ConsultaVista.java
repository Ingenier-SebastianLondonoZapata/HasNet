package Dao.Documentos;

import Enums.TipoDocumento;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ConsultaVista {

    private static final Pattern SIN_CALIFICAR = Pattern.compile("(?i)(?<![\\w.'])(?<!AS\\s)(fechaFactura|anulada)(?![\\w'])");
    private static final String PLAZO_FACTURA = "(SELECT c.plazo FROM bdcxc c WHERE c.factura2 = d.factura AND c.tipo IN ('FACT', 'NOTADEBITO') ORDER BY c.Id LIMIT 1)";
    private static final String CUOTA_INICIAL = "(SELECT pr.cuotaInicial FROM bdprestamo pr WHERE pr.factura = d.idFactura ORDER BY pr.contrato LIMIT 1)";

    public static final ConsultaVista FACTURA = new ConsultaVista("factura", DaoDocumentos.TIPOS_FACTURA, true, null);
    public static final ConsultaVista FACTURACION = new ConsultaVista("facturacion", DaoDocumentos.TIPOS_FACTURA, false, null);
    public static final ConsultaVista COTIZACION = new ConsultaVista("cotizacion", Collections.singletonList(TipoDocumento.COTIZACION), true, null);
    public static final ConsultaVista PLAN_SEPARE = new ConsultaVista("planSepare", Collections.singletonList(TipoDocumento.PLAN_SEPARE), true,
            "EXISTS (SELECT 1 FROM bdcxc c WHERE c.factura = d.idFactura AND c.tipo = 'SEPARE')");

    static {
        FACTURA.agregar("ordenId", "CAST(SUBSTR(d.idFactura, 6, 100) AS SIGNED)");
        FACTURA.agregarTercero("cliente", "id");
        for (String campo : new String[]{"nombre", "telefono", "direccion", "nombreContacto", "cargo", "eps", "idSistema"}) {
            FACTURA.agregarTercero(campo, campo);
        }
        FACTURA.agregar("nombreEps", "e.nombre");
        for (String campo : new String[]{"manejaInventario", "ubicacion1", "referencia", "unidad", "Codigo"}) {
            FACTURA.agregar(campo, "p." + campo);
        }
        FACTURA.agregar("Grupo", "p.grupo");
        FACTURA.agregar("codEstandar", "p.codContable");
        FACTURA.agregar("nombreGrupo", "g.nombre");
        FACTURA.agregar("color", "dp.color");
        FACTURA.agregar("id2", "d.factura");
        FACTURA.agregar("valor3", "(d.subtotalGeneral - d.descuentoGeneral)");
        FACTURA.agregar("cuotaInicial2", "IFNULL(" + CUOTA_INICIAL + ", 0)");
        FACTURA.agregar("plazo", PLAZO_FACTURA);
        FACTURA.agregar("plazo1", "IF(" + PLAZO_FACTURA + " <> 0, " + PLAZO_FACTURA + ", '0')");
        FACTURA.agregar("tipo", "(SELECT pl.tipo FROM bdplacas pl WHERE pl.placa = d.placa ORDER BY pl.Id LIMIT 1)");
        for (String campo : new String[]{"fIngreso", "fSalida", "hIngreso", "hSalida", "horas"}) {
            FACTURA.agregar(campo, "(SELECT pa." + campo + " FROM bdparqueaderoautos pa WHERE pa.factura = d.factura ORDER BY pa.Id LIMIT 1)");
        }
        FACTURA.agregar("anexoFacturacion", "(SELECT mi.anexoFactura FROM bdmaestraimpresiones mi LIMIT 1)");

        FACTURACION.agregar("ordenId", "CAST(SUBSTR(d.idFactura, 6, 100) AS SIGNED)");
        FACTURACION.agregarTercero("cliente", "id");
        FACTURACION.agregarTercero("nombre", "nombre");
        FACTURACION.agregarTercero("idSistema", "idSistema");
        FACTURACION.agregar("id2", "d.factura");
        FACTURACION.agregar("cuotaInicial2", CUOTA_INICIAL);
        FACTURACION.agregar("fechaFactura1", "d.fechaFactura");
        FACTURACION.agregar("impoconsumo", "d.impoconsumoGeneral");
        FACTURACION.agregar("rango", "(SELECT x.rango FROM bdDocumentosDetalle x WHERE x.idDocumento = d.idDocumento ORDER BY x.idDetalle LIMIT 1)");

        COTIZACION.agregar("ordenId", "CAST(SUBSTR(d.idFactura, 6, 100) AS SIGNED)");
        for (String campo : new String[]{"nombre", "telefono", "celular", "direccion"}) {
            COTIZACION.agregarTercero(campo, campo);
        }
        COTIZACION.agregarTercero("Id", "id");

        PLAN_SEPARE.agregar("ordenId", "CAST(SUBSTR(d.idFactura, 8, 100) AS SIGNED)");
        for (String campo : new String[]{"nombre", "telefono", "direccion"}) {
            PLAN_SEPARE.agregarTercero(campo, campo);
        }
        PLAN_SEPARE.agregarTercero("IdTercero", "id");
        PLAN_SEPARE.agregar("plazo", "(SELECT c.plazo FROM bdcxc c WHERE c.factura = d.idFactura AND c.tipo = 'SEPARE' ORDER BY c.Id LIMIT 1)");
        PLAN_SEPARE.agregar("tipo", "'SEPARE'");
        PLAN_SEPARE.agregar("ubicacion", "''");
        PLAN_SEPARE.agregar("referencia", "''");
        PLAN_SEPARE.agregar("cuotaInicial2", "0");
        PLAN_SEPARE.agregar("CodigoProd", "p.Codigo");
    }

    private final Pattern referencia;
    private final List<TipoDocumento> tipos;
    private final boolean conDetalle;
    private final String condicionBase;
    private final Map<String, String> expresiones = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    private ConsultaVista(String nombre, List<TipoDocumento> tipos, boolean conDetalle, String condicionBase) {
        this.referencia = Pattern.compile("(?i)(?<![\\w.])" + Pattern.quote(nombre) + "\\.(\\w+)");
        this.tipos = tipos;
        this.conDetalle = conDetalle;
        this.condicionBase = condicionBase;
    }

    private void agregar(String nombre, String expresion) {
        expresiones.put(nombre, expresion);
    }

    private void agregarTercero(String nombre, String columna) {
        expresiones.put(nombre, "t." + columna);
    }

    public String consulta(String columnas, String condicion, String cola) {
        String select = traducir(columnas);
        String filtro = filtro(condicion);
        String resto = cola == null ? "" : traducir(cola);
        return "SELECT " + select + " " + desde(select, filtro, resto) + " " + filtro + " " + resto;
    }

    public String desde(String... textos) {
        String texto = String.join(" ", Arrays.asList(textos));
        StringBuilder resultado = new StringBuilder(conDetalle ? "FROM" + ConsultaDocumentos.ORIGEN.replaceAll("\\s+$", "") : "FROM bdDocumentos d");
        if (usa(texto, "t") || usa(texto, "e")) {
            resultado.append(" LEFT JOIN bdterceros t ON t.idSistema = d.cliente");
        }
        if (usa(texto, "e")) {
            resultado.append(" LEFT JOIN bdepsprecargados e ON e.Id = t.eps");
        }
        if (usa(texto, "p") || usa(texto, "g")) {
            resultado.append(" LEFT JOIN bdproductos p ON p.idSistema = dd.producto");
        }
        if (usa(texto, "g")) {
            resultado.append(" LEFT JOIN bdgrupo g ON g.codigo = p.grupo");
        }
        if (usa(texto, "dp")) {
            resultado.append(" LEFT JOIN bddetalleproductos dp ON dp.Id = dd.idProd");
        }
        return resultado.toString();
    }

    public String filtro(String condicion) {
        String limpia = ConsultaDocumentos.sinWhere(condicion);
        String base = ConsultaDocumentos.filtroTipos(tipos) + (condicionBase == null ? "" : " AND " + condicionBase);
        return "WHERE " + base + (limpia.isEmpty() ? "" : " AND (" + traducir(limpia) + ")");
    }

    public static String y(String condicion, String adicional) {
        String limpia = ConsultaDocumentos.sinWhere(condicion);
        return limpia.isEmpty() ? adicional : "(" + limpia + ") AND " + adicional;
    }

    public String expresion(String nombre) {
        String expresion = expresiones.get(nombre);
        return expresion != null ? expresion : ConsultaDocumentos.expresion(tipos.get(0), nombre);
    }

    public String columnas(String... nombres) {
        StringBuilder resultado = new StringBuilder();
        for (String nombre : nombres) {
            resultado.append(resultado.length() == 0 ? "" : ", ").append(expresion(nombre)).append(" AS ").append(nombre);
        }
        return resultado.toString();
    }

    public String traducir(String sql) {
        Matcher coincidencia = referencia.matcher(sql);
        StringBuffer conReferencias = new StringBuffer();
        while (coincidencia.find()) {
            coincidencia.appendReplacement(conReferencias, Matcher.quoteReplacement(expresion(coincidencia.group(1))));
        }
        coincidencia.appendTail(conReferencias);

        Matcher sinCalificar = SIN_CALIFICAR.matcher(conReferencias.toString());
        StringBuffer resultado = new StringBuffer();
        while (sinCalificar.find()) {
            sinCalificar.appendReplacement(resultado, Matcher.quoteReplacement("d." + sinCalificar.group(1)));
        }
        sinCalificar.appendTail(resultado);
        return resultado.toString();
    }

    private static boolean usa(String sql, String alias) {
        return Pattern.compile("(?<![\\w.])" + alias + "\\.\\w").matcher(sql).find();
    }
}
