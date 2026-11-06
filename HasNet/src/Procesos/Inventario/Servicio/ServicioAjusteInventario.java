package Procesos.Inventario.Servicio;

import Dao.Productos.DaoAjusteInventario;
import Dao.Productos.DaoProducto;
import Modelo.Inventario.DetalleProducto;
import Modelo.Inventario.MovimientoInventario;
import Modelo.Productos.LineaAjusteInventario;
import Modelo.Productos.ModeloAjusteInventario;
import Modelo.Productos.ModeloProducto;
import Utilidades.Contactos.Bodegas;
import clases.Instancias;
import clases.metodosGenerales;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServicioAjusteInventario {

    private static final Logger LOGGER = Logger.getLogger(ServicioAjusteInventario.class.getName());

    public static final String CONSECUTIVO = "TRAS";
    public static final String PREFIJO = CONSECUTIVO + "-";

    private final Instancias instancias = Instancias.getInstancias();
    private final metodosGenerales metodos = new metodosGenerales();
    private final DaoAjusteInventario daoAjusteInventario = new DaoAjusteInventario();
    private final DaoProducto daoProducto = new DaoProducto();

    public String siguienteConsecutivo() throws SQLException {
        return daoAjusteInventario.obtenerConsecutivo(CONSECUTIVO);
    }

    public String siguienteDocumento() throws SQLException {
        return PREFIJO + siguienteConsecutivo();
    }

    public String guardar(ModeloAjusteInventario ajuste) throws SQLException {
        validar(ajuste);

        ajuste.setDocumento(siguienteDocumento());
        ajuste.setFecha(metodos.fechaConsulta(metodosGenerales.fecha()));
        ajuste.setHora(metodosGenerales.hora());
        ajuste.setUsuario(instancias.getUsuario());
        ajuste.setTerminal(instancias.getTerminal());
        ajuste.setIdBodega(Bodegas.resolver(ajuste.getIdBodega()));

        daoAjusteInventario.guardar(ajuste);

        try {
            procesarInventario(ajuste);
        } catch (SQLException | RuntimeException e) {
            deshacer(ajuste.getDocumento());
            throw e;
        }

        aumentarConsecutivo();

        return ajuste.getDocumento();
    }

    public ModeloAjusteInventario obtener(String documento) throws SQLException {
        return daoAjusteInventario.obtener(documento);
    }

    public void anular(String documento) throws SQLException {
        ModeloAjusteInventario ajuste = daoAjusteInventario.obtener(documento);

        if (ajuste == null) {
            throw new IllegalArgumentException("El documento no existe: " + documento);
        }

        if (ajuste.estaAnulado()) {
            throw new IllegalStateException("El documento ya se encuentra anulado: " + documento);
        }

        ajuste.setIdBodega(Bodegas.resolver(ajuste.getIdBodega()));

        if (!daoAjusteInventario.anular(documento)) {
            throw new SQLException("No se pudo anular el documento: " + documento);
        }

        try {
            new ServicioInventario(
                    movimientos(ajuste),
                    new ArrayList<>(),
                    ajuste.getTipoDocumentoAnulacion(),
                    ajuste.getDocumento(),
                    ajuste.getIdBodega(),
                    instancias.getUsuario(),
                    null).procesarMovimiento();
        } catch (SQLException | RuntimeException e) {
            reactivar(documento);
            throw e;
        }
    }

    private void reactivar(String documento) {
        try {
            daoAjusteInventario.reactivar(documento);
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "No se pudo reactivar el ajuste " + documento, ex);
        }
    }

    public void validar(ModeloAjusteInventario ajuste) {
        if (ajuste.getLineas().isEmpty()) {
            throw new IllegalArgumentException("No ha añadido ningun producto");
        }

        for (LineaAjusteInventario linea : ajuste.getLineas()) {
            if (linea.getCantidad().compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("Las cantidades deben ser mayor a 0");
            }

            if (linea.getValorUnitario().compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("Los valores deben ser mayor a " + instancias.getSimbolo() + " 0");
            }
        }
    }

    private void procesarInventario(ModeloAjusteInventario ajuste) throws SQLException {
        List<DetalleProducto> detalles = ajuste.esEntrada()
                ? ajuste.getDetallesProductos()
                : new ArrayList<>();

        new ServicioInventario(
                movimientos(ajuste),
                detalles,
                ajuste.getTipoDocumento(),
                ajuste.getDocumento(),
                ajuste.getIdBodega(),
                ajuste.getUsuario(),
                null).procesarMovimiento();
    }

    private List<MovimientoInventario> movimientos(ModeloAjusteInventario ajuste) {
        List<MovimientoInventario> movimientos = new ArrayList<>();

        for (LineaAjusteInventario linea : ajuste.getLineas()) {
            ModeloProducto producto = daoProducto.obtenerDatosProducto(linea.getIdSistema(), ajuste.getIdBodega());
            if (producto.getIdSistema() == null) {
                throw new IllegalArgumentException("El producto no existe: " + linea.getIdSistema());
            }

            movimientos.add(new MovimientoInventario(producto, linea.getCantidadInventario(), BigDecimal.ZERO, linea.getIdDetalleProducto()));
        }

        return movimientos;
    }

    private void deshacer(String documento) {
        try {
            daoAjusteInventario.eliminar(documento);
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "No se pudo deshacer el ajuste " + documento, ex);
        }
    }

    private void aumentarConsecutivo() throws SQLException {
        int siguiente = Integer.parseInt(siguienteConsecutivo()) + 1;
        daoAjusteInventario.actualizarConsecutivo(CONSECUTIVO, siguiente);
    }
}
