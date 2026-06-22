package formularios.Veterinario;

import clases.Celda_CheckBox;
import clases.ImagePreviewPanel;
import clases.Instancias;
import clases.Medico.ndConsultaClinica;
import clases.Render_CheckBox;
import Utilidades.BaseDatos.SQL;
import clases.Veterinario.ndConsulta;
import clases.Veterinario.ndHistoria;
import clases.Veterinario.ndMascota;
import clases.Veterinario.ndSeguimiento;
import clases.metodosGenerales;
import Modelo.Terceros.ModeloContacto;
import datechooser.beans.DateChooserCombo;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;
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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class infHistoria extends javax.swing.JInternalFrame {

    metodosGenerales metodos = new metodosGenerales();
    Instancias instancias;
    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;

    ndHistoria historia;
    ndConsulta nodoConsulta;

    String imagenes[];
    int actual;
    public Path FROM2;

    public infHistoria() {

        initComponents();

        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        setBorder(null);
        repaint();

        btnModificar.setVisible(false);
        jTabbedPane1.setEnabledAt(4, false);

        instancias = Instancias.getInstancias();

//        jButton2.setVisible(false);
//        jButton3.setVisible(false);
//        btnReimprimir.setVisible(false);
        for (int i = 0; i < 5; i++) {
            tblSistemas.setValueAt(false, i, 1);
            tblSistemas.setValueAt(false, i, 3);
        }

        //Se crea el JCheckBox en la columna indicada en getColumn, en este caso, la primera columna
        tblSistemas.getColumnModel().getColumn(1).setCellEditor(new Celda_CheckBox());
        tblSistemas.getColumnModel().getColumn(3).setCellEditor(new Celda_CheckBox());

        //para pintar la columna con el CheckBox en la tabla, en este caso, la primera columna
        tblSistemas.getColumnModel().getColumn(1).setCellRenderer(new Render_CheckBox());
        tblSistemas.getColumnModel().getColumn(3).setCellRenderer(new Render_CheckBox());

        scrFormulario.getVerticalScrollBar().setUnitIncrement(18);

        if (txtCodigo.getText().equals("")) {
            try {
                instancias.getMascotas().setSelected(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(infHistoria.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        popBorrar = new javax.swing.JMenuItem();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        popBorrar1 = new javax.swing.JMenuItem();
        scrFormulario = new javax.swing.JScrollPane();
        pnlFormulario = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lbFoto = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtRaza = new javax.swing.JTextField();
        lbNit = new javax.swing.JLabel();
        txtGenero = new javax.swing.JTextField();
        lbDireccion6 = new javax.swing.JLabel();
        txtReproductivo = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        lbDireccion4 = new javax.swing.JLabel();
        lbNit1 = new javax.swing.JLabel();
        txtHistoria = new javax.swing.JTextField();
        lbEmail1 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        lbDireccion5 = new javax.swing.JLabel();
        lbRazon = new javax.swing.JLabel();
        lbEmail8 = new javax.swing.JLabel();
        txtDieta = new javax.swing.JTextField();
        txtFrecuencia = new javax.swing.JTextField();
        lbEmail9 = new javax.swing.JLabel();
        txtEdad = new javax.swing.JTextField();
        lbEmail10 = new javax.swing.JLabel();
        txtObservaciones = new javax.swing.JTextField();
        lbEmail11 = new javax.swing.JLabel();
        txtEspecie = new javax.swing.JTextField();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        lbEmail12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtObv = new javax.swing.JTextArea();
        jPanel5 = new javax.swing.JPanel();
        txtDireccion = new javax.swing.JTextField();
        lbNit2 = new javax.swing.JLabel();
        lbDireccion9 = new javax.swing.JLabel();
        txtNombrePropietario = new javax.swing.JTextField();
        lbNit3 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        txtCedula = new javax.swing.JTextField();
        lbRazon1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel7 = new javax.swing.JPanel();
        btnGuardar2 = new javax.swing.JButton();
        pnlVacunasGatos = new javax.swing.JPanel();
        dtProxima13 = new datechooser.beans.DateChooserCombo();
        txtMarca13 = new javax.swing.JTextField();
        txtLote13 = new javax.swing.JTextField();
        dtNueva13 = new datechooser.beans.DateChooserCombo();
        cmbHistorico13 = new javax.swing.JComboBox();
        alerta13 = new javax.swing.JButton();
        lbNombre13 = new javax.swing.JLabel();
        lbNombre11 = new javax.swing.JLabel();
        alerta11 = new javax.swing.JButton();
        cmbHistorico11 = new javax.swing.JComboBox();
        dtNueva11 = new datechooser.beans.DateChooserCombo();
        txtLote11 = new javax.swing.JTextField();
        txtMarca11 = new javax.swing.JTextField();
        dtProxima11 = new datechooser.beans.DateChooserCombo();
        dtProxima10 = new datechooser.beans.DateChooserCombo();
        txtMarca10 = new javax.swing.JTextField();
        txtLote10 = new javax.swing.JTextField();
        dtNueva10 = new datechooser.beans.DateChooserCombo();
        cmbHistorico10 = new javax.swing.JComboBox();
        alerta10 = new javax.swing.JButton();
        lbNombre10 = new javax.swing.JLabel();
        lbNombre9 = new javax.swing.JLabel();
        alerta9 = new javax.swing.JButton();
        cmbHistorico9 = new javax.swing.JComboBox();
        dtNueva9 = new datechooser.beans.DateChooserCombo();
        txtLote9 = new javax.swing.JTextField();
        txtMarca9 = new javax.swing.JTextField();
        dtProxima9 = new datechooser.beans.DateChooserCombo();
        dtProxima8 = new datechooser.beans.DateChooserCombo();
        txtMarca8 = new javax.swing.JTextField();
        txtLote8 = new javax.swing.JTextField();
        dtNueva8 = new datechooser.beans.DateChooserCombo();
        cmbHistorico8 = new javax.swing.JComboBox();
        lbNombre8 = new javax.swing.JLabel();
        alerta8 = new javax.swing.JButton();
        lbNombre7 = new javax.swing.JLabel();
        alerta7 = new javax.swing.JButton();
        lbNit39 = new javax.swing.JLabel();
        cmbHistorico7 = new javax.swing.JComboBox();
        lbNit40 = new javax.swing.JLabel();
        lbNit41 = new javax.swing.JLabel();
        dtNueva7 = new datechooser.beans.DateChooserCombo();
        lbNit42 = new javax.swing.JLabel();
        txtLote7 = new javax.swing.JTextField();
        txtMarca7 = new javax.swing.JTextField();
        lbNit43 = new javax.swing.JLabel();
        lbNit44 = new javax.swing.JLabel();
        dtProxima7 = new datechooser.beans.DateChooserCombo();
        pnlVacunasPerros = new javax.swing.JPanel();
        lbNit23 = new javax.swing.JLabel();
        lbNombre1 = new javax.swing.JLabel();
        lbNombre2 = new javax.swing.JLabel();
        lbNombre3 = new javax.swing.JLabel();
        lbNombre4 = new javax.swing.JLabel();
        lbNombre6 = new javax.swing.JLabel();
        lbNombre12 = new javax.swing.JLabel();
        lbNombre5 = new javax.swing.JLabel();
        alerta12 = new javax.swing.JButton();
        alerta6 = new javax.swing.JButton();
        alerta5 = new javax.swing.JButton();
        alerta4 = new javax.swing.JButton();
        alerta3 = new javax.swing.JButton();
        alerta2 = new javax.swing.JButton();
        lbNit15 = new javax.swing.JLabel();
        alerta1 = new javax.swing.JButton();
        cmbPuppy = new javax.swing.JComboBox();
        cmbHistorico2 = new javax.swing.JComboBox();
        cmbHistorico3 = new javax.swing.JComboBox();
        cmbHistorico4 = new javax.swing.JComboBox();
        cmbHistorico5 = new javax.swing.JComboBox();
        cmbHistorico6 = new javax.swing.JComboBox();
        cmbHistorico12 = new javax.swing.JComboBox();
        dtNueva6 = new datechooser.beans.DateChooserCombo();
        dtNueva12 = new datechooser.beans.DateChooserCombo();
        dtNueva5 = new datechooser.beans.DateChooserCombo();
        dtNueva4 = new datechooser.beans.DateChooserCombo();
        dtNueva3 = new datechooser.beans.DateChooserCombo();
        dtNueva2 = new datechooser.beans.DateChooserCombo();
        dtNueva1 = new datechooser.beans.DateChooserCombo();
        lbNit14 = new javax.swing.JLabel();
        lbNit16 = new javax.swing.JLabel();
        txtLote1 = new javax.swing.JTextField();
        txtLote2 = new javax.swing.JTextField();
        txtLote3 = new javax.swing.JTextField();
        txtLote4 = new javax.swing.JTextField();
        txtLote5 = new javax.swing.JTextField();
        txtLote6 = new javax.swing.JTextField();
        txtLote12 = new javax.swing.JTextField();
        txtMarca12 = new javax.swing.JTextField();
        txtMarca6 = new javax.swing.JTextField();
        txtMarca5 = new javax.swing.JTextField();
        txtMarca4 = new javax.swing.JTextField();
        txtMarca3 = new javax.swing.JTextField();
        txtMarca2 = new javax.swing.JTextField();
        txtMarca1 = new javax.swing.JTextField();
        lbNit17 = new javax.swing.JLabel();
        lbNit18 = new javax.swing.JLabel();
        dtProxima1 = new datechooser.beans.DateChooserCombo();
        dtProxima2 = new datechooser.beans.DateChooserCombo();
        dtProxima3 = new datechooser.beans.DateChooserCombo();
        dtProxima4 = new datechooser.beans.DateChooserCombo();
        dtProxima5 = new datechooser.beans.DateChooserCombo();
        dtProxima6 = new datechooser.beans.DateChooserCombo();
        dtProxima12 = new datechooser.beans.DateChooserCombo();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        lbEmail2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtMotivoAnt = new javax.swing.JTextArea();
        lbEmail3 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtEvolucionAnt = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtTratamientos = new javax.swing.JTextArea();
        lbEmail4 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        lbEmail14 = new javax.swing.JLabel();
        jScrollPane15 = new javax.swing.JScrollPane();
        txtMotivo = new javax.swing.JTextArea();
        lbDireccion16 = new javax.swing.JLabel();
        txtFc = new javax.swing.JTextField();
        lbDireccion17 = new javax.swing.JLabel();
        txtTllc = new javax.swing.JTextField();
        lbDireccion18 = new javax.swing.JLabel();
        txtFr = new javax.swing.JTextField();
        lbDireccion19 = new javax.swing.JLabel();
        txtT = new javax.swing.JTextField();
        lbDireccion20 = new javax.swing.JLabel();
        txtPeso = new javax.swing.JTextField();
        txtPulso = new javax.swing.JTextField();
        lbDireccion22 = new javax.swing.JLabel();
        lbDireccion23 = new javax.swing.JLabel();
        txtCc = new javax.swing.JTextField();
        lbEmail15 = new javax.swing.JLabel();
        jScrollPane23 = new javax.swing.JScrollPane();
        txtSistemas = new javax.swing.JTextArea();
        lbSistemas = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblSistemas = new javax.swing.JTable();
        lbEmail6 = new javax.swing.JLabel();
        lbDireccion24 = new javax.swing.JLabel();
        txtMucosa = new javax.swing.JTextField();
        lbDireccion25 = new javax.swing.JLabel();
        txtCaracteristicas = new javax.swing.JTextField();
        lbEmail25 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        lbEmail16 = new javax.swing.JLabel();
        jScrollPane16 = new javax.swing.JScrollPane();
        txtProblemas = new javax.swing.JTextArea();
        lbEmail17 = new javax.swing.JLabel();
        jScrollPane17 = new javax.swing.JScrollPane();
        txtDiagPresunti = new javax.swing.JTextArea();
        lbEmail27 = new javax.swing.JLabel();
        jScrollPane18 = new javax.swing.JScrollPane();
        txtPlanDiagnostico = new javax.swing.JTextArea();
        lbEmail20 = new javax.swing.JLabel();
        jScrollPane19 = new javax.swing.JScrollPane();
        txtTratamiento = new javax.swing.JTextArea();
        lbEmail21 = new javax.swing.JLabel();
        jScrollPane20 = new javax.swing.JScrollPane();
        txtRecomendaciones = new javax.swing.JTextArea();
        lbEmail28 = new javax.swing.JLabel();
        dtControl = new datechooser.beans.DateChooserCombo();
        jSeparator1 = new javax.swing.JSeparator();
        jButton9 = new javax.swing.JButton();
        lbNit6 = new javax.swing.JLabel();
        lbNit11 = new javax.swing.JLabel();
        txtDescripcionExamenConsulta = new javax.swing.JTextField();
        lbNit9 = new javax.swing.JLabel();
        txtNombreAnexoConsulta = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        jScrollPane11 = new javax.swing.JScrollPane();
        tblExamenes = new javax.swing.JTable();
        btnGuardar1 = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnReimprimir = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        lbNit4 = new javax.swing.JLabel();
        txtProfesional = new javax.swing.JTextField();
        lbEmail22 = new javax.swing.JLabel();
        jScrollPane21 = new javax.swing.JScrollPane();
        txtEvolucionSeg = new javax.swing.JTextArea();
        lbEmail23 = new javax.swing.JLabel();
        jScrollPane22 = new javax.swing.JScrollPane();
        txtObservacionesSeg = new javax.swing.JTextArea();
        lbImagenes = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        lbEmail45 = new javax.swing.JLabel();
        lbDireccion40 = new javax.swing.JLabel();
        lbDireccion39 = new javax.swing.JLabel();
        txtTllc3 = new javax.swing.JTextField();
        txtFc3 = new javax.swing.JTextField();
        lbDireccion41 = new javax.swing.JLabel();
        lbDireccion42 = new javax.swing.JLabel();
        txtT3 = new javax.swing.JTextField();
        txtFr3 = new javax.swing.JTextField();
        lbDireccion44 = new javax.swing.JLabel();
        lbDireccion43 = new javax.swing.JLabel();
        txtCc3 = new javax.swing.JTextField();
        txtPulso3 = new javax.swing.JTextField();
        lbDireccion46 = new javax.swing.JLabel();
        lbDireccion45 = new javax.swing.JLabel();
        txtMucosa1 = new javax.swing.JTextField();
        txtPeso3 = new javax.swing.JTextField();
        jScrollPane12 = new javax.swing.JScrollPane();
        tblEvoluciones = new javax.swing.JTable();
        lbNit13 = new javax.swing.JLabel();
        txtDescripcionExamenConsulta1 = new javax.swing.JTextField();
        jButton12 = new javax.swing.JButton();
        lbNit7 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        lbNit12 = new javax.swing.JLabel();
        txtNombreAnexoConsulta1 = new javax.swing.JTextField();
        jButton13 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btnGuardar3 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        lbEmail5 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        lista = new javax.swing.JList();
        lbRazon5 = new javax.swing.JLabel();
        cmbFecha = new javax.swing.JComboBox();

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        popBorrar.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        popBorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar-cancelar-icono-4935-16.png"))); // NOI18N
        popBorrar.setText("Borrar");
        popBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popBorrarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(popBorrar);

        jPopupMenu2.setComponentPopupMenu(jPopupMenu2);

        popBorrar1.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        popBorrar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar-cancelar-icono-4935-16.png"))); // NOI18N
        popBorrar1.setText("Borrar");
        popBorrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popBorrar1ActionPerformed(evt);
            }
        });
        jPopupMenu2.add(popBorrar1);

        setTitle("Factura");

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));
        pnlFormulario.setToolTipText("");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Información basica", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 18))); // NOI18N

        lbFoto.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lbFoto.setToolTipText("");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        txtRaza.setEditable(false);
        txtRaza.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
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

        lbNit.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit.setText("Codigo:");

        txtGenero.setEditable(false);
        txtGenero.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
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

        lbDireccion6.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbDireccion6.setText("Raza:");

        txtReproductivo.setEditable(false);
        txtReproductivo.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtReproductivo.setName("Edad"); // NOI18N
        txtReproductivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtReproductivoActionPerformed(evt);
            }
        });
        txtReproductivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtReproductivoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtReproductivoKeyTyped(evt);
            }
        });

        txtNombre.setEditable(false);
        txtNombre.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtNombre.setName("Nombre"); // NOI18N
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
        });

        lbDireccion4.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbDireccion4.setText("Edad: ");

        lbNit1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit1.setText("Historia:");

        txtHistoria.setEditable(false);
        txtHistoria.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtHistoria.setName("Codigo"); // NOI18N
        txtHistoria.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtHistoriaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtHistoriaKeyTyped(evt);
            }
        });

        lbEmail1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbEmail1.setText("E.reproductivo:");

        txtCodigo.setEditable(false);
        txtCodigo.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtCodigo.setName("Codigo"); // NOI18N
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoKeyTyped(evt);
            }
        });

        lbDireccion5.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbDireccion5.setText("Genero:");

        lbRazon.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbRazon.setText("Nombre:");

        lbEmail8.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbEmail8.setText("Dieta:");

        txtDieta.setEditable(false);
        txtDieta.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
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

        txtFrecuencia.setEditable(false);
        txtFrecuencia.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtFrecuencia.setName("Edad"); // NOI18N
        txtFrecuencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFrecuenciaActionPerformed(evt);
            }
        });
        txtFrecuencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFrecuenciaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFrecuenciaKeyTyped(evt);
            }
        });

        lbEmail9.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbEmail9.setText("Frecuencia:");

        txtEdad.setEditable(false);
        txtEdad.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
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

        lbEmail10.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbEmail10.setText("Temperamento:");

        txtObservaciones.setEditable(false);
        txtObservaciones.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtObservaciones.setName("Edad"); // NOI18N
        txtObservaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtObservacionesActionPerformed(evt);
            }
        });
        txtObservaciones.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtObservacionesKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtObservacionesKeyTyped(evt);
            }
        });

        lbEmail11.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbEmail11.setText("Especie:");

        txtEspecie.setEditable(false);
        txtEspecie.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
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

        lbEmail12.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbEmail12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbEmail12.setText("Observaciones de la mascota");

        txtObv.setColumns(20);
        txtObv.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtObv.setRows(3);
        jScrollPane1.setViewportView(txtObv);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator8, javax.swing.GroupLayout.DEFAULT_SIZE, 684, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbRazon, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
                            .addComponent(lbNit1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtNombre, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtHistoria, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(lbNit)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbDireccion5, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbDireccion6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtGenero, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                            .addComponent(txtRaza)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(lbEmail10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtObservaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbEmail9, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbEmail1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtReproductivo, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(txtFrecuencia, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbEmail8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbEmail11, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
                            .addComponent(lbDireccion4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtEspecie, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                            .addComponent(txtDieta)
                            .addComponent(txtEdad)))
                    .addComponent(jSeparator9, javax.swing.GroupLayout.DEFAULT_SIZE, 684, Short.MAX_VALUE)
                    .addComponent(lbEmail12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtHistoria, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                        .addComponent(lbNit)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbDireccion6)
                        .addComponent(txtRaza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbNit1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbRazon)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbDireccion5)
                        .addComponent(txtGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbEmail1)
                    .addComponent(txtReproductivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbDireccion4)
                    .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbEmail9)
                    .addComponent(txtFrecuencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbEmail8)
                    .addComponent(txtDieta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbEmail10)
                    .addComponent(txtObservaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbEmail11)
                    .addComponent(txtEspecie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbEmail12)
                .addGap(5, 5, 5)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Propietario", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 14))); // NOI18N

        txtDireccion.setEditable(false);
        txtDireccion.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtDireccion.setName("Edad"); // NOI18N
        txtDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDireccionActionPerformed(evt);
            }
        });
        txtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDireccionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDireccionKeyTyped(evt);
            }
        });

        lbNit2.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit2.setText("Cedula:");

        lbDireccion9.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbDireccion9.setText("Direccion:");

        txtNombrePropietario.setEditable(false);
        txtNombrePropietario.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
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

        lbNit3.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit3.setText("Telefono:");

        txtTelefono.setEditable(false);
        txtTelefono.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtTelefono.setName("Codigo"); // NOI18N
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });

        txtCedula.setEditable(false);
        txtCedula.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtCedula.setName("Codigo"); // NOI18N
        txtCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCedulaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCedulaKeyTyped(evt);
            }
        });

        lbRazon1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbRazon1.setText("Nombre:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(lbNit2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(lbNit3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTelefono)))
                .addGap(27, 27, 27)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbDireccion9, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                    .addComponent(lbRazon1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombrePropietario)
                    .addComponent(txtDireccion))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNit2)
                    .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbRazon1)
                    .addComponent(txtNombrePropietario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDireccion9)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNit3)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/anterior_2.png"))); // NOI18N
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Volver");
        jLabel11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbFoto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel9)
                .addGap(0, 0, 0)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbFoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        btnGuardar2.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardar2.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnGuardar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardar2.setText("GUARDAR VACUNAS");
        btnGuardar2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardar2.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnGuardar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar2ActionPerformed(evt);
            }
        });

        pnlVacunasGatos.setBackground(new java.awt.Color(255, 255, 255));

        try {
            dtProxima13.setDefaultPeriods(new datechooser.model.multiple.PeriodSet());
        } catch (datechooser.model.exeptions.IncompatibleDataExeption e1) {
            e1.printStackTrace();
        }
        dtProxima13.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 12));

        txtMarca13.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        txtLote13.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        try {
            dtNueva13.setDefaultPeriods(new datechooser.model.multiple.PeriodSet());
        } catch (datechooser.model.exeptions.IncompatibleDataExeption e1) {
            e1.printStackTrace();
        }
        dtNueva13.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 12));

        cmbHistorico13.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbHistorico13ItemStateChanged(evt);
            }
        });

        alerta13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alerta13ActionPerformed(evt);
            }
        });

        lbNombre13.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNombre13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNombre13.setText("Desparasitante");

        lbNombre11.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNombre11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNombre11.setText("Otros");

        cmbHistorico11.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbHistorico11ItemStateChanged(evt);
            }
        });
        cmbHistorico11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbHistorico11ActionPerformed(evt);
            }
        });

        try {
            dtNueva11.setDefaultPeriods(new datechooser.model.multiple.PeriodSet());
        } catch (datechooser.model.exeptions.IncompatibleDataExeption e1) {
            e1.printStackTrace();
        }
        dtNueva11.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 12));

        txtLote11.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        txtMarca11.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        try {
            dtProxima11.setDefaultPeriods(new datechooser.model.multiple.PeriodSet());
        } catch (datechooser.model.exeptions.IncompatibleDataExeption e1) {
            e1.printStackTrace();
        }
        dtProxima11.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 12));

        try {
            dtProxima10.setDefaultPeriods(new datechooser.model.multiple.PeriodSet());
        } catch (datechooser.model.exeptions.IncompatibleDataExeption e1) {
            e1.printStackTrace();
        }
        dtProxima10.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 12));

        txtMarca10.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        txtLote10.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        try {
            dtNueva10.setDefaultPeriods(new datechooser.model.multiple.PeriodSet());
        } catch (datechooser.model.exeptions.IncompatibleDataExeption e1) {
            e1.printStackTrace();
        }
        dtNueva10.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 12));

        cmbHistorico10.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbHistorico10ItemStateChanged(evt);
            }
        });

        lbNombre10.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNombre10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNombre10.setText("Rabia");

        lbNombre9.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNombre9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNombre9.setText("Leucemia");

        cmbHistorico9.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbHistorico9ItemStateChanged(evt);
            }
        });

        try {
            dtNueva9.setDefaultPeriods(new datechooser.model.multiple.PeriodSet());
        } catch (datechooser.model.exeptions.IncompatibleDataExeption e1) {
            e1.printStackTrace();
        }
        dtNueva9.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 12));

        txtLote9.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        txtMarca9.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        try {
            dtProxima9.setDefaultPeriods(new datechooser.model.multiple.PeriodSet());
        } catch (datechooser.model.exeptions.IncompatibleDataExeption e1) {
            e1.printStackTrace();
        }
        dtProxima9.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 12));

        try {
            dtProxima8.setDefaultPeriods(new datechooser.model.multiple.PeriodSet());
        } catch (datechooser.model.exeptions.IncompatibleDataExeption e1) {
            e1.printStackTrace();
        }
        dtProxima8.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 12));

        txtMarca8.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        txtLote8.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        try {
            dtNueva8.setDefaultPeriods(new datechooser.model.multiple.PeriodSet());
        } catch (datechooser.model.exeptions.IncompatibleDataExeption e1) {
            e1.printStackTrace();
        }
        dtNueva8.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 12));

        cmbHistorico8.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbHistorico8ItemStateChanged(evt);
            }
        });

        lbNombre8.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNombre8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNombre8.setText("Triple + rabia");

        lbNombre7.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNombre7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNombre7.setText("Triple");

        alerta7.setForeground(new java.awt.Color(204, 0, 0));

        lbNit39.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit39.setText("NOMBRE");

        cmbHistorico7.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbHistorico7ItemStateChanged(evt);
            }
        });

        lbNit40.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit40.setText("HISTORICO");

        lbNit41.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit41.setText("NUEVA");

        try {
            dtNueva7.setDefaultPeriods(new datechooser.model.multiple.PeriodSet());
        } catch (datechooser.model.exeptions.IncompatibleDataExeption e1) {
            e1.printStackTrace();
        }
        dtNueva7.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 12));
        dtNueva7.addCommitListener(new datechooser.events.CommitListener() {
            public void onCommit(datechooser.events.CommitEvent evt) {
                dtNueva7OnCommit(evt);
            }
        });
        dtNueva7.addSelectionChangedListener(new datechooser.events.SelectionChangedListener() {
            public void onSelectionChange(datechooser.events.SelectionChangedEvent evt) {
                dtNueva7OnSelectionChange(evt);
            }
        });

        lbNit42.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit42.setText("LOTE");

        txtLote7.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        txtMarca7.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        lbNit43.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit43.setText("MARCA");

        lbNit44.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit44.setText("PROXIMA");

        try {
            dtProxima7.setDefaultPeriods(new datechooser.model.multiple.PeriodSet());
        } catch (datechooser.model.exeptions.IncompatibleDataExeption e1) {
            e1.printStackTrace();
        }
        dtProxima7.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 12));
        dtProxima7.addCommitListener(new datechooser.events.CommitListener() {
            public void onCommit(datechooser.events.CommitEvent evt) {
                dtProxima7OnCommit(evt);
            }
        });
        dtProxima7.addSelectionChangedListener(new datechooser.events.SelectionChangedListener() {
            public void onSelectionChange(datechooser.events.SelectionChangedEvent evt) {
                dtProxima7OnSelectionChange(evt);
            }
        });

        javax.swing.GroupLayout pnlVacunasGatosLayout = new javax.swing.GroupLayout(pnlVacunasGatos);
        pnlVacunasGatos.setLayout(pnlVacunasGatosLayout);
        pnlVacunasGatosLayout.setHorizontalGroup(
            pnlVacunasGatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVacunasGatosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlVacunasGatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlVacunasGatosLayout.createSequentialGroup()
                        .addComponent(lbNit39, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(lbNit40, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbNit41, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                        .addGap(182, 182, 182))
                    .addGroup(pnlVacunasGatosLayout.createSequentialGroup()
                        .addGroup(pnlVacunasGatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlVacunasGatosLayout.createSequentialGroup()
                                .addGroup(pnlVacunasGatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbNombre10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbNombre9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbNombre8, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                                    .addComponent(lbNombre7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlVacunasGatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(alerta10, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(alerta9, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(alerta8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(alerta7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlVacunasGatosLayout.createSequentialGroup()
                                .addComponent(lbNombre11, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(alerta11, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlVacunasGatosLayout.createSequentialGroup()
                                .addComponent(lbNombre13, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(alerta13, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlVacunasGatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlVacunasGatosLayout.createSequentialGroup()
                                .addComponent(cmbHistorico7, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlVacunasGatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(dtNueva8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(dtNueva9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(dtNueva7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlVacunasGatosLayout.createSequentialGroup()
                                .addGroup(pnlVacunasGatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cmbHistorico13, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbHistorico9, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlVacunasGatosLayout.createSequentialGroup()
                                        .addComponent(cmbHistorico10, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(cmbHistorico8, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbHistorico11, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlVacunasGatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(dtNueva10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(dtNueva11, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                                    .addComponent(dtNueva13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlVacunasGatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtLote7, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtLote8)
                            .addComponent(txtLote9)
                            .addComponent(txtLote10)
                            .addComponent(txtLote11, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbNit42, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                            .addComponent(txtLote13))
                        .addGap(10, 10, 10)))
                .addGroup(pnlVacunasGatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNit43, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                    .addComponent(txtMarca8)
                    .addComponent(txtMarca9)
                    .addComponent(txtMarca10)
                    .addComponent(txtMarca11)
                    .addComponent(txtMarca7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtMarca13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlVacunasGatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbNit44, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                    .addComponent(dtProxima8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(dtProxima9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(dtProxima10, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(dtProxima11, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(dtProxima13, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(dtProxima7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlVacunasGatosLayout.setVerticalGroup(
            pnlVacunasGatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVacunasGatosLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlVacunasGatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNit39)
                    .addComponent(lbNit40, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNit41)
                    .addComponent(lbNit43)
                    .addComponent(lbNit44)
                    .addComponent(lbNit42, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(pnlVacunasGatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlVacunasGatosLayout.createSequentialGroup()
                        .addGroup(pnlVacunasGatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtMarca7)
                            .addComponent(txtLote7)
                            .addComponent(dtNueva7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbNombre7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbHistorico7, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dtProxima7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(pnlVacunasGatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dtProxima8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(alerta8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlVacunasGatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtLote8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtMarca8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(dtNueva8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbHistorico8)
                            .addComponent(lbNombre8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(pnlVacunasGatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cmbHistorico9, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dtNueva9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtLote9, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMarca9, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(alerta9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dtProxima9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbNombre9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(pnlVacunasGatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dtNueva10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbHistorico10)
                            .addComponent(alerta10, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbNombre10, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dtProxima10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pnlVacunasGatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtLote10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtMarca10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(3, 3, 3)
                        .addGroup(pnlVacunasGatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(alerta11, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dtProxima11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMarca11)
                            .addComponent(txtLote11)
                            .addComponent(dtNueva11, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                            .addComponent(cmbHistorico11)
                            .addComponent(lbNombre11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(alerta7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(pnlVacunasGatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(alerta13)
                    .addComponent(lbNombre13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dtNueva13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtLote13, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(txtMarca13)
                    .addComponent(dtProxima13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbHistorico13, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(5, 5, 5))
        );

        pnlVacunasPerros.setBackground(new java.awt.Color(255, 255, 255));

        lbNit23.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit23.setText("NOMBRE");

        lbNombre1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNombre1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNombre1.setText("Puppy");

        lbNombre2.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNombre2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNombre2.setText("Penta");

        lbNombre3.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNombre3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNombre3.setText("Hexa");

        lbNombre4.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNombre4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNombre4.setText("Rabia");

        lbNombre6.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNombre6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNombre6.setText("Otros");

        lbNombre12.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNombre12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNombre12.setText("Desparasitante");

        lbNombre5.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNombre5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNombre5.setText("KC (Tos perreras)");

        alerta12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alerta12ActionPerformed(evt);
            }
        });

        alerta6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alerta6ActionPerformed(evt);
            }
        });

        alerta5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alerta5ActionPerformed(evt);
            }
        });

        lbNit15.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit15.setText("HISTORICO");

        alerta1.setForeground(new java.awt.Color(204, 0, 0));

        cmbPuppy.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbPuppyItemStateChanged(evt);
            }
        });

        cmbHistorico2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbHistorico2ItemStateChanged(evt);
            }
        });

        cmbHistorico3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbHistorico3ItemStateChanged(evt);
            }
        });

        cmbHistorico4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbHistorico4ItemStateChanged(evt);
            }
        });

        cmbHistorico5.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbHistorico5ItemStateChanged(evt);
            }
        });

        cmbHistorico6.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbHistorico6ItemStateChanged(evt);
            }
        });

        cmbHistorico12.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbHistorico12ItemStateChanged(evt);
            }
        });

        try {
            dtNueva6.setDefaultPeriods(new datechooser.model.multiple.PeriodSet());
        } catch (datechooser.model.exeptions.IncompatibleDataExeption e1) {
            e1.printStackTrace();
        }
        dtNueva6.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 12));

        try {
            dtNueva12.setDefaultPeriods(new datechooser.model.multiple.PeriodSet());
        } catch (datechooser.model.exeptions.IncompatibleDataExeption e1) {
            e1.printStackTrace();
        }
        dtNueva12.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 12));

        try {
            dtNueva5.setDefaultPeriods(new datechooser.model.multiple.PeriodSet());
        } catch (datechooser.model.exeptions.IncompatibleDataExeption e1) {
            e1.printStackTrace();
        }
        dtNueva5.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 12));

        try {
            dtNueva4.setDefaultPeriods(new datechooser.model.multiple.PeriodSet());
        } catch (datechooser.model.exeptions.IncompatibleDataExeption e1) {
            e1.printStackTrace();
        }
        dtNueva4.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 12));

        try {
            dtNueva3.setDefaultPeriods(new datechooser.model.multiple.PeriodSet());
        } catch (datechooser.model.exeptions.IncompatibleDataExeption e1) {
            e1.printStackTrace();
        }
        dtNueva3.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 12));

        try {
            dtNueva2.setDefaultPeriods(new datechooser.model.multiple.PeriodSet());
        } catch (datechooser.model.exeptions.IncompatibleDataExeption e1) {
            e1.printStackTrace();
        }
        dtNueva2.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 12));

        try {
            dtNueva1.setDefaultPeriods(new datechooser.model.multiple.PeriodSet());
        } catch (datechooser.model.exeptions.IncompatibleDataExeption e1) {
            e1.printStackTrace();
        }
        dtNueva1.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 12));
        dtNueva1.addCommitListener(new datechooser.events.CommitListener() {
            public void onCommit(datechooser.events.CommitEvent evt) {
                dtNueva1OnCommit(evt);
            }
        });
        dtNueva1.addSelectionChangedListener(new datechooser.events.SelectionChangedListener() {
            public void onSelectionChange(datechooser.events.SelectionChangedEvent evt) {
                dtNueva1OnSelectionChange(evt);
            }
        });

        lbNit14.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit14.setText("NUEVA");

        lbNit16.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit16.setText("LOTE");

        txtLote1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        txtLote2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        txtLote3.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        txtLote4.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        txtLote5.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        txtLote6.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        txtLote12.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        txtMarca12.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        txtMarca6.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        txtMarca5.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        txtMarca4.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        txtMarca3.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        txtMarca2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        txtMarca1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        lbNit17.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit17.setText("MARCA");

        lbNit18.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit18.setText("PROXIMA");

        try {
            dtProxima1.setDefaultPeriods(new datechooser.model.multiple.PeriodSet());
        } catch (datechooser.model.exeptions.IncompatibleDataExeption e1) {
            e1.printStackTrace();
        }
        dtProxima1.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 12));
        dtProxima1.addCommitListener(new datechooser.events.CommitListener() {
            public void onCommit(datechooser.events.CommitEvent evt) {
                dtProxima1OnCommit(evt);
            }
        });
        dtProxima1.addSelectionChangedListener(new datechooser.events.SelectionChangedListener() {
            public void onSelectionChange(datechooser.events.SelectionChangedEvent evt) {
                dtProxima1OnSelectionChange(evt);
            }
        });

        try {
            dtProxima2.setDefaultPeriods(new datechooser.model.multiple.PeriodSet());
        } catch (datechooser.model.exeptions.IncompatibleDataExeption e1) {
            e1.printStackTrace();
        }
        dtProxima2.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 12));

        try {
            dtProxima3.setDefaultPeriods(new datechooser.model.multiple.PeriodSet());
        } catch (datechooser.model.exeptions.IncompatibleDataExeption e1) {
            e1.printStackTrace();
        }
        dtProxima3.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 12));

        try {
            dtProxima4.setDefaultPeriods(new datechooser.model.multiple.PeriodSet());
        } catch (datechooser.model.exeptions.IncompatibleDataExeption e1) {
            e1.printStackTrace();
        }
        dtProxima4.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 12));

        try {
            dtProxima5.setDefaultPeriods(new datechooser.model.multiple.PeriodSet());
        } catch (datechooser.model.exeptions.IncompatibleDataExeption e1) {
            e1.printStackTrace();
        }
        dtProxima5.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 12));

        try {
            dtProxima6.setDefaultPeriods(new datechooser.model.multiple.PeriodSet());
        } catch (datechooser.model.exeptions.IncompatibleDataExeption e1) {
            e1.printStackTrace();
        }
        dtProxima6.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 12));

        try {
            dtProxima12.setDefaultPeriods(new datechooser.model.multiple.PeriodSet());
        } catch (datechooser.model.exeptions.IncompatibleDataExeption e1) {
            e1.printStackTrace();
        }
        dtProxima12.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 12));

        javax.swing.GroupLayout pnlVacunasPerrosLayout = new javax.swing.GroupLayout(pnlVacunasPerros);
        pnlVacunasPerros.setLayout(pnlVacunasPerrosLayout);
        pnlVacunasPerrosLayout.setHorizontalGroup(
            pnlVacunasPerrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVacunasPerrosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlVacunasPerrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbNit23, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNombre1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNombre2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNombre3, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNombre12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNombre6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNombre5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNombre4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlVacunasPerrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbNit15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlVacunasPerrosLayout.createSequentialGroup()
                        .addGroup(pnlVacunasPerrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlVacunasPerrosLayout.createSequentialGroup()
                                .addGroup(pnlVacunasPerrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(alerta1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(alerta2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(alerta3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(alerta4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(alerta5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(pnlVacunasPerrosLayout.createSequentialGroup()
                                .addComponent(alerta12, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)))
                        .addGroup(pnlVacunasPerrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbHistorico12, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbPuppy, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbHistorico3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbHistorico2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbHistorico4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbHistorico5, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbHistorico6, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(alerta6, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlVacunasPerrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbNit14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dtNueva1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dtNueva2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(dtNueva3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(dtNueva4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(dtNueva5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(dtNueva6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dtNueva12, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlVacunasPerrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtLote6, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtLote5, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtLote3, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtLote1)
                    .addComponent(lbNit16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtLote2)
                    .addComponent(txtLote4)
                    .addComponent(txtLote12, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlVacunasPerrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMarca12)
                    .addComponent(txtMarca5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtMarca4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtMarca3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtMarca2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtMarca1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtMarca6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbNit17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlVacunasPerrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dtProxima2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(dtProxima1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(dtProxima3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(dtProxima4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(dtProxima6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(dtProxima5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(dtProxima12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                    .addComponent(lbNit18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlVacunasPerrosLayout.setVerticalGroup(
            pnlVacunasPerrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVacunasPerrosLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlVacunasPerrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNit23)
                    .addComponent(lbNit14)
                    .addComponent(lbNit18)
                    .addComponent(lbNit15, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNit17)
                    .addComponent(lbNit16))
                .addGap(3, 3, 3)
                .addGroup(pnlVacunasPerrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(dtNueva1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNombre1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(alerta1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dtProxima1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtLote1)
                    .addComponent(txtMarca1, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(cmbPuppy))
                .addGap(3, 3, 3)
                .addGroup(pnlVacunasPerrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlVacunasPerrosLayout.createSequentialGroup()
                        .addGroup(pnlVacunasPerrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(dtProxima2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlVacunasPerrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(cmbHistorico2)
                                .addComponent(dtNueva2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlVacunasPerrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtLote2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMarca2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(lbNombre2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(pnlVacunasPerrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtLote3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dtNueva3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMarca3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dtProxima3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(alerta3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbHistorico3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbNombre3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(pnlVacunasPerrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbNombre4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(alerta4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlVacunasPerrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(cmbHistorico4, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(dtNueva4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtLote4, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtMarca4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(dtProxima4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(alerta2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(pnlVacunasPerrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbNombre5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMarca5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dtProxima5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(alerta5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlVacunasPerrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(dtNueva5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtLote5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cmbHistorico5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(pnlVacunasPerrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(alerta6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbHistorico6)
                    .addComponent(dtNueva6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlVacunasPerrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtLote6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtMarca6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dtProxima6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNombre6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(pnlVacunasPerrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(alerta12, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbHistorico12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlVacunasPerrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtLote12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtMarca12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dtProxima12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNombre12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dtNueva12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        jButton1.setBackground(new java.awt.Color(247, 220, 111));
        jButton1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/imprimir.png"))); // NOI18N
        jButton1.setText("HISTORIAL DE VACUNACIÓN");
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlVacunasGatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlVacunasPerros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(17, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardar2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(pnlVacunasPerros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlVacunasGatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnGuardar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Vacunación y Desparasitación", jPanel7);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        lbEmail2.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbEmail2.setText("Diagnosticos:");

        txtMotivoAnt.setColumns(20);
        txtMotivoAnt.setLineWrap(true);
        txtMotivoAnt.setRows(3);
        txtMotivoAnt.setName("Motivos"); // NOI18N
        txtMotivoAnt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMotivoAntKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(txtMotivoAnt);

        lbEmail3.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbEmail3.setText("Evolución:");

        txtEvolucionAnt.setColumns(20);
        txtEvolucionAnt.setLineWrap(true);
        txtEvolucionAnt.setRows(3);
        txtEvolucionAnt.setName("Evolución"); // NOI18N
        txtEvolucionAnt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEvolucionAntKeyReleased(evt);
            }
        });
        jScrollPane4.setViewportView(txtEvolucionAnt);

        txtTratamientos.setColumns(20);
        txtTratamientos.setLineWrap(true);
        txtTratamientos.setRows(3);
        txtTratamientos.setName("Tratamientos"); // NOI18N
        jScrollPane5.setViewportView(txtTratamientos);

        lbEmail4.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbEmail4.setText("Tratamientos:");

        btnGuardar.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardar.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardar.setText("GUARDAR ANTECEDENTES");
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbEmail3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbEmail2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                    .addComponent(lbEmail4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 898, Short.MAX_VALUE)
                    .addComponent(jScrollPane4)
                    .addComponent(jScrollPane5))
                .addContainerGap(27, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGuardar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbEmail2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addComponent(lbEmail3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbEmail4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnGuardar)
                .addContainerGap(124, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Antecedentes", jPanel4);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        lbEmail14.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbEmail14.setText("Anamnesis:");

        txtMotivo.setColumns(20);
        txtMotivo.setLineWrap(true);
        txtMotivo.setRows(2);
        txtMotivo.setName("Motivo"); // NOI18N
        txtMotivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMotivoKeyReleased(evt);
            }
        });
        jScrollPane15.setViewportView(txtMotivo);

        lbDireccion16.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion16.setText("FC:");

        txtFc.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtFc.setName("FC"); // NOI18N
        txtFc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFcActionPerformed(evt);
            }
        });
        txtFc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFcKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFcKeyTyped(evt);
            }
        });

        lbDireccion17.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion17.setText("TLLC:");

        txtTllc.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtTllc.setName("TLLC"); // NOI18N
        txtTllc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTllcActionPerformed(evt);
            }
        });
        txtTllc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTllcKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTllcKeyTyped(evt);
            }
        });

        lbDireccion18.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion18.setText("FR:");

        txtFr.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtFr.setName("FR"); // NOI18N
        txtFr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFrActionPerformed(evt);
            }
        });
        txtFr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFrKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFrKeyTyped(evt);
            }
        });

        lbDireccion19.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion19.setText("Tº:");

        txtT.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtT.setName("Tº"); // NOI18N
        txtT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTActionPerformed(evt);
            }
        });
        txtT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTKeyTyped(evt);
            }
        });

        lbDireccion20.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion20.setText("Peso:");

        txtPeso.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtPeso.setName("Peso"); // NOI18N
        txtPeso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPesoActionPerformed(evt);
            }
        });
        txtPeso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPesoKeyTyped(evt);
            }
        });

        txtPulso.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtPulso.setName("Pulso"); // NOI18N
        txtPulso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPulsoActionPerformed(evt);
            }
        });
        txtPulso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPulsoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPulsoKeyTyped(evt);
            }
        });

        lbDireccion22.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion22.setText("Pulso:");

        lbDireccion23.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion23.setText("CC:");

        txtCc.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtCc.setName("CC"); // NOI18N
        txtCc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCcActionPerformed(evt);
            }
        });
        txtCc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCcKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCcKeyTyped(evt);
            }
        });

        lbEmail15.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbEmail15.setText("Examen fisico:");

        txtSistemas.setColumns(20);
        txtSistemas.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        txtSistemas.setLineWrap(true);
        txtSistemas.setRows(2);
        txtSistemas.setName("Tratamientos"); // NOI18N
        txtSistemas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSistemasKeyReleased(evt);
            }
        });
        jScrollPane23.setViewportView(txtSistemas);

        lbSistemas.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbSistemas.setText("  ");

        tblSistemas.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        tblSistemas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"    Nervioso:", "", "    Musculo esquelético:", ""},
                {"    Digestivo:", "", "    Piel y anexos:", ""},
                {"    Respiratorio:", "", "    Organos sentidos:", ""},
                {"    Cardiaco:", "", "    Ganglios linfaticos:", ""},
                {"    Urinario:", "", "    Reproductivo:", ""},
                {"    Endocrino:", null, null, null}
            },
            new String [] {
                "", "", "", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSistemas.setRowHeight(24);
        tblSistemas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSistemasMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblSistemasMousePressed(evt);
            }
        });
        jScrollPane7.setViewportView(tblSistemas);
        if (tblSistemas.getColumnModel().getColumnCount() > 0) {
            tblSistemas.getColumnModel().getColumn(1).setMinWidth(35);
            tblSistemas.getColumnModel().getColumn(1).setPreferredWidth(35);
            tblSistemas.getColumnModel().getColumn(1).setMaxWidth(35);
            tblSistemas.getColumnModel().getColumn(3).setMinWidth(35);
            tblSistemas.getColumnModel().getColumn(3).setPreferredWidth(35);
            tblSistemas.getColumnModel().getColumn(3).setMaxWidth(35);
        }

        lbEmail6.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbEmail6.setText("Examen por sistemas");

        lbDireccion24.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbDireccion24.setText("Caracteristicas:");

        txtMucosa.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtMucosa.setName("Actitud"); // NOI18N
        txtMucosa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMucosaActionPerformed(evt);
            }
        });
        txtMucosa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMucosaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMucosaKeyTyped(evt);
            }
        });

        lbDireccion25.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion25.setText("Mucosa:");

        txtCaracteristicas.setName("TLLC"); // NOI18N
        txtCaracteristicas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCaracteristicasActionPerformed(evt);
            }
        });
        txtCaracteristicas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCaracteristicasKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCaracteristicasKeyTyped(evt);
            }
        });

        lbEmail25.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbEmail25.setText("(Motivo consulta)");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbEmail25)
                            .addComponent(lbEmail15, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbDireccion24))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane15)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbDireccion17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbDireccion16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(5, 5, 5)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtFc, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                                    .addComponent(txtTllc))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbDireccion19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbDireccion18))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtFr, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbDireccion23, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbDireccion22, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPulso, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCc, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbDireccion20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbDireccion25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMucosa, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtCaracteristicas)))
                    .addComponent(lbEmail6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(lbEmail14, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 933, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(lbSistemas, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane23)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(lbEmail14)
                        .addGap(0, 0, 0)
                        .addComponent(lbEmail25)
                        .addGap(14, 14, 14))
                    .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDireccion16, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbEmail15)
                    .addComponent(txtFc, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbDireccion18, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFr, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbDireccion22, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPulso, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbDireccion20, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDireccion17, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTllc, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbDireccion19, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtT, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbDireccion23, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCc, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbDireccion25, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMucosa, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbDireccion24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCaracteristicas, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbEmail6)
                .addGap(3, 3, 3)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane23, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                    .addComponent(lbSistemas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Consulta", jPanel8);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        lbEmail16.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbEmail16.setText("Problemas");

        txtProblemas.setColumns(20);
        txtProblemas.setLineWrap(true);
        txtProblemas.setRows(2);
        txtProblemas.setName("Problemas"); // NOI18N
        txtProblemas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProblemasKeyReleased(evt);
            }
        });
        jScrollPane16.setViewportView(txtProblemas);

        lbEmail17.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbEmail17.setText("Diag. Diferencial");

        txtDiagPresunti.setColumns(20);
        txtDiagPresunti.setLineWrap(true);
        txtDiagPresunti.setRows(2);
        txtDiagPresunti.setName("Diagnosticos presuntivos"); // NOI18N
        txtDiagPresunti.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDiagPresuntiKeyReleased(evt);
            }
        });
        jScrollPane17.setViewportView(txtDiagPresunti);

        lbEmail27.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbEmail27.setText("Diag. Definitivo");

        txtPlanDiagnostico.setColumns(20);
        txtPlanDiagnostico.setLineWrap(true);
        txtPlanDiagnostico.setRows(2);
        txtPlanDiagnostico.setName("Plan diagnostico"); // NOI18N
        txtPlanDiagnostico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPlanDiagnosticoKeyReleased(evt);
            }
        });
        jScrollPane18.setViewportView(txtPlanDiagnostico);

        lbEmail20.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbEmail20.setText("Tratamiento");

        txtTratamiento.setColumns(20);
        txtTratamiento.setLineWrap(true);
        txtTratamiento.setRows(2);
        txtTratamiento.setName("Tratamiento"); // NOI18N
        txtTratamiento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTratamientoKeyReleased(evt);
            }
        });
        jScrollPane19.setViewportView(txtTratamiento);

        lbEmail21.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbEmail21.setText("Recomendaciones:");

        txtRecomendaciones.setColumns(20);
        txtRecomendaciones.setLineWrap(true);
        txtRecomendaciones.setRows(2);
        txtRecomendaciones.setName("Recomendaciones"); // NOI18N
        txtRecomendaciones.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRecomendacionesKeyReleased(evt);
            }
        });
        jScrollPane20.setViewportView(txtRecomendaciones);

        lbEmail28.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbEmail28.setText("Proximo control:");

        try {
            dtControl.setDefaultPeriods(new datechooser.model.multiple.PeriodSet());
        } catch (datechooser.model.exeptions.IncompatibleDataExeption e1) {
            e1.printStackTrace();
        }
        dtControl.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 12));
        dtControl.addCommitListener(new datechooser.events.CommitListener() {
            public void onCommit(datechooser.events.CommitEvent evt) {
                dtControlOnCommit(evt);
            }
        });
        dtControl.addSelectionChangedListener(new datechooser.events.SelectionChangedListener() {
            public void onSelectionChange(datechooser.events.SelectionChangedEvent evt) {
                dtControlOnSelectionChange(evt);
            }
        });

        jButton9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton9.setText("Seleccionar Archivo");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        lbNit6.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit6.setText("Anexos:");

        lbNit11.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit11.setText("Descripción:");

        txtDescripcionExamenConsulta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescripcionExamenConsultaKeyReleased(evt);
            }
        });

        lbNit9.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit9.setText("Nombre del archivo:");

        txtNombreAnexoConsulta.setEditable(false);
        txtNombreAnexoConsulta.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jButton10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton10.setText("Agregar");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        tblExamenes.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        tblExamenes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Descripción del examen", "url"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblExamenes.setRowHeight(19);
        tblExamenes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblExamenesMouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(tblExamenes);
        if (tblExamenes.getColumnModel().getColumnCount() > 0) {
            tblExamenes.getColumnModel().getColumn(2).setMinWidth(0);
            tblExamenes.getColumnModel().getColumn(2).setPreferredWidth(0);
            tblExamenes.getColumnModel().getColumn(2).setMaxWidth(0);
        }

        btnGuardar1.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardar1.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnGuardar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardar1.setText("GUARDAR CONSULTA");
        btnGuardar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardar1.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnGuardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar1ActionPerformed(evt);
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

        btnReimprimir.setBackground(new java.awt.Color(247, 220, 111));
        btnReimprimir.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnReimprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/imprimir.png"))); // NOI18N
        btnReimprimir.setText("REIMPRIMIR CONSULTA");
        btnReimprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReimprimir.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnReimprimir.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnReimprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReimprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap(60, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane11)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbNit11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbNit6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jButton9)
                                .addGap(44, 44, 44)
                                .addComponent(lbNit9, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtNombreAnexoConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtDescripcionExamenConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 691, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 48, Short.MAX_VALUE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbEmail28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbEmail16, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbEmail17, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbEmail21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbEmail20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbEmail27, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane20, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane18)
                            .addComponent(jScrollPane17)
                            .addComponent(jScrollPane16)
                            .addComponent(jScrollPane19, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(dtControl, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addComponent(jSeparator1))
                .addContainerGap(60, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnReimprimir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardar1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbEmail16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbEmail17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbEmail27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbEmail20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbEmail21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbEmail28, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dtControl, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbNit6)
                            .addComponent(lbNit9)
                            .addComponent(txtNombreAnexoConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton9)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtDescripcionExamenConsulta)
                        .addComponent(jButton10))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbNit11)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar1)
                    .addComponent(btnModificar)
                    .addComponent(btnReimprimir))
                .addGap(38, 38, 38))
        );

        jTabbedPane1.addTab("Seguimiento Consulta", jPanel10);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        lbNit4.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit4.setText("Profesional:");

        txtProfesional.setEditable(false);
        txtProfesional.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtProfesional.setName("Nit"); // NOI18N
        txtProfesional.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProfesionalKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtProfesionalKeyTyped(evt);
            }
        });

        lbEmail22.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbEmail22.setText("Evolución:      *");

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

        lbEmail23.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbEmail23.setText("Observaciones: *");

        txtObservacionesSeg.setColumns(20);
        txtObservacionesSeg.setLineWrap(true);
        txtObservacionesSeg.setRows(3);
        txtObservacionesSeg.setName("Observaciones"); // NOI18N
        txtObservacionesSeg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtObservacionesSegKeyReleased(evt);
            }
        });
        jScrollPane22.setViewportView(txtObservacionesSeg);

        lbImagenes.setForeground(new java.awt.Color(255, 255, 255));

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        lbEmail45.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbEmail45.setText("Examen fisico:");

        lbDireccion40.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbDireccion40.setText("FC:");

        lbDireccion39.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbDireccion39.setText("TLLC:");

        txtTllc3.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtTllc3.setName("TLLC"); // NOI18N
        txtTllc3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTllc3ActionPerformed(evt);
            }
        });
        txtTllc3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTllc3KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTllc3KeyTyped(evt);
            }
        });

        txtFc3.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtFc3.setName("FC"); // NOI18N
        txtFc3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFc3ActionPerformed(evt);
            }
        });
        txtFc3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFc3KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFc3KeyTyped(evt);
            }
        });

        lbDireccion41.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbDireccion41.setText("FR:");

        lbDireccion42.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbDireccion42.setText("Tº:");

        txtT3.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtT3.setName("Tº"); // NOI18N
        txtT3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtT3ActionPerformed(evt);
            }
        });
        txtT3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtT3KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtT3KeyTyped(evt);
            }
        });

        txtFr3.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtFr3.setName("FR"); // NOI18N
        txtFr3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFr3ActionPerformed(evt);
            }
        });
        txtFr3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFr3KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFr3KeyTyped(evt);
            }
        });

        lbDireccion44.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbDireccion44.setText("Pulso:");

        lbDireccion43.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbDireccion43.setText("CC:");

        txtCc3.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCc3.setName("CC"); // NOI18N
        txtCc3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCc3ActionPerformed(evt);
            }
        });
        txtCc3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCc3KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCc3KeyTyped(evt);
            }
        });

        txtPulso3.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtPulso3.setName("Pulso"); // NOI18N
        txtPulso3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPulso3ActionPerformed(evt);
            }
        });
        txtPulso3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPulso3KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPulso3KeyTyped(evt);
            }
        });

        lbDireccion46.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbDireccion46.setText("Peso:");

        lbDireccion45.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbDireccion45.setText("Mucosa:");

        txtMucosa1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtMucosa1.setName("Actitud"); // NOI18N
        txtMucosa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMucosa1ActionPerformed(evt);
            }
        });
        txtMucosa1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMucosa1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMucosa1KeyTyped(evt);
            }
        });

        txtPeso3.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtPeso3.setName("Peso"); // NOI18N
        txtPeso3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPeso3ActionPerformed(evt);
            }
        });
        txtPeso3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPeso3KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPeso3KeyTyped(evt);
            }
        });

        tblEvoluciones.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        tblEvoluciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Descripción del examen", "url"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblEvoluciones.setRowHeight(19);
        tblEvoluciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEvolucionesMouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(tblEvoluciones);
        if (tblEvoluciones.getColumnModel().getColumnCount() > 0) {
            tblEvoluciones.getColumnModel().getColumn(2).setMinWidth(0);
            tblEvoluciones.getColumnModel().getColumn(2).setPreferredWidth(0);
            tblEvoluciones.getColumnModel().getColumn(2).setMaxWidth(0);
        }

        lbNit13.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit13.setText("Descripción:");

        txtDescripcionExamenConsulta1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescripcionExamenConsulta1KeyReleased(evt);
            }
        });

        jButton12.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jButton12.setText("Agregar");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        lbNit7.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit7.setText("Anexos:");

        jButton11.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jButton11.setText("Seleccionar Archivo");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        lbNit12.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit12.setText("Nombre del archivo:");

        txtNombreAnexoConsulta1.setEditable(false);
        txtNombreAnexoConsulta1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jButton13.setBackground(new java.awt.Color(255, 255, 255));
        jButton13.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jButton13.setText("QUIRURGICA");
        jButton13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton8.setBackground(new java.awt.Color(255, 255, 255));
        jButton8.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jButton8.setText("REMISIÓN");
        jButton8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jButton3.setText("FORMULA MEDICA");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jButton2.setText("AYUDA DIAGNOSTICA");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane12)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(lbEmail45, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbDireccion39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbDireccion40, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTllc3)
                            .addComponent(txtFc3, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbDireccion42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbDireccion41, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtT3)
                            .addComponent(txtFr3, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(49, 49, 49)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(lbDireccion43, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCc3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(lbDireccion44, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPulso3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbDireccion45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbDireccion46, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMucosa1)
                            .addComponent(txtPeso3, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbNit7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbNit13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(txtDescripcionExamenConsulta1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(jButton11)
                                .addGap(39, 39, 39)
                                .addComponent(lbNit12, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNombreAnexoConsulta1, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(208, 208, 208)
                .addComponent(jButton13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNit7)
                    .addComponent(jButton11)
                    .addComponent(lbNit12)
                    .addComponent(txtNombreAnexoConsulta1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbNit13)
                    .addComponent(jButton12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDescripcionExamenConsulta1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbEmail45)
                        .addComponent(lbDireccion40)
                        .addComponent(txtFc3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbDireccion41)
                        .addComponent(txtFr3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbDireccion44)
                        .addComponent(txtPulso3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbDireccion46, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtPeso3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbDireccion39)
                        .addComponent(txtTllc3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbDireccion42)
                        .addComponent(txtT3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbDireccion43)
                        .addComponent(txtCc3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbDireccion45))
                    .addComponent(txtMucosa1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        btnGuardar3.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardar3.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnGuardar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardar3.setText("GUARDAR CONTROL");
        btnGuardar3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar3.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardar3.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnGuardar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(lbNit4, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtProfesional, javax.swing.GroupLayout.PREFERRED_SIZE, 908, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 56, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(0, 15, Short.MAX_VALUE)
                                .addComponent(lbImagenes, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 828, Short.MAX_VALUE))
                            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbEmail22, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbEmail23, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane22)
                                    .addComponent(jScrollPane21))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGuardar3)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNit4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtProfesional, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbEmail22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane21, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnGuardar3)
                .addGap(15, 15, 15)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane22, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(lbEmail23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbImagenes))
        );

        jTabbedPane1.addTab("Control", jPanel9);

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        scrFormulario.setViewportView(pnlFormulario);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        lbEmail5.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbEmail5.setText("Consultas:");

        lista.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lista.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Hoy" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        lista.setToolTipText("");
        lista.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listaValueChanged(evt);
            }
        });
        jScrollPane8.setViewportView(lista);

        lbRazon5.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbRazon5.setText("Controles");

        cmbFecha.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
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

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane8)
                    .addComponent(lbEmail5, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                    .addComponent(cmbFecha, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbRazon5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbEmail5)
                .addGap(5, 5, 5)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(lbRazon5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(scrFormulario)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(scrFormulario)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private String datosExamenes(JTable tabla) {
        String aux = "";
        for (int i = 0; i < tabla.getRowCount(); i++) {
            aux += tabla.getValueAt(i, 0) + "&";
        }
        return aux;
    }

    private void cargarDatosExamen(JTable tabla, String dato) {
        String aux[] = dato.split("&");
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        for (int i = 0; i < aux.length; i++) {
            modelo.addRow(new String[]{aux[i]});
        }
    }

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (metodos.msgPregunta(this, "¿Desea continuar?") == 0) {
            historia.setMotivo(txtMotivoAnt.getText());
            historia.setEvolucion(txtEvolucionAnt.getText());
            historia.setTratamientos(txtTratamientos.getText());
            instancias.getSql().modificarHistoria(historia);
            metodos.msgExito(this, "Historia modificada con éxito");
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyTyped

    }//GEN-LAST:event_txtCodigoKeyTyped

    private void txtCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyReleased

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtNombre.requestFocus();
        }
    }//GEN-LAST:event_txtCodigoKeyReleased

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbFecha.requestFocus();
        }
    }//GEN-LAST:event_txtNombreKeyReleased

    private void txtEdadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEdadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEdadActionPerformed

    private void txtEdadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEdadKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEdadKeyReleased

    private void txtEdadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEdadKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEdadKeyTyped

    private void txtGeneroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGeneroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGeneroActionPerformed

    private void txtGeneroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGeneroKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGeneroKeyReleased

    private void txtGeneroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGeneroKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGeneroKeyTyped

    private void txtRazaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRazaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRazaActionPerformed

    private void txtRazaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRazaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRazaKeyReleased

    private void txtRazaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRazaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRazaKeyTyped

    private void txtFcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFcActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFcActionPerformed

    private void txtFcKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFcKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB || evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtFr.requestFocus();
        }
    }//GEN-LAST:event_txtFcKeyReleased

    private void txtFcKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFcKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFcKeyTyped

    private void txtTllcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTllcActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTllcActionPerformed

    private void txtTllcKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTllcKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB || evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtT.requestFocus();
        }
    }//GEN-LAST:event_txtTllcKeyReleased

    private void txtTllcKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTllcKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTllcKeyTyped

    private void txtFrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFrActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFrActionPerformed

    private void txtFrKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFrKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB || evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtPulso.requestFocus();
        }
    }//GEN-LAST:event_txtFrKeyReleased

    private void txtFrKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFrKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFrKeyTyped

    private void txtTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTActionPerformed

    private void txtTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB || evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtCc.requestFocus();
        }
    }//GEN-LAST:event_txtTKeyReleased

    private void txtTKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTKeyTyped

    private void txtPesoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPesoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesoActionPerformed

    private void txtPesoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB || evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtTllc.requestFocus();
        }
    }//GEN-LAST:event_txtPesoKeyReleased

    private void txtPesoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesoKeyTyped

    private void txtPulsoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPulsoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPulsoActionPerformed

    private void txtPulsoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPulsoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB || evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtPeso.requestFocus();
        }
    }//GEN-LAST:event_txtPulsoKeyReleased

    private void txtPulsoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPulsoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPulsoKeyTyped

    private void txtCcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCcActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCcActionPerformed

    private void txtCcKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCcKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB || evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtMucosa.requestFocus();
        }
    }//GEN-LAST:event_txtCcKeyReleased

    private void txtCcKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCcKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCcKeyTyped

    private void btnGuardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar1ActionPerformed
        if (metodos.msgPregunta(this, "¿Desea continuar?") == 0) {
            int i = 0;
            if (lista.getSelectedIndex() == 0) {
                String consulta = instancias.getSql().getNumConsecutivo("CONSULTA")[0].toString();

                String examen = "";
                for (int j = 0; j < tblExamenes.getRowCount(); j++) {
                    examen = examen + tblExamenes.getValueAt(j, 0) + "," + tblExamenes.getValueAt(j, 1) + "," + tblExamenes.getValueAt(j, 2) + ";";
                }

                if (!examen.equals("")) {
                    examen = examen.substring(0, examen.length() - 1);
                }

                Object[] vector = {consulta, txtHistoria.getText(), txtMotivo.getText(), txtFc.getText(), txtFr.getText(),
                    txtPulso.getText(), txtPeso.getText(), txtTllc.getText(), txtT.getText(), txtCc.getText(),
                    nodoConsulta.getActitud(), txtProblemas.getText(), txtDiagPresunti.getText(), txtPlanDiagnostico.getText(),
                    txtTratamiento.getText(), txtRecomendaciones.getText(),
                    instancias.getUsuario(), metodos.fechaConsulta(metodosGenerales.fecha()),
                    nodoConsulta.getNervioso(), nodoConsulta.getDigestivo(),
                    nodoConsulta.getRespiratorio(), nodoConsulta.getCardiaco(),
                    nodoConsulta.getUrinario(), nodoConsulta.getMusculo(), nodoConsulta.getPiel(),
                    nodoConsulta.getOidos(), nodoConsulta.getOjos(),
                    txtMucosa.getText(), "", txtCaracteristicas.getText(), nodoConsulta.getHidratacion(),
                    nodoConsulta.getNutricional(), nodoConsulta.getOrganosSentidos(), nodoConsulta.getGanglios(),
                    metodos.desdeDate(dtControl.getSelectedDate()), "", examen, nodoConsulta.getEndocrino()
                };

                ndConsulta nodConsulta = metodos.llenarConsulta(vector);
                if (!instancias.getSql().agregarConsulta(nodConsulta)) {
                    metodos.msgError(this, "Hubo un problema al guardar la consulta");
                    return;
                }

                if (!instancias.getSql().aumentarConsecutivo("CONSULTA", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("CONSULTA")[0]) + 1)) {
                    metodos.msgError(this, "Error al aumentar consecutivo");
                }

                if (metodos.desdeDate(dtControl.getCurrent()).equals(metodos.fecha4(metodosGenerales.fecha()))) {
                    if (!instancias.getSql().modificarGestionControl(consulta, true)) {
                        metodos.msgError(null, "Error al modificar el estado de la alerta");
                        return;
                    }
                }

                i++;
            }

            if (i > 0) {
                metodos.msgExito(this, "Historia guardada con exito");
                seguimientoEditable(true);
                consultaEditable(true);
                setMascota(txtCodigo.getText());
            }
        }
        txtFc3.setText("");
        txtTllc3.setText("");
        txtFr3.setText("");
        txtT3.setText("");
        txtPulso3.setText("");
        txtCc3.setText("");
        txtPeso3.setText("");
        txtMucosa1.setText("");
    }//GEN-LAST:event_btnGuardar1ActionPerformed

    private void txtMotivoAntKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMotivoAntKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtEvolucionAnt.requestFocus();
        }
    }//GEN-LAST:event_txtMotivoAntKeyReleased

    private void txtEvolucionAntKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEvolucionAntKeyReleased
//        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
//            txtEnfermedades.requestFocus();
//        }
    }//GEN-LAST:event_txtEvolucionAntKeyReleased

    private void txtMotivoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMotivoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtFc.requestFocus();
        }
    }//GEN-LAST:event_txtMotivoKeyReleased

    private void txtProblemasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProblemasKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtDiagPresunti.requestFocus();
        }
    }//GEN-LAST:event_txtProblemasKeyReleased

    private void txtDiagPresuntiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiagPresuntiKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtPlanDiagnostico.requestFocus();
        }
    }//GEN-LAST:event_txtDiagPresuntiKeyReleased

    private void txtPlanDiagnosticoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlanDiagnosticoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtTratamiento.requestFocus();
        }
    }//GEN-LAST:event_txtPlanDiagnosticoKeyReleased

    private void txtTratamientoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTratamientoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtRecomendaciones.requestFocus();
        }
    }//GEN-LAST:event_txtTratamientoKeyReleased

    private void txtRecomendacionesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRecomendacionesKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtEvolucionSeg.requestFocus();
        }
    }//GEN-LAST:event_txtRecomendacionesKeyReleased

    private void txtHistoriaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHistoriaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHistoriaKeyReleased

    private void txtHistoriaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHistoriaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHistoriaKeyTyped

    private void tblSistemasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSistemasMouseClicked
        txtSistemas.setText(getSistemas());
        validarCheckRevision();
    }//GEN-LAST:event_tblSistemasMouseClicked

    private void tblSistemasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSistemasMousePressed
        txtSistemas.setText(getSistemas());
        validarCheckRevision();
    }//GEN-LAST:event_tblSistemasMousePressed

    private void txtSistemasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSistemasKeyReleased
        setSistemas();
    }//GEN-LAST:event_txtSistemasKeyReleased

    private void listaValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listaValueChanged
        try {
            if (lista.getSelectedValue().toString().equalsIgnoreCase("HOY - NUEVO")) {
                jTabbedPane1.setEnabledAt(4, false);
                jTabbedPane1.setSelectedIndex(0);

                consultaEditable(true);
//                lbFoto5.setIcon(null);
                Object[] vector = {"", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""
                };

                nodoConsulta = metodos.llenarConsulta(vector);

                txtLote1.setText("");
                txtLote2.setText("");
                txtLote3.setText("");
                txtLote4.setText("");
                txtLote5.setText("");
                txtLote6.setText("");
                txtLote7.setText("");
                txtLote8.setText("");
                txtLote9.setText("");
                txtLote10.setText("");
                txtLote11.setText("");
                txtLote12.setText("");
                txtLote13.setText("");

                txtMarca1.setText("");
                txtMarca2.setText("");
                txtMarca3.setText("");
                txtMarca4.setText("");
                txtMarca5.setText("");
                txtMarca6.setText("");
                txtMarca7.setText("");
                txtMarca8.setText("");
                txtMarca9.setText("");
                txtMarca10.setText("");
                txtMarca11.setText("");
                txtMarca12.setText("");
                txtMarca13.setText("");

                txtFc3.setText("");
                txtTllc3.setText("");
                txtFr3.setText("");
                txtT3.setText("");
                txtPulso3.setText("");
                txtCc3.setText("");
                txtPeso3.setText("");
                txtMucosa1.setText("");
                btnModificar.setVisible(false);
            } else {
                jTabbedPane1.setEnabledAt(4, true);
                consultaEditable(false);
                //ndConsulta nodo = instancias.getSql().getDatosConsulta(lista.getSelectedValue().toString().split(" - ")[0]);
                btnModificar.setVisible(true);
                ndConsulta nodo = instancias.getSql().getDatosConsulta(lista.getSelectedValue().toString().split(" - ")[0]);
                nodoConsulta = nodo;
                txtMotivo.setText(nodo.getMotivo());
                txtFc.setText(nodo.getFc());
                txtFr.setText(nodo.getFr());
                txtPulso.setText(nodo.getPulso());
                txtPeso.setText(nodo.getPeso());
                txtTllc.setText(nodo.getTllc());
                txtT.setText(nodo.getTemp());
                txtCc.setText(nodo.getCc());
                txtCaracteristicas.setText(nodo.getCaracteristicas());
                txtMucosa.setText(nodo.getMucosa());

                txtProblemas.setText(nodo.getProblemas());
                txtDiagPresunti.setText(nodo.getDiagnostico());
                txtPlanDiagnostico.setText(nodo.getPlan());
                txtTratamiento.setText(nodo.getTratamiento());
                txtRecomendaciones.setText(nodo.getRecomendacion());
                dtControl.setSelectedDate(metodos.haciaDate(nodo.getProximoControl()));

//                lbCodFoto2.setText(nodo.getFotos());
                try {
                    if (!nodo.getExamenes().equals("")) {
                        String[] examen = nodo.getExamenes().split(";");
                        DefaultTableModel modeloTabla = (DefaultTableModel) tblExamenes.getModel();
                        for (int i = 0; i < examen.length; i++) {
                            modeloTabla.addRow(new Object[]{examen[i].split(",")[0], examen[i].split(",")[1], examen[i].split(",")[2]});
                        }
                    }
                } catch (Exception e) {
                }

                Object[][] fechas = instancias.getSql().getSeguimientos(lista.getSelectedValue().toString().split(" - ")[0]);
                Object[] datos = new Object[fechas.length + 1];
                datos[0] = "HOY - NUEVO";
                int j = fechas.length;

                for (int i = 0; i < fechas.length; i++) {
                    datos[j--] = fechas[i][0] + " - " + metodos.fecha(fechas[i][1].toString());
                }

                try {
                    if (!nodo.getNervioso().equalsIgnoreCase("")) {
                        tblSistemas.setValueAt(true, 0, 1);
                    } else {
                        tblSistemas.setValueAt(false, 0, 1);
                    }
                } catch (Exception e) {
                }

                try {
                    if (!nodo.getDigestivo().equalsIgnoreCase("")) {
                        tblSistemas.setValueAt(true, 1, 1);
                    } else {
                        tblSistemas.setValueAt(false, 1, 1);
                    }
                } catch (Exception e) {
                }

                try {
                    if (!nodo.getRespiratorio().equalsIgnoreCase("")) {
                        tblSistemas.setValueAt(true, 2, 1);
                    } else {
                        tblSistemas.setValueAt(false, 2, 1);
                    }
                } catch (Exception e) {
                }

                try {
                    if (!nodo.getCardiaco().equalsIgnoreCase("")) {
                        tblSistemas.setValueAt(true, 3, 1);
                    } else {
                        tblSistemas.setValueAt(false, 3, 1);
                    }
                } catch (Exception e) {
                }

                try {
                    if (!nodo.getUrinario().equalsIgnoreCase("")) {
                        tblSistemas.setValueAt(true, 4, 1);
                    } else {
                        tblSistemas.setValueAt(false, 4, 1);
                    }
                } catch (Exception e) {
                }

                try {
                    if (!nodo.getMusculo().equalsIgnoreCase("")) {
                        tblSistemas.setValueAt(true, 0, 3);
                    } else {
                        tblSistemas.setValueAt(false, 0, 3);
                    }
                } catch (Exception e) {
                }

                try {
                    if (!nodo.getPiel().equalsIgnoreCase("")) {
                        tblSistemas.setValueAt(true, 1, 3);
                    } else {
                        tblSistemas.setValueAt(false, 1, 3);
                    }
                } catch (Exception e) {
                }

                try {
                    if (!nodo.getOrganosSentidos().equalsIgnoreCase("")) {
                        tblSistemas.setValueAt(true, 2, 3);
                    } else {
                        tblSistemas.setValueAt(false, 2, 3);
                    }
                } catch (Exception e) {
                }

                try {
                    if (!nodo.getGanglios().equalsIgnoreCase("")) {
                        tblSistemas.setValueAt(true, 3, 3);
                    } else {
                        tblSistemas.setValueAt(false, 3, 3);
                    }
                } catch (Exception e) {
                }

                try {
                    if (!nodo.getActitud().equalsIgnoreCase("")) {
                        tblSistemas.setValueAt(true, 4, 3);
                    } else {
                        tblSistemas.setValueAt(false, 4, 3);
                    }
                } catch (Exception e) {
                }

                try {
                    if (!nodo.getEndocrino().equalsIgnoreCase("")) {
                        tblSistemas.setValueAt(true, 5, 1);
                    } else {
                        tblSistemas.setValueAt(false, 5, 1);
                    }
                } catch (Exception e) {
                }

                cmbFecha.setModel(new javax.swing.DefaultComboBoxModel(datos));
                cmbFechaItemStateChanged(null);

                txtPlanDiagnostico.setEnabled(true);
                txtPlanDiagnostico.setEditable(true);
            }
        } catch (Exception e) {
            lista.setSelectedIndex(0);
            consultaEditable(true);
        }
    }//GEN-LAST:event_listaValueChanged

    private void txtMucosaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMucosaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMucosaActionPerformed

    private void txtMucosaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMucosaKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB || evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtCaracteristicas.requestFocus();
        }
    }//GEN-LAST:event_txtMucosaKeyReleased

    private void txtMucosaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMucosaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMucosaKeyTyped

    private void txtCaracteristicasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCaracteristicasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCaracteristicasActionPerformed

    private void txtCaracteristicasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCaracteristicasKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB || evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtMucosa.requestFocus();
        }
    }//GEN-LAST:event_txtCaracteristicasKeyReleased

    private void txtCaracteristicasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCaracteristicasKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCaracteristicasKeyTyped

    private void txtReproductivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtReproductivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtReproductivoActionPerformed

    private void txtReproductivoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtReproductivoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtReproductivoKeyReleased

    private void txtReproductivoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtReproductivoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtReproductivoKeyTyped

    private void txtDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionActionPerformed

    private void txtDireccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionKeyReleased

    private void txtDireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionKeyTyped

    private void txtNombrePropietarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombrePropietarioKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombrePropietarioKeyReleased

    private void txtTelefonoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoKeyReleased

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoKeyTyped

    private void txtCedulaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCedulaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCedulaKeyReleased

    private void txtCedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCedulaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCedulaKeyTyped

    private void dtNueva1OnCommit(datechooser.events.CommitEvent evt) {//GEN-FIRST:event_dtNueva1OnCommit

    }//GEN-LAST:event_dtNueva1OnCommit

    private void dtNueva1OnSelectionChange(datechooser.events.SelectionChangedEvent evt) {//GEN-FIRST:event_dtNueva1OnSelectionChange

    }//GEN-LAST:event_dtNueva1OnSelectionChange

    private void dtProxima1OnCommit(datechooser.events.CommitEvent evt) {//GEN-FIRST:event_dtProxima1OnCommit
        // TODO add your handling code here:
    }//GEN-LAST:event_dtProxima1OnCommit

    private void dtProxima1OnSelectionChange(datechooser.events.SelectionChangedEvent evt) {//GEN-FIRST:event_dtProxima1OnSelectionChange
        // TODO add your handling code here:
    }//GEN-LAST:event_dtProxima1OnSelectionChange

    private void btnGuardar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar2ActionPerformed
        if (metodos.msgPregunta(this, "¿Desea continuar?") == 0) {
            boolean gestion;

            if (metodos.desdeDate(dtNueva1.getCurrent()).equals(metodos.desdeDate(dtProxima1.getCurrent())) || metodos.desdeDate(dtProxima1.getCurrent()).equals(metodos.fecha4(metodosGenerales.fecha()))) {
                gestion = true;
            } else {
                gestion = false;
            }
            if (cmbPuppy.getSelectedIndex() == 1) {
                if (txtLote1.getText().equals("") || txtMarca1.getText().equals("")) {
                    if (metodos.msgPregunta(this, "No ha ingresado el lote o marca en " + lbNombre1.getText() + "¿Desea continuar?") == 0) {
                        if (!instancias.getSql().agregarVacuna(new Object[]{metodos.desdeDate(dtNueva1.getCurrent()), txtCodigo.getText(), "1", txtMarca1.getText(),
                            txtLote1.getText(), metodos.desdeDate(dtProxima1.getCurrent()), gestion})) {
                            metodos.msgError(this, "Hubo un problema al guardar la vacuna");
                        }
                        vacunas(cmbPuppy, "1");
                        dtNueva1.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
                        dtProxima1.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
                        txtLote1.setText("");
                        txtMarca1.setText("");
                    } else {
                        return;
                    }
                } else {
                    if (!instancias.getSql().agregarVacuna(new Object[]{metodos.desdeDate(dtNueva1.getCurrent()), txtCodigo.getText(), "1", txtMarca1.getText(),
                        txtLote1.getText(), metodos.desdeDate(dtProxima1.getCurrent()), gestion})) {
                        metodos.msgError(this, "Hubo un problema al guardar la vacuna");
                    }
                    vacunas(cmbPuppy, "1");
                    dtNueva1.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
                    dtProxima1.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
                    txtLote1.setText("");
                    txtMarca1.setText("");
                }
            }

            if (metodos.desdeDate(dtNueva2.getCurrent()).equals(metodos.desdeDate(dtProxima2.getCurrent())) || metodos.desdeDate(dtProxima2.getCurrent()).equals(metodos.fecha4(metodosGenerales.fecha()))) {
                gestion = true;
            } else {
                gestion = false;
            }
            if (cmbHistorico2.getSelectedIndex() == 1) {
                if (txtLote2.getText().equals("") || txtMarca2.getText().equals("")) {
                    if (metodos.msgPregunta(this, "No ha ingresado el lote o marca en " + lbNombre2.getText() + "¿Desea continuar?") == 0) {
                        if (!instancias.getSql().agregarVacuna(new Object[]{metodos.desdeDate(dtNueva2.getCurrent()), txtCodigo.getText(), "2", txtMarca2.getText(),
                            txtLote2.getText(), metodos.desdeDate(dtProxima2.getCurrent()), gestion})) {
                            metodos.msgError(this, "Hubo un problema al guardar la vacuna");
                        }
                        vacunas(cmbHistorico2, "2");
                        dtNueva2.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
                        dtProxima2.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
                        txtLote2.setText("");
                        txtMarca2.setText("");
                    } else {
                        return;
                    }
                } else {
                    if (!instancias.getSql().agregarVacuna(new Object[]{metodos.desdeDate(dtNueva2.getCurrent()), txtCodigo.getText(), "2", txtMarca2.getText(),
                        txtLote2.getText(), metodos.desdeDate(dtProxima2.getCurrent()), gestion})) {
                        metodos.msgError(this, "Hubo un problema al guardar la vacuna");
                    }
                    vacunas(cmbHistorico2, "2");
                    dtNueva2.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
                    dtProxima2.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
                    txtLote2.setText("");
                    txtMarca2.setText("");
                }
            }

            if (metodos.desdeDate(dtNueva3.getCurrent()).equals(metodos.desdeDate(dtProxima3.getCurrent())) || metodos.desdeDate(dtProxima3.getCurrent()).equals(metodos.fecha4(metodosGenerales.fecha()))) {
                gestion = true;
            } else {
                gestion = false;
            }
            if (cmbHistorico3.getSelectedIndex() == 1) {
                if (txtLote3.getText().equals("") || txtMarca3.getText().equals("")) {
                    if (metodos.msgPregunta(this, "No ha ingresado el lote o marca en " + lbNombre3.getText() + "¿Desea continuar?") == 0) {
                        if (!instancias.getSql().agregarVacuna(new Object[]{metodos.desdeDate(dtNueva3.getCurrent()), txtCodigo.getText(), "3", txtMarca3.getText(),
                            txtLote3.getText(), metodos.desdeDate(dtProxima3.getCurrent()), gestion})) {
                            metodos.msgError(this, "Hubo un problema al guardar la vacuna");
                        }
                        vacunas(cmbHistorico3, "3");
                        dtNueva3.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
                        dtProxima3.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
                        txtLote3.setText("");
                        txtMarca3.setText("");
                    } else {
                        return;
                    }
                } else {
                    if (!instancias.getSql().agregarVacuna(new Object[]{metodos.desdeDate(dtNueva3.getCurrent()), txtCodigo.getText(), "3", txtMarca3.getText(),
                        txtLote3.getText(), metodos.desdeDate(dtProxima3.getCurrent()), gestion})) {
                        metodos.msgError(this, "Hubo un problema al guardar la vacuna");
                    }
                    vacunas(cmbHistorico3, "3");
                    dtNueva3.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
                    dtProxima3.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
                    txtLote3.setText("");
                    txtMarca3.setText("");
                }
            }

            if (metodos.desdeDate(dtNueva4.getCurrent()).equals(metodos.desdeDate(dtProxima4.getCurrent())) || metodos.desdeDate(dtProxima4.getCurrent()).equals(metodos.fecha4(metodosGenerales.fecha()))) {
                gestion = true;
            } else {
                gestion = false;
            }
            if (cmbHistorico4.getSelectedIndex() == 1) {
                if (txtLote4.getText().equals("") || txtMarca4.getText().equals("")) {
                    if (metodos.msgPregunta(this, "No ha ingresado el lote o marca en " + lbNombre4.getText() + "¿Desea continuar?") == 0) {
                        if (!instancias.getSql().agregarVacuna(new Object[]{metodos.desdeDate(dtNueva4.getCurrent()), txtCodigo.getText(), "4", txtMarca4.getText(),
                            txtLote4.getText(), metodos.desdeDate(dtProxima4.getCurrent()), gestion})) {
                            metodos.msgError(this, "Hubo un problema al guardar la vacuna");
                        }
                        vacunas(cmbHistorico4, "4");
                        dtNueva4.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
                        dtProxima4.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
                        txtLote4.setText("");
                        txtMarca4.setText("");
                    } else {
                        return;
                    }
                } else {
                    if (!instancias.getSql().agregarVacuna(new Object[]{metodos.desdeDate(dtNueva4.getCurrent()), txtCodigo.getText(), "4", txtMarca4.getText(),
                        txtLote4.getText(), metodos.desdeDate(dtProxima4.getCurrent()), gestion})) {
                        metodos.msgError(this, "Hubo un problema al guardar la vacuna");
                    }
                    vacunas(cmbHistorico4, "4");
                    dtNueva4.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
                    dtProxima4.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
                    txtLote4.setText("");
                    txtMarca4.setText("");
                }
            }

            if (metodos.desdeDate(dtNueva5.getCurrent()).equals(metodos.desdeDate(dtProxima5.getCurrent())) || metodos.desdeDate(dtProxima5.getCurrent()).equals(metodos.fecha4(metodosGenerales.fecha()))) {
                gestion = true;
            } else {
                gestion = false;
            }
            if (cmbHistorico5.getSelectedIndex() == 1) {
                if (txtLote5.getText().equals("") || txtMarca5.getText().equals("")) {
                    if (metodos.msgPregunta(this, "No ha ingresado el lote o marca en " + lbNombre5.getText() + "¿Desea continuar?") == 0) {
                        if (!instancias.getSql().agregarVacuna(new Object[]{metodos.desdeDate(dtNueva5.getCurrent()), txtCodigo.getText(), "5", txtMarca5.getText(),
                            txtLote5.getText(), metodos.desdeDate(dtProxima5.getCurrent()), gestion})) {
                            metodos.msgError(this, "Hubo un problema al guardar la vacuna");
                        }
                        vacunas(cmbHistorico5, "5");
                        dtNueva5.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
                        dtProxima5.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
                        txtLote5.setText("");
                        txtMarca5.setText("");
                    } else {
                        return;
                    }
                } else {
                    if (!instancias.getSql().agregarVacuna(new Object[]{metodos.desdeDate(dtNueva5.getCurrent()), txtCodigo.getText(), "5", txtMarca5.getText(),
                        txtLote5.getText(), metodos.desdeDate(dtProxima5.getCurrent()), gestion})) {
                        metodos.msgError(this, "Hubo un problema al guardar la vacuna");
                    }
                    vacunas(cmbHistorico5, "5");
                    dtNueva5.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
                    dtProxima5.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
                    txtLote5.setText("");
                    txtMarca5.setText("");
                }
            }

            if (metodos.desdeDate(dtNueva6.getCurrent()).equals(metodos.desdeDate(dtProxima6.getCurrent())) || metodos.desdeDate(dtProxima6.getCurrent()).equals(metodos.fecha4(metodosGenerales.fecha()))) {
                gestion = true;
            } else {
                gestion = false;
            }
            if (cmbHistorico6.getSelectedIndex() == 1) {
                if (txtLote6.getText().equals("") || txtMarca6.getText().equals("")) {
                    if (metodos.msgPregunta(this, "No ha ingresado el lote o marca en " + lbNombre6.getText() + "¿Desea continuar?") == 0) {
                        if (!instancias.getSql().agregarVacuna(new Object[]{metodos.desdeDate(dtNueva6.getCurrent()), txtCodigo.getText(), "6", txtMarca6.getText(),
                            txtLote6.getText(), metodos.desdeDate(dtProxima6.getCurrent()), gestion})) {
                            metodos.msgError(this, "Hubo un problema al guardar la vacuna");
                        }
                        vacunas(cmbHistorico6, "6");
                        dtNueva6.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
                        dtProxima6.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
                        txtLote6.setText("");
                        txtMarca6.setText("");
                    } else {
                        return;
                    }
                } else {
                    if (!instancias.getSql().agregarVacuna(new Object[]{metodos.desdeDate(dtNueva6.getCurrent()), txtCodigo.getText(), "6", txtMarca6.getText(),
                        txtLote6.getText(), metodos.desdeDate(dtProxima6.getCurrent()), gestion})) {
                        metodos.msgError(this, "Hubo un problema al guardar la vacuna");
                    }
                    vacunas(cmbHistorico6, "6");
                    dtNueva6.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
                    dtProxima6.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
                    txtLote6.setText("");
                    txtMarca6.setText("");
                }
            }

            if (metodos.desdeDate(dtNueva7.getCurrent()).equals(metodos.desdeDate(dtProxima7.getCurrent())) || metodos.desdeDate(dtProxima7.getCurrent()).equals(metodos.fecha4(metodosGenerales.fecha()))) {
                gestion = true;
            } else {
                gestion = false;
            }
            if (cmbHistorico7.getSelectedIndex() == 1) {
                if (txtLote7.getText().equals("") || txtMarca7.getText().equals("")) {
                    if (metodos.msgPregunta(this, "No ha ingresado el lote o marca en " + lbNombre7.getText() + "¿Desea continuar?") == 0) {
                        if (!instancias.getSql().agregarVacuna(new Object[]{metodos.desdeDate(dtNueva7.getCurrent()), txtCodigo.getText(), "7", txtMarca7.getText(),
                            txtLote7.getText(), metodos.desdeDate(dtProxima7.getCurrent()), gestion})) {
                            metodos.msgError(this, "Hubo un problema al guardar la vacuna");
                        }
                        vacunas(cmbHistorico7, "7");
                        dtNueva7.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
                        dtProxima7.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
                        txtLote7.setText("");
                        txtMarca7.setText("");
                    } else {
                        return;
                    }
                } else {
                    if (!instancias.getSql().agregarVacuna(new Object[]{metodos.desdeDate(dtNueva7.getCurrent()), txtCodigo.getText(), "7", txtMarca7.getText(),
                        txtLote7.getText(), metodos.desdeDate(dtProxima7.getCurrent()), gestion})) {
                        metodos.msgError(this, "Hubo un problema al guardar la vacuna");
                    }
                    vacunas(cmbHistorico7, "7");
                    dtNueva7.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
                    dtProxima7.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
                    txtLote7.setText("");
                    txtMarca7.setText("");
                }
            }

            if (metodos.desdeDate(dtNueva8.getCurrent()).equals(metodos.desdeDate(dtProxima8.getCurrent())) || metodos.desdeDate(dtProxima8.getCurrent()).equals(metodos.fecha4(metodosGenerales.fecha()))) {
                gestion = true;
            } else {
                gestion = false;
            }
            if (cmbHistorico8.getSelectedIndex() == 1) {
                if (txtLote8.getText().equals("") || txtMarca8.getText().equals("")) {
                    if (metodos.msgPregunta(this, "No ha ingresado el lote o marca en " + lbNombre8.getText() + "¿Desea continuar?") == 0) {
                        if (!instancias.getSql().agregarVacuna(new Object[]{metodos.desdeDate(dtNueva8.getCurrent()), txtCodigo.getText(), "8", txtMarca8.getText(),
                            txtLote8.getText(), metodos.desdeDate(dtProxima8.getCurrent()), gestion})) {
                            metodos.msgError(this, "Hubo un problema al guardar la vacuna");
                        }
                        vacunas(cmbHistorico8, "8");
                        dtNueva8.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
                        dtProxima8.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
                        txtLote8.setText("");
                        txtMarca8.setText("");
                    } else {
                        return;
                    }
                } else {
                    if (!instancias.getSql().agregarVacuna(new Object[]{metodos.desdeDate(dtNueva8.getCurrent()), txtCodigo.getText(), "8", txtMarca8.getText(),
                        txtLote8.getText(), metodos.desdeDate(dtProxima8.getCurrent()), gestion})) {
                        metodos.msgError(this, "Hubo un problema al guardar la vacuna");
                    }
                    vacunas(cmbHistorico8, "8");
                    dtNueva8.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
                    dtProxima8.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
                    txtLote8.setText("");
                    txtMarca8.setText("");
                }
            }

            if (metodos.desdeDate(dtNueva9.getCurrent()).equals(metodos.desdeDate(dtProxima9.getCurrent())) || metodos.desdeDate(dtProxima9.getCurrent()).equals(metodos.fecha4(metodosGenerales.fecha()))) {
                gestion = true;
            } else {
                gestion = false;
            }
            if (cmbHistorico9.getSelectedIndex() == 1) {
                if (txtLote9.getText().equals("") || txtMarca9.getText().equals("")) {
                    if (metodos.msgPregunta(this, "No ha ingresado el lote o marca en " + lbNombre9.getText() + "¿Desea continuar?") == 0) {
                        if (!instancias.getSql().agregarVacuna(new Object[]{metodos.desdeDate(dtNueva9.getCurrent()), txtCodigo.getText(), "9", txtMarca9.getText(),
                            txtLote9.getText(), metodos.desdeDate(dtProxima9.getCurrent()), gestion})) {
                            metodos.msgError(this, "Hubo un problema al guardar la vacuna");
                        }
                        vacunas(cmbHistorico9, "9");
                        dtNueva9.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
                        dtProxima9.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
                        txtLote9.setText("");
                        txtMarca9.setText("");
                    } else {
                        return;
                    }
                } else {
                    if (!instancias.getSql().agregarVacuna(new Object[]{metodos.desdeDate(dtNueva9.getCurrent()), txtCodigo.getText(), "9", txtMarca9.getText(),
                        txtLote9.getText(), metodos.desdeDate(dtProxima9.getCurrent()), gestion})) {
                        metodos.msgError(this, "Hubo un problema al guardar la vacuna");
                    }
                    vacunas(cmbHistorico9, "9");
                    dtNueva9.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
                    dtProxima9.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
                    txtLote9.setText("");
                    txtMarca9.setText("");
                }
            }

            if (metodos.desdeDate(dtNueva10.getCurrent()).equals(metodos.desdeDate(dtProxima10.getCurrent())) || metodos.desdeDate(dtProxima10.getCurrent()).equals(metodos.fecha4(metodosGenerales.fecha()))) {
                gestion = true;
            } else {
                gestion = false;
            }
            if (cmbHistorico10.getSelectedIndex() == 1) {
                if (txtLote10.getText().equals("") || txtMarca10.getText().equals("")) {
                    if (metodos.msgPregunta(this, "No ha ingresado el lote o marca en " + lbNombre10.getText() + "¿Desea continuar?") == 0) {
                        if (!instancias.getSql().agregarVacuna(new Object[]{metodos.desdeDate(dtNueva10.getCurrent()), txtCodigo.getText(), "10", txtMarca10.getText(),
                            txtLote10.getText(), metodos.desdeDate(dtProxima10.getCurrent()), gestion})) {
                            metodos.msgError(this, "Hubo un problema al guardar la vacuna");
                        }
                        vacunas(cmbHistorico10, "10");
                        dtNueva10.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
                        dtProxima10.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
                        txtLote10.setText("");
                        txtMarca10.setText("");
                    } else {
                        return;
                    }
                } else {
                    if (!instancias.getSql().agregarVacuna(new Object[]{metodos.desdeDate(dtNueva10.getCurrent()), txtCodigo.getText(), "10", txtMarca10.getText(),
                        txtLote10.getText(), metodos.desdeDate(dtProxima10.getCurrent()), gestion})) {
                        metodos.msgError(this, "Hubo un problema al guardar la vacuna");
                    }
                    vacunas(cmbHistorico10, "10");
                    dtNueva10.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
                    dtProxima10.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
                    txtLote10.setText("");
                    txtMarca10.setText("");
                }
            }

            if (metodos.desdeDate(dtNueva11.getCurrent()).equals(metodos.desdeDate(dtProxima11.getCurrent())) || metodos.desdeDate(dtProxima11.getCurrent()).equals(metodos.fecha4(metodosGenerales.fecha()))) {
                gestion = true;
            } else {
                gestion = false;
            }
            if (cmbHistorico11.getSelectedIndex() == 1) {
                if (txtLote11.getText().equals("") || txtMarca11.getText().equals("")) {
                    if (metodos.msgPregunta(this, "No ha ingresado el lote o marca en " + lbNombre11.getText() + "¿Desea continuar?") == 0) {
                        if (!instancias.getSql().agregarVacuna(new Object[]{metodos.desdeDate(dtNueva11.getCurrent()), txtCodigo.getText(), "11", txtMarca11.getText(),
                            txtLote11.getText(), metodos.desdeDate(dtProxima11.getCurrent()), gestion})) {
                            metodos.msgError(this, "Hubo un problema al guardar la vacuna");
                        }
                        vacunas(cmbHistorico11, "11");
                        dtNueva11.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
                        dtProxima11.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
                        txtLote11.setText("");
                        txtMarca11.setText("");
                    } else {
                        return;
                    }
                } else {
                    if (!instancias.getSql().agregarVacuna(new Object[]{metodos.desdeDate(dtNueva11.getCurrent()), txtCodigo.getText(), "11", txtMarca11.getText(),
                        txtLote11.getText(), metodos.desdeDate(dtProxima11.getCurrent()), gestion})) {
                        metodos.msgError(this, "Hubo un problema al guardar la vacuna");
                    }
                    vacunas(cmbHistorico11, "11");
                    dtNueva11.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
                    dtProxima11.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
                    txtLote11.setText("");
                    txtMarca11.setText("");
                }
            }

            if (metodos.desdeDate(dtNueva12.getCurrent()).equals(metodos.desdeDate(dtProxima12.getCurrent())) || metodos.desdeDate(dtProxima12.getCurrent()).equals(metodos.fecha4(metodosGenerales.fecha()))) {
                gestion = true;
            } else {
                gestion = false;
            }
            if (cmbHistorico12.getSelectedIndex() == 1) {
                if (txtLote12.getText().equals("") || txtMarca12.getText().equals("")) {
                    if (metodos.msgPregunta(this, "No ha ingresado el lote o marca en " + lbNombre12.getText() + "¿Desea continuar?") == 0) {
                        if (!instancias.getSql().agregarVacuna(new Object[]{metodos.desdeDate(dtNueva12.getCurrent()), txtCodigo.getText(), "12", txtMarca12.getText(),
                            txtLote12.getText(), metodos.desdeDate(dtProxima12.getCurrent()), gestion})) {
                            metodos.msgError(this, "Hubo un problema al guardar la vacuna");
                        }
                        vacunas(cmbHistorico12, "12");
                        dtNueva12.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
                        dtProxima12.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
                        txtLote12.setText("");
                        txtMarca12.setText("");
                    } else {
                        return;
                    }
                } else {
                    if (!instancias.getSql().agregarVacuna(new Object[]{metodos.desdeDate(dtNueva12.getCurrent()), txtCodigo.getText(), "12", txtMarca12.getText(),
                        txtLote12.getText(), metodos.desdeDate(dtProxima12.getCurrent()), gestion})) {
                        metodos.msgError(this, "Hubo un problema al guardar la vacuna");
                    }
                    vacunas(cmbHistorico12, "12");
                    dtNueva12.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
                    dtProxima12.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
                    txtLote12.setText("");
                    txtMarca12.setText("");
                }
            }

            if (metodos.desdeDate(dtNueva13.getCurrent()).equals(metodos.desdeDate(dtProxima13.getCurrent())) || metodos.desdeDate(dtProxima13.getCurrent()).equals(metodos.fecha4(metodosGenerales.fecha()))) {
                gestion = true;
            } else {
                gestion = false;
            }
            if (cmbHistorico13.getSelectedIndex() == 1) {
                if (txtLote13.getText().equals("") || txtMarca13.getText().equals("")) {
                    if (metodos.msgPregunta(this, "No ha ingresado el lote o marca en " + lbNombre13.getText() + "¿Desea continuar?") == 0) {
                        if (!instancias.getSql().agregarVacuna(new Object[]{metodos.desdeDate(dtNueva13.getCurrent()), txtCodigo.getText(), "13", txtMarca13.getText(),
                            txtLote13.getText(), metodos.desdeDate(dtProxima13.getCurrent()), gestion})) {
                            metodos.msgError(this, "Hubo un problema al guardar la vacuna");
                        }
                        vacunas(cmbHistorico13, "13");
                        dtNueva13.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
                        dtProxima13.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
                        txtLote13.setText("");
                        txtMarca13.setText("");
                    } else {
                        return;
                    }
                } else {
                    if (!instancias.getSql().agregarVacuna(new Object[]{metodos.desdeDate(dtNueva13.getCurrent()), txtCodigo.getText(), "13", txtMarca13.getText(),
                        txtLote13.getText(), metodos.desdeDate(dtProxima13.getCurrent()), gestion})) {
                        metodos.msgError(this, "Hubo un problema al guardar la vacuna");
                    }
                    vacunas(cmbHistorico13, "13");
                    dtNueva13.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
                    dtProxima13.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
                    txtLote13.setText("");
                    txtMarca13.setText("");
                }
            }

            metodos.msgExito(this, "Vacunas registradas con exito");

            botonAlerta(cmbPuppy, alerta1);
            botonAlerta(cmbHistorico2, alerta2);
            botonAlerta(cmbHistorico3, alerta3);
            botonAlerta(cmbHistorico4, alerta4);
            botonAlerta(cmbHistorico5, alerta5);
            botonAlerta(cmbHistorico6, alerta6);
            botonAlerta(cmbHistorico7, alerta7);
            botonAlerta(cmbHistorico8, alerta8);
            botonAlerta(cmbHistorico9, alerta9);
            botonAlerta(cmbHistorico10, alerta10);
            botonAlerta(cmbHistorico11, alerta11);
            botonAlerta(cmbHistorico12, alerta12);
            botonAlerta(cmbHistorico13, alerta13);
        }
    }//GEN-LAST:event_btnGuardar2ActionPerformed

    private void txtDietaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDietaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDietaActionPerformed

    private void txtDietaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDietaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDietaKeyReleased

    private void txtDietaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDietaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDietaKeyTyped

    private void txtFrecuenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFrecuenciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFrecuenciaActionPerformed

    private void txtFrecuenciaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFrecuenciaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFrecuenciaKeyReleased

    private void txtFrecuenciaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFrecuenciaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFrecuenciaKeyTyped

    private void dtControlOnCommit(datechooser.events.CommitEvent evt) {//GEN-FIRST:event_dtControlOnCommit
        // TODO add your handling code here:
    }//GEN-LAST:event_dtControlOnCommit

    private void dtControlOnSelectionChange(datechooser.events.SelectionChangedEvent evt) {//GEN-FIRST:event_dtControlOnSelectionChange
        // TODO add your handling code here:
    }//GEN-LAST:event_dtControlOnSelectionChange

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        try {
            instancias.getMascotas().setSelected(true);
            instancias.getMenu().cambiarTitulo("MASCOTAS");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jLabel11MouseClicked

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        limpiarPantalla();
        try {
            instancias.getMascotas().setSelected(true);
            instancias.getMenu().cambiarTitulo("MASCOTAS");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jLabel9MouseClicked

    private void btnReimprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReimprimirActionPerformed
        if (lista.getSelectedIndex() == 0) {
            metodos.msgError(this, "Seleccione una consulta");
            return;
        }
        instancias.getReporte().verRemisionVeterinaria(lista.getSelectedValue().toString().split(" - ")[0], txtEdad.getText());
    }//GEN-LAST:event_btnReimprimirActionPerformed

    private void txtNombrePropietarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombrePropietarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombrePropietarioActionPerformed

    private void cmbPuppyItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbPuppyItemStateChanged
        if (cmbPuppy.getSelectedIndex() == 0 || cmbPuppy.getSelectedIndex() == 1) {
            dtNueva1.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
            txtLote1.setText("");
            txtMarca1.setText("");
            dtProxima1.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
        } else {
            Object[] infoVacuna = instancias.getSql().infoVacunas(cmbPuppy.getSelectedItem().toString().split("/")[0]);
            dtNueva1.setSelectedDate(metodos.haciaDate(infoVacuna[0].toString()));
            txtLote1.setText(infoVacuna[2].toString());
            txtMarca1.setText(infoVacuna[1].toString());
            dtProxima1.setSelectedDate(metodos.haciaDate(infoVacuna[3].toString()));
        }
    }//GEN-LAST:event_cmbPuppyItemStateChanged

    private void cmbHistorico2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbHistorico2ItemStateChanged
        if (cmbHistorico2.getSelectedIndex() == 0 || cmbHistorico2.getSelectedIndex() == 1) {
            dtNueva2.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
            txtLote2.setText("");
            txtMarca2.setText("");
            dtProxima2.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
        } else {
            Object[] infoVacuna = instancias.getSql().infoVacunas(cmbHistorico2.getSelectedItem().toString().split("/")[0]);
            dtNueva2.setSelectedDate(metodos.haciaDate(infoVacuna[0].toString()));
            txtLote2.setText(infoVacuna[2].toString());
            txtMarca2.setText(infoVacuna[1].toString());
            dtProxima2.setSelectedDate(metodos.haciaDate(infoVacuna[3].toString()));

        }
    }//GEN-LAST:event_cmbHistorico2ItemStateChanged

    private void cmbHistorico3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbHistorico3ItemStateChanged
        if (cmbHistorico3.getSelectedIndex() == 0 || cmbHistorico3.getSelectedIndex() == 1) {
            dtNueva3.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
            txtLote3.setText("");
            txtMarca3.setText("");
            dtProxima3.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
        } else {
            Object[] infoVacuna = instancias.getSql().infoVacunas(cmbHistorico3.getSelectedItem().toString().split("/")[0]);
            dtNueva3.setSelectedDate(metodos.haciaDate(infoVacuna[0].toString()));
            txtLote3.setText(infoVacuna[2].toString());
            txtMarca3.setText(infoVacuna[1].toString());
            dtProxima3.setSelectedDate(metodos.haciaDate(infoVacuna[3].toString()));

        }
    }//GEN-LAST:event_cmbHistorico3ItemStateChanged

    private void cmbHistorico4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbHistorico4ItemStateChanged
        if (cmbHistorico4.getSelectedIndex() == 0 || cmbHistorico4.getSelectedIndex() == 1) {
            dtNueva4.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
            txtLote4.setText("");
            txtMarca4.setText("");
            dtProxima4.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
        } else {
            Object[] infoVacuna = instancias.getSql().infoVacunas(cmbHistorico4.getSelectedItem().toString().split("/")[0]);
            dtNueva4.setSelectedDate(metodos.haciaDate(infoVacuna[0].toString()));
            txtLote4.setText(infoVacuna[2].toString());
            txtMarca4.setText(infoVacuna[1].toString());
            dtProxima4.setSelectedDate(metodos.haciaDate(infoVacuna[3].toString()));
        }
    }//GEN-LAST:event_cmbHistorico4ItemStateChanged

    private void cmbHistorico5ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbHistorico5ItemStateChanged
        if (cmbHistorico5.getSelectedIndex() == 0 || cmbHistorico5.getSelectedIndex() == 1) {
            dtNueva5.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
            txtLote5.setText("");
            txtMarca5.setText("");
            dtProxima5.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
        } else {
            Object[] infoVacuna = instancias.getSql().infoVacunas(cmbHistorico5.getSelectedItem().toString().split("/")[0]);
            dtNueva5.setSelectedDate(metodos.haciaDate(infoVacuna[0].toString()));
            txtLote5.setText(infoVacuna[2].toString());
            txtMarca5.setText(infoVacuna[1].toString());
            dtProxima5.setSelectedDate(metodos.haciaDate(infoVacuna[3].toString()));

        }
    }//GEN-LAST:event_cmbHistorico5ItemStateChanged

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (lista.getSelectedIndex() == 0) {
            metodos.msgAdvertencia(this, "Debe de generar una consulta asociada");
            return;
        }

        instancias.getFormulaMedicaVeterinaria().nuevaFormula(historia, dtControl.getText());
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (lista.getSelectedIndex() == 0) {
            metodos.msgAdvertencia(this, "Debe de generar una consulta asociada");
            return;
        }

        instancias.getAyudaDiagnosticoVeterinaria().nuevaAyuda(txtHistoria.getText());
    }//GEN-LAST:event_jButton2ActionPerformed


    private void txtObservacionesSegKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtObservacionesSegKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            btnGuardar1.requestFocus();
        }
    }//GEN-LAST:event_txtObservacionesSegKeyReleased

    private void txtEvolucionSegKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEvolucionSegKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtObservacionesSeg.requestFocus();
        }
    }//GEN-LAST:event_txtEvolucionSegKeyReleased

    private void txtProfesionalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProfesionalKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProfesionalKeyTyped

    private void txtProfesionalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProfesionalKeyReleased

    }//GEN-LAST:event_txtProfesionalKeyReleased

    private void popBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popBorrarActionPerformed
        if (tblEvoluciones.getSelectedRow() > -1) {
            int fila = tblEvoluciones.getSelectedRow();
            String codigo = (String) tblEvoluciones.getValueAt(fila, 1);

            DefaultTableModel modelo = (DefaultTableModel) tblEvoluciones.getModel();
            modelo.removeRow(fila);
            int cant = modelo.getRowCount();

//            if (fila == 0) {
//                if (cant > 0) {
//                    lbCodFoto1.setText(lbCodFoto1.getText().replace(codigo + ";", ""));
//                } else {
//                    lbCodFoto1.setText(lbCodFoto1.getText().replace(codigo, ""));
//                }
//
//            } else {
//                lbCodFoto1.setText(lbCodFoto1.getText().replace(";" + codigo, ""));
//            }
//            File ruta = new File(Paths.get(System.getProperty("user.dir") + "\\imagenes\\veterinaria\\examenes\\IMG-" + codigo + ".jpg").toString());
//            if (!ruta.delete()) {
//                metodos.msgError(null, "Hubo un error al eliminar la imagen");
//            }
//            if (cant > 0) {
//                actual = 0;
//                cambiarImagen(0);
//            } else {
//                lbFoto4.setIcon(null);
//                repaint();
//            }
        } else {
            metodos.msgAdvertencia(this, "Seleccione una fila");
        }
    }//GEN-LAST:event_popBorrarActionPerformed

    private void popBorrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popBorrar1ActionPerformed
        if (tblExamenes.getSelectedRow() > -1) {
            int fila = tblExamenes.getSelectedRow();
            String codigo = (String) tblExamenes.getValueAt(fila, 1);

            DefaultTableModel modelo = (DefaultTableModel) tblExamenes.getModel();
            modelo.removeRow(fila);
            int cant = modelo.getRowCount();

//            if (fila == 0) {
//                if (cant > 0) {
//                    lbCodFoto2.setText(lbCodFoto2.getText().replace(codigo + ";", ""));
//                } else {
//                    lbCodFoto2.setText(lbCodFoto2.getText().replace(codigo, ""));
//                }
//
//            } else {
//                lbCodFoto2.setText(lbCodFoto2.getText().replace(";" + codigo, ""));
//            }
//            File ruta = new File(Paths.get(System.getProperty("user.dir") + "\\imagenes\\veterinaria\\examenes\\IMG-" + codigo + ".jpg").toString());
//            if (!ruta.delete()) {
//                metodos.msgError(null, "Hubo un error al eliminar la imagen");
//            }
//            if (cant > 0) {
//                actual = 0;
//                cambiarImagen(0);
//            } else {
//                lbFoto5.setIcon(null);
//                repaint();
//            }
        } else {
            metodos.msgAdvertencia(this, "Seleccione una fila");
        }
    }//GEN-LAST:event_popBorrar1ActionPerformed

    private void txtFc3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFc3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFc3ActionPerformed

    private void txtFc3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFc3KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFc3KeyReleased

    private void txtFc3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFc3KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFc3KeyTyped

    private void txtTllc3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTllc3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTllc3ActionPerformed

    private void txtTllc3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTllc3KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTllc3KeyReleased

    private void txtTllc3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTllc3KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTllc3KeyTyped

    private void txtFr3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFr3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFr3ActionPerformed

    private void txtFr3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFr3KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFr3KeyReleased

    private void txtFr3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFr3KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFr3KeyTyped

    private void txtT3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtT3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtT3ActionPerformed

    private void txtT3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtT3KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtT3KeyReleased

    private void txtT3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtT3KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtT3KeyTyped

    private void txtPulso3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPulso3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPulso3ActionPerformed

    private void txtPulso3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPulso3KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPulso3KeyReleased

    private void txtPulso3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPulso3KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPulso3KeyTyped

    private void txtCc3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCc3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCc3ActionPerformed

    private void txtCc3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCc3KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCc3KeyReleased

    private void txtCc3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCc3KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCc3KeyTyped

    private void txtPeso3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPeso3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPeso3ActionPerformed

    private void txtPeso3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPeso3KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPeso3KeyReleased

    private void txtPeso3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPeso3KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPeso3KeyTyped

    private void txtMucosa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMucosa1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMucosa1ActionPerformed

    private void txtMucosa1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMucosa1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMucosa1KeyReleased

    private void txtMucosa1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMucosa1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMucosa1KeyTyped

    private void txtObservacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtObservacionesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtObservacionesActionPerformed

    private void txtObservacionesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtObservacionesKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtObservacionesKeyReleased

    private void txtObservacionesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtObservacionesKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtObservacionesKeyTyped

    private void alerta5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alerta5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_alerta5ActionPerformed

    private void alerta6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alerta6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_alerta6ActionPerformed

    private void cmbHistorico6ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbHistorico6ItemStateChanged
        if (cmbHistorico6.getSelectedIndex() == 0 || cmbHistorico6.getSelectedIndex() == 1) {
            dtNueva6.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
            txtLote6.setText("");
            txtMarca6.setText("");
            dtProxima6.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
        } else {
            Object[] infoVacuna = instancias.getSql().infoVacunas(cmbHistorico6.getSelectedItem().toString().split("/")[0]);
            dtNueva6.setSelectedDate(metodos.haciaDate(infoVacuna[0].toString()));
            txtLote6.setText(infoVacuna[2].toString());
            txtMarca6.setText(infoVacuna[1].toString());
            dtProxima6.setSelectedDate(metodos.haciaDate(infoVacuna[3].toString()));
        }
    }//GEN-LAST:event_cmbHistorico6ItemStateChanged

    private void cmbHistorico10ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbHistorico10ItemStateChanged
        if (cmbHistorico10.getSelectedIndex() == 0 || cmbHistorico10.getSelectedIndex() == 1) {
            dtNueva10.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
            txtLote10.setText("");
            txtMarca10.setText("");
            dtProxima10.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
        } else {
            Object[] infoVacuna = instancias.getSql().infoVacunas(cmbHistorico10.getSelectedItem().toString().split("/")[0]);
            dtNueva10.setSelectedDate(metodos.haciaDate(infoVacuna[0].toString()));
            txtLote10.setText(infoVacuna[2].toString());
            txtMarca10.setText(infoVacuna[1].toString());
            dtProxima10.setSelectedDate(metodos.haciaDate(infoVacuna[3].toString()));
        }
    }//GEN-LAST:event_cmbHistorico10ItemStateChanged

    private void cmbHistorico9ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbHistorico9ItemStateChanged
        if (cmbHistorico9.getSelectedIndex() == 0 || cmbHistorico9.getSelectedIndex() == 1) {
            dtNueva9.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
            txtLote9.setText("");
            txtMarca9.setText("");
            dtProxima9.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
        } else {
            Object[] infoVacuna = instancias.getSql().infoVacunas(cmbHistorico9.getSelectedItem().toString().split("/")[0]);
            dtNueva9.setSelectedDate(metodos.haciaDate(infoVacuna[0].toString()));
            txtLote9.setText(infoVacuna[2].toString());
            txtMarca9.setText(infoVacuna[1].toString());
            dtProxima9.setSelectedDate(metodos.haciaDate(infoVacuna[3].toString()));
        }
    }//GEN-LAST:event_cmbHistorico9ItemStateChanged

    private void cmbHistorico8ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbHistorico8ItemStateChanged
        if (cmbHistorico8.getSelectedIndex() == 0 || cmbHistorico8.getSelectedIndex() == 1) {
            dtNueva8.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
            txtLote8.setText("");
            txtMarca8.setText("");
            dtProxima8.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
        } else {
            Object[] infoVacuna = instancias.getSql().infoVacunas(cmbHistorico8.getSelectedItem().toString().split("/")[0]);
            dtNueva8.setSelectedDate(metodos.haciaDate(infoVacuna[0].toString()));
            txtLote8.setText(infoVacuna[2].toString());
            txtMarca8.setText(infoVacuna[1].toString());
            dtProxima8.setSelectedDate(metodos.haciaDate(infoVacuna[3].toString()));
        }
    }//GEN-LAST:event_cmbHistorico8ItemStateChanged

    private void cmbHistorico7ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbHistorico7ItemStateChanged
        if (cmbHistorico7.getSelectedIndex() == 0 || cmbHistorico7.getSelectedIndex() == 1) {
            dtNueva7.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
            txtLote7.setText("");
            txtMarca7.setText("");
            dtProxima7.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
        } else {
            Object[] infoVacuna = instancias.getSql().infoVacunas(cmbHistorico7.getSelectedItem().toString().split("/")[0]);
            dtNueva7.setSelectedDate(metodos.haciaDate(infoVacuna[0].toString()));
            txtLote7.setText(infoVacuna[2].toString());
            txtMarca7.setText(infoVacuna[1].toString());
            dtProxima7.setSelectedDate(metodos.haciaDate(infoVacuna[3].toString()));
        }
    }//GEN-LAST:event_cmbHistorico7ItemStateChanged

    private void dtNueva7OnCommit(datechooser.events.CommitEvent evt) {//GEN-FIRST:event_dtNueva7OnCommit
        // TODO add your handling code here:
    }//GEN-LAST:event_dtNueva7OnCommit

    private void dtNueva7OnSelectionChange(datechooser.events.SelectionChangedEvent evt) {//GEN-FIRST:event_dtNueva7OnSelectionChange
        // TODO add your handling code here:
    }//GEN-LAST:event_dtNueva7OnSelectionChange

    private void dtProxima7OnCommit(datechooser.events.CommitEvent evt) {//GEN-FIRST:event_dtProxima7OnCommit
        // TODO add your handling code here:
    }//GEN-LAST:event_dtProxima7OnCommit

    private void dtProxima7OnSelectionChange(datechooser.events.SelectionChangedEvent evt) {//GEN-FIRST:event_dtProxima7OnSelectionChange
        // TODO add your handling code here:
    }//GEN-LAST:event_dtProxima7OnSelectionChange

    private void cmbHistorico11ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbHistorico11ItemStateChanged
        if (cmbHistorico11.getSelectedIndex() == 0 || cmbHistorico11.getSelectedIndex() == 1) {
            dtNueva11.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
            txtLote11.setText("");
            txtMarca11.setText("");
            dtProxima11.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
        } else {
            Object[] infoVacuna = instancias.getSql().infoVacunas(cmbHistorico11.getSelectedItem().toString().split("/")[0]);
            dtNueva11.setSelectedDate(metodos.haciaDate(infoVacuna[0].toString()));
            txtLote11.setText(infoVacuna[2].toString());
            txtMarca11.setText(infoVacuna[1].toString());
            dtProxima11.setSelectedDate(metodos.haciaDate(infoVacuna[3].toString()));
        }
    }//GEN-LAST:event_cmbHistorico11ItemStateChanged

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        if (lista.getSelectedIndex() == 0) {
            metodos.msgAdvertencia(this, "Debe de generar una consulta asociada");
            return;
        }

        instancias.getRemisionVeterinaria().nuevaFormula(historia, dtControl.getText(), txtHistoria.getText());
    }//GEN-LAST:event_jButton8ActionPerformed

    private void alerta12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alerta12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_alerta12ActionPerformed

    private void cmbHistorico12ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbHistorico12ItemStateChanged
        if (cmbHistorico12.getSelectedIndex() == 0 || cmbHistorico12.getSelectedIndex() == 1) {
            dtNueva12.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
            txtLote12.setText("");
            txtMarca12.setText("");
            dtProxima12.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
        } else {
            Object[] infoVacuna = instancias.getSql().infoVacunas(cmbHistorico12.getSelectedItem().toString().split("/")[0]);
            dtNueva12.setSelectedDate(metodos.haciaDate(infoVacuna[0].toString()));
            txtLote12.setText(infoVacuna[2].toString());
            txtMarca12.setText(infoVacuna[1].toString());
            dtProxima12.setSelectedDate(metodos.haciaDate(infoVacuna[3].toString()));

        }
    }//GEN-LAST:event_cmbHistorico12ItemStateChanged

    private void alerta13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alerta13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_alerta13ActionPerformed

    private void cmbHistorico13ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbHistorico13ItemStateChanged
        if (cmbHistorico13.getSelectedIndex() == 0 || cmbHistorico13.getSelectedIndex() == 1) {
            dtNueva13.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
            txtLote13.setText("");
            txtMarca13.setText("");
            dtProxima13.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
        } else {
            Object[] infoVacuna = instancias.getSql().infoVacunas(cmbHistorico13.getSelectedItem().toString().split("/")[0]);
            dtNueva13.setSelectedDate(metodos.haciaDate(infoVacuna[0].toString()));
            txtLote13.setText(infoVacuna[2].toString());
            txtMarca13.setText(infoVacuna[1].toString());
            dtProxima13.setSelectedDate(metodos.haciaDate(infoVacuna[3].toString()));
        }
    }//GEN-LAST:event_cmbHistorico13ItemStateChanged

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        if (metodos.msgPregunta(this, "¿Desea continuar?") == 0) {
            String[] consulta = lista.getSelectedValue().toString().split(" - ");
            instancias.getSql().modificarDiagnostico(txtPlanDiagnostico.getText(), consulta[0]);

            metodos.msgExito(this, "Diagnostico definitivo modificado con exito");
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        try {
            JFileChooser chooser = new JFileChooser();
            ImagePreviewPanel preview = new ImagePreviewPanel();
            chooser.setAccessory(preview);
            chooser.addPropertyChangeListener(preview);

            int respuesta = chooser.showSaveDialog(this);

            if (respuesta == JFileChooser.APPROVE_OPTION) {
                FROM2 = Paths.get(chooser.getSelectedFile().getAbsolutePath());
            }
            txtNombreAnexoConsulta.setText(chooser.getSelectedFile().getName());

        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void txtDescripcionExamenConsultaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionExamenConsultaKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jButton10ActionPerformed(null);
        }
    }//GEN-LAST:event_txtDescripcionExamenConsultaKeyReleased

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed

        String id = "";
        if (txtDescripcionExamenConsulta.getText().equals("")) {
            metodos.msgError(this, "No ha ingresado ninguna descripción del anexo");
            return;
        }

        if (txtNombreAnexoConsulta.getText().contains(",") || txtNombreAnexoConsulta.getText().contains(";")) {
            metodos.msgError(this, "El nombre del archivo no puede contener comas ',' ni ';' ");
            return;
        }

        id = instancias.getSql().getNumConsecutivo("DOCIMPORTADO")[0] + "-" + txtNombreAnexoConsulta.getText();

        //        Path TO = Paths.get(System.getProperty("user.dir") + "\\imagenes\\medico\\IMG-" + id + ".jpg");
        Path TO = Paths.get(System.getProperty("user.dir") + "\\imagenes\\medico\\documentosImportados\\" + id);

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

        if (!instancias.getSql().aumentarConsecutivo("DOCIMPORTADO", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("DOCIMPORTADO")[0]) + 1)) {
            metodos.msgError(this, "Hubo un problema al guardar el codigo de la imagen");
        }

        DefaultTableModel modeloTabla = (DefaultTableModel) tblExamenes.getModel();
        modeloTabla.addRow(new Object[]{id, txtDescripcionExamenConsulta.getText(), TO.toString()});

        txtDescripcionExamenConsulta.setText("");
        txtNombreAnexoConsulta.setText("");
        FROM2 = null;

    }//GEN-LAST:event_jButton10ActionPerformed

    private void tblExamenesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblExamenesMouseClicked
        if (evt.getClickCount() > 1) {
            //ruta del archivo en el pc
            String file = tblExamenes.getValueAt(tblExamenes.getSelectedRow(), 2).toString();
            try {
                //definiendo la ruta en la propiedad file

                //                File path = new File(file);
                //                Desktop.getDesktop().open(path);
                Runtime.getRuntime().exec("rundll32 SHELL32.DLL,ShellExec_RunDLL " + file);
            } catch (IOException e) {

            }
        }
    }//GEN-LAST:event_tblExamenesMouseClicked

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        try {
            JFileChooser chooser = new JFileChooser();
            ImagePreviewPanel preview = new ImagePreviewPanel();
            chooser.setAccessory(preview);
            chooser.addPropertyChangeListener(preview);

            int respuesta = chooser.showSaveDialog(this);

            if (respuesta == JFileChooser.APPROVE_OPTION) {
                FROM2 = Paths.get(chooser.getSelectedFile().getAbsolutePath());
            }
            txtNombreAnexoConsulta1.setText(chooser.getSelectedFile().getName());

        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void txtDescripcionExamenConsulta1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionExamenConsulta1KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jButton12ActionPerformed(null);
        }
    }//GEN-LAST:event_txtDescripcionExamenConsulta1KeyReleased

    private void tblEvolucionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEvolucionesMouseClicked
        if (evt.getClickCount() > 1) {
            String file = tblEvoluciones.getValueAt(tblEvoluciones.getSelectedRow(), 2).toString();
            try {
                Runtime.getRuntime().exec("rundll32 SHELL32.DLL,ShellExec_RunDLL " + file);
            } catch (IOException e) {

            }
        }
    }//GEN-LAST:event_tblEvolucionesMouseClicked

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed

        String id = "";

        if (txtDescripcionExamenConsulta1.getText().equals("")) {
            metodos.msgError(this, "No ha ingresado ninguna descripción del anexo");
            return;
        }

        if (txtNombreAnexoConsulta1.getText().contains(",") || txtNombreAnexoConsulta1.getText().contains(";")) {
            metodos.msgError(this, "El nombre del archivo no puede contener comas ',' ni ';' ");
            return;
        }

        id = instancias.getSql().getNumConsecutivo("DOCIMPORTADO")[0] + "-" + txtNombreAnexoConsulta1.getText();

        //        Path TO = Paths.get(System.getProperty("user.dir") + "\\imagenes\\medico\\IMG-" + id + ".jpg");
        Path TO = Paths.get(System.getProperty("user.dir") + "\\imagenes\\medico\\documentosImportados\\" + id);

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

        if (!instancias.getSql().aumentarConsecutivo("DOCIMPORTADO", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("DOCIMPORTADO")[0]) + 1)) {
            metodos.msgError(this, "Hubo un problema al guardar el codigo de la imagen");
        }

        DefaultTableModel modeloTabla = (DefaultTableModel) tblEvoluciones.getModel();

        modeloTabla.addRow(new Object[]{id, txtDescripcionExamenConsulta1.getText(), TO.toString()});

        txtDescripcionExamenConsulta1.setText("");
        txtNombreAnexoConsulta1.setText("");
        FROM2 = null;
    }//GEN-LAST:event_jButton12ActionPerformed

    private void cmbHistorico11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbHistorico11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbHistorico11ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        if (lista.getSelectedIndex() == 0) {
            metodos.msgAdvertencia(this, "Debe de generar una consulta asociada");
            return;
        }
        instancias.getQuirurgica().nuevaFormula(historia, dtControl.getText(), txtHistoria.getText());
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        instancias.getReporte().ver_ImpresionVacunas(txtCodigo.getText());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtEspecieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEspecieActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEspecieActionPerformed

    private void txtEspecieKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEspecieKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEspecieKeyReleased

    private void txtEspecieKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEspecieKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEspecieKeyTyped

    private void cmbFechaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbFechaItemStateChanged
        try {
            if (cmbFecha.getSelectedItem().equals("HOY - NUEVO")) {

                btnGuardar3.setEnabled(true);

                seguimientoEditable(true);
                txtFc3.setText("");
                txtTllc3.setText("");
                txtFr3.setText("");
                txtT3.setText("");
                txtPulso3.setText("");
                txtCc3.setText("");
                txtPeso3.setText("");
                txtMucosa1.setText("");
                txtEvolucionSeg.setText("");
                txtObservacionesSeg.setText("");
            } else {
                btnGuardar3.setEnabled(false);
                seguimientoEditable(false);

                ndSeguimiento nodo = instancias.getSql().getDatosSeguimiento(cmbFecha.getSelectedItem().toString().split(" - ")[0]);

                txtProfesional.setText(instancias.getSql().getDatosUsuario(nodo.getUsuario()).getNombre());
                txtEvolucionSeg.setText(nodo.getEvolucion());
                txtObservacionesSeg.setText(nodo.getObservaciones());
                txtFc3.setText(nodo.getFc());
                txtTllc3.setText(nodo.getTllc());
                txtFr3.setText(nodo.getFr());
                txtT3.setText(nodo.getT());
                txtPulso3.setText(nodo.getPulso());
                txtCc3.setText(nodo.getCc());
                txtPeso3.setText(nodo.getPeso());
                txtMucosa1.setText(nodo.getMucosa());

                if (!nodo.getExamen1().equals("")) {
                    String[] examen = nodo.getExamen1().split(";");
                    DefaultTableModel modeloTabla = (DefaultTableModel) tblEvoluciones.getModel();
                    for (int i = 0; i < examen.length; i++) {
                        modeloTabla.addRow(new Object[]{examen[i].split(",")[0], examen[i].split(",")[1], examen[i].split(",")[2]});
                    }
                }

//                try {
//                    actual = 0;
//                    cambiarImagen(0);
//                } catch (Exception x) {
//                    lbFoto4.setIcon(null);
//                    repaint();
//                }
            }
        } catch (NullPointerException e) {
            seguimientoEditable(true);
        }
    }//GEN-LAST:event_cmbFechaItemStateChanged

    private void cmbFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbFechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbFechaActionPerformed

    private void btnGuardar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar3ActionPerformed
        if (metodos.msgPregunta(this, "¿Desea continuar?") == 0) {
            int i = 0;
            if ((!txtEvolucionSeg.getText().equals("") || !txtObservacionesSeg.getText().equals("")) && cmbFecha.getSelectedIndex() == 0 && lista.getSelectedIndex() != 0) {
                Object[][] fechas = instancias.getSql().getConsultas(historia.getId());
                String consulta = fechas[fechas.length - 1][0].toString();

                if (lista.getSelectedIndex() != 0) {
                    consulta = lista.getSelectedValue().toString().split(" - ")[0];
                }

                String examen = "";
                for (int j = 0; j < tblEvoluciones.getRowCount(); j++) {
                    examen = examen + tblEvoluciones.getValueAt(j, 0) + "," + tblEvoluciones.getValueAt(j, 1) + "," + tblEvoluciones.getValueAt(j, 2) + ";";
                }

                if (!examen.equals("")) {
                    examen = examen.substring(0, examen.length() - 1);
                }

                Object[] vector2 = {"", metodos.fechaConsulta(metodosGenerales.fecha()), instancias.getUsuarioLog().getUsuario(), txtEvolucionSeg.getText(), txtObservacionesSeg.getText(),
                    "", consulta, examen, "", "", "", txtFc3.getText(), txtTllc3.getText(), txtFr3.getText(), txtT3.getText(),
                    txtPulso3.getText(), txtCc3.getText(), txtPeso3.getText(), txtMucosa1.getText()};

                ndSeguimiento nodoSeguimiento = metodos.llenarSeguimiento(vector2);

                if (!instancias.getSql().agregarSeguimiento(nodoSeguimiento)) {
                    metodos.msgError(this, "Hubo un problema al guardar el seguimiento");
                    return;
                }
                i++;
            }

            if (i > 0) {
                metodos.msgExito(this, "Control guardado con exito");
                seguimientoEditable(true);
                consultaEditable(true);
                setMascota(txtCodigo.getText());
            }
        }

        txtFc3.setText("");
        txtTllc3.setText("");
        txtFr3.setText("");
        txtT3.setText("");
        txtPulso3.setText("");
        txtCc3.setText("");
        txtPeso3.setText("");
        txtMucosa1.setText("");
    }//GEN-LAST:event_btnGuardar3ActionPerformed

    public void setInstancias(Instancias instancias) {
        this.instancias = instancias;
    }

    public void setMascota(String mascota) {
        for (int i = 0; i < 5; i++) {
            tblSistemas.setValueAt(false, i, 1);
            tblSistemas.setValueAt(false, i, 3);
        }

        cmbFecha.addItem("HOY - NUEVO");

        for (int x = 0; x < jPanel7.getComponentCount(); x++) {
            if (jPanel7.getComponent(x) instanceof DateChooserCombo) {
                DateChooserCombo textField = (DateChooserCombo) jPanel7.getComponent(x);
                textField.setText("");
            } else if (jPanel7.getComponent(x) instanceof JTextField) {
                JTextField textField = (JTextField) jPanel7.getComponent(x);
                textField.setText("");
            } else if (jPanel7.getComponent(x) instanceof DateChooserCombo) {
                DateChooserCombo textField = (DateChooserCombo) jPanel7.getComponent(x);
                textField.setText("");
            }
        }

        dtNueva1.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
        dtNueva2.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
        dtNueva3.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
        dtNueva4.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
        dtNueva5.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
        dtNueva6.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
        dtNueva7.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
        dtNueva8.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
        dtNueva9.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
        dtNueva10.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
        dtNueva11.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
        dtNueva12.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
        dtNueva13.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));

        dtProxima1.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
        dtProxima2.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
        dtProxima3.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
        dtProxima4.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
        dtProxima5.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
        dtProxima6.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
        dtProxima7.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
        dtProxima8.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
        dtProxima9.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
        dtProxima10.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
        dtProxima11.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
        dtProxima12.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));
        dtProxima13.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));

        dtControl.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));

        ndHistoria nodo = instancias.getSql().getDatosHistoria(mascota);
        ndMascota Mascota = instancias.getSql().getDatosMascota(mascota);
        ModeloContacto cliente = instancias.getSql().getDatosTercero(Mascota.getCliente());

        txtNombre.setText(Mascota.getNombre());
        txtRaza.setText(Mascota.getRaza());
        txtGenero.setText(Mascota.getGenero());
        txtObservaciones.setText(Mascota.getTemperamento());
        txtEspecie.setText(Mascota.getExpecie());

        if (Mascota.getExpecie() != null) {
            if (Mascota.getExpecie().equals("Gato")) {
                pnlVacunasGatos.setVisible(true);
                pnlVacunasPerros.setVisible(false);
            } else if (Mascota.getExpecie().equals("Perro")) {
                pnlVacunasGatos.setVisible(false);
                pnlVacunasPerros.setVisible(true);
            }
        }

