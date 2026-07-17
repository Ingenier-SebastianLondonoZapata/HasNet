package Vista.Ventas;

import Enums.TipoDocumento;
import java.util.LinkedHashMap;
import java.util.Map;

public class ConversorDocumentoAFactura {

    public interface ActualizadorDocumento {
        void actualizar(String idDocumento, String tituloDocumento);
    }

    private static class EntradaConversion {

        final TipoDocumento tipoAnulacion;
        final ActualizadorDocumento actualizador;

        EntradaConversion(TipoDocumento tipoAnulacion, ActualizadorDocumento actualizador) {
            this.tipoAnulacion = tipoAnulacion;
            this.actualizador = actualizador;
        }
    }

    private final Map<String, EntradaConversion> registro = new LinkedHashMap<>();

    public ConversorDocumentoAFactura registrar(String tipoProceso, TipoDocumento tipoAnulacion, ActualizadorDocumento actualizador) {
        registro.put(tipoProceso, new EntradaConversion(tipoAnulacion, actualizador));
        return this;
    }

    public boolean esConvertible(String tipoProceso) {
        return registro.containsKey(tipoProceso);
    }

    public TipoDocumento obtenerTipoAnulacion(String tipoProceso) {
        EntradaConversion entrada = registro.get(tipoProceso);
        return entrada != null ? entrada.tipoAnulacion : null;
    }

    public void actualizarDocumentoOrigen(String tipoProcesoOriginal, String idDocumento, String tituloDocumento) {
        EntradaConversion entrada = registro.get(tipoProcesoOriginal);
        if (entrada != null) {
            entrada.actualizador.actualizar(idDocumento, tituloDocumento);
        }
    }
}
