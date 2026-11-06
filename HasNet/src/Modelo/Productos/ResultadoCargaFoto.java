package Modelo.Productos;

import Enums.EstadoCargaFoto;

public class ResultadoCargaFoto {

    private final String archivo;
    private final String codigo;
    private final String descripcion;
    private final EstadoCargaFoto estado;

    public ResultadoCargaFoto(String archivo, String codigo, String descripcion, EstadoCargaFoto estado) {
        this.archivo = archivo;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public String getArchivo() {
        return archivo;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public EstadoCargaFoto getEstado() {
        return estado;
    }
}
