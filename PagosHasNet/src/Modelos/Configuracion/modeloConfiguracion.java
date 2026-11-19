package Modelos.Configuracion;

public class modeloConfiguracion {

    String regimen, nit, nombre, telefono, fechaInicio;

    int diasAntesAlertaBloqueo, diasDespuesGabelaBloqueo, numeroFacturasElectronicasDisponibles;

    boolean congeladas, medico, veterinaria, parqueadero, ordenServicio, creditos, separe, pedido, agenda, restaurante, recordatorios,
            laboratorio, servicioAutomotor, oftalmologia, inventarioBodegas, productosSerial, facturacionLote, facturaElectronica,
            pruebasFacturacionElectronica;

    public boolean isPruebasFacturacionElectronica() {
        return pruebasFacturacionElectronica;
    }

    public void setPruebasFacturacionElectronica(boolean pruebasFacturacionElectronica) {
        this.pruebasFacturacionElectronica = pruebasFacturacionElectronica;
    }

    public int getNumeroFacturasElectronicasDisponibles() {
        return numeroFacturasElectronicasDisponibles;
    }

    public void setNumeroFacturasElectronicasDisponibles(int numeroFacturasElectronicasDisponibles) {
        this.numeroFacturasElectronicasDisponibles = numeroFacturasElectronicasDisponibles;
    }

    public String getRegimen() {
        return regimen;
    }

