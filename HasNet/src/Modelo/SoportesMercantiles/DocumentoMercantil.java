package Modelo.SoportesMercantiles;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DocumentoMercantil {

    public static final String ORIGEN_EXCEL = "EXCEL_DIAN";
    public static final String ORIGEN_XML = "XML";

    private String cufe = "";
    private String prefijo = "";
    private String folio = "";
    private String nitEmisor = "";
    private String nombreEmisor = "";
    private LocalDate fechaEmision;
    private BigDecimal total;
    private String xmlBase64;
    private String origen = "";
    private String archivo = "";

    public String getCufe() {
        return cufe;
    }

    public void setCufe(String cufe) {
        this.cufe = limpiar(cufe);
    }

    public String getPrefijo() {
        return prefijo;
    }

    public void setPrefijo(String prefijo) {
        this.prefijo = limpiar(prefijo);
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = limpiar(folio);
    }

    public String getNitEmisor() {
        return nitEmisor;
    }

    public void setNitEmisor(String nitEmisor) {
        this.nitEmisor = limpiar(nitEmisor);
    }

    public String getNombreEmisor() {
        return nombreEmisor;
    }

    public void setNombreEmisor(String nombreEmisor) {
        this.nombreEmisor = limpiar(nombreEmisor);
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getXmlBase64() {
        return xmlBase64;
    }

    public void setXmlBase64(String xmlBase64) {
        this.xmlBase64 = xmlBase64;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = limpiar(origen);
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = limpiar(archivo);
    }

    public boolean tieneXml() {
        return xmlBase64 != null && !xmlBase64.isEmpty();
    }

    public String getNumeroDocumento() {
        return prefijo + folio;
    }

    public String getIdentificador() {
        String numero = getNumeroDocumento();
        if (!numero.isEmpty()) {
            return numero + (nombreEmisor.isEmpty() ? "" : " - " + nombreEmisor);
        }
        return cufe.length() > 20 ? cufe.substring(0, 20) + "..." : cufe;
    }

    private static String limpiar(String valor) {
        return valor == null ? "" : valor.trim();
    }
}
