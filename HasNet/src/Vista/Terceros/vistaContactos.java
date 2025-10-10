package Vista.Terceros;

import Modelo.Terceros.ModeloDatosVehiculo;
import Utilidades.Limpiadores;
import Validaciones.Terceros.squemaDatosVehiculo;
import clases.ImagePreviewPanel;
import clases.Instancias;
import clases.Medico.ndConvenio;
import clases.Medico.ndEpsPrecargados;
import clases.Medico.ndHistoriaClinica;
import Modelo.Terceros.ModeloContacto;
import Validaciones.Terceros.squemaDatosContacto;
import clases.big;
import clases.metodosGenerales;
import formularios.Medico.buscConvenio;
import formularios.Medico.buscEpsPrecargadas;
import formularios.Ventas.buscTipoVehiculo;
import formularios.productos.buscColores;
import formularios.productos.buscMarcas;
import formularios.terceros.buscClientes;
import formularios.terceros.buscOcupaciones;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.nio.file.CopyOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class vistaContactos extends javax.swing.JInternalFrame {

    private squemaDatosVehiculo squemaDatosVehiculo = new squemaDatosVehiculo();
    private squemaDatosContacto squemaDatosContacto = new squemaDatosContacto();

    metodosGenerales metodos = new metodosGenerales();
    private Instancias instancias;

    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;
    String tipo = "", id = "", simbolo = "";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public vistaContactos(String tipo) {

        initComponents();
        instancias = Instancias.getInstancias();

        simbolo = instancias.getSimbolo();

        String consecutivo = "";
        int num = Integer.parseInt(instancias.getSql().getNumConsecutivo("TERCERO")[0].toString());
        consecutivo = String.valueOf(num);
        for (int i = 0; i < 8 - String.valueOf(num).length(); i++) {
            consecutivo = "0" + consecutivo;
        }
        txtIdSistema.setText("TERC-" + consecutivo);

        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        setBorder(null);

        pnlInvisible.setVisible(false);

        this.tipo = tipo;

        if (tipo.equals("paciente")) {
            btnImprimir.setVisible(true);
            cmbTipoIdentificacion.removeItem("Nit");
            lbNota.setVisible(false);
            jScrollPane1.setVisible(false);
            txtNota.setVisible(false);
        } else {
            btnImprimir.setVisible(false);

            try {
                tabDatosTerceros.remove(2);
            } catch (Exception e) {
            }
        }

        if (instancias.getConfiguraciones().isOrdenServicio() || instancias.getConfiguraciones().isParqueadero()) {

        } else {
            try {
                tabDatosTerceros.remove(1);
            } catch (Exception e) {
            }
        }

        Object[][] dep = instancias.getSql().getDepartamentos();
        cmbDepartamento.removeAllItems();

        if (dep != null) {
            for (int i = 0; i < dep.length; i++) {
                if (null != dep[i][0]) {
                    cmbDepartamento.addItem(dep[i][0].toString().toUpperCase());
                }
            }
        }

        cmbDepartamento.setSelectedItem("ANTIOQUIA");
        consultarMunicipios("ANTIOQUIA");

        pnlFormulario.registerKeyboardAction(accion("guardar"), "guardar", KeyStroke.getKeyStroke(KeyEvent.VK_G, Event.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
        pnlFormulario.registerKeyboardAction(accion("limpiar"), "limpiar", KeyStroke.getKeyStroke(KeyEvent.VK_L, Event.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
        pnlFormulario.registerKeyboardAction(accion("terceros"), "terceros", KeyStroke.getKeyStroke(KeyEvent.VK_T, Event.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
        pnlFormulario.registerKeyboardAction(accion("modificar"), "modificar", KeyStroke.getKeyStroke(KeyEvent.VK_M, Event.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
    }

    private ActionListener accion(final String opc) {
        ActionListener a = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (opc) {
                    case "limpiar":
                        if ((btnNuevo.isEnabled()) && (btnNuevo.isVisible())) {
                            btnNuevoActionPerformed(null);
                        }
                        break;
                    case "guardar":
                        if ((btnGuardar.isEnabled()) && (btnGuardar.isVisible())) {
                            btnGuardarActionPerformed(null);
                        }
                        break;
                    case "terceros":
                        if ((btnBuscTerceros.isEnabled()) && (btnBuscTerceros.isVisible())) {
                            btnBuscTercerosActionPerformed(null);
                        }
                        break;
                    case "modificar":
                        if ((btnActualizar.isEnabled()) && (btnActualizar.isVisible())) {
                            btnActualizarActionPerformed(null);
                        }
                        break;
                }
            }
        };
        return a;
    }

    public void consultarMunicipios(String departamento) {
        Object[][] municipios = instancias.getSql().getMunicipios(departamento);
        cmbCiudad.removeAllItems();
        cmbCiudad.addItem(" ");
        for (int i = 0; i < municipios.length; i++) {
            if (null != municipios[i][0]) {
                cmbCiudad.addItem(municipios[i][0]);
            }
        }
    }

    public void nuevoCliente(String idSistema) {
        txtIdentificacion.setText(idSistema);
        cargarTercero(idSistema);
    }

//    public void nuevoCliente(String id, String nombre, String tel, String cel, String idSistema) {
//
//        Object[][] nuevoTercero = instancias.getSql().getNuevoTercero(idSistema);
//
//        txtId.setText(id);
//        txtNombre.setText(nombre);
//        txtTelefono.setText(tel);
//        txtCelular.setText(cel);
//
//        String nombre1, nombre2, nombre3, nombre4;
//
//        try {
//            nombre1 = nuevoTercero[0][1].toString();
//        } catch (Exception e) {
//            nombre1 = "";
//        }
//
//        try {
//            nombre2 = nuevoTercero[0][2].toString();
//        } catch (Exception e) {
//            nombre2 = "";
//        }
//
//        try {
//            nombre3 = nuevoTercero[0][3].toString();
//        } catch (Exception e) {
//            nombre3 = "";
//        }
//
//        try {
//            nombre4 = nuevoTercero[0][4].toString();
//        } catch (Exception e) {
//            nombre4 = "";
//        }
//
//        txtpNombre.setText(nombre1);
//        txtsNombre.setText(nombre2);
//        txtpApellido.setText(nombre3);
//        txtsApellido.setText(nombre4);
//        txtNombre.setText(nombre1 + " " + nombre2 + " " + nombre3 + " " + nombre4);
//    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jMonthChooser1 = new com.toedter.calendar.JMonthChooser();
        grupoResidencia = new javax.swing.ButtonGroup();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        popBorrar = new javax.swing.JMenuItem();
        grupoConvenio = new javax.swing.ButtonGroup();
        grpResponsableIva = new javax.swing.ButtonGroup();
        scrFormulario = new javax.swing.JScrollPane();
        pnlFormulario = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnBuscTerceros = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();
        tabDatosTerceros = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        pnlDatosVentas = new javax.swing.JPanel();
        lbEmail8 = new javax.swing.JLabel();
        cmbLista = new javax.swing.JComboBox();
        cmbVendedor = new javax.swing.JComboBox();
        lbVendedor = new javax.swing.JLabel();
        lbPlazo = new javax.swing.JLabel();
        txtPlazo = new javax.swing.JTextField();
        txtCupo = new javax.swing.JTextField();
        lbCupo = new javax.swing.JLabel();
        lbNota = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtNota = new javax.swing.JTextArea();
        pnlDatosObligatorios = new javax.swing.JPanel();
        lbTipo = new javax.swing.JLabel();
        cmbTipoIdentificacion = new javax.swing.JComboBox();
        lbNit = new javax.swing.JLabel();
        txtIdentificacion = new javax.swing.JTextField();
        txtDigito = new javax.swing.JTextField();
        lbNit3 = new javax.swing.JLabel();
        lbCiudad4 = new javax.swing.JLabel();
        cmbTipoContacto = new javax.swing.JComboBox();
        lbRazon = new javax.swing.JLabel();
        txtNombreCompleto = new javax.swing.JTextField();
        lbPNombre = new javax.swing.JLabel();
        lbApellido = new javax.swing.JLabel();
        txtpNombre = new javax.swing.JTextField();
        txtpApellido = new javax.swing.JTextField();
        lbSNombre = new javax.swing.JLabel();
        txtsNombre = new javax.swing.JTextField();
        lbSapellido = new javax.swing.JLabel();
        txtsApellido = new javax.swing.JTextField();
        lbDepartamento = new javax.swing.JLabel();
        cmbDepartamento = new javax.swing.JComboBox();
        lbCiudad = new javax.swing.JLabel();
        cmbCiudad = new javax.swing.JComboBox();
        lbDepartamento5 = new javax.swing.JLabel();
        cmbCodigoPostal = new javax.swing.JComboBox();
        lbTelefono = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        lbEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        pnlOtrosDatos = new javax.swing.JPanel();
        lbNit1 = new javax.swing.JLabel();
        txtTipoCliente = new javax.swing.JTextField();
        lbFecha = new javax.swing.JLabel();
        dtFechaNacimiento = new com.toedter.calendar.JDateChooser();
        lbFecha1 = new javax.swing.JLabel();
        txtLugarNacimiento = new javax.swing.JTextField();
        lbNit2 = new javax.swing.JLabel();
        txtTarjeta = new javax.swing.JTextField();
        lbCiudad6 = new javax.swing.JLabel();
        cmbSexo = new javax.swing.JComboBox();
        lbCiudad7 = new javax.swing.JLabel();
        cmbEstadoCivil = new javax.swing.JComboBox();
        lbDepartamento3 = new javax.swing.JLabel();
        cmbNaturaleza = new javax.swing.JComboBox();
        lbCiudad3 = new javax.swing.JLabel();
        cmbRut = new javax.swing.JComboBox();
        lbBarrio1 = new javax.swing.JLabel();
        chkSiResponsableIVA = new javax.swing.JCheckBox();
        chkNoResponsableIVA = new javax.swing.JCheckBox();
        lbEmail2 = new javax.swing.JLabel();
        txtOcupacion = new javax.swing.JTextField();
        btnBuscTerceros1 = new javax.swing.JButton();
        lbBarrio = new javax.swing.JLabel();
        txtBarrio = new javax.swing.JTextField();
        lbCelular = new javax.swing.JLabel();
        txtCelular = new javax.swing.JTextField();
        lbDireccion1 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        pnlDatosVehiculo = new javax.swing.JPanel();
        lbEmail6 = new javax.swing.JLabel();
        lbPlazo1 = new javax.swing.JLabel();
        txtPlaca = new javax.swing.JTextField();
        lbCupo1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblAutos = new javax.swing.JTable();
        btnGuardarDatosVehiculo = new javax.swing.JButton();
        txtMarca = new javax.swing.JTextField();
        lbPlazo2 = new javax.swing.JLabel();
        lbPlazo3 = new javax.swing.JLabel();
        lbPlazo4 = new javax.swing.JLabel();
        txtChasis = new javax.swing.JTextField();
        txtModelo = new javax.swing.JTextField();
        txtMotor = new javax.swing.JTextField();
        lbEmail7 = new javax.swing.JLabel();
        txtColor = new javax.swing.JTextField();
        lbPlazo5 = new javax.swing.JLabel();
        dtFechaCompra = new datechooser.beans.DateChooserCombo();
        txtTipoVehiculo = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        pnlDatosMedicos = new javax.swing.JPanel();
        lbCiudad23 = new javax.swing.JLabel();
        lbCiudad24 = new javax.swing.JLabel();
        txtNombreMadre = new javax.swing.JTextField();
        txtNombrePadre = new javax.swing.JTextField();
        lbCiudad19 = new javax.swing.JLabel();
        lbCiudad20 = new javax.swing.JLabel();
        txtNombreResponsable = new javax.swing.JTextField();
        lbCiudad21 = new javax.swing.JLabel();
        cmbParentesco = new javax.swing.JComboBox();
        lbCiudad22 = new javax.swing.JLabel();
        txtTelefonoResponsable = new javax.swing.JTextField();
        pnlDatosMedicosImportantes = new javax.swing.JPanel();
        lbEmail3 = new javax.swing.JLabel();
        txtCodigoEps = new javax.swing.JTextField();
        txtNombreEps = new javax.swing.JTextField();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        lbCiudad12 = new javax.swing.JLabel();
        lbCiudad13 = new javax.swing.JLabel();
        lbCiudad14 = new javax.swing.JLabel();
        lbCiudad9 = new javax.swing.JLabel();
        cmbRegimen = new javax.swing.JComboBox();
        cmbAfiliado = new javax.swing.JComboBox();
        cmbTrabajador = new javax.swing.JComboBox();
        cmbZona = new javax.swing.JComboBox();
        txtSangre = new javax.swing.JTextField();
        txtReligion = new javax.swing.JTextField();
        lbEmail4 = new javax.swing.JLabel();
        lbCiudad11 = new javax.swing.JLabel();
        lbCiudad15 = new javax.swing.JLabel();
        txtConvenio = new javax.swing.JTextField();
        txtNombreConvenio = new javax.swing.JTextField();
        lbEmail11 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtNota1 = new javax.swing.JTextArea();
        lbEmail5 = new javax.swing.JLabel();
        txtCodigoReferido = new javax.swing.JTextField();
        txtNombreReferido = new javax.swing.JTextField();
        lbFoto = new javax.swing.JLabel();
        btnImagen = new javax.swing.JButton();
        pnlInvisible = new javax.swing.JPanel();
        lbCupo6 = new javax.swing.JLabel();
        txtIdSistema = new javax.swing.JTextField();

        popBorrar.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        popBorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar-cancelar-icono-4935-16.png"))); // NOI18N
        popBorrar.setText("Borrar");
        popBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popBorrarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(popBorrar);

        setTitle("Creación de contactos");

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        btnNuevo.setBackground(new java.awt.Color(204, 204, 204));
        btnNuevo.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/nuevo.png"))); // NOI18N
        btnNuevo.setText("NUEVO        ");
        btnNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnNuevo.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnGuardar.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardar.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardar.setText("GUARDAR    ");
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnActualizar.setBackground(new java.awt.Color(93, 173, 226));
        btnActualizar.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/actualizar.png"))); // NOI18N
        btnActualizar.setText("MODIFICAR  ");
        btnActualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizar.setEnabled(false);
        btnActualizar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnActualizar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(241, 148, 138));
        btnEliminar.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/borrar2.png"))); // NOI18N
        btnEliminar.setText("INACTIVAR ");
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminar.setEnabled(false);
        btnEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnEliminar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnBuscTerceros.setBackground(new java.awt.Color(247, 220, 111));
        btnBuscTerceros.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscTerceros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cliente.png"))); // NOI18N
        btnBuscTerceros.setText("LISTADO      ");
        btnBuscTerceros.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscTerceros.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBuscTerceros.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnBuscTerceros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscTercerosActionPerformed(evt);
            }
        });

        btnImprimir.setBackground(new java.awt.Color(255, 255, 255));
        btnImprimir.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/imprimir.png"))); // NOI18N
        btnImprimir.setText("IMPRIMIR INFORME DEL PACIENTE");
        btnImprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnImprimir.setEnabled(false);
        btnImprimir.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnImprimir.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnImprimir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btnBuscTerceros, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNuevo)
                    .addComponent(btnGuardar))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnActualizar)
                    .addComponent(btnBuscTerceros))
                .addGap(3, 3, 3)
                .addComponent(btnEliminar)
                .addGap(3, 3, 3)
                .addComponent(btnImprimir)
                .addGap(5, 5, 5))
        );

        tabDatosTerceros.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Adobe Arabic", 1, 12))); // NOI18N

        pnlDatosVentas.setBackground(new java.awt.Color(255, 255, 255));
        pnlDatosVentas.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos para ventas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N

        lbEmail8.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbEmail8.setText("Lista Precio:");

        cmbLista.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cmbLista.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "L1", "L2", "L3", "L4", "L5", "L6", "L7", "L8" }));
        cmbLista.setName("Lista Precios"); // NOI18N
        cmbLista.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbListaKeyReleased(evt);
            }
        });

        cmbVendedor.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cmbVendedor.setName("Vendedor"); // NOI18N
        cmbVendedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbVendedorKeyReleased(evt);
            }
        });

        lbVendedor.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbVendedor.setText("Vendedor:");

        lbPlazo.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbPlazo.setText("Días de plazo:");

        txtPlazo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtPlazo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPlazo.setName("Plazo"); // NOI18N
        txtPlazo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPlazoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPlazoKeyTyped(evt);
            }
        });

        txtCupo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtCupo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCupo.setText("0");
        txtCupo.setName("Cupo"); // NOI18N
        txtCupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCupoActionPerformed(evt);
            }
        });
        txtCupo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCupoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCupoKeyTyped(evt);
            }
        });

        lbCupo.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbCupo.setText("Cupo crédito:");

        lbNota.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbNota.setText("Notas:");

        txtNota.setColumns(20);
        txtNota.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtNota.setLineWrap(true);
        txtNota.setRows(2);
        txtNota.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNotaKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(txtNota);

        javax.swing.GroupLayout pnlDatosVentasLayout = new javax.swing.GroupLayout(pnlDatosVentas);
        pnlDatosVentas.setLayout(pnlDatosVentasLayout);
        pnlDatosVentasLayout.setHorizontalGroup(
            pnlDatosVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosVentasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDatosVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDatosVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lbNota, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbVendedor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
                    .addComponent(lbCupo, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDatosVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDatosVentasLayout.createSequentialGroup()
                        .addComponent(txtCupo, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(lbPlazo, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPlazo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbEmail8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbLista, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cmbVendedor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        pnlDatosVentasLayout.setVerticalGroup(
            pnlDatosVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDatosVentasLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(pnlDatosVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbCupo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbPlazo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPlazo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbEmail8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbLista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(pnlDatosVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(pnlDatosVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNota, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pnlDatosObligatorios.setBackground(new java.awt.Color(255, 255, 255));
        pnlDatosObligatorios.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos obligatorios", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N

        lbTipo.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbTipo.setText("Tipo identificación: ");

        cmbTipoIdentificacion.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cmbTipoIdentificacion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Cédula de ciudadanía", "Nit", "Cédula de extranjería", "Pasaporte", "Registro civil", "Tarjeta de identidad", "Adulto sin identificación", "Menor sin identificación", "Número único de identificación" }));
        cmbTipoIdentificacion.setName("Tipo"); // NOI18N
        cmbTipoIdentificacion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbTipoIdentificacionItemStateChanged(evt);
            }
        });
        cmbTipoIdentificacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbTipoIdentificacionKeyReleased(evt);
            }
        });

        lbNit.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbNit.setText("Identificación:");
        lbNit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lbNitKeyReleased(evt);
            }
        });

        txtIdentificacion.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtIdentificacion.setName("CC/NIT"); // NOI18N
        txtIdentificacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdentificacionActionPerformed(evt);
            }
        });
        txtIdentificacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdentificacionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdentificacionKeyTyped(evt);
            }
        });

        txtDigito.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtDigito.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDigito.setName("CC/NIT"); // NOI18N
        txtDigito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDigitoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDigitoKeyTyped(evt);
            }
        });

        lbNit3.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit3.setText("-");

        lbCiudad4.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbCiudad4.setText("Tipo contacto:");

        cmbTipoContacto.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cmbTipoContacto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TERCERO", "PROVEEDOR" }));
        cmbTipoContacto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbTipoContactoKeyReleased(evt);
            }
        });

        lbRazon.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbRazon.setText("Razón social: ");

        txtNombreCompleto.setEditable(false);
        txtNombreCompleto.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtNombreCompleto.setName("Razón social"); // NOI18N
        txtNombreCompleto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreCompletoKeyReleased(evt);
            }
        });

        lbPNombre.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbPNombre.setText("Primer nombre:     ");

        lbApellido.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbApellido.setText("Primer apellido:   ");

        txtpNombre.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtpNombre.setMaximumSize(new java.awt.Dimension(167, 167));
        txtpNombre.setName("Primer nombre"); // NOI18N
        txtpNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtpNombreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtpNombreKeyTyped(evt);
            }
        });

        txtpApellido.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtpApellido.setName("Primer apellido"); // NOI18N
        txtpApellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpApellidoActionPerformed(evt);
            }
        });
        txtpApellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtpApellidoKeyReleased(evt);
            }
        });

        lbSNombre.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbSNombre.setText("Segundo nombre:");

        txtsNombre.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtsNombre.setName("Segundo nombre"); // NOI18N
        txtsNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsNombreActionPerformed(evt);
            }
        });
        txtsNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtsNombreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtsNombreKeyTyped(evt);
            }
        });

        lbSapellido.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbSapellido.setText("Segundo apellido:");

        txtsApellido.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtsApellido.setName("Segundo apellido"); // NOI18N
        txtsApellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtsApellidoKeyReleased(evt);
            }
        });

        lbDepartamento.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbDepartamento.setText("Departamento:");

        cmbDepartamento.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cmbDepartamento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "DEPARTAMENTOS" }));
        cmbDepartamento.setName("Departamento"); // NOI18N
        cmbDepartamento.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbDepartamentoItemStateChanged(evt);
            }
        });

        lbCiudad.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbCiudad.setText("Ciudad: ");

        cmbCiudad.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cmbCiudad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));
        cmbCiudad.setName("Ciudad"); // NOI18N
        cmbCiudad.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbCiudadItemStateChanged(evt);
            }
        });

        lbDepartamento5.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbDepartamento5.setText("Codigo postal:");

        cmbCodigoPostal.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cmbCodigoPostal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));
        cmbCodigoPostal.setName("Ciudad"); // NOI18N
        cmbCodigoPostal.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbCodigoPostalItemStateChanged(evt);
            }
        });

        lbTelefono.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbTelefono.setText("Teléfono:");

        txtTelefono.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtTelefono.setName("Teléfono"); // NOI18N
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });

        lbEmail.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbEmail.setText("Correo electrónico:");

        txtEmail.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtEmail.setName("E-mail"); // NOI18N
        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEmailKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlDatosObligatoriosLayout = new javax.swing.GroupLayout(pnlDatosObligatorios);
        pnlDatosObligatorios.setLayout(pnlDatosObligatoriosLayout);
        pnlDatosObligatoriosLayout.setHorizontalGroup(
            pnlDatosObligatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosObligatoriosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDatosObligatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lbEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbDepartamento5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbDepartamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbApellido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbPNombre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbRazon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTipo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbCiudad4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDatosObligatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlDatosObligatoriosLayout.createSequentialGroup()
                        .addGroup(pnlDatosObligatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbTipoIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbTipoContacto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbNit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(5, 5, 5)
                        .addComponent(txtIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(lbNit3)
                        .addGap(3, 3, 3)
                        .addComponent(txtDigito, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlDatosObligatoriosLayout.createSequentialGroup()
                        .addGroup(pnlDatosObligatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbCodigoPostal, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtpApellido)
                            .addComponent(cmbDepartamento, 0, 167, Short.MAX_VALUE)
                            .addComponent(txtpNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(pnlDatosObligatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(pnlDatosObligatoriosLayout.createSequentialGroup()
                                .addGroup(pnlDatosObligatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbCiudad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbSapellido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pnlDatosObligatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(pnlDatosObligatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(cmbCiudad, javax.swing.GroupLayout.Alignment.TRAILING, 0, 167, Short.MAX_VALUE)
                                        .addComponent(txtsApellido))
                                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pnlDatosObligatoriosLayout.createSequentialGroup()
                                .addComponent(lbSNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtsNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtNombreCompleto))
                .addContainerGap())
        );
        pnlDatosObligatoriosLayout.setVerticalGroup(
            pnlDatosObligatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosObligatoriosLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(pnlDatosObligatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbTipoContacto, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbCiudad4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(pnlDatosObligatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtIdentificacion, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDigito, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNit3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlDatosObligatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(cmbTipoIdentificacion, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                        .addComponent(lbTipo, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)))
                .addGap(3, 3, 3)
                .addGroup(pnlDatosObligatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNombreCompleto)
                    .addComponent(lbRazon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(pnlDatosObligatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDatosObligatoriosLayout.createSequentialGroup()
                        .addGroup(pnlDatosObligatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbPNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtpNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtsNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(pnlDatosObligatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlDatosObligatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtpApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbSapellido, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtsApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(lbSNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(pnlDatosObligatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDatosObligatoriosLayout.createSequentialGroup()
                        .addGroup(pnlDatosObligatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbDepartamento, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                            .addComponent(cmbDepartamento, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(lbCiudad, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                    .addComponent(cmbCiudad, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(pnlDatosObligatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDatosObligatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lbDepartamento5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbCodigoPostal, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(txtTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(pnlDatosObligatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtEmail)
                    .addComponent(lbEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3))
        );

        pnlOtrosDatos.setBackground(new java.awt.Color(255, 255, 255));
        pnlOtrosDatos.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Otros datos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N

        lbNit1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbNit1.setText("Tipo de cliente:");

        txtTipoCliente.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtTipoCliente.setName("Segundo nombre"); // NOI18N
        txtTipoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTipoClienteActionPerformed(evt);
            }
        });
        txtTipoCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTipoClienteKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTipoClienteKeyTyped(evt);
            }
        });

        lbFecha.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbFecha.setText("F. Nacimiento: ");

        dtFechaNacimiento.setDateFormatString("dd/MM/yyyy");
        dtFechaNacimiento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                dtFechaNacimientoKeyReleased(evt);
            }
        });

        lbFecha1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbFecha1.setText("Lugar nacimiento:");

        txtLugarNacimiento.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtLugarNacimiento.setName("E-mail"); // NOI18N
        txtLugarNacimiento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLugarNacimientoKeyReleased(evt);
            }
        });

        lbNit2.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbNit2.setText("Número tarjeta:");

        txtTarjeta.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtTarjeta.setName("Segundo nombre"); // NOI18N
        txtTarjeta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTarjetaActionPerformed(evt);
            }
        });
        txtTarjeta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTarjetaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTarjetaKeyTyped(evt);
            }
        });

        lbCiudad6.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbCiudad6.setText("Sexo:             ");

        cmbSexo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Femenino", "Masculino" }));
        cmbSexo.setName("Sexo"); // NOI18N
        cmbSexo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbSexoKeyReleased(evt);
            }
        });

        lbCiudad7.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbCiudad7.setText("Estado civil:");

        cmbEstadoCivil.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Soltero(a)", "Casado(a)", "Union Libre", "Divorciado(a)", "Viudo(a)" }));
        cmbEstadoCivil.setName("Estado civil"); // NOI18N
        cmbEstadoCivil.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbEstadoCivilKeyReleased(evt);
            }
        });

        lbDepartamento3.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbDepartamento3.setText("Naturaleza:  ");

        cmbNaturaleza.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cmbNaturaleza.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Persona natural", "Persona juridica" }));
        cmbNaturaleza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbNaturalezaActionPerformed(evt);
            }
        });
        cmbNaturaleza.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbNaturalezaKeyReleased(evt);
            }
        });

        lbCiudad3.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbCiudad3.setText("Rut:");

        cmbRut.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cmbRut.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "No", "Si" }));
        cmbRut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbRutKeyReleased(evt);
            }
        });

        lbBarrio1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbBarrio1.setText("Responsable IVA:");

        grpResponsableIva.add(chkSiResponsableIVA);
        chkSiResponsableIVA.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        chkSiResponsableIVA.setText("SI");
        chkSiResponsableIVA.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        grpResponsableIva.add(chkNoResponsableIVA);
        chkNoResponsableIVA.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        chkNoResponsableIVA.setSelected(true);
        chkNoResponsableIVA.setText("NO");
        chkNoResponsableIVA.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lbEmail2.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbEmail2.setText("Ocupación:");

        txtOcupacion.setBackground(new java.awt.Color(255, 204, 204));
        txtOcupacion.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtOcupacion.setName("Ocupación"); // NOI18N
        txtOcupacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtOcupacionKeyReleased(evt);
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

        lbBarrio.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbBarrio.setText("Barrio:");

        txtBarrio.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtBarrio.setName("Dirección"); // NOI18N
        txtBarrio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBarrioActionPerformed(evt);
            }
        });
        txtBarrio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBarrioKeyReleased(evt);
            }
        });

        lbCelular.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbCelular.setText("Celular:");

        txtCelular.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCelular.setName("Celular"); // NOI18N
        txtCelular.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCelularKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCelularKeyTyped(evt);
            }
        });

        lbDireccion1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbDireccion1.setText("Dirección:    ");

        txtDireccion.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtDireccion.setName("Dirección"); // NOI18N
        txtDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDireccionActionPerformed(evt);
            }
        });
        txtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDireccionKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlOtrosDatosLayout = new javax.swing.GroupLayout(pnlOtrosDatos);
        pnlOtrosDatos.setLayout(pnlOtrosDatosLayout);
        pnlOtrosDatosLayout.setHorizontalGroup(
            pnlOtrosDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOtrosDatosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlOtrosDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlOtrosDatosLayout.createSequentialGroup()
                        .addGroup(pnlOtrosDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbEmail2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbDepartamento3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(pnlOtrosDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlOtrosDatosLayout.createSequentialGroup()
                                .addComponent(cmbNaturaleza, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbCiudad3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbRut, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                                .addComponent(lbBarrio1)
                                .addGap(5, 5, 5)
                                .addComponent(chkSiResponsableIVA)
                                .addGap(3, 3, 3)
                                .addComponent(chkNoResponsableIVA))
                            .addGroup(pnlOtrosDatosLayout.createSequentialGroup()
                                .addComponent(txtOcupacion)
                                .addGap(3, 3, 3)
                                .addComponent(btnBuscTerceros1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(pnlOtrosDatosLayout.createSequentialGroup()
                        .addGroup(pnlOtrosDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbDireccion1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbCiudad6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                            .addComponent(lbFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbNit1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbBarrio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(9, 9, 9)
                        .addGroup(pnlOtrosDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlOtrosDatosLayout.createSequentialGroup()
                                .addGroup(pnlOtrosDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlOtrosDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(cmbSexo, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(dtFechaNacimiento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                                        .addComponent(txtTipoCliente, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addComponent(txtBarrio))
                                .addGap(32, 32, 32)
                                .addGroup(pnlOtrosDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(pnlOtrosDatosLayout.createSequentialGroup()
                                        .addGroup(pnlOtrosDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(lbFecha1, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                                            .addComponent(lbCiudad7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lbNit2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(pnlOtrosDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtTarjeta)
                                            .addComponent(txtLugarNacimiento)
                                            .addComponent(cmbEstadoCivil, 0, 167, Short.MAX_VALUE)))
                                    .addGroup(pnlOtrosDatosLayout.createSequentialGroup()
                                        .addComponent(lbCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                                        .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        pnlOtrosDatosLayout.setVerticalGroup(
            pnlOtrosDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOtrosDatosLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pnlOtrosDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbRut, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbCiudad3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbNaturaleza, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbDepartamento3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlOtrosDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(chkSiResponsableIVA, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, Short.MAX_VALUE)
                        .addComponent(lbBarrio1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                        .addComponent(chkNoResponsableIVA, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, Short.MAX_VALUE)))
                .addGap(3, 3, 3)
                .addGroup(pnlOtrosDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtOcupacion, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbEmail2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscTerceros1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(pnlOtrosDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(dtFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 23, Short.MAX_VALUE)
                    .addComponent(txtLugarNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 23, Short.MAX_VALUE)
                    .addComponent(lbFecha1, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                    .addComponent(lbFecha, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(pnlOtrosDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTarjeta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlOtrosDatosLayout.createSequentialGroup()
                        .addGroup(pnlOtrosDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbCiudad7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbEstadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbCiudad6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(pnlOtrosDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbNit1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTipoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbNit2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(3, 3, 3)
                .addGroup(pnlOtrosDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlOtrosDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbBarrio, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtBarrio, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlOtrosDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lbCelular, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                        .addComponent(txtCelular, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, Short.MAX_VALUE)))
                .addGap(3, 3, 3)
                .addGroup(pnlOtrosDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbDireccion1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(pnlDatosObligatorios, javax.swing.GroupLayout.PREFERRED_SIZE, 660, Short.MAX_VALUE)
                    .addComponent(pnlOtrosDatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlDatosVentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(pnlDatosObligatorios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(pnlDatosVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(pnlOtrosDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        tabDatosTerceros.addTab("Datos contacto", jPanel1);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        pnlDatosVehiculo.setBackground(new java.awt.Color(255, 255, 255));

        lbEmail6.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbEmail6.setText("Marca:");

        lbPlazo1.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        lbPlazo1.setText("Tipo de vehiculo:");

        txtPlaca.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtPlaca.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPlaca.setEnabled(false);
        txtPlaca.setName("Cupo"); // NOI18N
        txtPlaca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPlacaKeyReleased(evt);
            }
        });

        lbCupo1.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        lbCupo1.setText("Placa:");

        tblAutos.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        tblAutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Placa", "Tipo", "Modelo", "Marca", "Color", "Chasis", "Motor", "F.Compra"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblAutos.setComponentPopupMenu(jPopupMenu1);
        tblAutos.setRowHeight(20);
        tblAutos.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tblAutos);
        if (tblAutos.getColumnModel().getColumnCount() > 0) {
            tblAutos.getColumnModel().getColumn(0).setMinWidth(55);
            tblAutos.getColumnModel().getColumn(0).setPreferredWidth(55);
            tblAutos.getColumnModel().getColumn(0).setMaxWidth(55);
            tblAutos.getColumnModel().getColumn(2).setMinWidth(55);
            tblAutos.getColumnModel().getColumn(2).setPreferredWidth(55);
            tblAutos.getColumnModel().getColumn(2).setMaxWidth(55);
        }

        btnGuardarDatosVehiculo.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardarDatosVehiculo.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnGuardarDatosVehiculo.setText("Agregar Vehiculo");
        btnGuardarDatosVehiculo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarDatosVehiculo.setEnabled(false);
        btnGuardarDatosVehiculo.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardarDatosVehiculo.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnGuardarDatosVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarDatosVehiculoActionPerformed(evt);
            }
        });

        txtMarca.setBackground(new java.awt.Color(255, 204, 204));
        txtMarca.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtMarca.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMarca.setEnabled(false);
        txtMarca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMarcaKeyReleased(evt);
            }
        });

        lbPlazo2.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbPlazo2.setText("Modelo:");

        lbPlazo3.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbPlazo3.setText("# de chasis:");

        lbPlazo4.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbPlazo4.setText("# de motor:");

        txtChasis.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtChasis.setEnabled(false);

        txtModelo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtModelo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtModelo.setEnabled(false);

        txtMotor.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtMotor.setEnabled(false);

        lbEmail7.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbEmail7.setText("Color:");

        txtColor.setBackground(new java.awt.Color(255, 204, 204));
        txtColor.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtColor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtColor.setEnabled(false);
        txtColor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtColorKeyReleased(evt);
            }
        });

        lbPlazo5.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbPlazo5.setText("Fecha compra:");

        dtFechaCompra.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 12));
        dtFechaCompra.addCommitListener(new datechooser.events.CommitListener() {
            public void onCommit(datechooser.events.CommitEvent evt) {
                dtFechaCompraOnCommit(evt);
            }
        });

        txtTipoVehiculo.setBackground(new java.awt.Color(255, 204, 204));
        txtTipoVehiculo.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtTipoVehiculo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTipoVehiculo.setEnabled(false);
        txtTipoVehiculo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTipoVehiculoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTipoVehiculoKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlDatosVehiculoLayout = new javax.swing.GroupLayout(pnlDatosVehiculo);
        pnlDatosVehiculo.setLayout(pnlDatosVehiculoLayout);
        pnlDatosVehiculoLayout.setHorizontalGroup(
            pnlDatosVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 659, Short.MAX_VALUE)
            .addGroup(pnlDatosVehiculoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDatosVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lbPlazo4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbPlazo3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbCupo1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbPlazo5, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE))
                .addGap(5, 5, 5)
                .addGroup(pnlDatosVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtPlaca)
                    .addComponent(txtMotor)
                    .addComponent(dtFechaCompra, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                    .addComponent(txtChasis, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(60, 60, 60)
                .addGroup(pnlDatosVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbEmail7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbEmail6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbPlazo2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbPlazo1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDatosVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtModelo)
                    .addComponent(txtMarca)
                    .addComponent(txtColor)
                    .addComponent(txtTipoVehiculo, javax.swing.GroupLayout.Alignment.LEADING)))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDatosVehiculoLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnGuardarDatosVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlDatosVehiculoLayout.setVerticalGroup(
            pnlDatosVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosVehiculoLayout.createSequentialGroup()
                .addGroup(pnlDatosVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbCupo1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(txtPlaca, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbPlazo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTipoVehiculo, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(10, 10, 10)
                .addGroup(pnlDatosVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlDatosVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbPlazo2, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                        .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbPlazo3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                    .addComponent(txtChasis, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(pnlDatosVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlDatosVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbEmail6, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                        .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtMotor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                    .addComponent(lbPlazo4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(pnlDatosVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbPlazo5, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                    .addComponent(dtFechaCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 24, Short.MAX_VALUE)
                    .addGroup(pnlDatosVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbEmail7, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                        .addComponent(txtColor, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardarDatosVehiculo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlDatosVehiculo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlDatosVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );

        tabDatosTerceros.addTab("Datos Vehiculo", jPanel5);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        pnlDatosMedicos.setBackground(new java.awt.Color(255, 255, 255));

        lbCiudad23.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbCiudad23.setText("Nombre completo de la madre:");

        lbCiudad24.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbCiudad24.setText("Nombre completo del padre:");

        txtNombreMadre.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        txtNombrePadre.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lbCiudad19.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lbCiudad19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbCiudad19.setText("Datos del responsable del paciente");

        lbCiudad20.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbCiudad20.setText("Nombre del responsable:");

        txtNombreResponsable.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        lbCiudad21.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbCiudad21.setText("Parentesco:");

        cmbParentesco.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cmbParentesco.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Papá", "Mamá", "Cónyuge", "Yerno", "Nuera", "Madrastra", "Padrastro", "Tio(a)", "Hermano(a)", "Abuelo(a)", "Primo(a)", "Sobrino(a)", "Amigo(a)", "Hijo(a)", "Novio(a)", "Cuñado(a)", "Suegro(a)", "Otro" }));
        cmbParentesco.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbParentescoKeyReleased(evt);
            }
        });

        lbCiudad22.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbCiudad22.setText("Teléfono:");

        txtTelefonoResponsable.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        pnlDatosMedicosImportantes.setBackground(new java.awt.Color(255, 255, 255));
        pnlDatosMedicosImportantes.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos importantes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N

        lbEmail3.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lbEmail3.setText("EPS: *");

        txtCodigoEps.setBackground(new java.awt.Color(255, 204, 204));
        txtCodigoEps.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtCodigoEps.setName("Empresa"); // NOI18N
        txtCodigoEps.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoEpsKeyReleased(evt);
            }
        });

        txtNombreEps.setEditable(false);
        txtNombreEps.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtNombreEps.setName("Nombre empresa"); // NOI18N
        txtNombreEps.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreEpsKeyReleased(evt);
            }
        });

        jRadioButton2.setBackground(new java.awt.Color(255, 255, 255));
        grupoConvenio.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jRadioButton2.setSelected(true);
        jRadioButton2.setText("Paciente");

        jRadioButton1.setBackground(new java.awt.Color(255, 255, 255));
        grupoConvenio.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jRadioButton1.setText("Convenio");

        lbCiudad12.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lbCiudad12.setText("Regimen: *");

        lbCiudad13.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbCiudad13.setText("Afiliado: *");

        lbCiudad14.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lbCiudad14.setText("Tipo trabajador: *");

        lbCiudad9.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lbCiudad9.setText("Zona:");

        cmbRegimen.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cmbRegimen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Contributivo", "Subsidiado", "Vinculado", "Particular", "Otro" }));
        cmbRegimen.setName("Regimen"); // NOI18N
        cmbRegimen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbRegimenKeyReleased(evt);
            }
        });

        cmbAfiliado.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cmbAfiliado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Cotizante", "Beneficiario", "Adicional", "Subsidiado", "Medicina Prepagada", "Particular", "Otros" }));
        cmbAfiliado.setName("Afiliado"); // NOI18N
        cmbAfiliado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbAfiliadoActionPerformed(evt);
            }
        });
        cmbAfiliado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbAfiliadoKeyReleased(evt);
            }
        });

        cmbTrabajador.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cmbTrabajador.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Dependiente", "Independiente", "Otro" }));
        cmbTrabajador.setName("Tipo trabajador"); // NOI18N
        cmbTrabajador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbTrabajadorKeyReleased(evt);
            }
        });

        cmbZona.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cmbZona.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Urbana", "Rural" }));
        cmbZona.setName("Zona"); // NOI18N
        cmbZona.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbZonaKeyReleased(evt);
            }
        });

        txtSangre.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtSangre.setName("Sangre"); // NOI18N

        txtReligion.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtReligion.setName("Religion"); // NOI18N

        lbEmail4.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lbEmail4.setText("Convenio:");

        lbCiudad11.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lbCiudad11.setText("Tipo de sangre:");

        lbCiudad15.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lbCiudad15.setText("Religión:");

        txtConvenio.setBackground(new java.awt.Color(255, 204, 204));
        txtConvenio.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtConvenio.setName("Convenio"); // NOI18N
        txtConvenio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtConvenioKeyReleased(evt);
            }
        });

        txtNombreConvenio.setEditable(false);
        txtNombreConvenio.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNombreConvenio.setName("Nombre Convenio"); // NOI18N
        txtNombreConvenio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreConvenioKeyReleased(evt);
            }
        });

        lbEmail11.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbEmail11.setText("NOTA DEL PAC:");

        txtNota1.setColumns(20);
        txtNota1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtNota1.setLineWrap(true);
        txtNota1.setRows(3);
        txtNota1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNota1KeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(txtNota1);

        lbEmail5.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbEmail5.setText("Referido Por:");

        txtCodigoReferido.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtCodigoReferido.setName("Empresa"); // NOI18N
        txtCodigoReferido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoReferidoActionPerformed(evt);
            }
        });
        txtCodigoReferido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoReferidoKeyReleased(evt);
            }
        });

        txtNombreReferido.setEditable(false);
        txtNombreReferido.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtNombreReferido.setName("Referido"); // NOI18N
        txtNombreReferido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreReferidoKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlDatosMedicosImportantesLayout = new javax.swing.GroupLayout(pnlDatosMedicosImportantes);
        pnlDatosMedicosImportantes.setLayout(pnlDatosMedicosImportantesLayout);
        pnlDatosMedicosImportantesLayout.setHorizontalGroup(
            pnlDatosMedicosImportantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosMedicosImportantesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDatosMedicosImportantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDatosMedicosImportantesLayout.createSequentialGroup()
                        .addComponent(lbEmail11, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDatosMedicosImportantesLayout.createSequentialGroup()
                        .addComponent(jRadioButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton1))
                    .addGroup(pnlDatosMedicosImportantesLayout.createSequentialGroup()
                        .addGroup(pnlDatosMedicosImportantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbEmail3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pnlDatosMedicosImportantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(pnlDatosMedicosImportantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lbEmail4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbCiudad11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbCiudad12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbCiudad14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(lbEmail5, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlDatosMedicosImportantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtConvenio)
                            .addComponent(txtNombreReferido)
                            .addComponent(txtNombreConvenio)
                            .addGroup(pnlDatosMedicosImportantesLayout.createSequentialGroup()
                                .addComponent(txtCodigoReferido, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(pnlDatosMedicosImportantesLayout.createSequentialGroup()
                                .addGroup(pnlDatosMedicosImportantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtCodigoEps)
                                    .addComponent(txtSangre, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmbTrabajador, javax.swing.GroupLayout.Alignment.LEADING, 0, 157, Short.MAX_VALUE)
                                    .addComponent(cmbRegimen, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(pnlDatosMedicosImportantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlDatosMedicosImportantesLayout.createSequentialGroup()
                                        .addGroup(pnlDatosMedicosImportantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(lbCiudad13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lbCiudad9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lbCiudad15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(pnlDatosMedicosImportantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cmbAfiliado, 0, 181, Short.MAX_VALUE)
                                            .addComponent(cmbZona, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtReligion)))
                                    .addComponent(txtNombreEps))))))
                .addContainerGap())
        );
        pnlDatosMedicosImportantesLayout.setVerticalGroup(
            pnlDatosMedicosImportantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosMedicosImportantesLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(pnlDatosMedicosImportantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDatosMedicosImportantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlDatosMedicosImportantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCodigoEps, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNombreEps, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbEmail3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(pnlDatosMedicosImportantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDatosMedicosImportantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lbCiudad12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbCiudad13, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
                    .addComponent(cmbAfiliado, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbRegimen, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(pnlDatosMedicosImportantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(cmbTrabajador, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbCiudad9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbCiudad14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbZona))
                .addGap(3, 3, 3)
                .addGroup(pnlDatosMedicosImportantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSangre)
                    .addComponent(lbCiudad11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbCiudad15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtReligion))
                .addGap(3, 3, 3)
                .addGroup(pnlDatosMedicosImportantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbEmail4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtConvenio, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addComponent(txtNombreConvenio, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(pnlDatosMedicosImportantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbEmail11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(pnlDatosMedicosImportantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbEmail5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodigoReferido, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addComponent(txtNombreReferido, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout pnlDatosMedicosLayout = new javax.swing.GroupLayout(pnlDatosMedicos);
        pnlDatosMedicos.setLayout(pnlDatosMedicosLayout);
        pnlDatosMedicosLayout.setHorizontalGroup(
            pnlDatosMedicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosMedicosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDatosMedicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlDatosMedicosImportantes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlDatosMedicosLayout.createSequentialGroup()
                        .addGroup(pnlDatosMedicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbCiudad24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbCiudad23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlDatosMedicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombreMadre)
                            .addComponent(txtNombrePadre)))
                    .addComponent(lbCiudad19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlDatosMedicosLayout.createSequentialGroup()
                        .addGroup(pnlDatosMedicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbCiudad21, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbCiudad20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlDatosMedicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombreResponsable)
                            .addGroup(pnlDatosMedicosLayout.createSequentialGroup()
                                .addComponent(cmbParentesco, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(lbCiudad22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTelefonoResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        pnlDatosMedicosLayout.setVerticalGroup(
            pnlDatosMedicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosMedicosLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pnlDatosMedicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNombreMadre)
                    .addComponent(lbCiudad23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(pnlDatosMedicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbCiudad24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNombrePadre))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbCiudad19)
                .addGap(3, 3, 3)
                .addGroup(pnlDatosMedicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbCiudad20, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombreResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(pnlDatosMedicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbCiudad22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTelefonoResponsable)
                    .addComponent(cmbParentesco)
                    .addComponent(lbCiudad21, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(pnlDatosMedicosImportantes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlDatosMedicos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(pnlDatosMedicos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabDatosTerceros.addTab("Datos Medicos", jPanel6);

        lbFoto.setBackground(new java.awt.Color(204, 204, 204));
        lbFoto.setToolTipText("Esta herramienta solo recibe formatos JPG y PNG. Al montar la imagen recibe el tamaño de 250x250.");
        lbFoto.setMaximumSize(new java.awt.Dimension(200, 200));
        lbFoto.setMinimumSize(new java.awt.Dimension(200, 200));
        lbFoto.setName(""); // NOI18N
        lbFoto.setOpaque(true);
        lbFoto.setPreferredSize(new java.awt.Dimension(200, 200));
        lbFoto.setRequestFocusEnabled(false);

        btnImagen.setBackground(new java.awt.Color(204, 204, 204));
        btnImagen.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnImagen.setForeground(new java.awt.Color(255, 255, 255));
        btnImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/webcam.png"))); // NOI18N
        btnImagen.setText("AGREGAR IMAGEN    ");
        btnImagen.setToolTipText("");
        btnImagen.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnImagen.setEnabled(false);
        btnImagen.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnImagen.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImagenActionPerformed(evt);
            }
        });

        lbCupo6.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbCupo6.setText("CODIGO DEL SISTEMA:");

        txtIdSistema.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtIdSistema.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIdSistema.setDisabledTextColor(new java.awt.Color(255, 51, 0));
        txtIdSistema.setEnabled(false);
        txtIdSistema.setName("Codigo"); // NOI18N
        txtIdSistema.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdSistemaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdSistemaKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout pnlInvisibleLayout = new javax.swing.GroupLayout(pnlInvisible);
        pnlInvisible.setLayout(pnlInvisibleLayout);
        pnlInvisibleLayout.setHorizontalGroup(
            pnlInvisibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInvisibleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbCupo6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtIdSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(63, Short.MAX_VALUE))
        );
        pnlInvisibleLayout.setVerticalGroup(
            pnlInvisibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInvisibleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInvisibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCupo6)
                    .addComponent(txtIdSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(tabDatosTerceros, javax.swing.GroupLayout.PREFERRED_SIZE, 694, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlFormularioLayout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(pnlInvisible, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(lbFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btnImagen)
                        .addGap(15, 15, 15)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnlInvisible, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(tabDatosTerceros, javax.swing.GroupLayout.PREFERRED_SIZE, 638, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24))
        );

        scrFormulario.setViewportView(pnlFormulario);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrFormulario)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(scrFormulario)
                .addGap(0, 0, 0))
        );

        getAccessibleContext().setAccessibleName("Creación de contactos");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPlazoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlazoKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtPlazoKeyTyped

    private void txtIdentificacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdentificacionKeyTyped

    }//GEN-LAST:event_txtIdentificacionKeyTyped

    private void txtIdentificacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdentificacionKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ModeloContacto nodo = instancias.getSql().getDatosTercero(txtIdentificacion.getText());
            if (nodo.getId() == null) {
                txtDigito.requestFocus();
            } else {
                cargarTercero(txtIdentificacion.getText());
            }
        }
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtDigito.requestFocus();
        }
        if (txtIdentificacion.getText().equals("")) {
            btnImagen.setEnabled(false);
        } else {
            btnImagen.setEnabled(true);
        }
    }//GEN-LAST:event_txtIdentificacionKeyReleased

    private void txtEmailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            dtFechaNacimiento.requestFocus();
        }
    }//GEN-LAST:event_txtEmailKeyReleased

    private void txtNombreCompletoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreCompletoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtTelefono.requestFocus();
        }
    }//GEN-LAST:event_txtNombreCompletoKeyReleased

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtTelefonoKeyTyped

    private void txtTelefonoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtCelular.requestFocus();
        }
    }//GEN-LAST:event_txtTelefonoKeyReleased

    private void txtCupoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCupoKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtCupoKeyTyped

    private void txtCupoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCupoKeyReleased
        if (txtCupo.getText().equals("") || txtCupo.getText().equals(this.simbolo) || txtCupo.getText().equals(this.simbolo + " ")) {
            txtCupo.setText("0");
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtPlazo.requestFocus();
        } else {
            txtCupo.setText(big.setMoneda(big.getMoneda(txtCupo.getText())));
        }
    }//GEN-LAST:event_txtCupoKeyReleased

    private void txtCelularKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCelularKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtCelularKeyTyped

    private void txtCelularKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCelularKeyReleased
