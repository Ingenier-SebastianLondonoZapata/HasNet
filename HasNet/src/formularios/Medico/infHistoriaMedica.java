package formularios.Medico;

import clases.ImagePreviewPanel;
import clases.Instancias;
import clases.Medico.ndCamposOrdenPredeterminada;

import clases.Medico.ndConsultaClinica;
import clases.Medico.ndEpsPrecargados;
import clases.Medico.ndHistoriaClinica;
import clases.Medico.ndOrdenServicio;
import clases.Medico.ndSeguimientoClinica;

import Utilidades.BaseDatos.SQL;
import clases.big;
import clases.metodosGenerales;
import clases.productos.ndProducto;
import Modelo.Terceros.ModeloContacto;
import formularios.Genericos.buscPlantillaDeTextos;
import formularios.Veterinario.visor;

import java.awt.Dimension;
import java.awt.Event;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

public class infHistoriaMedica extends javax.swing.JInternalFrame {

    metodosGenerales metodos = new metodosGenerales();
    private Instancias instancias;

    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;
    private String paciente;

    ndHistoriaClinica historia;
    ModeloContacto nodoTercero;
    ndCamposOrdenPredeterminada nodoCampos;

    String imagenes[];
    private String orden;
    int actual;
    private JTable tabla;
    boolean facturarOrden = false;
    String productoFacturarOrden = "";
    public Path FROM2;
    boolean consultaCargada = false;

