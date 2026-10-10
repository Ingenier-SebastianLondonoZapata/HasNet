package Modelo.FacturacionMasiva;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ResultadoFacturacion {

    private final boolean facturaGenerada;
    private final List<String> errores;

    public ResultadoFacturacion(boolean facturaGenerada, List<String> errores) {
        this.facturaGenerada = facturaGenerada;
        this.errores = Collections.unmodifiableList(new ArrayList<>(errores));
    }

    public static ResultadoFacturacion exito() {
        return new ResultadoFacturacion(true, new ArrayList<String>());
    }

    public static ResultadoFacturacion fallo(String error) {
        List<String> errores = new ArrayList<>();
        errores.add(error);
        return new ResultadoFacturacion(false, errores);
    }

    public boolean isFacturaGenerada() {
        return facturaGenerada;
    }

    public List<String> getErrores() {
        return errores;
    }

    public boolean tieneErrores() {
        return !errores.isEmpty();
    }

    public String getMensajeErrores() {
        StringBuilder sb = new StringBuilder();
        for (String error : errores) {
            if (sb.length() > 0) {
                sb.append("\n");
            }
            sb.append(error);
        }
        return sb.toString();
    }
}
