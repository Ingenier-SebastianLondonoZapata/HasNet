package formularios.Oftalmologia;

import clases.Instancias;
import clases.Oftalmologia.ndFormulaLentes;
import Utilidades.BaseDatos.SQL;
import clases.metodosGenerales;
import Modelo.Terceros.ModeloContacto;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.table.DefaultTableModel;

public class infFormulaLentesOf extends javax.swing.JInternalFrame {

    metodosGenerales metodos = new metodosGenerales();
    private Instancias instancias;
    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;
    DefaultTableModel modelo;

    public infFormulaLentesOf() {

        initComponents();

        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        setBorder(null);
        repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrFormulario = new javax.swing.JScrollPane();
        pnlFormulario = new javax.swing.JPanel();
        lbNit10 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        lbRazon = new javax.swing.JLabel();
        txtIdentificacion = new javax.swing.JTextField();
        txtFecha = new javax.swing.JTextField();
        lbRazon1 = new javax.swing.JLabel();
        lbRazon3 = new javax.swing.JLabel();
        txtNombres = new javax.swing.JTextField();
        lbFacturaNo = new javax.swing.JLabel();
        lbNoFactura = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lbRazon4 = new javax.swing.JLabel();
        lbRazon5 = new javax.swing.JLabel();
        lbRazon6 = new javax.swing.JLabel();
        lbRazon7 = new javax.swing.JLabel();
        jScrollPane29 = new javax.swing.JScrollPane();
        txtBifocal = new javax.swing.JTextArea();
        jScrollPane30 = new javax.swing.JScrollPane();
        txtOD = new javax.swing.JTextArea();
        jScrollPane31 = new javax.swing.JScrollPane();
        txtADD = new javax.swing.JTextArea();
        jScrollPane32 = new javax.swing.JScrollPane();
        txtOI = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        btnBuscTerceros2 = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        setTitle("Ayuda Diagnostica");

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));

        lbNit10.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        lbNit10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit10.setText("FORMULA DE LENTES");

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lbRazon.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbRazon.setText("Fecha:");

        txtIdentificacion.setEditable(false);
        txtIdentificacion.setName("No. Identificacion"); // NOI18N
        txtIdentificacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdentificacionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdentificacionKeyTyped(evt);
            }
        });

        txtFecha.setEditable(false);
        txtFecha.setName("Fecha De Examen"); // NOI18N
        txtFecha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFechaKeyReleased(evt);
            }
        });

        lbRazon1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbRazon1.setText("No. Identificación:");

        lbRazon3.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbRazon3.setText("Nombre completo:");

        txtNombres.setEditable(false);
        txtNombres.setName("Nombre Y Apellidos"); // NOI18N
        txtNombres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombresActionPerformed(evt);
            }
        });
        txtNombres.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombresKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombresKeyTyped(evt);
            }
        });

        lbFacturaNo.setBackground(new java.awt.Color(102, 153, 0));
        lbFacturaNo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbFacturaNo.setForeground(new java.awt.Color(255, 255, 255));
        lbFacturaNo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbFacturaNo.setText("FORMULA N°");
        lbFacturaNo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lbFacturaNo.setOpaque(true);

        lbNoFactura.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbNoFactura.setForeground(new java.awt.Color(255, 0, 0));
        lbNoFactura.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNoFactura.setText("1");
        lbNoFactura.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbRazon3)
                    .addComponent(lbRazon1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(txtIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbRazon)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbFacturaNo, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNoFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(lbFacturaNo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(lbNoFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIdentificacion)
                            .addComponent(lbRazon)
                            .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbRazon1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbRazon3)
                            .addComponent(txtNombres))
                        .addGap(210, 210, 210))))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lbRazon4.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbRazon4.setText("OD:");

        lbRazon5.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbRazon5.setText("OI:");

        lbRazon6.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbRazon6.setText("ADD:");

        lbRazon7.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbRazon7.setText("BIFOCAL:");

        txtBifocal.setColumns(20);
        txtBifocal.setLineWrap(true);
        txtBifocal.setRows(2);
        txtBifocal.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtBifocal.setName("BIFOCAL"); // NOI18N
        txtBifocal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBifocalKeyReleased(evt);
            }
        });
        jScrollPane29.setViewportView(txtBifocal);

        txtOD.setColumns(20);
        txtOD.setLineWrap(true);
        txtOD.setRows(2);
        txtOD.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtOD.setName("OD"); // NOI18N
        txtOD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtODKeyReleased(evt);
            }
        });
        jScrollPane30.setViewportView(txtOD);

        txtADD.setColumns(20);
        txtADD.setLineWrap(true);
        txtADD.setRows(2);
        txtADD.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtADD.setName("ADD"); // NOI18N
        txtADD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtADDKeyReleased(evt);
            }
        });
        jScrollPane31.setViewportView(txtADD);

        txtOI.setColumns(20);
        txtOI.setLineWrap(true);
        txtOI.setRows(2);
        txtOI.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtOI.setName("OI"); // NOI18N
        txtOI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtOIKeyReleased(evt);
            }
        });
        jScrollPane32.setViewportView(txtOI);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbRazon7)
                    .addComponent(lbRazon6)
                    .addComponent(lbRazon5)
                    .addComponent(lbRazon4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane29)
                    .addComponent(jScrollPane30, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane31, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane32, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane30, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(lbRazon4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane32, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(lbRazon5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane31, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(lbRazon6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane29, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(lbRazon7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        btnBuscTerceros2.setBackground(new java.awt.Color(204, 204, 204));
        btnBuscTerceros2.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscTerceros2.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscTerceros2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/imprimir.png"))); // NOI18N
        btnBuscTerceros2.setText("REIMPRIMIR");
        btnBuscTerceros2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscTerceros2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBuscTerceros2.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnBuscTerceros2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscTerceros2ActionPerformed(evt);
            }
        });

        btnGuardar.setBackground(new java.awt.Color(0, 153, 153));
        btnGuardar.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardar.setText("GUARDAR");
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        btnGuardar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                btnGuardarKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscTerceros2, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscTerceros2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGuardar))
                .addContainerGap())
        );

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/anterior_1.png"))); // NOI18N
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Volver");
        jLabel11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addComponent(lbNit10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel11))
                    .addComponent(lbNit10, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
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

    private void btnGuardarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnGuardarKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnGuardarActionPerformed(null);
        }
    }//GEN-LAST:event_btnGuardarKeyReleased

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        Object[] campos = {txtFecha, txtIdentificacion, txtNombres};
        String faltantes = metodos.camposVacios(campos);
        Object[] campos2 = {txtOD, txtOI, txtBifocal, txtADD};
        String faltantes2 = metodos.camposVacios(campos2);

        if (!faltantes.equals("")) {
            metodos.msgAdvertencia(this, "No puede continuar, faltan los siguientes campos: " + faltantes);
            return;
        } else if (!faltantes2.equals("")) {
            metodos.msgAdvertencia(this, "Faltan los siguientes campos: " + faltantes2);
        }

        if (metodos.msgPregunta(this, "¿Desea continuar?") == 0) {

            String id = "FORMLENTES-" + instancias.getSql().getNumConsecutivo("FORMLENTES")[0];

            ndFormulaLentes nodo = instancias.getSql().getDatosFormulaLentes(id);

            if (nodo.getId() != null) {
                metodos.msgError(this, "La formula de lentes ya existe");
                lbFacturaNo.requestFocus();
                return;
            }

            Object[] vector = {id, metodosGenerales.fecha(), txtIdentificacion.getText(), txtOD.getText(), txtOI.getText(),
                txtADD.getText(), txtBifocal.getText()};

            nodo = metodos.llenarFormulaLentes(vector);

            if (!instancias.getSql().agregarFormulaLentes(nodo)) {
                metodos.msgError(this, "Hubo un problema al guardar la formula de lentes");
                return;
            }

            if (!instancias.getSql().aumentarConsecutivo("FORMLENTES", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("FORMLENTES")[0]) + 1)) {
                metodos.msgError(this, "Hubo un problema al guardar en el consecutivo de la formula de lentes");
            }

            metodos.msgExito(this, "Formula de lentes registrada con éxito");

            if (metodos.msgPregunta(this, "¿Desea imprimir?") == 0) {
                instancias.getReporte().verFormulaLentes("FORMLENTES-" + lbNoFactura.getText(), instancias.getInformacionEmpresa());
            }

            lbNoFactura.setText("" + instancias.getSql().getNumConsecutivo("FORMLENTES")[0]);

            limpiar();
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtFechaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFechaKeyReleased

    }//GEN-LAST:event_txtFechaKeyReleased

    private void txtIdentificacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdentificacionKeyTyped

    }//GEN-LAST:event_txtIdentificacionKeyTyped

    private void txtIdentificacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdentificacionKeyReleased

    }//GEN-LAST:event_txtIdentificacionKeyReleased

    private void txtNombresKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombresKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombresKeyTyped

    private void txtNombresKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombresKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombresKeyReleased

    private void txtNombresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombresActionPerformed

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        try {
            instancias.getHistoriaClinica().setSelected(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(infHojaIngreso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel11MouseClicked

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
//        try {
//            instancias.getHistoriaClinica().setSelected(true);
//        } catch (PropertyVetoException ex) {
//            Logger.getLogger(infHistoriaMedica.class.getName()).log(Level.SEVERE, null, ex);
//        }

        try {
            instancias.getHojaIngreso().setSelected(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(infHojaIngreso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel9MouseClicked

    private void btnBuscTerceros2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscTerceros2ActionPerformed
        infReimpresionExamenes reimpresion = new infReimpresionExamenes();
        reimpresion.show();
        reimpresion.cargarRegistros(txtIdentificacion.getText(), "formLentes", instancias, "");
    }//GEN-LAST:event_btnBuscTerceros2ActionPerformed

    private void txtBifocalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBifocalKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBifocalKeyReleased

    private void txtODKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtODKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtODKeyReleased

    private void txtADDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtADDKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtADDKeyReleased

    private void txtOIKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOIKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOIKeyReleased

    public void limpiar() {
        txtADD.setText("");
        txtOD.setText("");
        txtOI.setText("");
        txtBifocal.setText("");
    }

    public void reimprimir(String consecutivo) {
        instancias.getReporte().verFormulaLentes(consecutivo, instancias.getInformacionEmpresa());
    }

    public void setInstancias(Instancias instancias) {
        this.instancias = instancias;

        txtFecha.setText(metodosGenerales.fecha());
        lbNoFactura.setText("" + instancias.getSql().getNumConsecutivo("FORMLENTES")[0]);
    }

    public void nuevaFormula(String id, String od, String oi) {

        instancias = Instancias.getInstancias();

        txtOD.setText(od);
        txtOI.setText(oi);

        lbNoFactura.setText((String) instancias.getSql().getNumConsecutivo("FORMLENTES")[0]);

        try {
            this.setSelected(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(infHojaIngreso.class.getName()).log(Level.SEVERE, null, ex);
        }

        ModeloContacto Paciente = instancias.getSql().getDatosTercero(id);

        txtIdentificacion.setText(Paciente.getId());
        txtNombres.setText(Paciente.getpNombre() + " " + Paciente.getsNombre() + " " + Paciente.getpApellido() + " " + Paciente.getsApellido());
        txtFecha.setText(metodosGenerales.fecha());

        lbNoFactura.setText("" + instancias.getSql().getNumConsecutivo("FORMLENTES")[0]);

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscTerceros2;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane29;
    private javax.swing.JScrollPane jScrollPane30;
    private javax.swing.JScrollPane jScrollPane31;
    private javax.swing.JScrollPane jScrollPane32;
    private javax.swing.JLabel lbFacturaNo;
    private javax.swing.JLabel lbNit10;
    private javax.swing.JLabel lbNoFactura;
    private javax.swing.JLabel lbRazon;
    private javax.swing.JLabel lbRazon1;
    private javax.swing.JLabel lbRazon3;
    private javax.swing.JLabel lbRazon4;
    private javax.swing.JLabel lbRazon5;
    private javax.swing.JLabel lbRazon6;
    private javax.swing.JLabel lbRazon7;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JTextArea txtADD;
    private javax.swing.JTextArea txtBifocal;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtIdentificacion;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextArea txtOD;
    private javax.swing.JTextArea txtOI;
    // End of variables declaration//GEN-END:variables

}
