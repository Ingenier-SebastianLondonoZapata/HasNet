package Procesos.Multiempresa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

final class DatosBaseEmpresa {

    private static final Logger LOGGER = Logger.getLogger(DatosBaseEmpresa.class.getName());

    private static final String[][] RESOLUCIONES_BASE = {
        {"FACTURA NORMAL", "Factura Normal"},
        {"COMPRA NORMAL", "Compra Normal"}
    };

    private DatosBaseEmpresa() {
    }

    static void asegurar(Connection conexion, String esquema) throws SQLException {
        String tabla = EsquemaEmpresa.tabla(esquema, "bdResoluciones");
        for (String[] resolucion : RESOLUCIONES_BASE) {
            try (PreparedStatement insertar = conexion.prepareStatement("INSERT INTO " + tabla
                    + " (descripcionResolucion, tipoResolucion, disenho, numeroResolucion, prefijo, fechaInicio, fechaFinal, "
                    + "numeracionDel, numeracionHasta, consecutivo, estado) "
                    + "SELECT ?, ?, '', '0000000000000', '', CURDATE(), DATE_ADD(CURDATE(), INTERVAL 10 YEAR), 1, 99999999, 1, 1 FROM DUAL "
                    + "WHERE NOT EXISTS (SELECT 1 FROM " + tabla + " WHERE tipoResolucion = ?)")) {
                insertar.setString(1, resolucion[0]);
                insertar.setString(2, resolucion[1]);
                insertar.setString(3, resolucion[1]);
                if (insertar.executeUpdate() > 0) {
                    LOGGER.log(Level.INFO, "Resolución base {0} creada en {1}", new Object[]{resolucion[1], esquema});
                }
            }
        }
    }
}
