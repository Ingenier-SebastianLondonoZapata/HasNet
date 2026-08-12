package formularios.Agenda;

import Modelo.Terceros.ModeloContacto;
import clases.Instancias;
import Utilidades.BaseDatos.SQL;
import clases.big;
import clases.metodosGenerales;
import clases.productos.ndProducto;
import formularios.Agenda.ndAgenda;
import Vista.Productos.VistaBuscadorProductos;
import formularios.terceros.buscClientes;
import formularios.terceros.nuevoTercero;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

public class infInfoAgenda extends javax.swing.JDialog {

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

    public infInfoAgenda(java.awt.Frame parent, boolean modal, String atiende, String fecha, String hora, int fila, int columna, String campoTabla) {
        super(parent, modal);
        initComponents();

        instancias = Instancias.getInstancias();

        pnlInvisible.setVisible(false);

        dtNuevaCita.setVisible(false);
        lblNuevaFecha.setVisible(false);
        lblMedicoNuevo.setVisible(false);
        cmbMedicoNuevo.setVisible(false);
        btnFacturar.setEnabled(false);
        btnCancelar.setEnabled(false);
        btnConfirmar.setEnabled(false);
        btnFinalizar.setEnabled(false);
        btnInasistencia.setEnabled(false);
        btnModificar.setEnabled(false);
        btnPasarOrden.setEnabled(false);
        dtNuevaCita.setSelectedDate(Calendar.getInstance());
        this.atiende = atiende;
        this.fecha = fecha;
        txthora.setText(hora);
        txtEncargado.setText(atiende);

        if (campoTabla.equals("")) {
            txtCita.setText(instancias.getSql().getNumConsecutivo("AGENDA")[0].toString());
//            btnBuscarHoras.setEnabled(true);
        } else {
            String[] campo = campoTabla.split("-");
            txtCita.setText(campo[0]);
            cargarClienteAgenda();
//            btnBuscarHoras.setEnabled(false);
        }

        if (instancias.getConfiguraciones().isMedico() || instancias.getConfiguraciones().isOftalmologia()) {
            btnPasarOrden.setVisible(true);
        } else {
            btnPasarOrden.setVisible(false);
        }

        if (instancias.getConfiguraciones().isMedico()) {
//            lbtipo1.setText("PROFESIONAL");
            btnFacturar.setVisible(false);
        }

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
                    case "terceros":
                        if ((btnBuscarPaciente.isEnabled()) && (btnBuscarPaciente.isVisible())) {
                            btnBuscarPacienteActionPerformed(null);
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
        jPanel2 = new javax.swing.JPanel();
        btnLimpiar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnConfirmar = new javax.swing.JButton();
        btnFinalizar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnFacturar = new javax.swing.JButton();
        btnPasarOrden = new javax.swing.JButton();
        btnInasistencia = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        lbltipodoc = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtpNombre = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtfechaNacimiento = new javax.swing.JTextField();
        txtEdad = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtNota = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        txtCelular = new javax.swing.JTextField();
        btnBuscarPaciente = new javax.swing.JButton();
        pnlInvisible = new javax.swing.JPanel();
        txtIdSistema = new javax.swing.JTextField();
        txtCita = new javax.swing.JTextField();
        txtIdProd = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel5 = new javax.swing.JPanel();
        lblNuevaFecha = new javax.swing.JLabel();
        lblMedicoNuevo = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        dtNuevaCita = new datechooser.beans.DateChooserCombo();
        cmbMedicoNuevo = new javax.swing.JComboBox();
        txtEstado = new javax.swing.JTextField();
        txthora = new javax.swing.JTextField();
        txtIdMotivo = new javax.swing.JTextField();
        txtmotivo = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtObservaciones = new javax.swing.JTextArea();
        btnBuscarHoras = new javax.swing.JButton();
        btnBuscarProductos = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblRegistros = new javax.swing.JTable();
        lbFoto = new javax.swing.JLabel();
        txtEncargado = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jPanel1KeyReleased(evt);
            }
        });

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
        btnConfirmar.setFont(new java.awt.Font("Calibri", 1, 15)); // NOI18N
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
        btnFinalizar.setText("FINALIZAR");
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

        btnFacturar.setBackground(new java.awt.Color(46, 204, 113));
        btnFacturar.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnFacturar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/historia.png"))); // NOI18N
        btnFacturar.setText("FACTURAR");
        btnFacturar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFacturar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnFacturar.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnFacturar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFacturarActionPerformed(evt);
            }
        });

        btnPasarOrden.setBackground(new java.awt.Color(204, 204, 204));
        btnPasarOrden.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnPasarOrden.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/historia.png"))); // NOI18N
        btnPasarOrden.setText("PASAR ORDEN");
        btnPasarOrden.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPasarOrden.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnPasarOrden.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnPasarOrden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPasarOrdenActionPerformed(evt);
            }
        });

        btnInasistencia.setBackground(new java.awt.Color(241, 148, 138));
        btnInasistencia.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnInasistencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/borrar2.png"))); // NOI18N
        btnInasistencia.setText("NO VINO");
        btnInasistencia.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnInasistencia.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnInasistencia.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnInasistencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInasistenciaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnFinalizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnConfirmar, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnModificar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addComponent(btnFacturar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnInasistencia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnPasarOrden, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLimpiar)
                    .addComponent(btnGuardar))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModificar)
                    .addComponent(btnConfirmar))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFinalizar)
                    .addComponent(btnCancelar))
                .addGap(3, 3, 3)
                .addComponent(btnInasistencia)
                .addGap(3, 3, 3)
                .addComponent(btnPasarOrden)
                .addGap(3, 3, 3)
                .addComponent(btnFacturar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        lbltipodoc.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbltipodoc.setText("Tipo documento:");
        lbltipodoc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lbltipodocKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel3.setText("Nombre Completo:");

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel2.setText("Telefono:");

        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel4.setText("Fecha Nac. :");

        jLabel5.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel5.setText("Edad:");

        txtId.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtId.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        txtId.setName("Tipo Documento"); // NOI18N
        txtId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdKeyReleased(evt);
            }
        });

        txtpNombre.setEditable(false);
        txtpNombre.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtpNombre.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        txtpNombre.setName("Primer Nombre"); // NOI18N

        txtTelefono.setEditable(false);
        txtTelefono.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtTelefono.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        txtTelefono.setName("Telefono"); // NOI18N

        txtfechaNacimiento.setEditable(false);
        txtfechaNacimiento.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtfechaNacimiento.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        txtfechaNacimiento.setName("Telefono"); // NOI18N
        txtfechaNacimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtfechaNacimientoActionPerformed(evt);
            }
        });

        txtEdad.setEditable(false);
        txtEdad.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtEdad.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        txtEdad.setName("Telefono"); // NOI18N

        txtNota.setEditable(false);
        txtNota.setColumns(20);
        txtNota.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNota.setLineWrap(true);
        txtNota.setRows(2);
        txtNota.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtNota.setEnabled(false);
        jScrollPane1.setViewportView(txtNota);

        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel6.setText("Cel:");

        txtCelular.setEditable(false);
        txtCelular.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCelular.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        txtCelular.setName("Telefono"); // NOI18N

        btnBuscarPaciente.setBackground(new java.awt.Color(204, 204, 204));
        btnBuscarPaciente.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnBuscarPaciente.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar1.png"))); // NOI18N
        btnBuscarPaciente.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnBuscarPaciente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarPaciente.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBuscarPaciente.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnBuscarPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarPacienteActionPerformed(evt);
            }
        });

        pnlInvisible.setBackground(new java.awt.Color(0, 255, 255));

        txtIdSistema.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        txtIdSistema.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdSistemaKeyReleased(evt);
            }
        });

        txtCita.setEditable(false);
        txtCita.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        txtCita.setName("Tipo Documento"); // NOI18N
        txtCita.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCitaKeyReleased(evt);
            }
        });

        txtIdProd.setEditable(false);
        txtIdProd.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        txtIdProd.setName("Tipo Documento"); // NOI18N

        javax.swing.GroupLayout pnlInvisibleLayout = new javax.swing.GroupLayout(pnlInvisible);
        pnlInvisible.setLayout(pnlInvisibleLayout);
        pnlInvisibleLayout.setHorizontalGroup(
            pnlInvisibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInvisibleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtIdSistema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtIdProd, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );
        pnlInvisibleLayout.setVerticalGroup(
            pnlInvisibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInvisibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtIdSistema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtCita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtIdProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jSeparator2)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbltipodoc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtpNombre)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnBuscarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(pnlInvisible, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                            .addComponent(txtfechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 1, Short.MAX_VALUE))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbltipodoc, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBuscarPaciente))
                    .addComponent(pnlInvisible, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtpNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtfechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        lblNuevaFecha.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lblNuevaFecha.setText("Nueva fecha:");

        lblMedicoNuevo.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblMedicoNuevo.setText("Nuevo encargado:");

        jLabel9.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel9.setText("Estado:");

        jLabel8.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel8.setText("Hora cita:");

        jLabel7.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel7.setText("Servicio:");

        jLabel12.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Observación");

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

    cmbMedicoNuevo.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
    cmbMedicoNuevo.setName("Nuevo Medico"); // NOI18N
    cmbMedicoNuevo.addItemListener(new java.awt.event.ItemListener() {
        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            cmbMedicoNuevoItemStateChanged(evt);
        }
    });

    txtEstado.setEditable(false);
    txtEstado.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
    txtEstado.setText("Registrada");
    txtEstado.setDisabledTextColor(new java.awt.Color(51, 51, 51));
    txtEstado.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyReleased(java.awt.event.KeyEvent evt) {
            txtEstadoKeyReleased(evt);
        }
    });

    txthora.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
    txthora.setToolTipText("00:00");
    txthora.setDisabledTextColor(new java.awt.Color(51, 51, 51));
    txthora.setName("Hora Cita"); // NOI18N
    txthora.addFocusListener(new java.awt.event.FocusAdapter() {
        public void focusLost(java.awt.event.FocusEvent evt) {
            txthoraFocusLost(evt);
        }
    });
    txthora.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            txthoraActionPerformed(evt);
        }
    });
    txthora.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyReleased(java.awt.event.KeyEvent evt) {
            txthoraKeyReleased(evt);
        }
    });

    txtIdMotivo.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
    txtIdMotivo.setDisabledTextColor(new java.awt.Color(51, 51, 51));
    txtIdMotivo.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyReleased(java.awt.event.KeyEvent evt) {
            txtIdMotivoKeyReleased(evt);
        }
    });

    txtmotivo.setEditable(false);
    txtmotivo.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
    txtmotivo.setDisabledTextColor(new java.awt.Color(51, 51, 51));
    txtmotivo.setName("Motivo Consulta"); // NOI18N
    txtmotivo.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyReleased(java.awt.event.KeyEvent evt) {
            txtmotivoKeyReleased(evt);
        }
    });

    txtObservaciones.setColumns(20);
    txtObservaciones.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
    txtObservaciones.setLineWrap(true);
    txtObservaciones.setRows(2);
    txtObservaciones.setText(" ");
    txtObservaciones.setDisabledTextColor(new java.awt.Color(51, 51, 51));
    txtObservaciones.setDragEnabled(true);
    jScrollPane2.setViewportView(txtObservaciones);

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

    javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
    jPanel5.setLayout(jPanel5Layout);
    jPanel5Layout.setHorizontalGroup(
        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel5Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblNuevaFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblMedicoNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(dtNuevaCita, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtIdMotivo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                                .addComponent(txthora, javax.swing.GroupLayout.Alignment.LEADING))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnBuscarHoras, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                                .addComponent(btnBuscarProductos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(cmbMedicoNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtmotivo, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE))
                    .addGap(0, 0, Short.MAX_VALUE))))
    );
    jPanel5Layout.setVerticalGroup(
        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel5Layout.createSequentialGroup()
            .addGap(5, 5, 5)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(dtNuevaCita, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblNuevaFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(3, 3, 3)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(cmbMedicoNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(1, 1, 1)
                    .addComponent(lblMedicoNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGap(3, 3, 3)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel9)
                .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(3, 3, 3)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txthora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(btnBuscarHoras))
                    .addGap(3, 3, 3)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(txtIdMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addComponent(btnBuscarProductos))
            .addGap(2, 2, 2)
            .addComponent(txtmotivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(jLabel12)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane2)
            .addContainerGap())
    );

    jLabel13.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
    jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel13.setText("X");
    jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jLabel13MouseClicked(evt);
        }
    });

    tblRegistros.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
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
    tblRegistros.setRowHeight(20);
    jScrollPane3.setViewportView(tblRegistros);
    if (tblRegistros.getColumnModel().getColumnCount() > 0) {
        tblRegistros.getColumnModel().getColumn(1).setMinWidth(40);
        tblRegistros.getColumnModel().getColumn(1).setPreferredWidth(40);
        tblRegistros.getColumnModel().getColumn(1).setMaxWidth(40);
    }

    lbFoto.setBackground(new java.awt.Color(204, 204, 204));
    lbFoto.setToolTipText("Esta herramienta solo recibe formatos JPG y PNG. Al montar la imagen recibe el tamaño de 250x250.");
    lbFoto.setMaximumSize(new java.awt.Dimension(200, 200));
    lbFoto.setMinimumSize(new java.awt.Dimension(200, 200));
    lbFoto.setName(""); // NOI18N
    lbFoto.setOpaque(true);
    lbFoto.setPreferredSize(new java.awt.Dimension(200, 200));
    lbFoto.setRequestFocusEnabled(false);

    txtEncargado.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
    txtEncargado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    txtEncargado.setText("ENCARGADO");

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(txtEncargado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(2, 2, 2)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbFoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING))
            .addGap(15, 15, 15))
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(txtEncargado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGap(3, 3, 3)
            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(10, 10, 10)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())))
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarCliente(txtId.getText());
        } else {
            txtpNombre.setText("");
            txtTelefono.setText("");
            txtCelular.setText("");

            lbFoto.setIcon(null);
            this.repaint();
        }
    }//GEN-LAST:event_txtIdKeyReleased

    private void txtmotivoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmotivoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
