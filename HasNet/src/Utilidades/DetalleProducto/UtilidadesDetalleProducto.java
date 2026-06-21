package Utilidades.DetalleProducto;

import Enums.EstadosDetalleProducto;
import Enums.enumBodegas;
import Modelo.Inventario.DetalleProducto;
import Utilidades.Utilidades;
import clases.Instancias;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;

public class UtilidadesDetalleProducto {

    private final Instancias instancias = Instancias.getInstancias();
    private final JTable detallesProductos;
    
    public UtilidadesDetalleProducto(JTable detallesProductos) {
        this.detallesProductos = detallesProductos;
    }

    public List<DetalleProducto> generarDetallesProductos() {

        int consecutivoDetalleProducto = Integer.parseInt(instancias.getSql().getNumConsecutivo("DETALLEPROD")[0].toString());
        List<DetalleProducto> detalles = new ArrayList<>();

        for (int i = 0; i < detallesProductos.getRowCount(); i++) {
            String codigoProducto = obtenerValorTabla(i, 0);
            String imei = obtenerValorTabla(i, 1);
            String lote = obtenerValorTabla(i, 2);
            LocalDate fechaVencimiento = Utilidades.convertirFecha(obtenerValorTabla(i, 3));
            String temperatura = obtenerValorTabla(i, 4);
            BigDecimal cantidad = Utilidades.convertirBigDecimal(obtenerValorTabla(i, 5));
            String descripcion = obtenerValorTabla(i, 6);
            String color = obtenerValorTabla(i, 7);
            String talla = obtenerValorTabla(i, 8);

            DetalleProducto detalle = new DetalleProducto(
                    String.valueOf(consecutivoDetalleProducto),
                    codigoProducto,
                    descripcion,
                    imei,
                    lote,
                    color,
                    talla,
                    fechaVencimiento,
                    temperatura,
                    EstadosDetalleProducto.DISPONIBLE.getNombre(),
                    enumBodegas.TipoBodega.BODEGA_PRINCIPAL.getValue(),
                    cantidad,
                    cantidad
            );

            detalles.add(detalle);
            consecutivoDetalleProducto++;
        }

        return detalles;
    }

    private String obtenerValorTabla(int row, int col) {
        Object value = detallesProductos.getValueAt(row, col);
        return value != null ? value.toString() : "";
    }
}
