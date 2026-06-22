package Vista.Configuraciones;

import Modelo.Maestra.modeloConfiguracion;
import Controlador.Alertas.ControladorAlertas;
import dao.Configuraciones.DaoResoluciones;
import Modelo.Maestra.ModeloPrefijos;
import clases.Instancias;
import clases.metodosGenerales;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.DefaultCellEditor;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.KeyStroke;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

public class vistaSuperMaestra extends javax.swing.JDialog {

    private final DaoResoluciones daoResoluciones = new DaoResoluciones();
    private final ControladorAlertas alertas = new ControladorAlertas();
    private metodosGenerales metodos = new metodosGenerales();
    private Instancias instancias = Instancias.getInstancias();

    private boolean congeladas, medico, veterinaria, parqueadero, ordenServicio, creditos, separe, pedido, agenda, restaurante, recordatorios,
            laboratorio, servicioAutomotor, periodo, oftalmologia, inventarioBodegas, productosDetallados, facturacionLote, usb, facturaElectronica;
    private String regimen, informacionLegal, tipoImpresion, nit, nombre, telefono, fechaInicio;
    int diasAlertaBloqueo, diasGabelaBloqueo;

    public boolean isCongeladas() {
        return congeladas;
    }

    public boolean isMedico() {
        return medico;
    }

    public boolean isVeterinaria() {
        return veterinaria;
    }

    public boolean isParqueadero() {
        return parqueadero;
    }

    public boolean isOrdenServicio() {
        return ordenServicio;
    }

    public boolean isCreditos() {
        return creditos;
    }

    public boolean isSepare() {
        return separe;
    }

    public boolean isPedido() {
        return pedido;
    }

    public boolean isAgenda() {
        return agenda;
    }

    public boolean isRestaurante() {
        return restaurante;
    }

    public boolean isRecordatorios() {
        return recordatorios;
    }

    public boolean isLaboratorio() {
        return laboratorio;
    }

    public boolean isServicioAutomotor() {
        return servicioAutomotor;
    }

    public boolean isPeriodo() {
        return periodo;
    }

    public boolean isOftalmologia() {
        return oftalmologia;
    }

    public boolean isInventarioBodegas() {
        return inventarioBodegas;
    }

    public boolean isProductosDetallados() {
        return productosDetallados;
    }

    public boolean isFacturacionLote() {
        return facturacionLote;
    }

    public boolean isUsb() {
        return usb;
    }

    public boolean isFacturaElectronica() {
        return facturaElectronica;
    }

    public String getRegimen() {
        return regimen;
    }

    public String getInformacionLegal() {
        return informacionLegal;
    }

    public String getTipoImpresion() {
        return tipoImpresion;
    }

    public int getDiasAlertaBloqueo() {
        return diasAlertaBloqueo;
    }

    public int getDiasGabelaBloqueo() {
        return diasGabelaBloqueo;
    }

    public vistaSuperMaestra(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);

        inicializarModelosTablas();

        cargarSuperMaestra();
        cargarPrefijos();

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

