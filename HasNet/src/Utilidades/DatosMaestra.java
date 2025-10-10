/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

/**
 *
 * @author sebastian.londono
 */
public class DatosMaestra {

    public Object[] datosMaestra;

    private static String responsabilidadesFiscales;
    private static String cantidadEstablecidaAlCargar;
    private static String focoDespuesDeCargarProducto;

    public static String getResponsabilidadesFiscales() {
        return responsabilidadesFiscales;
    }

    public static String getCantidadEstablecidaAlCargar() {
        return cantidadEstablecidaAlCargar;
    }

    public static String getFocoDespuesDeCargarProducto() {
        return focoDespuesDeCargarProducto;
    }

    public void setearDatosMaestra(Object[] datosMaestra) {
        System.out.println("-------------------- Se cargaron los datos en la maestra --------------------");
        this.datosMaestra = datosMaestra;

        cantidadEstablecidaAlCargar = datosMaestra[87] != null ? datosMaestra[87].toString() : "1";
        focoDespuesDeCargarProducto = datosMaestra[97] != null ? datosMaestra[97].toString() : "Cant";
        responsabilidadesFiscales = obtenerResponsabilidadesFiscales();
    }

    private String obtenerResponsabilidadesFiscales() {
        if (datosMaestra[113] == null) {
            return "";
        }

        String texto = datosMaestra[113].toString().trim();
        if (texto.isEmpty()) {
            return "";
        }

        String[] responsabilidades = texto.split(", ");
        StringBuilder sb = new StringBuilder();

        for (String resp : responsabilidades) {
            String parte = resp.split("\\s*/")[0].trim();

            if (!parte.isEmpty()) {
                if (sb.length() > 0) {
                    sb.append(';');
                }
                sb.append(parte);
            }
        }

        return sb.toString();
    }
}
