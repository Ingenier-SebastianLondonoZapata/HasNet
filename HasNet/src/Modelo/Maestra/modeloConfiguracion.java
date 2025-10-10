package Modelo.Maestra;

public class modeloConfiguracion {

    private String regimen, informacionLegal, tipoImpresion, nit, nombre, telefono, fechaInicio;

    int diasAntesAlertaBloqueo, diasDespuesGabelaBloqueo, numeroFacturasElectronicasDisponibles;

    private boolean congeladas, medico, veterinaria, parqueadero, ordenServicio, creditos, separe, pedido, agenda, restaurante, recordatorios,
            laboratorio, servicioAutomotor, oftalmologia, inventarioBodegas, productosSerial, facturacionLote, usb, facturaElectronica,
            pruebasFacturacion;

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

    public String getInformacionLegal() {
        return informacionLegal;
    }

    public void setInformacionLegal(String informacionLegal) {
        this.informacionLegal = informacionLegal;
    }

    public String getTipoImpresion() {
        return tipoImpresion;
    }

    public void setTipoImpresion(String tipoImpresion) {
        this.tipoImpresion = tipoImpresion;
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

    public boolean isUsb() {
        return usb;
    }

    public void setUsb(boolean usb) {
        this.usb = usb;
    }

    public boolean isFacturaElectronica() {
        return facturaElectronica;
    }

    public void setFacturaElectronica(boolean facturaElectronica) {
        this.facturaElectronica = facturaElectronica;
    }

    public boolean isPruebasFacturacion() {
        return pruebasFacturacion;
    }

    public void setPruebasFacturacion(boolean pruebasFacturacion) {
        this.pruebasFacturacion = pruebasFacturacion;
    }

    public Object[] pasarDatosConfiguracion(modeloConfiguracion nodo) {
        Object[] vector = {"1", nodo.getRegimen(), nodo.getInformacionLegal(), nodo.getTipoImpresion(), nodo.getNit(), nodo.getNombre(),
            nodo.getTelefono(), nodo.getFechaInicio(), nodo.getDiasAntesAlertaBloqueo(), nodo.getDiasDespuesGabelaBloqueo(), nodo.getNumeroFacturasElectronicasDisponibles(),
            nodo.isCongeladas(), nodo.isMedico(), nodo.isVeterinaria(), nodo.isParqueadero(), nodo.isOrdenServicio(), nodo.isCreditos(), nodo.isSepare(), nodo.isPedido(),
            nodo.isAgenda(), nodo.isRestaurante(), nodo.isRecordatorios(), nodo.isLaboratorio(), nodo.isServicioAutomotor(), nodo.isOftalmologia(),
            nodo.isInventarioBodegas(), nodo.isProductosSerial(), nodo.isFacturacionLote(), nodo.isUsb(), nodo.isFacturaElectronica(),
            nodo.isPruebasFacturacion()
        };

        return vector;
    }

    public modeloConfiguracion llenarConfiguracion(Object[] vector) {
        modeloConfiguracion modelo = new modeloConfiguracion();

        if (null != vector[3] || null != vector[4]) {
            modelo.setRegimen((String) vector[0]);
            modelo.setInformacionLegal((String) vector[1]);
            modelo.setTipoImpresion((String) vector[2]);
            modelo.setNit((String) vector[3]);
            modelo.setNombre((String) vector[4]);
            modelo.setTelefono((String) vector[5]);
            modelo.setFechaInicio((String) vector[6]);
            modelo.setDiasAntesAlertaBloqueo(Integer.parseInt(vector[7].toString()));
            modelo.setDiasDespuesGabelaBloqueo(Integer.parseInt(vector[8].toString()));
            modelo.setNumeroFacturasElectronicasDisponibles(Integer.parseInt(vector[9].toString()));
            modelo.setCongeladas((Boolean) vector[10]);
            modelo.setMedico((Boolean) vector[11]);
            modelo.setVeterinaria((Boolean) vector[12]);
            modelo.setParqueadero((Boolean) vector[13]);
            modelo.setOrdenServicio((Boolean) vector[14]);
            modelo.setCreditos((Boolean) vector[15]);
            modelo.setSepare((Boolean) vector[16]);
            modelo.setPedido((Boolean) vector[17]);
            modelo.setAgenda((Boolean) vector[18]);
            modelo.setRestaurante((Boolean) vector[19]);
            modelo.setRecordatorios((Boolean) vector[20]);
            modelo.setLaboratorio((Boolean) vector[21]);
            modelo.setServicioAutomotor((Boolean) vector[22]);
            modelo.setOftalmologia((Boolean) vector[23]);
            modelo.setInventarioBodegas((Boolean) vector[24]);
            modelo.setProductosSerial((Boolean) vector[25]);
            modelo.setFacturacionLote((Boolean) vector[26]);
            modelo.setUsb((Boolean) vector[27]);
            modelo.setFacturaElectronica((Boolean) vector[28]);
            modelo.setPruebasFacturacion((Boolean) vector[29]);
        }

        return modelo;
    }
}
