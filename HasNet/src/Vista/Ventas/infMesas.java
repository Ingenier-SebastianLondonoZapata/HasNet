package Vista.Ventas;

import clases.IconCellRendererConColor;
import clases.Instancias;
import Utilidades.BaseDatos.SQL;
import clases.big;
import clases.metodosGenerales;
import java.awt.Dimension;
import java.awt.Image;
import java.beans.PropertyVetoException;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

public class infMesas extends javax.swing.JInternalFrame {

    metodosGenerales metodos = new metodosGenerales();
    private Instancias instancias;
    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;
    DefaultTableModel modelo;
    Object[] datos;

    public infMesas() {
        initComponents();
        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        setBorder(null);
        repaint();

        tblMesas.setDefaultRenderer(Object.class, new IconCellRendererConColor());
        instancias = Instancias.getInstancias();

        if (instancias.getConfiguraciones().isRestaurante()) {
            lblMesa.setText("MESAS");
        } else {
            lblMesa.setText("CONGELADAS");
        }

        consultarMaestros();

        cargarColumna();
        cargarFila();
        cargarRegistrosMesas();
        cargarRegistros();
    }

    @Override
    public void setSelected(boolean selected) {
        try {
            super.setSelected(selected); //To change body of generated methods, choose Tools | Templates.
        } catch (PropertyVetoException ex) {
            Logger.getLogger(infMesas.class.getName()).log(Level.SEVERE, null, ex);
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
                            .addComponent(jScrollPane11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1234, Short.MAX_VALUE)
                            .addGroup(pnlFormularioLayout.createSequentialGroup()
                                .addComponent(lblMesa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1)))
                        .addGap(24, 24, 24))))
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
                .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 593, Short.MAX_VALUE)
                .addGap(30, 30, 30))
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
        cargarColumna();
        cargarFila();

        cargarRegistrosMesas();
        cargarRegistros();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tblMesasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMesasMouseClicked
        if (evt.getClickCount() >= 1) {

            cargarRegistrosMesas();
            cargarRegistros();

            String valor = "";
            if (tblMesas.getSelectedRow() % 2 == 0) {
                valor = tblMesas.getValueAt(tblMesas.getSelectedRow() + 1, tblMesas.getSelectedColumn()).toString();
            } else {
                valor = tblMesas.getValueAt(tblMesas.getSelectedRow(), tblMesas.getSelectedColumn()).toString();
            }

            String mesa = "";

            if (valor.contains("text=M")) {
                Object[] cadenas = valor.split(",");
                for (int i = 0; i < cadenas.length; i++) {
                    if (cadenas[i].toString().contains("text=M")) {
                        mesa = cadenas[i].toString().replace("text=", "");
                        break;
                    }
                }
            }

            String estadoMesa = "", mesaActual = "";
            if (mesa.contains("-")) {
                mesaActual = mesa.split("-")[0];
                mesaActual = mesaActual.replace("M", "Mesa. ");
                estadoMesa = instancias.getSql().getEstadoMesa(mesaActual);
            } else if (mesa.length() > 1) {
                estadoMesa = instancias.getSql().getEstadoMesa(mesa);
            }

            if (estadoMesa.equals("OCUPADO")) {
                metodos.msgAdvertenciaAjustado(this, "Esta mesa esta ocupada");
                return;
            }

            if (mesa.contains("-")) {
                instancias.getMenu().ocultarMenu("preparacion");
                mesa = mesa.split("-")[0];
                mesa = mesa.replace("M", "Mesa. ");

                instancias.getMenu().cambiarTitulo(mesa.toUpperCase());
                instancias.getMesa().setSelected(true);
                instancias.getMesa().getPnlFactura().cargarCongelada(mesa, "MESA", mesa);
            } else if (mesa.length() > 1) {
                instancias.getMenu().ocultarMenu("preparacion");
                instancias.getMenu().cambiarTitulo(mesa.toUpperCase());
                instancias.getMesa().setSelected(true);
                instancias.getMesa().getPnlFactura().cargarCongelada("", "MESA", mesa);
            } else if (valor.equals("")) {
                metodos.msgError(this, "¡Seleccione una mesa válida!");
                return;
            }
        }
    }//GEN-LAST:event_tblMesasMouseClicked

    private void btnOcultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOcultarActionPerformed
        instancias.getMenu().ocultarMenu("preparacion");
        instancias.getMenu().cambiarTitulo("DOMICILIO");
        instancias.getMesa().setSelected(true);
        instancias.getMesa().getPnlFactura().cargarCongelada("", "DOMICILIO", "DOMICILIO");
    }//GEN-LAST:event_btnOcultarActionPerformed

    public void cargarRegistros() {

        Object[][] congeladas = instancias.getSql().getDatosCongelada1();

        if (congeladas.length > 0) {
            Icon icono = null;
            ImageIcon fot = new ImageIcon(getClass().getResource("/imagenes/MESA_ROJA.png"));
            icono = new ImageIcon(fot.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));

            for (int i = 0; i < congeladas.length; i++) {

                if (congeladas[i][0] != null) {
                    String lugar = congeladas[i][2].toString();

                    for (int k = 0; k < tblMesas.getRowCount(); k++) {
                        for (int s = 0; s < tblMesas.getColumnCount(); s++) {

                            String lugarTabla = tblMesas.getValueAt(k, s).toString();
                            String mesa = "";

                            Object[] cadenas = lugarTabla.split(",");

                            for (int ser = 0; ser < cadenas.length; ser++) {
                                if (cadenas[ser].toString().contains("text=Mesa")) {
                                    mesa = cadenas[ser].toString().replace("text=", "");
                                }
                            }

                            if (!mesa.equals("")) {
                                if (mesa.equals(lugar)) {
                                    String turno = "";
                                    BigDecimal total = big.getBigDecimal(congeladas[i][1]);
                                    if (null == congeladas[i][3] || congeladas[i][3].toString().isEmpty()) {
                                    } else {
                                        turno = " - T" + congeladas[i][3].toString();
                                    }

                                    tblMesas.setValueAt(new JLabel(icono), k - 1, s);
                                    tblMesas.setValueAt(new JLabel("M" + mesa.replace("Mesa. ", "") + "-" + big.setMonedaExacta(total) + turno), k, s);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void consultarMaestros() {
        datos = instancias.getSql().getDatosMaestra();
    }

    public void cargarRegistrosMesas() {
        Object[][] mesas = instancias.getSql().getPosicionesMesas();
        Icon icono = null;
        ImageIcon fot = new ImageIcon(getClass().getResource("/imagenes/MESA_VERDE.png"));
        icono = new ImageIcon(fot.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));

        for (int i = 0; i < mesas.length; i++) {
            int x = Integer.parseInt(mesas[i][0].toString().split(",")[0]);
            int y = Integer.parseInt(mesas[i][0].toString().split(",")[1]);
            tblMesas.setValueAt(new JLabel(icono), x - 1, y);
            tblMesas.setValueAt(new JLabel(mesas[i][1].toString()), x, y);
        }
    }

    public void cargarFila() {
        DefaultTableModel model = (DefaultTableModel) tblMesas.getModel();
        int filas = Integer.parseInt(datos[88].toString());
        while (tblMesas.getRowCount() > 0) {
            model.removeRow(0);
        }
        filas = filas * 2;
        for (int i = 1; i <= filas; i++) {
            model.addRow(new Object[]{"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""});
        }

        for (int i = 0; i < tblMesas.getRowCount(); i++) {
            if (i % 2 == 0) {
                tblMesas.setRowHeight(i, 110);
            } else {
                tblMesas.setRowHeight(i, 25);
            }
        }
    }

    public void cargarColumna() {

        for (int i = 0; i < 26; i++) {
            tblMesas.getColumnModel().getColumn(i).setMinWidth(0);
            tblMesas.getColumnModel().getColumn(i).setPreferredWidth(0);
            tblMesas.getColumnModel().getColumn(i).setMaxWidth(0);
        }

        int columnas = Integer.parseInt(datos[89].toString());

        tblMesas.setModel(new javax.swing.table.DefaultTableModel(new Object[][]{}, new String[]{
            "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""
        }) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false,
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });

        tblMesas.getTableHeader().setResizingAllowed(false);
        tblMesas.getTableHeader().setReorderingAllowed(false);
        jScrollPane11.setViewportView(tblMesas);

        for (int i = columnas; i < 26; i++) {
            tblMesas.getColumnModel().getColumn(i).setMinWidth(0);
            tblMesas.getColumnModel().getColumn(i).setPreferredWidth(0);
            tblMesas.getColumnModel().getColumn(i).setMaxWidth(0);
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
