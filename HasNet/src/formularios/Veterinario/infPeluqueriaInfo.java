package formularios.Veterinario;

import formularios.Agenda.*;
import clases.Instancias;
import Utilidades.BaseDatos.SQL;
import clases.Veterinario.ndMascota;
import clases.big;
import clases.metodosGenerales;
import clases.productos.ndProducto;
import Modelo.Terceros.ModeloContacto;
import formularios.Agenda.ndAgenda;
import formularios.productos.buscProductos;
import formularios.terceros.buscClientes;
import formularios.terceros.nuevoTercero;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.nio.file.Paths;
import java.util.Calendar;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

public class infPeluqueriaInfo extends javax.swing.JDialog {

    metodosGenerales metodos = new metodosGenerales();
    private Instancias instancias;
    private Dimension dimBarra = null;
    DefaultTableModel modelo;
    String fecha;
    String atiende;
    public String aCargo = null;
    public boolean agenda = false;
    public boolean doctor = false;
    public boolean normal = false;

    public boolean isAgenda() {
        return agenda;
    }

    public void setAgenda(boolean agenda) {
        this.agenda = agenda;
    }

    public boolean isNormal() {
        return normal;
    }

    public void setNormal(boolean normal) {
        this.normal = normal;
    }

    public boolean isDoctor() {
        return doctor;
    }

    public void setDoctor(boolean doctor) {
        this.doctor = doctor;
    }

    public String getaCargo() {
        return aCargo;
    }

    public void setaCargo(String aCargo) {
        this.aCargo = aCargo;
    }

