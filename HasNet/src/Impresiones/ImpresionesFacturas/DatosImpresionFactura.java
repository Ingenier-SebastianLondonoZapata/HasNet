package Impresiones.ImpresionesFacturas;

import Enums.TipoDocumento;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DatosImpresionFactura {

    private TipoDocumento tipoDocumento = TipoDocumento.FACTURACION;
    private String tituloDocumento = "";
    private String factura = "";
    private String nombreReporte = "";
    private String impresora = "";
    private String titulo = "";
    private String informacionEmpresa = "";
    private String observaciones = "";
    private String legal = "";
    private String pie = "";
    private List<String> grupos = new ArrayList<>();

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public DatosImpresionFactura setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento == null ? TipoDocumento.FACTURACION : tipoDocumento;
        return this;
    }

    public String getTituloDocumento() {
        return tituloDocumento;
    }

    public DatosImpresionFactura setTituloDocumento(String tituloDocumento) {
        this.tituloDocumento = tituloDocumento == null ? "" : tituloDocumento;
        return this;
    }

    public String getFactura() {
        return factura;
    }

    public DatosImpresionFactura setFactura(String factura) {
        this.factura = factura;
        return this;
    }

    public String getNombreReporte() {
        return nombreReporte;
    }

    public DatosImpresionFactura setNombreReporte(String nombreReporte) {
        this.nombreReporte = nombreReporte;
        return this;
    }

    public String getImpresora() {
        return impresora;
    }

    public DatosImpresionFactura setImpresora(String impresora) {
        this.impresora = impresora;
        return this;
    }

    public String getTitulo() {
        return titulo;
    }

    public DatosImpresionFactura setTitulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    public String getInformacionEmpresa() {
        return informacionEmpresa;
    }

    public DatosImpresionFactura setInformacionEmpresa(String informacionEmpresa) {
        this.informacionEmpresa = informacionEmpresa;
        return this;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public DatosImpresionFactura setObservaciones(String observaciones) {
        this.observaciones = observaciones;
        return this;
    }

    public String getLegal() {
        return legal;
    }

    public DatosImpresionFactura setLegal(String legal) {
        this.legal = legal;
        return this;
    }

    public String getPie() {
        return pie;
    }

    public DatosImpresionFactura setPie(String pie) {
        this.pie = pie;
        return this;
    }

    public List<String> getGrupos() {
        return Collections.unmodifiableList(grupos);
    }

    public DatosImpresionFactura setGrupos(List<String> grupos) {
        this.grupos = grupos == null ? new ArrayList<>() : new ArrayList<>(grupos);
        return this;
    }
}
