/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Configuraciones;

import dao.Generales.DaoGenerales;
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
        String sql = "SELECT idResolucion, descripcionResolucion, tipoResolucion, disenho, numeroResolucion, prefijo, "
                + "fechaInicio, fechaFinal, numeracionDel, numeracionHasta, consecutivo FROM bdResoluciones WHERE " + condicion;

        try (PreparedStatement stmt = conexion.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                ModeloResolucion resolucion = new ModeloResolucion();
                resolucion.setIdResolucion(rs.getInt("idResolucion"));
                resolucion.setDescripcionResolucion(rs.getString("descripcionResolucion"));
                resolucion.setTipoResolucion(rs.getString("tipoResolucion"));
                resolucion.setDisenho(rs.getString("disenho"));
                resolucion.setNumeroResolucion(rs.getString("numeroResolucion"));
                resolucion.setPrefijo(rs.getString("prefijo"));
                resolucion.setFechaFinal(rs.getString("fechaInicio"));
                resolucion.setFechaFinal(rs.getString("fechaFinal"));
                resolucion.setNumeracionDel(rs.getInt("numeracionDel"));
                resolucion.setNumeracionHasta(rs.getInt("numeracionHasta"));
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

    public DefaultTableModel obtenerResolucionesEnTabla() {
        String[] nombreColumnasBD = {
            "idResolucion", "descripcionResolucion", "tipoResolucion", "disenho", "numeroResolucion", "prefijo", "fechaInicio",
            "fechaFinal", "numeracionDel", "numeracionHasta", "consecutivo"
        };

        String[] nombreColumnasTabla = {
            "ID", "Descripción resolución", "Tipo de resolución", "Diseño", "Número resolución",
            "Prefijo", "Fecha inicio", "Fecha final", "# Del", "# Hasta", "# Actual", ""
        };

        String sql = "SELECT idResolucion, descripcionResolucion, tipoResolucion, disenho, numeroResolucion, prefijo, fechaInicio, "
                + "fechaFinal, numeracionDel, numeracionHasta, consecutivo FROM bdResoluciones";

        Object[][] datos = daoGenerales.obtenerDatosTabla(nombreColumnasBD, sql);

        return new DefaultTableModel(datos, nombreColumnasTabla) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return true;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return (columnIndex == 6 || columnIndex == 7) ? java.util.Date.class : Object.class;
            }
        };
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
}
