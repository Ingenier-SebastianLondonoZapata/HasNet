package Validaciones.Contactos;

import Controlador.Alertas.ControladorAlertas;
import Enums.TipoContacto;
import Modelo.Contactos.ModeloContacto;
import clases.metodosGenerales;
import java.util.ArrayList;
import java.util.List;

public class SquemaDatosContacto extends javax.swing.JPanel {

    public SquemaDatosContacto() {
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

    public boolean validarContacto(ModeloContacto datos, TipoContacto tipoContacto, boolean esPaciente,
            boolean esFacturacionElectronica, boolean esNuevo, ModeloContacto existente) {

        List<Object> errores = new ArrayList<>();
        boolean esEmpleado = tipoContacto == TipoContacto.EMPLEADO;

        if (estaVacio(datos.getTipo())) {
            errores.add("Debe ingresar el tipo de identificación");
        }

        if (estaVacio(datos.getId())) {
            errores.add("Debe ingresar la identificación");
        } else if (existente != null && (esNuevo || !datos.getIdSistema().equals(existente.getIdSistema()))) {
            errores.add(esEmpleado ? "El empleado ya existe" : esPaciente ? "El paciente ya existe" : "El cliente ya existe");
        }

        if (estaVacio(datos.getNombre())) {
            errores.add("Debe ingresar el nombre");
        }

        if (esEmpleado) {
            validarEmpleado(datos, errores);
        }

        if (!estaVacio(datos.getEmail()) && !metodosGenerales.emailEsCorrecto(datos.getEmail())) {
            errores.add("Debe ingresar un correo electrónico correcto");
        }

        if (esPaciente || (esFacturacionElectronica && !esEmpleado)) {
            if (estaVacio(datos.getDepartamento())) {
                errores.add("Debe seleccionar el departamento");
            }

            if (estaVacio(datos.getCiudad())) {
                errores.add("Debe seleccionar la ciudad");
            }
        }

        if (esFacturacionElectronica && !esEmpleado) {
            if (estaVacio(datos.getEmail())) {
                errores.add("Debe ingresar el correo electrónico");
            }

            if (estaVacio(datos.getCodigoPostal())) {
                errores.add("Debe seleccionar el código postal");
            }
        }

        if (esPaciente) {
            validarPaciente(datos, errores);
        }

        if (!errores.isEmpty()) {
            ControladorAlertas.alertaGrandeListado("Se presentaron los siguientes errores...", errores, false);
            return false;
        }
        return true;
    }

    private void validarEmpleado(ModeloContacto datos, List<Object> errores) {
        if (estaVacio(datos.getCargo())) {
            errores.add("Debe ingresar el cargo");
        }

        if (estaVacio(datos.getTelefono()) && estaVacio(datos.getCelular()) && estaVacio(datos.getEmail())) {
            errores.add("Debe ingresar al menos un medio de comunicación: teléfono, celular o correo electrónico");
        }
    }

    private void validarPaciente(ModeloContacto datos, List<Object> errores) {
        if (estaVacio(datos.getEps())) {
            errores.add("Debe cargar una EPS");
        }

        if (estaVacio(datos.getRegimen())) {
            errores.add("Debe seleccionar un régimen");
        }

        if (estaVacio(datos.getAfiliado())) {
            errores.add("Debe seleccionar el tipo de afiliado");
        }

        if (estaVacio(datos.getTipoTrabajador())) {
            errores.add("Debe seleccionar el tipo de trabajador");
        }
    }

    private boolean estaVacio(String valor) {
        return valor == null || valor.trim().isEmpty();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