//        txtEdad.setText(metodosGenerales.calcularEdad(Mascota.getEdad()) + "");
        String fecha = Mascota.getEdad();
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

        txtCodigo.setText(mascota);
        txtHistoria.setText(nodo.getId());
        txtReproductivo.setText(Mascota.getObservaciones());
        txtNombrePropietario.setText(cliente.getNombre());
        txtDireccion.setText(cliente.getDireccion());
        txtTelefono.setText(cliente.getTelefono());
        txtCedula.setText(cliente.getId());
//        txtEmail.setText(cliente.getEmail());
        txtDieta.setText(Mascota.getAlimentacion());
        txtFrecuencia.setText(Mascota.getRestricciones());
        txtObv.setText(Mascota.getObservacion());
//        txtColor.setText(Mascota.getColor());

//        ImageIcon fot = new ImageIcon("D:\\Dropbox\\imagenesClick\\IMG-" + Mascota.getFoto() + ".jpg");
        ImageIcon fot = new ImageIcon(Paths.get(System.getProperty("user.dir") + "\\imagenes\\veterinaria\\mascotas\\IMG-" + Mascota.getFoto() + ".jpg").toString());
        Icon icono = new ImageIcon(fot.getImage().getScaledInstance(lbFoto.getWidth(), lbFoto.getHeight(), Image.SCALE_DEFAULT));
        lbFoto.setIcon(icono);
        this.repaint();

        historia = nodo;

        vacunas(cmbPuppy, "1");
        vacunas(cmbHistorico2, "2");
        vacunas(cmbHistorico3, "3");
        vacunas(cmbHistorico4, "4");
        vacunas(cmbHistorico5, "5");
        vacunas(cmbHistorico6, "6");
        vacunas(cmbHistorico7, "7");
        vacunas(cmbHistorico8, "8");
        vacunas(cmbHistorico9, "9");
        vacunas(cmbHistorico10, "10");
        vacunas(cmbHistorico11, "11");
        vacunas(cmbHistorico12, "12");
        vacunas(cmbHistorico13, "13");

        botonAlerta(cmbPuppy, alerta1);
        botonAlerta(cmbHistorico2, alerta2);
        botonAlerta(cmbHistorico3, alerta3);
        botonAlerta(cmbHistorico4, alerta4);
        botonAlerta(cmbHistorico5, alerta5);
        botonAlerta(cmbHistorico6, alerta6);
        botonAlerta(cmbHistorico7, alerta7);
        botonAlerta(cmbHistorico8, alerta8);
        botonAlerta(cmbHistorico9, alerta9);
        botonAlerta(cmbHistorico10, alerta10);
        botonAlerta(cmbHistorico11, alerta11);
        botonAlerta(cmbHistorico12, alerta12);
        botonAlerta(cmbHistorico13, alerta13);

        System.out.println(historia.getMotivo());

        if ((historia.getMotivo() == null) || (historia.getMotivo().equals("")) || (historia.getMotivo().equals(null)) || (historia.getMotivo().equals("null"))) {
            txtMotivoAnt.setText("");
        } else {
            txtMotivoAnt.setText(historia.getMotivo());
        }

        if ((historia.getEvolucion() == null) || (historia.getEvolucion().equals("")) || (historia.getEvolucion().equals(null)) || (historia.getEvolucion().equals("null"))) {
            txtEvolucionAnt.setText("");
        } else {
            txtEvolucionAnt.setText(historia.getEvolucion());
        }

