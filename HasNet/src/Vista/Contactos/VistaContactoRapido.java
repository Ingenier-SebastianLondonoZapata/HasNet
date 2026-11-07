package Vista.Contactos;

import Consumidor.Contactos.ConsumidorContactos;
import Controlador.Alertas.ControladorAlertas;
import Dao.Terceros.DaoContactos;
import Enums.TipoContacto;
import Enums.enumTipoIdentificacion;
import Modelo.Contactos.ContactoApiDTO;
import Modelo.Contactos.ModeloConsultaContacto;
import Modelo.Contactos.ModeloContacto;
import Utilidades.Contactos.NombreContacto;
import Utilidades.Contactos.Regiones;
import Validaciones.Contactos.SquemaContactoRapido;
import clases.Instancias;
import clases.metodosGenerales;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

public class VistaContactoRapido extends javax.swing.JDialog {

    private static final String TIPO_IDENTIFICACION_PREDETERMINADO = "Cédula de ciudadanía";
    private static final String TIPO_IDENTIFICACION_NIT = "Nit";
    private static final Color COLOR_OBLIGATORIO = new Color(255, 204, 204);

    private final SquemaContactoRapido validaciones = new SquemaContactoRapido();
    private final DaoContactos daoContactos = new DaoContactos();
    private final ConsumidorContactos consumidorContactos = new ConsumidorContactos();

    private boolean aceptado;
    private boolean contactoNuevo;
    private boolean facturaElectronica;
    private ModeloContacto contacto;

