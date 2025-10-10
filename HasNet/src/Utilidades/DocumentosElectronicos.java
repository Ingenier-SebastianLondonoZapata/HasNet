/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import Controlador.Alertas.ControladorAlertas;
import Enums.enumTipoIdentificacion;
import Enums.enumTipoPersona;
import Modelo.DocumentoSoporte.Entrada.ModeloDocumentoSoporte;
import Modelo.Terceros.ModeloContacto;
import clases.Instancias;

/**
 *
 * @author sebastian.londono
 */
public class DocumentosElectronicos {

    private final Instancias instancias = Instancias.getInstancias();

    public ModeloDocumentoSoporte construirDatosCliente(ModeloDocumentoSoporte modeloDocumentoSoporte, ModeloContacto datosCliente) {
        modeloDocumentoSoporte.setEmailVendedor(datosCliente.getEmail());
        modeloDocumentoSoporte.setTipoIdentificacionVendedor(enumTipoIdentificacion.obtenerTipoIdentificacion(datosCliente.getTipo()));
        modeloDocumentoSoporte.setIdentificacionVendedor(datosCliente.getId());
        modeloDocumentoSoporte.setDigitoVerificacionVendedor(datosCliente.getId().split("-")[1]);
        modeloDocumentoSoporte.setTipoPersonaVendedor(enumTipoPersona.obtenerTipoPersona(datosCliente.getNaturaleza()));
        modeloDocumentoSoporte.setRegimenVendedor(Constantes.obtenerRegimen(datosCliente.isResponsableIva()));

        if (datosCliente.getTipo().equals(enumTipoIdentificacion.TipoIdentificacion.NIT.getValue())) {
            modeloDocumentoSoporte.setNombresVendedor(datosCliente.getNombre());
            modeloDocumentoSoporte.setPrimerApellido(datosCliente.getNombre());
        } else {
            modeloDocumentoSoporte.setNombresVendedor(datosCliente.getpNombre());
            modeloDocumentoSoporte.setSegundoNombre(datosCliente.getsNombre());
            modeloDocumentoSoporte.setPrimerApellido(datosCliente.getpApellido());
            modeloDocumentoSoporte.setSegundoApellido(datosCliente.getsApellido());
        }

        modeloDocumentoSoporte.setCodigoPostalVendedor(datosCliente.getCodigoPostal());
        modeloDocumentoSoporte.setDireccionVendedor(datosCliente.getDireccion());
        modeloDocumentoSoporte.setVendedorResponsable(datosCliente.isResponsableIva());
        modeloDocumentoSoporte.setTelefonoVendedor(datosCliente.getTelefono());

        // String identificadorTributarioVendedor;
        Object[][] datosDepartamentoYCiudad = instancias.getSql().getCodigoLugar(datosCliente.getDepartamento(), datosCliente.getCiudad());
        if (null != datosDepartamentoYCiudad[0][1]) {
            String cdDaneCiudad = datosDepartamentoYCiudad[0][1].toString();
            modeloDocumentoSoporte.setCdDaneCiudad(cdDaneCiudad.substring(2, cdDaneCiudad.length()));
            modeloDocumentoSoporte.setDsNombreCiudad(datosCliente.getCiudad());
            modeloDocumentoSoporte.setCdDaneDepartamento(datosDepartamentoYCiudad[0][0].toString());
            modeloDocumentoSoporte.setDsNombreDepartamento(datosCliente.getDepartamento());
            modeloDocumentoSoporte.setCdIsoPais("CO");
            modeloDocumentoSoporte.setDsNombrePais(datosCliente.getPais());
        } else {
            ControladorAlertas.alertFail("Revisar la ciudad y departamento del contacto");
            return null;
        }

        modeloDocumentoSoporte.setResponsabilidadesFiscales(DatosMaestra.getResponsabilidadesFiscales());
        modeloDocumentoSoporte.setMoneda("COP");

        return modeloDocumentoSoporte;
    }
}
