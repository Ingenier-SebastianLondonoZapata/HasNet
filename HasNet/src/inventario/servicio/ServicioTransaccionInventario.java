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

public class ServicioTransaccionInventario extends AbstractDao {

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
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }
}
