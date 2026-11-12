package Dao.Ventas;

import Dao.Documentos.DaoDocumentos;
import Enums.TipoDocumento;
import Utilidades.BaseDatos.MySql_connection;
import Utilidades.Constantes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoPlanSepare {

    private final DaoDocumentos daoDocumentos = new DaoDocumentos();

    private Connection conexion() {
        return MySql_connection.getInstancia(Constantes.BASE_DATOS_PRINCIPAL).getConnection();
    }

    public boolean modificarEstadoPlanSepare(String estado, String idFacturaGenerada, String idDocumento) {
        return daoDocumentos.actualizarEstadoPorIdFactura(TipoDocumento.PLAN_SEPARE, idDocumento, estado, idFacturaGenerada);
    }

    public boolean esFacturaDePlanSepare(String idFactura) {
        String sql = "SELECT 1 FROM bdDocumentos WHERE tipoDocumento = ? AND estado2 = ? LIMIT 1";

        try (PreparedStatement stmt = conexion().prepareStatement(sql)) {
            stmt.setString(1, TipoDocumento.PLAN_SEPARE.getValor());
            stmt.setString(2, idFactura);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar la factura del plan separe: " + e.getMessage());
            return false;
        }
    }

    public boolean estaFacturado(String idPlanSepare) {
        String sql = "SELECT 1 FROM bdDocumentos WHERE tipoDocumento = ? AND idFactura = ? "
                + "AND estado2 IS NOT NULL AND estado2 <> '' LIMIT 1";

        try (PreparedStatement stmt = conexion().prepareStatement(sql)) {
            stmt.setString(1, TipoDocumento.PLAN_SEPARE.getValor());
            stmt.setString(2, idPlanSepare);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar si el plan separe está facturado: " + e.getMessage());
            return false;
        }
    }
}
