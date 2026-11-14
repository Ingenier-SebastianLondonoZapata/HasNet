package Vista.Contactos;

import Controlador.Alertas.ControladorAlertas;
import Dao.Terceros.DaoContactos;
import Dao.Terceros.DaoVehiculos;
import Modelo.Contactos.ModeloDatosVehiculo;
import Utilidades.Constantes;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;

public class RegistradorPlacas {

    private final Frame parent;
    private final DaoContactos daoContactos = new DaoContactos();
    private final DaoVehiculos daoVehiculos = new DaoVehiculos();

    public RegistradorPlacas(Frame parent) {
        this.parent = parent;
    }

    public String contactoDePlacas(String idSistemaCliente) {
        if (idSistemaCliente == null || idSistemaCliente.trim().isEmpty()) {
            return "";
        }

        String identificacion = daoContactos.obtenerIdentificacionPorIdSistema(idSistemaCliente.trim());
        if (identificacion.isEmpty() || Constantes.CLIENTE_POR_DEFECTO.equals(identificacion.trim())) {
            return "";
        }
        return idSistemaCliente.trim();
    }

    public String registrar(String placa, String idSistemaContacto) {
        String idSistema = idSistemaContacto == null || idSistemaContacto.isEmpty() ? seleccionarContacto(placa) : idSistemaContacto;
        if (idSistema.isEmpty()) {
            return null;
        }

        VistaDatosVehiculo modal = new VistaDatosVehiculo(parent, true);
        modal.preparar(null, placasDelContacto(idSistema), idSistema);
        modal.sugerirPlaca(placa);
        modal.setVisible(true);

        if (!modal.isAceptado()) {
            return null;
        }

        ModeloDatosVehiculo vehiculo = modal.getVehiculo();
        if (!daoVehiculos.agregarVehiculo(idSistema, vehiculo)) {
            ControladorAlertas.alertFail("No fue posible registrar la placa " + vehiculo.getPlaca());
            return null;
        }

        ControladorAlertas.alertSuccess("Placa registrada con éxito");
        return vehiculo.getPlaca();
    }

    private String seleccionarContacto(String placa) {
        VistaBuscadorContactos buscador = new VistaBuscadorContactos(parent, true, false, null, "");
        buscador.activarSoloSeleccion();
        buscador.setTitle("Seleccione el contacto al que pertenece la placa " + placa.toUpperCase());
        buscador.setLocationRelativeTo(null);
        buscador.noEncontrado("");
        buscador.setVisible(true);

        String identificacion = buscador.getContactoSeleccionado();
        if (identificacion == null || identificacion.trim().isEmpty()) {
            return "";
        }

        if (Constantes.CLIENTE_POR_DEFECTO.equals(identificacion.trim())) {
            ControladorAlertas.alertFail("La placa debe relacionarse a un contacto diferente al cliente por defecto");
            return "";
        }

        String idSistema = daoContactos.obtenerIdSistemaPorIdentificacion(identificacion.trim());
        if (idSistema.isEmpty()) {
            ControladorAlertas.alertFail("No se encontró el contacto seleccionado");
        }
        return idSistema;
    }

    private List<String> placasDelContacto(String idSistema) {
        List<String> placas = new ArrayList<>();
        for (ModeloDatosVehiculo vehiculo : daoContactos.consultarVehiculos(idSistema)) {
            placas.add(vehiculo.getPlaca());
        }
        return placas;
    }
}
