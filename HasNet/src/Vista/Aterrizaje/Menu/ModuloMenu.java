package Vista.Aterrizaje.Menu;

import Vista.Configuraciones.vistaSuperMaestra;

public enum ModuloMenu {

    VENTAS("ventas", "MIS VENTAS", "VENTAS", false),
    PRODUCTOS("productos", "PRODUCTOS", "PRODUCTOS", false),
    TERCEROS("terceros", "TERCEROS", "TERCEROS", false),
    TESORERIA("egresos", "TESORERIA", "TESORERIA", false),
    CARTERA("cartera", "CARTERA", "CARTERA", false),
    AGENDA("agenda", "AGENDA", "AGENDA", true),
    VETERINARIA("veterinaria", "VETERINARIO", "VETERINARIO", true),
    MEDICO("medico", "MEDICO", "MEDICO", true),
    OFTALMOLOGIA("oftamologia", "OFTALMOLOGIA", "MEDICO", true),
    PARQUEADERO("parqueadero", "PARQUEADERO", "PARQUEADERO", true),
    RECORDATORIO("recordatorio", "RECORDATORIO", "RECORDATORIO", true),
    LABORATORIO("laboratorio", "LABORATORIO", "LABORATORIO", true);

    private final String clave;
    private final String texto;
    private final String icono;
    private final boolean especializado;

    ModuloMenu(String clave, String texto, String icono, boolean especializado) {
        this.clave = clave;
        this.texto = texto;
        this.icono = icono;
        this.especializado = especializado;
    }

    public String getClave() {
        return clave;
    }

    public String getTexto() {
        return texto;
    }

    public boolean isEspecializado() {
        return especializado;
    }

    public String getIconoActivo() {
        return "/imagenes/" + icono + "-BLANCO.png";
    }

    public String getIconoInactivo() {
        return "/imagenes/" + icono + "-NEGRO.png";
    }

    public boolean estaHabilitado(vistaSuperMaestra config) {
        switch (this) {
            case AGENDA:
                return config.isAgenda();
            case VETERINARIA:
                return config.isVeterinaria();
            case MEDICO:
                return config.isMedico();
            case OFTALMOLOGIA:
                return config.isOftalmologia();
            case PARQUEADERO:
                return config.isParqueadero();
            case RECORDATORIO:
                return config.isRecordatorios();
            case LABORATORIO:
                return config.isLaboratorio();
            default:
                return true;
        }
    }

    public static ModuloMenu desdeClave(String clave) {
        for (ModuloMenu modulo : values()) {
            if (modulo.clave.equals(clave)) {
                return modulo;
            }
        }
        return null;
    }
}
