package Vistas.Ingresos;

import Controlador.Alertas.controladorAlertas;
import Vistas.Tarifas.PanelTarifasPlanes;
import clases.Instancias;
import clases.SQLIngresos;
import clases.big;
import java.awt.Color;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.table.DefaultTableModel;

public class PanelIngresos extends javax.swing.JPanel {

    private static final DateTimeFormatter FORMATO_FECHA_HORA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private static final Color COLOR_POSITIVO = new Color(39, 174, 96);
    private static final Color COLOR_NEGATIVO = new Color(192, 57, 43);
    private static final Color COLOR_NEUTRO = new Color(44, 62, 80);
    private static final Color FONDO_REPARTIDO = new Color(232, 248, 239);
    private static final Color FONDO_PENDIENTE = new Color(253, 242, 233);

    private final List<Integer> idGastos = new ArrayList<>();
    private boolean inicializando = true;

    public PanelIngresos() {
        initComponents();
        spnAnio.setEditor(new JSpinner.NumberEditor(spnAnio, "0"));
        java.awt.Font fuenteEncabezado = new java.awt.Font("Century Gothic", java.awt.Font.BOLD, 14);
        tblMovimientos.getTableHeader().setFont(fuenteEncabezado);
        tblGastos.getTableHeader().setFont(fuenteEncabezado);
        tblGastos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        seleccionarPeriodo(YearMonth.now());
        inicializando = false;
    }

    private SQLIngresos sqlIngresos() {
        return Instancias.getInstancias().getSqlIngresos();
    }

    private YearMonth periodo() {
        return YearMonth.of((Integer) spnAnio.getValue(), cmbMes.getSelectedIndex() + 1);
    }

    private void seleccionarPeriodo(YearMonth periodo) {
        boolean estabaInicializando = inicializando;
        inicializando = true;
        cmbMes.setSelectedIndex(periodo.getMonthValue() - 1);
        spnAnio.setValue(periodo.getYear());
        inicializando = estabaInicializando;
        actualizarTituloPeriodo();
    }

    private void actualizarTituloPeriodo() {
        lbPeriodo.setText(cmbMes.getSelectedItem().toString().toUpperCase() + " " + spnAnio.getValue());
    }

    private void moverPeriodo(int meses) {
        YearMonth nuevo = periodo().plusMonths(meses);
        javax.swing.SpinnerNumberModel modelo = (javax.swing.SpinnerNumberModel) spnAnio.getModel();
        if (nuevo.getYear() < (Integer) modelo.getMinimum() || nuevo.getYear() > (Integer) modelo.getMaximum()) {
            return;
        }
        seleccionarPeriodo(nuevo);
        recargarSiCorresponde();
    }

