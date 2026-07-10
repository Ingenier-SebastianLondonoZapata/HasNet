package dao.Ventas;

import Modelo.Ventas.ModeloComanda;
import Utilidades.BaseDatos.MySql_connection;
import Utilidades.Constantes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoComanda {

    private final Connection conexion;

    public DaoComanda() {
        this.conexion = MySql_connection.getInstancia(Constantes.BASE_DATOS_PRINCIPAL).getConnection();
    }

    public boolean agregarComanda(String congelada, String factura, String codigo, String producto,
            String opciones, String ingredientes, String adiciones, String aderezos,
            String cantidad, String observaciones, int turno, String pedido,
            String consecutivo) {
        String instruccionSql = "INSERT INTO bdComanda (congelada, factura, cod, producto, opciones, "
                + "ingredientes, adiciones, aderezos, cant, observaciones, turno, pedido, consecutivo) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conexion.prepareStatement(instruccionSql)) {
            pstmt.setString(1, congelada);
            pstmt.setString(2, factura);
            pstmt.setString(3, codigo);
            pstmt.setString(4, producto);
            pstmt.setString(5, opciones);
            pstmt.setString(6, ingredientes);
            pstmt.setString(7, adiciones);
            pstmt.setString(8, aderezos);
            pstmt.setString(9, cantidad);
            pstmt.setString(10, observaciones);
            pstmt.setInt(11, turno);
            pstmt.setString(12, pedido);
            pstmt.setString(13, consecutivo);

            int filasInsertadas = pstmt.executeUpdate();
            return filasInsertadas > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DaoComanda.class.getName()).log(Level.SEVERE,
                    "Error al insertar comanda: " + factura + " / " + codigo, ex);
            return false;
        }
    }

    public boolean agregarComandaEnBatch(List<ModeloComanda> comandas) {
        if (comandas == null || comandas.isEmpty()) {
            return true;
        }

        boolean todasCorrectas = true;
        for (ModeloComanda comanda : comandas) {
            String cantidadStr = comanda.getCantidad() != null
                    ? comanda.getCantidad().toPlainString()
                    : "0";

            boolean resultado = agregarComanda(
                    comanda.getCongelada(),
                    comanda.getFactura(),
                    comanda.getCodigo(),
                    comanda.getProducto(),
                    comanda.getOpciones(),
                    comanda.getIngredientes(),
                    comanda.getAdiciones(),
                    comanda.getAderezos(),
                    cantidadStr,
                    comanda.getObservaciones(),
                    comanda.getTurno(),
                    comanda.getPedido(),
                    comanda.getConsecutivo()
            );

            if (!resultado) {
                todasCorrectas = false;
                Logger.getLogger(DaoComanda.class.getName()).log(Level.SEVERE,
                        "Error al insertar comanda: {0} / {1}", new Object[]{comanda.getFactura(), comanda.getCodigo()});
            }
        }

        return todasCorrectas;
    }

    public Connection getConexion() {
        return conexion;
    }
}
