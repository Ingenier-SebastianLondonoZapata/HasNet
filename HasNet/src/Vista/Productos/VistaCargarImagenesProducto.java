package Vista.Productos;

import Controlador.Alertas.ControladorAlertas;
import Modelo.Productos.ProductoImagen;
import Modelo.Productos.ResultadoCargaFoto;
import Procesos.Productos.Servicio.ServicioCargaFotosProductos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.KeyStroke;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public final class VistaCargarImagenesProducto extends javax.swing.JDialog {

    private final ServicioCargaFotosProductos servicio = new ServicioCargaFotosProductos();
    private int cargados;
    private int omitidos;
    private int errados;
    private boolean cargando;

    public VistaCargarImagenesProducto(java.awt.Frame parent, boolean modal, String fact, String panel) {
        super(parent, modal);
        initComponents();
        this.setTitle("Imágenes de los productos");
        this.setLocationRelativeTo(null);
        this.getRootPane().registerKeyboardAction(accion("cerrar", this), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
    }

    private ActionListener accion(final String opc, final JDialog ventana) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ("cerrar".equals(opc) && !cargando) {
                    ventana.dispose();
                }
            }
        };
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblImagenes = new javax.swing.JTable();
        btnArchivos = new javax.swing.JButton();
        btnCarpeta = new javax.swing.JButton();
        chkSubcarpetas = new javax.swing.JCheckBox();
        chkReemplazar = new javax.swing.JCheckBox();
        barraProgreso = new javax.swing.JProgressBar();
        jButton2 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtCargados = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtOmitidos = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtErrados = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("FOTOS DE LOS PRODUCTOS");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("NOTA: EL NOMBRE DE LA IMAGEN DEBE SER EL CÓDIGO, EL CÓDIGO DE BARRAS O EL ID DEL PRODUCTO");

        tblImagenes.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tblImagenes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ARCHIVO", "CÓDIGO", "DESCRIPCIÓN DEL PRODUCTO", "ESTADO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblImagenes.setRowHeight(20);
        tblImagenes.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblImagenes);

        btnArchivos.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        btnArchivos.setText("SELECCIONAR FOTOS");
        btnArchivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArchivosActionPerformed(evt);
            }
        });

        btnCarpeta.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        btnCarpeta.setText("SELECCIONAR CARPETA");
        btnCarpeta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCarpetaActionPerformed(evt);
            }
        });

        chkSubcarpetas.setBackground(new java.awt.Color(255, 255, 255));
        chkSubcarpetas.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        chkSubcarpetas.setText("Incluir subcarpetas");

        chkReemplazar.setBackground(new java.awt.Color(255, 255, 255));
        chkReemplazar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        chkReemplazar.setSelected(true);
        chkReemplazar.setText("Reemplazar fotos existentes");

        barraProgreso.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        barraProgreso.setStringPainted(true);

        jButton2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButton2.setText("SALIR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("CARGADOS:");

        txtCargados.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtCargados.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txtCargados.setText("0");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("OMITIDOS:");

        txtOmitidos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtOmitidos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txtOmitidos.setText("0");

        jLabel11.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("ERRADOS:");

        txtErrados.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtErrados.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txtErrados.setText("0");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("TOTAL:");

        txtTotal.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtTotal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txtTotal.setText("0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 820, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(barraProgreso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(3, 3, 3)
                        .addComponent(txtCargados, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addGap(3, 3, 3)
                        .addComponent(txtOmitidos, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11)
                        .addGap(3, 3, 3)
                        .addComponent(txtErrados, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addGap(3, 3, 3)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnArchivos, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCarpeta, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(chkSubcarpetas)
                                .addGap(18, 18, 18)
                                .addComponent(chkReemplazar)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnArchivos, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCarpeta, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkSubcarpetas)
                    .addComponent(chkReemplazar))
                .addGap(25, 25, 25)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(barraProgreso, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(txtCargados)
                        .addComponent(jLabel8)
                        .addComponent(txtOmitidos)
                        .addComponent(jLabel11)
                        .addComponent(txtErrados)
                        .addComponent(jLabel7)
                        .addComponent(txtTotal))
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnArchivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArchivosActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Seleccione las fotos de los productos");
        chooser.setMultiSelectionEnabled(true);
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setFileFilter(new FileNameExtensionFilter("Imágenes (JPG, PNG, GIF, BMP)", ServicioCargaFotosProductos.extensionesPermitidas()));

        if (chooser.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
            return;
        }

        final List<File> archivos = Arrays.asList(chooser.getSelectedFiles());
        iniciarCarga(new Callable<List<File>>() {
            @Override
            public List<File> call() {
                return archivos;
            }
        });
    }//GEN-LAST:event_btnArchivosActionPerformed

    private void btnCarpetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCarpetaActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Seleccione la carpeta con las fotos de los productos");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
            return;
        }

        final File carpeta = chooser.getSelectedFile();
        final boolean incluirSubcarpetas = chkSubcarpetas.isSelected();
        iniciarCarga(new Callable<List<File>>() {
            @Override
            public List<File> call() throws Exception {
                return servicio.listarImagenes(carpeta, incluirSubcarpetas);
            }
        });
    }//GEN-LAST:event_btnCarpetaActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void iniciarCarga(final Callable<List<File>> origenArchivos) {
        final boolean reemplazar = chkReemplazar.isSelected();
        limpiarResultados();
        habilitarControles(false);

        SwingWorker<Integer, ResultadoCargaFoto> tarea = new SwingWorker<Integer, ResultadoCargaFoto>() {
            @Override
            protected Integer doInBackground() throws Exception {
                List<File> archivos = origenArchivos.call();
                if (archivos.isEmpty()) {
                    return 0;
                }

                Map<String, ProductoImagen> indice = servicio.obtenerIndiceProductos();
                Set<String> productosCargados = new HashSet<>();

                for (int i = 0; i < archivos.size(); i++) {
                    publish(servicio.cargar(archivos.get(i), indice, productosCargados, reemplazar));
                    setProgress((i + 1) * 100 / archivos.size());
                }
                return archivos.size();
            }

            @Override
            protected void process(List<ResultadoCargaFoto> resultados) {
                for (ResultadoCargaFoto resultado : resultados) {
                    agregarResultado(resultado);
                }
            }

            @Override
            protected void done() {
                habilitarControles(true);
                try {
                    if (get() == 0) {
                        ControladorAlertas.alert("No se encontraron imágenes para cargar");
                    } else {
                        ControladorAlertas.alertSuccess("Carga finalizada: " + cargados + " cargadas, " + omitidos + " omitidas, " + errados + " con error");
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } catch (ExecutionException e) {
                    ControladorAlertas.alertFail("No se pudieron cargar las fotos: " + e.getCause().getMessage());
                }
            }
        };

        tarea.addPropertyChangeListener(evento -> {
            if ("progress".equals(evento.getPropertyName())) {
                barraProgreso.setValue((Integer) evento.getNewValue());
            }
        });
        tarea.execute();
    }

    private void agregarResultado(ResultadoCargaFoto resultado) {
        DefaultTableModel modelo = (DefaultTableModel) tblImagenes.getModel();
        modelo.addRow(new Object[]{resultado.getArchivo(), resultado.getCodigo(), resultado.getDescripcion(), resultado.getEstado().getDescripcion()});

        switch (resultado.getEstado().getCategoria()) {
            case CARGADO:
                cargados++;
                break;
            case OMITIDO:
                omitidos++;
                break;
            default:
                errados++;
                break;
        }

        actualizarContadores(modelo.getRowCount());
    }

    private void limpiarResultados() {
        ((DefaultTableModel) tblImagenes.getModel()).setRowCount(0);
        cargados = 0;
        omitidos = 0;
        errados = 0;
        barraProgreso.setValue(0);
        actualizarContadores(0);
    }

    private void actualizarContadores(int total) {
        txtCargados.setText(String.valueOf(cargados));
        txtOmitidos.setText(String.valueOf(omitidos));
        txtErrados.setText(String.valueOf(errados));
        txtTotal.setText(String.valueOf(total));
    }

    private void habilitarControles(boolean habilitar) {
        cargando = !habilitar;
        btnArchivos.setEnabled(habilitar);
        btnCarpeta.setEnabled(habilitar);
        chkSubcarpetas.setEnabled(habilitar);
        chkReemplazar.setEnabled(habilitar);
        jButton2.setEnabled(habilitar);
        setDefaultCloseOperation(habilitar ? javax.swing.WindowConstants.DISPOSE_ON_CLOSE : javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaCargarImagenesProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                VistaCargarImagenesProducto dialog = new VistaCargarImagenesProducto(new javax.swing.JFrame(), true, "", "");
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar barraProgreso;
    private javax.swing.JButton btnArchivos;
    private javax.swing.JButton btnCarpeta;
    private javax.swing.JCheckBox chkReemplazar;
    private javax.swing.JCheckBox chkSubcarpetas;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblImagenes;
    private javax.swing.JLabel txtCargados;
    private javax.swing.JLabel txtErrados;
    private javax.swing.JLabel txtOmitidos;
    private javax.swing.JLabel txtTotal;
    // End of variables declaration//GEN-END:variables
}