    public infHistoriaMedica() {

        initComponents();

        chkMedico.setSelected(true);
        pnlCirujano.setVisible(false);
        pnlCirujano1.setVisible(false);
        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        setBorder(null);
        repaint();

        instancias = Instancias.getInstancias();
        nodoCampos = instancias.getSql().getDatosCamposOrdenPredeterminada("1");;

        scrFormulario.getVerticalScrollBar().setUnitIncrement(18);
        pnlFormulario.registerKeyboardAction(accion("guardar"), "guardar", KeyStroke.getKeyStroke(KeyEvent.VK_G, Event.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
    }

    private ActionListener accion(final String opc) {
        ActionListener a = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (opc) {
                    case "guardar":
                        if ((!txtEvolucionSeg.getText().equals("") || !txtObservacionesSeg.getText().equals("")) && cmbFecha.getSelectedIndex() == 0) {
                            btnGuardarSeguimientoActionPerformed(null);
                        } else {
                            btnGuardarConsultaActionPerformed(null);
                        }
                        break;
                }
            }
        };
        return a;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        buttonGroup1 = new javax.swing.ButtonGroup();
        grupoFumador = new javax.swing.ButtonGroup();
        grupoIngesta = new javax.swing.ButtonGroup();
        grupoAlergias = new javax.swing.ButtonGroup();
        grupoToxicos = new javax.swing.ButtonGroup();
        grupoMedicamentos = new javax.swing.ButtonGroup();
        grupoPatologicos = new javax.swing.ButtonGroup();
        grupoPsiquiatricos = new javax.swing.ButtonGroup();
        grupoQuirurgicos = new javax.swing.ButtonGroup();
        grupoPlanificacion = new javax.swing.ButtonGroup();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        popBorrar = new javax.swing.JMenuItem();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        popBorrar1 = new javax.swing.JMenuItem();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        buttonGroup6 = new javax.swing.ButtonGroup();
        buttonGroup7 = new javax.swing.ButtonGroup();
        buttonGroup8 = new javax.swing.ButtonGroup();
        buttonGroup9 = new javax.swing.ButtonGroup();
        buttonGroup10 = new javax.swing.ButtonGroup();
        buttonGroup11 = new javax.swing.ButtonGroup();
        buttonGroup12 = new javax.swing.ButtonGroup();
        buttonGroup13 = new javax.swing.ButtonGroup();
        jPanel18 = new javax.swing.JPanel();
        buttonGroup14 = new javax.swing.ButtonGroup();
        buttonGroup15 = new javax.swing.ButtonGroup();
        scrFormulario = new javax.swing.JScrollPane();
        pnlFormulario = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        lbNit = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtNombreCompleto = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        txtEdad = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        txtGenero = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        txtFechaNacimiento = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        lbDireccion25 = new javax.swing.JLabel();
        txtResponsable = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        lbDireccion40 = new javax.swing.JLabel();
        txtTelefonoResponsable = new javax.swing.JTextField();
        lbDireccion27 = new javax.swing.JLabel();
        cmbParentesco = new javax.swing.JComboBox();
        jLabel31 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        btnGuardarConsulta = new javax.swing.JButton();
        btnReimprimirConsulta = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        chkMedico = new javax.swing.JCheckBox();
        chkCirujano = new javax.swing.JCheckBox();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        tabPrincipal = new javax.swing.JTabbedPane();
        jPanel30 = new javax.swing.JPanel();
        lbEmail14 = new javax.swing.JLabel();
        jScrollPane15 = new javax.swing.JScrollPane();
        txtMotivo = new javax.swing.JTextArea();
        lbEmail29 = new javax.swing.JLabel();
        jScrollPane23 = new javax.swing.JScrollPane();
        txtEnfermedadActual = new javax.swing.JTextArea();
        lbEmail35 = new javax.swing.JLabel();
        jScrollPane40 = new javax.swing.JScrollPane();
        txtRevisionxSistemas = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        lbRazon14 = new javax.swing.JLabel();
        jScrollPane64 = new javax.swing.JScrollPane();
        txtFamiliar = new javax.swing.JTextArea();
        lbRazon23 = new javax.swing.JLabel();
        lbRazon24 = new javax.swing.JLabel();
        lbRazon25 = new javax.swing.JLabel();
        lbRazon26 = new javax.swing.JLabel();
        lbRazon27 = new javax.swing.JLabel();
        lbRazon29 = new javax.swing.JLabel();
        btnHipertension = new javax.swing.JButton();
        btnDiabetes = new javax.swing.JButton();
        btnMetabolica = new javax.swing.JButton();
        lbRazon30 = new javax.swing.JLabel();
        lbRazon31 = new javax.swing.JLabel();
        jScrollPane29 = new javax.swing.JScrollPane();
        txtPatologicos = new javax.swing.JTextArea();
        jScrollPane30 = new javax.swing.JScrollPane();
        txtPsiquiatricos = new javax.swing.JTextArea();
        jScrollPane31 = new javax.swing.JScrollPane();
        txtQuirurgicos = new javax.swing.JTextArea();
        jScrollPane32 = new javax.swing.JScrollPane();
        txtToxicos = new javax.swing.JTextArea();
        jScrollPane33 = new javax.swing.JScrollPane();
        txtAlergias = new javax.swing.JTextArea();
        jScrollPane34 = new javax.swing.JScrollPane();
        txtIngestaAlcohol = new javax.swing.JTextArea();
        jScrollPane35 = new javax.swing.JScrollPane();
        txtFumador = new javax.swing.JTextArea();
        jScrollPane36 = new javax.swing.JScrollPane();
        txtMedicamentos = new javax.swing.JTextArea();
        jScrollPane65 = new javax.swing.JScrollPane();
        txtNutricionales = new javax.swing.JTextArea();
        lbRazon34 = new javax.swing.JLabel();
        lbRazon35 = new javax.swing.JLabel();
        btnBuscPlantilla14 = new javax.swing.JButton();
        btnBuscPlantilla15 = new javax.swing.JButton();
        btnBuscPlantilla16 = new javax.swing.JButton();
        btnBuscPlantilla17 = new javax.swing.JButton();
        btnBuscPlantilla18 = new javax.swing.JButton();
        btnBuscPlantilla19 = new javax.swing.JButton();
        btnBuscPlantilla20 = new javax.swing.JButton();
        btnBuscPlantilla21 = new javax.swing.JButton();
        btnBuscPlantilla22 = new javax.swing.JButton();
        btnBuscPlantilla23 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        jCheckBox6 = new javax.swing.JCheckBox();
        jCheckBox7 = new javax.swing.JCheckBox();
        jCheckBox8 = new javax.swing.JCheckBox();
        jCheckBox9 = new javax.swing.JCheckBox();
        jCheckBox10 = new javax.swing.JCheckBox();
        jCheckBox11 = new javax.swing.JCheckBox();
        jCheckBox12 = new javax.swing.JCheckBox();
        jCheckBox13 = new javax.swing.JCheckBox();
        jCheckBox14 = new javax.swing.JCheckBox();
        jCheckBox15 = new javax.swing.JCheckBox();
        jCheckBox16 = new javax.swing.JCheckBox();
        jCheckBox17 = new javax.swing.JCheckBox();
        jCheckBox18 = new javax.swing.JCheckBox();
        jCheckBox19 = new javax.swing.JCheckBox();
        jCheckBox20 = new javax.swing.JCheckBox();
        lbRazon37 = new javax.swing.JLabel();
        lbRazon39 = new javax.swing.JLabel();
        lbRazon40 = new javax.swing.JLabel();
        btnBuscPlantilla29 = new javax.swing.JButton();
        jCheckBox21 = new javax.swing.JCheckBox();
        jCheckBox22 = new javax.swing.JCheckBox();
        jScrollPane47 = new javax.swing.JScrollPane();
        txtTraumaticos = new javax.swing.JTextArea();
        btnBuscPlantilla36 = new javax.swing.JButton();
        jCheckBox23 = new javax.swing.JCheckBox();
        jCheckBox24 = new javax.swing.JCheckBox();
        jScrollPane48 = new javax.swing.JScrollPane();
        txtHistorial = new javax.swing.JTextArea();
        jPanel16 = new javax.swing.JPanel();
        lbRazon20 = new javax.swing.JLabel();
        lbRazon12 = new javax.swing.JLabel();
        lbRazon15 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtResultadoCitologia = new javax.swing.JTextArea();
        lbRazon28 = new javax.swing.JLabel();
        jScrollPane38 = new javax.swing.JScrollPane();
        txtPlanificacion = new javax.swing.JTextArea();
        lbRazon33 = new javax.swing.JLabel();
        txtMenarca = new javax.swing.JTextField();
        txtUltimaMenstruacion = new javax.swing.JTextField();
        txtUltimaCitologia = new javax.swing.JTextField();
        pnlCirujano = new javax.swing.JPanel();
        lbPartos = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtPartos1 = new javax.swing.JTextArea();
        lbRazon13 = new javax.swing.JLabel();
        txtCiclo = new javax.swing.JTextField();
        pnlMedicoEmb = new javax.swing.JPanel();
        lbRazon16 = new javax.swing.JLabel();
        txtEmbarazos = new javax.swing.JTextField();
        lbRazon19 = new javax.swing.JLabel();
        txtCesareas = new javax.swing.JTextField();
        lbRazon32 = new javax.swing.JLabel();
        txtEctopicos = new javax.swing.JTextField();
        pnlMedicoEmb1 = new javax.swing.JPanel();
        lbRazon18 = new javax.swing.JLabel();
        txtAbortos = new javax.swing.JTextField();
        lbRazon17 = new javax.swing.JLabel();
        txtPartos = new javax.swing.JTextField();
        lbRazon21 = new javax.swing.JLabel();
        txtHijosVivos = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jScrollPane25 = new javax.swing.JScrollPane();
        txtRevision = new javax.swing.JTextArea();
        lbRazon38 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        pnlMedico1 = new javax.swing.JPanel();
        lbDireccion16 = new javax.swing.JLabel();
        jScrollPane16 = new javax.swing.JScrollPane();
        txtCabezaCuello = new javax.swing.JTextArea();
        btnBuscPlantilla1 = new javax.swing.JButton();
        lbDireccion47 = new javax.swing.JLabel();
        lbDireccion48 = new javax.swing.JLabel();
        lbDireccion49 = new javax.swing.JLabel();
        lbDireccion50 = new javax.swing.JLabel();
        lbDireccion51 = new javax.swing.JLabel();
        lbDireccion23 = new javax.swing.JLabel();
        lbDireccion52 = new javax.swing.JLabel();
        lbDireccion55 = new javax.swing.JLabel();
        lbDireccion56 = new javax.swing.JLabel();
        lbDireccion57 = new javax.swing.JLabel();
        lbDireccion58 = new javax.swing.JLabel();
        lbDireccion59 = new javax.swing.JLabel();
        btnBuscPlantilla3 = new javax.swing.JButton();
        btnBuscPlantilla4 = new javax.swing.JButton();
        btnBuscPlantilla5 = new javax.swing.JButton();
        btnBuscPlantilla6 = new javax.swing.JButton();
        btnBuscPlantilla7 = new javax.swing.JButton();
        btnBuscPlantilla8 = new javax.swing.JButton();
        btnBuscPlantilla9 = new javax.swing.JButton();
        btnBuscPlantilla10 = new javax.swing.JButton();
        btnBuscPlantilla11 = new javax.swing.JButton();
        btnBuscPlantilla12 = new javax.swing.JButton();
        btnBuscPlantilla13 = new javax.swing.JButton();
        btnBuscPlantilla2 = new javax.swing.JButton();
        jScrollPane61 = new javax.swing.JScrollPane();
        txtNeurologico = new javax.swing.JTextArea();
        jScrollPane62 = new javax.swing.JScrollPane();
        txtPiel = new javax.swing.JTextArea();
        jScrollPane60 = new javax.swing.JScrollPane();
        txtMusculoEsqueletico = new javax.swing.JTextArea();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtUrinario = new javax.swing.JTextArea();
        jScrollPane58 = new javax.swing.JScrollPane();
        txtDigestivo = new javax.swing.JTextArea();
        jScrollPane57 = new javax.swing.JScrollPane();
        txtAbdomen = new javax.swing.JTextArea();
        jScrollPane56 = new javax.swing.JScrollPane();
        txtCardioPulmonar = new javax.swing.JTextArea();
        jScrollPane28 = new javax.swing.JScrollPane();
        txtTacto = new javax.swing.JTextArea();
        jScrollPane27 = new javax.swing.JScrollPane();
        txtGusto = new javax.swing.JTextArea();
        jScrollPane26 = new javax.swing.JScrollPane();
        txtNariz = new javax.swing.JTextArea();
        jScrollPane17 = new javax.swing.JScrollPane();
        txtOido = new javax.swing.JTextArea();
        jScrollPane18 = new javax.swing.JScrollPane();
        txtVista = new javax.swing.JTextArea();
        lbDireccion63 = new javax.swing.JLabel();
        btnBuscPlantilla37 = new javax.swing.JButton();
        jScrollPane63 = new javax.swing.JScrollPane();
        txtExtremidades1 = new javax.swing.JTextArea();
        lbDireccion64 = new javax.swing.JLabel();
        btnBuscPlantilla38 = new javax.swing.JButton();
        jScrollPane66 = new javax.swing.JScrollPane();
        txtGluteos = new javax.swing.JTextArea();
        lbDireccion65 = new javax.swing.JLabel();
        btnBuscPlantilla39 = new javax.swing.JButton();
        jScrollPane67 = new javax.swing.JScrollPane();
        txtMamas = new javax.swing.JTextArea();
        pnlCirujano1 = new javax.swing.JPanel();
        lbDireccion53 = new javax.swing.JLabel();
        lbDireccion54 = new javax.swing.JLabel();
        lbDireccion60 = new javax.swing.JLabel();
        lbDireccion61 = new javax.swing.JLabel();
        lbDireccion62 = new javax.swing.JLabel();
        lbDireccion24 = new javax.swing.JLabel();
        btnBuscPlantilla30 = new javax.swing.JButton();
        btnBuscPlantilla31 = new javax.swing.JButton();
        btnBuscPlantilla32 = new javax.swing.JButton();
        btnBuscPlantilla33 = new javax.swing.JButton();
        btnBuscPlantilla34 = new javax.swing.JButton();
        btnBuscPlantilla35 = new javax.swing.JButton();
        jScrollPane69 = new javax.swing.JScrollPane();
        txtPielYFaneras = new javax.swing.JTextArea();
        jScrollPane41 = new javax.swing.JScrollPane();
        txtNeurologico1 = new javax.swing.JTextArea();
        jScrollPane42 = new javax.swing.JScrollPane();
        txtExtremidades = new javax.swing.JTextArea();
        jScrollPane43 = new javax.swing.JScrollPane();
        txtAbdomen1 = new javax.swing.JTextArea();
        jScrollPane44 = new javax.swing.JScrollPane();
        txtTorax = new javax.swing.JTextArea();
        jScrollPane45 = new javax.swing.JScrollPane();
        txtCC = new javax.swing.JTextArea();
        jPanel14 = new javax.swing.JPanel();
        lbDireccion29 = new javax.swing.JLabel();
        txtFrecCardiaca = new javax.swing.JTextField();
        lbDireccion33 = new javax.swing.JLabel();
        lbDireccion32 = new javax.swing.JLabel();
        lbDireccion30 = new javax.swing.JLabel();
        txtPa = new javax.swing.JTextField();
        txtPa2 = new javax.swing.JTextField();
        lbDireccion46 = new javax.swing.JLabel();
        txtPeso = new javax.swing.JTextField();
        txtImc = new javax.swing.JTextField();
        lbDireccion19 = new javax.swing.JLabel();
        txtFrecRespiratoria = new javax.swing.JTextField();
        lbDireccion21 = new javax.swing.JLabel();
        txtT = new javax.swing.JTextField();
        lbDireccion28 = new javax.swing.JLabel();
        txtEstatura = new javax.swing.JTextField();
        lbDireccion31 = new javax.swing.JLabel();
        txtTiene = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        lbEmail34 = new javax.swing.JLabel();
        cmbTipoDiagnostico = new javax.swing.JComboBox();
        lbEmail18 = new javax.swing.JLabel();
        btnBuscCie1 = new javax.swing.JButton();
        txtCie1 = new javax.swing.JTextField();
        txtDiagnostico1 = new javax.swing.JTextField();
        lbEmail19 = new javax.swing.JLabel();
        txtCie2 = new javax.swing.JTextField();
        btnBuscCie2 = new javax.swing.JButton();
        txtDiagnostico2 = new javax.swing.JTextField();
        txtDiagnostico3 = new javax.swing.JTextField();
        txtCie3 = new javax.swing.JTextField();
        btnBuscCie3 = new javax.swing.JButton();
        lbEmail26 = new javax.swing.JLabel();
        lbEmail27 = new javax.swing.JLabel();
        btnBuscCie4 = new javax.swing.JButton();
        txtCie4 = new javax.swing.JTextField();
        txtDiagnostico4 = new javax.swing.JTextField();
        jScrollPane37 = new javax.swing.JScrollPane();
        txtDiagnosticoEspecializado = new javax.swing.JTextArea();
        btnBuscPlantilla24 = new javax.swing.JButton();
        lbEmail30 = new javax.swing.JLabel();
        lbEmail33 = new javax.swing.JLabel();
        lbEmail20 = new javax.swing.JLabel();
        jScrollPane19 = new javax.swing.JScrollPane();
        txtTratamiento = new javax.swing.JTextArea();
        btnBuscPlantilla25 = new javax.swing.JButton();
        jScrollPane20 = new javax.swing.JScrollPane();
        txtRiesgosRecomendaciones = new javax.swing.JTextArea();
        lbEmail21 = new javax.swing.JLabel();
        lbEmail28 = new javax.swing.JLabel();
        lbEmail24 = new javax.swing.JLabel();
        lbEmail25 = new javax.swing.JLabel();
        btnBuscCup2 = new javax.swing.JButton();
        txtCup1 = new javax.swing.JTextField();
        txtProcedimiento1 = new javax.swing.JTextField();
        txtProcedimiento = new javax.swing.JTextField();
        txtCup = new javax.swing.JTextField();
        btnBuscCup1 = new javax.swing.JButton();
        lbEmail31 = new javax.swing.JLabel();
        lbEmail32 = new javax.swing.JLabel();
        btnBuscPlantilla26 = new javax.swing.JButton();
        jScrollPane24 = new javax.swing.JScrollPane();
        txtObservacionProcedimientos = new javax.swing.JTextArea();
        lbNit6 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        txtNombreAnexoConsulta = new javax.swing.JTextField();
        lbNit9 = new javax.swing.JLabel();
        txtDescripcionExamenConsulta = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblExamenes = new javax.swing.JTable();
        lbNit11 = new javax.swing.JLabel();
        btnGuardarArchivos = new javax.swing.JButton();
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
        jScrollPane9 = new javax.swing.JScrollPane();
        tblExamenesEvolucion = new javax.swing.JTable();
        lbNit5 = new javax.swing.JLabel();
        lbDireccion34 = new javax.swing.JLabel();
        txtPeso1 = new javax.swing.JTextField();
        lbDireccion35 = new javax.swing.JLabel();
        txtPa1 = new javax.swing.JTextField();
        lbDireccion36 = new javax.swing.JLabel();
        txtT1 = new javax.swing.JTextField();
        lbDireccion37 = new javax.swing.JLabel();
        txtFr2 = new javax.swing.JTextField();
        txtDescripcionExamenEvolucion = new javax.swing.JTextField();
        btnAgregarExamenEvolucion = new javax.swing.JButton();
        btnSubirImagen2 = new javax.swing.JButton();
        btnGuardarSeguimiento = new javax.swing.JButton();
        lbNit7 = new javax.swing.JLabel();
        txtNombreAnexoEvolucion = new javax.swing.JTextField();
        lbNit8 = new javax.swing.JLabel();
        btnBuscPlantilla27 = new javax.swing.JButton();
        btnBuscPlantilla28 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnAyudaDiagnostica = new javax.swing.JButton();
        btnFormulaMedica = new javax.swing.JButton();
        btnIncapacidad = new javax.swing.JButton();
        btnContraremision = new javax.swing.JButton();
        btnRemision = new javax.swing.JButton();
        pnlMenu = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        lista = new javax.swing.JList();
        lbLista = new javax.swing.JLabel();
        lbFechaEvolucion = new javax.swing.JLabel();
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

        popBorrar1.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        popBorrar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar-cancelar-icono-4935-16.png"))); // NOI18N
        popBorrar1.setText("Borrar");
        popBorrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popBorrar1ActionPerformed(evt);
            }
        });
        jPopupMenu2.add(popBorrar1);

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(255, 255, 255));
        setTitle("Factura");

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));
        pnlFormulario.setToolTipText("");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Información", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 14))); // NOI18N

        jLabel27.setBackground(new java.awt.Color(255, 255, 255));
        jLabel27.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel27.setText("Nombre paciente:");
        jLabel27.setOpaque(true);

        lbNit.setBackground(new java.awt.Color(255, 255, 255));
        lbNit.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNit.setText("Identificación paciente:");
        lbNit.setOpaque(true);
        lbNit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lbNitKeyReleased(evt);
            }
        });

        txtId.setEditable(false);
        txtId.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtId.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdActionPerformed(evt);
            }
        });
        txtId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdKeyReleased(evt);
            }
        });

        txtNombreCompleto.setEditable(false);
        txtNombreCompleto.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNombreCompleto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreCompletoActionPerformed(evt);
            }
        });

        jLabel29.setBackground(new java.awt.Color(255, 255, 255));
        jLabel29.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel29.setText("Edad:");
        jLabel29.setOpaque(true);

        txtEdad.setEditable(false);
        txtEdad.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtEdad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEdadActionPerformed(evt);
            }
        });

        jLabel30.setBackground(new java.awt.Color(255, 255, 255));
        jLabel30.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel30.setText("Genero:");
        jLabel30.setOpaque(true);

        txtGenero.setEditable(false);
        txtGenero.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtGenero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGeneroActionPerformed(evt);
            }
        });

        jLabel28.setBackground(new java.awt.Color(255, 255, 255));
        jLabel28.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel28.setText("Fecha nacimiento:");
        jLabel28.setOpaque(true);

        txtFechaNacimiento.setEditable(false);
        txtFechaNacimiento.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtFechaNacimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaNacimientoActionPerformed(evt);
            }
        });

        lbDireccion25.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbDireccion25.setText("Identificación acompañante:");

        txtResponsable.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtResponsable.setName("FC"); // NOI18N
        txtResponsable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtResponsableActionPerformed(evt);
            }
        });
        txtResponsable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtResponsableKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtResponsableKeyTyped(evt);
            }
        });

        txtNombre.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNombre.setName("FC"); // NOI18N
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        lbDireccion40.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbDireccion40.setText("Télefono:");

        txtTelefonoResponsable.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtTelefonoResponsable.setName("FC"); // NOI18N
        txtTelefonoResponsable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonoResponsableActionPerformed(evt);
            }
        });
        txtTelefonoResponsable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTelefonoResponsableKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoResponsableKeyTyped(evt);
            }
        });

        lbDireccion27.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbDireccion27.setText("Parentesco:");

        cmbParentesco.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cmbParentesco.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Papá", "Mamá", "Cónyuge", "Yerno", "Nuera", "Madrastra", "Padrastro", "Tio(a)", "Hermano(a)", "Abuelo(a)", "Primo(a)", "Sobrino(a)", "Amigo(a)", "Hijo(a)", "Novio(a)", "Cuñado(a)", "Suegro(a)", "Otro, Describa cual." }));
        cmbParentesco.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbParentescoKeyReleased(evt);
            }
        });

        jLabel31.setBackground(new java.awt.Color(255, 255, 255));
        jLabel31.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel31.setText("Nombre acompañante:");
        jLabel31.setOpaque(true);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbDireccion25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtNombre))
                        .addGap(48, 48, 48)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbDireccion27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbDireccion40, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTelefonoResponsable)
                            .addComponent(cmbParentesco, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jSeparator3)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbNit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(jLabel28)
                                .addGap(18, 18, 18)
                                .addComponent(txtFechaNacimiento))
                            .addComponent(txtNombreCompleto))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtEdad, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                            .addComponent(txtGenero))))
                .addGap(15, 15, 15))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNit, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtGenero, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                        .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(3, 3, 3)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNombreCompleto, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(txtEdad)
                    .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbDireccion25, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbDireccion27, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbParentesco, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                                    .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(txtTelefonoResponsable, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))))
                    .addComponent(lbDireccion40, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));

        btnGuardarConsulta.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardarConsulta.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnGuardarConsulta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardarConsulta.setText("GUARDAR");
        btnGuardarConsulta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarConsulta.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardarConsulta.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnGuardarConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarConsultaActionPerformed(evt);
            }
        });

        btnReimprimirConsulta.setBackground(new java.awt.Color(247, 220, 111));
        btnReimprimirConsulta.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnReimprimirConsulta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/imprimir.png"))); // NOI18N
        btnReimprimirConsulta.setText("IMPRIMIR");
        btnReimprimirConsulta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReimprimirConsulta.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnReimprimirConsulta.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnReimprimirConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReimprimirConsultaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnReimprimirConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardarConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReimprimirConsulta)
                    .addComponent(btnGuardarConsulta))
                .addContainerGap())
        );

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));

        chkMedico.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup12.add(chkMedico);
        chkMedico.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        chkMedico.setSelected(true);
        chkMedico.setText("MEDICO");
        chkMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkMedicoActionPerformed(evt);
            }
        });

        chkCirujano.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup12.add(chkCirujano);
        chkCirujano.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        chkCirujano.setText("CIRUJANO");
        chkCirujano.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCirujanoActionPerformed(evt);
            }
        });

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/anterior_2.png"))); // NOI18N
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Volver");
        jLabel11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(chkMedico)
                .addGap(18, 18, 18)
                .addComponent(chkCirujano)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkCirujano)
                    .addComponent(chkMedico))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel11))
        );

        jPanel30.setBackground(new java.awt.Color(255, 255, 255));

        lbEmail14.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbEmail14.setText("Motivo de la consulta:");

        txtMotivo.setColumns(20);
        txtMotivo.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtMotivo.setLineWrap(true);
        txtMotivo.setRows(2);
        txtMotivo.setName("Motivo"); // NOI18N
        txtMotivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMotivoKeyReleased(evt);
            }
        });
        jScrollPane15.setViewportView(txtMotivo);

        lbEmail29.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbEmail29.setText("Enfermedad actual:");

        txtEnfermedadActual.setColumns(20);
        txtEnfermedadActual.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtEnfermedadActual.setLineWrap(true);
        txtEnfermedadActual.setRows(2);
        txtEnfermedadActual.setName("Motivo"); // NOI18N
        txtEnfermedadActual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEnfermedadActualKeyReleased(evt);
            }
        });
        jScrollPane23.setViewportView(txtEnfermedadActual);

        lbEmail35.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbEmail35.setText("Revisión x sistemas:");

        txtRevisionxSistemas.setColumns(20);
        txtRevisionxSistemas.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtRevisionxSistemas.setLineWrap(true);
        txtRevisionxSistemas.setRows(2);
        txtRevisionxSistemas.setName("Motivo"); // NOI18N
        txtRevisionxSistemas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRevisionxSistemasKeyReleased(evt);
            }
        });
        jScrollPane40.setViewportView(txtRevisionxSistemas);

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbEmail14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbEmail35, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbEmail29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane23)
                    .addComponent(jScrollPane40, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 818, Short.MAX_VALUE)
                    .addComponent(jScrollPane15))
                .addContainerGap())
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
                    .addComponent(lbEmail14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane23, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                    .addComponent(lbEmail29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbEmail35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane40, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
                .addContainerGap())
        );

        tabPrincipal.addTab("Motivo de Consulta", jPanel30);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        lbRazon14.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        lbRazon14.setText("Familiares:");

        txtFamiliar.setColumns(20);
        txtFamiliar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtFamiliar.setLineWrap(true);
        txtFamiliar.setRows(2);
        txtFamiliar.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtFamiliar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFamiliarKeyReleased(evt);
            }
        });
        jScrollPane64.setViewportView(txtFamiliar);

        lbRazon23.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon23.setText("Fumador:");

        lbRazon24.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon24.setText("Alcohol:");

        lbRazon25.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon25.setText("Alergias:");

        lbRazon26.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon26.setText("Toxicos:");

        lbRazon27.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon27.setText("Medicamentos:");

        lbRazon29.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        lbRazon29.setText("Patologicos:");

        btnHipertension.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        btnHipertension.setText("Hipertensión");
        btnHipertension.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHipertensionActionPerformed(evt);
            }
        });

        btnDiabetes.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        btnDiabetes.setText("Diabetes");
        btnDiabetes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDiabetesActionPerformed(evt);
            }
        });

        btnMetabolica.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        btnMetabolica.setText("Metabólica");
        btnMetabolica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMetabolicaActionPerformed(evt);
            }
        });

        lbRazon30.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon30.setText("Psiquiatricos:");

        lbRazon31.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon31.setText("Quirurgicos:");

        txtPatologicos.setColumns(20);
        txtPatologicos.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtPatologicos.setLineWrap(true);
        txtPatologicos.setRows(2);
        txtPatologicos.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtPatologicos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPatologicosKeyReleased(evt);
            }
        });
        jScrollPane29.setViewportView(txtPatologicos);

        txtPsiquiatricos.setColumns(20);
        txtPsiquiatricos.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtPsiquiatricos.setLineWrap(true);
        txtPsiquiatricos.setRows(2);
        txtPsiquiatricos.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtPsiquiatricos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPsiquiatricosKeyReleased(evt);
            }
        });
        jScrollPane30.setViewportView(txtPsiquiatricos);

        txtQuirurgicos.setColumns(20);
        txtQuirurgicos.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtQuirurgicos.setLineWrap(true);
        txtQuirurgicos.setRows(2);
        txtQuirurgicos.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtQuirurgicos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtQuirurgicosKeyReleased(evt);
            }
        });
        jScrollPane31.setViewportView(txtQuirurgicos);

        txtToxicos.setColumns(20);
        txtToxicos.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtToxicos.setLineWrap(true);
        txtToxicos.setRows(2);
        txtToxicos.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtToxicos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtToxicosKeyReleased(evt);
            }
        });
        jScrollPane32.setViewportView(txtToxicos);

        txtAlergias.setColumns(20);
        txtAlergias.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtAlergias.setLineWrap(true);
        txtAlergias.setRows(2);
        txtAlergias.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtAlergias.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAlergiasKeyReleased(evt);
            }
        });
        jScrollPane33.setViewportView(txtAlergias);

        txtIngestaAlcohol.setColumns(20);
        txtIngestaAlcohol.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtIngestaAlcohol.setLineWrap(true);
        txtIngestaAlcohol.setRows(2);
        txtIngestaAlcohol.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtIngestaAlcohol.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIngestaAlcoholKeyReleased(evt);
            }
        });
        jScrollPane34.setViewportView(txtIngestaAlcohol);

        txtFumador.setColumns(20);
        txtFumador.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtFumador.setLineWrap(true);
        txtFumador.setRows(2);
        txtFumador.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtFumador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFumadorKeyReleased(evt);
            }
        });
        jScrollPane35.setViewportView(txtFumador);

        txtMedicamentos.setColumns(20);
        txtMedicamentos.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtMedicamentos.setLineWrap(true);
        txtMedicamentos.setRows(2);
        txtMedicamentos.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtMedicamentos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMedicamentosKeyReleased(evt);
            }
        });
        jScrollPane36.setViewportView(txtMedicamentos);

        txtNutricionales.setColumns(20);
        txtNutricionales.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNutricionales.setLineWrap(true);
        txtNutricionales.setRows(2);
        txtNutricionales.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtNutricionales.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNutricionalesKeyReleased(evt);
            }
        });
        jScrollPane65.setViewportView(txtNutricionales);

        lbRazon34.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon34.setText("Nutricionales:");

        lbRazon35.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon35.setText("Habitos");

        btnBuscPlantilla14.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla14.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscPlantilla14.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/listadoPequeño.png"))); // NOI18N
        btnBuscPlantilla14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        btnBuscPlantilla14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscPlantilla14.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscPlantilla14.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnBuscPlantilla14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscPlantilla14ActionPerformed(evt);
            }
        });

        btnBuscPlantilla15.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla15.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscPlantilla15.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/listadoPequeño.png"))); // NOI18N
        btnBuscPlantilla15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        btnBuscPlantilla15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscPlantilla15.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscPlantilla15.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnBuscPlantilla15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscPlantilla15ActionPerformed(evt);
            }
        });

        btnBuscPlantilla16.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla16.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscPlantilla16.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/listadoPequeño.png"))); // NOI18N
        btnBuscPlantilla16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        btnBuscPlantilla16.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscPlantilla16.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscPlantilla16.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnBuscPlantilla16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscPlantilla16ActionPerformed(evt);
            }
        });

        btnBuscPlantilla17.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla17.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscPlantilla17.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/listadoPequeño.png"))); // NOI18N
        btnBuscPlantilla17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        btnBuscPlantilla17.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscPlantilla17.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscPlantilla17.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnBuscPlantilla17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscPlantilla17ActionPerformed(evt);
            }
        });

        btnBuscPlantilla18.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla18.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscPlantilla18.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/listadoPequeño.png"))); // NOI18N
        btnBuscPlantilla18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        btnBuscPlantilla18.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscPlantilla18.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscPlantilla18.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnBuscPlantilla18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscPlantilla18ActionPerformed(evt);
            }
        });

        btnBuscPlantilla19.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla19.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscPlantilla19.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/listadoPequeño.png"))); // NOI18N
        btnBuscPlantilla19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        btnBuscPlantilla19.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscPlantilla19.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscPlantilla19.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnBuscPlantilla19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscPlantilla19ActionPerformed(evt);
            }
        });

        btnBuscPlantilla20.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla20.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscPlantilla20.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/listadoPequeño.png"))); // NOI18N
        btnBuscPlantilla20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        btnBuscPlantilla20.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscPlantilla20.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscPlantilla20.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnBuscPlantilla20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscPlantilla20ActionPerformed(evt);
            }
        });

        btnBuscPlantilla21.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla21.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscPlantilla21.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/listadoPequeño.png"))); // NOI18N
        btnBuscPlantilla21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        btnBuscPlantilla21.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscPlantilla21.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscPlantilla21.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnBuscPlantilla21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscPlantilla21ActionPerformed(evt);
            }
        });

        btnBuscPlantilla22.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla22.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscPlantilla22.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/listadoPequeño.png"))); // NOI18N
        btnBuscPlantilla22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        btnBuscPlantilla22.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscPlantilla22.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscPlantilla22.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnBuscPlantilla22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscPlantilla22ActionPerformed(evt);
            }
        });

        btnBuscPlantilla23.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla23.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscPlantilla23.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/listadoPequeño.png"))); // NOI18N
        btnBuscPlantilla23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        btnBuscPlantilla23.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscPlantilla23.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscPlantilla23.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnBuscPlantilla23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscPlantilla23ActionPerformed(evt);
            }
        });

        buttonGroup2.add(jCheckBox1);
        jCheckBox1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jCheckBox1.setSelected(true);
        jCheckBox1.setText("SI");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        buttonGroup2.add(jCheckBox2);
        jCheckBox2.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jCheckBox2.setText("NO");
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });

        buttonGroup3.add(jCheckBox3);
        jCheckBox3.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jCheckBox3.setText("NO");
        jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox3ActionPerformed(evt);
            }
        });

        buttonGroup3.add(jCheckBox4);
        jCheckBox4.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jCheckBox4.setSelected(true);
        jCheckBox4.setText("SI");
        jCheckBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox4ActionPerformed(evt);
            }
        });

        buttonGroup4.add(jCheckBox5);
        jCheckBox5.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jCheckBox5.setText("NO");
        jCheckBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox5ActionPerformed(evt);
            }
        });

        buttonGroup4.add(jCheckBox6);
        jCheckBox6.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jCheckBox6.setText("SI");
        jCheckBox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox6ActionPerformed(evt);
            }
        });

        buttonGroup5.add(jCheckBox7);
        jCheckBox7.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jCheckBox7.setText("NO");
        jCheckBox7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox7ActionPerformed(evt);
            }
        });

        buttonGroup5.add(jCheckBox8);
        jCheckBox8.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jCheckBox8.setSelected(true);
        jCheckBox8.setText("SI");
        jCheckBox8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox8ActionPerformed(evt);
            }
        });

        buttonGroup6.add(jCheckBox9);
        jCheckBox9.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jCheckBox9.setText("NO");
        jCheckBox9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox9ActionPerformed(evt);
            }
        });

        buttonGroup6.add(jCheckBox10);
        jCheckBox10.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jCheckBox10.setSelected(true);
        jCheckBox10.setText("SI");
        jCheckBox10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox10ActionPerformed(evt);
            }
        });

        buttonGroup7.add(jCheckBox11);
        jCheckBox11.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jCheckBox11.setText("NO");
        jCheckBox11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox11ActionPerformed(evt);
            }
        });

        buttonGroup7.add(jCheckBox12);
        jCheckBox12.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jCheckBox12.setSelected(true);
        jCheckBox12.setText("SI");
        jCheckBox12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox12ActionPerformed(evt);
            }
        });

        buttonGroup8.add(jCheckBox13);
        jCheckBox13.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jCheckBox13.setText("NO");
        jCheckBox13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox13ActionPerformed(evt);
            }
        });

        buttonGroup8.add(jCheckBox14);
        jCheckBox14.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jCheckBox14.setSelected(true);
        jCheckBox14.setText("SI");
        jCheckBox14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox14ActionPerformed(evt);
            }
        });

        buttonGroup9.add(jCheckBox15);
        jCheckBox15.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jCheckBox15.setText("NO");
        jCheckBox15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox15ActionPerformed(evt);
            }
        });

        buttonGroup9.add(jCheckBox16);
        jCheckBox16.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jCheckBox16.setSelected(true);
        jCheckBox16.setText("SI");
        jCheckBox16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox16ActionPerformed(evt);
            }
        });

        buttonGroup10.add(jCheckBox17);
        jCheckBox17.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jCheckBox17.setText("NO");
        jCheckBox17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox17ActionPerformed(evt);
            }
        });

        buttonGroup10.add(jCheckBox18);
        jCheckBox18.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jCheckBox18.setSelected(true);
        jCheckBox18.setText("SI");
        jCheckBox18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox18ActionPerformed(evt);
            }
        });

        buttonGroup11.add(jCheckBox19);
        jCheckBox19.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jCheckBox19.setText("NO");
        jCheckBox19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox19ActionPerformed(evt);
            }
        });

        buttonGroup11.add(jCheckBox20);
        jCheckBox20.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jCheckBox20.setSelected(true);
        jCheckBox20.setText("SI");
        jCheckBox20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox20ActionPerformed(evt);
            }
        });

        lbRazon37.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        lbRazon37.setText("Personales:");

        lbRazon39.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        lbRazon39.setText("Historial personal y social");

        lbRazon40.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon40.setText("Traumaticos:");

        btnBuscPlantilla29.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla29.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscPlantilla29.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/listadoPequeño.png"))); // NOI18N
        btnBuscPlantilla29.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        btnBuscPlantilla29.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscPlantilla29.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscPlantilla29.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnBuscPlantilla29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscPlantilla29ActionPerformed(evt);
            }
        });

        buttonGroup15.add(jCheckBox21);
        jCheckBox21.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jCheckBox21.setSelected(true);
        jCheckBox21.setText("SI");
        jCheckBox21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox21ActionPerformed(evt);
            }
        });

        buttonGroup15.add(jCheckBox22);
        jCheckBox22.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jCheckBox22.setText("NO");
        jCheckBox22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox22ActionPerformed(evt);
            }
        });

        txtTraumaticos.setColumns(20);
        txtTraumaticos.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtTraumaticos.setLineWrap(true);
        txtTraumaticos.setRows(2);
        txtTraumaticos.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtTraumaticos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTraumaticosKeyReleased(evt);
            }
        });
        jScrollPane47.setViewportView(txtTraumaticos);

        btnBuscPlantilla36.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla36.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscPlantilla36.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/listadoPequeño.png"))); // NOI18N
        btnBuscPlantilla36.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        btnBuscPlantilla36.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscPlantilla36.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscPlantilla36.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnBuscPlantilla36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscPlantilla36ActionPerformed(evt);
            }
        });

        buttonGroup14.add(jCheckBox23);
        jCheckBox23.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jCheckBox23.setSelected(true);
        jCheckBox23.setText("SI");
        jCheckBox23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox23ActionPerformed(evt);
            }
        });

        buttonGroup14.add(jCheckBox24);
        jCheckBox24.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jCheckBox24.setText("NO");
        jCheckBox24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox24ActionPerformed(evt);
            }
        });

        txtHistorial.setColumns(20);
        txtHistorial.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtHistorial.setLineWrap(true);
        txtHistorial.setRows(2);
        txtHistorial.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtHistorial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtHistorialKeyReleased(evt);
            }
        });
        jScrollPane48.setViewportView(txtHistorial);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnBuscPlantilla36, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jCheckBox24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jCheckBox23, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane48))
                    .addComponent(lbRazon37, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnBuscPlantilla14, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jCheckBox2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jCheckBox1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane64))
                    .addComponent(lbRazon14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnBuscPlantilla23, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jCheckBox13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jCheckBox14, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane29))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(lbRazon30, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscPlantilla20, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbRazon31, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(lbRazon35, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbRazon34, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnBuscPlantilla22, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                                    .addComponent(btnBuscPlantilla21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jCheckBox15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jCheckBox17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jCheckBox19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jCheckBox16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jCheckBox18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jCheckBox20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane65, javax.swing.GroupLayout.DEFAULT_SIZE, 771, Short.MAX_VALUE)
                            .addComponent(jScrollPane31)
                            .addComponent(jScrollPane30)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addComponent(lbRazon29, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnHipertension, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDiabetes, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnMetabolica, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbRazon39, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbRazon27, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                                    .addComponent(lbRazon26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(4, 4, 4)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnBuscPlantilla18, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                                    .addComponent(btnBuscPlantilla19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lbRazon25, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lbRazon40, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(lbRazon24, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lbRazon23, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnBuscPlantilla16, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                                    .addComponent(btnBuscPlantilla15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnBuscPlantilla29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnBuscPlantilla17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jCheckBox11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jCheckBox12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jCheckBox22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jCheckBox21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jCheckBox7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jCheckBox8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jCheckBox9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jCheckBox10, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane32, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 766, Short.MAX_VALUE)
                                    .addComponent(jScrollPane33, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane36)
                                    .addComponent(jScrollPane47, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jCheckBox6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jCheckBox3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jCheckBox4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jCheckBox5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane34, javax.swing.GroupLayout.DEFAULT_SIZE, 766, Short.MAX_VALUE)
                                    .addComponent(jScrollPane35))))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(lbRazon14)
                .addGap(3, 3, 3)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jCheckBox1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox2))
                    .addComponent(jScrollPane64, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscPlantilla14, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(lbRazon37, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane35)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jCheckBox4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox3, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                    .addComponent(btnBuscPlantilla15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbRazon23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jCheckBox6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox5))
                    .addComponent(jScrollPane34)
                    .addComponent(btnBuscPlantilla16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbRazon24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jCheckBox21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox22))
                    .addComponent(jScrollPane47)
                    .addComponent(btnBuscPlantilla29, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbRazon40, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane33)
                    .addComponent(btnBuscPlantilla17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jCheckBox8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox7))
                    .addComponent(lbRazon25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jCheckBox10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox9))
                    .addComponent(jScrollPane32, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lbRazon26, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBuscPlantilla18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)))
                .addGap(3, 3, 3)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane36, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lbRazon27, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                        .addComponent(btnBuscPlantilla19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jCheckBox12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox11)))
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnHipertension, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbRazon29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDiabetes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMetabolica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBuscPlantilla23, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane29, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jCheckBox14)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jCheckBox13))))
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jCheckBox16)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jCheckBox15))
                        .addComponent(jScrollPane30)
                        .addComponent(lbRazon30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnBuscPlantilla20, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane31)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jCheckBox18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox17))
                    .addComponent(btnBuscPlantilla21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbRazon31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15)
                .addComponent(lbRazon39)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBuscPlantilla36, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane48, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jCheckBox23)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jCheckBox24))))
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBuscPlantilla22, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane65, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jCheckBox20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox19))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lbRazon35)
                        .addGap(0, 0, 0)
                        .addComponent(lbRazon34)))
                .addGap(10, 10, 10))
        );

        tabPrincipal.addTab("Antecedentes", jPanel4);

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));

        lbRazon20.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon20.setText("Resultados citologia:");

        lbRazon12.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon12.setText("Fecha ultima menstruación:");

        lbRazon15.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon15.setText("Fecha ultima citologia:");

        txtResultadoCitologia.setColumns(20);
        txtResultadoCitologia.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtResultadoCitologia.setLineWrap(true);
        txtResultadoCitologia.setRows(2);
        txtResultadoCitologia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtResultadoCitologiaKeyReleased(evt);
            }
        });
        jScrollPane4.setViewportView(txtResultadoCitologia);

        lbRazon28.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon28.setText("Planificación:");

        txtPlanificacion.setColumns(20);
        txtPlanificacion.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtPlanificacion.setLineWrap(true);
        txtPlanificacion.setRows(1);
        txtPlanificacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPlanificacionKeyReleased(evt);
            }
        });
        jScrollPane38.setViewportView(txtPlanificacion);

        lbRazon33.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon33.setText("Fecha de menarca:");

        txtMenarca.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtMenarca.setName("Embarazos"); // NOI18N
        txtMenarca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMenarcaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMenarcaKeyTyped(evt);
            }
        });

        txtUltimaMenstruacion.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtUltimaMenstruacion.setName("Embarazos"); // NOI18N
        txtUltimaMenstruacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUltimaMenstruacionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUltimaMenstruacionKeyTyped(evt);
            }
        });

        txtUltimaCitologia.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtUltimaCitologia.setName("Embarazos"); // NOI18N
        txtUltimaCitologia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUltimaCitologiaActionPerformed(evt);
            }
        });
        txtUltimaCitologia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUltimaCitologiaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUltimaCitologiaKeyTyped(evt);
            }
        });

        pnlCirujano.setBackground(new java.awt.Color(255, 255, 255));

        lbPartos.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbPartos.setText("Partos:");

        txtPartos1.setColumns(20);
        txtPartos1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtPartos1.setLineWrap(true);
        txtPartos1.setRows(2);
        txtPartos1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPartos1KeyReleased(evt);
            }
        });
        jScrollPane5.setViewportView(txtPartos1);

        javax.swing.GroupLayout pnlCirujanoLayout = new javax.swing.GroupLayout(pnlCirujano);
        pnlCirujano.setLayout(pnlCirujanoLayout);
        pnlCirujanoLayout.setHorizontalGroup(
            pnlCirujanoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCirujanoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbPartos, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5)
                .addGap(0, 0, 0))
        );
        pnlCirujanoLayout.setVerticalGroup(
            pnlCirujanoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCirujanoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCirujanoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                    .addComponent(lbPartos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        lbRazon13.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon13.setText("Ciclo menstrual:");

        txtCiclo.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCiclo.setName("Embarazos"); // NOI18N
        txtCiclo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCicloKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCicloKeyTyped(evt);
            }
        });

        pnlMedicoEmb.setBackground(new java.awt.Color(255, 255, 255));

        lbRazon16.setFont(new java.awt.Font("Century Gothic", 0, 22)); // NOI18N
        lbRazon16.setText("Embarazos:");

        txtEmbarazos.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtEmbarazos.setName("Embarazos"); // NOI18N
        txtEmbarazos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEmbarazosKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEmbarazosKeyTyped(evt);
            }
        });

        lbRazon19.setFont(new java.awt.Font("Century Gothic", 0, 22)); // NOI18N
        lbRazon19.setText("Cesareas:");

        txtCesareas.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCesareas.setName("Cesareas"); // NOI18N
        txtCesareas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCesareasKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCesareasKeyTyped(evt);
            }
        });

        lbRazon32.setFont(new java.awt.Font("Century Gothic", 0, 22)); // NOI18N
        lbRazon32.setText("Ectopicos:");

        txtEctopicos.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtEctopicos.setName("Partos"); // NOI18N
        txtEctopicos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEctopicosKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEctopicosKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout pnlMedicoEmbLayout = new javax.swing.GroupLayout(pnlMedicoEmb);
        pnlMedicoEmb.setLayout(pnlMedicoEmbLayout);
        pnlMedicoEmbLayout.setHorizontalGroup(
            pnlMedicoEmbLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMedicoEmbLayout.createSequentialGroup()
                .addContainerGap(244, Short.MAX_VALUE)
                .addGroup(pnlMedicoEmbLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lbRazon16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbRazon19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbRazon32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(pnlMedicoEmbLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCesareas, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                    .addComponent(txtEctopicos)
                    .addComponent(txtEmbarazos))
                .addGap(10, 10, 10))
        );
        pnlMedicoEmbLayout.setVerticalGroup(
            pnlMedicoEmbLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMedicoEmbLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pnlMedicoEmbLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtEmbarazos)
                    .addComponent(lbRazon16, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(pnlMedicoEmbLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbRazon19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCesareas))
                .addGap(3, 3, 3)
                .addGroup(pnlMedicoEmbLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMedicoEmbLayout.createSequentialGroup()
                        .addComponent(txtEctopicos)
                        .addGap(3, 3, 3))
                    .addComponent(lbRazon32))
                .addGap(9, 9, 9))
        );

        pnlMedicoEmb1.setBackground(new java.awt.Color(255, 255, 255));

        lbRazon18.setFont(new java.awt.Font("Century Gothic", 0, 22)); // NOI18N
        lbRazon18.setText("Abortos:");

        txtAbortos.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtAbortos.setName("Abortos"); // NOI18N
        txtAbortos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAbortosKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAbortosKeyTyped(evt);
            }
        });

        lbRazon17.setFont(new java.awt.Font("Century Gothic", 0, 22)); // NOI18N
        lbRazon17.setText("Partos:");

        txtPartos.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtPartos.setName("Partos"); // NOI18N
        txtPartos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPartosActionPerformed(evt);
            }
        });
        txtPartos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPartosKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPartosKeyTyped(evt);
            }
        });

        lbRazon21.setFont(new java.awt.Font("Century Gothic", 0, 22)); // NOI18N
        lbRazon21.setText("Hijos vivos:");

        txtHijosVivos.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtHijosVivos.setName("Embarazos"); // NOI18N
        txtHijosVivos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtHijosVivosKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtHijosVivosKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout pnlMedicoEmb1Layout = new javax.swing.GroupLayout(pnlMedicoEmb1);
        pnlMedicoEmb1.setLayout(pnlMedicoEmb1Layout);
        pnlMedicoEmb1Layout.setHorizontalGroup(
            pnlMedicoEmb1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMedicoEmb1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMedicoEmb1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbRazon21)
                    .addComponent(lbRazon17)
                    .addComponent(lbRazon18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMedicoEmb1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtPartos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                    .addComponent(txtAbortos, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtHijosVivos))
                .addGap(0, 248, Short.MAX_VALUE))
        );
        pnlMedicoEmb1Layout.setVerticalGroup(
            pnlMedicoEmb1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMedicoEmb1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pnlMedicoEmb1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbRazon18, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAbortos, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(pnlMedicoEmb1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtPartos, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbRazon17, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(pnlMedicoEmb1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbRazon21, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHijosVivos, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(pnlCirujano, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(pnlMedicoEmb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlMedicoEmb1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbRazon20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbRazon33, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                            .addComponent(lbRazon15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbRazon28, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtMenarca, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                                    .addComponent(txtUltimaCitologia))
                                .addGap(38, 38, 38)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbRazon12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbRazon13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtCiclo, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                                    .addComponent(txtUltimaMenstruacion)))
                            .addComponent(jScrollPane38)
                            .addComponent(jScrollPane4))))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(lbRazon33, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(txtMenarca, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(lbRazon12, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(txtUltimaMenstruacion, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbRazon15, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(txtUltimaCitologia, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(lbRazon13, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(txtCiclo, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                .addGap(21, 21, 21)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane38, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                    .addComponent(lbRazon28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                    .addComponent(lbRazon20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlMedicoEmb1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlMedicoEmb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5)
                .addComponent(pnlCirujano, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(421, 421, 421))
        );

        tabPrincipal.addTab("Gineco-Obstetricos", jPanel16);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));

        txtRevision.setColumns(20);
        txtRevision.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtRevision.setLineWrap(true);
        txtRevision.setRows(2);
        txtRevision.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRevisionKeyReleased(evt);
            }
        });
        jScrollPane25.setViewportView(txtRevision);

        lbRazon38.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbRazon38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbRazon38.setText("Inspección general");

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane25)
                    .addComponent(lbRazon38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(lbRazon38)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("CLASIFICACIÓN");

        jLabel18.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel18.setText("Obeso: Tipo III");

        jLabel19.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel19.setText("Obeso: Tipo II");

        jLabel20.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel20.setText("Obeso: Tipo I");

        jLabel21.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel21.setText("Sobrepeso");

        jLabel22.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel22.setText("Peso Normal");

        jLabel23.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel23.setText("Delgadez aceptable");

        jLabel24.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel24.setText("Delgadez moderada");

        jLabel25.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel25.setText("Delgadez Severa");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator7)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel25)
                .addGap(3, 3, 3)
                .addComponent(jLabel24)
                .addGap(3, 3, 3)
                .addComponent(jLabel23)
                .addGap(3, 3, 3)
                .addComponent(jLabel22)
                .addGap(3, 3, 3)
                .addComponent(jLabel21)
                .addGap(3, 3, 3)
                .addComponent(jLabel20)
                .addGap(3, 3, 3)
                .addComponent(jLabel19)
                .addGap(3, 3, 3)
                .addComponent(jLabel18))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("ÍNDICE MASA");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setText("<16.00");

        jLabel10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel10.setText("16.00 - 16.99");

        jLabel12.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel12.setText("17.00 - 18.49");

        jLabel13.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel13.setText("18.50 - 24.99");

        jLabel14.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel14.setText("25.00 - 29.99");

        jLabel15.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel15.setText("30.00 - 34.99");

        jLabel16.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel16.setText("35.00 - 40.00");

        jLabel17.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel17.setText(">40.00");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator5)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel8)
                .addGap(3, 3, 3)
                .addComponent(jLabel10)
                .addGap(3, 3, 3)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel13)
                .addGap(3, 3, 3)
                .addComponent(jLabel14)
                .addGap(3, 3, 3)
                .addComponent(jLabel15)
                .addGap(3, 3, 3)
                .addComponent(jLabel16)
                .addGap(3, 3, 3)
                .addComponent(jLabel17))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 101, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlMedico1.setBackground(new java.awt.Color(255, 255, 255));

        lbDireccion16.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion16.setText("Cabeza y cuello");

        txtCabezaCuello.setColumns(20);
        txtCabezaCuello.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        txtCabezaCuello.setLineWrap(true);
        txtCabezaCuello.setRows(2);
        txtCabezaCuello.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCabezaCuelloKeyReleased(evt);
            }
        });
        jScrollPane16.setViewportView(txtCabezaCuello);

        btnBuscPlantilla1.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla1.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscPlantilla1.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/listadoPequeño.png"))); // NOI18N
        btnBuscPlantilla1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        btnBuscPlantilla1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscPlantilla1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscPlantilla1.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnBuscPlantilla1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscPlantilla1ActionPerformed(evt);
            }
        });

        lbDireccion47.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion47.setText("Ojos");

        lbDireccion48.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion48.setText("Oídos");

        lbDireccion49.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion49.setText("Nariz");

        lbDireccion50.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion50.setText("Boca y garganta:");

        lbDireccion51.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion51.setText("Torax");

        lbDireccion23.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion23.setText("Cardiopulmonar");

        lbDireccion52.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion52.setText("Abdomen");

        lbDireccion55.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion55.setText("Ano");

        lbDireccion56.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion56.setText("Genitourinario");

        lbDireccion57.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion57.setText("Musculo Esqueletico");

        lbDireccion58.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion58.setText("Piel Y Anexos");

        lbDireccion59.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion59.setText("Neurologico");

        btnBuscPlantilla3.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla3.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscPlantilla3.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/listadoPequeño.png"))); // NOI18N
        btnBuscPlantilla3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        btnBuscPlantilla3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscPlantilla3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscPlantilla3.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnBuscPlantilla3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscPlantilla3ActionPerformed(evt);
            }
        });

        btnBuscPlantilla4.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla4.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscPlantilla4.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/listadoPequeño.png"))); // NOI18N
        btnBuscPlantilla4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        btnBuscPlantilla4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscPlantilla4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscPlantilla4.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnBuscPlantilla4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscPlantilla4ActionPerformed(evt);
            }
        });

        btnBuscPlantilla5.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla5.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscPlantilla5.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/listadoPequeño.png"))); // NOI18N
        btnBuscPlantilla5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        btnBuscPlantilla5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscPlantilla5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscPlantilla5.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnBuscPlantilla5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscPlantilla5ActionPerformed(evt);
            }
        });

        btnBuscPlantilla6.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla6.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscPlantilla6.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/listadoPequeño.png"))); // NOI18N
        btnBuscPlantilla6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        btnBuscPlantilla6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscPlantilla6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscPlantilla6.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnBuscPlantilla6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscPlantilla6ActionPerformed(evt);
            }
        });

        btnBuscPlantilla7.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla7.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscPlantilla7.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/listadoPequeño.png"))); // NOI18N
        btnBuscPlantilla7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        btnBuscPlantilla7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscPlantilla7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscPlantilla7.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnBuscPlantilla7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscPlantilla7ActionPerformed(evt);
            }
        });

        btnBuscPlantilla8.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla8.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscPlantilla8.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/listadoPequeño.png"))); // NOI18N
        btnBuscPlantilla8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        btnBuscPlantilla8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscPlantilla8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscPlantilla8.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnBuscPlantilla8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscPlantilla8ActionPerformed(evt);
            }
        });

        btnBuscPlantilla9.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla9.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscPlantilla9.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/listadoPequeño.png"))); // NOI18N
        btnBuscPlantilla9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        btnBuscPlantilla9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscPlantilla9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscPlantilla9.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnBuscPlantilla9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscPlantilla9ActionPerformed(evt);
            }
        });

        btnBuscPlantilla10.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla10.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscPlantilla10.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/listadoPequeño.png"))); // NOI18N
        btnBuscPlantilla10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        btnBuscPlantilla10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscPlantilla10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscPlantilla10.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnBuscPlantilla10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscPlantilla10ActionPerformed(evt);
            }
        });

        btnBuscPlantilla11.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla11.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscPlantilla11.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/listadoPequeño.png"))); // NOI18N
        btnBuscPlantilla11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        btnBuscPlantilla11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscPlantilla11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscPlantilla11.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnBuscPlantilla11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscPlantilla11ActionPerformed(evt);
            }
        });

        btnBuscPlantilla12.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla12.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscPlantilla12.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/listadoPequeño.png"))); // NOI18N
        btnBuscPlantilla12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        btnBuscPlantilla12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscPlantilla12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscPlantilla12.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnBuscPlantilla12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscPlantilla12ActionPerformed(evt);
            }
        });

        btnBuscPlantilla13.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla13.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscPlantilla13.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/listadoPequeño.png"))); // NOI18N
        btnBuscPlantilla13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        btnBuscPlantilla13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscPlantilla13.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscPlantilla13.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnBuscPlantilla13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscPlantilla13ActionPerformed(evt);
            }
        });

        btnBuscPlantilla2.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla2.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscPlantilla2.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/listadoPequeño.png"))); // NOI18N
        btnBuscPlantilla2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        btnBuscPlantilla2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscPlantilla2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscPlantilla2.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnBuscPlantilla2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscPlantilla2ActionPerformed(evt);
            }
        });

        txtNeurologico.setColumns(20);
        txtNeurologico.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        txtNeurologico.setLineWrap(true);
        txtNeurologico.setRows(1);
        txtNeurologico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNeurologicoKeyReleased(evt);
            }
        });
        jScrollPane61.setViewportView(txtNeurologico);

        txtPiel.setColumns(20);
        txtPiel.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        txtPiel.setLineWrap(true);
        txtPiel.setRows(2);
        txtPiel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPielKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPielKeyTyped(evt);
            }
        });
        jScrollPane62.setViewportView(txtPiel);

        txtMusculoEsqueletico.setColumns(20);
        txtMusculoEsqueletico.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        txtMusculoEsqueletico.setLineWrap(true);
        txtMusculoEsqueletico.setRows(2);
        txtMusculoEsqueletico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMusculoEsqueleticoKeyReleased(evt);
            }
        });
        jScrollPane60.setViewportView(txtMusculoEsqueletico);

        txtUrinario.setColumns(20);
        txtUrinario.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        txtUrinario.setLineWrap(true);
        txtUrinario.setRows(2);
        txtUrinario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUrinarioKeyReleased(evt);
            }
        });
        jScrollPane7.setViewportView(txtUrinario);

        txtDigestivo.setColumns(20);
        txtDigestivo.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        txtDigestivo.setLineWrap(true);
        txtDigestivo.setRows(1);
        txtDigestivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDigestivoKeyReleased(evt);
            }
        });
        jScrollPane58.setViewportView(txtDigestivo);

        txtAbdomen.setColumns(20);
        txtAbdomen.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        txtAbdomen.setLineWrap(true);
        txtAbdomen.setRows(1);
        txtAbdomen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAbdomenKeyReleased(evt);
            }
        });
        jScrollPane57.setViewportView(txtAbdomen);

        txtCardioPulmonar.setColumns(20);
        txtCardioPulmonar.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        txtCardioPulmonar.setLineWrap(true);
        txtCardioPulmonar.setRows(1);
        txtCardioPulmonar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCardioPulmonarKeyReleased(evt);
            }
        });
        jScrollPane56.setViewportView(txtCardioPulmonar);

        txtTacto.setColumns(20);
        txtTacto.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        txtTacto.setLineWrap(true);
        txtTacto.setRows(2);
        txtTacto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTactoKeyReleased(evt);
            }
        });
        jScrollPane28.setViewportView(txtTacto);

        txtGusto.setColumns(20);
        txtGusto.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        txtGusto.setLineWrap(true);
        txtGusto.setRows(2);
        txtGusto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGustoKeyReleased(evt);
            }
        });
        jScrollPane27.setViewportView(txtGusto);

        txtNariz.setColumns(20);
        txtNariz.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        txtNariz.setLineWrap(true);
        txtNariz.setRows(2);
        txtNariz.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNarizKeyReleased(evt);
            }
        });
        jScrollPane26.setViewportView(txtNariz);

        txtOido.setColumns(20);
        txtOido.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        txtOido.setLineWrap(true);
        txtOido.setRows(1);
        txtOido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtOidoKeyReleased(evt);
            }
        });
        jScrollPane17.setViewportView(txtOido);

        txtVista.setColumns(20);
        txtVista.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        txtVista.setLineWrap(true);
        txtVista.setRows(1);
        txtVista.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtVistaKeyReleased(evt);
            }
        });
        jScrollPane18.setViewportView(txtVista);

        lbDireccion63.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion63.setText("Extremidades");

        btnBuscPlantilla37.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla37.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscPlantilla37.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/listadoPequeño.png"))); // NOI18N
        btnBuscPlantilla37.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        btnBuscPlantilla37.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscPlantilla37.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscPlantilla37.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnBuscPlantilla37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscPlantilla37ActionPerformed(evt);
            }
        });

        txtExtremidades1.setColumns(20);
        txtExtremidades1.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        txtExtremidades1.setLineWrap(true);
        txtExtremidades1.setRows(1);
        txtExtremidades1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtExtremidades1KeyReleased(evt);
            }
        });
        jScrollPane63.setViewportView(txtExtremidades1);

        lbDireccion64.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion64.setText("Glúteos");

        btnBuscPlantilla38.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla38.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscPlantilla38.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/listadoPequeño.png"))); // NOI18N
        btnBuscPlantilla38.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        btnBuscPlantilla38.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscPlantilla38.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscPlantilla38.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnBuscPlantilla38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscPlantilla38ActionPerformed(evt);
            }
        });

        txtGluteos.setColumns(20);
        txtGluteos.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        txtGluteos.setLineWrap(true);
        txtGluteos.setRows(1);
        txtGluteos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGluteosKeyReleased(evt);
            }
        });
        jScrollPane66.setViewportView(txtGluteos);

        lbDireccion65.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion65.setText("Mamas");

        btnBuscPlantilla39.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla39.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscPlantilla39.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/listadoPequeño.png"))); // NOI18N
        btnBuscPlantilla39.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        btnBuscPlantilla39.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscPlantilla39.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscPlantilla39.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnBuscPlantilla39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscPlantilla39ActionPerformed(evt);
            }
        });

        txtMamas.setColumns(20);
        txtMamas.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        txtMamas.setLineWrap(true);
        txtMamas.setRows(1);
        txtMamas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMamasKeyReleased(evt);
            }
        });
        jScrollPane67.setViewportView(txtMamas);

        javax.swing.GroupLayout pnlMedico1Layout = new javax.swing.GroupLayout(pnlMedico1);
        pnlMedico1.setLayout(pnlMedico1Layout);
        pnlMedico1Layout.setHorizontalGroup(
            pnlMedico1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMedico1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMedico1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbDireccion65, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbDireccion64, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbDireccion63, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbDireccion47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbDireccion49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbDireccion50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbDireccion51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbDireccion23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbDireccion52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbDireccion59, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbDireccion58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbDireccion57, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                    .addComponent(lbDireccion56, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbDireccion48, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbDireccion55, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbDireccion16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMedico1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMedico1Layout.createSequentialGroup()
                        .addGroup(pnlMedico1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlMedico1Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(jScrollPane26, javax.swing.GroupLayout.DEFAULT_SIZE, 772, Short.MAX_VALUE))
                            .addGroup(pnlMedico1Layout.createSequentialGroup()
                                .addGroup(pnlMedico1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnBuscPlantilla5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnBuscPlantilla6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnBuscPlantilla7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnBuscPlantilla9, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnBuscPlantilla10, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnBuscPlantilla11, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnBuscPlantilla12, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnBuscPlantilla13, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnBuscPlantilla2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnBuscPlantilla3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnBuscPlantilla4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlMedico1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane56)
                                    .addComponent(jScrollPane28, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane57)
                                    .addComponent(jScrollPane58)
                                    .addComponent(jScrollPane7)
                                    .addComponent(jScrollPane60)
                                    .addComponent(jScrollPane62)
                                    .addComponent(jScrollPane61)
                                    .addComponent(jScrollPane27, javax.swing.GroupLayout.Alignment.LEADING)))
                            .addComponent(btnBuscPlantilla8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMedico1Layout.createSequentialGroup()
                        .addComponent(btnBuscPlantilla1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlMedico1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane18)
                            .addComponent(jScrollPane16))
                        .addGap(10, 10, 10))
                    .addGroup(pnlMedico1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jScrollPane17)
                        .addContainerGap())
                    .addGroup(pnlMedico1Layout.createSequentialGroup()
                        .addGroup(pnlMedico1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnBuscPlantilla39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnBuscPlantilla38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnBuscPlantilla37, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlMedico1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane66, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane67)
                            .addComponent(jScrollPane63, javax.swing.GroupLayout.Alignment.LEADING))
                        .addContainerGap())))
        );
        pnlMedico1Layout.setVerticalGroup(
            pnlMedico1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMedico1Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(pnlMedico1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane16)
                    .addComponent(btnBuscPlantilla1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbDireccion16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(pnlMedico1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBuscPlantilla3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane18, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(lbDireccion47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(pnlMedico1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlMedico1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnBuscPlantilla4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbDireccion48, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)))
                .addGap(3, 3, 3)
                .addGroup(pnlMedico1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBuscPlantilla5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlMedico1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lbDireccion49, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane26, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(3, 3, 3)
                .addGroup(pnlMedico1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMedico1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lbDireccion50, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                        .addComponent(btnBuscPlantilla6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(pnlMedico1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbDireccion51, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlMedico1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(pnlMedico1Layout.createSequentialGroup()
                            .addComponent(btnBuscPlantilla7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(2, 2, 2))))
                .addGap(3, 3, 3)
                .addGroup(pnlMedico1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbDireccion23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBuscPlantilla8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane56, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(pnlMedico1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBuscPlantilla9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane57)
                    .addComponent(lbDireccion52, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(pnlMedico1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbDireccion55, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBuscPlantilla10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane58, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(pnlMedico1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMedico1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lbDireccion56, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                        .addComponent(btnBuscPlantilla11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(pnlMedico1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMedico1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lbDireccion57, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                        .addComponent(btnBuscPlantilla12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane60, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(pnlMedico1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMedico1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lbDireccion58, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                        .addComponent(btnBuscPlantilla13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane62, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(pnlMedico1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnBuscPlantilla2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbDireccion59, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane61, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(pnlMedico1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnBuscPlantilla37, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbDireccion63, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane63, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(pnlMedico1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnBuscPlantilla38, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbDireccion64, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane66, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(pnlMedico1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnBuscPlantilla39, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbDireccion65, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane67, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        pnlCirujano1.setBackground(new java.awt.Color(255, 255, 255));

        lbDireccion53.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion53.setText("C/C");

        lbDireccion54.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion54.setText("Torax");

        lbDireccion60.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion60.setText("Abdomen");

        lbDireccion61.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion61.setText("Extremidades");

        lbDireccion62.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion62.setText("Neurologico");

        lbDireccion24.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion24.setText("Piel y Faneras");

        btnBuscPlantilla30.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla30.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscPlantilla30.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/listadoPequeño.png"))); // NOI18N
        btnBuscPlantilla30.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        btnBuscPlantilla30.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscPlantilla30.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscPlantilla30.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnBuscPlantilla30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscPlantilla30ActionPerformed(evt);
            }
        });

        btnBuscPlantilla31.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla31.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscPlantilla31.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/listadoPequeño.png"))); // NOI18N
        btnBuscPlantilla31.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        btnBuscPlantilla31.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscPlantilla31.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscPlantilla31.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnBuscPlantilla31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscPlantilla31ActionPerformed(evt);
            }
        });

        btnBuscPlantilla32.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla32.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscPlantilla32.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/listadoPequeño.png"))); // NOI18N
        btnBuscPlantilla32.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        btnBuscPlantilla32.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscPlantilla32.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscPlantilla32.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnBuscPlantilla32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscPlantilla32ActionPerformed(evt);
            }
        });

        btnBuscPlantilla33.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla33.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscPlantilla33.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/listadoPequeño.png"))); // NOI18N
        btnBuscPlantilla33.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        btnBuscPlantilla33.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscPlantilla33.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscPlantilla33.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnBuscPlantilla33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscPlantilla33ActionPerformed(evt);
            }
        });

        btnBuscPlantilla34.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla34.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscPlantilla34.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/listadoPequeño.png"))); // NOI18N
        btnBuscPlantilla34.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        btnBuscPlantilla34.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscPlantilla34.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscPlantilla34.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnBuscPlantilla34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscPlantilla34ActionPerformed(evt);
            }
        });

        btnBuscPlantilla35.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla35.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscPlantilla35.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/listadoPequeño.png"))); // NOI18N
        btnBuscPlantilla35.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        btnBuscPlantilla35.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscPlantilla35.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscPlantilla35.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnBuscPlantilla35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscPlantilla35ActionPerformed(evt);
            }
        });

        txtPielYFaneras.setColumns(20);
        txtPielYFaneras.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        txtPielYFaneras.setLineWrap(true);
        txtPielYFaneras.setRows(1);
        txtPielYFaneras.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPielYFanerasKeyReleased(evt);
            }
        });
        jScrollPane69.setViewportView(txtPielYFaneras);

        txtNeurologico1.setColumns(20);
        txtNeurologico1.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        txtNeurologico1.setLineWrap(true);
        txtNeurologico1.setRows(2);
        txtNeurologico1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNeurologico1KeyReleased(evt);
            }
        });
        jScrollPane41.setViewportView(txtNeurologico1);

        txtExtremidades.setColumns(20);
        txtExtremidades.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        txtExtremidades.setLineWrap(true);
        txtExtremidades.setRows(2);
        txtExtremidades.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtExtremidadesKeyReleased(evt);
            }
        });
        jScrollPane42.setViewportView(txtExtremidades);

        txtAbdomen1.setColumns(20);
        txtAbdomen1.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        txtAbdomen1.setLineWrap(true);
        txtAbdomen1.setRows(2);
        txtAbdomen1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAbdomen1KeyReleased(evt);
            }
        });
        jScrollPane43.setViewportView(txtAbdomen1);

        txtTorax.setColumns(20);
        txtTorax.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        txtTorax.setLineWrap(true);
        txtTorax.setRows(1);
        txtTorax.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtToraxKeyReleased(evt);
            }
        });
        jScrollPane44.setViewportView(txtTorax);

        txtCC.setColumns(20);
        txtCC.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        txtCC.setLineWrap(true);
        txtCC.setRows(1);
        txtCC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCCKeyReleased(evt);
            }
        });
        jScrollPane45.setViewportView(txtCC);

        javax.swing.GroupLayout pnlCirujano1Layout = new javax.swing.GroupLayout(pnlCirujano1);
        pnlCirujano1.setLayout(pnlCirujano1Layout);
        pnlCirujano1Layout.setHorizontalGroup(
            pnlCirujano1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCirujano1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(pnlCirujano1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlCirujano1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lbDireccion53, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbDireccion60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbDireccion61, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                        .addComponent(lbDireccion62, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbDireccion24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(lbDireccion54, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCirujano1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBuscPlantilla32, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(btnBuscPlantilla33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBuscPlantilla34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBuscPlantilla31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBuscPlantilla35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBuscPlantilla30, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCirujano1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane41, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane42, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane43, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane44)
                    .addComponent(jScrollPane45)
                    .addComponent(jScrollPane69))
                .addGap(12, 12, 12))
        );
        pnlCirujano1Layout.setVerticalGroup(
            pnlCirujano1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCirujano1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pnlCirujano1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBuscPlantilla30, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(lbDireccion53, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane45))
                .addGap(3, 3, 3)
                .addGroup(pnlCirujano1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane44, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlCirujano1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnBuscPlantilla31, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbDireccion54, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)))
                .addGap(3, 3, 3)
                .addGroup(pnlCirujano1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBuscPlantilla32, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlCirujano1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lbDireccion60, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane43, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(3, 3, 3)
                .addGroup(pnlCirujano1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCirujano1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lbDireccion61, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                        .addComponent(btnBuscPlantilla33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(pnlCirujano1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbDireccion62, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlCirujano1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(pnlCirujano1Layout.createSequentialGroup()
                            .addComponent(btnBuscPlantilla34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(2, 2, 2))))
                .addGap(3, 3, 3)
                .addGroup(pnlCirujano1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbDireccion24, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(btnBuscPlantilla35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane69))
                .addGap(5, 5, 5))
        );

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));

        lbDireccion29.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion29.setText("Frec. Cardiaca:");

        txtFrecCardiaca.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtFrecCardiaca.setName("TLLC"); // NOI18N
        txtFrecCardiaca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFrecCardiacaActionPerformed(evt);
            }
        });
        txtFrecCardiaca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFrecCardiacaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFrecCardiacaKeyTyped(evt);
            }
        });

        lbDireccion33.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion33.setText("Presión Arterial:");

        lbDireccion32.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion32.setText("Peso (Kg):");

        lbDireccion30.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion30.setText("Indice Masa Corp.:");

        txtPa.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtPa.setName("Tº"); // NOI18N
        txtPa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPaActionPerformed(evt);
            }
        });
        txtPa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPaKeyTyped(evt);
            }
        });

        txtPa2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtPa2.setName("Actitud"); // NOI18N
        txtPa2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPa2ActionPerformed(evt);
            }
        });
        txtPa2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPa2KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPa2KeyTyped(evt);
            }
        });

        lbDireccion46.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion46.setText("(mmHg)");

        txtPeso.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtPeso.setName("FR"); // NOI18N
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

        txtImc.setEditable(false);
        txtImc.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtImc.setName("TLLC"); // NOI18N
        txtImc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtImcActionPerformed(evt);
            }
        });
        txtImc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtImcKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtImcKeyTyped(evt);
            }
        });

        lbDireccion19.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion19.setText("Frec. Respiratoria:");

        txtFrecRespiratoria.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtFrecRespiratoria.setName("TLLC"); // NOI18N
        txtFrecRespiratoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFrecRespiratoriaActionPerformed(evt);
            }
        });
        txtFrecRespiratoria.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFrecRespiratoriaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFrecRespiratoriaKeyTyped(evt);
            }
        });

        lbDireccion21.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion21.setText("Temperatura:");

        txtT.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtT.setName("FC"); // NOI18N
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

        lbDireccion28.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion28.setText("Talla (cm):");

        txtEstatura.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtEstatura.setName("Pulso"); // NOI18N
        txtEstatura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEstaturaActionPerformed(evt);
            }
        });
        txtEstatura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEstaturaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEstaturaKeyTyped(evt);
            }
        });

        lbDireccion31.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion31.setText("Tiene:");

        txtTiene.setEditable(false);
        txtTiene.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtTiene.setName("TLLC"); // NOI18N
        txtTiene.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTieneActionPerformed(evt);
            }
        });
        txtTiene.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTieneKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTieneKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lbDireccion29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbDireccion33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbDireccion32, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbDireccion30, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtFrecCardiaca)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(txtPa, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPa2))
                    .addComponent(txtPeso)
                    .addComponent(txtImc, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbDireccion46)
                .addGap(58, 58, 58)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbDireccion28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbDireccion19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbDireccion21, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtFrecRespiratoria, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                            .addComponent(txtT)
                            .addComponent(txtEstatura, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(lbDireccion31, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTiene, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDireccion29, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFrecCardiaca, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbDireccion19, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFrecRespiratoria, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDireccion33, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPa, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPa2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbDireccion46, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbDireccion21, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtT, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDireccion32, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbDireccion28, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEstatura, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDireccion30, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtImc, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbDireccion31, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTiene, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(pnlCirujano1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlMedico1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlMedico1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(pnlCirujano1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3))
        );

        tabPrincipal.addTab("Examen Fisico", jPanel8);

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));

        lbEmail34.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbEmail34.setText("Tipo diagnóstico:");

        cmbTipoDiagnostico.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        cmbTipoDiagnostico.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Impresión diagnóstica", "Confirmado nuevo", "Confirmado repetido" }));

        lbEmail18.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbEmail18.setText("Principal:");

        btnBuscCie1.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscCie1.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscCie1.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscCie1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/listadoPequeño.png"))); // NOI18N
        btnBuscCie1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        btnBuscCie1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscCie1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscCie1.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnBuscCie1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscCie1ActionPerformed(evt);
            }
        });

        txtCie1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCie1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCie1FocusLost(evt);
            }
        });
        txtCie1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCie1KeyReleased(evt);
            }
        });

        txtDiagnostico1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtDiagnostico1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDiagnostico1KeyReleased(evt);
            }
        });

        lbEmail19.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbEmail19.setText("Relacionado:");

        txtCie2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCie2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCie2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCie2FocusLost(evt);
            }
        });
        txtCie2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCie2KeyReleased(evt);
            }
        });

        btnBuscCie2.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscCie2.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscCie2.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscCie2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/listadoPequeño.png"))); // NOI18N
        btnBuscCie2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        btnBuscCie2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscCie2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscCie2.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnBuscCie2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscCie2ActionPerformed(evt);
            }
        });

        txtDiagnostico2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtDiagnostico2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDiagnostico2KeyReleased(evt);
            }
        });

        txtDiagnostico3.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtDiagnostico3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDiagnostico3KeyReleased(evt);
            }
        });

        txtCie3.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCie3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCie3FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCie3FocusLost(evt);
            }
        });
        txtCie3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCie3KeyReleased(evt);
            }
        });

        btnBuscCie3.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscCie3.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscCie3.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscCie3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/listadoPequeño.png"))); // NOI18N
        btnBuscCie3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        btnBuscCie3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscCie3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscCie3.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnBuscCie3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscCie3ActionPerformed(evt);
            }
        });

        lbEmail26.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbEmail26.setText("Relacionado:");

        lbEmail27.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbEmail27.setText("Rel.(complicación):");

        btnBuscCie4.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscCie4.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscCie4.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscCie4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/listadoPequeño.png"))); // NOI18N
        btnBuscCie4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        btnBuscCie4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscCie4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscCie4.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnBuscCie4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscCie4ActionPerformed(evt);
            }
        });

        txtCie4.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCie4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCie4FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCie4FocusLost(evt);
            }
        });
        txtCie4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCie4KeyReleased(evt);
            }
        });

        txtDiagnostico4.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtDiagnostico4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDiagnostico4KeyReleased(evt);
            }
        });

        txtDiagnosticoEspecializado.setColumns(20);
        txtDiagnosticoEspecializado.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtDiagnosticoEspecializado.setLineWrap(true);
        txtDiagnosticoEspecializado.setRows(1);
        txtDiagnosticoEspecializado.setName("Tratamiento"); // NOI18N
        txtDiagnosticoEspecializado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDiagnosticoEspecializadoKeyReleased(evt);
            }
        });
        jScrollPane37.setViewportView(txtDiagnosticoEspecializado);

        btnBuscPlantilla24.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla24.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscPlantilla24.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/listadoPequeño.png"))); // NOI18N
        btnBuscPlantilla24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        btnBuscPlantilla24.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscPlantilla24.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscPlantilla24.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnBuscPlantilla24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscPlantilla24ActionPerformed(evt);
            }
        });

        lbEmail30.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbEmail30.setText("Diagnostico");

        lbEmail33.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbEmail33.setText("especializado:");

        lbEmail20.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbEmail20.setText("Tratamiento:");

        txtTratamiento.setColumns(20);
        txtTratamiento.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtTratamiento.setLineWrap(true);
        txtTratamiento.setRows(3);
        txtTratamiento.setName("Tratamiento"); // NOI18N
        txtTratamiento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTratamientoKeyReleased(evt);
            }
        });
        jScrollPane19.setViewportView(txtTratamiento);

        btnBuscPlantilla25.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla25.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscPlantilla25.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/listadoPequeño.png"))); // NOI18N
        btnBuscPlantilla25.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        btnBuscPlantilla25.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscPlantilla25.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscPlantilla25.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnBuscPlantilla25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscPlantilla25ActionPerformed(evt);
            }
        });

        txtRiesgosRecomendaciones.setColumns(20);
        txtRiesgosRecomendaciones.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtRiesgosRecomendaciones.setLineWrap(true);
        txtRiesgosRecomendaciones.setRows(2);
        txtRiesgosRecomendaciones.setName("Recomendaciones"); // NOI18N
        txtRiesgosRecomendaciones.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRiesgosRecomendacionesKeyReleased(evt);
            }
        });
        jScrollPane20.setViewportView(txtRiesgosRecomendaciones);

        lbEmail21.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbEmail21.setText("Riesgos:");

        lbEmail28.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        lbEmail28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbEmail28.setText("PROCEDIMIENTOS PROPUESTOS");

        lbEmail24.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbEmail24.setText("Principal:");

        lbEmail25.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbEmail25.setText("Relacionado:");

        btnBuscCup2.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscCup2.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscCup2.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscCup2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/listadoPequeño.png"))); // NOI18N
        btnBuscCup2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        btnBuscCup2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscCup2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscCup2.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnBuscCup2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscCup2ActionPerformed(evt);
            }
        });

        txtCup1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCup1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCup1KeyReleased(evt);
            }
        });

        txtProcedimiento1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtProcedimiento1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProcedimiento1KeyReleased(evt);
            }
        });

        txtProcedimiento.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtProcedimiento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProcedimientoKeyReleased(evt);
            }
        });

        txtCup.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCup.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCupKeyReleased(evt);
            }
        });

        btnBuscCup1.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscCup1.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscCup1.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscCup1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/listadoPequeño.png"))); // NOI18N
        btnBuscCup1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        btnBuscCup1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscCup1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscCup1.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnBuscCup1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscCup1ActionPerformed(evt);
            }
        });

        lbEmail31.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbEmail31.setText("Observación de");

        lbEmail32.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbEmail32.setText("procedimientos:");

        btnBuscPlantilla26.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla26.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscPlantilla26.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/listadoPequeño.png"))); // NOI18N
        btnBuscPlantilla26.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        btnBuscPlantilla26.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscPlantilla26.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscPlantilla26.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnBuscPlantilla26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscPlantilla26ActionPerformed(evt);
            }
        });

        txtObservacionProcedimientos.setColumns(20);
        txtObservacionProcedimientos.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtObservacionProcedimientos.setLineWrap(true);
        txtObservacionProcedimientos.setRows(2);
        txtObservacionProcedimientos.setName("Recomendaciones"); // NOI18N
        txtObservacionProcedimientos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtObservacionProcedimientosKeyReleased(evt);
            }
        });
        jScrollPane24.setViewportView(txtObservacionProcedimientos);

        lbNit6.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbNit6.setText("Anexos:");

        jButton6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/subir imagen.png"))); // NOI18N
        jButton6.setText("Seleccionar Archivo");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        txtNombreAnexoConsulta.setEditable(false);
        txtNombreAnexoConsulta.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        lbNit9.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbNit9.setText("Nombre del archivo:");

        txtDescripcionExamenConsulta.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtDescripcionExamenConsulta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescripcionExamenConsultaKeyReleased(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/agregar2.png"))); // NOI18N
        jButton7.setText("Agregar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        tblExamenes.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        tblExamenes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Descripción del examen", "url", "Activo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblExamenes.setComponentPopupMenu(jPopupMenu2);
        tblExamenes.setRowHeight(19);
        tblExamenes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblExamenesMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(tblExamenes);
        if (tblExamenes.getColumnModel().getColumnCount() > 0) {
            tblExamenes.getColumnModel().getColumn(0).setMinWidth(200);
            tblExamenes.getColumnModel().getColumn(0).setPreferredWidth(200);
            tblExamenes.getColumnModel().getColumn(0).setMaxWidth(200);
            tblExamenes.getColumnModel().getColumn(2).setMinWidth(0);
            tblExamenes.getColumnModel().getColumn(2).setPreferredWidth(0);
            tblExamenes.getColumnModel().getColumn(2).setMaxWidth(0);
            tblExamenes.getColumnModel().getColumn(3).setMinWidth(0);
            tblExamenes.getColumnModel().getColumn(3).setPreferredWidth(0);
            tblExamenes.getColumnModel().getColumn(3).setMaxWidth(0);
        }

        lbNit11.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbNit11.setText("Descripción:");

        btnGuardarArchivos.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardarArchivos.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnGuardarArchivos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardarArchivos.setText("GUARDAR ARCHIVOS ADJUNTOS   ");
        btnGuardarArchivos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarArchivos.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardarArchivos.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnGuardarArchivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarArchivosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbEmail28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbEmail32, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                            .addComponent(lbEmail24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbEmail25, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbEmail31, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel15Layout.createSequentialGroup()
                                        .addComponent(btnBuscCup1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtCup, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel15Layout.createSequentialGroup()
                                        .addComponent(btnBuscCup2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtCup1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtProcedimiento1)
                                    .addComponent(txtProcedimiento)))
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(btnBuscPlantilla26, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane24))))
                    .addComponent(jScrollPane10)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lbNit6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbNit11, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel15Layout.createSequentialGroup()
                                        .addComponent(txtDescripcionExamenConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 669, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel15Layout.createSequentialGroup()
                                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbNit9, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtNombreAnexoConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGap(350, 350, 350)
                                .addComponent(btnGuardarArchivos)))
                        .addGap(0, 72, Short.MAX_VALUE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbEmail34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lbEmail19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbEmail18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbEmail26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbEmail27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbEmail30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbEmail33, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lbEmail21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbEmail20, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane19)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnBuscCie2, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                                    .addComponent(btnBuscCie1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnBuscCie3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnBuscCie4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnBuscPlantilla24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnBuscPlantilla25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel15Layout.createSequentialGroup()
                                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtCie3, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtCie2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtCie1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtCie4, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtDiagnostico4)
                                            .addComponent(txtDiagnostico3)
                                            .addComponent(txtDiagnostico2)
                                            .addComponent(txtDiagnostico1)))
                                    .addComponent(jScrollPane20)
                                    .addComponent(jScrollPane37, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(cmbTipoDiagnostico, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbEmail34, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbTipoDiagnostico, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtDiagnostico1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(txtCie1)
                    .addComponent(lbEmail18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBuscCie1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lbEmail19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCie2, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(btnBuscCie2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDiagnostico2))
                .addGap(3, 3, 3)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBuscCie3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCie3, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(txtDiagnostico3)
                    .addComponent(lbEmail26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtCie4, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(lbEmail27, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBuscCie4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDiagnostico4))
                .addGap(10, 10, 10)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnBuscPlantilla24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel15Layout.createSequentialGroup()
                        .addComponent(lbEmail30, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(lbEmail33))
                    .addComponent(jScrollPane37))
                .addGap(10, 10, 10)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane19)
                    .addComponent(lbEmail20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane20, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                    .addComponent(btnBuscPlantilla25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbEmail21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(25, 25, 25)
                .addComponent(lbEmail28, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtProcedimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbEmail24, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbEmail25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtProcedimiento1, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnBuscCup1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCup, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnBuscCup2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCup1, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))))
                .addGap(3, 3, 3)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(lbEmail31)
                        .addGap(0, 0, 0)
                        .addComponent(lbEmail32))
                    .addComponent(btnBuscPlantilla26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane24))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtNombreAnexoConsulta)
                        .addComponent(lbNit9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbNit6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(txtDescripcionExamenConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(lbNit11, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                .addGap(5, 5, 5)
                .addComponent(btnGuardarArchivos)
                .addContainerGap())
        );

        tabPrincipal.addTab("Diagnostico y Tratamiento", jPanel15);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        lbNit4.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit4.setText("Profesional:");

        txtProfesional.setEditable(false);
        txtProfesional.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtProfesional.setName("Nit"); // NOI18N
        txtProfesional.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProfesionalKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtProfesionalKeyTyped(evt);
            }
        });

        lbEmail22.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
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
        lbEmail23.setText("Observaciones:");

        txtObservacionesSeg.setColumns(20);
        txtObservacionesSeg.setLineWrap(true);
        txtObservacionesSeg.setRows(2);
        txtObservacionesSeg.setName("Observaciones"); // NOI18N
        txtObservacionesSeg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtObservacionesSegKeyReleased(evt);
            }
        });
        jScrollPane22.setViewportView(txtObservacionesSeg);

        lbImagenes.setForeground(new java.awt.Color(255, 255, 255));

        tblExamenesEvolucion.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblExamenesEvolucion.setModel(new javax.swing.table.DefaultTableModel(
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
        tblExamenesEvolucion.setComponentPopupMenu(jPopupMenu1);
        tblExamenesEvolucion.setRowHeight(19);
        tblExamenesEvolucion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblExamenesEvolucionMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblExamenesEvolucionMouseEntered(evt);
            }
        });
        jScrollPane9.setViewportView(tblExamenesEvolucion);
        if (tblExamenesEvolucion.getColumnModel().getColumnCount() > 0) {
            tblExamenesEvolucion.getColumnModel().getColumn(0).setMinWidth(200);
            tblExamenesEvolucion.getColumnModel().getColumn(0).setPreferredWidth(200);
            tblExamenesEvolucion.getColumnModel().getColumn(0).setMaxWidth(200);
            tblExamenesEvolucion.getColumnModel().getColumn(2).setMinWidth(0);
            tblExamenesEvolucion.getColumnModel().getColumn(2).setPreferredWidth(0);
            tblExamenesEvolucion.getColumnModel().getColumn(2).setMaxWidth(0);
        }

        lbNit5.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbNit5.setText("Anexos:");

        lbDireccion34.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion34.setText("Peso (Kg):");

        txtPeso1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtPeso1.setName("FR"); // NOI18N
        txtPeso1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPeso1ActionPerformed(evt);
            }
        });
        txtPeso1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPeso1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPeso1KeyTyped(evt);
            }
        });

        lbDireccion35.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion35.setText("Presión Arterial: (mmHg:)");

        txtPa1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtPa1.setName("Tº"); // NOI18N
        txtPa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPa1ActionPerformed(evt);
            }
        });
        txtPa1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPa1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPa1KeyTyped(evt);
            }
        });

        lbDireccion36.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion36.setText("Tempº:");

        txtT1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtT1.setName("FC"); // NOI18N
        txtT1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtT1ActionPerformed(evt);
            }
        });
        txtT1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtT1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtT1KeyTyped(evt);
            }
        });

        lbDireccion37.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion37.setText("Frec. Respiratoria:");

        txtFr2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtFr2.setName("TLLC"); // NOI18N
        txtFr2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFr2ActionPerformed(evt);
            }
        });
        txtFr2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFr2KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFr2KeyTyped(evt);
            }
        });

        txtDescripcionExamenEvolucion.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        txtDescripcionExamenEvolucion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescripcionExamenEvolucionKeyReleased(evt);
            }
        });

        btnAgregarExamenEvolucion.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnAgregarExamenEvolucion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/agregar2.png"))); // NOI18N
        btnAgregarExamenEvolucion.setText("Agregar");
        btnAgregarExamenEvolucion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarExamenEvolucionActionPerformed(evt);
            }
        });

        btnSubirImagen2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSubirImagen2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/subir imagen.png"))); // NOI18N
        btnSubirImagen2.setText("Seleccionar Archivo");
        btnSubirImagen2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubirImagen2ActionPerformed(evt);
            }
        });

        btnGuardarSeguimiento.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardarSeguimiento.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnGuardarSeguimiento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardarSeguimiento.setText("GUARDAR EVOLUCIÓN");
        btnGuardarSeguimiento.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarSeguimiento.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardarSeguimiento.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnGuardarSeguimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarSeguimientoActionPerformed(evt);
            }
        });

        lbNit7.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbNit7.setText("Descripción:");

        txtNombreAnexoEvolucion.setEditable(false);
        txtNombreAnexoEvolucion.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N

        lbNit8.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbNit8.setText("Nombre del archivo:");

        btnBuscPlantilla27.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla27.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscPlantilla27.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/listadoPequeño.png"))); // NOI18N
        btnBuscPlantilla27.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        btnBuscPlantilla27.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscPlantilla27.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscPlantilla27.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnBuscPlantilla27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscPlantilla27ActionPerformed(evt);
            }
        });

        btnBuscPlantilla28.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla28.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscPlantilla28.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/listadoPequeño.png"))); // NOI18N
        btnBuscPlantilla28.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        btnBuscPlantilla28.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscPlantilla28.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscPlantilla28.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnBuscPlantilla28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscPlantilla28ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jScrollPane9)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lbImagenes, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(82, 82, 82))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbDireccion35, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                            .addComponent(lbDireccion37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPa1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFr2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(lbDireccion34, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPeso1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(lbDireccion36, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtT1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 458, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbNit4, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbNit5, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(txtProfesional, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(btnSubirImagen2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbNit8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNombreAnexoEvolucion, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE))
                            .addComponent(txtDescripcionExamenEvolucion, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAgregarExamenEvolucion, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbEmail22, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                                    .addComponent(lbNit7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(10, 10, 10)
                                .addComponent(btnBuscPlantilla27, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane21))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(lbEmail23)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnBuscPlantilla28, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane22)))
                        .addGap(11, 11, 11))))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGuardarSeguimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNit4)
                    .addComponent(txtProfesional, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbNit8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtNombreAnexoEvolucion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnSubirImagen2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNit5, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDescripcionExamenEvolucion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbNit7)
                        .addComponent(btnAgregarExamenEvolucion)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane21, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                    .addComponent(btnBuscPlantilla27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbEmail22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(31, 31, 31)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDireccion37, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFr2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbDireccion34, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPeso1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDireccion35, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPa1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbDireccion36, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(txtT1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 53, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane22, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                    .addComponent(btnBuscPlantilla28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbEmail23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardarSeguimiento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
                .addComponent(lbImagenes)
                .addGap(0, 0, 0))
        );

        tabPrincipal.addTab("Evolución", jPanel9);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnAyudaDiagnostica.setBackground(new java.awt.Color(255, 255, 255));
        btnAyudaDiagnostica.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        btnAyudaDiagnostica.setText("Ayuda diagnostica");
        btnAyudaDiagnostica.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAyudaDiagnostica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAyudaDiagnosticaActionPerformed(evt);
            }
        });

        btnFormulaMedica.setBackground(new java.awt.Color(255, 255, 255));
        btnFormulaMedica.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        btnFormulaMedica.setText("Formula medica");
        btnFormulaMedica.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFormulaMedica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFormulaMedicaActionPerformed(evt);
            }
        });

        btnIncapacidad.setBackground(new java.awt.Color(255, 255, 255));
        btnIncapacidad.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        btnIncapacidad.setText("Incapacidad");
        btnIncapacidad.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnIncapacidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncapacidadActionPerformed(evt);
            }
        });

        btnContraremision.setBackground(new java.awt.Color(255, 255, 255));
        btnContraremision.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        btnContraremision.setText("Contraremisión");
        btnContraremision.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnContraremision.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContraremisionActionPerformed(evt);
            }
        });

        btnRemision.setBackground(new java.awt.Color(255, 255, 255));
        btnRemision.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        btnRemision.setText("Remisión");
        btnRemision.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRemision.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemisionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAyudaDiagnostica)
                .addGap(5, 5, 5)
                .addComponent(btnFormulaMedica)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnIncapacidad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnContraremision, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRemision, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnRemision, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnFormulaMedica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnIncapacidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAyudaDiagnostica, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnContraremision, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlFormularioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tabPrincipal, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(tabPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 993, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(180, 180, 180))
        );

        scrFormulario.setViewportView(pnlFormulario);

        pnlMenu.setBackground(new java.awt.Color(255, 255, 255));

        lista.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lista.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Hoy" };
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

        lbLista.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbLista.setText("Consultas:");

        lbFechaEvolucion.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbFechaEvolucion.setText("Evoluciones:");

        cmbFecha.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Hoy" }));
        cmbFecha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbFechaMouseClicked(evt);
            }
        });
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

        javax.swing.GroupLayout pnlMenuLayout = new javax.swing.GroupLayout(pnlMenu);
        pnlMenu.setLayout(pnlMenuLayout);
        pnlMenuLayout.setHorizontalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbFechaEvolucion, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                    .addComponent(lbLista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane8)
                    .addComponent(cmbFecha, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlMenuLayout.setVerticalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lbLista)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbFechaEvolucion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(373, 373, 373))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrFormulario, javax.swing.GroupLayout.DEFAULT_SIZE, 1065, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrFormulario, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(pnlMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
//    private String datosExamenes(JTable tabla) {
//        String aux = "";
//        for (int i = 0; i < tabla.getRowCount(); i++) {
//            aux += tabla.getValueAt(i, 0) + "&";
//        }
//        return aux;
//    }
//
//    private void cargarDatosExamen(JTable tabla, String dato) {
//        String aux[] = dato.split("&");
//        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
//        for (int i = 0; i < aux.length; i++) {
//            modelo.addRow(new String[]{aux[i]});
//        }
//    }

    private void btnGuardarConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarConsultaActionPerformed
        if (cmbTipoDiagnostico.getSelectedIndex() == 0) {
            metodos.msgError(this, "Seleccionar tipo de diagnostico");
            return;
        }

        if (cmbTipoDiagnostico.getSelectedIndex() == 0) {
            metodos.msgError(this, "Seleccionar tipo de diagnostico");
            return;
        }

        int i = 0;
        if (instancias.isGeneraOrdenMedica()) {
            if (orden == null && i == 0) {
                metodos.msgAdvertenciaAjustado(this, "No se relaciona con ninguna orden");
                return;
            }
        }

        nodoTercero = instancias.getSql().getDatosTercero(this.paciente);
        if (null == nodoTercero.getEps() || "".equals(nodoTercero.getEps())) {
            metodos.msgAdvertenciaAjustado(this, "El paciente no tiene EPS");
            return;
        }

        if (!instancias.isGeneraOrdenMedica()) {
            if (null == nodoCampos.getServicio() || "".equals(nodoCampos.getServicio())) {
                metodos.msgAdvertenciaAjustado(this, "No tiene un servicio establecido");
                return;
            }
        }

        ndEpsPrecargados nodoEps = instancias.getSql().getDatosEpsPrecargados(nodoTercero.getEps());
        String consecutivo = nodoEps.getConsecutivo();

        try {
            String numeroConse = instancias.getSql().getNumConsecutivoEps(consecutivo)[0].toString();
            consecutivo = consecutivo + "-" + numeroConse;
        } catch (Exception e) {
            metodos.msgError(this, "La EPS no tiene consecutivo");
            return;
        }

        if (metodos.msgPregunta(this, "¿Desea continuar?") == 0) {

            if (lista.getSelectedIndex() == 0) {

                if (!instancias.isGeneraOrdenMedica()) {
                    guardarOrden(consecutivo);
                }

                String consulta = instancias.getSql().getNumConsecutivo("CONSULTA")[0].toString();

                String examen = "";
                for (int j = 0; j < tblExamenes.getRowCount(); j++) {
                    examen = examen + "" + String.valueOf(tblExamenes.getValueAt(j, 0)) + "<&" + String.valueOf(tblExamenes.getValueAt(j, 1))
                            + "<&" + String.valueOf(tblExamenes.getValueAt(j, 2)) + "<>";
                }

                if (!examen.equals("")) {
                    examen = examen.substring(0, examen.length() - 2);
                }

                String partos;
                if (chkCirujano.isSelected()) {
                    partos = txtPartos1.getText();
                } else {
                    partos = txtPartos.getText();
                }

                String extremidades;
                if (chkCirujano.isSelected()) {
                    extremidades = txtExtremidades.getText();
                } else {
                    extremidades = txtExtremidades1.getText();
                }

                consecutivo = consulta;
                Object[] vector = {consulta, historia.getId(), txtResponsable.getText(), txtNombre.getText(), cmbParentesco.getSelectedItem(), txtMotivo.getText(),
                    txtT.getText(), txtPeso.getText(), txtEstatura.getText(), txtFrecCardiaca.getText(), txtFrecRespiratoria.getText(), txtPa.getText(), txtTiene.getText(),
                    txtPa2.getText(), txtImc.getText(), "", "", txtEnfermedadActual.getText(), txtCie1.getText(), txtCie2.getText(), txtCie3.getText(),
                    txtCie4.getText(), txtTratamiento.getText(), txtRiesgosRecomendaciones.getText(), txtCup.getText(), instancias.getUsuario(),
                    metodos.fechaConsulta(metodosGenerales.fecha()),
                    txtRevision.getText(), txtCabezaCuello.getText(), txtCardioPulmonar.getText(), txtAbdomen.getText(), txtDigestivo.getText(), txtUrinario.getText(), orden,
                    txtOido.getText(), txtMusculoEsqueletico.getText(), txtNeurologico.getText(), txtPiel.getText(),
                    txtFamiliar.getText(), txtFumador.getText(), txtIngestaAlcohol.getText(), txtAlergias.getText(),
                    txtToxicos.getText(), txtMedicamentos.getText(), txtPatologicos.getText(),
                    txtPsiquiatricos.getText(), txtQuirurgicos.getText(),
                    txtUltimaMenstruacion.getText(), txtUltimaCitologia.getText(), txtResultadoCitologia.getText(), txtEmbarazos.getText(), txtAbortos.getText(),
                    partos, txtCesareas.getText(), txtHijosVivos.getText(), txtPlanificacion.getText(), txtCiclo.getText(), examen,
                    txtTelefonoResponsable.getText(), txtEctopicos.getText(), txtCup1.getText(), txtObservacionProcedimientos.getText(), txtOido.getText(), txtVista.getText(),
                    txtNariz.getText(), txtGusto.getText(), txtTacto.getText(), txtMenarca.getText(),
                    txtDiagnosticoEspecializado.getText(), txtNutricionales.getText(), cmbTipoDiagnostico.getSelectedItem(), txtCC.getText(),
                    txtTorax.getText(), txtAbdomen1.getText(), extremidades, txtNeurologico1.getText(), txtPielYFaneras.getText(),
                    txtRevisionxSistemas.getText(), txtHistorial.getText(), txtTraumaticos.getText(), txtGluteos.getText(), txtMamas.getText()
                };

                ndConsultaClinica nodo = metodos.llenarConsultaClinica(vector); //txtExtremidades
                ndOrdenServicio nodo2 = instancias.getSql().getDatosOrdenServicio(orden);

                nodo2.setCie(txtCie1.getText());
                nodo2.setCup(txtCup.getText());

                if (!instancias.getSql().modificarOrdenServicio(nodo2)) {
                    metodos.msgError(this, "Hubo un problema al modificar la orden");
                    return;
                }

                if (!instancias.getSql().agregarConsultaClinica(nodo)) {
                    metodos.msgError(this, "Hubo un problema al guardar la Consulta");
                    return;
                }
                if (!instancias.getSql().aumentarConsecutivo("CONSULTA", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("CONSULTA")[0]) + 1)) {
                    metodos.msgError(this, "Hubo un problema al guardar en el consecutivo de la consulta");
                }

                i++;
            }

            if (facturarOrden) {
                facturar(productoFacturarOrden);
            }

            if (i > 0) {
                metodos.msgExito(this, "Historia guardada con exito");

                seguimientoEditable(true);
                consultaEditable(true);
                instancias.getHistoriaC().actualizar(consecutivo, txtCup.getText(), txtProcedimiento.getText());
                setPaciente(paciente);
                orden = null;
                lista.setSelectedIndex(1);

                if (instancias.getConfiguraciones().isAgenda()) {
                    try {
                        instancias.getAgendaConsulta().setSelected(true);
                    } catch (PropertyVetoException ex) {
                        Logger.getLogger(infHistoriaMedica.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }

            facturarOrden = false;
            productoFacturarOrden = "";
        }
    }//GEN-LAST:event_btnGuardarConsultaActionPerformed

    private void txtObservacionesSegKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtObservacionesSegKeyReleased
//        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
//            btnGuardarSeguimiento.requestFocus();
//        }
    }//GEN-LAST:event_txtObservacionesSegKeyReleased

    private void txtEvolucionSegKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEvolucionSegKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtFr2.requestFocus();
        }
    }//GEN-LAST:event_txtEvolucionSegKeyReleased

    private void txtProfesionalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProfesionalKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProfesionalKeyTyped

    private void txtProfesionalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProfesionalKeyReleased

    }//GEN-LAST:event_txtProfesionalKeyReleased

    private void txtRiesgosRecomendacionesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRiesgosRecomendacionesKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtProcedimiento.requestFocus();
        }
    }//GEN-LAST:event_txtRiesgosRecomendacionesKeyReleased

    private void txtTratamientoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTratamientoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtRiesgosRecomendaciones.requestFocus();
        }
    }//GEN-LAST:event_txtTratamientoKeyReleased

    private void txtMotivoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMotivoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtEnfermedadActual.requestFocus();
        }
    }//GEN-LAST:event_txtMotivoKeyReleased
    public void setCie(JTextField campo, String codigo) {
        try {
            if (campo == txtDiagnostico1) {
                if (validarCie(codigo)) {
                    txtCie1.setText(codigo);
                    txtCie2.requestFocus();
                    txtDiagnostico1.setText(instancias.getSql().getNombreCie(codigo));
                }
            }
            if (campo == txtDiagnostico2) {
                if (validarCie(codigo)) {
                    txtCie2.setText(codigo);
                    txtCie3.requestFocus();
                    txtDiagnostico2.setText(instancias.getSql().getNombreCie(codigo));
                }
            }
            if (campo == txtDiagnostico3) {
                if (validarCie(codigo)) {
                    txtCie3.setText(codigo);
                    txtCie4.requestFocus();
                    txtDiagnostico3.setText(instancias.getSql().getNombreCie(codigo));
                }
            }
            if (campo == txtDiagnostico4) {
                if (validarCie(codigo)) {
                    txtCie4.setText(codigo);
                    txtRiesgosRecomendaciones.requestFocus();
                    txtDiagnostico4.setText(instancias.getSql().getNombreCie(codigo));
                }
            }
        } catch (Exception e) {
            campo.setText("");
            ventanaCies(codigo, campo);
        }
    }

    public boolean validarCie(String codigo) {

        Object[][] datosCie = instancias.getSql().getDatosCie(codigo);
        String genero = datosCie[0][2].toString();
        String edadMin = datosCie[0][3].toString();
        String edadMax = datosCie[0][4].toString();
        boolean cumpleMinimo = true;
        boolean cumpleMaximo = true;

        System.out.println("Genero Cie " + genero);
        if (genero.equals("H") && !(txtGenero.getText().equals("Masculino") || txtGenero.getText().equals("masculino") || txtGenero.getText().equals("MASCULINO"))) {
            metodos.msgError(this, "Este Cie es de genero masculino");
            return false;
        } else if (genero.equals("M") && !(txtGenero.getText().equals("Femenino") || txtGenero.getText().equals("femenino") || txtGenero.getText().equals("FEMENINO"))) {
            metodos.msgError(this, "Este Cie es de genero femenino");
            return false;
        }

        if (!edadMin.equals("0")) {
            System.out.println("Entro 1 ");
            if (!edadMin.equals("000")) {
                int undMedidaCie = Integer.parseInt(edadMin.substring(0, 1));
                int medidaCie = Integer.parseInt(edadMin.substring(1));
                System.out.println("Entro 2 Und " + undMedidaCie);
                System.out.println("Entro 2 Cie " + medidaCie);
                String edadPaciente = txtEdad.getText();

                int medidaEdad = 0;
                int edad = Integer.parseInt(edadPaciente.split("-")[0]);

                if (edadPaciente.contains("Días")) {
                    medidaEdad = 2;
                } else if (edadPaciente.contains("Meses")) {
                    medidaEdad = 3;
                } else if (edadPaciente.contains("Años")) {
                    medidaEdad = 4;
                }
                System.out.println("Entro 3 " + medidaEdad);

                if (undMedidaCie == 2 && medidaEdad >= 2) {
                    System.out.println("Entro 4 ");
                    if (medidaEdad == 2) {
                        System.out.println("Entro 4.1 ");
                        cumpleMinimo = edad >= medidaCie;
                    } else {
                        System.out.println("Entro 4.2 ");
                        cumpleMinimo = true;
                    }
                } else if (undMedidaCie == 3 && medidaEdad >= 3) {
                    System.out.println("Entro 5 ");
                    if (medidaEdad == 3) {
                        System.out.println("Entro 5.1 ");
                        cumpleMinimo = edad >= medidaCie;
                    } else {
                        System.out.println("Entro 5.2 ");
                        cumpleMinimo = true;
                    }
                } else if (undMedidaCie == 4 && medidaEdad >= 4) {
                    System.out.println("Entro 6 ");
                    if (medidaEdad == 4) {
                        System.out.println("Entro 6.1 ");
                        cumpleMinimo = edad >= medidaCie;
                    } else {
                        System.out.println("Entro 6.2 ");
                        cumpleMinimo = true;
                    }
                } else {
                    cumpleMinimo = false;
                }
            }

            if (!edadMax.equals("599")) {
                int undMedidaCie = Integer.parseInt(edadMax.substring(0, 1));
                int medidaCie = Integer.parseInt(edadMax.substring(1));
                System.out.println("Entro 7 Und " + undMedidaCie);
                System.out.println("Entro 7 Cie " + medidaCie);

                String edadPaciente = txtEdad.getText();
                int medidaEdad = 0;
                int edad = Integer.parseInt(edadPaciente.split("-")[0]);

                if (edadPaciente.contains("Días")) {
                    medidaEdad = 2;
                } else if (edadPaciente.contains("Meses")) {
                    medidaEdad = 3;
                } else if (edadPaciente.contains("Años")) {
                    medidaEdad = 4;
                }
                System.out.println("Entro 8 " + medidaEdad);
                if (undMedidaCie == 2 && medidaEdad >= 2) {
                    System.out.println("Entro 9 ");
                    if (medidaEdad == 2) {
                        System.out.println("Entro 9.1 ");
                        cumpleMaximo = edad >= medidaCie;
                    } else {
                        System.out.println("Entro 9.2 ");
                        cumpleMaximo = true;
                    }
                } else if (undMedidaCie == 3 && medidaEdad >= 3) {
                    System.out.println("Entro 10 ");
                    if (medidaEdad == 3) {
                        System.out.println("Entro 10.1 ");
                        cumpleMaximo = edad >= medidaCie;
                    } else {
                        System.out.println("Entro 10.2 ");
                        cumpleMaximo = true;
                    }
                } else if (undMedidaCie == 4 && medidaEdad >= 4) {
                    System.out.println("Entro 11 ");
                    if (medidaEdad == 4) {
                        System.out.println("Entro 11.1 ");
                        cumpleMaximo = edad >= medidaCie;
                    } else {
                        System.out.println("Entro 11.2 ");
                        cumpleMaximo = true;
                    }
                } else {
                    cumpleMaximo = false;
                }
            }
        }

        if (cumpleMaximo && cumpleMinimo) {
            System.out.println("Si Cumple");
            return true;
        } else {
            if (!cumpleMaximo) {
                metodos.msgError(this, "Sobrepasa la edad maxima permitida.");
                return false;
            } else {
                metodos.msgError(this, "No cumple con la edad minima requerida.");
                return false;
            }
        }

    }
    private void txtCie1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCie1KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                setCie(txtDiagnostico1, txtCie1.getText());
            } catch (Exception e) {
                txtDiagnostico1.setText("");
            }
        } else {
            txtDiagnostico1.setText("");
        }

    }//GEN-LAST:event_txtCie1KeyReleased

    private void txtDiagnostico1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiagnostico1KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ventanaCies(txtDiagnostico1.getText(), txtDiagnostico1);
        } else {
            txtCie1.setText("");
        }
    }//GEN-LAST:event_txtDiagnostico1KeyReleased

    private void txtDiagnostico2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiagnostico2KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ventanaCies(txtDiagnostico2.getText(), txtDiagnostico2);
        } else {
            txtCie2.setText("");
        }
    }//GEN-LAST:event_txtDiagnostico2KeyReleased

    private void txtCie2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCie2KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                setCie(txtDiagnostico2, txtCie2.getText());
            } catch (Exception e) {
                txtDiagnostico2.setText("");
            }
        } else {
            txtDiagnostico2.setText("");
        }
    }//GEN-LAST:event_txtCie2KeyReleased

    private void txtDiagnostico3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiagnostico3KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ventanaCies(txtDiagnostico3.getText(), txtDiagnostico3);
        } else {
            txtCie3.setText("");
        }
    }//GEN-LAST:event_txtDiagnostico3KeyReleased

    private void txtCie3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCie3KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                setCie(txtDiagnostico3, txtCie3.getText());
            } catch (Exception e) {
                txtDiagnostico3.setText("");
            }
        } else {
            txtDiagnostico3.setText("");
        }
    }//GEN-LAST:event_txtCie3KeyReleased

    private void txtDiagnostico4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiagnostico4KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ventanaCies(txtDiagnostico4.getText(), txtDiagnostico4);
        } else {
            txtCie4.setText("");
        }
    }//GEN-LAST:event_txtDiagnostico4KeyReleased

    private void txtCie4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCie4KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                setCie(txtDiagnostico4, txtCie4.getText());
            } catch (Exception e) {
                txtDiagnostico4.setText("");
            }
        } else {
            txtDiagnostico4.setText("");
        }


    }//GEN-LAST:event_txtCie4KeyReleased

    private void txtCupKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCupKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                txtProcedimiento.setText(instancias.getSql().getNombreCup(txtCup.getText()));
                txtProcedimiento1.requestFocus();
            } catch (Exception e) {
                txtProcedimiento.setText("");
                ventanaCups(txtCup.getText(), txtCup);
            }
        }
    }//GEN-LAST:event_txtCupKeyReleased

    private void txtProcedimientoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProcedimientoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ventanaCups(txtProcedimiento.getText(), txtCup);
        } else {
            txtCup.setText("");
        }
    }//GEN-LAST:event_txtProcedimientoKeyReleased

    private void txtPartosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPartosKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtCesareas.requestFocus();
        }
    }//GEN-LAST:event_txtPartosKeyReleased

    private void txtPartosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPartosKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtPartosKeyTyped

    private void txtEmbarazosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmbarazosKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtAbortos.requestFocus();
        }
    }//GEN-LAST:event_txtEmbarazosKeyReleased

    private void txtEmbarazosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmbarazosKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtEmbarazosKeyTyped

    private void txtCesareasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCesareasKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtHijosVivos.requestFocus();
        }
    }//GEN-LAST:event_txtCesareasKeyReleased

    private void txtCesareasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCesareasKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtCesareasKeyTyped

    private void txtAbortosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAbortosKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtEctopicos.requestFocus();
        }
    }//GEN-LAST:event_txtAbortosKeyReleased

    private void txtAbortosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAbortosKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtAbortosKeyTyped
    private void regresarHistoriaC() {
        String orden;
        try {
            orden = lista.getSelectedValue().toString();
        } catch (Exception e) {
            orden = "";
        }
        if (lista.getSelectedIndex() == 0) {
            orden = "";
        }
        instancias.getHistoriaC().actualizar2(orden);
    }
    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        limpiarAntecedentes(true);
        regresarHistoriaC();
    }//GEN-LAST:event_jLabel9MouseClicked

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        limpiarAntecedentes(true);
        regresarHistoriaC();
    }//GEN-LAST:event_jLabel11MouseClicked

    private void txtEnfermedadActualKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEnfermedadActualKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtFamiliar.requestFocus();
        }
    }//GEN-LAST:event_txtEnfermedadActualKeyReleased

    private void txtHijosVivosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHijosVivosKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtRevision.requestFocus();
        }
    }//GEN-LAST:event_txtHijosVivosKeyReleased

    private void txtHijosVivosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHijosVivosKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHijosVivosKeyTyped

    private void txtUrinarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUrinarioKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtMusculoEsqueletico.requestFocus();
        }
    }//GEN-LAST:event_txtUrinarioKeyReleased

    private void btnAyudaDiagnosticaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAyudaDiagnosticaActionPerformed
        if (lista.getSelectedIndex() == 0) {
            metodos.msgError(this, "Debe asociar una consulta");
            return;
        }

        instancias.getAyudaDiagnostica().nuevaAyuda(historia);
        instancias.getMenu().cambiarTitulo("AYUDA DIAGNOSTICA");
    }//GEN-LAST:event_btnAyudaDiagnosticaActionPerformed

    private void btnFormulaMedicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFormulaMedicaActionPerformed
        if (lista.getSelectedIndex() == 0) {
            metodos.msgError(this, "Debe asociar una consulta");
            return;
        }

        String frecCardiaca, presionArterial, peso, frecRespiratoria, temperatura, estatura;

        if (cmbFecha.getItemCount() > 1) {

            Object[][] fechas = instancias.getSql().getSeguimientosClinica(lista.getSelectedValue().toString().split(" - ")[0]);
            int mayor = 0;
            for (int i = 0; i < fechas.length; i++) {
                if (Integer.parseInt(fechas[i][0].toString()) > mayor) {
                    mayor = Integer.parseInt(fechas[i][0].toString());
                }
            }

            ndSeguimientoClinica nodo = instancias.getSql().getDatosSeguimientoClinica(String.valueOf(mayor));

            presionArterial = nodo.getFr();
            peso = nodo.getPeso();
            frecRespiratoria = nodo.getFr();
            temperatura = nodo.getT();
            frecCardiaca = txtFrecCardiaca.getText();
            estatura = txtEstatura.getText();
        } else {
            frecCardiaca = txtFrecCardiaca.getText();
            presionArterial = txtPa.getText() + "/" + txtPa2.getText();
            peso = txtPeso.getText();
            frecRespiratoria = txtFrecRespiratoria.getText();
            temperatura = txtT.getText();
            estatura = txtEstatura.getText();
        }

        instancias.getFormulaMedica().nuevaFormula(historia, frecCardiaca, presionArterial, peso, frecRespiratoria, temperatura, estatura);
        instancias.getMenu().cambiarTitulo("FORMULA MEDICA");
    }//GEN-LAST:event_btnFormulaMedicaActionPerformed

    private void btnIncapacidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncapacidadActionPerformed
        if (lista.getSelectedIndex() == 0) {
            metodos.msgError(this, "Debe asociar una consulta");
            return;
        }

        instancias.getIncapacidad().nuevaIncapacidad(historia, txtCup.getText(), txtProcedimiento.getText(), txtCie1.getText(), txtDiagnostico1.getText());
        instancias.getMenu().cambiarTitulo("INCAPACIDAD");
    }//GEN-LAST:event_btnIncapacidadActionPerformed

    private void txtImcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtImcActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtImcActionPerformed

    private void txtImcKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtImcKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtCabezaCuello.requestFocus();
            try {
                Thread.sleep(100);
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_txtImcKeyReleased

    private void txtImcKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtImcKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtImcKeyTyped

    private void txtFrecRespiratoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFrecRespiratoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFrecRespiratoriaActionPerformed

    private void txtFrecRespiratoriaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFrecRespiratoriaKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtPa.requestFocus();
        }
    }//GEN-LAST:event_txtFrecRespiratoriaKeyReleased

    private void txtFrecRespiratoriaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFrecRespiratoriaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFrecRespiratoriaKeyTyped

    private void txtTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTActionPerformed

    private void txtTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtPeso.requestFocus();
        }
    }//GEN-LAST:event_txtTKeyReleased

    private void txtTKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTKeyTyped

    private void txtPesoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPesoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesoActionPerformed

    private void txtPesoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_ENTER) {
                try {

                    double x = (Double.parseDouble(txtPeso.getText()) / (((Double.parseDouble(txtEstatura.getText()) / 100)) * (Double.parseDouble(txtEstatura.getText()) / 100)));
                    double imc = Math.round(x * 100.0) / 100.0;

                    txtImc.setText(imc + "");

                    if (imc < 16.00) {
                        txtTiene.setText("Delgadez Severa");
                    } else if (imc < 17.00) {
                        txtTiene.setText("Delgadez moderada");
                    } else if (imc < 18.50) {
                        txtTiene.setText("Delgadez aceptable");
                    } else if (imc < 25.00) {
                        txtTiene.setText("Peso Normal");
                    } else if (imc < 30.00) {
                        txtTiene.setText("Sobrepeso");
                    } else if (imc < 35.00) {
                        txtTiene.setText("Obeso: Tipo I");
                    } else if (imc < 40.01) {
                        txtTiene.setText("Obeso: Tipo II");
                    } else {
                        txtTiene.setText("Obeso: Tipo III");
                    }

                } catch (Exception e) {
                }
                txtEstatura.requestFocus();
            }
        }
    }//GEN-LAST:event_txtPesoKeyReleased

    private void txtPesoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesoKeyTyped

    private void txtPaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPaActionPerformed

    private void txtPaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPaKeyReleased
        if (txtPa.getText().equals("")) {
            txtPa.setText("0");
        }
        int numero = Integer.parseInt(txtPa.getText());
        if (numero > 300) {
            txtPa.setText("300");
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtPa2.requestFocus();
        }

    }//GEN-LAST:event_txtPaKeyReleased

    private void txtPaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPaKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtPaKeyTyped

    private void txtEstaturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEstaturaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEstaturaActionPerformed

    private void txtEstaturaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEstaturaKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {

                double x = (Double.parseDouble(txtPeso.getText()) / (((Double.parseDouble(txtEstatura.getText()) / 100)) * (Double.parseDouble(txtEstatura.getText()) / 100)));
                double imc = Math.round(x * 100.0) / 100.0;

                txtImc.setText(imc + "");

                if (imc < 16.00) {
                    txtTiene.setText("Delgadez Severa");
                } else if (imc < 17.00) {
                    txtTiene.setText("Delgadez moderada");
                } else if (imc < 18.50) {
                    txtTiene.setText("Delgadez aceptable");
                } else if (imc < 25.00) {
                    txtTiene.setText("Peso Normal");
                } else if (imc < 30.00) {
                    txtTiene.setText("Sobrepeso");
                } else if (imc < 35.00) {
                    txtTiene.setText("Obeso: Tipo I");
                } else if (imc < 40.01) {
                    txtTiene.setText("Obeso: Tipo II");
                } else {
                    txtTiene.setText("Obeso: Tipo III");
                }

            } catch (Exception e) {
            }
            txtCabezaCuello.requestFocus();
        }
    }//GEN-LAST:event_txtEstaturaKeyReleased

    private void txtEstaturaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEstaturaKeyTyped

    }//GEN-LAST:event_txtEstaturaKeyTyped

    private void txtPa2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPa2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPa2ActionPerformed

    private void txtPa2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPa2KeyReleased
        if (txtPa2.getText().equals("")) {
            txtPa2.setText("0");
        }
        int numero = Integer.parseInt(txtPa2.getText());
        int numero2 = Integer.parseInt(txtPa.getText());
        if (numero > numero2) {
            txtPa2.setText(numero2 - 1 + "");
        }

        if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_ENTER)) {
            txtT.requestFocus();
        }


    }//GEN-LAST:event_txtPa2KeyReleased

    private void txtPa2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPa2KeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtPa2KeyTyped

    private void txtFrecCardiacaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFrecCardiacaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFrecCardiacaActionPerformed

    private void txtFrecCardiacaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFrecCardiacaKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtFrecRespiratoria.requestFocus();
        }
    }//GEN-LAST:event_txtFrecCardiacaKeyReleased

    private void txtFrecCardiacaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFrecCardiacaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFrecCardiacaKeyTyped

    private void btnReimprimirConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReimprimirConsultaActionPerformed
        if (lista.getSelectedIndex() == 0) {
            metodos.msgError(this, "Seleccione una consulta");
            return;
        }
        ModeloContacto Paciente = instancias.getSql().getDatosTercero(paciente);

        if (!txtCC.getText().equals("") || !txtAbdomen1.getText().equals("") || !txtPartos1.getText().equals("") || !txtTorax.getText().equals("") || !txtCC.getText().equals("") || !txtNeurologico1.getText().equals("")) {
            instancias.getReporte().verRemision1(lista.getSelectedValue().toString().split(" - ")[0], "" + metodosGenerales.calcularEdad2(metodos.fecha(Paciente.getNacimiento())));
        } else {
            instancias.getReporte().verRemision(lista.getSelectedValue().toString().split(" - ")[0], "" + metodosGenerales.calcularEdad2(metodos.fecha(Paciente.getNacimiento())));
        }
    }//GEN-LAST:event_btnReimprimirConsultaActionPerformed

    private void txtCabezaCuelloKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCabezaCuelloKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtVista.requestFocus();
        }
    }//GEN-LAST:event_txtCabezaCuelloKeyReleased

    private void txtCardioPulmonarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCardioPulmonarKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtAbdomen.requestFocus();
        }
    }//GEN-LAST:event_txtCardioPulmonarKeyReleased

    private void txtAbdomenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAbdomenKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtDigestivo.requestFocus();
        }
    }//GEN-LAST:event_txtAbdomenKeyReleased

    private void txtDigestivoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDigestivoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtUrinario.requestFocus();
        }
    }//GEN-LAST:event_txtDigestivoKeyReleased

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtTelefonoResponsable.requestFocus();
        }
    }//GEN-LAST:event_txtNombreKeyReleased

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtResponsableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtResponsableActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtResponsableActionPerformed

    private void txtResponsableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtResponsableKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtNombre.requestFocus();
        }
    }//GEN-LAST:event_txtResponsableKeyReleased

    private void txtResponsableKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtResponsableKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtResponsableKeyTyped

    private void cmbParentescoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbParentescoKeyReleased

    }//GEN-LAST:event_cmbParentescoKeyReleased

    private void txtResultadoCitologiaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtResultadoCitologiaKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtPlanificacion.requestFocus();
        }
    }//GEN-LAST:event_txtResultadoCitologiaKeyReleased

    private void txtCie2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCie2FocusGained

    }//GEN-LAST:event_txtCie2FocusGained

    private void txtCie3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCie3FocusGained

    }//GEN-LAST:event_txtCie3FocusGained

    private void txtCie4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCie4FocusGained

    }//GEN-LAST:event_txtCie4FocusGained

    private void txtPeso1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPeso1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPeso1ActionPerformed

    private void txtPeso1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPeso1KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtT1.requestFocus();
        }
    }//GEN-LAST:event_txtPeso1KeyReleased

    private void txtPeso1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPeso1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPeso1KeyTyped

    private void txtPa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPa1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPa1ActionPerformed

    private void txtPa1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPa1KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtPeso1.requestFocus();
        }
    }//GEN-LAST:event_txtPa1KeyReleased

    private void txtPa1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPa1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPa1KeyTyped

    private void txtT1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtT1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtT1ActionPerformed

    private void txtT1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtT1KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtObservacionesSeg.requestFocus();
        }
    }//GEN-LAST:event_txtT1KeyReleased

    private void txtT1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtT1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtT1KeyTyped

    private void txtFr2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFr2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFr2ActionPerformed

    private void txtFr2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFr2KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtPa1.requestFocus();
        }
    }//GEN-LAST:event_txtFr2KeyReleased

    private void txtFr2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFr2KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFr2KeyTyped

    private void txtMusculoEsqueleticoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMusculoEsqueleticoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtPiel.requestFocus();
        }
    }//GEN-LAST:event_txtMusculoEsqueleticoKeyReleased

    private void txtNeurologicoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNeurologicoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            cmbTipoDiagnostico.requestFocus();
        }
    }//GEN-LAST:event_txtNeurologicoKeyReleased

    private void txtPielKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPielKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtNeurologico.requestFocus();
        }
    }//GEN-LAST:event_txtPielKeyReleased

    private void txtFamiliarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFamiliarKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtFumador.requestFocus();
        }
    }//GEN-LAST:event_txtFamiliarKeyReleased

    private void txtRevisionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRevisionKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtFrecCardiaca.requestFocus();
        }
    }//GEN-LAST:event_txtRevisionKeyReleased

    private void btnHipertensionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHipertensionActionPerformed

        txtPatologicos.setText(txtPatologicos.getText() + "Hipertensión, ");
        btnHipertension.setEnabled(false);
    }//GEN-LAST:event_btnHipertensionActionPerformed

    private void btnDiabetesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDiabetesActionPerformed

        txtPatologicos.setText(txtPatologicos.getText() + "Diabetes, ");

        btnDiabetes.setEnabled(false);
    }//GEN-LAST:event_btnDiabetesActionPerformed

    private void btnMetabolicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMetabolicaActionPerformed

        txtPatologicos.setText(txtPatologicos.getText() + "Metabolica, ");

        btnMetabolica.setEnabled(false);

    }//GEN-LAST:event_btnMetabolicaActionPerformed

    private void btnSubirImagen2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubirImagen2ActionPerformed
