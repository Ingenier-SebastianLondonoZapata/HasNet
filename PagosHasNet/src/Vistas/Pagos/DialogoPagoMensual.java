package Vistas.Pagos;

import Controlador.Alertas.controladorAlertas;
import Modelos.Ingresos.modeloEstadoCliente;
import Modelos.Ingresos.modeloPlanFacturacion;
import clases.Instancias;
import clases.SQLIngresos;
import clases.big;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SpinnerNumberModel;

public class DialogoPagoMensual extends javax.swing.JDialog {

    private final String idCliente;
    private final int idPago;
    private final String fechaLimite;

    private modeloEstadoCliente estado;
    private modeloPlanFacturacion planSiguiente;
    private BigDecimal tarifaServidor = BigDecimal.ZERO;
    private BigDecimal tarifaTerminal = BigDecimal.ZERO;
    private BigDecimal totalCalculado = BigDecimal.ZERO;
    private int mesesRestantes = 1;
    private boolean pagoRegistrado;
    private boolean listo;

    public DialogoPagoMensual(java.awt.Frame padre, String idCliente, int idPago, String fechaLimite) {
        super(padre, true);
        this.idCliente = idCliente;
        this.idPago = idPago;
        this.fechaLimite = fechaLimite;
        initComponents();
        if (cargarDatos()) {
            mostrarDatos();
            listo = true;
            actualizarTotales();
            setLocationRelativeTo(padre);
        }
    }

    public boolean isPagoRegistrado() {
        return pagoRegistrado;
    }

    public boolean isListo() {
        return estado != null;
    }

    private SQLIngresos sqlIngresos() {
        return Instancias.getInstancias().getSqlIngresos();
    }

    private boolean cargarDatos() {
        try {
            estado = sqlIngresos().obtenerEstadoCliente(idCliente);
            tarifaServidor = sqlIngresos().obtenerTarifa(SQLIngresos.MENSUALIDAD_SERVIDOR);
            tarifaTerminal = sqlIngresos().obtenerTarifa(SQLIngresos.MENSUALIDAD_TERMINAL);
            Integer idPlan = estado.planParaSiguientePeriodo();
            planSiguiente = idPlan == null ? null : sqlIngresos().obtenerPlan(idPlan);
            if (planSiguiente != null && !planSiguiente.isMensual()) {
                planSiguiente = null;
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DialogoPagoMensual.class.getName()).log(Level.SEVERE, "Error al cargar el estado del cliente", ex);
            controladorAlertas.alertFail("No se pudo consultar la información del cliente");
        } catch (IllegalStateException ex) {
            controladorAlertas.alertFail(ex.getMessage());
        }
        estado = null;
        return false;
    }

    private void mostrarDatos() {
        lbTitulo.setText(estado.getNombre());
        lbSubtitulo.setText("NIT " + estado.getNit());
        lbFecha.setText(fechaLimite);
        lbTerminales.setText(estado.getNumeroTerminales() + " contratadas  ·  " + estado.getTerminalesPagadas() + " pagadas");
        lbPlan.setText(descripcionPlan());

        spnTerminales.setModel(new SpinnerNumberModel(estado.getNumeroTerminales(), 0, estado.getNumeroTerminales(), 1));
        boolean tieneTerminales = estado.getNumeroTerminales() > 0;
        lbTerminalesPagar.setVisible(tieneTerminales);
        spnTerminales.setVisible(tieneTerminales);
        lbValorTerminales.setVisible(tieneTerminales);

        boolean tienePlan = planSiguiente != null;
        chkPlan.setVisible(tienePlan);
        lbValorPlan.setVisible(tienePlan);
        if (tienePlan) {
            chkPlan.setText("Plan " + planSiguiente.getNombre() + " (" + planSiguiente.getNumeroDocumentos() + " documentos por mes, no acumulables)");
            chkPlan.setSelected(true);
        }

        spnMeses.setModel(new SpinnerNumberModel(1, 1, SQLIngresos.MAXIMO_MESES_ADELANTADOS, 1));
        mesesRestantes = SQLIngresos.mesesRestantesCobertura(fechaLimiteComoFecha(), SQLIngresos.hoy());

        int pendientes = estado.getTerminalesPendientes();
        spnPendientes.setModel(new SpinnerNumberModel(pendientes, Math.min(1, pendientes), pendientes, 1));
        lbPendientes.setText("Terminales a pagar (" + pendientes + " sin pagar)");
        pnlTipoPago.setVisible(pendientes > 0);
        rdMensualidad.setSelected(true);
    }

