package Modelo.Ventas;

public class DocumentoSeleccionado {

    private final String idDocumento;
    private final String nitCliente;
    private final String nombreCliente;

    public DocumentoSeleccionado(String idDocumento, String nitCliente, String nombreCliente) {
        this.idDocumento = idDocumento;
        this.nitCliente = nitCliente;
        this.nombreCliente = nombreCliente;
    }

    public String getIdDocumento() {
        return idDocumento;
    }

    public String getNitCliente() {
        return nitCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }
}