//        try {
        JFileChooser chooser = new JFileChooser();
        ImagePreviewPanel preview = new ImagePreviewPanel();
        chooser.setAccessory(preview);
        chooser.addPropertyChangeListener(preview);

        int respuesta = chooser.showSaveDialog(this);

        if (respuesta == JFileChooser.APPROVE_OPTION) {
            FROM2 = Paths.get(chooser.getSelectedFile().getAbsolutePath());
        }

        txtNombreAnexoEvolucion.setText(chooser.getSelectedFile().getName());
//        } catch (Exception e) {
//        }


    }//GEN-LAST:event_btnSubirImagen2ActionPerformed

    private void btnAgregarExamenEvolucionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarExamenEvolucionActionPerformed
        String id = "";
        if (txtDescripcionExamenEvolucion.getText().equals("")) {
            metodos.msgError(this, "No ha ingresado ninguna descripción del anexo");
            return;
        }

        id = instancias.getSql().getNumConsecutivo("DOCIMPORTADO")[0] + "-" + txtNombreAnexoEvolucion.getText();

//        Path TO = Paths.get(System.getProperty("user.dir") + "\\imagenes\\medico\\IMG-" + id + "-" + lbNombre.getText() + ".jpg");
//        Path TO = Paths.get(System.getProperty("user.dir") + "\\imagenes\\medico\\IMG-" + id + ".jpg");
//        Path TO = Paths.get(System.getProperty("user.dir") + "\\imagenes\\medico\\IMG-" + id + "-" + lbNombre.getText());
//        Path TO = Paths.get(System.getProperty("user.dir") + "\\documentosImportados\\" + id);
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

        DefaultTableModel modeloTabla = (DefaultTableModel) tblExamenesEvolucion.getModel();

        modeloTabla.addRow(new Object[]{id, txtDescripcionExamenEvolucion.getText(), TO.toString()});
        txtDescripcionExamenEvolucion.setText("");
        txtNombreAnexoEvolucion.setText("");
        FROM2 = null;


    }//GEN-LAST:event_btnAgregarExamenEvolucionActionPerformed

    private void tblExamenesEvolucionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblExamenesEvolucionMouseClicked

        if (evt.getClickCount() > 1) {
            //ruta del archivo en el pc
            String file = tblExamenesEvolucion.getValueAt(tblExamenesEvolucion.getSelectedRow(), 2).toString();
            try {
                //definiendo la ruta en la propiedad file

//                File path = new File(file);
//                Desktop.getDesktop().open(path);
                Runtime.getRuntime().exec("rundll32 SHELL32.DLL,ShellExec_RunDLL " + file);
            } catch (IOException e) {

            }
        }

    }//GEN-LAST:event_tblExamenesEvolucionMouseClicked

    private void popBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popBorrarActionPerformed

        if (tblExamenesEvolucion.getSelectedRow() > -1) {
            int fila = tblExamenesEvolucion.getSelectedRow();
            String codigo = (String) tblExamenesEvolucion.getValueAt(fila, 2);

            File fichero = new File(codigo);
            if (fichero.delete()) {
                DefaultTableModel modelo = (DefaultTableModel) tblExamenesEvolucion.getModel();
                modelo.removeRow(fila);
            } else {
                metodos.msgError(this, "Hubo un problema al borrar el archivo adjunto");
            }

        } else {
            metodos.msgAdvertencia(this, "Seleccione una fila");
        }
    }//GEN-LAST:event_popBorrarActionPerformed

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

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
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

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        String id = "";
        if (txtDescripcionExamenConsulta.getText().equals("")) {
            metodos.msgError(this, "No ha ingresado ninguna descripción");
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
            metodos.msgError(this, "Error al cargar el archivo");
            return;
        }

        if (!instancias.getSql().aumentarConsecutivo("DOCIMPORTADO", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("DOCIMPORTADO")[0]) + 1)) {
            metodos.msgError(this, "Error al guardar el codigo de la imagen");
        }

        DefaultTableModel modeloTabla = (DefaultTableModel) tblExamenes.getModel();

        modeloTabla.addRow(new Object[]{id, txtDescripcionExamenConsulta.getText(), TO.toString(), false});

        txtDescripcionExamenConsulta.setText("");
        txtNombreAnexoConsulta.setText("");
        FROM2 = null;


    }//GEN-LAST:event_jButton7ActionPerformed

    private void popBorrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popBorrar1ActionPerformed
        if (tblExamenes.getSelectedRow() > -1) {
            int fila = tblExamenes.getSelectedRow();
            String codigo = (String) tblExamenes.getValueAt(fila, 2);

            File fichero = new File(codigo);
            if (fichero.delete()) {
                DefaultTableModel modelo = (DefaultTableModel) tblExamenes.getModel();
                modelo.removeRow(fila);
            } else {
                metodos.msgError(this, "Hubo un problema al borrar el archivo adjunto");
            }
        } else {
            metodos.msgAdvertencia(this, "Seleccione un producto");
        }
    }//GEN-LAST:event_popBorrar1ActionPerformed

    private void txtTieneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTieneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTieneActionPerformed

    private void txtTieneKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTieneKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTieneKeyReleased

    private void txtTieneKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTieneKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTieneKeyTyped

    private void txtDescripcionExamenConsultaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionExamenConsultaKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jButton7ActionPerformed(null);
        }
    }//GEN-LAST:event_txtDescripcionExamenConsultaKeyReleased

    private void txtDescripcionExamenEvolucionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionExamenEvolucionKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
