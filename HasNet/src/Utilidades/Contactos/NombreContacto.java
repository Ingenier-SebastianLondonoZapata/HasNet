package Utilidades.Contactos;

public final class NombreContacto {

    private final String primerNombre, segundoNombre, primerApellido, segundoApellido;

    private NombreContacto(String primerNombre, String segundoNombre, String primerApellido, String segundoApellido) {
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
    }

    public static NombreContacto repartir(String nombreCompleto) {
        String[] partes = partir(nombreCompleto);

        switch (partes.length) {
            case 0:
                return new NombreContacto("", "", "", "");
            case 1:
                return new NombreContacto(partes[0], "", "", "");
            case 2:
                return new NombreContacto(partes[0], "", partes[1], "");
            case 3:
                return new NombreContacto(partes[0], "", partes[1], partes[2]);
            case 4:
                return new NombreContacto(partes[0], partes[1], partes[2], partes[3]);
            default:
                return new NombreContacto(partes[0], partes[1], partes[2], unir(partes, 3));
        }
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    private static String[] partir(String nombreCompleto) {
        if (nombreCompleto == null || nombreCompleto.trim().isEmpty()) {
            return new String[0];
        }

        return nombreCompleto.trim().split("\\s+");
    }

    private static String unir(String[] partes, int desde) {
        StringBuilder unidas = new StringBuilder();

        for (int i = desde; i < partes.length; i++) {
            if (unidas.length() > 0) {
                unidas.append(" ");
            }
            unidas.append(partes[i]);
        }
        return unidas.toString();
    }
}
