package Controlador.ConsumirServicio;

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

public class controladorConsumirServicioREST {

    Instancias instancias = Instancias.getInstancias();

    public String consumirServicioREST(String urlServicio, String json) throws IOException {
        if (urlServicio == null || urlServicio.isEmpty()) {
            throw new IllegalArgumentException("La URL del servicio no puede ser nula o vacía.");
        }

        HttpURLConnection connection = null;
        try {
            System.out.println("URL Servicio: " + urlServicio);
            URL url = new URL(urlServicio);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            if (null != instancias.getTokenEmisor() && !instancias.getTokenEmisor().isEmpty()) {
                connection.setRequestProperty("Authorization", instancias.getTokenEmisor());
            }

            connection.setRequestProperty("Host", url.getHost());
            connection.setDoOutput(true);
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(40000);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(false);

            if (json != null && !json.isEmpty()) {
                try (OutputStream os = connection.getOutputStream()) {
                    byte[] input = json.getBytes(StandardCharsets.UTF_8);
                    os.write(input, 0, input.length);
                }
            }

            int responseCode = connection.getResponseCode();
            try (InputStream inputStream = (responseCode >= 200 && responseCode < 300) ? connection.getInputStream() : connection.getErrorStream();
                    InputStreamReader isr = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                    BufferedReader br = new BufferedReader(isr)) {
                return br.lines().collect(Collectors.joining());
            }
        } catch (IOException e) {
            System.err.println("Error al consumir el servicio REST: " + e.getMessage());
            System.err.println("URL: " + urlServicio);
            return "";
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
