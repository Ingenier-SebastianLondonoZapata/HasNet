package Procesos.Tesoreria.Servicio;

import Dao.Productos.DaoProducto;
import Dao.Tesoreria.DaoCuadreCaja;
import Enums.enumBodegas;
import Modelo.Productos.ModeloProducto;
import Modelo.Tesoreria.ModeloDocumentoCuadre;
import Modelo.Tesoreria.ModeloResumenCuadre;
import java.math.BigDecimal;
import java.util.List;

public class ServicioCuadreCaja {

    private static final String CODIGO_DOMICILIO = "DOMI";
    private static final String LUGAR_DOMICILIO = "DOMICILIO";
    private static final String MARCA_PLAN_SEPARE = "SEPARE";

    private final DaoCuadreCaja daoCuadreCaja = new DaoCuadreCaja();
    private final DaoProducto daoProducto = new DaoProducto();

    public ModeloResumenCuadre calcular(String fecha, String usuario) {
        ModeloResumenCuadre resumen = new ModeloResumenCuadre();

        List<ModeloDocumentoCuadre> facturas = daoCuadreCaja.obtenerFacturas(fecha, usuario);
        List<ModeloDocumentoCuadre> planes = daoCuadreCaja.obtenerPlanesSepare(fecha, usuario);
        List<ModeloDocumentoCuadre> abonos = daoCuadreCaja.obtenerAbonos(fecha, usuario);
        List<ModeloDocumentoCuadre> notasCredito = daoCuadreCaja.obtenerNotasCredito(fecha, usuario);
        List<ModeloDocumentoCuadre> egresos = daoCuadreCaja.obtenerEgresos(fecha, usuario);

        resumen.getDocumentos().addAll(facturas);
        resumen.getDocumentos().addAll(planes);
        resumen.getDocumentos().addAll(abonos);
        resumen.getDocumentos().addAll(notasCredito);
        resumen.getDocumentos().addAll(egresos);

        Acumulador acumulador = new Acumulador();
        acumularFacturas(resumen, acumulador, facturas);
        acumularPlanesSepare(resumen, planes);
        acumularAbonos(resumen, acumulador, abonos);
        acumularNotasCredito(resumen, notasCredito);

        resumen.setEfectivo(acumulador.efectivo);
        resumen.setTarjeta(acumulador.tarjeta);
        resumen.setCheque(acumulador.cheque);
        resumen.setNotasCreditoRecibidas(acumulador.notaCredito);

        acumularEgresos(resumen, egresos);
        resumen.setRecogidaPendiente(daoCuadreCaja.obtenerTotalRecogidaParcial(fecha, usuario));
        resumen.setBase(daoCuadreCaja.obtenerTotalBase(fecha, usuario));
        resumen.setProductoDomicilio(obtenerTotalProductoDomicilio(fecha, usuario));

        return resumen;
    }

    public boolean marcarDocumentosCuadrados(String documento, String fecha, String usuario) {
        return daoCuadreCaja.marcarDocumentosCuadrados(documento, fecha, usuario);
    }

    private void acumularFacturas(ModeloResumenCuadre resumen, Acumulador acumulador, List<ModeloDocumentoCuadre> facturas) {
        BigDecimal credito = BigDecimal.ZERO;
        BigDecimal contado = BigDecimal.ZERO;
        BigDecimal sistecredito = BigDecimal.ZERO;
        BigDecimal totalVentas = BigDecimal.ZERO;
        BigDecimal domicilios = BigDecimal.ZERO;
        BigDecimal cuotasIniciales = BigDecimal.ZERO;
        BigDecimal propinas = BigDecimal.ZERO;

        for (ModeloDocumentoCuadre factura : facturas) {
            if (factura.isCredito()) {
                if (factura.isSisteCredito()) {
                    sistecredito = sistecredito.add(factura.getTotal());
                } else {
                    credito = credito.add(factura.getTotal());
                }
                resumen.setContCredito(resumen.getContCredito() + 1);
            } else {
                contado = contado.add(factura.getTotal());
                resumen.setContContado(resumen.getContContado() + 1);
            }

            if (LUGAR_DOMICILIO.equals(factura.getLugar())) {
                domicilios = domicilios.add(factura.getTotal());
            }

            if (esPositivo(factura.getCuotaInicial())) {
                resumen.setContCuotasIniciales(resumen.getContCuotasIniciales() + 1);
            }

            totalVentas = totalVentas.add(factura.getTotal());
            propinas = propinas.add(factura.getPropina());
            cuotasIniciales = cuotasIniciales.add(factura.getCuotaInicial());

            acumulador.efectivo = acumulador.efectivo.add(factura.getCuotaInicial());
            acumulador.acumularMediosPago(resumen, factura);

            if (esPositivo(factura.getTarjetaCredito())) {
                resumen.setContTarjeta(resumen.getContTarjeta() + 1);
            }
            acumulador.tarjeta = acumulador.tarjeta.add(factura.getTarjetaCredito());
        }

        resumen.setVentasCredito(credito);
        resumen.setVentasContado(contado);
        resumen.setVentasSistecredito(sistecredito);
        resumen.setTotalVentas(totalVentas);
        resumen.setVentasDomicilio(domicilios);
        resumen.setCuotasIniciales(cuotasIniciales);
        resumen.setPropinas(propinas);

        if (!facturas.isEmpty()) {
            resumen.setPrimeraFactura(facturas.get(0).getId());
            resumen.setUltimaFactura(facturas.get(facturas.size() - 1).getId());
        }
    }