    public VistaContactoRapido(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        configurarNavegacion();
        this.getRootPane().registerKeyboardAction(e -> cancelar(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    public void preparar(TipoContacto tipoContacto, String busqueda, boolean facturaElectronica) {
        this.aceptado = false;
        this.contactoNuevo = true;
        this.contacto = null;
        this.facturaElectronica = facturaElectronica;

        btnConsultarApi.setVisible(facturaElectronica);

        cmbTipoContacto.setSelectedItem((tipoContacto == null ? TipoContacto.TERCERO : tipoContacto).getValor());
        Regiones.cargarDepartamentos(cmbDepartamento);
        Regiones.cargarCiudades(cmbCiudad, cmbDepartamento.getSelectedItem());
        Regiones.cargarCodigosPostales(cmbCodigoPostal, cmbDepartamento.getSelectedItem(), cmbCiudad.getSelectedItem());

        lbCorreo.setText(facturaElectronica ? "Correo electrónico:" : "Correo (opcional):");
        txtCorreo.setBackground(facturaElectronica ? COLOR_OBLIGATORIO : Color.WHITE);

        precargarBusqueda(busqueda == null ? "" : busqueda.trim());

        pack();
        setLocationRelativeTo(null);
    }

    public boolean isAceptado() {
        return aceptado;
    }

    public ModeloContacto getContacto() {
        return contacto;
    }

    public boolean isContactoNuevo() {
        return contactoNuevo;
    }

    private void consultarEnApi() {
        String tipoIdentificacion = enumTipoIdentificacion.obtenerTipoIdentificacion(seleccion(cmbTipoIdentificacion));

        if (tipoIdentificacion.isEmpty()) {
            ControladorAlertas.alert("El tipo de identificación seleccionado no se puede consultar");
            cmbTipoIdentificacion.requestFocus();
            return;
        }

        if (identificacion().isEmpty()) {
            ControladorAlertas.alert("Debe ingresar el número de identificación");
            txtIdentificacion.requestFocus();
            return;
        }

        if (seleccionarSiYaExiste()) {
            return;
        }

        ContactoApiDTO datos = consumidorContactos.consultarContacto(
                new ModeloConsultaContacto(tipoIdentificacion, identificacionSinDigito()));

        if (datos == null) {
            return;
        }

        txtNombre.setText(texto(datos.getNombre()).toUpperCase());
        txtCorreo.setText(texto(datos.getEmail()));
        ControladorAlertas.alertSuccess("Datos cargados, complete la información y guarde el contacto");
        txtTelefono.requestFocus();
    }

    private boolean seleccionarSiYaExiste() {
        ModeloContacto existente = buscarExistente();

        if (existente == null) {
            return false;
        }

        ControladorAlertas.alert("El contacto ya está registrado, se seleccionará");
        contacto = existente;
        contactoNuevo = false;
        aceptado = true;
        dispose();
        return true;
    }

    private ModeloContacto buscarExistente() {
        String completa = identificacion();
        if (completa.isEmpty()) {
            return null;
        }

        ModeloContacto existente = daoContactos.consultarPorIdentificacion(completa);
        String sinDigito = identificacionSinDigito();

        if (existente == null && !sinDigito.equals(completa)) {
            existente = daoContactos.consultarPorIdentificacion(sinDigito);
        }
        return existente;
    }

    private void precargarBusqueda(String busqueda) {
        boolean esIdentificacion = busqueda.matches("[0-9][0-9\\-]*");

        txtIdentificacion.setText(esIdentificacion ? busqueda : "");
        txtNombre.setText(esIdentificacion ? "" : busqueda.toUpperCase());
        txtTelefono.setText("");
        txtCorreo.setText("");
        cmbTipoIdentificacion.setSelectedItem(busqueda.contains("-") ? TIPO_IDENTIFICACION_NIT : TIPO_IDENTIFICACION_PREDETERMINADO);

        if (esIdentificacion) {
            txtNombre.requestFocus();
        } else {
            txtIdentificacion.requestFocus();
        }
    }

    private void configurarNavegacion() {
        Component[] orden = {cmbTipoContacto, cmbTipoIdentificacion, txtIdentificacion, txtNombre, txtTelefono, txtCorreo,
            cmbDepartamento, cmbCiudad, cmbCodigoPostal};

        for (int i = 0; i < orden.length; i++) {
            final Component siguiente = i + 1 < orden.length ? orden[i + 1] : btnAceptar;
            final boolean esUltimo = i + 1 == orden.length;
            orden[i].addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent evt) {
                    if (evt.getKeyCode() != KeyEvent.VK_ENTER) {
                        return;
                    }
                    if (esUltimo) {
                        guardar();
                    } else {
                        siguiente.requestFocus();
                    }
                }
            });
        }

        txtTelefono.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                if (!Character.isDigit(evt.getKeyChar())) {
                    evt.consume();
                }
            }
        });
    }

    private void guardar() {
        ModeloContacto capturado = leerFormulario();
        ModeloContacto existente = buscarExistente();

        if (!validaciones.validarContacto(capturado, facturaElectronica, existente)) {
            return;
        }

        contacto = capturado;
        aceptado = true;
        dispose();
    }

    private void cancelar() {
        aceptado = false;
        dispose();
    }

    private ModeloContacto leerFormulario() {
        Instancias instancias = Instancias.getInstancias();
        String fecha = new metodosGenerales().fechaConsulta(metodosGenerales.fecha());
        TipoContacto tipoContacto = TipoContacto.desdeValor(seleccion(cmbTipoContacto));
        String tipoIdentificacion = seleccion(cmbTipoIdentificacion);
        boolean esNit = TIPO_IDENTIFICACION_NIT.equals(tipoIdentificacion);
        String nombre = txtNombre.getText().trim().toUpperCase();
        NombreContacto partes = NombreContacto.repartir(nombre);

        ModeloContacto datos = new ModeloContacto();
        datos.setIdSistema("");
        datos.setId(identificacion());
        datos.setTipo(tipoIdentificacion);
        datos.setTipoTercero(tipoContacto.getValor());
        datos.setNombre(nombre);
        datos.setCompleta(nombre);
        datos.setpNombre(partes.getPrimerNombre());
        datos.setsNombre(partes.getSegundoNombre());
        datos.setpApellido(partes.getPrimerApellido());
        datos.setsApellido(partes.getSegundoApellido());

        datos.setTelefono(txtTelefono.getText().trim());
        datos.setCelular("");
        datos.setDireccion("");
        datos.setEmail(txtCorreo.getText().trim());
        datos.setDepartamento(seleccion(cmbDepartamento));
        datos.setCiudad(seleccion(cmbCiudad));
        datos.setCodigoPostal(seleccion(cmbCodigoPostal));
        datos.setPais("Colombia");

        datos.setFecha(fecha);
        datos.setNacimiento(fecha);
        datos.setUsuario(instancias.getUsuario());
        datos.setTerminal(instancias.getTerminal());
        datos.setActivo(false);

        datos.setNaturaleza(esNit ? "Persona juridica" : "Persona natural");
        datos.setRut(esNit ? "Si" : "No");
        datos.setResponsableIva(false);
        datos.setZona("Urbana");

        datos.setOcupacion("");
        datos.setActividad("");
        datos.setSexo("");
        datos.setEstado("");
        datos.setRegimen("");
        datos.setAfiliado("");
        datos.setTipoTrabajador("");
        datos.setSangre("");
        datos.setEps(null);
        datos.setPlazo("0");
        datos.setCupo("0");
        datos.setSalario("0");
        datos.setReferido("");
        datos.setVendedor("");
        datos.setLista("");
        datos.setNota("");
        datos.setConvenio("");
        datos.setConvenioActual("");
        datos.setReligion("");
        datos.setNombreMadre("");
        datos.setNombrePadre("");
        datos.setNombreResponsable("");
        datos.setParentescoResponsable("");
        datos.setTelefonoResponsable("");
        datos.setLugarNacimiento("");
        datos.setBarrio("");
        datos.setNombreContacto("");
        datos.setCargo("");
        datos.setPlacas("");
        datos.setTipo2("");
        datos.setRango("");
        datos.setTelefonoContacto("");
        datos.setWeb("");
        datos.setRnt("");
        datos.setTipoProveedor("");
        datos.setCodigoUsuario("");
        datos.setUsuarioAsociado("");
        datos.setArl("");
        datos.setCcf("");
        datos.setAgendar(false);

        if (tipoContacto == TipoContacto.EMPLEADO) {
            datos.setIngreso(fecha);
        }

        return datos;
    }

    private String identificacion() {
        String valor = txtIdentificacion.getText().trim().replace(" ", "");
        while (valor.endsWith("-")) {
            valor = valor.substring(0, valor.length() - 1);
        }
        return valor;
    }

    private String identificacionSinDigito() {
        String valor = identificacion();
        int guion = valor.indexOf('-');
        return guion < 0 ? valor : valor.substring(0, guion);
    }

    private String texto(String valor) {
        return valor == null ? "" : valor.trim();
    }

    private String seleccion(JComboBox combo) {
        Object item = combo.getSelectedItem();
        return item == null ? "" : item.toString().trim();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlContenido = new javax.swing.JPanel();
        lbTipoContacto = new javax.swing.JLabel();
        cmbTipoContacto = new javax.swing.JComboBox();
        lbTipoIdentificacion = new javax.swing.JLabel();
        cmbTipoIdentificacion = new javax.swing.JComboBox();
        lbIdentificacion = new javax.swing.JLabel();
        txtIdentificacion = new javax.swing.JTextField();
        btnConsultarApi = new javax.swing.JButton();
        lbNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lbTelefono = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        lbCorreo = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        lbDepartamento = new javax.swing.JLabel();
        cmbDepartamento = new javax.swing.JComboBox();
        lbCiudad = new javax.swing.JLabel();
        cmbCiudad = new javax.swing.JComboBox();
        lbCodigoPostal = new javax.swing.JLabel();
        cmbCodigoPostal = new javax.swing.JComboBox();
        btnCancelar = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nuevo contacto");
        setResizable(false);

        pnlContenido.setBackground(new java.awt.Color(255, 255, 255));

        lbTipoContacto.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbTipoContacto.setText("Tipo de contacto:");

        cmbTipoContacto.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cmbTipoContacto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TERCERO", "PROVEEDOR", "EMPLEADO" }));

        lbTipoIdentificacion.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbTipoIdentificacion.setText("Tipo de identificación:");

        cmbTipoIdentificacion.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cmbTipoIdentificacion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Cédula de ciudadanía", "Nit", "Cédula de extranjería", "Pasaporte", "Registro civil", "Tarjeta de identidad", "Adulto sin identificación", "Menor sin identificación", "Número único de identificación" }));

        lbIdentificacion.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbIdentificacion.setText("Identificación:");

        txtIdentificacion.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        btnConsultarApi.setBackground(new java.awt.Color(255, 255, 255));
        btnConsultarApi.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        btnConsultarApi.setText("Consultar datos");
        btnConsultarApi.setToolTipText("Consulta el nombre y el correo de la persona en el servicio de facturación electrónica");
        btnConsultarApi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnConsultarApi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarApiActionPerformed(evt);
            }
        });

        lbNombre.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbNombre.setText("Nombre o razón social:");

        txtNombre.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lbTelefono.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbTelefono.setText("Teléfono:");

        txtTelefono.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lbCorreo.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbCorreo.setText("Correo electrónico:");

        txtCorreo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lbDepartamento.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbDepartamento.setText("Departamento:");

        cmbDepartamento.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cmbDepartamento.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbDepartamentoItemStateChanged(evt);
            }
        });

        lbCiudad.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbCiudad.setText("Ciudad:");

        cmbCiudad.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cmbCiudad.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbCiudadItemStateChanged(evt);
            }
        });

        lbCodigoPostal.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbCodigoPostal.setText("Código postal:");

        cmbCodigoPostal.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        btnCancelar.setBackground(new java.awt.Color(241, 148, 138));
        btnCancelar.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnAceptar.setBackground(new java.awt.Color(46, 204, 113));
        btnAceptar.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnAceptar.setText("Guardar");
        btnAceptar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlContenidoLayout = new javax.swing.GroupLayout(pnlContenido);
        pnlContenido.setLayout(pnlContenidoLayout);
        pnlContenidoLayout.setHorizontalGroup(
            pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContenidoLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlContenidoLayout.createSequentialGroup()
                        .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbTipoContacto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbTipoIdentificacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbIdentificacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbCorreo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbDepartamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbCiudad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbCodigoPostal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(8, 8, 8)
                        .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbTipoContacto, 0, 320, Short.MAX_VALUE)
                            .addComponent(cmbTipoIdentificacion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlContenidoLayout.createSequentialGroup()
                                .addComponent(txtIdentificacion)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnConsultarApi))
                            .addComponent(txtNombre)
                            .addComponent(txtTelefono)
                            .addComponent(txtCorreo)
                            .addComponent(cmbDepartamento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbCiudad, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbCodigoPostal, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlContenidoLayout.createSequentialGroup()
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15))
        );
        pnlContenidoLayout.setVerticalGroup(
            pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContenidoLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTipoContacto)
                    .addComponent(cmbTipoContacto, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTipoIdentificacion)
                    .addComponent(cmbTipoIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbIdentificacion)
                    .addComponent(txtIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConsultarApi, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNombre)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTelefono)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCorreo)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDepartamento)
                    .addComponent(cmbDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCiudad)
                    .addComponent(cmbCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCodigoPostal)
                    .addComponent(cmbCodigoPostal, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlContenido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlContenido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbDepartamentoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbDepartamentoItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            Regiones.cargarCiudades(cmbCiudad, cmbDepartamento.getSelectedItem());
        }
    }//GEN-LAST:event_cmbDepartamentoItemStateChanged

    private void cmbCiudadItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbCiudadItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            Regiones.cargarCodigosPostales(cmbCodigoPostal, cmbDepartamento.getSelectedItem(), cmbCiudad.getSelectedItem());
        }
    }//GEN-LAST:event_cmbCiudadItemStateChanged

    private void btnConsultarApiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarApiActionPerformed
        consultarEnApi();
    }//GEN-LAST:event_btnConsultarApiActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        cancelar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        guardar();
    }//GEN-LAST:event_btnAceptarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConsultarApi;
    private javax.swing.JComboBox cmbCiudad;
    private javax.swing.JComboBox cmbCodigoPostal;
    private javax.swing.JComboBox cmbDepartamento;
    private javax.swing.JComboBox cmbTipoContacto;
    private javax.swing.JComboBox cmbTipoIdentificacion;
    private javax.swing.JLabel lbCiudad;
    private javax.swing.JLabel lbCodigoPostal;
    private javax.swing.JLabel lbCorreo;
    private javax.swing.JLabel lbDepartamento;
    private javax.swing.JLabel lbIdentificacion;
    private javax.swing.JLabel lbNombre;
    private javax.swing.JLabel lbTelefono;
    private javax.swing.JLabel lbTipoContacto;
    private javax.swing.JLabel lbTipoIdentificacion;
    private javax.swing.JPanel pnlContenido;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtIdentificacion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
