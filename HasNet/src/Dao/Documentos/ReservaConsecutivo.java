package Dao.Documentos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReservaConsecutivo implements AutoCloseable {

    private static final Logger LOGGER = Logger.getLogger(ReservaConsecutivo.class.getName());

    private final Connection conexion;
    private final String sentenciaActualizar;
    private final Object llave;
    private final String prefijo;
    private int numero;
    private boolean confirmada;

    ReservaConsecutivo(Connection conexion, String sentenciaActualizar, Object llave, String prefijo, int numero) {
        this.conexion = conexion;
        this.sentenciaActualizar = sentenciaActualizar;
        this.llave = llave;
        this.prefijo = prefijo == null ? "" : prefijo;
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public String getPrefijo() {
        return prefijo;
    }

    public void avanzar() {
        numero++;
    }

    public void confirmar() throws SQLException {
        try (PreparedStatement ps = conexion.prepareStatement(sentenciaActualizar)) {
            ps.setString(1, String.valueOf(numero + 1));
            ps.setObject(2, llave);
            ps.executeUpdate();
        }
        conexion.commit();
        confirmada = true;
    }

    @Override
    public void close() {
        try {
            if (!confirmada) {
                conexion.rollback();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al liberar la reserva del consecutivo " + llave, e);
        } finally {
            try {
                conexion.close();
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Error al cerrar la conexión de la reserva " + llave, e);
            }
        }
    }
}