//            ventanaProductos(txtmotivo.getText());
        } else {
            txtIdMotivo.setText("");
        }
    }//GEN-LAST:event_txtmotivoKeyReleased

    private void txthoraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txthoraKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
//            cargarProducto(txtIdMotivo.getText());
        }
    }//GEN-LAST:event_txthoraKeyReleased

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        Object[] campos = {txtId, txthora, txtmotivo};
        String faltantes = metodos.camposVacios(campos);

        if (!faltantes.equals("")) {
            metodos.msgAdvertencia(null, "No puede continuar, faltan los siguientes campos: " + faltantes);
            return;
        }

        Object[][] agendas = instancias.getSql().getRegistrosAgendasHora(metodos.fechaConsulta(metodos.fecha(fecha)), atiende, txthora.getText());

        if (agendas.length > 0) {
            metodos.msgError(null, "Ya hay una cita registrada a esta hora");
            return;
        }

        if (metodos.msgPregunta(null, "¿Desea continuar?") == 0) {

            String cita = instancias.getSql().getNumConsecutivo("AGENDA")[0].toString();
            Object[] vector = {cita, txtIdSistema.getText(), fecha, txthora.getText(), txtIdProd.getText(), atiende, "Registrada",
                txtObservaciones.getText(), "", metodos.fechaConsulta(metodosGenerales.fecha()), txtmotivo.getText(), true};

            ndAgenda nodo = metodos.llenarAgenda(vector);

            if (!instancias.getSql().agregarAgenda(nodo)) {
                metodos.msgError(null, "Hubo un problema al guardar la Agenda");
                return;
            }

            if (!instancias.getSql().aumentarConsecutivo("AGENDA", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("AGENDA")[0]) + 1)) {
                metodos.msgError(null, "Hubo un problema al guardar en el consecutivo de la agenda");
                return;
            }
            metodos.msgExito(null, "Cita Registrada Exitosamente");
        }

        instancias.getAgendaConsulta().cargarAgendas(instancias.getMedico());
        instancias.getAgendaConsulta().cargarAgendas();
        this.dispose();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnGuardarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnGuardarKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardarKeyReleased

    private void btnFacturarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFacturarActionPerformed
