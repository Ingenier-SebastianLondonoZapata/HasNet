/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Egresos;

import DAO.Generales.DaoGenerales;
import Modelo.Egresos.ModeloDetalleEgreso;
import Utilidades.BaseDatos.MySql_connection;
import Utilidades.Constantes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author sebastian.londono
 */
public class DaoEgresos {

    DaoGenerales daoGenerales = new DaoGenerales();
    Connection conexion = MySql_connection.getInstancia(Constantes.BASE_DATOS_PRINCIPAL).getConnection();

    public boolean agregarDetalleEgreso(ModeloDetalleEgreso detalleEgreso) {
        String sql = "INSERT INTO bdEgresoCods (egreso, codigo, descripcion, valor, "
                + "subtotal, iva, porcentajeIva, factura, codigoUsuario) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, detalleEgreso.getEgreso());
            stmt.setString(2, detalleEgreso.getCodigo());
            stmt.setString(3, detalleEgreso.getDescripcion());
            stmt.setBigDecimal(4, detalleEgreso.getValor());
            stmt.setBigDecimal(5, detalleEgreso.getSubtotal());
            stmt.setBigDecimal(6, detalleEgreso.getIva());
            stmt.setInt(7, detalleEgreso.getPorcentajeIva());
            stmt.setString(8, detalleEgreso.getFactura());
            stmt.setString(9, detalleEgreso.getCodigoUsuario());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al insertar el detalle del egreso: " + e.getMessage());
            return false;
        }
    }
}