//            jButton1ActionPerformed(null);
            btnAgregarExamenEvolucionActionPerformed(null);
        }
    }//GEN-LAST:event_txtDescripcionExamenEvolucionKeyReleased

    private void txtTelefonoResponsableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoResponsableActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoResponsableActionPerformed

    private void txtTelefonoResponsableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoResponsableKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            cmbParentesco.requestFocus();
        }
    }//GEN-LAST:event_txtTelefonoResponsableKeyReleased

    private void txtTelefonoResponsableKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoResponsableKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoResponsableKeyTyped

    private void txtEctopicosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEctopicosKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtPartos.requestFocus();
        }
    }//GEN-LAST:event_txtEctopicosKeyReleased

    private void txtEctopicosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEctopicosKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEctopicosKeyTyped

    private void txtPartosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPartosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPartosActionPerformed

    private void txtCup1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCup1KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                txtProcedimiento1.setText(instancias.getSql().getNombreCup(txtCup1.getText()));
                txtObservacionProcedimientos.requestFocus();
            } catch (Exception e) {
                txtProcedimiento1.setText("");
                ventanaCups(txtCup1.getText(), txtCup1);
            }
        }
    }//GEN-LAST:event_txtCup1KeyReleased

    private void txtProcedimiento1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProcedimiento1KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ventanaCups(txtProcedimiento1.getText(), txtCup1);

        } else {
            txtCup1.setText("");
        }

    }//GEN-LAST:event_txtProcedimiento1KeyReleased

    private void txtObservacionProcedimientosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtObservacionProcedimientosKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
