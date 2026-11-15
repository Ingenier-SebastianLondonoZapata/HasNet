package Vista.Productos;

import Controlador.Alertas.ControladorAlertas;
import Dao.SoportesMercantiles.DaoSoportesMercantiles;
import Enums.EventoMercantil;
import Modelo.SoportesMercantiles.DocumentoMercantil;
import Modelo.SoportesMercantiles.FiltroSoportesMercantiles;
import Modelo.SoportesMercantiles.LecturaDocumentosMercantiles;
import Modelo.SoportesMercantiles.RegistroSoporteMercantil;
import Modelo.SoportesMercantiles.ResultadoEventoMercantil;
import Procesos.SoportesMercantiles.Servicio.LectorDocumentosMercantiles;
import Procesos.SoportesMercantiles.Servicio.ServicioSoportesMercantiles;
import clases.Instancias;
import clases.big;
import datechooser.beans.DateChooserCombo;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class VistaSoporteMercantil extends javax.swing.JDialog {

    private static final Color VERDE = new Color(30, 132, 73);
    private static final Color ROJO = new Color(176, 58, 46);
    private static final Font FUENTE_ESTADO = new Font("Arial", Font.BOLD, 13);
    private static final String TODOS_EMISORES = "Todos";
    private static final DateTimeFormatter FORMATO_FECHA_HORA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final int[] ANCHOS_COLUMNAS = {130, 90, 95, 200, 80, 100, 150, 115, 260, 150};
    private static final int COLUMNA_TOTAL = 5;
    private static final int COLUMNA_ESTADO = 7;

    private final Instancias instancias = Instancias.getInstancias();
    private final DaoSoportesMercantiles dao = new DaoSoportesMercantiles();
    private final LectorDocumentosMercantiles lector = new LectorDocumentosMercantiles();
    private final AtomicBoolean cancelar = new AtomicBoolean(false);
    private final List<RegistroSoporteMercantil> registros = new ArrayList<>();

    private DefaultTableModel modeloHistorial;
    private File archivoXml;
    private SwingWorker<ServicioSoportesMercantiles.Resumen, Void> proceso;

    public VistaSoporteMercantil(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        configurarComponentes();
        inicializarFiltros();
        pack();
        setMinimumSize(getSize());
        setLocationRelativeTo(null);
    }

    private void configurarComponentes() {
        getRootPane().registerKeyboardAction(e -> cerrar(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_IN_FOCUSED_WINDOW);

        dtFechaInicio.setFormat(2);
        dtFechaFin.setFormat(2);
        barraProgreso.setString("");

        modeloHistorial = (DefaultTableModel) tblHistorial.getModel();
        tblHistorial.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblHistorial.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        for (int i = 0; i < ANCHOS_COLUMNAS.length && i < tblHistorial.getColumnCount(); i++) {
            tblHistorial.getColumnModel().getColumn(i).setPreferredWidth(ANCHOS_COLUMNAS[i]);
        }
        DefaultTableCellRenderer derecha = new DefaultTableCellRenderer();
        derecha.setHorizontalAlignment(SwingConstants.RIGHT);
        tblHistorial.getColumnModel().getColumn(COLUMNA_TOTAL).setCellRenderer(derecha);
        tblHistorial.getColumnModel().getColumn(COLUMNA_ESTADO).setCellRenderer(new RenderEstado());
    }

    private void inicializarFiltros() {
        LocalDate hoy = LocalDate.now();
        dtFechaInicio.setSelectedDate(calendario(hoy.withDayOfMonth(1)));
        dtFechaFin.setSelectedDate(calendario(hoy));
        cargarEmisores();
        consultar();
    }

    private void cargarEmisores() {
        Object seleccionado = cmbEmisor.getSelectedItem();
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        modelo.addElement(TODOS_EMISORES);
        try {
            for (String nombre : dao.nombresEmisores()) {
                modelo.addElement(nombre);
            }
        } catch (SQLException ex) {
            System.err.println("No se pudieron cargar los emisores de soportes mercantiles: " + ex.getMessage());
        }
        cmbEmisor.setModel(modelo);
        if (seleccionado != null) {
            cmbEmisor.setSelectedItem(seleccionado);
        }
    }

    private void consultar() {
        FiltroSoportesMercantiles filtro = new FiltroSoportesMercantiles();
        filtro.setFechaInicio(fecha(dtFechaInicio));
        filtro.setFechaFin(fecha(dtFechaFin));
        if (filtro.getFechaInicio() != null && filtro.getFechaFin() != null && filtro.getFechaInicio().isAfter(filtro.getFechaFin())) {
            ControladorAlertas.alert("La fecha inicio no puede ser mayor a la fecha fin");
            return;
        }
        filtro.setPrefijo(txtPrefijo.getText());
        filtro.setFolio(txtFolio.getText());
        filtro.setCufe(txtCufe.getText());
        filtro.setNitEmisor(txtNitEmisor.getText());
        Object emisor = cmbEmisor.getSelectedItem();
        filtro.setNombreEmisor(emisor == null || TODOS_EMISORES.equals(emisor) ? "" : emisor.toString());

        List<RegistroSoporteMercantil> encontrados;
        try {
            encontrados = dao.consultar(filtro);
        } catch (SQLException ex) {
            ControladorAlertas.alertFail("No se pudo consultar el historial de soportes mercantiles: " + ex.getMessage());
            return;
        }

        registros.clear();
        registros.addAll(encontrados);
        modeloHistorial.setRowCount(0);
        for (RegistroSoporteMercantil r : registros) {
            modeloHistorial.addRow(new Object[]{
                r.getFechaRegistro() == null ? "" : r.getFechaRegistro().format(FORMATO_FECHA_HORA),
                r.getNumeroDocumento(),
                r.getNitEmisor(),
                r.getNombreEmisor(),
                r.getFechaEmision() == null ? "" : r.getFechaEmision().format(FORMATO_FECHA),
                r.getTotal() == null ? "" : big.setMoneda(r.getTotal()),
                EventoMercantil.descripcionDe(r.getEvento()),
                textoEstado(r.getEstado()),
                r.getErrores().isEmpty() ? r.getDescripcion() : r.getErrores().split("\n")[0],
                r.getCufe()});
        }
        lbTotalRegistros.setText(registros.size() + " registros. Doble clic en una fila para ver el detalle.");
    }

    private void seleccionarXml() {
        JFileChooser examinar = new JFileChooser();
        examinar.setDialogTitle("Factura electrónica (.xml o .zip)");
        examinar.setFileFilter(new FileNameExtensionFilter("XML o ZIP (*.xml, *.zip)", "xml", "zip"));
        if (examinar.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            archivoXml = examinar.getSelectedFile().getAbsoluteFile();
            txtArchivoXml.setText(archivoXml.getName());
        }
    }

    private void procesarExcelDian() {
        JFileChooser examinar = new JFileChooser();
        examinar.setDialogTitle("Excel de documentos recibidos descargado de la DIAN");
        examinar.setFileFilter(new FileNameExtensionFilter("Excel (*.xlsx, *.xls)", "xlsx", "xls"));
        if (examinar.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
            return;
        }
        iniciarProceso(examinar.getSelectedFile().getAbsoluteFile(), true);
    }

    private void procesarXml() {
        if (archivoXml == null) {
            seleccionarXml();
            if (archivoXml == null) {
                return;
            }
        }
        iniciarProceso(archivoXml, false);
    }

    private void iniciarProceso(File archivo, boolean esExcel) {
        ServicioSoportesMercantiles servicio = new ServicioSoportesMercantiles(instancias.getUsuario());
        if (!servicio.hayDatosDelEmisor()) {
            ControladorAlertas.alertFail("Para gestionar soportes mercantiles debe tener activa la facturación electrónica e iniciar sesión nuevamente");
            return;
        }

        LecturaDocumentosMercantiles lectura;
        try {
            lectura = esExcel ? lector.leerExcelDian(archivo) : lector.leerXmlOZip(archivo);
        } catch (IOException ex) {
            ControladorAlertas.alertFail("No se pudo leer el archivo " + archivo.getName() + ": " + ex.getMessage());
            return;
        }

        txtBitacora.setText("");
        for (String aviso : lectura.getAvisos()) {
            registrarBitacora(aviso);
        }

        List<DocumentoMercantil> documentos = lectura.getDocumentos();
        if (documentos.isEmpty()) {
            ControladorAlertas.alert("No se encontraron facturas para procesar en " + archivo.getName());
            return;
        }

        Boolean continuar = ControladorAlertas.option("Se enviarán los eventos mercantiles a " + documentos.size()
                + (documentos.size() == 1 ? " factura" : " facturas") + ". ¿Desea continuar?");
        if (continuar == null || !continuar) {
            return;
        }

        ejecutar(servicio, documentos);
    }

    private void ejecutar(ServicioSoportesMercantiles servicio, List<DocumentoMercantil> documentos) {
        cancelar.set(false);
        habilitarControles(false);
        barraProgreso.setMaximum(documentos.size());
        barraProgreso.setValue(0);
        barraProgreso.setString("0 / " + documentos.size());
        registrarBitacora("Procesando " + documentos.size() + " facturas...");

        proceso = new SwingWorker<ServicioSoportesMercantiles.Resumen, Void>() {
            @Override
            protected ServicioSoportesMercantiles.Resumen doInBackground() {
                return servicio.procesar(documentos, (procesados, total, mensaje) -> SwingUtilities.invokeLater(() -> {
                    barraProgreso.setValue(procesados);
                    barraProgreso.setString(procesados + " / " + total);
                    registrarBitacora(mensaje);
                }), cancelar::get);
            }

            @Override
            protected void done() {
                habilitarControles(true);
                proceso = null;
                try {
                    mostrarResumen(get());
                } catch (InterruptedException | ExecutionException ex) {
                    registrarBitacora("Error inesperado: " + ex.getMessage());
                    ControladorAlertas.alertFail("Ocurrió un error procesando los soportes mercantiles: " + ex.getMessage());
                }
                cargarEmisores();
                dtFechaFin.setSelectedDate(calendario(LocalDate.now()));
                consultar();
            }
        };
        proceso.execute();
    }

    private void mostrarResumen(ServicioSoportesMercantiles.Resumen resumen) {
        StringBuilder texto = new StringBuilder();
        texto.append("Soportes mercantiles completos: ").append(resumen.getCompletos());
        if (resumen.getYaCompletos() > 0) {
            texto.append("\nYa eran soporte mercantil: ").append(resumen.getYaCompletos());
        }
        if (resumen.getConErrores() > 0) {
            texto.append("\nCon errores: ").append(resumen.getConErrores());
        }
        if (resumen.getSinProcesar() > 0) {
            texto.append("\nSin procesar: ").append(resumen.getSinProcesar());
        }
        if (resumen.fueDetenido()) {
            texto.append("\n\nEl proceso se detuvo: ").append(resumen.getMotivoDetencion());
        }
        registrarBitacora(texto.toString().replace("\n\n", "\n"));

        if (resumen.getConErrores() == 0 && !resumen.fueDetenido()) {
            ControladorAlertas.alertSuccess(texto.toString());
        } else {
            ControladorAlertas.alert(texto.toString() + "\n\nRevise el detalle en el historial.");
        }
    }

    private void solicitarCancelacion() {
        cancelar.set(true);
        btnCancelar.setEnabled(false);
        registrarBitacora("Cancelando... se termina la factura en curso.");
    }

    private void cerrar() {
        if (proceso != null) {
            Boolean salir = ControladorAlertas.option("Hay un proceso en curso. ¿Desea cancelarlo y cerrar?");
            if (salir == null || !salir) {
                return;
            }
            solicitarCancelacion();
        }
        dispose();
    }

    private void habilitarControles(boolean habilitar) {
        btnSubirExcel.setEnabled(habilitar);
        btnSubirXml.setEnabled(habilitar);
        btnExaminarXml.setEnabled(habilitar);
        btnConsultar.setEnabled(habilitar);
        btnCancelar.setEnabled(!habilitar);
        setCursor(habilitar ? Cursor.getDefaultCursor() : Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    }

    private void registrarBitacora(String mensaje) {
        txtBitacora.append(mensaje + "\n");
        txtBitacora.setCaretPosition(txtBitacora.getDocument().getLength());
    }

    private void mostrarDetalle(RegistroSoporteMercantil r) {
        StringBuilder detalle = new StringBuilder();
        detalle.append("Documento: ").append(r.getNumeroDocumento()).append("\n");
        detalle.append("Emisor: ").append(r.getNombreEmisor()).append(" (").append(r.getNitEmisor()).append(")\n");
        detalle.append("CUFE: ").append(r.getCufe()).append("\n");
        detalle.append("Evento: ").append(EventoMercantil.descripcionDe(r.getEvento())).append("\n");
        detalle.append("Estado: ").append(textoEstado(r.getEstado())).append("\n");
        if (!r.getDescripcion().isEmpty()) {
            detalle.append("Descripción: ").append(r.getDescripcion()).append("\n");
        }
        if (!r.getUuidEvento().isEmpty()) {
            detalle.append("UUID del evento: ").append(r.getUuidEvento()).append("\n");
        }
        if (!r.getFechaEvento().isEmpty()) {
            detalle.append("Fecha del evento: ").append(r.getFechaEvento()).append("\n");
        }
        if (!r.getErrores().isEmpty()) {
            detalle.append("\nErrores:\n").append(r.getErrores()).append("\n");
        }
        detalle.append("\nOrigen: ").append(r.getOrigen()).append(r.getArchivo().isEmpty() ? "" : " - " + r.getArchivo());
        detalle.append("\nUsuario: ").append(r.getUsuario());

        JTextArea texto = new JTextArea(detalle.toString(), 14, 60);
        texto.setEditable(false);
        texto.setLineWrap(true);
        texto.setWrapStyleWord(true);
        texto.setFont(new Font("Arial", Font.PLAIN, 13));
        JOptionPane.showMessageDialog(this, new JScrollPane(texto), "Detalle del soporte mercantil", JOptionPane.INFORMATION_MESSAGE);
    }

    private static String textoEstado(String estado) {
        switch (estado == null ? "" : estado) {
            case ResultadoEventoMercantil.EXITOSO:
                return "Exitoso";
            case ResultadoEventoMercantil.YA_REGISTRADO:
                return "Ya registrado";
            case ResultadoEventoMercantil.ERROR_DIAN:
                return "Rechazado DIAN";
            default:
                return "Error";
        }
    }

    private static class RenderEstado extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable tabla, Object valor, boolean seleccionado, boolean foco, int fila, int columna) {
            Component celda = super.getTableCellRendererComponent(tabla, valor, seleccionado, foco, fila, columna);
            if (!seleccionado) {
                boolean correcto = "Exitoso".equals(valor) || "Ya registrado".equals(valor);
                celda.setForeground(correcto ? VERDE : ROJO);
            }
            setFont(FUENTE_ESTADO);
            return celda;
        }
    }

    private static LocalDate fecha(DateChooserCombo selector) {
        Calendar calendario = selector.getSelectedDate();
        return calendario == null ? null : calendario.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    private static Calendar calendario(LocalDate fecha) {
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(Date.from(fecha.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        return calendario;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlSoporteMercantil = new javax.swing.JPanel();
        pnlProcesar = new javax.swing.JPanel();
        lbTituloProcesar = new javax.swing.JLabel();
        lbArchivoMasivo = new javax.swing.JLabel();
        btnSubirExcel = new javax.swing.JButton();
        lbNotaExcel = new javax.swing.JLabel();
        lbArchivoXml = new javax.swing.JLabel();
        txtArchivoXml = new javax.swing.JTextField();
        btnExaminarXml = new javax.swing.JButton();
        btnSubirXml = new javax.swing.JButton();
        lbEventos = new javax.swing.JLabel();
        barraProgreso = new javax.swing.JProgressBar();
        btnCancelar = new javax.swing.JButton();
        scrBitacora = new javax.swing.JScrollPane();
        txtBitacora = new javax.swing.JTextArea();
        pnlConsultar = new javax.swing.JPanel();
        lbTituloConsultar = new javax.swing.JLabel();
        lbFechaInicio = new javax.swing.JLabel();
        lbFechaFin = new javax.swing.JLabel();
        lbPrefijo = new javax.swing.JLabel();
        lbFolio = new javax.swing.JLabel();
        dtFechaInicio = new datechooser.beans.DateChooserCombo();
        dtFechaFin = new datechooser.beans.DateChooserCombo();
        txtPrefijo = new javax.swing.JTextField();
        txtFolio = new javax.swing.JTextField();
        lbCufe = new javax.swing.JLabel();
        txtCufe = new javax.swing.JTextField();
        lbNombreEmisor = new javax.swing.JLabel();
        lbNitEmisor = new javax.swing.JLabel();
        cmbEmisor = new javax.swing.JComboBox();
        txtNitEmisor = new javax.swing.JTextField();
        btnConsultar = new javax.swing.JButton();
        pnlHistorial = new javax.swing.JPanel();
        lbTituloHistorial = new javax.swing.JLabel();
        scrHistorial = new javax.swing.JScrollPane();
        tblHistorial = new javax.swing.JTable();
        lbTotalRegistros = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Soportes mercantiles");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        pnlSoporteMercantil.setBackground(new java.awt.Color(255, 255, 255));

        pnlProcesar.setBackground(new java.awt.Color(255, 255, 255));
        pnlProcesar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 220, 220)));

        lbTituloProcesar.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        lbTituloProcesar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTituloProcesar.setText("Subir y procesar masivamente");

        lbArchivoMasivo.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        lbArchivoMasivo.setText("Archivo masivo de CUFES");

        btnSubirExcel.setBackground(new java.awt.Color(204, 204, 204));
        btnSubirExcel.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        btnSubirExcel.setText("SUBIR Y PROCESAR");
        btnSubirExcel.setToolTipText("Excel con los CUFES que descarga de la DIAN");
        btnSubirExcel.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 14, 8, 14));
        btnSubirExcel.setContentAreaFilled(false);
        btnSubirExcel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSubirExcel.setFocusPainted(false);
        btnSubirExcel.setOpaque(true);
        btnSubirExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubirExcelActionPerformed(evt);
            }
        });

        lbNotaExcel.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        lbNotaExcel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNotaExcel.setText("<html><div style='width:170px;text-align:center'>Suba el mismo archivo Excel que descarga de la DIAN (Histórico &gt; Descargar listado)</div></html>");

        lbArchivoXml.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        lbArchivoXml.setText("Archivo XML o .ZIP");

        txtArchivoXml.setEditable(false);
        txtArchivoXml.setBackground(new java.awt.Color(255, 255, 255));
        txtArchivoXml.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        btnExaminarXml.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        btnExaminarXml.setText("Examinar");
        btnExaminarXml.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnExaminarXml.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExaminarXmlActionPerformed(evt);
            }
        });

        btnSubirXml.setBackground(new java.awt.Color(204, 204, 204));
        btnSubirXml.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        btnSubirXml.setText("SUBIR Y PROCESAR");
        btnSubirXml.setToolTipText("AttachedDocument (.xml) que envía el proveedor, o un .zip con varios");
        btnSubirXml.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 14, 8, 14));
        btnSubirXml.setContentAreaFilled(false);
        btnSubirXml.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSubirXml.setFocusPainted(false);
        btnSubirXml.setOpaque(true);
        btnSubirXml.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubirXmlActionPerformed(evt);
            }
        });

        lbEventos.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        lbEventos.setText("<html><div style='width:430px'>A cada factura se le envían los eventos: <b>Acuse de recibo</b>, <b>Recibo del bien o servicio</b> y <b>Aceptación expresa</b>. Los que ya estén registrados no se reenvían.</div></html>");

        barraProgreso.setStringPainted(true);

        btnCancelar.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        btnCancelar.setText("CANCELAR");
        btnCancelar.setEnabled(false);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        txtBitacora.setEditable(false);
        txtBitacora.setColumns(20);
        txtBitacora.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtBitacora.setLineWrap(true);
        txtBitacora.setRows(5);
        txtBitacora.setWrapStyleWord(true);
        scrBitacora.setViewportView(txtBitacora);

        javax.swing.GroupLayout pnlProcesarLayout = new javax.swing.GroupLayout(pnlProcesar);
        pnlProcesar.setLayout(pnlProcesarLayout);
        pnlProcesarLayout.setHorizontalGroup(
            pnlProcesarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProcesarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlProcesarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTituloProcesar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlProcesarLayout.createSequentialGroup()
                        .addGroup(pnlProcesarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbArchivoMasivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbArchivoXml, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlProcesarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlProcesarLayout.createSequentialGroup()
                                .addComponent(btnSubirExcel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbNotaExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 190, Short.MAX_VALUE))
                            .addGroup(pnlProcesarLayout.createSequentialGroup()
                                .addComponent(txtArchivoXml, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnExaminarXml)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSubirXml))))
                    .addComponent(lbEventos, javax.swing.GroupLayout.PREFERRED_SIZE, 518, Short.MAX_VALUE)
                    .addGroup(pnlProcesarLayout.createSequentialGroup()
                        .addComponent(barraProgreso, javax.swing.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar))
                    .addComponent(scrBitacora))
                .addContainerGap())
        );
        pnlProcesarLayout.setVerticalGroup(
            pnlProcesarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProcesarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTituloProcesar)
                .addGap(10, 10, 10)
                .addGroup(pnlProcesarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lbArchivoMasivo)
                    .addComponent(btnSubirExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNotaExcel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlProcesarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lbArchivoXml)
                    .addComponent(txtArchivoXml, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExaminarXml, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSubirXml, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addComponent(lbEventos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(pnlProcesarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(barraProgreso, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addComponent(scrBitacora, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlConsultar.setBackground(new java.awt.Color(255, 255, 255));
        pnlConsultar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 220, 220)));

        lbTituloConsultar.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        lbTituloConsultar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTituloConsultar.setText("Consultar");

        lbFechaInicio.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        lbFechaInicio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbFechaInicio.setText("Fecha inicio");

        lbFechaFin.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        lbFechaFin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbFechaFin.setText("Fecha fin");

        lbPrefijo.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        lbPrefijo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbPrefijo.setText("Prefijo");

        lbFolio.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        lbFolio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbFolio.setText("Folio");

        txtPrefijo.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        txtPrefijo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrefijoActionPerformed(evt);
            }
        });

        txtFolio.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        txtFolio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFolioActionPerformed(evt);
            }
        });

        lbCufe.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        lbCufe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCufe.setText("CUFE");

        txtCufe.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        txtCufe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCufeActionPerformed(evt);
            }
        });

        lbNombreEmisor.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        lbNombreEmisor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNombreEmisor.setText("Nombre emisor");

        lbNitEmisor.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        lbNitEmisor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNitEmisor.setText("NIT del emisor");

        cmbEmisor.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        txtNitEmisor.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        txtNitEmisor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNitEmisorActionPerformed(evt);
            }
        });

        btnConsultar.setBackground(new java.awt.Color(204, 204, 204));
        btnConsultar.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        btnConsultar.setText("CONSULTAR");
        btnConsultar.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 14, 8, 14));
        btnConsultar.setContentAreaFilled(false);
        btnConsultar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnConsultar.setFocusPainted(false);
        btnConsultar.setOpaque(true);
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlConsultarLayout = new javax.swing.GroupLayout(pnlConsultar);
        pnlConsultar.setLayout(pnlConsultarLayout);
        pnlConsultarLayout.setHorizontalGroup(
            pnlConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlConsultarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTituloConsultar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlConsultarLayout.createSequentialGroup()
                        .addGroup(pnlConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbFechaInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dtFechaInicio, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbFechaFin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dtFechaFin, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbPrefijo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPrefijo, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbFolio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtFolio, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)))
                    .addComponent(lbCufe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCufe)
                    .addGroup(pnlConsultarLayout.createSequentialGroup()
                        .addGroup(pnlConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbNombreEmisor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbEmisor, 0, 245, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbNitEmisor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNitEmisor, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)))
                    .addGroup(pnlConsultarLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnConsultar)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlConsultarLayout.setVerticalGroup(
            pnlConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlConsultarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTituloConsultar)
                .addGap(10, 10, 10)
                .addGroup(pnlConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbFechaInicio)
                    .addComponent(lbFechaFin)
                    .addComponent(lbPrefijo)
                    .addComponent(lbFolio))
                .addGap(4, 4, 4)
                .addGroup(pnlConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dtFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dtFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrefijo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFolio, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addComponent(lbCufe)
                .addGap(4, 4, 4)
                .addComponent(txtCufe, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(pnlConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNombreEmisor)
                    .addComponent(lbNitEmisor))
                .addGap(4, 4, 4)
                .addGroup(pnlConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbEmisor, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNitEmisor, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(btnConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlHistorial.setBackground(new java.awt.Color(255, 255, 255));
        pnlHistorial.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 220, 220)));

        lbTituloHistorial.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        lbTituloHistorial.setForeground(new java.awt.Color(59, 48, 140));
        lbTituloHistorial.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTituloHistorial.setText("Historial de soportes mercantiles");

        tblHistorial.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        tblHistorial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Registro", "Documento", "NIT emisor", "Emisor", "Emisión", "Total", "Evento", "Estado", "Detalle", "CUFE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHistorial.setRowHeight(24);
        tblHistorial.getTableHeader().setReorderingAllowed(false);
        tblHistorial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHistorialMouseClicked(evt);
            }
        });
        scrHistorial.setViewportView(tblHistorial);

        lbTotalRegistros.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbTotalRegistros.setText(" ");

        javax.swing.GroupLayout pnlHistorialLayout = new javax.swing.GroupLayout(pnlHistorial);
        pnlHistorial.setLayout(pnlHistorialLayout);
        pnlHistorialLayout.setHorizontalGroup(
            pnlHistorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHistorialLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlHistorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTituloHistorial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scrHistorial, javax.swing.GroupLayout.DEFAULT_SIZE, 1008, Short.MAX_VALUE)
                    .addComponent(lbTotalRegistros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlHistorialLayout.setVerticalGroup(
            pnlHistorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHistorialLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTituloHistorial)
                .addGap(6, 6, 6)
                .addComponent(scrHistorial, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                .addGap(4, 4, 4)
                .addComponent(lbTotalRegistros)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlSoporteMercantilLayout = new javax.swing.GroupLayout(pnlSoporteMercantil);
        pnlSoporteMercantil.setLayout(pnlSoporteMercantilLayout);
        pnlSoporteMercantilLayout.setHorizontalGroup(
            pnlSoporteMercantilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSoporteMercantilLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnlSoporteMercantilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSoporteMercantilLayout.createSequentialGroup()
                        .addComponent(pnlProcesar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(12, 12, 12)
                        .addComponent(pnlConsultar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(pnlHistorial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );
        pnlSoporteMercantilLayout.setVerticalGroup(
            pnlSoporteMercantilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSoporteMercantilLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnlSoporteMercantilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlProcesar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlConsultar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addComponent(pnlHistorial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlSoporteMercantil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlSoporteMercantil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        cerrar();
    }//GEN-LAST:event_formWindowClosing

    private void btnSubirExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubirExcelActionPerformed
        procesarExcelDian();
    }//GEN-LAST:event_btnSubirExcelActionPerformed

    private void btnExaminarXmlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExaminarXmlActionPerformed
        seleccionarXml();
    }//GEN-LAST:event_btnExaminarXmlActionPerformed

    private void btnSubirXmlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubirXmlActionPerformed
        procesarXml();
    }//GEN-LAST:event_btnSubirXmlActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        solicitarCancelacion();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        consultar();
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void txtPrefijoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrefijoActionPerformed
        consultar();
    }//GEN-LAST:event_txtPrefijoActionPerformed

    private void txtFolioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFolioActionPerformed
        consultar();
    }//GEN-LAST:event_txtFolioActionPerformed

    private void txtCufeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCufeActionPerformed
        consultar();
    }//GEN-LAST:event_txtCufeActionPerformed

    private void txtNitEmisorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNitEmisorActionPerformed
        consultar();
    }//GEN-LAST:event_txtNitEmisorActionPerformed

    private void tblHistorialMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHistorialMouseClicked
        if (evt.getClickCount() == 2 && tblHistorial.getSelectedRow() >= 0) {
            mostrarDetalle(registros.get(tblHistorial.convertRowIndexToModel(tblHistorial.getSelectedRow())));
        }
    }//GEN-LAST:event_tblHistorialMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar barraProgreso;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnExaminarXml;
    private javax.swing.JButton btnSubirExcel;
    private javax.swing.JButton btnSubirXml;
    private javax.swing.JComboBox cmbEmisor;
    private datechooser.beans.DateChooserCombo dtFechaFin;
    private datechooser.beans.DateChooserCombo dtFechaInicio;
    private javax.swing.JLabel lbArchivoMasivo;
    private javax.swing.JLabel lbArchivoXml;
    private javax.swing.JLabel lbCufe;
    private javax.swing.JLabel lbEventos;
    private javax.swing.JLabel lbFechaFin;
    private javax.swing.JLabel lbFechaInicio;
    private javax.swing.JLabel lbFolio;
    private javax.swing.JLabel lbNitEmisor;
    private javax.swing.JLabel lbNombreEmisor;
    private javax.swing.JLabel lbNotaExcel;
    private javax.swing.JLabel lbPrefijo;
    private javax.swing.JLabel lbTituloConsultar;
    private javax.swing.JLabel lbTituloHistorial;
    private javax.swing.JLabel lbTituloProcesar;
    private javax.swing.JLabel lbTotalRegistros;
    private javax.swing.JPanel pnlConsultar;
    private javax.swing.JPanel pnlHistorial;
    private javax.swing.JPanel pnlProcesar;
    private javax.swing.JPanel pnlSoporteMercantil;
    private javax.swing.JScrollPane scrBitacora;
    private javax.swing.JScrollPane scrHistorial;
    private javax.swing.JTable tblHistorial;
    private javax.swing.JTextField txtArchivoXml;
    private javax.swing.JTextArea txtBitacora;
    private javax.swing.JTextField txtCufe;
    private javax.swing.JTextField txtFolio;
    private javax.swing.JTextField txtNitEmisor;
    private javax.swing.JTextField txtPrefijo;
    // End of variables declaration//GEN-END:variables
}
