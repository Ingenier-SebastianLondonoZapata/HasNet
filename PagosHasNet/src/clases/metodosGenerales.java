
package clases;

import com.toedter.calendar.JDateChooser;
import datechooser.beans.DateChooserCombo;
import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class metodosGenerales {

// ---------------------------------------------------ESTRUCTURAS-----------------------------------------------------------------------------------------------
    public static void generarArchivoPlano(String ruta, String info) {
        File archivo = new File(ruta);
        BufferedWriter bw = null;
        try {
            if (archivo.exists()) {
                bw = new BufferedWriter(new FileWriter(archivo));
                bw.write(info);
            } else {
                bw = new BufferedWriter(new FileWriter(archivo));
                bw.write(info);
            }
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(metodosGenerales.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String campoVacio(JTextField campo) {
        if (campo.getText().equals("")) {
            return campo.getName() + ", ";
        }
        return "";
    }

    public String comboVacio(JComboBox combo) {
        if (combo.getSelectedIndex() <= 0) {
            return combo.getName() + ", ";
        }
        return "";
    }

    public String areaVacio(JTextArea campo) {
        if (campo.getText().equals("")) {
            return campo.getName() + ", ";
        }
        return "";
    }

    public String dateChooserVacio(DateChooserCombo campo) {
        if (campo.getText().equals("")) {
            return campo.getAccessibleContext().getAccessibleName() + ", ";
        }
        return "";
    }

    public String jdateChooserVacio(JDateChooser campo) {
        if (campo.getCalendar().equals(null)) {
            return campo.getAccessibleContext().getAccessibleName() + ", ";
        }
        return "";
    }

    public String camposVacios(Object[] campos) {
        String cadena = "";

        for (Object campo : campos) {

            if (campo instanceof JTextField) {
                cadena += campoVacio((JTextField) campo);
            } else if (campo instanceof JTextArea) {
                cadena += areaVacio((JTextArea) campo);
            } else if (campo instanceof JComboBox) {
                cadena += comboVacio((JComboBox) campo);
            } else if (campo instanceof DateChooserCombo) {
                cadena += dateChooserVacio((DateChooserCombo) campo);
            } else if (campo instanceof JDateChooser) {
                cadena += jdateChooserVacio((JDateChooser) campo);
            }
        }

        if (!cadena.equals("")) {
            cadena = cadena.substring(0, cadena.length() - 2);
        }

        return cadena;
    }

    public void limpiarCampos(JTextField[] textos, JComboBox[] combos) {

        for (JTextField campo : textos) {
            campo.setText("");
        }

        for (JComboBox campo : combos) {
            campo.setSelectedIndex(0);
        }
    }

// -----------------------------------------------------RECURSOS---------------------------------------------------------------  
// -----------------------------------------------------RECURSOS---------------------------------------------------------------   
    public static String fecha() {
        Calendar fecha = Calendar.getInstance();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        formato.setCalendar(fecha);
        String resultado = formato.format(fecha.getTime());
        return resultado;
    }

    public static String dia() {
        Calendar fecha = Calendar.getInstance();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        formato.setCalendar(fecha);
        String resultado = (formato.format(fecha.getTime())).substring(0, 2);

        return resultado;
    }

    public static String mes() {
        Calendar fecha = Calendar.getInstance();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        formato.setCalendar(fecha);
        String resultado = (formato.format(fecha.getTime())).substring(3, 5);

        return resultado;
    }

    public String fechaEnLetras(String dato) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date dateObj;
        String resultado = "";

        try {
            dateObj = formato.parse(dato.substring(0, 10).replace('-', '/'));
            Calendar fecha = Calendar.getInstance();
            SimpleDateFormat formato2 = new SimpleDateFormat("EEEE', 'dd 'de' MMMM 'del' yyyy");
            fecha.setTime(dateObj);
            resultado = formato2.format(fecha.getTime());
        } catch (Exception ex) {
            return "";
        }
        return resultado;
    }

    public static String mesEnPalabra() {

        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        Calendar fecha = Calendar.getInstance();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        formato.setCalendar(fecha);
        String resultado = (formato.format(fecha.getTime())).substring(3, 5);

        return meses[Integer.parseInt(resultado) - 1];
    }

    public static String anho() {
        Calendar fecha = Calendar.getInstance();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        formato.setCalendar(fecha);
        String resultado = (formato.format(fecha.getTime())).substring(6);

        return resultado;
    }

    public static String hora() {
        Calendar calendario = Calendar.getInstance();

        int hora, minutos, segundos;

        hora = calendario.get(Calendar.HOUR_OF_DAY);
        minutos = calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND);

        return hora + ":" + minutos;
    }

    public String fechaConsulta(String dato) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date dateObj;
        String resultado = "";

        try {
            dateObj = formato.parse(dato.substring(0, 10).replace('-', '/'));
            Calendar fecha = Calendar.getInstance();
            SimpleDateFormat formato2 = new SimpleDateFormat("yyyy-MM-dd");
            fecha.setTime(dateObj);
            resultado = formato2.format(fecha.getTime());
        } catch (Exception ex) {
            return "";
        }
        return resultado;
    }

    public String fecha(String dato) {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
        Date dateObj;
        String resultado = "";

        try {
            dateObj = formato.parse(dato.substring(0, 10).replace('-', '/'));
            Calendar fecha = Calendar.getInstance();
            SimpleDateFormat formato2 = new SimpleDateFormat("dd/MM/yyyy");
            fecha.setTime(dateObj);
            resultado = formato2.format(fecha.getTime());
        } catch (Exception ex) {
            return "";
        }
        return resultado;
    }

    public String fecha2(String dato) {

        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        Date dateObj;
        String resultado = "";

        try {
            dateObj = formato.parse(dato.substring(0, 10).replace('/', '-'));
            Calendar fecha = Calendar.getInstance();
            SimpleDateFormat formato2 = new SimpleDateFormat("yyyy/MM/dd");
            fecha.setTime(dateObj);
            resultado = formato2.format(fecha.getTime());
        } catch (Exception ex) {
            return "";
        }

        return resultado;
    }

    public String fecha3(String dato) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date dateObj;
        String resultado = "";

        try {
            dateObj = formato.parse(dato.substring(0, 10).replace('-', '/'));
            Calendar fecha = Calendar.getInstance();
            SimpleDateFormat formato2 = new SimpleDateFormat("yyyy/MM/dd");
            fecha.setTime(dateObj);
            resultado = formato2.format(fecha.getTime());
        } catch (Exception ex) {
            return "";
        }
        return resultado;
    }

    public String fecha4(String dato) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date dateObj;
        String resultado = "";
        try {
            dateObj = formato.parse(dato.substring(0, 10).replace('-', '/'));
            Calendar fecha = Calendar.getInstance();
            SimpleDateFormat formato2 = new SimpleDateFormat("yyyy-MM-dd");
            fecha.setTime(dateObj);
            resultado = formato2.format(fecha.getTime());
        } catch (Exception ex) {
            return "";
        }
        return resultado;
    }

    public String desdeDate(Calendar date) {
        String sDia = "", sMes = "", fecha = "";
        int sAnho;

        Calendar cal = date;
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        int mes = cal.get(Calendar.MONTH) + 1;

        if (dia < 10) {
            sDia = "0";
            sDia += dia;
        } else {
            sDia = "" + dia;
        }
        if (mes < 10) {
            sMes = "0";
        }

        sMes += mes;
        sAnho = cal.get(Calendar.YEAR);
        fecha = sAnho + "-" + sMes + "-" + sDia;

        return fecha;
    }

    public String desdeDate2(Calendar date) {
        String sDia = "", sMes = "", fecha = "";
        int sAnho;

        Calendar cal = date;
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        int mes = cal.get(Calendar.MONTH) + 1;

        if (dia < 10) {
            sDia = "0";
            sDia += dia;
        } else {
            sDia = "" + dia;
        }
        if (mes < 10) {
            sMes = "0";
        }

        sMes += mes;
        sAnho = cal.get(Calendar.YEAR);
        fecha = sDia + "/" + sMes + "/" + sAnho;

        return fecha;
    }

    public String sumarFecha(String fechaActual, int dias) {

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date dateObj;
        try {
            dateObj = formato.parse(fechaActual);
            Calendar fecha = Calendar.getInstance();
            fecha.setTime(dateObj);
            fecha.add(Calendar.DATE, dias);
            String resultado = formato.format(fecha.getTime());
            return resultado;

        } catch (ParseException ex) {
            Logger.getLogger(metodosGenerales.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return "error fecha";
    }

    public String sumarMeses(String fechaActual, int meses) {

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date dateObj;
        try {
            dateObj = formato.parse(fechaActual);
            Calendar fecha = Calendar.getInstance();
            fecha.setTime(dateObj);
            fecha.add(Calendar.MONTH, meses);
            String resultado = formato.format(fecha.getTime());
            return resultado;

        } catch (ParseException ex) {
            Logger.getLogger(metodosGenerales.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return "error fecha";
    }

    public long restarFecha(String fechaUno, String fechaDos) {

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date f1, f2;
        try {
            f1 = formato.parse(fechaUno);
            Calendar fecha = Calendar.getInstance();
            fecha.setTime(f1);

            f2 = formato.parse(fechaDos);
            Calendar fecha2 = Calendar.getInstance();
            fecha2.setTime(f2);

            return (fecha2.getTimeInMillis() - fecha.getTimeInMillis()) / (1000 * 60 * 60 * 24);

        } catch (ParseException ex) {
            Logger.getLogger(metodosGenerales.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }

    public String formatoFecha(String dato) {

        SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
        Date dateObj;
        String resultado = "";

        try {
            dateObj = formato.parse(dato.substring(0, 10).replace('-', '/'));
            Calendar fecha = Calendar.getInstance();
            SimpleDateFormat formato2 = new SimpleDateFormat("dd/MM/yyyy");
            fecha.setTime(dateObj);
            resultado = formato2.format(fecha.getTime());
        } catch (Exception ex) {
            return "";
        }

        return resultado;
    }

    public boolean soloNum(KeyEvent evt) {

        if (!Character.isDigit(evt.getKeyChar()) && !Character.toString(evt.getKeyChar()).equals(",") && !Character.toString(evt.getKeyChar()).equals(".")) {
            evt.consume();
            return false;
        }

        return true;
    }

    public void ponerIcono(String imag, JLabel etiq) {
        try {
            Image fot = new ImageIcon(getClass().getResource(imag)).getImage();

            //ImageIcon icono = new ImageIcon(fot.getScaledInstance(etiq.getWidth(), etiq.getHeight(), Image.SCALE_DEFAULT));
            ImageIcon icono = new ImageIcon(fot);
            etiq.setIcon(icono);
        } catch (NullPointerException e) {
            etiq.setIcon(null);
        }
    }

    public static void presionarBoton(JButton campo) {
        Robot robot;

        try {
            campo.requestFocus();
            robot = new Robot();
            robot.delay(200);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (AWTException ex) {
            Logger.getLogger(metodosGenerales.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void presionarEnter(JTextField campo) {
        Robot robot;
        try {
            campo.requestFocus();
            robot = new Robot();
            robot.delay(200);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (AWTException ex) {
            Logger.getLogger(metodosGenerales.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Calendar haciaDate(String dato) {

        SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
        Date dateObj;

        try {
            dateObj = formato.parse(dato.substring(0, 10).replace('-', '/'));
            Calendar fecha = Calendar.getInstance();
            fecha.setTime(dateObj);
            return fecha;
        } catch (Exception ex) {
            return null;
        }
    }

    public Calendar haciaDate2(String dato) {

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date dateObj;

        try {
            dateObj = formato.parse(dato.substring(0, 10).replace('-', '/'));
            Calendar fecha = Calendar.getInstance();
            fecha.setTime(dateObj);
            return fecha;
        } catch (Exception ex) {
            return null;
        }
    }

//    public Date haciaDateJCalendar(String dato) {
//
//        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
//        Date fechaDate = formato.parse(dato);
//        return fechaDate;
////        jdcFecha.setDate(fechaDate);
//
//        SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
//        Date dateObj;
//
//        try {
//            dateObj = formato.parse(dato.substring(0, 10).replace('-', '/'));
//            return dateObj;
//        } catch (Exception ex) {
//            return null;
//        }
//    }
    public static String convertToMultiline(String orig) {
        return "<html><center>" + orig.replaceAll("\\n", "<br />") + "</center></html>";
    }

    public static boolean toBoolean(String x) {
        return x.equals("1");
    }

    public static boolean emailEsCorrecto(String email) {
        String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        // Compiles the given regular expression into a pattern.
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);

        // Match the given input against this pattern
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static Integer calcularEdad(String fecha) {
        Date fechaNac = null;
        try {
            /**
             * Se puede cambiar la mascara por el formato de la fecha que se
             * quiera recibir, por ejemplo año mes día "yyyy-MM-dd" en este caso
             * es día mes año
             */
            fecha = fecha.replace("/", "-");
            fechaNac = new SimpleDateFormat("dd-MM-yyyy").parse(fecha);
        } catch (Exception ex) {
            System.out.println("Error:" + ex);
        }

        Calendar fechaNacimiento = Calendar.getInstance();
        //Se crea un objeto con la fecha actual
        Calendar fechaActual = Calendar.getInstance();
        //Se asigna la fecha recibida a la fecha de nacimiento.
        fechaNacimiento.setTime(fechaNac);
        //Se restan la fecha actual y la fecha de nacimiento
        int año = fechaActual.get(Calendar.YEAR) - fechaNacimiento.get(Calendar.YEAR);
        int mes = fechaActual.get(Calendar.MONTH) - fechaNacimiento.get(Calendar.MONTH);
        int dia = fechaActual.get(Calendar.DATE) - fechaNacimiento.get(Calendar.DATE);
        //Se ajusta el año dependiendo el mes y el día
        if (mes < 0 || (mes == 0 && dia < 0)) {
            año--;
        }
        //Regresa la edad en base a la fecha de nacimiento       
        return año;
    }

    public static String calcularEdad2(String fecha) {
        Date fechaNac = null;
        try {
            /**
             * Se puede cambiar la mascara por el formato de la fecha que se
             * quiera recibir, por ejemplo año mes día "yyyy-MM-dd" en este caso
             * es día mes año
             */
            fecha = fecha.replace("/", "-");
            fechaNac = new SimpleDateFormat("dd-MM-yyyy").parse(fecha);
        } catch (Exception ex) {
            System.out.println("Error:" + ex);
        }
        Calendar fechaNacimiento = Calendar.getInstance();
        //Se crea un objeto con la fecha actual
        Calendar fechaActual = Calendar.getInstance();
        //Se asigna la fecha recibida a la fecha de nacimiento.
        fechaNacimiento.setTime(fechaNac);
        //Se restan la fecha actual y la fecha de nacimiento
        int año = fechaActual.get(Calendar.YEAR) - fechaNacimiento.get(Calendar.YEAR);
        int mes = fechaActual.get(Calendar.MONTH) - fechaNacimiento.get(Calendar.MONTH);
        int dia = fechaActual.get(Calendar.DATE) - fechaNacimiento.get(Calendar.DATE);
        //Se ajusta el año dependiendo el mes y el día
        if (mes < 0 || (mes == 0 && dia < 0)) {
            año--;
        }
        //Regresa la edad en base a la fecha de nacimiento       

        if (año == 0) {
            if (mes >= 1) {
                return mes + "-Meses";
            } else {
                return dia + "-Días";
            }
        }
        return año + "-Años";
    }

    public static Integer calcularEdadMeses(String fecha) {
        Date fechaNac = null;
        try {
            fecha = fecha.replace("/", "-");
            fechaNac = new SimpleDateFormat("dd-MM-yyyy").parse(fecha);
        } catch (Exception ex) {
            System.out.println("Error:" + ex);
        }
        Calendar fechaNacimiento = Calendar.getInstance();
        //Se crea un objeto con la fecha actual
        Calendar fechaActual = Calendar.getInstance();
        //Se asigna la fecha recibida a la fecha de nacimiento.
        fechaNacimiento.setTime(fechaNac);
        //Se restan la fecha actual y la fecha de nacimiento
        int año = fechaActual.get(Calendar.YEAR) - fechaNacimiento.get(Calendar.YEAR);
        int mes = fechaActual.get(Calendar.MONTH) - fechaNacimiento.get(Calendar.MONTH);
        int dia = fechaActual.get(Calendar.DATE) - fechaNacimiento.get(Calendar.DATE);
        //Se ajusta el año dependiendo el mes y el día
        if (mes < 0 || (mes == 0 && dia < 0)) {
            año--;
        }
        if (mes < 0) {
            mes = 12 + mes;
        }
        return mes;
    }

    public String obtenerRuta1(JInternalFrame padre, String nombre) {
        JFileChooser ventana = new JFileChooser("C:\\");
        ventana.setSelectedFile(new File("C:\\" + nombre + ".pdf"));
        int boton = ventana.showDialog(null, "Seleccionar");
        if (boton == 0) {
            return ventana.getSelectedFile().toString();
        }
        return null;
    }

    public String obtenerRuta(JInternalFrame padre, String nombre) {
        JFileChooser ventana = new JFileChooser("C:\\");
        ventana.setSelectedFile(new File("C:\\" + nombre + ".xls"));
        int boton = ventana.showDialog(null, "Seleccionar");
        if (boton == 0) {
            return ventana.getSelectedFile().toString();
        }
        return null;
    }

    public String obtenerRuta2(JInternalFrame padre, String nombre) {
        JFileChooser ventana = new JFileChooser("C:\\");
        ventana.setSelectedFile(new File("C:\\" + nombre + ".accdb"));
        int boton = ventana.showDialog(null, "Seleccionar");
        if (boton == 0) {
            return ventana.getSelectedFile().toString();
        }
        return null;
    }

    public String obtenerRuta4(JInternalFrame padre, String nombre) {
        JFileChooser ventana = new JFileChooser("C:\\");
        ventana.setSelectedFile(new File("C:\\" + nombre + ".sql"));
        int boton = ventana.showDialog(null, "Seleccionar");
        if (boton == 0) {
            return ventana.getSelectedFile().toString();
        }
        return null;
    }

    public static void copiarArchivo(String origen, String destino) {
        try {
            Path FROM = Paths.get(origen);
            Path TO = Paths.get(destino);
            //sobreescribir el fichero de destino, si existe, y copiar
            // los atributos, incluyendo los permisos rwx
            CopyOption[] options = new CopyOption[]{
                StandardCopyOption.REPLACE_EXISTING,
                StandardCopyOption.COPY_ATTRIBUTES
            };
            Files.copy(FROM, TO, options);

        } catch (IOException ex) {
            Logger.getLogger(metodosGenerales.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void abrirArchivo(String ruta) {
        try {
            File objetofile = new File(ruta);
            Desktop.getDesktop().open(objetofile);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public static String fechaHora() {
        Date fecha1 = new Date();
        return fecha1.toLocaleString();
    }

    public String leerArchivo(String archivo) {
        try {
            String cadena;
            FileReader f;

            f = new FileReader(archivo);
            BufferedReader b = new BufferedReader(f);
            while ((cadena = b.readLine()) != null) {
                return cadena;
            }
            try {

            } catch (Exception e) {
            }
            b.close();

        } catch (IOException ex) {
            Logger.getLogger(metodosGenerales.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "1";
    }

    //Montar una imagen de terceros y recortarla.
    public void montarImagenTerceros(String origen, String destino) {
        //llama el metodo con la ruta de origen, destino y el tamaño que debe tomar la imagen.
        recortarYguardarImagen(origen, destino, 250, 250);
    }

    //Montar la imagen del logo.
    public void montarLogo(String origen, String destino) {
        //llama el metodo con la ruta de origen, destino y el tamaño que debe tomar la imagen.
        recortarYguardarImagen(origen, destino, 280, 150);
    }

    //Montar las firmas
    public void montarFirma(String origen, String destino) {
        //llama el metodo con la ruta de origen, destino y el tamaño que debe tomar la imagen.
        recortarYguardarImagen(origen, destino, 200, 60);
    }

    /*Este método es el de la magia recibe la ruta al archivo original y la ruta donde vamos a guardar la copia*/
    public static void recortarYguardarImagen(String origen, String destino, int anchoMax, int altoMax) {
        BufferedImage imagen = cargarImagen(origen);
        if (imagen.getHeight() > imagen.getWidth()) {
            int heigt = (imagen.getHeight() * anchoMax) / imagen.getWidth();
            imagen = recortar(imagen, anchoMax, heigt);
            int width = (imagen.getWidth() * altoMax) / imagen.getHeight();
            imagen = recortar(imagen, width, altoMax);
        } else {
            int width = (imagen.getWidth() * altoMax) / imagen.getHeight();
            imagen = recortar(imagen, width, altoMax);
            int heigt = (imagen.getHeight() * anchoMax) / imagen.getWidth();
            imagen = recortar(imagen, anchoMax, heigt);
        }
        guardarImagen(imagen, destino);
    }

    /*Este método se utiliza para cargar la imagen de disco*/
    public static BufferedImage cargarImagen(String origen) {
        BufferedImage bimage = null;
        try {
            bimage = ImageIO.read(new File(origen));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bimage;
    }
    /*Este método se utiliza para almacenar la imagen en disco*/

    public static void guardarImagen(BufferedImage bufferedImage, String pathName) {
        try {
            String format = (pathName.endsWith(".png")) ? "png" : "jpg";
            File file = new File(pathName);
            file.getParentFile().mkdirs();
            ImageIO.write(bufferedImage, format, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*Este método se utiliza para redimensionar la imagen */

    public static BufferedImage recortar(BufferedImage bufferedImage, int newW, int newH) {
        int w = bufferedImage.getWidth();
        int h = bufferedImage.getHeight();
        BufferedImage bufim = new BufferedImage(newW, newH, bufferedImage.getType());
        Graphics2D g = bufim.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(bufferedImage, 0, 0, newW, newH, 0, 0, w, h, null);
        g.dispose();
        return bufim;
    }
}
