/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao.Configuraciones;

import Controlador.Alertas.ControladorAlertas;
import Dao.Generales.DaoGenerales;
import Modelo.Maestra.ModeloPrefijos;
import Modelo.Maestra.ModeloResolucion;
import Utilidades.BaseDatos.MySql_connection;
import Utilidades.Constantes;
import Utilidades.Fechas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author sebastian.londono
 */
public class DaoResoluciones {

    DaoGenerales daoGenerales = new DaoGenerales();
    Connection conexion = MySql_connection.getInstancia(Constantes.BASE_DATOS_PRINCIPAL).getConnection();

    public List<ModeloResolucion> obtenerResoluciones(String tipoMovimiento) {
        List<ModeloResolucion> listaResoluciones = new ArrayList<>();
        String condicion = obtenerCondicion(tipoMovimiento);
        // Las resoluciones inactivas no se ofrecen en los documentos
        String sql = "SELECT idResolucion, descripcionResolucion, tipoResolucion, disenho, numeroResolucion, prefijo, "
                + "fechaInicio, fechaFinal, numeracionDel, numeracionHasta, consecutivo FROM bdResoluciones WHERE estado = 1"
                + (condicion.isEmpty() ? "" : " AND (" + condicion + ")");

        try (PreparedStatement stmt = conexion.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                ModeloResolucion resolucion = new ModeloResolucion();
                resolucion.setIdResolucion(rs.getInt("idResolucion"));
                resolucion.setDescripcionResolucion(rs.getString("descripcionResolucion"));
                resolucion.setTipoResolucion(rs.getString("tipoResolucion"));
                resolucion.setDisenho(rs.getString("disenho"));
                resolucion.setNumeroResolucion(rs.getString("numeroResolucion"));
                resolucion.setPrefijo(rs.getString("prefijo"));
                resolucion.setFechaInicio(rs.getString("fechaInicio"));
                resolucion.setFechaFinal(rs.getString("fechaFinal"));
                resolucion.setNumeracionDel(rs.getInt("numeracionDel"));
                resolucion.setNumeracionHasta(rs.getInt("numeracionHasta"));
                resolucion.setConsecutivo(rs.getInt("consecutivo"));
                listaResoluciones.add(resolucion);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener resoluciones: " + e.getMessage());
        }

        return listaResoluciones;
    }

    private String obtenerCondicion(String tipoMovimiento) {
        switch (tipoMovimiento) {
            case "facturacion":
                return condicionParaFactura();
            case "ingreso":
                return condicionParaCompra();
            case "egreso":
                return condicionParaEgreso();
            default:
                return "";
        }
    }

    private String condicionParaEgreso() {
        return "tipoResolucion = 'Documento Soporte' ";
    }

    private String condicionParaFactura() {
        return "tipoResolucion = 'Factura Normal' || tipoResolucion = 'Facturación Electrónica' || tipoResolucion = 'Facturación Electrónica POS'";
    }

    private String condicionParaCompra() {
        return "tipoResolucion = 'Documento Soporte' || tipoResolucion = 'Compra Normal'";
    }

    /**
     * Listado de solo lectura para la maestra: la captura se hace en un modal,
     * por eso ninguna celda es editable. Las fechas llegan como yyyy-MM-dd y el
     * estado ya viene como etiqueta ACTIVA / INACTIVA.
     */
    public DefaultTableModel obtenerResolucionesEnTabla() {
        String[] nombreColumnasBD = {
            "idResolucion", "descripcionResolucion", "tipoResolucion", "disenho", "numeroResolucion", "prefijo", "fechaInicio",
            "fechaFinal", "numeracionDel", "numeracionHasta", "consecutivo", "estado"
        };

        String[] nombreColumnasTabla = {
            "ID", "Descripción resolución", "Tipo de resolución", "Diseño", "Número resolución",
            "Prefijo", "Fecha inicio", "Fecha final", "# Del", "# Hasta", "# Actual", "Estado"
        };

        String sql = "SELECT idResolucion, descripcionResolucion, tipoResolucion, disenho, numeroResolucion, prefijo, "
                + "DATE_FORMAT(fechaInicio, '%Y-%m-%d') AS fechaInicio, DATE_FORMAT(fechaFinal, '%Y-%m-%d') AS fechaFinal, "
                + "numeracionDel, numeracionHasta, consecutivo, "
                + "IF(estado = 1, '" + ESTADO_ACTIVA + "', '" + ESTADO_INACTIVA + "') AS estado "
                + "FROM bdResoluciones ORDER BY bdResoluciones.estado DESC, idResolucion";

        Object[][] datos = daoGenerales.obtenerDatosTabla(nombreColumnasBD, sql);

        return new DefaultTableModel(datos, nombreColumnasTabla) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }

    public static final String ESTADO_ACTIVA = "ACTIVA";
    public static final String ESTADO_INACTIVA = "INACTIVA";

