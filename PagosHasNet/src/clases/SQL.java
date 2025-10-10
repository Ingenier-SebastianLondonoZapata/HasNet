package clases;

import Modelos.JSONCreacion.modeloCreacion;
import Modelos.Usuario.modeloUsuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import Modelos.Configuracion.modeloConfiguracion;

public class SQL {

    metodosGenerales metodos;
    MySql_connection mysql;

    public SQL() {
        this.mysql = MySql_connection.getMySql_connection();
        metodos = new metodosGenerales();
    }

    public MySql_connection getMysql() {
        return mysql;
    }

    public Object[][] obtenerDepartamentos() {
        String colName[] = {"nombreDepartamento"};
        String origen = " bdRegiones ";
        return getDatos(colName, origen, "select nombreDepartamento from bdRegiones group by nombreDepartamento ", "");
    }

    public Object[][] obtenerMunicipiosPorDepartamento(String departamento) {
        String[] colName = {"nombreMunicipio"};
        String origen = " bdRegiones";
        return getDatos(colName, origen, "select nombreMunicipio from bdRegiones where nombreDepartamento ='" + departamento + "' "
                + "GROUP BY nombreMunicipio ORDER BY nombreMunicipio ", " where nombreDepartamento ='" + departamento + "' ");
    }

    public Object[][] obtenerCodigosPostales(String departamento, String ciudad) {
        String[] colName = {"codigoPostal"};
        String origen = " bdRegiones ";
        return getDatos(colName, origen, "select codigoPostal from bdRegiones where nombreDepartamento = '" + departamento + "' "
                + "&& nombreMunicipio = '" + ciudad + "' ", " where nombreDepartamento ='" + departamento + "' && nombreMunicipio = '" + ciudad + "' ");
    }

    public Object[][] obtenerCodigoLugar(String departamento, String ciudad) {
        String[] colName = {"codigoDepartamento", "codigoMunicipio"};
        String origen = " bdRegiones ";
        return getDatos(colName, origen, "select codigoDepartamento, codigoMunicipio from bdRegiones where nombreDepartamento = '" + departamento + "' "
                + "&& nombreMunicipio = '" + ciudad + "' ", " where nombreDepartamento ='" + departamento + "' && nombreMunicipio = '" + ciudad + "' ");
    }

    public modeloUsuario getDatosUsuario(String usuario) {
        modeloUsuario modelo = new modeloUsuario();
        String instruccion_sql = "select idUsuario, usuario, password from bdUsuarios where idUsuario = '" + usuario + "' ;";
        String[] colName = {"idUsuario", "usuario", "password"};
        boolean[] cadena = {true, true, true};
        return modelo.llenarUsuario(GetRegistro(colName, cadena, instruccion_sql));
    }

