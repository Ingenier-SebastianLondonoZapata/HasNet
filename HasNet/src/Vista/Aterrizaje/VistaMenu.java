package Vista.Aterrizaje;

import Controlador.Alertas.ControladorAlertas;

import Utilidades.DatosMaestra;
import Vista.Cartera.VistaNotaCredito;
import Vista.Configuraciones.VistaConfiguraciones;
import Vista.InicioSesion.vistaInicioSesion;
import clases.Instancias;
import clases.alertasHospitalizacionVeterinario;
import clases.metodosGenerales;
import clases.recordatorios.alertaRecordatorio;
import Vista.Configuraciones.hiloSolicitudesPermisos;
import Vista.Configuraciones.vistaSuperMaestra;
import Vista.Productos.VistaProductos;
import configuracion.dlgPermisos;
import configuracion.infPlantillas;
import formularios.Agenda.infAgendaConsulta;
import formularios.Agenda.infRepAgenda;

import Vista.Cartera.VistaAbonos;
import Vista.Cartera.VistaNotaDebito;
import Vista.Cartera.VistaPagos;
import Vista.Cartera.Reportes.VistaReportesCartera;

import Vista.Tesoreria.VistaEgresos;
import formularios.Tesoreria.infRepEgresos;
import formularios.Labotario.infAcidoUrico;
import formularios.Labotario.infAcidos;
import formularios.Labotario.infAntiestreptolisina;
import formularios.Labotario.infCannabinoides;
import formularios.Labotario.infCitoquimico;
import formularios.Labotario.infCocaina;
import formularios.Labotario.infColesterol;
import formularios.Labotario.infCoprograma;
import formularios.Labotario.infCoprologico;
import formularios.Labotario.infCreatinina;
import formularios.Labotario.infDirecto;
import formularios.Labotario.infEmbarazo;
import formularios.Labotario.infGlicemia;
import formularios.Labotario.infHemoclasificacion;
import formularios.Labotario.infHemoglobina;
import formularios.Labotario.infHemograma;
import formularios.Labotario.infHistoriaLaboratorio;
import formularios.Labotario.infKoh;
import formularios.Labotario.infPerfil;
import formularios.Labotario.infProteina;
import formularios.Labotario.infSangreOculta;
import formularios.Labotario.infSerologia;
import formularios.Labotario.infTiempos;
import formularios.Medico.infAyudaDiagnostico;
import formularios.Medico.infContraremision;
import formularios.Medico.infConvenio;
import formularios.Medico.infEps;
import formularios.Medico.infFormatosDeTextos;
import formularios.Medico.infFormulaMedica;
import formularios.Medico.infHistoriaC;
import formularios.Medico.infHistoriaMedica;
import formularios.Medico.infIncapacidad;
import formularios.Medico.infMedicamentos;
import formularios.Medico.infNotaEnfermeria;
import formularios.Medico.infOrdenServicioMedico;
import formularios.Medico.infRemision;
import formularios.Medico.infReportesMedico;
import formularios.Medico.infRips;
import formularios.Oftalmologia.infAyudaDiagnosticoOf;
import formularios.Oftalmologia.infBiometria;
import formularios.Oftalmologia.infFormulaLentesOf;
import formularios.Oftalmologia.infFormulaMedicaOf;
import formularios.Oftalmologia.infHojaIngreso;
import formularios.Oftalmologia.infIncapacidadOf;
import formularios.Oftalmologia.infPaquimetria;
import formularios.Parqueadero.infLavadero;
import formularios.Parqueadero.infMensualidad;
import formularios.Parqueadero.infParqueadero;
import formularios.Parqueadero.infRepHistorial;
import formularios.Parqueadero.infRepLavadero;
import formularios.Parqueadero.infRepLavadero1;
import formularios.Parqueadero.infRepMensualidades;
import formularios.Parqueadero.infRepParqueadero;

import Vista.Tesoreria.VistaCuadreCaja;
import formularios.Tesoreria.infRepCuadre;

import Vista.Ventas.cotizacion;
import formularios.Ventas.infCuentaCobro;
import Vista.Ventas.factura;
import Vista.Ventas.creditos;
import formularios.Ventas.infFacturarLotes;
import Vista.Restaurante.VistaMesas;
import Vista.Documentos.VistaDocumentos;
import formularios.Ventas.infMesa;
import Vista.Ventas.ordenServicio;
import Vista.Ventas.pedido;
import Vista.Ventas.planSepare;
import Vista.Restaurante.VistaPreparacion;
import Vista.Ventas.VistaReportesVentas;
import formularios.Veterinario.dlgMedicamentosPendientes;
import formularios.Veterinario.infAlertasProximas;
import formularios.Veterinario.infAyudaDiagnosticoVeterinaria;
import formularios.Veterinario.infFormatos;
import formularios.Veterinario.infFormulaMedicaVeterinaria;
import formularios.Veterinario.infGuarderia;
import formularios.Veterinario.infHistoria;
import formularios.Veterinario.infHospitalizacion;
import formularios.Veterinario.infIngresoHospitalizacion;

import formularios.productos.dlgConsultarCodigos;
import Vista.Productos.VistaGrupos;
import Vista.Productos.ingreso;
import Vista.Productos.VistaDiseno;

import Vista.Contactos.VistaContactos;
import formularios.terceros.infRepClientes;
import formularios.terceros.infRepEmpleados;

import formularios.Veterinario.infMascotas;
import formularios.Veterinario.infPeluqueria;
import formularios.Veterinario.infQuirurgica;
import formularios.Veterinario.infRemisionVeterinaria;
import formularios.Veterinario.infRepGuarderia;
import formularios.Veterinario.infRepHospitalizacion;
import formularios.Veterinario.infRepMascotas;
import formularios.Veterinario.infRepPeluqueria;

import Vista.Productos.VistaAjusteInventario;
import Vista.Productos.VistaInventarioInicial;
import Vista.Productos.ordenCompra;
import formularios.productos.infTrasladosInternos;
import formularios.recordatorios.infRecordatorios;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyVetoException;
import java.net.ServerSocket;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import clases.ndUsuario;
import Vista.Aterrizaje.Menu.CatalogoMenu;
import Vista.Aterrizaje.Menu.ModuloMenu;
import Vista.Aterrizaje.Menu.OpcionMenu;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import Dao.Configuraciones.DaoMaestra;
import Vista.Productos.Reportes.VistaReportesProducto;

public class VistaMenu extends javax.swing.JFrame {

    private hiloSolicitudesPermisos hiloSolicitudes;

    private final Rectangle desktop;
    private final metodosGenerales metodos;
    private Instancias instancias;

    private final Rectangle MENU, SUBMENU;
    boolean seVeElMenu;
    private List<OpcionMenu> opcionesActuales = Collections.emptyList();
    private final Map<ModuloMenu, JButton> botonesModulo = new EnumMap<>(ModuloMenu.class);
    private final List<JButton> botonesOpcion = new ArrayList<>();
    private final ImageIcon iconoOpcion = new ImageIcon(getClass().getResource("/imagenes/flecha.png"));
    private final ImageIcon iconoOpcionActiva = new ImageIcon(getClass().getResource("/imagenes/flecha1.png"));
    ServerSocket SERVER_SOCKETE;

    Boolean noAbrir = true;

    private static final Color COLOR_NAVBAR = new Color(54, 54, 52);
    private static final Color COLOR_NAVBAR_HOVER = new Color(80, 80, 77);
    private static final Color COLOR_ALERTA = new Color(255, 170, 0);
    private static final Color COLOR_BOTON_MENU = new Color(214, 214, 214);
    private static final Color COLOR_OPCION_ACTIVA = new Color(0, 153, 255);
    private static final Color COLOR_FONDO_MENU = new Color(242, 244, 244);

