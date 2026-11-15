package Impresiones.ImpresionesReportes;

import Modelo.Reportes.SeleccionReporte;
import Utilidades.BaseDatos.SQL;
import clases.Instancias;
import clases.big;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class GeneradorReportesCalculados {

    private final Instancias instancias;

    public GeneradorReportesCalculados(Instancias instancias) {
        this.instancias = instancias;
    }

    public void resumenGerencial(SeleccionReporte seleccion) {
        String inicial = seleccion.getFechaInicial();
        String fin = seleccion.getFechaFinal();
        SQL sql = instancias.getSql();
        sql.eliminarTodo("bdGenerarResumen");

        Object[] ventas = primeraFila(sql.getResumenVentas(inicial, fin), 5);
        Object[] costos = primeraFila(sql.getResumenCostos(inicial, fin), 2);
        Object[] ponderado = primeraFila(sql.getResumenCostos1(inicial, fin), 1);

        BigDecimal efectivo = valor(ventas[0]);
        BigDecimal iva = valor(ventas[2]);
        BigDecimal total = valor(ventas[3]);
        BigDecimal impoconsumo = valor(ventas[4]);
        BigDecimal costoPreparacion = valor(costos[1]);
        BigDecimal costoSinPreparacion = valor(ponderado[0]);

        BigDecimal utilidadPorcentaje = porcentaje(total.subtract(efectivo), total);
        BigDecimal efectivoPorcentaje = porcentaje(efectivo, total);
        BigDecimal margen = porcentaje(total.subtract(costoPreparacion).subtract(costoSinPreparacion), total.subtract(iva));

        sql.agregarRegistrosResumenDia("DESDE " + inicial + " HASTA " + fin, efectivo, iva, costoSinPreparacion, total,
                costoPreparacion, utilidadPorcentaje, efectivoPorcentaje, margen, impoconsumo);
        instancias.getReporte().ver_ResumenGerencial();
    }

    private static Object[] primeraFila(Object[][] filas, int columnas) {
        if (filas == null || filas.length == 0 || filas[0] == null) {
            return new Object[columnas];
        }
        return filas[0];
    }

    private static BigDecimal porcentaje(BigDecimal valor, BigDecimal base) {
        if (base.signum() == 0) {
            return BigDecimal.ZERO;
        }
        return valor.multiply(BigDecimal.valueOf(100)).divide(base, 2, RoundingMode.HALF_UP);
    }

    private static BigDecimal valor(Object dato) {
        if (dato == null || dato.toString().trim().isEmpty()) {
            return BigDecimal.ZERO;
        }
        return big.getBigDecimal(dato instanceof String ? ((String) dato).trim() : dato);
    }
}
