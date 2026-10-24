package Validaciones.Terceros;

import Controlador.Alertas.ControladorAlertas;
import Dao.Terceros.DaoVehiculos;
import Modelo.Terceros.ModeloDatosVehiculo;
import java.util.ArrayList;
import java.util.List;

public class squemaDatosVehiculo extends javax.swing.JPanel {

    private final DaoVehiculos daoVehiculos = new DaoVehiculos();

    public squemaDatosVehiculo() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    public boolean validarVehiculo(ModeloDatosVehiculo datosVehiculo, List<String> placasRegistradas, String idSistemaContacto) {

        List<Object> errores_validacion = new ArrayList<>();
        String placa = texto(datosVehiculo.getPlaca());

        if (placa.isEmpty()) {
            errores_validacion.add("Debe ingresar la placa del vehículo");
        } else {
            if (estaRegistrada(placa, placasRegistradas)) {
                errores_validacion.add("La placa " + placa + " ya está registrada en este contacto");
            } else if (daoVehiculos.existePlacaDeOtroContacto(placa, idSistemaContacto)) {
                errores_validacion.add("La placa " + placa + " ya pertenece a otro contacto");
            }
        }

        if (texto(datosVehiculo.getTipoVehiculo()).isEmpty()) {
            errores_validacion.add("Debe ingresar el tipo del vehículo");
        }

        if (texto(datosVehiculo.getMarca()).isEmpty()) {
            errores_validacion.add("Debe ingresar la marca del vehículo");
        }

        String modelo = texto(datosVehiculo.getModelo());
        if (modelo.isEmpty()) {
            errores_validacion.add("Debe ingresar el modelo del vehículo");
        } else if (!modelo.matches("\\d{4}")) {
            errores_validacion.add("El modelo del vehículo debe ser un año de 4 dígitos");
        }

        if (errores_validacion.size() > 0) {
            ControladorAlertas.alertaGrandeListado("Se presentaron los siguientes errores...", errores_validacion, false);
            return false;
        } else {
            return true;
        }
    }

    private boolean estaRegistrada(String placa, List<String> placasRegistradas) {
        if (placasRegistradas == null) {
            return false;
        }

        for (String registrada : placasRegistradas) {
            if (placa.equalsIgnoreCase(texto(registrada))) {
                return true;
            }
        }
        return false;
    }

    private String texto(String valor) {
        return valor == null ? "" : valor.trim();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
