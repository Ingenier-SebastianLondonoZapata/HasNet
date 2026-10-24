package Controlador.ConsumirServicio;

import Modelo.ConsumirServicio.RespuestaServicio;
import clases.Instancias;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class ControladorConsumirServicioREST {

    private static final int TIEMPO_CONEXION = 10000;
    private static final int TIEMPO_LECTURA = 40000;

    Instancias instancias = Instancias.getInstancias();

    public String consumirServicioREST(String urlServicio, String json) throws IOException {
        return ejecutarPost(urlServicio, json).getCuerpo();
    }

    public RespuestaServicio ejecutarPost(String urlServicio, String json) {
        if (urlServicio == null || urlServicio.isEmpty()) {
            throw new IllegalArgumentException("La URL del servicio no puede ser nula o vacía.");
        }

        HttpURLConnection connection = null;
        try {
            System.out.println("URL Servicio: " + urlServicio);
            connection = abrirConexion(urlServicio);
            escribirCuerpo(connection, json);

            int codigoHttp = connection.getResponseCode();
            return RespuestaServicio.deHttp(codigoHttp, leerRespuesta(connection, codigoHttp));
        } catch (IOException e) {
            System.err.println("Error al consumir el servicio REST: " + e.getMessage());
            System.err.println("URL: " + urlServicio);
            return RespuestaServicio.deError(e.getMessage());
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    private HttpURLConnection abrirConexion(String urlServicio) throws IOException {
        URL url = new URL(urlServicio);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");

        if (null != instancias.getTokenEmisor() && !instancias.getTokenEmisor().isEmpty()) {
            connection.setRequestProperty("Authorization", instancias.getTokenEmisor());
        }

        connection.setRequestProperty("Host", url.getHost());
        connection.setDoOutput(true);
        connection.setConnectTimeout(TIEMPO_CONEXION);
        connection.setReadTimeout(TIEMPO_LECTURA);
        connection.setUseCaches(false);
        connection.setInstanceFollowRedirects(false);
        return connection;
    }

    private void escribirCuerpo(HttpURLConnection connection, String json) throws IOException {
        if (json == null || json.isEmpty()) {
            return;
        }

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = json.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }
    }

    private String leerRespuesta(HttpURLConnection connection, int codigoHttp) throws IOException {
        InputStream inputStream = (codigoHttp >= 200 && codigoHttp < 300) ? connection.getInputStream() : connection.getErrorStream();

        if (inputStream == null) {
            return "";
        }

        try (InputStreamReader isr = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr)) {
            return br.lines().collect(Collectors.joining());
        }
    }
}
