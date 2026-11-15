package Procesos.SoportesMercantiles.Servicio;

import Consumidor.SoportesMercantiles.ConsumidorEventosMercantiles;
import Dao.SoportesMercantiles.DaoSoportesMercantiles;
import Enums.EventoMercantil;
import Modelo.SoportesMercantiles.DocumentoMercantil;
import Modelo.SoportesMercantiles.RegistroSoporteMercantil;
import Modelo.SoportesMercantiles.ResultadoEventoMercantil;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.BooleanSupplier;

public class ServicioSoportesMercantiles {

    public static final EventoMercantil[] SECUENCIA = {
        EventoMercantil.ACUSE_DOCUMENTO, EventoMercantil.RECIBO_SERVICIO, EventoMercantil.ACEPTACION_EXPRESA};

    public interface Seguimiento {

        void avance(int procesados, int total, String mensaje);
    }

    public static class Resumen {

        private int completos;
        private int conErrores;
        private int yaCompletos;
        private int sinProcesar;
        private String motivoDetencion = "";

        public int getCompletos() {
            return completos;
        }

        public int getConErrores() {
            return conErrores;
        }

        public int getYaCompletos() {
            return yaCompletos;
        }

        public int getSinProcesar() {
            return sinProcesar;
        }

        public String getMotivoDetencion() {
            return motivoDetencion;
        }

        public boolean fueDetenido() {
            return !motivoDetencion.isEmpty();
        }
    }

    private final ConsumidorEventosMercantiles consumidor = new ConsumidorEventosMercantiles();
    private final DaoSoportesMercantiles dao = new DaoSoportesMercantiles();
    private final String usuario;

    public ServicioSoportesMercantiles(String usuario) {
        this.usuario = usuario;
    }

    public boolean hayDatosDelEmisor() {
        return consumidor.hayDatosDelEmisor();
    }

    public Resumen procesar(List<DocumentoMercantil> documentos, Seguimiento seguimiento, BooleanSupplier cancelado) {
        Resumen resumen = new Resumen();
        int total = documentos.size();

        for (int i = 0; i < total; i++) {
            if (cancelado.getAsBoolean()) {
                resumen.motivoDetencion = "Proceso cancelado por el usuario";
                resumen.sinProcesar = total - i;
                break;
            }

            DocumentoMercantil documento = documentos.get(i);
            String estadoDocumento = procesarDocumento(documento, resumen, i, total, seguimiento);
            seguimiento.avance(i + 1, total, documento.getIdentificador() + ": " + estadoDocumento);

            if (resumen.fueDetenido()) {
                resumen.sinProcesar = total - i - 1;
                break;
            }
        }
        return resumen;
    }

    private String procesarDocumento(DocumentoMercantil documento, Resumen resumen, int indice, int total, Seguimiento seguimiento) {
        Set<String> completados = eventosCompletados(documento.getCufe());
        boolean enviado = false;

        for (EventoMercantil evento : SECUENCIA) {
            if (completados.contains(evento.name())) {
                continue;
            }

            seguimiento.avance(indice, total, documento.getIdentificador() + ": enviando " + evento.getDescripcion().toLowerCase() + "...");
            ResultadoEventoMercantil resultado = consumidor.enviarEvento(documento, evento);
            enviado = true;

            if (resultado.detieneProceso()) {
                resumen.motivoDetencion = resultado.getDescripcion();
                resumen.conErrores++;
                return "ERROR - " + resultado.getDescripcion();
            }

            guardar(documento, evento, resultado);
            if (!resultado.isCompletado()) {
                resumen.conErrores++;
                return "ERROR en " + evento.getDescripcion().toLowerCase() + " - " + resultado.getResumen();
            }

            if (documento.getCufe().isEmpty() && !resultado.getCufe().isEmpty()) {
                documento.setCufe(resultado.getCufe());
            }
        }

        if (!enviado) {
            resumen.yaCompletos++;
            return "ya era soporte mercantil, no se reenvió";
        }
        resumen.completos++;
        return "soporte mercantil completo";
    }

    private Set<String> eventosCompletados(String cufe) {
        try {
            return dao.eventosCompletados(cufe);
        } catch (SQLException ex) {
            System.err.println("No se pudo consultar el historial del CUFE " + cufe + ": " + ex.getMessage());
            return Collections.emptySet();
        }
    }

    private void guardar(DocumentoMercantil documento, EventoMercantil evento, ResultadoEventoMercantil resultado) {
        try {
            dao.guardar(RegistroSoporteMercantil.de(documento, evento.name(), resultado, usuario));
        } catch (SQLException ex) {
            System.err.println("No se pudo guardar el historial del soporte mercantil " + documento.getIdentificador() + ": " + ex.getMessage());
        }
    }
}