//        if ((historia.getEnfermedades() == null) || (historia.getEnfermedades().equals("")) || (historia.getEnfermedades().equals(null)) || (historia.getEnfermedades().equals("null"))) {
//            txtEnfermedades.setText("");
//        } else {
//            txtEnfermedades.setText(historia.getEnfermedades());
//        }
        if ((historia.getTratamientos() == null) || (historia.getTratamientos().equals("")) || (historia.getTratamientos().equals(null)) || (historia.getTratamientos().equals("null"))) {
            txtTratamientos.setText("");
        } else {
            txtTratamientos.setText(historia.getTratamientos());
        }

        Object[][] fechas = instancias.getSql().getConsultas(historia.getId());
        Object[] datos = new Object[fechas.length + 1];
        datos[0] = "HOY - NUEVO";
        int j = fechas.length;

        for (int i = 0; i < fechas.length; i++) {
            datos[j--] = fechas[i][0] + " - " + metodos.fecha(fechas[i][1].toString());
        }

        lista.setListData(datos);
        lista.setSelectedIndex(0);
        txtSistemas.setText("");
    }

    public void limpiarPantalla() {
        txtNombre.setText("");
        txtRaza.setText("");
        txtGenero.setText("");
        txtEdad.setText("");
        txtCodigo.setText("");
        txtHistoria.setText("");
        txtReproductivo.setText("");
        txtNombrePropietario.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        txtCedula.setText("");
        txtDieta.setText("");
        txtFrecuencia.setText("");

        txtMotivoAnt.setText("");
        txtEvolucionAnt.setText("");
//        txtEnfermedades.setText("");
        txtTratamientos.setText("");

        consultaEditable(false);
        seguimientoEditable(false);

        limpiarExamenes(tblEvoluciones);
        limpiarExamenes(tblExamenes);

        limpiarCombo(cmbPuppy);
        limpiarCombo(cmbHistorico2);
        limpiarCombo(cmbHistorico3);
        limpiarCombo(cmbHistorico4);
        limpiarCombo(cmbHistorico5);
        limpiarCombo(cmbHistorico6);
        limpiarCombo(cmbHistorico7);
        limpiarCombo(cmbHistorico8);
        limpiarCombo(cmbHistorico9);
        limpiarCombo(cmbHistorico10);
        limpiarCombo(cmbHistorico11);
        limpiarCombo(cmbHistorico12);
        limpiarCombo(cmbHistorico13);
    }

    public void limpiarCombo(JComboBox combo) {
        for (int i = 1; i < combo.getItemCount(); i++) {
            combo.removeItemAt(i);
        }
    }

    public String getSistemas() {

        int i = tblSistemas.getSelectedColumn();

        if (i == 1) {
            i = 0;
        } else if (i == 3) {
            i = 2;
        }

        String variable = tblSistemas.getValueAt(tblSistemas.getSelectedRow(), i).toString();

        lbSistemas.setText(variable.substring(4));
        switch (variable) {
            case "    Nervioso:":
                variable = nodoConsulta.getNervioso();
                break;
            case "    Digestivo:":
                variable = nodoConsulta.getDigestivo();
                break;
            case "    Respiratorio:":
                variable = nodoConsulta.getRespiratorio();
                break;
            case "    Cardiaco:":
                variable = nodoConsulta.getCardiaco();
                break;
            case "    Urinario:":
                variable = nodoConsulta.getUrinario();
                break;
            case "    Musculo esquelético:":
                variable = nodoConsulta.getMusculo();
                break;
            case "    Piel y anexos:":
                variable = nodoConsulta.getPiel();
                break;
            case "    Reproductivo:":
                variable = nodoConsulta.getActitud();
                break;
            case "    Organos sentidos:":
                variable = nodoConsulta.getOrganosSentidos();
                break;
            case "    Ganglios linfaticos:":
                variable = nodoConsulta.getGanglios();
                break;
            case "    Endocrino:":
                variable = nodoConsulta.getEndocrino();
                break;
        }
        txtSistemas.requestFocus();
        return variable;
    }

    String convert(boolean x) {

        if (x) {
            return "+";
        }

        return "-";
    }

    boolean asConvert(String x) {

        if (x.equals("+")) {
            return true;
        }
        return false;
    }

    public void setSistemas() {
        switch (lbSistemas.getText()) {

            case "Nervioso:":
                nodoConsulta.setNervioso(txtSistemas.getText());
                break;
            case "Digestivo:":
                nodoConsulta.setDigestivo(txtSistemas.getText());
                break;
            case "Respiratorio:":
                nodoConsulta.setRespiratorio(txtSistemas.getText());
                break;
            case "Cardiaco:":
                nodoConsulta.setCardiaco(txtSistemas.getText());
                break;
            case "Urinario:":
                nodoConsulta.setUrinario(txtSistemas.getText());
                break;
            case "Musculo esquelético:":
                nodoConsulta.setMusculo(txtSistemas.getText());
                break;
            case "Piel y anexos:":
                nodoConsulta.setPiel(txtSistemas.getText());
                break;
            case "Reproductivo:":
                nodoConsulta.setActitud(txtSistemas.getText());
                break;
            case "Hidratación:":
                nodoConsulta.setHidratacion(txtSistemas.getText());
                break;
            case "Estado nutricional:":
                nodoConsulta.setNutricional(txtSistemas.getText());
                break;
            case "Organos sentidos:":
                nodoConsulta.setOrganosSentidos(txtSistemas.getText());
                break;
            case "Ganglios linfaticos:":
                nodoConsulta.setGanglios(txtSistemas.getText());
                break;
            case "Endocrino:":
                nodoConsulta.setEndocrino(txtSistemas.getText());
                break;
        }
    }

    public void consultaEditable(boolean x) {
        txtMotivo.setEditable(x);
        txtFc.setEditable(x);
        txtFr.setEditable(x);
        txtPulso.setEditable(x);
        txtPeso.setEditable(x);
        txtTllc.setEditable(x);
        txtT.setEditable(x);
        txtCc.setEditable(x);
        txtProblemas.setEditable(x);
        txtDiagPresunti.setEditable(x);
        txtPlanDiagnostico.setEditable(x);
        txtTratamiento.setEditable(x);
        txtRecomendaciones.setEditable(x);
        txtCaracteristicas.setEditable(x);
        txtMucosa.setEditable(x);

        if (x) {
            cmbFecha.removeAllItems();
            cmbFecha.addItem("HOY - NUEVO");
        }

        txtMotivo.setText("");
        txtFc.setText("");
        txtFr.setText("");
        txtPulso.setText("");
        txtPeso.setText("");
        txtTllc.setText("");
        txtT.setText("");
        txtCc.setText("");
        txtProblemas.setText("");
        txtDiagPresunti.setText("");
        txtPlanDiagnostico.setText("");
        txtTratamiento.setText("");
        txtRecomendaciones.setText("");
        txtCaracteristicas.setText("");
        txtMucosa.setText("");
        dtControl.setSelectedDate(metodos.haciaDate(metodos.fecha2(metodosGenerales.fecha())));

//        lbCodFoto2.setText("");
//        txtExamenes1.setEditable(x);
//        jButton4.setEnabled(x);
//        jButton6.setEnabled(x);
        for (int i = 0; i < 6; i++) {
            tblSistemas.setValueAt(false, i, 1);
            tblSistemas.setValueAt(false, i, 3);
        }

        limpiarExamenes(tblEvoluciones);
        limpiarExamenes(tblExamenes);

    }

    public void seguimientoEditable(boolean x) {
        txtEvolucionSeg.setEditable(x);
        txtObservacionesSeg.setEditable(x);
        limpiarExamenes(tblEvoluciones);
        if (x) {
            txtProfesional.setText(instancias.getUsuarioLog().getNombre());
            repaint();
        }
//        txtEvolucionSeg.setText("");
//        txtObservacionesSeg.setText("");
    }

