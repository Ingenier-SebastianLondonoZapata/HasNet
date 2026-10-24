package Vista.Contactos;

import Consumidor.Contactos.ConsumidorContactos;
import Controlador.Alertas.ControladorAlertas;

import Dao.Terceros.DaoContactos;
import Enums.TipoContacto;
import Enums.enumTipoIdentificacion;
import Impresiones.ImpresionesMedico.GeneradorReporteMedico;
import Modelo.Terceros.ContactoApiDTO;
import Modelo.Terceros.ModeloConsultaContacto;
import Modelo.Terceros.ModeloDatosVehiculo;
import Utilidades.Limpiadores;
import clases.ImagePreviewPanel;
import clases.Instancias;
import clases.Medico.ndConvenio;
import clases.Medico.ndEpsPrecargados;
import Modelo.Terceros.ModeloContacto;
import Validaciones.Contactos.SquemaDatosContacto;
import clases.big;
import clases.metodosGenerales;
import formularios.Medico.buscConvenio;
import formularios.Medico.buscEpsPrecargadas;
import formularios.terceros.buscCargos;
import formularios.terceros.buscClientes;
import formularios.terceros.buscOcupaciones;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import com.toedter.calendar.JDateChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class VistaContactos extends javax.swing.JInternalFrame {

    private static final String TIPO_PACIENTE = "paciente";
    private static final String DEPARTAMENTO_PREDETERMINADO = "ANTIOQUIA";
    private static final String TEXTO_ACTIVAR = "ACTIVAR";
    private static final String TEXTO_INACTIVAR = "INACTIVAR";

    private SquemaDatosContacto squemaDatosContacto = new SquemaDatosContacto();
    private final DaoContactos daoTerceros = new DaoContactos();
    private final ConsumidorContactos consumidorContactos = new ConsumidorContactos();

    metodosGenerales metodos = new metodosGenerales();
    private Instancias instancias;

    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    String tipo = "", id = "", simbolo = "";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public VistaContactos(String tipo) {

        initComponents();
        instancias = Instancias.getInstancias();

        simbolo = instancias.getSimbolo();
        this.tipo = tipo;

        txtIdSistema.setText(daoTerceros.obtenerSiguienteIdSistema());

        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        setBorder(null);

        btnConsultarInformacionDesdeApi.setVisible(false);
        pnlInvisible.setVisible(false);

        configurarSegunModo();
        cargarDepartamentos();
        cargarUsuarios();

        cmbTipoContacto.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() != ItemEvent.SELECTED) {
                    return;
                }

                if (tipoSeleccionado() == TipoContacto.EMPLEADO && !puedeGestionarEmpleados()) {
                    ControladorAlertas.alertFail("No tiene permisos para gestionar empleados");
                    cmbTipoContacto.setSelectedItem(TipoContacto.TERCERO.getValor());
                    return;
                }

                actualizarPestanaEmpleado();
            }
        });
        actualizarPestanaEmpleado();

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

    private boolean esPaciente() {
        return TIPO_PACIENTE.equals(tipo);
    }

    private void configurarSegunModo() {
        if (esPaciente()) {
            btnImprimir.setVisible(true);
            cmbTipoIdentificacion.removeItem("Nit");
            cmbTipoContacto.removeItem(TipoContacto.EMPLEADO.getValor());
            lbNota.setVisible(false);
            jScrollPane1.setVisible(false);
            txtNota.setVisible(false);
            tabDatosTerceros.remove(pnlDatosVentasYOtros);
        } else {
            btnImprimir.setVisible(false);
            tabDatosTerceros.remove(pnlDatosMedicos);
        }

        if (!(instancias.getConfiguraciones().isOrdenServicio() || instancias.getConfiguraciones().isParqueadero())) {
            pnlDatosVehiculo.setVisible(false);
        }
    }

    private void cargarDepartamentos() {
        Object[][] departamentos = instancias.getSql().getDepartamentos();
        cmbDepartamento.removeAllItems();

        if (departamentos != null) {
            for (Object[] departamento : departamentos) {
                if (departamento[0] != null) {
                    cmbDepartamento.addItem(departamento[0].toString().toUpperCase());
                }
            }
        }

        cmbDepartamento.setSelectedItem(DEPARTAMENTO_PREDETERMINADO);
        consultarMunicipios(DEPARTAMENTO_PREDETERMINADO);
    }

    private void cargarUsuarios() {
        cmbUsuarios.addItem("");
        Object[][] usuarios = instancias.getSql().getUsuarios();
        if (usuarios != null) {
            for (Object[] usuario : usuarios) {
                cmbUsuarios.addItem(usuario[0]);
            }
        }
    }

    private void actualizarPestanaEmpleado() {
        boolean esEmpleado = tipoSeleccionado() == TipoContacto.EMPLEADO;
        pnlDatosEmpleado.setVisible(esEmpleado);
    }

    private boolean puedeGestionarEmpleados() {
        return instancias.getUsuarioLog().isCrearEmpleado();
    }

    private TipoContacto tipoSeleccionado() {
        return TipoContacto.desdeValor(String.valueOf(cmbTipoContacto.getSelectedItem()));
    }

    public void consultarMunicipios(String departamento) {
        Object[][] municipios = instancias.getSql().getMunicipios(departamento);
        cmbCiudad.removeAllItems();
        cmbCiudad.addItem(" ");
        for (Object[] municipio : municipios) {
            if (null != municipio[0]) {
                cmbCiudad.addItem(municipio[0]);
            }
        }
    }

    public void nuevoCliente(String idSistema) {
        txtIdentificacion.setText(idSistema);
        cargarTercero(idSistema);
    }

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
        grupoAgendar = new javax.swing.ButtonGroup();
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
        pnlDatosContacto = new javax.swing.JPanel();
        pnlDatosObligatorios = new javax.swing.JPanel();
        lbTipo = new javax.swing.JLabel();
        cmbTipoIdentificacion = new javax.swing.JComboBox();
        lbNit = new javax.swing.JLabel();
        txtIdentificacion = new javax.swing.JTextField();
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
        lbCiudad4 = new javax.swing.JLabel();
        cmbTipoContacto = new javax.swing.JComboBox();
        pnlDatosEmpleado = new javax.swing.JPanel();
        lbPlazo6 = new javax.swing.JLabel();
        txtCargo = new javax.swing.JTextField();
        lbCupo2 = new javax.swing.JLabel();
        txtSalario = new javax.swing.JTextField();
        lbFecha3 = new javax.swing.JLabel();
        dtFechaIngreso = new com.toedter.calendar.JDateChooser();
        lbFecha4 = new javax.swing.JLabel();
        dtFechaRetiro = new com.toedter.calendar.JDateChooser();
        lbPlazo7 = new javax.swing.JLabel();
        cmbUsuarios = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        rdSiAgendar = new javax.swing.JRadioButton();
        rdNoAgendar = new javax.swing.JRadioButton();
        lbCiudad2 = new javax.swing.JLabel();
        txtEps = new javax.swing.JTextField();
        lbCiudad5 = new javax.swing.JLabel();
        txtArl = new javax.swing.JTextField();
        txtccf = new javax.swing.JTextField();
        lbCiudad8 = new javax.swing.JLabel();
        pnlDatosVehiculo = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblAutos = new javax.swing.JTable();
        btnAgregarVehiculo = new javax.swing.JButton();
        btnModificarVehiculo = new javax.swing.JButton();
        btnEliminarVehiculo = new javax.swing.JButton();
        btnConsultarInformacionDesdeApi = new javax.swing.JButton();
        pnlDatosVentasYOtros = new javax.swing.JPanel();
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
        jPanel1 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblBodegasContactos = new javax.swing.JTable();
        btnAgregarBodega = new javax.swing.JButton();
        btnModificarBodega = new javax.swing.JButton();
        btnCambiarEstadoBodega = new javax.swing.JButton();
        pnlDatosMedicos = new javax.swing.JPanel();
        pnlDatos = new javax.swing.JPanel();
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

        pnlDatosContacto.setBackground(new java.awt.Color(255, 255, 255));
        pnlDatosContacto.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Adobe Arabic", 1, 12))); // NOI18N

        pnlDatosObligatorios.setBackground(new java.awt.Color(255, 255, 255));
        pnlDatosObligatorios.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos obligatorios", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 13))); // NOI18N

        lbTipo.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
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

        lbNit.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lbNit.setText("Identificación:");
        lbNit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lbNitKeyReleased(evt);
            }
        });

        txtIdentificacion.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtIdentificacion.setToolTipText("Digite la identificación con el dígito de verificación cuando aplique, por ejemplo 900123456-7");
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
                .addGap(5, 5, 5)
                .addGroup(pnlDatosObligatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lbEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbDepartamento5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbDepartamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbApellido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbPNombre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbRazon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTipo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDatosObligatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlDatosObligatoriosLayout.createSequentialGroup()
                        .addComponent(cmbTipoIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbNit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(5, 5, 5)
                        .addComponent(txtIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlDatosObligatoriosLayout.createSequentialGroup()
                        .addGroup(pnlDatosObligatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbCodigoPostal, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtpApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbDepartamento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtpNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                        .addComponent(cmbCiudad, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtsApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pnlDatosObligatoriosLayout.createSequentialGroup()
                                .addComponent(lbSNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtsNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtNombreCompleto))
                .addGap(10, 10, 10))
        );
        pnlDatosObligatoriosLayout.setVerticalGroup(
            pnlDatosObligatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosObligatoriosLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pnlDatosObligatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtIdentificacion, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlDatosObligatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(cmbTipoIdentificacion, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                        .addComponent(lbTipo, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)))
                .addGap(3, 3, 3)
                .addGroup(pnlDatosObligatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNombreCompleto, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(lbRazon, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(pnlDatosObligatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDatosObligatoriosLayout.createSequentialGroup()
                        .addGroup(pnlDatosObligatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbPNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtpNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtsNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(pnlDatosObligatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbApellido, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(txtpApellido, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbSapellido, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtsApellido)))
                    .addComponent(lbSNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(pnlDatosObligatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDatosObligatoriosLayout.createSequentialGroup()
                        .addGroup(pnlDatosObligatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbDepartamento, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                            .addComponent(cmbDepartamento, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(lbCiudad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbCiudad))
                .addGap(3, 3, 3)
                .addGroup(pnlDatosObligatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDatosObligatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lbDepartamento5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbCodigoPostal, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(txtTelefono))
                .addGap(3, 3, 3)
                .addGroup(pnlDatosObligatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(lbEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );

        lbCiudad4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lbCiudad4.setText("Tipo de contacto:");

        cmbTipoContacto.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cmbTipoContacto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TERCERO", "PROVEEDOR", "EMPLEADO" }));

        pnlDatosEmpleado.setBackground(new java.awt.Color(255, 255, 255));
        pnlDatosEmpleado.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos del empleado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 13))); // NOI18N

        lbPlazo6.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbPlazo6.setText("Cargo del empleado: *");

        txtCargo.setBackground(new java.awt.Color(255, 204, 204));
        txtCargo.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCargo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCargo.setName("Cargo"); // NOI18N
        txtCargo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCargoKeyReleased(evt);
            }
        });

        lbCupo2.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbCupo2.setText("Salario:");

        txtSalario.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtSalario.setText("$ 0");
        txtSalario.setName("Salario"); // NOI18N
        txtSalario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSalarioKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSalarioKeyTyped(evt);
            }
        });

        lbFecha3.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbFecha3.setText("Fecha de ingreso:");

        dtFechaIngreso.setDateFormatString("dd/MM/yyyy");
        dtFechaIngreso.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        lbFecha4.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbFecha4.setText("Fecha de retiro: ");

        dtFechaRetiro.setDateFormatString("dd/MM/yyyy");
        dtFechaRetiro.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        lbPlazo7.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbPlazo7.setText("Usuario asignado:");

        cmbUsuarios.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cmbUsuariosFocusGained(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel1.setText("Permitir agendar:");

        rdSiAgendar.setBackground(new java.awt.Color(255, 255, 255));
        grupoAgendar.add(rdSiAgendar);
        rdSiAgendar.setText("SI");

        rdNoAgendar.setBackground(new java.awt.Color(255, 255, 255));
        grupoAgendar.add(rdNoAgendar);
        rdNoAgendar.setSelected(true);
        rdNoAgendar.setText("NO");

        lbCiudad2.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbCiudad2.setText("EPS:");

        txtEps.setBackground(new java.awt.Color(255, 204, 204));
        txtEps.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtEps.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEps.setName("Ciudad"); // NOI18N
        txtEps.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEpsKeyReleased(evt);
            }
        });

        lbCiudad5.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbCiudad5.setText("ARL:");

        txtArl.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtArl.setName("Ciudad"); // NOI18N
        txtArl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtArlKeyReleased(evt);
            }
        });

        txtccf.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtccf.setName("Ciudad"); // NOI18N

        lbCiudad8.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbCiudad8.setText("CCF:");

        javax.swing.GroupLayout pnlDatosEmpleadoLayout = new javax.swing.GroupLayout(pnlDatosEmpleado);
        pnlDatosEmpleado.setLayout(pnlDatosEmpleadoLayout);
        pnlDatosEmpleadoLayout.setHorizontalGroup(
            pnlDatosEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosEmpleadoLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pnlDatosEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbFecha3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbCiudad8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbCiudad2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbPlazo6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDatosEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDatosEmpleadoLayout.createSequentialGroup()
                        .addComponent(rdSiAgendar)
                        .addGap(10, 10, 10)
                        .addComponent(rdNoAgendar)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlDatosEmpleadoLayout.createSequentialGroup()
                        .addGroup(pnlDatosEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtEps)
                            .addComponent(txtccf)
                            .addComponent(dtFechaIngreso, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                            .addComponent(txtCargo, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(pnlDatosEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbCupo2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbCiudad5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbFecha4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                            .addComponent(lbPlazo7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(5, 5, 5)
                        .addGroup(pnlDatosEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSalario)
                            .addComponent(dtFechaRetiro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtArl)
                            .addComponent(cmbUsuarios, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        pnlDatosEmpleadoLayout.setVerticalGroup(
            pnlDatosEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosEmpleadoLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pnlDatosEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbPlazo6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlDatosEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbPlazo7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(3, 3, 3)
                .addGroup(pnlDatosEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbCiudad2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEps, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbCiudad5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtArl, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(pnlDatosEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDatosEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lbCiudad8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtccf, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                    .addComponent(lbCupo2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSalario, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(pnlDatosEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbFecha3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dtFechaIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbFecha4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dtFechaRetiro, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(pnlDatosEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(rdSiAgendar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rdNoAgendar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );

        pnlDatosVehiculo.setBackground(new java.awt.Color(255, 255, 255));
        pnlDatosVehiculo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos de vehiculos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 13))); // NOI18N

        tblAutos.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        tblAutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Placa", "Tipo", "Modelo", "Marca", "Color", "Chasis", "Motor", "F.Compra"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblAutos.setComponentPopupMenu(jPopupMenu1);
        tblAutos.setRowHeight(20);
        tblAutos.getTableHeader().setReorderingAllowed(false);
        tblAutos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAutosMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblAutos);
        if (tblAutos.getColumnModel().getColumnCount() > 0) {
            tblAutos.getColumnModel().getColumn(0).setMinWidth(55);
            tblAutos.getColumnModel().getColumn(0).setPreferredWidth(55);
            tblAutos.getColumnModel().getColumn(0).setMaxWidth(55);
            tblAutos.getColumnModel().getColumn(2).setMinWidth(55);
            tblAutos.getColumnModel().getColumn(2).setPreferredWidth(55);
            tblAutos.getColumnModel().getColumn(2).setMaxWidth(55);
        }

        btnAgregarVehiculo.setBackground(new java.awt.Color(46, 204, 113));
        btnAgregarVehiculo.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        btnAgregarVehiculo.setText("Agregar vehiculo");
        btnAgregarVehiculo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregarVehiculo.setEnabled(false);
        btnAgregarVehiculo.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnAgregarVehiculo.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnAgregarVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarVehiculoActionPerformed(evt);
            }
        });

        btnModificarVehiculo.setBackground(new java.awt.Color(93, 173, 226));
        btnModificarVehiculo.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        btnModificarVehiculo.setText("Modificar vehiculo");
        btnModificarVehiculo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnModificarVehiculo.setEnabled(false);
        btnModificarVehiculo.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnModificarVehiculo.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnModificarVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarVehiculoActionPerformed(evt);
            }
        });

        btnEliminarVehiculo.setBackground(new java.awt.Color(241, 148, 138));
        btnEliminarVehiculo.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        btnEliminarVehiculo.setText("Eliminar vehiculo");
        btnEliminarVehiculo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminarVehiculo.setEnabled(false);
        btnEliminarVehiculo.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnEliminarVehiculo.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnEliminarVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarVehiculoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDatosVehiculoLayout = new javax.swing.GroupLayout(pnlDatosVehiculo);
        pnlDatosVehiculo.setLayout(pnlDatosVehiculoLayout);
        pnlDatosVehiculoLayout.setHorizontalGroup(
            pnlDatosVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosVehiculoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDatosVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDatosVehiculoLayout.createSequentialGroup()
                        .addComponent(btnAgregarVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModificarVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminarVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane3))
                .addContainerGap())
        );
        pnlDatosVehiculoLayout.setVerticalGroup(
            pnlDatosVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosVehiculoLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pnlDatosVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregarVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModificarVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnConsultarInformacionDesdeApi.setText("Consultar información");
        btnConsultarInformacionDesdeApi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarInformacionDesdeApiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDatosContactoLayout = new javax.swing.GroupLayout(pnlDatosContacto);
        pnlDatosContacto.setLayout(pnlDatosContactoLayout);
        pnlDatosContactoLayout.setHorizontalGroup(
            pnlDatosContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDatosContactoLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlDatosContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlDatosEmpleado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlDatosObligatorios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlDatosContactoLayout.createSequentialGroup()
                        .addComponent(lbCiudad4, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbTipoContacto, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnConsultarInformacionDesdeApi, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlDatosVehiculo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );
        pnlDatosContactoLayout.setVerticalGroup(
            pnlDatosContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosContactoLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnlDatosContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDatosContactoLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(btnConsultarInformacionDesdeApi, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlDatosContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(cmbTipoContacto, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lbCiudad4, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDatosObligatorios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDatosEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDatosVehiculo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabDatosTerceros.addTab("Datos contacto", pnlDatosContacto);

        pnlDatosVentasYOtros.setBackground(new java.awt.Color(255, 255, 255));
        pnlDatosVentasYOtros.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 14))); // NOI18N

        pnlDatosVentas.setBackground(new java.awt.Color(255, 255, 255));
        pnlDatosVentas.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos para ventas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 13))); // NOI18N

        lbEmail8.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbEmail8.setText("Lista de precio predeterminada:");

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
        lbVendedor.setText("Vendedor por defecto :");

        lbPlazo.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbPlazo.setText("Días de plazo automático:");

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
        txtCupo.setText("$ 0");
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
        lbCupo.setText("Cupo para créditos:");

        lbNota.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbNota.setText("Notas:");

        txtNota.setColumns(20);
        txtNota.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtNota.setLineWrap(true);
        txtNota.setRows(3);
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
                .addGroup(pnlDatosVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lbVendedor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbEmail8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                    .addComponent(lbPlazo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbCupo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNota, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDatosVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDatosVentasLayout.createSequentialGroup()
                        .addGroup(pnlDatosVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cmbLista, javax.swing.GroupLayout.Alignment.LEADING, 0, 126, Short.MAX_VALUE)
                            .addComponent(txtPlazo, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCupo, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(0, 270, Short.MAX_VALUE))
                    .addComponent(cmbVendedor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        pnlDatosVentasLayout.setVerticalGroup(
            pnlDatosVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDatosVentasLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pnlDatosVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCupo, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(lbCupo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(pnlDatosVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbPlazo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtPlazo, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(pnlDatosVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbEmail8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbLista, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(pnlDatosVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(pnlDatosVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNota, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        pnlOtrosDatos.setBackground(new java.awt.Color(255, 255, 255));
        pnlOtrosDatos.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Otros datos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 13))); // NOI18N

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
                                .addComponent(cmbNaturaleza, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                                .addComponent(lbCiudad3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbRut, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                                .addGroup(pnlOtrosDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cmbSexo, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(dtFechaNacimiento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                                    .addComponent(txtTipoCliente, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtBarrio))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                                .addGroup(pnlOtrosDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlOtrosDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lbFecha1, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                                        .addComponent(lbCiudad7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbNit2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(lbCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlOtrosDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtTarjeta)
                                    .addComponent(txtLugarNacimiento)
                                    .addComponent(cmbEstadoCivil, 0, 167, Short.MAX_VALUE)
                                    .addComponent(txtCelular))
                                .addGap(1, 1, 1))
                            .addComponent(txtDireccion))))
                .addContainerGap())
        );
        pnlOtrosDatosLayout.setVerticalGroup(
            pnlOtrosDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOtrosDatosLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pnlOtrosDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbRut, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbCiudad3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbNaturaleza, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbDepartamento3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlOtrosDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(chkSiResponsableIVA, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbBarrio1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(chkNoResponsableIVA, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(3, 3, 3)
                .addGroup(pnlOtrosDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(dtFechaNacimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtLugarNacimiento, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(lbFecha1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(pnlOtrosDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTarjeta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlOtrosDatosLayout.createSequentialGroup()
                        .addGroup(pnlOtrosDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbCiudad7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbEstadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbCiudad6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(pnlOtrosDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbNit1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTipoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbNit2, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))))
                .addGap(3, 3, 3)
                .addGroup(pnlOtrosDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlOtrosDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbBarrio, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtBarrio, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlOtrosDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lbCelular, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtCelular, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)))
                .addGap(3, 3, 3)
                .addGroup(pnlOtrosDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbDireccion1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(pnlOtrosDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtOcupacion, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbEmail2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscTerceros1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bodegas relacionadas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 13))); // NOI18N

        tblBodegasContactos.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        tblBodegasContactos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Nombre bodega"
            }
        ));
        jScrollPane4.setViewportView(tblBodegasContactos);

        btnAgregarBodega.setBackground(new java.awt.Color(46, 204, 113));
        btnAgregarBodega.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        btnAgregarBodega.setText("Agregar bodega");
        btnAgregarBodega.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregarBodega.setEnabled(false);
        btnAgregarBodega.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnAgregarBodega.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnAgregarBodega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarBodegaActionPerformed(evt);
            }
        });

        btnModificarBodega.setBackground(new java.awt.Color(93, 173, 226));
        btnModificarBodega.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        btnModificarBodega.setText("Modificar bodega");
        btnModificarBodega.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnModificarBodega.setEnabled(false);
        btnModificarBodega.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnModificarBodega.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnModificarBodega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarBodegaActionPerformed(evt);
            }
        });

        btnCambiarEstadoBodega.setBackground(new java.awt.Color(241, 148, 138));
        btnCambiarEstadoBodega.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        btnCambiarEstadoBodega.setText("Inactivar bodega");
        btnCambiarEstadoBodega.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCambiarEstadoBodega.setEnabled(false);
        btnCambiarEstadoBodega.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnCambiarEstadoBodega.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnCambiarEstadoBodega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarEstadoBodegaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAgregarBodega, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModificarBodega, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCambiarEstadoBodega, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregarBodega, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModificarBodega, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCambiarEstadoBodega, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlDatosVentasYOtrosLayout = new javax.swing.GroupLayout(pnlDatosVentasYOtros);
        pnlDatosVentasYOtros.setLayout(pnlDatosVentasYOtrosLayout);
        pnlDatosVentasYOtrosLayout.setHorizontalGroup(
            pnlDatosVentasYOtrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosVentasYOtrosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDatosVentasYOtrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlDatosVentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlOtrosDatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlDatosVentasYOtrosLayout.setVerticalGroup(
            pnlDatosVentasYOtrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosVentasYOtrosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlDatosVentas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlOtrosDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabDatosTerceros.addTab("Datos ventas y otros", pnlDatosVentasYOtros);

        pnlDatosMedicos.setBackground(new java.awt.Color(255, 255, 255));

        pnlDatos.setBackground(new java.awt.Color(255, 255, 255));

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
                                            .addComponent(cmbAfiliado, 0, 208, Short.MAX_VALUE)
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

        javax.swing.GroupLayout pnlDatosLayout = new javax.swing.GroupLayout(pnlDatos);
        pnlDatos.setLayout(pnlDatosLayout);
        pnlDatosLayout.setHorizontalGroup(
            pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlDatosMedicosImportantes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlDatosLayout.createSequentialGroup()
                        .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbCiudad24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbCiudad23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombreMadre)
                            .addComponent(txtNombrePadre)))
                    .addComponent(lbCiudad19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlDatosLayout.createSequentialGroup()
                        .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbCiudad21, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbCiudad20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombreResponsable)
                            .addGroup(pnlDatosLayout.createSequentialGroup()
                                .addComponent(cmbParentesco, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(lbCiudad22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTelefonoResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        pnlDatosLayout.setVerticalGroup(
            pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNombreMadre)
                    .addComponent(lbCiudad23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbCiudad24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNombrePadre))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbCiudad19)
                .addGap(3, 3, 3)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbCiudad20, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombreResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbCiudad22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTelefonoResponsable)
                    .addComponent(cmbParentesco)
                    .addComponent(lbCiudad21, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(pnlDatosMedicosImportantes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlDatosMedicosLayout = new javax.swing.GroupLayout(pnlDatosMedicos);
        pnlDatosMedicos.setLayout(pnlDatosMedicosLayout);
        pnlDatosMedicosLayout.setHorizontalGroup(
            pnlDatosMedicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosMedicosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlDatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );
        pnlDatosMedicosLayout.setVerticalGroup(
            pnlDatosMedicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosMedicosLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(pnlDatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabDatosTerceros.addTab("Datos Medicos", pnlDatosMedicos);

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tabDatosTerceros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(pnlFormularioLayout.createSequentialGroup()
                            .addGap(36, 36, 36)
                            .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lbFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(pnlInvisible, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                        .addComponent(tabDatosTerceros, javax.swing.GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE)))
                .addGap(10, 10, 10))
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
                .addGap(4, 4, 4))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPlazoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlazoKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtPlazoKeyTyped

    private void txtIdentificacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdentificacionKeyTyped

    }//GEN-LAST:event_txtIdentificacionKeyTyped

    private void txtIdentificacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdentificacionKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (daoTerceros.consultarPorIdentificacion(txtIdentificacion.getText()) == null) {
                enfocarNombre();
            } else {
                cargarTercero(txtIdentificacion.getText());
            }
        }
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            enfocarNombre();
        }

        btnImagen.setEnabled(!txtIdentificacion.getText().isEmpty());
        activarBotonConsultarInformacionApi();
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
        limpiarFormulario();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        ModeloContacto contacto = leerFormulario(crearContactoNuevo());

        if (!validarContacto(contacto, true)) {
            return;
        }

        if (!ControladorAlertas.option("¿Desea continuar?")) {
            return;
        }

        if (!daoTerceros.guardar(contacto, leerVehiculos())) {
            ControladorAlertas.alertFail("Hubo un error al guardar");
            return;
        }

        if (esPaciente()) {
            if (!crearHistoriaClinica(contacto)) {
                return;
            }
        } else {
            ControladorAlertas.alertSuccess(etiquetaContacto() + " creado con éxito");
        }

        String cliente = identificacionSinDigito();
        boolean esEmpleado = tipoSeleccionado() == TipoContacto.EMPLEADO;
        refrescarModulosRelacionados(contacto);
        limpiarFormulario();

        if (!esEmpleado) {
            procesarCitaPendiente(cliente);
        }

        lbNit.requestFocus();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        ModeloContacto guardado = daoTerceros.consultarPorIdentificacion(txtIdSistema.getText());
        if (guardado == null) {
            ControladorAlertas.alertFail("No se encontró el contacto a modificar");
            return;
        }

        ModeloContacto contacto = leerFormulario(guardado);

        if (!validarContacto(contacto, false)) {
            return;
        }

        if (!ControladorAlertas.option("¿Desea continuar?")) {
            return;
        }

        if (!daoTerceros.modificar(contacto, leerVehiculos())) {
            ControladorAlertas.alertFail("Hubo un problema al modificar el " + etiquetaContacto().toLowerCase());
            return;
        }

        refrescarModulosRelacionados(contacto);
        ControladorAlertas.alertSuccess(etiquetaContacto() + " modificado con éxito");
        limpiarFormulario();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        boolean activar = TEXTO_ACTIVAR.equalsIgnoreCase(btnEliminar.getText().trim());

        if (!ControladorAlertas.option(activar ? "¿Activar este registro?" : "¿Inactivar este registro?")) {
            return;
        }

        if (!daoTerceros.cambiarEstado(txtIdSistema.getText(), !activar)) {
            ControladorAlertas.alertFail("No fue posible cambiar el estado del registro");
            return;
        }

        if (activar) {
            ControladorAlertas.alertSuccess(etiquetaContacto() + " activado con éxito");
        } else {
            ControladorAlertas.alert(etiquetaContacto() + " inactivado, este registro podrá ser activado posteriormente");
        }
        limpiarFormulario();
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

        activarBotonConsultarInformacionApi();
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
        chooser.setFileFilter(new FileNameExtensionFilter("JPG, PNG & GIF", "jpg", "png", "gif"));

        if (chooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) {
            return;
        }

        String origen = chooser.getSelectedFile().getAbsolutePath();

        try {
            metodos.montarImagenTerceros(origen, rutaFoto(txtIdSistema.getText(), tipoSeleccionado()));

            ImageIcon fot = new ImageIcon(origen);
            Icon icono = new ImageIcon(fot.getImage().getScaledInstance(lbFoto.getWidth(), lbFoto.getHeight(), Image.SCALE_DEFAULT));
            lbFoto.setIcon(icono);
            this.repaint();
        } catch (Exception ex) {
            ControladorAlertas.alertFail("Hubo un error al cargar el archivo");
        }
    }//GEN-LAST:event_btnImagenActionPerformed

    private void cmbAfiliadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAfiliadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbAfiliadoActionPerformed

    private void txtNota1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNota1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNota1KeyReleased

    private void btnAgregarVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarVehiculoActionPerformed
        capturarVehiculo(-1);
    }//GEN-LAST:event_btnAgregarVehiculoActionPerformed

    private void btnModificarVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarVehiculoActionPerformed
        if (tblAutos.getSelectedRow() < 0) {
            ControladorAlertas.alert("Seleccione una placa");
            return;
        }
        capturarVehiculo(tblAutos.getSelectedRow());
    }//GEN-LAST:event_btnModificarVehiculoActionPerformed

    private void btnEliminarVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarVehiculoActionPerformed
        eliminarVehiculoSeleccionado();
    }//GEN-LAST:event_btnEliminarVehiculoActionPerformed

    private void tblAutosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAutosMouseClicked
        if (evt.getClickCount() == 2 && btnModificarVehiculo.isEnabled() && tblAutos.getSelectedRow() > -1) {
            capturarVehiculo(tblAutos.getSelectedRow());
        }
    }//GEN-LAST:event_tblAutosMouseClicked

    private void popBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popBorrarActionPerformed
        eliminarVehiculoSeleccionado();
    }//GEN-LAST:event_popBorrarActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        instancias.getReporte().setImagenInforme1(txtIdSistema.getText());
        instancias.getReporte().setImagenInforme();

        try {
            new GeneradorReporteMedico(instancias).ver_informePaciente(txtIdSistema.getText());
        } catch (Exception e) {
            new GeneradorReporteMedico(instancias).ver_informePaciente1(txtIdSistema.getText());
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

    private void lbNitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbNitKeyReleased
        txtIdentificacion.requestFocus();
    }//GEN-LAST:event_lbNitKeyReleased

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

        for (Object[] codigosPostale : codigosPostales) {
            if (null != codigosPostale[0]) {
                cmbCodigoPostal.addItem(codigosPostale[0]);
            }
        }
    }//GEN-LAST:event_cmbCiudadItemStateChanged

    private void cmbCodigoPostalItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbCodigoPostalItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbCodigoPostalItemStateChanged

    private void txtDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionActionPerformed

    private void txtEpsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEpsKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            cargarEpsEmpleado(txtEps.getText());
        } else {
            txtEps.setText("");
        }
    }//GEN-LAST:event_txtEpsKeyReleased

    private void txtArlKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtArlKeyReleased
        avanzarConEnter(evt, txtccf);
    }//GEN-LAST:event_txtArlKeyReleased

    private void txtCargoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCargoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txtCargo.getText().trim().isEmpty()) {
                ventanaCargos("");
            } else {
                dtFechaIngreso.requestFocus();
            }
        }
    }//GEN-LAST:event_txtCargoKeyReleased

    private void txtSalarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSalarioKeyReleased
        if (!txtSalario.getText().trim().isEmpty()) {
            txtSalario.setText(big.setMoneda(big.getMoneda(txtSalario.getText())));
        }
    }//GEN-LAST:event_txtSalarioKeyReleased

    private void txtSalarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSalarioKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtSalarioKeyTyped

    private void cmbUsuariosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbUsuariosFocusGained
        if (!instancias.getUsuario().equals("ADMIN")) {
            dtFechaIngreso.requestFocus();
            ControladorAlertas.alertFail("No tiene permisos para abrir esta lista");
        }
    }//GEN-LAST:event_cmbUsuariosFocusGained

    private void btnConsultarInformacionDesdeApiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarInformacionDesdeApiActionPerformed
        consultarContactoEnLaApi();
    }//GEN-LAST:event_btnConsultarInformacionDesdeApiActionPerformed

    private void btnAgregarBodegaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarBodegaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregarBodegaActionPerformed

    private void btnModificarBodegaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarBodegaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnModificarBodegaActionPerformed

    private void btnCambiarEstadoBodegaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarEstadoBodegaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCambiarEstadoBodegaActionPerformed

    private void capturarVehiculo(int fila) {
        VistaDatosVehiculo modal = new VistaDatosVehiculo(instancias.getMenu(), true);
        modal.preparar(fila < 0 ? null : vehiculoDeLaFila(fila), placasRegistradas(fila), txtIdSistema.getText());
        modal.setVisible(true);

        if (!modal.isAceptado()) {
            return;
        }

        ModeloDatosVehiculo vehiculo = modal.getVehiculo();
        DefaultTableModel modelo = (DefaultTableModel) tblAutos.getModel();
        Object[] valores = new Object[]{vehiculo.getPlaca(), vehiculo.getTipoVehiculo(), vehiculo.getModelo(), vehiculo.getMarca(),
            vehiculo.getColor(), vehiculo.getNumeroChasis(), vehiculo.getNumeroMotor(), vehiculo.getFechaCompra()};

        if (fila < 0) {
            modelo.addRow(valores);
            return;
        }

        for (int columna = 0; columna < valores.length; columna++) {
            modelo.setValueAt(valores[columna], fila, columna);
        }
    }

    private ModeloDatosVehiculo vehiculoDeLaFila(int fila) {
        return new ModeloDatosVehiculo(valorCelda(fila, 0), valorCelda(fila, 1), valorCelda(fila, 5), valorCelda(fila, 6),
                valorCelda(fila, 2), valorCelda(fila, 3), valorCelda(fila, 7), valorCelda(fila, 4));
    }

    private List<String> placasRegistradas(int filaOmitida) {
        List<String> placas = new ArrayList<>();

        for (int fila = 0; fila < tblAutos.getRowCount(); fila++) {
            if (fila != filaOmitida) {
                placas.add(valorCelda(fila, 0));
            }
        }
        return placas;
    }

    private void eliminarVehiculoSeleccionado() {
        if (tblAutos.getSelectedRow() < 0) {
            ControladorAlertas.alert("Seleccione una placa");
            return;
        }

        ((DefaultTableModel) tblAutos.getModel()).removeRow(tblAutos.getSelectedRow());
        tblAutos.removeEditor();
    }

    public void cargarReferido() {
        ModeloContacto referido = daoTerceros.consultarPorIdentificacion(txtCodigoReferido.getText());
        if (referido != null) {
            txtNombreReferido.setText(referido.getNombre());
            cmbVendedor.requestFocus();
        } else {
            ventanaTercerosReferidos(txtCodigoReferido.getText());
        }
    }

    public void cargarTercero(String Id) {
        ModeloContacto contacto = daoTerceros.consultarPorIdentificacion(Id);

        if (contacto != null) {
            mostrarContacto(contacto);
            return;
        }
        ventanaTerceros(Id, txtIdentificacion, "");
    }

    private void consultarContactoEnLaApi() {
        String tipoIdentificacion = enumTipoIdentificacion.obtenerTipoIdentificacion(seleccion(cmbTipoIdentificacion));
        String identificacion = identificacionCompleta();

        if (tipoIdentificacion.isEmpty()) {
            ControladorAlertas.alert("Debe seleccionar el tipo de identificación");
            return;
        }

        if (identificacion.isEmpty()) {
            ControladorAlertas.alert("Debe ingresar el número de identificación");
            return;
        }

        if (contactoYaRegistrado()) {
            return;
        }

        // el servicio recibe el número sin el dígito de verificación
        ContactoApiDTO contacto = consumidorContactos.consultarContacto(
                new ModeloConsultaContacto(tipoIdentificacion, identificacionSinDigito()));

        if (contacto == null) {
            return;
        }

        String tipoIdentificacionSeleccionado = seleccion(cmbTipoIdentificacion);
        String tipoContactoSeleccionado = seleccion(cmbTipoContacto);

        limpiarFormulario();
        cmbTipoContacto.setSelectedItem(tipoContactoSeleccionado);
        cmbTipoIdentificacion.setSelectedItem(tipoIdentificacionSeleccionado);
        txtIdentificacion.setText(identificacion);

        mostrarContactoDeLaApi(contacto);
        ControladorAlertas.alertSuccess("Datos cargados, revíselos y guarde el " + etiquetaContacto().toLowerCase());
    }

    private boolean contactoYaRegistrado() {
        ModeloContacto existente = daoTerceros.consultarPorIdentificacion(identificacionCompleta());

        if (existente == null && !identificacionSinDigito().equals(identificacionCompleta())) {
            existente = daoTerceros.consultarPorIdentificacion(identificacionSinDigito());
        }

        if (existente == null) {
            return false;
        }

        ControladorAlertas.alert("El " + etiquetaContacto().toLowerCase() + " ya está registrado, se cargaron sus datos");
        mostrarContacto(existente);
        btnConsultarInformacionDesdeApi.setVisible(false);
        return true;
    }

    private void mostrarContactoDeLaApi(ContactoApiDTO contacto) {
        txtNombreCompleto.setText(contacto.getNombre());
        txtEmail.setText(contacto.getEmail());

        tabDatosTerceros.setSelectedIndex(0);
        btnGuardar.setEnabled(true);
        txtTelefono.requestFocus();
    }

    private void mostrarContacto(ModeloContacto contacto) {
        TipoContacto tipoContacto = TipoContacto.desdeValor(contacto.getTipoTercero());
        boolean esEmpleado = tipoContacto == TipoContacto.EMPLEADO;

        if (esEmpleado && !puedeGestionarEmpleados()) {
            ControladorAlertas.alertFail("No tiene permisos para gestionar empleados");
            return;
        }

        edicionCamposDatosVehiculo(true);
        cmbTipoContacto.setSelectedItem(tipoContacto.getValor());

        txtIdSistema.setText(contacto.getIdSistema());
        txtIdentificacion.setText(texto(contacto.getId()));

        mostrarTipoIdentificacion(contacto.getTipo());

        txtTelefono.setText(texto(contacto.getTelefono()));
        txtCelular.setText(texto(contacto.getCelular()));
        txtDireccion.setText(texto(contacto.getDireccion()));
        txtEmail.setText(texto(contacto.getEmail()));
        seleccionarOAgregar(cmbDepartamento, contacto.getDepartamento());
        seleccionarOAgregar(cmbCiudad, contacto.getCiudad());
        seleccionarOAgregar(cmbCodigoPostal, contacto.getCodigoPostal());
        dtFechaNacimiento.setCalendar(metodos.haciaDate(contacto.getNacimiento()));

        txtpNombre.setText(texto(contacto.getpNombre()));
        txtsNombre.setText(texto(contacto.getsNombre()));
        txtpApellido.setText(texto(contacto.getpApellido()));
        txtsApellido.setText(texto(contacto.getsApellido()));
        txtOcupacion.setText(texto(contacto.getOcupacion()));
        seleccionar(cmbEstadoCivil, contacto.getEstado());
        seleccionar(cmbRegimen, contacto.getRegimen());
        seleccionar(cmbTrabajador, contacto.getTipoTrabajador());
        seleccionar(cmbAfiliado, contacto.getAfiliado());
        seleccionar(cmbNaturaleza, contacto.getNaturaleza());
        seleccionar(cmbRut, contacto.getRut());
        txtPlazo.setText(texto(contacto.getPlazo()));
        mostrarMonto(txtCupo, contacto.getCupo());
        seleccionar(cmbLista, contacto.getLista());
        seleccionar(cmbSexo, contacto.getSexo());

        if (esPaciente()) {
            txtNota1.setText(texto(contacto.getNota()));
        } else {
            txtNota.setText(texto(contacto.getNota()));
        }

        txtReligion.setText(texto(contacto.getReligion()));
        txtNombreMadre.setText(texto(contacto.getNombreMadre()));
        txtNombrePadre.setText(texto(contacto.getNombrePadre()));
        txtNombreResponsable.setText(texto(contacto.getNombreResponsable()));
        seleccionar(cmbParentesco, contacto.getParentescoResponsable());
        txtTelefonoResponsable.setText(texto(contacto.getTelefonoResponsable()));
        txtSangre.setText(texto(contacto.getSangre()));
        txtLugarNacimiento.setText(texto(contacto.getLugarNacimiento()));
        txtBarrio.setText(texto(contacto.getBarrio()));
        txtTipoCliente.setText(texto(contacto.getNombreContacto()));
        seleccionar(cmbZona, contacto.getZona());

        if (contacto.isResponsableIva()) {
            chkSiResponsableIVA.setSelected(true);
        } else {
            chkNoResponsableIVA.setSelected(true);
        }

        if (!texto(contacto.getReferido()).isEmpty()) {
            txtCodigoReferido.setText(contacto.getReferido());
            cargarReferido();
        }

        if (!texto(contacto.getVendedor()).isEmpty()) {
            cmbVendedor.setSelectedItem(daoTerceros.obtenerNombrePorIdSistema(contacto.getVendedor()));
        }

        txtConvenio.setText(texto(contacto.getConvenioActual()));
        if (!txtConvenio.getText().isEmpty()) {
            ModeloContacto convenio = daoTerceros.consultarPorIdentificacion(txtConvenio.getText());
            if (convenio != null) {
                txtNombreConvenio.setText(texto(convenio.getCompleta()));
            }
        }

        if (esEmpleado) {
            mostrarDatosEmpleado(contacto);
        } else {
            txtTarjeta.setText(texto(contacto.getCargo()));
            txtCodigoEps.setText(texto(contacto.getEps()));
            if (!txtCodigoEps.getText().isEmpty()) {
                cargarEps(txtCodigoEps.getText());
            }
        }

        txtNombreCompleto.setText(texto(contacto.getNombre()));

        mostrarFoto(contacto.getIdSistema(), tipoContacto);
        mostrarVehiculos(daoTerceros.consultarVehiculos(contacto.getIdSistema()));

        btnActualizar.setEnabled(true);
        btnEliminar.setEnabled(true);
        btnImprimir.setEnabled(true);
        btnGuardar.setEnabled(false);
        btnImagen.setEnabled(true);
        btnConsultarInformacionDesdeApi.setVisible(false);
        
        if (contacto.isActivo()) {
            btnEliminar.setText(TEXTO_ACTIVAR);
            btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/agregar.png")));
        } else {
            btnEliminar.setText(TEXTO_INACTIVAR);
            btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/borrar2.png")));
        }
    }

    private void mostrarDatosEmpleado(ModeloContacto contacto) {
        txtCargo.setText(texto(contacto.getCargo()));
        txtEps.setText(texto(contacto.getEps()));
        txtArl.setText(texto(contacto.getArl()));
        txtccf.setText(texto(contacto.getCcf()));
        mostrarMonto(txtSalario, contacto.getSalario());
        dtFechaIngreso.setCalendar(metodos.haciaDate(contacto.getIngreso()));
        dtFechaRetiro.setCalendar(metodos.haciaDate(contacto.getRetiro()));
        cmbUsuarios.setSelectedItem(texto(contacto.getUsuarioAsociado()));

        if (contacto.isAgendar()) {
            rdSiAgendar.setSelected(true);
        } else {
            rdNoAgendar.setSelected(true);
        }
    }

    private void mostrarTipoIdentificacion(String tipoIdentificacion) {
        if (tipoIdentificacion == null) {
            return;
        }

        if (tipoIdentificacion.equalsIgnoreCase("CEDULA") || tipoIdentificacion.equalsIgnoreCase("CC")
                || tipoIdentificacion.equalsIgnoreCase("Cédula")) {
            cmbTipoIdentificacion.setSelectedItem("Cédula de ciudadanía");
        } else if (tipoIdentificacion.equalsIgnoreCase("NIT")) {
            cmbTipoIdentificacion.setSelectedItem("Nit");
        } else {
            cmbTipoIdentificacion.setSelectedItem(tipoIdentificacion);
        }
    }

    private void mostrarFoto(String idSistema, TipoContacto tipoContacto) {
        ImageIcon fot = new ImageIcon(rutaFoto(idSistema, tipoContacto));
        Icon icono = new ImageIcon(fot.getImage().getScaledInstance(lbFoto.getWidth(), lbFoto.getHeight(), Image.SCALE_DEFAULT));
        lbFoto.setIcon(icono);
        this.repaint();
    }

    private String rutaFoto(String idSistema, TipoContacto tipoContacto) {
        String carpeta = tipoContacto == TipoContacto.EMPLEADO ? "empleados" : "terceros";
        return System.getProperty("user.dir") + "\\imagenes\\" + carpeta + "\\" + idSistema + ".jpg";
    }

    private void mostrarVehiculos(List<ModeloDatosVehiculo> vehiculos) {
        DefaultTableModel modelo = (DefaultTableModel) tblAutos.getModel();
        modelo.setRowCount(0);

        for (ModeloDatosVehiculo vehiculo : vehiculos) {
            modelo.addRow(new Object[]{vehiculo.getPlaca(), vehiculo.getTipoVehiculo(), vehiculo.getModelo(), vehiculo.getMarca(),
                vehiculo.getColor(), vehiculo.getNumeroChasis(), vehiculo.getNumeroMotor(), vehiculo.getFechaCompra()});
        }
    }

    private void limpiarFormulario() {
        chkNoResponsableIVA.setSelected(true);
        txtNota1.setText("");

        txtIdentificacion.setEditable(true);

        Limpiadores.limpiarPanel(pnlDatosObligatorios);
        Limpiadores.limpiarPanel(pnlDatosVentas);
        Limpiadores.limpiarPanel(pnlOtrosDatos);
        Limpiadores.limpiarPanel(pnlDatosVentasYOtros);

        edicionCamposDatosVehiculo(true);
        ((DefaultTableModel) tblAutos.getModel()).setRowCount(0);

        Limpiadores.limpiarPanel(pnlDatos);
        Limpiadores.limpiarPanel(pnlDatosMedicosImportantes);

        Limpiadores.limpiarPanel(pnlDatosContacto);
        Limpiadores.limpiarPanel(pnlDatosMedicos);

        lbFoto.setIcon(null);
        repaint();

        btnImagen.setEnabled(false);
        btnConsultarInformacionDesdeApi.setVisible(false);

        cmbDepartamento.setSelectedItem(DEPARTAMENTO_PREDETERMINADO);
        dtFechaNacimiento.setCalendar(null);
        dtFechaIngreso.setCalendar(null);
        dtFechaRetiro.setCalendar(null);
        rdNoAgendar.setSelected(true);
        tabDatosTerceros.setSelectedIndex(0);
        txtIdentificacion.requestFocus();
        btnActualizar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnImprimir.setEnabled(false);
        btnGuardar.setEnabled(true);
        btnEliminar.setText(TEXTO_INACTIVAR);
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/borrar2.png")));

        btnAgregarVehiculo.setEnabled(false);
        btnModificarVehiculo.setEnabled(false);
        btnEliminarVehiculo.setEnabled(false);

        txtIdSistema.setText(daoTerceros.obtenerSiguienteIdSistema());
        txtCupo.setText(this.simbolo + " 0");
        actualizarPestanaEmpleado();
    }

    private ModeloContacto crearContactoNuevo() {
        ModeloContacto contacto = new ModeloContacto();
        contacto.setFecha(fechaActual());
        contacto.setUsuario(instancias.getUsuario());
        contacto.setTerminal(instancias.getTerminal());
        contacto.setActivo(false);
        contacto.setPais("Colombia");
        contacto.setPlacas("");
        contacto.setTipo2("");
        contacto.setRango("");
        contacto.setTelefonoContacto("");
        contacto.setWeb("");
        contacto.setRnt("");
        contacto.setTipoProveedor("");
        contacto.setCodigoUsuario("");
        contacto.setSalario("0");
        return contacto;
    }

    private ModeloContacto leerFormulario(ModeloContacto contacto) {
        TipoContacto tipoContacto = tipoSeleccionado();
        boolean esEmpleado = tipoContacto == TipoContacto.EMPLEADO;

        contacto.setIdSistema(txtIdSistema.getText().trim());
        contacto.setId(identificacionCompleta());
        contacto.setTipo(seleccion(cmbTipoIdentificacion));
        contacto.setNombre(txtNombreCompleto.getText().trim());
        contacto.setCompleta(contacto.getNombre());
        contacto.setpNombre(txtpNombre.getText().trim());
        contacto.setsNombre(txtsNombre.getText().trim());
        contacto.setpApellido(txtpApellido.getText().trim());
        contacto.setsApellido(txtsApellido.getText().trim());

        contacto.setTelefono(txtTelefono.getText().trim());
        contacto.setCelular(txtCelular.getText().trim());
        contacto.setDireccion(txtDireccion.getText().trim());
        contacto.setEmail(txtEmail.getText().trim());
        contacto.setDepartamento(seleccion(cmbDepartamento));
        contacto.setCiudad(seleccion(cmbCiudad));
        contacto.setCodigoPostal(seleccion(cmbCodigoPostal));
        contacto.setNacimiento(fechaNacimientoParaGuardar());

        contacto.setOcupacion(txtOcupacion.getText().trim());
        contacto.setActividad(contacto.getOcupacion());
        contacto.setNaturaleza(seleccion(cmbNaturaleza));
        contacto.setRut(seleccion(cmbRut));
        contacto.setSexo(seleccion(cmbSexo));
        contacto.setEstado(seleccion(cmbEstadoCivil));
        contacto.setRegimen(seleccion(cmbRegimen));
        contacto.setAfiliado(seleccion(cmbAfiliado));
        contacto.setTipoTrabajador(seleccion(cmbTrabajador));
        contacto.setZona(seleccion(cmbZona));
        contacto.setSangre(txtSangre.getText().trim());
        contacto.setEps(leerEps(esEmpleado));

        contacto.setPlazo(txtPlazo.getText().trim().isEmpty() ? "0" : txtPlazo.getText().trim());
        contacto.setCupo(montoComoTexto(txtCupo));
        contacto.setReferido(txtCodigoReferido.getText().trim());
        contacto.setVendedor(cmbVendedor.getSelectedIndex() > 0 ? daoTerceros.obtenerIdSistemaPorNombre(seleccion(cmbVendedor)) : "");
        contacto.setLista(seleccion(cmbLista));
        contacto.setNota(txtNota.getText() + txtNota1.getText());
        contacto.setConvenio(jRadioButton1.isSelected() ? "1" : "");
        contacto.setConvenioActual(txtConvenio.getText().trim());

        contacto.setReligion(txtReligion.getText().trim());
        contacto.setNombreMadre(txtNombreMadre.getText().trim());
        contacto.setNombrePadre(txtNombrePadre.getText().trim());
        contacto.setNombreResponsable(txtNombreResponsable.getText().trim());
        contacto.setParentescoResponsable(seleccion(cmbParentesco));
        contacto.setTelefonoResponsable(txtTelefonoResponsable.getText().trim());
        contacto.setLugarNacimiento(txtLugarNacimiento.getText().trim());
        contacto.setBarrio(txtBarrio.getText().trim());
        contacto.setNombreContacto(txtTipoCliente.getText().trim());

        contacto.setTipoTercero(tipoContacto.getValor());
        contacto.setResponsableIva(chkSiResponsableIVA.isSelected());

        if (esEmpleado) {
            leerDatosEmpleado(contacto);
        } else {
            // la columna cargo guarda la tarjeta del cliente o el cargo del empleado
            contacto.setCargo(txtTarjeta.getText().trim());
        }

        return contacto;
    }

    private void leerDatosEmpleado(ModeloContacto contacto) {
        contacto.setCargo(txtCargo.getText().trim());
        contacto.setSalario(montoComoTexto(txtSalario));
        contacto.setIngreso(fechaDe(dtFechaIngreso) != null ? fechaDe(dtFechaIngreso) : fechaActual());
        contacto.setRetiro(fechaDe(dtFechaRetiro));
        contacto.setAgendar(rdSiAgendar.isSelected());
        contacto.setUsuarioAsociado(seleccion(cmbUsuarios));
        contacto.setArl(txtArl.getText().trim());
        contacto.setCcf(txtccf.getText().trim());
    }

    private String leerEps(boolean esEmpleado) {
        String eps;
        if (esEmpleado) {
            eps = txtEps.getText().trim();
        } else {
            eps = txtNombreEps.getText().trim().isEmpty() ? "" : txtCodigoEps.getText().trim();
        }
        return eps.isEmpty() ? null : eps;
    }

    private List<ModeloDatosVehiculo> leerVehiculos() {
        List<ModeloDatosVehiculo> vehiculos = new ArrayList<>();

        for (int fila = 0; fila < tblAutos.getRowCount(); fila++) {
            vehiculos.add(new ModeloDatosVehiculo(valorCelda(fila, 0), valorCelda(fila, 1), valorCelda(fila, 5), valorCelda(fila, 6),
                    valorCelda(fila, 2), valorCelda(fila, 3), valorCelda(fila, 7), valorCelda(fila, 4)));
        }
        return vehiculos;
    }

    private String valorCelda(int fila, int columna) {
        Object valor = tblAutos.getValueAt(fila, columna);
        return valor == null ? "" : valor.toString();
    }

    private boolean validarContacto(ModeloContacto contacto, boolean esNuevo) {
        ModeloContacto existente = contacto.getId().isEmpty() ? null : daoTerceros.consultarPorIdentificacion(contacto.getId());

        if (!squemaDatosContacto.validarContacto(contacto, tipoSeleccionado(), esPaciente(),
                instancias.getConfiguraciones().isFacturaElectronica(), esNuevo, existente)) {
            return false;
        }

        if (requiereFechaNacimiento() && dtFechaNacimiento.getCalendar() == null) {
            ControladorAlertas.alert("Debe ingresar la fecha de nacimiento");
            return false;
        }
        return true;
    }

    private boolean requiereFechaNacimiento() {
        return tipoSeleccionado() != TipoContacto.EMPLEADO
                && (instancias.getConfiguraciones().isMedico() || instancias.getConfiguraciones().isLaboratorio()
                || instancias.getConfiguraciones().isOftalmologia());
    }

    private String fechaNacimientoParaGuardar() {
        String fecha = fechaDe(dtFechaNacimiento);
        return fecha != null ? fecha : fechaActual();
    }

    private String fechaDe(JDateChooser selector) {
        Calendar calendario = selector.getCalendar();
        return calendario == null ? null : metodos.desdeDate(calendario);
    }

    private String fechaActual() {
        return metodos.fechaConsulta(metodosGenerales.fecha());
    }

    private boolean crearHistoriaClinica(ModeloContacto contacto) {
        String historia = "HSTC-" + identificacionSinDigito();
        String fecha = fechaActual();
        Object[] datosHistoria = {historia, contacto.getIdSistema(), fecha, "", fecha, fecha, "",
            "", "", "", "", "0&0&0&0", "", "", ""};

        if (!instancias.getSql().agregarHistClinica(metodos.llenarHistClinica(datosHistoria))) {
            ControladorAlertas.alertFail("Error al guardar historia clinica");
            return false;
        }

        ControladorAlertas.alertSuccess("Historia Clinica No." + historia.replace("HSTC-", ""));
        return true;
    }

    private void refrescarModulosRelacionados(ModeloContacto contacto) {
        if (!TipoContacto.EMPLEADO.getValor().equals(contacto.getTipoTercero())) {
            return;
        }

        if (instancias.getConfiguraciones().isVeterinaria()) {
            instancias.getPeluqueria().titulosTabla();
        }

        if (contacto.isAgendar() && instancias.getConfiguraciones().isAgenda()) {
            instancias.getAgendaConsulta().titulosTabla();
        }

        instancias.getMaestra().llenarVendedores(cmbUsuarios.getSelectedIndex() != 0 ? contacto.getNombre() : "");
    }

    private void procesarCitaPendiente(String cliente) {
        String cita;
        try {
            cita = instancias.getSql().getAgendasDelDia(cliente, fechaActual());
        } catch (Exception e) {
            return;
        }

        if (cita == null || cita.isEmpty()) {
            return;
        }

        String medico = instancias.getSql().medicoDeLaCita(cita);

        try {
            if (esPaciente()) {
                if (instancias.isGeneraOrdenMedica()) {
                    instancias.getOrdenMedica().cargarClienteDesdeAgenda(cliente, medico);
                    instancias.getOrdenMedica().setSelected(true);
                } else {
                    instancias.getHistoriaC().setPaciente(cliente);
                    instancias.getHistoriaC().setSelected(true);
                }
            } else {
                instancias.getFactura().cargarCliente(cliente);
                instancias.getFacturaContenedor().setSelected(true);
            }
        } catch (PropertyVetoException e) {
            // la ventana no acepta el foco; no impide el flujo
        }
    }

    private String etiquetaContacto() {
        if (esPaciente()) {
            return "Paciente";
        }

        switch (tipoSeleccionado()) {
            case EMPLEADO:
                return "Empleado";
            case PROVEEDOR:
                return "Proveedor";
            default:
                return "Cliente";
        }
    }

    private void enfocarNombre() {
        if (enumTipoIdentificacion.TipoIdentificacion.NIT.getValue().equalsIgnoreCase(seleccion(cmbTipoIdentificacion))) {
            txtNombreCompleto.requestFocus();
        } else {
            txtpNombre.requestFocus();
        }
    }

    private String identificacionCompleta() {
        String identificacion = txtIdentificacion.getText().trim().replace(" ", "");

        while (identificacion.endsWith("-")) {
            identificacion = identificacion.substring(0, identificacion.length() - 1);
        }

        return identificacion;
    }

    private String identificacionSinDigito() {
        String identificacion = identificacionCompleta();
        int guion = identificacion.indexOf('-');
        return guion < 0 ? identificacion : identificacion.substring(0, guion);
    }

    private String texto(String valor) {
        return valor == null ? "" : valor;
    }

    private String seleccion(JComboBox combo) {
        Object item = combo.getSelectedItem();
        return item == null ? "" : item.toString().trim();
    }

    private void seleccionar(JComboBox combo, String valor) {
        if (valor != null) {
            combo.setSelectedItem(valor);
        }
    }

    private void seleccionarOAgregar(JComboBox combo, String valor) {
        if (valor == null || valor.trim().isEmpty()) {
            return;
        }

        DefaultComboBoxModel modelo = (DefaultComboBoxModel) combo.getModel();
        if (modelo.getIndexOf(valor) < 0) {
            combo.addItem(valor);
        }
        combo.setSelectedItem(valor);
    }

    private void mostrarMonto(JTextField campo, String valor) {
        try {
            campo.setText(big.setMoneda(big.getBigDecimal(valor)));
        } catch (RuntimeException e) {
            campo.setText(simbolo + " 0");
        }
    }

    private String montoComoTexto(JTextField campo) {
        String valor = campo.getText().trim();
        if (valor.isEmpty() || valor.equals(simbolo)) {
            return "0";
        }
        return big.getMoneda(valor).toPlainString();
    }

    private void avanzarConEnter(java.awt.event.KeyEvent evt, java.awt.Component siguiente) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            siguiente.requestFocus();
        }
    }

    public void cargarEpsEmpleado(String codigo) {
        ndEpsPrecargados eps = instancias.getSql().getDatosEpsPrecargados(codigo);

        if (eps.getId() != null) {
            txtArl.requestFocus();
            return;
        }
        ventanaEps(codigo, txtEps);
    }

    public void ventanaCargos(String nit) {
        buscCargos buscar = new buscCargos(instancias.getMenu(), rootPaneCheckingEnabled);
        buscar.setLocationRelativeTo(null);
        instancias.setCampoActual(txtCargo);
        txtCargo.requestFocus();
        buscar.setInstancia(instancias);
        buscar.noEncontrado(nit);
        buscar.show();
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
        ventanaEps(nit, txtCodigoEps);
    }

    private void ventanaEps(String nit, JTextField campo) {
        buscEpsPrecargadas buscar = new buscEpsPrecargadas(instancias.getMenu(), rootPaneCheckingEnabled);
        buscar.setLocationRelativeTo(null);
        instancias.setBuscEpsPre(buscar);
        instancias.setCampoActual(campo);
        campo.requestFocus();
        buscar.noEncontrado(nit);
        buscar.show();
    }

    private void edicionCamposDatosVehiculo(boolean tipoAccion) {
        btnAgregarVehiculo.setEnabled(tipoAccion);
        btnModificarVehiculo.setEnabled(tipoAccion);
        btnEliminarVehiculo.setEnabled(tipoAccion);
    }

    private void activarBotonConsultarInformacionApi() {
        if (btnGuardar.isEnabled() && cmbTipoIdentificacion.getSelectedIndex() > 0
                && instancias.getConfiguraciones().isFacturaElectronica()) {
            btnConsultarInformacionDesdeApi.setVisible(!txtIdentificacion.getText().isEmpty());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAgregarBodega;
    private javax.swing.JButton btnAgregarVehiculo;
    private javax.swing.JButton btnBuscTerceros;
    private javax.swing.JButton btnBuscTerceros1;
    private javax.swing.JButton btnCambiarEstadoBodega;
    private javax.swing.JButton btnConsultarInformacionDesdeApi;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnEliminarVehiculo;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnImagen;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnModificarBodega;
    private javax.swing.JButton btnModificarVehiculo;
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
    private javax.swing.JComboBox cmbUsuarios;
    private javax.swing.JComboBox cmbVendedor;
    private javax.swing.JComboBox cmbZona;
    private com.toedter.calendar.JDateChooser dtFechaIngreso;
    private com.toedter.calendar.JDateChooser dtFechaNacimiento;
    private com.toedter.calendar.JDateChooser dtFechaRetiro;
    private javax.swing.ButtonGroup grpResponsableIva;
    private javax.swing.ButtonGroup grupoAgendar;
    private javax.swing.ButtonGroup grupoConvenio;
    private javax.swing.ButtonGroup grupoResidencia;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private com.toedter.calendar.JMonthChooser jMonthChooser1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
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
    private javax.swing.JLabel lbCiudad2;
    private javax.swing.JLabel lbCiudad20;
    private javax.swing.JLabel lbCiudad21;
    private javax.swing.JLabel lbCiudad22;
    private javax.swing.JLabel lbCiudad23;
    private javax.swing.JLabel lbCiudad24;
    private javax.swing.JLabel lbCiudad3;
    private javax.swing.JLabel lbCiudad4;
    private javax.swing.JLabel lbCiudad5;
    private javax.swing.JLabel lbCiudad6;
    private javax.swing.JLabel lbCiudad7;
    private javax.swing.JLabel lbCiudad8;
    private javax.swing.JLabel lbCiudad9;
    private javax.swing.JLabel lbCupo;
    private javax.swing.JLabel lbCupo2;
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
    private javax.swing.JLabel lbEmail8;
    private javax.swing.JLabel lbFecha;
    private javax.swing.JLabel lbFecha1;
    private javax.swing.JLabel lbFecha3;
    private javax.swing.JLabel lbFecha4;
    private javax.swing.JLabel lbFoto;
    private javax.swing.JLabel lbNit;
    private javax.swing.JLabel lbNit1;
    private javax.swing.JLabel lbNit2;
    private javax.swing.JLabel lbNota;
    private javax.swing.JLabel lbPNombre;
    private javax.swing.JLabel lbPlazo;
    private javax.swing.JLabel lbPlazo6;
    private javax.swing.JLabel lbPlazo7;
    private javax.swing.JLabel lbRazon;
    private javax.swing.JLabel lbSNombre;
    private javax.swing.JLabel lbSapellido;
    private javax.swing.JLabel lbTelefono;
    private javax.swing.JLabel lbTipo;
    private javax.swing.JLabel lbVendedor;
    private javax.swing.JPanel pnlDatos;
    private javax.swing.JPanel pnlDatosContacto;
    private javax.swing.JPanel pnlDatosEmpleado;
    private javax.swing.JPanel pnlDatosMedicos;
    private javax.swing.JPanel pnlDatosMedicosImportantes;
    private javax.swing.JPanel pnlDatosObligatorios;
    private javax.swing.JPanel pnlDatosVehiculo;
    private javax.swing.JPanel pnlDatosVentas;
    private javax.swing.JPanel pnlDatosVentasYOtros;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JPanel pnlInvisible;
    private javax.swing.JPanel pnlOtrosDatos;
    private javax.swing.JMenuItem popBorrar;
    private javax.swing.JRadioButton rdNoAgendar;
    private javax.swing.JRadioButton rdSiAgendar;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JTabbedPane tabDatosTerceros;
    private javax.swing.JTable tblAutos;
    private javax.swing.JTable tblBodegasContactos;
    private javax.swing.JTextField txtArl;
    private javax.swing.JTextField txtBarrio;
    private javax.swing.JTextField txtCargo;
    private javax.swing.JTextField txtCelular;
    private javax.swing.JTextField txtCodigoEps;
    private javax.swing.JTextField txtCodigoReferido;
    private javax.swing.JTextField txtConvenio;
    private javax.swing.JTextField txtCupo;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEps;
    private javax.swing.JTextField txtIdSistema;
    private javax.swing.JTextField txtIdentificacion;
    private javax.swing.JTextField txtLugarNacimiento;
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
    private javax.swing.JTextField txtPlazo;
    private javax.swing.JTextField txtReligion;
    private javax.swing.JTextField txtSalario;
    private javax.swing.JTextField txtSangre;
    private javax.swing.JTextField txtTarjeta;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtTelefonoResponsable;
    private javax.swing.JTextField txtTipoCliente;
    private javax.swing.JTextField txtccf;
    private javax.swing.JTextField txtpApellido;
    private javax.swing.JTextField txtpNombre;
    private javax.swing.JTextField txtsApellido;
    private javax.swing.JTextField txtsNombre;
    // End of variables declaration//GEN-END:variables
}
