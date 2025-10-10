package Modelos.Usuario;

public class modeloUsuario {

    String idUsuario, usuario, password;

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public modeloUsuario llenarUsuario(Object[] vector) {
        modeloUsuario nodo = new modeloUsuario();

        nodo.setIdUsuario((String) vector[0]);
        nodo.setUsuario((String) vector[1]);
        nodo.setPassword((String) vector[2]);
        return nodo;
    }
}