//            txtExamenes.requestFocus();
            txtDescripcionExamenConsulta.requestFocusInWindow();
        }
    }//GEN-LAST:event_txtObservacionProcedimientosKeyReleased

    private void txtOidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOidoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtNariz.requestFocus();
        }
    }//GEN-LAST:event_txtOidoKeyReleased

    private void txtVistaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVistaKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtOido.requestFocus();
        }
    }//GEN-LAST:event_txtVistaKeyReleased

    private void txtNarizKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNarizKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtGusto.requestFocus();
        }
    }//GEN-LAST:event_txtNarizKeyReleased

    private void txtGustoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGustoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtTacto.requestFocus();
        }
    }//GEN-LAST:event_txtGustoKeyReleased

    private void txtTactoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTactoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtCardioPulmonar.requestFocus();
        }
    }//GEN-LAST:event_txtTactoKeyReleased

    private void btnGuardarSeguimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarSeguimientoActionPerformed
//            int i = 0;
        if ((!txtEvolucionSeg.getText().equals("") || !txtObservacionesSeg.getText().equals("")) && cmbFecha.getSelectedIndex() == 0) {

            if (metodos.msgPregunta(this, "¿Desea continuar?") == 0) {
                Object[][] fechas = instancias.getSql().getConsultasClinica(historia.getId());
                String consulta = fechas[fechas.length - 1][0].toString();

                if (lista.getSelectedIndex() != 0) {
                    consulta = lista.getSelectedValue().toString().split(" - ")[0];
                }

                String examenId = "";
                String examenDesc = "";
                String examenUrl = "";
                for (int j = 0; j < tblExamenesEvolucion.getRowCount(); j++) {
                    examenId = examenId + "" + String.valueOf(tblExamenesEvolucion.getValueAt(j, 0)) + "<>";
                    examenDesc = examenDesc + "" + String.valueOf(tblExamenesEvolucion.getValueAt(j, 1)) + "<>";
                    examenUrl = examenUrl + "" + String.valueOf(tblExamenesEvolucion.getValueAt(j, 2)) + "<>";
                }

                if (!examenId.equals("")) {
                    examenId = examenId.substring(0, examenId.length() - 2);
                    examenDesc = examenDesc.substring(0, examenDesc.length() - 2);
                    examenUrl = examenUrl.substring(0, examenUrl.length() - 2);
                }

                Object[] vector2 = {"", metodos.fechaConsulta(metodosGenerales.fecha()), instancias.getUsuarioLog().getUsuario(),
                    txtEvolucionSeg.getText(), txtObservacionesSeg.getText(),
                    consulta, examenId, examenDesc, examenUrl, txtT1.getText(), txtPeso1.getText(), txtPa1.getText(), txtFr2.getText()};

                ndSeguimientoClinica nodoSeguimiento = metodos.llenarSeguimientoClinica(vector2);

                if (!instancias.getSql().agregarSeguimientoClinica(nodoSeguimiento)) {
                    metodos.msgError(this, "Hubo un problema al guardar el seguimiento");
                    return;
                }
                metodos.msgExito(this, "Evolución guardada con exito");

                Object[][] fechasCombo = instancias.getSql().getSeguimientosClinica(lista.getSelectedValue().toString().split(" - ")[0]);
                Object[] datos = new Object[fechasCombo.length + 1];
                datos[0] = "HOY - NUEVO";
                int j = fechasCombo.length;

                for (int i = 0; i < fechasCombo.length; i++) {
                    datos[j--] = fechasCombo[i][0] + " - " + metodos.fecha(fechasCombo[i][1].toString());
                }

                cmbFecha.setModel(new javax.swing.DefaultComboBoxModel(datos));
                cmbFecha.setSelectedIndex(0);
//                i++;

            }
//
//            String consecutivo = "";
//
//            if (i > 0) {
//                metodos.msgExito(this, "Evolución guardada con exito");
//                seguimientoEditable(true);
//                consultaEditable(true);
//                instancias.getHistoriaC().actualizar(consecutivo, txtCup.getText(), txtProcedimiento.getText());
//              
//            }

        }
    }//GEN-LAST:event_btnGuardarSeguimientoActionPerformed

    private void btnBuscPlantilla4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscPlantilla4ActionPerformed
        ventanaPlantillas(txtOido, txtOido.getText());
    }//GEN-LAST:event_btnBuscPlantilla4ActionPerformed

    private void btnBuscPlantilla3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscPlantilla3ActionPerformed
        ventanaPlantillas(txtVista, txtVista.getText());
    }//GEN-LAST:event_btnBuscPlantilla3ActionPerformed

    private void btnBuscPlantilla5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscPlantilla5ActionPerformed
        ventanaPlantillas(txtNariz, txtNariz.getText());
    }//GEN-LAST:event_btnBuscPlantilla5ActionPerformed

    private void btnBuscPlantilla6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscPlantilla6ActionPerformed
        ventanaPlantillas(txtGusto, txtGusto.getText());
    }//GEN-LAST:event_btnBuscPlantilla6ActionPerformed

    private void btnBuscPlantilla7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscPlantilla7ActionPerformed
        ventanaPlantillas(txtTacto, txtTacto.getText());
    }//GEN-LAST:event_btnBuscPlantilla7ActionPerformed

    private void btnBuscPlantilla1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscPlantilla1ActionPerformed
        ventanaPlantillas(txtCabezaCuello, txtCabezaCuello.getText());
    }//GEN-LAST:event_btnBuscPlantilla1ActionPerformed

    private void btnBuscPlantilla8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscPlantilla8ActionPerformed
        ventanaPlantillas(txtCardioPulmonar, txtCardioPulmonar.getText());
    }//GEN-LAST:event_btnBuscPlantilla8ActionPerformed

    private void btnBuscPlantilla9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscPlantilla9ActionPerformed
        ventanaPlantillas(txtAbdomen, txtAbdomen.getText());
    }//GEN-LAST:event_btnBuscPlantilla9ActionPerformed

    private void btnBuscPlantilla10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscPlantilla10ActionPerformed
        ventanaPlantillas(txtDigestivo, txtDigestivo.getText());
    }//GEN-LAST:event_btnBuscPlantilla10ActionPerformed

    private void btnBuscPlantilla11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscPlantilla11ActionPerformed
        ventanaPlantillas(txtUrinario, txtUrinario.getText());
    }//GEN-LAST:event_btnBuscPlantilla11ActionPerformed

    private void btnBuscPlantilla12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscPlantilla12ActionPerformed
        ventanaPlantillas(txtMusculoEsqueletico, txtMusculoEsqueletico.getText());
    }//GEN-LAST:event_btnBuscPlantilla12ActionPerformed

    private void btnBuscPlantilla2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscPlantilla2ActionPerformed
        ventanaPlantillas(txtNeurologico, txtNeurologico.getText());
    }//GEN-LAST:event_btnBuscPlantilla2ActionPerformed

    private void btnBuscPlantilla13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscPlantilla13ActionPerformed
        ventanaPlantillas(txtPiel, txtPiel.getText());
    }//GEN-LAST:event_btnBuscPlantilla13ActionPerformed

    private void txtPielKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPielKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPielKeyTyped

    private void btnBuscCie1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscCie1ActionPerformed
        ventanaCies(txtDiagnostico1.getText(), txtDiagnostico1);
    }//GEN-LAST:event_btnBuscCie1ActionPerformed

    private void btnBuscCie2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscCie2ActionPerformed
        ventanaCies(txtDiagnostico2.getText(), txtDiagnostico2);
    }//GEN-LAST:event_btnBuscCie2ActionPerformed

    private void btnBuscCie3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscCie3ActionPerformed
        ventanaCies(txtDiagnostico3.getText(), txtDiagnostico3);
    }//GEN-LAST:event_btnBuscCie3ActionPerformed

    private void btnBuscCie4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscCie4ActionPerformed
        ventanaCies(txtDiagnostico4.getText(), txtDiagnostico4);
    }//GEN-LAST:event_btnBuscCie4ActionPerformed

    private void btnBuscCup1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscCup1ActionPerformed
        ventanaCups(txtProcedimiento.getText(), txtCup);
    }//GEN-LAST:event_btnBuscCup1ActionPerformed

    private void btnBuscCup2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscCup2ActionPerformed
        ventanaCups(txtProcedimiento1.getText(), txtCup1);
    }//GEN-LAST:event_btnBuscCup2ActionPerformed

    private void tblExamenesEvolucionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblExamenesEvolucionMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tblExamenesEvolucionMouseEntered

    private void txtPatologicosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPatologicosKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtPsiquiatricos.requestFocus();
        }
    }//GEN-LAST:event_txtPatologicosKeyReleased

    private void txtPsiquiatricosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPsiquiatricosKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtQuirurgicos.requestFocus();
        }
    }//GEN-LAST:event_txtPsiquiatricosKeyReleased

    private void txtQuirurgicosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuirurgicosKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtResultadoCitologia.requestFocus();
        }
    }//GEN-LAST:event_txtQuirurgicosKeyReleased

    private void txtToxicosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtToxicosKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtMedicamentos.requestFocus();
        }
    }//GEN-LAST:event_txtToxicosKeyReleased

    private void txtAlergiasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAlergiasKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtToxicos.requestFocus();
        }
    }//GEN-LAST:event_txtAlergiasKeyReleased

    private void txtIngestaAlcoholKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIngestaAlcoholKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtAlergias.requestFocus();
        }
    }//GEN-LAST:event_txtIngestaAlcoholKeyReleased

    private void txtFumadorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFumadorKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtIngestaAlcohol.requestFocus();
        }
    }//GEN-LAST:event_txtFumadorKeyReleased

    private void txtMedicamentosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMedicamentosKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtPatologicos.requestFocus();
        }
    }//GEN-LAST:event_txtMedicamentosKeyReleased

    private void txtPlanificacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlanificacionKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtEmbarazos.requestFocus();
        }
    }//GEN-LAST:event_txtPlanificacionKeyReleased

    private void listaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaMouseClicked
