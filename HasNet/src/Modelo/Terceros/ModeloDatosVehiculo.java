package Modelo.Terceros;

public class ModeloDatosVehiculo {

    private String placa, tipoVehiculo, numeroChasis, numeroMotor, modelo, marca, fechaCompra, color;

    public ModeloDatosVehiculo() {
        //DO NOTHING
    }

    public ModeloDatosVehiculo(String placa, String tipoVehiculo, String numeroChasis, String numeroMotor, String modelo, String marca, String fechaCompra, String color) {
        this.placa = placa;
        this.tipoVehiculo = tipoVehiculo;
        this.numeroChasis = numeroChasis;
        this.numeroMotor = numeroMotor;
        this.modelo = modelo;
        this.marca = marca;
        this.fechaCompra = fechaCompra;
        this.color = color;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public String getNumeroChasis() {
        return numeroChasis;
    }

    public void setNumeroChasis(String numeroChasis) {
        this.numeroChasis = numeroChasis;
    }

    public String getNumeroMotor() {
        return numeroMotor;
    }

    public void setNumeroMotor(String numeroMotor) {
        this.numeroMotor = numeroMotor;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(String fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
