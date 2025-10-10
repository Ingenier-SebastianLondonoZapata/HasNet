package formularios.Veterinario;

import formularios.Agenda.*;
import clases.IconCellRenderer;
import clases.Instancias;

import Utilidades.BaseDatos.SQL;

import clases.cambiarColorTabla;
import clases.metodosGenerales;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.sql.Date;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class infPeluqueria extends javax.swing.JInternalFrame {

    metodosGenerales metodos = new metodosGenerales();
    Instancias instancias;
    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;
    DefaultTableModel modelo;
    String bloquear = null;
    String mensaje = null;

    public infPeluqueria() {

        initComponents();
        tblRegistros.setDefaultRenderer(Object.class, new cambiarColorTabla(5, 0));
        tblRegistros1.setDefaultRenderer(Object.class, new cambiarColorTabla(19, 0));
        tblDoctores.setDefaultRenderer(Object.class, new IconCellRenderer());

        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        setBorder(null);
        repaint();
        Boolean cargo = false;

        instancias = Instancias.getInstancias();

        titulosTabla();

        JTableHeader th;
        th = tblRegistros.getTableHeader();
        Font fuente = new Font("Century Gothic", Font.BOLD, 18);
        th.setFont(fuente);
        lbFecha.setVisible(false);
        lbFecha1.setVisible(false);

        dtFecha.setCalendar(metodos.haciaDate2(metodosGenerales.fecha()));
    }

//    private infAgendaConsulta agenda;
//
//    public infAgendaConsulta getAgenda() {
//        return agenda;
//    }
//
//    public void setAgenda(infAgendaConsulta agenda) {
//        this.agenda = agenda;
//    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrFormulario = new javax.swing.JScrollPane();
        pnlFormulario = new javax.swing.JPanel();
        lbMedico = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        lbTitulo1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDoctores = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRegistros = new javax.swing.JTable();
        lbFecha = new javax.swing.JLabel();
        lbTitulo = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblRegistros1 = new javax.swing.JTable();
        lbTitulo2 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        lbFecha1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        dtFecha = new com.toedter.calendar.JDateChooser();
        lbTitulo4 = new javax.swing.JLabel();

        setTitle("Factura");

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));

        lbMedico.setFont(new java.awt.Font("Century Gothic", 1, 1)); // NOI18N
        lbMedico.setForeground(new java.awt.Color(255, 255, 255));
        lbMedico.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbMedico.setText("DOCTORES");

        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lbTitulo1.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbTitulo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitulo1.setText("PELUQUEROS");

        tblDoctores.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        tblDoctores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {""},
                {null}
            },
            new String [] {
                ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDoctores.setCellSelectionEnabled(true);
        tblDoctores.setRowHeight(150);
        tblDoctores.getTableHeader().setReorderingAllowed(false);
        tblDoctores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDoctoresMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblDoctoresMouseEntered(evt);
            }
        });
        jScrollPane2.setViewportView(tblDoctores);
        tblDoctores.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        tblRegistros.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        tblRegistros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Estado", "Hora", "Descripción", "IdAgenda"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblRegistros.setCellSelectionEnabled(true);
        tblRegistros.setRowHeight(40);
        tblRegistros.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tblRegistros.getTableHeader().setReorderingAllowed(false);
        tblRegistros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblRegistrosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblRegistrosMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(tblRegistros);
        tblRegistros.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        if (tblRegistros.getColumnModel().getColumnCount() > 0) {
            tblRegistros.getColumnModel().getColumn(0).setMinWidth(100);
            tblRegistros.getColumnModel().getColumn(0).setPreferredWidth(100);
            tblRegistros.getColumnModel().getColumn(0).setMaxWidth(100);
            tblRegistros.getColumnModel().getColumn(1).setMinWidth(85);
            tblRegistros.getColumnModel().getColumn(1).setPreferredWidth(85);
            tblRegistros.getColumnModel().getColumn(1).setMaxWidth(85);
            tblRegistros.getColumnModel().getColumn(3).setMinWidth(0);
            tblRegistros.getColumnModel().getColumn(3).setPreferredWidth(0);
            tblRegistros.getColumnModel().getColumn(3).setMaxWidth(0);
        }

        lbFecha.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbFecha.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbFecha.setText(".");

        lbTitulo.setFont(new java.awt.Font("Century Gothic", 1, 25)); // NOI18N
        lbTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitulo.setText("AGENDA");

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(lbTitulo1, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(lbFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jSeparator1))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTitulo1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbFecha))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE))
                .addGap(44, 44, 44))
        );

        jTabbedPane1.addTab("AGENDA 1", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        tblRegistros1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        tblRegistros1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Hora"
            }
        ));
        tblRegistros1.setRowHeight(40);
        tblRegistros1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblRegistros1MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblRegistros1);

        lbTitulo2.setFont(new java.awt.Font("Century Gothic", 1, 38)); // NOI18N
        lbTitulo2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitulo2.setText("PELUQUERIAS");

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));

        lbFecha1.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbFecha1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbFecha1.setText("jLabel1");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
                    .addComponent(lbTitulo2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator2)
                    .addComponent(lbFecha1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTitulo2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbFecha1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("AGENDA 2", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        dtFecha.setFont(new java.awt.Font("Century Gothic", 1, 19)); // NOI18N
        dtFecha.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                dtFechaMouseWheelMoved(evt);
            }
        });
        dtFecha.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dtFechaPropertyChange(evt);
            }
        });

        lbTitulo4.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        lbTitulo4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitulo4.setText("FECHA");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dtFecha, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                    .addComponent(lbTitulo4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(lbTitulo4)
                .addGap(5, 5, 5)
                .addComponent(dtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addComponent(lbMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jTabbedPane1)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        scrFormulario.setViewportView(pnlFormulario);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(scrFormulario)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(scrFormulario, javax.swing.GroupLayout.DEFAULT_SIZE, 629, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void cargarAgendas() {
        modelo = (DefaultTableModel) tblRegistros1.getModel();

        try {
            lbFecha.setText(metodos.fechaEnLetras(metodos.desdeDate2(dtFecha.getCalendar())));
            lbFecha1.setText(metodos.fechaEnLetras(metodos.desdeDate2(dtFecha.getCalendar())));
        } catch (Exception e) {
        }

        while (tblRegistros1.getRowCount() > 0) {
            modelo.removeRow(0);
        }

        String x;
        int horaIni = 6;
        int horaFin = 20;
        int minInic = 00;
        int intervalo = 10;
        int contador = 0;

        try {
            horaIni = Integer.parseInt(instancias.getConfigAgenda()[0]);
            horaFin = Integer.parseInt(instancias.getConfigAgenda()[1]);
            intervalo = Integer.parseInt(instancias.getConfigAgenda()[2]);
        } catch (Exception e) {
        }

        while (horaIni < horaFin) {
            if (minInic == 0) {
                x = horaIni + ":" + minInic + "0";
            } else {
                x = horaIni + ":" + minInic;
            }

            Object[] titulosTabla = new Object[tblRegistros1.getColumnCount()];
            titulosTabla[0] = x;
            int z = 1;
            for (int i = 0; i < tblRegistros1.getColumnCount() - 1; i++) {
                titulosTabla[z] = "";
                z++;
            }

            modelo.addRow(titulosTabla);

            contador = contador + intervalo;
            if (contador > 59) {
                horaIni = horaIni + 1;
                contador = contador - 60;
                minInic = contador;
            } else {
                minInic = contador;
            }
        }

        llenarInformacionAgenda();
    }

    public void llenarInformacionAgenda() {
        for (int k = 1; k < tblRegistros1.getColumnCount(); k++) {
            try {
                Object[][] agendas = instancias.getSql().getRegistrosPeluquerias(metodos.fechaConsulta(metodos.desdeDate2(dtFecha.getCalendar())), tblRegistros1.getColumnName(k));

                for (int q = 0; q < agendas.length; q++) {
                    int cantidadFilas = modelo.getRowCount();
                    String horaAgendada = agendas[q][0].toString().split(":")[0];
                    String minutoAgendada = agendas[q][0].toString().split(":")[1];
                    for (int i = 0; i < cantidadFilas; i++) {
                        String hora = modelo.getValueAt(i, 0).toString().split(":")[0];
                        String minuto = modelo.getValueAt(i, 0).toString().split(":")[1];

                        if (horaAgendada.equals(hora) || horaAgendada.equals("0" + hora)) {
                            if (minutoAgendada.equals(minuto)) {
                                String observacion = "";
                                try {
                                    //Si entra aca es por que el valor esta nulo y no funciona en la condición.
                                    if (agendas[q][5].toString() != null) {
                                        observacion = agendas[q][5].toString();
                                    }
                                } catch (Exception e) {
                                }
                                modelo.setValueAt("<html>" + agendas[q][3] + " - " + agendas[q][6] + ", " + agendas[q][2] + " <br>" + agendas[q][7] + ", " + observacion + "</html> ", i, k);
                                modelo.setValueAt(agendas[q][1], i, k + 1);
                                i = cantidadFilas;
                            } else {
                                String minutoSiguiente = modelo.getValueAt(i + 1, 0).toString().split(":")[1];
                                if ((Integer.parseInt(minutoAgendada) < Integer.parseInt(minutoSiguiente)) || Integer.parseInt(minutoSiguiente) == 00) {
                                    Object[] titulosTabla = new Object[tblRegistros1.getColumnCount()];
                                    titulosTabla[0] = agendas[q][0];
                                    int f = 1;
                                    for (int s = 0; s < tblRegistros1.getColumnCount() - 1; s++) {
                                        titulosTabla[f] = "";
                                        f++;
                                    }
                                    modelo.addRow(titulosTabla);
                                    i = cantidadFilas;
                                    cantidadFilas = cantidadFilas + 1;
                                    organizarTabla();
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
            }
        }
    }

    public void organizarTabla() {
        int aux;
        String auxHora;
        int auxMinuto;
        int[] vectorHora = new int[tblRegistros1.getRowCount()];
        int[] vectorMinuto = new int[tblRegistros1.getRowCount()];
        String[] vectorHoraCompleta = new String[tblRegistros1.getRowCount()];
        for (int i = 0; i < tblRegistros1.getRowCount(); i++) {
            vectorHora[i] = Integer.parseInt(tblRegistros1.getValueAt(i, 0).toString().split(":")[0]);
            vectorMinuto[i] = Integer.parseInt(tblRegistros1.getValueAt(i, 0).toString().split(":")[1]);
            vectorHoraCompleta[i] = tblRegistros1.getValueAt(i, 0).toString();
        }

        for (int i = 1; i < tblRegistros1.getRowCount(); i++) {
            for (int j = 0; j < tblRegistros1.getRowCount() - i; j++) {
                if (vectorHora[j] > vectorHora[j + 1]) {
                    aux = vectorHora[j];
                    auxHora = vectorHoraCompleta[j];
                    auxMinuto = vectorMinuto[j];

                    vectorHora[j] = vectorHora[j + 1];
                    vectorMinuto[j] = vectorMinuto[j + 1];
                    vectorHoraCompleta[j] = vectorHoraCompleta[j + 1];

                    vectorHora[j + 1] = aux;
                    vectorMinuto[j + 1] = auxMinuto;
                    vectorHoraCompleta[j + 1] = auxHora;
                }
            }
        }

        for (int i = 1; i < tblRegistros1.getRowCount(); i++) {
            for (int j = 0; j < tblRegistros1.getRowCount() - i; j++) {
                if (vectorHora[j] == vectorHora[j + 1]) {
                    if (vectorMinuto[j] > vectorMinuto[j + 1]) {
                        aux = vectorHora[j];
                        auxHora = vectorHoraCompleta[j];
                        auxMinuto = vectorMinuto[j];
                        vectorHora[j] = vectorHora[j + 1];
                        vectorMinuto[j] = vectorMinuto[j + 1];
                        vectorHoraCompleta[j] = vectorHoraCompleta[j + 1];
                        vectorHora[j + 1] = aux;
                        vectorMinuto[j + 1] = auxMinuto;
                        vectorHoraCompleta[j + 1] = auxHora;
                    }
                }
            }
        }

        int cantFilas = tblRegistros1.getRowCount();

        DefaultTableModel c = (DefaultTableModel) tblRegistros1.getModel();
        for (int m = 0; m < cantFilas; m++) {
            c.removeRow(0);
        }

        for (int w = 0; w < cantFilas; w++) {
            Object[] titulosTabla = new Object[tblRegistros1.getColumnCount()];
            titulosTabla[0] = vectorHoraCompleta[w];
            for (int i = 1; i < tblRegistros1.getColumnCount(); i++) {
                titulosTabla[i] = "";
            }
            modelo.addRow(titulosTabla);
        }
        llenarInformacionAgenda();
    }

    public void titulosTabla() {
        modelo = (DefaultTableModel) tblRegistros1.getModel();
        Object[][] empleados2 = instancias.getSql().getPeluqueros();

        /* CARGAR DOCTORES EN LA AGENDA INDIVIDUAL */
        DefaultTableModel modelo = (DefaultTableModel) tblDoctores.getModel();
        while (tblDoctores.getRowCount() > 0) {
            modelo.removeRow(0);
        }

        for (int i = 0; i < empleados2.length; i++) {
            ImageIcon fot = new ImageIcon(System.getProperty("user.dir") + "\\imagenes\\empleados\\" + empleados2[i][2] + ".jpg");
            Icon icono = new ImageIcon(fot.getImage().getScaledInstance(250, 200, Image.SCALE_DEFAULT));
            modelo.addRow(new Object[]{new JLabel(icono)});
            modelo.addRow(new Object[]{empleados2[i][0]});
            tblDoctores.setRowHeight(tblDoctores.getRowCount() - 1, 30);
        }
        for (int i = 0; i < tblDoctores.getRowCount(); i++) {
            if (tblDoctores.getValueAt(i, 0).equals("")) {
                tblDoctores.setRowHeight(tblDoctores.getRowCount() - 1, 30);
            }
        }
        /* FIN AGENDA INDIVIDUAL */

        /* CARGAR DOCTORES EN LA AGENDA GENERAL */
        Object[] empleados = new Object[empleados2.length + empleados2.length + 1];
        empleados[0] = "Hora";
        int f = 1;
        for (int i = 0; i < empleados2.length; i++) {
            empleados[f] = empleados2[i][0];
            empleados[f + 1] = "Info " + i;
            f = f + 2;
        }

        tblRegistros1.setModel(new javax.swing.table.DefaultTableModel(new Object[0][0], empleados) {
            boolean[] canEdit = new boolean[]{
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[0];
            }
        });

        if (tblRegistros1.getColumnModel().getColumnCount() > 0) {
            tblRegistros1.getColumnModel().getColumn(0).setMinWidth(70);
            tblRegistros1.getColumnModel().getColumn(0).setPreferredWidth(70);
            tblRegistros1.getColumnModel().getColumn(0).setMaxWidth(70);
            int numeroColumnas = tblRegistros1.getColumnCount();
            int num = (numeroColumnas - 1) / 2;
            int columna = 2;
            if (num > 0) {
                for (int i = 0; i < num; i++) {
                    tblRegistros1.getColumnModel().getColumn(columna).setMinWidth(0);
                    tblRegistros1.getColumnModel().getColumn(columna).setPreferredWidth(0);
                    tblRegistros1.getColumnModel().getColumn(columna).setMaxWidth(0);
                    columna = columna + 2;
                }
            }
        }
    }

    public void cargarAgendas(String medico) {

        modelo = (DefaultTableModel) tblRegistros.getModel();

        try {
            lbFecha.setText(metodos.fechaEnLetras(metodos.desdeDate2(dtFecha.getCalendar())));
            lbFecha1.setText(metodos.fechaEnLetras(metodos.desdeDate2(dtFecha.getCalendar())));
        } catch (Exception e) {
        }

        Object[][] horasBloqueo = instancias.getSql().getHorasBloqueoMedico(medico);
        Boolean entro = false;

        String horas = "";
        String mensaje = "";
        String medico1 = "";
        String fecha = "";

        if (horasBloqueo.length > 0) {
            try {
                for (int i = 0; i < horasBloqueo.length; i++) {
                    if (metodos.fecha(horasBloqueo[i][0].toString()).equals(metodos.desdeDate2(dtFecha.getCalendar()))) {

                        try {
                            horas = horasBloqueo[i][4].toString();
                        } catch (Exception e) {
                            horas = "";
                        }

                        if (!lbTitulo.getText().equals("AGENDA") && horas.equals("")) {

                            try {
                                medico1 = horasBloqueo[i][1].toString();
                            } catch (Exception e) {
                                medico1 = "";
                            }

                            try {
                                mensaje = horasBloqueo[i][2].toString();
                            } catch (Exception e) {
                                mensaje = "";
                            }

                            try {
                                fecha = horasBloqueo[i][0].toString();
                            } catch (Exception e) {
                                fecha = "";
                            }

                            entro = true;
                        }
                    }
                }
            } catch (Exception e) {
            }
        }

        while (tblRegistros.getRowCount() > 0) {
            modelo.removeRow(0);
        }

        if (!entro) {
            String x, y;
            int horaIni = 6;
            int horaFin = 20;
            int minInic = 00;
            int intervalo = 10;
            int contador = 0;

            try {
                horaIni = Integer.parseInt(instancias.getConfigAgenda()[0]);
                horaFin = Integer.parseInt(instancias.getConfigAgenda()[1]);
                intervalo = Integer.parseInt(instancias.getConfigAgenda()[2]);
            } catch (Exception e) {
            }

            while (horaIni < horaFin) {
                if (minInic == 0) {
                    x = horaIni + ":" + minInic + "0";
                } else {
                    x = horaIni + ":" + minInic;
                }

                Object[] titulosTabla = new Object[tblRegistros.getColumnCount()];
                titulosTabla[0] = x;
                int z = 1;
                for (int i = 0; i < tblRegistros.getColumnCount() - 1; i++) {
                    titulosTabla[z] = "";
                    z++;
                }

                modelo.addRow(new Object[]{"Disponible", titulosTabla[0], "", ""});
//            modelo.addRow(titulosTabla);

                contador = contador + intervalo;
                if (contador > 59) {
                    horaIni = horaIni + 1;
                    contador = contador - 60;
                    minInic = contador;
                } else {
                    minInic = contador;
                }

            }

            int z = modelo.getRowCount();

            try {
                Object[][] agendas = instancias.getSql().getRegistrosPeluquerias(metodos.fechaConsulta(metodos.desdeDate2(dtFecha.getCalendar())), medico);
                for (int q = 0; q < agendas.length; q++) {
                    for (int i = 0; i < z; i++) {
                        String agenda = agendas[q][0].toString().substring(0, agendas[q][0].toString().length() - 3);
                        String hora = modelo.getValueAt(i, 1).toString();
                        if (agenda.equals(hora) || agenda.equals("0" + hora)) {
                            modelo.setValueAt(agendas[q][3], i, 0);
                            modelo.setValueAt("<html>" + agendas[q][6] + " - " + agendas[q][2] + " <br>" + agendas[q][7] + ", " + agendas[q][5] + "</html> ", i, 2);
                            modelo.setValueAt(agendas[q][1].toString(), i, 3);
//                        modelo.setValueAt(agendas[q][3] + " - " + agendas[q][2] + " / " + agendas[q][4]+ " / " + agendas[q][5], i, k);
//                        modelo.setValueAt(agendas[q][1], i, k + 1);
                        }
                    }
                }
            } catch (Exception e) {
            }

        } else {
            lbFecha.setText(lbFecha.getText() + ".   ¡DÍA BLOQUEADO!");
            lbFecha1.setText(lbFecha1.getText() + ".   ¡DÍA BLOQUEADO!");

            dlgNovedad novedad = new dlgNovedad(null, true, medico1, fecha, horas, mensaje);
            novedad.setLocationRelativeTo(null);
            novedad.setVisible(true);
        }
    }

    public void cargarCita(String fecha, String hora, String medico) {
//        dtFecha.setSelectedDate(metodos.haciaDate(metodos.fecha2(fecha)));
//        cmbACargo.setSelectedItem(medico);
//        cargarAgendas();
//
//        for (int i = 0; i < tblRegistros.getRowCount(); i++) {
//            if (tblRegistros.getValueAt(i, 0).toString().equalsIgnoreCase(hora)) {
//                tblRegistros.changeSelection(i, 0, false, false);
//                tblRegistros.removeEditor();
//                cargarClienteAgenda(tblRegistros.getValueAt(i, 1).toString());
//            }
//        }
    }


    private void tblRegistrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRegistrosMouseClicked
        if (evt.getClickCount() > 1) {

            if (lbTitulo.getText().equals("AGENDA")) {
                metodos.msgError(this, "Seleccione un peluquero");
                return;
            }

            Object[][] horasBloqueo = instancias.getSql().getHorasBloqueoMedico(lbMedico.getText());

            for (int i = 0; i < horasBloqueo.length; i++) {
                if (metodos.fecha(horasBloqueo[i][0].toString()).equals(metodos.desdeDate2(dtFecha.getCalendar()))) {
                    String[] horas = horasBloqueo[i][4].toString().split(", ");
                    if (!lbTitulo.getText().equals("AGENDA")) {
                        for (int j = 0; j < horas.length; j++) {
                            if (tblRegistros.getValueAt(tblRegistros.getSelectedRow(), 1).equals(horas[j])) {
                                dlgNovedad novedad = new dlgNovedad(null, true, horasBloqueo[i][1].toString(), horasBloqueo[i][0].toString(), horasBloqueo[i][4].toString(),
                                        horasBloqueo[i][2].toString());
                                novedad.setLocationRelativeTo(null);
                                novedad.setVisible(true);
                                return;
                            }
                        }
                    }
                }
            }

            try {
                infPeluqueriaInfo buscar = new infPeluqueriaInfo(null, rootPaneCheckingEnabled,
                        lbTitulo.getText().replace("AGENDA DE ", ""),
                        metodos.desdeDate(dtFecha.getCalendar()),
                        tblRegistros.getValueAt(tblRegistros.getSelectedRow(), 1).toString(),
                        tblRegistros.getSelectedRow(),
                        tblRegistros.getSelectedColumn(),
                        tblRegistros.getValueAt(tblRegistros.getSelectedRow(), 3).toString()
                );
                buscar.setAgenda(true);
                buscar.setLocationRelativeTo(null);
                instancias.setInfoPeluqueria(buscar);
                buscar.show();
            } catch (Exception e) {
                metodos.msgError(this, "Seleccione la fecha");
                return;
            }
        }
    }//GEN-LAST:event_tblRegistrosMouseClicked


    private void tblRegistrosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRegistrosMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tblRegistrosMouseEntered

    private void tblDoctoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDoctoresMouseClicked
        int row = tblDoctores.getSelectedRow();
        String medico;

        if (row == 0) {
            row = 2;
        }

        if (row % 2 > 0) {
            medico = tblDoctores.getValueAt(tblDoctores.getSelectedRow(), 0).toString();
        } else {
            medico = tblDoctores.getValueAt(tblDoctores.getSelectedRow() + 1, 0).toString();
        }

        lbFecha.setVisible(true);
        lbFecha1.setVisible(true);
        lbTitulo.setText("AGENDA DE " + medico.toUpperCase());
        instancias.setMedico(medico);
        lbMedico.setText(medico);
        cargarAgendas(medico);
    }//GEN-LAST:event_tblDoctoresMouseClicked

    private void tblDoctoresMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDoctoresMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tblDoctoresMouseEntered

    private void tblRegistros1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRegistros1MouseClicked
        Object[][] horasBloqueo = instancias.getSql().getHorasBloqueoMedico(tblRegistros1.getColumnName(tblRegistros1.getSelectedColumn()));
        Boolean entro = false;
        String mensaje = "";

        if (horasBloqueo.length > 0) {
            for (int i = 0; i < horasBloqueo.length; i++) {
                if (metodos.fecha(horasBloqueo[i][0].toString()).equals(metodos.desdeDate2(dtFecha.getCalendar()))) {
                    metodos.msgAdvertencia(this, "Este día esta bloqueado!");
                    mensaje = horasBloqueo[i][2].toString();
                    entro = true;
                }
            }
        }

        if (entro) {
//            txtRazon.setText(mensaje);
        } else {
//            txtRazon.setText("");
            if (evt.getClickCount() > 1) {
                if (tblRegistros1.getSelectedColumn() > 0) {
                    infInfoAgenda buscar = new infInfoAgenda(null, rootPaneCheckingEnabled,
                            tblRegistros1.getColumnName(tblRegistros1.getSelectedColumn()),
                            metodos.desdeDate(dtFecha.getCalendar()),
                            tblRegistros1.getValueAt(tblRegistros1.getSelectedRow(), 0).toString(),
                            tblRegistros1.getSelectedRow(),
                            tblRegistros1.getSelectedColumn(),
                            tblRegistros1.getValueAt(tblRegistros1.getSelectedRow(), tblRegistros1.getSelectedColumn() + 1).toString()
                    );
                    buscar.setDoctor(true);
                    buscar.setLocationRelativeTo(null);
                    instancias.setInfoAgenda(buscar);
                    buscar.show();
                }
            }
        }
    }//GEN-LAST:event_tblRegistros1MouseClicked

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked

    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void dtFechaMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_dtFechaMouseWheelMoved
        cargarAgendas();
        cargarAgendas(lbMedico.getText());
    }//GEN-LAST:event_dtFechaMouseWheelMoved

    private void dtFechaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dtFechaPropertyChange
        cargarAgendas();
        cargarAgendas(lbMedico.getText());
    }//GEN-LAST:event_dtFechaPropertyChange


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser dtFecha;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbFecha;
    private javax.swing.JLabel lbFecha1;
    private javax.swing.JLabel lbMedico;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JLabel lbTitulo1;
    private javax.swing.JLabel lbTitulo2;
    private javax.swing.JLabel lbTitulo4;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JTable tblDoctores;
    private javax.swing.JTable tblRegistros;
    private javax.swing.JTable tblRegistros1;
    // End of variables declaration//GEN-END:variables
}
