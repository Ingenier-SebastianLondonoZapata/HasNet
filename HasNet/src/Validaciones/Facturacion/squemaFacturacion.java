package Validaciones.Facturacion;

import Utilidades.Utilidades;
import Controlador.Alertas.ControladorAlertas;
import Enums.TipoDocumento;
import Modelo.Ventas.ModeloValidacionFactura;
import Modelo.Ventas.ResultadoValidacionInventario;
import Utilidades.Constantes;
import clases.Instancias;
import clases.big;
import clases.metodosGenerales;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;

public class squemaFacturacion extends javax.swing.JPanel {

    Instancias instancias = Instancias.getInstancias();
    private final String simboloMoneda = instancias.getSimbolo();

    public squemaFacturacion() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    public boolean validaciones_facturacion(ModeloValidacionFactura datos) {

        List<Object> errores = new ArrayList<>();
        List<Object> alertas = new ArrayList<>();

        if (null == datos.getDatosCliente().getIdSistema()) {
            errores.add("No ha cargado ningún cliente");
        }

        if (TipoDocumento.MESA.getValor().equals(datos.getTipoProceso()) && "DOMICILIO".equals(datos.getTitulo())) {
            if (datos.getNit().isEmpty() || datos.getNit().equals(USUARIO_POR_DEFECTO)) {
                errores.add("¡Debe asociar un cliente!");
            }
        }

        if (TipoDocumento.CUENTA_COBRO.getValor().equals(datos.getTipoProceso())) {
            if (datos.getNit().isEmpty() || datos.getNit().equals(USUARIO_POR_DEFECTO)) {
                errores.add("¡Debe asociar un cliente!");
            }

            if (datos.getCantIncremento().isEmpty()) {
                errores.add("Falta la cantidad para el incremento");
            }
        }

        if (!datos.isSaltarPasos() && datos.isCheckCupo() && TipoDocumento.FACTURACION.getValor().equals(datos.getTipoProceso())
                && !datos.getDiasPlazo().equals("0") && !datos.getDiasPlazo().isEmpty()) {
            if (big.getMoneda(datos.getCupo()).compareTo(BigDecimal.ZERO) <= 0) {
                alertas.add("No tiene cupo de credito");
            }
        }

        if (TipoDocumento.PLAN_SEPARE.getValor().equals(datos.getTipoProceso())) {
            if (datos.getDiasPlazo().equals("0") || datos.getDiasPlazo().isEmpty()) {
                errores.add("No ha ingresado días de plazo");
            }
        }

        if (TipoDocumento.ORDER_SERVICIO.getValor().equals(datos.getTipoProceso()) && datos.isServicioAutomotor()) {
            boolean entro = false;
            for (int i = 0; i < datos.getTblArticulos().getRowCount(); i++) {
                if ((Boolean) datos.getTblArticulos().getValueAt(i, 2)) {
                    entro = true;
                    break;
                }
            }
            if (!entro) {
                errores.add("No ha ingresado el inventario");
            }
        }

        if (datos.isFacturaCredito() && !datos.getInteres().isEmpty() && Integer.parseInt(datos.getInteres()) == 0) {
            alertas.add("Interés del 0%");
        }

        if (datos.isSisteCredito() && datos.getDiasPlazoInt() <= 0) {
            errores.add("Debe ingresar los días de plazo para facturar por Sistecredito");
        }

        if (datos.isFacturaCredito()) {
            if (datos.getCuotas().equals("0") || datos.getCuotas().isEmpty()) {
                errores.add("No ha ingresado el número de cuotas");
            }
            if (datos.getTipoPlazoIndex() == 0) {
                errores.add("No ha seleccionado el tipo de plazo");
            }
            if (datos.getFechaDesenvolso().equals(metodosGenerales.fecha())) {
                errores.add("Seleccione la fecha de pago");
            }
            if (datos.getFilasTablaCuotas() <= 0) {
                errores.add("No ha ingresado cuotas");
            }
        }

        if (!errores.isEmpty()) {
            ControladorAlertas.alertaGrandeListado("Se presentaron los siguientes errores...", errores, false);
            return false;
        }

        if (!alertas.isEmpty()) {
            boolean respuesta = ControladorAlertas.alertaGrandeListado("Se presentaron las siguientes alertas...", alertas, true);
            if (!respuesta) {
                return false;
            }
        }

        return true;
    }
    private static final String USUARIO_POR_DEFECTO = "1010";

