package Controlador.InicioSesion;

import Controlador.Alertas.ControladorAlertas;
import Controlador.BarraProceso.controladorBarraProceso;
import Controlador.FacturacionElectronica.controladorFacturacionElectronica;
import DAO.InicioSesion.DaoInicioSesion;
import Modelo.InicioSesion.Terminal;
import Utilidades.Constantes;
import Vista.BarraProceso.vistaBarraProceso;
import clases.Instancias;
import clases.metodosGenerales;
import clases.ndUsuario;
import Vista.Aterrizaje.vistaMenu;
import java.io.File;
import javax.swing.JFrame;

public class controladorInicioSesion {

    private final DaoInicioSesion daoInicioSesion = new DaoInicioSesion();
    Instancias instancias = Instancias.getInstancias();
    metodosGenerales metodos = new metodosGenerales();
    controladorFacturacionElectronica controladorFacturacion = new controladorFacturacionElectronica();
    ControladorAlertas alertas = new ControladorAlertas();

    private static final String RUTA_FICHERO = "C:/info.txt";
    private static final String MENSAJE_DATOS_INCORRECTOS = "Usuario o contraseña incorrecta";

    public boolean validarIniciarSesion(String usuario, String password) {
        String codigoTerminal = Constantes.leerArchivoTerminal();
        Terminal terminal = daoInicioSesion.obtenerTerminal(codigoTerminal);
        Object[] datosUsuario = instancias.getSql().obtenerDatosUsuarioIniciado(usuario);
        ndUsuario nodo = instancias.getSql().getDatosUsuario(usuario);

        if (terminal.getId() != null && existeFichero()) {
            if (nodo.getUsuario() != null) {
                if (!password.equals(nodo.getContra())) {
                    alertas.alertFail(MENSAJE_DATOS_INCORRECTOS);
                    return false;
                }

                instancias.setTerminal(terminal.getTerminal());
                if (datosUsuario[0].toString().equals("ON")) {
                    if (!datosUsuario[1].toString().equals(instancias.getTerminal())) {
                        alertas.alertFail("Usuario ingresado en la TERM-" + datosUsuario[1].toString().replace("TERM-", ""));
                        return false;
                    }
                }

                iniciarCargado iniciarDatos = new iniciarCargado(nodo);
                vistaBarraProceso barraProceso = new vistaBarraProceso(iniciarDatos, instancias);
                barraProceso.show();

                return true;
            } else {
                alertas.alertFail(MENSAJE_DATOS_INCORRECTOS);
                return false;
            }
        } else {
            alertas.alertFail("Sistema no funcional");
            return false;
        }
    }

    private boolean existeFichero() {
        File fichero = new File(RUTA_FICHERO);
        return fichero.exists();
    }

    class iniciarCargado extends controladorBarraProceso {

        Instancias instancias;
        ndUsuario nodo;

        iniciarCargado(ndUsuario nodo) {
            this.instancias = Instancias.getInstancias();
            this.nodo = nodo;
        }

        public void run() {
            vistaMenu formularioMenu = new vistaMenu();

            instancias.setMenu(formularioMenu);
            instancias.setUsuario(nodo.getUsuario());
            instancias.setUsuarioLog(nodo);

            formularioMenu.setInstancias(instancias);
            formularioMenu.setExtendedState(JFrame.MAXIMIZED_BOTH);
            formularioMenu.show();

            instancias.getProgres().detener(true);
            instancias.setProgres(null);
            formularioMenu.cargarSocket();
        }
    }

}
