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
public class enumBodegas {

    public enum TipoBodega {
        BODEGA_PRINCIPAL("123-22", "bdProductos"),
        PRIMERA_BODEGA("BODEGA-1", "bdProductosBodega1"),
        SEGUNDA_BODEGA("BODEGA-2", "bdProductosBodega2"),
        TERCERA_BODEGA("BODEGA-3", "bdProductosBodega3"),
        CUARTA_BODEGA("BODEGA-4", "bdProductosBodega4");

        String bodega;
        String nombreTabla;

        TipoBodega(String bodega, String nombreTabla) {
            this.bodega = bodega;
            this.nombreTabla = nombreTabla;
        }

        public String getValue() {
            return bodega;
        }

        public String getNombreTabla() {
            return nombreTabla;
        }
    }

    public static String obtenerBodega(String bodega) {
        for (TipoBodega tipo : TipoBodega.values()) {
            if (tipo.getValue().equals(bodega)) {
                return tipo.getValue();
            }
        }
        return "";
    }

    public static String obtenerNombreTablaBodega(String bodega, boolean esInventarioPorBodega) {
        String nombreTabla = TipoBodega.BODEGA_PRINCIPAL.getNombreTabla();

        if (esInventarioPorBodega) {
            for (TipoBodega tipo : TipoBodega.values()) {
                if (tipo.getValue().equals(bodega)) {
                    nombreTabla = tipo.getNombreTabla();
                }
            }
        }
        
        return nombreTabla;
    }

}
