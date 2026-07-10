package Modelo.Ventas;

public class ModeloDetalleOrdenServicio {

    private final String idOrden;
    private final String idParte;
    private final String nombreParte;
    private final boolean inventario;
    private final String problemasDerecha;
    private final String problemasIzquierda;
    private final String observaciones;
    private final int num;

    public ModeloDetalleOrdenServicio(String idOrden, String idParte, String nombreParte,
            boolean inventario, String problemasDerecha, String problemasIzquierda,
            String observaciones, int num) {
        this.idOrden = idOrden;
        this.idParte = idParte;
        this.nombreParte = nombreParte;
        this.inventario = inventario;
        this.problemasDerecha = problemasDerecha;
        this.problemasIzquierda = problemasIzquierda;
        this.observaciones = observaciones;
        this.num = num;
    }

    public String getIdOrden() { return idOrden; }
    public String getIdParte() { return idParte; }
    public String getNombreParte() { return nombreParte; }
    public boolean isInventario() { return inventario; }
    public String getProblemasDerecha() { return problemasDerecha; }
    public String getProblemasIzquierda() { return problemasIzquierda; }
    public String getObservaciones() { return observaciones; }
    public int getNum() { return num; }
}
