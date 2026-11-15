package Modelo.SoportesMercantiles;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class RegistroSoporteMercantil {

    private int id;
    private String cufe = "";
    private String prefijo = "";
    private String folio = "";
    private String nitEmisor = "";
    private String nombreEmisor = "";
    private LocalDate fechaEmision;
    private BigDecimal total;
    private String evento = "";
    private String estado = "";
    private String descripcion = "";
    private String errores = "";
    private String uuidEvento = "";
    private String fechaEvento = "";
    private String origen = "";
    private String archivo = "";
    private String usuario = "";
    private LocalDateTime fechaRegistro;

    public static RegistroSoporteMercantil de(DocumentoMercantil documento, String evento, ResultadoEventoMercantil resultado, String usuario) {
        RegistroSoporteMercantil registro = new RegistroSoporteMercantil();
        registro.cufe = !documento.getCufe().isEmpty() ? documento.getCufe() : resultado.getCufe();
        registro.prefijo = documento.getPrefijo();
        registro.folio = documento.getFolio();
        if (registro.prefijo.isEmpty() && registro.folio.isEmpty()) {
            registro.folio = resultado.getPrefijoFactura();
        }
        registro.nitEmisor = documento.getNitEmisor();
        registro.nombreEmisor = documento.getNombreEmisor();
        registro.fechaEmision = documento.getFechaEmision();
        registro.total = documento.getTotal();
        registro.evento = evento;
        registro.estado = resultado.getEstado();
        registro.descripcion = recortar(resultado.getDescripcion(), 500);
        registro.errores = resultado.getErroresTexto();
        registro.uuidEvento = resultado.getUuid();
        registro.fechaEvento = resultado.getFecha();
        registro.origen = documento.getOrigen();
        registro.archivo = recortar(documento.getArchivo(), 255);
        registro.usuario = usuario == null ? "" : usuario;
        registro.fechaRegistro = LocalDateTime.now();
        return registro;
    }

    private static String recortar(String valor, int maximo) {
        return valor == null ? "" : (valor.length() > maximo ? valor.substring(0, maximo) : valor);
    }

    public String getNumeroDocumento() {
        return prefijo + folio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCufe() {
        return cufe;
    }

    public void setCufe(String cufe) {
        this.cufe = cufe;
    }

    public String getPrefijo() {
        return prefijo;
    }

    public void setPrefijo(String prefijo) {
        this.prefijo = prefijo;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getNitEmisor() {
        return nitEmisor;
    }

    public void setNitEmisor(String nitEmisor) {
        this.nitEmisor = nitEmisor;
    }

    public String getNombreEmisor() {
        return nombreEmisor;
    }

    public void setNombreEmisor(String nombreEmisor) {
        this.nombreEmisor = nombreEmisor;
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

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getErrores() {
        return errores;
    }

    public void setErrores(String errores) {
        this.errores = errores;
    }

    public String getUuidEvento() {
        return uuidEvento;
    }

    public void setUuidEvento(String uuidEvento) {
        this.uuidEvento = uuidEvento;
    }

    public String getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(String fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
