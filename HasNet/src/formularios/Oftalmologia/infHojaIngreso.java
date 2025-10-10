package formularios.Oftalmologia;

import clases.ImagePreviewPanel;
import clases.Instancias;
import clases.Medico.ndCamposOrdenPredeterminada;
import clases.Medico.ndEpsPrecargados;
import clases.Medico.ndHistoriaClinica;
import clases.Medico.ndOrdenServicio;
import clases.Medico.ndSeguimientoClinica;
import clases.Oftalmologia.ndHojaIngreso;
import clases.Oftalmologia.ndSeguimientoClinicaOf;
import Utilidades.BaseDatos.SQL;
import clases.big;
import clases.metodosGenerales;
import clases.productos.ndProducto;
import Modelo.Terceros.ModeloContacto;
import formularios.Agenda.infInfoAgenda;
import formularios.Medico.buscAntecedentes;
import formularios.Medico.buscCies;
import formularios.Medico.buscCups;
import formularios.productos.buscProductos;
import formularios.terceros.buscClientes;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.math.BigDecimal;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class infHojaIngreso extends javax.swing.JInternalFrame {

    metodosGenerales metodos = new metodosGenerales();
    private Instancias instancias;
    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;
    private String paciente;
    String imagenes[];
    ndHistoriaClinica historia;
    private String orden, consulta;
    public String numConsulta, cliente, seleccionar;
    public boolean desdeOtro;
    int actual;
    boolean activo = false;
    boolean confirmada = false;
    boolean servicios = false;
    private JTable tabla;
    boolean facturarOrden = false;
    boolean agenda = false;
    String productoFacturarOrden = "";
    public Path FROM2;
    ndHojaIngreso nodoConsultaPublic = null;

    public infHojaIngreso() {

        initComponents();
        btnGuardar1.setEnabled(false);
        instancias = Instancias.getInstancias();
        lista.setSelectedIndex(0);
        txtFechaIngreso.setText(metodosGenerales.fecha());
        txtProfesional.setText(instancias.getUsuarioLog().getNombre());
        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        setBorder(null);
        repaint();

        scrFormulario.getVerticalScrollBar().setUnitIncrement(18);
    }

    public boolean isServicios() {
        return servicios;
    }

    public void setServicios(boolean servicios) {
        this.servicios = servicios;
    }

    public boolean isAgenda() {
        return agenda;
    }

    public void setAgenda(boolean agenda) {
        this.agenda = agenda;
    }

    public boolean isConfirmada() {
        return confirmada;
    }

    public void setConfirmada(boolean confirmada) {
        this.confirmada = confirmada;
    }

    public String getNumConsulta() {
        return numConsulta;
    }

    public void setNumConsulta(String numConsulta) {
        this.numConsulta = numConsulta;
    }

    public boolean isDesdeOtro() {
        return desdeOtro;
    }

    public void setDesdeOtro(boolean desdeOtro) {
        this.desdeOtro = desdeOtro;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        scrFormulario = new javax.swing.JScrollPane();
        pnlFormulario = new javax.swing.JPanel();
        lbNit10 = new javax.swing.JLabel();
        lbEmail5 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        lista = new javax.swing.JList();
        jPanel1 = new javax.swing.JPanel();
        lbRazon22 = new javax.swing.JLabel();
        btnHipertension = new javax.swing.JButton();
        btnDiabetes = new javax.swing.JButton();
        btnArtritis = new javax.swing.JButton();
        btnUlceraGastrica = new javax.swing.JButton();
        btnAlergias = new javax.swing.JButton();
        txtAP = new javax.swing.JTextField();
        lbRazon32 = new javax.swing.JLabel();
        lbRazon33 = new javax.swing.JLabel();
        txtLentes = new javax.swing.JTextField();
        lbRazon34 = new javax.swing.JLabel();
        btnHipertension1 = new javax.swing.JButton();
        btnDiabetes1 = new javax.swing.JButton();
        btnArtritis1 = new javax.swing.JButton();
        btnEstrabismo = new javax.swing.JButton();
        btnCataratas = new javax.swing.JButton();
        btnGlaucoma = new javax.swing.JButton();
        txtAP1 = new javax.swing.JTextField();
        lbRazon35 = new javax.swing.JLabel();
        lbRazon41 = new javax.swing.JLabel();
        txtAlergias1 = new javax.swing.JTextField();
        txtLentes1 = new javax.swing.JTextField();
        lbRazon37 = new javax.swing.JLabel();
        lbRazon38 = new javax.swing.JLabel();
        lbRazon48 = new javax.swing.JLabel();
        lbRazon43 = new javax.swing.JLabel();
        lbRazon45 = new javax.swing.JLabel();
        lbRazon49 = new javax.swing.JLabel();
        lbRazon39 = new javax.swing.JLabel();
        txtAvOI = new javax.swing.JTextField();
        txtAvOD = new javax.swing.JTextField();
        lbRazon44 = new javax.swing.JLabel();
        txtClOD = new javax.swing.JTextField();
        txtClOI = new javax.swing.JTextField();
        jScrollPane23 = new javax.swing.JScrollPane();
        txtOtrasEnfermedadesOculares = new javax.swing.JTextArea();
        jScrollPane24 = new javax.swing.JScrollPane();
        txtOtrasEnfermedadesGenerales = new javax.swing.JTextArea();
        jScrollPane25 = new javax.swing.JScrollPane();
        txtOtrasEnfermedades = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        lbDireccion40 = new javax.swing.JLabel();
        lbDireccion23 = new javax.swing.JLabel();
        txtPrimeroApellido = new javax.swing.JTextField();
        txtFechaIngreso = new javax.swing.JTextField();
        txtPrimerNombre = new javax.swing.JTextField();
        lbDireccion20 = new javax.swing.JLabel();
        lbDireccion41 = new javax.swing.JLabel();
        btnBuscTerceros = new javax.swing.JButton();
        txtIdentificacion = new javax.swing.JTextField();
        lbDireccion42 = new javax.swing.JLabel();
        txtSegundoApellido = new javax.swing.JTextField();
        txtSegundoNombre = new javax.swing.JTextField();
        lbDireccion43 = new javax.swing.JLabel();
        lbDireccion44 = new javax.swing.JLabel();
        lbDireccion47 = new javax.swing.JLabel();
        txtOcupacion = new javax.swing.JTextField();
        txtFechaNacimiento = new javax.swing.JTextField();
        lbDireccion45 = new javax.swing.JLabel();
        txtEdad = new javax.swing.JTextField();
        lbDireccion48 = new javax.swing.JLabel();
        lbDireccion46 = new javax.swing.JLabel();
        txtSexo = new javax.swing.JTextField();
        txtEstadoCivil = new javax.swing.JTextField();
        lbDireccion49 = new javax.swing.JLabel();
        lbDireccion25 = new javax.swing.JLabel();
        txtDepartamento = new javax.swing.JTextField();
        lbDireccion26 = new javax.swing.JLabel();
        lbDireccion50 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        txtMunicipio = new javax.swing.JTextField();
        lbDireccion27 = new javax.swing.JLabel();
        txtZona = new javax.swing.JTextField();
        lbDireccion51 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        btnCargar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        lbRazon36 = new javax.swing.JLabel();
        txtForias = new javax.swing.JTextField();
        lbRazon40 = new javax.swing.JLabel();
        txtPupilas = new javax.swing.JTextField();
        lbRazon50 = new javax.swing.JLabel();
        lbRazon54 = new javax.swing.JLabel();
        txtTonometriaOD = new javax.swing.JTextField();
        lbRazon55 = new javax.swing.JLabel();
        lbRazon56 = new javax.swing.JLabel();
        txtTonometriaOI = new javax.swing.JTextField();
        lbRazon57 = new javax.swing.JLabel();
        lbRazon58 = new javax.swing.JLabel();
        txtRefraccionOI = new javax.swing.JTextField();
        lbRazon46 = new javax.swing.JLabel();
        lbRazon47 = new javax.swing.JLabel();
        lbRazon42 = new javax.swing.JLabel();
        txtRefraccionOD = new javax.swing.JTextField();
        txtRefSubjetivoOI = new javax.swing.JTextField();
        lbRazon53 = new javax.swing.JLabel();
        lbRazon52 = new javax.swing.JLabel();
        txtRefSubjetivoOD = new javax.swing.JTextField();
        lbRazon51 = new javax.swing.JLabel();
        jScrollPane22 = new javax.swing.JScrollPane();
        txtMc = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        btnPaquimetria = new javax.swing.JButton();
        btnBiometria = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnReimprimir = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        lbCodFoto = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        lbDireccion52 = new javax.swing.JLabel();
        lbDireccion55 = new javax.swing.JLabel();
        txtResponsable = new javax.swing.JTextField();
        lbDireccion56 = new javax.swing.JLabel();
        txtTelefono1 = new javax.swing.JTextField();
        cmbParentesco = new javax.swing.JComboBox();
        jPanel7 = new javax.swing.JPanel();
        lbNit4 = new javax.swing.JLabel();
        txtProfesional = new javax.swing.JTextField();
        lbRazon4 = new javax.swing.JLabel();
        cmbFecha = new javax.swing.JComboBox();
        lbNit5 = new javax.swing.JLabel();
        txtExamenes = new javax.swing.JTextField();
        lbFoto4 = new javax.swing.JLabel();
        btnNuevo5 = new javax.swing.JButton();
        btnNuevo9 = new javax.swing.JButton();
        btnNuevo8 = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblExamens = new javax.swing.JTable();
        btnSubir = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        lbEmail22 = new javax.swing.JLabel();
        jScrollPane21 = new javax.swing.JScrollPane();
        txtEvolucionSeg = new javax.swing.JTextArea();
        lbEmail23 = new javax.swing.JLabel();
        btnGuardar1 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        btnBusProd = new javax.swing.JButton();
        txtCodProducto = new javax.swing.JTextField();
        lbDireccion59 = new javax.swing.JLabel();
        txtCodServicioo = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        lbRazon59 = new javax.swing.JLabel();
        lbRazon60 = new javax.swing.JLabel();
        lbDireccion57 = new javax.swing.JLabel();
        txtCie = new javax.swing.JTextField();
        txtDiagnostico = new javax.swing.JTextField();
        txtProcedimiento = new javax.swing.JTextField();
        txtCup = new javax.swing.JTextField();
        lbDireccion58 = new javax.swing.JLabel();
        jScrollPane26 = new javax.swing.JScrollPane();
        txtBiomicroscopia = new javax.swing.JTextArea();
        jScrollPane27 = new javax.swing.JScrollPane();
        txtFondoDeOjo = new javax.swing.JTextArea();
        btnGuardar = new javax.swing.JButton();
        txtCie1 = new javax.swing.JTextField();
        txtDiagnostico1 = new javax.swing.JTextField();
        txtDiagnostico2 = new javax.swing.JTextField();
        txtCie2 = new javax.swing.JTextField();
        txtDiagnostico3 = new javax.swing.JTextField();
        txtCie3 = new javax.swing.JTextField();
        txtDiagnostico4 = new javax.swing.JTextField();
        txtCie4 = new javax.swing.JTextField();
        lbDireccion60 = new javax.swing.JLabel();
        lbDireccion61 = new javax.swing.JLabel();
        lbDireccion62 = new javax.swing.JLabel();
        lbDireccion63 = new javax.swing.JLabel();
        lbDireccion64 = new javax.swing.JLabel();
        lbDireccion65 = new javax.swing.JLabel();
        lbDireccion66 = new javax.swing.JLabel();
        lbDireccion67 = new javax.swing.JLabel();
        lbDireccion68 = new javax.swing.JLabel();
        lbDireccion69 = new javax.swing.JLabel();
        txtCup1 = new javax.swing.JTextField();
        txtCup2 = new javax.swing.JTextField();
        txtCup3 = new javax.swing.JTextField();
        txtCup4 = new javax.swing.JTextField();
        txtProcedimiento1 = new javax.swing.JTextField();
        txtProcedimiento2 = new javax.swing.JTextField();
        txtProcedimiento3 = new javax.swing.JTextField();
        txtProcedimiento4 = new javax.swing.JTextField();
        btnProximaCita = new javax.swing.JButton();
        jScrollPane28 = new javax.swing.JScrollPane();
        txtConducta = new javax.swing.JTextArea();
        lbDireccion70 = new javax.swing.JLabel();
        jScrollPane29 = new javax.swing.JScrollPane();
        txtAnexos = new javax.swing.JTextArea();
        lbRazon61 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        jTextField1.setText("jTextField1");

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        setBackground(new java.awt.Color(255, 255, 255));
        setTitle("Factura");

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));
        pnlFormulario.setToolTipText("");

        lbNit10.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        lbNit10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit10.setText("HOJA DE INGRESO");

        lbEmail5.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbEmail5.setText("Seleccione");

        lista.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lista.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "HOY - NUEVO" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        lista.setToolTipText("");
        lista.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaMouseClicked(evt);
            }
        });
        lista.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listaValueChanged(evt);
            }
        });
        jScrollPane8.setViewportView(lista);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lbRazon22.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon22.setText("A.P.");

        btnHipertension.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        btnHipertension.setText("Hipertensión");
        btnHipertension.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHipertensionMouseClicked(evt);
            }
        });
        btnHipertension.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHipertensionActionPerformed(evt);
            }
        });

        btnDiabetes.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        btnDiabetes.setText("Diabetes");
        btnDiabetes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDiabetesActionPerformed(evt);
            }
        });

        btnArtritis.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        btnArtritis.setText("Artritis");
        btnArtritis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArtritisActionPerformed(evt);
            }
        });

        btnUlceraGastrica.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        btnUlceraGastrica.setText("Ulcera Gástrica");
        btnUlceraGastrica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUlceraGastricaActionPerformed(evt);
            }
        });

        btnAlergias.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        btnAlergias.setText("Alergias");
        btnAlergias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlergiasActionPerformed(evt);
            }
        });

        txtAP.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        lbRazon32.setFont(new java.awt.Font("Bell MT", 0, 16)); // NOI18N
        lbRazon32.setText("Otras enfermedades generales:");

        lbRazon33.setFont(new java.awt.Font("Bell MT", 0, 16)); // NOI18N
        lbRazon33.setText("Lentes");

        txtLentes.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        lbRazon34.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon34.setText("A.F.");

        btnHipertension1.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        btnHipertension1.setText("Hipertensión");
        btnHipertension1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHipertension1ActionPerformed(evt);
            }
        });

        btnDiabetes1.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        btnDiabetes1.setText("Diabetes");
        btnDiabetes1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDiabetes1ActionPerformed(evt);
            }
        });

        btnArtritis1.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        btnArtritis1.setText("Artritis");
        btnArtritis1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArtritis1ActionPerformed(evt);
            }
        });

        btnEstrabismo.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        btnEstrabismo.setText("Estrabismo");
        btnEstrabismo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEstrabismoActionPerformed(evt);
            }
        });

        btnCataratas.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        btnCataratas.setText("Cataratas");
        btnCataratas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCataratasActionPerformed(evt);
            }
        });

        btnGlaucoma.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        btnGlaucoma.setText("Glaucoma");
        btnGlaucoma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGlaucomaActionPerformed(evt);
            }
        });

        txtAP1.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        lbRazon35.setFont(new java.awt.Font("Bell MT", 0, 16)); // NOI18N
        lbRazon35.setText("Lentes:");

        lbRazon41.setFont(new java.awt.Font("Bell MT", 0, 16)); // NOI18N
        lbRazon41.setText("Alergias:");

        txtAlergias1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtAlergias1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAlergias1ActionPerformed(evt);
            }
        });

        txtLentes1.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        lbRazon37.setFont(new java.awt.Font("Bell MT", 0, 16)); // NOI18N
        lbRazon37.setText("Otras enfermedades oculares:");

        lbRazon38.setFont(new java.awt.Font("Bell MT", 0, 16)); // NOI18N
        lbRazon38.setText("Otras enfermedades generales:");

        lbRazon48.setFont(new java.awt.Font("Bell MT", 0, 16)); // NOI18N
        lbRazon48.setText("O.I.");

        lbRazon43.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon43.setText("C.L.");

        lbRazon45.setFont(new java.awt.Font("Bell MT", 0, 16)); // NOI18N
        lbRazon45.setText("O.I.");

        lbRazon49.setFont(new java.awt.Font("Bell MT", 0, 16)); // NOI18N
        lbRazon49.setText("O.D.");

        lbRazon39.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon39.setText("A.V.");

        txtAvOI.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        txtAvOD.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        lbRazon44.setFont(new java.awt.Font("Bell MT", 0, 16)); // NOI18N
        lbRazon44.setText("O.D.");

        txtClOD.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        txtClOI.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        txtOtrasEnfermedadesOculares.setColumns(20);
        txtOtrasEnfermedadesOculares.setLineWrap(true);
        txtOtrasEnfermedadesOculares.setRows(2);
        txtOtrasEnfermedadesOculares.setName("Evolución"); // NOI18N
        txtOtrasEnfermedadesOculares.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtOtrasEnfermedadesOcularesKeyReleased(evt);
            }
        });
        jScrollPane23.setViewportView(txtOtrasEnfermedadesOculares);

        txtOtrasEnfermedadesGenerales.setColumns(20);
        txtOtrasEnfermedadesGenerales.setLineWrap(true);
        txtOtrasEnfermedadesGenerales.setRows(2);
        txtOtrasEnfermedadesGenerales.setName("Evolución"); // NOI18N
        txtOtrasEnfermedadesGenerales.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtOtrasEnfermedadesGeneralesKeyReleased(evt);
            }
        });
        jScrollPane24.setViewportView(txtOtrasEnfermedadesGenerales);

        txtOtrasEnfermedades.setColumns(20);
        txtOtrasEnfermedades.setLineWrap(true);
        txtOtrasEnfermedades.setRows(2);
        txtOtrasEnfermedades.setName("Evolución"); // NOI18N
        txtOtrasEnfermedades.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtOtrasEnfermedadesKeyReleased(evt);
            }
        });
        jScrollPane25.setViewportView(txtOtrasEnfermedades);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane23)
                    .addComponent(txtAP1)
                    .addComponent(txtAP)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbRazon39)
                                    .addComponent(lbRazon43))
                                .addGap(31, 31, 31)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbRazon49, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbRazon44))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtAvOD)
                                    .addComponent(txtClOD, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE))
                                .addGap(36, 36, 36)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbRazon45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbRazon48, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtAvOI, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                                    .addComponent(txtClOI)))
                            .addComponent(lbRazon32)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lbRazon22, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnHipertension, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDiabetes, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnArtritis, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnUlceraGastrica, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAlergias, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbRazon38)
                            .addComponent(lbRazon37))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane25)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbRazon33, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbRazon34))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnHipertension1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(352, 352, 352)
                                        .addComponent(btnCataratas, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btnDiabetes1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnArtritis1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnEstrabismo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnGlaucoma, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtLentes)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbRazon35, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbRazon41, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtLentes1)
                            .addComponent(txtAlergias1)))
                    .addComponent(jScrollPane24))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbRazon22)
                    .addComponent(btnHipertension)
                    .addComponent(btnDiabetes)
                    .addComponent(btnArtritis)
                    .addComponent(btnUlceraGastrica)
                    .addComponent(btnAlergias))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAP, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(lbRazon32)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbRazon33)
                    .addComponent(txtLentes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbRazon34)
                    .addComponent(btnDiabetes1)
                    .addComponent(btnArtritis1)
                    .addComponent(btnEstrabismo)
                    .addComponent(btnCataratas)
                    .addComponent(btnHipertension1)
                    .addComponent(btnGlaucoma))
                .addGap(5, 5, 5)
                .addComponent(txtAP1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbRazon35)
                    .addComponent(txtLentes1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAlergias1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbRazon41))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbRazon37)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(lbRazon38)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbRazon48)
                        .addComponent(txtAvOI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbRazon39)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbRazon49, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtAvOD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbRazon43)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtClOD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbRazon44)
                                .addComponent(lbRazon45)
                                .addComponent(txtClOI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lbDireccion40.setFont(new java.awt.Font("Bell MT", 0, 16)); // NOI18N
        lbDireccion40.setText("PRIMER NOMBRE:");

        lbDireccion23.setFont(new java.awt.Font("Bell MT", 0, 16)); // NOI18N
        lbDireccion23.setText("PRIMER APELLIDO:");

        txtPrimeroApellido.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtPrimeroApellido.setEnabled(false);

        txtFechaIngreso.setEditable(false);
        txtFechaIngreso.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtFechaIngreso.setName("FechaIngreso"); // NOI18N

        txtPrimerNombre.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtPrimerNombre.setEnabled(false);

        lbDireccion20.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbDireccion20.setText("FECHA DE INGRESO:");

        lbDireccion41.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbDireccion41.setText("N° DE IDENTIFICACIÓN:");

        btnBuscTerceros.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscTerceros.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscTerceros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/LupaPequeña.png"))); // NOI18N
        btnBuscTerceros.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscTerceros.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBuscTerceros.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnBuscTerceros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscTercerosActionPerformed(evt);
            }
        });

        txtIdentificacion.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtIdentificacion.setName("Identificación"); // NOI18N
        txtIdentificacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdentificacionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdentificacionKeyTyped(evt);
            }
        });

        lbDireccion42.setFont(new java.awt.Font("Bell MT", 0, 16)); // NOI18N
        lbDireccion42.setText("SEGUNDO APELLIDO:");

        txtSegundoApellido.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtSegundoApellido.setEnabled(false);

        txtSegundoNombre.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtSegundoNombre.setEnabled(false);

        lbDireccion43.setFont(new java.awt.Font("Bell MT", 0, 16)); // NOI18N
        lbDireccion43.setText("SEGUNDO NOMBRE:");

        lbDireccion44.setFont(new java.awt.Font("Bell MT", 1, 14)); // NOI18N
        lbDireccion44.setText("FECHA DE NACIMIENTO:");

        lbDireccion47.setFont(new java.awt.Font("Bell MT", 0, 16)); // NOI18N
        lbDireccion47.setText("OCUPACIÓN(OFICIO):");

        txtOcupacion.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtOcupacion.setEnabled(false);

        txtFechaNacimiento.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtFechaNacimiento.setEnabled(false);

        lbDireccion45.setFont(new java.awt.Font("Bell MT", 0, 16)); // NOI18N
        lbDireccion45.setText("EDAD:");

        txtEdad.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtEdad.setEnabled(false);

        lbDireccion48.setFont(new java.awt.Font("Bell MT", 0, 16)); // NOI18N
        lbDireccion48.setText("ESTADO CIVIL:");

        lbDireccion46.setFont(new java.awt.Font("Bell MT", 0, 16)); // NOI18N
        lbDireccion46.setText("SEXO:");

        txtSexo.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtSexo.setEnabled(false);

        txtEstadoCivil.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtEstadoCivil.setEnabled(false);

        lbDireccion49.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbDireccion49.setText("RESIDENCIA HABITAL");

        lbDireccion25.setFont(new java.awt.Font("Bell MT", 0, 16)); // NOI18N
        lbDireccion25.setText("DEPARTAMENTO:");

        txtDepartamento.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtDepartamento.setEnabled(false);

        lbDireccion26.setFont(new java.awt.Font("Bell MT", 0, 16)); // NOI18N
        lbDireccion26.setText("MUNICIPIO:");

        lbDireccion50.setFont(new java.awt.Font("Bell MT", 0, 16)); // NOI18N
        lbDireccion50.setText("DIRECCIÓN:");

        txtDireccion.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtDireccion.setEnabled(false);

        txtMunicipio.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtMunicipio.setEnabled(false);

        lbDireccion27.setFont(new java.awt.Font("Bell MT", 0, 16)); // NOI18N
        lbDireccion27.setText("ZONA:");

        txtZona.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtZona.setEnabled(false);

        lbDireccion51.setFont(new java.awt.Font("Bell MT", 0, 16)); // NOI18N
        lbDireccion51.setText("TELEFONO:");

        txtTelefono.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtTelefono.setEnabled(false);
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });

        btnCargar.setText("jButton1");
        btnCargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbDireccion25)
                            .addComponent(lbDireccion50))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDireccion)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lbDireccion26)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbDireccion51, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbDireccion27, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtZona)
                            .addComponent(txtTelefono)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lbDireccion49)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbDireccion44)
                                    .addComponent(lbDireccion47))
                                .addGap(10, 10, 10)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtFechaNacimiento, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                                    .addComponent(txtOcupacion)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                            .addComponent(lbDireccion20)
                                            .addGap(18, 18, 18))
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                            .addComponent(lbDireccion23, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(lbDireccion40, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)))
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtPrimeroApellido, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                                    .addComponent(txtPrimerNombre, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtFechaIngreso))))
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lbDireccion42, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lbDireccion43, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                            .addComponent(lbDireccion45, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(24, 24, 24)
                                            .addComponent(lbDireccion46))
                                        .addComponent(lbDireccion48))
                                    .addGap(10, 10, 10)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtSegundoNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                                        .addComponent(txtSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtEstadoCivil))))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(lbDireccion41, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtSegundoApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addComponent(txtIdentificacion)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnBuscTerceros))))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(btnCargar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(220, 220, 220)))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCargar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbDireccion20)
                            .addComponent(txtFechaIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbDireccion41)
                            .addComponent(txtIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPrimeroApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbDireccion23))
                                .addGap(3, 3, 3)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbDireccion40)
                                    .addComponent(txtPrimerNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbDireccion42)
                                    .addComponent(txtSegundoApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(3, 3, 3)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtSegundoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(lbDireccion43, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(1, 1, 1)))))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbDireccion44)
                            .addComponent(txtFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbDireccion45)
                            .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbDireccion46)
                            .addComponent(txtSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbDireccion47)
                            .addComponent(txtOcupacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbDireccion48)
                            .addComponent(txtEstadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addComponent(lbDireccion49)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbDireccion25)
                            .addComponent(lbDireccion26)
                            .addComponent(txtDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbDireccion27)
                            .addComponent(txtZona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lbDireccion51)
                                .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbDireccion50)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnBuscTerceros, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        txtIdentificacion.getAccessibleContext().setAccessibleName("Identificacion");

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lbRazon36.setFont(new java.awt.Font("Bell MT", 0, 16)); // NOI18N
        lbRazon36.setText("FORIAS:");

        txtForias.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        lbRazon40.setFont(new java.awt.Font("Bell MT", 0, 16)); // NOI18N
        lbRazon40.setText("PUPILAS:");

        txtPupilas.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtPupilas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPupilasActionPerformed(evt);
            }
        });

        lbRazon50.setFont(new java.awt.Font("Bell MT", 0, 16)); // NOI18N
        lbRazon50.setText("M.C.");

        lbRazon54.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon54.setText("TONOMETRIA");

        txtTonometriaOD.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        lbRazon55.setFont(new java.awt.Font("Bell MT", 0, 16)); // NOI18N
        lbRazon55.setText("O.D.");

        lbRazon56.setFont(new java.awt.Font("Bell MT", 0, 16)); // NOI18N
        lbRazon56.setText("O.I.");

        txtTonometriaOI.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtTonometriaOI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTonometriaOIActionPerformed(evt);
            }
        });

        lbRazon57.setFont(new java.awt.Font("Bell MT", 0, 16)); // NOI18N
        lbRazon57.setText("m.m.h.g");

        lbRazon58.setFont(new java.awt.Font("Bell MT", 0, 16)); // NOI18N
        lbRazon58.setText("m.m.h.g");

        txtRefraccionOI.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        lbRazon46.setFont(new java.awt.Font("Bell MT", 0, 16)); // NOI18N
        lbRazon46.setText("O.I.");

        lbRazon47.setFont(new java.awt.Font("Bell MT", 0, 16)); // NOI18N
        lbRazon47.setText("O.D.");

        lbRazon42.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon42.setText("REFRACCIÓN:");

        txtRefraccionOD.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        txtRefSubjetivoOI.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        lbRazon53.setFont(new java.awt.Font("Bell MT", 0, 16)); // NOI18N
        lbRazon53.setText("O.I.");

        lbRazon52.setFont(new java.awt.Font("Bell MT", 0, 16)); // NOI18N
        lbRazon52.setText("O.D.");

        txtRefSubjetivoOD.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        lbRazon51.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon51.setText("SUBJETIVO");

        txtMc.setColumns(20);
        txtMc.setLineWrap(true);
        txtMc.setRows(2);
        txtMc.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtMc.setName("Evolución"); // NOI18N
        txtMc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMcKeyReleased(evt);
            }
        });
        jScrollPane22.setViewportView(txtMc);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(lbRazon54)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbRazon55, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbRazon56, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtTonometriaOI, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                                    .addComponent(txtTonometriaOD))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbRazon58)
                                    .addComponent(lbRazon57))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lbRazon36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbRazon40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(lbRazon50, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtPupilas, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 668, Short.MAX_VALUE)
                                    .addComponent(txtForias, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addGap(10, 10, 10))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbRazon51)
                            .addComponent(lbRazon42))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(lbRazon52)
                                .addGap(18, 18, 18)
                                .addComponent(txtRefSubjetivoOD, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(lbRazon47, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtRefraccionOD)))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbRazon53, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbRazon46, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtRefraccionOI)
                            .addComponent(txtRefSubjetivoOI))
                        .addContainerGap())))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbRazon46)
                    .addComponent(txtRefraccionOI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbRazon47)
                    .addComponent(txtRefraccionOD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbRazon42))
                .addGap(3, 3, 3)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRefSubjetivoOD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbRazon52)
                    .addComponent(lbRazon51)
                    .addComponent(lbRazon53)
                    .addComponent(txtRefSubjetivoOI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbRazon36)
                    .addComponent(txtForias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbRazon40)
                    .addComponent(txtPupilas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbRazon50)
                    .addComponent(jScrollPane22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbRazon54)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbRazon55)
                            .addComponent(txtTonometriaOD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbRazon56)
                            .addComponent(txtTonometriaOI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(lbRazon57)
                        .addGap(5, 5, 5)
                        .addComponent(lbRazon58)))
                .addGap(5, 5, 5))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jButton3.setText("Formula medica");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(255, 255, 255));
        jButton4.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jButton4.setText("Incapacidad");
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jButton2.setText("Ayuda diagnostica");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(255, 255, 255));
        jButton6.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jButton6.setText("Formula lentes");
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        btnPaquimetria.setBackground(new java.awt.Color(204, 204, 204));
        btnPaquimetria.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnPaquimetria.setForeground(new java.awt.Color(255, 255, 255));
        btnPaquimetria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/paquimetria.jpg"))); // NOI18N
        btnPaquimetria.setText("PAQUIMETRIA");
        btnPaquimetria.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPaquimetria.setEnabled(false);
        btnPaquimetria.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnPaquimetria.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnPaquimetria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPaquimetriaActionPerformed(evt);
            }
        });

        btnBiometria.setBackground(new java.awt.Color(204, 204, 204));
        btnBiometria.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBiometria.setForeground(new java.awt.Color(255, 255, 255));
        btnBiometria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/biometria.png"))); // NOI18N
        btnBiometria.setText("BIOMETRIA");
        btnBiometria.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBiometria.setEnabled(false);
        btnBiometria.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBiometria.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnBiometria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBiometriaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPaquimetria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBiometria, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnPaquimetria, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBiometria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 22, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        btnReimprimir.setBackground(new java.awt.Color(0, 153, 153));
        btnReimprimir.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnReimprimir.setForeground(new java.awt.Color(255, 255, 255));
        btnReimprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/imprimir.png"))); // NOI18N
        btnReimprimir.setText("REIMPRIMIR");
        btnReimprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReimprimir.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnReimprimir.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnReimprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReimprimirActionPerformed(evt);
            }
        });

        btnLimpiar.setBackground(new java.awt.Color(102, 102, 255));
        btnLimpiar.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/limpiar.png"))); // NOI18N
        btnLimpiar.setText("LIMPIAR  ");
        btnLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpiar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnLimpiar.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        lbCodFoto.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(lbCodFoto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnReimprimir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLimpiar)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnReimprimir)
                        .addComponent(btnLimpiar))
                    .addComponent(lbCodFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lbDireccion52.setFont(new java.awt.Font("Bell MT", 0, 16)); // NOI18N
        lbDireccion52.setText("NOMBRE RESPONSABLE:");

        lbDireccion55.setFont(new java.awt.Font("Bell MT", 0, 16)); // NOI18N
        lbDireccion55.setText("PARENTESCO:");

        txtResponsable.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtResponsable.setName("Responsable"); // NOI18N
        txtResponsable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtResponsableActionPerformed(evt);
            }
        });

        lbDireccion56.setFont(new java.awt.Font("Bell MT", 0, 16)); // NOI18N
        lbDireccion56.setText("TELEFONO:");

        txtTelefono1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtTelefono1.setName("Telefono responsable"); // NOI18N
        txtTelefono1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefono1KeyTyped(evt);
            }
        });

        cmbParentesco.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione...", "Papá", "Mamá", "Cónyuge", "Yerno", "Nuera", "Madrastra", "Padrastro", "Tio(a)", "Hermano(a)", "Abuelo(a)", "Primo(a)", "Sobrino(a)", "Amigo(a)", "Hijo(a)", "Novio(a)", "Cuñado(a)", "Suegro(a)" }));
        cmbParentesco.setName("Parentesco"); // NOI18N
        cmbParentesco.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbParentescoKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbDireccion52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbDireccion55, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(txtResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(lbDireccion56, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTelefono1))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(cmbParentesco, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbDireccion52)
                        .addComponent(txtResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbDireccion56))
                    .addComponent(txtTelefono1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDireccion55)
                    .addComponent(cmbParentesco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 10))); // NOI18N

        lbNit4.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbNit4.setText("Profesional:");

        txtProfesional.setEditable(false);
        txtProfesional.setName("Nit"); // NOI18N
        txtProfesional.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProfesionalKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtProfesionalKeyTyped(evt);
            }
        });

        lbRazon4.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon4.setText("Fecha:");

        cmbFecha.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Hoy" }));
        cmbFecha.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbFechaItemStateChanged(evt);
            }
        });
        cmbFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbFechaActionPerformed(evt);
            }
        });

        lbNit5.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbNit5.setText("Examenes:");

        txtExamenes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtExamenesKeyReleased(evt);
            }
        });

        lbFoto4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lbFoto4.setToolTipText("");
        lbFoto4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(153, 153, 0)));

        btnNuevo5.setBackground(new java.awt.Color(204, 204, 204));
        btnNuevo5.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnNuevo5.setForeground(new java.awt.Color(255, 255, 255));
        btnNuevo5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pantallaCompleta.png"))); // NOI18N
        btnNuevo5.setToolTipText("Subir");
        btnNuevo5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevo5.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnNuevo5.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnNuevo5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevo5ActionPerformed(evt);
            }
        });

        btnNuevo9.setBackground(new java.awt.Color(204, 204, 204));
        btnNuevo9.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnNuevo9.setForeground(new java.awt.Color(255, 255, 255));
        btnNuevo9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/anterior_1.png"))); // NOI18N
        btnNuevo9.setToolTipText("Subir");
        btnNuevo9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevo9.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnNuevo9.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnNuevo9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevo9ActionPerformed(evt);
            }
        });

        btnNuevo8.setBackground(new java.awt.Color(204, 204, 204));
        btnNuevo8.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnNuevo8.setForeground(new java.awt.Color(255, 255, 255));
        btnNuevo8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/siguiente.png"))); // NOI18N
        btnNuevo8.setToolTipText("Subir");
        btnNuevo8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevo8.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnNuevo8.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnNuevo8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevo8ActionPerformed(evt);
            }
        });

        tblExamens.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Descripción del examen", "null"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblExamens.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblExamensMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(tblExamens);
        if (tblExamens.getColumnModel().getColumnCount() > 0) {
            tblExamens.getColumnModel().getColumn(1).setMinWidth(0);
            tblExamens.getColumnModel().getColumn(1).setPreferredWidth(0);
            tblExamens.getColumnModel().getColumn(1).setMaxWidth(0);
        }

        btnSubir.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSubir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/subir imagen.png"))); // NOI18N
        btnSubir.setText("Subir Imagen");
        btnSubir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubirActionPerformed(evt);
            }
        });

        btnAgregar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/agregar.png"))); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        lbEmail22.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbEmail22.setText("Evolución:");

        txtEvolucionSeg.setColumns(20);
        txtEvolucionSeg.setLineWrap(true);
        txtEvolucionSeg.setRows(3);
        txtEvolucionSeg.setName("Evolución"); // NOI18N
        txtEvolucionSeg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEvolucionSegKeyReleased(evt);
            }
        });
        jScrollPane21.setViewportView(txtEvolucionSeg);

        lbEmail23.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbEmail23.setText("*");

        btnGuardar1.setBackground(new java.awt.Color(0, 153, 153));
        btnGuardar1.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnGuardar1.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardar1.setText("GUARDAR");
        btnGuardar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardar1.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnGuardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane21)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbNit5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbNit4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtProfesional, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                            .addComponent(txtExamenes))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addComponent(btnSubir)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addComponent(lbRazon4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmbFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(lbFoto4, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnNuevo8, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNuevo9, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNuevo5, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(lbEmail22, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbEmail23)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnGuardar1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNit4)
                    .addComponent(txtProfesional, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbRazon4)
                    .addComponent(cmbFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNit5)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtExamenes, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSubir)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(btnNuevo5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNuevo9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNuevo8))
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(lbFoto4, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbEmail22)
                            .addComponent(lbEmail23))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50))
                    .addComponent(btnGuardar1))
                .addGap(15, 15, 15))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnBusProd.setBackground(new java.awt.Color(204, 204, 204));
        btnBusProd.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBusProd.setForeground(new java.awt.Color(255, 255, 255));
        btnBusProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/LupaPequeña.png"))); // NOI18N
        btnBusProd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBusProd.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBusProd.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnBusProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBusProdActionPerformed(evt);
            }
        });

        txtCodProducto.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtCodProducto.setName("Servicio"); // NOI18N
        txtCodProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodProductoActionPerformed(evt);
            }
        });
        txtCodProducto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCodProductoFocusGained(evt);
            }
        });
        txtCodProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodProductoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodProductoKeyReleased(evt);
            }
        });

        lbDireccion59.setFont(new java.awt.Font("Bell MT", 0, 16)); // NOI18N
        lbDireccion59.setText("SERVICIO:");

        txtCodServicioo.setEditable(false);
        txtCodServicioo.setName("Servicio"); // NOI18N
        txtCodServicioo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodServiciooKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbDireccion59, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCodServicioo, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtCodProducto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBusProd)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnBusProd, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCodProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbDireccion59))
                    .addComponent(txtCodServicioo, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(5, 5, 5))
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lbRazon59.setFont(new java.awt.Font("Bell MT", 0, 16)); // NOI18N
        lbRazon59.setText("BIOMICROSCOPIA:");

        lbRazon60.setFont(new java.awt.Font("Bell MT", 0, 16)); // NOI18N
        lbRazon60.setText("FONDO DE OJO:");

        lbDireccion57.setFont(new java.awt.Font("Bell MT", 0, 16)); // NOI18N
        lbDireccion57.setText("1.");

        txtCie.setEditable(false);
        txtCie.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtCie.setName("Cie"); // NOI18N
        txtCie.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCieKeyReleased(evt);
            }
        });

        txtDiagnostico.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtDiagnostico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDiagnosticoKeyReleased(evt);
            }
        });

        txtProcedimiento.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtProcedimiento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProcedimientoKeyReleased(evt);
            }
        });

        txtCup.setEditable(false);
        txtCup.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtCup.setName("Cup"); // NOI18N
        txtCup.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCupKeyReleased(evt);
            }
        });

        lbDireccion58.setFont(new java.awt.Font("Bell MT", 0, 16)); // NOI18N
        lbDireccion58.setText("CONDUCTA:");

        txtBiomicroscopia.setColumns(20);
        txtBiomicroscopia.setLineWrap(true);
        txtBiomicroscopia.setRows(2);
        txtBiomicroscopia.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtBiomicroscopia.setName("Evolución"); // NOI18N
        txtBiomicroscopia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBiomicroscopiaKeyReleased(evt);
            }
        });
        jScrollPane26.setViewportView(txtBiomicroscopia);

        txtFondoDeOjo.setColumns(20);
        txtFondoDeOjo.setLineWrap(true);
        txtFondoDeOjo.setRows(2);
        txtFondoDeOjo.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtFondoDeOjo.setName("Evolución"); // NOI18N
        txtFondoDeOjo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFondoDeOjoKeyReleased(evt);
            }
        });
        jScrollPane27.setViewportView(txtFondoDeOjo);

        btnGuardar.setBackground(new java.awt.Color(0, 153, 153));
        btnGuardar.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardar.setText("GUARDAR ");
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        txtCie1.setEditable(false);
        txtCie1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtCie1.setName("Cie"); // NOI18N
        txtCie1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCie1KeyReleased(evt);
            }
        });

        txtDiagnostico1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtDiagnostico1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDiagnostico1KeyReleased(evt);
            }
        });

        txtDiagnostico2.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtDiagnostico2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiagnostico2ActionPerformed(evt);
            }
        });
        txtDiagnostico2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDiagnostico2KeyReleased(evt);
            }
        });

        txtCie2.setEditable(false);
        txtCie2.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtCie2.setName("Cie"); // NOI18N
        txtCie2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCie2KeyReleased(evt);
            }
        });

        txtDiagnostico3.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtDiagnostico3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDiagnostico3KeyReleased(evt);
            }
        });

        txtCie3.setEditable(false);
        txtCie3.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtCie3.setName("Cie"); // NOI18N
        txtCie3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCie3KeyReleased(evt);
            }
        });

        txtDiagnostico4.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtDiagnostico4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDiagnostico4KeyReleased(evt);
            }
        });

        txtCie4.setEditable(false);
        txtCie4.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtCie4.setName("Cie"); // NOI18N
        txtCie4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCie4KeyReleased(evt);
            }
        });

        lbDireccion60.setFont(new java.awt.Font("Bell MT", 0, 16)); // NOI18N
        lbDireccion60.setText("DIAGNOSTICO:");

        lbDireccion61.setFont(new java.awt.Font("Bell MT", 0, 16)); // NOI18N
        lbDireccion61.setText("2.");

        lbDireccion62.setFont(new java.awt.Font("Bell MT", 0, 16)); // NOI18N
        lbDireccion62.setText("3.");

        lbDireccion63.setFont(new java.awt.Font("Bell MT", 0, 16)); // NOI18N
        lbDireccion63.setText("4.");

        lbDireccion64.setFont(new java.awt.Font("Bell MT", 0, 16)); // NOI18N
        lbDireccion64.setText("5.");

        lbDireccion65.setFont(new java.awt.Font("Bell MT", 0, 16)); // NOI18N
        lbDireccion65.setText("5.");

        lbDireccion66.setFont(new java.awt.Font("Bell MT", 0, 16)); // NOI18N
        lbDireccion66.setText("4.");

        lbDireccion67.setFont(new java.awt.Font("Bell MT", 0, 16)); // NOI18N
        lbDireccion67.setText("3.");

        lbDireccion68.setFont(new java.awt.Font("Bell MT", 0, 16)); // NOI18N
        lbDireccion68.setText("2.");

        lbDireccion69.setFont(new java.awt.Font("Bell MT", 0, 16)); // NOI18N
        lbDireccion69.setText("1.");

        txtCup1.setEditable(false);
        txtCup1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtCup1.setName("Cup"); // NOI18N
        txtCup1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCup1KeyReleased(evt);
            }
        });

        txtCup2.setEditable(false);
        txtCup2.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtCup2.setName("Cup"); // NOI18N
        txtCup2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCup2KeyReleased(evt);
            }
        });

        txtCup3.setEditable(false);
        txtCup3.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtCup3.setName("Cup"); // NOI18N
        txtCup3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCup3KeyReleased(evt);
            }
        });

        txtCup4.setEditable(false);
        txtCup4.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtCup4.setName("Cup"); // NOI18N
        txtCup4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCup4KeyReleased(evt);
            }
        });

        txtProcedimiento1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtProcedimiento1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProcedimiento1KeyReleased(evt);
            }
        });

        txtProcedimiento2.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtProcedimiento2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProcedimiento2KeyReleased(evt);
            }
        });

        txtProcedimiento3.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtProcedimiento3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProcedimiento3KeyReleased(evt);
            }
        });

        txtProcedimiento4.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtProcedimiento4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProcedimiento4KeyReleased(evt);
            }
        });

        btnProximaCita.setBackground(new java.awt.Color(0, 153, 153));
        btnProximaCita.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnProximaCita.setForeground(new java.awt.Color(255, 255, 255));
        btnProximaCita.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnProximaCita.setText("PROXIMA CITA");
        btnProximaCita.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProximaCita.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnProximaCita.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnProximaCita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProximaCitaActionPerformed(evt);
            }
        });

        txtConducta.setColumns(20);
        txtConducta.setLineWrap(true);
        txtConducta.setRows(2);
        txtConducta.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtConducta.setName("Evolución"); // NOI18N
        txtConducta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtConductaKeyReleased(evt);
            }
        });
        jScrollPane28.setViewportView(txtConducta);

        lbDireccion70.setFont(new java.awt.Font("Bell MT", 0, 16)); // NOI18N
        lbDireccion70.setText("PROCEDIMIENTO:");

        txtAnexos.setColumns(20);
        txtAnexos.setLineWrap(true);
        txtAnexos.setRows(2);
        txtAnexos.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtAnexos.setName("Evolución"); // NOI18N
        txtAnexos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAnexosKeyReleased(evt);
            }
        });
        jScrollPane29.setViewportView(txtAnexos);

        lbRazon61.setFont(new java.awt.Font("Bell MT", 0, 16)); // NOI18N
        lbRazon61.setText("ANEXOS:");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnProximaCita)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGuardar))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel10Layout.createSequentialGroup()
                                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lbDireccion60)
                                        .addComponent(lbDireccion70))
                                    .addGap(21, 21, 21)
                                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(lbDireccion57)
                                            .addComponent(lbDireccion61)
                                            .addComponent(lbDireccion62))
                                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(lbDireccion63)
                                            .addComponent(lbDireccion64)
                                            .addComponent(lbDireccion66)
                                            .addComponent(lbDireccion65)
                                            .addComponent(lbDireccion67))
                                        .addComponent(lbDireccion69)))
                                .addComponent(lbRazon59, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbDireccion68, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(lbDireccion58, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbRazon61, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbRazon60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane29, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 582, Short.MAX_VALUE)
                            .addComponent(jScrollPane28)
                            .addComponent(jScrollPane27, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtCup3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                                    .addComponent(txtCup2)
                                    .addComponent(txtCup4, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtCup1, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtCup))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtProcedimiento1, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtProcedimiento)
                                    .addComponent(txtProcedimiento4, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtProcedimiento3)
                                    .addComponent(txtProcedimiento2)))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtCie2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                                    .addComponent(txtCie3, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCie4, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCie1, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCie, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtDiagnostico2)
                                    .addComponent(txtDiagnostico1)
                                    .addComponent(txtDiagnostico, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDiagnostico4)
                                    .addComponent(txtDiagnostico3, javax.swing.GroupLayout.Alignment.LEADING)))
                            .addComponent(jScrollPane26))))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane29)
                    .addComponent(lbRazon61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbRazon59, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane26))
                .addGap(3, 3, 3)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane27)
                    .addComponent(lbRazon60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbDireccion60)
                            .addComponent(txtDiagnostico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addComponent(txtDiagnostico1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(txtDiagnostico2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(txtDiagnostico3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(txtDiagnostico4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbDireccion58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane28))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lbDireccion69)
                                .addComponent(txtCup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtProcedimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbDireccion70))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtProcedimiento1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCup1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbDireccion68))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtCup2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtProcedimiento2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbDireccion67))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addComponent(txtProcedimiento3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtCup4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtProcedimiento4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lbDireccion65)))
                                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtCup3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbDireccion66))))))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(txtCie, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(txtCie1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(txtCie2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(txtCie3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(txtCie4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(lbDireccion57)
                        .addGap(3, 3, 3)
                        .addComponent(lbDireccion61)
                        .addGap(3, 3, 3)
                        .addComponent(lbDireccion62)
                        .addGap(3, 3, 3)
                        .addComponent(lbDireccion63)
                        .addGap(3, 3, 3)
                        .addComponent(lbDireccion64)))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnProximaCita))
                .addGap(10, 10, 10))
        );

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/anterior_1.png"))); // NOI18N
        jLabel10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Volver");
        jLabel13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNit10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                    .addComponent(lbEmail5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel13))
                    .addComponent(lbNit10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addComponent(lbEmail5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane8)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(scrFormulario, javax.swing.GroupLayout.DEFAULT_SIZE, 704, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void listaValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listaValueChanged
        try {
            if (lista.getSelectedIndex() == 0) {
                nodoConsultaPublic = null;
                consultaEditable(true);
                txtOtrasEnfermedadesGenerales.setText("");
                btnGuardar.setEnabled(true);
                btnGuardar1.setEnabled(false);
                txtExamenes.setEnabled(false);
                btnNuevo5.setEnabled(false);
                btnNuevo9.setEnabled(false);
                btnNuevo8.setEnabled(false);
                btnAgregar.setEnabled(false);
                btnSubir.setEnabled(false);
                cmbFecha.setEnabled(false);
                lbFoto4.setEnabled(false);
                tblExamens.setEnabled(false);
                txtEvolucionSeg.setEnabled(false);
                txtBiomicroscopia.setEnabled(false);
                txtAnexos.setEnabled(false);
                txtFondoDeOjo.setEnabled(false);
                btnBiometria.setEnabled(true);
                btnPaquimetria.setEnabled(true);
                btnProximaCita.setVisible(false);
                txtConducta.setEnabled(false);
                txtDiagnostico.setEnabled(false);
                txtDiagnostico1.setEnabled(false);
                txtDiagnostico2.setEnabled(false);
                txtDiagnostico3.setEnabled(false);
                txtDiagnostico4.setEnabled(false);
                txtProcedimiento.setEnabled(false);
                txtProcedimiento1.setEnabled(false);
                txtProcedimiento2.setEnabled(false);
                txtProcedimiento3.setEnabled(false);
                txtProcedimiento4.setEnabled(false);
                txtCodServicioo.setText("");

                ModeloContacto nodo = instancias.getSql().getDatosTercero(txtIdentificacion.getText());

                Object[][] fechas1 = instancias.getSql().getConsultasClinica1(txtIdentificacion.getText());
                Object[] datos1 = new Object[fechas1.length + 1];
                datos1[0] = "HOY - NUEVO";
                int k = fechas1.length;
                int id = 0;

                for (int i = 0; i < fechas1.length; i++) {
                    datos1[k--] = fechas1[i][0] + " - " + metodos.fecha(fechas1[i][1].toString());
                    if (Integer.parseInt(fechas1[i][0].toString()) > id) {
                        id = Integer.parseInt(fechas1[i][0].toString());
                    }
                }

                if (id != 0) {
                    ndHojaIngreso nodoConsulta = instancias.getSql().getDatosHojaIngreso(Integer.toString(id));
                    if (nodo.getId() != null) {

                        txtAP.setText(nodoConsulta.getEnfermedades());
                        String texto1 = txtAP.getText();
                        if (texto1.contains("Hipertensión")) {
                            btnHipertension.setEnabled(false);
                        }
                        if (texto1.contains("Diabetes")) {
                            btnDiabetes.setEnabled(false);
                        }
                        if (texto1.contains("Artritis")) {
                            btnArtritis.setEnabled(false);
                        }
                        if (texto1.contains("Ulcera gástrica")) {
                            btnUlceraGastrica.setEnabled(false);
                        }
                        if (texto1.contains("Alergias")) {
                            btnAlergias.setEnabled(false);
                        }
                        txtOtrasEnfermedades.setText(nodoConsulta.getOtrasEnfermedades());
                        txtLentes.setText(nodoConsulta.getLentes());
                        txtAP1.setText(nodoConsulta.getEnfermedades1());
                        String texto2 = txtAP1.getText();
                        if (texto2.contains("Hipertensión")) {
                            btnHipertension1.setEnabled(false);
                        }
                        if (texto2.contains("Diabetes ")) {
                            btnDiabetes1.setEnabled(false);
                        }
                        if (texto2.contains("Artritis")) {
                            btnArtritis1.setEnabled(false);
                        }
                        if (texto2.contains("Estrabismo")) {
                            btnEstrabismo.setEnabled(false);
                        }
                        if (texto2.contains("Cataratas")) {
                            btnCataratas.setEnabled(false);
                        }
                        if (texto2.contains("Glaucoma")) {
                            btnGlaucoma.setEnabled(false);
                        }
                        txtLentes1.setText(nodoConsulta.getLentes1());
                        txtAlergias1.setText(nodoConsulta.getAlergias());
                        txtOtrasEnfermedadesOculares.setText(nodoConsulta.getOtrasEnfermedadesOculares());
                        txtOtrasEnfermedadesGenerales.setText(nodoConsulta.getOtrasEnfermadesdeGenerales());
//                        txtClOD.setText(nodoConsulta.getClOD());
//                        txtClOI.setText(nodoConsulta.getClOI());
//                        txtAvOD.setText(nodoConsulta.getAvOD());
//                        txtAvOI.setText(nodoConsulta.getAvOI());
                    }
                }
            } else if (agenda && lista.getSelectedIndex() != 0) {
                consultaEditable(false);

                if (!lista.getSelectedValue().toString().equals(null) || !lista.getSelectedValue().toString().equals("")) {

                    ndHojaIngreso nodo = instancias.getSql().getDatosHojaIngreso(lista.getSelectedValue().toString().split(" - ")[0]);
                    nodoConsultaPublic = nodo;
                    if (nodo.getId() != null) {
                        txtDepartamento.setText(nodo.getDepartamento());
                        txtMunicipio.setText(nodo.getMunicipio());
                        txtZona.setText(nodo.getZona());
                        txtDireccion.setText(nodo.getDireccion());
                        txtTelefono.setText(nodo.getTelefono());
                        txtResponsable.setText(nodo.getResponsable());
                        txtTelefono1.setText(nodo.getTelefono1());
                        cmbParentesco.setSelectedItem(nodo.getParentesco());
                        txtAP.setText(nodo.getEnfermedades());
                        txtOtrasEnfermedades.setText(nodo.getOtrasEnfermedades());
                        txtLentes.setText(nodo.getLentes());
                        txtAP1.setText(nodo.getEnfermedades1());
                        txtLentes1.setText(nodo.getLentes1());
                        txtAlergias1.setText(nodo.getAlergias());
                        txtOtrasEnfermedadesOculares.setText(nodo.getOtrasEnfermedadesOculares());
                        txtOtrasEnfermedadesGenerales.setText(nodo.getOtrasEnfermadesdeGenerales());
                        txtRefraccionOD.setText(nodo.getRefraccionOD());
                        txtRefraccionOI.setText(nodo.getRefraccionOI());
                        txtClOD.setText(nodo.getClOD());
                        txtClOI.setText(nodo.getClOI());
                        txtRefSubjetivoOD.setText(nodo.getRefSubjetivoOD());
                        txtRefSubjetivoOI.setText(nodo.getRefSubjetivoOI());
                        txtAvOD.setText(nodo.getAvOD());
                        txtAvOI.setText(nodo.getAvOI());
                        txtForias.setText(nodo.getForias());
                        txtPupilas.setText(nodo.getPupilas());
                        txtMc.setText(nodo.getMc());
                        txtTonometriaOD.setText(nodo.getTonometriaOD());
                        txtTonometriaOI.setText(nodo.getTonometriaOI());
                        txtBiomicroscopia.setText(nodo.getBiomicroscopia());
                        txtAnexos.setText(nodo.getAnexos());
                        txtFondoDeOjo.setText(nodo.getFondoDeOjo());
                        txtCodServicioo.setText(nodo.getServicio());
                        txtConducta.setText(nodo.getConducta());

                        txtConducta.setEnabled(false);
                        txtBiomicroscopia.setEnabled(false);
                        txtAnexos.setEnabled(false);
                        txtFondoDeOjo.setEnabled(false);
                        txtDiagnostico.setEnabled(false);
                        txtDiagnostico1.setEnabled(false);
                        txtDiagnostico2.setEnabled(false);
                        txtDiagnostico3.setEnabled(false);
                        txtDiagnostico4.setEnabled(false);
                        txtProcedimiento.setEnabled(false);
                        txtProcedimiento1.setEnabled(false);
                        txtProcedimiento2.setEnabled(false);
                        txtProcedimiento3.setEnabled(false);
                        btnProximaCita.setVisible(false);
                        btnBiometria.setEnabled(true);
                        btnPaquimetria.setEnabled(true);
                        txtProcedimiento4.setEnabled(false);

                        ndProducto producto = instancias.getSql().getDatosProducto(txtCodServicioo.getText(), "bdProductos");
                        txtCodProducto.setText(producto.getDescripcion());

                        if (txtRefraccionOD.getText().equals("")) {
                            txtRefraccionOD.setEnabled(true);
                            txtRefraccionOI.setEnabled(true);
                            txtRefSubjetivoOD.setEnabled(true);
                            txtRefSubjetivoOI.setEnabled(true);
                            txtTonometriaOD.setEnabled(true);
                            txtTonometriaOI.setEnabled(true);
                        } else {
                            txtRefraccionOD.setEnabled(false);
                            txtRefraccionOI.setEnabled(false);
                            txtRefSubjetivoOD.setEnabled(false);
                            txtRefSubjetivoOI.setEnabled(false);
                            txtTonometriaOD.setEnabled(false);
                            txtTonometriaOI.setEnabled(false);
                        }
                    }
                }
//            } else if (lista.getSelectedIndex() != 0) {
            } else {
                consultaEditable(false);

                if (!lista.getSelectedValue().toString().equals(null) || !lista.getSelectedValue().toString().equals("")) {

                    ndHojaIngreso nodo = instancias.getSql().getDatosHojaIngreso(lista.getSelectedValue().toString().split(" - ")[0]);
                    nodoConsultaPublic = nodo;
                    if (nodo.getId() != null) {
                        txtDepartamento.setText(nodo.getDepartamento());
                        txtMunicipio.setText(nodo.getMunicipio());
                        txtZona.setText(nodo.getZona());
                        txtDireccion.setText(nodo.getDireccion());
                        txtTelefono.setText(nodo.getTelefono());
                        txtResponsable.setText(nodo.getResponsable());
                        txtTelefono1.setText(nodo.getTelefono1());
                        cmbParentesco.setSelectedItem(nodo.getParentesco());
                        txtAP.setText(nodo.getEnfermedades());
                        txtOtrasEnfermedades.setText(nodo.getOtrasEnfermedades());
                        txtLentes.setText(nodo.getLentes());
                        txtAP1.setText(nodo.getEnfermedades1());
                        txtLentes1.setText(nodo.getLentes1());
                        txtAlergias1.setText(nodo.getAlergias());
                        txtOtrasEnfermedadesOculares.setText(nodo.getOtrasEnfermedadesOculares());
                        txtOtrasEnfermedadesGenerales.setText(nodo.getOtrasEnfermadesdeGenerales());
                        txtRefraccionOD.setText(nodo.getRefraccionOD());
                        txtRefraccionOI.setText(nodo.getRefraccionOI());
                        txtClOD.setText(nodo.getClOD());
                        txtClOI.setText(nodo.getClOI());
                        txtRefSubjetivoOD.setText(nodo.getRefSubjetivoOD());
                        txtRefSubjetivoOI.setText(nodo.getRefSubjetivoOI());
                        txtAvOD.setText(nodo.getAvOD());
                        txtAvOI.setText(nodo.getAvOI());
                        txtConducta.setText(nodo.getConducta());
                        txtForias.setText(nodo.getForias());
                        txtPupilas.setText(nodo.getPupilas());
                        txtMc.setText(nodo.getMc());
                        txtTonometriaOD.setText(nodo.getTonometriaOD());
                        txtTonometriaOI.setText(nodo.getTonometriaOI());
                        txtBiomicroscopia.setText(nodo.getBiomicroscopia());
                        txtAnexos.setText(nodo.getAnexos());
                        txtFondoDeOjo.setText(nodo.getFondoDeOjo());
                        txtCodServicioo.setText(nodo.getServicio());
                        txtBiomicroscopia.setEnabled(true);
                        txtAnexos.setEnabled(true);
                        txtFondoDeOjo.setEnabled(true);
                        txtConducta.setEnabled(true);
                        txtDiagnostico.setEnabled(true);
                        txtDiagnostico1.setEnabled(true);
                        txtDiagnostico2.setEnabled(true);
                        txtDiagnostico3.setEnabled(true);
                        txtDiagnostico4.setEnabled(true);
                        txtProcedimiento.setEnabled(true);
                        txtProcedimiento1.setEnabled(true);
                        txtProcedimiento2.setEnabled(true);
                        txtProcedimiento3.setEnabled(true);
                        btnProximaCita.setVisible(true);
                        btnBiometria.setEnabled(false);
                        btnPaquimetria.setEnabled(false);
                        txtProcedimiento4.setEnabled(true);
                        txtCie.setText(nodo.getCie());
                        txtCie1.setText(nodo.getCie1());
                        txtCie2.setText(nodo.getCie2());
                        txtCie3.setText(nodo.getCie3());
                        txtCie4.setText(nodo.getCie4());
                        txtCup.setText(nodo.getCup());
                        txtCup1.setText(nodo.getCup1());
                        txtCup2.setText(nodo.getCup2());
                        txtCup3.setText(nodo.getCup3());
                        txtCup4.setText(nodo.getCup4());
                        ndProducto producto = instancias.getSql().getDatosProducto(txtCodServicioo.getText(), "bdProductos");
                        txtCodProducto.setText(producto.getDescripcion());

                        int i = 0;

                        if (!txtCie.getText().equals("")) {
                            txtDiagnostico.setText(instancias.getSql().getNombreCie(txtCie.getText()));
                            i++;
                        }

                        if (!txtCie1.getText().equals("")) {
                            txtDiagnostico1.setText(instancias.getSql().getNombreCie(txtCie1.getText()));
                            i++;
                        }

                        if (!txtCie2.getText().equals("")) {
                            txtDiagnostico2.setText(instancias.getSql().getNombreCie(txtCie2.getText()));
                            i++;
                        }

                        if (!txtCie3.getText().equals("")) {
                            txtDiagnostico3.setText(instancias.getSql().getNombreCie(txtCie3.getText()));
                            i++;
                        }

                        if (!txtCie4.getText().equals("")) {
                            txtDiagnostico4.setText(instancias.getSql().getNombreCie(txtCie4.getText()));
                            i++;
                        }

                        if (!txtCup.getText().equals("")) {
                            txtProcedimiento.setText(instancias.getSql().getNombreCup(txtCup.getText()));
                            i++;
                        }

                        if (!txtCup1.getText().equals("")) {
                            txtProcedimiento1.setText(instancias.getSql().getNombreCup(txtCup1.getText()));
                            i++;
                        }
                        if (!txtCup2.getText().equals("")) {
                            txtProcedimiento2.setText(instancias.getSql().getNombreCup(txtCup2.getText()));
                            i++;
                        }
                        if (!txtCup3.getText().equals("")) {
                            txtProcedimiento3.setText(instancias.getSql().getNombreCup(txtCup3.getText()));
                            i++;
                        }
                        if (!txtCup4.getText().equals("")) {
                            txtProcedimiento4.setText(instancias.getSql().getNombreCup(txtCup4.getText()));
                            i++;
                        }

                        btnGuardar1.setEnabled(true);
                        txtExamenes.setEnabled(true);
                        btnNuevo5.setEnabled(true);
                        btnNuevo9.setEnabled(true);
                        btnNuevo8.setEnabled(true);
                        btnAgregar.setEnabled(true);
                        btnSubir.setEnabled(true);
                        cmbFecha.setEnabled(true);
                        lbFoto4.setEnabled(true);
                        tblExamens.setEnabled(true);
                        txtEvolucionSeg.setEnabled(true);

                        if (txtTonometriaOD.getText().equals("")) {
                            txtTonometriaOD.setEnabled(true);
                            txtTonometriaOI.setEnabled(true);
                        } else {
                            txtTonometriaOD.setEnabled(false);
                            txtTonometriaOI.setEnabled(false);
                        }

                        if (i != 0) {
                            btnGuardar.setEnabled(false);
                            txtBiomicroscopia.setEnabled(false);
                            txtAnexos.setEnabled(false);
                            txtConducta.setEnabled(false);
                            txtProcedimiento.setEnabled(false);
                            txtProcedimiento1.setEnabled(false);
                            txtProcedimiento2.setEnabled(false);
                            txtProcedimiento4.setEnabled(false);
                            txtProcedimiento3.setEnabled(false);
                            txtFondoDeOjo.setEnabled(false);
                            txtDiagnostico.setEnabled(false);
                            txtDiagnostico1.setEnabled(false);
                            txtDiagnostico2.setEnabled(false);
                            txtDiagnostico3.setEnabled(false);
                            txtDiagnostico4.setEnabled(false);
                            btnProximaCita.setVisible(false);
                        }
                    }
                }
            }
        } catch (Exception e) {
            lista.setSelectedIndex(0);
            consultaEditable(true);
            txtBiomicroscopia.setEnabled(false);
            txtAnexos.setEnabled(false);
            txtConducta.setEnabled(false);
            txtFondoDeOjo.setEnabled(false);
            btnProximaCita.setVisible(false);
            txtDiagnostico.setEnabled(false);
            txtDiagnostico1.setEnabled(false);
            txtDiagnostico2.setEnabled(false);
            txtDiagnostico3.setEnabled(false);
            txtDiagnostico4.setEnabled(false);
            txtProcedimiento.setEnabled(false);
            txtProcedimiento1.setEnabled(false);
            txtProcedimiento2.setEnabled(false);
            txtProcedimiento3.setEnabled(false);
            txtProcedimiento4.setEnabled(false);
        }
    }//GEN-LAST:event_listaValueChanged

    private void btnGlaucomaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGlaucomaActionPerformed
        txtAP1.setText(txtAP1.getText() + "Glaucoma, ");
        btnGlaucoma.setEnabled(false);
        btnGlaucoma.setForeground(new java.awt.Color(255, 0, 0));
    }//GEN-LAST:event_btnGlaucomaActionPerformed

    private void btnCataratasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCataratasActionPerformed
        txtAP1.setText(txtAP1.getText() + "Cataratas, ");
        btnCataratas.setEnabled(false);
        btnCataratas.setForeground(new java.awt.Color(255, 0, 0));
    }//GEN-LAST:event_btnCataratasActionPerformed

    private void btnEstrabismoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEstrabismoActionPerformed
        txtAP1.setText(txtAP1.getText() + "Estrabismo, ");
        btnEstrabismo.setEnabled(false);
        btnEstrabismo.setForeground(new java.awt.Color(255, 0, 0));
    }//GEN-LAST:event_btnEstrabismoActionPerformed

    private void btnArtritis1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArtritis1ActionPerformed
        txtAP1.setText(txtAP1.getText() + "Artritis, ");
        btnArtritis1.setEnabled(false);
        btnArtritis1.setForeground(new java.awt.Color(255, 0, 0));
    }//GEN-LAST:event_btnArtritis1ActionPerformed

    private void btnDiabetes1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDiabetes1ActionPerformed
        txtAP1.setText(txtAP1.getText() + "Diabetes, ");
        btnDiabetes1.setEnabled(false);
        btnDiabetes1.setForeground(new java.awt.Color(255, 0, 0));
    }//GEN-LAST:event_btnDiabetes1ActionPerformed

    private void btnHipertension1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHipertension1ActionPerformed
        txtAP1.setText(txtAP1.getText() + "Hipertensión, ");
        btnHipertension1.setEnabled(false);
        btnHipertension1.setForeground(new java.awt.Color(255, 0, 0));
    }//GEN-LAST:event_btnHipertension1ActionPerformed

    private void btnAlergiasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlergiasActionPerformed
        txtAP.setText(txtAP.getText() + "Alergias, ");

        btnAlergias.setForeground(new java.awt.Color(255, 0, 0));
        btnAlergias.setEnabled(false);
    }//GEN-LAST:event_btnAlergiasActionPerformed

    private void btnHipertensionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHipertensionActionPerformed
        txtAP.setText(txtAP.getText() + "Hipertensión, ");
        btnHipertension.setEnabled(false);
        btnHipertension.setForeground(new java.awt.Color(255, 0, 0));
    }//GEN-LAST:event_btnHipertensionActionPerformed

    private void btnArtritisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArtritisActionPerformed
        txtAP.setText(txtAP.getText() + "Artritis, ");
        btnArtritis.setEnabled(false);
        btnArtritis.setForeground(new java.awt.Color(255, 0, 0));
    }//GEN-LAST:event_btnArtritisActionPerformed

    private void btnUlceraGastricaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUlceraGastricaActionPerformed
        txtAP.setText(txtAP.getText() + "Ulcera gástrica, ");
        btnUlceraGastrica.setEnabled(false);
        btnUlceraGastrica.setForeground(new java.awt.Color(255, 0, 0));
    }//GEN-LAST:event_btnUlceraGastricaActionPerformed

    private void btnDiabetesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDiabetesActionPerformed
        txtAP.setText(txtAP.getText() + "Diabetes, ");
        btnDiabetes.setEnabled(false);
        btnDiabetes.setForeground(new java.awt.Color(255, 0, 0));
    }//GEN-LAST:event_btnDiabetesActionPerformed

    private void txtResponsableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtResponsableActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtResponsableActionPerformed

    private void btnReimprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReimprimirActionPerformed
        if (lista.getSelectedIndex() == 0) {
            metodos.msgError(this, "Seleccione una consulta");
            return;
        }

        ModeloContacto Paciente = instancias.getSql().getDatosTercero(txtIdentificacion.getText());
        System.out.println(lista.getSelectedValue().toString().split(" - ")[0]);
        instancias.getReporte().ver_HojaIngreso(lista.getSelectedValue().toString().split(" - ")[0], false);
    }//GEN-LAST:event_btnReimprimirActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (agenda && lista.getSelectedIndex() != 0) {
            ndHojaIngreso nodo = instancias.getSql().getDatosHojaIngreso(lista.getSelectedValue().toString().split(" - ")[0]);
            nodoConsultaPublic = nodo;

            nodoConsultaPublic.setRefraccionOD(txtRefraccionOD.getText());
            nodoConsultaPublic.setRefraccionOI(txtRefraccionOI.getText());
            nodoConsultaPublic.setRefSubjetivoOD(txtRefSubjetivoOD.getText());
            nodoConsultaPublic.setRefSubjetivoOI(txtRefSubjetivoOI.getText());
            nodoConsultaPublic.setTonometriaOD(txtTonometriaOD.getText());
            nodoConsultaPublic.setTonometriaOI(txtTonometriaOI.getText());

            Object[] vector = {nodoConsultaPublic};