    public boolean cambiarEstadoResolucion(int idResolucion, boolean activa) {
        String sql = "UPDATE bdResoluciones SET estado = ? WHERE idResolucion = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, activa ? 1 : 0);
            stmt.setInt(2, idResolucion);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al cambiar el estado de la resolución: " + e.getMessage());
            return false;
        }
    }

    /**
     * Aplica en una sola transacción los cambios hechos en la maestra:
     * resoluciones nuevas (id &lt; 0), resoluciones modificadas (id &gt; 0) y
     * cambios de estado. Si algo falla no se guarda nada.
     *
     * @param pendientes resoluciones nuevas o modificadas, con fechas dd-MM-yyyy
     * @param estados id de la resolución -&gt; activa
     */
    public boolean guardarCambios(java.util.Collection<ModeloResolucion> pendientes, java.util.Map<Integer, Boolean> estados) {
        String sqlInsertar = "INSERT INTO bdResoluciones (descripcionResolucion, tipoResolucion, disenho, numeroResolucion, "
                + "prefijo, fechaInicio, fechaFinal, numeracionDel, numeracionHasta, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String sqlModificar = "UPDATE bdResoluciones SET descripcionResolucion = ?, tipoResolucion = ?, disenho = ?, numeroResolucion = ?, "
                + "prefijo = ?, fechaInicio = ?, fechaFinal = ?, numeracionDel = ?, numeracionHasta = ? WHERE idResolucion = ?";
        String sqlEstado = "UPDATE bdResoluciones SET estado = ? WHERE idResolucion = ?";

        boolean autoCommitPrevio = true;
        try {
            autoCommitPrevio = conexion.getAutoCommit();
            conexion.setAutoCommit(false);

            try (PreparedStatement insertar = conexion.prepareStatement(sqlInsertar);
                    PreparedStatement modificar = conexion.prepareStatement(sqlModificar);
                    PreparedStatement estado = conexion.prepareStatement(sqlEstado)) {

                for (ModeloResolucion resolucion : pendientes) {
                    boolean nueva = resolucion.getIdResolucion() < 0;
                    PreparedStatement stmt = nueva ? insertar : modificar;
                    stmt.setString(1, resolucion.getDescripcionResolucion());
                    stmt.setString(2, resolucion.getTipoResolucion());
                    stmt.setString(3, resolucion.getDisenho());
                    stmt.setString(4, resolucion.getNumeroResolucion());
                    stmt.setString(5, resolucion.getPrefijo());
                    stmt.setDate(6, Fechas.convertirAFechaSQL(resolucion.getFechaInicio()));
                    stmt.setDate(7, Fechas.convertirAFechaSQL(resolucion.getFechaFinal()));
                    stmt.setInt(8, resolucion.getNumeracionDel());
                    stmt.setInt(9, resolucion.getNumeracionHasta());
                    if (nueva) {
                        Boolean activa = estados.get(resolucion.getIdResolucion());
                        stmt.setInt(10, activa == null || activa ? 1 : 0);
                    } else {
                        stmt.setInt(10, resolucion.getIdResolucion());
                    }
                    stmt.executeUpdate();
                }

                // Los estados de las nuevas ya se guardaron en el INSERT
                for (java.util.Map.Entry<Integer, Boolean> entrada : estados.entrySet()) {
                    if (entrada.getKey() > 0) {
                        estado.setInt(1, entrada.getValue() ? 1 : 0);
                        estado.setInt(2, entrada.getKey());
                        estado.executeUpdate();
                    }
                }
            }

            conexion.commit();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al guardar las resoluciones: " + e.getMessage());
            try {
                conexion.rollback();
            } catch (SQLException ex) {
                System.err.println("No se pudo revertir la transacción de resoluciones: " + ex.getMessage());
            }
            return false;
        } finally {
            try {
                conexion.setAutoCommit(autoCommitPrevio);
            } catch (SQLException e) {
                System.err.println("No se pudo restaurar el autocommit: " + e.getMessage());
            }
        }
    }

    public ModeloPrefijos obtenerPrefijos() {
        String sql = "SELECT prefijoAbonos, prefijoEgresos, prefijoNotaCredito, prefijoNotaDebito FROM bdLogErrores WHERE terminal = 'TERM-1'";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ModeloPrefijos prefijos = new ModeloPrefijos();
                    prefijos.setPrefijoAbonos(rs.getString("prefijoAbonos"));
                    prefijos.setPrefijoEgresos(rs.getString("prefijoEgresos"));
                    prefijos.setPrefijoNotaCredito(rs.getString("prefijoNotaCredito"));
                    prefijos.setPrefijoNotaDebito(rs.getString("prefijoNotaDebito"));
                    return prefijos;
                } else {
                    System.out.println("No se encontró prefijos con ID: TERM-1");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener la terminal: " + e.getMessage());
        }

        return null;
    }

    public boolean agregarResolucion(ModeloResolucion datosResolucion) {
        String sql = "INSERT INTO bdResoluciones (descripcionResolucion, tipoResolucion, disenho, numeroResolucion, "
                + "prefijo, fechaInicio, fechaFinal, numeracionDel, numeracionHasta) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, datosResolucion.getDescripcionResolucion());
            stmt.setString(2, datosResolucion.getTipoResolucion());
            stmt.setString(3, datosResolucion.getDisenho());
            stmt.setString(4, datosResolucion.getNumeroResolucion());
            stmt.setString(5, datosResolucion.getPrefijo());
            stmt.setDate(6, Fechas.convertirAFechaSQL(datosResolucion.getFechaInicio()));
            stmt.setDate(7, Fechas.convertirAFechaSQL(datosResolucion.getFechaFinal()));
            stmt.setInt(8, datosResolucion.getNumeracionDel());
            stmt.setInt(9, datosResolucion.getNumeracionHasta());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al insertar resolución: " + e.getMessage());
            return false;
        }
    }

    public boolean modificarResolucion(ModeloResolucion datosResolucion) {
        String sql = "UPDATE bdResoluciones SET descripcionResolucion = ?, tipoResolucion = ?, disenho = ?, numeroResolucion = ?, "
                + "prefijo = ?, fechaInicio = ?, fechaFinal = ?, numeracionDel = ?, numeracionHasta = ? WHERE idResolucion = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, datosResolucion.getDescripcionResolucion());
            stmt.setString(2, datosResolucion.getTipoResolucion());
            stmt.setString(3, datosResolucion.getDisenho());
            stmt.setString(4, datosResolucion.getNumeroResolucion());
            stmt.setString(5, datosResolucion.getPrefijo());
            stmt.setDate(6, Fechas.convertirAFechaSQL(datosResolucion.getFechaInicio()));
            stmt.setDate(7, Fechas.convertirAFechaSQL(datosResolucion.getFechaFinal()));
            stmt.setInt(8, datosResolucion.getNumeracionDel());
            stmt.setInt(9, datosResolucion.getNumeracionHasta());
            stmt.setInt(10, datosResolucion.getIdResolucion());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al modificar resolución: " + e.getMessage());
            return false;
        }
    }

    public boolean modificarPrefijos(ModeloPrefijos datosPrefijos) {
        String sql = "UPDATE bdLogErrores SET prefijoNotaCredito = ?, prefijoNotaDebito = ?, prefijoAbonos = ?, prefijoEgresos = ? WHERE terminal = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, datosPrefijos.getPrefijoNotaCredito());
            stmt.setString(2, datosPrefijos.getPrefijoNotaDebito());
            stmt.setString(3, datosPrefijos.getPrefijoAbonos());
            stmt.setString(4, datosPrefijos.getPrefijoEgresos());
            stmt.setString(5, datosPrefijos.getTerminal());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al modificar resolución: " + e.getMessage());
            return false;
        }
    }

    public ModeloResolucion obtenerInformacionResolucion(int idResolucion) {
        String sql = "SELECT idResolucion, descripcionResolucion, tipoResolucion, disenho, numeroResolucion, "
                + "prefijo, fechaInicio, fechaFinal, numeracionDel, numeracionHasta, consecutivo "
                + "FROM bdResoluciones WHERE idResolucion = " + idResolucion;

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ModeloResolucion resolucion = new ModeloResolucion();
                    resolucion.setIdResolucion(rs.getInt("idResolucion"));
                    resolucion.setDescripcionResolucion(rs.getString("descripcionResolucion"));
                    resolucion.setTipoResolucion(rs.getString("tipoResolucion"));
                    resolucion.setDisenho(rs.getString("disenho"));
                    resolucion.setNumeroResolucion(rs.getString("numeroResolucion"));
                    resolucion.setPrefijo(rs.getString("prefijo"));
                    resolucion.setFechaInicio(rs.getString("fechaInicio"));
                    resolucion.setFechaFinal(rs.getString("fechaFinal"));
                    resolucion.setNumeracionDel(rs.getInt("numeracionDel"));
                    resolucion.setNumeracionHasta(rs.getInt("numeracionHasta"));
                    resolucion.setConsecutivo(rs.getInt("consecutivo"));
                    return resolucion;
                } else {
                    System.out.println("No se encontró resolucion con ID: " + idResolucion);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener la terminal: " + e.getMessage());
        }

        return null;
    }

    public boolean aumentarConsecutivoResolucion(int idResolucion) {
        String sql = "UPDATE bdResoluciones SET consecutivo = consecutivo + 1 WHERE idResolucion = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, idResolucion);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al modificar resolución: " + e.getMessage());
            return false;
        }
    }

    public int obtenerConsecutivoResolucion(int idResolucion) {
        String sql = "SELECT consecutivo FROM bdResoluciones WHERE idResolucion = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, idResolucion);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("consecutivo");
                }
            }
        } catch (SQLException e) {
            ControladorAlertas.alertFail("Error consultando consecutivo resolución");
            System.err.println("Error al consultar consecutivo de resolución: " + e.getMessage());
        }

        return 0;
    }
}
