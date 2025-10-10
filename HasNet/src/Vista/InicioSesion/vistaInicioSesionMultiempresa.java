package Vista.InicioSesion;

import Controlador.Alertas.ControladorAlertas;
import DisenoUX.disenoMultiempresas;
import Utilidades.Constantes;
import clases.Instancias;
import clases.metodosGenerales;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;

public class vistaInicioSesionMultiempresa extends javax.swing.JFrame {

    private disenoMultiempresas panelMultiempresa;
    private final Instancias instancias = Instancias.getInstancias();
    private final ControladorAlertas alertas = new ControladorAlertas();
    private final metodosGenerales metodos = new metodosGenerales();

    public vistaInicioSesionMultiempresa() {
        initComponents();

        obtenerEmpresasRegistradas();
        this.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        lbTitulo = new javax.swing.JLabel();
        btnSalida = new javax.swing.JLabel();
        pnlServicios = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("HS.NET");
        setBackground(new java.awt.Color(51, 51, 51));
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/icono.png")).getImage());
        setUndecorated(true);
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        lbTitulo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitulo.setText("Bienvenidos a System - Pro. La mejor solución para tus problemas");

        btnSalida.setFont(new java.awt.Font("Arial Black", 0, 24)); // NOI18N
        btnSalida.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnSalida.setText("X");
        btnSalida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSalidaMouseClicked(evt);
            }
        });

        pnlServicios.setBackground(new java.awt.Color(255, 255, 255));
        pnlServicios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlServiciosMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlServiciosLayout = new javax.swing.GroupLayout(pnlServicios);
        pnlServicios.setLayout(pnlServiciosLayout);
        pnlServiciosLayout.setHorizontalGroup(
            pnlServiciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlServiciosLayout.setVerticalGroup(
            pnlServiciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 510, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pnlServicios, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbTitulo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE))
                        .addGap(12, 12, 12))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(btnSalida)
                .addGap(5, 5, 5)
                .addComponent(lbTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlServicios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalidaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalidaMouseClicked
        System.exit(0);
    }//GEN-LAST:event_btnSalidaMouseClicked

    private void pnlServiciosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlServiciosMouseClicked
        this.dispose();
    }//GEN-LAST:event_pnlServiciosMouseClicked

    private void obtenerEmpresasRegistradas() {
        Object[][] datos = instancias.getSql().getMultiempresas();
        if (datos.length == 0) {
            datos = obtenerRegistroPorDefecto();
        }

        cargarEmpresasAlPanel(datos);
    }

    private void cargarEmpresasAlPanel(Object[][] datos) {
        panelMultiempresa = new disenoMultiempresas(datos, this);
        panelMultiempresa.setVisible(true);

        pnlServicios.setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(panelMultiempresa);
        pnlServicios.add(scrollPane);
    }

    private Object[][] obtenerRegistroPorDefecto() {
        Object[][] registroPorDefecto = new Object[1][3];
        registroPorDefecto[0][1] = "PRINCIPAL";
        registroPorDefecto[0][2] = Constantes.BASE_DATOS_PRINCIPAL;
        return registroPorDefecto;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnSalida;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JPanel pnlServicios;
    // End of variables declaration//GEN-END:variables
}
