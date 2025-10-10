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
public class enumTipoDocumento {

    public enum TipoDocumento {

        FACTURACION("facturacion"),
        PEDIDO("pedido"),
        MESA("mesa"),
        ORDER_SERVICIO("orden"),
        CUENTA_COBRO("cuentaCobro"),
        COTIZACION("cotizacion"),
        PLAN_SEPARE("separe"),
        CREDITO("credito"),
        
        NOTA_DEBITO("notaDebito"),
        NOTA_CREDITO("notaCredito"),
        
        COMPRA("ingreso"),
        ORDER_COMPRA("ordenCompra"),
        EGRESO("egreso"),
        
        ANULACION_FACTURACION("anulacion_facturacion");
        
        String tipo;

        TipoDocumento(String tipo) {
            this.tipo = tipo;
        }

        public String getValue() {
            return tipo;
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
