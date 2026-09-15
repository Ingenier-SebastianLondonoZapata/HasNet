package Utilidades;

import Controlador.Alertas.ControladorAlertas;
import Enums.enumTipoIdentificacion;
import Enums.enumTipoPersona;
import Modelo.DocumentosElectronicos.DatosClienteAsignable;
import Modelo.Terceros.ModeloContacto;
import clases.Instancias;
import javax.swing.JTable;

public class DocumentosElectronicos {

    private final Instancias instancias = Instancias.getInstancias();

    public boolean construirDatosCliente(DatosClienteAsignable documento, ModeloContacto datosCliente) {
        documento.setEmailCliente(datosCliente.getEmail());
        documento.setTipoIdentificacionCliente(enumTipoIdentificacion.obtenerTipoIdentificacion(datosCliente.getTipo()));
        documento.setIdentificacionCliente(datosCliente.getId().split("-")[0]);
        documento.setDigitoVerificacionCliente(datosCliente.getId().split("-")[1]);
        documento.setCodigoPostalCliente(datosCliente.getCodigoPostal());
        documento.setTipoPersonaCliente(enumTipoPersona.obtenerTipoPersona(datosCliente.getNaturaleza()));
        documento.setRegimenCliente(Constantes.obtenerRegimen(datosCliente.isResponsableIva()));

        if (datosCliente.getTipo().equals(enumTipoIdentificacion.TipoIdentificacion.NIT.getValue())) {
            documento.setNombresCliente(datosCliente.getNombre());
            documento.setPrimerApellido(datosCliente.getNombre());
        } else {
            documento.setNombresCliente(datosCliente.getpNombre());
            documento.setSegundoNombre(datosCliente.getsNombre());
            documento.setPrimerApellido(datosCliente.getpApellido());
            documento.setSegundoApellido(datosCliente.getsApellido());
        }

        documento.setDireccionCliente(datosCliente.getDireccion());
        documento.setClienteResponsable(datosCliente.isResponsableIva());
        documento.setTelefonoCliente(datosCliente.getTelefono());

        Object[][] datosDepartamentoYCiudad = instancias.getSql().getCodigoLugar(datosCliente.getDepartamento(), datosCliente.getCiudad());
        if (null != datosDepartamentoYCiudad[0][1]) {
            String cdDaneCiudad = datosDepartamentoYCiudad[0][1].toString();
            documento.setCdDaneCiudad(cdDaneCiudad.substring(2, cdDaneCiudad.length()));
            documento.setDsNombreCiudad(datosCliente.getCiudad());
            documento.setCdDaneDepartamento(datosDepartamentoYCiudad[0][0].toString());
            documento.setDsNombreDepartamento(datosCliente.getDepartamento());
            documento.setCdIsoPais("CO");
            documento.setDsNombrePais(datosCliente.getPais());
        } else {
            ControladorAlertas.alertFail("Revisar la ciudad y departamento del cliente");
            return false;
        }

        documento.setResponsabilidadesFiscales(DatosMaestra.getResponsabilidadesFiscales());
        documento.setMoneda("COP");

        return true;
    }

    public String obtenerResolucionDocumento(JTable tabla) {
        for (int i = 0; i < tabla.getRowCount(); i++) {
            if (Boolean.TRUE.equals(tabla.getValueAt(i, 2))) {
                Object resolucion = tabla.getValueAt(i, 3);
                return resolucion != null ? resolucion.toString() : "";
            }
        }

        return "";
    }
}
