package inventario.servicio;

import inventario.dao.DaoDetalleProducto;
import inventario.dao.DaoInventario;
import inventario.dao.DaoPonderado;
import Estrategia.AbstractDao;
import Modelo.Inventario.DetalleProducto;
import Modelo.Inventario.PonderadoPendiente;
import Utilidades.BaseDatos.SentenciaSql;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServicioTransaccionInventario extends AbstractDao {

    private static final Logger LOGGER = Logger.getLogger(ServicioTransaccionInventario.class.getName());
    private static final String NO_SE_PUDO_OBTENER_UNA_CONEXION_A_LA_BASE_DE_DATOS = "No se pudo obtener una conexión a la base de datos";
    private static final String ERROR_AL_REVERTIR_LA_TRANSACCIÓN_DE_INVENTARIO = "Error al revertir la transacción de inventario";
    private static final String ERROR_AL_RESTAURAR_EL_AUTOCOMMIT_DE_LA_CONEXION = "Error al restaurar el autocommit de la conexión";

    private final DaoInventario daoInventario;
    private final DaoPonderado daoPonderado;
    private final DaoDetalleProducto daoDetalleProducto;

    public ServicioTransaccionInventario() {
        this.daoInventario = new DaoInventario();
        this.daoPonderado = new DaoPonderado();
        this.daoDetalleProducto = new DaoDetalleProducto();
    }

    public void ejecutarIngreso(List<SentenciaSql> sqlInventario, List<PonderadoPendiente> ponderados, List<DetalleProducto> detalles,
            String usuario, String numeroDocumento) throws SQLException {

        Connection conn = getConnection();
        if (conn == null) {
            throw new SQLException(NO_SE_PUDO_OBTENER_UNA_CONEXION_A_LA_BASE_DE_DATOS);
        }

        try {
            conn.setAutoCommit(false);

            daoInventario.ejecutarSentencias(conn, sqlInventario);

            if (!detalles.isEmpty()) {
                for (DetalleProducto detalle : detalles) {
                    daoDetalleProducto.guardarDetalle(conn, detalle, usuario, numeroDocumento);
                }
                daoDetalleProducto.actualizarConsecutivo(conn, detalles.size());
            }

            for (PonderadoPendiente ponderado : ponderados) {
                daoPonderado.guardarHistorico(conn, ponderado, usuario, numeroDocumento);
                daoPonderado.actualizarUltimoPonderado(conn, ponderado.getProducto(), ponderado, usuario, numeroDocumento);
            }

            conn.commit();
        } catch (SQLException e) {
            revertir(conn);
            throw e;
        } finally {
            restaurarAutoCommit(conn);
        }
    }

    private void revertir(Connection conn) {
        try {
            conn.rollback();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ERROR_AL_REVERTIR_LA_TRANSACCIÓN_DE_INVENTARIO, ex);
        }
    }

    private void restaurarAutoCommit(Connection conn) {
        try {
            conn.setAutoCommit(true);
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ERROR_AL_RESTAURAR_EL_AUTOCOMMIT_DE_LA_CONEXION, ex);
        }
    }
}
