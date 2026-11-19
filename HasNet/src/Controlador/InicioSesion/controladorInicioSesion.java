package Controlador.InicioSesion;

import Controlador.Alertas.ControladorAlertas;
import Controlador.BarraProceso.controladorBarraProceso;
import Controlador.FacturacionElectronica.ControladorFacturacionElectronica;
import Dao.Configuraciones.DaoUsuarios;
import Dao.InicioSesion.DaoInicioSesion;
import Dao.Multiempresa.DaoEmpresas;
import Modelo.InicioSesion.Terminal;
import Modelo.Maestra.modeloConfiguracion;
import Modelo.Multiempresa.Empresa;
import Modelo.Usuarios.ModeloUsuario;
import Procesos.Multiempresa.ActualizadorEsquema;
import java.sql.SQLException;
import Utilidades.Constantes;
import Utilidades.Seguridad.ContrasenasSeguras;
import Utilidades.Seguridad.ControlInactividad;
import Utilidades.Seguridad.ControlIntentosSesion;
import Utilidades.Seguridad.ControlSesionesTerminal;
import Utilidades.Seguridad.SesionUsuario;
import Vista.Aterrizaje.VistaMenu;
import Vista.BarraProceso.vistaBarraProceso;
import clases.Instancias;
import clases.metodosGenerales;
import clases.ndUsuario;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class controladorInicioSesion {

    public enum EstadoArranque {
        LISTO, CUOTA_PENDIENTE, BLOQUEADO, ERROR
    }

    private static final String MENSAJE_DATOS_INCORRECTOS = "Usuario o contraseña incorrecta";
    private static final Logger LOGGER = Logger.getLogger(controladorInicioSesion.class.getName());

    private final DaoInicioSesion daoInicioSesion = new DaoInicioSesion();
    private final DaoUsuarios daoUsuarios = new DaoUsuarios();
    private final DaoEmpresas daoEmpresas = new DaoEmpresas();
    private final Instancias instancias = Instancias.getInstancias();
    private final metodosGenerales metodos = new metodosGenerales();
    private final ControladorFacturacionElectronica controladorFacturacion = new ControladorFacturacionElectronica();

    private String mensajeArranque = "";

    public EstadoArranque validarPermisosCliente() {
        String identificadorCliente = Constantes.leerIdentificadorCliente();
        if (identificadorCliente.isEmpty()) {
            mensajeArranque = "El sistema no se pudo cargar";
            return EstadoArranque.ERROR;
        }

        modeloConfiguracion modelo = instancias.getSqlPagos().obtenerConfiguracionCliente(identificadorCliente);
        if (modelo.getNit() == null && modelo.getNombre() == null) {
            mensajeArranque = "Tenemos problemas al consultar la información de la empresa, si el problema persiste comunicarse directamente con el proveedor.";
            return EstadoArranque.ERROR;
        }

        instancias.setConfiguracionCliente(modelo);
        instancias.setEsPruebasFacturacionElectronica(modelo.isPruebasFacturacion());
        instancias.setCanalFacturacion(modelo.isPruebasFacturacion() ? Constantes.CANAL_PRUEBAS : Constantes.CANAL_PRODUCCION);

        Object[][] registroPago = instancias.getSqlPagos().obtenerRegistroPago(identificadorCliente);
        if (registroPago.length == 0) {
            mensajeArranque = "No se encontró el registro de pago de la empresa, comuníquese con el proveedor.";
            return EstadoArranque.ERROR;
        }
        instancias.setEmisorPrincipal(texto(registroPago[0][1]), texto(registroPago[0][8]));
        instancias.setNitEmisor(instancias.getNitEmisorPrincipal());
        instancias.setTipoDocumentoEmisor(instancias.getTipoDocumentoEmisorPrincipal());

        String fechaActual = metodos.fecha(instancias.getSqlPagos().obtenerFecha());
        String fechaLimite = texto(registroPago[0][3]);
        int anterioridad = Integer.parseInt(texto(registroPago[0][4]));
        int despues = Integer.parseInt(texto(registroPago[0][5]));
        long diferenciaDias = metodos.restarFecha(fechaLimite, fechaActual) * -1;

        if (diferenciaDias < 0) {
            if ((diferenciaDias * -1) > despues) {
                return EstadoArranque.BLOQUEADO;
            }
            mensajeArranque = "Recuerde que la fecha limite para el pago fue el " + fechaLimite
                    + " más " + despues + " días de gabela para que pueda realizar el pago.";
            return EstadoArranque.CUOTA_PENDIENTE;
        }
        if (diferenciaDias <= anterioridad) {
            mensajeArranque = "Recuerde que la fecha limite para el pago es el " + fechaLimite;
            return EstadoArranque.CUOTA_PENDIENTE;
        }
        return EstadoArranque.LISTO;
    }

    public String getMensajeArranque() {
        return mensajeArranque;
    }

    public boolean multiempresaHabilitada() {
        modeloConfiguracion modelo = instancias.getConfiguracionCliente();
        return modelo != null && modelo.isMultiempresa();
    }

    public List<Empresa> empresasDisponibles() {
        List<Empresa> empresas = new ArrayList<>();
        empresas.add(daoEmpresas.empresaPrincipal());
        if (multiempresaHabilitada()) {
            empresas.addAll(daoEmpresas.listar(true));
        }
        return empresas;
    }

    public boolean validarIniciarSesion(String usuario, char[] password, Empresa empresaSeleccionada) {
        String usuarioLimpio = usuario == null ? "" : usuario.trim().toUpperCase();
        if (usuarioLimpio.isEmpty() || password == null || password.length == 0) {
            ControladorAlertas.alert("Ingrese el usuario y la contraseña");
            return false;
        }

        long minutosBloqueo = ControlIntentosSesion.minutosBloqueoRestantes(usuarioLimpio);
        if (minutosBloqueo > 0) {
            ControladorAlertas.alertFail("Demasiados intentos fallidos. Intente de nuevo en " + minutosBloqueo + " minuto(s)");
            return false;
        }

        String codigoTerminal = Constantes.obtenerIdTerminal();
        Terminal terminal = codigoTerminal.isEmpty() ? null : daoInicioSesion.obtenerTerminal(codigoTerminal);
        if (terminal == null || terminal.getId() == null) {
            ControladorAlertas.alertFail("Terminal no activada. Ejecute la herramienta de activación.");
            return false;
        }

        ModeloUsuario modelo = daoUsuarios.consultar(usuarioLimpio);
        if (modelo == null || !ContrasenasSeguras.verificar(password, modelo.getContra())) {
            int restantes = ControlIntentosSesion.registrarFallo(usuarioLimpio);
            ControladorAlertas.alertFail(restantes == 0 ? "Demasiados intentos fallidos. El usuario queda bloqueado por 5 minutos"
                    : MENSAJE_DATOS_INCORRECTOS);
            return false;
        }
        ControlIntentosSesion.registrarExito(usuarioLimpio);
        if (ContrasenasSeguras.necesitaActualizacion(modelo.getContra())) {
            daoUsuarios.actualizarContra(modelo.getUsuario(), ContrasenasSeguras.generarHash(password));
        }

        Empresa empresa = empresaSeleccionada == null || !multiempresaHabilitada() ? daoEmpresas.empresaPrincipal() : empresaSeleccionada;
        if (!daoEmpresas.tieneAcceso(modelo.getUsuario(), empresa)) {
            ControladorAlertas.alertFail("El usuario no tiene acceso a la empresa " + empresa.getNombre());
            return false;
        }

        instancias.setTerminal(terminal.getTerminal());
        if (!SesionUsuario.abrir(modelo.getUsuario(), instancias.getTerminal())) {
            String[] sesion = daoUsuarios.estadoSesion(modelo.getUsuario());
            ControladorAlertas.alertFail("El usuario " + modelo.getUsuario() + " ya tiene una sesión abierta en la TERM-"
                    + sesion[1].replace("TERM-", "") + ". Ciérrela en esa terminal o use \"¿Problemas para iniciar sesión?\"");
            return false;
        }

        if (!activarEmpresa(empresa)) {
            SesionUsuario.cerrar();
            return false;
        }

        String errorSesion = ControlSesionesTerminal.iniciar();
        if (errorSesion != null) {
            SesionUsuario.cerrar();
            ControladorAlertas.alertFail(errorSesion);
            return false;
        }

        generarTokenFacturacion(empresa);

        ndUsuario nodo = instancias.getSql().getDatosUsuario(modelo.getUsuario());
        iniciarCargado iniciarDatos = new iniciarCargado(nodo);
        vistaBarraProceso barraProceso = new vistaBarraProceso(iniciarDatos, instancias);
        barraProceso.show();
        return true;
    }

    private boolean activarEmpresa(Empresa empresa) {
        try {
            ActualizadorEsquema.preparar(empresa.getBaseDatos());
        } catch (SQLException | RuntimeException ex) {
            LOGGER.log(Level.SEVERE, "No se pudo actualizar la estructura de " + empresa.getBaseDatos(), ex);
            ControladorAlertas.alertFail("No se pudo actualizar la estructura de la base de datos de " + empresa.getNombre() + ". " + ex.getMessage());
            return false;
        }
        try {
            instancias.getSql().obtenerConexion(empresa.getBaseDatos(), true);
            if (instancias.getSql().getMysql().getConnection() != null) {
                instancias.setEmpresaActual(empresa);
                return true;
            }
        } catch (RuntimeException ex) {
            LOGGER.log(Level.SEVERE, "No se pudo activar la empresa " + empresa.getBaseDatos(), ex);
        }
        instancias.getSql().obtenerConexion(Constantes.BASE_DATOS_PRINCIPAL, true);
        ControladorAlertas.alertFail("No se pudo abrir la empresa " + empresa.getNombre());
        return false;
    }

    private void generarTokenFacturacion(Empresa empresa) {
        String nit = empresa.isPrincipal() ? instancias.getNitEmisorPrincipal() : empresa.getNit();
        String tipoDocumento = empresa.isPrincipal() || empresa.getTipoIdentificacion() == null
                ? instancias.getTipoDocumentoEmisorPrincipal() : empresa.getTipoIdentificacion();
        instancias.setTokenEmisor(null);
        instancias.setNitEmisor(nit);
        instancias.setTipoDocumentoEmisor(tipoDocumento);

        modeloConfiguracion modelo = instancias.getConfiguracionCliente();
        if (modelo == null || !modelo.isFacturaElectronica()) {
            return;
        }
        try {
            if (!controladorFacturacion.generarToken(nit, tipoDocumento)) {
                LOGGER.log(Level.WARNING, "No se pudo generar el token de facturación electrónica para el NIT {0}", nit);
            }
        } catch (RuntimeException ex) {
            LOGGER.log(Level.SEVERE, "Error al generar el token de facturación electrónica", ex);
        }
    }

    private static String texto(Object valor) {
        return valor == null ? "" : valor.toString();
    }

    class iniciarCargado extends controladorBarraProceso {

        Instancias instancias;
        ndUsuario nodo;

        iniciarCargado(ndUsuario nodo) {
            this.instancias = Instancias.getInstancias();
            this.nodo = nodo;
        }

        public void run() {
            VistaMenu formularioMenu = new VistaMenu();

            instancias.setMenu(formularioMenu);
            instancias.setUsuario(nodo.getUsuario());
            instancias.setUsuarioLog(nodo);

            formularioMenu.setInstancias(instancias);
            formularioMenu.setExtendedState(JFrame.MAXIMIZED_BOTH);
            formularioMenu.show();

            instancias.getProgres().detener(true);
            instancias.setProgres(null);
            formularioMenu.cargarSocket();
            java.awt.EventQueue.invokeLater(ControlInactividad::iniciar);
        }
    }

}
