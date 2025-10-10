/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Terceros;

import DAO.Generales.DaoGenerales;
import Modelo.Terceros.ModeloDatosVehiculo;
import Utilidades.BaseDatos.MySql_connection;
import Utilidades.Constantes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author sebastian.londono
 */
public class DaoTerceros {

    DaoGenerales daoGenerales = new DaoGenerales();
    Connection conexion = MySql_connection.getInstancia(Constantes.BASE_DATOS_PRINCIPAL).getConnection();

    public ModeloDatosVehiculo obtenerDatosVehiculo(String ordenServicio) {
        String sql = "SELECT placa, tipo, modelo, numeroChasis, fechaCompra, marca, km, numeroMotor, color FROM bdOServicio WHERE id = '" + ordenServicio + "'";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ModeloDatosVehiculo datosVehiculo = new ModeloDatosVehiculo();
                    datosVehiculo.setPlaca(rs.getString("placa"));
                    datosVehiculo.setTipoVehiculo(rs.getString("tipo"));
                    datosVehiculo.setModelo(rs.getString("modelo"));
                    datosVehiculo.setNumeroChasis(rs.getString("numeroChasis"));
                    datosVehiculo.setFechaCompra(rs.getString("fechaCompra"));
                    datosVehiculo.setMarca(rs.getString("marca"));
                    datosVehiculo.setNumeroMotor(rs.getString("numeroMotor"));
                    datosVehiculo.setColor(rs.getString("color"));
                    return datosVehiculo;
                } else {
                    System.out.println("No se encontró datos del vehículo con OSERV: " + ordenServicio);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener los datos del vehículo: " + e.getMessage());
        }

        return null;
    }
}