//                ndHojaIngreso nodo = metodos.llenarHojaIngreso(vector);
            if (!instancias.getSql().modificarHojaIngreso(nodoConsultaPublic)) {
                metodos.msgError(this, "Hubo un problema al guardar la consulta");
                return;
            } else {
                metodos.msgExito(this, "Cambios guardados con exito");
            }
        } else if (lista.getSelectedIndex() != 0) {

            Object[] campos = {txtIdentificacion, txtFechaIngreso, txtCie, txtCup};
            String faltantes = metodos.camposVacios(campos);

            txtBiomicroscopia.setEnabled(true);
            txtAnexos.setEnabled(true);
            txtConducta.setEnabled(true);
            txtFondoDeOjo.setEnabled(true);
            txtDiagnostico.setEnabled(true);
            txtDiagnostico1.setEnabled(true);
            txtDiagnostico2.setEnabled(true);
            txtDiagnostico3.setEnabled(true);
            txtDiagnostico4.setEnabled(true);
            txtProcedimiento.setEnabled(true);
            txtProcedimiento1.setEnabled(true);
            txtProcedimiento2.setEnabled(true);
            txtProcedimiento3.setEnabled(true);
            txtProcedimiento4.setEnabled(true);
            btnGuardar1.setEnabled(true);
            txtExamenes.setEnabled(true);
            btnNuevo5.setEnabled(true);
            btnNuevo9.setEnabled(true);
            btnNuevo8.setEnabled(true);
            btnAgregar.setEnabled(true);
            btnSubir.setEnabled(true);
            cmbFecha.setEnabled(true);
            lbFoto4.setEnabled(true);
            tblExamens.setEnabled(true);
            txtEvolucionSeg.setEnabled(true);

            if (!faltantes.equals("")) {
                metodos.msgAdvertencia(this, "No puede continuar, faltan los siguientes campos: " + faltantes);
                return;
            }

            if (metodos.msgPregunta(this, "¿Desea continuar?") == 0) {

                if (!instancias.isGeneraOrdenMedica()) {
                    guardarOrden();
                }

                nodoConsultaPublic.setConducta(txtConducta.getText());
                nodoConsultaPublic.setAnexos(txtAnexos.getText());
                nodoConsultaPublic.setTonometriaOD(txtTonometriaOD.getText());
                nodoConsultaPublic.setTonometriaOI(txtTonometriaOI.getText());
                nodoConsultaPublic.setBiomicroscopia(txtBiomicroscopia.getText());
                nodoConsultaPublic.setFondoDeOjo(txtFondoDeOjo.getText());
                nodoConsultaPublic.setCie(txtCie.getText());
                nodoConsultaPublic.setCup(txtCup.getText());
                nodoConsultaPublic.setOrden(orden);
                nodoConsultaPublic.setCie1(txtCie1.getText());
                nodoConsultaPublic.setCie2(txtCie2.getText());
                nodoConsultaPublic.setCie3(txtCie3.getText());
                nodoConsultaPublic.setCie4(txtCie4.getText());
                nodoConsultaPublic.setCup1(txtCup1.getText());
                nodoConsultaPublic.setCup2(txtCup2.getText());
                nodoConsultaPublic.setCup3(txtCup3.getText());
                nodoConsultaPublic.setCup4(txtCup4.getText());

                Object[] vector = {nodoConsultaPublic};

//                ndHojaIngreso nodo = metodos.llenarHojaIngreso(vector);
                if (!instancias.getSql().modificarHojaIngreso(nodoConsultaPublic)) {
                    metodos.msgError(this, "Hubo un problema al guardar la consulta");
                    return;
                }

                metodos.msgExito(this, "Historia guardada con exito");

                if (facturarOrden) {
                    facturar(txtCodServicioo.getText());
                }

                facturarOrden = false;
                productoFacturarOrden = "";

                String cliente = txtIdentificacion.getText();
                String cita = "";

                try {
                    cita = instancias.getSql().getAgendasDelDia(cliente, metodos.fechaConsulta(metodosGenerales.fecha()));
                } catch (Exception e) {
                }

                if (!cita.equalsIgnoreCase("")) {
                    if (!instancias.getSql().modificarCita(cita, "Atendido")) {
                        metodos.msgError(null, "No fue posible modificar el estado de la Cita");
                    }
                }

                instancias.getInfoAgenda().setDoctor(false);
                agenda = false;
                btnLimpiarActionPerformed(evt);
            }
        } else {
            Object[] campos = {txtIdentificacion, txtFechaIngreso, txtCodServicioo};
            String faltantes = metodos.camposVacios(campos);

            Object[] campos2 = {txtResponsable, txtTelefono1, cmbParentesco};
            String faltantes2 = metodos.camposVacios(campos2);

            if (!faltantes.equals("")) {
                metodos.msgAdvertencia(this, "No puede continuar, faltan los siguientes campos: " + faltantes);
                return;
            }

            if (!faltantes2.equals("")) {
                metodos.msgAdvertencia(this, "Faltan los siguientes campos: " + faltantes2);
            }

            if (metodos.msgPregunta(this, "¿Desea continuar?") == 0) {
                int i = 0;
//                String consecutivo = "";
//                if (lista.getSelectedIndex() == 0) {
//                    if (!instancias.isGeneraOrdenMedica()) {
//                        guardarOrden();
//                    }
//                }
                String consulta = instancias.getSql().getNumConsecutivo("CONSULTAOF")[0].toString();

                BigDecimal edad;
                if (txtEdad.getText().equals("")) {
                    edad = big.getBigDecimal(0);
                } else {
                    edad = big.getBigDecimal(txtEdad.getText());
                }

//                consecutivo = consulta;
                System.out.println("people " + txtIdentificacion.getText());
                ndHistoriaClinica history = instancias.getSql().getDatosHistoriaClinica(txtIdentificacion.getText());

                System.out.println("dsfa " + history.getId());
                String historia = history.getId();

                System.out.println("historiaaaaaaaaaaa " + historia);
                try {
                    if (historia.equals("") || historia == null) {
                        historia = "";
                    }
                } catch (Exception e) {
                    historia = "";
                }

                Object[] vector = {consulta, historia, txtFechaIngreso.getText(), txtIdentificacion.getText(), txtPrimeroApellido.getText(),
                    txtSegundoApellido.getText(), txtPrimerNombre.getText(), txtSegundoNombre.getText(), txtFechaNacimiento.getText(), edad,
                    txtSexo.getText(), txtOcupacion.getText(), txtEstadoCivil.getText(), txtDepartamento.getText(), txtMunicipio.getText(), txtZona.getText(),
                    txtDireccion.getText(), txtTelefono.getText(), txtResponsable.getText(), txtTelefono1.getText(), cmbParentesco.getSelectedItem(),
                    txtAP.getText(), txtOtrasEnfermedades.getText(), txtLentes.getText(), txtAP1.getText(), txtLentes1.getText(), txtAlergias1.getText(),
                    txtOtrasEnfermedadesOculares.getText(), txtOtrasEnfermedadesOculares.getText(), txtRefraccionOD.getText(), txtRefraccionOI.getText(),
                    txtClOD.getText(), txtClOI.getText(), txtRefSubjetivoOD.getText(), txtRefSubjetivoOI.getText(), txtAvOD.getText(), txtAvOI.getText(),
                    txtForias.getText(), txtPupilas.getText(), txtMc.getText(), txtTonometriaOD.getText(), txtTonometriaOI.getText(), "",
                    "", "", "", "", "", "", "", txtCodServicioo.getText(), "", "", "", "", "", "", "", "", metodosGenerales.hora(), "", ""};

                ndHojaIngreso nodo = metodos.llenarHojaIngreso(vector);

                if (!instancias.getSql().agregarHojaIngreso(nodo)) {
                    metodos.msgError(this, "Hubo un problema al guardar la consulta");
                    return;
                }

                i++;

                String cita = "";
                String cliente = txtIdentificacion.getText();

                try {
                    cita = instancias.getSql().getAgendasDelDia(cliente, metodos.fechaConsulta(metodosGenerales.fecha()));
                    if (!instancias.getSql().modificarCita(cita, "Confirmada")) {
                        metodos.msgError(null, "No fue posible modificar el estado de la Cita");
                    }
                } catch (Exception e) {
                }

                if (i > 0) {
                    metodos.msgExito(this, "Historia guardada con exito");
                }

//                System.out.println("jaja " + productoFacturarOrden);
//
//                if (facturarOrden) {
//                    facturar(txtCodServicioo.getText());
//                }
                facturarOrden = false;
                productoFacturarOrden = "";

//                instancias.getReporte().ver_HojaIngreso(consecutivo, false);
                if (!instancias.getSql().aumentarConsecutivo("CONSULTAOF", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("CONSULTAOF")[0]) + 1)) {
                    metodos.msgError(this, "Hubo un problema al guardar en el consecutivo de la consulta");
                    return;
                }
                btnLimpiarActionPerformed(evt);
            }
        }

        try {
            instancias.getAgendaConsulta().cargarAgendas(instancias.getMedico());
            instancias.getAgendaConsulta().cargarAgendas();
            instancias.getAgendaConsulta().setSelected(true);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnBuscTercerosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscTercerosActionPerformed
        ventanaTerceros("");
    }//GEN-LAST:event_btnBuscTercerosActionPerformed

    private void txtIdentificacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdentificacionKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarCliente(txtIdentificacion.getText());
        }

        if (txtIdentificacion.getText().equals("")) {
            btnLimpiarActionPerformed(null);
        }
    }//GEN-LAST:event_txtIdentificacionKeyReleased

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        txtCodProducto.setText("");
        txtCodServicioo.setText("");
        txtConducta.setText("");

        limpiarExamenes(tblExamens);
        lbFoto4.setIcon(null);

        for (int x = 0; x < jPanel7.getComponentCount(); x++) {
            if (jPanel7.getComponent(x) instanceof JTextField) {
                JTextField textField = (JTextField) jPanel7.getComponent(x);
                textField.setText("");
            }
        }

        for (int x = 0; x < jPanel10.getComponentCount(); x++) {
            if (jPanel10.getComponent(x) instanceof JTextField) {
                JTextField textField = (JTextField) jPanel10.getComponent(x);
                textField.setText("");
            }
        }

        for (int x = 0; x < jPanel1.getComponentCount(); x++) {
            if (jPanel1.getComponent(x) instanceof JTextField) {
                JTextField textField = (JTextField) jPanel1.getComponent(x);
                textField.setText("");
            }
        }

        for (int x = 0; x < jPanel4.getComponentCount(); x++) {
            if (jPanel4.getComponent(x) instanceof JTextField) {
                JTextField textField = (JTextField) jPanel4.getComponent(x);
                textField.setText("");
            }
        }

        for (int x = 0; x < jPanel5.getComponentCount(); x++) {
            if (jPanel5.getComponent(x) instanceof JTextField) {
                JTextField textField = (JTextField) jPanel5.getComponent(x);
                textField.setText("");
            }
        }

        for (int x = 0; x < jPanel6.getComponentCount(); x++) {
            if (jPanel6.getComponent(x) instanceof JTextField) {
                JTextField textField = (JTextField) jPanel6.getComponent(x);
                textField.setText("");
            }
        }
        cmbParentesco.setSelectedIndex(0);
        txtOtrasEnfermedades.setText("");
        txtOtrasEnfermedadesOculares.setText("");
        txtOtrasEnfermedadesOculares.setText("");
        txtFechaIngreso.setText(metodosGenerales.fecha());
        btnHipertension.setForeground(new java.awt.Color(0, 0, 0));
        btnDiabetes.setForeground(new java.awt.Color(0, 0, 0));
        btnArtritis.setForeground(new java.awt.Color(0, 0, 0));
        btnUlceraGastrica.setForeground(new java.awt.Color(0, 0, 0));
        btnAlergias.setForeground(new java.awt.Color(0, 0, 0));
        btnHipertension1.setForeground(new java.awt.Color(0, 0, 0));
        btnDiabetes1.setForeground(new java.awt.Color(0, 0, 0));
        btnArtritis1.setForeground(new java.awt.Color(0, 0, 0));
        btnEstrabismo.setForeground(new java.awt.Color(0, 0, 0));
        btnCataratas.setForeground(new java.awt.Color(0, 0, 0));
        btnGlaucoma.setForeground(new java.awt.Color(0, 0, 0));

        btnHipertension.setEnabled(true);
        btnDiabetes.setEnabled(true);
        btnArtritis.setEnabled(true);
        btnUlceraGastrica.setEnabled(true);
        btnAlergias.setEnabled(true);
        btnHipertension1.setEnabled(true);
        btnDiabetes1.setEnabled(true);
        btnArtritis1.setEnabled(true);
        btnEstrabismo.setEnabled(true);
        btnCataratas.setEnabled(true);
        btnGlaucoma.setEnabled(true);

