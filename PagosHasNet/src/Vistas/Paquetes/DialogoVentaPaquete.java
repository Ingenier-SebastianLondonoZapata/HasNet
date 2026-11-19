package Vistas.Paquetes;

import Controlador.Alertas.controladorAlertas;
import Modelos.Ingresos.modeloEstadoCliente;
import Modelos.Ingresos.modeloPlanFacturacion;
import Modelos.Ingresos.modeloVentaPaquete;
import clases.Instancias;
import clases.SQLIngresos;
import clases.big;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class DialogoVentaPaquete extends javax.swing.JDialog {

    private final String idCliente;
    private final List<modeloPlanFacturacion> planes = new ArrayList<>();
    private modeloEstadoCliente estado;
    private modeloVentaPaquete ventaPrevista;
    private boolean ventaRealizada;

    public DialogoVentaPaquete(java.awt.Frame padre, String idCliente) {
        super(padre, true);
        this.idCliente = idCliente;
        initComponents();
        tblPlanes.getTableHeader().setFont(new java.awt.Font("Century Gothic", java.awt.Font.BOLD, 14));
        tblPlanes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblPlanes.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                actualizarVistaPrevia();
            }
        });
        if (cargarDatos()) {
            pack();
            setLocationRelativeTo(padre);
        }
    }

    public boolean isListo() {
        return estado != null;
    }

    public boolean isVentaRealizada() {
        return ventaRealizada;
    }

    private SQLIngresos sqlIngresos() {
        return Instancias.getInstancias().getSqlIngresos();
    }

    private boolean cargarDatos() {
        try {
            estado = sqlIngresos().obtenerEstadoCliente(idCliente);
            LocalDate hoy = SQLIngresos.hoy();
            lbTitulo.setText(estado.getNombre());
            lbSubtitulo.setText("NIT " + estado.getNit());
            lbSinVencimiento.setText(big.setNumero(estado.getNumeroFEDisponibles()) + " documentos");
            int mensuales = estado.documentosMensualesVigentes(hoy);
            lbMensuales.setText(big.setNumero(mensuales) + " documentos" + (mensuales > 0 && estado.getVigenciaFEMensuales() != null
                    ? " (hasta " + estado.getVigenciaFEMensuales().format(SQLIngresos.FORMATO_FECHA) + ")" : ""));
            lbPlan.setText(descripcionPlan());
            btnCancelarPlan.setVisible(estado.getIdPlanMensual() != null && !estado.isCancelacionPlanProgramada());

            planes.clear();
            planes.addAll(sqlIngresos().listarPlanes(true));
            DefaultTableModel modelo = (DefaultTableModel) tblPlanes.getModel();
            modelo.setRowCount(0);
            for (modeloPlanFacturacion plan : planes) {
                modelo.addRow(new Object[]{plan.getIdPlan(), plan.getNombre(), plan.isMensual() ? "Mensual (vence)" : "Sin vencimiento",
                    big.setNumero(plan.getNumeroDocumentos()), big.setMoneda(plan.getPrecio())});
            }
            if (planes.isEmpty()) {
                mostrarDescripcion("No hay paquetes activos. Créelos en la pestaña de configuraciones, tarifas y paquetes.");
                btnVender.setEnabled(false);
            } else {
                tblPlanes.setRowSelectionInterval(0, 0);
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DialogoVentaPaquete.class.getName()).log(Level.SEVERE, "Error al cargar la venta de paquetes", ex);
            controladorAlertas.alertFail("No se pudo cargar la información del cliente");
        } catch (IllegalStateException ex) {
            controladorAlertas.alertFail(ex.getMessage());
        }
        estado = null;
        return false;
    }

    private String descripcionPlan() throws SQLException {
        String actual = "Ninguno";
        if (estado.getIdPlanMensual() != null) {
            modeloPlanFacturacion plan = sqlIngresos().obtenerPlan(estado.getIdPlanMensual());
            actual = plan == null ? "Ninguno" : plan.getNombre();
        }
        if (estado.isCancelacionPlanProgramada()) {
            return actual + " (se cancela en el próximo pago)";
        }
        if (estado.getIdPlanMensualSiguiente() != null) {
            modeloPlanFacturacion siguiente = sqlIngresos().obtenerPlan(estado.getIdPlanMensualSiguiente());
            return actual + " → " + (siguiente == null ? "?" : siguiente.getNombre());
        }
        return actual;
    }

    private void actualizarVistaPrevia() {
        int fila = tblPlanes.getSelectedRow();
        if (fila < 0) {
            ventaPrevista = null;
            mostrarDescripcion("Seleccione un paquete.");
            lbTotal.setText(big.setMoneda(BigDecimal.ZERO));
            btnVender.setEnabled(false);
            return;
        }
        modeloPlanFacturacion plan = planes.get(tblPlanes.convertRowIndexToModel(fila));
        try {
            ventaPrevista = sqlIngresos().previsualizarVenta(idCliente, plan.getIdPlan());
            mostrarDescripcion(ventaPrevista.getDescripcion());
            lbTotal.setText(big.setMoneda(ventaPrevista.getValor()));
            btnVender.setEnabled(ventaPrevista.isPermitida());
            btnVender.setText(ventaPrevista.getValor().signum() > 0 ? "VENDER" : "PROGRAMAR");
        } catch (SQLException | IllegalStateException ex) {
            Logger.getLogger(DialogoVentaPaquete.class.getName()).log(Level.WARNING, "No se pudo calcular la venta", ex);
            ventaPrevista = null;
            mostrarDescripcion("No se pudo calcular la venta: " + ex.getMessage());
            btnVender.setEnabled(false);
        }
    }

    private void mostrarDescripcion(String texto) {
        lbDescripcion.setText("<html><body style='width:560px'>" + texto.replace("&", "&amp;").replace("<", "&lt;") + "</body></html>");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlContenido = new javax.swing.JPanel();
        pnlEncabezado = new javax.swing.JPanel();
        lbTitulo = new javax.swing.JLabel();
        lbSubtitulo = new javax.swing.JLabel();
        pnlDatos = new javax.swing.JPanel();
        lbEtiquetaSinVencimiento = new javax.swing.JLabel();
        lbSinVencimiento = new javax.swing.JLabel();
        lbEtiquetaMensuales = new javax.swing.JLabel();
        lbMensuales = new javax.swing.JLabel();
        lbEtiquetaPlan = new javax.swing.JLabel();
        lbPlan = new javax.swing.JLabel();
        pnlPaquetes = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPlanes = new javax.swing.JTable();
        pnlResumen = new javax.swing.JPanel();
        lbDescripcion = new javax.swing.JLabel();
        pnlTotal = new javax.swing.JPanel();
        lbEtiquetaTotal = new javax.swing.JLabel();
        lbTotal = new javax.swing.JLabel();
        btnCancelarPlan = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        btnVender = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Vender paquete de facturación");
        setResizable(false);

        pnlContenido.setBackground(new java.awt.Color(255, 255, 255));

        pnlEncabezado.setBackground(new java.awt.Color(44, 62, 80));

        lbTitulo.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        lbTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lbTitulo.setText("Cliente");

        lbSubtitulo.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbSubtitulo.setForeground(new java.awt.Color(236, 240, 241));
        lbSubtitulo.setText("NIT");

        javax.swing.GroupLayout pnlEncabezadoLayout = new javax.swing.GroupLayout(pnlEncabezado);
        pnlEncabezado.setLayout(pnlEncabezadoLayout);
        pnlEncabezadoLayout.setHorizontalGroup(
            pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTitulo)
                    .addComponent(lbSubtitulo))
                .addGap(20, 20, Short.MAX_VALUE))
        );
        pnlEncabezadoLayout.setVerticalGroup(
            pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lbTitulo)
                .addGap(4, 4, 4)
                .addComponent(lbSubtitulo)
                .addGap(15, 15, 15))
        );

        pnlDatos.setBackground(new java.awt.Color(255, 255, 255));

        lbEtiquetaSinVencimiento.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lbEtiquetaSinVencimiento.setForeground(new java.awt.Color(127, 140, 141));
        lbEtiquetaSinVencimiento.setText("SIN VENCIMIENTO");

        lbSinVencimiento.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbSinVencimiento.setText("0 documentos");

        lbEtiquetaMensuales.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lbEtiquetaMensuales.setForeground(new java.awt.Color(127, 140, 141));
        lbEtiquetaMensuales.setText("MENSUALES VIGENTES");

        lbMensuales.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbMensuales.setText("0 documentos");

        lbEtiquetaPlan.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lbEtiquetaPlan.setForeground(new java.awt.Color(127, 140, 141));
        lbEtiquetaPlan.setText("PLAN MENSUAL");

        lbPlan.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbPlan.setText("Ninguno");

        javax.swing.GroupLayout pnlDatosLayout = new javax.swing.GroupLayout(pnlDatos);
        pnlDatos.setLayout(pnlDatosLayout);
        pnlDatosLayout.setHorizontalGroup(
            pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosLayout.createSequentialGroup()
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbEtiquetaSinVencimiento)
                    .addComponent(lbSinVencimiento))
                .addGap(40, 40, 40)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbEtiquetaMensuales)
                    .addComponent(lbMensuales))
                .addGap(40, 40, 40)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbEtiquetaPlan)
                    .addComponent(lbPlan))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlDatosLayout.setVerticalGroup(
            pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosLayout.createSequentialGroup()
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDatosLayout.createSequentialGroup()
                        .addComponent(lbEtiquetaSinVencimiento)
                        .addGap(2, 2, 2)
                        .addComponent(lbSinVencimiento))
                    .addGroup(pnlDatosLayout.createSequentialGroup()
                        .addComponent(lbEtiquetaMensuales)
                        .addGap(2, 2, 2)
                        .addComponent(lbMensuales))
                    .addGroup(pnlDatosLayout.createSequentialGroup()
                        .addComponent(lbEtiquetaPlan)
                        .addGap(2, 2, 2)
                        .addComponent(lbPlan))))
        );

        pnlPaquetes.setBackground(new java.awt.Color(255, 255, 255));
        pnlPaquetes.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Paquetes disponibles", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 14))); // NOI18N

        tblPlanes.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        tblPlanes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Paquete", "Tipo", "Documentos", "Precio"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPlanes.setRowHeight(28);
        tblPlanes.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblPlanes);
        if (tblPlanes.getColumnModel().getColumnCount() > 0) {
            tblPlanes.getColumnModel().getColumn(0).setMinWidth(0);
            tblPlanes.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblPlanes.getColumnModel().getColumn(0).setMaxWidth(0);
            tblPlanes.getColumnModel().getColumn(1).setPreferredWidth(220);
            tblPlanes.getColumnModel().getColumn(2).setPreferredWidth(140);
            tblPlanes.getColumnModel().getColumn(3).setPreferredWidth(100);
            tblPlanes.getColumnModel().getColumn(4).setPreferredWidth(120);
        }

        javax.swing.GroupLayout pnlPaquetesLayout = new javax.swing.GroupLayout(pnlPaquetes);
        pnlPaquetes.setLayout(pnlPaquetesLayout);
        pnlPaquetesLayout.setHorizontalGroup(
            pnlPaquetesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPaquetesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlPaquetesLayout.setVerticalGroup(
            pnlPaquetesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPaquetesLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
        );

        pnlResumen.setBackground(new java.awt.Color(255, 255, 255));
        pnlResumen.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "¿Qué pasará al vender?", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 14))); // NOI18N

        lbDescripcion.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbDescripcion.setText("Seleccione un paquete.");
        lbDescripcion.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout pnlResumenLayout = new javax.swing.GroupLayout(pnlResumen);
        pnlResumen.setLayout(pnlResumenLayout);
        pnlResumenLayout.setHorizontalGroup(
            pnlResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlResumenLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbDescripcion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlResumenLayout.setVerticalGroup(
            pnlResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlResumenLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(lbDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
        );

        pnlTotal.setBackground(new java.awt.Color(232, 248, 239));

        lbEtiquetaTotal.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbEtiquetaTotal.setText("SE COBRA AHORA");

        lbTotal.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        lbTotal.setForeground(new java.awt.Color(39, 174, 96));
        lbTotal.setText("$ 0");

        javax.swing.GroupLayout pnlTotalLayout = new javax.swing.GroupLayout(pnlTotal);
        pnlTotal.setLayout(pnlTotalLayout);
        pnlTotalLayout.setHorizontalGroup(
            pnlTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTotalLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lbEtiquetaTotal)
                .addGap(20, 20, Short.MAX_VALUE)
                .addComponent(lbTotal)
                .addGap(15, 15, 15))
        );
        pnlTotalLayout.setVerticalGroup(
            pnlTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTotalLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnlTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbEtiquetaTotal)
                    .addComponent(lbTotal))
                .addGap(10, 10, 10))
        );

        btnCancelarPlan.setBackground(new java.awt.Color(255, 226, 157));
        btnCancelarPlan.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnCancelarPlan.setText("CANCELAR PLAN MENSUAL");
        btnCancelarPlan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarPlanActionPerformed(evt);
            }
        });

        btnCerrar.setBackground(new java.awt.Color(255, 204, 204));
        btnCerrar.setFont(new java.awt.Font("Century Gothic", 1, 15)); // NOI18N
        btnCerrar.setText("CERRAR");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        btnVender.setBackground(new java.awt.Color(46, 204, 113));
        btnVender.setFont(new java.awt.Font("Century Gothic", 1, 15)); // NOI18N
        btnVender.setText("VENDER");
        btnVender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVenderActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlContenidoLayout = new javax.swing.GroupLayout(pnlContenido);
        pnlContenido.setLayout(pnlContenidoLayout);
        pnlContenidoLayout.setHorizontalGroup(
            pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlEncabezado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlContenidoLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlDatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlPaquetes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlResumen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlContenidoLayout.createSequentialGroup()
                        .addComponent(btnCancelarPlan, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnVender, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );
        pnlContenidoLayout.setVerticalGroup(
            pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContenidoLayout.createSequentialGroup()
                .addComponent(pnlEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(pnlDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(pnlPaquetes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(pnlResumen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(pnlTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelarPlan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVender, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlContenido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlContenido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarPlanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarPlanActionPerformed
        if (!controladorAlertas.option("¿Cancelar el plan mensual? Los documentos ya pagados se conservan hasta el próximo pago mensual.")) {
            return;
        }
        try {
            sqlIngresos().cancelarPlanMensual(idCliente);
            ventaRealizada = true;
            controladorAlertas.alertSuccess("El plan mensual se cancelará en el próximo pago mensual");
            dispose();
        } catch (IllegalStateException ex) {
            controladorAlertas.alertFail(ex.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(DialogoVentaPaquete.class.getName()).log(Level.SEVERE, "Error al cancelar el plan mensual", ex);
            controladorAlertas.alertFail("Error al cancelar el plan mensual");
        }
    }//GEN-LAST:event_btnCancelarPlanActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnVenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVenderActionPerformed
        if (ventaPrevista == null || !ventaPrevista.isPermitida()) {
            return;
        }
        modeloPlanFacturacion plan = ventaPrevista.getPlan();
        String pregunta = ventaPrevista.getValor().signum() > 0
                ? "¿Vender " + plan.getNombre() + " por " + big.setMoneda(ventaPrevista.getValor()) + "?"
                : "¿Programar el plan " + plan.getNombre() + "?";
        if (!controladorAlertas.option(pregunta)) {
            return;
        }
        try {
            String resultado = sqlIngresos().venderPaquete(idCliente, plan.getIdPlan());
            ventaRealizada = true;
            controladorAlertas.alertSuccess(resultado);
            dispose();
        } catch (IllegalStateException ex) {
            controladorAlertas.alertFail(ex.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(DialogoVentaPaquete.class.getName()).log(Level.SEVERE, "Error al vender el paquete", ex);
            controladorAlertas.alertFail("Error al registrar el paquete");
        }
    }//GEN-LAST:event_btnVenderActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelarPlan;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnVender;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbDescripcion;
    private javax.swing.JLabel lbEtiquetaMensuales;
    private javax.swing.JLabel lbEtiquetaPlan;
    private javax.swing.JLabel lbEtiquetaSinVencimiento;
    private javax.swing.JLabel lbEtiquetaTotal;
    private javax.swing.JLabel lbMensuales;
    private javax.swing.JLabel lbPlan;
    private javax.swing.JLabel lbSinVencimiento;
    private javax.swing.JLabel lbSubtitulo;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JLabel lbTotal;
    private javax.swing.JPanel pnlContenido;
    private javax.swing.JPanel pnlDatos;
    private javax.swing.JPanel pnlEncabezado;
    private javax.swing.JPanel pnlPaquetes;
    private javax.swing.JPanel pnlResumen;
    private javax.swing.JPanel pnlTotal;
    private javax.swing.JTable tblPlanes;
    // End of variables declaration//GEN-END:variables
}