        grupoRegimen = new javax.swing.ButtonGroup();
        jPanel5 = new javax.swing.JPanel();
        btnGuardarSuperMaestra = new javax.swing.JButton();
        jPanel45 = new javax.swing.JPanel();
        lbNit9 = new javax.swing.JLabel();
        jScrollPane16 = new javax.swing.JScrollPane();
        tblModulos = new javax.swing.JTable();
        jPanel46 = new javax.swing.JPanel();
        lbNit10 = new javax.swing.JLabel();
        rdComun = new javax.swing.JRadioButton();
        rdSimplificado = new javax.swing.JRadioButton();
        jPanel47 = new javax.swing.JPanel();
        lbNit11 = new javax.swing.JLabel();
        cmbTipoImpresiones = new javax.swing.JComboBox<>();
        lbTelefono17 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtInformacionLegal = new javax.swing.JTextArea();
        jPanel15 = new javax.swing.JPanel();
        lbNit13 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtDiasAlertaBloqueo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtDiasGabelaBloqueo = new javax.swing.JTextField();
        pnlOculto = new javax.swing.JPanel();
        cmbOpcionesSINO = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtFEDisponibles = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Configuraciones del sistema");
        setResizable(false);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        btnGuardarSuperMaestra.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardarSuperMaestra.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnGuardarSuperMaestra.setText("GUARDAR");
        btnGuardarSuperMaestra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarSuperMaestraActionPerformed(evt);
            }
        });

        jPanel45.setBackground(new java.awt.Color(255, 255, 255));
        jPanel45.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Adobe Arabic", 1, 12), new java.awt.Color(102, 153, 0))); // NOI18N

        lbNit9.setBackground(new java.awt.Color(204, 204, 204));
        lbNit9.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit9.setText("Modulos");
        lbNit9.setOpaque(true);

        tblModulos.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        tblModulos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"ORDENES DE SERVICIO", null},
                {"ORDENES DE SERVICIO AUTOMOTOR", null},
                {"CRÉDITOS", null},
                {"PLAN SEPARE", null},
                {"PEDIDOS", null},
                {"CONGELADAS", null},
                {"MEDICO", null},
                {"VETERINARIO", null},
                {"PARQUEADERO", null},
                {"AGENDA", null},
                {"RESTAURANTE", null},
                {"RECORDATORIOS", null},
                {"LABORATORIO", null},
                {"OFTALMOLOGIA", null},
                {"FACTURACIÓN LOTE", null},
                {"FACTURACIÓN ELECTRÓNICA", null},
                {"INVENTARIO POR BODEGAS", null},
                {"PRODUCTOS DETALLADOS", null},
                {"USB PARA DESBLOQUEAR", null}
            },
            new String [] {
                "Descripción", "Opción"
            }
        ));
        tblModulos.setRowHeight(28);
        tblModulos.getTableHeader().setReorderingAllowed(false);
        jScrollPane16.setViewportView(tblModulos);
        if (tblModulos.getColumnModel().getColumnCount() > 0) {
            tblModulos.getColumnModel().getColumn(1).setMinWidth(80);
            tblModulos.getColumnModel().getColumn(1).setPreferredWidth(80);
            tblModulos.getColumnModel().getColumn(1).setMaxWidth(80);
        }

        javax.swing.GroupLayout jPanel45Layout = new javax.swing.GroupLayout(jPanel45);
        jPanel45.setLayout(jPanel45Layout);
        jPanel45Layout.setHorizontalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbNit9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel45Layout.setVerticalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel45Layout.createSequentialGroup()
                .addComponent(lbNit9, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 562, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel46.setBackground(new java.awt.Color(255, 255, 255));
        jPanel46.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Adobe Arabic", 1, 12), new java.awt.Color(102, 153, 0))); // NOI18N

        lbNit10.setBackground(new java.awt.Color(204, 204, 204));
        lbNit10.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit10.setText("Regimen");
        lbNit10.setOpaque(true);

        rdComun.setBackground(new java.awt.Color(255, 255, 255));
        grupoRegimen.add(rdComun);
        rdComun.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdComun.setText("COMÚN");

        rdSimplificado.setBackground(new java.awt.Color(255, 255, 255));
        grupoRegimen.add(rdSimplificado);
        rdSimplificado.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdSimplificado.setText("SIMPLIFICADO");

        javax.swing.GroupLayout jPanel46Layout = new javax.swing.GroupLayout(jPanel46);
        jPanel46.setLayout(jPanel46Layout);
        jPanel46Layout.setHorizontalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbNit10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel46Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rdComun)
                .addGap(3, 3, 3)
                .addComponent(rdSimplificado, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel46Layout.setVerticalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel46Layout.createSequentialGroup()
                .addComponent(lbNit10, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdComun)
                    .addComponent(rdSimplificado)))
        );

        jPanel47.setBackground(new java.awt.Color(255, 255, 255));
        jPanel47.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Adobe Arabic", 1, 12), new java.awt.Color(102, 153, 0))); // NOI18N

        lbNit11.setBackground(new java.awt.Color(204, 204, 204));
        lbNit11.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit11.setText("Impresiones");
        lbNit11.setOpaque(true);

        cmbTipoImpresiones.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cmbTipoImpresiones.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Con codigo", "Sin codigo", "Tipo serial" }));

        javax.swing.GroupLayout jPanel47Layout = new javax.swing.GroupLayout(jPanel47);
        jPanel47.setLayout(jPanel47Layout);
        jPanel47Layout.setHorizontalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cmbTipoImpresiones, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbNit11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel47Layout.setVerticalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel47Layout.createSequentialGroup()
                .addComponent(lbNit11, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cmbTipoImpresiones, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
        );

        lbTelefono17.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono17.setText("Información legal impresión:");

        txtInformacionLegal.setColumns(20);
        txtInformacionLegal.setRows(3);
        jScrollPane1.setViewportView(txtInformacionLegal);

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lbNit13.setBackground(new java.awt.Color(204, 204, 204));
        lbNit13.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit13.setText("Vencimiento de licencia");
        lbNit13.setOpaque(true);

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel1.setText("A cuantos días antes mostrar alerta de bloqueo:");

        txtDiasAlertaBloqueo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDiasAlertaBloqueo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDiasAlertaBloqueoKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel4.setText("Días de gabela para omitir el bloqueo:");

        txtDiasGabelaBloqueo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDiasGabelaBloqueo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDiasGabelaBloqueoKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbNit13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDiasAlertaBloqueo, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDiasGabelaBloqueo, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(lbNit13, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDiasAlertaBloqueo, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDiasGabelaBloqueo)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );

        pnlOculto.setBackground(new java.awt.Color(255, 255, 255));

        cmbOpcionesSINO.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NO", "SI" }));

        javax.swing.GroupLayout pnlOcultoLayout = new javax.swing.GroupLayout(pnlOculto);
        pnlOculto.setLayout(pnlOcultoLayout);
        pnlOcultoLayout.setHorizontalGroup(
            pnlOcultoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlOcultoLayout.createSequentialGroup()
                .addGap(0, 27, Short.MAX_VALUE)
                .addComponent(cmbOpcionesSINO, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlOcultoLayout.setVerticalGroup(
            pnlOcultoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOcultoLayout.createSequentialGroup()
                .addComponent(cmbOpcionesSINO, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 49, Short.MAX_VALUE))
        );

        jLabel5.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel5.setText("Facturas electronicas disponibles");

        txtFEDisponibles.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFEDisponibles.setText("0");
        txtFEDisponibles.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtFEDisponibles.setEnabled(false);
        txtFEDisponibles.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFEDisponiblesKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(pnlOculto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGuardarSuperMaestra, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbTelefono17)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFEDisponibles, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtFEDisponibles, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(21, 21, 21)
                        .addComponent(lbTelefono17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(pnlOculto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnGuardarSuperMaestra, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)))
                    .addComponent(jPanel45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarSuperMaestraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarSuperMaestraActionPerformed
        modeloConfiguracion modelo = llenarModeloSuperMaestra();

        if (!instancias.getSql().modificarConfiguracion(modelo)) {
            metodos.msgError(null, "Hubo un problema al guardar los cambios de configuracion");
            return;
        }

        metodos.msgExito(null, "Cambios guardados con éxito");
        instancias.getMenu().setInstancias(instancias);
        this.dispose();
    }//GEN-LAST:event_btnGuardarSuperMaestraActionPerformed


    private void txtDiasAlertaBloqueoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiasAlertaBloqueoKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtDiasAlertaBloqueoKeyTyped

    private void txtDiasGabelaBloqueoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiasGabelaBloqueoKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtDiasGabelaBloqueoKeyTyped

    private void txtFEDisponiblesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFEDisponiblesKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFEDisponiblesKeyTyped

    private modeloConfiguracion llenarModeloSuperMaestra() {
        modeloConfiguracion modelo = new modeloConfiguracion();
        String regimenSistema = "ConIva", tipoImpresion;
        if (rdSimplificado.isSelected()) {
            regimenSistema = "SinIva";
        }

        if (cmbTipoImpresiones.getSelectedItem().equals("Con codigo")) {
            tipoImpresion = "Con-Codigo";
        } else if (cmbTipoImpresiones.getSelectedItem().equals("Sin codigo")) {
            tipoImpresion = "Sin-Codigo";
        } else {
            tipoImpresion = "Imei";
        }

        modelo.setNit(nit);
        modelo.setNombre(nombre);
        modelo.setTelefono(telefono);
        modelo.setFechaInicio(fechaInicio);
        modelo.setRegimen(regimenSistema);
        modelo.setOrdenServicio(tipoSeleccionTabla(tblModulos.getValueAt(0, 1).toString()));
        modelo.setServicioAutomotor(tipoSeleccionTabla(tblModulos.getValueAt(1, 1).toString()));
        modelo.setCreditos(tipoSeleccionTabla(tblModulos.getValueAt(2, 1).toString()));
        modelo.setSepare(tipoSeleccionTabla(tblModulos.getValueAt(3, 1).toString()));
        modelo.setPedido(tipoSeleccionTabla(tblModulos.getValueAt(4, 1).toString()));
        modelo.setCongeladas(tipoSeleccionTabla(tblModulos.getValueAt(5, 1).toString()));
        modelo.setMedico(tipoSeleccionTabla(tblModulos.getValueAt(6, 1).toString()));
        modelo.setVeterinaria(tipoSeleccionTabla(tblModulos.getValueAt(7, 1).toString()));
        modelo.setParqueadero(tipoSeleccionTabla(tblModulos.getValueAt(8, 1).toString()));
        modelo.setAgenda(tipoSeleccionTabla(tblModulos.getValueAt(9, 1).toString()));
        modelo.setRestaurante(tipoSeleccionTabla(tblModulos.getValueAt(10, 1).toString()));
        modelo.setRecordatorios(tipoSeleccionTabla(tblModulos.getValueAt(11, 1).toString()));
        modelo.setLaboratorio(tipoSeleccionTabla(tblModulos.getValueAt(12, 1).toString()));
        modelo.setOftalmologia(tipoSeleccionTabla(tblModulos.getValueAt(13, 1).toString()));
        modelo.setFacturacionLote(tipoSeleccionTabla(tblModulos.getValueAt(14, 1).toString()));
        modelo.setFacturaElectronica(tipoSeleccionTabla(tblModulos.getValueAt(15, 1).toString()));
        modelo.setInventarioBodegas(tipoSeleccionTabla(tblModulos.getValueAt(16, 1).toString()));
        modelo.setProductosSerial(tipoSeleccionTabla(tblModulos.getValueAt(17, 1).toString()));
        modelo.setUsb(tipoSeleccionTabla(tblModulos.getValueAt(18, 1).toString()));
        modelo.setInformacionLegal(txtInformacionLegal.getText());
        modelo.setTipoImpresion(tipoImpresion);
        modelo.setDiasAntesAlertaBloqueo(Integer.parseInt(txtDiasAlertaBloqueo.getText()));
        modelo.setDiasDespuesGabelaBloqueo(Integer.parseInt(txtDiasGabelaBloqueo.getText()));
        modelo.setNumeroFacturasElectronicasDisponibles(Integer.parseInt(txtFEDisponibles.getText()));
        return modelo;
    }

    private void cargarPrefijos() {
        ModeloPrefijos prefijos = daoResoluciones.obtenerPrefijos();
        if (null != prefijos.getPrefijoNotaCredito()) {
            instancias.setIdNC(prefijos.getPrefijoNotaCredito());
        }

        if (null != prefijos.getPrefijoNotaDebito()) {
            instancias.setIdND(prefijos.getPrefijoNotaDebito());
        }

        if (null != prefijos.getPrefijoAbonos()) {
            instancias.setIdAbono(prefijos.getPrefijoAbonos());
        }

        if (null != prefijos.getPrefijoEgresos()) {
            instancias.setIdEgreso(prefijos.getPrefijoEgresos());
        }
    }

    public void cargarSuperMaestra() {
        instancias.setSimbolo("$");
        instancias.setDescripcionSimbolo("PESOS");
        instancias.setCadenaDecimales("##");

        modeloConfiguracion modelo = instancias.getSql().getDatosConfiguracion();
        tblModulos.setValueAt(cargarTipoSeleccion(modelo.isOrdenServicio()), 0, 1);
        this.ordenServicio = modelo.isOrdenServicio();
        tblModulos.setValueAt(cargarTipoSeleccion(modelo.isServicioAutomotor()), 1, 1);
        this.servicioAutomotor = modelo.isServicioAutomotor();
        tblModulos.setValueAt(cargarTipoSeleccion(modelo.isCreditos()), 2, 1);
        this.creditos = modelo.isCreditos();
        tblModulos.setValueAt(cargarTipoSeleccion(modelo.isSepare()), 3, 1);
        this.separe = modelo.isSepare();
        tblModulos.setValueAt(cargarTipoSeleccion(modelo.isPedido()), 4, 1);
        this.pedido = modelo.isPedido();
        tblModulos.setValueAt(cargarTipoSeleccion(modelo.isCongeladas()), 5, 1);
        this.congeladas = modelo.isCongeladas();
        tblModulos.setValueAt(cargarTipoSeleccion(modelo.isMedico()), 6, 1);
        this.medico = modelo.isMedico();
        tblModulos.setValueAt(cargarTipoSeleccion(modelo.isVeterinaria()), 7, 1);
        this.veterinaria = modelo.isVeterinaria();
        tblModulos.setValueAt(cargarTipoSeleccion(modelo.isParqueadero()), 8, 1);
        this.parqueadero = modelo.isParqueadero();
        tblModulos.setValueAt(cargarTipoSeleccion(modelo.isAgenda()), 9, 1);
        this.agenda = modelo.isAgenda();
        tblModulos.setValueAt(cargarTipoSeleccion(modelo.isRestaurante()), 10, 1);
        this.restaurante = modelo.isRestaurante();
        tblModulos.setValueAt(cargarTipoSeleccion(modelo.isRecordatorios()), 11, 1);
        this.recordatorios = modelo.isRecordatorios();
        tblModulos.setValueAt(cargarTipoSeleccion(modelo.isLaboratorio()), 12, 1);
        this.laboratorio = modelo.isLaboratorio();
        tblModulos.setValueAt(cargarTipoSeleccion(modelo.isOftalmologia()), 13, 1);
        this.oftalmologia = modelo.isOftalmologia();
        tblModulos.setValueAt(cargarTipoSeleccion(modelo.isFacturacionLote()), 14, 1);
        this.facturacionLote = modelo.isFacturacionLote();
        tblModulos.setValueAt(cargarTipoSeleccion(modelo.isFacturaElectronica()), 15, 1);
        this.facturaElectronica = modelo.isFacturaElectronica();
        tblModulos.setValueAt(cargarTipoSeleccion(modelo.isInventarioBodegas()), 16, 1);
        this.inventarioBodegas = modelo.isInventarioBodegas();
        tblModulos.setValueAt(cargarTipoSeleccion(modelo.isProductosSerial()), 17, 1);
        this.productosDetallados = modelo.isProductosSerial();
        tblModulos.setValueAt(cargarTipoSeleccion(modelo.isUsb()), 18, 1);
        this.usb = modelo.isUsb();

        if (modelo.getRegimen().equals("SinIva")) {
            rdSimplificado.setSelected(true);
            instancias.setRegimen("SinIva");
        } else {
            rdComun.setSelected(true);
            instancias.setRegimen("");
        }

        if (modelo.getTipoImpresion().equals("Con-Codigo")) {
            cmbTipoImpresiones.setSelectedIndex(0);
        } else if (modelo.getTipoImpresion().equals("Sin-Codigo")) {
            cmbTipoImpresiones.setSelectedIndex(1);
        } else {
            cmbTipoImpresiones.setSelectedIndex(2);
        }

        nit = modelo.getNit();
        nombre = modelo.getNombre();
        telefono = modelo.getTelefono();
        fechaInicio = modelo.getFechaInicio();
        txtDiasAlertaBloqueo.setText(modelo.getDiasAntesAlertaBloqueo() + "");
        txtDiasGabelaBloqueo.setText(modelo.getDiasDespuesGabelaBloqueo() + "");
        txtFEDisponibles.setText(modelo.getNumeroFacturasElectronicasDisponibles() + "");
        txtInformacionLegal.setText(modelo.getInformacionLegal() + "");

        instancias.getReporte().setInformacionLegalClick(modelo.getInformacionLegal());
        this.tipoImpresion = modelo.getTipoImpresion();
    }

    private String cargarTipoSeleccion(boolean tipoSeleccionada) {
        String opcionSeleccionada = "NO";
        if (tipoSeleccionada) {
            opcionSeleccionada = "SI";
        }

        return opcionSeleccionada;
    }

    private boolean tipoSeleccionTabla(String tipoSeleccionada) {
        boolean opcionSeleccionada = false;
        if ("SI".equals(tipoSeleccionada)) {
            opcionSeleccionada = true;
        }

        return opcionSeleccionada;
    }

    private void inicializarModelosTablas() {
        TableColumn tc = tblModulos.getColumnModel().getColumn(1);
        TableCellEditor tce = new DefaultCellEditor(cmbOpcionesSINO);
        tc.setCellEditor(tce);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardarSuperMaestra;
    private javax.swing.JComboBox<String> cmbOpcionesSINO;
    private javax.swing.JComboBox<String> cmbTipoImpresiones;
    private javax.swing.ButtonGroup grupoRegimen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JLabel lbNit10;
    private javax.swing.JLabel lbNit11;
    private javax.swing.JLabel lbNit13;
    private javax.swing.JLabel lbNit9;
    private javax.swing.JLabel lbTelefono17;
    private javax.swing.JPanel pnlOculto;
    private javax.swing.JRadioButton rdComun;
    private javax.swing.JRadioButton rdSimplificado;
    private javax.swing.JTable tblModulos;
    private javax.swing.JTextField txtDiasAlertaBloqueo;
    private javax.swing.JTextField txtDiasGabelaBloqueo;
    private javax.swing.JTextField txtFEDisponibles;
    private javax.swing.JTextArea txtInformacionLegal;
    // End of variables declaration//GEN-END:variables
}
