package formularios.Medico;

import clases.Instancias;
import Utilidades.BaseDatos.SQL;
import clases.metodosGenerales;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

public class infFormatosDeTextos extends javax.swing.JInternalFrame {

    metodosGenerales metodos = new metodosGenerales();
    private Instancias instancias;
    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    boolean guardar;

    public infFormatosDeTextos() {
        initComponents();

        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        setBorder(null);
        repaint();

        instancias = Instancias.getInstancias();
        cargarTabla();
        pnlFormulario.registerKeyboardAction(accion("guardar"), "guardar", KeyStroke.getKeyStroke(KeyEvent.VK_G, Event.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
        pnlFormulario.registerKeyboardAction(accion("limpiar"), "limpiar", KeyStroke.getKeyStroke(KeyEvent.VK_L, Event.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
    }

    private ActionListener accion(final String opc) {
        ActionListener a = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (opc) {
                    case "limpiar":
                        if ((btnNuevo1.isEnabled()) && (btnNuevo1.isVisible())) {
                            btnNuevo1ActionPerformed(null);
                        }
                        break;
                    case "guardar":
                        if ((btnGuardar1.isEnabled()) && (btnGuardar1.isVisible())) {
                            btnGuardar1ActionPerformed(null);
                        }
                        break;
                }
            }
        };
        return a;
    }

    public void cargarTabla() {
        tblIncluye.setModel(instancias.getSql().getPlantillaDeTexto());
        if (tblIncluye.getColumnModel().getColumnCount() > 0) {
            tblIncluye.getColumnModel().getColumn(0).setMinWidth(180);
            tblIncluye.getColumnModel().getColumn(0).setPreferredWidth(180);
            tblIncluye.getColumnModel().getColumn(0).setMaxWidth(180);
            tblIncluye.getColumnModel().getColumn(2).setMinWidth(50);
            tblIncluye.getColumnModel().getColumn(2).setPreferredWidth(50);
            tblIncluye.getColumnModel().getColumn(2).setMaxWidth(50);
            tblIncluye.getColumnModel().getColumn(3).setMinWidth(0);
            tblIncluye.getColumnModel().getColumn(3).setPreferredWidth(0);
            tblIncluye.getColumnModel().getColumn(3).setMaxWidth(0);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrFormulario = new javax.swing.JScrollPane();
        pnlFormulario = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblIncluye = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btnGuardar1 = new javax.swing.JButton();
        btnNuevo1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        lbNit2 = new javax.swing.JLabel();
        checkActivo = new javax.swing.JCheckBox();
        txtNombre = new javax.swing.JTextField();
        lbNit3 = new javax.swing.JLabel();
        txtIdIncluye = new javax.swing.JTextField();

        setTitle("Factura");

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        tblIncluye.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        tblIncluye.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Descripción", "Activo", "Id"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblIncluye.setRowHeight(24);
        tblIncluye.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblIncluyeMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblIncluye);
        if (tblIncluye.getColumnModel().getColumnCount() > 0) {
            tblIncluye.getColumnModel().getColumn(0).setMinWidth(180);
            tblIncluye.getColumnModel().getColumn(0).setPreferredWidth(180);
            tblIncluye.getColumnModel().getColumn(0).setMaxWidth(180);
            tblIncluye.getColumnModel().getColumn(2).setMinWidth(50);
            tblIncluye.getColumnModel().getColumn(2).setPreferredWidth(50);
            tblIncluye.getColumnModel().getColumn(2).setMaxWidth(50);
            tblIncluye.getColumnModel().getColumn(3).setMinWidth(0);
            tblIncluye.getColumnModel().getColumn(3).setPreferredWidth(0);
            tblIncluye.getColumnModel().getColumn(3).setMaxWidth(0);
        }

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnGuardar1.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardar1.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnGuardar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardar1.setText("GUARDAR");
        btnGuardar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardar1.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnGuardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar1ActionPerformed(evt);
            }
        });