//    public void cambiarImagen(int x) {
//
//        imagenes = lbCodFoto1.getText().split(";");
//
//        if (((actual + x) >= 0) && ((actual + x) < imagenes.length)) {
//
//            actual += x;
//
//            ImageIcon fot = new ImageIcon(System.getProperty("user.dir") + "\\imagenes\\veterinaria\\examenes\\IMG-" + imagenes[actual] + ".jpg");
//
//            Icon icono = new ImageIcon(fot.getImage().getScaledInstance(lbFoto4.getWidth(), lbFoto4.getHeight(), Image.SCALE_DEFAULT));
//            lbFoto4.setIcon(icono);
//            this.repaint();
//        }
//    }
//    public void cambiarImagenConsulta(int x) {
//
//        imagenes = lbCodFoto2.getText().split(";");
//
//        if (((actual + x) >= 0) && ((actual + x) < imagenes.length)) {
//
//            actual += x;
//
//            ImageIcon fot = new ImageIcon(System.getProperty("user.dir") + "\\imagenes\\veterinaria\\examenes\\IMG-" + imagenes[actual] + ".jpg");
//
//            Icon icono = new ImageIcon(fot.getImage().getScaledInstance(lbFoto5.getWidth(), lbFoto5.getHeight(), Image.SCALE_DEFAULT));
//            lbFoto5.setIcon(icono);
//            this.repaint();
//        }
//    }
//    public void seleccionarImagen(String x) {
////Selecciona imagen del seguimiento
//        ImageIcon fot = new ImageIcon(System.getProperty("user.dir") + "\\imagenes\\veterinaria\\examenes\\IMG-" + x + ".jpg");
//        Icon icono = new ImageIcon(fot.getImage().getScaledInstance(lbFoto4.getWidth(), lbFoto4.getHeight(), Image.SCALE_DEFAULT));
//        lbFoto4.setIcon(icono);
//        this.repaint();
//
//    }
//    public void seleccionarImagenConsulta(String x) {
////Selecciona imagen de la consulta
//        ImageIcon fot = new ImageIcon(System.getProperty("user.dir") + "\\imagenes\\veterinaria\\examenes\\IMG-" + x + ".jpg");
//        Icon icono = new ImageIcon(fot.getImage().getScaledInstance(lbFoto5.getWidth(), lbFoto5.getHeight(), Image.SCALE_DEFAULT));
//        lbFoto5.setIcon(icono);
//        this.repaint();
//
//    }
    public void limpiarExamenes(JTable tabla) {
        int cant = tabla.getRowCount();
        for (int i = 0; i < cant; i++) {
            ((DefaultTableModel) tabla.getModel()).removeRow(0);
        }
    }

