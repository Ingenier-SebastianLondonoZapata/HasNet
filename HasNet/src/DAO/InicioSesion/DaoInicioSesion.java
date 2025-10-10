/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.InicioSesion;

import DAO.Generales.DaoGenerales;
import Modelo.InicioSesion.Terminal;
import Utilidades.BaseDatos.MySql_connection;
import Utilidades.Constantes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sebastian.londono
 */
public class DaoInicioSesion {

    DaoGenerales daoGenerales = new DaoGenerales();
    Connection conexion = MySql_connection.getInstancia(Constantes.BASE_DATOS_PRINCIPAL).getConnection();

    public Terminal obtenerTerminal(String identificador) {
        String sql = "SELECT Id, codigo1, codigo2, terminal FROM bdLogErrores WHERE Id = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, identificador);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Terminal terminal = new Terminal();
                    terminal.setId(rs.getString("Id"));
                    terminal.setCodigo1(rs.getString("codigo1"));
                    terminal.setCodigo2(rs.getString("codigo2"));
                    terminal.setTerminal(rs.getString("terminal"));
                    return terminal;
                } else {
                    System.out.println("No se encontró una terminal con ID: " + identificador);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener la terminal: " + e.getMessage());
        }

        return null;
    }
    
    public List<Terminal> obtenerTerminales() {
        List<Terminal> listaTerminales = new ArrayList<>();
        String sql = "SELECT Id, codigo1, codigo2, terminal FROM bdLogErrores";

        try (PreparedStatement stmt = conexion.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Terminal terminal = new Terminal();
                    terminal.setId(rs.getString("Id"));
                    terminal.setCodigo1(rs.getString("codigo1"));
                    terminal.setCodigo2(rs.getString("codigo2"));
                    terminal.setTerminal(rs.getString("terminal"));

                listaTerminales.add(terminal);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener resoluciones: " + e.getMessage());
        }

        return listaTerminales;
    }
}