    public void recargar() {
        YearMonth periodo = periodo();
        actualizarTituloPeriodo();
        try {
            BigDecimal ingresos = BigDecimal.ZERO;
            DefaultTableModel modeloMovimientos = (DefaultTableModel) tblMovimientos.getModel();
            modeloMovimientos.setRowCount(0);
            for (Object[] movimiento : sqlIngresos().movimientosDelPeriodo(periodo)) {
                BigDecimal valor = (BigDecimal) movimiento[6];
                ingresos = ingresos.add(valor);
                String cliente = movimiento[2] == null ? "" : movimiento[2].toString();
                String cantidad = ((Integer) movimiento[5]) > 1 ? " (x" + movimiento[5] + ")" : "";
                modeloMovimientos.addRow(new Object[]{((LocalDate) movimiento[0]).format(SQLIngresos.FORMATO_FECHA), cliente,
                    SQLIngresos.nombreTipoMovimiento((String) movimiento[3]), movimiento[4] + cantidad, big.setMoneda(valor)});
            }

            BigDecimal gastos = BigDecimal.ZERO;
            DefaultTableModel modeloGastos = (DefaultTableModel) tblGastos.getModel();
            modeloGastos.setRowCount(0);
            idGastos.clear();
            for (Object[] gasto : sqlIngresos().gastosDelPeriodo(periodo)) {
                BigDecimal valor = (BigDecimal) gasto[3];
                gastos = gastos.add(valor);
                idGastos.add((Integer) gasto[0]);
                modeloGastos.addRow(new Object[]{gasto[0], ((LocalDate) gasto[1]).format(SQLIngresos.FORMATO_FECHA), gasto[2], big.setMoneda(valor)});
            }

            BigDecimal utilidad = ingresos.subtract(gastos);
            lbIngresos.setText(big.setMoneda(ingresos));
            lbDetalleIngresos.setText(modeloMovimientos.getRowCount() + (modeloMovimientos.getRowCount() == 1 ? " movimiento" : " movimientos"));
            lbGastos.setText(big.setMoneda(gastos));
            lbDetalleGastos.setText(modeloGastos.getRowCount() + (modeloGastos.getRowCount() == 1 ? " gasto" : " gastos"));
            lbUtilidad.setText(big.setMoneda(utilidad));
            lbUtilidad.setForeground(utilidad.signum() < 0 ? COLOR_NEGATIVO : COLOR_NEUTRO);
            lbDetalleUtilidad.setText(ingresos.signum() == 0 ? "Sin ingresos en el mes"
                    : "Margen " + utilidad.multiply(BigDecimal.valueOf(100)).divide(ingresos, 0, RoundingMode.HALF_UP) + "%");

            Object[] cierre = sqlIngresos().obtenerCierre(periodo);
            boolean repartido = cierre != null;
            if (repartido) {
                String detalle = "El " + ((LocalDateTime) cierre[0]).format(FORMATO_FECHA_HORA) + ": " + big.setMoneda((BigDecimal) cierre[4]);
                lbEstado.setText("REPARTIDO");
                lbEstado.setForeground(COLOR_POSITIVO);
                lbDetalleReparto.setText(detalle);
                pnlTarjetaReparto.setBackground(FONDO_REPARTIDO);
                lbDetalleReparto.setToolTipText(cierre[1] == null || cierre[1].toString().isEmpty() ? detalle : detalle + " - " + cierre[1]);
            } else {
                lbEstado.setText("PENDIENTE");
                lbEstado.setForeground(COLOR_NEGATIVO);
                lbDetalleReparto.setText(periodo.equals(YearMonth.now()) ? "El mes está en curso" : "Aún no se ha repartido");
                pnlTarjetaReparto.setBackground(FONDO_PENDIENTE);
                lbDetalleReparto.setToolTipText(null);
            }
            btnRepartir.setVisible(!repartido);
            btnAnularReparto.setVisible(repartido);
            txtConceptoGasto.setEnabled(!repartido);
            txtValorGasto.setEnabled(!repartido);
            btnAgregarGasto.setEnabled(!repartido);
            btnEliminarGasto.setEnabled(!repartido);
            lbAyudaReparto.setText(repartido
                    ? "Este mes ya fue repartido: sus gastos quedan bloqueados. Anule el reparto si necesita corregirlos."
                    : "Al marcar el mes como repartido se guardan los totales y ya no se pueden modificar sus gastos.");
        } catch (SQLException ex) {
            Logger.getLogger(PanelIngresos.class.getName()).log(Level.SEVERE, "Error al cargar los ingresos", ex);
            controladorAlertas.alertFail("No se pudieron cargar los ingresos. Verifique que los scripts de la base de pagos estén aplicados.");
        }
    }

    private void recargarSiCorresponde() {
        if (!inicializando && !java.beans.Beans.isDesignTime()) {
            recargar();
        }
    }