//        if (txtEstado.getText().equals("Confirmada") || txtEstado.getText().equals("Atendido")) {
        if (txtEstado.getText().equals("Atendido")) {
            metodos.msgError(null, "Esta cita ya se encuentra atendida");
        } else {
            confirmarFacturarAgenda();
        }
    }//GEN-LAST:event_btnFacturarActionPerformed

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
                Object[] campos = {txtId, txthora, txtmotivo};
                String faltantes = metodos.camposVacios(campos);

                if (!faltantes.equals("")) {
                    metodos.msgAdvertencia(null, "No puede continuar, faltan los siguientes campos: " + faltantes);
                    return;
                }

                if (metodos.msgPregunta(null, "¿Desea continuar?") == 0) {

                    String fechaNueva = metodos.desdeDate(dtNuevaCita.getCurrent());
                    String idCita = txtCita.getText();

                    Object[] vector = {idCita, txtIdSistema.getText(), fechaNueva,
                        txthora.getText(), txtIdProd.getText(), cmbMedicoNuevo.getSelectedItem(), "Reagendada",
                        txtObservaciones.getText(), "", metodos.fechaConsulta(metodosGenerales.fecha()),
                        txtmotivo.getText(), true
                    };

                    ndAgenda nodo = metodos.llenarAgenda(vector);
                    ndAgenda nodoAgenda = instancias.getSql().getDatosAgenda(txtCita.getText());

                    Object[] vectorAntiguo = {idCita, fecha,
                        nodoAgenda.getHora(), nodoAgenda.getMotivo(), nodoAgenda.getaCargo(), nodoAgenda.getObservacion()
                    };

                    if (!instancias.getSql().AgregarConsultaAntigua(vectorAntiguo)) {
                        metodos.msgError(null, "Hubo un problema al guardar la Agenda Antigüa");
                        return;
                    }

                    if (!instancias.getSql().modificarAgenda(nodo)) {
                        metodos.msgError(null, "Hubo un problema al guardar la Agenda");
                        return;
                    }

                    metodos.msgExito(null, "Cita Actualizada Exitosamente");

//                    if (metodos.msgPregunta(null, "¿Desea imprimir?") == 0) {
////                        instancias.getReporte().verCita(txtCita.getText(), instancias.getInformacionEmpresa());
//                    }
                    instancias.getAgendaConsulta().cargarAgendas(instancias.getMedico());
                    instancias.getAgendaConsulta().cargarAgendas();
                    this.dispose();
                }
            }
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        String encargado = txtEncargado.getText();
        String estado = txtEstado.getText();
        String hora = txthora.getText();
        for (int x = 0; x < jPanel1.getComponentCount(); x++) {
            if (jPanel1.getComponent(x) instanceof JTextField) {
                JTextField textField = (JTextField) jPanel1.getComponent(x);
                textField.setText("");
            }
        }

        txtEstado.setText(estado);
        txthora.setText(hora);
        txtEncargado.setText(encargado);
        txtNota.setText("");
        txtNota.setEnabled(false);
        txtObservaciones.setText("");
        txtmotivo.setEnabled(true);
        txtId.setEnabled(true);
        btnBuscarPaciente.setEnabled(true);
        txtObservaciones.setEnabled(true);
        btnFacturar.setEnabled(false);
        btnCancelar.setEnabled(false);
        btnModificar.setEnabled(false);
        btnGuardar.setEnabled(true);
        txtCita.setText(instancias.getSql().getNumConsecutivo("AGENDA")[0].toString());
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        if (txtEstado.getText().equals("Confirmada") || txtEstado.getText().equals("Atendido")) {
            metodos.msgError(null, "Esta cita se encuentra confirmada y no se puede cancelar");
        } else if (metodos.msgPregunta(null, "¿Esta seguro de cancelar esta cita?") == 0) {

            if (!instancias.getSql().modificarCita(txtCita.getText(), "Cancelada")) {
                metodos.msgError(null, "Error la cita no pudo ser cancelada");
                return;
            }
            metodos.msgExito(null, "Cita Cancelada");

            instancias.getAgendaConsulta().cargarAgendas(instancias.getMedico());
            instancias.getAgendaConsulta().cargarAgendas();
            this.dispose();

        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtCitaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCitaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCitaKeyReleased

    private void txtIdMotivoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdMotivoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtmotivo.setText("");
            cargarProducto(txtIdMotivo.getText());
        } else {
            txtmotivo.setText("");
        }
    }//GEN-LAST:event_txtIdMotivoKeyReleased

    private void cmbMedicoNuevoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbMedicoNuevoItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbMedicoNuevoItemStateChanged

    private void dtNuevaCitaOnSelectionChange(datechooser.events.SelectionChangedEvent evt) {//GEN-FIRST:event_dtNuevaCitaOnSelectionChange

    }//GEN-LAST:event_dtNuevaCitaOnSelectionChange

    private void dtNuevaCitaOnCommit(datechooser.events.CommitEvent evt) {//GEN-FIRST:event_dtNuevaCitaOnCommit
        txthora.setText("");
    }//GEN-LAST:event_dtNuevaCitaOnCommit

    private void txtEstadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEstadoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEstadoKeyReleased

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
            if (!instancias.getSql().modificarCita(txtCita.getText(), "Atendido")) {
                metodos.msgError(null, "No fue posible modificar el estado de la Cita");
                return;
            }
            metodos.msgExito(null, "Cita Finalizada");
            instancias.getAgendaConsulta().cargarAgendas(instancias.getMedico());
            instancias.getAgendaConsulta().cargarAgendas();
            this.dispose();
        }
    }//GEN-LAST:event_btnFinalizarActionPerformed

    private void txthoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txthoraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txthoraActionPerformed

    private void btnPasarOrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPasarOrdenActionPerformed
        if (instancias.getConfiguraciones().isOftalmologia()) {

            if (doctor) {
                if (txtEstado.getText().equals("Confirmada")) {
                    if (txtEstado.getText().equals("Confirmada")) {
                        instancias.getHojaIngreso().setConfirmada(true);
                        confirmarPasarAOrden1();
                    }
                } else {
                    metodos.msgError(null, "La cita debe estar confirmada");
                }

            } else if (normal) {
                if (txtEstado.getText().equals("Confirmada")) {
                    instancias.getHojaIngreso().setConfirmada(true);
                    confirmarPasarAOrden1();
                } else {
                    confirmarPasarAOrden1();
                }
            } else {
                if (txtEstado.getText().equals("Confirmada")) {
                    instancias.getHojaIngreso().setConfirmada(true);
                    confirmarPasarAOrden1();
                } else {
                    confirmarPasarAOrden1();
                }
            }

            normal = false;
        } else {
            if (txtEstado.getText().equals("Atendido")) {
                metodos.msgError(null, "Esta cita ya se encuentra confirmada");
            } else {
                confirmarPasarAOrden();
            }
        }
    }//GEN-LAST:event_btnPasarOrdenActionPerformed

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        if (txtEstado.getText().equals("Confirmada") || txtEstado.getText().equals("Atendido")) {
            metodos.msgError(null, "Esta cita ya se encuentra confirmada");
            return;
        }

        if (metodos.msgPregunta(null, "¿Desea continuar?") == 0) {
            if (!instancias.getSql().modificarCita(txtCita.getText(), "Confirmada")) {
                metodos.msgError(null, "No fue posible modificar el estado de la Cita");
                return;
            }
            metodos.msgExito(null, "Cita Confirmada");
            instancias.getAgendaConsulta().cargarAgendas(instancias.getMedico());
            instancias.getAgendaConsulta().cargarAgendas();
            this.dispose();
        }
    }//GEN-LAST:event_btnConfirmarActionPerformed

    private void lbltipodocKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbltipodocKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtId.requestFocus();
        }
    }//GEN-LAST:event_lbltipodocKeyReleased

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
        } else if (metodos.msgPregunta(null, "¿Dar por inasistencia esta cita?") == 0) {
            if (!instancias.getSql().modificarCita(txtCita.getText(), "No vino")) {
                metodos.msgError(null, "Error al inactivar la cita");
                return;
            }
            metodos.msgExito(null, "Cita inactivada");
            instancias.getAgendaConsulta().cargarAgendas(instancias.getMedico());
            instancias.getAgendaConsulta().cargarAgendas();
            this.dispose();
        }
    }//GEN-LAST:event_btnInasistenciaActionPerformed

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        this.dispose();
    }//GEN-LAST:event_jLabel13MouseClicked

    private void txtfechaNacimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtfechaNacimientoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfechaNacimientoActionPerformed

    private void txtIdSistemaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdSistemaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdSistemaKeyReleased

    private void btnBuscarPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPacienteActionPerformed
        ventanaTerceros("");
    }//GEN-LAST:event_btnBuscarPacienteActionPerformed

    private void btnBuscarHorasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarHorasActionPerformed
        setaCargo(txtEncargado.getText());
        if (dtNuevaCita.isVisible()) {
            setaCargo(cmbMedicoNuevo.getSelectedItem().toString());
        }

        ventanaHoras(dtNuevaCita.getText());
    }//GEN-LAST:event_btnBuscarHorasActionPerformed

    private void btnBuscarProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProductosActionPerformed
        ventanaProductos("");
    }//GEN-LAST:event_btnBuscarProductosActionPerformed

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

        btnBuscarPaciente.setEnabled(false);
        btnGuardar.setEnabled(false);

        dtNuevaCita.setVisible(true);
        dtNuevaCita.setSelectedDate(metodos.haciaDate(fecha));

        lblNuevaFecha.setVisible(true);
        lblMedicoNuevo.setVisible(true);

        cmbMedicoNuevo.setVisible(true);