    public infPeluqueriaInfo(java.awt.Frame parent, boolean modal, String atiende, String fecha, String hora, int fila, int columna, String campoTabla) {
        super(parent, modal);
        initComponents();

        instancias = Instancias.getInstancias();

        Object[][] aux = instancias.getSql().getPeluqueros();
        for (int i = 0; i < aux.length; i++) {
            cmbMedicoNuevo.addItem(aux[i][0].toString());
        }
        cmbMedicoNuevo.setSelectedItem(txtEncargado.getText());

        pnlInvisible.setVisible(false);
        dtNuevaCita.setVisible(false);
        lblNuevaFecha.setVisible(false);
        btnBuscarHoras.setVisible(false);

        btnAgregarProductos.setEnabled(false);
        btnInasistencia.setEnabled(false);
        btnCancelar.setEnabled(false);
        btnConfirmar.setEnabled(false);
        btnFinalizar.setEnabled(false);
        btnModificar.setEnabled(false);

        cmbMedicoNuevo.setEnabled(false);
        txthora.setEnabled(false);

        lbProxima.setVisible(false);
        dtProxima.setVisible(false);

        dtNuevaCita.setSelectedDate(Calendar.getInstance());

        this.atiende = atiende;
        this.fecha = fecha;

        txthora.setText(hora);
        txtEncargado.setText(atiende);

        if (campoTabla.equals("")) {
            txtCita.setText(instancias.getSql().getNumConsecutivo("PELU")[0].toString());
//            btnBuscarHoras.setEnabled(true);
        } else {
            String[] campo = campoTabla.split("-");
            txtCita.setText(campo[1]);
            cargarClienteAgenda();
//            btnBuscarHoras.setEnabled(false);
        }

        dtProxima.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));

        jPanel1.registerKeyboardAction(accion("cerrar", this), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
        jPanel1.registerKeyboardAction(accion("guardar", null), "guardar", KeyStroke.getKeyStroke(KeyEvent.VK_G, Event.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
        jPanel1.registerKeyboardAction(accion("limpiar", null), "limpiar", KeyStroke.getKeyStroke(KeyEvent.VK_L, Event.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
        jPanel1.registerKeyboardAction(accion("productos", null), "productos", KeyStroke.getKeyStroke(KeyEvent.VK_P, Event.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
        jPanel1.registerKeyboardAction(accion("terceros", null), "terceros", KeyStroke.getKeyStroke(KeyEvent.VK_T, Event.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
        jPanel1.registerKeyboardAction(accion("modificar", null), "modificar", KeyStroke.getKeyStroke(KeyEvent.VK_M, Event.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
    }

    private ActionListener accion(final String opc, final JDialog ventana) {
        ActionListener a = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (opc) {
                    case "limpiar":
                        if ((btnLimpiar.isEnabled()) && (btnLimpiar.isVisible())) {
                            btnLimpiarActionPerformed(null);
                        }
                        break;
                    case "guardar":
                        if ((btnGuardar.isEnabled()) && (btnGuardar.isVisible())) {
                            btnGuardarActionPerformed(null);
                        }
                        break;
                    case "productos":
                        if ((btnBuscarProductos.isEnabled()) && (btnBuscarProductos.isVisible())) {
                            btnBuscarProductosActionPerformed(null);
                        }
                        break;
                    case "modificar":
                        if ((btnModificar.isEnabled()) && (btnModificar.isVisible())) {
                            btnModificarActionPerformed(null);
                        }
                        break;
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

        jPanel1 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        txtEncargado = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        lbNit = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        btnBuscTerceros1 = new javax.swing.JButton();
        lbRazon = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lbDireccion6 = new javax.swing.JLabel();
        txtRaza = new javax.swing.JTextField();
        lbDireccion5 = new javax.swing.JLabel();
        txtGenero = new javax.swing.JTextField();
        lbDireccion4 = new javax.swing.JLabel();
        txtEdad = new javax.swing.JTextField();
        lbEmail8 = new javax.swing.JLabel();
        txtDieta = new javax.swing.JTextField();
        lbEmail10 = new javax.swing.JLabel();
        txtTemp = new javax.swing.JTextField();
        lbEmail11 = new javax.swing.JLabel();
        txtEspecie = new javax.swing.JTextField();
        jSeparator9 = new javax.swing.JSeparator();
        lbEmail12 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtObv = new javax.swing.JTextArea();
        lbProxima = new javax.swing.JLabel();
        dtProxima = new datechooser.beans.DateChooserCombo();
        jPanel10 = new javax.swing.JPanel();
        lbNit2 = new javax.swing.JLabel();
        txtNombrePropietario = new javax.swing.JTextField();
        lbNit3 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        txtId = new javax.swing.JTextField();
        lbRazon1 = new javax.swing.JLabel();
        btnBuscTerceros = new javax.swing.JButton();
        txtCelular = new javax.swing.JTextField();
        lbNit4 = new javax.swing.JLabel();
        lbNit5 = new javax.swing.JLabel();
        txtCita = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtEstado = new javax.swing.JTextField();
        lbFoto = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnLimpiar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnConfirmar = new javax.swing.JButton();
        btnFinalizar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        txtServicio = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtObservaciones = new javax.swing.JTextArea();
        btnBuscarProductos = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txthora = new javax.swing.JTextField();
        btnBuscarHoras = new javax.swing.JButton();
        lblMedicoNuevo = new javax.swing.JLabel();
        cmbMedicoNuevo = new javax.swing.JComboBox();
        lblNuevaFecha = new javax.swing.JLabel();
        dtNuevaCita = new datechooser.beans.DateChooserCombo();
        pnlInvisible = new javax.swing.JPanel();
        txtIdProd = new javax.swing.JTextField();
        txtIdTercero = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblRegistros = new javax.swing.JTable();
        btnInasistencia = new javax.swing.JButton();
        btnAgregarProductos = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jPanel1KeyReleased(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("X");
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        txtEncargado.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        txtEncargado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtEncargado.setText("ENCARGADO");

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mascota", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 14))); // NOI18N

        lbNit.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit.setText("Codigo:");

        txtCodigo.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCodigo.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtCodigo.setName("Codigo"); // NOI18N
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoKeyReleased(evt);
            }
        });

        btnBuscTerceros1.setBackground(new java.awt.Color(204, 204, 204));
        btnBuscTerceros1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnBuscTerceros1.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscTerceros1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar1.png"))); // NOI18N
        btnBuscTerceros1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnBuscTerceros1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscTerceros1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBuscTerceros1.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnBuscTerceros1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscTerceros1ActionPerformed(evt);
            }
        });

        lbRazon.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbRazon.setText("Nombre:");

        txtNombre.setEditable(false);
        txtNombre.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNombre.setName("Nombre"); // NOI18N
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
        });

        lbDireccion6.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion6.setText("Raza:");

        txtRaza.setEditable(false);
        txtRaza.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtRaza.setName("Edad"); // NOI18N
        txtRaza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRazaActionPerformed(evt);
            }
        });
        txtRaza.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRazaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRazaKeyTyped(evt);
            }
        });

        lbDireccion5.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion5.setText("Genero:");

        txtGenero.setEditable(false);
        txtGenero.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtGenero.setName("Edad"); // NOI18N
        txtGenero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGeneroActionPerformed(evt);
            }
        });
        txtGenero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGeneroKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtGeneroKeyTyped(evt);
            }
        });

        lbDireccion4.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion4.setText("Edad: ");

        txtEdad.setEditable(false);
        txtEdad.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtEdad.setName("Edad"); // NOI18N
        txtEdad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEdadActionPerformed(evt);
            }
        });
        txtEdad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEdadKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEdadKeyTyped(evt);
            }
        });

        lbEmail8.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbEmail8.setText("Dieta:");

        txtDieta.setEditable(false);
        txtDieta.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtDieta.setName("Edad"); // NOI18N
        txtDieta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDietaActionPerformed(evt);
            }
        });
        txtDieta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDietaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDietaKeyTyped(evt);
            }
        });

        lbEmail10.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbEmail10.setText("Temp:");

        txtTemp.setEditable(false);
        txtTemp.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtTemp.setName("Edad"); // NOI18N
        txtTemp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTempActionPerformed(evt);
            }
        });
        txtTemp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTempKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTempKeyTyped(evt);
            }
        });

        lbEmail11.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbEmail11.setText("Especie:");

        txtEspecie.setEditable(false);
        txtEspecie.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtEspecie.setName("Edad"); // NOI18N
        txtEspecie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEspecieActionPerformed(evt);
            }
        });
        txtEspecie.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEspecieKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEspecieKeyTyped(evt);
            }
        });

        lbEmail12.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbEmail12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbEmail12.setText("Observaciones de la mascota");

        txtObv.setColumns(20);
        txtObv.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtObv.setRows(2);
        txtObv.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtObv.setEnabled(false);
        jScrollPane4.setViewportView(txtObv);

        lbProxima.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbProxima.setText("PROXIMA PELUQUERIA:");

        try {
            dtProxima.setDefaultPeriods(new datechooser.model.multiple.PeriodSet());
        } catch (datechooser.model.exeptions.IncompatibleDataExeption e1) {
            e1.printStackTrace();
        }
        dtProxima.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 12));
        dtProxima.addCommitListener(new datechooser.events.CommitListener() {
            public void onCommit(datechooser.events.CommitEvent evt) {
                dtProximaOnCommit(evt);
            }
        });
        dtProxima.addSelectionChangedListener(new datechooser.events.SelectionChangedListener() {
            public void onSelectionChange(datechooser.events.SelectionChangedEvent evt) {
                dtProximaOnSelectionChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(lbProxima)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dtProxima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lbRazon, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
                                .addComponent(lbNit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel8Layout.createSequentialGroup()
                                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnBuscTerceros1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel8Layout.createSequentialGroup()
                                    .addGap(22, 22, 22)
                                    .addComponent(lbDireccion6, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel8Layout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addComponent(lbDireccion5)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtRaza, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                                .addComponent(txtGenero)))
                        .addComponent(lbEmail12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator9)
                        .addComponent(jScrollPane4)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lbEmail10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbDireccion4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtTemp, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                                .addComponent(txtEdad))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lbEmail11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbEmail8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(20, 20, 20)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtDieta)
                                .addComponent(txtEspecie)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbNit, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnBuscTerceros1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbDireccion6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtRaza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(3, 3, 3)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(lbRazon, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(lbDireccion5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtGenero))
                .addGap(3, 3, 3)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbEmail8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDieta, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbDireccion4)
                        .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(4, 4, 4)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbEmail11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbEmail10)
                        .addComponent(txtTemp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtEspecie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbEmail12)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbProxima, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(dtProxima, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Propietario", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 14))); // NOI18N

        lbNit2.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit2.setText("Cedula:");

        txtNombrePropietario.setEditable(false);
        txtNombrePropietario.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNombrePropietario.setName("Nombre"); // NOI18N
        txtNombrePropietario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombrePropietarioActionPerformed(evt);
            }
        });
        txtNombrePropietario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombrePropietarioKeyReleased(evt);
            }
        });

        lbNit3.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbNit3.setText("Telefono:");

        txtTelefono.setEditable(false);
        txtTelefono.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtTelefono.setName("Codigo"); // NOI18N
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });

        txtId.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtId.setName("Codigo"); // NOI18N
        txtId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdKeyReleased(evt);
            }
        });

        lbRazon1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon1.setText("Nombre:");

        btnBuscTerceros.setBackground(new java.awt.Color(204, 204, 204));
        btnBuscTerceros.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnBuscTerceros.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscTerceros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar1.png"))); // NOI18N
        btnBuscTerceros.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnBuscTerceros.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscTerceros.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBuscTerceros.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnBuscTerceros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscTercerosActionPerformed(evt);
            }
        });

        txtCelular.setEditable(false);
        txtCelular.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCelular.setName("Codigo"); // NOI18N
        txtCelular.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCelularKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCelularKeyTyped(evt);
            }
        });

        lbNit4.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbNit4.setText("Celular:");

        lbNit5.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit5.setText("Pelu No.");

        txtCita.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        txtCita.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtCita.setText("2");

        jLabel9.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel9.setText("Estado:");

        txtEstado.setEditable(false);
        txtEstado.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtEstado.setText("Registrada");
        txtEstado.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        txtEstado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEstadoKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbNit3, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                    .addComponent(lbRazon1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNit2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNit5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbNit4, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtNombrePropietario, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtCita, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtId, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE))
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(btnBuscTerceros, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCita, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbNit5)
                        .addComponent(txtEstado)
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lbNit2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtId, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBuscTerceros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbRazon1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombrePropietario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTelefono)
                    .addComponent(txtCelular)
                    .addComponent(lbNit4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNit3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );

        lbFoto.setBackground(new java.awt.Color(204, 204, 204));
        lbFoto.setToolTipText("Esta herramienta solo recibe formatos JPG y PNG. Al montar la imagen recibe el tamaño de 250x250.");
        lbFoto.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        lbFoto.setMaximumSize(new java.awt.Dimension(200, 200));
        lbFoto.setMinimumSize(new java.awt.Dimension(200, 200));
        lbFoto.setName(""); // NOI18N
        lbFoto.setOpaque(true);
        lbFoto.setPreferredSize(new java.awt.Dimension(200, 200));
        lbFoto.setRequestFocusEnabled(false);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        btnLimpiar.setBackground(new java.awt.Color(204, 204, 204));
        btnLimpiar.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/limpiar.png"))); // NOI18N
        btnLimpiar.setText("LIMPIAR");
        btnLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpiar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnLimpiar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnGuardar.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardar.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
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

        btnModificar.setBackground(new java.awt.Color(93, 173, 226));
        btnModificar.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/actualizar.png"))); // NOI18N
        btnModificar.setText("MODIFICAR");
        btnModificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnModificar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnModificar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnConfirmar.setBackground(new java.awt.Color(46, 204, 113));
        btnConfirmar.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnConfirmar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/agregar.png"))); // NOI18N
        btnConfirmar.setText("CONFIRMAR");
        btnConfirmar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnConfirmar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnConfirmar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarActionPerformed(evt);
            }
        });

        btnFinalizar.setBackground(new java.awt.Color(46, 204, 113));
        btnFinalizar.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnFinalizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/agregar.png"))); // NOI18N
        btnFinalizar.setText("FACTURAR");
        btnFinalizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFinalizar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnFinalizar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(241, 148, 138));
        btnCancelar.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/borrar2.png"))); // NOI18N
        btnCancelar.setText("CANCELAR");
        btnCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnCancelar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnFinalizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnConfirmar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLimpiar)
                    .addComponent(btnGuardar))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConfirmar)
                    .addComponent(btnModificar))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnFinalizar))
                .addGap(3, 3, 3))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 14))); // NOI18N

        txtServicio.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtServicio.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtServicio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtServicioKeyReleased(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel7.setText("Servicio:");

        jLabel12.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Observaciones");

        txtObservaciones.setColumns(20);
        txtObservaciones.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtObservaciones.setLineWrap(true);
        txtObservaciones.setRows(2);
        txtObservaciones.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        txtObservaciones.setDragEnabled(true);
        jScrollPane2.setViewportView(txtObservaciones);

        btnBuscarProductos.setBackground(new java.awt.Color(204, 204, 204));
        btnBuscarProductos.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnBuscarProductos.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar1.png"))); // NOI18N
        btnBuscarProductos.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnBuscarProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarProductos.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBuscarProductos.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnBuscarProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarProductosActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel8.setText("Hora Cita:");

        txthora.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txthora.setToolTipText("00:00");
        txthora.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        txthora.setName("Hora Cita"); // NOI18N
        txthora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txthoraActionPerformed(evt);
            }
        });
        txthora.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txthoraFocusLost(evt);
            }
        });
        txthora.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txthoraKeyReleased(evt);
            }
        });

        btnBuscarHoras.setBackground(new java.awt.Color(204, 204, 204));
        btnBuscarHoras.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnBuscarHoras.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarHoras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar1.png"))); // NOI18N
        btnBuscarHoras.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnBuscarHoras.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarHoras.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBuscarHoras.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnBuscarHoras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarHorasActionPerformed(evt);
            }
        });

        lblMedicoNuevo.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lblMedicoNuevo.setText("Encargado:");

        cmbMedicoNuevo.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        cmbMedicoNuevo.setName("Nuevo Medico"); // NOI18N
        cmbMedicoNuevo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbMedicoNuevoItemStateChanged(evt);
            }
        });

        lblNuevaFecha.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lblNuevaFecha.setText("Fecha:");

        try {
            dtNuevaCita.setDefaultPeriods(new datechooser.model.multiple.PeriodSet(new datechooser.model.multiple.Period(new java.util.GregorianCalendar(2014, 3, 1),
                new java.util.GregorianCalendar(2014, 3, 1))));
    } catch (datechooser.model.exeptions.IncompatibleDataExeption e1) {
        e1.printStackTrace();
    }
    dtNuevaCita.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 12));
    dtNuevaCita.addCommitListener(new datechooser.events.CommitListener() {
        public void onCommit(datechooser.events.CommitEvent evt) {
            dtNuevaCitaOnCommit(evt);
        }
    });
    dtNuevaCita.addSelectionChangedListener(new datechooser.events.SelectionChangedListener() {
        public void onSelectionChange(datechooser.events.SelectionChangedEvent evt) {
            dtNuevaCitaOnSelectionChange(evt);
        }
    });

    javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
    jPanel3.setLayout(jPanel3Layout);
    jPanel3Layout.setHorizontalGroup(
        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel3Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblMedicoNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(txthora, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnBuscarHoras, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblNuevaFecha)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(dtNuevaCita, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscarProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(cmbMedicoNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap())
    );
    jPanel3Layout.setVerticalGroup(
        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel3Layout.createSequentialGroup()
            .addGap(2, 2, 2)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnBuscarProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(3, 3, 3)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblMedicoNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(cmbMedicoNuevo))
            .addGap(2, 2, 2)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txthora))
                .addComponent(lblNuevaFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(dtNuevaCita, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBuscarHoras, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE))
            .addGap(18, 18, 18)
            .addComponent(jLabel12)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
            .addContainerGap())
    );

    txtIdProd.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
    txtIdProd.setDisabledTextColor(new java.awt.Color(51, 51, 51));
    txtIdProd.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyReleased(java.awt.event.KeyEvent evt) {
            txtIdProdKeyReleased(evt);
        }
    });

    txtIdTercero.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
    txtIdTercero.setDisabledTextColor(new java.awt.Color(51, 51, 51));
    txtIdTercero.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyReleased(java.awt.event.KeyEvent evt) {
            txtIdTerceroKeyReleased(evt);
        }
    });

    javax.swing.GroupLayout pnlInvisibleLayout = new javax.swing.GroupLayout(pnlInvisible);
    pnlInvisible.setLayout(pnlInvisibleLayout);
    pnlInvisibleLayout.setHorizontalGroup(
        pnlInvisibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(pnlInvisibleLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(txtIdProd, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(txtIdTercero, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    pnlInvisibleLayout.setVerticalGroup(
        pnlInvisibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(pnlInvisibleLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(txtIdTercero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addGroup(pnlInvisibleLayout.createSequentialGroup()
            .addComponent(txtIdProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, Short.MAX_VALUE))
    );

    txtIdProd.getAccessibleContext().setAccessibleName("IdProducto");
    txtIdTercero.getAccessibleContext().setAccessibleName("IdTercero");

    tblRegistros.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
    tblRegistros.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {
            {"No vino", null},
            {"Cancelada", null},
            {"Atendida", null},
            {"Reagendada", null}
        },
        new String [] {
            "Detalle Citas", "#"
        }
    ) {
        boolean[] canEdit = new boolean [] {
            false, false
        };

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }
    });
    tblRegistros.setRowHeight(22);
    jScrollPane3.setViewportView(tblRegistros);

    btnInasistencia.setBackground(new java.awt.Color(241, 148, 138));
    btnInasistencia.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
    btnInasistencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/borrar2.png"))); // NOI18N
    btnInasistencia.setText("Inasistencia");
    btnInasistencia.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    btnInasistencia.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
    btnInasistencia.setMargin(new java.awt.Insets(2, 14, 2, 5));
    btnInasistencia.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnInasistenciaActionPerformed(evt);
        }
    });

    btnAgregarProductos.setBackground(new java.awt.Color(204, 204, 204));
    btnAgregarProductos.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
    btnAgregarProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/producto.png"))); // NOI18N
    btnAgregarProductos.setText("Agregar productos");
    btnAgregarProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    btnAgregarProductos.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
    btnAgregarProductos.setMargin(new java.awt.Insets(2, 14, 2, 5));
    btnAgregarProductos.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnAgregarProductosActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
    jPanel6.setLayout(jPanel6Layout);
    jPanel6Layout.setHorizontalGroup(
        jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel6Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlInvisible, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtEncargado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(lbFoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnInasistencia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAgregarProductos, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE))))
            .addContainerGap())
    );
    jPanel6Layout.setVerticalGroup(
        jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel6Layout.createSequentialGroup()
            .addGap(0, 0, 0)
            .addComponent(txtEncargado)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addComponent(btnInasistencia)
                    .addGap(2, 2, 2)
                    .addComponent(btnAgregarProductos)
                    .addGap(3, 3, 3)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(pnlInvisible, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
    );

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
            .addGap(0, 0, Short.MAX_VALUE)
            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, 0)
            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txthoraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txthoraKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