    private void agregarGasto() {
        try {
            sqlIngresos().agregarGasto(periodo(), txtConceptoGasto.getText(), PanelTarifasPlanes.leerValor(txtValorGasto.getText()));
            txtConceptoGasto.setText("");
            txtValorGasto.setText("");
            txtConceptoGasto.requestFocusInWindow();
            recargar();
        } catch (IllegalStateException ex) {
            controladorAlertas.alertFail(ex.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(PanelIngresos.class.getName()).log(Level.SEVERE, "Error al agregar el gasto", ex);
            controladorAlertas.alertFail("Error al agregar el gasto");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlEncabezado = new javax.swing.JPanel();
        btnMesAnterior = new javax.swing.JButton();
        lbPeriodo = new javax.swing.JLabel();
        btnMesSiguiente = new javax.swing.JButton();
        lbMes = new javax.swing.JLabel();
        cmbMes = new javax.swing.JComboBox<>();
        lbAnio = new javax.swing.JLabel();
        spnAnio = new javax.swing.JSpinner();
        pnlTarjetaIngresos = new javax.swing.JPanel();
        lbTituloIngresos = new javax.swing.JLabel();
        lbIngresos = new javax.swing.JLabel();
        lbDetalleIngresos = new javax.swing.JLabel();
        pnlTarjetaGastos = new javax.swing.JPanel();
        lbTituloGastos = new javax.swing.JLabel();
        lbGastos = new javax.swing.JLabel();
        lbDetalleGastos = new javax.swing.JLabel();
        pnlTarjetaUtilidad = new javax.swing.JPanel();
        lbTituloUtilidad = new javax.swing.JLabel();
        lbUtilidad = new javax.swing.JLabel();
        lbDetalleUtilidad = new javax.swing.JLabel();
        pnlTarjetaReparto = new javax.swing.JPanel();
        lbTituloReparto = new javax.swing.JLabel();
        lbEstado = new javax.swing.JLabel();
        lbDetalleReparto = new javax.swing.JLabel();
        pnlMovimientos = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMovimientos = new javax.swing.JTable();
        pnlGastos = new javax.swing.JPanel();
        lbConceptoGasto = new javax.swing.JLabel();
        txtConceptoGasto = new javax.swing.JTextField();
        lbValorGasto = new javax.swing.JLabel();
        txtValorGasto = new javax.swing.JTextField();
        btnAgregarGasto = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblGastos = new javax.swing.JTable();
        btnEliminarGasto = new javax.swing.JButton();
        lbAyudaReparto = new javax.swing.JLabel();
        btnAnularReparto = new javax.swing.JButton();
        btnRepartir = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        pnlEncabezado.setBackground(new java.awt.Color(44, 62, 80));

        btnMesAnterior.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        btnMesAnterior.setText("<");
        btnMesAnterior.setToolTipText("Mes anterior");
        btnMesAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesAnteriorActionPerformed(evt);
            }
        });

        lbPeriodo.setFont(new java.awt.Font("Century Gothic", 1, 22)); // NOI18N
        lbPeriodo.setForeground(new java.awt.Color(255, 255, 255));
        lbPeriodo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbPeriodo.setText("MES AÑO");

