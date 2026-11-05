package Modelo.Usuarios;

import java.util.EnumMap;
import java.util.Map;

public class ModeloUsuario {

    private String usuario = "";
    private String nombre = "";
    private String contra = "";
    private final Map<PermisoUsuario, Boolean> permisos = new EnumMap<>(PermisoUsuario.class);

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario == null ? "" : usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre == null ? "" : nombre;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra == null ? "" : contra;
    }

    public boolean tienePermiso(PermisoUsuario permiso) {
        return Boolean.TRUE.equals(permisos.get(permiso));
    }

    public void setPermiso(PermisoUsuario permiso, boolean concedido) {
        permisos.put(permiso, concedido);
    }
}
