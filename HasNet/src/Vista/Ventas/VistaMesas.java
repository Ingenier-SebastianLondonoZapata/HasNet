package Vista.Ventas;

import Modelo.Ventas.ModeloMesa;
import Utilidades.DatosMaestra;
import clases.Instancias;
import clases.big;
import clases.metodosGenerales;
import java.awt.Dimension;
import java.beans.PropertyVetoException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.table.DefaultTableModel;

public final class VistaMesas extends javax.swing.JInternalFrame {

    metodosGenerales metodos = new metodosGenerales();
    private final Instancias instancias;
    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    DefaultTableModel modelo;
    private final PanelMesas panelMesas;
    private List<ModeloMesa> listaMesas = new ArrayList<>();

    public VistaMesas() {

        initComponents();
        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        setBorder(null);
        repaint();

        panelMesas = new PanelMesas();
        panelMesas.setListener(new PanelMesas.ListenerMesa() {
            @Override
            public void mesaSeleccionada(ModeloMesa mesa) {
                manejarClickMesa(mesa);
            }
        });
        jScrollPane11.setViewportView(panelMesas);

        instancias = Instancias.getInstancias();

        if (instancias.getConfiguraciones().isRestaurante()) {
            lblMesa.setText("MESAS");
        } else {
            lblMesa.setText("CONGELADAS");
        }

        cargarDimensiones();
        cargarRegistrosMesas();
        cargarRegistros();
    }

    @Override
    public void setSelected(boolean selected) {
        try {
            super.setSelected(selected);
            if (selected) {
                cargarRegistrosMesas();
                cargarRegistros();
            }
        } catch (PropertyVetoException ex) {
            Logger.getLogger(VistaMesas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        pnlFormulario = new javax.swing.JPanel();
        lblMesa = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane11 = new javax.swing.JScrollPane();
        tblMesas = new javax.swing.JTable();
        btnOcultar = new javax.swing.JButton();

        setTitle("Factura");

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));

        lblMesa.setFont(new java.awt.Font("Century Gothic", 1, 28)); // NOI18N
        lblMesa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMesa.setText("MESAS");

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/FRESH.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        tblMesas.setBackground(new java.awt.Color(255, 0, 0));
        tblMesas.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        tblMesas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblMesas.setRowHeight(100);
        tblMesas.setSelectionBackground(new java.awt.Color(255, 255, 255));
        tblMesas.setSelectionForeground(new java.awt.Color(204, 204, 204));
        tblMesas.getTableHeader().setResizingAllowed(false);
        tblMesas.getTableHeader().setReorderingAllowed(false);
        tblMesas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMesasMouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(tblMesas);

        btnOcultar.setBackground(new java.awt.Color(255, 255, 255));
        btnOcultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ESTAERA.jpg"))); // NOI18N
        btnOcultar.setToolTipText("");
        btnOcultar.setBorder(null);
        btnOcultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOcultarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlFormularioLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addComponent(btnOcultar, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlFormularioLayout.createSequentialGroup()
                                .addComponent(lblMesa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1))
                            .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 755, Short.MAX_VALUE))
                        .addGap(20, 20, 20))))
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnOcultar, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblMesa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addComponent(jScrollPane11)
                .addGap(20, 20, 20))
        );

        jScrollPane1.setViewportView(pnlFormulario);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        cargarDimensiones();
        cargarRegistrosMesas();
        cargarRegistros();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnOcultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOcultarActionPerformed
        instancias.getMenu().ocultarMenu("preparacion");
        instancias.getMenu().cambiarTitulo("DOMICILIO");
        instancias.getMesa().setSelected(true);
        instancias.getMesa().getPnlFactura().abrirNuevaCongelada("DOMICILIO", "DOMICILIO");
    }//GEN-LAST:event_btnOcultarActionPerformed

    private void tblMesasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMesasMouseClicked
        // delegado a PanelMesas.ListenerMesa → manejarClickMesa()
    }//GEN-LAST:event_tblMesasMouseClicked

    public void cargarRegistros() {
        Object[][] congeladas = instancias.getSql().getDatosCongelada1();
        if (congeladas.length > 0) {
            for (int i = 0; i < congeladas.length; i++) {
                if (congeladas[i][0] == null) {
                    continue;
                }
                String lugar = congeladas[i][2].toString();
                BigDecimal total = big.getBigDecimal(congeladas[i][1]);
                String turno = "";
                if (congeladas[i][3] != null && !congeladas[i][3].toString().isEmpty()) {
                    turno = congeladas[i][3].toString();
                }
                for (ModeloMesa mesa : listaMesas) {
                    if (mesa.getNombre().equals(lugar)) {
                        mesa.setOcupada(true);
                        mesa.setTotal(total);
                        mesa.setTurno(turno);
                        break;
                    }
                }
            }
        }
        panelMesas.setMesas(listaMesas);
    }

    public void cargarRegistrosMesas() {
        listaMesas = new ArrayList<>();
        if (instancias.getConfiguraciones().isRestaurante()) {
            Object[][] mesas = instancias.getSql().getPosicionesMesas();
            for (int i = 0; i < mesas.length; i++) {
                String[] coords = mesas[i][0].toString().split(",");
                int rawFila = Integer.parseInt(coords[0]);
                int columna = Integer.parseInt(coords[1]);
                int fila = (rawFila - 1) / 2;
                String nombre = mesas[i][1].toString();
                listaMesas.add(new ModeloMesa(fila, columna, nombre));
            }
        } else {
            int slot = 1;
            for (int fila = 0; fila < 4; fila++) {
                for (int col = 0; col < 5; col++) {
                    listaMesas.add(new ModeloMesa(fila, col, "CONGELADA-" + slot));
                    slot++;
                }
            }
        }
        panelMesas.setMesas(listaMesas);
    }

    private void cargarDimensiones() {
        if (instancias.getConfiguraciones().isRestaurante()) {
            int filas = Integer.parseInt(DatosMaestra.getFilas());
            int columnas = Integer.parseInt(DatosMaestra.getColumnas());
            panelMesas.setDimensiones(filas, columnas);
        } else {
            panelMesas.setDimensiones(4, 5);
        }
    }

    private void manejarClickMesa(ModeloMesa mesa) {
        cargarRegistrosMesas();
        cargarRegistros();

        ModeloMesa mesaActualizada = null;
        for (ModeloMesa m : listaMesas) {
            if (m.getNombre().equals(mesa.getNombre())) {
                mesaActualizada = m;
                break;
            }
        }
        if (mesaActualizada == null) {
            return;
        }

        boolean esRestaurante = instancias.getConfiguraciones().isRestaurante();
        if (esRestaurante) {
            String estadoMesa = instancias.getSql().getEstadoMesa(mesaActualizada.getNombre());
            if ("OCUPADO".equals(estadoMesa)) {
                metodos.msgAdvertenciaAjustado(this, "Esta mesa esta ocupada");
                return;
            }
        }

        instancias.getMenu().ocultarMenu("preparacion");
        instancias.getMenu().cambiarTitulo(mesaActualizada.getNombre().toUpperCase());
        instancias.getMesa().setSelected(true);

        if (mesaActualizada.isOcupada()) {
            instancias.getMesa().getPnlFactura().cargarMovimientoMesa(mesaActualizada.getNombre());
        } else {
            String tipo = esRestaurante ? "MESA" : "CONG";
            instancias.getMesa().getPnlFactura().abrirNuevaCongelada(tipo, mesaActualizada.getNombre());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOcultar;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JLabel lblMesa;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JTable tblMesas;
    // End of variables declaration//GEN-END:variables
}