    private String descripcionPlan() {
        String actual = "Ninguno";
        try {
            if (estado.getIdPlanMensual() != null) {
                modeloPlanFacturacion plan = sqlIngresos().obtenerPlan(estado.getIdPlanMensual());
                actual = plan == null ? "Ninguno" : plan.getNombre();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DialogoPagoMensual.class.getName()).log(Level.WARNING, "No se pudo consultar el plan actual", ex);
        }
        if (estado.isCancelacionPlanProgramada()) {
            return actual + " (se cancela en este pago)";
        }
        if (estado.getIdPlanMensualSiguiente() != null && planSiguiente != null) {
            return actual + " → " + planSiguiente.getNombre();
        }
        return actual;
    }

    private String calcularNuevaFechaLimite(int meses) {
        try {
            return sqlIngresos().calcularInicioPeriodo(fechaLimite, estado.getDiasGabela(), SQLIngresos.hoy())
                    .plusMonths(meses).format(SQLIngresos.FORMATO_FECHA);
        } catch (RuntimeException ex) {
            return "";
        }
    }

    private java.time.LocalDate fechaLimiteComoFecha() {
        try {
            return java.time.LocalDate.parse(fechaLimite, SQLIngresos.FORMATO_FECHA);
        } catch (RuntimeException ex) {
            return null;
        }
    }

