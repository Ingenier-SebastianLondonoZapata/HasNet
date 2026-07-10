package Modelo.Ventas;

import java.math.BigDecimal;

public class ModeloMesa {

    private int fila;
    private int columna;
    private String nombre;
    private boolean ocupada;
    private BigDecimal total;
    private String turno;
    private int pixelX;
    private int pixelY;
    private int ancho;
    private int alto;

    public ModeloMesa(int fila, int columna, String nombre) {
        this.fila = fila;
        this.columna = columna;
        this.nombre = nombre;
        this.ocupada = false;
        this.total = BigDecimal.ZERO;
        this.turno = "";
    }

    public boolean contains(int px, int py) {
        return px >= pixelX && px <= pixelX + ancho
                && py >= pixelY && py <= pixelY + alto;
    }

    public int getFila() { return fila; }
    public int getColumna() { return columna; }
    public String getNombre() { return nombre; }
    public boolean isOcupada() { return ocupada; }
    public BigDecimal getTotal() { return total; }
    public String getTurno() { return turno; }
    public int getPixelX() { return pixelX; }
    public int getPixelY() { return pixelY; }
    public int getAncho() { return ancho; }
    public int getAlto() { return alto; }

    public void setOcupada(boolean ocupada) { this.ocupada = ocupada; }
    public void setTotal(BigDecimal total) { this.total = total; }
    public void setTurno(String turno) { this.turno = turno; }
    public void setPixelX(int pixelX) { this.pixelX = pixelX; }
    public void setPixelY(int pixelY) { this.pixelY = pixelY; }
    public void setAncho(int ancho) { this.ancho = ancho; }
    public void setAlto(int alto) { this.alto = alto; }
}