    private String obtenerValorTabla(JTable tablaProductos, String campoTabla, int posicionProducto, boolean esNotaCredito, boolean esNotaDebito) {
        switch (campoTabla) {
            case "ID_SISTEMA":
                if (esNotaCredito) {
                    return tablaProductos.getValueAt(posicionProducto, Constantes.COLUMNA_ID_SISTEMA_NOTA_CREDITO).toString();
                } else {
                    return tablaProductos.getValueAt(posicionProducto, Constantes.COLUMNA_ID_SISTEMA).toString();
                }
            case "DESCRIPCION_PRODUCTO":
                return tablaProductos.getValueAt(posicionProducto, Constantes.COLUMNA_DESCRIPCION_PRODUCTO).toString();
            case "UNIDAD_MEDIDA":
                if (esNotaCredito) {
                    return tablaProductos.getValueAt(posicionProducto, 21).toString();
                } else if (esNotaDebito) {
                    return tablaProductos.getValueAt(posicionProducto, Constantes.COLUMNA_UNIDAD_MEDIDA_NOTA_DEBITO).toString();
                } else {
                    return tablaProductos.getValueAt(posicionProducto, Constantes.COLUMNA_UNIDAD_MEDIDA).toString();
                }
            case "PORCENTAJE_IVA":
                if (esNotaCredito) {
                    return tablaProductos.getValueAt(posicionProducto, 6).toString();
                } else {
                    return tablaProductos.getValueAt(posicionProducto, Constantes.COLUMNA_PORCENTAJE_IVA).toString();
                }
            case "PORCENTAJE_IMPOCONSUMO":
                if (esNotaCredito) {
                    return tablaProductos.getValueAt(posicionProducto, 8).toString();
                } else {
                    return tablaProductos.getValueAt(posicionProducto, Constantes.COLUMNA_PORCENTAJE_IMPOCONSUMO).toString();
                }
            default:
                return "";
        }
    }

    private static final int COLUMNA_MARCA_UTILIDAD = 15;
    private static final String MARCA_UTILIDAD_MIN = "ERROR1";
    private static final String MARCA_UTILIDAD_MAX = "ERROR2";

    public boolean validaciones_detalle_facturacion(JTable tablaProductos, String tipoComprobante, String tipoProceso) {
        return validaciones_detalle_facturacion(tablaProductos, tipoComprobante, tipoProceso, null, false, false);
    }

