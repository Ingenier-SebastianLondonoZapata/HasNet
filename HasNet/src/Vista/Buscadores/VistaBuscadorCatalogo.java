package Vista.Buscadores;

import Controlador.Alertas.ControladorAlertas;
import clases.Instancias;
import clases.metodosGenerales;
import java.awt.event.KeyEvent;
import java.util.Map;
import java.util.regex.PatternSyntaxException;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JRadioButton;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class VistaBuscadorCatalogo extends javax.swing.JDialog {

    private final ConfiguracionBuscadorCatalogo configuracion;
    private final Instancias instancias = Instancias.getInstancias();
    private final ButtonGroup grupoFiltros = new ButtonGroup();

    private TableRowSorter<TableModel> modeloOrdenado;
    private int columnaFiltro;

    public VistaBuscadorCatalogo(java.awt.Frame parent, boolean modal, ConfiguracionBuscadorCatalogo configuracion) {
        super(parent, modal);
        this.configuracion = configuracion;
        initComponents();

        setTitle(configuracion.getTitulo());
        columnaFiltro = configuracion.getColumnaFiltro();
        btnRegistrar.setVisible(configuracion.permiteRegistrar());
        crearFiltros();
        cargarRegistros();
        pack();

        this.getRootPane().registerKeyboardAction(e -> dispose(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    public void noEncontrado(String codigo) {
        txtId.setText(codigo);
        Integer columna = configuracion.getColumnaNoEncontrado();
        filtrar(codigo, columna == null ? columnaFiltro : columna);
    }

    private void crearFiltros() {
        pnlFiltros.setVisible(!configuracion.getFiltros().isEmpty());

        for (ConfiguracionBuscadorCatalogo.FiltroColumna filtro : configuracion.getFiltros()) {
            JRadioButton opcion = new JRadioButton(filtro.getEtiqueta());
            opcion.setFont(new java.awt.Font("Arial", 0, 14));
            opcion.setBackground(pnlFiltros.getBackground());
            opcion.setSelected(filtro.getColumna() == columnaFiltro);
            opcion.addActionListener(e -> columnaFiltro = filtro.getColumna());
            grupoFiltros.add(opcion);
            pnlFiltros.add(opcion);
        }
    }

    private void cargarRegistros() {
        DefaultTableModel modelo = configuracion.cargarRegistros();
        tblRegistros.setModel(modelo);
        aplicarAnchos();

        modeloOrdenado = new TableRowSorter<>(modelo);
        tblRegistros.setRowSorter(modeloOrdenado);

        txtId.requestFocus();
        seleccionarPrimeraFila();
    }

    private void aplicarAnchos() {
        for (Map.Entry<Integer, Integer> ancho : configuracion.getAnchosFijos().entrySet()) {
            if (ancho.getKey() >= tblRegistros.getColumnModel().getColumnCount()) {
                continue;
            }
            TableColumn columna = tblRegistros.getColumnModel().getColumn(ancho.getKey());
            columna.setMinWidth(ancho.getValue());
            columna.setPreferredWidth(ancho.getValue());
            columna.setMaxWidth(ancho.getValue());
        }
    }

    private void filtrar(String texto) {
        filtrar(texto, columnaFiltro);
    }

    private void filtrar(String texto, int columna) {
        try {
            modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + texto, columna));
        } catch (PatternSyntaxException e) {
            return;
        }
        seleccionarPrimeraFila();
    }

    private void seleccionarPrimeraFila() {
        if (tblRegistros.getRowCount() > 0) {
            tblRegistros.getSelectionModel().setSelectionInterval(0, 0);
        }
    }

    private void moverSeleccion(int aumento) {
        int fila = tblRegistros.getSelectionModel().getMinSelectionIndex() + aumento;
        if (fila == tblRegistros.getRowCount() && aumento == 1) {
            fila = 0;
        }
        if (fila < 0 || fila >= tblRegistros.getRowCount()) {
            return;
        }
        tblRegistros.getSelectionModel().setSelectionInterval(fila, fila);
        tblRegistros.scrollRectToVisible(tblRegistros.getCellRect(tblRegistros.getSelectedRow(), 0, true));
    }

    private void seleccionar() {
        int fila = tblRegistros.getSelectedRow();
        if (fila < 0) {
            return;
        }

        Object valor = tblRegistros.getValueAt(fila, configuracion.getColumnaValor());
        instancias.setValorCampo(valor == null ? null : valor.toString());
        dispose();

        if (configuracion.isPresionarEnterAlSeleccionar()) {
            metodosGenerales.presionarEnter(instancias.getCampoActual());
        }
    }

    private void registrar() {
        RegistroCatalogo registro = configuracion.getRegistro();
        String texto = txtId.getText();

        if (texto.equals("")) {
            ControladorAlertas.alertFail(registro.getMensajeVacio());
            return;
        }

        String valor = registro.normalizar(texto);
        if (registro.existe(valor)) {
            if (registro.getMensajeExiste() != null) {
                ControladorAlertas.alertFail(registro.getMensajeExiste());
            }
            return;
        }

        if (!ControladorAlertas.option(registro.pregunta(valor))) {
            return;
        }

        if (!registro.guardar(valor)) {
            ControladorAlertas.alertFail(registro.getMensajeError());
            return;
        }

        ControladorAlertas.alertSuccess(registro.getMensajeExito());
        cargarRegistros();
        noEncontrado(texto);
        seleccionar();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pnlFiltros = new javax.swing.JPanel();
        txtId = new javax.swing.JTextField();
        btnRegistrar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRegistros = new javax.swing.JTable();
        btnSeleccionar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        pnlFiltros.setBackground(new java.awt.Color(255, 255, 255));
        pnlFiltros.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 0));

        txtId.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdKeyReleased(evt);
            }
        });

        btnRegistrar.setBackground(new java.awt.Color(46, 204, 113));
        btnRegistrar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnRegistrar.setText("REGISTRAR");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        tblRegistros.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        tblRegistros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblRegistros.setRowHeight(24);
        tblRegistros.getTableHeader().setReorderingAllowed(false);
        tblRegistros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblRegistrosMouseClicked(evt);
            }
        });
        tblRegistros.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblRegistrosKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblRegistros);

        btnSeleccionar.setBackground(new java.awt.Color(204, 204, 204));
        btnSeleccionar.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        btnSeleccionar.setText("SELECCIONAR");
        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtId)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlFiltros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSeleccionar)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(pnlFiltros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtId)
                    .addComponent(btnRegistrar, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSeleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_DOWN || evt.getKeyCode() == KeyEvent.VK_UP) {
            moverSeleccion(evt.getKeyCode() == KeyEvent.VK_DOWN ? 1 : -1);
            return;
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            seleccionar();
            return;
        }

        filtrar(txtId.getText());
    }//GEN-LAST:event_txtIdKeyReleased

    private void tblRegistrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRegistrosMouseClicked
        if (evt.getClickCount() > 1) {
            seleccionar();
        }
    }//GEN-LAST:event_tblRegistrosMouseClicked

    private void tblRegistrosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblRegistrosKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            int anterior = tblRegistros.getSelectionModel().getMinSelectionIndex() - 1;
            if (tblRegistros.getRowCount() > 1 && anterior >= 0) {
                tblRegistros.getSelectionModel().setSelectionInterval(anterior, anterior);
            }
            seleccionar();
        } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            tblRegistros.scrollRectToVisible(tblRegistros.getCellRect(tblRegistros.getSelectedRow(), 0, true));
        }
    }//GEN-LAST:event_tblRegistrosKeyReleased

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        registrar();
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
        seleccionar();
    }//GEN-LAST:event_btnSeleccionarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlFiltros;
    private javax.swing.JTable tblRegistros;
    private javax.swing.JTextField txtId;
    // End of variables declaration//GEN-END:variables
}
