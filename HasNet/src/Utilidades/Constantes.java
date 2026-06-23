/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author sebastian.londono
 */
public class Constantes {

    public static final int MAX_DECIMALES_CANTIDAD = 4;

    public static final String CLIENTE_POR_DEFECTO = "1010";

    public static final String FACTURACION_NORMAL = "Facturación Normal";
    public static final String FACTURACION_ELECTRONICA = "Facturación Electrónica";
    public static final String FACTURACION_ELECTRONICA_POS = "Facturación Electrónica POS";
    public static final String DOCUMENTO_SOPORTE = "Documento Soporte";

    public static final String EGRESO_NORMAL = "Egreso Normal";
    public static final String COMPRA_NORMAL = "Compra Normal";

    public static final String BASE_DATOS_PRINCIPAL = "bdClick";

    private static final String RUTA_ARCHIVO_TERMINAL = "C:\\Java.old\\conf.ck";
    private static final String RUTA_ARCHIVO_ID_CLIENTE = "C:\\Java.old\\IdentificadorCliente.txt";

    public static final int CANAL_PRUEBAS = 37;
    public static final int CANAL_PRODUCCION = 37;

    public static final int LONGITUD_MAXIMA_DESCRIPCION_PRODUCTOS = 255;
    public static final int LONGITUD_MINIMA_DESCRIPCION_PRODUCTOS = 5;

    public static final int COLUMNA_CODIGO_PRODUCTO = 0;
    public static final int COLUMNA_DESCRIPCION_PRODUCTO = 1;
    public static final int COLUMNA_VALOR_PRODUCTO = 2;
    public static final int COLUMNA_CANTIDAD = 3;
    public static final int COLUMNA_SUBTOTAL = 4;
    public static final int COLUMNA_PORCENTAJE_IVA = 7;
    public static final int COLUMNA_VALOR_IMPOCONSUMO = 8;
    public static final int COLUMNA_PORCENTAJE_IMPOCONSUMO = 23;

    public static final int COLUMNA_ID_SISTEMA = 32;
    public static final int COLUMNA_ID_SISTEMA_NOTA_CREDITO = 0;

    public static final int COLUMNA_VALOR_IVA = 33;
    public static final int COLUMNA_UNIDAD_MEDIDA = 35;

    public static final int COLUMNA_UNIDAD_MEDIDA_NOTA_DEBITO = 36;

    public static final List<Integer> TARIFAS_IVAS_PERMITIDOS = Arrays.asList(0, 5, 16, 19);
    public static final List<Integer> TARIFAS_IMPOCONSUMO_PERMITIDOS = Arrays.asList(0, 2, 4, 8, 16);

    public static boolean esFacturacionElectronica(String tipoFacturacion) {
        return FACTURACION_ELECTRONICA.equals(tipoFacturacion) || FACTURACION_ELECTRONICA_POS.equals(tipoFacturacion);
    }

    public static boolean esDocumentoSoporte(String tipoDocumento) {
        return DOCUMENTO_SOPORTE.equals(tipoDocumento);
    }

    public static String obtenerRegimen(boolean esResponsableIva) {
        String regimenAdquirente = "SIMPLE";
        if (esResponsableIva) {
            regimenAdquirente = "ORDINARIO";
        }

        return regimenAdquirente;
    }

    public static String leerIdentificadorCliente() {
        String texto = "", resultado = "";
        try {
            FileReader lector = new FileReader(RUTA_ARCHIVO_ID_CLIENTE);
            BufferedReader contenido = new BufferedReader(lector);
            while ((texto = contenido.readLine()) != null) {
                resultado = resultado + texto;
            }
        } catch (Exception e) {
            System.out.println("Error al leer el archivo de propiedades: + " + e);
        }

        return resultado;
    }

    public static String leerArchivoTerminal() {
        String texto = "", resultado = "";

        try {
            FileReader lector = new FileReader(RUTA_ARCHIVO_TERMINAL);
            BufferedReader contenido = new BufferedReader(lector);
            while ((texto = contenido.readLine()) != null) {
                resultado = resultado + texto;
            }
        } catch (Exception e) {
            System.out.println("Error al leer archivo de terminal " + e);
        }

        return resultado;
    }

    public static Map<String, String> obtenerListadoPrefijosDocumento() {
        Map<String, String> prefijos = new HashMap<>();
        prefijos.put("FACTURA", "FACT-");
        prefijos.put("PLAN SEPARE", "SEPARE-");
        prefijos.put("COTIZACIÓN", "COTI-");
        prefijos.put("PEDIDOS", "PEDIDO-");
        prefijos.put("ORDEN DE SERVICIO", "OSERV-");
        prefijos.put("NOTA DÉBITO", "ND-");
        return prefijos;
    }
}