//            cargarProducto(txtIdMotivo.getText());
        }
    }//GEN-LAST:event_txthoraKeyReleased

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        Object[] campos = {txtIdProd, txtIdTercero, txtId, txtCodigo, txtServicio};
        String faltantes = metodos.camposVacios(campos);

        if (!faltantes.equals("")) {
            metodos.msgAdvertencia(null, "No puede continuar, faltan los siguientes campos: " + faltantes);
            return;
        }

//        Object[][] agendas = sql.getRegistrosAgendasHora(metodos.fechaConsulta(metodos.fecha(fecha)), atiende, txthora.getText());
//        if (agendas.length > 0) {
//            metodos.msgError(null, "Ya hay una cita registrada a esta hora");
//            return;
//        }
        if (metodos.msgPregunta(null, "¿Desea continuar?") == 0) {

            boolean gestion;
            if (metodos.desdeDate(metodos.haciaDate(metodosGenerales.fecha())).equals(metodos.desdeDate(dtProxima.getCurrent()))
                    || metodos.desdeDate(dtProxima.getCurrent()).equals(metodos.fecha4(metodosGenerales.fecha()))) {
                gestion = true;
            } else {
                gestion = false;
            }

            String citaPeluqueria = "PELU-" + instancias.getSql().getNumConsecutivo("PELU")[0].toString();
            Object[] vector = {citaPeluqueria, txtIdTercero.getText(), fecha, txthora.getText(), txtIdProd.getText(), atiende,
                "Registrada", txtObservaciones.getText(), txtCodigo.getText(),
                metodos.desdeDate(dtProxima.getCurrent()), txtServicio.getText(), gestion};

            ndAgenda nodo = metodos.llenarAgenda(vector);

            if (!instancias.getSql().agregarPeluqueria(nodo)) {
                metodos.msgError(null, "Hubo un problema al guardar la Agenda");
                return;
            }

            if (!instancias.getSql().aumentarConsecutivo("PELU", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("PELU")[0]) + 1)) {
                metodos.msgError(null, "Hubo un problema al guardar en el consecutivo de la peluqueria");
                return;
            }
            metodos.msgExito(null, "Cita Registrada Exitosamente");
        } else {
            return;
        }

        instancias.getPeluqueria().cargarAgendas(instancias.getMedico());
        instancias.getPeluqueria().cargarAgendas();
        this.dispose();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnGuardarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnGuardarKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardarKeyReleased

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        if (txtEstado.getText().equals("Confirmada") || txtEstado.getText().equals("Atendido")) {
            metodos.msgError(null, "Esta cita se encuentra confirmada y no se puede modificar");
        } else {
            int op = 0;
            if (dtNuevaCita.isVisible()) {
                op = 1;
            }

            if (op == 0) {
                desactivarCita();
            } else {
                Object[] campos = {txtId, txthora, txtServicio};
                String faltantes = metodos.camposVacios(campos);

                if (!faltantes.equals("")) {
                    metodos.msgAdvertencia(null, "No puede continuar, faltan los siguientes campos: " + faltantes);
                    return;
                }
                if (metodos.msgPregunta(null, "¿Desea continuar?") == 0) {
                    String fechaNueva = metodos.desdeDate(dtNuevaCita.getCurrent());
                    String idCita = txtCita.getText();

                    boolean gestion;
                    if (metodos.desdeDate(metodos.haciaDate(metodosGenerales.fecha())).equals(metodos.desdeDate(dtProxima.getCurrent()))
                            || metodos.desdeDate(dtProxima.getCurrent()).equals(metodos.fecha4(metodosGenerales.fecha()))) {
                        gestion = true;
                    } else {
                        gestion = false;
                    }

                    Object[] vector = {idCita, txtIdTercero.getText(), fechaNueva,
                        txthora.getText(), txtIdProd.getText(), cmbMedicoNuevo.getSelectedItem(), "Reagendada",
                        txtObservaciones.getText(), txtCodigo.getText(), metodos.desdeDate(dtProxima.getCurrent()),
                        txtServicio.getText(), gestion
                    };

                    ndAgenda nodo = metodos.llenarAgenda(vector);
                    ndAgenda nodoAgenda = instancias.getSql().getDatosPeluqueriaVet(txtCita.getText());

                    Object[] vectorAntiguo = {idCita, fecha, nodoAgenda.getHora(),
                        nodoAgenda.getDescripcion(), nodoAgenda.getaCargo(), nodoAgenda.getObservacion()};

                    if (!instancias.getSql().AgregarPeluqueriaAntigua(vectorAntiguo)) {
                        metodos.msgError(null, "Hubo un problema al guardar la Agenda Antigüa");
                        return;
                    }

                    if (!instancias.getSql().modificarPeluqueria(nodo)) {
                        metodos.msgError(null, "Hubo un problema al guardar la Agenda");
                        return;
                    }

                    metodos.msgExito(null, "Cita Actualizada Exitosamente");

//                    if (metodos.msgPregunta(null, "¿Desea imprimir?") == 0) {
////                        instancias.getReporte().verCita(txtCita.getText(), instancias.getInformacionEmpresa());
//                    }
                    instancias.getPeluqueria().cargarAgendas(instancias.getMedico());
                    instancias.getPeluqueria().cargarAgendas();
                    this.dispose();
                }
            }
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        if (metodos.msgPregunta(null, "¿Desea limpiar la peluqueria?") == 0) {
            limpiar();
        }
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        if (txtEstado.getText().equals("Confirmada") || txtEstado.getText().equals("Atendido")) {
            metodos.msgError(null, "Esta cita se encuentra confirmada y no se puede cancelar");
        } else if (metodos.msgPregunta(null, "¿Cancelar esta cita?") == 0) {

            if (!instancias.getSql().modificarPeluqueria(txtCita.getText(), "Cancelada", "")) {
                metodos.msgError(null, "Error la cita no pudo ser cancelada");
                return;
            }
            metodos.msgExito(null, "Cita Cancelada");

            instancias.getPeluqueria().cargarAgendas(instancias.getMedico());
            instancias.getPeluqueria().cargarAgendas();
            this.dispose();
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtServicioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtServicioKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarProducto(txtServicio.getText());
        } else {
            txtIdProd.setText("");
        }
    }//GEN-LAST:event_txtServicioKeyReleased

    private void cmbMedicoNuevoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbMedicoNuevoItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbMedicoNuevoItemStateChanged

    private void dtNuevaCitaOnSelectionChange(datechooser.events.SelectionChangedEvent evt) {//GEN-FIRST:event_dtNuevaCitaOnSelectionChange

    }//GEN-LAST:event_dtNuevaCitaOnSelectionChange

    private void dtNuevaCitaOnCommit(datechooser.events.CommitEvent evt) {//GEN-FIRST:event_dtNuevaCitaOnCommit
        txthora.setText("");
    }//GEN-LAST:event_dtNuevaCitaOnCommit

    private void jPanel1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel1KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            this.dispose();
        }
    }//GEN-LAST:event_jPanel1KeyReleased

    private void btnFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarActionPerformed
        if (txtEstado.getText().equals("Atendido")) {
            metodos.msgError(null, "Esta cita ya se encuentra Finalizada");
            return;
        }

        if (metodos.msgPregunta(null, "¿Desea continuar?") == 0) {
            this.dispose();

            boolean gestion;
            if (metodos.desdeDate(metodos.haciaDate(metodosGenerales.fecha())).equals(metodos.desdeDate(dtProxima.getCurrent()))
                    || metodos.desdeDate(dtProxima.getCurrent()).equals(metodos.fecha4(metodosGenerales.fecha()))) {
                gestion = true;
            } else {
                gestion = false;
            }

            instancias.getSql().modificarProximoControl(txtCita.getText(),
                    metodos.desdeDate(dtProxima.getCurrent()), gestion);

            instancias.getFactura().desdePeluqueria(txtIdTercero.getText(), txtIdProd.getText(), txtCita.getText());
            instancias.getFactura().setVisible(true);
            instancias.getFacturaContenedor().setSelected(true);
//            instancias.getFacturaContenedor().setEnabled(true);
//            instancias.getFacturaContenedor().setVisible(true);
        }
    }//GEN-LAST:event_btnFinalizarActionPerformed

    private void txthoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txthoraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txthoraActionPerformed

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        if (txtEstado.getText().equals("Confirmada") || txtEstado.getText().equals("Atendido")) {
            metodos.msgError(null, "Esta cita ya se encuentra confirmada");
            return;
        }

        if (metodos.msgPregunta(null, "¿Desea continuar?") == 0) {
            if (!instancias.getSql().modificarPeluqueria(txtCita.getText(), "Confirmada", "")) {
                metodos.msgError(null, "No fue posible modificar el estado de la Cita");
                return;
            }
            metodos.msgExito(null, "Cita Confirmada");
            instancias.getPeluqueria().cargarAgendas(instancias.getMedico());
            instancias.getPeluqueria().cargarAgendas();
            this.dispose();
        }
    }//GEN-LAST:event_btnConfirmarActionPerformed

    private void txthoraFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txthoraFocusLost
        if (!revisarHora()) {
            if (!txthora.getText().equals("")) {
                metodos.msgError(null, "Por favor ingrese una hora valida: '00:00'.");
                txthora.requestFocus();
            }
        }
    }//GEN-LAST:event_txthoraFocusLost

    private void btnInasistenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInasistenciaActionPerformed
        if (txtEstado.getText().equals("Confirmada") || txtEstado.getText().equals("Atendido")) {
            metodos.msgError(null, "Esta cita ya se encuentra confirmada");
        } else if (metodos.msgPregunta(null, "¿Dar por inasistencia?") == 0) {

            if (!instancias.getSql().modificarPeluqueria(txtCita.getText(), "No vino", "")) {
                metodos.msgError(null, "Error al inactivar la cita");
                return;
            }
            metodos.msgExito(null, "Cita inactivada");
            instancias.getPeluqueria().cargarAgendas(instancias.getMedico());
            instancias.getPeluqueria().cargarAgendas();
            this.dispose();
        }
    }//GEN-LAST:event_btnInasistenciaActionPerformed

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        this.dispose();
    }//GEN-LAST:event_jLabel13MouseClicked

    private void btnBuscTercerosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscTercerosActionPerformed
        ventanaTerceros("");
    }//GEN-LAST:event_btnBuscTercerosActionPerformed

    private void txtRazaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRazaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRazaActionPerformed

    private void txtRazaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRazaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRazaKeyReleased

    private void txtRazaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRazaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRazaKeyTyped

    private void txtGeneroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGeneroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGeneroActionPerformed

    private void txtGeneroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGeneroKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGeneroKeyReleased

    private void txtGeneroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGeneroKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGeneroKeyTyped

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

        }
    }//GEN-LAST:event_txtNombreKeyReleased

    private void txtCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarMascota(txtCodigo.getText(), true);
        } else {
            txtNombrePropietario.setText("");
            txtTelefono.setText("");
            txtCodigo.setText("");
            txtRaza.setText("");
            txtNombre.setText("");
            txtGenero.setText("");
            txtEdad.setText("");
            txtDieta.setText("");
            txtTemp.setText("");
            txtEspecie.setText("");
            txtObv.setText("");
        }
    }//GEN-LAST:event_txtCodigoKeyReleased

    private void txtDietaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDietaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDietaActionPerformed

    private void txtDietaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDietaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDietaKeyReleased

    private void txtDietaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDietaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDietaKeyTyped

    private void txtEdadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEdadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEdadActionPerformed

    private void txtEdadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEdadKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEdadKeyReleased

    private void txtEdadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEdadKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEdadKeyTyped

    private void txtTempActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTempActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTempActionPerformed

    private void txtTempKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTempKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTempKeyReleased

    private void txtTempKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTempKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTempKeyTyped

    private void txtEspecieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEspecieActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEspecieActionPerformed

    private void txtEspecieKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEspecieKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEspecieKeyReleased

    private void txtEspecieKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEspecieKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEspecieKeyTyped

    private void txtNombrePropietarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombrePropietarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombrePropietarioActionPerformed

    private void txtNombrePropietarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombrePropietarioKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombrePropietarioKeyReleased

    private void txtTelefonoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoKeyReleased

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoKeyTyped

    private void txtIdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarCliente(txtId.getText(), true);
        } else {
            txtCodigo.setEnabled(true);
            txtNombrePropietario.setText("");
            txtTelefono.setText("");
            txtCodigo.setText("");
            txtRaza.setText("");
            txtNombre.setText("");
            txtGenero.setText("");
            txtEdad.setText("");
            txtDieta.setText("");
            txtTemp.setText("");
            txtEspecie.setText("");
            txtObv.setText("");
        }
    }//GEN-LAST:event_txtIdKeyReleased

    private void btnBuscTerceros1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscTerceros1ActionPerformed
        ventanaMascotas(txtCodigo.getText());
    }//GEN-LAST:event_btnBuscTerceros1ActionPerformed

    private void txtCelularKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCelularKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCelularKeyReleased

    private void txtCelularKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCelularKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCelularKeyTyped

    private void btnBuscarProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProductosActionPerformed
        ventanaProductos("");
    }//GEN-LAST:event_btnBuscarProductosActionPerformed

    private void txtIdProdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdProdKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdProdKeyReleased

    private void txtIdTerceroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdTerceroKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdTerceroKeyReleased

    private void btnBuscarHorasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarHorasActionPerformed
        setaCargo(txtEncargado.getText());
        if (dtNuevaCita.isVisible()) {
            setaCargo(cmbMedicoNuevo.getSelectedItem().toString());
        }

        ventanaHoras(dtNuevaCita.getText());
    }//GEN-LAST:event_btnBuscarHorasActionPerformed

    private void txtEstadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEstadoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEstadoKeyReleased

    private void btnAgregarProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarProductosActionPerformed
        dlgAgregarProductosHistorial productos = new dlgAgregarProductosHistorial(null, true, txtCita.getText(), txtIdTercero.getText(),
                txtCodigo.getText());
        this.dispose();
        productos.setVisible(true);
    }//GEN-LAST:event_btnAgregarProductosActionPerformed

    private void dtProximaOnCommit(datechooser.events.CommitEvent evt) {//GEN-FIRST:event_dtProximaOnCommit
        // TODO add your handling code here:
    }//GEN-LAST:event_dtProximaOnCommit

    private void dtProximaOnSelectionChange(datechooser.events.SelectionChangedEvent evt) {//GEN-FIRST:event_dtProximaOnSelectionChange
        // TODO add your handling code here:
    }//GEN-LAST:event_dtProximaOnSelectionChange

    public void ventanaMascotas(String nit) {
        buscMascotas buscar = new buscMascotas(instancias.getMenu(), rootPaneCheckingEnabled, "", txtId.getText());
        buscar.setLocationRelativeTo(null);
        instancias.setBuscMascotas(buscar);
        instancias.setCampoActual(txtCodigo);
        txtCodigo.requestFocus();
        buscar.setInstancia(instancias);
        buscar.noEncontrado(nit);
        buscar.show();
    }

    private void limpiar() {
        String estado = txtEstado.getText();
        String hora = txthora.getText();

        for (int x = 0; x < jPanel1.getComponentCount(); x++) {
            if (jPanel1.getComponent(x) instanceof JTextField) {
                JTextField textField = (JTextField) jPanel1.getComponent(x);
                textField.setText("");
            }
        }

        txtCita.setText(instancias.getSql().getNumConsecutivo("PELU")[0].toString());
        txtCodigo.setEnabled(true);
        txtNombrePropietario.setText("");
        txtTelefono.setText("");
        txtCelular.setText("");
        txtCodigo.setText("");
        txtRaza.setText("");
        txtNombre.setText("");
        txtGenero.setText("");
        txtEdad.setText("");
        txtDieta.setText("");
        txtTemp.setText("");
        txtEspecie.setText("");
        txtObv.setText("");

        txtServicio.setText("");
        txthora.setText("");
        txtIdProd.setText("");
        txtIdTercero.setText("");
        txtId.setText("");
        txtObservaciones.setText("");

        lbFoto.setIcon(null);
        lbFoto.repaint();

        txtEstado.setText(estado);
        txthora.setText(hora);
    }

    private boolean revisarHora() {

        boolean sino = false;

        int cant = txthora.getText().length();

        if (cant == 2) {
            String splitini = txthora.getText().split(":")[0];
            String hora = txthora.getText();
            int cantidadSplit = splitini.length();
            if (cantidadSplit == 1) {
                hora = "" + hora;
            }
            try {
                if (Integer.parseInt(hora) > 23) {
                    hora = "23";
                }
            } catch (Exception e) {
                hora = "00";
            }
            txthora.setText(hora);
            sino = false;
        } else if (cant == 3) {
            String splitini = txthora.getText().split(":")[0];
            try {
                String split = txthora.getText().split(":")[1];
                if (splitini.length() == 1) {
                    splitini = "" + splitini;
                }
                if (split.length() == 1) {
                    split = "0" + split;
                }
                txthora.setText(splitini + ":" + split);
                sino = true;
            } catch (Exception e) {
                txthora.setText(splitini + ":00");
            }

        } else if (cant > 3) {
            String splitini = txthora.getText().split(":")[0];

            String split = txthora.getText().split(":")[1];

            if (splitini.length() == 1) {
                splitini = "" + splitini;
            }

            if (Integer.parseInt(splitini) > 23) {
                splitini = "23";
            }

            if (split.length() == 1) {
                split = split + "0";
            }

            if (split.length() > 2) {
                split = split.substring(1);
            }
            try {
                if (Integer.parseInt(split) > 59) {
                    split = "59";
                }
            } catch (Exception e) {
                split = "00";
            }
            txthora.setText(splitini + ":" + split);
            sino = true;
        }
        return sino;
    }

    public void desactivarCita() {
        btnGuardar.setEnabled(false);

        dtNuevaCita.setVisible(true);
        dtNuevaCita.setSelectedDate(metodos.haciaDate(fecha));

        dtNuevaCita.setVisible(true);
        lblNuevaFecha.setVisible(true);
        btnBuscarHoras.setVisible(true);
        cmbMedicoNuevo.setEnabled(true);

//        btnBuscarHoras.setEnabled(true);
        txtObservaciones.setEnabled(true);
//        btnFinalizar.setEnabled(false);
        txtServicio.setEnabled(true);
//        txthora.setText("");
//        txthora.setEnabled(true);
    }

    public void ventanaHoras(String fecha) {
        buscHorasPeluqueria buscar = new buscHorasPeluqueria(null, rootPaneCheckingEnabled);
        buscar.setLocationRelativeTo(null);
        instancias.setBuscarHorasPeluqueria(buscar);
        instancias.setValor(fecha);
        instancias.setCampoActual(txthora);
        buscar.setInstancia(instancias);
        txthora.requestFocus();
        buscar.show();
    }

    public void ventanaTerceros(String nit) {
        buscClientes buscar = new buscClientes(null, rootPaneCheckingEnabled, false, null, "");
        buscar.setLocationRelativeTo(null);
        instancias.setBusClientes(buscar);
        instancias.setCampoActual(txtId);
        txtId.requestFocus();
        buscar.noEncontrado(nit);
        buscar.show();
    }

    public void cargarNuevoPaciente(String id, String nombre, String telefono, String cel) {
        txtId.setText(id);
        txtNombrePropietario.setText(nombre);
        txtTelefono.setText(telefono);
        txtCelular.setText(cel);
    }

    public void cargarMascota(String nit, Boolean abrirBuscador) {

        ndMascota nodo = instancias.getSql().getDatosMascota(nit);
        if (nodo.getCodigo() != null) {

            cargarCliente(nodo.getCliente(), false);

            txtCodigo.setEnabled(false);
            txtCodigo.setText(nodo.getCodigo());
            txtNombre.setText(nodo.getNombre());
            txtRaza.setText(nodo.getRaza());
            txtGenero.setText(nodo.getGenero());
            txtObv.setText(nodo.getObservacion());
            txtDieta.setText(nodo.getAlimentacion());
            txtTemp.setText(nodo.getTemperamento());
            txtEspecie.setText(nodo.getExpecie());

            String fecha = nodo.getEdad();
            if (fecha.length() == 9) {
                fecha = "0" + fecha;
            }

            int anho = metodosGenerales.calcularEdad(fecha);
            int meses = metodosGenerales.calcularEdadMeses(fecha);
            if (anho > 0) {
                txtEdad.setText(anho + " años y " + meses + " meses");
            } else {
                txtEdad.setText(meses + " meses");
            }

            ImageIcon fot = new ImageIcon(Paths.get(System.getProperty("user.dir") + "\\imagenes\\veterinaria\\mascotas\\IMG-" + nodo.getFoto() + ".jpg").toString());
            Icon icono = new ImageIcon(fot.getImage().getScaledInstance(lbFoto.getWidth(), lbFoto.getHeight(), Image.SCALE_DEFAULT));
            lbFoto.setIcon(icono);
            this.repaint();

            if (abrirBuscador) {
                ventanaProductos("");
            }

            return;
        }
        ventanaMascotas(nit);
    }

    public void cargarCliente(String nit, Boolean abrirBuscador) {

        ModeloContacto nodo = instancias.getSql().getDatosTercero(nit);

        if (nodo.getId() != null) {
            if (nodo.isActivo()) {
                metodos.msgError(null, "Este cliente esta inactivado");
                lbNit4.requestFocus();
                return;
            }

            txtId.setText(nodo.getId());
            txtIdTercero.setText(nodo.getIdSistema());
            txtTelefono.setText(nodo.getTelefono());
            txtNombrePropietario.setText(nodo.getNombre());
            txtCelular.setText(nodo.getCelular());
            txtServicio.requestFocus();
            txtCodigo.setText(nodo.getId());

            Object[][] registroCitas = instancias.getSql().getRegistroPeluqueria(nodo.getIdSistema());
            tblRegistros.setValueAt(big.setNumero(big.getBigDecimal(registroCitas[0][0])), 2, 1);
            tblRegistros.setValueAt(big.setNumero(big.getBigDecimal(registroCitas[0][1])), 0, 1);
            tblRegistros.setValueAt(big.setNumero(big.getBigDecimal(registroCitas[0][2])), 3, 1);
            tblRegistros.setValueAt(big.setNumero(big.getBigDecimal(registroCitas[0][3])), 1, 1);

            if (abrirBuscador) {
                ventanaMascotas(nodo.getId());
            }

            return;
        }

        ventanaTerceros(nit);
    }

    public void cargarClienteAgenda() {

        btnInasistencia.setEnabled(true);
        txtServicio.setEnabled(false);
        txtId.setEnabled(false);
        txtObservaciones.setEnabled(false);
        txthora.setEnabled(false);
        btnLimpiar.setEnabled(false);

        ndAgenda nodoAgenda = instancias.getSql().getDatosPeluqueriaVet("PELU-" + txtCita.getText());
        ModeloContacto nodoCliente = instancias.getSql().getDatosTercero(nodoAgenda.getCliente());

        if (nodoCliente.getId() != null) {
            txtId.setText(nodoAgenda.getCliente());
            cargarCliente(nodoAgenda.getCliente(), false);
        } else {
            Object[][] nuevoTercero = instancias.getSql().getNuevoTercero(nodoAgenda.getCliente());
            nodoAgenda = instancias.getSql().getDatosAgenda(txtCita.getText());
            txtCita.setText(nodoAgenda.getId());

            String nombre1, nombre2, nombre3, nombre4;

            try {
                nombre1 = nuevoTercero[0][1].toString();
            } catch (Exception e) {
                nombre1 = "";
            }

            try {
                nombre2 = nuevoTercero[0][2].toString();
            } catch (Exception e) {
                nombre2 = "";
            }

            try {
                nombre3 = nuevoTercero[0][3].toString();
            } catch (Exception e) {
                nombre3 = "";
            }

            try {
                nombre4 = nuevoTercero[0][4].toString();
            } catch (Exception e) {
                nombre4 = "";
            }

            txtNombrePropietario.setText(nombre1 + " " + nombre2 + " " + nombre3 + " " + nombre4);
            txtId.setText(nuevoTercero[0][0].toString());

            if (nuevoTercero[0][5] != null) {
                txtTelefono.setText(nuevoTercero[0][5].toString());
            }

            if (nuevoTercero[0][6] != null) {
                txtCelular.setText(nuevoTercero[0][6].toString());
            }
        }

        cargarMascota(nodoAgenda.getMascota(), false);
        txtEstado.setText(nodoAgenda.getEstado());
        txtCita.setText(nodoAgenda.getId());
        txthora.setText(nodoAgenda.getHora());
        txtIdProd.setText(nodoAgenda.getMotivo());

        try {
            ndProducto producto = instancias.getSql().getDatosProducto(nodoAgenda.getMotivo(), "bdProductos");
            if (!producto.getCodigo().equalsIgnoreCase("")) {
                cargarProducto(nodoAgenda.getMotivo());
            }
        } catch (Exception e) {
            txtServicio.setText("");
        }

        txtObservaciones.setText(nodoAgenda.getObservacion());

        if (txtEstado.getText().equals("Confirmada") || txtEstado.getText().equals("Atendido")) {
            if (txtEstado.getText().equals("Atendido")) {
                btnAgregarProductos.setEnabled(false);
                btnFinalizar.setEnabled(false);
//                btnPasarOrden.setEnabled(false);
            } else {
                lbProxima.setVisible(true);
                dtProxima.setVisible(true);
                btnFinalizar.setEnabled(true);
                btnAgregarProductos.setEnabled(true);
//                btnPasarOrden.setEnabled(true);
            }

            btnInasistencia.setEnabled(false);
            btnGuardar.setEnabled(false);
            btnCancelar.setEnabled(false);
            btnModificar.setEnabled(false);
            btnConfirmar.setEnabled(false);
//            btnFacturar.setEnabled(false);
        } else if (txtEstado.getText().equals("No vino")) {
            btnAgregarProductos.setEnabled(false);
            btnLimpiar.setEnabled(false);
            btnGuardar.setEnabled(false);
            btnModificar.setEnabled(false);
            btnConfirmar.setEnabled(false);
            btnFinalizar.setEnabled(false);
            btnCancelar.setEnabled(false);
            btnInasistencia.setEnabled(false);
//            btnFacturar.setEnabled(false);
//            btnPasarOrden.setEnabled(false);
        } else {
            if (txtEstado.getText().equals("Registrada") || txtEstado.getText().equals("Reagendada")) {
                btnGuardar.setEnabled(false);
            }

            btnAgregarProductos.setEnabled(false);
            btnCancelar.setEnabled(true);
            btnModificar.setEnabled(true);
            btnConfirmar.setEnabled(true);
//            btnFacturar.setEnabled(true);
//            btnPasarOrden.setEnabled(true);
            btnFinalizar.setEnabled(false);
        }
    }