    public boolean validaciones_detalle_facturacion(JTable tablaProductos, String tipoComprobante, String tipoProceso,
            ResultadoValidacionInventario inventario, boolean facturarSinInventario, boolean saltarPasosFactura) {

        List<Object> errores_validacion = new ArrayList<>();
        List<Object> alertas_validacion = new ArrayList<>();
        boolean esNotaCredito = tipoProceso.equals(TipoDocumento.NOTA_CREDITO.getValor());
        boolean esNotaDebito = tipoProceso.equals(TipoDocumento.NOTA_DEBITO.getValor());

        if (tablaProductos.getRowCount() == 0 && !tipoProceso.equals(TipoDocumento.NOTA_CREDITO.getValor())) {
            errores_validacion.add("No ha cargado ningún producto");
        }

        for (int i = 0; i < tablaProductos.getRowCount(); i++) {
            String descripcionProducto = obtenerValorTabla(tablaProductos, "DESCRIPCION_PRODUCTO", i, esNotaCredito, esNotaDebito);
            String unidadMedida = obtenerValorTabla(tablaProductos, "UNIDAD_MEDIDA", i, esNotaCredito, esNotaDebito);

            if (descripcionProducto.length() > Constantes.LONGITUD_MAXIMA_DESCRIPCION_PRODUCTOS) {
                errores_validacion.add("El producto '" + descripcionProducto + "' supera los 255 caracteres!");
            }

            BigDecimal valorProducto = big.getMoneda(tablaProductos.getValueAt(i, Constantes.COLUMNA_VALOR_PRODUCTO).toString());
            if (valorProducto.compareTo(BigDecimal.ZERO) <= 0) {
                errores_validacion.add("El valor unitario del producto '" + descripcionProducto + "' debe ser mayor a " + simboloMoneda + "0");
            }

            if (Constantes.esFacturacionElectronica(tipoComprobante)
                    && (tipoProceso.equals(TipoDocumento.FACTURACION.getValor())
                    || tipoProceso.equals(TipoDocumento.NOTA_DEBITO.getValor())
                    || tipoProceso.equals(TipoDocumento.NOTA_CREDITO.getValor()))) {
                if (descripcionProducto.length() < Constantes.LONGITUD_MINIMA_DESCRIPCION_PRODUCTOS) {
                    errores_validacion.add("El producto '" + descripcionProducto + "' debe tener descripción más larga");
                }

                if ("".equals(unidadMedida)) {
                    errores_validacion.add("El producto '" + descripcionProducto + "' debe tener unidad de medida");
                }

                String codigoUnidadMedida = instancias.getSql().getDatosMedidas(unidadMedida);
                if (null == codigoUnidadMedida) {
                    errores_validacion.add("El producto '" + descripcionProducto + "' debe tener unidad de medida válida");
                }

                int porcentajeIva = Integer.parseInt(obtenerValorTabla(tablaProductos, "PORCENTAJE_IVA", i, esNotaCredito, esNotaDebito));
                int porcentajeImpoconsumo = Integer.parseInt(obtenerValorTabla(tablaProductos, "PORCENTAJE_IMPOCONSUMO", i, esNotaCredito, esNotaDebito));

                if (!Constantes.TARIFAS_IVAS_PERMITIDOS.contains(porcentajeIva)) {
                    errores_validacion.add("El IVA " + porcentajeIva + "% del producto '" + descripcionProducto + "' no es válido. "
                            + "Los permitidos son: " + Constantes.TARIFAS_IVAS_PERMITIDOS.toString());
                }

                if (!Constantes.TARIFAS_IMPOCONSUMO_PERMITIDOS.contains(porcentajeImpoconsumo)) {
                    errores_validacion.add("El IMPOCONSUMO " + porcentajeImpoconsumo + "% del producto '" + descripcionProducto + "' "
                            + "no es válido. Los permitidos son: " + Constantes.TARIFAS_IMPOCONSUMO_PERMITIDOS.toString());
                }
            }

            String idSistema = obtenerValorTabla(tablaProductos, "ID_SISTEMA", i, esNotaCredito, esNotaDebito);
            String costoProducto = instancias.getSql().obtenerUltimoCostoProducto(idSistema);
            if (valorProducto.compareTo(big.getBigDecimal(costoProducto)) < 0 && !saltarPasosFactura) {
                alertas_validacion.add("El producto '" + descripcionProducto + "' se esta facturando por debajo del costo");
            }

            if (instancias.isMensajeUtilidad() && !saltarPasosFactura) {
                Object marcaUtilidad = tablaProductos.getValueAt(i, COLUMNA_MARCA_UTILIDAD);
                if (marcaUtilidad != null) {
                    String mensaje = null;
                    if (MARCA_UTILIDAD_MIN.equals(marcaUtilidad.toString())) {
                        mensaje = "El producto '" + descripcionProducto + "' tiene utilidad mínima sobrepasada";
                    } else if (MARCA_UTILIDAD_MAX.equals(marcaUtilidad.toString())) {
                        mensaje = "El producto '" + descripcionProducto + "' tiene utilidad máxima sobrepasada";
                    }
                    if (mensaje != null) {
                        if (instancias.isUtilidad()) {
                            errores_validacion.add(mensaje);
                        } else {
                            alertas_validacion.add(mensaje);
                        }
                    }
                }
            }
        }

        agregarMensajesInventario(inventario, tipoProceso, facturarSinInventario, errores_validacion, alertas_validacion);

        if (errores_validacion.size() > 0) {
            ControladorAlertas.alertaGrandeListado("Se presentaron los siguientes errores...", errores_validacion, false);
            return false;
        }

        if (alertas_validacion.size() > 0) {
            boolean respuesta = ControladorAlertas.alertaGrandeListado("Se presentaron las siguientes alertas...", alertas_validacion, true);
            if (!respuesta) {
                return false;
            }
        }

        return true;
    }

    private void agregarMensajesInventario(ResultadoValidacionInventario inventario, String tipoProceso,
            boolean facturarSinInventario, List<Object> errores, List<Object> alertas) {

        if (inventario == null || !inventario.hayProductosSinInventario()) {
            return;
        }

        if (TipoDocumento.COTIZACION.getValor().equals(tipoProceso)) {
            return;
        }

        List<Object> destino = facturarSinInventario ? alertas : errores;

        // [0]=id, [1]=descripcion, [2]=disponible, [3]=necesario
        for (Object[] prod : inventario.getProductosSinInventario()) {
            destino.add(prod[1] + " (disponible: " + Utilidades.formatearCantidadVista(prod[2].toString())
                    + ", necesario: " + Utilidades.formatearCantidadVista(prod[3].toString()) + ")");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