    private static final int ANCHO_MODULO = 195;
    private static final int ALTO_MODULO = 43;
    private static final int ESPACIO_MODULOS = 3;
    private static final int ANCHO_OPCION = 176;
    private static final int ALTO_OPCION = 28;
    private static final int ANCHO_MINIATURA = 60;
    private static final int ALTO_MINIATURA = 50;
    private static final int ESPACIO_MINIATURAS = 6;

    private static final String ULTIMO_CIERRE_MENU = "ultimoCierreMenu";

    private JPopupMenu menuOpciones;
    private JMenuItem itemConfiguraciones;
    private JMenuItem itemSolicitudes;
    private JMenuItem itemMedicamentos;
    private JMenuItem itemHistorialMovimientos;
    private JMenuItem itemCerrarSesion;
    private boolean hayAlertaSolicitudes;
    private boolean hayAlertaMedicamentos;

    private JPopupMenu menuAccesos;
    private JMenuItem accesoFactura;
    private JMenuItem accesoCongelada;
    private JMenuItem accesoCompra;
    private JMenuItem accesoEgreso;
    private JMenuItem accesoCuadreCaja;
    private JMenuItem accesoHistorialMovimientos;

    public VistaMenu() {
        initComponents();
        construirMenuAccesos();
        construirMenuOpciones();

        MENU = pnlMenu.getBounds();
        SUBMENU = pnlMenu.getBounds();

        this.setLocationRelativeTo(null);
        desktop = dkpFormularios.getBounds();
        metodos = new metodosGenerales();

        seVeElMenu = true;

        instancias = Instancias.getInstancias();

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                cerrarAplicacion();
            }
        });

        configurarAtajos();

        pnlMiniaturas.setVisible(false);

        DaoMaestra _daoMaestra = new DaoMaestra();
        DatosMaestra.setearDatosMaestra(_daoMaestra.obtenerGeneral(), _daoMaestra.obtenerImpresiones());
    }

    private void configurarAtajos() {
        KeyStroke F2 = KeyStroke.getKeyStroke("F2");
        dkpFormularios.registerKeyboardAction(accion("facturar"), "FACTURA", F2, JComponent.WHEN_IN_FOCUSED_WINDOW);

        KeyStroke CTRL_S = KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK);
        dkpFormularios.registerKeyboardAction(accion("expcont"), "EXPCONT", CTRL_S, JComponent.WHEN_IN_FOCUSED_WINDOW);

        //BOTONES MODULOS
        KeyStroke ALT_1 = KeyStroke.getKeyStroke(KeyEvent.VK_1, Event.ALT_MASK);
        dkpFormularios.registerKeyboardAction(accion("modulo1"), "MODULO1", ALT_1, JComponent.WHEN_IN_FOCUSED_WINDOW);

        KeyStroke ALT_2 = KeyStroke.getKeyStroke(KeyEvent.VK_2, Event.ALT_MASK);
        dkpFormularios.registerKeyboardAction(accion("modulo2"), "MODULO2", ALT_2, JComponent.WHEN_IN_FOCUSED_WINDOW);

        KeyStroke ALT_3 = KeyStroke.getKeyStroke(KeyEvent.VK_3, Event.ALT_MASK);
        dkpFormularios.registerKeyboardAction(accion("modulo3"), "MODULO3", ALT_3, JComponent.WHEN_IN_FOCUSED_WINDOW);

        KeyStroke ALT_4 = KeyStroke.getKeyStroke(KeyEvent.VK_4, Event.ALT_MASK);
        dkpFormularios.registerKeyboardAction(accion("modulo4"), "MODULO4", ALT_4, JComponent.WHEN_IN_FOCUSED_WINDOW);

        KeyStroke ALT_5 = KeyStroke.getKeyStroke(KeyEvent.VK_5, Event.ALT_MASK);
        dkpFormularios.registerKeyboardAction(accion("modulo5"), "MODULO5", ALT_5, JComponent.WHEN_IN_FOCUSED_WINDOW);

        KeyStroke ALT_6 = KeyStroke.getKeyStroke(KeyEvent.VK_6, Event.ALT_MASK);
        dkpFormularios.registerKeyboardAction(accion("modulo6"), "MODULO6", ALT_6, JComponent.WHEN_IN_FOCUSED_WINDOW);

        KeyStroke ALT_7 = KeyStroke.getKeyStroke(KeyEvent.VK_7, Event.ALT_MASK);
        dkpFormularios.registerKeyboardAction(accion("modulo7"), "MODULO7", ALT_7, JComponent.WHEN_IN_FOCUSED_WINDOW);

        int[] teclasOpcion = {KeyEvent.VK_1, KeyEvent.VK_2, KeyEvent.VK_3, KeyEvent.VK_4, KeyEvent.VK_5,
            KeyEvent.VK_6, KeyEvent.VK_7, KeyEvent.VK_8, KeyEvent.VK_9, KeyEvent.VK_0};
        for (int i = 0; i < teclasOpcion.length; i++) {
            final int indice = i;
            KeyStroke tecla = KeyStroke.getKeyStroke(teclasOpcion[i], Event.CTRL_MASK);
            dkpFormularios.registerKeyboardAction(e -> seleccionarOpcion(indice), "BOTON" + (i + 1), tecla, JComponent.WHEN_IN_FOCUSED_WINDOW);
        }
    }

    private ActionListener accion(final String opc) {
        ActionListener a = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (opc) {
                    case "facturar":
                        if (noAbrir) {
                            actualizarMenu("ventas");
                            seleccionarOpcion(0);
                        }
                        break;
                    case "expcont":
                        if (noAbrir) {
                            btnOcultarActionPerformed(null);
                        }
                        break;
                    case "modulo1":
                        seleccionarModulo(ModuloMenu.VENTAS);
                        break;
                    case "modulo2":
                        seleccionarModulo(ModuloMenu.PRODUCTOS);
                        break;
                    case "modulo3":
                        seleccionarModulo(ModuloMenu.TERCEROS);
                        break;
                    case "modulo4":
                        seleccionarModulo(ModuloMenu.TESORERIA);
                        break;
                    case "modulo5":
                        seleccionarModulo(ModuloMenu.CARTERA);
                        break;
                    case "modulo6":
                        seleccionarModulo(ModuloMenu.TESORERIA);
                        break;
                    case "modulo7":
                        btnVerificarActionPerformed(null);
                        break;

                }
            }
        };
        return a;
    }

    public void actualizarUsuario(String usuario) {
        setTitle("HasNet - " + metodosGenerales.fecha() + " - " + usuario);
    }

    private void cerrarAplicacion() {
        Vista.Alertas.Option confirmar = new Vista.Alertas.Option(this, true, "¿Desea salir del sistema?");
        confirmar.setVisible(true);

        if (!Boolean.TRUE.equals(confirmar.answer)) {
            return;
        }

        try {
            instancias.getSql().usuarioActivo("OFF", "");
        } catch (Exception e) {
            Logger.getLogger(VistaMenu.class.getName()).log(Level.SEVERE, "No se pudo cerrar la sesión del usuario", e);
        }

        System.exit(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dkpFormularios = new javax.swing.JDesktopPane();
        pnlContenedor = new javax.swing.JPanel();
        pnlMenu = new javax.swing.JPanel();
        lbLogo = new javax.swing.JButton();
        pnlPrincipal = new javax.swing.JPanel();
        jPanelsSliding1 = new formularios.JPanelsSliding();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        pnlModulos = new javax.swing.JPanel();
        btnAbrirCaja = new javax.swing.JButton();
        btnVerificar = new javax.swing.JButton();
        btnOcultar = new javax.swing.JButton();
        pnlNavbar = new javax.swing.JPanel();
        lbTitulo = new javax.swing.JLabel();
        btnAccesos = new javax.swing.JButton();
        sep3 = new javax.swing.JSeparator();
        btnOpciones = new javax.swing.JButton();
        pnlMiniaturas = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/icono.png")).getImage());
        setMinimumSize(new java.awt.Dimension(1177, 317));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                formComponentHidden(evt);
            }
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        dkpFormularios.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout dkpFormulariosLayout = new javax.swing.GroupLayout(dkpFormularios);
        dkpFormularios.setLayout(dkpFormulariosLayout);
        dkpFormulariosLayout.setHorizontalGroup(
            dkpFormulariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        dkpFormulariosLayout.setVerticalGroup(
            dkpFormulariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        pnlMenu.setBackground(new java.awt.Color(242, 244, 244));
        pnlMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlMenuMouseClicked(evt);
            }
        });

        lbLogo.setBackground(new java.awt.Color(242, 244, 244));
        lbLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/logoNuevo.png"))); // NOI18N
        lbLogo.setBorder(null);
        lbLogo.setContentAreaFilled(false);
        lbLogo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lbLogo.setFocusPainted(false);
        lbLogo.setFocusable(false);
        lbLogo.setMargin(new java.awt.Insets(0, 0, 0, 0));

        pnlPrincipal.setBackground(new java.awt.Color(242, 244, 244));

        jPanelsSliding1.setBackground(new java.awt.Color(242, 244, 244));
        jPanelsSliding1.setBorder(null);

        jPanel3.setBackground(new java.awt.Color(242, 244, 244));
        jPanel3.setName("panel"); // NOI18N
        jPanel3.setPreferredSize(new java.awt.Dimension(176, 504));
        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.Y_AXIS));
        jPanelsSliding1.add(jPanel3, "card2");

        jPanel4.setBackground(new java.awt.Color(242, 244, 244));

        pnlModulos.setBackground(new java.awt.Color(242, 244, 244));
        pnlModulos.setLayout(new javax.swing.BoxLayout(pnlModulos, javax.swing.BoxLayout.Y_AXIS));

        btnAbrirCaja.setBackground(new java.awt.Color(204, 204, 204));
        btnAbrirCaja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/caja Reg.png"))); // NOI18N
        btnAbrirCaja.setText("Abrir Caja");
        btnAbrirCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirCajaActionPerformed(evt);
            }
        });

        btnVerificar.setBackground(new java.awt.Color(214, 214, 214));
        btnVerificar.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        btnVerificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/LupaPequeña.png"))); // NOI18N
        btnVerificar.setText("Consultar precios");
        btnVerificar.setBorder(null);
        btnVerificar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnVerificar.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnVerificar.setMaximumSize(new java.awt.Dimension(146, 119));
        btnVerificar.setMinimumSize(new java.awt.Dimension(146, 119));
        btnVerificar.setPreferredSize(new java.awt.Dimension(146, 119));
        btnVerificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerificarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlModulos, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
            .addComponent(btnAbrirCaja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnVerificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(pnlModulos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(btnAbrirCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVerificar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout pnlPrincipalLayout = new javax.swing.GroupLayout(pnlPrincipal);
        pnlPrincipal.setLayout(pnlPrincipalLayout);
        pnlPrincipalLayout.setHorizontalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelsSliding1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlPrincipalLayout.setVerticalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelsSliding1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout pnlMenuLayout = new javax.swing.GroupLayout(pnlMenu);
        pnlMenu.setLayout(pnlMenuLayout);
        pnlMenuLayout.setHorizontalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMenuLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        pnlMenuLayout.setVerticalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(lbLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(pnlPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlContenedorLayout = new javax.swing.GroupLayout(pnlContenedor);
        pnlContenedor.setLayout(pnlContenedorLayout);
        pnlContenedorLayout.setHorizontalGroup(
            pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContenedorLayout.createSequentialGroup()
                .addComponent(pnlMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        pnlContenedorLayout.setVerticalGroup(
            pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlContenedorLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(pnlMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnOcultar.setBackground(new java.awt.Color(242, 244, 244));
        btnOcultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/izquierda.png"))); // NOI18N
        btnOcultar.setToolTipText("Ctrl+S");
        btnOcultar.setBorder(null);
        btnOcultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOcultarActionPerformed(evt);
            }
        });

        pnlNavbar.setBackground(new java.awt.Color(54, 54, 52));

        lbTitulo.setFont(new java.awt.Font("Arial", 1, 22)); // NOI18N
        lbTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lbTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitulo.setText("TITULO");

        btnAccesos.setBackground(new java.awt.Color(54, 54, 52));
        btnAccesos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnAccesos.setForeground(new java.awt.Color(255, 255, 255));
        btnAccesos.setText("Accesos rápidos");
        btnAccesos.setBorder(null);
        btnAccesos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAccesos.setFocusPainted(false);
        btnAccesos.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnAccesos.setIconTextGap(8);
        btnAccesos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAccesosActionPerformed(evt);
            }
        });

        sep3.setForeground(new java.awt.Color(255, 255, 255));
        sep3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        btnOpciones.setBackground(new java.awt.Color(54, 54, 52));
        btnOpciones.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnOpciones.setForeground(new java.awt.Color(255, 255, 255));
        btnOpciones.setText("Opciones");
        btnOpciones.setBorder(null);
        btnOpciones.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOpciones.setFocusPainted(false);
        btnOpciones.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnOpciones.setIconTextGap(8);
        btnOpciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpcionesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlNavbarLayout = new javax.swing.GroupLayout(pnlNavbar);
        pnlNavbar.setLayout(pnlNavbarLayout);
        pnlNavbarLayout.setHorizontalGroup(
            pnlNavbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNavbarLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lbTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 581, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnAccesos, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(sep3, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(btnOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );
        pnlNavbarLayout.setVerticalGroup(
            pnlNavbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
            .addComponent(btnAccesos, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
            .addComponent(sep3)
            .addComponent(btnOpciones, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        pnlMiniaturas.setBackground(new java.awt.Color(242, 244, 244));
        pnlMiniaturas.setLayout(new javax.swing.BoxLayout(pnlMiniaturas, javax.swing.BoxLayout.Y_AXIS));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlNavbar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(pnlMiniaturas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlContenedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnOcultar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(dkpFormularios))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(pnlNavbar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnOcultar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlContenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlMiniaturas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dkpFormularios, javax.swing.GroupLayout.Alignment.TRAILING)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pnlMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMenuMouseClicked

    }//GEN-LAST:event_pnlMenuMouseClicked

    private void formComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentHidden

    }//GEN-LAST:event_formComponentHidden

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        if (!seVeElMenu) {
            ocultarMenu("");
        }
    }//GEN-LAST:event_formComponentResized

    private void construirModulos() {
        botonesModulo.clear();
        pnlModulos.removeAll();
        pnlMiniaturas.removeAll();
        pnlMiniaturas.add(Box.createRigidArea(new Dimension(0, 5)));

        boolean separadorAgregado = false;
        for (ModuloMenu modulo : ModuloMenu.values()) {
            if (!modulo.estaHabilitado(instancias.getConfiguraciones())) {
                continue;
            }
            if (modulo.isEspecializado() && !separadorAgregado) {
                pnlModulos.add(crearSeparadorModulos());
                separadorAgregado = true;
            } else if (!botonesModulo.isEmpty()) {
                pnlModulos.add(Box.createRigidArea(new Dimension(0, ESPACIO_MODULOS)));
            }

            JButton boton = crearBotonModulo(modulo);
            botonesModulo.put(modulo, boton);
            pnlModulos.add(boton);

            pnlMiniaturas.add(crearMiniatura(modulo));
            pnlMiniaturas.add(Box.createRigidArea(new Dimension(0, ESPACIO_MINIATURAS)));
        }

        refrescar(pnlModulos);
        refrescar(pnlMiniaturas);
    }

    private JButton crearBotonModulo(ModuloMenu modulo) {
        JButton boton = new JButton(" " + modulo.getTexto());
        boton.setFont(new Font("Arial", Font.PLAIN, 15));
        boton.setBackground(COLOR_BOTON_MENU);
        boton.setIcon(new ImageIcon(getClass().getResource(modulo.getIconoInactivo())));
        boton.setBorder(null);
        boton.setMargin(new Insets(0, 0, 0, 0));
        boton.setHorizontalTextPosition(SwingConstants.RIGHT);
        fijarTamano(boton, ANCHO_MODULO, ALTO_MODULO);
        boton.addActionListener(e -> seleccionarModulo(modulo));
        return boton;
    }

    private JButton crearMiniatura(ModuloMenu modulo) {
        JButton boton = new JButton(new ImageIcon(getClass().getResource(modulo.getIconoInactivo())));
        boton.setBackground(COLOR_FONDO_MENU);
        boton.setBorder(null);
        boton.setMargin(new Insets(0, 0, 0, 0));
        boton.setToolTipText(modulo.getTexto());
        fijarTamano(boton, ANCHO_MINIATURA, ALTO_MINIATURA);
        boton.addActionListener(e -> seleccionarModulo(modulo));
        return boton;
    }

    private JButton crearBotonOpcion(OpcionMenu opcion, int indice) {
        JButton boton = new JButton(opcion.getTexto(), iconoOpcion);
        boton.setFont(new Font("Arial", Font.PLAIN, 14));
        boton.setBackground(COLOR_BOTON_MENU);
        boton.setBorderPainted(false);
        boton.setHorizontalAlignment(SwingConstants.LEFT);
        boton.setMargin(new Insets(2, 0, 2, 0));
        boton.setToolTipText(indice < 10 ? opcion.getTitulo() + " (Ctrl+" + ((indice + 1) % 10) + ")" : opcion.getTitulo());
        fijarTamano(boton, ANCHO_OPCION, ALTO_OPCION);
        boton.addActionListener(e -> seleccionarOpcion(indice));
        return boton;
    }

    private JPanel crearSeparadorModulos() {
        JSeparator separador = new JSeparator();
        separador.setForeground(Color.black);

        JPanel contenedor = new JPanel(new BorderLayout());
        contenedor.setOpaque(false);
        contenedor.setBorder(BorderFactory.createEmptyBorder(5, 0, 3, 0));
        contenedor.add(separador, BorderLayout.CENTER);
        fijarTamano(contenedor, ANCHO_MODULO, 10);
        return contenedor;
    }

    private void fijarTamano(JComponent componente, int ancho, int alto) {
        componente.setPreferredSize(new Dimension(ancho, alto));
        componente.setMinimumSize(new Dimension(ancho, alto));
        componente.setMaximumSize(new Dimension(Short.MAX_VALUE, alto));
        componente.setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    private void refrescar(JComponent componente) {
        componente.revalidate();
        componente.repaint();
    }

    private void seleccionarModulo(ModuloMenu modulo) {
        animacion(modulo.getClave());
        activarModulo(modulo);
    }

    private void activarModulo(ModuloMenu activo) {
        for (Map.Entry<ModuloMenu, JButton> entrada : botonesModulo.entrySet()) {
            ModuloMenu modulo = entrada.getKey();
            JButton boton = entrada.getValue();
            boolean esActivo = modulo == activo;
            boton.setBackground(esActivo ? Color.black : COLOR_BOTON_MENU);
            boton.setForeground(esActivo ? Color.white : Color.black);
            boton.setIcon(new ImageIcon(getClass().getResource(esActivo ? modulo.getIconoActivo() : modulo.getIconoInactivo())));
        }
        resaltarOpcion(null);
    }

    private void resaltarOpcion(JButton seleccionado) {
        for (JButton boton : botonesOpcion) {
            boolean esActivo = boton == seleccionado;
            boton.setForeground(esActivo ? COLOR_OPCION_ACTIVA : Color.black);
            boton.setBackground(esActivo ? Color.black : COLOR_BOTON_MENU);
            boton.setIcon(esActivo ? iconoOpcionActiva : iconoOpcion);
        }
    }

    private void animacion(String opc) {
        jPanelsSliding1.nextPanel(10, 40, jPanel3, true, opc);
        jPanelsSliding1.refresh();
    }

    public void actualizarMenu(String opc) {
        opcionesActuales = new CatalogoMenu(instancias).opciones(ModuloMenu.desdeClave(opc));

        botonesOpcion.clear();
        jPanel3.removeAll();
        for (int i = 0; i < opcionesActuales.size(); i++) {
            JButton boton = crearBotonOpcion(opcionesActuales.get(i), i);
            botonesOpcion.add(boton);
            jPanel3.add(boton);
        }
        refrescar(jPanel3);
    }

    private void seleccionarOpcion(int indice) {
        if (indice < 0 || indice >= opcionesActuales.size()) {
            return;
        }
        resaltarOpcion(botonesOpcion.get(indice));
        if (abrirOpcion(opcionesActuales.get(indice))) {
            accionarMenu();
        }
    }

    private boolean abrirOpcion(OpcionMenu opcion) {
        if (!opcion.tienePermiso()) {
            ControladorAlertas.alert("No tiene permisos para esta función");
            return false;
        }
        try {
            opcion.getAntesDeAbrir().ejecutar();
            opcion.getFormulario().setSelected(true);
            cambiarTitulo(opcion.getTitulo());
            opcion.getDespuesDeAbrir().ejecutar();
            return true;
        } catch (PropertyVetoException ex) {
            Logger.getLogger(VistaMenu.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    private void btnOcultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOcultarActionPerformed
        boolean seVe = instancias.getMenu().getSeVeElMenu();

        if (seVe) {
            instancias.getMenu().ocultarMenu("");
            btnOcultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/derecha.png")));
        } else {
            instancias.getMenu().expandirMenu();
            btnOcultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/izquierda.png")));
        }
    }//GEN-LAST:event_btnOcultarActionPerformed

    private void btnAbrirCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirCajaActionPerformed
        new Impresiones.ImpresionesCuadreCaja.GeneradorReporteCuadreCaja(instancias).abrirCajaRegistradora();
    }//GEN-LAST:event_btnAbrirCajaActionPerformed

    private void construirMenuAccesos() {
        menuAccesos = crearMenuDesplegable(btnAccesos);
        btnAccesos.setIcon(new IconoFlecha(Color.WHITE));

        accesoFactura = crearItemMenu("Crear factura", e -> abrirOpcion(OpcionMenu.de("FACTURACIÓN", () -> instancias.getFacturaContenedor())));
        accesoCongelada = crearItemMenu("Crear congelada", e -> abrirOpcion(OpcionMenu.de(tituloCongeladas(), () -> instancias.getMesas())));
        accesoCompra = crearItemMenu("Crear compra", e -> abrirOpcion(OpcionMenu.de("COMPRA", () -> instancias.getIngresoContenedor())));
        accesoEgreso = crearItemMenu("Crear gasto", e -> abrirOpcion(OpcionMenu.de("EGRESOS", () -> instancias.getEgresos())));
        accesoCuadreCaja = crearItemMenu("Cuadre de caja", e -> abrirOpcion(OpcionMenu.de("CUADRE DE CAJA", () -> instancias.getCaja())));
        accesoHistorialMovimientos = crearItemMenu("Historial de movimientos", e -> abrirHistorialMovimientos());

        menuAccesos.add(accesoFactura);
        menuAccesos.add(accesoCongelada);
        menuAccesos.add(accesoCompra);
        menuAccesos.add(accesoEgreso);
        menuAccesos.add(accesoCuadreCaja);
        menuAccesos.add(crearSeparadorMenu());
        menuAccesos.add(accesoHistorialMovimientos);
    }

    private void actualizarAccesosRapidos() {
        boolean congeladas = instancias.getConfiguraciones().isCongeladas();

        accesoFactura.setVisible(instancias.getUsuarioLog().isFacturas());
        accesoCongelada.setVisible(congeladas && instancias.getUsuarioLog().isCongeladas());
        accesoCongelada.setText(instancias.getConfiguraciones().isRestaurante() ? "Crear mesa" : "Crear congelada");
        accesoCompra.setVisible(instancias.getUsuarioLog().isCompras());
        accesoEgreso.setVisible(instancias.getUsuarioLog().isEgresos());
        accesoCuadreCaja.setVisible(instancias.getUsuarioLog().isCuadreCaja());

        boolean historial = instancias.getUsuarioLog().isReimpresion();
        accesoHistorialMovimientos.setVisible(historial);
        itemHistorialMovimientos.setVisible(historial);
    }

    private String tituloCongeladas() {
        return instancias.getConfiguraciones().isRestaurante() ? "MESAS" : "CONGELADAS";
    }

    private void abrirHistorialMovimientos() {
        if (!instancias.getUsuarioLog().isReimpresion()) {
            ControladorAlertas.alert("No tiene permisos para esta función");
            return;
        }
        try {
            cambiarTitulo("DOCUMENTOS");
            instancias.getReimpresion().setSelected(true);
            instancias.getReimpresion().setDetectarClicABoton(true);
            instancias.getReimpresion().actualizarTablaDocumentos();

            if (getSeVeElMenu()) {
                ocultarMenu("");
                btnOcultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/derecha.png")));
            }
        } catch (PropertyVetoException ex) {
            Logger.getLogger(VistaMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void construirMenuOpciones() {
        menuOpciones = crearMenuDesplegable(btnOpciones);
        btnOpciones.setIcon(new IconoFlecha(Color.WHITE));

        itemConfiguraciones = crearItemMenu("Gestionar configuraciones", e -> abrirConfiguraciones());
        itemSolicitudes = crearItemMenu("Historial de solicitudes", e -> abrirHistorialSolicitudes());
        itemMedicamentos = crearItemMenu("Historial de medicamentos", e -> abrirHistorialMedicamentos());
        itemHistorialMovimientos = crearItemMenu("Historial de movimientos", e -> abrirHistorialMovimientos());
        itemCerrarSesion = crearItemMenu("Cerrar sesión", e -> cerrarSesion());

        menuOpciones.add(itemConfiguraciones);
        menuOpciones.add(itemSolicitudes);
        menuOpciones.add(itemMedicamentos);
        menuOpciones.add(itemHistorialMovimientos);
        menuOpciones.add(crearSeparadorMenu());
        menuOpciones.add(itemCerrarSesion);

        itemConfiguraciones.setVisible(false);
        itemSolicitudes.setVisible(false);
        itemMedicamentos.setVisible(false);
        itemHistorialMovimientos.setVisible(false);
    }

    private JPopupMenu crearMenuDesplegable(JButton boton) {
        JPopupMenu menu = new JPopupMenu();
        menu.setBackground(COLOR_NAVBAR);
        menu.setBorder(BorderFactory.createLineBorder(COLOR_NAVBAR_HOVER));
        menu.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                boton.putClientProperty(ULTIMO_CIERRE_MENU, System.currentTimeMillis());
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
            }
        });
        return menu;
    }

    private JSeparator crearSeparadorMenu() {
        JSeparator separador = new JSeparator();
        separador.setForeground(COLOR_NAVBAR_HOVER);
        separador.setBackground(COLOR_NAVBAR);
        return separador;
    }

    private JMenuItem crearItemMenu(String texto, ActionListener accion) {
        JMenuItem item = new JMenuItem(texto);
        item.setFont(new java.awt.Font("Arial", 0, 14));
        item.setForeground(Color.WHITE);
        item.setBackground(COLOR_NAVBAR);
        item.setOpaque(true);
        item.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 24));
        item.setPreferredSize(new Dimension(250, 38));
        item.addChangeListener(e -> item.setBackground(item.isArmed() ? COLOR_NAVBAR_HOVER : COLOR_NAVBAR));
        item.addActionListener(accion);
        return item;
    }

    private void mostrarMenuDesplegable(JPopupMenu menu, JButton boton) {
        Object ultimoCierre = boton.getClientProperty(ULTIMO_CIERRE_MENU);
        if (ultimoCierre instanceof Long && System.currentTimeMillis() - (Long) ultimoCierre < 250) {
            return;
        }

        boolean hayOpciones = false;
        for (java.awt.Component componente : menu.getComponents()) {
            if (componente instanceof JMenuItem && componente.isVisible()) {
                hayOpciones = true;
                break;
            }
        }
        if (!hayOpciones) {
            ControladorAlertas.alert("No tiene opciones disponibles");
            return;
        }

        int ancho = menu.getPreferredSize().width;
        menu.show(boton, boton.getWidth() - ancho, boton.getHeight());
    }

    private void btnAccesosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAccesosActionPerformed
        mostrarMenuDesplegable(menuAccesos, btnAccesos);
    }//GEN-LAST:event_btnAccesosActionPerformed

    private void btnOpcionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpcionesActionPerformed
        mostrarMenuDesplegable(menuOpciones, btnOpciones);
    }//GEN-LAST:event_btnOpcionesActionPerformed

    private void actualizarIndicadorOpciones() {
        boolean hayPendientes = (itemSolicitudes.isVisible() && hayAlertaSolicitudes)
                || (itemMedicamentos.isVisible() && hayAlertaMedicamentos);
        Color color = hayPendientes ? COLOR_ALERTA : Color.WHITE;
        btnOpciones.setForeground(color);
        btnOpciones.setIcon(new IconoFlecha(color));
        btnOpciones.setToolTipText(hayPendientes ? "Tiene solicitudes o medicamentos pendientes" : null);
    }

    private static class IconoFlecha implements Icon {

        private final Color color;

        IconoFlecha(Color color) {
            this.color = color;
        }

        @Override
        public void paintIcon(java.awt.Component c, java.awt.Graphics g, int x, int y) {
            java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
            g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(c.isEnabled() ? color : Color.GRAY);
            g2.fillPolygon(new int[]{x, x + getIconWidth(), x + getIconWidth() / 2}, new int[]{y, y, y + getIconHeight()}, 3);
            g2.dispose();
        }

        @Override
        public int getIconWidth() {
            return 10;
        }

        @Override
        public int getIconHeight() {
            return 6;
        }
    }

    private void cerrarSesion() {
        if (ControladorAlertas.option("¿Desea continuar?")) {
            this.dispose();
            vistaInicioSesion vistaInicioSesion = new vistaInicioSesion(false);
            vistaInicioSesion.setLocationRelativeTo(null);
            vistaInicioSesion.setVisible(true);
        }
    }

    private void abrirHistorialSolicitudes() {
        dlgPermisos permisos = new dlgPermisos(this, false);
        permisos.setVisible(true);
    }

    private void abrirHistorialMedicamentos() {
        dlgMedicamentosPendientes medicamentosPend = new dlgMedicamentosPendientes(null, true);
        medicamentosPend.setLocationRelativeTo(null);
        medicamentosPend.setVisible(true);
    }

    private void btnVerificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerificarActionPerformed
        new dlgConsultarCodigos(this, true).setVisible(true);
    }//GEN-LAST:event_btnVerificarActionPerformed

    private void abrirConfiguraciones() {
        try {
            if (instancias.getUsuarioLog().getUsuario().equals("ADMIN")) {
                instancias.getMaestra().setSelected(true);
                instancias.getMenu().cambiarTitulo("CONFIGURACIÓNES");
            } else {
                ControladorAlertas.alert("Función solo disponible para el usuario administrador.");

            }
        } catch (PropertyVetoException ex) {
            Logger.getLogger(VistaMenu.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cerrarAlCambiar() {
        detenerFuncionSolicitudes();
        this.dispose();

        if (!instancias.getUsuario().equals("ADMIN")) {
            if (DatosMaestra.isSoloMesas()) {
                Object[][] vendedores = instancias.getSql().getVendedores1();

                for (int i = 0; i < vendedores.length; i++) {
                    String asociado = "";
                    try {
                        asociado = vendedores[i][1].toString();
                    } catch (Exception e) {
                    }

                    if (instancias.getUsuario().equals(asociado)) {
                        soloMesas();
                    }
                }
            }
        }
    }

    public void setInstancias(Instancias instancias) {

        this.instancias = instancias;
        instancias.setMenu(this);

        vistaSuperMaestra internoConfig = new vistaSuperMaestra(null, true);
        instancias.setConfiguraciones(internoConfig);
        internoConfig.cargarSuperMaestra();

        cargarModulosPrincipales();

        if (instancias.getConfiguraciones().isCongeladas()) {
            cargarModulosCongeladas();
        }

        if (instancias.getConfiguraciones().isAgenda()) {
            cargarModulosAgenda();
        }

        if (instancias.getConfiguraciones().isVeterinaria()) {
            cargarModulosVeterinaria();
        }

        if (instancias.getConfiguraciones().isParqueadero()) {
            cargarModulosParqueadero();
        }

        if (instancias.getConfiguraciones().isOftalmologia()) {
            cargarModulosOftalmologia();
        }

        if (instancias.getConfiguraciones().isLaboratorio()) {
            cargarModulosLaboratorio();
        }

        if (instancias.getConfiguraciones().isMedico()) {
            cargarModulosMedico();
        }

        if (instancias.getConfiguraciones().isRecordatorios()) {
            cargarModulosRecordatorios();
            obtenerRecordatorios();
        }

        construirModulos();

        itemConfiguraciones.setVisible("ADMIN".equals(instancias.getUsuarioLog().getUsuario()));
        actualizarAccesosRapidos();

        if (instancias.getConfiguraciones().isMedico() || instancias.getConfiguraciones().isLaboratorio() || instancias.getConfiguraciones().isOftalmologia()) {
            cargarModuloGeneracionRips();
        }

        cargarModuloSeleccionadoParaPantallaPrincipal();
        seleccionarModulo(ModuloMenu.VENTAS);

        activarFuncionSolicitudes();
        activarFuncionAlertasVeterinario();
        activarFuncionSoloMesasParaRestaurante();

        instancias.getMaestra().actualizarVariables();

        String nombre = "";
        try {
            nombre = instancias.getSql().getNombreEmpleadoUsuario(instancias.getUsuarioLog().getUsuario());
        } catch (Exception e) {
        }

        instancias.getMaestra().llenarVendedores(nombre);
        instancias.getReporte().setFirma();

        activarBotonAbrirCaja();
    }

    private void obtenerRecordatorios() {
        Object[][] datosRecor = instancias.getSql().getAlertasRecordatorios("PENDIENTE", metodos.fechaConsulta(metodosGenerales.fecha()));
        if (datosRecor.length > 0) {
            ControladorAlertas.alert("Tiene recordatorios para hoy");
            alertaRecordatorio alerta = new alertaRecordatorio();
            alerta.start();
        }
    }

    private void activarFuncionSoloMesasParaRestaurante() {
        Boolean vendedorActivo = false;
        if (!instancias.getUsuario().equals("ADMIN")) {

            if (instancias.getConfiguraciones().isRestaurante()) {

                if (DatosMaestra.isSoloMesas()) {
                    Object[][] vendedores = instancias.getSql().getVendedores1();

                    for (int i = 0; i < vendedores.length; i++) {
                        String asociado = "";
                        try {
                            asociado = vendedores[i][1].toString();
                        } catch (Exception e) {
                        }

                        if (instancias.getUsuario().equals(asociado)) {
                            soloMesas();

                            try {
                                VistaMesas interno14 = new VistaMesas();
                                dkpFormularios.add(interno14);
                                if (!vendedorActivo) {
                                    interno14.show();
                                }
                                interno14.setMaximum(true);
                                instancias.setMesas(interno14);
                            } catch (PropertyVetoException ex) {
                                Logger.getLogger(VistaMenu.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            vendedorActivo = true;
                        }
                    }
                }
            }
        }
    }

    private void activarFuncionAlertasVeterinario() {
        alertasHospitalizacionVeterinario alertas = new alertasHospitalizacionVeterinario();
        if (instancias.getConfiguraciones().isVeterinaria()) {

            Object[][] vacunas = instancias.getSql().getVacunasPendientes(metodosGenerales.fecha());
            Object[][] peluquerias = instancias.getSql().getPeluqueriasPendientes(metodosGenerales.fecha());

            Boolean activarAlerta = false;
            if (vacunas.length > 0) {
                activarAlerta = true;
            }
            if (peluquerias.length > 0) {
                activarAlerta = true;
            }

            itemMedicamentos.setVisible(true);
            actualizarIndicadorOpciones();
            alertas.start();

            if (activarAlerta) {
                ControladorAlertas.alert("Tiene peluquerias o vacunas pendientes para el día de hoy!");
            }
        } else {
            itemMedicamentos.setVisible(false);
            actualizarIndicadorOpciones();
            alertas.stop();
        }
    }

    public void activarFuncionSolicitudes() {
        boolean activo = "ADMIN".equals(instancias.getUsuario()) && DatosMaestra.isModuloPermisos();

        itemSolicitudes.setVisible(activo);
        actualizarIndicadorOpciones();

        if (!activo) {
            detenerFuncionSolicitudes();
            return;
        }

        if (hiloSolicitudes == null || !hiloSolicitudes.isAlive()) {
            hiloSolicitudes = new hiloSolicitudesPermisos();
            hiloSolicitudes.start();
        }
    }

    private void detenerFuncionSolicitudes() {
        if (hiloSolicitudes != null) {
            hiloSolicitudes.detener();
            hiloSolicitudes = null;
        }
    }

    private <T extends JInternalFrame> T agregarFormulario(T formulario, Consumer<? super T> registrar, boolean permiso) {
        dkpFormularios.add(formulario);
        registrar.accept(formulario);
        if (permiso) {
            formulario.show();
        }
        try {
            formulario.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(VistaMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return formulario;
    }

    private <T extends JInternalFrame> T agregarFormulario(T formulario, Consumer<? super T> registrar) {
        return agregarFormulario(formulario, registrar, true);
    }

    private <T extends JInternalFrame> T agregarFormularioSeleccionado(T formulario, Consumer<? super T> registrar, boolean permiso) {
        agregarFormulario(formulario, registrar, permiso);
        try {
            formulario.setSelected(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(VistaMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return formulario;
    }

    private <T extends JInternalFrame> T agregarFormularioSeleccionado(T formulario, Consumer<? super T> registrar) {
        return agregarFormularioSeleccionado(formulario, registrar, true);
    }

    private void cargarModulosPrincipales() {
        cargarModuloMaestra();
        instancias.getMaestra().llenarDatos();

        cargarModuloPlanillas();
        cargarModulosVentas();
        cargarModulosProductos();
        cargarModulosTerceros();
        cargarModulosCartera();
        cargarModulosTesoreria();
    }

    private void cargarModuloMaestra() {
        agregarFormulario(new VistaConfiguraciones(), instancias::setMaestra);
    }

    private void cargarModuloPlanillas() {
        agregarFormulario(new infPlantillas(), instancias::setPlantillas);
    }

    private void cargarModulosVentas() {
        ndUsuario usuario = instancias.getUsuarioLog();

        agregarFormulario(new creditos(), instancias::setFacturaCreditos, usuario.isCreditos());
        agregarFormulario(new VistaReportesVentas(), instancias::setReportesVentas);
        instancias.setPreparacion(new VistaPreparacion(this, true));
        agregarFormulario(new pedido(), instancias::setPedido, usuario.isPedidos());
        agregarFormulario(new planSepare(), instancias::setPlanSepare, usuario.isPlanSepares());
        agregarFormulario(new infFacturarLotes(), instancias::setFacturarLotes);
        agregarFormulario(new infCuentaCobro(), instancias::setCuentaCobro);
        agregarFormularioSeleccionado(new VistaDocumentos(), instancias::setReimpresion, usuario.isReimpresion());
        agregarFormulario(new ordenServicio(), instancias::setOrdenServicio, usuario.isOrdenesServicio());
        agregarFormularioSeleccionado(new cotizacion(), instancias::setCotiza, usuario.isCotizaciones());
    }

    private void cargarModuloSeleccionadoParaPantallaPrincipal() {
        agregarFormulario(new factura(), instancias::setFactura, instancias.getUsuarioLog().isFacturas());
        instancias.getMenu().cambiarTitulo("FACTURACIÓN");
    }

    private void cargarModulosProductos() {
        ndUsuario usuario = instancias.getUsuarioLog();

        agregarFormulario(new VistaProductos(), instancias::setProductos, usuario.isCreacionProductos());
        agregarFormulario(new VistaInventarioInicial(), instancias::setInventarioInicial, usuario.isInventarioInicial());
        agregarFormulario(new VistaGrupos(), instancias::setGrupos, usuario.isGrupos());
        agregarFormulario(new ordenCompra(), instancias::setOrdenCompra, usuario.isOrdenesCompras());
        agregarFormulario(new VistaAjusteInventario(), instancias::setuInt);
        agregarFormulario(new VistaDiseno(), instancias::setCosteo, usuario.isDisenos() || usuario.isArmados());
        agregarFormulario(new VistaReportesProducto(), instancias::setReportesProductos, usuario.isReporteProductos());
        agregarFormulario(new ingreso(), instancias::setIngresos, usuario.isCompras());
        agregarFormularioSeleccionado(new infTrasladosInternos(), instancias::setTrasladosInternos, usuario.isTrasladosInternos());
    }

    private void cargarModulosTerceros() {
        ndUsuario usuario = instancias.getUsuarioLog();

        agregarFormulario(new VistaContactos(""), instancias::setClientes, usuario.isCrearClientes());
        agregarFormularioSeleccionado(new infRepEmpleados(), instancias::setRepEmpleados, usuario.isReporteEmpleados());
        agregarFormularioSeleccionado(new infRepClientes(), instancias::setRepClientes, usuario.isReporteClientes());
    }

    private void cargarModulosTesoreria() {
        ndUsuario usuario = instancias.getUsuarioLog();

        agregarFormulario(new VistaEgresos(), instancias::setEgresos, usuario.isEgresos());
        agregarFormulario(new VistaCuadreCaja(), instancias::setCaja, usuario.isCuadreCaja());
        agregarFormulario(new VistaNotaCredito(), instancias::setNc, usuario.isNotasCredito());
        agregarFormulario(new VistaNotaDebito(), instancias::setNd, usuario.isNotasCredito());
        agregarFormularioSeleccionado(new infRepEgresos(), instancias::setRepEgresos, usuario.isReporteEgresos());
        agregarFormulario(new infRepCuadre(), instancias::setRepCuadre, usuario.isReporteCuadreCaja());
    }

    private void cargarModuloGeneracionRips() {
        agregarFormulario(new infRips(), instancias::setRips);
    }

    private void cargarModulosRecordatorios() {
        agregarFormulario(new infRecordatorios(), instancias::setRecordatorios);
    }

    private void cargarModulosCongeladas() {
        boolean permiso = instancias.getUsuarioLog().isCongeladas();

        agregarFormulario(new infMesa(), instancias::setMesa, permiso);
        agregarFormulario(new VistaMesas(), instancias::setMesas, permiso);
    }

    private void cargarModulosCartera() {
        ndUsuario usuario = instancias.getUsuarioLog();

        agregarFormularioSeleccionado(new VistaReportesCartera(), instancias::setReportesCartera);
        agregarFormularioSeleccionado(new VistaAbonos(), instancias::setAbonos, usuario.isAbonosCxC());
        agregarFormulario(new VistaPagos(), instancias::setPagos, usuario.isAbonosCxP());
    }

    private void cargarModulosAgenda() {
        ndUsuario usuario = instancias.getUsuarioLog();

        agregarFormulario(new infAgendaConsulta(), instancias::setAgendaConsulta, usuario.isRegistrarAgenda());
        agregarFormulario(new infRepAgenda(""), instancias::setRepAgenda, usuario.isReportesAgenda());
    }

    private void cargarModulosVeterinaria() {
        ndUsuario usuario = instancias.getUsuarioLog();

        agregarFormulario(new infAlertasProximas(), instancias::setAlertasProximas, usuario.isAlertas());
        agregarFormulario(new infFormatos(), instancias::setFormatos, usuario.isFormatos());
        agregarFormulario(new infMascotas(), instancias::setMascotas, usuario.isMascotas());
        agregarFormulario(new infHospitalizacion(), instancias::setHospitalizacion);
        agregarFormulario(new infIngresoHospitalizacion(), instancias::setIngresoHospitalizacion, usuario.isAlertas());
        agregarFormulario(new infRepMascotas(), instancias::setRepMascotas, usuario.isReporteMascotas());
        agregarFormulario(new infHistoria(), instancias::setHistoria, usuario.isHistoriaClinica());
        agregarFormulario(new infRepPeluqueria(), instancias::setRepPeluqueria, usuario.isReportePeluqueria());
        agregarFormulario(new infRepGuarderia(), instancias::setRepGuarderia, usuario.isReporteGuarderia());
        agregarFormulario(new infRepHospitalizacion(), instancias::setRepHospitalizacion, usuario.isReporteHospitalizacion());
        agregarFormulario(new infPeluqueria(), instancias::setPeluqueria, usuario.isPeluqueria());
        agregarFormulario(new infGuarderia(), instancias::setGuarderia, usuario.isGuarderia());
        agregarFormulario(new infQuirurgica(), instancias::setQuirurgica);
        agregarFormulario(new infRemisionVeterinaria(), instancias::setRemisionVeterinaria);
        agregarFormulario(new infFormulaMedicaVeterinaria(), instancias::setFormulaMedicaVeterinaria);
        agregarFormulario(new infAyudaDiagnosticoVeterinaria(), instancias::setAyudaDiagnosticoVeterinaria);
    }

    private void cargarModulosParqueadero() {
        agregarFormularioSeleccionado(new infMensualidad(), instancias::setMensualidad);
        agregarFormularioSeleccionado(new infRepMensualidades(), instancias::setRepMensualidades);
        agregarFormularioSeleccionado(new infRepParqueadero(), instancias::setRepParqueadero);
        agregarFormularioSeleccionado(new infParqueadero(), instancias::setParqueadero);
        agregarFormularioSeleccionado(new infRepLavadero(), instancias::setRepLavadero);
        agregarFormularioSeleccionado(new infRepLavadero1(), instancias::setRepLavadero1);
        agregarFormularioSeleccionado(new infLavadero(), instancias::setLavadero);
        agregarFormularioSeleccionado(new infRepHistorial(), instancias::setRepHistorial);
    }

    private void cargarModulosOftalmologia() {
        agregarFormulario(new VistaContactos("paciente"), instancias::setPaciente);
        agregarFormulario(new infHojaIngreso(), instancias::setHojaIngreso);
        agregarFormulario(new infAyudaDiagnosticoOf(), instancias::setAyudaDiagnosticoOf);
        agregarFormulario(new infFormulaMedicaOf(), instancias::setFormulaMedicaOf);
        agregarFormulario(new infIncapacidadOf(), instancias::setIncapacidadOf);
        agregarFormulario(new infFormulaLentesOf(), instancias::setFormulaLentes);
        agregarFormulario(new infBiometria(), instancias::setBiometria);
        agregarFormulario(new infPaquimetria(), instancias::setPaquimetria);
    }

    private void cargarModulosLaboratorio() {
        agregarFormulario(new VistaContactos("paciente"), instancias::setPaciente);
        agregarFormulario(new infAcidoUrico(), instancias::setAcidoUrico);
        agregarFormulario(new infHistoriaLaboratorio(), instancias::setHistoriaLaboratorio);
        agregarFormulario(new infPerfil(), instancias::setPerfil);
        agregarFormulario(new infEmbarazo(), instancias::setEmbarazo);
        agregarFormulario(new infGlicemia(), instancias::setGlicemia);
        agregarFormulario(new infKoh(), instancias::setKoh);
        agregarFormulario(new infProteina(), instancias::setProteina);
        agregarFormulario(new infTiempos(), instancias::setTiempos);
        agregarFormulario(new infSerologia(), instancias::setSerologia);
        agregarFormulario(new infHemoclasificacion(), instancias::setHemoclasificacion);
        agregarFormulario(new infHemograma(), instancias::setHemograma);
        agregarFormulario(new infCreatinina(), instancias::setCreatinina);
        agregarFormulario(new infDirecto(), instancias::setDirectos);
        agregarFormulario(new infAntiestreptolisina(), instancias::setAntiestreptosilina);
        agregarFormulario(new infSangreOculta(), instancias::setSangreOculta);
        agregarFormulario(new infAcidos(), instancias::setAcidos);
        agregarFormulario(new infHemoglobina(), instancias::setHemoglobina);
        agregarFormulario(new infCannabinoides(), instancias::setCannabinoides);
        agregarFormulario(new infCitoquimico(), instancias::setCitoquimico);
        agregarFormulario(new infCocaina(), instancias::setCocaina);
        agregarFormulario(new infCoprologico(), instancias::setCoprologico);
        agregarFormulario(new infCoprograma(), instancias::setCoprograma);
        agregarFormulario(new infColesterol(), instancias::setColesterol);
    }

    private void cargarModulosMedico() {
        ndUsuario usuario = instancias.getUsuarioLog();

        agregarFormulario(new infReportesMedico(), instancias::setReportesMedicos);
        agregarFormulario(new VistaContactos("paciente"), instancias::setPaciente, usuario.isCrearClientes());
        agregarFormulario(new infEps(), instancias::setEps);
        agregarFormulario(new infFormulaMedica(), instancias::setFormulaMedica);
        agregarFormulario(new infFormatosDeTextos(), instancias::setPlantillasDeTextos, usuario.isFormatosMedicas());
        agregarFormulario(new infOrdenServicioMedico(), instancias::setOrdenMedica, usuario.isOrdenesServicioMedica());
        agregarFormulario(new infRemision(), instancias::setRemision);
        agregarFormulario(new infContraremision(), instancias::setContraremision);
        agregarFormulario(new infIncapacidad(), instancias::setIncapacidad);
        agregarFormulario(new infHistoriaC(), instancias::setHistoriaC, usuario.isHistoria());
        agregarFormulario(new infAyudaDiagnostico(), instancias::setAyudaDiagnostica);
        agregarFormulario(new infConvenio(), instancias::setConvenio, usuario.isConvenio());
        agregarFormulario(new infNotaEnfermeria(), instancias::setNotaEnfermeria, usuario.isNotaEnfermaria());
        agregarFormulario(new infMedicamentos(), instancias::setMedicamentos, usuario.isMedicamentos());
        agregarFormulario(new infHistoriaMedica(), instancias::setHistoriaClinica, usuario.isHistoria());
    }

    public void soloMesas() {
        pnlContenedor.setVisible(false);
        pnlPrincipal.setVisible(false);
        pnlMenu.setVisible(false);
        btnAccesos.setVisible(false);
        sep3.setVisible(false);
        btnOcultar.setVisible(false);
        pnlMiniaturas.setVisible(false);
        btnOpciones.setEnabled(true);
    }

    public void actualizarMedicamentos(boolean cambiarColor) {
        hayAlertaMedicamentos = cambiarColor;
        String icono = cambiarColor ? "/imagenes/medicina2.png" : "/imagenes/medicina1.png";
        actualizarIndicadorOpciones();
    }

    public void actualizarPermisos(int cant, boolean cambiarColor, Boolean actualizarTexto) {
        if (actualizarTexto) {
            itemSolicitudes.setText("Historial de solicitudes (" + cant + ")");
        }

        hayAlertaSolicitudes = cambiarColor;
        String icono = cambiarColor ? "/imagenes/buzonRojo.png" : "/imagenes/buzonBlanco.png";
        actualizarIndicadorOpciones();
    }

    public void cambiarTitulo(String titulo) {
        lbTitulo.setText(titulo);
    }

    public void activarBotonAbrirCaja() {
        btnAbrirCaja.setVisible(DatosMaestra.getTipoImpresionPredeterminado().equals("pos"));
    }

    public void llamarRecordatorio() {
        seleccionarModulo(ModuloMenu.AGENDA);
        infRecordatorios inf = new infRecordatorios();
        inf.llamarRecordatorios();
    }

    public JDesktopPane getDkpFormularios() {
        return dkpFormularios;
    }

    public void setDkpFormularios(JDesktopPane dkpFormularios) {
        this.dkpFormularios = dkpFormularios;
    }

    public void ocultarMenu(String desde) {
        pnlContenedor.setVisible(false);
        pnlMenu.setSize(0, 0);
        seVeElMenu = false;

        if (desde.equals("preparacion")) {
            noAbrir = false;
            btnOcultar.setEnabled(false);
            btnOpciones.setEnabled(false);
            pnlMiniaturas.setVisible(false);
            btnAccesos.setEnabled(false);
        } else {
            pnlMiniaturas.setVisible(true);
        }
    }

    public void expandirMenu() {
        if (esVendedorSoloMesas()) {
            soloMesas();
            return;
        }

        mostrarMenuCompleto();
    }

    private boolean esVendedorSoloMesas() {
        if (instancias.getUsuario().equals("ADMIN")) {
            return false;
        }
        if (!instancias.getConfiguraciones().isRestaurante()) {
            return false;
        }
        if (!DatosMaestra.isSoloMesas()) {
            return false;
        }

        String usuario = instancias.getUsuario();
        for (Object[] vendedor : instancias.getSql().getVendedores1()) {
            try {
                if (usuario.equals(vendedor[1].toString())) {
                    return true;
                }
            } catch (Exception ignored) {
            }
        }
        return false;
    }

    public void activarBoton() {
        btnOcultar.setEnabled(true);
    }

    private void mostrarMenuCompleto() {
        noAbrir = true;
        pnlMiniaturas.setVisible(false);
        pnlContenedor.setVisible(true);
        btnOcultar.setEnabled(true);
        pnlMenu.setBounds(MENU.x, MENU.y, MENU.width, this.getHeight());
        dkpFormularios.setBounds(323, 128, this.getWidth() - 338, this.getHeight() - 166);
        seVeElMenu = true;
        btnOpciones.setEnabled(true);
        btnAccesos.setEnabled(true);
    }

    public boolean getSeVeElMenu() {
        return pnlContenedor.isVisible();
    }

    public void accionarMenu() {
        if (instancias.getMenu().getSeVeElMenu()) {
            instancias.getMenu().ocultarMenu("");
            btnOcultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/derecha.png")));
        } else {
            instancias.getMenu().expandirMenu();
            btnOcultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/izquierda.png")));
        }
    }

    public void cargarSocket() {
        try {
            SERVER_SOCKETE = new ServerSocket(1779);
            System.out.println("Inicio el server");
        } catch (Exception e) {
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VistaMenu().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbrirCaja;
    private javax.swing.JButton btnAccesos;
    private javax.swing.JButton btnOcultar;
    private javax.swing.JButton btnOpciones;
    private javax.swing.JButton btnVerificar;
    private javax.swing.JDesktopPane dkpFormularios;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private formularios.JPanelsSliding jPanelsSliding1;
    private javax.swing.JButton lbLogo;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JPanel pnlContenedor;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JPanel pnlMiniaturas;
    private javax.swing.JPanel pnlModulos;
    private javax.swing.JPanel pnlNavbar;
    private javax.swing.JPanel pnlPrincipal;
    private javax.swing.JSeparator sep3;
    // End of variables declaration//GEN-END:variables

}
