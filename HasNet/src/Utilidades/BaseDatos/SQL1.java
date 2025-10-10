package Utilidades.BaseDatos;

import clases.metodosGenerales;
import Modelo.Maestra.modeloConfiguracion;
import clases.big;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQL1 {

    private metodosGenerales metodos = new metodosGenerales();
    private static MySql_connection1 mysql;

    public void obtenerConexion() {
        this.mysql = MySql_connection1.getMySql_connection();
    }

    public MySql_connection1 getMysql() {
        return mysql;
    }

    public String obtenerFecha() {
        String instruccion_sql = "SELECT CURRENT_DATE() AS FECHA; ";
        String[] colName = {"FECHA"};
        boolean[] cadena = {true};
        return GetRegistro(colName, cadena, instruccion_sql)[0].toString();
    }

    public Object[] obtenerNumeroConsecutivo(String idCliente) {
        String instruccion_sql = "select numeroFEDisponibles from bdClientes where idCliente = '" + idCliente + "'; ";
        String[] colName = {"numeroFEDisponibles"};
        boolean[] cadena = {true};
        return GetRegistro(colName, cadena, instruccion_sql);
    }

    public boolean disminuirConsecutivo(String idCliente, int numeroFinal) {
        String instruccion_sql = "update bdClientes set numeroFEDisponibles=?" + " where idCliente = '" + idCliente + "' ;";
        return Actualizar_Registro(new Object[]{"", numeroFinal}, null, instruccion_sql);
    }

    public boolean agregarRegistroFactura(String idCliente, String numeroFactura, String fecha) {
        String instruccion_sql = "insert into bdRegistrosClientes(idCliente, numeroFactura, fecha) values (?,?,?);";
        return Agregar_Registro(new Object[]{idCliente, numeroFactura, fecha}, null, instruccion_sql);
    }

    public modeloConfiguracion obtenerConfiguracionCliente(String idCliente) {
        modeloConfiguracion modelo = new modeloConfiguracion();
        String instruccion_sql = "select regimen, informacionLegal, tipoImpresion, nit, nombre, telefono, fechaInicio, diasAntesAlertaBloqueo,"
                + "diasDespuesGabelaBloqueo, numeroFEDisponibles, congeladas, medico, veterinaria, parqueadero, ordenServicio, creditos, separe, pedido, agenda, restaurante, "
                + "recordatorios, laboratorio, servicioAutomotor, oftalmologia, inventarioBodegas, productosSerial, facturacionLote, usb, "
                + "facturaElectronica, pruebasFacturacion from bdClientes where idCliente = '" + idCliente + "' ";
        String[] colName = {"regimen", "informacionLegal", "tipoImpresion", "nit", "nombre", "telefono", "fechaInicio", "diasAntesAlertaBloqueo", "diasDespuesGabelaBloqueo",
            "numeroFEDisponibles", "congeladas", "medico", "veterinaria", "parqueadero", "ordenServicio", "creditos", "separe", "pedido", "agenda", "restaurante",
            "recordatorios", "laboratorio", "servicioAutomotor", "oftalmologia", "inventarioBodegas", "productosSerial", "facturacionLote", 
            "usb", "facturaElectronica", "pruebasFacturacion"};
        boolean[] cadena = {true, true, true, true, true, true, true, true, true, true, false, false, false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false, false, false
        };

        return modelo.llenarConfiguracion(GetRegistro(colName, cadena, instruccion_sql));
    }

    public Object[][] obtenerRegistroPago(String idCliente) {
        String colName[] = {"idPago", "nit", "nombre", "fechaLimite", "diasAntesAlertaBloqueo", "diasDespuesGabelaBloqueo", "bdClientes.idCliente", "fechaPago", "tipoIdentificacion"};
        String origen = " bdPagos INNER JOIN bdClientes ON bdPagos.idCliente = bdClientes.idCliente ";
        return getDatos(colName, origen,
                "SELECT idPago, nit, nombre, fechaLimite, diasAntesAlertaBloqueo, diasDespuesGabelaBloqueo, bdClientes.idCliente, fechaPago, tipoIdentificacion "
                + "FROM bdPagos INNER JOIN bdClientes ON bdPagos.idCliente = bdClientes.idCliente WHERE estado = 'PENDIENTE' "
                + "and bdClientes.idCliente = '" + idCliente + "' ",
                " WHERE estado = 'PENDIENTE' and bdClientes.idCliente = '" + idCliente + "' ");
    }

    private Object[] GetRegistro(String colName[], boolean cadena[], String sql) {
        Object[] data = new Object[colName.length];
        try {
            PreparedStatement pstm = mysql.getConnection().prepareStatement(sql);
            ResultSet res = pstm.executeQuery();
            while (res.next()) {
                for (int j = 0; j <= colName.length - 1; j++) {
                    if (cadena[j]) {
                        data[j] = res.getString(colName[j]);
                    } else {
                        data[j] = res.getBoolean(colName[j]);
                    }
                }
            }
            res.close();
            pstm.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return data;
    }

    public Object[][] getDatos(String colName[], String tabla, String sql, String condicion) {
        int registros = 0;
        //obtenemos la cantidad de registros existentes en la tabla
        try {
            PreparedStatement pstm = mysql.getConnection().prepareStatement("SELECT count(*) as total FROM " + tabla + condicion);
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
            pstm.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        Object[][] data = new String[registros][colName.length];
        String col[] = new String[colName.length];

        //realizamos la consulta sql y llenamos los datos en "Object"
        try {
            PreparedStatement pstm = mysql.getConnection().prepareStatement(sql);
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while (res.next()) {
                for (int j = 0; j <= colName.length - 1; j++) {
                    col[j] = res.getString(colName[j]);
                    data[i][j] = col[j];
                }
                i++;
            }
            res.close();
            pstm.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return data;
    }

    private boolean Actualizar_Registro(Object[] datos, Object[] valores, String instruccion_sql) {
        boolean ok = false;
        try {
            int i = 1;
            PreparedStatement pstm = mysql.getConnection().prepareStatement(instruccion_sql);

            if (datos != null) {
                for (i = 1; i < datos.length; i++) {

                    try {
                        pstm.setBoolean(i, (boolean) datos[i]);
                    } catch (Exception e) {
                        try {
                            pstm.setInt(i, (int) datos[i]);
                        } catch (Exception ex) {
                            try {
                                pstm.setString(i, (String) datos[i]);
                            } catch (ArrayIndexOutOfBoundsException ee) {
                            }
                        }
                    }
                }
            }

            if (valores != null) {
                for (Object valor : valores) {
                    try {
                        pstm.setBigDecimal(i++, big.getBigDecimal(valor));
                    } catch (NumberFormatException d) {
                        System.out.println(d + " Error, no es numero");
                    }
                }
            }

            pstm.execute();
            pstm.close();
            ok = true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return ok;
    }

    public boolean Agregar_Registro(Object[] datos, Object[] valores, String instruccion_sql) {

        boolean ok = false;
        try {
            try (PreparedStatement pstm = mysql.getConnection().prepareStatement(instruccion_sql)) {
                int i = 0;

                for (i = 0; i < datos.length; i++) {

                    if (datos[i] == null) {
                        pstm.setString(i + 1, (String) null);
                    } else {
                        try {
                            pstm.setBoolean(i + 1, (boolean) datos[i]);
                        } catch (ClassCastException e) {
                            try {
                                pstm.setFloat(i + 1, (float) datos[i]);
                            } catch (ClassCastException ex) {
                                try {
                                    pstm.setInt(i + 1, (int) datos[i]);
                                } catch (ClassCastException exe) {
                                    try {
                                        pstm.setString(i + 1, (String) datos[i]);
                                    } catch (ClassCastException exep) {
                                        pstm.setDouble(i + 1, (double) datos[i]);

                                    }
                                }
                            }
                        }
                    }
                }

                i++;

                if (valores != null) {
                    for (Object valor : valores) {

                        try {
                            pstm.setBigDecimal(i++, big.getBigDecimal(valor));
                        } catch (NumberFormatException d) {
                            System.out.println(d + " Error, no es numero");
                        }
                    }
                }
                pstm.execute();
            }

            ok = true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return ok;
    }
}