    private void acumularPlanesSepare(ModeloResumenCuadre resumen, List<ModeloDocumentoCuadre> planes) {
        BigDecimal total = BigDecimal.ZERO;
        for (ModeloDocumentoCuadre plan : planes) {
            total = total.add(plan.getTotal());
        }
        resumen.setFacturasPlanSepare(total);
    }

    private void acumularAbonos(ModeloResumenCuadre resumen, Acumulador acumulador, List<ModeloDocumentoCuadre> abonos) {
        BigDecimal cartera = BigDecimal.ZERO;
        BigDecimal planSepare = BigDecimal.ZERO;

        for (ModeloDocumentoCuadre abono : abonos) {
            resumen.setContAbonos(resumen.getContAbonos() + 1);
            acumulador.acumularMediosPago(resumen, abono);

            if (abono.getComprobante().contains(MARCA_PLAN_SEPARE)) {
                planSepare = planSepare.add(abono.getTotal());
            } else {
                cartera = cartera.add(abono.getTotal());
            }
        }

        resumen.setAbonosCartera(cartera);
        resumen.setAbonosPlanSepare(planSepare);
        resumen.setTotalAbonos(cartera.add(planSepare));

        if (!abonos.isEmpty()) {
            resumen.setPrimerAbono(abonos.get(0).getId());
            resumen.setUltimoAbono(abonos.get(abonos.size() - 1).getId());
        }
    }

    private void acumularNotasCredito(ModeloResumenCuadre resumen, List<ModeloDocumentoCuadre> notasCredito) {
        BigDecimal realizadas = BigDecimal.ZERO;
        BigDecimal reembolsadas = BigDecimal.ZERO;

        for (ModeloDocumentoCuadre notaCredito : notasCredito) {
            realizadas = realizadas.add(notaCredito.getNotaCredito());
            resumen.setContNcRealizadas(resumen.getContNcRealizadas() + 1);

            if (notaCredito.isReembolsada()) {
                reembolsadas = reembolsadas.add(notaCredito.getNotaCredito());
                resumen.setContNcReembolsadas(resumen.getContNcReembolsadas() + 1);
            }
        }

        resumen.setNotasCreditoRealizadas(realizadas);
        resumen.setNotasCreditoReembolsadas(reembolsadas);
    }

    private void acumularEgresos(ModeloResumenCuadre resumen, List<ModeloDocumentoCuadre> egresos) {
        BigDecimal total = BigDecimal.ZERO;
        for (ModeloDocumentoCuadre egreso : egresos) {
            total = total.add(egreso.getTotal());
        }
        resumen.setGastosRegistrados(total);
        resumen.setContGastos(egresos.size());
    }

    private BigDecimal obtenerTotalProductoDomicilio(String fecha, String usuario) {
        ModeloProducto producto = daoProducto.obtenerDatosProducto(CODIGO_DOMICILIO, enumBodegas.ID_BODEGA_PRINCIPAL);
        return daoCuadreCaja.obtenerTotalProductoDomicilio(producto.getIdSistema(), fecha, usuario);
    }

    private static boolean esPositivo(BigDecimal valor) {
        return valor.compareTo(BigDecimal.ZERO) > 0;
    }

    private static class Acumulador {

        BigDecimal efectivo = BigDecimal.ZERO;
        BigDecimal tarjeta = BigDecimal.ZERO;
        BigDecimal cheque = BigDecimal.ZERO;
        BigDecimal notaCredito = BigDecimal.ZERO;

        void acumularMediosPago(ModeloResumenCuadre resumen, ModeloDocumentoCuadre documento) {
            efectivo = efectivo.add(documento.getEfectivo());
            if (esPositivo(documento.getEfectivo())) {
                resumen.setContEfectivo(resumen.getContEfectivo() + 1);
            }

            tarjeta = tarjeta.add(documento.getTarjeta());
            if (esPositivo(documento.getTarjeta())) {
                resumen.setContTarjeta(resumen.getContTarjeta() + 1);
            }

            cheque = cheque.add(documento.getCheque());
            if (esPositivo(documento.getCheque())) {
                resumen.setContCheque(resumen.getContCheque() + 1);
            }

            notaCredito = notaCredito.add(documento.getNotaCredito());
            if (esPositivo(documento.getNotaCredito())) {
                resumen.setContNcRecibidas(resumen.getContNcRecibidas() + 1);
            }
        }
    }
}
