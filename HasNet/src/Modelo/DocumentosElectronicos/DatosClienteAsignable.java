package Modelo.DocumentosElectronicos;

/**
 * Contrato comun para los documentos electronicos que reciben los datos de la
 * contraparte (cliente/adquiriente o vendedor/proveedor). Permite construir esa
 * informacion en un unico lugar ({@code DocumentosElectronicos.construirDatosCliente})
 * aunque cada modelo use su propia terminologia interna en los setters.
 *
 * @author sebastian.londono
 */
public interface DatosClienteAsignable {

    // Datos que cada modelo nombra distinto (Adquiriente vs Vendedor)
    void setEmailCliente(String email);

    void setTipoIdentificacionCliente(String tipoIdentificacion);

    void setIdentificacionCliente(String identificacion);

    void setDigitoVerificacionCliente(String digitoVerificacion);

    void setCodigoPostalCliente(String codigoPostal);

    void setTipoPersonaCliente(String tipoPersona);

    void setNombresCliente(String nombres);

    void setDireccionCliente(String direccion);

    void setClienteResponsable(boolean responsable);

    void setRegimenCliente(String regimen);

    void setTelefonoCliente(String telefono);

    // Datos que ya se llaman igual en ambos modelos
    void setSegundoNombre(String segundoNombre);

    void setPrimerApellido(String primerApellido);

    void setSegundoApellido(String segundoApellido);

    void setCdDaneCiudad(String cdDaneCiudad);

    void setDsNombreCiudad(String dsNombreCiudad);

    void setCdDaneDepartamento(String cdDaneDepartamento);

    void setDsNombreDepartamento(String dsNombreDepartamento);

    void setCdIsoPais(String cdIsoPais);

    void setDsNombrePais(String dsNombrePais);

    void setResponsabilidadesFiscales(String responsabilidadesFiscales);

    void setMoneda(String moneda);
}