        btnMesSiguiente.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        btnMesSiguiente.setText(">");
        btnMesSiguiente.setToolTipText("Mes siguiente");
        btnMesSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesSiguienteActionPerformed(evt);
            }
        });

        lbMes.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbMes.setForeground(new java.awt.Color(236, 240, 241));
        lbMes.setText("Mes:");

        cmbMes.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        cmbMes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));
        cmbMes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbMesItemStateChanged(evt);
            }
        });

        lbAnio.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbAnio.setForeground(new java.awt.Color(236, 240, 241));
        lbAnio.setText("Año:");

        spnAnio.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        spnAnio.setModel(new javax.swing.SpinnerNumberModel(2026, 2020, 2100, 1));
        spnAnio.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnAnioStateChanged(evt);
            }
        });

        javax.swing.GroupLayout pnlEncabezadoLayout = new javax.swing.GroupLayout(pnlEncabezado);
        pnlEncabezado.setLayout(pnlEncabezadoLayout);
        pnlEncabezadoLayout.setHorizontalGroup(
            pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(btnMesAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(lbPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btnMesSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, Short.MAX_VALUE)
                .addComponent(lbMes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbMes, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbAnio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spnAnio, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        pnlEncabezadoLayout.setVerticalGroup(
            pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnMesAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbPeriodo)
                    .addComponent(btnMesSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbMes)
                    .addComponent(cmbMes, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbAnio)
                    .addComponent(spnAnio, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );

        pnlTarjetaIngresos.setBackground(new java.awt.Color(232, 248, 239));

        lbTituloIngresos.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        lbTituloIngresos.setForeground(new java.awt.Color(127, 140, 141));
        lbTituloIngresos.setText("INGRESOS DEL MES");

        lbIngresos.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        lbIngresos.setForeground(new java.awt.Color(39, 174, 96));
        lbIngresos.setText("$ 0");

        lbDetalleIngresos.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        lbDetalleIngresos.setForeground(new java.awt.Color(127, 140, 141));
        lbDetalleIngresos.setText("0 movimientos");

        javax.swing.GroupLayout pnlTarjetaIngresosLayout = new javax.swing.GroupLayout(pnlTarjetaIngresos);
        pnlTarjetaIngresos.setLayout(pnlTarjetaIngresosLayout);
        pnlTarjetaIngresosLayout.setHorizontalGroup(
            pnlTarjetaIngresosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTarjetaIngresosLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pnlTarjetaIngresosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTituloIngresos)
                    .addComponent(lbIngresos)
                    .addComponent(lbDetalleIngresos))
                .addGap(15, 15, Short.MAX_VALUE))
        );
        pnlTarjetaIngresosLayout.setVerticalGroup(
            pnlTarjetaIngresosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTarjetaIngresosLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(lbTituloIngresos)
                .addGap(4, 4, 4)
                .addComponent(lbIngresos)
                .addGap(2, 2, 2)
                .addComponent(lbDetalleIngresos)
                .addGap(12, 12, 12))
        );

        pnlTarjetaGastos.setBackground(new java.awt.Color(253, 237, 236));

        lbTituloGastos.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        lbTituloGastos.setForeground(new java.awt.Color(127, 140, 141));
        lbTituloGastos.setText("GASTOS DEL MES");

        lbGastos.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        lbGastos.setForeground(new java.awt.Color(192, 57, 43));
        lbGastos.setText("$ 0");

        lbDetalleGastos.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        lbDetalleGastos.setForeground(new java.awt.Color(127, 140, 141));
        lbDetalleGastos.setText("0 gastos");

        javax.swing.GroupLayout pnlTarjetaGastosLayout = new javax.swing.GroupLayout(pnlTarjetaGastos);
        pnlTarjetaGastos.setLayout(pnlTarjetaGastosLayout);
        pnlTarjetaGastosLayout.setHorizontalGroup(
            pnlTarjetaGastosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTarjetaGastosLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pnlTarjetaGastosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTituloGastos)
                    .addComponent(lbGastos)
                    .addComponent(lbDetalleGastos))
                .addGap(15, 15, Short.MAX_VALUE))
        );
        pnlTarjetaGastosLayout.setVerticalGroup(
            pnlTarjetaGastosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTarjetaGastosLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(lbTituloGastos)
                .addGap(4, 4, 4)
                .addComponent(lbGastos)
                .addGap(2, 2, 2)
                .addComponent(lbDetalleGastos)
                .addGap(12, 12, 12))
        );

        pnlTarjetaUtilidad.setBackground(new java.awt.Color(234, 242, 248));

        lbTituloUtilidad.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        lbTituloUtilidad.setForeground(new java.awt.Color(127, 140, 141));
        lbTituloUtilidad.setText("UTILIDAD");

        lbUtilidad.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        lbUtilidad.setForeground(new java.awt.Color(44, 62, 80));
        lbUtilidad.setText("$ 0");

        lbDetalleUtilidad.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        lbDetalleUtilidad.setForeground(new java.awt.Color(127, 140, 141));
        lbDetalleUtilidad.setText("Margen 0%");

        javax.swing.GroupLayout pnlTarjetaUtilidadLayout = new javax.swing.GroupLayout(pnlTarjetaUtilidad);
        pnlTarjetaUtilidad.setLayout(pnlTarjetaUtilidadLayout);
        pnlTarjetaUtilidadLayout.setHorizontalGroup(
            pnlTarjetaUtilidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTarjetaUtilidadLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pnlTarjetaUtilidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTituloUtilidad)
                    .addComponent(lbUtilidad)
                    .addComponent(lbDetalleUtilidad))
                .addGap(15, 15, Short.MAX_VALUE))
        );
        pnlTarjetaUtilidadLayout.setVerticalGroup(
            pnlTarjetaUtilidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTarjetaUtilidadLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(lbTituloUtilidad)
                .addGap(4, 4, 4)
                .addComponent(lbUtilidad)
                .addGap(2, 2, 2)
                .addComponent(lbDetalleUtilidad)
                .addGap(12, 12, 12))
        );

        pnlTarjetaReparto.setBackground(new java.awt.Color(244, 246, 246));

        lbTituloReparto.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        lbTituloReparto.setForeground(new java.awt.Color(127, 140, 141));
        lbTituloReparto.setText("REPARTO ENTRE COLABORADORES");

        lbEstado.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        lbEstado.setForeground(new java.awt.Color(192, 57, 43));
        lbEstado.setText("PENDIENTE");

        lbDetalleReparto.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        lbDetalleReparto.setForeground(new java.awt.Color(127, 140, 141));
        lbDetalleReparto.setText("Aún no se ha repartido");

        javax.swing.GroupLayout pnlTarjetaRepartoLayout = new javax.swing.GroupLayout(pnlTarjetaReparto);
        pnlTarjetaReparto.setLayout(pnlTarjetaRepartoLayout);
        pnlTarjetaRepartoLayout.setHorizontalGroup(
            pnlTarjetaRepartoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTarjetaRepartoLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pnlTarjetaRepartoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTituloReparto)
                    .addComponent(lbEstado)
                    .addComponent(lbDetalleReparto))
                .addGap(15, 15, Short.MAX_VALUE))
        );
        pnlTarjetaRepartoLayout.setVerticalGroup(
            pnlTarjetaRepartoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTarjetaRepartoLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(lbTituloReparto)
                .addGap(4, 4, 4)
                .addComponent(lbEstado)
                .addGap(2, 2, 2)
                .addComponent(lbDetalleReparto)
                .addGap(12, 12, 12))
        );

        pnlMovimientos.setBackground(new java.awt.Color(255, 255, 255));
        pnlMovimientos.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ingresos del mes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 14))); // NOI18N

        tblMovimientos.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        tblMovimientos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha", "Cliente", "Tipo", "Descripción", "Valor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblMovimientos.setRowHeight(26);
        tblMovimientos.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblMovimientos);
        if (tblMovimientos.getColumnModel().getColumnCount() > 0) {
            tblMovimientos.getColumnModel().getColumn(0).setMinWidth(90);
            tblMovimientos.getColumnModel().getColumn(0).setPreferredWidth(95);
            tblMovimientos.getColumnModel().getColumn(0).setMaxWidth(105);
            tblMovimientos.getColumnModel().getColumn(1).setPreferredWidth(180);
            tblMovimientos.getColumnModel().getColumn(2).setPreferredWidth(150);
            tblMovimientos.getColumnModel().getColumn(3).setPreferredWidth(300);
            tblMovimientos.getColumnModel().getColumn(4).setMinWidth(100);
            tblMovimientos.getColumnModel().getColumn(4).setPreferredWidth(120);
            tblMovimientos.getColumnModel().getColumn(4).setMaxWidth(140);
        }

        javax.swing.GroupLayout pnlMovimientosLayout = new javax.swing.GroupLayout(pnlMovimientos);
        pnlMovimientos.setLayout(pnlMovimientosLayout);
        pnlMovimientosLayout.setHorizontalGroup(
            pnlMovimientosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMovimientosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        pnlMovimientosLayout.setVerticalGroup(
            pnlMovimientosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMovimientosLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlGastos.setBackground(new java.awt.Color(255, 255, 255));
        pnlGastos.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Gastos del mes (servicios externos)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 14))); // NOI18N

        lbConceptoGasto.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbConceptoGasto.setText("Concepto:");

        txtConceptoGasto.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        lbValorGasto.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbValorGasto.setText("Valor:");

        txtValorGasto.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtValorGasto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtValorGasto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtValorGastoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtValorGastoKeyTyped(evt);
            }
        });

        btnAgregarGasto.setBackground(new java.awt.Color(46, 204, 113));
        btnAgregarGasto.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnAgregarGasto.setText("AGREGAR");
        btnAgregarGasto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarGastoActionPerformed(evt);
            }
        });

        tblGastos.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        tblGastos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Fecha", "Concepto", "Valor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblGastos.setRowHeight(26);
        tblGastos.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tblGastos);
        if (tblGastos.getColumnModel().getColumnCount() > 0) {
            tblGastos.getColumnModel().getColumn(0).setMinWidth(0);
            tblGastos.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblGastos.getColumnModel().getColumn(0).setMaxWidth(0);
            tblGastos.getColumnModel().getColumn(1).setMinWidth(90);
            tblGastos.getColumnModel().getColumn(1).setPreferredWidth(95);
            tblGastos.getColumnModel().getColumn(1).setMaxWidth(105);
            tblGastos.getColumnModel().getColumn(2).setPreferredWidth(220);
            tblGastos.getColumnModel().getColumn(3).setMinWidth(100);
            tblGastos.getColumnModel().getColumn(3).setPreferredWidth(120);
            tblGastos.getColumnModel().getColumn(3).setMaxWidth(140);
        }

        btnEliminarGasto.setBackground(new java.awt.Color(255, 204, 204));
        btnEliminarGasto.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnEliminarGasto.setText("ELIMINAR SELECCIONADO");
        btnEliminarGasto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarGastoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlGastosLayout = new javax.swing.GroupLayout(pnlGastos);
        pnlGastos.setLayout(pnlGastosLayout);
        pnlGastosLayout.setHorizontalGroup(
            pnlGastosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGastosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlGastosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlGastosLayout.createSequentialGroup()
                        .addComponent(lbConceptoGasto, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtConceptoGasto))
                    .addGroup(pnlGastosLayout.createSequentialGroup()
                        .addComponent(lbValorGasto, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtValorGasto, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, Short.MAX_VALUE)
                        .addComponent(btnAgregarGasto, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlGastosLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnEliminarGasto, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlGastosLayout.setVerticalGroup(
            pnlGastosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGastosLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(pnlGastosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbConceptoGasto)
                    .addComponent(txtConceptoGasto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlGastosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbValorGasto)
                    .addComponent(txtValorGasto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarGasto, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminarGasto, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        lbAyudaReparto.setFont(new java.awt.Font("Century Gothic", 2, 13)); // NOI18N
        lbAyudaReparto.setForeground(new java.awt.Color(127, 140, 141));
        lbAyudaReparto.setText("Al marcar el mes como repartido se guardan los totales y ya no se pueden modificar sus gastos.");

        btnAnularReparto.setBackground(new java.awt.Color(255, 204, 204));
        btnAnularReparto.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnAnularReparto.setText("ANULAR REPARTO");
        btnAnularReparto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnularRepartoActionPerformed(evt);
            }
        });

        btnRepartir.setBackground(new java.awt.Color(52, 152, 219));
        btnRepartir.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnRepartir.setForeground(new java.awt.Color(255, 255, 255));
        btnRepartir.setText("MARCAR COMO REPARTIDO");
        btnRepartir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRepartirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlEncabezado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlTarjetaIngresos, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                        .addGap(10, 10, 10)
                        .addComponent(pnlTarjetaGastos, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                        .addGap(10, 10, 10)
                        .addComponent(pnlTarjetaUtilidad, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                        .addGap(10, 10, 10)
                        .addComponent(pnlTarjetaReparto, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlMovimientos, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
                        .addGap(10, 10, 10)
                        .addComponent(pnlGastos, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbAyudaReparto)
                        .addGap(20, 20, Short.MAX_VALUE)
                        .addComponent(btnAnularReparto, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRepartir, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlTarjetaIngresos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlTarjetaGastos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlTarjetaUtilidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlTarjetaReparto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlMovimientos, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addComponent(pnlGastos, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbAyudaReparto)
                    .addComponent(btnAnularReparto, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRepartir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnMesAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesAnteriorActionPerformed
        moverPeriodo(-1);
    }//GEN-LAST:event_btnMesAnteriorActionPerformed

    private void btnMesSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesSiguienteActionPerformed
        moverPeriodo(1);
    }//GEN-LAST:event_btnMesSiguienteActionPerformed

    private void cmbMesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbMesItemStateChanged
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            recargarSiCorresponde();
        }
    }//GEN-LAST:event_cmbMesItemStateChanged

    private void spnAnioStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnAnioStateChanged
        recargarSiCorresponde();
    }//GEN-LAST:event_spnAnioStateChanged

    private void txtValorGastoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorGastoKeyTyped
        if (evt.getKeyChar() == java.awt.event.KeyEvent.VK_ENTER) {
            evt.consume();
            agregarGasto();
        } else if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_txtValorGastoKeyTyped

    private void txtValorGastoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorGastoKeyReleased
        String digitos = txtValorGasto.getText().replaceAll("[^0-9]", "");
        String formateado = digitos.isEmpty() ? "" : big.setMoneda(new BigDecimal(digitos));
        if (!formateado.equals(txtValorGasto.getText())) {
            txtValorGasto.setText(formateado);
        }
    }//GEN-LAST:event_txtValorGastoKeyReleased

    private void btnAgregarGastoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarGastoActionPerformed
        agregarGasto();
    }//GEN-LAST:event_btnAgregarGastoActionPerformed

    private void btnEliminarGastoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarGastoActionPerformed
        int fila = tblGastos.getSelectedRow();
        if (fila < 0) {
            controladorAlertas.alert("Seleccione el gasto a eliminar");
            return;
        }
        if (!controladorAlertas.option("¿Desea eliminar el gasto seleccionado?")) {
            return;
        }
        try {
            sqlIngresos().eliminarGasto(periodo(), idGastos.get(tblGastos.convertRowIndexToModel(fila)));
            recargar();
        } catch (IllegalStateException ex) {
            controladorAlertas.alertFail(ex.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(PanelIngresos.class.getName()).log(Level.SEVERE, "Error al eliminar el gasto", ex);
            controladorAlertas.alertFail("Error al eliminar el gasto");
        }
    }//GEN-LAST:event_btnEliminarGastoActionPerformed

    private void btnRepartirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRepartirActionPerformed
        YearMonth periodo = periodo();
        String pregunta = periodo.equals(YearMonth.now())
                ? "El mes aún no termina; los ingresos posteriores no quedarán incluidos en el reparto. ¿Desea marcarlo como repartido?"
                : "¿Desea marcar " + cmbMes.getSelectedItem() + " de " + periodo.getYear() + " como repartido?";
        if (!controladorAlertas.option(pregunta)) {
            return;
        }
        String observacion = JOptionPane.showInputDialog(this, "Observación del reparto (opcional):", "Reparto entre colaboradores",
                JOptionPane.PLAIN_MESSAGE);
        try {
            sqlIngresos().marcarRepartido(periodo, observacion == null ? null : observacion.trim());
            controladorAlertas.alertSuccess("Mes marcado como repartido");
            recargar();
        } catch (IllegalStateException ex) {
            controladorAlertas.alertFail(ex.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(PanelIngresos.class.getName()).log(Level.SEVERE, "Error al marcar el reparto", ex);
            controladorAlertas.alertFail("Error al marcar el mes como repartido");
        }
    }//GEN-LAST:event_btnRepartirActionPerformed

    private void btnAnularRepartoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnularRepartoActionPerformed
        if (!controladorAlertas.option("¿Desea anular el reparto de este mes? Podrá volver a modificar los gastos.")) {
            return;
        }
        try {
            sqlIngresos().anularReparto(periodo());
            recargar();
        } catch (SQLException ex) {
            Logger.getLogger(PanelIngresos.class.getName()).log(Level.SEVERE, "Error al anular el reparto", ex);
            controladorAlertas.alertFail("Error al anular el reparto");
        }
    }//GEN-LAST:event_btnAnularRepartoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarGasto;
    private javax.swing.JButton btnAnularReparto;
    private javax.swing.JButton btnEliminarGasto;
    private javax.swing.JButton btnMesAnterior;
    private javax.swing.JButton btnMesSiguiente;
    private javax.swing.JButton btnRepartir;
    private javax.swing.JComboBox<String> cmbMes;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbAnio;
    private javax.swing.JLabel lbAyudaReparto;
    private javax.swing.JLabel lbConceptoGasto;
    private javax.swing.JLabel lbDetalleGastos;
    private javax.swing.JLabel lbDetalleIngresos;
    private javax.swing.JLabel lbDetalleReparto;
    private javax.swing.JLabel lbDetalleUtilidad;
    private javax.swing.JLabel lbEstado;
    private javax.swing.JLabel lbGastos;
    private javax.swing.JLabel lbIngresos;
    private javax.swing.JLabel lbMes;
    private javax.swing.JLabel lbPeriodo;
    private javax.swing.JLabel lbTituloGastos;
    private javax.swing.JLabel lbTituloIngresos;
    private javax.swing.JLabel lbTituloReparto;
    private javax.swing.JLabel lbTituloUtilidad;
    private javax.swing.JLabel lbUtilidad;
    private javax.swing.JLabel lbValorGasto;
    private javax.swing.JPanel pnlEncabezado;
    private javax.swing.JPanel pnlGastos;
    private javax.swing.JPanel pnlMovimientos;
    private javax.swing.JPanel pnlTarjetaGastos;
    private javax.swing.JPanel pnlTarjetaIngresos;
    private javax.swing.JPanel pnlTarjetaReparto;
    private javax.swing.JPanel pnlTarjetaUtilidad;
    private javax.swing.JSpinner spnAnio;
    private javax.swing.JTable tblGastos;
    private javax.swing.JTable tblMovimientos;
    private javax.swing.JTextField txtConceptoGasto;
    private javax.swing.JTextField txtValorGasto;
    // End of variables declaration//GEN-END:variables
}
