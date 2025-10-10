/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Inventario;

import DAO.Generales.DaoGenerales;
import Enums.enumTipoDocumento;
import Utilidades.BaseDatos.MySql_connection;
import Utilidades.Constantes;
import Utilidades.Inventario.DatosMovimientoInventario;
import Utilidades.Inventario.MovimientoInventario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author sebastian.londono
 */
public class DaoInventario {

    DaoGenerales daoGenerales = new DaoGenerales();
    Connection conexion = MySql_connection.getInstancia(Constantes.BASE_DATOS_PRINCIPAL).getConnection();

    private void construirParametros(PreparedStatement ps, DatosMovimientoInventario datosMovimiento) throws SQLException {
        for (MovimientoInventario producto : datosMovimiento.getProductos()) {

            String tipoMovimiento = datosMovimiento.getTipoMovimiento();
            switch (tipoMovimiento) {
                case "facturacion":
                    ps.setDouble(1, producto.getCantidad());
                    ps.setDouble(2, producto.getCantidad());
                    ps.setDouble(3, producto.getCantidad());
                    ps.setString(2, producto.getIdProducto());
                    ps.addBatch();
                default:
                //"hello";
                }
        }
    }

    public void actualizarInventarioBatch(String sql, DatosMovimientoInventario datosMovimiento) throws SQLException {

        PreparedStatement ps = null;

        try {
            conexion.setAutoCommit(false);
            ps = conexion.prepareStatement(sql);
            construirParametros(ps, datosMovimiento);
            ps.executeBatch();
            conexion.commit();
        } catch (SQLException e) {
            if (conexion != null) {
                try {
                    conexion.rollback();
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conexion != null) {
                conexion.setAutoCommit(true);
            }
        }
    }

}