//    public void ventanaProductos(String codigo) {
//        buscProductos buscar = new buscProductos(null, rootPaneCheckingEnabled, false);
//        buscar.setLocationRelativeTo(null);
//        buscar.setClaseBuscador("Agenda");
//        buscar.cargarTablaProductos(false);
//        instancias.setBusProductos(buscar);
//        instancias.setCampoActual(txtIdMotivo);
//        txtIdMotivo.requestFocus();
//        buscar.noEncontrado(codigo.replace("'", "//"));
//        buscar.show();
//    }
    public void ventanaProductos(String codigo) {
        buscProductos buscar = new buscProductos(null, true, false, "peluqueria", "productos1");
        buscar.setLocationRelativeTo(null);
        instancias.setBusProductos(buscar);
        instancias.setCampoActual(txtServicio);
        txtServicio.requestFocus();
        buscar.noEncontrado(codigo.replace("'", "//"));
        buscar.show();
    }

    public void cargarProducto(String cod) {

        ndProducto nodo = null;

        String CodigoProd = "";
        if (cod.equals("")) {
            CodigoProd = "";
        } else {
            Object[][] listado = instancias.getSql().getCodigosRelacionados(cod, " where codigo");
            if (listado.length > 0) {
                cod = listado[0][0].toString();
            }

            nodo = instancias.getSql().getDatosProducto(cod, "bdProductos");
            if (nodo.getIdSistema() != null) {
                CodigoProd = nodo.getIdSistema();
            }
        }

        if (!CodigoProd.equals("")) {
            txtServicio.setText(nodo.getDescripcion());
            txtIdProd.setText(nodo.getIdSistema());
            txtObservaciones.requestFocus();
            return;
        }
        ventanaProductos(cod);
    }

    public void confirmarFacturarAgenda() {

        ModeloContacto nodo = instancias.getSql().getDatosTercero(txtId.getText());

        if (nodo.getId() != null) {
            instancias.getFactura().cargarClienteYProducto(txtId.getText(), new String[][]{new String[]{txtServicio.getText(), "1"}});
            try {
                instancias.getFacturaContenedor().setSelected(true);
            } catch (Exception e) {
            }
        } else {
            instancias.getFactura().cargarClienteYProducto("1010", new String[][]{new String[]{txtServicio.getText(), "1"}});
            if (metodos.msgPregunta(null, "¿Desea registrar este cliente?") == 0) {
                try {
                    instancias.getClientes().setSelected(true);
                } catch (Exception e) {
                }
            } else {
                try {
                    instancias.getFacturaContenedor().setSelected(true);
                } catch (Exception e) {
                }
            }
        }

        this.dispose();
    }

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
            java.util.logging.Logger.getLogger(infPeluqueriaInfo.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(infPeluqueriaInfo.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(infPeluqueriaInfo.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(infPeluqueriaInfo.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                infPeluqueriaInfo dialog = new infPeluqueriaInfo(new javax.swing.JFrame(), true, "", "", "", 0, 0, "");
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
    private javax.swing.JButton btnAgregarProductos;
    private javax.swing.JButton btnBuscTerceros;
    private javax.swing.JButton btnBuscTerceros1;
    private javax.swing.JButton btnBuscarHoras;
    private javax.swing.JButton btnBuscarProductos;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConfirmar;
    private javax.swing.JButton btnFinalizar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnInasistencia;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JComboBox cmbMedicoNuevo;
    private datechooser.beans.DateChooserCombo dtNuevaCita;
    private datechooser.beans.DateChooserCombo dtProxima;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JLabel lbDireccion4;
    private javax.swing.JLabel lbDireccion5;
    private javax.swing.JLabel lbDireccion6;
    private javax.swing.JLabel lbEmail10;
    private javax.swing.JLabel lbEmail11;
    private javax.swing.JLabel lbEmail12;
    private javax.swing.JLabel lbEmail8;
    private javax.swing.JLabel lbFoto;
    private javax.swing.JLabel lbNit;
    private javax.swing.JLabel lbNit2;
    private javax.swing.JLabel lbNit3;
    private javax.swing.JLabel lbNit4;
    private javax.swing.JLabel lbNit5;
    private javax.swing.JLabel lbProxima;
    private javax.swing.JLabel lbRazon;
    private javax.swing.JLabel lbRazon1;
    private javax.swing.JLabel lblMedicoNuevo;
    private javax.swing.JLabel lblNuevaFecha;
    private javax.swing.JPanel pnlInvisible;
    private javax.swing.JTable tblRegistros;
    private javax.swing.JTextField txtCelular;
    private javax.swing.JLabel txtCita;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDieta;
    private javax.swing.JTextField txtEdad;
    private javax.swing.JLabel txtEncargado;
    private javax.swing.JTextField txtEspecie;
    private javax.swing.JTextField txtEstado;
    private javax.swing.JTextField txtGenero;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtIdProd;
    private javax.swing.JTextField txtIdTercero;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNombrePropietario;
    private javax.swing.JTextArea txtObservaciones;
    private javax.swing.JTextArea txtObv;
    private javax.swing.JTextField txtRaza;
    private javax.swing.JTextField txtServicio;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtTemp;
    private javax.swing.JTextField txthora;
    // End of variables declaration//GEN-END:variables
}