//        btnBuscarHoras.setEnabled(true);
        txtObservaciones.setEnabled(true);
//        btnFinalizar.setEnabled(false);
        txtIdMotivo.setEnabled(true);
//        txthora.setText("");
//        txthora.setEnabled(true);
        cmbMedicoNuevo.removeAllItems();
        String tipoEmpleado = "";

        Object[][] aux = instancias.getSql().getEmpleadosNombreAgenda();
        for (int i = 0; i < aux.length; i++) {
            cmbMedicoNuevo.addItem(aux[i][0].toString());
        }
        cmbMedicoNuevo.setSelectedItem(txtEncargado.getText());

    }

    public void ventanaHoras(String fecha) {
        buscHorasAgenda buscar = new buscHorasAgenda(null, rootPaneCheckingEnabled);
        buscar.setLocationRelativeTo(null);
        instancias.setBuscarHorasAgenda(buscar);
        instancias.setValor(fecha);
        instancias.setCampoActual(txthora);
        buscar.setInstancia(instancias);
        txthora.requestFocus();
        buscar.show();
    }

    public void ventanaTerceros(String nit) {
        buscClientes buscar = new buscClientes(null, rootPaneCheckingEnabled, false, null, "");
        buscar.setLocationRelativeTo(null);
        buscar.setOpc("agenda");
        instancias.setBusClientes(buscar);
        instancias.setCampoActual(txtId);
        txtId.requestFocus();
        buscar.noEncontrado(nit);
        buscar.show();
    }

    public void cargarNuevoPaciente(String id, String nombre, String telefono, String cel, String idSistema) {
        txtIdSistema.setText(idSistema);
        txtId.setText(id);
        txtpNombre.setText(nombre);
        txtTelefono.setText(telefono);
        txtCelular.setText(cel);
    }

    public void cargarCliente(String nit) {

        ModeloContacto nodo = instancias.getSql().getDatosTercero(nit);
        if (nodo.getId() != null) {
            if (nodo.isActivo()) {
                metodos.msgError(null, "Este cliente esta inactivado");
                lbltipodoc.requestFocus();
                return;
            }

            txtpNombre.setText(nodo.getCompleta());
            txtId.setText(nodo.getId());
            txtIdSistema.setText(nodo.getIdSistema());
            txtCelular.setText(nodo.getCelular());
//            lbltipodoc.setText(nodo.getTipo() + ":");
            txtTelefono.setText(nodo.getTelefono());
            txtNota.setText(nodo.getNota());
            txtfechaNacimiento.setText(metodos.fecha(nodo.getNacimiento()));
            txtEdad.setText(metodosGenerales.calcularEdad(metodos.fecha(nodo.getNacimiento())) + "");
            txtIdMotivo.requestFocus();

            ImageIcon fot = new ImageIcon(System.getProperty("user.dir") + "\\imagenes\\terceros\\" + txtIdSistema.getText() + ".jpg");
            Icon icono = new ImageIcon(fot.getImage().getScaledInstance(lbFoto.getWidth(), lbFoto.getHeight(), Image.SCALE_DEFAULT));
            lbFoto.setIcon(icono);
            this.repaint();

            Object[][] registroCitas = instancias.getSql().getRegistroCitas(nodo.getIdSistema());
            tblRegistros.setValueAt(big.setNumero(big.getBigDecimal(registroCitas[0][0])), 2, 1);
            tblRegistros.setValueAt(big.setNumero(big.getBigDecimal(registroCitas[0][1])), 0, 1);
            tblRegistros.setValueAt(big.setNumero(big.getBigDecimal(registroCitas[0][2])), 3, 1);
            tblRegistros.setValueAt(big.setNumero(big.getBigDecimal(registroCitas[0][3])), 1, 1);

            ventanaProductos("");
            return;
        }

        Object[][] nuevoPaciente = instancias.getSql().getNuevoTercero(nit);
        try {
            if (!nuevoPaciente[0][0].toString().equalsIgnoreCase("") || !nuevoPaciente[0][0].toString().equalsIgnoreCase(null)) {
                txtpNombre.setText(nuevoPaciente[0][1].toString());

                txtId.setText(nuevoPaciente[0][8].toString());
                txtIdSistema.setText(nuevoPaciente[0][0].toString());

                try {
                    txtTelefono.setText(nuevoPaciente[0][5].toString());
                } catch (Exception e) {
                    txtTelefono.setText("");
                }

                try {
                    txtCelular.setText(nuevoPaciente[0][6].toString());
                } catch (Exception e) {
                    txtCelular.setText("");
                }

                txtIdMotivo.requestFocus();
                ventanaProductos("");
                return;
            }
        } catch (Exception e) {
        }

        ventanaTerceros(nit);
    }

    public void cargarClienteAgenda() {

        txtIdMotivo.setEnabled(false);
        txtId.setEnabled(false);
        txtObservaciones.setEnabled(false);
        txthora.setEnabled(false);
        btnLimpiar.setEnabled(false);
        btnBuscarPaciente.setEnabled(false);

        ndAgenda nodoAgenda = instancias.getSql().getDatosAgenda(txtCita.getText());
        ModeloContacto nodo = instancias.getSql().getDatosTercero(nodoAgenda.getCliente());

        if (nodo.getId() != null) {
            txtpNombre.setText(nodo.getNombre());
            txtId.setText(nodo.getId());
            txtIdSistema.setText(nodo.getIdSistema());
            txtTelefono.setText(nodo.getTelefono());
            txtNota.setText(nodo.getNota());
            txtfechaNacimiento.setText(metodos.fecha(nodo.getNacimiento()));
            txtEdad.setText(metodosGenerales.calcularEdad(metodos.fecha(nodo.getNacimiento())) + "");
        }
//        else {
//            Object[][] nuevoTercero = sql.getNuevoTercero(nodoAgenda.getCliente());
//            nodoAgenda = sql.getDatosAgenda(txtCita.getText());
//            txtCita.setText(nodoAgenda.getId());
//
//            String nombre1, nombre2, nombre3, nombre4;
//
//            try {
//                nombre1 = nuevoTercero[0][1].toString();
//            } catch (Exception e) {
//                nombre1 = "";
//            }
//
//            try {
//                nombre2 = nuevoTercero[0][2].toString();
//            } catch (Exception e) {
//                nombre2 = "";
//            }
//
//            try {
//                nombre3 = nuevoTercero[0][3].toString();
//            } catch (Exception e) {
//                nombre3 = "";
//            }
//
//            try {
//                nombre4 = nuevoTercero[0][4].toString();
//            } catch (Exception e) {
//                nombre4 = "";
//            }
//
//            txtpNombre.setText(nombre1 + " " + nombre2 + " " + nombre3 + " " + nombre4);
//            txtId.setText(nuevoTercero[0][8].toString());
//            txtIdSistema.setText(nuevoTercero[0][0].toString());
//
//            try {
//                txtTelefono.setText(nuevoTercero[0][5].toString());
//            } catch (Exception e) {
//                txtTelefono.setText("");
//            }
//
//            try {
//                txtCelular.setText(nuevoTercero[0][6].toString());
//            } catch (Exception e) {
//                txtCelular.setText("");
//            }
//        }

        txtEstado.setText(nodoAgenda.getEstado());
        txtCita.setText(nodoAgenda.getId());
        txthora.setText(nodoAgenda.getHora());
        txtIdMotivo.setText(nodoAgenda.getMotivo());

        Object[][] registroCitas = instancias.getSql().getRegistroCitas(nodoAgenda.getCliente());

        try {
            tblRegistros.setValueAt(big.setNumero(big.getBigDecimal(registroCitas[0][0])), 2, 1);
        } catch (Exception e) {
            tblRegistros.setValueAt("0", 2, 1);
        }

        try {
            tblRegistros.setValueAt(big.setNumero(big.getBigDecimal(registroCitas[0][1])), 0, 1);
        } catch (Exception e) {
            tblRegistros.setValueAt("0", 0, 1);
        }

        try {
            tblRegistros.setValueAt(big.setNumero(big.getBigDecimal(registroCitas[0][2])), 3, 1);
        } catch (Exception e) {
            tblRegistros.setValueAt("0", 3, 1);
        }

        try {
            tblRegistros.setValueAt(big.setNumero(big.getBigDecimal(registroCitas[0][3])), 1, 1);
        } catch (Exception e) {
            tblRegistros.setValueAt("0", 1, 1);
        }

        ImageIcon fot = new ImageIcon(System.getProperty("user.dir") + "\\imagenes\\terceros\\" + txtIdSistema.getText() + ".jpg");
        Icon icono = new ImageIcon(fot.getImage().getScaledInstance(lbFoto.getWidth(), lbFoto.getHeight(), Image.SCALE_DEFAULT));
        lbFoto.setIcon(icono);
        this.repaint();

        try {
            ndProducto producto = instancias.getSql().getDatosProducto(nodoAgenda.getMotivo(), "bdProductos");
            if (!producto.getCodigo().equalsIgnoreCase("")) {
                cargarProducto(nodoAgenda.getMotivo());
            }
        } catch (Exception e) {
            txtmotivo.setText(nodoAgenda.getMotivo());
        }

        txtObservaciones.setText(nodoAgenda.getObservacion());

        if (txtEstado.getText().equals("Confirmada") || txtEstado.getText().equals("Atendido")) {
            if (txtEstado.getText().equals("Atendido")) {
                btnFinalizar.setEnabled(false);
                btnPasarOrden.setEnabled(false);
                btnFacturar.setEnabled(false);
            } else {
                btnFinalizar.setEnabled(true);
                btnPasarOrden.setEnabled(true);
                btnFacturar.setEnabled(true);
            }

            btnInasistencia.setEnabled(false);
            btnGuardar.setEnabled(false);
            btnCancelar.setEnabled(false);
            btnModificar.setEnabled(false);
            btnConfirmar.setEnabled(false);
        } else if (txtEstado.getText().equals("No vino")) {
            btnLimpiar.setEnabled(false);
            btnGuardar.setEnabled(false);
            btnModificar.setEnabled(false);
            btnConfirmar.setEnabled(false);
            btnFinalizar.setEnabled(false);
            btnCancelar.setEnabled(false);
            btnInasistencia.setEnabled(false);
            btnFacturar.setEnabled(false);
            btnPasarOrden.setEnabled(false);
        } else {
            if (txtEstado.getText().equals("Registrada")) {
                btnGuardar.setEnabled(false);
            }
            btnCancelar.setEnabled(true);
            btnModificar.setEnabled(true);
            btnConfirmar.setEnabled(true);
            btnFacturar.setEnabled(true);
            btnPasarOrden.setEnabled(true);
            btnFinalizar.setEnabled(true);
            btnInasistencia.setEnabled(true);
        }
    }