//    public void montarExamen(int num) {
//
//        if (cmbFecha.getSelectedIndex() != 0) {
//
//            if (tblExamens.getValueAt(num, 1) == null || tblExamens.getValueAt(num, 1).toString().equals("")) {
//                metodos.msgError(this, "No hay examen");
//            } else {
//                visor1 completa = new visor1(tblExamens.getValueAt(num, 1).toString());
//                completa.show();
//            }
//
//            System.out.println(tblExamens.getValueAt(num, 1));
//            return;
//        }
//
//        JFileChooser chooser = new JFileChooser();
//        ImagePreviewPanel preview = new ImagePreviewPanel();
//        chooser.setAccessory(preview);
//        chooser.addPropertyChangeListener(preview);
//
//        int respuesta = chooser.showSaveDialog(this);
//
//        if (respuesta == JFileChooser.APPROVE_OPTION) {
//            Path FROM = Paths.get(chooser.getSelectedFile().getAbsolutePath());
//            String id = "";
//
//            id = Arrays.toString(sql.getNumConsecutivo("IMG"));
//
//            if (!sql.aumentarConsecutivo("IMG", Integer.parseInt((String) sql.getNumConsecutivo("IMG")[0]) + 1)) {
//                metodos.msgError(this, "Hubo un problema al guardar el codigo de la imagen");
//            }
//
//            tblExamens.setValueAt(id, num, 1);
//
////            if (lbCodFoto2.getText().equals("")) {
////                lbCodFoto2.setText(id);
////            } else {
////                lbCodFoto2.setText(lbCodFoto2.getText() + ";" + id);
////            }
//
////            Path TO = Paths.get("D:\\Dropbox\\imagenesClick\\IMG-" + id + ".jpg");
//            Path TO = Paths.get(System.getProperty("user.dir") + "\\imagenes\\veterinaria\\examenes\\IMG-" + id + ".jpg");
//
//            //sobreescribir el fichero de destino, si existe, y copiar
//            // los atributos, incluyendo los permisos rwx
//            CopyOption[] options = new CopyOption[]{
//                StandardCopyOption.REPLACE_EXISTING,
//                StandardCopyOption.COPY_ATTRIBUTES
//            };
//
//            try {
//                Files.copy(FROM, TO, options);
//
//                ImageIcon fot = new ImageIcon(chooser.getSelectedFile().getAbsolutePath());
//                Icon icono = new ImageIcon(fot.getImage().getScaledInstance(lbFoto4.getWidth(), lbFoto4.getHeight(), Image.SCALE_DEFAULT));
//                lbFoto4.setIcon(icono);
//                this.repaint();
//
//            } catch (Exception ex) {
//                metodos.msgError(this, "Hubo un error al cargar el archivo");
//            }
//
//        }
//    }
    public void validarCheckRevision() {

        if (!nodoConsulta.getNervioso().equalsIgnoreCase("")) {
            tblSistemas.setValueAt(true, 0, 1);
        } else {
            tblSistemas.setValueAt(false, 0, 1);
        }

        if (!nodoConsulta.getDigestivo().equalsIgnoreCase("")) {
            tblSistemas.setValueAt(true, 1, 1);
        } else {
            tblSistemas.setValueAt(false, 1, 1);
        }

        if (!nodoConsulta.getRespiratorio().equalsIgnoreCase("")) {
            tblSistemas.setValueAt(true, 2, 1);
        } else {
            tblSistemas.setValueAt(false, 2, 1);
        }

        if (!nodoConsulta.getCardiaco().equalsIgnoreCase("")) {
            tblSistemas.setValueAt(true, 3, 1);
        } else {
            tblSistemas.setValueAt(false, 3, 1);
        }

        if (!nodoConsulta.getUrinario().equalsIgnoreCase("")) {
            tblSistemas.setValueAt(true, 4, 1);
        } else {
            tblSistemas.setValueAt(false, 4, 1);
        }

        if (!nodoConsulta.getMusculo().equalsIgnoreCase("")) {
            tblSistemas.setValueAt(true, 0, 3);
        } else {
            tblSistemas.setValueAt(false, 0, 3);
        }

        if (!nodoConsulta.getPiel().equalsIgnoreCase("")) {
            tblSistemas.setValueAt(true, 1, 3);
        } else {
            tblSistemas.setValueAt(false, 1, 3);
        }

        if (!nodoConsulta.getOrganosSentidos().equalsIgnoreCase("")) {
            tblSistemas.setValueAt(true, 2, 3);
        } else {
            tblSistemas.setValueAt(false, 2, 3);
        }

        if (!nodoConsulta.getGanglios().equalsIgnoreCase("")) {
            tblSistemas.setValueAt(true, 3, 3);
        } else {
            tblSistemas.setValueAt(false, 3, 3);
        }

        if (!nodoConsulta.getActitud().equalsIgnoreCase("")) {
            tblSistemas.setValueAt(true, 4, 3);
        } else {
            tblSistemas.setValueAt(false, 4, 3);
        }
        if (!nodoConsulta.getEndocrino().equalsIgnoreCase("")) {
            tblSistemas.setValueAt(true, 5, 1);
        } else {
            tblSistemas.setValueAt(false, 5, 1);
        }
    }
