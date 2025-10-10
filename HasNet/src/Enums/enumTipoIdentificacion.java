/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enums;

/**
 *
 * @author sebastian.londono
 */
public class enumTipoIdentificacion {

    public enum TipoIdentificacion {

        CC("Cédula de ciudadanía", "CEDULA_CIUDADANIA"),
        CE("Cédula de extranjería", "CEDULA_EXTRANJERIA"),
        RC("Registro civil", "REGISTRO_CIVIL"),
        TI("Tarjeta de identidad", "TARJETA_IDENTIDAD"),
        PA("Pasaporte", "PASAPORTE"),
        NIT("Nit", "NIT");

        String tipo;
        String descripcion;

        TipoIdentificacion(String tipo, String descripcion) {
            this.tipo = tipo;
            this.descripcion = descripcion;
        }

        public String getValue() {
            return tipo;
        }

        public String getDescripcion() {
            return descripcion;
        }
    }

    public static String obtenerTipoIdentificacion(String descripcion) {
        String tipoIdentificacion = "";
        if (descripcion.equals("Cédula de ciudadanía")) {
            tipoIdentificacion = "CEDULA_CIUDADANIA";
        } else if (descripcion.equals("Nit")) {
            tipoIdentificacion = "NIT";
        } else if (descripcion.equals("Registro civil")) {
            tipoIdentificacion = "REGISTRO_CIVIL";
        } else if (descripcion.equals("Tarjeta de identidad")) {
            tipoIdentificacion = "TARJETA_IDENTIDAD";
        } else if (descripcion.equals("Cédula de extranjería")) {
            tipoIdentificacion = "CEDULA_EXTRANJERIA";
        } else if (descripcion.equals("Pasaporte")) {
            tipoIdentificacion = "PASAPORTE";
        }

        return tipoIdentificacion;
    }
}