//    public void ventanaProductos(String codigo) {
//        VistaBuscadorProductos buscar = new VistaBuscadorProductos(null, rootPaneCheckingEnabled, false);
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
        VistaBuscadorProductos buscar = new VistaBuscadorProductos(null, true, false, "");
        buscar.setLocationRelativeTo(null);
        buscar.setClaseBuscador("Agenda");
        buscar.cargarTablaProductos(false);
        instancias.setBusProductos(buscar);
        instancias.setCampoActual(txtIdMotivo);
        txtIdMotivo.requestFocus();
        buscar.noEncontrado(codigo.replace("'", "//"));
        buscar.show();
    }

    public void cargarProducto(String codigo) {

        ndProducto nodo = null;

        String CodigoProd = "";
        if (codigo.equals("")) {
            CodigoProd = "";
        } else {
            Object[][] listado = instancias.getSql().getCodigosRelacionados(codigo, " where codigo");
            if (listado.length > 0) {
                codigo = listado[0][0].toString();
            }

            nodo = instancias.getSql().getDatosProducto(codigo, "bdProductos");
            if (nodo.getIdSistema() != null) {
                CodigoProd = nodo.getIdSistema();
            }
        }

        if (!CodigoProd.equals("")) {
            txtIdProd.setText(nodo.getIdSistema());
            txtIdMotivo.setText(nodo.getCodigo());
            txtmotivo.setText(nodo.getDescripcion());
            txtObservaciones.requestFocus();
            return;
        }
        ventanaProductos(codigo);
    }

