package Modelo.Maestra;

import Dao.Configuraciones.DaoResoluciones;
import Utilidades.Constantes;
import clases.Instancias;

public final class ConfiguracionSistema {

    private boolean congeladas, medico, veterinaria, parqueadero, ordenServicio, creditos, separe, pedido, agenda, restaurante, recordatorios,
            laboratorio, servicioAutomotor, oftalmologia, inventarioBodegas, productosDetallados, facturacionLote, facturaElectronica, multiempresa;

    private int numeroEmpresas;

    public static ConfiguracionSistema cargar(Instancias instancias) {
        ConfiguracionSistema configuracion = new ConfiguracionSistema();
        configuracion.cargarDatos(instancias);
        configuracion.cargarPrefijos(instancias);
        return configuracion;
    }

    private void cargarDatos(Instancias instancias) {
        instancias.setSimbolo("$");
        instancias.setDescripcionSimbolo("PESOS");
        instancias.setCadenaDecimales("##");

        modeloConfiguracion modelo = instancias.getConfiguracionCliente();
        if (modelo == null) {
            modelo = instancias.getSqlPagos().obtenerConfiguracionCliente(Constantes.leerIdentificadorCliente());
            instancias.setConfiguracionCliente(modelo);
        }
        ordenServicio = modelo.isOrdenServicio();
        servicioAutomotor = modelo.isServicioAutomotor();
        creditos = modelo.isCreditos();
        separe = modelo.isSepare();
        pedido = modelo.isPedido();
        congeladas = modelo.isCongeladas();
        medico = modelo.isMedico();
        veterinaria = modelo.isVeterinaria();
        parqueadero = modelo.isParqueadero();
        agenda = modelo.isAgenda();
        restaurante = modelo.isRestaurante();
        recordatorios = modelo.isRecordatorios();
        laboratorio = modelo.isLaboratorio();
        oftalmologia = modelo.isOftalmologia();
        facturacionLote = modelo.isFacturacionLote();
        facturaElectronica = modelo.isFacturaElectronica();
        inventarioBodegas = modelo.isInventarioBodegas();
        productosDetallados = modelo.isProductosSerial();
        multiempresa = modelo.isMultiempresa();
        numeroEmpresas = modelo.getNumeroEmpresas();

        instancias.setRegimen("SinIva".equals(modelo.getRegimen()) ? "SinIva" : "");
        instancias.getReporte().setInformacionLegalHasNet(modelo.getInformacionLegal());
    }

    private void cargarPrefijos(Instancias instancias) {
        ModeloPrefijos prefijos = new DaoResoluciones().obtenerPrefijos();
        if (prefijos == null) {
            return;
        }
        if (prefijos.getPrefijoNotaCredito() != null) {
            instancias.setIdNC(prefijos.getPrefijoNotaCredito());
        }
        if (prefijos.getPrefijoNotaDebito() != null) {
            instancias.setIdND(prefijos.getPrefijoNotaDebito());
        }
        if (prefijos.getPrefijoAbonos() != null) {
            instancias.setIdAbono(prefijos.getPrefijoAbonos());
        }
        if (prefijos.getPrefijoEgresos() != null) {
            instancias.setIdEgreso(prefijos.getPrefijoEgresos());
        }
    }

    public boolean isCongeladas() {
        return congeladas;
    }

    public boolean isMedico() {
        return medico;
    }

    public boolean isVeterinaria() {
        return veterinaria;
    }

    public boolean isParqueadero() {
        return parqueadero;
    }

    public boolean isOrdenServicio() {
        return ordenServicio;
    }

    public boolean isCreditos() {
        return creditos;
    }

    public boolean isSepare() {
        return separe;
    }

    public boolean isPedido() {
        return pedido;
    }

    public boolean isAgenda() {
        return agenda;
    }

    public boolean isRestaurante() {
        return restaurante;
    }

    public boolean isRecordatorios() {
        return recordatorios;
    }

    public boolean isLaboratorio() {
        return laboratorio;
    }

    public boolean isServicioAutomotor() {
        return servicioAutomotor;
    }

    public boolean isOftalmologia() {
        return oftalmologia;
    }

    public boolean isInventarioBodegas() {
        return inventarioBodegas;
    }

    public boolean isProductosDetallados() {
        return productosDetallados;
    }

    public boolean isFacturacionLote() {
        return facturacionLote;
    }

    public boolean isFacturaElectronica() {
        return facturaElectronica;
    }

    public boolean isMultiempresa() {
        return multiempresa;
    }

    public int getNumeroEmpresas() {
        return numeroEmpresas;
    }
}
