package Controlador.ConsumirServicio;

import clases.Instancias;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;

public class controladorConsumirServicioREST {

    Instancias instancias = Instancias.getInstancias();

    public String consumirServicioREST(String urlServicio, String JSON, String token) throws Exception {
        String respuestaServicio = "";
        InputStream inputStream = null;

        try {
            HttpURLConnection myURLConnection = (HttpURLConnection) (new URL(urlServicio).openConnection());
            myURLConnection.setRequestMethod("POST");
            myURLConnection.setRequestProperty("Version-Document", "2");
            myURLConnection.setRequestProperty("Content-Type", "application/json");
            myURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
            myURLConnection.setRequestProperty("Authorization", token);
            myURLConnection.setDoOutput(true);
            myURLConnection.setConnectTimeout(10000);
            myURLConnection.setUseCaches(false);
            myURLConnection.setInstanceFollowRedirects(false);

            if (!JSON.equals("")) {
                try (OutputStream os = myURLConnection.getOutputStream()) {
                    byte[] input = JSON.getBytes("utf-8");
                    os.write(input, 0, input.length);
                }
            }

            try {
                int responseCode = myURLConnection.getResponseCode();
                if (responseCode == 200) {
                    inputStream = myURLConnection.getInputStream();
                } else {
                    inputStream = myURLConnection.getErrorStream();
                }
            } catch (SocketTimeoutException e) {
                System.err.println("Tiempo de conexión agotado. No se pudo establecer la conexión.");
                // Aquí puedes manejar el error de ConnectTimeout de acuerdo a tus necesidades
            } catch (IOException e) {
                System.err.println("Error al realizar la conexión: " + e.getMessage());
                // Aquí puedes manejar otros errores de conexión
            }

            InputStreamReader isr = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String read = br.readLine();
            while (read != null) {
                respuestaServicio += read;
                read = br.readLine();
            }
        } catch (Exception ex) {
            System.err.println("Se presento un error al consumir el servicio rest: " + ex);
            System.err.println("url: " + urlServicio);
        }

        return respuestaServicio;
    }
}
