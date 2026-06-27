package Modelo.Ventas;

import java.util.Collections;
import java.util.List;

public class DocumentoMovimiento {

    private final CabeceraDocumento cabecera;
    private final List<LineaProducto> lineas;

    public DocumentoMovimiento(CabeceraDocumento cabecera, List<LineaProducto> lineas) {
        this.cabecera = cabecera;
        this.lineas = lineas;
    }

    public static DocumentoMovimiento vacio() {
        return new DocumentoMovimiento(new CabeceraDocumento(), Collections.<LineaProducto>emptyList());
    }

    public boolean isEmpty() {
        return lineas.isEmpty();
    }

    public CabeceraDocumento getCabecera() { return cabecera; }
    public List<LineaProducto> getLineas() { return lineas; }
}