//        txtProfesional.setText(instancias.getUsuario());
        txtProfesional.setText(instancias.getUsuarioLog().getNombre());
        Object[] datos = new Object[]{"HOY - NUEVO"};
        lista.setListData(datos);
        lista.setSelectedIndex(0);

    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void txtCupKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCupKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                txtProcedimiento.setText(instancias.getSql().getNombreCup(txtCup.getText()));
            } catch (Exception e) {
                txtProcedimiento.setText("");
            }
        }
    }//GEN-LAST:event_txtCupKeyReleased

    private void txtDiagnosticoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiagnosticoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ventanaCie(txtDiagnostico.getText(), txtDiagnostico);
        } else {
            txtCie.setText("");
        }
    }//GEN-LAST:event_txtDiagnosticoKeyReleased

    private void txtProcedimientoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProcedimientoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ventanaCup(txtProcedimiento.getText());
        } else {
            txtCup.setText("");
        }
    }//GEN-LAST:event_txtProcedimientoKeyReleased

    private void txtCieKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCieKeyReleased
        txtDiagnostico.setText(instancias.getSql().getNombreCie(txtCie.getText()));
    }//GEN-LAST:event_txtCieKeyReleased

    private void listaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaMouseClicked

    }//GEN-LAST:event_listaMouseClicked

    private void btnHipertensionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHipertensionMouseClicked

    }//GEN-LAST:event_btnHipertensionMouseClicked

    private void txtIdentificacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdentificacionKeyTyped
        if (evt.getKeyCode() == KeyEvent.VK_DELETE || !txtPrimerNombre.getText().equals("")) {
            String code = txtIdentificacion.getText();
            btnLimpiarActionPerformed(null);
            txtIdentificacion.setText(code.substring(0, code.length()));
            txtIdentificacion.requestFocus();
        }

        txtIdentificacion.requestFocus();
    }//GEN-LAST:event_txtIdentificacionKeyTyped

    private void txtTonometriaOIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTonometriaOIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTonometriaOIActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (lista.getSelectedIndex() == 0) {
            metodos.msgAdvertencia(this, "Debe de generar una consulta asociada");
            return;
        }

        String resp = lista.getSelectedValue().toString().split(" - ")[0];
        ndHojaIngreso nodo = instancias.getSql().getDatosHojaIngreso(resp);

        System.out.println("");

        instancias.getAyudaDiagnosticoOf().nuevaAyuda(nodo.getHistoria(), txtIdentificacion.getText(), lista.getSelectedValue().toString().split(" - ")[0]);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (lista.getSelectedIndex() == 0) {
            metodos.msgAdvertencia(this, "Debe de generar una consulta asociada");
            return;
        }

        String resp = lista.getSelectedValue().toString().split(" - ")[0];
        ndHojaIngreso nodo = instancias.getSql().getDatosHojaIngreso(resp);
        System.out.println("nodo . " + nodo.getHistoria());
        instancias.getFormulaMedicaOf().nuevaFormula(nodo.getHistoria(), txtIdentificacion.getText(), lista.getSelectedValue().toString().split(" - ")[0]);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (lista.getSelectedIndex() == 0) {
            metodos.msgAdvertencia(this, "Debe de generar una consulta asociada");
            return;
        }

        String resp = lista.getSelectedValue().toString().split(" - ")[0];
        ndHojaIngreso nodo = instancias.getSql().getDatosHojaIngreso(resp);
        instancias.getIncapacidadOf().nuevaIncapacidad(nodo.getHistoria(), txtCup.getText(), txtProcedimiento.getText(), txtCie.getText(), txtDiagnostico.getText(), txtIdentificacion.getText(), resp);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txtAlergias1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAlergias1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAlergias1ActionPerformed

    private void cmbParentescoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbParentescoKeyReleased

    }//GEN-LAST:event_cmbParentescoKeyReleased

    private void txtProfesionalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProfesionalKeyReleased

    }//GEN-LAST:event_txtProfesionalKeyReleased

    private void txtProfesionalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProfesionalKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProfesionalKeyTyped

    private void cmbFechaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbFechaItemStateChanged
        try {
            if (cmbFecha.getSelectedItem().equals("HOY - NUEVO")) {
                seguimientoEditable(true);
                lbCodFoto.setText("");
            } else {

                seguimientoEditable(false);

                ndSeguimientoClinicaOf nodo = instancias.getSql().getDatosSeguimientoClinicaOf(cmbFecha.getSelectedItem().toString().split(" - ")[0]);

                txtProfesional.setText(instancias.getSql().getDatosUsuario(nodo.getUsuario()).getNombre());
                txtEvolucionSeg.setText(nodo.getEvolucion());
                lbCodFoto.setText(nodo.getFotos());

                if (!nodo.getExamen().equals("")) {
                    String[] examen = nodo.getExamen().split("&");
                    DefaultTableModel modeloTabla = (DefaultTableModel) tblExamens.getModel();
                    for (int i = 0; i < examen.length; i++) {
                        modeloTabla.addRow(new Object[]{examen[i].split(";")[0], examen[i].split(";")[1]});
                    }
                }

                try {
                    actual = 0;
                    cambiarImagen(0);
                } catch (Exception x) {
                    lbFoto4.setIcon(null);
                    repaint();
                }

            }
        } catch (NullPointerException e) {
            seguimientoEditable(true);
            lbCodFoto.setText("");
        }
    }//GEN-LAST:event_cmbFechaItemStateChanged

    public void seleccionarImagen(String x) {
        ImageIcon fot = new ImageIcon(System.getProperty("user.dir") + "\\imagenes\\medico\\IMG-" + x + ".jpg");
        Icon icono = new ImageIcon(fot.getImage().getScaledInstance(lbFoto4.getWidth(), lbFoto4.getHeight(), Image.SCALE_DEFAULT));
        lbFoto4.setIcon(icono);
        this.repaint();
    }

    public void limpiarExamenes(JTable tabla) {
        int cant = tabla.getRowCount();
        for (int i = 0; i < cant; i++) {
            ((DefaultTableModel) tabla.getModel()).removeRow(0);
        }
    }

    public void cambiarImagen(int x) {
        imagenes = lbCodFoto.getText().split(";");

        if (((actual + x) >= 0) && ((actual + x) < imagenes.length)) {

            actual += x;

            ImageIcon fot = new ImageIcon(System.getProperty("user.dir") + "\\imagenes\\medico\\IMG-" + imagenes[actual] + ".jpg");
            Icon icono = new ImageIcon(fot.getImage().getScaledInstance(lbFoto4.getWidth(), lbFoto4.getHeight(), Image.SCALE_DEFAULT));
            lbFoto4.setIcon(icono);
            this.repaint();
        }
    }

    public void seguimientoEditable(boolean x) {
        txtEvolucionSeg.setEditable(x);

        limpiarExamenes(tblExamens);

        if (x) {
            txtProfesional.setText(instancias.getUsuarioLog().getNombre());
            lbFoto4.setIcon(null);
            repaint();
        }

        txtEvolucionSeg.setText("");
    }

    private void cmbFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbFechaActionPerformed

    }//GEN-LAST:event_cmbFechaActionPerformed

    private void txtExamenesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtExamenesKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnAgregarActionPerformed(null);
        }
    }//GEN-LAST:event_txtExamenesKeyReleased

    private void btnNuevo5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevo5ActionPerformed
        visor ventana = new visor(lbCodFoto.getText());
        ventana.show();
    }//GEN-LAST:event_btnNuevo5ActionPerformed

    private void btnNuevo9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevo9ActionPerformed
        cambiarImagen(-1);
    }//GEN-LAST:event_btnNuevo9ActionPerformed

    private void btnNuevo8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevo8ActionPerformed
        cambiarImagen(1);
    }//GEN-LAST:event_btnNuevo8ActionPerformed

    private void tblExamensMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblExamensMouseClicked
        seleccionarImagen(tblExamens.getValueAt(tblExamens.getSelectedRow(), 1).toString());
    }//GEN-LAST:event_tblExamensMouseClicked

    private void btnSubirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubirActionPerformed
        JFileChooser chooser = new JFileChooser();
        ImagePreviewPanel preview = new ImagePreviewPanel();
        chooser.setAccessory(preview);
        chooser.addPropertyChangeListener(preview);

        int respuesta = chooser.showSaveDialog(this);

        if (respuesta == JFileChooser.APPROVE_OPTION) {
            FROM2 = Paths.get(chooser.getSelectedFile().getAbsolutePath());
        }
        ImageIcon fot = new ImageIcon(chooser.getSelectedFile().getAbsolutePath());
        Icon icono = new ImageIcon(fot.getImage().getScaledInstance(lbFoto4.getWidth(), lbFoto4.getHeight(), Image.SCALE_DEFAULT));
        lbFoto4.setIcon(icono);
        this.repaint();
    }//GEN-LAST:event_btnSubirActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        String id = "";

        id = Arrays.toString(instancias.getSql().getNumConsecutivo("IMG"));

        Path TO = Paths.get(System.getProperty("user.dir") + "\\imagenes\\medico\\IMG-" + id + ".jpg");

        //sobreescribir el fichero de destino, si existe, y copiar
        // los atributos, incluyendo los permisos rwx
        CopyOption[] options = new CopyOption[]{
            StandardCopyOption.REPLACE_EXISTING,
            StandardCopyOption.COPY_ATTRIBUTES
        };

        try {
            Files.copy(FROM2, TO, options);
        } catch (Exception ex) {
            metodos.msgError(this, "Hubo un error al cargar el archivo");
            return;
        }

        if (!instancias.getSql().aumentarConsecutivo("IMG", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("IMG")[0]) + 1)) {
            metodos.msgError(this, "Hubo un problema al guardar el codigo de la imagen");
        }
        if (lbCodFoto.getText().equals("")) {
            lbCodFoto.setText(id);
        } else {

            lbCodFoto.setText(lbCodFoto.getText() + ";" + id);
        }

        DefaultTableModel modeloTabla = (DefaultTableModel) tblExamens.getModel();

        modeloTabla.addRow(new Object[]{txtExamenes.getText(), id});
        txtExamenes.setText("");
        FROM2 = null;

    }//GEN-LAST:event_btnAgregarActionPerformed

    private void txtEvolucionSegKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEvolucionSegKeyReleased

    }//GEN-LAST:event_txtEvolucionSegKeyReleased

    private void btnGuardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar1ActionPerformed
        Object[] campos = {txtEvolucionSeg, txtIdentificacion, txtFechaIngreso};
        String faltantes = metodos.camposVacios(campos);

        if (!faltantes.equals("")) {
            metodos.msgAdvertencia(this, "No puede continuar, faltan los siguientes campos: " + faltantes);
            return;
        }

        if (metodos.msgPregunta(this, "¿Desea continuar?") == 0) {

            int i = 0;

            String consulta = lista.getSelectedValue().toString().split(" - ")[0];

            String examen = "";
            for (int j = 0; j < tblExamens.getRowCount(); j++) {
                examen = examen + "" + String.valueOf(tblExamens.getValueAt(j, 0)) + ";" + String.valueOf(tblExamens.getValueAt(j, 1)) + "&";
            }

            if (!examen.equals("")) {
                examen = examen.substring(0, examen.length() - 1);
            }

            Object[] vector2 = {"", metodosGenerales.fecha(), instancias.getUsuarioLog().getUsuario(), txtEvolucionSeg.getText(),
                lbCodFoto.getText(), consulta, examen};

            ndSeguimientoClinicaOf nodoSeguimiento = metodos.llenarSeguimientoClinicaOf(vector2);

            if (!instancias.getSql().agregarSeguimientoClinicaOf(nodoSeguimiento)) {
                metodos.msgError(this, "Hubo un problema al guardar el seguimiento");
                return;
            }

            i++;

            if (i > 0) {
                metodos.msgExito(this, "Evoluciòn guardada con exito");
            }
            txtEvolucionSeg.setText("");
            lbCodFoto.setText("");
            limpiarExamenes(tblExamens);
            lbFoto4.setIcon(null);

            for (int x = 0; x < jPanel7.getComponentCount(); x++) {
                if (jPanel7.getComponent(x) instanceof JTextField) {
                    JTextField textField = (JTextField) jPanel7.getComponent(x);
                    textField.setText("");
                }
            }
            txtProfesional.setText(instancias.getUsuarioLog().getNombre());
        }
    }//GEN-LAST:event_btnGuardar1ActionPerformed

    private void txtMcKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMcKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMcKeyReleased

    private void txtOtrasEnfermedadesOcularesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOtrasEnfermedadesOcularesKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOtrasEnfermedadesOcularesKeyReleased

    private void txtOtrasEnfermedadesGeneralesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOtrasEnfermedadesGeneralesKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOtrasEnfermedadesGeneralesKeyReleased

    private void txtOtrasEnfermedadesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOtrasEnfermedadesKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOtrasEnfermedadesKeyReleased

    private void btnBusProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusProdActionPerformed
        ventanaProductos("");
    }//GEN-LAST:event_btnBusProdActionPerformed

    private void txtCodProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodProductoActionPerformed

    private void txtCodProductoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodProductoFocusGained

    }//GEN-LAST:event_txtCodProductoFocusGained

    private void txtCodProductoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodProductoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodProductoKeyPressed

    private void txtCodProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodProductoKeyReleased
//        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
//            ndProducto producto = sql.getDatosProducto(txtCodServicioo.getText());
////            if (producto.getCodigo() == null) {
////                txtCodProducto.setText(producto.getDescripcion());
////            } else {
//                ventanaProductos(txtCodProducto.getText());
////            }
//        } else {
//            txtCodServicioo.setText("");
//        }
    }//GEN-LAST:event_txtCodProductoKeyReleased

    private void txtCodServiciooKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodServiciooKeyReleased
        ndProducto producto = instancias.getSql().getDatosProducto(txtCodServicioo.getText(), "bdProductos");
        txtCodProducto.setText(producto.getDescripcion());
    }//GEN-LAST:event_txtCodServiciooKeyReleased

    private void txtBiomicroscopiaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBiomicroscopiaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBiomicroscopiaKeyReleased

    private void txtFondoDeOjoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFondoDeOjoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFondoDeOjoKeyReleased


    private void txtCie1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCie1KeyReleased
        txtDiagnostico1.setText(instancias.getSql().getNombreCie(txtCie1.getText()));
    }//GEN-LAST:event_txtCie1KeyReleased

    private void txtDiagnostico1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiagnostico1KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ventanaCie1(txtDiagnostico1.getText(), txtDiagnostico1);
        }
    }//GEN-LAST:event_txtDiagnostico1KeyReleased

    private void txtDiagnostico2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiagnostico2KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ventanaCie2(txtDiagnostico2.getText(), txtDiagnostico2);
        }
    }//GEN-LAST:event_txtDiagnostico2KeyReleased

    private void txtCie2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCie2KeyReleased
        txtDiagnostico2.setText(instancias.getSql().getNombreCie(txtCie2.getText()));
    }//GEN-LAST:event_txtCie2KeyReleased

    private void txtDiagnostico3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiagnostico3KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ventanaCie3(txtDiagnostico3.getText(), txtDiagnostico3);
        }
    }//GEN-LAST:event_txtDiagnostico3KeyReleased

    private void txtCie3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCie3KeyReleased
        txtDiagnostico3.setText(instancias.getSql().getNombreCie(txtCie3.getText()));
    }//GEN-LAST:event_txtCie3KeyReleased

    private void txtDiagnostico4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiagnostico4KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ventanaCie4(txtDiagnostico4.getText(), txtDiagnostico4);
        }
    }//GEN-LAST:event_txtDiagnostico4KeyReleased

    private void txtCie4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCie4KeyReleased
        txtDiagnostico4.setText(instancias.getSql().getNombreCie(txtCie4.getText()));
    }//GEN-LAST:event_txtCie4KeyReleased

    private void txtCup1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCup1KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                txtProcedimiento1.setText(instancias.getSql().getNombreCup(txtCup1.getText()));
            } catch (Exception e) {
                txtProcedimiento1.setText("");
            }
        }
    }//GEN-LAST:event_txtCup1KeyReleased

    private void txtCup2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCup2KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                txtProcedimiento2.setText(instancias.getSql().getNombreCup(txtCup2.getText()));
            } catch (Exception e) {
                txtProcedimiento2.setText("");
            }
        }
    }//GEN-LAST:event_txtCup2KeyReleased

    private void txtCup3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCup3KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                txtProcedimiento3.setText(instancias.getSql().getNombreCup(txtCup3.getText()));
            } catch (Exception e) {
                txtProcedimiento3.setText("");
            }
        }
    }//GEN-LAST:event_txtCup3KeyReleased

    private void txtCup4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCup4KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                txtProcedimiento4.setText(instancias.getSql().getNombreCup(txtCup4.getText()));
            } catch (Exception e) {
                txtProcedimiento4.setText("");
            }
        }
    }//GEN-LAST:event_txtCup4KeyReleased

    private void txtProcedimiento1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProcedimiento1KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ventanaCup1(txtProcedimiento1.getText());
        } else {
            txtCup1.setText("");
        }
    }//GEN-LAST:event_txtProcedimiento1KeyReleased

    private void txtProcedimiento2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProcedimiento2KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ventanaCup2(txtProcedimiento2.getText());
        } else {
            txtCup2.setText("");
        }
    }//GEN-LAST:event_txtProcedimiento2KeyReleased

    private void txtProcedimiento3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProcedimiento3KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ventanaCup3(txtProcedimiento3.getText());
        } else {
            txtCup3.setText("");
        }
    }//GEN-LAST:event_txtProcedimiento3KeyReleased

    private void txtProcedimiento4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProcedimiento4KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ventanaCup4(txtProcedimiento4.getText());
        }
    }//GEN-LAST:event_txtProcedimiento4KeyReleased

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        if (lista.getSelectedIndex() == 0) {
            metodos.msgAdvertencia(this, "Debe de generar una consulta asociada");
            return;
        }

        String resp = lista.getSelectedValue().toString().split(" - ")[0];
        ndHojaIngreso nodo = instancias.getSql().getDatosHojaIngreso(resp);
        instancias.getFormulaLentes().nuevaFormula(txtIdentificacion.getText(), txtRefSubjetivoOD.getText(), txtRefSubjetivoOI.getText());
    }//GEN-LAST:event_jButton6ActionPerformed

    private void btnCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarActionPerformed
        txtIdentificacionKeyReleased(null);
    }//GEN-LAST:event_btnCargarActionPerformed

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        try {
            instancias.getAgendaConsulta().cargarAgendas(instancias.getMedico());
            instancias.getAgendaConsulta().cargarAgendas();
            instancias.getAgendaConsulta().setSelected(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(infAyudaDiagnosticoOf.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel10MouseClicked

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        try {
            instancias.getAgendaConsulta().cargarAgendas(instancias.getMedico());
            instancias.getAgendaConsulta().cargarAgendas();
            instancias.getAgendaConsulta().setSelected(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(infAyudaDiagnosticoOf.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel13MouseClicked

    private void btnProximaCitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProximaCitaActionPerformed
        String usuaiorLog;

        infInfoAgenda buscar = new infInfoAgenda(null, rootPaneCheckingEnabled, usuaiorLog = instancias.getSql().getNombreEmpleadoUsuario(instancias.getUsuario()), "",
                "", 0, 0, txtIdentificacion.getText()
        );
        buscar.setLocationRelativeTo(null);
        instancias.setInfoAgenda(buscar);
        buscar.show();
        instancias.getAgendaConsulta().cargarAgendas(instancias.getMedico());
        instancias.getAgendaConsulta().cargarAgendas();
    }//GEN-LAST:event_btnProximaCitaActionPerformed

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtTelefonoKeyTyped

    private void txtTelefono1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefono1KeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtTelefono1KeyTyped

    private void txtConductaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConductaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtConductaKeyReleased

    private void txtDiagnostico2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiagnostico2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiagnostico2ActionPerformed

    private void btnPaquimetriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPaquimetriaActionPerformed
        try {
            instancias.getPaquimetria().cargar(txtIdentificacion.getText());
            instancias.getPaquimetria().setSelected(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(infPaquimetria.class.getName()).log(Level.SEVERE, "", ex);
        }
    }//GEN-LAST:event_btnPaquimetriaActionPerformed

    private void btnBiometriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBiometriaActionPerformed
        try {
            instancias.getBiometria().cargar(txtIdentificacion.getText());
            instancias.getBiometria().setSelected(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(infBiometria.class.getName()).log(Level.SEVERE, "", ex);
        }
    }//GEN-LAST:event_btnBiometriaActionPerformed

    private void txtAnexosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAnexosKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAnexosKeyReleased

    private void txtPupilasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPupilasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPupilasActionPerformed

    public void cargarDesdeCliente(String cliente, int numConsulta) {
        txtIdentificacion.setText(cliente);
        activo = true;

        seleccionar = String.valueOf(numConsulta);
        cargarCliente(txtIdentificacion.getText());

    }

    public void cargarCliente(String nit) {

        ModeloContacto nodo = instancias.getSql().getDatosTercero(nit);

        if (nodo.getId() != null) {
            if (nodo.isActivo()) {
                metodos.msgError(null, "Este cliente esta inactivado");
                return;
            }

            txtPrimerNombre.setText(nodo.getpNombre());
            txtSegundoNombre.setText(nodo.getsNombre());
            txtPrimeroApellido.setText(nodo.getpApellido());
            txtSegundoApellido.setText(nodo.getsApellido());
            String fecha = nodo.getNacimiento().substring(0, 10);
            txtFechaNacimiento.setText(metodos.fecha(fecha));
            txtEdad.setText(metodosGenerales.calcularEdad(metodos.fecha(fecha)).toString());
            txtSexo.setText(nodo.getSexo());
            txtOcupacion.setText(nodo.getOcupacion());
            txtEstadoCivil.setText(nodo.getEstado());
            txtMunicipio.setText(nodo.getCiudad());
            txtDepartamento.setText(nodo.getDepartamento());
            txtZona.setText(nodo.getZona());
            txtDireccion.setText(nodo.getDireccion());
            txtTelefono.setText(nodo.getTelefono());

            Object[][] fechas = instancias.getSql().getConsultasClinica1(txtIdentificacion.getText());

            if (fechas.length != 0) {
                Object[] datos = new Object[fechas.length + 1];
                datos[0] = "HOY - NUEVO";
                int j = fechas.length;

                for (int i = 0; i < fechas.length; i++) {
                    datos[j--] = fechas[i][0] + " - " + metodos.fecha(fechas[i][1].toString());
                    if (activo && fechas[i][0].equals(seleccionar)) {
                        seleccionar = String.valueOf(j + 1);
                    }
                }
                lista.setListData(datos);
                if (activo) {
                    lista.setSelectedIndex(Integer.parseInt(seleccionar));
                } else {
                    lista.setSelectedIndex(0);
                }
            }

            activo = false;

            Object[][] fechas1 = instancias.getSql().getConsultasClinica1(txtIdentificacion.getText());
            Object[] datos1 = new Object[fechas1.length + 1];
            datos1[0] = "HOY - NUEVO";
            int k = fechas1.length;
            int id = 0;

            for (int i = 0; i < fechas1.length; i++) {
                datos1[k--] = fechas1[i][0] + " - " + metodos.fecha(fechas1[i][1].toString());
                if (Integer.parseInt(fechas1[i][0].toString()) > id) {
                    id = Integer.parseInt(fechas1[i][0].toString());
                }
            }

            if (id != 0) {
                ndHojaIngreso nodoConsulta = instancias.getSql().getDatosHojaIngreso(Integer.toString(id));
                if (nodo.getId() != null) {
                    txtAP.setText(nodoConsulta.getEnfermedades());
                    String texto1 = txtAP.getText();
                    if (texto1.contains("Hipertensión,")) {
                        btnHipertension.setEnabled(false);
                    }
                    if (texto1.contains("Diabetes,")) {
                        btnDiabetes.setEnabled(false);
                    }
                    if (texto1.contains("Artritis,")) {
                        btnArtritis.setEnabled(false);
                    }
                    if (texto1.contains("Ulcera gástrica,")) {
                        btnUlceraGastrica.setEnabled(false);
                    }
                    if (texto1.contains("Alergias,")) {
                        btnAlergias.setEnabled(false);
                    }

                    txtOtrasEnfermedades.setText(nodoConsulta.getOtrasEnfermedades());
                    txtLentes.setText(nodoConsulta.getLentes());
                    txtAP1.setText(nodoConsulta.getEnfermedades1());
                    String texto2 = txtAP1.getText();
                    if (texto2.contains("Hipertensión,")) {
                        btnHipertension1.setEnabled(false);
                    }
                    if (texto2.contains("Diabetes, ")) {
                        btnDiabetes1.setEnabled(false);
                    }
                    if (texto2.contains("Artritis,")) {
                        btnArtritis1.setEnabled(false);
                    }
                    if (texto2.contains("Estrabismo,")) {
                        btnEstrabismo.setEnabled(false);
                    }
                    if (texto2.contains("Cataratas,")) {
                        btnCataratas.setEnabled(false);
                    }
                    if (texto2.contains("Glaucoma,")) {
                        btnGlaucoma.setEnabled(false);
                    }
                    txtLentes1.setText(nodoConsulta.getLentes1());
                    txtAlergias1.setText(nodoConsulta.getAlergias());
                    txtOtrasEnfermedadesOculares.setText(nodoConsulta.getOtrasEnfermedadesOculares());
                    txtOtrasEnfermedadesOculares.setText(nodoConsulta.getOtrasEnfermadesdeGenerales());
//                    txtClOD.setText(nodoConsulta.getClOD());
//                    txtClOI.setText(nodoConsulta.getClOI());
//                    txtAvOD.setText(nodoConsulta.getAvOD());
//                    txtAvOI.setText(nodoConsulta.getAvOI());
//                    txtForias.setText(nodoConsulta.getForias());
//                    txtPupilas.setText(nodoConsulta.getPupilas());
//                    txtMc.setText(nodoConsulta.getMc());

//                    txtResponsable.setText(nodoConsulta.getResponsable());
//                    txtTelefono1.setText(nodoConsulta.getTelefono1());
//                    cmbParentesco.setSelectedItem(nodoConsulta.getParentesco());
                }
            }
            return;
        }
        ventanaTerceros(nit);
    }

    public void ventanaProductos(String nit) {
        buscProductos buscar = new buscProductos(instancias.getMenu(), true, false, "", "productos1");
        buscar.setLocationRelativeTo(null);
        instancias.setBusProductos(buscar);
//        buscar.setInstancia(instancias);
        instancias.setCampoActual(txtCodServicioo);
        txtCodServicioo.requestFocus();
        buscar.noEncontrado(nit);
        buscar.show();
    }

    public void consultaEditable(boolean x) {
        txtIdentificacion.setEnabled(x);
        txtResponsable.setEnabled(x);
        txtTelefono1.setEnabled(x);
        cmbParentesco.setEnabled(x);
        txtAP.setEnabled(x);
        txtAP1.setEnabled(x);
        txtOtrasEnfermedades.setEnabled(x);
        txtOtrasEnfermedadesOculares.setEnabled(x);
        txtOtrasEnfermedadesGenerales.setEnabled(x);
        txtLentes.setEnabled(x);
        txtLentes1.setEnabled(x);
        txtAlergias1.setEnabled(x);
        txtAvOD.setEnabled(x);
        txtAvOI.setEnabled(x);
        txtClOD.setEnabled(x);
        txtClOI.setEnabled(x);
        txtRefraccionOD.setEnabled(x);
        txtRefraccionOI.setEnabled(x);
        txtForias.setEnabled(x);
        txtMc.setEnabled(x);
        txtPupilas.setEnabled(x);
        btnAlergias.setEnabled(x);
        btnArtritis.setEnabled(x);
        btnArtritis1.setEnabled(x);
        btnCataratas.setEnabled(x);
        btnDiabetes.setEnabled(x);
        btnDiabetes1.setEnabled(x);
        btnEstrabismo.setEnabled(x);
        btnGlaucoma.setEnabled(x);
        btnHipertension.setEnabled(x);
        btnHipertension1.setEnabled(x);
        btnUlceraGastrica.setEnabled(x);
        txtRefSubjetivoOD.setEnabled(x);
        txtRefSubjetivoOI.setEnabled(x);
        txtTonometriaOD.setEnabled(x);
        txtTonometriaOI.setEnabled(x);
        txtCodProducto.setEnabled(x);
        txtCodProducto.setText("");
        btnBusProd.setEnabled(x);
        txtBiomicroscopia.setEnabled(x);
        txtBiomicroscopia.setText("");
        txtAnexos.setEnabled(x);
        txtAnexos.setText("");
        txtFondoDeOjo.setEnabled(x);
        txtFondoDeOjo.setText("");
        txtDiagnostico.setEnabled(x);
        txtDiagnostico1.setEnabled(x);
        txtDiagnostico2.setEnabled(x);
        txtDiagnostico3.setEnabled(x);
        txtDiagnostico4.setEnabled(x);
        txtProcedimiento.setEnabled(x);
        txtProcedimiento1.setEnabled(x);
        txtProcedimiento2.setEnabled(x);
        txtProcedimiento3.setEnabled(x);
        txtProcedimiento4.setEnabled(x);
        txtDiagnostico.setText("");
        txtDiagnostico1.setText("");
        txtDiagnostico2.setText("");
        txtDiagnostico3.setText("");
        txtDiagnostico4.setText("");
        txtProcedimiento.setText("");
        txtProcedimiento1.setText("");
        txtProcedimiento2.setText("");
        txtProcedimiento3.setText("");
        txtProcedimiento4.setText("");
        txtRefSubjetivoOD.setText("");
        txtRefSubjetivoOI.setText("");
        txtTonometriaOD.setText("");
        txtTonometriaOI.setText("");
        txtResponsable.setText("");
        txtTelefono1.setText("");
        cmbParentesco.setSelectedIndex(0);
        txtAP.setText("");
        txtAP1.setText("");
        txtOtrasEnfermedades.setText("");
        txtOtrasEnfermedadesOculares.setText("");
        txtOtrasEnfermedadesOculares.setText("");
        txtLentes.setText("");
        txtLentes1.setText("");
        txtAlergias1.setText("");
        txtAvOD.setText("");
        txtAvOI.setText("");
        txtClOD.setText("");
        txtClOI.setText("");
        txtRefraccionOD.setText("");
        txtRefraccionOI.setText("");
        txtForias.setText("");
        txtMc.setText("");
        txtPupilas.setText("");
        txtOtrasEnfermedades.setText("");
        txtOtrasEnfermedadesOculares.setText("");
        txtOtrasEnfermedadesOculares.setText("");

        txtCie.setText("");
        txtCie1.setText("");
        txtCie2.setText("");
        txtCie3.setText("");
        txtCie4.setText("");
        txtCup.setText("");
        txtCup1.setText("");
        txtCup2.setText("");
        txtCup3.setText("");
        txtCup4.setText("");
        txtFechaIngreso.setText(metodosGenerales.fecha());
        btnHipertension.setForeground(new java.awt.Color(0, 0, 0));
        btnDiabetes.setForeground(new java.awt.Color(0, 0, 0));
        btnArtritis.setForeground(new java.awt.Color(0, 0, 0));
        btnUlceraGastrica.setForeground(new java.awt.Color(0, 0, 0));
        btnAlergias.setForeground(new java.awt.Color(0, 0, 0));
        btnHipertension1.setForeground(new java.awt.Color(0, 0, 0));
        btnDiabetes1.setForeground(new java.awt.Color(0, 0, 0));
        btnArtritis1.setForeground(new java.awt.Color(0, 0, 0));
        btnEstrabismo.setForeground(new java.awt.Color(0, 0, 0));
        btnCataratas.setForeground(new java.awt.Color(0, 0, 0));
        btnGlaucoma.setForeground(new java.awt.Color(0, 0, 0));
    }

    public void ventanaAntecedentes(String codigo, JTextField campo, int tipo) {
        buscAntecedentes buscar = new buscAntecedentes(null, rootPaneCheckingEnabled);
        buscar.setLocationRelativeTo(null);
        instancias.setBuscAntecedentes(buscar);
        instancias.setCampoActual(campo);
        campo.requestFocus();
        buscar.setInstancia(instancias, tipo);
        buscar.noEncontrado(codigo.replace("'", "//"));
        buscar.show();
    }

    public void ventanaCies(String codigo, JTextField campo) {
        buscCies buscar = new buscCies(null, rootPaneCheckingEnabled);
        buscar.setOpc(1);
        buscar.setLocationRelativeTo(null);
        instancias.setBuscCies(buscar);
        instancias.setCampoActual(campo);
        campo.requestFocus();
        buscar.noEncontrado(codigo.replace("'", "//"));
        buscar.show();
    }

    public void ventanaCie(String codigo, JTextField campo) {
        buscCies buscar = new buscCies(null, rootPaneCheckingEnabled);
        buscar.setLocationRelativeTo(null);
        instancias.setBuscCies(buscar);
        instancias.setCampoActual(txtCie);
        txtCie.requestFocus();
        buscar.noEncontrado(codigo.replace("'", "//"));
        buscar.show();
        txtCieKeyReleased(null);
    }

    public void ventanaCie1(String codigo, JTextField campo) {
        buscCies buscar = new buscCies(null, rootPaneCheckingEnabled);
        buscar.setLocationRelativeTo(null);
        instancias.setBuscCies(buscar);
        instancias.setCampoActual(txtCie1);
        txtCie1.requestFocus();
        buscar.noEncontrado(codigo.replace("'", "//"));
        buscar.show();
        txtCie1KeyReleased(null);
    }

    public void ventanaCie2(String codigo, JTextField campo) {
        buscCies buscar = new buscCies(null, rootPaneCheckingEnabled);
        buscar.setLocationRelativeTo(null);
        instancias.setBuscCies(buscar);
        instancias.setCampoActual(txtCie2);
        txtCie2.requestFocus();
//        buscar.setInstancia(instancias);
        buscar.noEncontrado(codigo.replace("'", "//"));
        buscar.show();
        txtCie2KeyReleased(null);
    }

    public void ventanaCie3(String codigo, JTextField campo) {
        buscCies buscar = new buscCies(null, rootPaneCheckingEnabled);
        buscar.setLocationRelativeTo(null);
        instancias.setBuscCies(buscar);
        instancias.setCampoActual(txtCie3);
        txtCie3.requestFocus();
//        buscar.setInstancia(instancias);
        buscar.noEncontrado(codigo.replace("'", "//"));
        buscar.show();
        txtCie3KeyReleased(null);
    }

    public void ventanaCie4(String codigo, JTextField campo) {
        buscCies buscar = new buscCies(null, rootPaneCheckingEnabled);
        buscar.setLocationRelativeTo(null);
        instancias.setBuscCies(buscar);
        instancias.setCampoActual(txtCie4);
        txtCie4.requestFocus();
//        buscar.setInstancia(instancias);
        buscar.noEncontrado(codigo.replace("'", "//"));
        buscar.show();
        txtCie4KeyReleased(null);
    }

    public void ventanaCup(String codigo) {
        buscCups buscar = new buscCups(null, rootPaneCheckingEnabled);
        buscar.setLocationRelativeTo(null);
        instancias.setBuscCups(buscar);
        instancias.setCampoActual(txtCup);
        txtCup.requestFocus();
        buscar.noEncontrado(codigo.replace("'", "//"));
        buscar.show();
    }

    public void ventanaCup1(String codigo) {
        buscCups buscar = new buscCups(null, rootPaneCheckingEnabled);
        buscar.setLocationRelativeTo(null);
        instancias.setBuscCups(buscar);
        instancias.setCampoActual(txtCup1);
        txtCup1.requestFocus();
        buscar.noEncontrado(codigo.replace("'", "//"));
        buscar.show();
    }

    public void ventanaCup2(String codigo) {
        buscCups buscar = new buscCups(null, rootPaneCheckingEnabled);
        buscar.setLocationRelativeTo(null);
        instancias.setBuscCups(buscar);
        instancias.setCampoActual(txtCup2);
        txtCup2.requestFocus();
        buscar.noEncontrado(codigo.replace("'", "//"));
        buscar.show();
    }

    public void ventanaCup3(String codigo) {
        buscCups buscar = new buscCups(null, rootPaneCheckingEnabled);
        buscar.setLocationRelativeTo(null);
        instancias.setBuscCups(buscar);
        instancias.setCampoActual(txtCup3);
        txtCup3.requestFocus();
        buscar.noEncontrado(codigo.replace("'", "//"));
        buscar.show();
    }

    public void ventanaCup4(String codigo) {
        buscCups buscar = new buscCups(null, rootPaneCheckingEnabled);
        buscar.setLocationRelativeTo(null);
        instancias.setBuscCups(buscar);
        instancias.setCampoActual(txtCup4);
        txtCup4.requestFocus();
        buscar.noEncontrado(codigo.replace("'", "//"));
        buscar.show();
    }

    public String getConsulta() {

        try {
            if (lista.getSelectedIndex() == 0) {
                return historia.getId();
            }
        } catch (Exception e) {
            return historia.getId();
        }

        try {
            return lista.getSelectedValue().toString().split(" - ")[0];
        } catch (NullPointerException e) {
            return (String) null;
        }
    }

    public void ventanaTerceros(String nit) {
        buscClientes buscar = new buscClientes(instancias.getMenu(), true, false, null, "");
        buscar.setLocationRelativeTo(null);
        instancias.setBusClientes(buscar);
        instancias.setCampoActual(txtIdentificacion);
        txtIdentificacion.requestFocus();
        buscar.noEncontrado(nit);
        buscar.show();
    }

//    public void cargar(String paciente, ndOrdenServicio orden) {
//        this.paciente = paciente;
//
//        setPaciente(paciente);
//        try {
//            this.setSelected(true);
//        } catch (PropertyVetoException ex) {
//            Logger.getLogger(infHistoriaMedica.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        lista.setSelectedIndex(0);
//        try {
//            txtCup.setText(orden.getCup());
//            txtProcedimiento.setText(instancias.getSql().getNombreCup(orden.getCup()));
//            txtProcedimiento.setEditable(false);
//        } catch (Exception e) {
//            txtProcedimiento.setEditable(true);
//        }
//        try {
//            txtCie1.setText(orden.getCie());
//            txtDiagnostico1.setText(instancias.getSql().getNombreCie(orden.getCie()));
//            txtDiagnostico1.setEditable(false);
//        } catch (Exception e) {
//            txtProcedimiento.setEditable(true);
//        }
//        try {
//            this.orden = orden.getId();
//        } catch (Exception e) {
//            this.orden = null;
//        }
//
//    }
//    public void setInstancias(Instancias instancias) {
//        this.instancias = instancias;
//    }
    public void guardarOrden() {

        System.out.println("entro aquiii");

        instancias.setValor("Agenda");
        String consecutivo = "";
        String consecutivo1 = "";
        String valor = "0";
        String convenio = "";
        String factura = "";

        ndCamposOrdenPredeterminada nodo = instancias.getSql().getDatosCamposOrdenPredeterminada("1");

        try {
            ndProducto prod = instancias.getSql().getDatosProducto(nodo.getServicio(), "bdProductos");
            System.out.println(valor);
            valor = prod.getL1();
        } catch (Exception e) {
            valor = "0";
        }

        ModeloContacto nodoTercero = instancias.getSql().getDatosTercero(txtIdentificacion.getText());

        try {
            if (!nodoTercero.getEps().equals("")) {
                ndEpsPrecargados nodo2 = instancias.getSql().getDatosEpsPrecargados(nodoTercero.getEps());
                consecutivo = nodo2.getConsecutivo();
                consecutivo = consecutivo + "-" + instancias.getSql().getNumConsecutivoEps(consecutivo)[0];
            }
        } catch (Exception e) {
            consecutivo = "";
        }

        try {
            consecutivo1 = consecutivo.substring(6);
        } catch (Exception e) {
            consecutivo1 = "";
        }

        facturarOrden = true;
        factura = "FACT-" + instancias.getSql().getNumConsecutivo("FACT")[0];

        //guardar en ordenServicio
        orden = "" + instancias.getSql().getNumConsecutivo("ORDENOF")[0];

        productoFacturarOrden = nodo.getServicio();

        Object[] vector = {orden, factura, instancias.getUsuario(), metodosGenerales.fecha(), "", nodo.getConcepto(), txtCodServicioo.getText(),
            "", nodo.getFinalidad(), nodo.getCausaExterna(), nodo.getAmbito(), nodo.getPersonal(), nodo.getForma(), big.getMoneda("0"),
            big.getMoneda("0"), big.getMoneda("0"), valor, big.getMoneda("1"), big.getMoneda("0"), txtIdentificacion.getText(), txtCie.getText(),
            txtCup.getText(), metodosGenerales.fechaHora(), nodo.getTipo(), "", consecutivo1, nodo.getActoQuirurgico(), "1",
            consecutivo, nodo.getConsulta(), instancias.getSql().getNombreEmpleadoMedico(instancias.getUsuario()), metodosGenerales.hora(),
            convenio};

        ndOrdenServicio nodoOrden = metodos.llenarOrdenServicio(vector);

        if (!instancias.getSql().agregarOrdenServicioOftalmologia(nodoOrden)) {
            metodos.msgError(this, "Hubo un problema al guardar la orden de servicio");
            return;
        }

        if (!instancias.getSql().aumentarConsecutivo("ORDENOF", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("ORDENOF")[0]) + 1)) {
            metodos.msgError(this, "Hubo un problema al guardar en el consecutivo de la orden");
            return;
        }

        try {
            if (!instancias.getSql().aumentarConsecutivoEps(consecutivo.substring(0, 5), Integer.parseInt((String) instancias.getSql().getNumConsecutivoEps(consecutivo.substring(0, 5))[0]) + 1)) {
                metodos.msgError(this, "Hubo un problema al guardar en el consecutivo de la EPS");
                return;
            }
        } catch (Exception e) {
        }

//        if (!instancias.getSql().aumentarConsecutivo("HSTC", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("HSTC")[0]) + 1)) {
//            metodos.msgError(this, "Hubo un problema al guardar en el consecutivo de la historia");
//            return;
//        }
//        System.out.println(instancias.getValor() + "valor");
        if (instancias.getValor().equals("Agenda")) {
            String idCitaVieja = instancias.getCita();
            instancias.getSql().modificarCita(idCitaVieja, "Confirmada");
            instancias.setCita("");
        }
    }

    public void facturar(String producto) {
//        String[][] e = new String[1][4];
//        e[0][1] = producto;
//        e[0][2] = "1";
//        e[0][3] = "0";
//        e[0][4] = "0";

        instancias.getFactura().desdeOrden(txtIdentificacion.getText(), producto);
    }

    public void setPaciente(String paciente, String producto) {
        btnLimpiarActionPerformed(null);
        txtIdentificacion.setText(paciente);

        if (confirmada) {
            Object[][] fechas1 = instancias.getSql().getConsultasClinica1(paciente);
            Object[] datos1 = new Object[fechas1.length + 1];
            datos1[0] = "HOY - NUEVO";
            int k = fechas1.length;
            int id = 0;

            for (int i = 0; i < fechas1.length; i++) {
                datos1[k--] = fechas1[i][0] + " - " + metodos.fecha(fechas1[i][1].toString());
                if (Integer.parseInt(fechas1[i][0].toString()) > id) {
                    id = Integer.parseInt(fechas1[i][0].toString());
                }
            }
            cargarDesdeCliente(paciente, id);
            confirmada = false;
        } else {
            cargarCliente(paciente);
        }

        txtCodServicioo.setText(producto);
        ndProducto producto1 = instancias.getSql().getDatosProducto(producto, "bdProductos");
        txtCodProducto.setText(producto1.getDescripcion());
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnAlergias;
    private javax.swing.JButton btnArtritis;
    private javax.swing.JButton btnArtritis1;
    private javax.swing.JButton btnBiometria;
    private javax.swing.JButton btnBusProd;
    private javax.swing.JButton btnBuscTerceros;
    private javax.swing.JButton btnCargar;
    private javax.swing.JButton btnCataratas;
    private javax.swing.JButton btnDiabetes;
    private javax.swing.JButton btnDiabetes1;
    private javax.swing.JButton btnEstrabismo;
    private javax.swing.JButton btnGlaucoma;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnGuardar1;
    private javax.swing.JButton btnHipertension;
    private javax.swing.JButton btnHipertension1;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnNuevo5;
    private javax.swing.JButton btnNuevo8;
    private javax.swing.JButton btnNuevo9;
    private javax.swing.JButton btnPaquimetria;
    private javax.swing.JButton btnProximaCita;
    private javax.swing.JButton btnReimprimir;
    private javax.swing.JButton btnSubir;
    private javax.swing.JButton btnUlceraGastrica;
    private javax.swing.JComboBox cmbFecha;
    private javax.swing.JComboBox cmbParentesco;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane24;
    private javax.swing.JScrollPane jScrollPane25;
    private javax.swing.JScrollPane jScrollPane26;
    private javax.swing.JScrollPane jScrollPane27;
    private javax.swing.JScrollPane jScrollPane28;
    private javax.swing.JScrollPane jScrollPane29;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lbCodFoto;
    private javax.swing.JLabel lbDireccion20;
    private javax.swing.JLabel lbDireccion23;
    private javax.swing.JLabel lbDireccion25;
    private javax.swing.JLabel lbDireccion26;
    private javax.swing.JLabel lbDireccion27;
    private javax.swing.JLabel lbDireccion40;
    private javax.swing.JLabel lbDireccion41;
    private javax.swing.JLabel lbDireccion42;
    private javax.swing.JLabel lbDireccion43;
    private javax.swing.JLabel lbDireccion44;
    private javax.swing.JLabel lbDireccion45;
    private javax.swing.JLabel lbDireccion46;
    private javax.swing.JLabel lbDireccion47;
    private javax.swing.JLabel lbDireccion48;
    private javax.swing.JLabel lbDireccion49;
    private javax.swing.JLabel lbDireccion50;
    private javax.swing.JLabel lbDireccion51;
    private javax.swing.JLabel lbDireccion52;
    private javax.swing.JLabel lbDireccion55;
    private javax.swing.JLabel lbDireccion56;
    private javax.swing.JLabel lbDireccion57;
    private javax.swing.JLabel lbDireccion58;
    private javax.swing.JLabel lbDireccion59;
    private javax.swing.JLabel lbDireccion60;
    private javax.swing.JLabel lbDireccion61;
    private javax.swing.JLabel lbDireccion62;
    private javax.swing.JLabel lbDireccion63;
    private javax.swing.JLabel lbDireccion64;
    private javax.swing.JLabel lbDireccion65;
    private javax.swing.JLabel lbDireccion66;
    private javax.swing.JLabel lbDireccion67;
    private javax.swing.JLabel lbDireccion68;
    private javax.swing.JLabel lbDireccion69;
    private javax.swing.JLabel lbDireccion70;
    private javax.swing.JLabel lbEmail22;
    private javax.swing.JLabel lbEmail23;
    private javax.swing.JLabel lbEmail5;
    private javax.swing.JLabel lbFoto4;
    private javax.swing.JLabel lbNit10;
    private javax.swing.JLabel lbNit4;
    private javax.swing.JLabel lbNit5;
    private javax.swing.JLabel lbRazon22;
    private javax.swing.JLabel lbRazon32;
    private javax.swing.JLabel lbRazon33;
    private javax.swing.JLabel lbRazon34;
    private javax.swing.JLabel lbRazon35;
    private javax.swing.JLabel lbRazon36;
    private javax.swing.JLabel lbRazon37;
    private javax.swing.JLabel lbRazon38;
    private javax.swing.JLabel lbRazon39;
    private javax.swing.JLabel lbRazon4;
    private javax.swing.JLabel lbRazon40;
    private javax.swing.JLabel lbRazon41;
    private javax.swing.JLabel lbRazon42;
    private javax.swing.JLabel lbRazon43;
    private javax.swing.JLabel lbRazon44;
    private javax.swing.JLabel lbRazon45;
    private javax.swing.JLabel lbRazon46;
    private javax.swing.JLabel lbRazon47;
    private javax.swing.JLabel lbRazon48;
    private javax.swing.JLabel lbRazon49;
    private javax.swing.JLabel lbRazon50;
    private javax.swing.JLabel lbRazon51;
    private javax.swing.JLabel lbRazon52;
    private javax.swing.JLabel lbRazon53;
    private javax.swing.JLabel lbRazon54;
    private javax.swing.JLabel lbRazon55;
    private javax.swing.JLabel lbRazon56;
    private javax.swing.JLabel lbRazon57;
    private javax.swing.JLabel lbRazon58;
    private javax.swing.JLabel lbRazon59;
    private javax.swing.JLabel lbRazon60;
    private javax.swing.JLabel lbRazon61;
    private javax.swing.JList lista;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JTable tblExamens;
    private javax.swing.JTextField txtAP;
    private javax.swing.JTextField txtAP1;
    private javax.swing.JTextField txtAlergias1;
    private javax.swing.JTextArea txtAnexos;
    private javax.swing.JTextField txtAvOD;
    private javax.swing.JTextField txtAvOI;
    private javax.swing.JTextArea txtBiomicroscopia;
    private javax.swing.JTextField txtCie;
    private javax.swing.JTextField txtCie1;
    private javax.swing.JTextField txtCie2;
    private javax.swing.JTextField txtCie3;
    private javax.swing.JTextField txtCie4;
    private javax.swing.JTextField txtClOD;
    private javax.swing.JTextField txtClOI;
    private javax.swing.JTextField txtCodProducto;
    private javax.swing.JTextField txtCodServicioo;
    private javax.swing.JTextArea txtConducta;
    private javax.swing.JTextField txtCup;
    private javax.swing.JTextField txtCup1;
    private javax.swing.JTextField txtCup2;
    private javax.swing.JTextField txtCup3;
    private javax.swing.JTextField txtCup4;
    private javax.swing.JTextField txtDepartamento;
    private javax.swing.JTextField txtDiagnostico;
    private javax.swing.JTextField txtDiagnostico1;
    private javax.swing.JTextField txtDiagnostico2;
    private javax.swing.JTextField txtDiagnostico3;
    private javax.swing.JTextField txtDiagnostico4;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEdad;
    private javax.swing.JTextField txtEstadoCivil;
    private javax.swing.JTextArea txtEvolucionSeg;
    private javax.swing.JTextField txtExamenes;
    private javax.swing.JTextField txtFechaIngreso;
    private javax.swing.JTextField txtFechaNacimiento;
    private javax.swing.JTextArea txtFondoDeOjo;
    private javax.swing.JTextField txtForias;
    private javax.swing.JTextField txtIdentificacion;
    private javax.swing.JTextField txtLentes;
    private javax.swing.JTextField txtLentes1;
    private javax.swing.JTextArea txtMc;
    private javax.swing.JTextField txtMunicipio;
    private javax.swing.JTextField txtOcupacion;
    private javax.swing.JTextArea txtOtrasEnfermedades;
    private javax.swing.JTextArea txtOtrasEnfermedadesGenerales;
    private javax.swing.JTextArea txtOtrasEnfermedadesOculares;
    private javax.swing.JTextField txtPrimerNombre;
    private javax.swing.JTextField txtPrimeroApellido;
    private javax.swing.JTextField txtProcedimiento;
    private javax.swing.JTextField txtProcedimiento1;
    private javax.swing.JTextField txtProcedimiento2;
    private javax.swing.JTextField txtProcedimiento3;
    private javax.swing.JTextField txtProcedimiento4;
    private javax.swing.JTextField txtProfesional;
    private javax.swing.JTextField txtPupilas;
    private javax.swing.JTextField txtRefSubjetivoOD;
    private javax.swing.JTextField txtRefSubjetivoOI;
    private javax.swing.JTextField txtRefraccionOD;
    private javax.swing.JTextField txtRefraccionOI;
    private javax.swing.JTextField txtResponsable;
    private javax.swing.JTextField txtSegundoApellido;
    private javax.swing.JTextField txtSegundoNombre;
    private javax.swing.JTextField txtSexo;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtTelefono1;
    private javax.swing.JTextField txtTonometriaOD;
    private javax.swing.JTextField txtTonometriaOI;
    private javax.swing.JTextField txtZona;
    // End of variables declaration//GEN-END:variables
}