    public void setRegimen(String regimen) {
        this.regimen = regimen;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public int getDiasAntesAlertaBloqueo() {
        return diasAntesAlertaBloqueo;
    }

    public void setDiasAntesAlertaBloqueo(int diasAntesAlertaBloqueo) {
        this.diasAntesAlertaBloqueo = diasAntesAlertaBloqueo;
    }

    public int getDiasDespuesGabelaBloqueo() {
        return diasDespuesGabelaBloqueo;
    }

    public void setDiasDespuesGabelaBloqueo(int diasDespuesGabelaBloqueo) {
        this.diasDespuesGabelaBloqueo = diasDespuesGabelaBloqueo;
    }

    public boolean isCongeladas() {
        return congeladas;
    }

    public void setCongeladas(boolean congeladas) {
        this.congeladas = congeladas;
    }

    public boolean isMedico() {
        return medico;
    }

    public void setMedico(boolean medico) {
        this.medico = medico;
    }

    public boolean isVeterinaria() {
        return veterinaria;
    }

    public void setVeterinaria(boolean veterinaria) {
        this.veterinaria = veterinaria;
    }

    public boolean isParqueadero() {
        return parqueadero;
    }

    public void setParqueadero(boolean parqueadero) {
        this.parqueadero = parqueadero;
    }

    public boolean isOrdenServicio() {
        return ordenServicio;
    }

    public void setOrdenServicio(boolean ordenServicio) {
        this.ordenServicio = ordenServicio;
    }

    public boolean isCreditos() {
        return creditos;
    }

    public void setCreditos(boolean creditos) {
        this.creditos = creditos;
    }

    public boolean isSepare() {
        return separe;
    }

    public void setSepare(boolean separe) {
        this.separe = separe;
    }

    public boolean isPedido() {
        return pedido;
    }

    public void setPedido(boolean pedido) {
        this.pedido = pedido;
    }

    public boolean isAgenda() {
        return agenda;
    }

    public void setAgenda(boolean agenda) {
        this.agenda = agenda;
    }

    public boolean isRestaurante() {
        return restaurante;
    }

    public void setRestaurante(boolean restaurante) {
        this.restaurante = restaurante;
    }

    public boolean isRecordatorios() {
        return recordatorios;
    }

    public void setRecordatorios(boolean recordatorios) {
        this.recordatorios = recordatorios;
    }

    public boolean isLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(boolean laboratorio) {
        this.laboratorio = laboratorio;
    }

    public boolean isServicioAutomotor() {
        return servicioAutomotor;
    }

    public void setServicioAutomotor(boolean servicioAutomotor) {
        this.servicioAutomotor = servicioAutomotor;
    }

    public boolean isOftalmologia() {
        return oftalmologia;
    }

    public void setOftalmologia(boolean oftalmologia) {
        this.oftalmologia = oftalmologia;
    }

    public boolean isInventarioBodegas() {
        return inventarioBodegas;
    }

    public void setInventarioBodegas(boolean inventarioBodegas) {
        this.inventarioBodegas = inventarioBodegas;
    }

    public boolean isProductosSerial() {
        return productosSerial;
    }

    public void setProductosSerial(boolean productosSerial) {
        this.productosSerial = productosSerial;
    }

    public boolean isFacturacionLote() {
        return facturacionLote;
    }

    public void setFacturacionLote(boolean facturacionLote) {
        this.facturacionLote = facturacionLote;
    }

    public boolean isFacturaElectronica() {
        return facturaElectronica;
    }

    public void setFacturaElectronica(boolean facturaElectronica) {
        this.facturaElectronica = facturaElectronica;
    }

    public Object[] pasarDatosConfiguracion(modeloConfiguracion nodo) {
        Object[] vector = {"", nodo.getRegimen(), nodo.getNit(), nodo.getNombre(),
            nodo.getTelefono(), nodo.getFechaInicio(), nodo.getDiasAntesAlertaBloqueo(), nodo.getDiasDespuesGabelaBloqueo(),
            nodo.isCongeladas(), nodo.isMedico(), nodo.isVeterinaria(), nodo.isParqueadero(), nodo.isOrdenServicio(), nodo.isCreditos(), nodo.isSepare(), nodo.isPedido(),
            nodo.isAgenda(), nodo.isRestaurante(), nodo.isRecordatorios(), nodo.isLaboratorio(), nodo.isServicioAutomotor(), nodo.isOftalmologia(),
            nodo.isInventarioBodegas(), nodo.isProductosSerial(), nodo.isFacturacionLote(), nodo.isFacturaElectronica(),
            nodo.isPruebasFacturacionElectronica()
        };

        return vector;
    }

    public modeloConfiguracion llenarConfiguracion(Object[] vector) {
        modeloConfiguracion modelo = new modeloConfiguracion();

        modelo.setRegimen((String) vector[0]);
        modelo.setNit((String) vector[1]);
        modelo.setNombre((String) vector[2]);
        modelo.setTelefono((String) vector[3]);
        modelo.setFechaInicio((String) vector[4]);
        modelo.setDiasAntesAlertaBloqueo(Integer.parseInt(vector[5].toString()));
        modelo.setDiasDespuesGabelaBloqueo(Integer.parseInt(vector[6].toString()));
        modelo.setNumeroFacturasElectronicasDisponibles(Integer.parseInt(vector[7].toString()));
        modelo.setCongeladas((Boolean) vector[8]);
        modelo.setMedico((Boolean) vector[9]);
        modelo.setVeterinaria((Boolean) vector[10]);
        modelo.setParqueadero((Boolean) vector[11]);
        modelo.setOrdenServicio((Boolean) vector[12]);
        modelo.setCreditos((Boolean) vector[13]);
        modelo.setSepare((Boolean) vector[14]);
        modelo.setPedido((Boolean) vector[15]);
        modelo.setAgenda((Boolean) vector[16]);
        modelo.setRestaurante((Boolean) vector[17]);
        modelo.setRecordatorios((Boolean) vector[18]);
        modelo.setLaboratorio((Boolean) vector[19]);
        modelo.setServicioAutomotor((Boolean) vector[20]);
        modelo.setOftalmologia((Boolean) vector[21]);
        modelo.setInventarioBodegas((Boolean) vector[22]);
        modelo.setProductosSerial((Boolean) vector[23]);
        modelo.setFacturacionLote((Boolean) vector[24]);
        modelo.setFacturaElectronica((Boolean) vector[25]);
        modelo.setPruebasFacturacionElectronica((Boolean) vector[26]);
        return modelo;
    }
}