    public DefaultTableModel obtenerRegistrosClientesTabla() {
        String columNames[] = {"Cliente", "Nit", "Nombre", "Telefono"};
        String colName[] = {"idCliente", "nit", "nombre", "telefono"};
        String origen = " bdClientes ";
        Object dtDatos[][] = GetTabla(colName, origen, (new StringBuilder()).append("select idCliente, nit, nombre, telefono from ").append(origen).toString());
        DefaultTableModel datos = new DefaultTableModel(dtDatos, columNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        return datos;
    }

    public boolean agregarCliente(String idCliente, String nit, String tipoIdentificacion, String nombre, String telefono, String fechaInicio) {
        String instruccion_sql = "insert into bdClientes(idCliente, nit, tipoIdentificacion, nombre, telefono, fechaInicio) values (?,?,?,?,?,?);";
        return Agregar_Registro(new Object[]{idCliente, nit, tipoIdentificacion, nombre, telefono, fechaInicio}, null, instruccion_sql);
    }

    public boolean agregarRegistroPaquete(String idCliente, int numeroFacturas, int valorUnitario, int valorTotal, String fecha) {
        String instruccion_sql = "insert into bdPaquetesClientes(idCliente, numeroFacturas, valorUnitario, valorTotal, fecha) values (?,?,?,?,?);";
        return Agregar_Registro(new Object[]{idCliente, numeroFacturas, valorUnitario, valorTotal, fecha}, null, instruccion_sql);
    }

    public boolean actualizarPaquete(String idCliente, int totalNumeroFacturas) {
        String instruccion_sql = "update bdClientes set numeroFEDisponibles=? where idCliente = '" + idCliente + "'; ";
        return Actualizar_Registro(new Object[]{"", totalNumeroFacturas}, null, instruccion_sql);
    }

    public boolean agregarDatosCliente(String idCliente, modeloCreacion modelo) {
        String instruccion_sql = "insert into bdDatosClientes(idCliente, tipoPersona, tipoIdentificacion, identificacion, nombres, segundoNombre, primerApellido, "
                + "segundoApellido, direccion, telefono, email, emailRemitente, emailDefecto, cdDaneCiudad, cdDaneDepartamento, tipoRegimen, sitioWeb, codigoPostal, "
                + "codigoCIIU, codigoObligaciones, codigoTributario, imagenHeaderIzquierda, identificadorPruebasDIAN) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        return Agregar_Registro(new Object[]{idCliente, modelo.getTipoPersona(), modelo.getTipoIdentificacion(), modelo.getIdentificacion(), modelo.getNombres(),
            modelo.getSegundoNombre(), modelo.getPrimerApellido(), modelo.getSegundoApellido(), modelo.getDireccion(), modelo.getTelefono(), modelo.getEmail(),
            modelo.getEmailRemitente(), modelo.getEmailDefecto(), modelo.getCdDaneCiudad(), modelo.getCdDaneDepartamento(), modelo.getTipoRegimen(), modelo.getSitioWeb(),
            modelo.getCodigoPostal(), modelo.getCodigoCIIU(), modelo.getCodigoObligaciones(), modelo.getCodigoTributario(), modelo.getImagenHeaderIzquierda(),
            modelo.getIdentificadorPruebasDIAN()}, null, instruccion_sql);
    }

    public boolean agregarRegistroPago(String idCliente, String fechaLimite) {
        String instruccion_sql = "insert into bdPagos(idCliente, fechaLimite) values (?,?);";
        return Agregar_Registro(new Object[]{idCliente, fechaLimite}, null, instruccion_sql);
    }

    public Object[][] obtenerRegistrosClientes() {
        String colName[] = {"idCliente", "nit", "nombre", "telefono", "fechaInicio"};
        String origen = " bdClientes ";
        return getDatos(colName, origen, "SELECT idCliente, nit, nombre, telefono, fechaInicio from bdClientes ", "");
    }

    public Object[][] obtenerRegistroPagos(String estado) {
        String colName[] = {"idPago", "nit", "nombre", "fechaLimite", "diasAntesAlertaBloqueo", "bdClientes.idCliente", "fechaPago"};
        String origen = " bdPagos INNER JOIN bdClientes ON bdPagos.idCliente = bdClientes.idCliente ";
        return getDatos(colName, origen,
                "SELECT idPago, nit, nombre, fechaLimite, diasAntesAlertaBloqueo, bdClientes.idCliente, fechaPago FROM bdPagos INNER JOIN bdClientes "
                + "ON bdPagos.idCliente = bdClientes.idCliente WHERE estado = '" + estado + "' ", " WHERE estado = '" + estado + "' ");
    }

    public Object[][] obtenerRegistroPaquete() {
        String colName[] = {"tabla1.idCliente", "nit", "nombre", "resultado"};
        String origen = " bdPaquetesClientes INNER JOIN bdClientes ON bdPaquetesClientes.idCliente = bdClientes.idCliente ";
        return getDatos(colName, origen, "SELECT tabla1.idCliente, nit, nombre, numeroFacturas - IFNULL(numRegistros, 0) AS resultado FROM "
                + "(SELECT bdPaquetesClientes.idCliente, nit, nombre, SUM(numeroFacturas) AS numeroFacturas "
                + "FROM bdPaquetesClientes INNER JOIN bdClientes ON bdPaquetesClientes.idCliente = bdClientes.idCliente GROUP BY idCliente) AS tabla1 "
                + "LEFT JOIN (SELECT idCliente, COUNT(numeroFactura) AS numRegistros FROM bdRegistrosClientes GROUP BY idCliente) AS tabla2 "
                + "ON tabla1.idCliente = tabla2.idCliente; ", "");
    }

    public Object[][] obtenerHistorialPaquetes() {
        String colName[] = {"bdPaquetesClientes.idCliente", "nit", "nombre", "numeroFacturas", "valorUnitario", "valorTotal", "fecha"};
        String origen = " bdPaquetesClientes INNER JOIN bdClientes ON bdPaquetesClientes.idCliente = bdClientes.idCliente";
        return getDatos(colName, origen, "SELECT bdPaquetesClientes.idCliente, nit, nombre, numeroFacturas, valorUnitario, valorTotal, fecha "
                + "FROM bdPaquetesClientes INNER JOIN bdClientes ON bdPaquetesClientes.idCliente = bdClientes.idCliente", "");
    }

    public modeloConfiguracion obtenerConfiguracionCliente(String idCliente) {
        modeloConfiguracion modelo = new modeloConfiguracion();
        String instruccion_sql = "select regimen, informacionLegal, tipoImpresion, nit, nombre, telefono, fechaInicio, diasAntesAlertaBloqueo,"
                + "diasDespuesGabelaBloqueo, numeroFEDisponibles, congeladas, medico, veterinaria, parqueadero, ordenServicio, creditos, separe, pedido, agenda, restaurante, "
                + "recordatorios, laboratorio, servicioAutomotor, oftalmologia, inventarioBodegas, productosSerial, facturacionLote, usb, "
                + "facturaElectronica,  pruebasFacturacion from bdClientes where idCliente = '" + idCliente + "' ";
        String[] colName = {"regimen", "informacionLegal", "tipoImpresion", "nit", "nombre", "telefono", "fechaInicio", "diasAntesAlertaBloqueo", "diasDespuesGabelaBloqueo",
            "numeroFEDisponibles", "congeladas", "medico", "veterinaria", "parqueadero", "ordenServicio", "creditos", "separe", "pedido", "agenda", "restaurante", "recordatorios",
            "laboratorio", "servicioAutomotor", "oftalmologia", "inventarioBodegas", "productosSerial", "facturacionLote", "usb",
            "facturaElectronica", "pruebasFacturacion"};
        boolean[] cadena = {true, true, true, true, true, true, true, true, true, true, false, false, false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false, false, false
        };
        return modelo.llenarConfiguracion(GetRegistro(colName, cadena, instruccion_sql));
    }

    public boolean actualizarPermisos(modeloConfiguracion modelo, String idCliente) {
        String instruccion_sql = "update bdClientes set regimen=?, informacionLegal=?, tipoImpresion=?, nit=?, nombre=?, telefono=?, fechaInicio=?, "
                + "diasAntesAlertaBloqueo=?, diasDespuesGabelaBloqueo=?, numeroFEDisponibles=?, congeladas=?, medico=?, veterinaria=?, parqueadero=?, ordenServicio=?, creditos=?, "
                + "separe=?, pedido=?, agenda=?, restaurante=?, recordatorios=?, laboratorio=?, servicioAutomotor=?, oftalmologia=?, inventarioBodegas=?, "
                + "productosSerial=?, facturacionLote=?, usb=?, facturaElectronica=?, pruebasFacturacion=? where idCliente = '" + idCliente + "'; ";
        return Actualizar_Registro(modelo.pasarDatosConfiguracion(modelo), null, instruccion_sql);
    }

    public boolean cambiarEstadoCuota(int idPago) {
        String instruccion_sql = "update bdPagos set estado=?, fechaPago=? where idPago = " + idPago + " ";
        return Actualizar_Registro(new Object[]{"", "PAGADO", metodosGenerales.fecha()}, null, instruccion_sql);
    }