//        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
//            txtDireccion.requestFocus();
//        }
    }//GEN-LAST:event_txtCelularKeyReleased

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        chkNoResponsableIVA.setSelected(true);
        txtNota1.setText("");

        txtIdentificacion.setEditable(true);

        Limpiadores.limpiarPanel(pnlDatosObligatorios);
        Limpiadores.limpiarPanel(pnlDatosVentas);
        Limpiadores.limpiarPanel(pnlOtrosDatos);

        Limpiadores.limpiarPanel(pnlDatosVehiculo);
        edicionCamposDatosVehiculo(false);
        DefaultTableModel modelo = (DefaultTableModel) tblAutos.getModel();
        while (tblAutos.getRowCount() != 0) {
            modelo.removeRow(0);
        }

        Limpiadores.limpiarPanel(pnlDatosMedicos);
        Limpiadores.limpiarPanel(pnlDatosMedicosImportantes);
        
        
        
        Limpiadores.limpiarPanel(jPanel1);
        Limpiadores.limpiarPanel(jPanel6);

        lbFoto.setIcon(null);
        repaint();

        btnImagen.setEnabled(false);
        cmbDepartamento.setSelectedItem("Antioquia");
        dtFechaNacimiento.setCalendar(null);
        tabDatosTerceros.setSelectedIndex(0);
        txtIdentificacion.requestFocus();
        btnGuardarDatosVehiculo.setEnabled(false);
        btnActualizar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnImprimir.setEnabled(false);
        btnGuardar.setEnabled(true);
        btnEliminar.setText("INACTIVAR");
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/borrar2.png")));

        String consecutivo = "";
        int num = Integer.parseInt(instancias.getSql().getNumConsecutivo("TERCERO")[0].toString());
        consecutivo = String.valueOf(num);
        for (int i = 0; i < 8 - String.valueOf(num).length(); i++) {
            consecutivo = "0" + consecutivo;
        }
        txtIdSistema.setText("TERC-" + consecutivo);
        txtCupo.setText(this.simbolo + " 0");
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        String digitoVerificacion = txtDigito.getText().equals("") ? "" : "-" + txtDigito.getText();
        ModeloContacto datosContacto = new ModeloContacto(txtIdentificacion.getText(), txtTelefono.getText(), cmbCiudad.getSelectedItem().toString(), 
                txtEmail.getText(), cmbDepartamento.getSelectedItem().toString(), cmbTipoIdentificacion.getSelectedItem().toString(), 
                txtNombreCompleto.getText(), cmbRegimen.getSelectedItem().toString(), cmbAfiliado.getSelectedItem().toString(), 
                cmbTrabajador.getSelectedItem().toString(), txtNombreEps.getText(), cmbCodigoPostal.getSelectedItem().toString());

        ModeloContacto consultaDatosContacto = instancias.getSql().getDatosTercero(datosContacto.getId() + digitoVerificacion);
        if (!squemaDatosContacto.validacionesDatosContacto(datosContacto, this.tipo.equals("paciente"), 
                instancias.getConfiguraciones().isFacturaElectronica(), digitoVerificacion, consultaDatosContacto)) {
            return;
        }

        if (metodos.msgPregunta(this, "¿Desea continuar?") == 0) {
            
            if (txtCupo.getText().equals("")) {
                txtCupo.setText("0");
            }
            if (txtPlazo.getText().equals("")) {
                txtPlazo.setText("0");
            }
            if (txtNombreEps.getText().equals("")) {
                txtCodigoEps.setText("");
            }

            String idVendedor = "";
            if (cmbVendedor.getSelectedIndex() > 0) {
                idVendedor = instancias.getSql().getIdEmpleado(cmbVendedor.getSelectedItem().toString());
            }

            String fechaNacimiento = "";
            if (instancias.getConfiguraciones().isMedico() || instancias.getConfiguraciones().isLaboratorio()
                    || instancias.getConfiguraciones().isOftalmologia()) {
                try {
                    fechaNacimiento = metodos.desdeDate(dtFechaNacimiento.getCalendar());
                } catch (Exception e) {
                    metodos.msgAdvertenciaAjustado(this, "Debe ingresar la fecha de nacimiento");
                    return;
                }
            } else {
                try {
                    fechaNacimiento = metodos.desdeDate(dtFechaNacimiento.getCalendar());
                } catch (Exception e) {
                    fechaNacimiento = metodos.fechaConsulta(metodosGenerales.fecha());
                }
            }

            String convenio = "";
            if (jRadioButton1.isSelected()) {
                convenio = "1";
            }

            String consecutivo = "";
            int num = Integer.parseInt(instancias.getSql().getNumConsecutivo("TERCERO")[0].toString());
            consecutivo = String.valueOf(num);
            for (int i = 0; i < 8 - String.valueOf(num).length(); i++) {
                consecutivo = "0" + consecutivo;
            }

            String eps = txtCodigoEps.getText();
            if ("".equals(eps)) {
                eps = null;
            }

            Object[] vector = {"TERC-" + consecutivo, txtIdentificacion.getText() + digitoVerificacion, txtNombreCompleto.getText(),
                txtTelefono.getText(), txtCelular.getText(), txtDireccion.getText(), cmbCiudad.getSelectedItem(),
                txtEmail.getText(), cmbDepartamento.getSelectedItem(),
                metodos.fechaConsulta(metodosGenerales.fecha()), instancias.getUsuario(),
                cmbTipoIdentificacion.getSelectedItem().toString(), txtNombreCompleto.getText(),
                txtpNombre.getText(), txtsNombre.getText(), txtpApellido.getText(), txtsApellido.getText(),
                txtOcupacion.getText(), cmbNaturaleza.getSelectedItem().toString(), cmbRut.getSelectedItem().toString(),
                false, txtPlazo.getText(), big.getMoneda(txtCupo.getText()), "", "Colombia",
                cmbSexo.getSelectedItem(), cmbEstadoCivil.getSelectedItem(), txtOcupacion.getText(), cmbRegimen.getSelectedItem(),
                cmbAfiliado.getSelectedItem(), cmbTrabajador.getSelectedItem(),
                cmbZona.getSelectedItem(), fechaNacimiento, txtSangre.getText(), eps, instancias.getTerminal(),
                txtCodigoReferido.getText(), idVendedor, cmbLista.getSelectedItem(), txtNota.getText() + "" + txtNota1.getText(), "",
                "", cmbCodigoPostal.getSelectedItem().toString(), convenio, txtConvenio.getText(), txtReligion.getText(),
                txtNombreMadre.getText(), txtNombrePadre.getText(), txtNombreResponsable.getText(), cmbParentesco.getSelectedItem(), txtTelefonoResponsable.getText(),
                txtLugarNacimiento.getText(), txtBarrio.getText(), txtTipoCliente.getText(), txtTarjeta.getText(), "", "", "", "", 0,
                metodos.fechaConsulta(metodosGenerales.fecha()), metodos.fechaConsulta(metodosGenerales.fecha()), false, "", "", "",
                cmbTipoContacto.getSelectedItem().toString(), "", chkSiResponsableIVA.isSelected()};

            consultaDatosContacto = metodos.llenarTercero(vector);

            if (!instancias.getSql().agregarTercero(consultaDatosContacto)) {
                metodos.msgError(this, "Hubo un error al guardar");
                return;
            }

            for (int i = 0; i < tblAutos.getRowCount(); i++) {
                if (!instancias.getSql().agregarPlacas(txtIdentificacion.getText(), tblAutos.getValueAt(i, 0).toString(),
                        tblAutos.getValueAt(i, 1).toString(), tblAutos.getValueAt(i, 2).toString(), tblAutos.getValueAt(i, 3).toString(),
                        tblAutos.getValueAt(i, 4).toString(), tblAutos.getValueAt(i, 5).toString(), tblAutos.getValueAt(i, 6).toString(),
                        tblAutos.getValueAt(i, 7).toString())) {
                    metodos.msgError(this, "Hubo un error al guardar las placas");
                    return;
                }
            }

            if (this.tipo.equals("paciente")) {
                String historia = "HSTC-" + txtIdentificacion.getText();
                Object[] vector2 = {historia, "TERC-" + consecutivo, metodos.fechaConsulta(metodosGenerales.fecha()), "",
                    metodos.fechaConsulta(metodosGenerales.fecha()), metodos.fechaConsulta(metodosGenerales.fecha()), "",
                    "", "", "", "", "0&0&0&0", "", "", ""};

                ndHistoriaClinica nodoDos = metodos.llenarHistClinica(vector2);

                if (!instancias.getSql().agregarHistClinica(nodoDos)) {
                    metodos.msgError(this, "Error al guardar historia clinica");
                    return;
                } else {
                    metodos.msgExito(this, "Historia Clinica No." + historia.replace("HSTC-", ""));
                }
            } else {
                metodos.msgExito(this, "Cliente creado con éxito");
            }

            if (!instancias.getSql().aumentarConsecutivo("TERCERO", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("TERCERO")[0]) + 1)) {
                metodos.msgError(this, "Hubo un problema al guardar en el consecutivo del tercero");
            }

            String cliente = txtIdentificacion.getText();
            btnNuevoActionPerformed(evt);
            String cita = "";
            String medico = "";

            try {
                cita = instancias.getSql().getAgendasDelDia(cliente, metodos.fechaConsulta(metodosGenerales.fecha()));
            } catch (Exception e) {
            }

            if (!cita.equals("")) {
                medico = instancias.getSql().medicoDeLaCita(cita);
            }

            if (!cita.equalsIgnoreCase("")) {
                if (tipo.equals("paciente")) {
                    if (instancias.isGeneraOrdenMedica()) {
                        instancias.getOrdenMedica().cargarClienteDesdeAgenda(cliente, medico);
                        try {
                            instancias.getOrdenMedica().setSelected(true);
                        } catch (Exception e) {
                        }
                    } else {
                        instancias.getHistoriaC().setPaciente(cliente);
                        try {
                            instancias.getHistoriaC().setSelected(true);
                        } catch (Exception e) {
                        }
                    }
                } else {
                    instancias.getFactura().cargarCliente(cliente);
                    try {
                        instancias.getFacturaContenedor().setSelected(true);
                    } catch (Exception e) {
                    }

                }
            }

            lbNit.requestFocus();
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        if (this.tipo.equals("paciente")) {
            Object[] campos = {txtNombreCompleto, txtTelefono, cmbTipoIdentificacion, txtIdentificacion, txtNombreEps, cmbRegimen, cmbAfiliado, cmbTrabajador, cmbDepartamento, cmbCiudad};
            String faltantes = metodos.camposVacios(campos);

            if (!faltantes.equals("")) {
                metodos.msgAdvertencia(this, "No puede continuar, faltan los siguientes campos: " + faltantes);
                return;
            }
        } else {

            String faltantes = "";
            if (instancias.getConfiguraciones().isFacturaElectronica()) {
                Object[] campos = {txtNombreCompleto, txtTelefono, cmbTipoIdentificacion, txtIdentificacion, txtEmail, cmbDepartamento, cmbCiudad};
                faltantes = metodos.camposVacios(campos);
                if (cmbCodigoPostal.getSelectedIndex() == 0) {
                    metodos.msgAdvertencia(this, "El codigo postal es obligatorio");
                    return;
                }
            } else {
                Object[] campos = {txtNombreCompleto, txtTelefono, cmbTipoIdentificacion, txtIdentificacion};
                faltantes = metodos.camposVacios(campos);
            }

            if (!faltantes.equals("")) {
                metodos.msgAdvertencia(this, "No puede continuar, faltan los siguientes campos: " + faltantes);
                return;
            }
        }

        ModeloContacto nodo = instancias.getSql().getDatosTercero(txtIdentificacion.getText() + "-" + txtDigito.getText());
        if (nodo.getId() != null) {
            if (!txtIdSistema.getText().equals(nodo.getIdSistema())) {
                metodos.msgError(this, "El documento ya existe");
                txtIdentificacion.requestFocus();
                return;
            }
        }

        if (metodos.msgPregunta(this, "¿Desea continuar?") == 0) {

//            try {
            if (txtCupo.getText().equals("")) {
                txtCupo.setText("0");
            }

            if (txtNombreEps.getText().equals("")) {
                txtCodigoEps.setText("");
            }
            String idVendedor = "";
            if (cmbVendedor.getSelectedIndex() > 0) {
                idVendedor = instancias.getSql().getIdEmpleado(cmbVendedor.getSelectedItem().toString());
            }

            String fechaNacimiento = "";
//            boolean fechaNull = false;
            try {
                fechaNacimiento = metodos.desdeDate(dtFechaNacimiento.getCalendar());

            } catch (Exception e) {
                fechaNacimiento = metodosGenerales.fecha();
//                fechaNull = true;
            }

            String convenio = "";
            if (jRadioButton1.isSelected()) {
                convenio = "1";
            }

            String digito = txtDigito.getText();
            if (digito.equals("")) {
                digito = "";
            } else {
                digito = "-" + digito;
            }

            String eps = txtCodigoEps.getText();
            if ("".equals(eps)) {
                eps = null;
            }

            Object[] vector = {txtIdSistema.getText(), txtIdentificacion.getText() + digito, txtNombreCompleto.getText(), txtTelefono.getText(), txtCelular.getText(), txtDireccion.getText(), cmbCiudad.getSelectedItem(),
                txtEmail.getText(), cmbDepartamento.getSelectedItem(), nodo.getFecha(), nodo.getUsuario(), cmbTipoIdentificacion.getSelectedItem().toString(), txtNombreCompleto.getText(),
                txtpNombre.getText(), txtsNombre.getText(), txtpApellido.getText(), txtsApellido.getText(), txtOcupacion.getText(), cmbNaturaleza.getSelectedItem().toString(),
                cmbRut.getSelectedItem().toString(), nodo.isActivo(), txtPlazo.getText(), big.getMoneda(txtCupo.getText()), "", "Colombia", cmbSexo.getSelectedItem(),
                cmbEstadoCivil.getSelectedItem(), txtOcupacion.getText(), cmbRegimen.getSelectedItem(), cmbAfiliado.getSelectedItem(), cmbTrabajador.getSelectedItem(),
                cmbZona.getSelectedItem(), fechaNacimiento, txtSangre.getText(), eps, nodo.getTerminal(),
                txtCodigoReferido.getText(), idVendedor, cmbLista.getSelectedItem(), txtNota.getText() + "" + txtNota1.getText(), "",
                "", cmbCodigoPostal.getSelectedItem().toString(), convenio, txtConvenio.getText(), txtReligion.getText(), txtNombreMadre.getText(), txtNombrePadre.getText(),
                txtNombreResponsable.getText(), cmbParentesco.getSelectedItem(), txtTelefonoResponsable.getText(), txtLugarNacimiento.getText(), txtBarrio.getText(),
                txtTipoCliente.getText(), txtTarjeta.getText(), "", "", "", "", 0, null, null, false, "", "", "",
                cmbTipoContacto.getSelectedItem().toString(), "", chkSiResponsableIVA.isSelected()};

            nodo = metodos.llenarTercero(vector);
            if (!instancias.getSql().modificarTercero(nodo)) {
                metodos.msgError(this, "Hubo un problema al modificar el Tercero");
                return;
            }

            instancias.getSql().eliminar_registro("bdPlacas", " usuario = '" + txtIdSistema.getText() + "'");

            for (int i = 0; i < tblAutos.getRowCount(); i++) {

                String valor1, valor2, valor3, valor4, valor5, valor6, valor7;

                try {
                    valor1 = tblAutos.getValueAt(i, 1).toString();
                } catch (Exception e) {
                    valor1 = "";
                }

                try {
                    valor2 = tblAutos.getValueAt(i, 2).toString();
                } catch (Exception e) {
                    valor2 = "";
                }

                try {
                    valor3 = tblAutos.getValueAt(i, 3).toString();
                } catch (Exception e) {
                    valor3 = "";
                }

                try {
                    valor4 = tblAutos.getValueAt(i, 4).toString();
                } catch (Exception e) {
                    valor4 = "";
                }

                try {
                    valor5 = tblAutos.getValueAt(i, 5).toString();
                } catch (Exception e) {
                    valor5 = "";
                }

                try {
                    valor6 = tblAutos.getValueAt(i, 6).toString();
                } catch (Exception e) {
                    valor6 = "";
                }

                try {
                    valor7 = tblAutos.getValueAt(i, 7).toString();
                } catch (Exception e) {
                    valor7 = "";
                }

                if (!instancias.getSql().agregarPlacas(txtIdSistema.getText(), tblAutos.getValueAt(i, 0).toString(),
                        valor1, valor2, valor3, valor4, valor5, valor6, valor7)) {
                    metodos.msgError(this, "Hubo un error al guardar las placas");
                    return;
                }
            }

            metodos.msgExito(this, "Tercero modificado con éxito");

            btnNuevoActionPerformed(evt);
//            } catch (Exception e) {
//                metodos.msgError(this, "Hubo un problema al modiicar el Tercero");
//            }
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if (btnEliminar.getText().equalsIgnoreCase("ACTIVAR")) {
            if (metodos.msgPregunta(this, "¿Activar este registro?") == 0) {
                instancias.getSql().activarCliente(txtIdentificacion.getText());
                metodos.msgExito(this, "Cliente activado con éxito");
                btnNuevoActionPerformed(evt);
                return;
            }
        }

        if (metodos.msgPregunta(this, "¿Inactivar este registro?") == 0) {
            instancias.getSql().eliminarCliente(txtIdentificacion.getText());
            metodos.msgAdvertencia(this, "Cliente inactivado, este registro podrá ser activado posteriormente");
            btnNuevoActionPerformed(evt);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnBuscTercerosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscTercerosActionPerformed
        ventanaTerceros("", txtIdentificacion, "");
    }//GEN-LAST:event_btnBuscTercerosActionPerformed

    private void txtsApellidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsApellidoKeyReleased
        txtNombreCompleto.setText(txtpNombre.getText() + " " + txtsNombre.getText() + " " + txtpApellido.getText() + " " + txtsApellido.getText());
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtLugarNacimiento.requestFocus();
        }
    }//GEN-LAST:event_txtsApellidoKeyReleased

    private void txtpApellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpApellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpApellidoActionPerformed

    private void txtpApellidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpApellidoKeyReleased
        txtNombreCompleto.setText(txtpNombre.getText() + " " + txtsNombre.getText() + " " + txtpApellido.getText() + " " + txtsApellido.getText());
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtsApellido.requestFocus();
        }
    }//GEN-LAST:event_txtpApellidoKeyReleased

    private void txtsNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsNombreKeyReleased
        txtNombreCompleto.setText(txtpNombre.getText() + " " + txtsNombre.getText() + " " + txtpApellido.getText() + " " + txtsApellido.getText());
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtpApellido.requestFocus();
        }
    }//GEN-LAST:event_txtsNombreKeyReleased

    private void txtsNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsNombreKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsNombreKeyTyped

    private void txtpNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpNombreKeyReleased
        txtNombreCompleto.setText(txtpNombre.getText() + " " + txtsNombre.getText() + " " + txtpApellido.getText() + " " + txtsApellido.getText());
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtsNombre.requestFocus();
        }
    }//GEN-LAST:event_txtpNombreKeyReleased

    private void txtpNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpNombreKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpNombreKeyTyped

    private void cmbTipoIdentificacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbTipoIdentificacionKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            if (cmbTipoIdentificacion.getSelectedIndex() == 0) {

            } else if (cmbTipoIdentificacion.getSelectedItem().equals("NIT")) {
                txtTarjeta.requestFocus();
            } else {
                txtTarjeta.requestFocus();
            }
        }
    }//GEN-LAST:event_cmbTipoIdentificacionKeyReleased

    private void txtCodigoEpsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoEpsKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarEps(txtCodigoEps.getText());
        } else {
            txtNombreEps.setText("");
        }
    }//GEN-LAST:event_txtCodigoEpsKeyReleased

    private void txtPlazoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlazoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtCodigoReferido.requestFocus();
        }
    }//GEN-LAST:event_txtPlazoKeyReleased

    private void cmbTipoIdentificacionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbTipoIdentificacionItemStateChanged
        if (cmbTipoIdentificacion.getSelectedItem().toString().equals("NIT") || cmbTipoIdentificacion.getSelectedItem().toString().equals("Nit")) {
            txtpNombre.setEditable(false);
            txtsNombre.setEditable(false);
            txtpApellido.setEditable(false);
            txtsApellido.setEditable(false);
            txtNombreCompleto.setEditable(true);
            txtpNombre.setText("");
            txtsNombre.setText("");
            txtpApellido.setText("");
            txtsApellido.setText("");
        } else {
            txtpNombre.setEditable(true);
            txtsNombre.setEditable(true);
            txtpApellido.setEditable(true);
            txtsApellido.setEditable(true);
            txtNombreCompleto.setEditable(false);
            txtNombreCompleto.setText("");
        }
    }//GEN-LAST:event_cmbTipoIdentificacionItemStateChanged

    private void txtsNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsNombreActionPerformed

    private void txtNombreEpsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreEpsKeyReleased

        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {

        }
    }//GEN-LAST:event_txtNombreEpsKeyReleased

    private void txtCupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCupoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCupoActionPerformed

    private void txtDireccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtEmail.requestFocus();
        }
    }//GEN-LAST:event_txtDireccionKeyReleased

    private void txtNombreReferidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreReferidoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreReferidoKeyReleased

    private void txtCodigoReferidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoReferidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoReferidoActionPerformed

    private void txtCodigoReferidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoReferidoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarReferido();
        } else if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            cmbVendedor.requestFocus();
        } else {
            txtNombreReferido.setText("");
        }
    }//GEN-LAST:event_txtCodigoReferidoKeyReleased

    private void dtFechaNacimientoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dtFechaNacimientoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtCupo.requestFocus();
        }
    }//GEN-LAST:event_dtFechaNacimientoKeyReleased

    private void cmbVendedorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbVendedorKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            cmbLista.requestFocus();
        }
    }//GEN-LAST:event_cmbVendedorKeyReleased

    private void cmbListaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbListaKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtNota.requestFocus();
        }

    }//GEN-LAST:event_cmbListaKeyReleased

    private void txtNotaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNotaKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            btnGuardar.requestFocus();
        }
    }//GEN-LAST:event_txtNotaKeyReleased

    private void cmbSexoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbSexoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbEstadoCivil.requestFocus();
        }
    }//GEN-LAST:event_cmbSexoKeyReleased

    private void cmbEstadoCivilKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbEstadoCivilKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtOcupacion.requestFocus();
        }
    }//GEN-LAST:event_cmbEstadoCivilKeyReleased

    private void txtOcupacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOcupacionKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ventanaOcupaciones(txtOcupacion.getText());
        }
    }//GEN-LAST:event_txtOcupacionKeyReleased

    public void ventanaOcupaciones(String ocupacion) {
        buscOcupaciones buscar = new buscOcupaciones(instancias.getMenu(), rootPaneCheckingEnabled);
        buscar.setLocationRelativeTo(null);
        instancias.setBuscarOcupaciones(buscar);
        instancias.setCampoActual(txtOcupacion);
        txtOcupacion.requestFocus();
        buscar.noEncontrado(ocupacion);
        buscar.show();
    }

    private void cmbRegimenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbRegimenKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbAfiliado.requestFocus();
        }
    }//GEN-LAST:event_cmbRegimenKeyReleased

    private void cmbAfiliadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbAfiliadoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbTrabajador.requestFocus();
        }
    }//GEN-LAST:event_cmbAfiliadoKeyReleased

    private void cmbTrabajadorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbTrabajadorKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbZona.requestFocus();
        }
    }//GEN-LAST:event_cmbTrabajadorKeyReleased

    private void cmbZonaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbZonaKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtSangre.requestFocus();
        }
    }//GEN-LAST:event_cmbZonaKeyReleased

    private void txtConvenioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConvenioKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ModeloContacto nodo = instancias.getSql().getDatosTercero(txtConvenio.getText());
            if (nodo.getId() != null) {
                txtNombreConvenio.setText(nodo.getCompleta());
                return;
            }
            ventanaTerceros(txtConvenio.getText(), txtConvenio, "convenio");
        } else {
            txtNombreConvenio.setText("");
        }
    }//GEN-LAST:event_txtConvenioKeyReleased

    private void cmbNaturalezaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbNaturalezaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbNaturalezaKeyReleased

    private void cmbRutKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbRutKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbRutKeyReleased

    private void txtNombreConvenioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreConvenioKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreConvenioKeyReleased

    private void cmbDepartamentoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbDepartamentoItemStateChanged
        try {
            consultarMunicipios(cmbDepartamento.getSelectedItem().toString());
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cmbDepartamentoItemStateChanged

    private void cmbParentescoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbParentescoKeyReleased

    }//GEN-LAST:event_cmbParentescoKeyReleased

    private void txtBarrioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBarrioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBarrioActionPerformed

    private void txtBarrioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBarrioKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBarrioKeyReleased

    private void btnImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImagenActionPerformed

        JFileChooser chooser = new JFileChooser();
        ImagePreviewPanel preview = new ImagePreviewPanel();
        chooser.setAccessory(preview);
        chooser.addPropertyChangeListener(preview);

        FileNameExtensionFilter filtroImagen = new FileNameExtensionFilter("JPG, PNG & GIF", "jpg", "png", "gif");
        chooser.setFileFilter(filtroImagen);

        int respuesta = chooser.showSaveDialog(this);

        if (respuesta == JFileChooser.APPROVE_OPTION) {
            Path FROM = Paths.get(chooser.getSelectedFile().getAbsolutePath());

            String id = "";

            if (lbFoto.getToolTipText().equalsIgnoreCase("")) {
                id = Arrays.toString(instancias.getSql().getNumConsecutivo("IMG"));

            } else {
                id = lbFoto.getToolTipText();
            }

            Path TO = Paths.get(System.getProperty("user.dir") + "\\imagenes\\terceros\\" + txtIdSistema.getText() + ".jpg");

            //sobreescribir el fichero de destino, si existe, y copiar
            // los atributos, incluyendo los permisos rwx
            CopyOption[] options = new CopyOption[]{
                StandardCopyOption.REPLACE_EXISTING,
                StandardCopyOption.COPY_ATTRIBUTES
            };

            try {
//                Files.copy(FROM, TO, options);

                metodos.montarImagenTerceros(FROM.toString(), TO.toString());

                ImageIcon fot = new ImageIcon(chooser.getSelectedFile().getAbsolutePath());
                Icon icono = new ImageIcon(fot.getImage().getScaledInstance(lbFoto.getWidth(), lbFoto.getHeight(), Image.SCALE_DEFAULT));
                lbFoto.setIcon(icono);
                this.repaint();

            } catch (Exception ex) {
                metodos.msgError(this, "Hubo un error al cargar el archivo");
            }

        }
    }//GEN-LAST:event_btnImagenActionPerformed

    private void cmbAfiliadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAfiliadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbAfiliadoActionPerformed

    private void txtNota1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNota1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNota1KeyReleased

    private void txtPlacaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlacaKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtTipoVehiculo.requestFocus();
        } else {
            txtPlaca.setText(txtPlaca.getText().toUpperCase());
        }
    }//GEN-LAST:event_txtPlacaKeyReleased

    private void btnGuardarDatosVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarDatosVehiculoActionPerformed
        ModeloDatosVehiculo datosVehiculo = new ModeloDatosVehiculo(txtPlaca.getText(), txtTipoVehiculo.getText(), txtChasis.getText(), txtMotor.getText(),
                txtModelo.getText(), txtMarca.getText(), dtFechaCompra.getText(), txtColor.getText());

        if (!squemaDatosVehiculo.validacionesDatosVehiculo(datosVehiculo, tblAutos)) {
            return;
        }

        DefaultTableModel modelo = (DefaultTableModel) tblAutos.getModel();
        modelo.addRow(new Object[]{txtPlaca.getText(), txtTipoVehiculo.getText(), txtModelo.getText(), txtMarca.getText(),
            txtColor.getText(), txtChasis.getText(), txtMotor.getText(), dtFechaCompra.getText()});

        Limpiadores.limpiarPanel(pnlDatosVehiculo);
    }//GEN-LAST:event_btnGuardarDatosVehiculoActionPerformed

    private void popBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popBorrarActionPerformed
        if (tblAutos.getSelectedRow() > -1) {
            int fila = tblAutos.getSelectedRow();

            DefaultTableModel modelo = (DefaultTableModel) tblAutos.getModel();
            modelo.removeRow(fila);
            tblAutos.removeEditor();
        } else {
            metodos.msgAdvertencia(null, "Seleccione una placa");
        }
    }//GEN-LAST:event_popBorrarActionPerformed

    private void dtFechaCompraOnCommit(datechooser.events.CommitEvent evt) {//GEN-FIRST:event_dtFechaCompraOnCommit

    }//GEN-LAST:event_dtFechaCompraOnCommit

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        instancias.getReporte().setImagenInforme1(txtIdSistema.getText());
        instancias.getReporte().setImagenInforme();

        try {
            instancias.getReporte().ver_informePaciente(txtIdSistema.getText());
        } catch (Exception e) {
            instancias.getReporte().ver_informePaciente1(txtIdSistema.getText());
        }

    }//GEN-LAST:event_btnImprimirActionPerformed

    private void txtTipoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTipoClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTipoClienteActionPerformed

    private void txtTipoClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTipoClienteKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            cmbTipoIdentificacion.requestFocus();
        }
    }//GEN-LAST:event_txtTipoClienteKeyReleased

    private void txtTipoClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTipoClienteKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTipoClienteKeyTyped

    private void txtTarjetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTarjetaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTarjetaActionPerformed

    private void txtTarjetaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTarjetaKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtpNombre.requestFocus();
        }
    }//GEN-LAST:event_txtTarjetaKeyReleased

    private void txtTarjetaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTarjetaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTarjetaKeyTyped

    private void txtLugarNacimientoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLugarNacimientoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtTelefono.requestFocus();
        }
    }//GEN-LAST:event_txtLugarNacimientoKeyReleased

    private void txtIdSistemaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdSistemaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdSistemaKeyReleased

    private void txtIdSistemaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdSistemaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdSistemaKeyTyped

    private void txtDigitoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDigitoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtTipoCliente.requestFocus();
        }
    }//GEN-LAST:event_txtDigitoKeyReleased

    private void txtDigitoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDigitoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDigitoKeyTyped

    private void lbNitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbNitKeyReleased
        txtIdentificacion.requestFocus();
    }//GEN-LAST:event_lbNitKeyReleased

    private void txtTipoVehiculoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTipoVehiculoKeyPressed

    }//GEN-LAST:event_txtTipoVehiculoKeyPressed

    private void txtTipoVehiculoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTipoVehiculoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!txtTipoVehiculo.getText().equals("")) {
                txtChasis.requestFocus();
            } else {
                ventanaTipoVehiculos(txtTipoVehiculo.getText());
            }
        } else {
            txtTipoVehiculo.setText("");
        }
    }//GEN-LAST:event_txtTipoVehiculoKeyReleased

    private void txtMarcaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMarcaKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!txtMarca.getText().equals("")) {
                btnNuevo.requestFocus();
            } else {
                ventanaMarcas(txtMarca.getText());
            }
        } else {
            txtMarca.setText("");
        }
    }//GEN-LAST:event_txtMarcaKeyReleased

    private void txtColorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtColorKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String color = txtColor.getText();

            if (!txtColor.getText().equals("")) {
                btnGuardarDatosVehiculo.requestFocus();
            } else {
                ventanaColores1(txtColor.getText());
            }
        } else {
            txtColor.setText("");
        }
    }//GEN-LAST:event_txtColorKeyReleased

    private void btnBuscTerceros1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscTerceros1ActionPerformed
        ventanaOcupaciones("");
    }//GEN-LAST:event_btnBuscTerceros1ActionPerformed

    private void cmbNaturalezaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbNaturalezaActionPerformed
        if (cmbNaturaleza.getSelectedItem().equals("Persona juridica")) {
            cmbRut.setSelectedItem("Si");
        }
    }//GEN-LAST:event_cmbNaturalezaActionPerformed

    private void txtIdentificacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdentificacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdentificacionActionPerformed

    private void cmbCiudadItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbCiudadItemStateChanged
        Object[][] codigosPostales = instancias.getSql().getCodigosPostales(cmbDepartamento.getSelectedItem().toString(), cmbCiudad.getSelectedItem().toString());
        cmbCodigoPostal.removeAllItems();
        cmbCodigoPostal.addItem(" ");

        for (int i = 0; i < codigosPostales.length; i++) {
            if (null != codigosPostales[i][0]) {
                cmbCodigoPostal.addItem(codigosPostales[i][0]);
            }
        }
    }//GEN-LAST:event_cmbCiudadItemStateChanged

    private void cmbCodigoPostalItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbCodigoPostalItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbCodigoPostalItemStateChanged

    private void cmbTipoContactoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbTipoContactoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTipoContactoKeyReleased

    private void txtDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionActionPerformed

    public void ventanaColores1(String nit) {
        buscColores buscar = new buscColores(instancias.getMenu(), rootPaneCheckingEnabled);
        buscar.setLocationRelativeTo(null);
        instancias.setBuscColores(buscar);
        instancias.setCampoActual(txtColor);
        txtColor.requestFocus();
        buscar.setInstancia(instancias);
        buscar.noEncontrado(nit);
        buscar.show();
    }

    public void ventanaMarcas(String nit) {
        buscMarcas buscar = new buscMarcas(instancias.getMenu(), rootPaneCheckingEnabled);
        buscar.setLocationRelativeTo(null);
        instancias.setBuscMarcas(buscar);
        instancias.setCampoActual(txtMarca);
        txtMarca.requestFocus();
        buscar.setInstancia(instancias);
        buscar.noEncontrado(nit);
        buscar.show();
    }

    public void ventanaTipoVehiculos(String nit) {
        buscTipoVehiculo buscar = new buscTipoVehiculo(instancias.getMenu(), true);
        buscar.setLocationRelativeTo(null);
        instancias.setBuscTipoVehiculo(buscar);
        instancias.setCampoActual(txtTipoVehiculo);
        txtTipoVehiculo.requestFocus();
        buscar.setInstancia(instancias);
        buscar.noEncontrado(nit);
        buscar.show();
    }

    public void cargarReferido() {
        ModeloContacto nodo = instancias.getSql().getDatosTercero(txtCodigoReferido.getText());
        if (nodo.getId() != null) {
            txtNombreReferido.setText(nodo.getNombre());
            cmbVendedor.requestFocus();
        } else {
            ventanaTercerosReferidos(txtCodigoReferido.getText());
        }
    }

    public void cargarTercero(String Id) {

        ModeloContacto nodo = instancias.getSql().getDatosTercero(Id);

        if (nodo.getId() != null) {

            edicionCamposDatosVehiculo(true);

            txtIdSistema.setText(nodo.getIdSistema());
            txtIdentificacion.setText(nodo.getId().split("-")[0]);
            try {
                txtDigito.setText(nodo.getId().split("-")[1]);
            } catch (Exception e) {
                txtDigito.setText("");
            }

            try {
                txtTelefono.setText(nodo.getTelefono());
            } catch (Exception e) {
            }

            try {
                txtTarjeta.setText(nodo.getCargo());
            } catch (Exception e) {
            }

            try {
                txtTipoCliente.setText(nodo.getNombreContacto());
            } catch (Exception e) {
            }

            try {
                txtCelular.setText(nodo.getCelular());
            } catch (Exception e) {
            }

            try {
                txtDireccion.setText(nodo.getDireccion());
            } catch (Exception e) {
            }

            try {
                cmbDepartamento.setSelectedItem(nodo.getDepartamento());
            } catch (Exception e) {
                cmbDepartamento.addItem(nodo.getDepartamento());
                cmbDepartamento.setSelectedItem(nodo.getDepartamento());
            }

            try {
                cmbCiudad.setSelectedItem(nodo.getCiudad());
            } catch (Exception e) {
                cmbCiudad.addItem(nodo.getCiudad());
                cmbCiudad.setSelectedItem(nodo.getCiudad());
            }

            try {
                cmbCodigoPostal.setSelectedItem(nodo.getCodigoPostal());
            } catch (Exception e) {
            }

            try {
                txtEmail.setText(nodo.getEmail());
            } catch (Exception e) {
            }

            try {
                dtFechaNacimiento.setCalendar(metodos.haciaDate(nodo.getNacimiento()));
            } catch (Exception e) {
            }

            try {
                if (nodo.getTipo().equals("CEDULA") || nodo.getTipo().equals("CC") || nodo.getTipo().equals("Cedula") || nodo.getTipo().equals("Cédula")) {
                    cmbTipoIdentificacion.setSelectedItem("Cédula de ciudadanía");
                } else if (nodo.getTipo().equals("NIT") || nodo.getTipo().equals("Nit")) {
                    cmbTipoIdentificacion.setSelectedItem("Nit");
                } else {
                    cmbTipoIdentificacion.setSelectedItem(nodo.getTipo());
                }
            } catch (Exception e) {
            }

            try {
                txtpNombre.setText(nodo.getpNombre());
            } catch (Exception e) {
            }
            try {
                txtsNombre.setText(nodo.getsNombre());
            } catch (Exception e) {
            }
            try {
                txtpApellido.setText(nodo.getpApellido());
            } catch (Exception e) {
            }
            try {
                txtsApellido.setText(nodo.getsApellido());
            } catch (Exception e) {
            }

            try {
                txtOcupacion.setText(nodo.getOcupacion());
            } catch (Exception e) {
            }
            try {
                cmbEstadoCivil.setSelectedItem(nodo.getEstado());
            } catch (Exception e) {
            }

            try {
                cmbRegimen.setSelectedItem(nodo.getRegimen());
            } catch (Exception e) {
            }

            //--------------------------------------------------------
            try {
                cmbTrabajador.setSelectedItem(nodo.getTipoTrabajador());
            } catch (Exception e) {
            }
            try {
                cmbAfiliado.setSelectedItem(nodo.getAfiliado());
            } catch (Exception e) {
            }

            try {
                txtCodigoEps.setText(nodo.getEps());
                if (!nodo.getEps().equals("")) {
                    cargarEps(nodo.getEps());
                }

            } catch (Exception e) {
            }

            try {
                cmbNaturaleza.setSelectedItem(nodo.getNaturaleza());
            } catch (Exception e) {
            }

            try {
                cmbRut.setSelectedItem(nodo.getRut());
            } catch (Exception e) {
            }

            try {
                cmbTipoContacto.setSelectedItem(nodo.getTipoTercero());
            } catch (Exception e) {
            }

            try {
                txtPlazo.setText(nodo.getPlazo());
            } catch (Exception e) {
            }

            try {
                txtCupo.setText(big.setMoneda(big.getBigDecimal(nodo.getCupo())));
            } catch (Exception e) {
            }

            try {
                if (!nodo.getReferido().equals("")) {
                    txtCodigoReferido.setText(nodo.getReferido());
                    cargarReferido();
                }
            } catch (Exception e) {
            }

            try {
                String nombreVendedor = "";
                if (!nodo.getVendedor().equals("")) {
                    nombreVendedor = instancias.getSql().getNombreEmpleado(nodo.getVendedor());
                    cmbVendedor.setSelectedItem(nombreVendedor);
                }
            } catch (Exception e) {
            }

            try {
                cmbLista.setSelectedItem(nodo.getLista());
            } catch (Exception e) {
            }

            try {
                if (tipo.equals("paciente")) {
                    txtNota1.setText(nodo.getNota());
                } else {
                    txtNota.setText(nodo.getNota());
                }

            } catch (Exception e) {
            }

            try {
                txtReligion.setText(nodo.getReligion());
            } catch (Exception e) {
            }

            try {
                txtNombreMadre.setText(nodo.getNombreMadre());
            } catch (Exception e) {
            }

            try {
                txtNombrePadre.setText(nodo.getNombrePadre());
            } catch (Exception e) {
            }

            try {
                txtNombreResponsable.setText(nodo.getNombreResponsable());
            } catch (Exception e) {
            }

            try {
                cmbParentesco.setSelectedItem(nodo.getParentescoResponsable());
            } catch (Exception e) {
            }

            try {
                txtTelefonoResponsable.setText(nodo.getTelefonoResponsable());
            } catch (Exception e) {
            }

            try {
                cmbSexo.setSelectedItem(nodo.getSexo());
            } catch (Exception e) {
            }

            try {
                txtSangre.setText(nodo.getSangre());
            } catch (Exception e) {
            }
            try {
                txtLugarNacimiento.setText(nodo.getLugarNacimiento());
            } catch (Exception e) {
            }
            try {
                txtBarrio.setText(nodo.getBarrio());
            } catch (Exception e) {
            }

            if (nodo.isResponsableIva()) {
                chkSiResponsableIVA.setSelected(true);
            } else {
                chkNoResponsableIVA.setSelected(true);
            }

            try {
                txtConvenio.setText(nodo.getConvenioActual());
                if (!txtConvenio.getText().equals("")) {
                    ModeloContacto nodoConv = instancias.getSql().getDatosTercero(txtConvenio.getText());
                    if (nodoConv.getId() != null) {
                        txtNombreConvenio.setText(nodoConv.getCompleta());
//                    cargarConvenio(nodo.getConvenioActual());
                    }
                }
            } catch (Exception e) {
            }

            txtNombreCompleto.setText(nodo.getNombre());

            ImageIcon fot = new ImageIcon(System.getProperty("user.dir") + "\\imagenes\\terceros\\" + txtIdSistema.getText() + ".jpg");
            Icon icono = new ImageIcon(fot.getImage().getScaledInstance(lbFoto.getWidth(), lbFoto.getHeight(), Image.SCALE_DEFAULT));
            lbFoto.setIcon(icono);
            this.repaint();

            btnGuardarDatosVehiculo.setEnabled(true);
            btnActualizar.setEnabled(true);
            btnEliminar.setEnabled(true);
            btnImprimir.setEnabled(true);
            btnGuardar.setEnabled(false);
            btnImagen.setEnabled(true);

            if (nodo.isActivo()) {
                btnEliminar.setText("ACTIVAR");
                btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/agregar.png")));
            } else {
                btnEliminar.setText("INACTIVAR");
                btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/borrar2.png")));
            }

            Object[][] placas = instancias.getSql().getPlacasUsuario(nodo.getIdSistema());

            DefaultTableModel modelo = (DefaultTableModel) tblAutos.getModel();

            for (int i = 0; i < placas.length; i++) {
                modelo.addRow(new Object[]{placas[i][1], placas[i][2], placas[i][3], placas[i][4], placas[i][5], placas[i][6], placas[i][7], placas[i][8]});
            }

            return;
        }
        ventanaTerceros(Id, txtIdentificacion, "");
    }

    public void ventanaTerceros(String nit, JTextField campo, String opcionBuscador) {
        buscClientes buscar = new buscClientes(instancias.getMenu(), rootPaneCheckingEnabled, true, null, "");
        buscar.setLocationRelativeTo(null);
        buscar.setTipoBuscador(opcionBuscador);
        instancias.setBusClientes(buscar);
        instancias.setCampoActual(campo);
        campo.requestFocus();
        buscar.noEncontrado(nit);
        buscar.show();
    }

    public void ventanaTercerosReferidos(String nit) {
        buscClientes buscar = new buscClientes(instancias.getMenu(), rootPaneCheckingEnabled, true, null, "");
        buscar.setLocationRelativeTo(null);
        instancias.setBusClientes(buscar);
        instancias.setCampoActual(txtCodigoReferido);
        txtCodigoReferido.requestFocus();
        buscar.noEncontrado(nit);
        buscar.show();
    }

    public void cargarEps(String nit) {
        ndEpsPrecargados nodo = instancias.getSql().getDatosEpsPrecargados(nit);

        if (nodo.getId() != null) {
            txtNombreEps.setText(nodo.getNombre());
            txtNombreEps.requestFocus();
            return;
        }
        ventanaEps(nit);
    }

    public void setVendedores(String[] Vendedores) {
        cmbVendedor.removeAllItems();
        for (String Vendedore : Vendedores) {
            cmbVendedor.addItem(Vendedore);
        }
    }

    public void cargarConvenio(String nit) {
        txtConvenio.setText(nit);

        ndConvenio nodo = instancias.getSql().getDatosConvenio(nit, "nit");

        if (nodo.getNit() != null) {
            txtNombreConvenio.setText(nodo.getConvenio());
            txtConvenio.requestFocus();
            return;
        }
        ventanaConvenio(nit);
    }

    public void ventanaConvenio(String nit) {
        buscConvenio buscar = new buscConvenio(instancias.getMenu(), rootPaneCheckingEnabled);
        buscar.setLocationRelativeTo(null);
        buscar.setOpc(1);
        instancias.setBuscConvenio(buscar);
        instancias.setCampoActual(txtConvenio);
        txtConvenio.requestFocus();
        buscar.setInstancia(instancias);
        buscar.noEncontrado(nit);
        buscar.show();
    }

    public void ventanaEps(String nit) {
        buscEpsPrecargadas buscar = new buscEpsPrecargadas(instancias.getMenu(), rootPaneCheckingEnabled);
        buscar.setLocationRelativeTo(null);
        instancias.setBuscEpsPre(buscar);
        instancias.setCampoActual(txtCodigoEps);
        txtCodigoEps.requestFocus();
        buscar.noEncontrado(nit);
        buscar.show();
    }

    private void edicionCamposDatosVehiculo(boolean tipoAccion) {
        txtPlaca.setEnabled(tipoAccion);
        txtChasis.setEnabled(tipoAccion);
        txtMotor.setEnabled(tipoAccion);
        txtTipoVehiculo.setEnabled(tipoAccion);
        txtModelo.setEnabled(tipoAccion);
        txtMarca.setEnabled(tipoAccion);
        txtColor.setEnabled(tipoAccion);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBuscTerceros;
    private javax.swing.JButton btnBuscTerceros1;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnGuardarDatosVehiculo;
    private javax.swing.JButton btnImagen;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JCheckBox chkNoResponsableIVA;
    private javax.swing.JCheckBox chkSiResponsableIVA;
    private javax.swing.JComboBox cmbAfiliado;
    private javax.swing.JComboBox cmbCiudad;
    private javax.swing.JComboBox cmbCodigoPostal;
    private javax.swing.JComboBox cmbDepartamento;
    private javax.swing.JComboBox cmbEstadoCivil;
    private javax.swing.JComboBox cmbLista;
    private javax.swing.JComboBox cmbNaturaleza;
    private javax.swing.JComboBox cmbParentesco;
    private javax.swing.JComboBox cmbRegimen;
    private javax.swing.JComboBox cmbRut;
    private javax.swing.JComboBox cmbSexo;
    private javax.swing.JComboBox cmbTipoContacto;
    private javax.swing.JComboBox cmbTipoIdentificacion;
    private javax.swing.JComboBox cmbTrabajador;
    private javax.swing.JComboBox cmbVendedor;
    private javax.swing.JComboBox cmbZona;
    private datechooser.beans.DateChooserCombo dtFechaCompra;
    private com.toedter.calendar.JDateChooser dtFechaNacimiento;
    private javax.swing.ButtonGroup grpResponsableIva;
    private javax.swing.ButtonGroup grupoConvenio;
    private javax.swing.ButtonGroup grupoResidencia;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JMonthChooser jMonthChooser1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbApellido;
    private javax.swing.JLabel lbBarrio;
    private javax.swing.JLabel lbBarrio1;
    private javax.swing.JLabel lbCelular;
    private javax.swing.JLabel lbCiudad;
    private javax.swing.JLabel lbCiudad11;
    private javax.swing.JLabel lbCiudad12;
    private javax.swing.JLabel lbCiudad13;
    private javax.swing.JLabel lbCiudad14;
    private javax.swing.JLabel lbCiudad15;
    private javax.swing.JLabel lbCiudad19;
    private javax.swing.JLabel lbCiudad20;
    private javax.swing.JLabel lbCiudad21;
    private javax.swing.JLabel lbCiudad22;
    private javax.swing.JLabel lbCiudad23;
    private javax.swing.JLabel lbCiudad24;
    private javax.swing.JLabel lbCiudad3;
    private javax.swing.JLabel lbCiudad4;
    private javax.swing.JLabel lbCiudad6;
    private javax.swing.JLabel lbCiudad7;
    private javax.swing.JLabel lbCiudad9;
    private javax.swing.JLabel lbCupo;
    private javax.swing.JLabel lbCupo1;
    private javax.swing.JLabel lbCupo6;
    private javax.swing.JLabel lbDepartamento;
    private javax.swing.JLabel lbDepartamento3;
    private javax.swing.JLabel lbDepartamento5;
    private javax.swing.JLabel lbDireccion1;
    private javax.swing.JLabel lbEmail;
    private javax.swing.JLabel lbEmail11;
    private javax.swing.JLabel lbEmail2;
    private javax.swing.JLabel lbEmail3;
    private javax.swing.JLabel lbEmail4;
    private javax.swing.JLabel lbEmail5;
    private javax.swing.JLabel lbEmail6;
    private javax.swing.JLabel lbEmail7;
    private javax.swing.JLabel lbEmail8;
    private javax.swing.JLabel lbFecha;
    private javax.swing.JLabel lbFecha1;
    private javax.swing.JLabel lbFoto;
    private javax.swing.JLabel lbNit;
    private javax.swing.JLabel lbNit1;
    private javax.swing.JLabel lbNit2;
    private javax.swing.JLabel lbNit3;
    private javax.swing.JLabel lbNota;
    private javax.swing.JLabel lbPNombre;
    private javax.swing.JLabel lbPlazo;
    private javax.swing.JLabel lbPlazo1;
    private javax.swing.JLabel lbPlazo2;
    private javax.swing.JLabel lbPlazo3;
    private javax.swing.JLabel lbPlazo4;
    private javax.swing.JLabel lbPlazo5;
    private javax.swing.JLabel lbRazon;
    private javax.swing.JLabel lbSNombre;
    private javax.swing.JLabel lbSapellido;
    private javax.swing.JLabel lbTelefono;
    private javax.swing.JLabel lbTipo;
    private javax.swing.JLabel lbVendedor;
    private javax.swing.JPanel pnlDatosMedicos;
    private javax.swing.JPanel pnlDatosMedicosImportantes;
    private javax.swing.JPanel pnlDatosObligatorios;
    private javax.swing.JPanel pnlDatosVehiculo;
    private javax.swing.JPanel pnlDatosVentas;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JPanel pnlInvisible;
    private javax.swing.JPanel pnlOtrosDatos;
    private javax.swing.JMenuItem popBorrar;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JTabbedPane tabDatosTerceros;
    private javax.swing.JTable tblAutos;
    private javax.swing.JTextField txtBarrio;
    private javax.swing.JTextField txtCelular;
    private javax.swing.JTextField txtChasis;
    private javax.swing.JTextField txtCodigoEps;
    private javax.swing.JTextField txtCodigoReferido;
    private javax.swing.JTextField txtColor;
    private javax.swing.JTextField txtConvenio;
    private javax.swing.JTextField txtCupo;
    private javax.swing.JTextField txtDigito;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtIdSistema;
    private javax.swing.JTextField txtIdentificacion;
    private javax.swing.JTextField txtLugarNacimiento;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JTextField txtModelo;
    private javax.swing.JTextField txtMotor;
    private javax.swing.JTextField txtNombreCompleto;
    private javax.swing.JTextField txtNombreConvenio;
    private javax.swing.JTextField txtNombreEps;
    private javax.swing.JTextField txtNombreMadre;
    private javax.swing.JTextField txtNombrePadre;
    private javax.swing.JTextField txtNombreReferido;
    private javax.swing.JTextField txtNombreResponsable;
    private javax.swing.JTextArea txtNota;
    private javax.swing.JTextArea txtNota1;
    private javax.swing.JTextField txtOcupacion;
    private javax.swing.JTextField txtPlaca;
    private javax.swing.JTextField txtPlazo;
    private javax.swing.JTextField txtReligion;
    private javax.swing.JTextField txtSangre;
    private javax.swing.JTextField txtTarjeta;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtTelefonoResponsable;
    private javax.swing.JTextField txtTipoCliente;
    private javax.swing.JTextField txtTipoVehiculo;
    private javax.swing.JTextField txtpApellido;
    private javax.swing.JTextField txtpNombre;
    private javax.swing.JTextField txtsApellido;
    private javax.swing.JTextField txtsNombre;
    // End of variables declaration//GEN-END:variables
}
