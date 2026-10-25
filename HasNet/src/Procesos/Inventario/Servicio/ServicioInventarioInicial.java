package Procesos.Inventario.Servicio;

import Enums.HistoricoPonderados;
import Enums.TipoDocumento;
import Utilidades.Contactos.Bodegas;
import Modelo.Inventario.DetalleProducto;
import Modelo.Inventario.LineaInventarioInicial;
import Modelo.Inventario.MovimientoInventario;
import Modelo.Productos.ModeloProducto;
import Procesos.Inventario.Dao.DaoDetalleProducto;
import Procesos.Inventario.Dao.DaoInventarioInicial;
import clases.Instancias;
import clases.metodosGenerales;
import Dao.Productos.DaoProducto;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class ServicioInventarioInicial {

    private static final String INGRESO_INICIAL = "ING-0";

    private final Instancias instancias = Instancias.getInstancias();
    private final DaoProducto daoProducto = new DaoProducto();
    private final DaoInventarioInicial daoInventarioInicial = new DaoInventarioInicial();
    private final DaoDetalleProducto daoDetalleProducto = new DaoDetalleProducto();
    private final ServicioActualizacionPonderado servicioPonderado = new ServicioActualizacionPonderado();

    public boolean tieneInventarioInicial(String idSistema) throws SQLException {
        return daoInventarioInicial.existeInventarioInicial(idSistema);
    }

    public BigDecimal obtenerCostoSugerido(String idSistema) throws SQLException {
        return servicioPonderado.obtenerUltimoPonderado(idSistema).getUltimoCosto();
    }

    public void guardar(List<LineaInventarioInicial> lineas, List<DetalleProducto> detalles) throws SQLException {
        if (lineas.isEmpty()) {
            throw new IllegalArgumentException("No hay productos para guardar");
        }

        registrarCatalogos(detalles);

        BigDecimal totalGeneral = BigDecimal.ZERO;
        for (LineaInventarioInicial linea : lineas) {
            totalGeneral = totalGeneral.add(linea.getTotalCosto());
        }

        boolean ingresoExistia = daoInventarioInicial.existeIngreso(INGRESO_INICIAL);
        if (!ingresoExistia) {
            daoInventarioInicial.insertarIngresoInicial(INGRESO_INICIAL, totalGeneral, LocalDate.now().toString(), metodosGenerales.hora(),
                    instancias.getUsuario(), instancias.getTerminal());
        }

        for (LineaInventarioInicial linea : lineas) {
            guardarRegistros(linea);
        }

        procesarMovimientos(lineas, detalles);

        if (ingresoExistia) {
            daoInventarioInicial.actualizarTotalIngreso(INGRESO_INICIAL, metodosGenerales.hora());
        }
    }

    private void guardarRegistros(LineaInventarioInicial linea) throws SQLException {
        ModeloProducto producto = linea.getProducto();

        daoInventarioInicial.insertarInventarioInicial(producto.getIdSistema(), linea.getCostoUnidad(), linea.getCantidad());
        daoInventarioInicial.insertarCompra(INGRESO_INICIAL, producto.getIdSistema(), producto.getDescripcion(), linea.getCantidad(),
                linea.getCostoUnidad(), linea.getTotalCosto(), instancias.getUsuario());
    }

    private void registrarCatalogos(List<DetalleProducto> detalles) throws SQLException {
        Set<String> colores = new LinkedHashSet<>();
        Set<String> tallas = new LinkedHashSet<>();
        for (DetalleProducto detalle : detalles) {
            if (detalle.getColor() != null && !detalle.getColor().trim().isEmpty()) {
                colores.add(detalle.getColor().trim().toUpperCase());
            }
            if (detalle.getTalla() != null && !detalle.getTalla().trim().isEmpty()) {
                tallas.add(detalle.getTalla().trim().toUpperCase());
            }
        }

        for (String color : colores) {
            if (!daoDetalleProducto.existeColor(color)) {
                daoDetalleProducto.crearColor(color);
            }
        }

        for (String talla : tallas) {
            if (!daoDetalleProducto.existeTalla(talla)) {
                daoDetalleProducto.crearTalla(talla);
            }
        }
    }

    private void procesarMovimientos(List<LineaInventarioInicial> lineas, List<DetalleProducto> detalles) throws SQLException {
        String idBodega = Bodegas.predeterminada();

        List<MovimientoInventario> movimientos = new ArrayList<>();
        for (LineaInventarioInicial linea : lineas) {
            ModeloProducto actual = daoProducto.obtenerDatosProducto(linea.getProducto().getIdSistema(), idBodega);
            movimientos.add(new MovimientoInventario(actual, linea.getCantidad(), linea.getCostoUnidad(), ""));
        }

        new ServicioInventario(movimientos, detalles, TipoDocumento.INVENTARIO_INICIAL,
                HistoricoPonderados.INVENTARIO_INICIAL.getNombre(), idBodega, instancias.getUsuario(), null).procesarMovimiento();
    }
}
