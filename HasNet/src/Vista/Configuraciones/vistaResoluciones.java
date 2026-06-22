package Vista.Configuraciones;

import Controlador.Alertas.ControladorAlertas;
import dao.Configuraciones.DaoResoluciones;
import Modelo.Maestra.ModeloPrefijos;
import Modelo.Maestra.ModeloResolucion;
import Utilidades.CalendarioEnTabla;
import Utilidades.Fechas;
import Validaciones.Configuraciones.squemaResoluciones;
import clases.Instancias;
import clases.metodosGenerales;
import clases.ndMaestra;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import java.util.Date;
import javax.swing.DefaultCellEditor;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

public class vistaResoluciones extends javax.swing.JDialog {

    private final DaoResoluciones daoResoluciones = new DaoResoluciones();
    private final squemaResoluciones squemaResoluciones = new squemaResoluciones();

    private metodosGenerales metodos;
    private Instancias instancias;
    private ndMaestra nodo;

    public vistaResoluciones(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        metodos = new metodosGenerales();
        instancias = Instancias.getInstancias();

        Object[] datos = instancias.getSql().getDatosMaestra();
        nodo = metodos.llenarMaestra(datos);

        cargarPrefijos();
        cargarTablaResoluciones();

        this.getRootPane().registerKeyboardAction(accion("cerrar", this), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private ActionListener accion(final String opc, final JDialog ventana) {
        ActionListener a = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (opc) {
                    case "cerrar":
                        ventana.dispose();
                        break;
                }
            }
        };
        return a;

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        lbNit39 = new javax.swing.JLabel();
        txtPrefijoAbono = new javax.swing.JTextField();
        lbNit40 = new javax.swing.JLabel();
        txtPrefijoEgreso = new javax.swing.JTextField();
        lbNit41 = new javax.swing.JLabel();
        txtPrefijoND = new javax.swing.JTextField();
        lbNit42 = new javax.swing.JLabel();
        txtPrefijoNC = new javax.swing.JTextField();
        btnGuardarPrefijos = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblComprobantes = new javax.swing.JTable();
        cmbTipoComprobante = new javax.swing.JComboBox();
        lbInformacion = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnGuardarResoluciones = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Resoluciones");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Prefijos", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N
        jPanel7.setToolTipText("");

        lbNit39.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbNit39.setText("Prefijo Abonos:");

        txtPrefijoAbono.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtPrefijoAbono.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        lbNit40.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbNit40.setText("Prefijo Egresos:");

        txtPrefijoEgreso.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtPrefijoEgreso.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        lbNit41.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbNit41.setText("Prefijo Notas Debito:");

        txtPrefijoND.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtPrefijoND.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        lbNit42.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbNit42.setText("Prefijo Notas Credito:");

        txtPrefijoNC.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtPrefijoNC.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btnGuardarPrefijos.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardarPrefijos.setFont(new java.awt.Font("Calibri", 1, 15)); // NOI18N
        btnGuardarPrefijos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardarPrefijos.setText("GUARDAR PREFIJOS");
        btnGuardarPrefijos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarPrefijos.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardarPrefijos.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnGuardarPrefijos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarPrefijosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbNit39)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPrefijoAbono, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(lbNit40)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPrefijoEgreso, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(lbNit41)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPrefijoND, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(lbNit42, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(txtPrefijoNC, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGuardarPrefijos, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lbNit39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtPrefijoAbono)
                        .addComponent(txtPrefijoEgreso)
                        .addComponent(lbNit40, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lbNit41, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPrefijoND, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbNit42, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPrefijoNC, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(15, 15, 15)
                .addComponent(btnGuardarPrefijos)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Resoluciones", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N

        tblComprobantes.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tblComprobantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IdComprobante", "Descripción comprobante", "Tipo de comprobante", "Diseño", "Número resolución", "Prefijo", "Fecha inicio", "Fecha final", "# Del", "# Hasta", "# Actual"
            }
        ));
        tblComprobantes.setRowHeight(20);
        tblComprobantes.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblComprobantes);
        if (tblComprobantes.getColumnModel().getColumnCount() > 0) {
            tblComprobantes.getColumnModel().getColumn(0).setMinWidth(0);
            tblComprobantes.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblComprobantes.getColumnModel().getColumn(0).setMaxWidth(0);
            tblComprobantes.getColumnModel().getColumn(3).setMinWidth(60);
            tblComprobantes.getColumnModel().getColumn(3).setPreferredWidth(60);
            tblComprobantes.getColumnModel().getColumn(3).setMaxWidth(60);
            tblComprobantes.getColumnModel().getColumn(5).setMinWidth(60);
            tblComprobantes.getColumnModel().getColumn(5).setPreferredWidth(60);
            tblComprobantes.getColumnModel().getColumn(5).setMaxWidth(60);
            tblComprobantes.getColumnModel().getColumn(6).setMinWidth(92);
            tblComprobantes.getColumnModel().getColumn(6).setPreferredWidth(92);
            tblComprobantes.getColumnModel().getColumn(6).setMaxWidth(92);
            tblComprobantes.getColumnModel().getColumn(7).setMinWidth(90);
            tblComprobantes.getColumnModel().getColumn(7).setPreferredWidth(90);
            tblComprobantes.getColumnModel().getColumn(7).setMaxWidth(90);
            tblComprobantes.getColumnModel().getColumn(8).setMinWidth(80);
            tblComprobantes.getColumnModel().getColumn(8).setPreferredWidth(80);
            tblComprobantes.getColumnModel().getColumn(8).setMaxWidth(80);
            tblComprobantes.getColumnModel().getColumn(9).setMinWidth(80);
            tblComprobantes.getColumnModel().getColumn(9).setPreferredWidth(80);
            tblComprobantes.getColumnModel().getColumn(9).setMaxWidth(80);
            tblComprobantes.getColumnModel().getColumn(10).setMinWidth(80);
            tblComprobantes.getColumnModel().getColumn(10).setPreferredWidth(80);
            tblComprobantes.getColumnModel().getColumn(10).setMaxWidth(80);
        }

        cmbTipoComprobante.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cmbTipoComprobante.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Factura Normal", "Facturación Electrónica", "Facturación Electrónica POS", "Compra Normal", "Documento Soporte" }));

        lbInformacion.setText("Información de resolución.");

        btnGuardar.setBackground(new java.awt.Color(255, 255, 255));
        btnGuardar.setFont(new java.awt.Font("Calibri", 1, 15)); // NOI18N
        btnGuardar.setText("AGREGAR LINEA  ");
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardar.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnGuardarResoluciones.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardarResoluciones.setFont(new java.awt.Font("Calibri", 1, 15)); // NOI18N
        btnGuardarResoluciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardarResoluciones.setText("GUARDAR CAMBIOS DE LAS RESOLUCIONES");
        btnGuardarResoluciones.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarResoluciones.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardarResoluciones.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnGuardarResoluciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarResolucionesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(cmbTipoComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGuardarResoluciones, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbInformacion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1))))
                .addGap(10, 10, 10))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbInformacion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnGuardarResoluciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cmbTipoComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 5, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        DefaultTableModel modeloResoluciones = (DefaultTableModel) tblComprobantes.getModel();
        modeloResoluciones.addRow(new Object[]{"", "", "", "", "", "", "", "", "", "", "", "SI"});
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnGuardarPrefijosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarPrefijosActionPerformed
        if (metodos.msgPregunta(null, "¿Desea continuar?") == 0) {
            ModeloPrefijos datosPrefijos = new ModeloPrefijos(txtPrefijoAbono.getText(), txtPrefijoEgreso.getText(), txtPrefijoND.getText(), txtPrefijoNC.getText(), "TERM-1");
            if (!daoResoluciones.modificarPrefijos(datosPrefijos)) {
                ControladorAlertas.alertFail("Error al guardar los cambios");
            } else {
                instancias.setIdNC(txtPrefijoNC.getText());
                instancias.setIdND(txtPrefijoND.getText());
                instancias.setIdAbono(txtPrefijoAbono.getText());
                instancias.setIdEgreso(txtPrefijoEgreso.getText());
                ControladorAlertas.alertSuccess("Prefijos registrados con éxito");
            }
        }
    }//GEN-LAST:event_btnGuardarPrefijosActionPerformed

    private void btnGuardarResolucionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarResolucionesActionPerformed
        tblComprobantes.removeEditor();
        if (!squemaResoluciones.validacionesResoluciones(tblComprobantes)) {
            return;
        }

        boolean erroresAlModificar = false;
        for (int i = 0; i < tblComprobantes.getRowCount(); i++) {
            int idResolucion = 0;
            if (!"".equals(tblComprobantes.getValueAt(i, 0).toString())) {
                idResolucion = Integer.parseInt(tblComprobantes.getValueAt(i, 0).toString());
            }

            String descripcionResolucion = tblComprobantes.getValueAt(i, 1).toString();
            String tipoResolucion = tblComprobantes.getValueAt(i, 2).toString();
            String disenho = tblComprobantes.getValueAt(i, 3).toString();
            String numeroResolucion = tblComprobantes.getValueAt(i, 4).toString();
            String prefijo = tblComprobantes.getValueAt(i, 5).toString();
            String fechaInicio = Fechas.convertirFechaAString((Date) tblComprobantes.getValueAt(i, 6));
            String fechaFinal = Fechas.convertirFechaAString((Date) tblComprobantes.getValueAt(i, 7));
            int numeracionDel = Integer.parseInt(tblComprobantes.getValueAt(i, 8).toString());
            int numeracionHasta = Integer.parseInt(tblComprobantes.getValueAt(i, 9).toString());
            boolean esResolucionNueva = tblComprobantes.getValueAt(i, 11).toString().equals("SI");

            ModeloResolucion resolucion = new ModeloResolucion(idResolucion, descripcionResolucion, tipoResolucion, disenho, numeroResolucion, prefijo, fechaInicio, fechaFinal, numeracionDel, numeracionHasta);
            if (esResolucionNueva) {
                if (!daoResoluciones.agregarResolucion(resolucion)) {
                    erroresAlModificar = true;
                }
            } else {
                if (!daoResoluciones.modificarResolucion(resolucion)) {
                    erroresAlModificar = true;
                }
            }
        }

        if (erroresAlModificar) {
            ControladorAlertas.alertFail("Error registrar las resoluciones");
        } else {
            ControladorAlertas.alertSuccess("Resoluciones registradas con éxito");
        }

        instancias.getFactura().actualizarTablaResoluciones();
        instancias.getIngresos().actualizarTablaResoluciones();
    }//GEN-LAST:event_btnGuardarResolucionesActionPerformed

    private void cargarPrefijos() {
        ModeloPrefijos prefijos = daoResoluciones.obtenerPrefijos();
        if (null != prefijos.getPrefijoNotaCredito()) {
            txtPrefijoNC.setText(prefijos.getPrefijoNotaCredito());
        }

        if (null != prefijos.getPrefijoNotaDebito()) {
            txtPrefijoND.setText(prefijos.getPrefijoNotaDebito());
        }

        if (null != prefijos.getPrefijoAbonos()) {
            txtPrefijoAbono.setText(prefijos.getPrefijoAbonos());
        }

        if (null != prefijos.getPrefijoEgresos()) {
            txtPrefijoEgreso.setText(prefijos.getPrefijoEgresos());
        }
    }

    private void cargarTablaResoluciones() {
        tblComprobantes.setModel(daoResoluciones.obtenerResolucionesEnTabla());

        for (int i = 0; i < tblComprobantes.getRowCount(); i++) {
            tblComprobantes.setValueAt(Fechas.formatearFecha(tblComprobantes.getValueAt(i, 6).toString()), i, 6);
            tblComprobantes.setValueAt(Fechas.formatearFecha(tblComprobantes.getValueAt(i, 7).toString()), i, 7);
            tblComprobantes.setValueAt("NO", i, 11);
        }

        TableCellEditor tce = new DefaultCellEditor(cmbTipoComprobante);
        tblComprobantes.getColumnModel().getColumn(2).setCellEditor(tce);
        tblComprobantes.getColumnModel().getColumn(6).setCellEditor(new CalendarioEnTabla.incluirCalendarioEnTabla());
        tblComprobantes.getColumnModel().getColumn(6).setCellRenderer(new CalendarioEnTabla.formatoFechaEnTabla("dd/MM/yyyy"));
        tblComprobantes.getColumnModel().getColumn(7).setCellEditor(new CalendarioEnTabla.incluirCalendarioEnTabla());
        tblComprobantes.getColumnModel().getColumn(7).setCellRenderer(new CalendarioEnTabla.formatoFechaEnTabla("dd/MM/yyyy"));

        tblComprobantes.getColumnModel().getColumn(0).setMinWidth(0);
        tblComprobantes.getColumnModel().getColumn(0).setPreferredWidth(0);
        tblComprobantes.getColumnModel().getColumn(0).setMaxWidth(0);
        tblComprobantes.getColumnModel().getColumn(3).setMinWidth(50);
        tblComprobantes.getColumnModel().getColumn(3).setPreferredWidth(50);
        tblComprobantes.getColumnModel().getColumn(3).setMaxWidth(50);
        tblComprobantes.getColumnModel().getColumn(5).setMinWidth(60);
        tblComprobantes.getColumnModel().getColumn(5).setPreferredWidth(60);
        tblComprobantes.getColumnModel().getColumn(5).setMaxWidth(60);
        tblComprobantes.getColumnModel().getColumn(6).setMinWidth(85);
        tblComprobantes.getColumnModel().getColumn(6).setPreferredWidth(85);
        tblComprobantes.getColumnModel().getColumn(6).setMaxWidth(85);
        tblComprobantes.getColumnModel().getColumn(7).setMinWidth(85);
        tblComprobantes.getColumnModel().getColumn(7).setPreferredWidth(85);
        tblComprobantes.getColumnModel().getColumn(7).setMaxWidth(85);
        tblComprobantes.getColumnModel().getColumn(8).setMinWidth(80);
        tblComprobantes.getColumnModel().getColumn(8).setPreferredWidth(80);
        tblComprobantes.getColumnModel().getColumn(8).setMaxWidth(80);
        tblComprobantes.getColumnModel().getColumn(9).setMinWidth(80);
        tblComprobantes.getColumnModel().getColumn(9).setPreferredWidth(80);
        tblComprobantes.getColumnModel().getColumn(9).setMaxWidth(80);
        tblComprobantes.getColumnModel().getColumn(10).setMinWidth(0);
        tblComprobantes.getColumnModel().getColumn(10).setPreferredWidth(0);
        tblComprobantes.getColumnModel().getColumn(10).setMaxWidth(0);
        tblComprobantes.getColumnModel().getColumn(11).setMinWidth(0);
        tblComprobantes.getColumnModel().getColumn(11).setPreferredWidth(0);
        tblComprobantes.getColumnModel().getColumn(11).setMaxWidth(0);

        if (!instancias.getConfiguraciones().isFacturaElectronica()) {
            tblComprobantes.getColumnModel().getColumn(2).setMinWidth(0);
            tblComprobantes.getColumnModel().getColumn(2).setPreferredWidth(0);
            tblComprobantes.getColumnModel().getColumn(2).setMaxWidth(0);
            tblComprobantes.getColumnModel().getColumn(3).setMinWidth(0);
            tblComprobantes.getColumnModel().getColumn(3).setPreferredWidth(0);
            tblComprobantes.getColumnModel().getColumn(3).setMaxWidth(0);
        }
    }

    private void limpiar(JPanel panel) {
        for (int x = 0; x < panel.getComponentCount(); x++) {
            if (panel.getComponent(x) instanceof JTextField) {
                JTextField textField = (JTextField) panel.getComponent(x);
                textField.setText("");
            }
        }
    }

    public int getDiasCalendario(Calendar fechaInicial, Calendar fechaFinal) {
        int diffDays = 0;
        if (fechaFinal.before(fechaInicial) || fechaInicial.equals(fechaFinal)) {
            diffDays = 0;
        } else {
            while (fechaInicial.before(fechaFinal) || fechaInicial.equals(fechaFinal)) {
                diffDays++;
                fechaInicial.add(Calendar.DATE, 1);
            }
        }
        return diffDays == 0 ? 0 : diffDays - 1;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(vistaResoluciones.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(vistaResoluciones.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(vistaResoluciones.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(vistaResoluciones.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                vistaResoluciones dialog = new vistaResoluciones(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnGuardarPrefijos;
    private javax.swing.JButton btnGuardarResoluciones;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cmbTipoComprobante;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbInformacion;
    private javax.swing.JLabel lbNit39;
    private javax.swing.JLabel lbNit40;
    private javax.swing.JLabel lbNit41;
    private javax.swing.JLabel lbNit42;
    private javax.swing.JTable tblComprobantes;
    private javax.swing.JTextField txtPrefijoAbono;
    private javax.swing.JTextField txtPrefijoEgreso;
    private javax.swing.JTextField txtPrefijoNC;
    private javax.swing.JTextField txtPrefijoND;
    // End of variables declaration//GEN-END:variables
}