//                                     PROCEDIMIENTOS SQL
/* ------------------------------------------------------------------------------------- */
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

    private boolean Inactivar_Registro(String instruccion_sql) {
        boolean ok = false;
        try {
            PreparedStatement pstm = mysql.getConnection().prepareStatement(instruccion_sql);
            pstm.setBoolean(1, true);
            pstm.execute();
            pstm.close();
            ok = true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return ok;
    }

    private boolean Activar_Registro(String instruccion_sql) {
        boolean ok = false;
        try {
            PreparedStatement pstm = mysql.getConnection().prepareStatement(instruccion_sql);
            pstm.setBoolean(1, false);
            pstm.execute();
            pstm.close();
            ok = true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return ok;
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

    public boolean eliminarTodo(String tabla) {
        boolean ok = false;
        String instruccion = "delete from " + tabla + ";";
        try {
            PreparedStatement pstm = mysql.getConnection().prepareStatement(instruccion);
            pstm.execute();
            pstm.close();
            ok = true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return ok;
    }

    public boolean eliminar_registro(String tabla, String condicion) {
        boolean ok = false;
        String instruccion = "delete from " + tabla + " where " + condicion + " ;";
        try {
            PreparedStatement pstm = mysql.getConnection().prepareStatement(instruccion);
            pstm.execute();
            pstm.close();
            ok = true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return ok;
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

    public Object[][] GetTabla(String colName[], String tabla, String sql) {
        int registros = 0;
        //obtenemos la cantidad de registros existentes en la tabla
        try {
            PreparedStatement pstm = mysql.getConnection().prepareStatement("SELECT count(*) as total FROM " + tabla);
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
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
        } catch (SQLException e) {
            System.out.println(e);
        }
        return data;
    }

    public Object[][] GetTabla(String colName[], String tabla, String sql, Integer[] boleano) {
        int registros = 0;
        //obtenemos la cantidad de registros existentes en la tabla
        try {
            PreparedStatement pstm = mysql.getConnection().prepareStatement("SELECT count(*) as total FROM " + tabla);
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        Object[][] data = new Object[registros][colName.length];
        Object col[] = new Object[colName.length];

        //realizamos la consulta sql y llenamos los datos en "Object"
        try {
            PreparedStatement pstm = mysql.getConnection().prepareStatement(sql);
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while (res.next()) {
                for (int j = 0; j <= colName.length - 1; j++) {
                    boolean entro = true;
                    for (int k = 0; k < boleano.length; k++) {
                        if (j == boleano[k]) {
                            col[j] = res.getBoolean(colName[j]);
                            entro = false;
                            break;
                        }
                    }
                    if (entro) {
                        col[j] = res.getString(colName[j]);
                    }
                    data[i][j] = col[j];
                }
                i++;
            }
            res.close();
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

    /* METODO PARA TODOS LOS DATOS DE UNA COLUMNA
     * parametros (Nombre de la tabla, nombre columna, instruccion sql )
     */
    public String[] GetColumna(String tabla, String colName, String sql) {
        int registros = 0;
        //obtenemos la cantidad de registros existentes en la tabla
        try {
            PreparedStatement pstm = mysql.getConnection().prepareStatement("SELECT count(*) as total FROM " + tabla);
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        String[] data = new String[registros];
        try {
            PreparedStatement pstm = mysql.getConnection().prepareStatement(sql);
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while (res.next()) {
                data[i] = res.getString(colName);
                i++;
            }
            res.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return data;
    }

    /* metodo para determinar si un registro dado la tabla, columna y el valor de busqueda, existe*/
    public boolean existe(String tabla, String columna, String valor) {
        boolean si_existe = false;
        int data = 0;
        try {
            String instruccion = "select count(*) as total from " + tabla + " WHERE " + columna + "='" + valor + "';";
            PreparedStatement pstm = mysql.getConnection().prepareStatement(instruccion);
            ResultSet res = pstm.executeQuery();
            while (res.next()) {
                data = res.getInt("total");
            }
            res.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        if (data > 0) {
            si_existe = true;
        }
        return si_existe;
    }

    public int Existen_Filas(String colName, String sql) {
        int filas = 0;
        try {
            PreparedStatement pstm = mysql.getConnection().prepareStatement(sql);
            ResultSet res = pstm.executeQuery();
            while (res.next()) {
                filas = res.getInt(colName);
            }
            res.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return filas;
    }

    public void Cerrar_Access() {
        try {
            mysql.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
