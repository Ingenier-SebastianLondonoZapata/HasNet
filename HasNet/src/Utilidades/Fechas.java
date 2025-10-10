/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author sebastian.londono
 */
public class Fechas {

    public static String obtenerFechaActual() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public static java.sql.Date convertirAFechaSQL(String valorFecha) {
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date fecha = formato.parse(valorFecha);
            return new java.sql.Date(fecha.getTime());
        } catch (ParseException e) {
            System.err.println("Error al convertir a fecha SQL: " + e.getMessage());
            return null;
        }
    }

    public static String convertirFechaAString(Date fecha) {
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        return formato.format(fecha);
    }

    public static Date formatearFecha(String fecha) {
        try {
            String fechaNormalizada = fecha.substring(0, 10).replace('-', '/');

            SimpleDateFormat formatoEntrada = new SimpleDateFormat("yyyy/MM/dd");
            Date date = formatoEntrada.parse(fechaNormalizada);

            SimpleDateFormat formatoSalida = new SimpleDateFormat("dd/MM/yyyy");
            String fechaFormateada = formatoSalida.format(date);

            return formatoSalida.parse(fechaFormateada);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String formatearFecha1(String dato) {
        if (dato == null || dato.length() < 10) {
            return "";
        }

        try {
            String fechaNormalizada = dato.substring(0, 10).replace('-', '/');
            Date fecha = new SimpleDateFormat("yyyy/MM/dd").parse(fechaNormalizada);
            return new SimpleDateFormat("dd/MM/yyyy").format(fecha);
        } catch (ParseException e) {
            return "";
        }
    }
}