//    public void grabarVacuna(DateChooserCombo campo, Object datos[]) {
//        if (!campo.getText().equalsIgnoreCase("")) {
//            if (!instancias.getSql().agregarVacuna(datos)) {
//                metodos.msgError(this, "Hubo un problema al guardar el concepto");
//                return;
//            }
//        }
//    }

    public boolean grabarVacuna(JComboBox campo, Object datos[], String texto1, String texto2, String nombreVacuna) {
        boolean retorno = true;

        if (campo.getSelectedIndex() == 1) {
            if (texto1.equals("") || texto2.equals("")) {
                metodos.msgError(this, "No ha ingresado Lote o Marca en " + nombreVacuna);
                retorno = false;
            } else {
                if (!instancias.getSql().agregarVacuna(datos)) {
                    metodos.msgError(this, "Hubo un problema al guardar la vacuna");
                    retorno = false;
                }
            }
        } else {
            retorno = true;
        }
        return retorno;
    }

    public void vacunas(JComboBox combo, String id) {

        Object[][] fechas = instancias.getSql().getVacunaciones(txtCodigo.getText(), id);
        Object[] datos = new Object[fechas.length + 2];

        int j = fechas.length + 1;
        datos[0] = " ";
        datos[1] = "HOY - NUEVO";
        for (int i = 0; i < fechas.length; i++) {
            datos[j--] = fechas[i][0].toString() + "/ " + fechas[i][1].toString();
        }
        combo.setModel(new javax.swing.DefaultComboBoxModel(datos));
    }

    public void botonAlerta(JComboBox combo, JButton botonAlerta) {
        if (combo.getItemCount() > 2) {
            botonAlerta.setBackground(new Color(102, 153, 0));
        } else {
            botonAlerta.setBackground(new Color(204, 0, 0));
        }
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

    public boolean validarHistoriaCargada() {
        System.out.println("validando:" + txtCodigo.getText());
        if (txtCodigo.getText().equals("")) {
            System.out.println("entro a true");
            return true;

        } else {
            return false;
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton alerta1;
    private javax.swing.JButton alerta10;
    private javax.swing.JButton alerta11;
    private javax.swing.JButton alerta12;
    private javax.swing.JButton alerta13;
    private javax.swing.JButton alerta2;
    private javax.swing.JButton alerta3;
    private javax.swing.JButton alerta4;
    private javax.swing.JButton alerta5;
    private javax.swing.JButton alerta6;
    private javax.swing.JButton alerta7;
    private javax.swing.JButton alerta8;
    private javax.swing.JButton alerta9;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnGuardar1;
    private javax.swing.JButton btnGuardar2;
    private javax.swing.JButton btnGuardar3;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnReimprimir;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cmbFecha;
    private javax.swing.JComboBox cmbHistorico10;
    private javax.swing.JComboBox cmbHistorico11;
    private javax.swing.JComboBox cmbHistorico12;
    private javax.swing.JComboBox cmbHistorico13;
    private javax.swing.JComboBox cmbHistorico2;
    private javax.swing.JComboBox cmbHistorico3;
    private javax.swing.JComboBox cmbHistorico4;
    private javax.swing.JComboBox cmbHistorico5;
    private javax.swing.JComboBox cmbHistorico6;
    private javax.swing.JComboBox cmbHistorico7;
    private javax.swing.JComboBox cmbHistorico8;
    private javax.swing.JComboBox cmbHistorico9;
    private javax.swing.JComboBox cmbPuppy;
    private datechooser.beans.DateChooserCombo dtControl;
    private datechooser.beans.DateChooserCombo dtNueva1;
    private datechooser.beans.DateChooserCombo dtNueva10;
    private datechooser.beans.DateChooserCombo dtNueva11;
    private datechooser.beans.DateChooserCombo dtNueva12;
    private datechooser.beans.DateChooserCombo dtNueva13;
    private datechooser.beans.DateChooserCombo dtNueva2;
    private datechooser.beans.DateChooserCombo dtNueva3;
    private datechooser.beans.DateChooserCombo dtNueva4;
    private datechooser.beans.DateChooserCombo dtNueva5;
    private datechooser.beans.DateChooserCombo dtNueva6;
    private datechooser.beans.DateChooserCombo dtNueva7;
    private datechooser.beans.DateChooserCombo dtNueva8;
    private datechooser.beans.DateChooserCombo dtNueva9;
    private datechooser.beans.DateChooserCombo dtProxima1;
    private datechooser.beans.DateChooserCombo dtProxima10;
    private datechooser.beans.DateChooserCombo dtProxima11;
    private datechooser.beans.DateChooserCombo dtProxima12;
    private datechooser.beans.DateChooserCombo dtProxima13;
    private datechooser.beans.DateChooserCombo dtProxima2;
    private datechooser.beans.DateChooserCombo dtProxima3;
    private datechooser.beans.DateChooserCombo dtProxima4;
    private datechooser.beans.DateChooserCombo dtProxima5;
    private datechooser.beans.DateChooserCombo dtProxima6;
    private datechooser.beans.DateChooserCombo dtProxima7;
    private datechooser.beans.DateChooserCombo dtProxima8;
    private datechooser.beans.DateChooserCombo dtProxima9;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbDireccion16;
    private javax.swing.JLabel lbDireccion17;
    private javax.swing.JLabel lbDireccion18;
    private javax.swing.JLabel lbDireccion19;
    private javax.swing.JLabel lbDireccion20;
    private javax.swing.JLabel lbDireccion22;
    private javax.swing.JLabel lbDireccion23;
    private javax.swing.JLabel lbDireccion24;
    private javax.swing.JLabel lbDireccion25;
    private javax.swing.JLabel lbDireccion39;
    private javax.swing.JLabel lbDireccion4;
    private javax.swing.JLabel lbDireccion40;
    private javax.swing.JLabel lbDireccion41;
    private javax.swing.JLabel lbDireccion42;
    private javax.swing.JLabel lbDireccion43;
    private javax.swing.JLabel lbDireccion44;
    private javax.swing.JLabel lbDireccion45;
    private javax.swing.JLabel lbDireccion46;
    private javax.swing.JLabel lbDireccion5;
    private javax.swing.JLabel lbDireccion6;
    private javax.swing.JLabel lbDireccion9;
    private javax.swing.JLabel lbEmail1;
    private javax.swing.JLabel lbEmail10;
    private javax.swing.JLabel lbEmail11;
    private javax.swing.JLabel lbEmail12;
    private javax.swing.JLabel lbEmail14;
    private javax.swing.JLabel lbEmail15;
    private javax.swing.JLabel lbEmail16;
    private javax.swing.JLabel lbEmail17;
    private javax.swing.JLabel lbEmail2;
    private javax.swing.JLabel lbEmail20;
    private javax.swing.JLabel lbEmail21;
    private javax.swing.JLabel lbEmail22;
    private javax.swing.JLabel lbEmail23;
    private javax.swing.JLabel lbEmail25;
    private javax.swing.JLabel lbEmail27;
    private javax.swing.JLabel lbEmail28;
    private javax.swing.JLabel lbEmail3;
    private javax.swing.JLabel lbEmail4;
    private javax.swing.JLabel lbEmail45;
    private javax.swing.JLabel lbEmail5;
    private javax.swing.JLabel lbEmail6;
    private javax.swing.JLabel lbEmail8;
    private javax.swing.JLabel lbEmail9;
    private javax.swing.JLabel lbFoto;
    private javax.swing.JLabel lbImagenes;
    private javax.swing.JLabel lbNit;
    private javax.swing.JLabel lbNit1;
    private javax.swing.JLabel lbNit11;
    private javax.swing.JLabel lbNit12;
    private javax.swing.JLabel lbNit13;
    private javax.swing.JLabel lbNit14;
    private javax.swing.JLabel lbNit15;
    private javax.swing.JLabel lbNit16;
    private javax.swing.JLabel lbNit17;
    private javax.swing.JLabel lbNit18;
    private javax.swing.JLabel lbNit2;
    private javax.swing.JLabel lbNit23;
    private javax.swing.JLabel lbNit3;
    private javax.swing.JLabel lbNit39;
    private javax.swing.JLabel lbNit4;
    private javax.swing.JLabel lbNit40;
    private javax.swing.JLabel lbNit41;
    private javax.swing.JLabel lbNit42;
    private javax.swing.JLabel lbNit43;
    private javax.swing.JLabel lbNit44;
    private javax.swing.JLabel lbNit6;
    private javax.swing.JLabel lbNit7;
    private javax.swing.JLabel lbNit9;
    private javax.swing.JLabel lbNombre1;
    private javax.swing.JLabel lbNombre10;
    private javax.swing.JLabel lbNombre11;
    private javax.swing.JLabel lbNombre12;
    private javax.swing.JLabel lbNombre13;
    private javax.swing.JLabel lbNombre2;
    private javax.swing.JLabel lbNombre3;
    private javax.swing.JLabel lbNombre4;
    private javax.swing.JLabel lbNombre5;
    private javax.swing.JLabel lbNombre6;
    private javax.swing.JLabel lbNombre7;
    private javax.swing.JLabel lbNombre8;
    private javax.swing.JLabel lbNombre9;
    private javax.swing.JLabel lbRazon;
    private javax.swing.JLabel lbRazon1;
    private javax.swing.JLabel lbRazon5;
    private javax.swing.JLabel lbSistemas;
    private javax.swing.JList lista;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JPanel pnlVacunasGatos;
    private javax.swing.JPanel pnlVacunasPerros;
    private javax.swing.JMenuItem popBorrar;
    private javax.swing.JMenuItem popBorrar1;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JTable tblEvoluciones;
    private javax.swing.JTable tblExamenes;
    private javax.swing.JTable tblSistemas;
    private javax.swing.JTextField txtCaracteristicas;
    private javax.swing.JTextField txtCc;
    private javax.swing.JTextField txtCc3;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDescripcionExamenConsulta;
    private javax.swing.JTextField txtDescripcionExamenConsulta1;
    private javax.swing.JTextArea txtDiagPresunti;
    private javax.swing.JTextField txtDieta;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEdad;
    private javax.swing.JTextField txtEspecie;
    private javax.swing.JTextArea txtEvolucionAnt;
    private javax.swing.JTextArea txtEvolucionSeg;
    private javax.swing.JTextField txtFc;
    private javax.swing.JTextField txtFc3;
    private javax.swing.JTextField txtFr;
    private javax.swing.JTextField txtFr3;
    private javax.swing.JTextField txtFrecuencia;
    private javax.swing.JTextField txtGenero;
    private javax.swing.JTextField txtHistoria;
    private javax.swing.JTextField txtLote1;
    private javax.swing.JTextField txtLote10;
    private javax.swing.JTextField txtLote11;
    private javax.swing.JTextField txtLote12;
    private javax.swing.JTextField txtLote13;
    private javax.swing.JTextField txtLote2;
    private javax.swing.JTextField txtLote3;
    private javax.swing.JTextField txtLote4;
    private javax.swing.JTextField txtLote5;
    private javax.swing.JTextField txtLote6;
    private javax.swing.JTextField txtLote7;
    private javax.swing.JTextField txtLote8;
    private javax.swing.JTextField txtLote9;
    private javax.swing.JTextField txtMarca1;
    private javax.swing.JTextField txtMarca10;
    private javax.swing.JTextField txtMarca11;
    private javax.swing.JTextField txtMarca12;
    private javax.swing.JTextField txtMarca13;
    private javax.swing.JTextField txtMarca2;
    private javax.swing.JTextField txtMarca3;
    private javax.swing.JTextField txtMarca4;
    private javax.swing.JTextField txtMarca5;
    private javax.swing.JTextField txtMarca6;
    private javax.swing.JTextField txtMarca7;
    private javax.swing.JTextField txtMarca8;
    private javax.swing.JTextField txtMarca9;
    private javax.swing.JTextArea txtMotivo;
    private javax.swing.JTextArea txtMotivoAnt;
    private javax.swing.JTextField txtMucosa;
    private javax.swing.JTextField txtMucosa1;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNombreAnexoConsulta;
    private javax.swing.JTextField txtNombreAnexoConsulta1;
    private javax.swing.JTextField txtNombrePropietario;
    private javax.swing.JTextField txtObservaciones;
    private javax.swing.JTextArea txtObservacionesSeg;
    private javax.swing.JTextArea txtObv;
    private javax.swing.JTextField txtPeso;
    private javax.swing.JTextField txtPeso3;
    private javax.swing.JTextArea txtPlanDiagnostico;
    private javax.swing.JTextArea txtProblemas;
    private javax.swing.JTextField txtProfesional;
    private javax.swing.JTextField txtPulso;
    private javax.swing.JTextField txtPulso3;
    private javax.swing.JTextField txtRaza;
    private javax.swing.JTextArea txtRecomendaciones;
    private javax.swing.JTextField txtReproductivo;
    private javax.swing.JTextArea txtSistemas;
    private javax.swing.JTextField txtT;
    private javax.swing.JTextField txtT3;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtTllc;
    private javax.swing.JTextField txtTllc3;
    private javax.swing.JTextArea txtTratamiento;
    private javax.swing.JTextArea txtTratamientos;
    // End of variables declaration//GEN-END:variables
}
