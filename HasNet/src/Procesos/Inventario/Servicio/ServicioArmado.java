package Procesos.Inventario.Servicio;

import Enums.TipoDocumento;
import Modelo.Inventario.DetalleProducto;
import Modelo.Inventario.MovimientoInventario;
import Modelo.Productos.LineaArmado;
import Modelo.Productos.ModeloArmado;
import Modelo.Productos.ModeloProducto;
import Dao.Productos.DaoArmado;
import Dao.Productos.DaoProducto;
import Utilidades.Contactos.Bodegas;
import clases.Instancias;
import clases.metodosGenerales;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServicioArmado {

    private static final Logger LOGGER = Logger.getLogger(ServicioArmado.class.getName());

    public static final String CONSECUTIVO = "CST";
    public static final String PREFIJO = CONSECUTIVO + "-";

    private final Instancias instancias = Instancias.getInstancias();
    private final metodosGenerales metodos = new metodosGenerales();
    private final DaoArmado daoArmado = new DaoArmado();
    private final DaoProducto daoProducto = new DaoProducto();

    public String siguienteConsecutivo() {
        return String.valueOf(instancias.getSql().getNumConsecutivo(CONSECUTIVO)[0]);
    }

    public String siguienteDocumento() {
        return PREFIJO + siguienteConsecutivo();
    }

    public ModeloArmado obtener(String documento) throws SQLException {
        return daoArmado.obtener(documento);
    }
    
    public String armar(ModeloArmado armado) throws SQLException {
        validar(armado);

        armado.setDocumento(siguienteDocumento());
        armado.setFecha(metodos.fechaConsulta(metodosGenerales.fecha()));
        armado.setUsuario(instancias.getUsuario());
        armado.setIdBodega(Bodegas.resolver(armado.getIdBodega()));

        daoArmado.guardar(armado);

        try {
            procesarInventario(armado, TipoDocumento.ARMADO);
        } catch (SQLException | RuntimeException e) {
            deshacer(armado.getDocumento());
            throw e;
        }

        aumentarConsecutivo();

        return armado.getDocumento();
    }

    public void anular(String documento) throws SQLException {
        ModeloArmado armado = daoArmado.obtener(documento);

        if (armado == null) {
            throw new IllegalArgumentException("El documento no existe: " + documento);
        }

        if (armado.estaAnulado()) {
            throw new IllegalStateException("El documento ya se encuentra anulado: " + documento);
        }

        armado.setIdBodega(Bodegas.resolver(armado.getIdBodega()));

        if (!daoArmado.anular(documento)) {
            throw new SQLException("No se pudo anular el documento: " + documento);
        }

        try {
            procesarInventario(armado, TipoDocumento.ANULAR_ARMADO);
        } catch (SQLException | RuntimeException e) {
            reactivar(documento);
            throw e;
        }
    }

    private void reactivar(String documento) {
        try {
            daoArmado.reactivar(documento);
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "No se pudo reactivar el armado " + documento, ex);
        }
    }

    private void deshacer(String documento) {
        try {
            daoArmado.eliminar(documento);
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "No se pudo deshacer el armado " + documento, ex);
        }
    }

    private void procesarInventario(ModeloArmado armado, TipoDocumento tipoDocumento) throws SQLException {
        new ServicioInventario(
                movimientos(armado),
                Collections.<DetalleProducto>emptyList(),
                tipoDocumento,
                armado.getDocumento(),
                armado.getIdBodega(),
                instancias.getUsuario(),
                null).procesarMovimiento();
    }

    private List<MovimientoInventario> movimientos(ModeloArmado armado) {
        List<MovimientoInventario> movimientos = new ArrayList<>();

        ModeloProducto terminado = daoProducto.obtenerDatosProducto(armado.getProducto(), armado.getIdBodega());

        if (terminado.getIdSistema() == null) {
            throw new IllegalArgumentException("El producto a armar no existe: " + armado.getProducto());
        }

        movimientos.add(new MovimientoInventario(terminado, armado.getUnidades(), BigDecimal.ZERO, ""));

        for (LineaArmado linea : armado.getLineas()) {
            ModeloProducto insumo = daoProducto.obtenerDatosProducto(linea.getProducto(), armado.getIdBodega());
            if (insumo.getIdSistema() == null) {
                continue;
            }

            movimientos.add(new MovimientoInventario(insumo, linea.getCantidadTotal(), BigDecimal.ZERO, "", true));
        }

        return movimientos;
    }

    private void validar(ModeloArmado armado) {
        if (armado.getProducto() == null || armado.getProducto().trim().isEmpty()) {
            throw new IllegalArgumentException("El armado no tiene producto");
        }

        if (armado.getLineas().isEmpty()) {
            throw new IllegalArgumentException("El armado no tiene insumos");
        }

        if (armado.getUnidades().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Las unidades a armar deben ser mayores a cero");
        }
    }

    private void aumentarConsecutivo() throws SQLException {
        int siguiente = Integer.parseInt(siguienteConsecutivo()) + 1;

        if (!instancias.getSql().aumentarConsecutivo(CONSECUTIVO, siguiente)) {
            throw new SQLException("No se pudo aumentar el consecutivo " + CONSECUTIVO);
        }
    }
}
