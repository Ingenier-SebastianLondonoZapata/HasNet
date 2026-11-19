package clases;

import Modelos.JSONCreacion.modeloCreacion;
import Modelos.Usuario.modeloUsuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        return consultarFilas(colName, "select nombreMunicipio from bdRegiones where nombreDepartamento = ? "
                + "GROUP BY nombreMunicipio ORDER BY nombreMunicipio", departamento);
    }

    public Object[][] obtenerCodigosPostales(String departamento, String ciudad) {
        String[] colName = {"codigoPostal"};
        return consultarFilas(colName, "select codigoPostal from bdRegiones where nombreDepartamento = ? and nombreMunicipio = ?",
                departamento, ciudad);
    }

    public Object[][] obtenerCodigoLugar(String departamento, String ciudad) {
        String[] colName = {"codigoDepartamento", "codigoMunicipio"};
        return consultarFilas(colName, "select codigoDepartamento, codigoMunicipio from bdRegiones where nombreDepartamento = ? and nombreMunicipio = ?",
                departamento, ciudad);
    }

    public modeloUsuario getDatosUsuario(String usuario) {
        Object[] datos = new Object[3];
        String instruccion_sql = "select idUsuario, usuario, password from bdUsuarios where idUsuario = ?";

        try (PreparedStatement pstm = mysql.getConnection().prepareStatement(instruccion_sql)) {
            pstm.setString(1, usuario);
            try (ResultSet res = pstm.executeQuery()) {
                if (res.next()) {
                    datos[0] = res.getString("idUsuario");
                    datos[1] = res.getString("usuario");
                    datos[2] = res.getString("password");
                }
            }
        } catch (SQLException | NullPointerException e) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, "Error al consultar el usuario", e);
        }

        return new modeloUsuario().llenarUsuario(datos);
    }

    public boolean actualizarPasswordUsuario(String idUsuario, String passwordHash, String passwordAnterior) {
        try {
            if (!escribirPassword(idUsuario, passwordHash)) {
                return false;
            }
            if (passwordHash.equals(getDatosUsuario(idUsuario).getPassword())) {
                return true;
            }
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE,
                    "La columna bdUsuarios.password no admite el hash completo; se restaura el valor anterior. Amplíe la columna a VARCHAR(255)");
            escribirPassword(idUsuario, passwordAnterior);
        } catch (SQLException | NullPointerException e) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, "Error al actualizar la contraseña del usuario", e);
        }
        return false;
    }

    private boolean escribirPassword(String idUsuario, String password) throws SQLException {
        try (PreparedStatement pstm = mysql.getConnection().prepareStatement("update bdUsuarios set password = ? where idUsuario = ?")) {
            pstm.setString(1, password);
            pstm.setString(2, idUsuario);
            return pstm.executeUpdate() == 1;
        }
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

    public boolean existeCliente(String nit, String nombre) {
        try (PreparedStatement pstm = mysql.getConnection().prepareStatement("select count(*) from bdClientes where nit = ? and nombre = ?")) {
            pstm.setString(1, nit);
            pstm.setString(2, nombre);
            try (ResultSet res = pstm.executeQuery()) {
                return res.next() && res.getInt(1) > 0;
            }
        } catch (SQLException | NullPointerException e) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, "Error al validar el NIT del cliente", e);
            return true;
        }
    }

    public boolean agregarCliente(String idCliente, String nit, String tipoIdentificacion, String nombre, String telefono, String fechaInicio) {
        String instruccion_sql = "insert into bdClientes(idCliente, nit, tipoIdentificacion, nombre, telefono, fechaInicio) values (?,?,?,?,?,?);";
        return Agregar_Registro(new Object[]{idCliente, nit, tipoIdentificacion, nombre, telefono, fechaInicio}, null, instruccion_sql);
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

    public Object[][] obtenerPagosPendientes() {
        String colName[] = {"idPago", "nit", "nombre", "fechaLimite", "diasAntesAlertaBloqueo", "bdClientes.idCliente"};
        return consultarFilas(colName,
                "SELECT idPago, nit, nombre, fechaLimite, diasAntesAlertaBloqueo, bdClientes.idCliente FROM bdPagos INNER JOIN bdClientes "
                + "ON bdPagos.idCliente = bdClientes.idCliente WHERE estado = 'PENDIENTE'");
    }

    public modeloConfiguracion obtenerConfiguracionCliente(String idCliente) {
        modeloConfiguracion modelo = new modeloConfiguracion();
        String instruccion_sql = "select regimen, nit, nombre, telefono, fechaInicio, diasAntesAlertaBloqueo,"
                + "diasDespuesGabelaBloqueo, numeroFEDisponibles, congeladas, medico, veterinaria, parqueadero, ordenServicio, creditos, separe, pedido, agenda, restaurante, "
                + "recordatorios, laboratorio, servicioAutomotor, oftalmologia, inventarioBodegas, productosSerial, facturacionLote, "
                + "facturaElectronica,  pruebasFacturacion, multiempresa, numeroEmpresas from bdClientes where idCliente = ?";
        String[] colName = {"regimen", "nit", "nombre", "telefono", "fechaInicio", "diasAntesAlertaBloqueo", "diasDespuesGabelaBloqueo",
            "numeroFEDisponibles", "congeladas", "medico", "veterinaria", "parqueadero", "ordenServicio", "creditos", "separe", "pedido", "agenda", "restaurante", "recordatorios",
            "laboratorio", "servicioAutomotor", "oftalmologia", "inventarioBodegas", "productosSerial", "facturacionLote",
            "facturaElectronica", "pruebasFacturacion", "multiempresa", "numeroEmpresas"};
        boolean[] cadena = {true, true, true, true, true, true, true, true, false, false, false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false, false, false, true
        };
        return modelo.llenarConfiguracion(GetRegistro(colName, cadena, instruccion_sql, idCliente));
    }

    public boolean actualizarPermisos(modeloConfiguracion modelo, String idCliente) {
        String instruccion_sql = "update bdClientes set regimen=?, nit=?, nombre=?, telefono=?, fechaInicio=?, "
                + "diasAntesAlertaBloqueo=?, diasDespuesGabelaBloqueo=?, congeladas=?, medico=?, veterinaria=?, parqueadero=?, ordenServicio=?, creditos=?, "
                + "separe=?, pedido=?, agenda=?, restaurante=?, recordatorios=?, laboratorio=?, servicioAutomotor=?, oftalmologia=?, inventarioBodegas=?, "
                + "productosSerial=?, facturacionLote=?, facturaElectronica=?, pruebasFacturacion=?, multiempresa=?, numeroEmpresas=? where idCliente = ?";
        Object[] datos = modelo.pasarDatosConfiguracion(modelo);
        Object[] datosConCliente = Arrays.copyOf(datos, datos.length + 1);
        datosConCliente[datos.length] = idCliente;
        return Actualizar_Registro(datosConCliente, null, instruccion_sql);
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

    private Object[] GetRegistro(String colName[], boolean cadena[], String sql, Object... parametros) {
        Object[] data = new Object[colName.length];
        try (PreparedStatement pstm = mysql.getConnection().prepareStatement(sql)) {
            asignarParametros(pstm, parametros);
            try (ResultSet res = pstm.executeQuery()) {
                while (res.next()) {
                    for (int j = 0; j <= colName.length - 1; j++) {
                        if (cadena[j]) {
                            data[j] = res.getString(colName[j]);
                        } else {
                            data[j] = res.getBoolean(colName[j]);
                        }
                    }
                }
            }
        } catch (SQLException | NullPointerException e) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, e);
        }
        return data;
    }

    private Object[][] consultarFilas(String colName[], String sql, Object... parametros) {
        List<String[]> filas = new ArrayList<>();
        try (PreparedStatement pstm = mysql.getConnection().prepareStatement(sql)) {
            asignarParametros(pstm, parametros);
            try (ResultSet res = pstm.executeQuery()) {
                while (res.next()) {
                    String[] fila = new String[colName.length];
                    for (int j = 0; j < colName.length; j++) {
                        fila[j] = res.getString(colName[j]);
                    }
                    filas.add(fila);
                }
            }
        } catch (SQLException | NullPointerException e) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, e);
        }
        return filas.toArray(new String[0][]);
    }

    private void asignarParametros(PreparedStatement pstm, Object... parametros) throws SQLException {
        for (int i = 0; i < parametros.length; i++) {
            pstm.setObject(i + 1, parametros[i]);
        }
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
