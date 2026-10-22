package Utilidades.ImportacionesExcel;

import Controlador.Alertas.ControladorAlertas;
import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public final class DescargadorPlantillas {

    public static final String CARPETA = "/Plantillas/";
    public static final String CARGAR_INVENTARIO = "CARGAR_INVENTARIO.xls";
    public static final String CARGAR_INVENTARIO_DETALLADO = "CARGAR_INVENTARIO_DETALLADO.xls";
    public static final String CREAR_PRODUCTOS = "CREAR_PRODUCTOS.xls";
    public static final String CREAR_PRODUCTOS_CON_INVENTARIO = "CREAR_PRODUCTOS_CON_INVENTARIO.xls";

    private static final Logger LOGGER = Logger.getLogger(DescargadorPlantillas.class.getName());

    private DescargadorPlantillas() {
    }

    public static void descargar(Component padre, String nombreArchivo) {
        descargar(padre, nombreArchivo, null);
    }

    public interface Personalizador {

        void personalizar(File archivo) throws IOException;
    }

    public static void descargar(Component padre, String nombreArchivo, Personalizador personalizador) {
        JFileChooser guardar = new JFileChooser();
        guardar.setDialogTitle("Guardar plantilla");
        guardar.setFileFilter(new FileNameExtensionFilter("Excel 97-2003 (*.xls)", "xls"));
        guardar.setSelectedFile(new File(nombreArchivo));
        if (guardar.showSaveDialog(padre) != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File destino = guardar.getSelectedFile().getAbsoluteFile();
        if (!destino.getName().toLowerCase().endsWith(".xls")) {
            destino = new File(destino.getPath() + ".xls");
        }

        if (destino.exists() && !ControladorAlertas.option("El archivo " + destino.getName() + " ya existe. ¿Desea reemplazarlo?")) {
            return;
        }

        try (InputStream plantilla = DescargadorPlantillas.class.getResourceAsStream(CARPETA + nombreArchivo)) {
            if (plantilla == null) {
                ControladorAlertas.alertFail("No se encontró la plantilla " + nombreArchivo + " dentro de la aplicación");
                return;
            }
            Files.copy(plantilla, destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            ControladorAlertas.alertFail("No se pudo guardar la plantilla: " + ex.getMessage());
            return;
        }

        if (personalizador != null) {
            try {
                personalizador.personalizar(destino);
            } catch (IOException | RuntimeException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
                ControladorAlertas.alert("La plantilla se guardó, pero no se pudo completar con los datos del sistema: " + ex.getMessage());
            }
        }

        ControladorAlertas.alertSuccess("Plantilla guardada en " + destino.getPath());
        abrir(destino);
    }

    private static void abrir(File archivo) {
        try {
            if (System.getProperty("os.name", "").toLowerCase().contains("win")) {
                new ProcessBuilder("rundll32", "url.dll,FileProtocolHandler", archivo.getAbsolutePath()).start();
            } else if (java.awt.Desktop.isDesktopSupported() && java.awt.Desktop.getDesktop().isSupported(java.awt.Desktop.Action.OPEN)) {
                java.awt.Desktop.getDesktop().open(archivo);
            } else {
                ControladorAlertas.alert("No se pudo abrir el archivo automáticamente, ábralo desde " + archivo.getPath());
            }
        } catch (IOException | RuntimeException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            ControladorAlertas.alert("La plantilla se guardó, pero no se pudo abrir automáticamente: " + ex.getMessage());
        }
    }
}