    private void actualizarTotales() {
        if (!listo) {
            return;
        }
        boolean mensualidad = rdMensualidad.isSelected();
        boolean cambioSeccion = pnlMensualidad.isVisible() != mensualidad;
        pnlMensualidad.setVisible(mensualidad);
        pnlPendientes.setVisible(!mensualidad);

        int meses = (Integer) spnMeses.getValue();
        int terminales = (Integer) spnTerminales.getValue();
        int pendientes = (Integer) spnPendientes.getValue();
        BigDecimal valorTerminales = tarifaTerminal.multiply(BigDecimal.valueOf(terminales));
        BigDecimal valorPendientes = tarifaTerminal.multiply(BigDecimal.valueOf(pendientes)).multiply(BigDecimal.valueOf(mesesRestantes));
        BigDecimal valorPlan = chkPlan.isSelected() && planSiguiente != null ? planSiguiente.getPrecio() : BigDecimal.ZERO;
        BigDecimal valorMes = tarifaServidor.add(valorTerminales).add(valorPlan);

        lbValorServidor.setText(big.setMoneda(tarifaServidor));
        lbValorTerminales.setText(terminales + " x " + big.setMoneda(tarifaTerminal) + " = " + big.setMoneda(valorTerminales));
        lbValorPendientes.setText(pendientes + " x " + big.setMoneda(tarifaTerminal)
                + (mesesRestantes == 1 ? "" : " x " + mesesRestantes + " meses") + " = " + big.setMoneda(valorPendientes));
        if (planSiguiente != null) {
            lbValorPlan.setText(big.setMoneda(valorPlan));
        }
        lbValorMes.setText("Valor por mes: " + big.setMoneda(valorMes));
        lbNuevaFecha.setText("Nueva fecha límite: " + calcularNuevaFechaLimite(meses));

        totalCalculado = mensualidad ? valorMes.multiply(BigDecimal.valueOf(meses)) : valorPendientes;
        lbEtiquetaTotal.setText(mensualidad && meses > 1 ? "TOTAL A PAGAR (" + meses + " MESES)" : "TOTAL A PAGAR");
        lbTotal.setText(big.setMoneda(totalCalculado));
        btnPagar.setEnabled(mensualidad || pendientes > 0);

        if (cambioSeccion || !isVisible()) {
            pack();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grpTipoPago = new javax.swing.ButtonGroup();
        pnlContenido = new javax.swing.JPanel();
        pnlEncabezado = new javax.swing.JPanel();
        lbTitulo = new javax.swing.JLabel();
        lbSubtitulo = new javax.swing.JLabel();
        pnlDatos = new javax.swing.JPanel();
        lbEtiquetaFecha = new javax.swing.JLabel();
        lbFecha = new javax.swing.JLabel();
        lbEtiquetaTerminales = new javax.swing.JLabel();
        lbTerminales = new javax.swing.JLabel();
        lbEtiquetaPlan = new javax.swing.JLabel();
        lbPlan = new javax.swing.JLabel();
        pnlTipoPago = new javax.swing.JPanel();
        rdMensualidad = new javax.swing.JRadioButton();
        rdPendientes = new javax.swing.JRadioButton();
        pnlMensualidad = new javax.swing.JPanel();
        lbMeses = new javax.swing.JLabel();
        spnMeses = new javax.swing.JSpinner();
        lbValorMes = new javax.swing.JLabel();
        sepMeses = new javax.swing.JSeparator();
        lbServidor = new javax.swing.JLabel();
        lbValorServidor = new javax.swing.JLabel();
        lbTerminalesPagar = new javax.swing.JLabel();
        spnTerminales = new javax.swing.JSpinner();
        lbValorTerminales = new javax.swing.JLabel();
        chkPlan = new javax.swing.JCheckBox();
        lbValorPlan = new javax.swing.JLabel();
        lbNuevaFecha = new javax.swing.JLabel();
        pnlPendientes = new javax.swing.JPanel();
        lbPendientes = new javax.swing.JLabel();
        spnPendientes = new javax.swing.JSpinner();
        lbValorPendientes = new javax.swing.JLabel();
        pnlTotal = new javax.swing.JPanel();
        lbEtiquetaTotal = new javax.swing.JLabel();
        lbTotal = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        btnPagar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registrar pago");
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

        lbEtiquetaFecha.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lbEtiquetaFecha.setForeground(new java.awt.Color(127, 140, 141));
        lbEtiquetaFecha.setText("FECHA LÍMITE");

        lbFecha.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbFecha.setText("-");

        lbEtiquetaTerminales.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lbEtiquetaTerminales.setForeground(new java.awt.Color(127, 140, 141));
        lbEtiquetaTerminales.setText("TERMINALES ADICIONALES");

        lbTerminales.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbTerminales.setText("-");

        lbEtiquetaPlan.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lbEtiquetaPlan.setForeground(new java.awt.Color(127, 140, 141));
        lbEtiquetaPlan.setText("PLAN MENSUAL DE DOCUMENTOS");

        lbPlan.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbPlan.setText("-");

        javax.swing.GroupLayout pnlDatosLayout = new javax.swing.GroupLayout(pnlDatos);
        pnlDatos.setLayout(pnlDatosLayout);
        pnlDatosLayout.setHorizontalGroup(
            pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosLayout.createSequentialGroup()
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbEtiquetaFecha)
                    .addComponent(lbFecha))
                .addGap(40, 40, 40)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbEtiquetaTerminales)
                    .addComponent(lbTerminales))
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
                        .addComponent(lbEtiquetaFecha)
                        .addGap(2, 2, 2)
                        .addComponent(lbFecha))
                    .addGroup(pnlDatosLayout.createSequentialGroup()
                        .addComponent(lbEtiquetaTerminales)
                        .addGap(2, 2, 2)
                        .addComponent(lbTerminales))
                    .addGroup(pnlDatosLayout.createSequentialGroup()
                        .addComponent(lbEtiquetaPlan)
                        .addGap(2, 2, 2)
                        .addComponent(lbPlan))))
        );

        pnlTipoPago.setBackground(new java.awt.Color(255, 255, 255));
        pnlTipoPago.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "¿Qué desea pagar?", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 14))); // NOI18N

        rdMensualidad.setBackground(new java.awt.Color(255, 255, 255));
        grpTipoPago.add(rdMensualidad);
        rdMensualidad.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        rdMensualidad.setSelected(true);
        rdMensualidad.setText("Mensualidad");
        rdMensualidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdMensualidadActionPerformed(evt);
            }
        });

        rdPendientes.setBackground(new java.awt.Color(255, 255, 255));
        grpTipoPago.add(rdPendientes);
        rdPendientes.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        rdPendientes.setText("Terminales pendientes del periodo actual");
        rdPendientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdPendientesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlTipoPagoLayout = new javax.swing.GroupLayout(pnlTipoPago);
        pnlTipoPago.setLayout(pnlTipoPagoLayout);
        pnlTipoPagoLayout.setHorizontalGroup(
            pnlTipoPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTipoPagoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rdMensualidad)
                .addGap(30, 30, 30)
                .addComponent(rdPendientes)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlTipoPagoLayout.setVerticalGroup(
            pnlTipoPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTipoPagoLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(pnlTipoPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdMensualidad)
                    .addComponent(rdPendientes))
                .addGap(6, 6, 6))
        );

        pnlMensualidad.setBackground(new java.awt.Color(255, 255, 255));
        pnlMensualidad.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalle de la mensualidad", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 14))); // NOI18N

        lbMeses.setFont(new java.awt.Font("Century Gothic", 1, 15)); // NOI18N
        lbMeses.setText("Meses a pagar");

        spnMeses.setFont(new java.awt.Font("Century Gothic", 1, 15)); // NOI18N
        spnMeses.setModel(new javax.swing.SpinnerNumberModel(1, 1, 12, 1));
        spnMeses.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnMesesStateChanged(evt);
            }
        });

        lbValorMes.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        lbValorMes.setForeground(new java.awt.Color(127, 140, 141));
        lbValorMes.setText("Valor por mes: $ 0");

        lbServidor.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        lbServidor.setText("Servidor (incluye una terminal)");

        lbValorServidor.setFont(new java.awt.Font("Century Gothic", 1, 15)); // NOI18N
        lbValorServidor.setText("$ 0");

        lbTerminalesPagar.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        lbTerminalesPagar.setText("Terminales adicionales a pagar");

        spnTerminales.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        spnTerminales.setModel(new javax.swing.SpinnerNumberModel(0, 0, 0, 1));
        spnTerminales.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnTerminalesStateChanged(evt);
            }
        });

        lbValorTerminales.setFont(new java.awt.Font("Century Gothic", 1, 15)); // NOI18N
        lbValorTerminales.setText("$ 0");

        chkPlan.setBackground(new java.awt.Color(255, 255, 255));
        chkPlan.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        chkPlan.setText("Plan mensual de documentos");
        chkPlan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkPlanActionPerformed(evt);
            }
        });

        lbValorPlan.setFont(new java.awt.Font("Century Gothic", 1, 15)); // NOI18N
        lbValorPlan.setText("$ 0");

        lbNuevaFecha.setFont(new java.awt.Font("Century Gothic", 2, 13)); // NOI18N
        lbNuevaFecha.setForeground(new java.awt.Color(127, 140, 141));
        lbNuevaFecha.setText("Nueva fecha límite:");

        javax.swing.GroupLayout pnlMensualidadLayout = new javax.swing.GroupLayout(pnlMensualidad);
        pnlMensualidad.setLayout(pnlMensualidadLayout);
        pnlMensualidadLayout.setHorizontalGroup(
            pnlMensualidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMensualidadLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMensualidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMensualidadLayout.createSequentialGroup()
                        .addComponent(lbMeses)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spnMeses, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, Short.MAX_VALUE)
                        .addComponent(lbValorMes))
                    .addComponent(sepMeses)
                    .addGroup(pnlMensualidadLayout.createSequentialGroup()
                        .addComponent(lbServidor)
                        .addGap(20, 20, Short.MAX_VALUE)
                        .addComponent(lbValorServidor))
                    .addGroup(pnlMensualidadLayout.createSequentialGroup()
                        .addComponent(lbTerminalesPagar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spnTerminales, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, Short.MAX_VALUE)
                        .addComponent(lbValorTerminales))
                    .addGroup(pnlMensualidadLayout.createSequentialGroup()
                        .addComponent(chkPlan)
                        .addGap(20, 20, Short.MAX_VALUE)
                        .addComponent(lbValorPlan))
                    .addComponent(lbNuevaFecha))
                .addContainerGap())
        );
        pnlMensualidadLayout.setVerticalGroup(
            pnlMensualidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMensualidadLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(pnlMensualidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbMeses)
                    .addComponent(spnMeses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbValorMes))
                .addGap(6, 6, 6)
                .addComponent(sepMeses, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnlMensualidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbServidor)
                    .addComponent(lbValorServidor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMensualidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTerminalesPagar)
                    .addComponent(spnTerminales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbValorTerminales))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMensualidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkPlan)
                    .addComponent(lbValorPlan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbNuevaFecha)
                .addGap(8, 8, 8))
        );

        pnlPendientes.setBackground(new java.awt.Color(255, 255, 255));
        pnlPendientes.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Terminales pendientes del periodo actual", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 14))); // NOI18N

        lbPendientes.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        lbPendientes.setText("Terminales a pagar");

        spnPendientes.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        spnPendientes.setModel(new javax.swing.SpinnerNumberModel(0, 0, 0, 1));
        spnPendientes.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnPendientesStateChanged(evt);
            }
        });

        lbValorPendientes.setFont(new java.awt.Font("Century Gothic", 1, 15)); // NOI18N
        lbValorPendientes.setText("$ 0");

        javax.swing.GroupLayout pnlPendientesLayout = new javax.swing.GroupLayout(pnlPendientes);
        pnlPendientes.setLayout(pnlPendientesLayout);
        pnlPendientesLayout.setHorizontalGroup(
            pnlPendientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPendientesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbPendientes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spnPendientes, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, Short.MAX_VALUE)
                .addComponent(lbValorPendientes)
                .addContainerGap())
        );
        pnlPendientesLayout.setVerticalGroup(
            pnlPendientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPendientesLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(pnlPendientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbPendientes)
                    .addComponent(spnPendientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbValorPendientes))
                .addGap(8, 8, 8))
        );

        pnlTotal.setBackground(new java.awt.Color(232, 248, 239));

        lbEtiquetaTotal.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbEtiquetaTotal.setText("TOTAL A PAGAR");

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

        btnCancelar.setBackground(new java.awt.Color(255, 204, 204));
        btnCancelar.setFont(new java.awt.Font("Century Gothic", 1, 15)); // NOI18N
        btnCancelar.setText("CANCELAR");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnPagar.setBackground(new java.awt.Color(46, 204, 113));
        btnPagar.setFont(new java.awt.Font("Century Gothic", 1, 15)); // NOI18N
        btnPagar.setText("REGISTRAR PAGO");
        btnPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagarActionPerformed(evt);
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
                    .addComponent(pnlTipoPago, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlMensualidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlPendientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlContenidoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );
        pnlContenidoLayout.setVerticalGroup(
            pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContenidoLayout.createSequentialGroup()
                .addComponent(pnlEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(pnlDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(pnlTipoPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(pnlMensualidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(pnlPendientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(pnlTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void rdMensualidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdMensualidadActionPerformed
        actualizarTotales();
    }//GEN-LAST:event_rdMensualidadActionPerformed

    private void rdPendientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdPendientesActionPerformed
        actualizarTotales();
    }//GEN-LAST:event_rdPendientesActionPerformed

    private void chkPlanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkPlanActionPerformed
        actualizarTotales();
    }//GEN-LAST:event_chkPlanActionPerformed

    private void spnTerminalesStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnTerminalesStateChanged
        actualizarTotales();
    }//GEN-LAST:event_spnTerminalesStateChanged

    private void spnPendientesStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnPendientesStateChanged
        actualizarTotales();
    }//GEN-LAST:event_spnPendientesStateChanged

    private void spnMesesStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnMesesStateChanged
        actualizarTotales();
    }//GEN-LAST:event_spnMesesStateChanged

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagarActionPerformed
        if (!controladorAlertas.option("¿Confirma el pago por " + big.setMoneda(totalCalculado) + "?")) {
            return;
        }
        try {
            BigDecimal valor;
            if (rdMensualidad.isSelected()) {
                valor = sqlIngresos().registrarPagoMensual(idCliente, idPago, (Integer) spnTerminales.getValue(), chkPlan.isSelected(),
                        (Integer) spnMeses.getValue());
            } else {
                valor = sqlIngresos().registrarPagoTerminalesPendientes(idCliente, (Integer) spnPendientes.getValue());
            }
            pagoRegistrado = true;
            controladorAlertas.alertSuccess("Pago registrado con éxito por " + big.setMoneda(valor));
            dispose();
        } catch (IllegalStateException ex) {
            controladorAlertas.alertFail(ex.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(DialogoPagoMensual.class.getName()).log(Level.SEVERE, "Error al registrar el pago", ex);
            controladorAlertas.alertFail("Error al registrar el pago");
        }
    }//GEN-LAST:event_btnPagarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnPagar;
    private javax.swing.JCheckBox chkPlan;
    private javax.swing.ButtonGroup grpTipoPago;
    private javax.swing.JLabel lbEtiquetaFecha;
    private javax.swing.JLabel lbEtiquetaPlan;
    private javax.swing.JLabel lbEtiquetaTerminales;
    private javax.swing.JLabel lbEtiquetaTotal;
    private javax.swing.JLabel lbFecha;
    private javax.swing.JLabel lbMeses;
    private javax.swing.JLabel lbValorMes;
    private javax.swing.JSeparator sepMeses;
    private javax.swing.JSpinner spnMeses;
    private javax.swing.JLabel lbNuevaFecha;
    private javax.swing.JLabel lbPendientes;
    private javax.swing.JLabel lbPlan;
    private javax.swing.JLabel lbServidor;
    private javax.swing.JLabel lbSubtitulo;
    private javax.swing.JLabel lbTerminales;
    private javax.swing.JLabel lbTerminalesPagar;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JLabel lbTotal;
    private javax.swing.JLabel lbValorPendientes;
    private javax.swing.JLabel lbValorPlan;
    private javax.swing.JLabel lbValorServidor;
    private javax.swing.JLabel lbValorTerminales;
    private javax.swing.JPanel pnlContenido;
    private javax.swing.JPanel pnlDatos;
    private javax.swing.JPanel pnlEncabezado;
    private javax.swing.JPanel pnlMensualidad;
    private javax.swing.JPanel pnlPendientes;
    private javax.swing.JPanel pnlTipoPago;
    private javax.swing.JPanel pnlTotal;
    private javax.swing.JRadioButton rdMensualidad;
    private javax.swing.JRadioButton rdPendientes;
    private javax.swing.JSpinner spnPendientes;
    private javax.swing.JSpinner spnTerminales;
    // End of variables declaration//GEN-END:variables
}