//        pnlTitulo1.scrollRectToVisible(pnlTitulo1.getVisibleRect());
    }//GEN-LAST:event_listaMouseClicked

    private void listaValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listaValueChanged
        try {
            if (lista.getSelectedValue().toString().equalsIgnoreCase("HOY - NUEVO")) {
                tabPrincipal.setEnabledAt(5, false);
                tabPrincipal.setSelectedIndex(0);
                consultaEditable(true);
                seguimientoEditable(false);
                btnGuardarConsulta.setEnabled(true);
                btnGuardarSeguimiento.setEnabled(false);
                cargarArchivos();
            } else {
                tabPrincipal.setEnabledAt(5, true);
                consultaEditable(false);
                seguimientoEditable(true);
                btnGuardarConsulta.setEnabled(false);
                btnGuardarSeguimiento.setEnabled(true);
                ndConsultaClinica nodo = instancias.getSql().getDatosConsultaClinica(lista.getSelectedValue().toString().split(" - ")[0]);
                if (nodo.getId() != null) {

                    txtResponsable.setText(nodo.getResponsable());
                    txtNombre.setText(nodo.getNombre());
                    cmbParentesco.setSelectedItem(nodo.getParentesco());
                    txtMotivo.setText(nodo.getMotivo());
                    txtT.setText(nodo.getT());
                    txtPeso.setText(nodo.getPeso());
                    txtEstatura.setText(nodo.getTalla());
                    txtFrecCardiaca.setText(nodo.getFc());
                    txtFrecRespiratoria.setText(nodo.getFr());
                    txtPa.setText(nodo.getPa());
                    txtTiene.setText(nodo.getCefalico());
                    txtPa2.setText(nodo.getAbdominal());
                    txtImc.setText(nodo.getFisico1());

                    txtCabezaCuello.setText(nodo.getCabezaCuello());
                    txtCardioPulmonar.setText(nodo.getCardioPulmonar());
                    txtAbdomen.setText(nodo.getAbdomen());
                    txtDigestivo.setText(nodo.getPelvis());
                    txtUrinario.setText(nodo.getExtremidades());
                    txtOido.setText(nodo.getOrganosSentidos());
                    txtMusculoEsqueletico.setText(nodo.getMusculoEsqueletico());
                    txtNeurologico.setText(nodo.getNeurologico());
                    txtPiel.setText(nodo.getPielAnexos());

                    txtGluteos.setText(nodo.getGluteos());
                    txtMamas.setText(nodo.getMamas());

                    txtEnfermedadActual.setText(nodo.getProblemas());
                    txtCie1.setText(nodo.getCie1());
                    txtCie2.setText(nodo.getCie2());
                    txtCie3.setText(nodo.getCie3());
                    txtCie4.setText(nodo.getCie4());

                    txtTratamiento.setText(nodo.getTratamiento());
                    txtRiesgosRecomendaciones.setText(nodo.getRecomendaciones());
                    txtCup.setText(nodo.getCup());
                    txtRevision.setText(nodo.getRevision());
                    txtCiclo.setText(nodo.getFotos());

                    try {
                        txtDiagnostico1.setText(instancias.getSql().getNombreCie(txtCie1.getText()));
                    } catch (NullPointerException e) {
                    }

                    try {
                        txtDiagnostico2.setText(instancias.getSql().getNombreCie(txtCie2.getText()));
                    } catch (NullPointerException e) {
                    }

                    try {
                        txtDiagnostico3.setText(instancias.getSql().getNombreCie(txtCie3.getText()));
                    } catch (NullPointerException e) {
                    }

                    try {
                        txtDiagnostico4.setText(instancias.getSql().getNombreCie(txtCie4.getText()));
                    } catch (NullPointerException e) {
                    }

                    try {
                        txtProcedimiento.setText(instancias.getSql().getNombreCup(txtCup.getText()));
                    } catch (NullPointerException e) {
                    }

                    txtFamiliar.setText(nodo.getFamiliar());
                    txtFumador.setText(nodo.getFumador());
                    txtIngestaAlcohol.setText(nodo.getIngesta());
                    txtAlergias.setText(nodo.getAlergias());
                    txtToxicos.setText(nodo.getToxicos());
                    txtMedicamentos.setText(nodo.getMedicamentos());
                    txtPatologicos.setText(nodo.getPatologicos());
                    txtNutricionales.setText(nodo.getNutricionales());
                    cmbTipoDiagnostico.setSelectedItem(nodo.getTipoDiagnostico());

                    if (txtPatologicos.getText().contains("Hipertensión")) {
                        btnHipertension.setEnabled(false);
                    }
                    if (txtPatologicos.getText().contains("Diabetes")) {
                        btnDiabetes.setEnabled(false);
                    }
                    if (txtPatologicos.getText().contains("Metabolica")) {
                        btnMetabolica.setEnabled(false);
                    }
                    txtPsiquiatricos.setText(nodo.getPsiquiatricos());
                    txtQuirurgicos.setText(nodo.getQuirurgicos());
                    //                    try {
                    //                        txtUltimaMenstruacion.setText(nodo.getUltimaMenstruacion());
                    //                    } catch (Exception e) {
                    //                    }

                    try {
                        //                        txtUltimaCitologia.setText(nodo.getUltimaCitologia());
                        txtUltimaCitologia.setText(nodo.getUltimaCitologia());
                    } catch (Exception e) {
                    }

                    try {
                        txtUltimaMenstruacion.setText(nodo.getUltimaMenstruacion());
                    } catch (Exception e) {
                    }

                    try {
                        txtMenarca.setText(nodo.getMenarca());
                    } catch (Exception e) {
                    }

                    txtResultadoCitologia.setText(nodo.getResultadosCitologia());
                    txtEmbarazos.setText(nodo.getEmbarazos());
                    txtAbortos.setText(nodo.getAbortos());
                    txtPartos.setText(nodo.getPartos());
                    txtCesareas.setText(nodo.getCesarias());
                    txtHijosVivos.setText(nodo.getHijosVivos());

                    txtPlanificacion.setText(nodo.getPlanificacion());

                    txtTelefonoResponsable.setText(nodo.getTelefonoResponsable());
                    txtEctopicos.setText(nodo.getEctopicos());
                    txtCup1.setText(nodo.getCup2());
                    try {
                        txtProcedimiento1.setText(instancias.getSql().getNombreCup(txtCup1.getText()));
                    } catch (NullPointerException e) {
                    }

                    txtObservacionProcedimientos.setText(nodo.getObservacionProcedimientos());
                    txtOido.setText(nodo.getOido());
                    txtVista.setText(nodo.getVista());
                    txtNariz.setText(nodo.getNariz());
                    txtGusto.setText(nodo.getGusto());
                    txtTacto.setText(nodo.getTacto());
//                    txtDiagnosticoEspecializado.setText(nodo.getDiagnosticoEspecializado());
                    cmbTipoDiagnostico.setEnabled(false);
                    txtFamiliar.setEditable(false);
                    txtFumador.setEditable(false);
                    txtIngestaAlcohol.setEditable(false);
                    txtAlergias.setEditable(false);
                    txtToxicos.setEditable(false);
                    txtMedicamentos.setEditable(false);
                    txtPatologicos.setEditable(false);
                    txtPsiquiatricos.setEditable(false);
                    txtPlanificacion.setEditable(false);

                    txtCC.setText(nodo.getCc());
                    txtTorax.setText(nodo.getTorax());
                    txtAbdomen1.setText(nodo.getAbdomen1());
                    txtNeurologico1.setText(nodo.getNeurologico1());

                    txtExtremidades.setText(nodo.getExtremidades1());
                    txtExtremidades1.setText(nodo.getExtremidades1());
                    txtPielYFaneras.setText(nodo.getPielYfaneras());

                    try {
                        if (!nodo.getExamenes().equals("")) {
                            String[] examen = nodo.getExamenes().split("<>");
                            DefaultTableModel modeloTabla = (DefaultTableModel) tblExamenes.getModel();
                            for (int i = 0; i < examen.length; i++) {
                                modeloTabla.addRow(new Object[]{examen[i].split("<&")[0], examen[i].split("<&")[1], examen[i].split("<&")[2], true});
                            }
                        }
                    } catch (Exception e) {
                    }

                    Object[][] fechas = instancias.getSql().getSeguimientosClinica(lista.getSelectedValue().toString().split(" - ")[0]);
                    Object[] datos = new Object[fechas.length + 1];
                    datos[0] = "HOY - NUEVO";
                    int j = fechas.length;

                    for (int i = 0; i < fechas.length; i++) {
                        datos[j--] = fechas[i][0] + " - " + metodos.fecha(fechas[i][1].toString());
                    }

                    cmbFecha.setModel(new javax.swing.DefaultComboBoxModel(datos));
                    cmbFechaItemStateChanged(null);

                }
            }
        } catch (Exception e) {
            lista.setSelectedIndex(0);
            consultaEditable(true);
            seguimientoEditable(false);
        }
    }//GEN-LAST:event_listaValueChanged

    private void cmbFechaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbFechaMouseClicked
//        if (evt.getClickCount() == 2 && !(lista.getSelectedValue().toString().equalsIgnoreCase("HOY - NUEVO"))) {
//            pnlFinalParaFocus1.scrollRectToVisible(pnlFinalParaFocus1.getVisibleRect());
//        }
    }//GEN-LAST:event_cmbFechaMouseClicked

    private void cmbFechaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbFechaItemStateChanged
        try {
            if (cmbFecha.getSelectedItem().equals("HOY - NUEVO")) {
                seguimientoEditable(true);
                //                jPanel9.scrollRectToVisible(jPanel9.getVisibleRect());
            } else {
                seguimientoEditable(false);
//                pnlFinalParaFocus1.scrollRectToVisible(pnlFinalParaFocus1.getVisibleRect());
                ndSeguimientoClinica nodo = instancias.getSql().getDatosSeguimientoClinica(cmbFecha.getSelectedItem().toString().split(" - ")[0]);

                txtProfesional.setText(instancias.getSql().getNombreEmpleadoUsuario(nodo.getUsuario()));
                txtEvolucionSeg.setText(nodo.getEvolucion());
                txtT1.setText(nodo.getT());
                txtPeso1.setText(nodo.getPeso());
                txtPa1.setText(nodo.getPa());
                txtFr2.setText(nodo.getFr());
                txtObservacionesSeg.setText(nodo.getObservaciones());

                try {
                    if (!nodo.getExamenesId().equals("")) {
                        String[] examenesId = nodo.getExamenesId().split("<>");
                        String[] examenesDesc = nodo.getExamenesDesc().split("<>");
                        String[] examenesUrl = nodo.getExamenesUrl().split("<>");
                        DefaultTableModel modeloTabla = (DefaultTableModel) tblExamenesEvolucion.getModel();
                        for (int i = 0; i < examenesId.length; i++) {
                            modeloTabla.addRow(new Object[]{examenesId[i], examenesDesc[i], examenesUrl[i]});
                        }
                    }
                } catch (Exception e) {
                }

            }
        } catch (NullPointerException e) {
            seguimientoEditable(true);
        }
    }//GEN-LAST:event_cmbFechaItemStateChanged

    private void cmbFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbFechaActionPerformed

    }//GEN-LAST:event_cmbFechaActionPerformed

    private void lbNitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbNitKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtId.requestFocus();
        }
    }//GEN-LAST:event_lbNitKeyReleased

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdActionPerformed

    private void txtIdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyReleased

    }//GEN-LAST:event_txtIdKeyReleased

    private void txtFechaNacimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaNacimientoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaNacimientoActionPerformed

    private void txtEdadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEdadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEdadActionPerformed

    private void txtNombreCompletoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreCompletoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreCompletoActionPerformed

    private void btnGuardarArchivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarArchivosActionPerformed
        if (lista.getSelectedIndex() == 0) {
            if (metodos.msgPregunta(this, "¿Desea continuar?") == 0) {
                for (int i = 0; i < tblExamenes.getRowCount(); i++) {
                    if (tblExamenes.getValueAt(i, 3).equals(false)) {
                        if (!instancias.getSql().agregarArchivosAdjuntos(historia.getId(), metodos.fechaConsulta(metodosGenerales.fecha()),
                                tblExamenes.getValueAt(i, 0).toString(), tblExamenes.getValueAt(i, 1).toString(), tblExamenes.getValueAt(i, 2).toString())) {
                            metodos.msgError(this, "Hubo un error al guardar los archivos.");
                            return;
                        }
                    }
                }
                metodos.msgExito(this, "Archivos guardados con exito");
                btnGuardarArchivos.setBackground(new java.awt.Color(255, 102, 102));
            }
        } else {
            if (metodos.msgPregunta(this, "¿Desea continuar?") == 0) {
                String examen = "";
                for (int j = 0; j < tblExamenes.getRowCount(); j++) {
                    examen = examen + "" + String.valueOf(tblExamenes.getValueAt(j, 0)) + "<&" + String.valueOf(tblExamenes.getValueAt(j, 1)) + "<&" + String.valueOf(tblExamenes.getValueAt(j, 2)) + "<>";
                }
                if (!examen.equals("")) {
                    examen = examen.substring(0, examen.length() - 2);
                }

                if (!instancias.getSql().modificarExamenesConsultaMedica(lista.getSelectedValue().toString().split(" - ")[0], examen)) {
                    metodos.msgError(this, "Hubo un problema al cargar los archivos.");
                    return;
                }
                metodos.msgExito(this, "Archivos guardados con exito");
                btnGuardarArchivos.setBackground(new java.awt.Color(255, 102, 102));
            }
        }
    }//GEN-LAST:event_btnGuardarArchivosActionPerformed

    private void txtDiagnosticoEspecializadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiagnosticoEspecializadoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtRiesgosRecomendaciones.requestFocus();
        }
    }//GEN-LAST:event_txtDiagnosticoEspecializadoKeyReleased

    private void txtNutricionalesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNutricionalesKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNutricionalesKeyReleased

    private void btnBuscPlantilla14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscPlantilla14ActionPerformed
        ventanaPlantillas(txtFamiliar, txtFamiliar.getText());
    }//GEN-LAST:event_btnBuscPlantilla14ActionPerformed

    private void btnBuscPlantilla15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscPlantilla15ActionPerformed
        ventanaPlantillas(txtFumador, txtFumador.getText());
    }//GEN-LAST:event_btnBuscPlantilla15ActionPerformed

    private void btnBuscPlantilla16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscPlantilla16ActionPerformed
        ventanaPlantillas(txtIngestaAlcohol, txtIngestaAlcohol.getText());
    }//GEN-LAST:event_btnBuscPlantilla16ActionPerformed

    private void btnBuscPlantilla17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscPlantilla17ActionPerformed
        ventanaPlantillas(txtAlergias, txtAlergias.getText());
    }//GEN-LAST:event_btnBuscPlantilla17ActionPerformed

    private void btnBuscPlantilla18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscPlantilla18ActionPerformed
        ventanaPlantillas(txtToxicos, txtToxicos.getText());
    }//GEN-LAST:event_btnBuscPlantilla18ActionPerformed

    private void btnBuscPlantilla19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscPlantilla19ActionPerformed
        ventanaPlantillas(txtMedicamentos, txtMedicamentos.getText());
    }//GEN-LAST:event_btnBuscPlantilla19ActionPerformed

    private void btnBuscPlantilla23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscPlantilla23ActionPerformed
        ventanaPlantillas(txtPatologicos, txtPatologicos.getText());
    }//GEN-LAST:event_btnBuscPlantilla23ActionPerformed

    private void btnBuscPlantilla20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscPlantilla20ActionPerformed
        ventanaPlantillas(txtPsiquiatricos, txtPsiquiatricos.getText());
    }//GEN-LAST:event_btnBuscPlantilla20ActionPerformed

    private void btnBuscPlantilla21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscPlantilla21ActionPerformed
        ventanaPlantillas(txtQuirurgicos, txtQuirurgicos.getText());
    }//GEN-LAST:event_btnBuscPlantilla21ActionPerformed

    private void btnBuscPlantilla22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscPlantilla22ActionPerformed
        ventanaPlantillas(txtNutricionales, txtNutricionales.getText());
    }//GEN-LAST:event_btnBuscPlantilla22ActionPerformed

    private void btnBuscPlantilla24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscPlantilla24ActionPerformed
        ventanaPlantillas(txtDiagnosticoEspecializado, txtDiagnosticoEspecializado.getText());
    }//GEN-LAST:event_btnBuscPlantilla24ActionPerformed

    private void btnBuscPlantilla25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscPlantilla25ActionPerformed
        ventanaPlantillas(txtRiesgosRecomendaciones, txtRiesgosRecomendaciones.getText());
    }//GEN-LAST:event_btnBuscPlantilla25ActionPerformed

    private void btnBuscPlantilla26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscPlantilla26ActionPerformed
        ventanaPlantillas(txtObservacionProcedimientos, txtObservacionProcedimientos.getText());
    }//GEN-LAST:event_btnBuscPlantilla26ActionPerformed

    private void btnBuscPlantilla27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscPlantilla27ActionPerformed
        ventanaPlantillas(txtEvolucionSeg, txtEvolucionSeg.getText());
    }//GEN-LAST:event_btnBuscPlantilla27ActionPerformed

    private void btnBuscPlantilla28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscPlantilla28ActionPerformed
        ventanaPlantillas(txtObservacionesSeg, txtObservacionesSeg.getText());
    }//GEN-LAST:event_btnBuscPlantilla28ActionPerformed

    private void txtGeneroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGeneroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGeneroActionPerformed

    private void txtCie2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCie2FocusLost
        if (txtDiagnostico2.getText().equals("")) {
            txtCie2.setText("");
        }
    }//GEN-LAST:event_txtCie2FocusLost

    private void txtCie1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCie1FocusLost
        if (txtDiagnostico1.getText().equals("")) {
            txtCie1.setText("");
        }
    }//GEN-LAST:event_txtCie1FocusLost

    private void txtCie3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCie3FocusLost
        if (txtDiagnostico3.getText().equals("")) {
            txtCie3.setText("");
        }
    }//GEN-LAST:event_txtCie3FocusLost

    private void txtCie4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCie4FocusLost
        if (txtDiagnostico4.getText().equals("")) {
            txtCie4.setText("");
        }
    }//GEN-LAST:event_txtCie4FocusLost

    private void txtMenarcaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMenarcaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMenarcaKeyReleased

    private void txtMenarcaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMenarcaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMenarcaKeyTyped

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        txtFamiliar.setText("NO");
        txtFamiliar.setEnabled(false);
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        txtFamiliar.setText("");
        txtFamiliar.setEnabled(true);
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox3ActionPerformed
        txtFumador.setText("NO");
        txtFumador.setEnabled(false);
    }//GEN-LAST:event_jCheckBox3ActionPerformed

    private void jCheckBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox4ActionPerformed
        txtFumador.setText("");
        txtFumador.setEnabled(true);
    }//GEN-LAST:event_jCheckBox4ActionPerformed

    private void jCheckBox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox6ActionPerformed
        txtIngestaAlcohol.setText("");
        txtIngestaAlcohol.setEnabled(true);
    }//GEN-LAST:event_jCheckBox6ActionPerformed

    private void jCheckBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox5ActionPerformed
        txtIngestaAlcohol.setText("NO");
        txtIngestaAlcohol.setEnabled(false);
    }//GEN-LAST:event_jCheckBox5ActionPerformed

    private void jCheckBox7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox7ActionPerformed
        txtAlergias.setText("NO");
        txtAlergias.setEnabled(false);
    }//GEN-LAST:event_jCheckBox7ActionPerformed

    private void jCheckBox8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox8ActionPerformed
        txtAlergias.setText("");
        txtAlergias.setEnabled(true);
    }//GEN-LAST:event_jCheckBox8ActionPerformed

    private void jCheckBox10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox10ActionPerformed
        txtToxicos.setText("");
        txtToxicos.setEnabled(true);
    }//GEN-LAST:event_jCheckBox10ActionPerformed

    private void jCheckBox9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox9ActionPerformed
        txtToxicos.setText("NO");
        txtToxicos.setEnabled(false);
    }//GEN-LAST:event_jCheckBox9ActionPerformed

    private void jCheckBox12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox12ActionPerformed
        txtMedicamentos.setText("");
        txtMedicamentos.setEnabled(true);
    }//GEN-LAST:event_jCheckBox12ActionPerformed

    private void jCheckBox11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox11ActionPerformed
        txtMedicamentos.setText("NO");
        txtMedicamentos.setEnabled(false);
    }//GEN-LAST:event_jCheckBox11ActionPerformed

    private void jCheckBox14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox14ActionPerformed
        txtPatologicos.setText("");
        txtPatologicos.setEnabled(true);
    }//GEN-LAST:event_jCheckBox14ActionPerformed

    private void jCheckBox13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox13ActionPerformed
        txtPatologicos.setText("NO");
        txtPatologicos.setEnabled(false);
    }//GEN-LAST:event_jCheckBox13ActionPerformed

    private void jCheckBox16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox16ActionPerformed
        txtPsiquiatricos.setText("");
        txtPsiquiatricos.setEnabled(true);
    }//GEN-LAST:event_jCheckBox16ActionPerformed

    private void jCheckBox15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox15ActionPerformed
        txtPsiquiatricos.setText("NO");
        txtPsiquiatricos.setEnabled(false);
    }//GEN-LAST:event_jCheckBox15ActionPerformed

    private void txtUltimaMenstruacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUltimaMenstruacionKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUltimaMenstruacionKeyReleased

    private void txtUltimaMenstruacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUltimaMenstruacionKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUltimaMenstruacionKeyTyped

    private void txtUltimaCitologiaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUltimaCitologiaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUltimaCitologiaKeyReleased

    private void txtUltimaCitologiaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUltimaCitologiaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUltimaCitologiaKeyTyped

    private void jCheckBox18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox18ActionPerformed
        txtQuirurgicos.setText("");
        txtQuirurgicos.setEnabled(true);
    }//GEN-LAST:event_jCheckBox18ActionPerformed

    private void jCheckBox17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox17ActionPerformed
        txtQuirurgicos.setText("NO");
        txtQuirurgicos.setEnabled(false);
    }//GEN-LAST:event_jCheckBox17ActionPerformed

    private void jCheckBox20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox20ActionPerformed
        txtNutricionales.setText("");
        txtNutricionales.setEnabled(true);
    }//GEN-LAST:event_jCheckBox20ActionPerformed

    private void jCheckBox19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox19ActionPerformed
        txtNutricionales.setText("NO");
        txtNutricionales.setEnabled(false);
    }//GEN-LAST:event_jCheckBox19ActionPerformed

    private void chkCirujanoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkCirujanoActionPerformed
        if (chkCirujano.isSelected()) {
            pnlCirujano.setVisible(true);
            pnlCirujano1.setVisible(true);
            pnlMedicoEmb.setVisible(false);
            pnlMedicoEmb1.setVisible(false);
            pnlMedico1.setVisible(false);
        } else {
            pnlCirujano.setVisible(false);
            pnlCirujano1.setVisible(false);
            pnlMedicoEmb.setVisible(true);
            pnlMedicoEmb1.setVisible(true);
            pnlMedico1.setVisible(true);
        }
    }//GEN-LAST:event_chkCirujanoActionPerformed

    private void chkMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkMedicoActionPerformed
        if (chkMedico.isSelected()) {
            pnlCirujano.setVisible(false);
            pnlCirujano1.setVisible(false);
            pnlMedicoEmb.setVisible(true);
            pnlMedicoEmb1.setVisible(true);
            pnlMedico1.setVisible(true);
        } else {
            pnlCirujano.setVisible(true);
            pnlCirujano1.setVisible(true);
            pnlMedicoEmb.setVisible(false);
            pnlMedicoEmb1.setVisible(false);
            pnlMedico1.setVisible(false);
        }
    }//GEN-LAST:event_chkMedicoActionPerformed

    private void btnBuscPlantilla30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscPlantilla30ActionPerformed
        ventanaPlantillas(txtCC, txtCC.getText());
    }//GEN-LAST:event_btnBuscPlantilla30ActionPerformed

    private void btnBuscPlantilla31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscPlantilla31ActionPerformed
        ventanaPlantillas(txtTorax, txtTorax.getText());
    }//GEN-LAST:event_btnBuscPlantilla31ActionPerformed

    private void btnBuscPlantilla32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscPlantilla32ActionPerformed
        ventanaPlantillas(txtAbdomen1, txtAbdomen1.getText());
    }//GEN-LAST:event_btnBuscPlantilla32ActionPerformed

    private void btnBuscPlantilla33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscPlantilla33ActionPerformed
        ventanaPlantillas(txtExtremidades, txtExtremidades.getText());
    }//GEN-LAST:event_btnBuscPlantilla33ActionPerformed

    private void btnBuscPlantilla34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscPlantilla34ActionPerformed
        ventanaPlantillas(txtNeurologico1, txtNeurologico1.getText());
    }//GEN-LAST:event_btnBuscPlantilla34ActionPerformed

    private void btnBuscPlantilla35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscPlantilla35ActionPerformed
        ventanaPlantillas(txtPielYFaneras, txtPielYFaneras.getText());
    }//GEN-LAST:event_btnBuscPlantilla35ActionPerformed

    private void txtPielYFanerasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPielYFanerasKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPielYFanerasKeyReleased

    private void txtNeurologico1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNeurologico1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNeurologico1KeyReleased

    private void txtExtremidadesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtExtremidadesKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtExtremidadesKeyReleased

    private void txtAbdomen1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAbdomen1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAbdomen1KeyReleased

    private void txtToraxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtToraxKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtToraxKeyReleased

    private void txtCCKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCCKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCCKeyReleased

    private void txtRevisionxSistemasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRevisionxSistemasKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRevisionxSistemasKeyReleased

    private void txtUltimaCitologiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUltimaCitologiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUltimaCitologiaActionPerformed

    private void btnRemisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemisionActionPerformed
        if (lista.getSelectedIndex() == 0) {
            metodos.msgError(this, "Debe asociar una consulta");
            return;
        }

        String id = txtId.getText();
        String cie1 = txtCie1.getText();
        String cie2 = txtCie2.getText();
        String nombre1 = txtDiagnostico1.getText();
        String nombre2 = txtDiagnostico2.getText();

        instancias.getRemision().nuevaRemision(id, cie1, cie2, nombre1, nombre2);
        instancias.getMenu().cambiarTitulo("REMISIÓN");
    }//GEN-LAST:event_btnRemisionActionPerformed

    private void btnBuscPlantilla29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscPlantilla29ActionPerformed
        ventanaPlantillas(txtTraumaticos, txtTraumaticos.getText());
    }//GEN-LAST:event_btnBuscPlantilla29ActionPerformed

    private void jCheckBox21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox21ActionPerformed
        txtTraumaticos.setText("");
        txtTraumaticos.setEnabled(true);
    }//GEN-LAST:event_jCheckBox21ActionPerformed

    private void jCheckBox22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox22ActionPerformed
        txtTraumaticos.setText("NO");
        txtTraumaticos.setEnabled(false);
    }//GEN-LAST:event_jCheckBox22ActionPerformed

    private void txtTraumaticosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTraumaticosKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTraumaticosKeyReleased

    private void btnBuscPlantilla36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscPlantilla36ActionPerformed
        ventanaPlantillas(txtHistorial, txtHistorial.getText());
    }//GEN-LAST:event_btnBuscPlantilla36ActionPerformed

    private void jCheckBox23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox23ActionPerformed
        txtHistorial.setText("");
        txtHistorial.setEnabled(true);
    }//GEN-LAST:event_jCheckBox23ActionPerformed

    private void jCheckBox24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox24ActionPerformed
        txtHistorial.setText("NO");
        txtHistorial.setEnabled(false);
    }//GEN-LAST:event_jCheckBox24ActionPerformed

    private void txtHistorialKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHistorialKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHistorialKeyReleased

    private void btnContraremisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContraremisionActionPerformed
        if (lista.getSelectedIndex() == 0) {
            metodos.msgError(this, "Debe asociar una consulta");
            return;
        }

        String id = txtId.getText();
        String cie1 = txtCie1.getText();
        String cie2 = txtCie2.getText();
        String nombre1 = txtDiagnostico1.getText();
        String nombre2 = txtDiagnostico2.getText();

        instancias.getContraremision().nuevaRemision(id, cie1, cie2, nombre1, nombre2);
        instancias.getMenu().cambiarTitulo("CONTRAREMISIÓN");
    }//GEN-LAST:event_btnContraremisionActionPerformed

    private void btnBuscPlantilla37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscPlantilla37ActionPerformed
        ventanaPlantillas(txtExtremidades1, txtExtremidades1.getText());
    }//GEN-LAST:event_btnBuscPlantilla37ActionPerformed

    private void txtExtremidades1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtExtremidades1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtExtremidades1KeyReleased

    private void txtCicloKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCicloKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCicloKeyReleased

    private void txtCicloKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCicloKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCicloKeyTyped

    private void txtPartos1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPartos1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPartos1KeyReleased

    private void btnBuscPlantilla38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscPlantilla38ActionPerformed
        ventanaPlantillas(txtGluteos, txtGluteos.getText());
    }//GEN-LAST:event_btnBuscPlantilla38ActionPerformed

    private void txtGluteosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGluteosKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGluteosKeyReleased

    private void btnBuscPlantilla39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscPlantilla39ActionPerformed
        ventanaPlantillas(txtMamas, txtMamas.getText());
    }//GEN-LAST:event_btnBuscPlantilla39ActionPerformed

    private void txtMamasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMamasKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMamasKeyReleased

    public void ventanaPlantillas(JTextArea campo, String texto) {
        buscPlantillaDeTextos buscar = new buscPlantillaDeTextos(null, rootPaneCheckingEnabled);
        buscar.setLocationRelativeTo(null);
        instancias.setBuscPlantillaDeTextos(buscar);
        instancias.setCampoActualTextArea(campo);
        campo.requestFocus();
//        buscar.setInstancia(instancias);
        buscar.noEncontrado(texto);
        buscar.show();
    }

    private void validarRadioSiNO(JRadioButton radio, JTextField texto) {
        if (radio.isSelected()) {
            texto.setEditable(true);
        } else {
            texto.setEditable(false);
        }

    }

    public void setPaciente(String paciente) {

        try {
            this.setSelected(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(infHistoriaMedica.class.getName()).log(Level.SEVERE, null, ex);
        }

        ModeloContacto Paciente = instancias.getSql().getDatosTercero(paciente);
        ndHistoriaClinica nodo = instancias.getSql().getDatosHistoriaClinica(Paciente.getIdSistema());

        historia = nodo;

        txtId.setText(paciente);
        txtNombreCompleto.setText(Paciente.getNombre());
        txtGenero.setText(Paciente.getSexo());

        if (txtGenero.getText().equals("Masculino")) {
            tabPrincipal.setEnabledAt(2, false);
        } else {
            tabPrincipal.setEnabledAt(2, true);
        }

        try {
            txtFechaNacimiento.setText(metodos.fecha(Paciente.getNacimiento()));
            txtEdad.setText(metodosGenerales.calcularEdad2(metodos.fecha(Paciente.getNacimiento())) + "");
        } catch (Exception e) {
            txtFechaNacimiento.setText("");
        }

//        txtPersonal.setText(historia.getHistoria());
//        txtUltimaMenstruacion.setText(historia.getMenstruacion());
//        txtUltimaCitologia.setText(historia.getCitologia());
//        txtResultadoCitologia.setText(historia.getResultadoCitologia());
//        txtEmbarazos.setText(historia.getEmbarazos());
//        txtPartos.setText(historia.getPartos());
//        txtAbortos.setText(historia.getAbortos());
//        txtCesareas.setText(historia.getCesareas());
//        txtHijosVivos.setText(historia.getHijosVivos());        
//        txtPersonal1.setText(historia.getPersonal());
//        txtFamiliar.setText(historia.getFamiliar());
        Object[][] fechas = instancias.getSql().getConsultasClinica(historia.getId());
        Object[] datos = new Object[fechas.length + 1];
        datos[0] = "HOY - NUEVO";
        int j = fechas.length;
        int id = 0;

        for (int i = 0; i < fechas.length; i++) {
            datos[j--] = fechas[i][0] + " - " + metodos.fecha(fechas[i][1].toString());
            if (Integer.parseInt(fechas[i][0].toString()) > id) {
                id = Integer.parseInt(fechas[i][0].toString());
            }
        }

        if (id != 0) {
            ndConsultaClinica nodoConsulta = instancias.getSql().getDatosConsultaClinica(Integer.toString(id));
            if (nodo.getId() != null) {
                txtFamiliar.setText(nodoConsulta.getFamiliar());
                txtFumador.setText(nodoConsulta.getFumador());
                txtIngestaAlcohol.setText(nodoConsulta.getIngesta());
                txtTraumaticos.setText(nodoConsulta.getTraumaticos());
                txtAlergias.setText(nodoConsulta.getAlergias());
                txtToxicos.setText(nodoConsulta.getToxicos());
                txtMedicamentos.setText(nodoConsulta.getMedicamentos());
                txtPatologicos.setText(nodoConsulta.getPatologicos());
                txtPsiquiatricos.setText(nodoConsulta.getPsiquiatricos());
                txtQuirurgicos.setText(nodoConsulta.getQuirurgicos());
                txtHistorial.setText(nodoConsulta.getHistorialPersonal());
                txtNutricionales.setText(nodoConsulta.getNutricionales());
//                txtUltimaMenstruacion.setText(nodoConsulta.getUltimaMenstruacion());
//                txtUltimaCitologia.setText(nodoConsulta.getUltimaCitologia());

                try {
                    txtUltimaCitologia.setText(nodoConsulta.getUltimaCitologia());
                } catch (Exception e) {
                }
                try {
                    txtUltimaMenstruacion.setText(nodoConsulta.getUltimaMenstruacion());
                } catch (Exception e) {
                }
                try {
                    txtMenarca.setText(nodoConsulta.getMenarca());
                } catch (Exception e) {
                }

                txtResultadoCitologia.setText(nodoConsulta.getResultadosCitologia());
                txtEmbarazos.setText(nodoConsulta.getEmbarazos());
                txtAbortos.setText(nodoConsulta.getAbortos());
                txtPartos.setText(nodoConsulta.getPartos());
                txtCesareas.setText(nodoConsulta.getCesarias());
                txtHijosVivos.setText(nodoConsulta.getHijosVivos());
                txtPlanificacion.setText(nodoConsulta.getPlanificacion());
                txtEctopicos.setText(nodoConsulta.getEctopicos());
            }
        }

        lista.setListData(datos);
        cmbFecha.addItem("HOY - NUEVO");
        lista.setSelectedIndex(0);
        consultaCargada = true;
//        cargarArchivos();

    }

    public void consultaEditable(boolean x) {

        txtDiagnosticoEspecializado.setEditable(x);
        cmbTipoDiagnostico.setEnabled(x);
        cmbTipoDiagnostico.setSelectedIndex(0);
        txtResponsable.setEditable(x);

        btnAyudaDiagnostica.setEnabled(!x);
        btnContraremision.setEnabled(!x);
        btnRemision.setEnabled(!x);
        btnFormulaMedica.setEnabled(!x);
        btnIncapacidad.setEnabled(!x);

        txtNombre.setEditable(x);
        txtCiclo.setEditable(x);

        txtPartos1.setEditable(x);
        txtCC.setEditable(x);
        txtTorax.setEditable(x);
        txtAbdomen1.setEditable(x);
        txtNeurologico1.setEditable(x);
        txtPielYFaneras.setEditable(x);
        txtExtremidades.setEditable(x);
        txtExtremidades1.setEditable(x);

        txtTelefonoResponsable.setEditable(x);
        cmbParentesco.setEnabled(x);
        txtMotivo.setEditable(x);
        txtRevision.setEditable(x);
        txtT.setEditable(x);
        txtPeso.setEditable(x);
        txtEstatura.setEditable(x);
        txtFrecCardiaca.setEditable(x);
        txtFrecRespiratoria.setEditable(x);
        txtPa.setEditable(x);
        txtPa2.setEditable(x);
        txtCabezaCuello.setEditable(x);
        txtCardioPulmonar.setEditable(x);
        txtAbdomen.setEditable(x);
        txtDigestivo.setEditable(x);
        txtUrinario.setEditable(x);
        txtEnfermedadActual.setEditable(x);
        txtDiagnostico1.setEditable(x);
        txtDiagnostico2.setEditable(x);
        txtDiagnostico3.setEditable(x);
        txtDiagnostico4.setEditable(x);
        txtRiesgosRecomendaciones.setEditable(x);
        txtProcedimiento.setEditable(x);
        txtProcedimiento1.setEditable(x);
        txtOido.setEditable(x);
        txtMusculoEsqueletico.setEditable(x);
        txtNeurologico.setEditable(x);
        txtPiel.setEditable(x);
        txtEctopicos.setEditable(x);
        txtObservacionProcedimientos.setEditable(x);
        txtOido.setEditable(x);
        txtVista.setEditable(x);
        txtNariz.setEditable(x);
        txtGusto.setEditable(x);
        txtTacto.setEditable(x);
        txtFamiliar.setEditable(x);
        txtFumador.setEditable(x);
        txtIngestaAlcohol.setEditable(x);
        txtAlergias.setEditable(x);
        txtToxicos.setEditable(x);
        txtGluteos.setEditable(x);
        txtMamas.setEditable(x);
        txtMedicamentos.setEditable(x);
        txtPatologicos.setEditable(x);
        txtPsiquiatricos.setEditable(x);
        txtQuirurgicos.setEditable(x);
        txtUltimaMenstruacion.setEnabled(x);
        txtUltimaCitologia.setEnabled(x);
        txtMenarca.setEnabled(x);
        txtResultadoCitologia.setEditable(x);
        txtEmbarazos.setEditable(x);
        txtAbortos.setEditable(x);
        txtPartos.setEditable(x);
        txtCesareas.setEditable(x);
        txtHijosVivos.setEditable(x);
        txtPlanificacion.setEditable(x);
//        txtDescripcionExamenConsulta.setEditable(x);
        btnHipertension.setEnabled(x);
        btnDiabetes.setEnabled(x);
        btnMetabolica.setEnabled(x);
//        jButton6.setEnabled(x);
//        jButton7.setEnabled(x);
        btnBuscCie1.setEnabled(x);
        btnBuscCie2.setEnabled(x);
        btnBuscCie3.setEnabled(x);
        btnBuscCie4.setEnabled(x);
        btnBuscCup1.setEnabled(x);
        btnBuscCup2.setEnabled(x);
        btnBuscPlantilla1.setEnabled(x);
        btnBuscPlantilla2.setEnabled(x);
        btnBuscPlantilla3.setEnabled(x);
        btnBuscPlantilla4.setEnabled(x);
        btnBuscPlantilla5.setEnabled(x);
        btnBuscPlantilla6.setEnabled(x);
        btnBuscPlantilla7.setEnabled(x);
        btnBuscPlantilla8.setEnabled(x);
        btnBuscPlantilla9.setEnabled(x);
        btnBuscPlantilla10.setEnabled(x);
        btnBuscPlantilla11.setEnabled(x);
        btnBuscPlantilla12.setEnabled(x);
        btnBuscPlantilla13.setEnabled(x);

        btnBuscPlantilla14.setEnabled(x);
        btnBuscPlantilla15.setEnabled(x);
        btnBuscPlantilla16.setEnabled(x);
        btnBuscPlantilla17.setEnabled(x);
        btnBuscPlantilla18.setEnabled(x);
        btnBuscPlantilla19.setEnabled(x);
        btnBuscPlantilla20.setEnabled(x);
        btnBuscPlantilla21.setEnabled(x);
        btnBuscPlantilla22.setEnabled(x);
        btnBuscPlantilla23.setEnabled(x);
        btnBuscPlantilla24.setEnabled(x);
        btnBuscPlantilla25.setEnabled(x);
        btnBuscPlantilla26.setEnabled(x);
        btnBuscPlantilla37.setEnabled(x);

        if (x) {
            if (txtPatologicos.getText().contains("Hipertensión")) {
                btnHipertension.setEnabled(false);
            }
            if (txtPatologicos.getText().contains("Diabetes")) {
                btnDiabetes.setEnabled(false);
            }
            if (txtPatologicos.getText().contains("Metabolica")) {
                btnMetabolica.setEnabled(false);
            }
            cmbFecha.removeAllItems();
            cmbFecha.addItem("HOY - NUEVO");

        }

        txtPartos1.setText("");
        txtCC.setText("");
        txtTorax.setText("");
        txtAbdomen1.setText("");
        txtNeurologico1.setText("");
        txtPielYFaneras.setText("");
        txtExtremidades.setText("");
        txtExtremidades1.setText("");
        txtCiclo.setText("");

        txtResponsable.setText("");
        txtDescripcionExamenConsulta.setText("");
        txtNombre.setText("");
        cmbParentesco.setSelectedIndex(0);
        txtMotivo.setText("");
        txtRevision.setText("");
        txtT.setText("");
        txtPeso.setText("");
        txtEstatura.setText("");
        txtFrecCardiaca.setText("");
        txtFrecRespiratoria.setText("");
        txtPa.setText("");
        txtTiene.setText("");
        txtPa2.setText("");
        txtImc.setText("");
        txtCabezaCuello.setText("");
        txtCardioPulmonar.setText("");
        txtAbdomen.setText("");
        txtDigestivo.setText("");
        txtUrinario.setText("");
        txtEnfermedadActual.setText("");
        txtCie1.setText("");
        txtCie2.setText("");
        txtCie3.setText("");
        txtCie4.setText("");
        txtDiagnostico1.setText("");
        txtDiagnostico2.setText("");
        txtDiagnostico3.setText("");
        txtDiagnostico4.setText("");
        txtTratamiento.setText("");
        txtRiesgosRecomendaciones.setText("");
        txtCup.setText("");
        txtProcedimiento.setText("");
        txtProcedimiento1.setText("");
        txtOido.setText("");
        txtMusculoEsqueletico.setText("");
        txtNeurologico.setText("");
        txtPiel.setText("");
        txtTelefonoResponsable.setText("");
        txtCup1.setText("");
        txtObservacionProcedimientos.setText("");
        txtOido.setText("");
        txtVista.setText("");
        txtNariz.setText("");
        txtGusto.setText("");
        txtTacto.setText("");
        txtGluteos.setText("");
        txtMamas.setText("");

        btnGuardarArchivos.setBackground(new java.awt.Color(46, 204, 113));

        limpiarExamenes(tblExamenesEvolucion);
        limpiarExamenes(tblExamenes);
    }

    public void limpiarAntecedentes(boolean x) {
        consultaCargada = false;
        txtFamiliar.setEditable(x);
        txtFumador.setEditable(x);
        txtIngestaAlcohol.setEditable(x);
        txtAlergias.setEditable(x);
        txtToxicos.setEditable(x);
        txtMedicamentos.setEditable(x);
        txtPatologicos.setEditable(x);
        txtPsiquiatricos.setEditable(x);
        txtQuirurgicos.setEditable(x);
        txtUltimaMenstruacion.setEnabled(x);
        txtUltimaCitologia.setEnabled(x);
        txtMenarca.setEnabled(x);
        txtResultadoCitologia.setEditable(x);
        txtEmbarazos.setEditable(x);
        txtAbortos.setEditable(x);
        txtPartos.setEditable(x);
        txtCesareas.setEditable(x);
        txtHijosVivos.setEditable(x);
        txtPlanificacion.setEditable(x);
        btnHipertension.setEnabled(x);
        btnDiabetes.setEnabled(x);
        btnMetabolica.setEnabled(x);

        txtFamiliar.setText("");
        txtFumador.setText("");
        txtIngestaAlcohol.setText("");
        txtAlergias.setText("");
        txtToxicos.setText("");
        txtMedicamentos.setText("");
        txtPatologicos.setText("");
        txtPsiquiatricos.setText("");
        txtQuirurgicos.setText("");
        txtUltimaMenstruacion.setText("");
        txtUltimaCitologia.setText("");
        txtMenarca.setText("");
        txtResultadoCitologia.setText("");
        txtEmbarazos.setText("");
        txtAbortos.setText("");
        txtPartos.setText("");
        txtCesareas.setText("");
        txtHijosVivos.setText("");
        txtPlanificacion.setText("");
        btnGuardarArchivos.setBackground(new java.awt.Color(46, 204, 113));
    }

    public void limpiarExamenes(JTable tabla) {
        int cant = tabla.getRowCount();
        for (int i = 0; i < cant; i++) {
            ((DefaultTableModel) tabla.getModel()).removeRow(0);
        }
    }

    public void seguimientoEditable(boolean x) {

        txtEvolucionSeg.setEditable(x);
        txtT1.setEditable(x);
        txtPeso1.setEditable(x);
        txtPa1.setEditable(x);
        txtFr2.setEditable(x);
        txtObservacionesSeg.setEditable(x);
        txtDescripcionExamenEvolucion.setEditable(x);
        btnAgregarExamenEvolucion.setEnabled(x);
        btnSubirImagen2.setEnabled(x);

        limpiarExamenes(tblExamenesEvolucion);

        if (x) {
            txtProfesional.setText(instancias.getUsuarioLog().getNombre());
        }
        txtEvolucionSeg.setText("");
        txtT1.setText("");
        txtPeso1.setText("");
        txtPa1.setText("");
        txtFr2.setText("");
        txtObservacionesSeg.setText("");

        btnBuscPlantilla27.setEnabled(x);
        btnBuscPlantilla28.setEnabled(x);
    }

    public void montarExamen(int num) {

        if (cmbFecha.getSelectedIndex() != 0) {

            if (tblExamenesEvolucion.getValueAt(num, 1) == null || tblExamenesEvolucion.getValueAt(num, 1).toString().equals("")) {
                metodos.msgError(this, "No hay examen");
            } else {
                visor completa = new visor(tblExamenesEvolucion.getValueAt(num, 1).toString());
                completa.show();
            }

            return;
        }

        JFileChooser chooser = new JFileChooser();
        ImagePreviewPanel preview = new ImagePreviewPanel();
        chooser.setAccessory(preview);
        chooser.addPropertyChangeListener(preview);

        int respuesta = chooser.showSaveDialog(this);

        if (respuesta == JFileChooser.APPROVE_OPTION) {
            Path FROM = Paths.get(chooser.getSelectedFile().getAbsolutePath());
            String id = "";

            id = Arrays.toString(instancias.getSql().getNumConsecutivo("IMG"));

            if (!instancias.getSql().aumentarConsecutivo("IMG", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("IMG")[0]) + 1)) {
                metodos.msgError(this, "Hubo un problema al guardar el codigo de la imagen");
            }

            tblExamenesEvolucion.setValueAt(id, num, 1);

            Path TO = Paths.get(System.getProperty("user.dir") + "\\imagenes\\medico\\IMG-" + id + ".jpg");

            //sobreescribir el fichero de destino, si existe, y copiar
            // los atributos, incluyendo los permisos rwx
            CopyOption[] options = new CopyOption[]{
                StandardCopyOption.REPLACE_EXISTING,
                StandardCopyOption.COPY_ATTRIBUTES
            };

            try {
                Files.copy(FROM, TO, options);
            } catch (Exception ex) {
                metodos.msgError(this, "Hubo un error al cargar el archivo");
            }

        }
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
//        buscar.setInstancia(instancias);
        buscar.noEncontrado(codigo.replace("'", "//"));
        buscar.show();
    }

    public void ventanaCups(String codigo, JTextField campo) {
        buscCups buscar = new buscCups(null, rootPaneCheckingEnabled);
        buscar.setLocationRelativeTo(null);
        instancias.setBuscCups(buscar);
        instancias.setCampoActual(campo);
        campo.requestFocus();
//        buscar.setInstancia(instancias);
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

    public void cargar(String paciente, ndOrdenServicio orden) {
        this.paciente = paciente;

        txtFamiliar.setText("");
        txtFumador.setText("");
        txtIngestaAlcohol.setText("");
        txtTraumaticos.setText("");
        txtAlergias.setText("");
        txtToxicos.setText("");
        txtMedicamentos.setText("");
        txtPatologicos.setText("");
        txtPsiquiatricos.setText("");
        txtQuirurgicos.setText("");
        txtHistorial.setText("");
        txtNutricionales.setText("");
        txtRevisionxSistemas.setText("");
        txtMenarca.setText("");
        txtUltimaMenstruacion.setText("");
        txtCiclo.setText("");
        txtUltimaCitologia.setText("");
        txtPlanificacion.setText("");
        txtResultadoCitologia.setText("");
        txtAbortos.setText("");
        txtCesareas.setText("");
        txtEctopicos.setText("");
        txtEmbarazos.setText("");
        txtPartos.setText("");
        txtHijosVivos.setText("");
//        consultaEditable(true);

        setPaciente(paciente);

        try {
            this.setSelected(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(infHistoriaMedica.class.getName()).log(Level.SEVERE, null, ex);
        }

        lista.setSelectedIndex(0);
        try {
            txtCup.setText(orden.getCup());
            txtProcedimiento.setText(instancias.getSql().getNombreCup(orden.getCup()));
            txtProcedimiento.setEditable(false);
        } catch (Exception e) {
            txtProcedimiento.setEditable(true);
        }

        try {
            txtCie1.setText(orden.getCie());
            txtDiagnostico1.setText(instancias.getSql().getNombreCie(orden.getCie()));
            txtDiagnostico1.setEditable(false);
        } catch (Exception e) {
            txtProcedimiento.setEditable(true);
        }
        try {
            this.orden = orden.getId();
        } catch (Exception e) {
            this.orden = null;
        }

        instancias.getMenu().cambiarTitulo("HISTORIA CLINICA");
    }

    public void guardarOrden(String consecutivo) {
        String valor = "0";
        String convenio = "";
        String factura = "";

        try {
            ndProducto prod = instancias.getSql().getDatosProducto(nodoCampos.getServicio(), "bdProductos");
            valor = prod.getL1();
        } catch (Exception e) {
        }

        String consecutivoEps = consecutivo.substring(6);
        String nombreEmpleado = instancias.getSql().getNombreEmpleadoUsuario(instancias.getUsuario());

        facturarOrden = true;
        factura = "FACT-" + instancias.getSql().getNumConsecutivo("FACT")[0];

        orden = "" + instancias.getSql().getNumConsecutivo("ORDEN")[0];
        productoFacturarOrden = nodoCampos.getServicio();
        Object[] vector = {orden, factura, instancias.getUsuario(), metodos.fechaConsulta(metodosGenerales.fecha()), "", nodoCampos.getConcepto(), nodoCampos.getServicio(),
            "", nodoCampos.getFinalidad(), nodoCampos.getCausaExterna(), nodoCampos.getAmbito(), nodoCampos.getPersonal(), nodoCampos.getForma(), big.getMoneda("0"),
            big.getMoneda("0"), big.getMoneda("0"), big.getMoneda(valor), big.getMoneda("1"), big.getMoneda("0"), nodoTercero.getIdSistema(), txtDiagnostico1.getText(),
            txtCup.getText(), metodos.fechaConsulta(metodosGenerales.fechaHora()), nodoCampos.getTipo(), "", consecutivoEps,
            nodoCampos.getActoQuirurgico(), "1", consecutivo, nodoCampos.getConsulta(), nombreEmpleado, metodosGenerales.hora(), convenio};

        ndOrdenServicio nodoOrden = metodos.llenarOrdenServicio(vector);

        if (!instancias.getSql().agregarOrdenServicio(nodoOrden)) {
            metodos.msgError(this, "Error al guardar la orden");
            return;
        }

        if (!instancias.getSql().aumentarConsecutivoEps(consecutivo.substring(0, 5), Integer.parseInt((String) instancias.getSql().getNumConsecutivoEps(consecutivo.substring(0, 5))[0]) + 1)) {
            metodos.msgError(this, "Error al aumentar consecutivo EPS");
        }
//            if (metodos.msgPregunta(this, "¿Desea imprimir Orden?") == 0) {
//                instancias.getReporte().verOrdenServicio(orden);
//            }

        if (!instancias.getSql().aumentarConsecutivo("ORDEN", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("ORDEN")[0]) + 1)) {
            metodos.msgError(this, "Error al aumentar consecutivo de la orden");
        }
    }

    public void facturar(String producto) {
        String[] e = new String[5];
        e[0] = producto;
        e[1] = "1";
        e[2] = "0";
        e[3] = "0";
        e[4] = "0";

        instancias.getFactura().desdeOrdenMedica(paciente, e, false);
    }

    public void validarHistoriaCargada() {
        if (!consultaCargada) {
            try {
                instancias.getHistoriaC().setSelected(true);
            } catch (Exception e) {
            }
        }
    }

    public void cargarArchivos() {
        Object[][] archivosAdjuntos = instancias.getSql().obtenerArchivosAdjuntos(historia.getId(), metodos.fechaConsulta(metodosGenerales.fecha()));

        for (int i = 0; i < archivosAdjuntos.length; i++) {
            DefaultTableModel modeloTabla = (DefaultTableModel) tblExamenes.getModel();
            modeloTabla.addRow(new Object[]{archivosAdjuntos[i][2], archivosAdjuntos[i][3], archivosAdjuntos[i][4], true});
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarExamenEvolucion;
    private javax.swing.JButton btnAyudaDiagnostica;
    private javax.swing.JButton btnBuscCie1;
    private javax.swing.JButton btnBuscCie2;
    private javax.swing.JButton btnBuscCie3;
    private javax.swing.JButton btnBuscCie4;
    private javax.swing.JButton btnBuscCup1;
    private javax.swing.JButton btnBuscCup2;
    private javax.swing.JButton btnBuscPlantilla1;
    private javax.swing.JButton btnBuscPlantilla10;
    private javax.swing.JButton btnBuscPlantilla11;
    private javax.swing.JButton btnBuscPlantilla12;
    private javax.swing.JButton btnBuscPlantilla13;
    private javax.swing.JButton btnBuscPlantilla14;
    private javax.swing.JButton btnBuscPlantilla15;
    private javax.swing.JButton btnBuscPlantilla16;
    private javax.swing.JButton btnBuscPlantilla17;
    private javax.swing.JButton btnBuscPlantilla18;
    private javax.swing.JButton btnBuscPlantilla19;
    private javax.swing.JButton btnBuscPlantilla2;
    private javax.swing.JButton btnBuscPlantilla20;
    private javax.swing.JButton btnBuscPlantilla21;
    private javax.swing.JButton btnBuscPlantilla22;
    private javax.swing.JButton btnBuscPlantilla23;
    private javax.swing.JButton btnBuscPlantilla24;
    private javax.swing.JButton btnBuscPlantilla25;
    private javax.swing.JButton btnBuscPlantilla26;
    private javax.swing.JButton btnBuscPlantilla27;
    private javax.swing.JButton btnBuscPlantilla28;
    private javax.swing.JButton btnBuscPlantilla29;
    private javax.swing.JButton btnBuscPlantilla3;
    private javax.swing.JButton btnBuscPlantilla30;
    private javax.swing.JButton btnBuscPlantilla31;
    private javax.swing.JButton btnBuscPlantilla32;
    private javax.swing.JButton btnBuscPlantilla33;
    private javax.swing.JButton btnBuscPlantilla34;
    private javax.swing.JButton btnBuscPlantilla35;
    private javax.swing.JButton btnBuscPlantilla36;
    private javax.swing.JButton btnBuscPlantilla37;
    private javax.swing.JButton btnBuscPlantilla38;
    private javax.swing.JButton btnBuscPlantilla39;
    private javax.swing.JButton btnBuscPlantilla4;
    private javax.swing.JButton btnBuscPlantilla5;
    private javax.swing.JButton btnBuscPlantilla6;
    private javax.swing.JButton btnBuscPlantilla7;
    private javax.swing.JButton btnBuscPlantilla8;
    private javax.swing.JButton btnBuscPlantilla9;
    private javax.swing.JButton btnContraremision;
    private javax.swing.JButton btnDiabetes;
    private javax.swing.JButton btnFormulaMedica;
    private javax.swing.JButton btnGuardarArchivos;
    private javax.swing.JButton btnGuardarConsulta;
    private javax.swing.JButton btnGuardarSeguimiento;
    private javax.swing.JButton btnHipertension;
    private javax.swing.JButton btnIncapacidad;
    private javax.swing.JButton btnMetabolica;
    private javax.swing.JButton btnReimprimirConsulta;
    private javax.swing.JButton btnRemision;
    private javax.swing.JButton btnSubirImagen2;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup10;
    private javax.swing.ButtonGroup buttonGroup11;
    private javax.swing.ButtonGroup buttonGroup12;
    private javax.swing.ButtonGroup buttonGroup13;
    private javax.swing.ButtonGroup buttonGroup14;
    private javax.swing.ButtonGroup buttonGroup15;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    private javax.swing.ButtonGroup buttonGroup7;
    private javax.swing.ButtonGroup buttonGroup8;
    private javax.swing.ButtonGroup buttonGroup9;
    private javax.swing.JCheckBox chkCirujano;
    private javax.swing.JCheckBox chkMedico;
    private javax.swing.JComboBox cmbFecha;
    private javax.swing.JComboBox cmbParentesco;
    private javax.swing.JComboBox cmbTipoDiagnostico;
    private javax.swing.ButtonGroup grupoAlergias;
    private javax.swing.ButtonGroup grupoFumador;
    private javax.swing.ButtonGroup grupoIngesta;
    private javax.swing.ButtonGroup grupoMedicamentos;
    private javax.swing.ButtonGroup grupoPatologicos;
    private javax.swing.ButtonGroup grupoPlanificacion;
    private javax.swing.ButtonGroup grupoPsiquiatricos;
    private javax.swing.ButtonGroup grupoQuirurgicos;
    private javax.swing.ButtonGroup grupoToxicos;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox10;
    private javax.swing.JCheckBox jCheckBox11;
    private javax.swing.JCheckBox jCheckBox12;
    private javax.swing.JCheckBox jCheckBox13;
    private javax.swing.JCheckBox jCheckBox14;
    private javax.swing.JCheckBox jCheckBox15;
    private javax.swing.JCheckBox jCheckBox16;
    private javax.swing.JCheckBox jCheckBox17;
    private javax.swing.JCheckBox jCheckBox18;
    private javax.swing.JCheckBox jCheckBox19;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox20;
    private javax.swing.JCheckBox jCheckBox21;
    private javax.swing.JCheckBox jCheckBox22;
    private javax.swing.JCheckBox jCheckBox23;
    private javax.swing.JCheckBox jCheckBox24;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JCheckBox jCheckBox8;
    private javax.swing.JCheckBox jCheckBox9;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane24;
    private javax.swing.JScrollPane jScrollPane25;
    private javax.swing.JScrollPane jScrollPane26;
    private javax.swing.JScrollPane jScrollPane27;
    private javax.swing.JScrollPane jScrollPane28;
    private javax.swing.JScrollPane jScrollPane29;
    private javax.swing.JScrollPane jScrollPane30;
    private javax.swing.JScrollPane jScrollPane31;
    private javax.swing.JScrollPane jScrollPane32;
    private javax.swing.JScrollPane jScrollPane33;
    private javax.swing.JScrollPane jScrollPane34;
    private javax.swing.JScrollPane jScrollPane35;
    private javax.swing.JScrollPane jScrollPane36;
    private javax.swing.JScrollPane jScrollPane37;
    private javax.swing.JScrollPane jScrollPane38;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane40;
    private javax.swing.JScrollPane jScrollPane41;
    private javax.swing.JScrollPane jScrollPane42;
    private javax.swing.JScrollPane jScrollPane43;
    private javax.swing.JScrollPane jScrollPane44;
    private javax.swing.JScrollPane jScrollPane45;
    private javax.swing.JScrollPane jScrollPane47;
    private javax.swing.JScrollPane jScrollPane48;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane56;
    private javax.swing.JScrollPane jScrollPane57;
    private javax.swing.JScrollPane jScrollPane58;
    private javax.swing.JScrollPane jScrollPane60;
    private javax.swing.JScrollPane jScrollPane61;
    private javax.swing.JScrollPane jScrollPane62;
    private javax.swing.JScrollPane jScrollPane63;
    private javax.swing.JScrollPane jScrollPane64;
    private javax.swing.JScrollPane jScrollPane65;
    private javax.swing.JScrollPane jScrollPane66;
    private javax.swing.JScrollPane jScrollPane67;
    private javax.swing.JScrollPane jScrollPane69;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JLabel lbDireccion16;
    private javax.swing.JLabel lbDireccion19;
    private javax.swing.JLabel lbDireccion21;
    private javax.swing.JLabel lbDireccion23;
    private javax.swing.JLabel lbDireccion24;
    private javax.swing.JLabel lbDireccion25;
    private javax.swing.JLabel lbDireccion27;
    private javax.swing.JLabel lbDireccion28;
    private javax.swing.JLabel lbDireccion29;
    private javax.swing.JLabel lbDireccion30;
    private javax.swing.JLabel lbDireccion31;
    private javax.swing.JLabel lbDireccion32;
    private javax.swing.JLabel lbDireccion33;
    private javax.swing.JLabel lbDireccion34;
    private javax.swing.JLabel lbDireccion35;
    private javax.swing.JLabel lbDireccion36;
    private javax.swing.JLabel lbDireccion37;
    private javax.swing.JLabel lbDireccion40;
    private javax.swing.JLabel lbDireccion46;
    private javax.swing.JLabel lbDireccion47;
    private javax.swing.JLabel lbDireccion48;
    private javax.swing.JLabel lbDireccion49;
    private javax.swing.JLabel lbDireccion50;
    private javax.swing.JLabel lbDireccion51;
    private javax.swing.JLabel lbDireccion52;
    private javax.swing.JLabel lbDireccion53;
    private javax.swing.JLabel lbDireccion54;
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
    private javax.swing.JLabel lbEmail14;
    private javax.swing.JLabel lbEmail18;
    private javax.swing.JLabel lbEmail19;
    private javax.swing.JLabel lbEmail20;
    private javax.swing.JLabel lbEmail21;
    private javax.swing.JLabel lbEmail22;
    private javax.swing.JLabel lbEmail23;
    private javax.swing.JLabel lbEmail24;
    private javax.swing.JLabel lbEmail25;
    private javax.swing.JLabel lbEmail26;
    private javax.swing.JLabel lbEmail27;
    private javax.swing.JLabel lbEmail28;
    private javax.swing.JLabel lbEmail29;
    private javax.swing.JLabel lbEmail30;
    private javax.swing.JLabel lbEmail31;
    private javax.swing.JLabel lbEmail32;
    private javax.swing.JLabel lbEmail33;
    private javax.swing.JLabel lbEmail34;
    private javax.swing.JLabel lbEmail35;
    private javax.swing.JLabel lbFechaEvolucion;
    private javax.swing.JLabel lbImagenes;
    private javax.swing.JLabel lbLista;
    private javax.swing.JLabel lbNit;
    private javax.swing.JLabel lbNit11;
    private javax.swing.JLabel lbNit4;
    private javax.swing.JLabel lbNit5;
    private javax.swing.JLabel lbNit6;
    private javax.swing.JLabel lbNit7;
    private javax.swing.JLabel lbNit8;
    private javax.swing.JLabel lbNit9;
    private javax.swing.JLabel lbPartos;
    private javax.swing.JLabel lbRazon12;
    private javax.swing.JLabel lbRazon13;
    private javax.swing.JLabel lbRazon14;
    private javax.swing.JLabel lbRazon15;
    private javax.swing.JLabel lbRazon16;
    private javax.swing.JLabel lbRazon17;
    private javax.swing.JLabel lbRazon18;
    private javax.swing.JLabel lbRazon19;
    private javax.swing.JLabel lbRazon20;
    private javax.swing.JLabel lbRazon21;
    private javax.swing.JLabel lbRazon23;
    private javax.swing.JLabel lbRazon24;
    private javax.swing.JLabel lbRazon25;
    private javax.swing.JLabel lbRazon26;
    private javax.swing.JLabel lbRazon27;
    private javax.swing.JLabel lbRazon28;
    private javax.swing.JLabel lbRazon29;
    private javax.swing.JLabel lbRazon30;
    private javax.swing.JLabel lbRazon31;
    private javax.swing.JLabel lbRazon32;
    private javax.swing.JLabel lbRazon33;
    private javax.swing.JLabel lbRazon34;
    private javax.swing.JLabel lbRazon35;
    private javax.swing.JLabel lbRazon37;
    private javax.swing.JLabel lbRazon38;
    private javax.swing.JLabel lbRazon39;
    private javax.swing.JLabel lbRazon40;
    private javax.swing.JList lista;
    private javax.swing.JPanel pnlCirujano;
    private javax.swing.JPanel pnlCirujano1;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JPanel pnlMedico1;
    private javax.swing.JPanel pnlMedicoEmb;
    private javax.swing.JPanel pnlMedicoEmb1;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JMenuItem popBorrar;
    private javax.swing.JMenuItem popBorrar1;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JTabbedPane tabPrincipal;
    private javax.swing.JTable tblExamenes;
    private javax.swing.JTable tblExamenesEvolucion;
    private javax.swing.JTextArea txtAbdomen;
    private javax.swing.JTextArea txtAbdomen1;
    private javax.swing.JTextField txtAbortos;
    private javax.swing.JTextArea txtAlergias;
    private javax.swing.JTextArea txtCC;
    private javax.swing.JTextArea txtCabezaCuello;
    private javax.swing.JTextArea txtCardioPulmonar;
    private javax.swing.JTextField txtCesareas;
    private javax.swing.JTextField txtCiclo;
    private javax.swing.JTextField txtCie1;
    private javax.swing.JTextField txtCie2;
    private javax.swing.JTextField txtCie3;
    private javax.swing.JTextField txtCie4;
    private javax.swing.JTextField txtCup;
    private javax.swing.JTextField txtCup1;
    private javax.swing.JTextField txtDescripcionExamenConsulta;
    private javax.swing.JTextField txtDescripcionExamenEvolucion;
    private javax.swing.JTextField txtDiagnostico1;
    private javax.swing.JTextField txtDiagnostico2;
    private javax.swing.JTextField txtDiagnostico3;
    private javax.swing.JTextField txtDiagnostico4;
    private javax.swing.JTextArea txtDiagnosticoEspecializado;
    private javax.swing.JTextArea txtDigestivo;
    private javax.swing.JTextField txtEctopicos;
    private javax.swing.JTextField txtEdad;
    private javax.swing.JTextField txtEmbarazos;
    private javax.swing.JTextArea txtEnfermedadActual;
    private javax.swing.JTextField txtEstatura;
    private javax.swing.JTextArea txtEvolucionSeg;
    private javax.swing.JTextArea txtExtremidades;
    private javax.swing.JTextArea txtExtremidades1;
    private javax.swing.JTextArea txtFamiliar;
    private javax.swing.JTextField txtFechaNacimiento;
    private javax.swing.JTextField txtFr2;
    private javax.swing.JTextField txtFrecCardiaca;
    private javax.swing.JTextField txtFrecRespiratoria;
    private javax.swing.JTextArea txtFumador;
    private javax.swing.JTextField txtGenero;
    private javax.swing.JTextArea txtGluteos;
    private javax.swing.JTextArea txtGusto;
    private javax.swing.JTextField txtHijosVivos;
    private javax.swing.JTextArea txtHistorial;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtImc;
    private javax.swing.JTextArea txtIngestaAlcohol;
    private javax.swing.JTextArea txtMamas;
    private javax.swing.JTextArea txtMedicamentos;
    private javax.swing.JTextField txtMenarca;
    private javax.swing.JTextArea txtMotivo;
    private javax.swing.JTextArea txtMusculoEsqueletico;
    private javax.swing.JTextArea txtNariz;
    private javax.swing.JTextArea txtNeurologico;
    private javax.swing.JTextArea txtNeurologico1;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNombreAnexoConsulta;
    private javax.swing.JTextField txtNombreAnexoEvolucion;
    private javax.swing.JTextField txtNombreCompleto;
    private javax.swing.JTextArea txtNutricionales;
    private javax.swing.JTextArea txtObservacionProcedimientos;
    private javax.swing.JTextArea txtObservacionesSeg;
    private javax.swing.JTextArea txtOido;
    private javax.swing.JTextField txtPa;
    private javax.swing.JTextField txtPa1;
    private javax.swing.JTextField txtPa2;
    private javax.swing.JTextField txtPartos;
    private javax.swing.JTextArea txtPartos1;
    private javax.swing.JTextArea txtPatologicos;
    private javax.swing.JTextField txtPeso;
    private javax.swing.JTextField txtPeso1;
    private javax.swing.JTextArea txtPiel;
    private javax.swing.JTextArea txtPielYFaneras;
    private javax.swing.JTextArea txtPlanificacion;
    private javax.swing.JTextField txtProcedimiento;
    private javax.swing.JTextField txtProcedimiento1;
    private javax.swing.JTextField txtProfesional;
    private javax.swing.JTextArea txtPsiquiatricos;
    private javax.swing.JTextArea txtQuirurgicos;
    private javax.swing.JTextField txtResponsable;
    private javax.swing.JTextArea txtResultadoCitologia;
    private javax.swing.JTextArea txtRevision;
    private javax.swing.JTextArea txtRevisionxSistemas;
    private javax.swing.JTextArea txtRiesgosRecomendaciones;
    private javax.swing.JTextField txtT;
    private javax.swing.JTextField txtT1;
    private javax.swing.JTextArea txtTacto;
    private javax.swing.JTextField txtTelefonoResponsable;
    private javax.swing.JTextField txtTiene;
    private javax.swing.JTextArea txtTorax;
    private javax.swing.JTextArea txtToxicos;
    private javax.swing.JTextArea txtTratamiento;
    private javax.swing.JTextArea txtTraumaticos;
    private javax.swing.JTextField txtUltimaCitologia;
    private javax.swing.JTextField txtUltimaMenstruacion;
    private javax.swing.JTextArea txtUrinario;
    private javax.swing.JTextArea txtVista;
    // End of variables declaration//GEN-END:variables
}