        btnNuevo1.setBackground(new java.awt.Color(204, 204, 204));
        btnNuevo1.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnNuevo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/nuevo.png"))); // NOI18N
        btnNuevo1.setText("NUEVO");
        btnNuevo1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevo1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnNuevo1.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnNuevo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevo1ActionPerformed(evt);
            }
        });

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(3);
        jScrollPane1.setViewportView(txtDescripcion);

        lbNit2.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit2.setText("Descripción");

        checkActivo.setBackground(new java.awt.Color(255, 255, 255));
        checkActivo.setText("Activo");

        txtNombre.setName("Nombre Grupo"); // NOI18N

        lbNit3.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit3.setText("Nombre:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbNit2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbNit3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 601, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(checkActivo, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnNuevo1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardar1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(checkActivo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbNit3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addComponent(lbNit2)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevo1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addGap(10, 10, 10))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap(12, Short.MAX_VALUE))
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addComponent(txtIdIncluye, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtIdIncluye, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        scrFormulario.setViewportView(pnlFormulario);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrFormulario)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrFormulario)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar1ActionPerformed
        if (!guardar) {
            if (txtDescripcion.getText().equals("")) {
                metodos.msgError(this, "Ingrese una descripción");
                txtDescripcion.requestFocusInWindow();
                return;
            }

            String campo = "";
            for (int i = 0; i < tblIncluye.getRowCount(); i++) {
                campo = (String) tblIncluye.getValueAt(i, 0);
                if (txtNombre.getText().equalsIgnoreCase(campo)) {
                    metodos.msgError(this, "Ya existe una plantilla con este nombre");
                    return;
                }
            }

            if (!instancias.getSql().agregarPlantillaDeTexto(new Object[]{txtNombre.getText(), txtDescripcion.getText(), true})) {
                metodos.msgError(this, "Hubo un problema al guardar el incluye del producto");
                return;
            }

            metodos.msgExito(this, "Plantilla registrada con exito");

            cargarTabla();
        } else {

            if (!instancias.getSql().modificarPlantillaDeTexto(txtIdIncluye.getText(), txtNombre.getText(), txtDescripcion.getText(), checkActivo.isSelected())) {
                metodos.msgError(this, "Hubo un problema al modificar el incluye del producto");
                return;
            }
            metodos.msgExito(this, "Plantilla modificada correctamente");
            txtDescripcion.setText("");
            txtNombre.setText("");
            checkActivo.setSelected(false);
            btnNuevo1.setEnabled(true);
            btnGuardar1.setText("GUARDAR");
            cargarTabla();
            guardar = false;

        }
    }//GEN-LAST:event_btnGuardar1ActionPerformed

    private void btnNuevo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevo1ActionPerformed
        txtIdIncluye.setText("");
        txtDescripcion.setText("");
        txtNombre.setText("");
        checkActivo.setSelected(false);
        btnGuardar1.setText("GUARDAR");
        guardar = false;
        cargarTabla();
    }//GEN-LAST:event_btnNuevo1ActionPerformed

    private void tblIncluyeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblIncluyeMouseClicked
        if (evt.getClickCount() == 2) {
            guardar = true;
            btnGuardar1.setText("MODIFICAR");
            txtNombre.setText(tblIncluye.getValueAt(tblIncluye.getSelectedRow(), 0).toString());
            txtDescripcion.setText(tblIncluye.getValueAt(tblIncluye.getSelectedRow(), 1).toString());
            txtIdIncluye.setText(tblIncluye.getValueAt(tblIncluye.getSelectedRow(), 3).toString());
            checkActivo.setSelected((Boolean) tblIncluye.getValueAt(tblIncluye.getSelectedRow(), 2));
        }
    }//GEN-LAST:event_tblIncluyeMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar1;
    private javax.swing.JButton btnNuevo1;
    private javax.swing.JCheckBox checkActivo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbNit2;
    private javax.swing.JLabel lbNit3;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JTable tblIncluye;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtIdIncluye;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
