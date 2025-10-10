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
public class enumTipoPersona {

    public enum TipoPersona {

        NATURAL("Persona natural", "NATURAL"),
        JURIDICA("Persona juridica", "JURIDICA");

        String tipo;
        String descripcion;

        TipoPersona(String tipo, String descripcion) {
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

    public static String obtenerTipoPersona(String naturaleza) {
        String tipoPersona = "";
        if (naturaleza != null) {
            if (naturaleza.equals("Persona juridica")) {
                tipoPersona = "JURIDICA";
            } else {
                tipoPersona = "NATURAL";
            }
        }

        return tipoPersona;
    }
}