//    public void cargarCita(String fecha, String hora, String medico) {
//
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
//
//    }
//
//    public void actualizarConsecutivo() {
//        txtCita.setText(sql.getNumConsecutivo("AGENDA")[0].toString());
//    }
//
//    public void cmbACargoSetIndex0() {
//        cmbACargo.setSelectedIndex(0);
//    }
//
    public void confirmarFacturarAgenda() {

        ModeloContacto nodo = instancias.getSql().getDatosTercero(txtId.getText());

        if (nodo.getId() != null) {
            instancias.getFactura().cargarClienteYProducto(txtId.getText(), new String[][]{new String[]{txtIdMotivo.getText(), "1"}});
            try {
                instancias.getFacturaContenedor().setSelected(true);
            } catch (Exception e) {
            }
        } else {
            instancias.getFactura().cargarClienteYProducto("1010", new String[][]{new String[]{txtIdMotivo.getText(), "1"}});
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

    public void confirmarPasarAOrden() {

        instancias.setValor("Agenda");

        Object[][] nuevoPaciente = instancias.getSql().getNuevoTercero(txtIdSistema.getText());
        if (nuevoPaciente.length > 0) {

            if (metodos.msgPregunta(null, "¿Desea completar la información?") == 0) {
                try {
                    instancias.getPaciente().setSelected(true);
                    instancias.getPaciente().nuevoCliente(txtIdSistema.getText());
                } catch (Exception e) {
                }
            } else {
                pasarOrden();
            }
        } else {
            pasarOrden();
        }

        this.dispose();
    }

    public void pasarOrden() {

        if (instancias.isGeneraOrdenMedica()) {
            instancias.getOrdenMedica().cargarClienteDesdeAgenda(txtId.getText(), txtEncargado.getText());
            try {
                instancias.getOrdenMedica().setSelected(true);
            } catch (Exception e) {
            }
        } else {
            instancias.getHistoriaC().setPaciente(txtId.getText());
            try {
                instancias.getHistoriaC().setSelected(true);
            } catch (Exception e) {
            }
        }

        btnLimpiarActionPerformed(null);
    }

    public void confirmarPasarAOrden1() {

        instancias.setValor("Agenda");

        ModeloContacto nodo = instancias.getSql().getDatosTercero(txtId.getText());

        if (nodo.getId() != null) {
            if (instancias.isGeneraOrdenMedica()) {
                instancias.getOrdenMedica().cargarClienteDesdeAgenda(txtId.getText(), txtEncargado.getText());
                try {
                    instancias.getOrdenMedica().setSelected(true);
                } catch (Exception e) {
                }
            } else {
                if (agenda) {
                    instancias.getHojaIngreso().setAgenda(true);
                }
                if (doctor) {
                    instancias.getHojaIngreso().setAgenda(false);
                }

                instancias.getHojaIngreso().setPaciente(txtId.getText(), txtIdMotivo.getText());

                try {
                    instancias.getHojaIngreso().setSelected(true);
                } catch (Exception e) {
                }
            }

            btnLimpiarActionPerformed(null);
        } else {
            System.out.println("entro a llamar la creacion del paciente");

            try {
                instancias.getClientes().setSelected(true);
            } catch (Exception e) {
            }
        }

        instancias.getAgendaConsulta().cargarAgendas(instancias.getMedico());
        instancias.getAgendaConsulta().cargarAgendas();
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
            java.util.logging.Logger.getLogger(infInfoAgenda.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(infInfoAgenda.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(infInfoAgenda.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(infInfoAgenda.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                infInfoAgenda dialog = new infInfoAgenda(new javax.swing.JFrame(), true, "", "", "", 0, 0, "");
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
    private javax.swing.JButton btnBuscarHoras;
    private javax.swing.JButton btnBuscarPaciente;
    private javax.swing.JButton btnBuscarProductos;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConfirmar;
    private javax.swing.JButton btnFacturar;
    private javax.swing.JButton btnFinalizar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnInasistencia;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnPasarOrden;
    private javax.swing.JComboBox cmbMedicoNuevo;
    private datechooser.beans.DateChooserCombo dtNuevaCita;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lbFoto;
    private javax.swing.JLabel lblMedicoNuevo;
    private javax.swing.JLabel lblNuevaFecha;
    private javax.swing.JLabel lbltipodoc;
    private javax.swing.JPanel pnlInvisible;
    private javax.swing.JTable tblRegistros;
    private javax.swing.JTextField txtCelular;
    private javax.swing.JTextField txtCita;
    private javax.swing.JTextField txtEdad;
    private javax.swing.JLabel txtEncargado;
    private javax.swing.JTextField txtEstado;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtIdMotivo;
    private javax.swing.JTextField txtIdProd;
    private javax.swing.JTextField txtIdSistema;
    private javax.swing.JTextArea txtNota;
    private javax.swing.JTextArea txtObservaciones;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtfechaNacimiento;
    private javax.swing.JTextField txthora;
    private javax.swing.JTextField txtmotivo;
    private javax.swing.JTextField txtpNombre;
    // End of variables declaration//GEN-END:variables
}
