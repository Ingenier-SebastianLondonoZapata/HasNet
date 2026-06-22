/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Generales;

import Utilidades.BaseDatos.MySql_connection;
import Utilidades.Constantes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sebastian.londono
 */
public class DaoGenerales {

    Connection conexion = MySql_connection.getInstancia(Constantes.BASE_DATOS_PRINCIPAL).getConnection();

    public Object[][] obtenerDatosTabla(String[] columnas, String sql) {
        List<Object[]> filas = new ArrayList<>();

        try (PreparedStatement stmt = conexion.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Object[] fila = new Object[columnas.length];
                for (int i = 0; i < columnas.length; i++) {
                    Object valor = rs.getObject(columnas[i]);

                    if (valor instanceof Timestamp) {
                        valor = new java.util.Date(((Timestamp) valor).getTime());
                    }

                    fila[i] = valor;
                }
                filas.add(fila);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener datos: " + e.getMessage());
        }

        return filas.toArray(new Object[0][]);
    }

}
