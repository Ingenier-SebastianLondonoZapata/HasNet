package Dao.Documentos;

public final class ConsultaFactura {

    private ConsultaFactura() {
    }

    public static String consulta(String columnas, String condicion, String cola) {
        return ConsultaVista.FACTURA.consulta(columnas, condicion, cola);
    }

    public static String filtro(String condicion) {
        return ConsultaVista.FACTURA.filtro(condicion);
    }

    public static String y(String condicion, String adicional) {
        return ConsultaVista.y(condicion, adicional);
    }

    public static String expresion(String nombre) {
        return ConsultaVista.FACTURA.expresion(nombre);
    }

    public static String columnas(String... nombres) {
        return ConsultaVista.FACTURA.columnas(nombres);
    }

    public static String traducir(String sql) {
        return ConsultaVista.FACTURA.traducir(sql);
    }
}
