package Utilidades.BaseDatos;

import Modelo.Ventas.ModeloComanda;
import Modelo.Egresos.ModeloDetalleEgreso;
import Modelo.Terceros.ModeloContacto;
import Modelo.Maestra.modeloConfiguracion;
import Modelo.Maestra.ModeloResolucion;
import dao.Ventas.DaoComanda;
import clases.Cartera.*;
import clases.Egresos.*;
import clases.Instancias;
import clases.Laboratorio.*;
import clases.Medico.*;
import clases.Oftalmologia.*;
import clases.Parqueadero.*;
import clases.Ventas.*;
import clases.Veterinario.*;
import clases.big;
import clases.credito.*;
import clases.metodosGenerales;
import clases.ndMaestra;
import clases.ndUsuario;
import clases.productos.*;
import clases.recordatorios.*;
import clases.terceros.*;
import formularios.Agenda.*;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.table.DefaultTableModel;

public class SQL {

    private static String nombreBaseDatosUtilizada;
    private metodosGenerales metodos = new metodosGenerales();
    private static MySql_connection mysql;

    public void obtenerConexion(String nombreBaseDatos, Boolean esDesdeMultiempresas) {
        this.nombreBaseDatosUtilizada = nombreBaseDatos;
        this.mysql = MySql_connection.getMySql_connection(this.nombreBaseDatosUtilizada, esDesdeMultiempresas);
    }

    public String getBaseDatos() {
        return nombreBaseDatosUtilizada;
    }

    public MySql_connection getMysql() {
        return mysql;
    }

    public Object[][] getIndicadoresPorGrupo(String sql) {
        String colName[] = {
            "subtotal", "iva", "total", "descuento", "Grupo", "utilidad", "impoconsumo", "nombreGrupo"
        };
        String origen = " factura ";
        Object dtDatos[][] = getDatos(colName, origen, "SELECT Sum(factura.subtotal) AS subtotal, Sum(factura.iva) AS iva, "
                + "Sum(factura.total) AS total, Sum(factura.descuento) AS descuento, factura.Grupo, Sum(factura.utilidad) AS utilidad, "
                + "Sum(factura.impoconsumo) AS impoconsumo, factura.nombreGrupo from " + origen + sql + " GROUP BY factura.Grupo, factura.nombreGrupo, factura.anulada"
                + " HAVING anulada = 0 ", "");
        return dtDatos;
    }

    public Object[][] getIndicadoresPor(String sql, String campo) {
        String colName[] = {
            "subtotal", "iva", "total", "descuento", campo, "utilidad", "impoconsumo"
        };
        String origen = " factura ";
        Object dtDatos[][] = getDatos(colName, origen, "SELECT Sum(factura.subtotal) AS subtotal, Sum(factura.iva) AS iva, "
                + "Sum(factura.total) AS total, Sum(factura.descuento) AS descuento, factura." + campo + ", Sum(factura.utilidad) AS utilidad, "
                + "Sum(factura.impoconsumo) AS impoconsumo from " + origen + sql + " GROUP BY factura." + campo + ", factura.anulada"
                + " HAVING ANULADA = 0 ", "");
        return dtDatos;
    }

    public Object[][] getIndicadoresPorGeneral(String sql) {
        String colName[] = {
            "subtotal", "iva", "total", "descuento", "Mes", "utilidad", "impoconsumo"
        };
        String origen = " factura ";
        Object dtDatos[][] = getDatos(colName, origen, "SELECT Sum(factura.subtotal) AS subtotal, Sum(factura.iva) AS iva, "
                + "Sum(factura.total) AS total, Sum(factura.descuento) AS descuento, Sum(factura.utilidad) AS utilidad, "
                + "Sum(factura.impoconsumo) AS impoconsumo, Month(fechaFactura) AS Mes, Year(fechaFactura) AS Ano "
                + "FROM " + origen + sql + " GROUP BY Month(fechaFactura), Year(fechaFactura), factura.anulada HAVING anulada = 0 ", "");
        return dtDatos;
    }

    public Object[][] getIndicadoresPorProductoMes(String sql) {
        String colName[] = {
            "subtotal", "iva", "total", "descuento", "Mes", "utilidad", "impoconsumo", "Codigo", "descripcion"
        };
        String origen = " factura ";
        Object dtDatos[][] = getDatos(colName, origen, "SELECT Sum(factura.subtotal) AS subtotal, Sum(factura.iva) AS iva, "
                + "Sum(factura.total) AS total, Sum(factura.descuento) AS descuento, Sum(factura.utilidad) AS utilidad, "
                + "Sum(factura.impoconsumo) AS impoconsumo, Month(fechaFactura) AS Mes, Year(fechaFactura) AS Ano, factura.Codigo, factura.descripcion "
                + "FROM " + origen + sql + " GROUP BY Month(fechaFactura), Year(fechaFactura), factura.Codigo, factura.descripcion, factura.anulada "
                + "HAVING anulada = 0 ", "");
        return dtDatos;
    }

    public Object[][] getIndicadoresPorProductos(String sql, String agrupar) {
        String colName[] = {
            "subtotal", "iva", "total", "descuento", "Codigo", "utilidad", "impoconsumo", "descripcion", "cant2"
        };
        String origen = " factura ";
        Object dtDatos[][] = getDatos(colName, origen, "SELECT Sum(factura.subtotal) AS subtotal, Sum(factura.iva) AS iva, "
                + "Sum(factura.total) AS total, Sum(factura.descuento) AS descuento, factura.Codigo, Sum(factura.utilidad) AS utilidad, "
                + "Sum(factura.impoconsumo) AS impoconsumo, factura.descripcion, Sum(factura.cant2) AS cant2 from " + origen + sql + " GROUP BY factura.Codigo, "
                + "factura.descripcion, factura.anulada HAVING anulada = 0 "
                + "ORDER BY Sum(FACTURA." + agrupar + ") DESC", "");
        return dtDatos;
    }

    public Object[][] getIndicadoresPorCliente(String sql) {
        String colName[] = {
            "subtotal", "iva", "total", "descuento", "cliente", "utilidad", "impoconsumo", "nombre"
        };
        String origen = " factura ";
        Object dtDatos[][] = getDatos(colName, origen, "SELECT Sum(factura.subtotal) AS subtotal, Sum(factura.iva) AS iva, "
                + "Sum(factura.total) AS total, Sum(factura.descuento) AS descuento, factura.cliente, Sum(factura.utilidad) AS utilidad, "
                + "Sum(factura.impoconsumo) AS impoconsumo, factura.nombre from " + origen + sql + " GROUP BY factura.cliente, factura.nombre, factura.anulada "
                + "HAVING anulada = 0 ", "");
        return dtDatos;
    }

    public boolean cambiarEstadoGeneral(String estado, String id, String tabla) {
        boolean ok = false;
        String instruccion_sql = "update " + tabla + " set estado=? where Id = '" + id + "' ;";
        ok = Actualizar_Registro(new Object[]{"", estado}, null, instruccion_sql);
        return ok;
    }

    public boolean activarRegistro(String Id, String tabla) {
        boolean ok = false;
        String instruccion_sql = "update " + tabla + " set activo=? "
                + " where codigo = '" + Id + "' ;";
        ok = Activar_Registro(instruccion_sql);
        return ok;
    }

    public boolean inactivarRegistro(String Id, String tabla) {
        boolean ok = false;
        String instruccion_sql = "update " + tabla + " set activo=?"
                + " where codigo ='" + Id + "' ;";
        ok = Inactivar_Registro(instruccion_sql);
        return ok;
    }

    public boolean activarRegistro1(String Id, String tabla) {
        boolean ok = false;
        String instruccion_sql = "update " + tabla + " set activo=? "
                + " where codigo = " + Id + " ;";
        ok = Activar_Registro(instruccion_sql);
        return ok;
    }

    public boolean inactivarRegistro1(String Id, String tabla) {
        boolean ok = false;
        String instruccion_sql = "update " + tabla + " set activo=?"
                + " where codigo = " + Id + " ;";
        ok = Inactivar_Registro(instruccion_sql);
        return ok;
    }

    /* INICIO VETERINARIO */
    public boolean modificarGestionVacunacion(String cod, boolean gestion, String fechaGestion) {
        boolean ok = false;
        String instruccion_sql = "update bdVacunacion set gestion=?, fechaGestion=? where id=" + cod + " ;";
        ok = Actualizar_Registro(new Object[]{"", gestion, fechaGestion}, null, instruccion_sql);
        return ok;
    }

    public boolean modificarGestionPeluqueria(String cod, boolean gestion, String fechaGestion) {
        boolean ok = false;
        String instruccion_sql = "update bdPeluqueria set gestion=?, fechaGestion=? where Id = '" + cod + "' ;";
        ok = Actualizar_Registro(new Object[]{"", gestion, fechaGestion}, null, instruccion_sql);
        return ok;
    }

    public DefaultTableModel getRegistrosAlertasVacunas(String fecha) {
        String columNames[] = {
            "Cod Mascota", "Mascota", "Cliente", "Teléfono", "Celular", "Vacuna", "Fecha Vacuna", "Fecha Sig", "Se Gestionó", "id"
        };
        String colName[] = {
            "codigo", "nombreMascota", "nombre", "telefono", "celular", "nombreVacuna", "fecha", "proxima", "gestiono", "id"
        };
        String origen = " vacunas where proxima= '" + fecha + "' ";

        Object dtDatos[][] = GetTabla(colName, origen, "select codigo, nombreMascota, nombre, telefono, celular, nombreVacuna, fecha, proxima, "
                + "false as gestiono, id from vacunas where proxima= '" + fecha + "' ", new Integer[]{8});

        DefaultTableModel datos = new DefaultTableModel(dtDatos, columNames) {
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        return datos;
    }

    public DefaultTableModel getRegistrosAlertasPeluquerias(String fecha) {
        String columNames[] = {
            "Cod Mascota", "Mascota", "Cliente", "Teléfono", "Celular", "Descripcion", "Fecha", "Fecha Sig", "Se Gestionó", "id"
        };
        String colName[] = {
            "codigo", "nombreMascota", "nombre", "telefono", "celular", "nombreServicio", "fecha", "proxima", "gestiono", "id"
        };
        String origen = " peluqueria where proxima= '" + fecha + "' ";

        Object dtDatos[][] = GetTabla(colName, origen, "select codigo, nombreMascota, nombre, telefono, celular, nombreServicio, fecha, proxima, "
                + "false as gestiono, id from peluqueria where proxima= '" + fecha + "' ", new Integer[]{8});

        DefaultTableModel datos = new DefaultTableModel(dtDatos, columNames) {
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        return datos;
    }

    public boolean agregarFormatosVeterinario(Object[] vector) {
        boolean ok = false;
        String instruccion_sql = "insert into bdFormatos(formato, tercero, fecha) values (?,?,?);";
        ok = Agregar_Registro(vector, null, instruccion_sql);
        return ok;
    }

    public boolean agregarMedicamentosAplicados(String idHost, String idMed, String descripcion, String cantAplicada, String prodFacturado,
            String responsable, String fechaEstablecida, String horaEstablecida, String nota, String horaAplicada, String fechaAplicada) {
        boolean ok = false;
        String instruccion_sql = "insert into bdMedicamentosAplicados(idHospitalizacion, idMedicamento, descripcion, cantAplicada, prodFacturado,"
                + "responsable, fechaEstablecida, horaEstablecida, nota, horaAplicada, fechaAplicada) "
                + "values (?,?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(new Object[]{idHost, idMed, descripcion, cantAplicada, prodFacturado, responsable, fechaEstablecida,
            horaEstablecida, nota, horaAplicada, fechaAplicada}, null, instruccion_sql);
        return ok;
    }

    public boolean agregarServicioPTM(int codigo, String nombre) {
        boolean ok = false;
        String instruccion_sql = "insert into bdServiciosPTM(nombre, fecha, codigo) values (?,?,?);";
        ok = Agregar_Registro(new Object[]{nombre, metodos.fechaConsulta(metodosGenerales.fecha())}, new Object[]{codigo}, instruccion_sql);
        return ok;
    }

    public boolean agregarServicioPTM(String codigo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdServiciosPTM1(codigo) values (?);";
        ok = Agregar_Registro(new Object[]{codigo}, null, instruccion_sql);
        return ok;
    }

    public boolean asignarMascotaHosp(String idHost, String mascota, String tercero) {
        boolean ok = false;
        String instruccion_sql = "update bdMedicamentosProcesosAlertas set mascota=?, tercero=?, estado=? where idHospitalizacion = '" + idHost + "' ;";
        ok = Actualizar_Registro(new Object[]{"", mascota, tercero, "Pendiente"}, null, instruccion_sql);
        return ok;
    }

    public boolean cambiarEstadoMedicamento(String idHost, String cod, String hora) {
        boolean ok = false;
        String instruccion_sql = "update bdMedicamentosProcesosAlertas set estado=? where idHospitalizacion = '" + idHost + "' and codigo = '" + cod + "' and hora = '" + hora + "' ;";
        ok = Actualizar_Registro(new Object[]{"", "Realizado"}, null, instruccion_sql);
        return ok;
    }

    public boolean agregarTablaMedicamentos(ndTablaMedicamentos nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdMedicamentosProcesos (idHospitalizacion, codigo, medicamentos, posologia, hora) "
                + " values (?,?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosTablaMedicamentos(nodo), null, instruccion_sql);
        return ok;
    }

    public boolean agregarTablaMedicamentosAlertas(ndTablaMedicamentos nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdMedicamentosProcesosAlertas (idHospitalizacion, codigo, medicamentos, posologia, hora) "
                + " values (?,?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosTablaMedicamentos(nodo), null, instruccion_sql);
        return ok;
    }

    public boolean agregarProductosProcesos(Object[] vector) {
        boolean ok = false;
        String instruccion_sql = "insert into bdProductosProcesos(IdMovimiento, Codigo, Producto, Cantidad, Precio, Plu, usuario, fecha, hora) "
                + "values (?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(vector, null, instruccion_sql);
        return ok;
    }

    public ndConstanteHospitalizacion getDatosConstanteHospitalizacion(String id) {

        String instruccion_sql = "select Id, IdHospitalizacion, fecha, hora, usuario, cardiaca, resp, t, mucosas, orina, defeca, apetito, informacion"
                + " from bdConstantesHospitalizacion where Id = '" + id + "' ;";
        //columnas de la tabla que se
        String[] colName = {"Id", "idHospitalizacion", "fecha", "hora", "usuario", "cardiaca", "resp", "t", "mucosas", "orina", "defeca", "apetito", "informacion"};
        boolean[] cadena = {true, true, true, true, true, true, true, true, true, true, true, true, true};
        //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);

        ndConstanteHospitalizacion nodo = metodos.llenarConstantesHopitalizacion(data);

        return nodo;
    }

    public Object[][] getVacunasPendientes(String fecha) {

        System.out.println("eso si: " + fecha);
        String colName[] = {
            "Id"
        };
        String origen = " vacunas ";
        Object dtDatos[][] = getDatos(colName, origen, "select Id FROM vacunas where proxima = '" + metodos.fechaConsulta(fecha) + "'; ",
                " where proxima = '" + metodos.fechaConsulta(fecha) + "'; ");
        return dtDatos;
    }

    public Object[][] getPeluqueriasPendientes(String fecha) {
        String colName[] = {
            "Id"
        };
        String origen = " peluqueria ";
        Object dtDatos[][] = getDatos(colName, origen, "select Id FROM peluqueria where proxima = '" + metodos.fechaConsulta(fecha) + "'; ",
                " where proxima = '" + metodos.fechaConsulta(fecha) + "'; ");
        return dtDatos;
    }

//    public Object[][] getVacunasPendientes() {
//        String colName[] = {
//            "Id"
//        };
//        String origen = " vacunas ";
//        Object dtDatos[][] = getDatos(colName, origen, "select Id FROM vacunas ", " ");
//        return dtDatos;
//    }
    public Object[][] getServiciosPTM() {
        String colName[] = {
            "codigo", "nombre", "fecha"
        };
        String origen = " bdServiciosPTM ";
        Object dtDatos[][] = getDatos(colName, origen, "select codigo, nombre, fecha FROM bdServiciosPTM ORDER BY codigo ASC ", " ORDER BY codigo ASC");
        return dtDatos;
    }

    public Object[][] getMedicamentosPendientes(String idMed) {
        String colName[] = {
            "num", "estado", "responsable", "fecha", "hora", "nota", "fechaAplicada", "horaAplicada"
        };
        String origen = " bdMedicamentosPendientes";
        Object dtDatos[][] = getDatos(colName, origen, "select num, estado, responsable, fecha, hora, nota, fechaAplicada, horaAplicada FROM bdMedicamentosPendientes "
                + "where idMedicamentoPend = '" + idMed + "'", " where idMedicamentoPend = '" + idMed + "'");
        return dtDatos;
    }

//    public Object[][] getMedicamentosHospitalizaciones(String historia) {
//        String colName[] = {
//            "Codigo", "medicamentos", "posologia", "hora", "estado"
//        };
//        String origen = " bdMedicamentosProcesos";
//        Object dtDatos[][] = getDatos(colName, origen, "select Codigo, medicamentos, posologia, hora, estado "
//                + "from bdMedicamentosProcesos where IdHospitalizacion = '" + historia + "'", " where IdHospitalizacion = '" + historia + "'");
//        return dtDatos;
//    }
    public Object[][] getMedicamentosHospitalizaciones(String historia, String prod) {
        String colName[] = {
            "Codigo", "medicamentos", "posologia", "hora", "estado"
        };
        String origen = " bdMedicamentosProcesos";
        Object dtDatos[][] = getDatos(colName, origen, "select Codigo, medicamentos, posologia, hora, estado "
                + "FROM bdMedicamentosProcesos where IdHospitalizacion = '" + historia + "' and codigo = '" + prod + "' ",
                " where IdHospitalizacion = '" + historia + "' and codigo = '" + prod + "' ");
        return dtDatos;
    }

    public Object[][] getMedicamentosHospitalizacionesAgrupados(String historia) {
        String colName[] = {
            "Codigo", "medicamentos", "posologia"
        };
        String origen = " medicamentosHospitalizacion";
        Object dtDatos[][] = getDatos(colName, origen, "select Codigo, medicamentos, posologia "
                + "FROM medicamentosHospitalizacion where IdHospitalizacion = '" + historia + "' ",
                " where IdHospitalizacion = '" + historia + "' ");
        return dtDatos;
    }

    public Object[][] getProductosHospitalizaciones(String historia) {
        String colName[] = {
            "Codigo", "Producto", "Cantidad", "Precio"
        };
        String origen = " bdProductosProcesos";
        Object dtDatos[][] = getDatos(colName, origen, "select Codigo, Producto, Cantidad, Precio FROM bdProductosProcesos "
                + "where IdMovimiento = '" + historia + "'", " where IdMovimiento = '" + historia + "'");
        return dtDatos;
    }

    public Object[][] getEstadoMedicamentos(String id) {
        String[] colName = {"idHospitalizacion", "codigo", "medicamentos", "posologia", "hora", "estado", "mascota", "tercero"};
        String origen = " bdMedicamentosProcesosAlertas";
        Object dtDatos[][] = getDatos(colName, origen, "select idHospitalizacion, codigo, medicamentos, posologia, hora, estado, mascota, tercero "
                + "from bdMedicamentosProcesosAlertas where IdHospitalizacion = '" + id + "'", " where IdHospitalizacion = '" + id + "'");
        return dtDatos;
    }

    public Object[][] getSeguimientoHospitalizaciones(String historia, String tabla) {
        String colName[] = {
            "Id", "fecha"
        };
        String origen = " " + tabla;
        Object dtDatos[][] = getDatos(colName, origen, "select Id, fecha FROM " + tabla + " where IdHospitalizacion = '" + historia + "'",
                " where IdHospitalizacion = '" + historia + "'");
        return dtDatos;
    }

    public boolean agregarConstanteHospitalizacion(ndConstanteHospitalizacion nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdConstantesHospitalizacion(Id, IdHospitalizacion, fecha, hora, usuario, cardiaca, resp, "
                + "t, mucosas, orina, defeca, apetito, informacion)"
                + " values(?,?,?,?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosConstanteHospitalizacion(nodo), null, instruccion_sql);
        return ok;
    }

    public Object[][] getNotasEnfermeriaVet(String id) {
        String colName[] = {
            "Id", "fecha"
        };
        String origen = " bdConstantesHospitalizacion ";
        Object dtDatos[][] = getDatos(colName, origen, "select Id, fecha FROM bdConstantesHospitalizacion where IdHospitalizacion = '"
                + id + "'", " where IdHospitalizacion = '" + id + "'");
        return dtDatos;
    }

    public boolean agregarGuarderia(ndGuarderia nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdGuarderia(id, mascota, producto, fIngreso, fSalida, hIngreso, hSalida, totTiempo, totHoras, totValor, horas,"
                + " dias, observacion, factura, usuario, terminal, activo, cliente) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosGuarderia(nodo), null, instruccion_sql);
        return ok;
    }

    public boolean agregarHospitalizacion(String Id, String mascota, String fecha, String usuario) {
        boolean ok = false;
        String instruccion_sql = "insert into bdHospitalizacion(Id, mascota, fecha, usuario)"
                + " values(?,?,?,?);";
        ok = Agregar_Registro(new Object[]{Id, mascota, fecha, usuario}, null, instruccion_sql);
        return ok;
    }

    public boolean agregarIngresoHospital(ndIngresoHospitalizacion nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdIngresosHospitalizacion(id, mascota, producto, fIngreso, fSalida, hIngreso,"
                + " hSalida, totTiempo, totHoras, totValor, horas, dias, observacion, factura, usuario, terminal, activo, cliente) "
                + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosIngresoHospital(nodo), null, instruccion_sql);
        return ok;
    }

    public ndIngresoHospitalizacion getDatosIngresoHospitalizacion(String id) {
        boolean ok = false;
        String instruccion_sql = " select id, mascota, producto, fIngreso, fSalida, hIngreso, hSalida, totTiempo, totHoras, totValor, horas, dias, observacion, factura, "
                + "usuario, terminal, activo, cliente from bdIngresosHospitalizacion where id = '" + id + "'; ";
        String[] colName = {"id", "mascota", "producto", "fIngreso", "fSalida", "hIngreso", "hSalida", "totTiempo", "totHoras", "totValor",
            "horas", "dias", "observacion", "factura", "usuario", "terminal", "activo", "cliente"};
        boolean[] cadena = {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, false, true};
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        ndIngresoHospitalizacion nodo = metodos.llenarIngresoHospitalizacion(data);
        return nodo;
    }

    public boolean eliminarPonderadoIngreso(String tabla, String id) {
        boolean ok = false;
        ok = eliminar_registro(tabla, " ingreso = '" + id + "'");
        return ok;
    }

    public ndMascota getDatosMascota(String id) {
        String instruccion_sql = "select cliente, codigo, nombre, especie, raza, genero, edad, restricciones, color, alimentacion, observaciones, foto, temperamento, observacion from bdMascotas where codigo = '" + id + "'; ";
        String[] colName = {"cliente", "codigo", "nombre", "especie", "raza", "genero", "edad", "restricciones", "color", "alimentacion", "observaciones", "foto", "temperamento", "observacion"};
        boolean[] cadena = {true, true, true, true, true, true, true, true, true, true, true, true, true, true};
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        ndMascota nodo = metodos.llenarMascota(data);
        return nodo;
    }

    public boolean modificarMascota(ndMascota nodo) {
        boolean ok = false;
        String instruccion_sql = "update bdMascotas set cliente=?, nombre=?, especie=?, raza=?, genero=?, edad=?, restricciones=?, color=?, alimentacion=?, observaciones=?, foto=?, temperamento=?, observacion=?"
                + " where codigo='" + nodo.getCliente() + "' ;";
        ok = Actualizar_Registro(metodos.pasarDatosMascotas(nodo), null, instruccion_sql);
        return ok;
    }

    public boolean agregarMascota(ndMascota nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdMascotas(cliente, codigo, nombre, especie, raza, genero, edad, restricciones, color, alimentacion, observaciones, foto, temperamento, observacion)"
                + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosMascotas(nodo), null, instruccion_sql);
        return ok;
    }

    public boolean agregarHistoria(ndHistoria nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdHistoria(id, mascota, motivo, evolucion, enfermedades, tratamientos, campo1, campo2, usuario, fecha)"
                + " values(?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosHistoria(nodo), null, instruccion_sql);
        return ok;
    }

    public DefaultTableModel getRegistrosRazas(String tipo) {
        String columNames[] = {" "};
        String colName[] = {"raza"};
        String origen = " bdRazas where tipo ='" + tipo + "' ";
        Object dtDatos[][] = GetTabla(colName, origen, (new StringBuilder()).append("select raza from ").append(origen).toString());
        DefaultTableModel datos = new DefaultTableModel(dtDatos, columNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        return datos;
    }

    public String getCantProsponer() {
        String instruccion_sql = "select prosponer from configuracion; ";
        //columnas de la tabla que se
        String[] colName = {"prosponer"};
        boolean[] cadena = {true};
        try {
            return GetRegistro(colName, cadena, instruccion_sql)[0].toString();
        } catch (Exception e) {
            return "3";
        }
    }

    public String getFacturaNotaCredito(String nc) {
        String instruccion_sql = "select factura from bdNc where Id = '" + nc + "'; ";
        //columnas de la tabla que se
        String[] colName = {"factura"};
        boolean[] cadena = {true};
        try {
            return GetRegistro(colName, cadena, instruccion_sql)[0].toString();
        } catch (Exception e) {
            return "";
        }
    }

    public String getDatosRazas(String cod) {
        String instruccion_sql = "select Id, raza"
                + " from bdRazas where raza = '" + cod + "'; ";
        //columnas de la tabla que se
        String[] colName = {"Id", "raza"};
        boolean[] cadena = {true, true};
        try {
            return GetRegistro(colName, cadena, instruccion_sql)[0].toString();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean agregarRaza(String nombre, String tipo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdRazas(raza, tipo) values (?,?);";
        ok = Agregar_Registro(new Object[]{nombre, tipo}, null, instruccion_sql);
        return ok;
    }

    public boolean agregarTemperamento(String Temperamento) {
        boolean ok = false;
        String instruccion_sql = "insert into bdTemperamento(temperamento) values (?);";
        ok = Agregar_Registro(new Object[]{Temperamento}, null, instruccion_sql);
        return ok;
    }

    public DefaultTableModel getRegistrosTemperamento() {
        String columNames[] = {" "};
        String colName[] = {"temperamento"};
        String origen = " bdTemperamento ";
        Object dtDatos[][] = GetTabla(colName, origen, (new StringBuilder()).append("select temperamento from ").append(origen).toString());
        DefaultTableModel datos = new DefaultTableModel(dtDatos, columNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        return datos;
    }

    public boolean modificarHistoria(ndHistoria nodo) {
        boolean ok = false;
        String instruccion_sql = "update bdHistoria set mascota=?, motivo=?, evolucion=?, enfermedades=?, tratamientos=?, "
                + "campo1=?, campo2=?, usuario=?, fecha=?"
                + " where id='" + nodo.getId() + "' ;";
        ok = Actualizar_Registro(metodos.pasarDatosHistoria(nodo), null, instruccion_sql);
        return ok;
    }

    public boolean agregarConsulta(ndConsulta nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdConsulta(id, historia, motivo, fc, fr, pulso, peso, tllc, temp, cc, actitud, "
                + "problemas, diagnostico, plan, tratamiento, recomendacion, usuario, fecha, nervioso, digestivo, "
                + "respiratorio, cardiaco, urinario, musculo, piel, oidos, ojos, "
                + "mucosa, llenadoCapilar, caracteristicas, hidratacion, nutricional, organosSentidos, ganglios, proximoControl, fotos, examenes, endocrino)"
                + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosConsulta(nodo), null, instruccion_sql);
        return ok;
    }

    public boolean modificarVersion(String version) {
        boolean ok = false;
        String instruccion_sql = "update configuracion set version=?; ";
        ok = Actualizar_Registro(new Object[]{"", version}, null, instruccion_sql);
        return ok;
    }

    public boolean modificarCantProsponer(int cant) {
        boolean ok = false;
        String instruccion_sql = "update configuracion set prosponer=?; ";
        ok = Actualizar_Registro(new Object[]{"", cant}, null, instruccion_sql);
        return ok;
    }

    public boolean modificarGestionControl(String cod, boolean gestion) {
        boolean ok = false;
        String instruccion_sql = "update bdConsulta set gestion=? where id='" + cod + "' ;";
        ok = Actualizar_Registro(new Object[]{"", gestion}, null, instruccion_sql);
        return ok;
    }

    public boolean asignarConsulta(String idHosp, String consulta) {
        boolean ok = false;
        String instruccion_sql = "update bdIngresosHospitalizacion set consulta = ? where Id='" + idHosp + "' ;";
        ok = Actualizar_Registro(new Object[]{"", consulta}, null, instruccion_sql);
        return ok;
    }

    public ndConsulta getDatosConsulta(String id) {
        String instruccion_sql = "select Id, historia, motivo, fc, fr, pulso, peso, tllc, temp, cc, "
                + "actitud, problemas, diagnostico, plan, tratamiento, recomendacion, usuario, fecha, nervioso, digestivo, "
                + "respiratorio, cardiaco, urinario, musculo, piel, oidos, ojos, "
                + "mucosa, llenadoCapilar, caracteristicas, hidratacion, nutricional, organosSentidos, ganglios, proximoControl, fotos, examenes, endocrino"
                + " from bdConsulta where Id = '" + id + "' ;";
        //columnas de la tabla que se
        String[] colName = {"Id", "historia", "motivo", "fc", "fr", "pulso", "peso", "tllc", "temp", "cc", "actitud", "problemas",
            "diagnostico", "plan", "tratamiento", "recomendacion", "usuario", "fecha", "nervioso", "digestivo",
            "respiratorio", "cardiaco", "urinario", "musculo", "piel", "oidos", "ojos", "mucosa", "llenadoCapilar", "caracteristicas", "hidratacion",
            "nutricional", "organosSentidos", "ganglios", "proximoControl", "fotos", "examenes", "endocrino"};
        boolean[] cadena = {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,
            true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true};
        //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        ndConsulta nodo = metodos.llenarConsulta(data);
        return nodo;
    }

    public Object[] infoVacunas(String id) {
        String instruccion_sql = "select fecha, marca, lote, proxima from bdVacunacion where id = " + id + " ";
        String[] colName = {"fecha", "marca", "lote", "proxima"};
        boolean[] cadena = {true, true, true, true};
        //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        return data;
    }

    public boolean modificarDiagnostico(String detalle, String consulta) {
        boolean ok = false;
        String instruccion_sql = "update bdConsulta set plan=? where id = '" + consulta + "' ;";
        ok = Actualizar_Registro(new Object[]{"", detalle}, null, instruccion_sql);
        return ok;
    }

    public ndHistoria getDatosHistoria(String mascota) {
        String instruccion_sql = "select Id, mascota, motivo, evolucion, enfermedades, tratamientos, campo1, campo2, "
                + "usuario, fecha from bdHistoria where mascota = '" + mascota + "'; ";
        String[] colName = {"Id", "mascota", "motivo", "evolucion", "enfermedades", "tratamientos", "campo1", "campo2", "usuario", "fecha"};
        boolean[] cadena = {true, true, true, true, true, true, true, true, true, true};
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        ndHistoria nodo = metodos.llenarHistoria(data);
        return nodo;
    }

    public boolean agregarSeguimiento(ndSeguimiento nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdSeguimiento(fecha, usuario, evolucion, observaciones, fotos, consulta, examen1, examen2, examen3, examen4, fc, tllc, fr, t, "
                + "pulso, cc, peso, mucosa)"
                + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosSeguimiento(nodo), null, instruccion_sql);
        return ok;
    }

    public boolean agregarQuirurgica(String id, String historia, String fecha, String usuario, String tipo, String observacion) {
        boolean ok = false;
        String instruccion_sql = "insert into bdQuirurgica(Id, historia, fecha, usuario, tipo, observacion) values (?,?,?,?,?,?);";
        ok = Agregar_Registro(new Object[]{id, historia, fecha, usuario, tipo, observacion}, null, instruccion_sql);
        return ok;
    }

    public ndSeguimientoClinica getDatosSeguimientoClinica(String id) {
        String instruccion_sql = "select Id, fecha, usuario, evolucion, observaciones, consulta, examenesId, examenesDesc, "
                + "examenesUrl, t, peso, pa, fr from bdSeguimientoClinica where Id = " + id + "; ";
        String[] colName = {"Id", "fecha", "usuario", "evolucion", "observaciones", "consulta", "examenesId", "examenesDesc", "examenesUrl",
            "t", "peso", "pa", "fr"};
        boolean[] cadena = {true, true, true, true, true, true, true, true, true, true, true, true, true};
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        ndSeguimientoClinica nodo = metodos.llenarSeguimientoClinica(data);
        return nodo;
    }
//    public ndSeguimientoClinica getDatosSeguimientoClinica(String id) {
//        String instruccion_sql = "select Id, fecha, usuario, evolucion, observaciones, fotos, consulta, examen1, examen2, "
//                + "examen3, examen4 from bdSeguimientoClinica where Id = " + id + "; ";
//        String[] colName = {"Id", "fecha", "usuario", "evolucion", "observaciones", "fotos", "consulta", "examen1", "examen2", "examen3", "examen4"};
//        boolean[] cadena = {true, true, true, true, true, true, true, true, true, true, true};
//        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
//        ndSeguimientoClinica nodo = metodos.llenarSeguimientoClinica(data);
//        return nodo;
//    }

    public boolean agregarRemisionVeterinaria(String id, String historia, String fecha, String usuario, String tipo, String observacion) {
        boolean ok = false;
        String instruccion_sql = "insert into bdRemisionVeterinaria(Id, historia, fecha, usuario, tipo, observacion) values (?,?,?,?,?,?);";
        ok = Agregar_Registro(new Object[]{id, historia, fecha, usuario, tipo, observacion}, null, instruccion_sql);
        return ok;
    }

    public ndSeguimiento getDatosSeguimiento(String id) {
        String instruccion_sql = "select Id, fecha, usuario, evolucion, observaciones, fotos, consulta, examen1, examen2, examen3, examen4, fc, tllc, fr, t, pulso, cc, peso, mucosa from bdSeguimiento where Id = " + id + "; ";
        String[] colName = {"Id", "fecha", "usuario", "evolucion", "observaciones", "fotos", "consulta", "examen1", "examen2", "examen3", "examen4", "fc", "tllc", "fr", "t", "pulso", "cc", "peso", "mucosa"};
        boolean[] cadena = {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true};
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        ndSeguimiento nodo = metodos.llenarSeguimiento(data);
        return nodo;
    }

    public String getConsultaAsociada(String id) {
        String instruccion_sql = "select consulta from bdIngresosHospitalizacion where Id = '" + id + "';";
        String[] colName = {"consulta"};
        boolean[] cadena = {true};
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        return data[0].toString();
    }

    public String getIdMascota(String id) {
        String instruccion_sql = "select mascota from bdHistoria where Id = '" + id + "';";
        String[] colName = {"mascota"};
        boolean[] cadena = {true};
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        return data[0].toString();
    }

    public String getIdHistoria(String ID) {
        String instruccion_sql = "select Id from bdHistoria where mascota ='" + ID + "'; ";
        String[] colName = {"Id"};
        boolean[] cadena = {true};
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        return data[0].toString();
    }

    public String getDatosMedicamentosVeterinario(String cod) {
        String instruccion_sql = "select Id,nombre"
                + " from bdMedicamentosVeterinaria where nombre = '" + cod + "'; ";
        String[] colName = {"Id", "nombre"};
        boolean[] cadena = {true, true};
        try {
            return GetRegistro(colName, cadena, instruccion_sql)[0].toString();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean agregarMedicamentoVeterinario(String nombre) {
        boolean ok = false;
        String instruccion_sql = "insert into bdMedicamentosVeterinaria(nombre) values (?);";
        ok = Agregar_Registro(new Object[]{nombre}, null, instruccion_sql);
        return ok;
    }

    public DefaultTableModel getRegistrosMedicamentosVeterinaria() {
        String columNames[] = {"Descripción"};
        String colName[] = {"nombre"};
        String origen = " bdMedicamentosVeterinaria";
        Object dtDatos[][] = GetTabla(colName, origen, (new StringBuilder()).append("select nombre from ").append(origen).toString());
        DefaultTableModel datos = new DefaultTableModel(dtDatos, columNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        return datos;
    }

    public Object[][] getResumenVentas(String sql) {
        String colName[] = {
            "efectivoGeneral", "subtotalGeneral", "ivaGeneral", "totalGeneral", "impoconsumo"
        };
        String origen = " facturacion ";
        Object dtDatos[][] = getDatos(colName, origen, " SELECT SUM(facturacion.efectivoGeneral) AS efectivoGeneral, SUM(facturacion.subtotalGeneral) AS subtotalGeneral,"
                + "SUM(facturacion.ivaGeneral) AS ivaGeneral, SUM(facturacion.totalGeneral) AS totalGeneral, SUM(impoconsumo) AS impoconsumo "
                + "FROM facturacion " + sql, sql);
        return dtDatos;
    }

    public Object[][] getResumenCostos(String sql) {
        String colName[] = {
            "SumaDecantidad", "total"
        };
        String origen = " bdFactura ";
        Object dtDatos[][] = getDatos(colName, origen, " SELECT SUM(bdFactura.cantidad) AS SumaDecantidad, SUM(bdCosteo.total) AS total "
                + "FROM bdFactura INNER JOIN bdCosteo ON bdFactura.idCosteo = bdCosteo.Id " + sql, sql);
        return dtDatos;
    }

    public Object[][] getResumenCostos1(String sql) {
        String colName[] = {
            "total"
        };
        String origen = " bdFactura ";
        Object dtDatos[][] = getDatos(colName, origen, " SELECT SUM(cantidad * CAST(nuevoPonderado AS DOUBLE)) AS total "
                + "FROM bdFactura LEFT JOIN bdUltimoPonderado ON bdFactura.producto = bdUltimoPonderado.producto " + sql, sql);
        return dtDatos;
    }

    public Object[][] getRemisiones(String id) {
        String colName[] = {
            "Id", "fecha", "observacion"
        };
        String origen = " bdRemisionVeterinaria";
        Object dtDatos[][] = getDatos(colName, origen, "select Id, fecha, observacion FROM bdRemisionVeterinaria where historia = '"
                + id + "'", " where historia = '" + id + "'");
        return dtDatos;
    }

    public Object[][] getHistoriasQuirurgicas(String id) {
        String colName[] = {
            "Id", "fecha", "observacion"
        };
        String origen = " quirurgicasVeterinaria";
        Object dtDatos[][] = getDatos(colName, origen, "select Id, fecha, observacion FROM quirurgicasVeterinaria where historia = '"
                + id + "'", " where historia = '" + id + "'");
        return dtDatos;
    }

    public Object[][] getRegistrosExamenes(String historia, String bd) {
        String colName[] = {"Id", "fecha", "usuario"};
        Object dtDatos[][] = getDatos(colName, bd, "select Id, fecha, usuario from " + bd + " where historia = '" + historia + "'", " where historia = '" + historia + "'");
        return dtDatos;
    }

    public boolean agregarVacuna(Object[] dato) {
        boolean ok = false;
        String instruccion_sql = "insert into bdVacunacion(fecha, mascota, campo, marca, lote, proxima, gestion)"
                + " values(?,?,?,?,?,?,?);";
        ok = Agregar_Registro(dato, null, instruccion_sql);
        return ok;
    }

    public Object[][] getRegistrosIngresoHospital() {
        String colName[] = {
            "codigo", "mascota", "fIngreso", "hIngreso", "id"
        };
        String origen = " ingresosHospitalPendientes";
        Object dtDatos[][] = GetTabla(colName, origen, (new StringBuilder()).append("select codigo, mascota, fIngreso, hIngreso, id from ").append(origen).toString());

        return dtDatos;
    }

    /* INICIO PARQUEADERO */
    public DefaultTableModel getMensualidades() {
        String columNames[] = {
            "ID", "Placa", "Cliente", "Fecha Inicio", "Fecha Final", "Días", "Meses", "Restantes", "estado"
        };
        String colName[] = {
            "id", "placa", "nombre", "fechaInicio", "fechaFinal", "dias", "meses", "restantes", "estado"
        };
        String origen = " conMensualidades where estado <> 'VENCIDA'";
        Object dtDatos[][] = GetTabla(colName, origen, (new StringBuilder()).append("select id,placa, nombre,fechaInicio, fechaFinal, dias, meses, restantes, estado from ").append(origen).toString());
        DefaultTableModel datos = new DefaultTableModel(dtDatos, columNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        return datos;
    }

    public boolean agregarMensualidad(Object[] info) {
        boolean ok = false;
        String instruccion_sql = "insert into bdMensualidad(placa, fechaInicio, dias, valor, fechaFinal, fechaAlerta, meses, codServicio, servicio, factura) values (?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(new Object[]{info[0], info[1], info[2], info[3], info[4], info[5], info[6], info[7], info[8], info[9]}, null, instruccion_sql);
        return ok;
    }

    public boolean agregarTerminosGarantia(String terminos) {
        boolean ok = false;
        String instruccion_sql = "update bdMaestra set terminosGarantia=? where Id = 1;";
        ok = Agregar_Registro(new Object[]{terminos}, null, instruccion_sql);
        return ok;
    }

    public boolean agregarCertificadoMedico(ndCertificadoMedico nodo, String tipo) {
        boolean ok = false;

        String instruccion_sql = "insert into bdCertificadoMedico" + tipo + "(id, historia, responsable, nombre, parentesco, motivo, enfermedad, revision, inspeccion, temp, peso, "
                + "estatura, fc, fr, pa, cefalico, abdominal, imc, cabeza, ojos, agudezaVisual, oidos, nariz, boca, cuello, cardioPulmonar, torax, abdomen, genitoUrinario, ano, "
                + "extremidades, neurologico, pies, osteoMuscular, diagPrincipal, diagRelacionado, diagRelacionado2, diagRelacionado3, tratamiento, recomendaciones, cup, usuario, fecha,"
                + " patologicos, quirurgicos, alergicos, traumaticos, ginecoobstetricos, familiares, social, muscular ) "
                + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

        ok = Agregar_Registro(metodos.pasarDatosCertificadoMedico(nodo), null, instruccion_sql);

        return ok;
    }

    public boolean finalizarParqueadero(String id, String fechaSalida, String horaSalida, String horas, String factura) {
        boolean ok = false;
        String instruccion_sql = "update bdParqueaderoAutos set fSalida=?, hSalida=?, horas=?, estado=?, factura=? where Id='" + id + "' ;";
        ok = Actualizar_Registro(new Object[]{"", fechaSalida, horaSalida, horas, "FINALIZADO", factura}, null, instruccion_sql);
        return ok;
    }

    public boolean cambiarDescripcionEmpresa(String id, String observacion) {
        boolean ok = false;
        String instruccion_sql = "update bdMultiEmpresa set descripcion=? where id = '" + id + "' ;";
        ok = Actualizar_Registro(new Object[]{"", observacion}, null, instruccion_sql);
        return ok;
    }

    public boolean cambiarObservacionParqueadero(String id, String observacion) {
        boolean ok = false;
        String instruccion_sql = "update bdParqueaderoAutos set observacion=? where Id='" + id + "' ;";
        ok = Actualizar_Registro(new Object[]{"", observacion}, null, instruccion_sql);
        return ok;
    }

    public boolean modificarMaestraParqueadero(Boolean egresoAuto, String inicioDia, String finDia, String inicioNoche, String finNoche, Boolean cupo, Boolean diasPlazo,
            Boolean cobrarCasco, BigDecimal valor, Boolean modificarPrecio) {
        boolean ok = false;
        String instruccion_sql = "update bdMaestraParqueadero set egresoAutomatico=?, inicioDia=?, finDia=?, inicioNoche=?, finNoche=?, "
                + "cupo=?, diasPlazo=?, cobrarCasco=?, modificarPrecio=?, valorCobro=? where Id = 1 ;";
        ok = Actualizar_Registro(new Object[]{"", egresoAuto, inicioDia, finDia, inicioNoche, finNoche, cupo, diasPlazo, cobrarCasco, modificarPrecio}, new Object[]{valor}, instruccion_sql);
        return ok;
    }

    public boolean insertarMaestraParqueadero(Boolean egresoAuto, String inicioDia, String finDia, String inicioNoche, String finNoche, Boolean cupo, Boolean diasPlazo,
            Boolean cobrarCasco, BigDecimal valor, Boolean modificarPrecio) {
        boolean ok = false;
        String instruccion_sql = "insert into bdMaestraParqueadero (egresoAutomatico, inicioDia, finDia, inicioNoche, finNoche, cupo, diasPlazo, cobrarCasco, "
                + "modificarPrecio, valorCobro) values(?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(new Object[]{egresoAuto, inicioDia, finDia, inicioNoche, finNoche, cupo, diasPlazo, cobrarCasco, modificarPrecio}, new Object[]{valor}, instruccion_sql);
        return ok;
    }

    public String getIdMaestraParqueadero() {
        String colName[] = {"Id"};
        String origen = " bdMaestraParqueadero";
        Object dtDatos[][] = getDatos(colName, origen, "select Id FROM bdMaestraParqueadero ", "");
        try {
            return dtDatos[0][0].toString();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean modificarMaestraParqueadero(String fila, String columnas) {
        boolean ok = false;
        String instruccion_sql = "update bdMaestraParqueadero set filas=?, columnas=? where Id = 1 ;";
        ok = Actualizar_Registro(new Object[]{"", fila, columnas}, null, instruccion_sql);
        return ok;
    }

    public boolean modificarMontoBanco(BigDecimal monto, String numero) {
        boolean ok = false;
        String instruccion_sql = "update bdBancos set monto=? where numero = '" + numero + "' ;";
        ok = Actualizar_Registro(null, new Object[]{monto}, instruccion_sql);
        return ok;
    }

    public boolean agregarParqueaderoAutos(ndParqueadero nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdParqueaderoAutos(Id, placa, producto, fIngreso, fSalida, hIngreso, hSalida,"
                + " horas, dias, noches, full, observacion, estado, factura) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosParqueaderoAutos(nodo), null, instruccion_sql);
        return ok;
    }

    public Object[][] getImpresionesPendientes() {
        String colName[] = {
            "idFactura", "factura2", "valor", "impresora", "titulo", "observaciones", "tipo"
        };
        String origen = " bdImpresionesSistema";
        Object dtDatos[][] = GetTabla(colName, origen, (new StringBuilder()).append("select idFactura, factura2, valor, impresora, titulo, "
                + "observaciones, tipo from ").append(origen).toString());

        return dtDatos;
    }

    public Object[][] getResumenGerencial(String sql) {
        String colName[] = {
            "fechaFactura", "porcIva", "SumaDeiva", "SumaDesubtotal", "SumaDedescuento", "SumaDetotal", "producto", "cliente", "vendedor"
        };
        String origen = "repIvasVentas" + sql;
        Object dtDatos[][] = GetTabla(colName, origen, (new StringBuilder()).append("select fechaFactura, porcIva, SumaDeiva, SumaDesubtotal, "
                + "SumaDedescuento, SumaDetotal, producto, cliente, vendedor from ").append(origen).toString());

        return dtDatos;
    }

    public Object[][] getReporteDeImpuestos(String sql, String tabla, String tipoPorc) {
        String colName[] = {
            "fechaFactura", tipoPorc, "SumaDeiva", "SumaDesubtotal", "SumaDedescuento", "SumaDetotal", "producto", "cliente", "vendedor"
        };
        String origen = tabla + sql;
        Object dtDatos[][] = GetTabla(colName, origen, (new StringBuilder()).append("select fechaFactura, " + tipoPorc + ", SumaDeiva, SumaDesubtotal, "
                + "SumaDedescuento, SumaDetotal, producto, cliente, vendedor from ").append(origen).toString());
        return dtDatos;
    }

    public Object[][] getRegistrosParqueaderoAutos() {
        String colName[] = {
            "placa", "hIngreso", "fIngreso", "Descripcion", "observacion", "Id", "producto"
        };
        String origen = " parqueaderosPendientes";
        Object dtDatos[][] = GetTabla(colName, origen, (new StringBuilder()).append("select placa, hIngreso, fIngreso, Descripcion, observacion, Id, producto from ").append(origen).toString());

        return dtDatos;
    }

    public Object[][] getRegistrosParqueaderoAutos(String placa) {
        String colName[] = {
            "placa", "hIngreso", "fIngreso", "observacion"
        };
        String origen = " parqueaderosPendientes where placa = '" + placa + "'";
        Object dtDatos[][] = GetTabla(colName, origen, (new StringBuilder()).append("select placa, hIngreso, fIngreso, observacion from ").append(origen).toString());

        return dtDatos;
    }

    public Object[][] getRegistrosParqueaderoAutos1(String id) {
        String colName[] = {
            "placa", "hIngreso", "fIngreso", "observacion"
        };
        String origen = " parqueaderosPendientes where Id = '" + id + "'";
        Object dtDatos[][] = GetTabla(colName, origen, (new StringBuilder()).append("select placa, hIngreso, fIngreso, observacion from ").append(origen).toString());

        return dtDatos;
    }

    public boolean agregarRegistrosIvas(String porc, BigDecimal base, BigDecimal subtotal, BigDecimal total, BigDecimal totalIva,
            BigDecimal descuento) {
        boolean ok = false;
        String instruccion_sql = "insert into bdGenerarIvas(porc, base, subtotal, total, totalIva, descuento)"
                + " values(?,?,?,?,?,?);";
        ok = Agregar_Registro(new Object[]{porc}, new Object[]{base, subtotal, total, totalIva, descuento}, instruccion_sql);
        return ok;
    }

    public boolean agregarGeneracionInterfase(String numDocumento, String comprobante, String detalle, String numero, String cuenta,
            String terceo, String centroContro, BigDecimal debito, BigDecimal credito) {
        boolean ok = false;
        String instruccion_sql = "insert into bdGeneracionInterfase(numDocumento, comprobante, detalle, numero, cuenta, tercero, "
                + "centroCosto, debito, credito) values(?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(new Object[]{numDocumento, comprobante, detalle, numero, cuenta, terceo, centroContro},
                new Object[]{debito, credito}, instruccion_sql);
        return ok;
    }

    public boolean agregarRegistrosResumenDia(String rango, BigDecimal efectivoGeneral, BigDecimal ivaGeneral, BigDecimal totalSinPreparacion, BigDecimal totalGeneral,
            BigDecimal totalPreparacion, BigDecimal resultado1, BigDecimal resultado2, BigDecimal resultado3, BigDecimal impoconsumoGeneral) {
        boolean ok = false;
        String instruccion_sql = "insert into bdGenerarResumen(rango, efectivoGeneral, ivaGeneral, totalSinPreparacion,"
                + "totalGeneral, totalPreparacion, resultado1, resultado2, resultado3, impoconsumoGeneral)"
                + " values(?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(new Object[]{rango}, new Object[]{efectivoGeneral, ivaGeneral, totalSinPreparacion, totalGeneral, totalPreparacion, resultado1, resultado2,
            resultado3, impoconsumoGeneral}, instruccion_sql);
        return ok;
    }

    public boolean agregarCasco(String identificador) {
        boolean ok = false;
        String instruccion_sql = "insert into bdCascos(identificador)"
                + " values(?);";
        ok = Agregar_Registro(new Object[]{identificador}, null, instruccion_sql);
        return ok;
    }

    public boolean asignarCasco(String Id, String placa, String obv) {
        boolean ok = false;
        String instruccion_sql = "update bdCascos set placa=?, descripcion=? where identificador = '" + Id + "' ;";
        ok = Actualizar_Registro(new Object[]{"", placa, obv}, null, instruccion_sql);
        return ok;
    }

    public boolean modificarPonderado(String fecha, String producto, BigDecimal ponderadoAntiguo, String cantidadAntigua, String cantidadEntrante,
            BigDecimal nuevoPonderado, String nuevaCantidad, String usuario, BigDecimal ultimoCosto, String ingreso) {
        boolean ok = false;
        String instruccion_sql = "update bdUltimoPonderado set fecha=?, cantidadAntigua=?, cantidadEntrante=?, "
                + "nuevaCantidad=?, usuario=?, ingreso=?, ponderadoAntiguo=?, nuevoPonderado=?, ultimoCosto=? "
                + "where producto = '" + producto + "' ;";
        ok = Actualizar_Registro(new Object[]{"", fecha, cantidadAntigua, cantidadEntrante,
            nuevaCantidad, usuario, ingreso}, new Object[]{ponderadoAntiguo, nuevoPonderado, ultimoCosto}, instruccion_sql);
        return ok;
    }

    public boolean agregarUltimoPonderado(String fecha, String producto, BigDecimal ponderadoAntiguo, String cantidadAntigua, String cantidadEntrante,
            BigDecimal nuevoPonderado, String nuevaCantidad, String usuario, BigDecimal ultimoCosto, String ingreso) {
        boolean ok = false;
        String instruccion_sql = "insert into bdUltimoPonderado(producto, fecha, cantidadAntigua, cantidadEntrante, "
                + "nuevaCantidad, usuario, ingreso, ponderadoAntiguo, nuevoPonderado, ultimoCosto)"
                + " values(?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(new Object[]{producto, fecha, cantidadAntigua, cantidadEntrante,
            nuevaCantidad, usuario, ingreso}, new Object[]{ponderadoAntiguo, nuevoPonderado, ultimoCosto}, instruccion_sql);
        return ok;
    }

    public boolean agregarPonderado(String fecha, String producto, BigDecimal ponderadoAntiguo, String cantidadAntigua, String cantidadEntrante,
            BigDecimal nuevoPonderado, String nuevaCantidad, String usuario, BigDecimal ultimoCosto, String ingreso) {
        boolean ok = false;

        String instruccion_sql = "insert into bdPonderado(fecha, producto, cantidadAntigua, cantidadEntrante, nuevaCantidad, usuario, "
                + "ingreso, ponderadoAntiguo, nuevoPonderado, ultimoCosto) values(?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(new Object[]{fecha, producto, cantidadAntigua, cantidadEntrante, nuevaCantidad,
            usuario, ingreso}, new Object[]{ponderadoAntiguo, nuevoPonderado, ultimoCosto}, instruccion_sql);
        return ok;
    }

    public boolean agregarImpresora(String impresora) {
        boolean ok = false;
        String instruccion_sql = "insert into bdImpresoras (nombre)"
                + " values(?);";
        ok = Agregar_Registro(new Object[]{impresora}, null, instruccion_sql);
        return ok;
    }

    public boolean agregarRegistroMesa(String ubicacion, String nombre) {
        boolean ok = false;
        String instruccion_sql = "insert into bdMesas (ubicacion, nombre)"
                + " values(?,?);";
        ok = Agregar_Registro(new Object[]{ubicacion, nombre}, null, instruccion_sql);
        return ok;
    }

    public boolean agregarExamenesLaboratorio(String codigo, String descripcion, BigDecimal valor, String codProd) {
        boolean ok = false;
        String instruccion_sql = "insert into bdExamenesLaboratorio (codigo, descripcion, codProd, valor)"
                + " values(?,?,?,?);";
        ok = Agregar_Registro(new Object[]{codigo, descripcion, codProd}, new Object[]{valor}, instruccion_sql);
        return ok;
    }

    public ndRecordatorio getDatosRecordatorio(String id) {
        boolean ok = false;
        String instruccion_sql = "select id, fecha, hora, tipo, tipoTercero, tercero, asunto, descripcion, estado, alerta, usuario, fechaRegistro"
                + " from bdRecordatorios where Id='" + id + "'";
        String[] colName = {"id", "fecha", "hora", "tipo", "tipoTercero", "tercero", "asunto", "descripcion", "estado", "alerta", "usuario", "fechaRegistro"};
        boolean[] cadena = {true, true, true, true, true, true, true, true, true, true, true, true};
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        ndRecordatorio nodo = metodos.llenarRecordatorio(data);
        return nodo;
    }

    public Object[][] getAlertasRecordatorios(String estado, String fecha) {
        String colName[] = {
            "Id", "fecha", "hora", "alerta"
        };
        String origen = " conRecordatorios";
        Object dtDatos[][] = getDatos(colName, origen, "select Id, fecha, hora, alerta from" + origen + " where estado='" + estado + "' and "
                + "fecha <= '" + fecha + "' order by fecha", " where estado = '" + estado + "' and fecha <= '" + fecha + "' ");

        return dtDatos;
    }

    public Object[][] getRegistrosRecordatorios(String estado) {
        String colName[] = {
            "Id", "fecha", "hora", "tipo", "tercero", "nombre", "asunto", "descripcion", "estado"
        };
        String origen = " conRecordatorios";
        Object dtDatos[][] = getDatos(colName, origen, "select Id, fecha, hora, tipo, tercero, nombre,asunto,descripcion, estado from" + origen + " where estado='" + estado + "' order by fecha", " where estado = '" + estado + "' ");

        return dtDatos;
    }

    public boolean agregarRecordatorio(ndRecordatorio nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdRecordatorios(id, fecha, hora, tipo, tipoTercero, tercero, asunto, descripcion, estado, alerta, usuario, "
                + "fechaRegistro) values (?,?,?,?,?,?,?,?,?,?,?,?);";

        ok = Agregar_Registro(metodos.pasarDatosRecordatorio(nodo), null, instruccion_sql);
        return ok;
    }

    public boolean modificarRecordatorio(ndRecordatorio nodo) {
        boolean ok = false;
        String instruccion_sql = "update bdRecordatorios set fecha=?, hora=?, tipo=?, tipoTercero=?, tercero=?, asunto=?, descripcion=?, estado=?, alerta=?, "
                + "usuario=?, fechaRegistro=? where Id='" + nodo.getId() + "' ;";

        ok = Actualizar_Registro(metodos.pasarDatosRecordatorio(nodo), null, instruccion_sql);
        return ok;
    }

    public void cambiarEstadoRecordatorio(String estado, String id) {
        String instruccion_sql = "update bdRecordatorios set estado=? WHERE id ='" + id + "' ;";
        Actualizar_Registro(new Object[]{"", estado}, null, instruccion_sql);
    }

    public boolean agregarDetalleOrdenServicio(String orden, String id, String nombreParte, Boolean inventario, String problemasDerecha, String problemasIzquierda, String observaciones, int num) {
        boolean ok = false;
        String instruccion_sql = "insert into bdDetalleOrdenServicio(ordenServicio, idParte, nombreParte, inventario, problemasDerecha, problemasIzquierda, observaciones, num)"
                + " values(?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(new Object[]{orden, id, nombreParte, inventario, problemasDerecha, problemasIzquierda, observaciones}, new Object[]{num}, instruccion_sql);
        return ok;
    }

    public boolean agregarProductosRelacionados(String idProd, String prod, String nombre, String relacion, String nombreProd, String cant) {
        boolean ok = false;
        String instruccion_sql = "insert into bdRelacionados(idProducto, prod, nombre, relacion, nombreProd, cant)"
                + " values(?,?,?,?,?,?);";
        ok = Agregar_Registro(new Object[]{idProd, prod, nombre, relacion, nombreProd, cant}, null, instruccion_sql);
        return ok;
    }

    public boolean agregarCodigosRelacionados(String idProd, String cod) {
        boolean ok = false;
        String instruccion_sql = "insert into bdCodigosRelacionados(idProducto, codigo)"
                + " values(?,?);";
        ok = Agregar_Registro(new Object[]{idProd, cod}, null, instruccion_sql);
        return ok;
    }

    public boolean agregarServicios(String codigo, String servicio, BigDecimal valorHora, String minCobro1Hora, String minCobro2Hora, BigDecimal dia, String noHorasDia,
            BigDecimal noche, String noHorasNoche, BigDecimal diaCompleto, String noHorasCompleto, BigDecimal mensualidad, Boolean hEntrada, Boolean hSalida, String cupo, String horasCobro) {
        boolean ok = false;
        String instruccion_sql = "insert into bdServicios(codigo, servicio, minCobro1Hora, minCobro2Hora, noHorasDia, noHorasNoche, "
                + "noHorasCompleto, entrada, salida, cupo, horasCobro, valorHora, dia, noche, diaCompleto, mensualidad)"
                + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

        ok = Agregar_Registro(new Object[]{codigo, servicio, minCobro1Hora, minCobro2Hora, noHorasDia, noHorasNoche, noHorasCompleto, hEntrada, hSalida, cupo, horasCobro},
                new Object[]{valorHora, dia, noche, diaCompleto, mensualidad}, instruccion_sql);
        return ok;
    }

    public DefaultTableModel getRegistrosPlacas1() {
        String columNames[] = {
            "ID", "Nombre", "Placa"
        };
        String colName[] = {
            "Id", "nombre", "placa"
        };
        String origen = " placas";
        Object dtDatos[][] = GetTabla(colName, origen, (new StringBuilder()).append("select Id, nombre, placa from ").append(origen).toString());
        DefaultTableModel datos = new DefaultTableModel(dtDatos, columNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        return datos;
    }

    public DefaultTableModel getRegistrosPlacasBuscador(String condicion) {
        String columNames[] = {
            " "
        };
        String colName[] = {
            "placa"
        };
        String origen = " bdPlacas " + condicion;
        Object dtDatos[][] = GetTabla(colName, origen, (new StringBuilder()).append("select placa from ").append(origen).toString());
        DefaultTableModel datos = new DefaultTableModel(dtDatos, columNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        return datos;
    }

    public Object[][] getLavadoPendiente(String cliente) {
        String colName[] = {
            "id", "fechaRealizado", "placa", "nombre", "servicio", "empleado"
        };
        String origen = "bdLavado";
        Object dtDatos[][] = getDatos(colName, origen, "select id, fechaRealizado, placa, nombre, servicio, empleado from " + origen + " where cliente = '" + cliente + "' AND liquidado = 'PENDIENTE'", " where cliente = '" + cliente + "' AND liquidado = 'PENDIENTE'");

        return dtDatos;
    }

    public Object[][] getEstadoVehiculo(String orden) {
        String colName[] = {
            "idParte", "nombreParte", "problemasDerecha", "problemasIzquierda", "observaciones"
        };
        String origen = "bdDetalleOrdenServicio";
        Object dtDatos[][] = getDatos(colName, origen, "select idParte, nombreParte, problemasDerecha, problemasIzquierda, observaciones from " + origen + " where ordenServicio = '" + orden + "' ", " where ordenServicio = '" + orden + "' ");

        return dtDatos;
    }

    public Object[][] getRegistrosLavadosPendientes() {
        String colName[] = {
            "id", "fechaRealizado", "placa", "nombre", "servicio", "empleado"
        };
        String origen = "bdLavado";
        Object dtDatos[][] = getDatos(colName, origen, "select id, fechaRealizado, placa, nombre, servicio, empleado from " + origen + " where liquidado = 'PENDIENTE'", " where liquidado = 'PENDIENTE'");

        return dtDatos;
    }

    public Object[][] getTrasladosPendientes() {
        String colName[] = {
            "Id", "origen", "destino", "fecha", "diasPlazo", "BodegaDestino", "BodegaOrigen"
        };
        String origen = "traslados1";
        Object dtDatos[][] = getDatos(colName, origen, "select Id, origen, destino, fecha, diasPlazo, BodegaDestino, BodegaOrigen from " + origen + " where estado = 'PENDIENTE' and anulada = false ", " where estado = 'PENDIENTE' and anulada = false ");
        return dtDatos;
    }

    public Object[][] getRegistrosFacturacionAutomatica(String condicion) {
        String colName[] = {
            "idFactura", "cliente", "totalGeneral", "descripcion", "fechaUltimoPago", "cantFacturados", "preparacion", "inicio", "hasta", "cantIncremento"};
        String origen = " bdCuentaCobro ";
        Object dtDatos[][] = getDatos(colName, origen, "select idFactura, cliente, totalGeneral, descripcion, fechaUltimoPago, cantFacturados, preparacion, "
                + "inicio, hasta, cantIncremento from bdCuentaCobro " + condicion, condicion);
        return dtDatos;
    }

    public Object[][] getMesFacturado(String mes, String tercero) {
        String colName[] = {"idFactura"};
        String origen = " bdFactura ";
        Object dtDatos[][] = getDatos(colName, origen, "select idFactura from mesFacturado where mesFacturado = '" + mes + "' and cliente = '" + tercero + "'; ", "where mesFacturado = '" + mes + "' and cliente = '" + tercero + "' ");
        return dtDatos;
    }

    public boolean agregarLavado(ndLavado nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdLavado(id, empleado, servicio, usuario, fecha, fechaRealizado, placa, cliente, nombre, liquidado, tipoVehiculo, marca, oServicio) values (?,?,?,?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosLavado(nodo), null, instruccion_sql);
        return ok;
    }

    public String getCantidadProductos(String id) {
        String colName[] = {"cantidad"};
        String origen = " bdDetalleProductos";
        Object dtDatos[][] = getDatos(colName, origen, "select cantidad FROM bdDetalleProductos where Id = " + id + ";", " where Id = " + id + " ");
        return dtDatos[0][0].toString();
    }

    public String getBodegaSepare(String id) {
        String colName[] = {"bodega"};
        String origen = " bdPlanSepare";
        Object dtDatos[][] = getDatos(colName, origen, "select bodega FROM bdPlanSepare where idFactura = '" + id + "';", " where idFactura = '" + id + "' ");
        return dtDatos[0][0].toString();
    }

    public String getIdMedicamentoVeterinario(String id) {
        String colName[] = {"Id"};
        String origen = " bdMedicamentosVeterinaria";
        Object dtDatos[][] = getDatos(colName, origen, "select Id FROM bdMedicamentosVeterinaria where nombre = '" + id + "';", " where nombre = '" + id + "' ");
        return dtDatos[0][0].toString();
    }

    public String getIdEmpleado(String id) {
        String colName[] = {"idSistema"};
        String origen = " bdTerceros";
        Object dtDatos[][] = getDatos(colName, origen, "select idSistema FROM bdTerceros where nombre = '" + id + "'; ", " where nombre = '" + id + "' ");
        return dtDatos[0][0].toString();
    }

    public String getBaseBodega(String id) {
        String colName[] = {"base"};
        String origen = " bdBodegas";
        Object dtDatos[][] = getDatos(colName, origen, "select base FROM bdBodegas where abreviado = '" + id + "';", " where abreviado = '" + id + "' ");
        return dtDatos[0][0].toString();
    }

    public String getComisionTarjeta(String nombre) {
        String colName[] = {"comision"};
        String origen = " bdTarjetas";
        Object dtDatos[][] = getDatos(colName, origen, "select comision FROM bdTarjetas where franquisia = '" + nombre + "';", " where franquisia = '" + nombre + "' ");
        return dtDatos[0][0].toString();
    }

    public String getTurno() {
        String colName[] = {"turno1"};
        String origen = " bdMaestra";
        Object dtDatos[][] = getDatos(colName, origen, "select turno1 FROM bdMaestra ", "");
        return dtDatos[0][0].toString();
    }

    public boolean agregarProductosLavado(Object[] info) {
        boolean ok = false;
        String instruccion_sql = "insert into bdLavadoProductos (IdLavado, codServicio, servicio, liquidado, empleado, valor) values (?,?,?,?,?,?);";
        ok = Agregar_Registro(new Object[]{info[0], info[1], info[2], info[4], info[5]}, new Object[]{big.getMoneda(info[3].toString())}, instruccion_sql);
        return ok;
    }

    public DefaultTableModel getTodasLiquidaciones(String condicion) {

        String columNames[] = {
            "ID", "Fecha", "Nombre", "Servicio", "Placa", "Tipo Vehiculo", "Marca", "Valor", "Utilidad", "Pago", "Cod Servicio", "Sel", "Empleado", "A"
        };
        String colName[] = {
            "id", "fechaRealizado", "nombre", "servicio", "placa", "tipoVehiculo", "marca", "valor", "utilidad", "pago", "codServicio", "sel", "trabajador", "credito2"
        };
        String origen = " liquidarLavadero " + condicion;
        Object dtDatos2[][] = GetTabla(colName, origen, (new StringBuilder()).append("select id, fechaRealizado, nombre, servicio, placa, tipoVehiculo, marca, valor, utilidad, pago, codServicio, false as sel, trabajador, credito2 from " + origen + "").toString(), new Integer[]{11});
        DefaultTableModel datos = new DefaultTableModel(dtDatos2, columNames) {
            public boolean isCellEditable(int row, int column) {
                if (column == 11) {
                    return true;
                }
                return false;

            }

            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class,
                java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        return datos;
    }

    public DefaultTableModel getLiquidacionPendiente(String condicion) {

        String columNames[] = {
            "ID", "Fecha", "Nombre", "Servicio", "Placa", "Tipo Vehiculo", "Marca", "Valor", "Utilidad", "Pago", "Cod Servicio", "Sel", "Empleado", "A"
        };
        String colName[] = {
            "id", "fechaRealizado", "nombre", "servicio", "placa", "tipoVehiculo", "marca", "valor", "utilidad", "pago", "codServicio", "sel", "trabajador", "credito2"
        };
        String origen = " liquidarLavadero " + condicion;
        Object dtDatos2[][] = GetTabla(colName, origen, (new StringBuilder()).append("select id, fechaRealizado, nombre, servicio, placa, tipoVehiculo, marca, valor, utilidad, pago, codServicio, false as sel, trabajador, credito2 from " + origen + "").toString(), new Integer[]{11});
        DefaultTableModel datos = new DefaultTableModel(dtDatos2, columNames) {
            public boolean isCellEditable(int row, int column) {
                if (column == 11) {
                    return true;
                }
                return false;
            }

            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class,
                java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        return datos;
    }

    public boolean agregarPagos(String pago, String lavado, String servicio, String placa, String tipoVehiculo, String marca, String empleado, BigDecimal valor) {
        boolean ok = false;
        String instruccion_sql = "insert into bdPago (IdPago, IdLavado, servicio, placa, tipoVehiculo, marca, empleado, valor) values (?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(new Object[]{pago, lavado, servicio, placa, tipoVehiculo, marca, empleado}, new Object[]{valor}, instruccion_sql);
        return ok;
    }

    public void cambiarEstadoLavado(String orden, String estado, String producto, String pago, String egreso) {
        String instruccion_sql = "update bdLavadoProductos set liquidado=?, pago=?, egreso=? WHERE IdLavado = 'LAV-" + orden + "' AND codServicio = '" + producto + "';";
        Actualizar_Registro(new Object[]{"", estado, pago, egreso}, null, instruccion_sql);
    }

    public void cambiarEstadoLavado1(String id, String estado, String factura) {
        String instruccion_sql = "update bdLavado set liquidado=?, factura=? WHERE id = '" + id + "';";
        Actualizar_Registro(new Object[]{"", estado, factura}, null, instruccion_sql);
    }

    public Object[][] getEmpleados() {
        String colName[] = {
            "Id", "nombre"
        };
        String origen = " bdTerceros";
        Object dtDatos[][] = getDatos(colName, origen, "select Id, nombre from bdTerceros where tipoTercero = 'EMPLEADO' ", " where tipoTercero = 'EMPLEADO' ");
        return dtDatos;
    }

    public Object[][] getDatosConfig() {
        String colName[] = {
            "codigo", "servicio", "valorHora", "minCobro1Hora", "minCobro2Hora", "dia", "noHorasDia", "noche", "noHorasNoche", "diaCompleto", "noHorasCompleto", "mensualidad", "entrada", "salida", "cupo", "horasCobro"
        };
        String origen = " bdServicios";
        Object dtDatos[][] = getDatos(colName, origen, "select codigo, servicio, valorHora, minCobro1Hora, minCobro2Hora, dia, noHorasDia, noche, noHorasNoche, "
                + "diaCompleto, noHorasCompleto, mensualidad, entrada, salida, cupo, horasCobro from bdServicios", "");
        return dtDatos;
    }

    public Object[][] getDatosConfig(String codigo) {
        String colName[] = {
            "codigo", "servicio", "valorHora", "minCobro1Hora", "minCobro2Hora", "dia", "noHorasDia", "noche", "noHorasNoche",
            "diaCompleto", "noHorasCompleto", "mensualidad", "entrada", "salida", "cupo", "horasCobro"
        };
        String origen = " bdServicios";
        Object dtDatos[][] = getDatos(colName, origen, "select codigo, servicio, valorHora, minCobro1Hora, minCobro2Hora, dia, noHorasDia, noche, noHorasNoche, "
                + "diaCompleto, noHorasCompleto, mensualidad, entrada, salida, cupo, horasCobro from bdServicios where codigo = '" + codigo + "'", " where codigo = '" + codigo + "'");
        return dtDatos;
    }

    public Object[][] getMensualidadesActivas(String placa, String servicio) {
        String colName[] = {
            "fechaFinal"
        };
        String origen = " conMensualidades";
        Object dtDatos[][] = getDatos(colName, origen, "select fechaFinal from conMensualidades where placa = '" + placa + "' and estado = 'DISPONIBLE'", " where placa = '" + placa + "' and estado = 'DISPONIBLE'");
        return dtDatos;
    }

    public Object[][] getCascos(String placa) {
        String colName[] = {
            "identificador"
        };
        String origen = " bdCascos";
        Object dtDatos[][] = getDatos(colName, origen, "select identificador from bdCascos where placa = '" + placa + "' ",
                " where placa = '" + placa + "' ");
        return dtDatos;
    }

    public Object[][] getDatosMaestraParqueadero() {
        String colName[] = {
            "filas", "columnas", "egresoAutomatico", "inicioDia", "finDia", "inicioNoche", "finNoche", "cupo", "diasPlazo", "cobrarCasco",
            "valorCobro", "modificarPrecio"
        };
        String origen = " bdMaestraParqueadero";
        Object dtDatos[][] = getDatos(colName, origen, "select filas, columnas, egresoAutomatico, inicioDia, finDia, inicioNoche, finNoche, cupo, diasPlazo, cobrarCasco, valorCobro, modificarPrecio from bdMaestraParqueadero", "");
        return dtDatos;
    }

    public Object[][] getDatosCascos() {
        String colName[] = {
            "identificador", "placa", "descripcion"
        };
        String origen = " bdCascos";
        Object dtDatos[][] = getDatos(colName, origen, "select identificador, placa, descripcion from bdCascos", "");
        return dtDatos;
    }

    public Object[][] getPlacasUsuario(String usuario) {

        String colName[] = {
            "usuario", "placa", "tipo", "modelo", "marca", "color", "chasis", "motor", "fechaCompra"
        };
        String origen = " bdPlacas";
        Object dtDatos[][] = getDatos(colName, origen, "select usuario, placa, tipo, modelo, marca, color, chasis, motor, fechaCompra from bdPlacas where usuario = '" + usuario + "'", " where usuario ='" + usuario + "'");
        return dtDatos;
    }

    public Object[][] getPlacas() {

        String colName[] = {
            "usuario", "placa", "tipo", "marca"
        };
        String origen = " bdPlacas";
        Object dtDatos[][] = getDatos(colName, origen, "select usuario, placa, tipo, marca from bdPlacas ", "");
        return dtDatos;
    }

    public boolean agregarPlacas(String usuario, String placa, String tipo, String modelo, String marca, String color, String chasis, String motor, String fechaCompra) {
        boolean ok = false;
        String instruccion_sql = "insert into bdPlacas(usuario, placa, tipo, modelo, marca, color, chasis, motor, fechaCompra) values (?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(new Object[]{usuario, placa, tipo, modelo, marca, color, chasis, motor, fechaCompra}, null, instruccion_sql);
        return ok;
    }

    public boolean agregarRecordatoriosOrden(String placa, String idLista, String nombreServicio, String fechaRealizada, String dias, String proxima, String observacion, String usuario) {
        boolean ok = false;
        String instruccion_sql = "insert into bdRecordatoriosOrden(placa, idLista, nombreServicio, fechaRealizada, dias, proximaRevision, observaciones, usuario) values (?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(new Object[]{placa, idLista, nombreServicio, fechaRealizada, dias, proxima, observacion, usuario}, null, instruccion_sql);
        return ok;
    }

    public boolean agregarProblema(String nombre) {
        boolean ok = false;
        String instruccion_sql = "insert into bdProblemas(problema) values (?);";
        ok = Agregar_Registro(new Object[]{nombre}, null, instruccion_sql);
        return ok;
    }

    public Object[][] getServiciosLavado(String id) {
        String colName[] = {
            "Id", "codServicio"};
        String origen = " bdLavadoProductos";
        Object dtDatos[][] = getDatos(colName, origen, "select id, codServicio from bdLavadoProductos where IdLavado = '" + id + "' ", " where IdLavado = '" + id + "' ");

        return dtDatos;
    }

    public Object[][] getDatosLavado(String producto) {
        String colName[] = {
            "id", "observaciones", "total", "fecha", "fechaRealizado", "cliente", "estadoGeneral", "codServicio", "servicio", "valor", "liquidado", "pago",
            "empleado", "placa", "oServicio", "tipoVehiculo", "marca"};
        String origen = " lavadero";
        Object dtDatos[][] = getDatos(colName, origen, "select id, observaciones, total, fecha, fechaRealizado, cliente, estadoGeneral, codServicio, servicio, valor, "
                + "liquidado, pago, empleado, placa, oServicio, tipoVehiculo, marca from lavadero where id = '" + producto + "' ", " where id = '" + producto + "' ");
        return dtDatos;
    }

    public Object[][] getProductosTraslados(String producto) {
        String colName[] = {
            "Id", "origen", "destino", "fecha", "unidades", "valor", "diasPlazo", "estado", "producto", "cantidad", "lista", "imeil",
            "observacion", "total", "lote", "idProd"};
        String origen = " traslados";
        Object dtDatos[][] = getDatos(colName, origen, "select Id, origen, destino, fecha, unidades, valor, diasPlazo, estado, producto, cantidad, lista, imeil, "
                + "observacion, total, lote, idProd from traslados where Id = '" + producto + "' and estadoProd = 'PENDIENTE' ",
                " where Id = '" + producto + "' and estadoProd = 'PENDIENTE' ");
        return dtDatos;
    }

    public Object[][] getProductosProductosServiciosAdicionales(String id) {
        String colName[] = {
            "producto", "cantidad", "lista", "imeil", "observacion", "total", "lote", "idProd"};
        String origen = " bdProductosServiciosAdiciones";
        Object dtDatos[][] = getDatos(colName, origen, "select producto, cantidad, lista, imeil, observacion, total, lote, idProd "
                + "from bdProductosServiciosAdiciones where traslado = '" + id + "' ", " where traslado = '" + id + "' ");
        return dtDatos;
    }

    public Object[][] getProductosTrasladosInternos(String producto) {
        String colName[] = {
            "Id", "origen", "destino", "fecha", "unidades", "valor", "diasPlazo", "estado", "producto", "cantidad", "lista", "imeil",
            "observacion", "total", "lote", "idProd"};
        String origen = " trasladosInternos";
        Object dtDatos[][] = getDatos(colName, origen, "select Id, origen, destino, fecha, unidades, valor, diasPlazo, estado, producto, cantidad, lista, imeil, "
                + "observacion, total, lote, idProd from trasladosInternos where Id = '" + producto + "' ",
                " where Id = '" + producto + "' ");
        return dtDatos;
    }

    /* FINAL PARQUEADERO */
    public boolean inactivarOrden(String Id) {
        boolean ok = false;
        String instruccion_sql = "update bdOrdenServicio set inactiva=? where Id='" + Id + "' ;";

        ok = Inactivar_Registro(instruccion_sql);
        return ok;
    }

    public boolean getEstadoOrden(String id) {
        String instruccion_sql = "select inactiva from bdOrdenServicio where Id = '" + id + "'; ";

        String[] colName = {"inactiva"};
        boolean[] cadena = {false};

        Object[] data = GetRegistro(colName, cadena, instruccion_sql);

        return Boolean.parseBoolean(data[0].toString());
    }

    public boolean modificarExamenesConsultaMedica(String consulta, String examen) {
        boolean ok = false;
        String instruccion_sql = "update bdConsultaClinica set examenes=? where id='" + consulta + "' ;";
        ok = Actualizar_Registro(new Object[]{"", examen}, null, instruccion_sql);
        return ok;
    }

    public boolean agregarArchivosAdjuntos(String historia, String fecha, String nombre, String decripcion, String url) {
        boolean ok = false;
        String instruccion_sql = "insert into bdArchivosAdjuntos(historia, fecha, nombre, decripcion, url)"
                + " values(?,?,?,?,?);";
        ok = Agregar_Registro(new Object[]{historia, fecha, nombre, decripcion, url}, null, instruccion_sql);
        return ok;
    }

    public boolean eliminarArchivoAdjunto(String id) {
        boolean ok = false;
        ok = eliminar_registro("bdArchivosAdjuntos", "nombre='" + id + "'");
        return ok;
    }

    public DefaultTableModel getRegistrosUsuarios() {
        String columNames[] = {
            "Usuario", "Nombre", "Contraseña"
        };
        String colName[] = {
            "usuario", "nombre", "contra"
        };
        String origen = " bdUsuario";
        Object dtDatos[][] = getDatos(colName, origen, "select usuario, nombre,contra from bdUsuario", " ");

        DefaultTableModel datos = new DefaultTableModel(dtDatos, columNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        return datos;
    }

    public ndInventarioInicial getDatosInventarioInicial(String cod) {
        String instruccion_sql = "select producto, costo, conteo1, conteo2, conteo3, total, auditoria1, auditoria2, totalAuditoria, finalizado, bodega"
                + " from bdInventarioInicial where producto = '" + cod + "' ;";
        //columnas de la tabla que se
        String[] colName = {"producto", "costo", "conteo1", "conteo2", "conteo3", "total", "auditoria1", "auditoria2", "totalAuditoria", "finalizado", "bodega"};
        boolean[] cadena = {true, true, true, true, true, true, true, true, true, true, true};
        //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        ndInventarioInicial nodo = metodos.llenarInventarioInicial(data);
        return nodo;
    }

    public boolean agregarInventarioInicial(ndInventarioInicial nodo) {
        boolean ok = false;

        String instruccion_sql = "insert into bdInventarioInicial(producto, costo, conteo1, conteo2, conteo3, total, auditoria1, auditoria2, totalAuditoria, finalizado, bodega)"
                + " values(?,?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosInventarioInicial(nodo), null, instruccion_sql);
        return ok;
    }

    public boolean modificarInventarioInicial(ndInventarioInicial nodo) {
        boolean ok = false;
        String instruccion_sql = "update bdInventarioInicial set costo=?, conteo1=?, conteo2=?, conteo3=?, total=?, auditoria1=?, auditoria2=?, totalAuditoria=?, finalizado=?"
                + " where producto='" + nodo.getProducto() + "' ;";
        ok = Actualizar_Registro(metodos.pasarDatosInventarioInicial(nodo), null, instruccion_sql);
        return ok;
    }

    public String idEgresoIngresoAsociado(String ingreso) {
        String colName[] = {"id"};
        String origen = " bdEgreso";
        Object dtDatos[][] = getDatos(colName, origen, "select id FROM bdEgreso where ingresoAsociado ='" + ingreso + "' ", " where ingresoAsociado = '" + ingreso + "' ");
        return dtDatos[0][0].toString();
    }

    public String getIngresoAsociado(String id) {
        String colName[] = {"ingresoAsociado"};
        String origen = " bdEgreso";
        Object dtDatos[][] = getDatos(colName, origen, "select ingresoAsociado FROM bdEgreso where egreso ='" + id + "' ", " where egreso = '" + id + "' ");
        return dtDatos[0][0].toString();
    }

    public String getMovimientoAsociado(String id) {
        String colName[] = {"numero"};
        String origen = " bdMovimientosBancos";
        Object dtDatos[][] = getDatos(colName, origen, "select numero FROM bdMovimientosBancos where egreso ='" + id + "' ", " where egreso = '" + id + "' ");
        return dtDatos[0][0].toString();
    }

    public boolean agregarFechasBloqueo(String fecha) {
        boolean ok = false;
        String instruccion_sql = "insert into bdLogErroresBloqueo(fecha) values (?);";
        ok = Agregar_Registro(new Object[]{fecha}, null, instruccion_sql);
        return ok;
    }

    public boolean modificarFechasBloqueo(String id, boolean habilitada) {
        boolean ok = false;
        String instruccion_sql = "update bdLogErroresBloqueo set habilitada=? where id=" + id + " ;";
        ok = Actualizar_Registro(new Object[]{"", habilitada}, null, instruccion_sql);
        return ok;
    }

    public DefaultTableModel getFechasBloqueadas() {
        String[] columNames = {"Id", "Fecha", "Estado"};

        String[] colName = {"Id", "fecha", "habilitada"};

        String origen = " bdLogErroresBloqueo";
        Object dtDatos[][] = GetTabla(colName, origen, (new StringBuilder()).append("select Id, fecha, habilitada from ").append(origen).toString(), new Integer[]{2});
        DefaultTableModel datos = new DefaultTableModel(dtDatos, columNames) {

            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        return datos;
    }

    public boolean eliminarFechaBloqueo(String id) {
        boolean ok = false;
        ok = eliminar_registro("bdLogErroresBloqueo", "Id= " + id + " ");
        return ok;
    }

    public Object[][] obtenerArchivosAdjuntos(String historia, String fecha) {
        String[] colName = {
            "historia", "fecha", "nombre", "decripcion", "url"
        };
        String origen = " bdArchivosAdjuntos ";
//        String origen = " (select historia, fecha, nombre, decripcion, url FROM bdArchivosAdjuntos where historia = '" + historia + "' and fecha = '" + fecha + "') ";
        Object[][] dtDatos = getDatos(colName, origen, "select historia, fecha, nombre, decripcion, url FROM bdArchivosAdjuntos "
                + "where historia = '" + historia + "' and fecha = '" + fecha + "' ", "where historia = '" + historia + "' and fecha = '" + fecha + "' ");
        return dtDatos;
    }

    public Object[][] getPonderadosProd(String producto) {
        String colName[] = {
            "fecha", "ponderadoAntiguo", "cantidadAntigua", "nuevoPonderado", "nuevaCantidad", "usuario", "ultimoCosto"
        };
        String origen = " bdPonderado";
        Object dtDatos[][] = getDatos(colName, origen, "select fecha, ponderadoAntiguo, cantidadAntigua, nuevoPonderado, nuevaCantidad, usuario, ultimoCosto "
                + "from bdPonderado where producto = '" + producto + "' ", " where producto = '" + producto + "' ");
        return dtDatos;
    }

    public Object[][] getTotalizadoNc(String base) {
        String colName[] = {
            "codigo", "Expr1"
        };
        String origen = " totalizadoNotasCredito";
        Object dtDatos[][] = getDatos(colName, origen, "select codigo, Expr1 from totalizadoNotasCredito ", "");
        return dtDatos;
    }

    public Object[][] getTotalizadoTrasladoBod(String sql, String base) {
        String colName[] = {
            "codigo", "Expr1"
        };
        String origen = " totalizadoTrasladoBod";
        Object dtDatos[][] = getDatos(colName, origen, "select codigo, Expr1 from totalizadoTrasladoBod " + sql, sql);
        return dtDatos;
    }

    public Object[][] getTotalizadoCongelada(String sql, String base) {
        String colName[] = {
            "codigo", "Expr1"
        };
        String origen = base;
        Object dtDatos[][] = getDatos(colName, origen, "select codigo, Expr1 from " + base + sql, sql);
        return dtDatos;
    }

    public Object[][] getTotalizadoPedidos(String sql, String base) {
        String colName[] = {
            "codigo", "Expr1"
        };
        String origen = base;
        Object dtDatos[][] = getDatos(colName, origen, "select codigo, Expr1 from " + base + sql, sql
        );
        return dtDatos;
    }

    public Object[][] getTotalizadoInvInicial() {
        String colName[] = {
            "codigo", "Expr1"
        };
        String origen = " totalizadoInvInicial";
        Object dtDatos[][] = getDatos(colName, origen, "select codigo, Expr1 from totalizadoInvInicial ", "");
        return dtDatos;
    }

    public Object[][] getTotalizadoInvInicial1(String producto) {
        String colName[] = {
            "producto", "total"
        };
        String origen = " bdInventarioInicial";
        Object dtDatos[][] = getDatos(colName, origen, "select producto, total from bdInventarioInicial where producto = '" + producto + "' ", " where producto = '" + producto + "' ");
        return dtDatos;
    }

    public Object[][] getTotalizadoCompras(String base) {
        String colName[] = {
            "codigo", "Expr1"
        };
        String origen = " totalizadoCompras";
        Object dtDatos[][] = getDatos(colName, origen, "select codigo, Expr1 from totalizadoCompras ", "");
        return dtDatos;
    }

    public Object[][] getTotalizadoAnuladas(String base) {
        String colName[] = {
            "codigo", "Expr1"
        };
        String origen = " totalizadoAnuladas";
        Object dtDatos[][] = getDatos(colName, origen, "select codigo, Expr1 from totalizadoAnuladas ", "");
        return dtDatos;
    }

    public Object[][] getTotalizadoArmados(String base) {
        String colName[] = {
            "codigo", "Expr1"
        };
        String origen = " totalizadoArmado";
        Object dtDatos[][] = getDatos(colName, origen, "select codigo, Expr1 from totalizadoArmado ", "");
        return dtDatos;
    }

    public Object[][] getTotalizadoCosteo(String base) {
        String colName[] = {
            "codigo", "Expr1"
        };
        String origen = " totalizadoCosteo";
        Object dtDatos[][] = getDatos(colName, origen, "select codigo, Expr1 from totalizadoCosteo ", "");
        return dtDatos;
    }

    public String getExistenciaProducto(String prod, String tabla) {
        String instruccion_sql = "select Expr1 from " + tabla + " where codigo = '" + prod + "' ";
        String[] colName = {"Expr1"};
        boolean[] cadena = {true};
        //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);

        try {
            return data[0].toString();
        } catch (Exception e) {
            return "0.0";
        }
    }

    public Object[][] getFechasBloqueo() {

        String colName[] = {
            "Id", "fecha"
        };
        String origen = " bdLogErroresBloqueo";
        Object dtDatos[][] = getDatos(colName, origen, "select Id, fecha from bdLogErroresBloqueo where habilitada=false", " where habilitada=false ");
        return dtDatos;

    }

    public Object[][] getRegistrosExamenesHistoria(String historia, String bd) {
        String colName[] = {
            "Id", "fecha", "usuarioNombre"
        };
        Object dtDatos[][] = getDatos(colName, bd, "select Id, fecha, usuarioNombre from " + bd + " where historia = '" + historia + "'", " where historia = '" + historia + "'");

        return dtDatos;
    }

    public Object[][] getRegistrosExamenes(String cedula, String tabla, String id) {
        String colName[] = {
            id, "numeroOrden", "cedula", "fecha"
        };
        Object dtDatos[][] = getDatos(colName, tabla, "select " + id + ", numeroOrden, cedula, fecha from " + tabla + " where cedula = '" + cedula + "'", " where cedula = '" + cedula + "'");

        return dtDatos;
    }

//    public boolean agregarAgendaConfirmada(String cliente, String cita, String encargado, String fecha) {
//        boolean ok = false;
//        String instruccion_sql = "insert into bdAgendaConfirmada(cliente, cita, encargado, fecha)"
//                + " values(?,?,?,?);";
//        ok = Agregar_Registro(new Object[]{cliente, cita, encargado, fecha}, null, instruccion_sql);
//        return ok;
//    }
    //    public boolean eliminarAgendaConfirmada(String id) {
//        boolean ok = false;
//        ok = eliminar_registro("bdAgendaConfirmada", "cita='" + id + "'");
//        return ok;
//    }
    public boolean agregarPlantillaDeTexto(Object[] datos) {
        boolean ok = false;
        String instruccion_sql = "insert into bdPlantillasDeTexto(nombre, descripcion, activo) values (?,?,?);";
        ok = Agregar_Registro(datos, null, instruccion_sql);
        return ok;
    }

    public boolean modificarPlantillaDeTexto(String id, String nombre, String descripcion, Boolean activo) {
        boolean ok = false;
        String instruccion_sql = "update bdPlantillasDeTexto set nombre=?, descripcion=?, activo=? where Id = " + id + " ;";
        ok = Actualizar_Registro(new Object[]{"", nombre, descripcion, activo}, null, instruccion_sql);
        return ok;
    }

    public DefaultTableModel getPlantillaDeTexto() {
        String columNames[] = {
            "Nombre", "Descripción", "Activo", "Codigo"
        };
        String colName[] = {
            "nombre", "descripcion", "activo", "Id"
        };
        String origen = " bdPlantillasDeTexto";
        Object dtDatos[][] = GetTabla(colName, origen, (new StringBuilder()).append("select nombre, descripcion, activo, Id from ").append(origen).toString(), new Integer[]{2});
        DefaultTableModel datos = new DefaultTableModel(dtDatos, columNames) {

            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        return datos;
    }

    public String getAgendasDelDia(String cliente, String fecha) {
        String colName[] = {"cita"};
        String origen = " agendasDelDia ";
        Object dtDatos[][] = getDatos(colName, origen, "select cita FROM agendasDelDia "
                + "where cliente ='" + cliente + "' and fecha = '" + fecha + "' ",
                " where cliente = '" + cliente + "' and fecha = '" + fecha + "' ");
        return dtDatos[0][0].toString();
    }

    public String medicoDeLaCita(String cita) {
        String colName[] = {"aCargo"};
        String origen = " agendasDelDia ";
        Object dtDatos[][] = getDatos(colName, origen, "select aCargo FROM agendasDelDia where cita ='" + cita + "' ", " where cita ='" + cita + "' ");
        return dtDatos[0][0].toString();
    }

    public boolean agregarOcupacion(String nombre) {
        //LISTO
        boolean ok = false;
        String instruccion_sql = "insert into bdListadoOcupaciones(nombre) values (?);";
        ok = Agregar_Registro(new Object[]{nombre}, null, instruccion_sql);
        return ok;
    }

    public DefaultTableModel buscarListadoOcupaciones() {
        //LISTO
        String columNames[] = {
            "Nombre"
        };
        String colName[] = {
            "nombre"
        };
        String origen = " bdListadoOcupaciones";
        Object dtDatos[][] = GetTabla(colName, origen, (new StringBuilder()).append("select nombre from ").append(origen).toString());
        DefaultTableModel datos = new DefaultTableModel(dtDatos, columNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        return datos;
    }

    public Object[][] getAbonosCxcCuotas(String abono) {

        String colName[] = {
            "id", "documentoAbono", "totalAbono", "fecha"
        };
        String origen = " bdCuota ";
        Object dtDatos[][] = getDatos(colName, origen, "select id,documentoAbono,totalAbono,fecha FROM bdCuota where documentoAbono LIKE '%" + abono + "%' ORDER BY fecha DESC", " where documentoAbono LIKE '%" + abono + "%'");

        return dtDatos;
    }

    public Object[][] getEmpleadosCargo(String cargo) {

        String colName[] = {
            "Id", "nombre"
        };
        String origen = " bdTerceros";
        Object dtDatos[][] = getDatos(colName, origen, "select Id, nombre from bdTerceros where cargo='" + cargo + "'", " where cargo='" + cargo + "'");
        return dtDatos;

    }

    public Object[][] getMunicipios(String departamento) {
        String[] colName = {"nombreMunicipio"};
        String origen = " bdRegiones";
        Object[][] dtDatos = getDatos(colName, origen, "select nombreMunicipio from bdRegiones where nombreDepartamento ='" + departamento + "' "
                + "GROUP BY nombreMunicipio ORDER BY nombreMunicipio ", " where nombreDepartamento ='" + departamento + "' ");

        return dtDatos;
    }

    public Object[][] getDepartamentos() {
        String colName[] = {"nombreDepartamento"};
        String origen = " bdRegiones ";
        Object dtDatos[][] = getDatos(colName, origen, "select nombreDepartamento from bdRegiones group by nombreDepartamento ", "");
        return dtDatos;
    }

    public Object[][] getCodigoLugar(String departamento, String ciudad) {
        String[] colName = {"codigoDepartamento", "codigoMunicipio"};
        String origen = " bdRegiones ";
        Object[][] dtDatos = getDatos(colName, origen, "select codigoDepartamento, codigoMunicipio from bdRegiones where nombreDepartamento = '" + departamento + "' "
                + "&& nombreMunicipio = '" + ciudad + "' ", " where nombreDepartamento ='" + departamento + "' && nombreMunicipio = '" + ciudad + "' ");

        return dtDatos;
    }

    public Object[][] getCodigosPostales(String departamento, String ciudad) {
        String[] colName = {"codigoPostal"};
        String origen = " bdRegiones ";
        Object[][] dtDatos = getDatos(colName, origen, "select codigoPostal from bdRegiones where nombreDepartamento = '" + departamento + "' "
                + "&& nombreMunicipio = '" + ciudad + "' ", " where nombreDepartamento ='" + departamento + "' && nombreMunicipio = '" + ciudad + "' ");

        return dtDatos;
    }

    // MEDICO // MEDICO // MEDICO // MEDICO // MEDICO // MEDICO // MEDICO // MEDICO //
    public Object[][] getRegistrosListadoOrden(String medico) {
        String colName[] = {
            "id", "consecutivo", "fecha", "hora", "paciente", "nombre", "estado"
        };

        String origen = "listadoOrdenesDia";
        Object dtDatos[][] = getDatos(colName, origen, "select id, consecutivo, fecha,hora, paciente, nombre, "
                + "estado  from " + origen + " WHERE medico = '" + medico + "' AND medico <> '' ORDER BY hora",
                " WHERE medico = '" + medico + "' AND medico <> ''");

        return dtDatos;
    }

    public Object[][] getRegistrosRemision(String medico) {
        String colName[] = {
            "Id", "fecha", "tipo", "especialidad"
        };
        String origen = " bdRemision ";
        Object dtDatos[][] = getDatos(colName, origen, "select Id, fecha, tipo, especialidad from " + origen + " WHERE identificacion = '" + medico + "' ", " WHERE identificacion = '" + medico + "' ");

        return dtDatos;
    }

    public Object[][] getRegistrosContraremision(String medico) {
        String colName[] = {
            "Id", "fecha", "hallazgos"
        };
        String origen = " bdContraremision ";
        Object dtDatos[][] = getDatos(colName, origen, "select Id, fecha, hallazgos from " + origen + " WHERE identificacion = '" + medico + "' ", " WHERE identificacion = '" + medico + "' ");

        return dtDatos;
    }

    public Object[][] getRegistrosCertificados(String paciente, String tabla) {
        String colName[] = {
            "Id", "nombre"
        };
        String origen = tabla;
        Object dtDatos[][] = getDatos(colName, origen, "select Id, nombre from " + origen + " where idSistema = '" + paciente + "'",
                " where idSistema = '" + paciente + "'");

        return dtDatos;
    }

    public Object[][] getRegistrosOrden(String paciente) {
        String colName[] = {
            "id", "consecutivo", "fecha"
        };
        String origen = "bdOrdenServicio";
        Object dtDatos[][] = getDatos(colName, origen, "select id, consecutivo, fecha from " + origen + " where paciente = '" + paciente + "'", " where paciente = '" + paciente + "'");

        return dtDatos;
    }

    public boolean agregarHistClinica(ndHistoriaClinica nodo) {
        //LISTO BD
        boolean ok = false;
        String instruccion_sql = "insert into bdHistoriaClinica(Id, paciente, fecha, historia, menstruacion, citologia, resultadoCitologia, embarazos, partos, abortos, cesareas,consecutivo,hijosVivos,personal,familiar) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosHistClinica(nodo), null, instruccion_sql);
        return ok;
    }

    public boolean modificarHistClinica(ndHistoriaClinica nodo) {
        //LISTO BD
        boolean ok = false;
        String instruccion_sql = "update bdHistoriaClinica set paciente=?, fecha=?, historia=?, menstruacion=?, citologia=?, resultadoCitologia=?, embarazos=?, partos=?, abortos=?, cesareas=?,consecutivo=?,hijosVivos=?,personal=?,familiar=? where Id='" + nodo.getId() + "' ;";
        ok = Actualizar_Registro(metodos.pasarDatosHistClinica(nodo), null, instruccion_sql);
        return ok;
    }

    public ndHistoriaClinica getDatosHistClinica(String id) {
        //LISTO BD
        boolean ok = false;
        String instruccion_sql = " select id, paciente, fecha, historia, menstruacion, citologia, resultadoCitologia, embarazos, partos, abortos, cesareas,consecutivo,"
                + "hijosVivos,personal,familiar from bdHistoriaClinica where Id = '" + id + "'; ";
        String[] colName = {"id", "paciente", "fecha", "historia", "menstruacion", "citologia", "resultadoCitologia", "embarazos", "partos", "abortos", "cesareas",
            "consecutivo", "hijosVivos", "personal", "familiar"};
        boolean[] cadena = {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true};
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        ndHistoriaClinica nodo = metodos.llenarHistClinica(data);
        return nodo;
    }

    public ndHistoriaClinica getDatosHistoriaClinica(String historia) {
        //LISTO BD
        boolean ok = false;
        String instruccion_sql = " select id, paciente, fecha, historia, menstruacion, citologia, resultadoCitologia, embarazos, partos, abortos, cesareas,"
                + "consecutivo,hijosVivos,personal,familiar from bdHistoriaClinica where paciente = '" + historia + "'; ";
        String[] colName = {"id", "paciente", "fecha", "historia", "menstruacion", "citologia", "resultadoCitologia", "embarazos", "partos", "abortos", "cesareas", "consecutivo",
            "hijosVivos", "personal", "familiar"};
        boolean[] cadena = {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true};
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        ndHistoriaClinica nodo = metodos.llenarHistClinica(data);
        return nodo;
    }

    public boolean modificarConsecutivoHistoria(String historial, String consecutivo) {
        //LISTO
        boolean ok = false;
        String instruccion_sql = "update bdHistoriaClinica set consecutivo=? where Id='" + historial + "' ;";
        ok = Actualizar_Registro(new Object[]{historial, consecutivo}, null, instruccion_sql);
        return ok;
    }

    /*public Object[] getUltimoPonderado(String producto) {
        boolean ok = false;
        String instruccion_sql = "select producto, ponderadoAntiguo, cantidadAntigua, cantidadEntrante, nuevoPonderado, nuevaCantidad, usuario, ultimoCosto, fecha"
                + " from ultimoPonderado where producto = '" + producto + "' ";
        String[] colName = {"producto", "ponderadoAntiguo", "cantidadAntigua", "cantidadEntrante", "nuevoPonderado", "nuevaCantidad", "usuario", "ultimoCosto", "fecha"};
        boolean[] cadena = {true, true, true, true, true, true, true, true, true, true, true};
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        return data;
    }*/

    public String obtenerUltimoCostoProducto(String codigoProducto) {
        String instruccion_sql = "select ultimoCosto from ultimoPonderado where producto = '" + codigoProducto + "'; ";
        String[] colName = {"ultimoCosto"};
        boolean[] cadena = {true};

        try {
            return GetRegistro(colName, cadena, instruccion_sql)[0].toString();
        } catch (Exception e) {
            return null;
        }
    }

    public String getConsecutivoPonderado(String cod) {
        String instruccion_sql = "select Id from ultimoPonderado1 where producto = '" + cod + "'; ";
        String[] colName = {"Id"};
        boolean[] cadena = {true};

        try {
            return GetRegistro(colName, cadena, instruccion_sql)[0].toString();
        } catch (Exception e) {
            return null;
        }
    }

    public Object[] getUltimoPonderado1(String producto) {
        boolean ok = false;
        String instruccion_sql = "select producto, ponderadoAntiguo, cantidadAntigua, cantidadEntrante, nuevoPonderado, nuevaCantidad, usuario, ultimoCosto, fecha, ingreso "
                + "from bdPonderado where Id = " + producto + " ";
        String[] colName = {"producto", "ponderadoAntiguo", "cantidadAntigua", "cantidadEntrante", "nuevoPonderado", "nuevaCantidad", "usuario", "ultimoCosto", "fecha", "ingreso"};
        boolean[] cadena = {true, true, true, true, true, true, true, true, true, true};
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        return data;
    }

    public Object[][] getUltimosPonderados() {
        String colName[] = {
            "producto", "nuevoPonderado", "ultimoCosto", "Descripcion", "Grupo"
        };
        String origen = " ultimoPonderado";
        Object dtDatos[][] = getDatos(colName, origen, "select producto, nuevoPonderado, ultimoCosto, Descripcion, Grupo FROM ultimoPonderado ", "");
        return dtDatos;
    }

    public Object[] getDatosConvenioPendiente(String nit, String condicion) {
        //LISTO
        boolean ok = false;
        String instruccion_sql = " select Id, nit,montoTotal,total,convenio,estado from conConvenioPendiente where convenio = '" + nit + "' " + condicion;
        String[] colName = {"Id", "nit", "montoTotal", "total", "convenio", "estado"};
        boolean[] cadena = {true, true, true, true, true, true};
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        return data;
    }

    public DefaultTableModel getRegistrosConvenio() {
        //LISTO
        String columNames[] = {
            "Nit", "Convenio"
        };
        String colName[] = {
            "nit", "convenio"
        };
        String origen = " conConvenioPendiente";
        Object dtDatos[][] = GetTabla(colName, origen, (new StringBuilder()).append("select nit, convenio from ").append(origen).toString());
        DefaultTableModel datos = new DefaultTableModel(dtDatos, columNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        return datos;
    }

    public DefaultTableModel getRegistrosConvenios(String nit) {
        //LISTO
        String columNames[] = {
            "Codigo", "Fecha Finalización", "Monto Total", "Total Registrado", "Estado"
        };
        String colName[] = {
            "convenio", "fechaFinalizacion", "montoTotal", "total", "estado"
        };
        String origen = " conConvenioPendiente where nit = '" + nit + "'";
        Object dtDatos[][] = GetTabla(colName, origen, (new StringBuilder()).append("select convenio, fechaFinalizacion,montoTotal,total,estado from ").append(origen).toString());
        for (int i = 0; i < dtDatos.length; i++) {
            dtDatos[i][1] = metodos.fecha(dtDatos[i][1].toString());
            dtDatos[i][2] = big.setMoneda(big.getBigDecimal(dtDatos[i][2]));
            dtDatos[i][3] = big.setMoneda(big.getBigDecimal(dtDatos[i][3]));
        }
        DefaultTableModel datos = new DefaultTableModel(dtDatos, columNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        return datos;
    }

    public ndInformacionPrestador getDatosInformacionPrestador() {
        boolean ok = false;
        String instruccion_sql = " select Id, nombre, tipo, numeroId, codigoHabilitador, direccion, indicativo,numero, departamento, municipio, entidad, "
                + "codigoEntidad from bdInformacionPrestador where Id = 1; ";
        String[] colName = {"Id", "nombre", "tipo", "numeroId", "codigoHabilitador", "direccion", "indicativo", "numero", "departamento", "municipio", "entidad", "codigoEntidad"};
        boolean[] cadena = {true, true, true, true, true, true, true, true, true, true, true, true};
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        ndInformacionPrestador nodo = metodos.llenarInformacionPrestador(data);
        return nodo;
    }

    public boolean modificarInformacionPrestador(ndInformacionPrestador nodo) {
        //LISTO
        boolean ok = false;
        String instruccion_sql = "update bdInformacionPrestador set nombre=?,tipo=?,numeroId=?,codigoHabilitador=?,direccion=?,indicativo=?,numero=?,departamento=?,municipio=?,entidad=?,codigoEntidad=? where Id=1 ;";
        ok = Actualizar_Registro(metodos.pasarDatosInformacionPrestador(nodo), null, instruccion_sql);
        return ok;
    }

    public boolean agregarAutorizacionServicios(Object[] datos) {
        //LISTO
        boolean ok = false;
        String instruccion_sql = "insert into bdAutorizacionServicios(Id, paciente, origen1, origen2, tipoServicio, prioridad, ubicacion, "
                + "servicio, cama, manejo, cup1, cup2, cup3, cup4, cup5, cup6, cup7, cup8, cup9, cup10, cup11, cup12, cup13, cup14, cup15,"
                + " cup16, cup17, cup18, cup19, cup20, justificacion, cie1, cie2,cie3,nombreSolicita,indicativo,numero,extension,cargo, celular,fecha,"
                + "hora,usuario) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(datos, null, instruccion_sql);
        return ok;
    }

    public boolean anularAutorizacion(String Id) {
        //LISTO
        boolean ok = false;
        String instruccion_sql = "update bdAutorizacionServicios set activo=?"
                + " where Id='" + Id + "' ;";
        ok = Activar_Registro(instruccion_sql);
        return ok;
    }

    public boolean agregarConvenio(ndConvenio nodo) {
        //LISTO
        boolean ok = false;
        String instruccion_sql = "insert into bdConvenio(nit,convenio,contrato,fechaFinalizacion,estado,tipoFactura,porcentaje,montoTotal) values (?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosConvenio(nodo), metodos.pasarValoresConvenio(nodo), instruccion_sql);
        return ok;
    }

    public boolean modificarConvenio(ndConvenio nodo) {
        //LISTO
        boolean ok = false;
        String instruccion_sql = "update bdConvenio set convenio=?, contrato=?, fechaFinalizacion=?, estado=?,tipoFactura=?,porcentaje=?, montoTotal=? where nit='" + nodo.getNit() + "' AND convenio = '" + nodo.getConvenio() + "';";
        ok = Actualizar_Registro(metodos.pasarDatosConvenio(nodo), metodos.pasarValoresConvenio(nodo), instruccion_sql);
        return ok;
    }

    public ndConvenio getDatosConvenio(String valor, String campo) {
        //LISTO
        boolean ok = false;
        String instruccion_sql = "select nit, convenio, contrato, fechaFinalizacion,montoTotal, estado, tipoFactura,porcentaje from bdConvenio where " + campo + " = '" + valor + "'; ";
        String[] colName = {"nit", "convenio", "contrato", "fechaFinalizacion", "montoTotal", "estado", "tipoFactura", "porcentaje"};
        boolean[] cadena = {true, true, true, true, true, true, true, true};
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        ndConvenio nodo = metodos.llenarConvenio(data);
        return nodo;
    }

    //NOTA ENFERMERIA...
//    public Object[][] getNotasEnfermeria(String id) {
//        //LISTO
//        String colName[] = {
//            "Id", "fecha"
//        };
//        String origen = " bdNotaEnfermeria";
//        Object dtDatos[][] = getDatos(colName, origen, "select Id, fecha FROM bdNotaEnfermeria where paciente = '"
//                + id + "'", " where paciente = '" + id + "'");
//
//        return dtDatos;
//    }
    public ndNotaEnfermeria getDatosNotasEnfermeria(String Id) {
        //LISTO
        boolean ok = false;
        String instruccion_sql = " select Id, paciente,fecha,informacion from bdNotaEnfermeria where Id = '" + Id + "'; ";
        String[] colName = {"Id", "paciente", "fecha", "informacion"};
        boolean[] cadena = {true, true, true, true};
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        ndNotaEnfermeria nodo = metodos.llenarNotaEnfermeria(data);
        return nodo;
    }

    public boolean agregarNotasEnfermeria(ndNotaEnfermeria nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdNotaEnfermeria(Id, paciente, fecha, informacion) values (?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosNotaEnfermeria(nodo), null, instruccion_sql);
        return ok;
    }

    public boolean modificarNotasEnfermeria(ndNotaEnfermeria nodo) {
        boolean ok = false;
        String instruccion_sql = "update bdNotaEnfermeria set paciente=?, fecha=?, informacion=? where Id='" + nodo.getId() + "' ;";
        ok = Actualizar_Registro(metodos.pasarDatosNotaEnfermeria(nodo), null, instruccion_sql);
        return ok;
    }

    public boolean agregarCamposOrdenPredeterminada(ndCamposOrdenPredeterminada nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdCamposOrdenPredeterminada(id, tipo, concepto, finalidad, causaExterna, servicio, personal, actoQuirurgico, forma, ambito, consulta) values (?,?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosCamposOrdenPredeterminada(nodo), null, instruccion_sql);
        return ok;
    }

    public boolean modificarCamposOrdenPredeterminada(ndCamposOrdenPredeterminada nodo) {
        boolean ok = false;
        String instruccion_sql = "update bdCamposOrdenPredeterminada set tipo=?, concepto=?, finalidad=?, causaExterna=?, servicio=?, personal=?, actoQuirurgico=?, forma=?, ambito=?, consulta=? where id=1 ;";
        ok = Actualizar_Registro(metodos.pasarDatosCamposOrdenPredeterminada(nodo), null, instruccion_sql);
        return ok;
    }

    public ndCamposOrdenPredeterminada getDatosCamposOrdenPredeterminada(String Id) {
        //LISTO
        boolean ok = false;
        String instruccion_sql = " select id, tipo, concepto, finalidad, causaExterna, servicio, personal, actoQuirurgico, forma, ambito, consulta from bdCamposOrdenPredeterminada where id = 1; ";
        String[] colName = {"id", "tipo", "concepto", "finalidad", "causaExterna", "servicio", "personal", "actoQuirurgico", "forma", "ambito", "consulta"};
        boolean[] cadena = {true, true, true, true, true, true, true, true, true, true, true,};
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        ndCamposOrdenPredeterminada nodo = metodos.llenarCamposOrdenPredeterminada(data);
        return nodo;
    }

    public String getNombreEmpleadoUsuario(String usuario) {
        String sql = "select nombre, usuarioAsociado FROM bdTerceros where usuarioAsociado = '" + usuario + "'";
        String colName[] = {"nombre", "usuarioAsociado"};
        boolean[] cadena = {true, true};
        Object[] data = GetRegistro(colName, cadena, sql);
        try {
            if (data[0] == null) {
                return "";
            }
            if (data[0].toString().equals("null")) {
                return "";
            }
        } catch (Exception e) {
            return "";
        }
        return data[0].toString();
    }

    public Object[][] getConsultaPorHistorial(String historia) {
        String colName[] = {
            "Id", "fecha", "cie1"
        };
        String origen = " bdConsultaClinica";
        Object dtDatos[][] = getDatos(colName, origen, "select Id,fecha,cie1 FROM bdConsultaClinica where historia = '" + historia + "' ORDER BY Id DESC",
                " where historia = '" + historia + "'");

        return dtDatos;
    }

    public Object[][] getEmpleadoUsuario(String usuario) {
        //LISTO
        String colName[] = {
            "Id", "nombre"
        };
        String origen = " bdTerceros";
        Object dtDatos[][] = getDatos(colName, origen, "select Id, nombre FROM bdTerceros where usuarioAsociado = '" + usuario + "'", " where usuarioAsociado = '" + usuario + "'");

        return dtDatos;
    }

    public boolean agregarIncapacidad(ndIncapacidad nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdIncapacidad(id, consulta, fechainicio, fechavencimiento, cup, descripcion,cie, "
                + "fechaRegistro, usuarioRegistro, dias) values (?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosIncapacidad(nodo), metodos.pasarValoresIncapacidad(nodo), instruccion_sql);
        return ok;
    }

    public boolean agregarIncapacidadOf(ndIncapacidadOf nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdIncapacidadOf (id, consulta, fechainicio, fechavencimiento, cup, descripcion, cie, historial, dias) values (?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosIncapacidadOf(nodo), metodos.pasarValoresIncapacidadOf(nodo), instruccion_sql);
        return ok;
    }

    public boolean agregarRemision(ndRemision nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdRemision(Id, fecha, tipo, especialidad, nombres, apellidos, edad, tipoId, identificacion, telefono, resumen, tratamientos, "
                + "diagnosticos, remision, diag1, diag2, nombre1, nombre2, medico, cedulaMedico, registro) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosRemision(nodo), null, instruccion_sql);
        return ok;
    }
//
//    public boolean modificarIncapacidad(ndIncapacidad nodo) {
//        //LISTO
//        boolean ok = false;
//        String instruccion_sql = "update bdIncapacidad set consulta=?, fechainicio=?, fechavencimiento=?, cup=?, descripcion=?, dias=? where id='" + nodo.getId() + "' ;";
//        ok = Actualizar_Registro(metodos.pasarDatosIncapacidad(nodo), metodos.pasarValoresIncapacidad(nodo), instruccion_sql);
//        return ok;
//    }

    public ndIncapacidad getDatosIncapacidad(String Id) {
        //LISTO
        boolean ok = false;
        String instruccion_sql = " select id, consulta, fechainicio, fechavencimiento, cup, descripcion, dias,cie, fechaRegistro, usuarioRegistro"
                + " from bdIncapacidad where id = '" + Id + "'; ";
        String[] colName = {"id", "consulta", "fechainicio", "fechavencimiento", "cup", "descripcion", "dias", "cie", "fechaRegistro", "usuarioRegistro"};
        boolean[] cadena = {true, true, true, true, true, true, true, true, true, true};
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        ndIncapacidad nodo = metodos.llenarIncapacidad(data);
        return nodo;
    }

    public ndIncapacidadOf getDatosIncapacidadOf(String Id) {
        boolean ok = false;
        String instruccion_sql = " select id, consulta, fechainicio, fechavencimiento, cup, descripcion, dias,cie, historial from bdIncapacidadOf where id = '" + Id + "'; ";
        String[] colName = {"id", "consulta", "fechainicio", "fechavencimiento", "cup", "descripcion", "dias", "cie", "historial"};
        boolean[] cadena = {true, true, true, true, true, true, true, true};
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        ndIncapacidadOf nodo = metodos.llenarIncapacidadOf(data);
        return nodo;
    }

    public ndCertificadoMedico getDatosCertificadoMedico(String Id, String tipo) {
        boolean ok = false;
        String instruccion_sql = " select id, historia, responsable, nombre, parentesco, motivo, enfermedad, revision, inspeccion, temp, peso, estatura, fc, fr, pa, cefalico, abdominal, imc, cabeza, ojos, agudezaVisual, oidos, nariz, boca, cuello, cardioPulmonar, torax, abdomen, genitoUrinario, ano, extremidades, neurologico, pies, osteoMuscular, diagPrincipal, diagRelacionado, diagRelacionado2, diagRelacionado3, tratamiento, recomendaciones, cup, usuario, fecha, patologicos,quirurgicos,alergicos,traumaticos,ginecoobstetricos,familiares,social, muscular from bdCertificadoMedico" + tipo + " where Id = '" + Id + "'; ";
        String[] colName = {"id", "historia", "responsable", "nombre", "parentesco", "motivo", "enfermedad", "revision", "inspeccion", "temp", "peso", "estatura", "fc", "fr", "pa", "cefalico", "abdominal", "imc", "cabeza", "ojos", "agudezaVisual", "oidos", "nariz", "boca", "cuello", "cardioPulmonar", "torax", "abdomen", "genitoUrinario", "ano", "extremidades", "neurologico", "pies", "osteoMuscular", "diagPrincipal", "diagRelacionado", "diagRelacionado2", "diagRelacionado3", "tratamiento", "recomendaciones", "cup", "usuario", "fecha", "patologicos", "quirurgicos", "alergicos", "traumaticos", "ginecoobstetricos", "familiares", "social", "muscular"};
        boolean[] cadena = {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true};
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        ndCertificadoMedico nodo = metodos.llenarCertificadoMedico(data);
        return nodo;
    }

    public ndRemision getDatosRemision(String Id) {
        //LISTO
        boolean ok = false;
        String instruccion_sql = " select Id, fecha, tipo, especialidad, nombres, apellidos, edad, tipoId, identificacion, telefono, resumen, tratamientos, "
                + "diagnosticos, remision, diag1, diag2, nombre1, nombre2, medico, cedulaMedico, registro"
                + " from bdRemision where Id = '" + Id + "'; ";
        String[] colName = {"Id", "fecha", "tipo", "especialidad", "nombres", "apellidos", "edad", "tipoId", "identificacion", "telefono", "resumen", "tratamientos",
            "diagnosticos", "remision", "diag1", "diag2", "nombre1", "nombre2", "medico", "cedulaMedico", "registro"};
        boolean[] cadena = {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true};
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        ndRemision nodo = metodos.llenarRemision(data);
        return nodo;
    }

    public boolean agregarConsultaClinica(ndConsultaClinica nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdConsultaClinica (id, historia, responsable, nombre, parentesco, motivo, t, peso, talla, fc, "
                + "fr, pa, cefalico, abdominal, fisico1, fisico2, fisico3, problemas, cie1, cie2, "
                + "cie3, cie4, tratamiento, recomendaciones, cup, usuario, fecha, revision, cabezaCuello, cardioPulmonar, "
                + "abdomen, pelvis, extremidades, orden, organosSentidos, musculoEsqueletico, neurologico, pielAnexos, familiar, fumador, "
                + "ingesta, alergias, toxicos, medicamentos, patologicos, psiquiatricos, quirurgicos, ultimaMenstruacion, ultimaCitologia, resultadosCitologia, "
                + "embarazos, abortos, partos, cesarias, hijosVivos, planificacion, fotos, examenes, telefonoResponsable, ectopicos, "
                + "cup2, observacionProcedimientos, oido, vista, nariz, gusto, tacto, menarca, diagnosticoEspecializado, nutricionales, "
                + "tipoDiagnostico, cc, torax, abdomen1, extremidades1, neurologico1, pielYfaneras, revisionSistemas, historialPersonal, traumaticos, gluteos, mamas) "
                + " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosConsultaClinica(nodo), null, instruccion_sql);
        return ok;
    }

    public boolean agregarConsultaLaboratorio(ndConsultaLaboratorio nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdConsultaLaboratorio (id, historia, responsable, nombre, parentesco, motivo, problemas, cie1, cie2, "
                + "cie3, cie4, tratamiento, recomendaciones, usuario, fecha, orden, telefonoResponsable, diagnosticoEspecializado, tipoDiagnostico) "
                + " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosConsultaLaboratorio(nodo), null, instruccion_sql);
        return ok;
    }

    public ndConsultaClinica getDatosConsultaClinica(String Id) {
        boolean ok = false;
        String instruccion_sql = "select id, historia, responsable, nombre, parentesco, motivo, t, peso, talla, fc, fr, pa, cefalico, abdominal, fisico1, fisico2, "
                + "fisico3, problemas, cie1, cie2, cie3, cie4, tratamiento, recomendaciones, cup, usuario, fecha, revision, "
                + "cabezaCuello,cardioPulmonar,abdomen,pelvis,extremidades,orden,organosSentidos,musculoEsqueletico,neurologico,pielAnexos, "
                + "familiar, fumador, ingesta, alergias, toxicos, medicamentos, patologicos, "
                + "psiquiatricos, quirurgicos, ultimaMenstruacion, ultimaCitologia, resultadosCitologia, embarazos, abortos, partos, "
                + "cesarias, hijosVivos, planificacion, fotos, examenes, telefonoResponsable, ectopicos,cup2, observacionProcedimientos, oido, vista, "
                + "nariz, gusto, tacto, menarca, diagnosticoEspecializado, nutricionales, tipoDiagnostico, cc, torax, abdomen1, extremidades1, "
                + "neurologico1, pielYfaneras, revisionSistemas, historialPersonal, traumaticos, gluteos, mamas "
                + " from bdConsultaClinica where id = '" + Id + "'; ";

        String[] colName = {"id", "historia", "responsable", "nombre", "parentesco", "motivo", "t", "peso", "talla", "fc", "fr", "pa", "cefalico", "abdominal", "fisico1", "fisico2",
            "fisico3", "problemas", "cie1", "cie2", "cie3", "cie4", "tratamiento", "recomendaciones", "cup", "usuario", "fecha", "revision",
            "cabezaCuello", "cardioPulmonar", "abdomen", "pelvis", "extremidades", "orden", "organosSentidos", "musculoEsqueletico", "neurologico", "pielAnexos",
            "familiar", "fumador", "ingesta", "alergias", "toxicos", "medicamentos", "patologicos",
            "psiquiatricos", "quirurgicos", "ultimaMenstruacion", "ultimaCitologia", "resultadosCitologia", "embarazos", "abortos", "partos",
            "cesarias", "hijosVivos", "planificacion", "fotos", "examenes", "telefonoResponsable", "ectopicos", "cup2", "observacionProcedimientos", "oido", "vista",
            "nariz", "gusto", "tacto", "menarca", "diagnosticoEspecializado", "nutricionales", "tipoDiagnostico", "cc", "torax", "abdomen1",
            "extremidades1", "neurologico1", "pielYfaneras", "revisionSistemas", "historialPersonal", "traumaticos", "gluteos", "mamas"};
        boolean[] cadena = {
            true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,
            true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,
            true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,
            true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true
        };
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        ndConsultaClinica nodo = metodos.llenarConsultaClinica(data);
        return nodo;

    }

    public ndConsultaLaboratorio getDatosConsultaLaboratorio(String Id) {
        boolean ok = false;
        String instruccion_sql = "select id, historia, responsable, nombre, parentesco, motivo, problemas, cie1, cie2, cie3, cie4, tratamiento, recomendaciones, usuario, fecha, "
                + "orden, telefonoResponsable, diagnosticoEspecializado, tipoDiagnostico from bdConsultaLaboratorio where Id = '" + Id + "'; ";
        String[] colName = {"id", "historia", "responsable", "nombre", "parentesco", "motivo", "problemas", "cie1", "cie2", "cie3", "cie4", "tratamiento", "recomendaciones",
            "usuario", "fecha", "orden", "telefonoResponsable", "diagnosticoEspecializado", "tipoDiagnostico"};
        boolean[] cadena = {
            true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true};
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        ndConsultaLaboratorio nodo = metodos.llenarConsultaLaboratorio(data);
        return nodo;
    }

    public boolean agregarMedicamento(ndMedicamentos nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdMedicamentos(nombre, recomendacion, usuario)"
                + " values(?,?,?);";

        ok = Agregar_Registro(metodos.pasarDatosMedicamentos(nodo), null, instruccion_sql);
        return ok;
    }

    public boolean modificarMedicamento(ndMedicamentos nodo) {
        boolean ok = false;
        String instruccion_sql = "update bdMedicamentos set nombre=?, recomendacion=? where Id=" + nodo.getId() + " ;";
        ok = Actualizar_Registro(metodos.pasarDatosMedicamentos2(nodo), null, instruccion_sql);
        return ok;
    }

    public ndMedicamentos getDatosMedicamentos(String Id) {
        boolean ok = false;
        String instruccion_sql = "select Id, nombre, recomendacion, usuario from bdMedicamentos where Id = '" + Id + "' ; ";
        String[] colName = {"Id", "nombre", "recomendacion", "usuario"};
        boolean[] cadena = {true, true, true, true};
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        ndMedicamentos nodo = metodos.llenarMedicamentos(data);
        return nodo;
    }

    public String getDatosMedicamentosNombre(String cod) {
        String instruccion_sql = "select Id,nombre"
                + " from bdMedicamentos where nombre = '" + cod + "'; ";
        //columnas de la tabla que se
        String[] colName = {"Id", "nombre"};
        boolean[] cadena = {true, true};
        try {
            return GetRegistro(colName, cadena, instruccion_sql)[0].toString();
        } catch (Exception e) {
            return null;
        }
    }

    public DefaultTableModel getRegistrosMedicamentos() {
        String columNames[] = {
            "Id", "Nombre"
        };
        String colName[] = {
            "Id", "nombre"
        };
        String origen = " bdMedicamentos";
        Object dtDatos[][] = GetTabla(colName, origen, (new StringBuilder()).append("select id, nombre from ").append(origen).toString());
        DefaultTableModel datos = new DefaultTableModel(dtDatos, columNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        return datos;
    }

    public Object[][] getMsgPermisos(String condicion) {
        String colName[] = {
            "IdPermiso", "mensaje"
        };
        String origen = " bdMsgPermisos";
        Object dtDatos[][] = getDatos(colName, origen, "select IdPermiso, mensaje FROM bdMsgPermisos " + condicion, condicion);

        return dtDatos;
    }

    public Object[][] getPermisosPendientes(String condicion) {
        String colName[] = {
            "Id", "tipoSolicitud", "valorSolicitud", "nota", "fecha", "hora", "usuario", "estado"
        };
        String origen = " bdPermisos";
        Object dtDatos[][] = getDatos(colName, origen, "select Id, tipoSolicitud, valorSolicitud, nota, fecha, hora, usuario, estado "
                + "FROM bdPermisos " + condicion, condicion);

        return dtDatos;
    }

    public Object[][] getMedicamentosPorAplicarVeterinario(String condicion) {
        String colName[] = {
            "idHospitalizacion", "codigo", "medicamentos", "posologia", "hora", "estado", "mascota", "tercero"
        };
        String origen = " bdMedicamentosProcesosAlertas";
        Object dtDatos[][] = getDatos(colName, origen, "select idHospitalizacion, codigo, medicamentos, posologia, hora, estado, "
                + "mascota, tercero FROM bdMedicamentosProcesosAlertas " + condicion, condicion);
        return dtDatos;
    }

    public Object[][] getMedicamentosPorAplicarVeterinario1(String condicion) {
        String colName[] = {
            "idHospitalizacion", "codigo", "medicamentos", "posologia", "una", "dos", "tres", "cuatro", "cinco", "seis", "siete", "ocho", "nueve", "diez",
            "once", "doce", "trece", "catorce", "quince", "diesiseis", "diesisiete", "diesiocho", "diesinueve", "veinte", "veintiuno", "veintidos",
            "veintitres", "veinticuatro", "estadoUna", "estadoDos", "estadoTres", "estadoCuatro", "estadoCinco", "estadoSeis", "estadoSiete", "estadoOcho",
            "estadoNueve", "estadoDiez", "estadoOnce", "estadoDoce", "estadoTrece", "estadoCatorce", "estadoQuince", "estadoDiesiseis", "estadoDiesisiete",
            "estadoDiesiocho", "estadoDiesinueve", "estadoVeinte", "estadoVeintiuno", "estadoVeintidos", "estadoVeintitres", "estadoVeinticuatro"
        };
        String origen = " bdMedicamentosProcesosAlertas";
        Object dtDatos[][] = getDatos(colName, origen, "select idHospitalizacion, codigo, medicamentos, posologia, una, dos, tres, cuatro, cinco, seis, "
                + "siete, ocho, nueve, diez, once, doce, trece, catorce, quince, diesiseis, diesisiete, diesiocho, diesinueve, veinte, veintiuno, "
                + "veintidos, veintitres, veinticuatro, estadoUna, estadoDos, estadoTres, estadoCuatro, estadoCinco, estadoSeis, estadoSiete, estadoOcho,"
                + "estadoNueve, estadoDiez, estadoOnce, estadoDoce, estadoTrece, estadoCatorce, estadoQuince, estadoDiesiseis, estadoDiesisiete, "
                + "estadoDiesiocho, estadoDiesinueve, estadoVeinte, estadoVeintiuno, estadoVeintidos, estadoVeintitres, estadoVeinticuatro "
                + "FROM bdMedicamentosProcesosAlertas " + condicion, condicion);

        return dtDatos;
    }

    public Object[][] getDatosCodsEgreso(String codigo) {
        //LISTO
        String colName[] = {
            "codigo", "codigoUsuario", "concepto"
        };
        String origen = " codsEgresos";
        Object dtDatos[][] = getDatos(colName, origen, "select  codigo, codigoUsuario, concepto FROM codsEgresos where codigoUsuario = '" + codigo + "'", " where codigoUsuario = '" + codigo + "'");

        return dtDatos;
    }

    public Object[] getInfoCodEgreso(String concepto) {
        String instruccion_sql = " select codigo,concepto,codigoUsuario from codsEgresos where concepto = '" + concepto + "'; ";
        String[] colName = {"codigo", "concepto", "codigoUsuario"};
        boolean[] cadena = {true, true, true};
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        return data;
    }

//    public DefaultTableModel getRegistrosCodigosEgresos1() {
//        //LISTO
//        String columNames[] = {
//            "codigo", "codigoUsuario", "concepto"
//        };
//        String colName[] = {
//            "codigo", "codigoUsuario", "concepto"};
//        String origen = " codsEgresos";
//        Object dtDatos[][] = GetTabla(colName, origen, (new StringBuilder()).append("select codigo, codigoUsuario, concepto from ").append(origen).toString());
//        DefaultTableModel datos = new DefaultTableModel(dtDatos, columNames) {
//            public boolean isCellEditable(int row, int column) {
//                return false;
//            }
//        };
//        return datos;
//    }
    public DefaultTableModel getRegistrosCodigosEgresos() {
        String columNames[] = {
            "codigo", "codigoUsuario", "concepto"
        };
        String colName[] = {
            "codigo", "codigoUsuario", "concepto"
        };
        String origen = " codsEgresos";
        String sql = "select codigo, codigoUsuario, concepto from codsEgresos where activo = false ";

        Object dtDatos[][] = GetTabla(colName, origen, (new StringBuilder()).append(sql).toString());
        DefaultTableModel datos = new DefaultTableModel(dtDatos, columNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        return datos;
    }

    public DefaultTableModel getRegistrosEpsPrecargadas() {
        //LISTO
        String columNames[] = {
            "Id", "nombre"
        };
        String colName[] = {
            "Id", "nombre"
        };
        String origen = " bdEpsPrecargados";
        Object dtDatos[][] = GetTabla(colName, origen, (new StringBuilder()).append("select Id, nombre from ").append(origen).toString());
        DefaultTableModel datos = new DefaultTableModel(dtDatos, columNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        return datos;
    }

    public String getDatosAyudaDiagnostico(String cod) {
        //LISTO
        String instruccion_sql = "select Id,nombre"
                + " from bdAyudaDiagnostico where nombre = '" + cod + "'; ";
        //columnas de la tabla que se
        String[] colName = {"Id", "nombre"};
        boolean[] cadena = {true, true};
        try {
            return GetRegistro(colName, cadena, instruccion_sql)[0].toString();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean agregarAyudaDiagnostico(String nombre) {
        //LISTO
        boolean ok = false;
        String instruccion_sql = "insert into bdAyudaDiagnostico(nombre) values (?);";
        ok = Agregar_Registro(new Object[]{nombre}, null, instruccion_sql);
        return ok;
    }

    public DefaultTableModel getRegistrosAyudaDiagnostico() {
        //LISTO
        String columNames[] = {
            "Nombre"
        };
        String colName[] = {
            "nombre"
        };
        String origen = " bdAyudaDiagnostico";
        Object dtDatos[][] = GetTabla(colName, origen, (new StringBuilder()).append("select nombre from ").append(origen).toString());
        DefaultTableModel datos = new DefaultTableModel(dtDatos, columNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        return datos;
    }

    public boolean agregarTablaDiagnosticaVeterinaria(ndTablaDiagnostica nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdTablaDiagnosticaVeterinaria(diagnostico, concepto, valor) values (?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosTablaDiagnostica(nodo), metodos.pasarValoresTablaDiagnostica(nodo), instruccion_sql);
        return ok;
    }

    public boolean agregarTablaDiagnosticaOf(ndTablaDiagnostica nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdTablaDiagnosticaOf(diagnostico, concepto, valor) values (?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosTablaDiagnostica(nodo), metodos.pasarValoresTablaDiagnostica(nodo), instruccion_sql);
        return ok;
    }

    public boolean agregarTablaDiagnosticaMedica(ndTablaDiagnostica nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdTablaDiagnostica (diagnostico, concepto, valor) values (?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosTablaDiagnostica(nodo), metodos.pasarValoresTablaDiagnostica(nodo), instruccion_sql);
        return ok;
    }

    public boolean modificarTablaDiagnostica(ndTablaDiagnostica nodo) {
        //LISTO
        boolean ok = false;
        String instruccion_sql = "update bdTablaDiagnostica set concepto=?, valor=? where diagnostico='" + nodo.getDiagnostico() + "' ;";
        ok = Actualizar_Registro(metodos.pasarDatosTablaDiagnostica(nodo), metodos.pasarValoresTablaDiagnostica(nodo), instruccion_sql);
        return ok;
    }

    public ndTablaDiagnostica getDatosTablaDiagnostica(String Diagnostico) {
        //LISTO
        boolean ok = false;
        String instruccion_sql = " select diagnostico, concepto, valor from bdTablaDiagnostica where diagnostico = '" + Diagnostico + "'; ";
        String[] colName = {"diagnostico", "concepto", "valor"};
        boolean[] cadena = {true, true, true};
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        ndTablaDiagnostica nodo = metodos.llenarTablaDiagnostica(data);
        return nodo;
    }

    public boolean agregarAyudaDiagnostica(ndAyudaDiagnostica nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdAyudaDiagnostica(id, historia, fecha, observaciones, usuarioRegistro, valor) values (?,?,?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosAyudaDiagnostica(nodo), metodos.pasarValoresAyudaDiagnostica(nodo), instruccion_sql);
        return ok;
    }

    public boolean agregarAyudaDiagnosticaVeterinaria(ndAyudaDiagnostica nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdAyudaDiagnosticaVeterinaria(id, historia, fecha, observaciones, usuarioRegistro, valor) "
                + "values (?,?,?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosAyudaDiagnostica(nodo), metodos.pasarValoresAyudaDiagnostica(nodo), instruccion_sql);
        return ok;
    }

    public boolean agregarAyudaDiagnosticaOf(ndAyudaDiagnosticaOf nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdAyudaDiagnosticaOf(id, historia, fecha, observaciones, valor) values (?,?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosAyudaDiagnosticaOf(nodo), metodos.pasarValoresAyudaDiagnosticaOf(nodo), instruccion_sql);
        return ok;
    }
//
//    public boolean modificarAyudaDiagnostica(ndAyudaDiagnostica nodo) {
//        //LISTO
//        boolean ok = false;
//        String instruccion_sql = "update bdAyudaDiagnostica set historia=?, fecha=?, observaciones=?, usuarioRegistro=?, valor=? where id='" + nodo.getId() + "' ;";
//        ok = Actualizar_Registro(metodos.pasarDatosAyudaDiagnostica(nodo), metodos.pasarValoresAyudaDiagnostica(nodo), instruccion_sql);
//        return ok;
//    }

    public ndAyudaDiagnostica getDatosAyudaDiagnostica(String Id) {
        //LISTO
        boolean ok = false;
        String instruccion_sql = " select id, historia, fecha, observaciones, valor, usuarioRegistro from bdAyudaDiagnostica where id = '" + Id + "'; ";
        String[] colName = {"id", "historia", "fecha", "observaciones", "valor", "usuarioRegistro"};
        boolean[] cadena = {true, true, true, true, true, true};
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        ndAyudaDiagnostica nodo = metodos.llenarAyudaDiagnostica(data);
        return nodo;
    }

    public ndAyudaDiagnostica getDatosAyudaDiagnosticaVeterinaria(String Id) {
        boolean ok = false;
        String instruccion_sql = " select id, historia, fecha, observaciones, valor, usuarioRegistro from bdAyudaDiagnosticaVeterinaria where id = '" + Id + "'; ";
        String[] colName = {"id", "historia", "fecha", "observaciones", "valor", "usuarioRegistro"};
        boolean[] cadena = {true, true, true, true, true, true};
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        ndAyudaDiagnostica nodo = metodos.llenarAyudaDiagnostica(data);
        return nodo;
    }

    public ndAyudaDiagnosticaOf getDatosAyudaDiagnosticaOf(String Id) {
        boolean ok = false;
        String instruccion_sql = " select id, historia, fecha, observaciones, valor from bdAyudaDiagnosticaOf where id = '" + Id + "'; ";
        String[] colName = {"id", "historia", "fecha", "observaciones", "valor"};
        boolean[] cadena = {true, true, true, true, true};
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        ndAyudaDiagnosticaOf nodo = metodos.llenarAyudaDiagnosticaOf(data);
        return nodo;
    }

    public boolean agregarTablaFormulaVeterinaria(ndTablaFormula nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdTablaFormulaVeterinaria(formula, prescripcion, posologia, cantidad, medida, valor) values (?,?,?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosTablaFormula(nodo), metodos.pasarValoresTablaFormula(nodo), instruccion_sql);
        return ok;
    }

    public boolean agregarTablaFormula(ndTablaFormula nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdTablaFormula(formula, prescripcion, posologia, cantidad, medida, valor) values (?,?,?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosTablaFormula(nodo), metodos.pasarValoresTablaFormula(nodo), instruccion_sql);
        return ok;
    }

    public boolean agregarTablaFormulaOf(ndTablaFormulaOf nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdTablaFormulaOf(formula, prescripcion, posologia, cantidad, medida, valor) values (?,?,?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosTablaFormulaOf(nodo), metodos.pasarValoresTablaFormulaOf(nodo), instruccion_sql);
        return ok;
    }

    public boolean modificarConsulta(String id, String tratamiento) {
        //LISTO
        boolean ok = false;
        String instruccion_sql = "update bdConsultaClinica set tratamiento=? where Id='" + id + "' ;";
        ok = Actualizar_Registro(new Object[]{id, tratamiento}, null, instruccion_sql);
        return ok;
    }

    public boolean agregarFormulaMedica(ndFormulaMedica nodo) {
        //LISTO
        boolean ok = false;
        String instruccion_sql = "insert into bdFormulaMedica(id, historia, fecha, usuario, observacion, "
                + "frecCardiaca, presionArterial, peso, frecRespiratoria, temperatura, estatura, valor) "
                + "values (?,?,?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosFormulaMedica(nodo), metodos.pasarValoresFormulaMedica(nodo), instruccion_sql);
        return ok;
    }

    public boolean agregarFormulaMedicaVeterinaria(ndFormulaVeterinaria nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdFormulaMedicaVeterinaria(id, historia, fecha, usuario, observacion, "
                + "valor) values (?,?,?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosFormulaVeterinaria(nodo), metodos.pasarValoresFormulaVeterinaria(nodo), instruccion_sql);
        return ok;
    }

    public boolean agregarFormulaMedicaOf(ndFormulaVeterinaria nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdFormulaMedicaOf(id, historia, fecha, usuario, observacion, valor) values (?,?,?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosFormulaVeterinaria(nodo), metodos.pasarValoresFormulaVeterinaria(nodo), instruccion_sql);
        return ok;
    }

    public boolean modificarFormulaMedica(ndFormulaMedica nodo) {
        //LISTO
        boolean ok = false;
        String instruccion_sql = "update bdFormulaMedica set historia=?, fecha=?, usuario=?, valor=? where id='" + nodo.getId() + "' ;";
        ok = Actualizar_Registro(metodos.pasarDatosFormulaMedica(nodo), metodos.pasarValoresFormulaMedica(nodo), instruccion_sql);
        return ok;
    }

    public ndFormulaMedica getDatosFormulaMedica(String Id) {
        boolean ok = false;
        String instruccion_sql = " select id, historia, fecha, usuario, valor, observacion, frecCardiaca, presionArterial, peso, "
                + "frecRespiratoria, temperatura, estatura"
                + " from bdFormulaMedica where id = '" + Id + "'; ";
        String[] colName = {"id", "historia", "fecha", "usuario", "valor", "observacion", "frecCardiaca", "presionArterial", "peso",
            "frecRespiratoria", "temperatura", "estatura"};
        boolean[] cadena = {true, true, true, true, true, true, true, true, true, true, true, true};
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        ndFormulaMedica nodo = metodos.llenarFormulaMedica(data);
        return nodo;
    }

    public ndFormulaVeterinaria getDatosFormulaMedicaVeterinaria(String Id) {
        boolean ok = false;
        String instruccion_sql = " select id, historia, fecha, usuario, valor, observacion from bdFormulaMedicaVeterinaria where id = '" + Id + "'; ";
        String[] colName = {"id", "historia", "fecha", "usuario", "valor", "observacion"};
        boolean[] cadena = {true, true, true, true, true, true};
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        ndFormulaVeterinaria nodo = metodos.llenarFormulaVeterinaria(data);
        return nodo;
    }

    public boolean agregarEps(ndEps nodo) {
        //LISTO
        boolean ok = false;
        String instruccion_sql = "insert into bdEps(id, dato1, dato2, dato3, dato4, dato5, dato6, tarifa, porcentaje) values (?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosEps(nodo), metodos.pasarValoresEps(nodo), instruccion_sql);
        return ok;
    }

    public boolean modificarEps(ndEps nodo) {
        //LISTO
        boolean ok = false;
        String instruccion_sql = "update bdEps set dato1=?, dato2=?, dato3=?, dato4=?, dato5=?, dato6=?, tarifa=?, porcentaje=? where id='" + nodo.getId() + "' ;";
        ok = Actualizar_Registro(metodos.pasarDatosEps(nodo), metodos.pasarValoresEps(nodo), instruccion_sql);
        return ok;
    }

    public ndEps getDatosEps(String Id) {
        //LISTO
        boolean ok = false;
        String instruccion_sql = " select id, dato1, dato2, dato3, dato4, dato5, dato6, tarifa, porcentaje from bdEps where id = '" + Id + "'; ";
        String[] colName = {"id", "dato1", "dato2", "dato3", "dato4", "dato5", "dato6", "tarifa", "porcentaje"};
        boolean[] cadena = {true, true, true, true, true, true, true, true, true};
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        ndEps nodo = metodos.llenarEps(data);
        return nodo;
    }

    public boolean agregarSeguimientoClinica(ndSeguimientoClinica nodo) {
        //LISTO
        boolean ok = false;
        String instruccion_sql = "insert into bdSeguimientoClinica(fecha, usuario, evolucion, observaciones,consulta, examenesId,examenesDesc,examenesUrl, t,peso,pa,fr)"
                + " values(?,?,?,?,?,?,?,?,?,?,?,?);";

        ok = Agregar_Registro(metodos.pasarDatosSeguimientoClinica(nodo), null, instruccion_sql);
        return ok;
    }

    public boolean agregarSeguimientoClinicaOf(ndSeguimientoClinicaOf nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdSeguimientoOftalmologia(fecha, usuario, evolucion, fotos, consulta, examen)"
                + " values(?,?,?,?,?,?);";

        ok = Agregar_Registro(metodos.pasarDatosSeguimientoClinicaOf(nodo), null, instruccion_sql);
        return ok;
    }

    public ndEpsPrecargados getDatosEpsPrecargados(String Id) {
        //LISTO
        boolean ok = false;
        String instruccion_sql = " select Id, nombre, codigoProteccionSocial,consecutivo,contrato,telefono,direccion from bdEpsPrecargados where Id = '" + Id + "'; ";
        String[] colName = {"Id", "nombre", "codigoProteccionSocial", "consecutivo", "contrato", "telefono", "direccion"};
        boolean[] cadena = {true, true, true, true, true, true, true};
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        ndEpsPrecargados nodo = metodos.llenarEpsPrecargados(data);
        return nodo;
    }

    public String getNombreEps(String id) {

        String instruccion_sql = "select nombre"
                + " from bdEpsPrecargados where Id = '" + id + "'; ";
        //columnas de la tabla que se
        String[] colName = {"nombre"};
        boolean[] cadena = {true};
        //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        return data[0].toString();
    }

    public Object[] getNumConsecutivoEps(String valor) {
        //LISTO
        String instruccion_sql = "select numero"
                + " from bdConsecutivosEps where consecutivo = '" + valor + "'; ";
        //columnas de la tabla que se
        String[] colName = {"numero"};
        boolean[] cadena = {true};
        //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        return data;
    }

    public boolean aumentarConsecutivoEps(String id, int num) {
        //LISTO
        boolean ok = false;
        String instruccion_sql = "update bdConsecutivosEps set numero=? where consecutivo='" + id + "' ;";
        ok = Actualizar_Registro(new Object[]{"", String.valueOf(num)}, null, instruccion_sql);
        return ok;
    }

    public String nombreProducto(String cod) {
        String colName[] = {"relacion"};
        String origen = " bdRelacionados";
        //Object dtDatos[][] = getDatos(colName, origen, "select restante, Id FROM bdNc where cliente = '" + cliente + "' AND restante <> 0", " where cliente = '" + cliente + "' AND restante <> 0");
        Object dtDatos[][] = getDatos(colName, origen, "select relacion from " + origen + " where nombreProd = '" + cod + "' ", " where nombreProd = '" + cod + "'");
        return dtDatos[0][0].toString();
    }

    public boolean agregarOrdenServicio(ndOrdenServicio nodo) {
        //LISTO
        boolean ok = false;
        String instruccion_sql = "insert into bdOrdenServicio(id, orden, usuario, fecha, poliza, concepto, acobrar, autorizacion, finalidad, causa, ambito, personal, forma, paciente, cie, cup, fechaSistema, tipo,codigoIps,codigoConsecutivo,actoQuirurgico,cantidadRealizada,consecutivo,codigoConsulta,medico,hora,convenio, copago, comision, descuento, valor, cantidad, cuota) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosOrdenServicio(nodo), metodos.pasarValoresOrdenServicio(nodo), instruccion_sql);
        return ok;
    }

    public boolean agregarOrdenServicioOftalmologia(ndOrdenServicio nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdOrdenServicioOftalmologia(id, orden, usuario, fecha, poliza, concepto, acobrar, autorizacion, finalidad, causa, ambito, personal, forma, paciente, cie, cup, fechaSistema, tipo,codigoIps,codigoConsecutivo,actoQuirurgico,cantidadRealizada,consecutivo,codigoConsulta,medico,hora,convenio, copago, comision, descuento, valor, cantidad, cuota) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosOrdenServicio(nodo), metodos.pasarValoresOrdenServicio(nodo), instruccion_sql);
        return ok;
    }

    public boolean agregarOrdenServicioLaboratorio(ndOrdenServicio nodo) {
        //LISTO
        boolean ok = false;
        String instruccion_sql = "insert into bdOrdenServicioLaboratorio(id, orden, usuario, fecha, poliza, concepto, acobrar, autorizacion, finalidad, causa, ambito, personal, forma, paciente, cie, cup, fechaSistema, tipo,codigoIps,codigoConsecutivo,actoQuirurgico,cantidadRealizada,consecutivo,codigoConsulta,medico,hora,convenio, copago, comision, descuento, valor, cantidad, cuota) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosOrdenServicio(nodo), metodos.pasarValoresOrdenServicio(nodo), instruccion_sql);
        return ok;
    }

    public boolean modificarOrdenServicioLaboratorio(ndOrdenServicio nodo) {
        //LISTO
        boolean ok = false;
        String instruccion_sql = "update bdOrdenServicioLaboratorio set orden=?, usuario=?, fecha=?, poliza=?, concepto=?, acobrar=?, autorizacion=?, finalidad=?, causa=?, ambito=?, personal=?, forma=?, paciente=?, cie=?, cup=?, fechaSistema=?, tipo=?,codigoIps=?,codigoConsecutivo=?,actoQuirurgico=?,cantidadRealizada=? ,consecutivo=?,codigoConsulta=?,medico=?,hora=?,convenio=?, copago=?, comision=?, descuento=?, valor=?, cantidad=?, cuota=? where id='" + nodo.getId() + "' ;";
        ok = Actualizar_Registro(metodos.pasarDatosOrdenServicio(nodo), metodos.pasarValoresOrdenServicio(nodo), instruccion_sql);
        return ok;
    }

    public boolean modificarOrdenServicio(ndOrdenServicio nodo) {
        //LISTO
        boolean ok = false;
        String instruccion_sql = "update bdOrdenServicio set orden=?, usuario=?, fecha=?, poliza=?, concepto=?, acobrar=?, autorizacion=?, finalidad=?, causa=?, ambito=?, "
                + "personal=?, forma=?, paciente=?, cie=?, cup=?, fechaSistema=?, tipo=?, codigoIps=?, codigoConsecutivo=?, actoQuirurgico=?, cantidadRealizada=?, consecutivo=?, "
                + "codigoConsulta=?, medico=?, hora=?, convenio=?, copago=?, comision=?, descuento=?, valor=?, cantidad=?, cuota=? where id='" + nodo.getId() + "' ;";
        ok = Actualizar_Registro(metodos.pasarDatosOrdenServicio(nodo), metodos.pasarValoresOrdenServicio(nodo), instruccion_sql);
        return ok;
    }

    public ndOrdenServicio getDatosOrdenServicio(String Id) {
        boolean ok = false;
        String instruccion_sql = " select id, orden, usuario, fecha, poliza, concepto, acobrar, autorizacion, finalidad, causa, "
                + "ambito, personal, forma, copago, comision, descuento, valor, cantidad, cuota, paciente, cie, cup, fechaSistema, "
                + "tipo,codigoIps,codigoConsecutivo,actoQuirurgico,cantidadRealizada,consecutivo,codigoConsulta,medico,hora,"
                + "convenio from bdOrdenServicio where id = '" + Id + "'; ";
        String[] colName = {"id", "orden", "usuario", "fecha", "poliza", "concepto", "acobrar", "autorizacion", "finalidad",
            "causa", "ambito", "personal", "forma", "copago", "comision", "descuento", "valor", "cantidad", "cuota", "paciente",
            "cie", "cup", "fechaSistema", "tipo", "codigoIps", "codigoConsecutivo", "actoQuirurgico", "cantidadRealizada",
            "consecutivo", "codigoConsulta", "medico", "hora", "convenio"};
        boolean[] cadena = {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true};
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        ndOrdenServicio nodo = metodos.llenarOrdenServicio(data);
        return nodo;
    }

    public ndOrdenServicio getDatosOrdenServicioLaboratorio(String Id) {
        //LISTO
        boolean ok = false;
        String instruccion_sql = " select id, orden, usuario, fecha, poliza, concepto, acobrar, autorizacion, finalidad, causa, "
                + "ambito, personal, forma, copago, comision, descuento, valor, cantidad, cuota, paciente, cie, cup, fechaSistema, "
                + "tipo,codigoIps,codigoConsecutivo,actoQuirurgico,cantidadRealizada,consecutivo,codigoConsulta,medico,hora,"
                + "convenio from bdOrdenServicioLaboratorio where id = '" + Id + "'; ";
        String[] colName = {"id", "orden", "usuario", "fecha", "poliza", "concepto", "acobrar", "autorizacion", "finalidad",
            "causa", "ambito", "personal", "forma", "copago", "comision", "descuento", "valor", "cantidad", "cuota", "paciente",
            "cie", "cup", "fechaSistema", "tipo", "codigoIps", "codigoConsecutivo", "actoQuirurgico", "cantidadRealizada",
            "consecutivo", "codigoConsulta", "medico", "hora", "convenio"};
        boolean[] cadena = {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true};
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        ndOrdenServicio nodo = metodos.llenarOrdenServicio(data);
        return nodo;
    }

    public ndOrdenServicio getDatosOrdenServicioAutorizacion(String Id) {
        boolean ok = false;
        String instruccion_sql = " select id, orden, usuario, fecha, poliza, concepto, acobrar, autorizacion, finalidad, causa, "
                + "ambito, personal, forma, copago, comision, descuento, valor, cantidad, cuota, paciente, cie, cup, fechaSistema, "
                + "tipo,codigoIps,codigoConsecutivo,actoQuirurgico,cantidadRealizada,consecutivo,codigoConsulta, medico,hora, "
                + "convenio from bdOrdenServicio where autorizacion = '" + Id + "'; ";
        String[] colName = {"id", "orden", "usuario", "fecha", "poliza", "concepto", "acobrar", "autorizacion", "finalidad",
            "causa", "ambito", "personal", "forma", "copago", "comision", "descuento", "valor", "cantidad", "cuota", "paciente",
            "cie", "cup", "fechaSistema", "tipo", "codigoIps", "codigoConsecutivo", "actoQuirurgico", "cantidadRealizada",
            "consecutivo", "codigoConsulta", "medico", "hora", "convenio"};
        boolean[] cadena = {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,
            true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true};
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        ndOrdenServicio nodo = this.metodos.llenarOrdenServicio(data);
        return nodo;
    }
//    public ndOrdenServicio getDatosOrdenServicioAutorizacion(String Id) {
//        boolean ok = false;
//        String instruccion_sql = " select id, orden, usuario, fecha, poliza, concepto, acobrar, autorizacion, finalidad, causa, "
//                + "ambito, personal, forma, copago, comision, descuento, valor, cantidad, cuota, paciente, cie, cup, fechaSistema, "
//                + "tipo,codigoIps,codigoConsecutivo,actoQuirurgico,cantidadRealizada,consecutivo,codigoConsulta, medico,hora, "
//                + "convenio, idMedico from bdOrdenServicio where autorizacion = '" + Id + "'; ";
//        String[] colName = {"id", "orden", "usuario", "fecha", "poliza", "concepto", "acobrar", "autorizacion", "finalidad",
//            "causa", "ambito", "personal", "forma", "copago", "comision", "descuento", "valor", "cantidad", "cuota", "paciente",
//            "cie", "cup", "fechaSistema", "tipo", "codigoIps", "codigoConsecutivo", "actoQuirurgico", "cantidadRealizada",
//            "consecutivo", "codigoConsulta", "medico", "hora", "convenio", "idMedico"};
//        boolean[] cadena = {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,
//            true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true};
//        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
//        ndOrdenServicio nodo = this.metodos.llenarOrdenServicio(data);
//        return nodo;
//    }

    public void cambiarEstadoOrdenTotal(String base) {
        //LISTO
        String instruccion_sql = "update " + base + " set estado2=? ;";
        Actualizar_Registro(new Object[]{"", ""}, null, instruccion_sql);
    }

    public void cambiarEstadoOrden1(String orden, String estado, String prod) {
        //LISTO
        String instruccion_sql = "update bdOServicio1 set estado2=? WHERE idFactura ='" + orden + "' AND producto = '" + prod + "' ;";
        Actualizar_Registro(new Object[]{"", estado}, null, instruccion_sql);
    }

    public void cambiarEstadoOrden1(String orden, String estado) {
        //LISTO
        String instruccion_sql = "update bdOServicio1 set estado2=? WHERE idFactura ='" + orden + "' ;";
        Actualizar_Registro(new Object[]{"", estado}, null, instruccion_sql);
    }

    public void cambiarEstadoOrden(String orden, String estado) {
        //LISTO
        String instruccion_sql = "update bdOrdenServicio set estado2=? WHERE id='" + orden + "' ;";
        Actualizar_Registro(new Object[]{"", estado}, null, instruccion_sql);
    }

    public void cambiarEstadoProdVitrina(String prod) {
        String instruccion_sql = "update bdDetalleVitrinas set estado=? WHERE idProd ='" + prod + "' ;";
        Actualizar_Registro(new Object[]{"", "FALTANTE"}, null, instruccion_sql);
    }

    public DefaultTableModel getRegistrosRips(String condicion, String base, String base2) {
        String[] columNames = {"Orden", "Id", "Tipo Id", "Nombre", "Cod.Procedimiento", "Procedimiento", "Eps", "Autorización", "Cie", "Cup",
            "Cant.Realizada", "Copago", "Valor", "Seleccionar"};
        String[] colName = {"orden", "Id", "tipoIdentidad", "nombre", "codigoTarifa", "procedimientoAutorizado", "nombreEntidadAdministradora",
            "autorizacion", "cie", "cup", "cantidadRealizada", "copago", "valor"};
        String origen = base;
        Object[][] dtDatos2 = getDatos(colName, origen, "select orden,Id,tipoIdentidad,nombre,codigoTarifa,procedimientoAutorizado,nombreEntidadAdministradora,"
                + "autorizacion,cie,cup,cantidadRealizada,copago,valor from " + base + " where Estado = 'PENDIENTE' " + condicion, " where Estado = 'PENDIENTE' " + condicion);
        Object[][] dtDatos = new Object[dtDatos2.length][14];

        for (int i = 0; i < dtDatos.length; i++) {
            for (int j = 0; j < 13; j++) {
                dtDatos[i][j] = dtDatos2[i][j];
            }
            dtDatos[i][13] = true;
        }

        DefaultTableModel datos = new DefaultTableModel(dtDatos, columNames) {
            public boolean isCellEditable(int row, int column) {
                if (column == 13) {
                    return true;
                }
                return false;
            }
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };

        System.out.println(dtDatos.length);
        for (Object[] id : dtDatos) {
            String instruccion_sql = "update " + base2 + " set estado2=? WHERE id='" + id[0] + "' ;";
            Actualizar_Registro(new Object[]{"", "ON-" + metodosGenerales.fecha()}, null, instruccion_sql);
        }

        return datos;
    }

    public Object[][] getCodigoPrestadorServicio(String base) {
        String colName[] = {"codPrestadorServicio", "razonSocial", "tipoPrestadorServicio", "IdPrestadorServicio"};
        String origen = base;
        Object dtDatos[][] = getDatos(colName, origen, "select codPrestadorServicio, razonSocial, tipoPrestadorServicio, IdPrestadorServicio FROM " + base + " group by codPrestadorServicio, razonSocial, tipoPrestadorServicio, IdPrestadorServicio ", "");
        return dtDatos;
    }

    public Object[][] getAf(String condicion, String[] infomacion, String base) {
        String[] colName = {"codPrestadorServicio", "razonSocial", "tipoPrestadorServicio", "IdPrestadorServicio", "factura", "fechaFactura", "fechaInicio", "fechaFinal", "codigoEntidadAdministradora", "nombreEntidadAdministradora", "numeroContrato",
            "planBeneficio", "nroPoliza", "copago", "valorComision", "valorDescuento", "valor2"};
        String origen = base;
        Object[][] dtDatos = getDatos(colName, origen, "select codPrestadorServicio, razonSocial, tipoPrestadorServicio, IdPrestadorServicio, "
                + " '" + infomacion[0] + "' AS factura,'" + infomacion[1] + "' AS fechaFactura,'" + infomacion[2] + "' AS fechaInicio,'" + infomacion[3] + "' "
                + "AS fechaFinal, codigoEntidadAdministradora, nombreEntidadAdministradora, numeroContrato,'" + infomacion[4] + "' AS planBeneficio, nroPoliza, "
                + "copago, valorComision, valorDescuento, valor2 FROM " + base + " " + condicion, "");
        return dtDatos;
    }

    public Object[][] getUs(String condicion, String base) {
        String[] colName = {"tipoIdentidad", "Id", "codigoEntidadAdministradora", "TipoUsuario", "pApellido", "sApellido", "pNombre", "sNombre", "Edad", "unidadMedidaEdad", "sexo", "codigoDepartamento", "codigoMunicipio", "zona"};
        String origen = base;
        Object[][] dtDatos = getDatos(colName, origen, "select tipoIdentidad, Id, codigoEntidadAdministradora, TipoUsuario, pApellido, sApellido, pNombre, "
                + "sNombre,Edad,unidadMedidaEdad,sexo,codigoDepartamento,codigoMunicipio,zona FROM " + base + " " + condicion + " ORDER BY pApellido", "");
        return dtDatos;
    }

    public Object[][] getAfGenerales(String condicion, String[] infomacion, String base) {
        String[] colName = {"codPrestadorServicio", "razonSocial", "tipoPrestadorServicio", "IdPrestadorServicio", "orden", "fechaFactura", "fechaInicio",
            "fechaFinal", "codigoEntidadAdministradora", "nombreEntidadAdministradora", "numeroContrato", "planBeneficio", "nroPoliza", "copago",
            "valorComision", "valorDescuento", "valor2"};
        String origen = base;

        System.out.println("informacionnnnnnnnn " + infomacion[1]);

        Object[][] dtDatos = getDatos(colName, origen, "select codPrestadorServicio, razonSocial, tipoPrestadorServicio, IdPrestadorServicio,orden, "
                + " '" + infomacion[1] + "' AS fechaFactura,'" + infomacion[2] + "' AS fechaInicio,'" + infomacion[3] + "' AS fechaFinal, "
                + "codigoEntidadAdministradora, nombreEntidadAdministradora, numeroContrato,'" + infomacion[4] + "' AS planBeneficio, nroPoliza, copago, "
                + "valorComision, valorDescuento, valor2 FROM " + base + " " + condicion, "");
        return dtDatos;
    }

    public Object[][] getAd(String condicion, String[] infomacion, String base) {
        String[] colName = {"factura", "codPrestadorServicio", "codigoConcepto", "cantidadRealizada", "valor", "valorTotal"};
        String origen = base;
        Object[][] dtDatos = getDatos(colName, origen, "select '" + infomacion[0] + "' AS factura,codPrestadorServicio,codigoConcepto,cantidadRealizada,valor,"
                + "valorTotal FROM " + base + " " + condicion, "");
        return dtDatos;
    }

    public Object[][] getAdGenerales(String condicion, String[] infomacion, String base) {
        String[] colName = {"orden", "codPrestadorServicio", "codigoConcepto", "cantidadRealizada", "valor", "valorTotal"};
        String origen = base;
        Object[][] dtDatos = getDatos(colName, origen, "select orden,codPrestadorServicio,codigoConcepto,cantidadRealizada,valor,"
                + "valorTotal FROM " + base + " " + condicion, "");
        return dtDatos;
    }

    public Object[][] getAc(String condicion, String[] infomacion, String base) {
        String[] colName = {"factura", "codPrestadorServicio", "tipoIdentidad", "Id", "fecha", "autorizacion", "codConsulta", "finalidadd", "causaExterna",
            "cie", "codDiagnosticoReacionado1", "codDiagnosticoReacionado2", "codDiagnosticoReacionado3", "tipoDiagnosticoPrincipal", "valor",
            "valorCuotaModeradora", "valorNeto"};
        String origen = base;
        Object[][] dtDatos = getDatos(colName, origen, "select '" + infomacion[0] + "' AS factura,codPrestadorServicio,tipoIdentidad,Id,fecha,autorizacion,"
                + "codConsulta,finalidadd,causaExterna,cie,codDiagnosticoReacionado1,codDiagnosticoReacionado2,codDiagnosticoReacionado3,"
                + "tipoDiagnosticoPrincipal,valor,valorCuotaModeradora,valorNeto FROM " + base + " " + condicion, "");
        return dtDatos;
    }

    public Object[][] getAcGenerales(String condicion, String[] infomacion, String base) {
        String[] colName = {"orden", "codPrestadorServicio", "tipoIdentidad", "Id", "fecha", "autorizacion", "codConsulta", "finalidadd", "causaExterna",
            "cie", "codDiagnosticoReacionado1", "codDiagnosticoReacionado2", "codDiagnosticoReacionado3", "tipoDiagnosticoPrincipal", "valor",
            "valorCuotaModeradora", "valorNeto"};
        String origen = base;
        Object[][] dtDatos = getDatos(colName, origen, "select orden,codPrestadorServicio,tipoIdentidad,Id,fecha,autorizacion,codConsulta,finalidadd,"
                + "causaExterna,cie,codDiagnosticoReacionado1,codDiagnosticoReacionado2,codDiagnosticoReacionado3,tipoDiagnosticoPrincipal,valor,"
                + "valorCuotaModeradora,valorNeto FROM " + base + " " + condicion, "");
        return dtDatos;
    }

    public Object[][] getAp(String condicion, String[] infomacion, String base) {
        String[] colName = {"factura", "codPrestadorServicio", "tipoIdentidad", "Id", "fecha", "autorizacion", "codigoTarifa", "ambitoProcedimiento",
            "finalidadd", "personalAtiende", "tipoDiagnosticoPrincipal", "DiagnosticoRelacionado", "complicaciones", "actoQuirurgico", "valor2"};
        String origen = base;
        Object[][] dtDatos = getDatos(colName, origen, "select '" + infomacion[0] + "' AS factura,codPrestadorServicio,tipoIdentidad,Id,fecha,autorizacion,"
                + "codigoTarifa,ambitoProcedimiento,finalidadd,personalAtiende,tipoDiagnosticoPrincipal,DiagnosticoRelacionado,complicaciones,"
                + "actoQuirurgico,valor2 FROM " + base + " " + condicion, "");
        return dtDatos;
    }

    public Object[][] getApGenerales(String condicion, String[] infomacion, String base) {
        String[] colName = {"orden", "codPrestadorServicio", "tipoIdentidad", "Id", "fecha", "autorizacion", "codigoTarifa", "ambitoProcedimiento",
            "finalidadd", "personalAtiende", "tipoDiagnosticoPrincipal", "DiagnosticoRelacionado", "complicaciones", "actoQuirurgico", "valor2"};
        String origen = base;
        Object[][] dtDatos = getDatos(colName, origen, "select orden,codPrestadorServicio,tipoIdentidad,Id,fecha,autorizacion,codigoTarifa,ambitoProcedimiento,"
                + "finalidadd,personalAtiende,tipoDiagnosticoPrincipal,DiagnosticoRelacionado,complicaciones,actoQuirurgico,valor2 FROM " + base + " " + condicion, "");
        return dtDatos;
    }

    //METODOS DE AGENDA
    public Object[][] getRegistrosCitas(String tercero) {
        String colName[] = {
            "fecha", "hora", "aCargo"
        };
        String origen = "Agenda";
        Object dtDatos[][] = getDatos(colName, origen, "select fecha, hora, aCargo from " + origen + " where cliente = '" + tercero + "' and estado <> 'Cancelada' order by Id Asc", " where cliente = '" + tercero + "' and estado <> 'Cancelada' ");

        return dtDatos;
    }

    public boolean AgregarConsultaAntigua(Object[] dato) {
        boolean ok = false;
        String instruccion_sql = "insert into bdAgendaAntigua(idAgenda, fecha, hora, motivo, medico, observacion)"
                + "values(?,?,?,?,?,?);";
        ok = Agregar_Registro(dato, null, instruccion_sql);
        return ok;
    }

    public boolean AgregarPeluqueriaAntigua(Object[] dato) {
        boolean ok = false;
        String instruccion_sql = "insert into bdPeluqueriaAntigua(idAgenda, fecha, hora, motivo, medico, observacion)"
                + "values(?,?,?,?,?,?);";
        ok = Agregar_Registro(dato, null, instruccion_sql);
        return ok;
    }

    public ndAgenda getDatosAgenda(String id) {
        boolean ok = false;
        String instruccion_sql = "select Id, cliente, fecha, hora, motivo, aCargo, estado, observacion, mascota, "
                + "proxima, descripcion, gestion from bdAgenda where Id='" + id + "'";
        String[] colName = {"Id", "cliente", "fecha", "hora", "motivo", "aCargo", "estado", "observacion", "mascota",
            "proxima", "descripcion", "gestion"};
        boolean[] cadena = {true, true, true, true, true, true, true, true, true, true, true, false};
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        ndAgenda nodo = metodos.llenarAgenda(data);
        return nodo;
    }

    public ndAgenda getDatosPeluqueriaVet(String id) {
        boolean ok = false;
        String instruccion_sql = "select Id, cliente, fecha, hora, motivo, aCargo, estado, observacion, mascota,"
                + " proxima, descripcion, gestion from bdPeluqueria where Id='" + id + "'";
        String[] colName = {"Id", "cliente", "fecha", "hora", "motivo", "aCargo", "estado", "observacion",
            "mascota", "proxima", "descripcion", "gestion"};
        boolean[] cadena = {true, true, true, true, true, true, true, true, true, true, true, false};
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        ndAgenda nodo = metodos.llenarAgenda(data);
        return nodo;
    }

    public boolean agregarAgenda(ndAgenda nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdAgenda(Id, cliente, fecha, hora, motivo, aCargo, estado, "
                + "observacion, mascota, proxima, descripcion, gestion) values (?,?,?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosAgenda(nodo), null, instruccion_sql);
        return ok;
    }

    public boolean agregarPeluqueria(ndAgenda nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdPeluqueria(Id, cliente, fecha, hora, motivo, aCargo, estado, "
                + "observacion, mascota, proxima, descripcion, gestion) values (?,?,?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosAgenda(nodo), null, instruccion_sql);
        return ok;
    }

    public boolean modificarAgenda(ndAgenda nodo) {
        boolean ok = false;
        String instruccion_sql = "update bdAgenda set cliente=?, fecha=?, hora=?, motivo=?, aCargo=?, estado=?, "
                + "observacion=?, mascota=?, proxima=?, descripcion=?, gestion=? where Id='" + nodo.getId() + "' ;";
        ok = Actualizar_Registro(metodos.pasarDatosAgenda(nodo), null, instruccion_sql);
        return ok;
    }

    public boolean modificarPeluqueria(ndAgenda nodo) {
        boolean ok = false;
        String instruccion_sql = "update bdPeluqueria set cliente=?, fecha=?, hora=?, motivo=?, aCargo=?, estado=?, "
                + "observacion=?, mascota=?, proxima=?, descripcion=?, gestion=? where Id='" + nodo.getId() + "' ;";
        ok = Actualizar_Registro(metodos.pasarDatosAgenda(nodo), null, instruccion_sql);
        return ok;
    }

    public boolean modificarGuarderia1(String id, String fact) {
        boolean ok = false;
        String instruccion_sql = "update bdGuarderia set factura=?, activo=?, fSalida=?,"
                + "hSalida=? where Id ='" + id + "' ;";
        Object[] datos = {"", fact, true, metodos.fecha4(metodosGenerales.fecha()), metodosGenerales.hora()};
        ok = Actualizar_Registro(datos, null, instruccion_sql);
        return ok;
    }

    public boolean modificarProximoControl(String id, String fecha, Boolean gestiono) {
        boolean ok = false;
        String instruccion_sql = "update bdPeluqueria set proxima=?, gestion=? where Id = '" + id + "' ;";
        Object[] datos = {"", fecha, gestiono};
        ok = Actualizar_Registro(datos, null, instruccion_sql);
        return ok;
    }

    public boolean modificarGuarderia(String id, String horas, String dias, BigDecimal total) {
        boolean ok = false;
        String instruccion_sql = "update bdGuarderia set horas=?, dias=?, totValor=? where Id ='" + id + "' ;";
        Object[] datos = {"", horas, dias};
        Object[] datos1 = {total};
        ok = Actualizar_Registro(datos, datos1, instruccion_sql);
        return ok;
    }

    public boolean modificarHospitalizacion1(String id, String fact, BigDecimal total) {
        boolean ok = false;
        String instruccion_sql = "update bdIngresosHospitalizacion set factura=?, activo=?, totValor=? where Id ='" + id + "' ;";
        Object[] datos = {"", fact, true};
        Object[] datos1 = {total};
        ok = Actualizar_Registro(datos, datos1, instruccion_sql);
        return ok;
    }

    public boolean modificarHospitalizacion(String id, String horas, String dias) {
        boolean ok = false;
        String instruccion_sql = "update bdIngresosHospitalizacion set horas=?, dias=?, fSalida=?, hSalida=? where Id ='" + id + "' ;";
        Object[] datos = {"", horas, dias, metodos.fechaConsulta(metodosGenerales.fecha()), metodosGenerales.hora()};
        ok = Actualizar_Registro(datos, null, instruccion_sql);
        return ok;
    }

    public boolean modificarPeluqueria(String id, String estado, String fact) {
        boolean ok = false;
        String instruccion_sql = "update bdPeluqueria set estado=?, factura=? where Id='" + id + "' ;";
        Object[] datos = {"", estado, fact};
        ok = Actualizar_Registro(datos, null, instruccion_sql);
        return ok;
    }

    public boolean modificarCita(String id, String estado) {
        boolean ok = false;
        String instruccion_sql = "update bdAgenda set estado=? where Id='" + id + "' ;";
        Object[] datos = {"", estado};
        ok = Actualizar_Registro(datos, null, instruccion_sql);
        return ok;
    }

    public Object[][] getNuevoTercero(String paciente) {
        String colName[] = {
            "cc", "primerNombre", "segundoNombre", "primerApellido", "segundoApellido", "telefono", "celular", "direccion", "cedula"
        };
        String origen = " bdNuevoTercero";
        Object dtDatos[][] = getDatos(colName, origen, "select cc, primerNombre, segundoNombre, primerApellido, segundoApellido, "
                + "telefono, celular, direccion, cedula FROM bdNuevoTercero where cc = '" + paciente + "'", " where cc = '" + paciente + "'");

        return dtDatos;
    }

    public Object[][] getRegistroCitas(String paciente) {
        String colName[] = {
            "Atendido", "NoVino", "Reagendada", "Cancelada", "Registrada"
        };
        String origen = " conteoCitas";
        Object dtDatos[][] = getDatos(colName, origen, "select Atendido, NoVino, Reagendada, Cancelada, Registrada FROM conteoCitas "
                + "where IdSistema = '" + paciente + "'", " where IdSistema = '" + paciente + "'");
        return dtDatos;
    }

    public Object[][] getRegistroPeluqueria(String paciente) {
        String colName[] = {
            "Atendido", "NoVino", "Reagendada", "Cancelada", "Registrada"
        };
        String origen = " conteoPeluqueria";
        Object dtDatos[][] = getDatos(colName, origen, "select Atendido, NoVino, Reagendada, Cancelada, Registrada FROM conteoPeluqueria where idSistema = '" + paciente + "' ",
                " where idSistema = '" + paciente + "'");

        return dtDatos;
    }

    public Object[][] getVitrinas(String consulta) {
        String colName[] = {"Id", "nombre", "capacidad"};
        String origen = " bdVitrina ";
        Object dtDatos[][] = getDatos(colName, origen, "select Id, nombre, capacidad FROM bdVitrina " + consulta, consulta);
        return dtDatos;
    }

    public boolean AgregarNuevoTercero(Object[] dato) {
        boolean ok = false;
        String instruccion_sql = "insert into bdNuevoTercero(cc, primerNombre, segundoNombre, primerApellido, segundoApellido, "
                + "telefono, usuario, fecha, celular, direccion, cedula)"
                + "values(?,?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(dato, null, instruccion_sql);
        return ok;
    }

    public boolean AgregarContraremision(Object[] dato) {
        boolean ok = false;
        String instruccion_sql = "insert into bdContraremision(Id, fecha, hallazgos, nombres, apellidos, edad, tipoId, identificacion, telefono, tratamientos, "
                + "recomendaciones, diag1, diag2, nombre1, nombre2, medico, cedulaMedico, registro) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(dato, null, instruccion_sql);
        return ok;
    }

    public DefaultTableModel getRegistrosProductosAgenda(String subGrupo) {
        String columNames[] = {
            "Codigo", "Descripcion", "Lista 1"
        };
        String colName[] = {
            "Codigo", "Descripcion", "L1"
        };
        String origen = " bdProductos where Grupo='GRP-0'";
        Object dtDatos[][] = GetTabla(colName, origen, (new StringBuilder()).append("select Codigo, Descripcion, L1 from bdProductos where Grupo='GRP-0' ORDER BY Descripcion").toString());
        DefaultTableModel datos = new DefaultTableModel(dtDatos, columNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        return datos;
    }

    public Object[][] getEmpleadosNombreAgenda() {
        String colName[] = {"nombre", "id", "idSistema"};
        String origen = " bdTerceros";
        Object dtDatos[][] = getDatos(colName, origen, "select nombre, id, idSistema from bdTerceros where agendar=true", " where agendar=true ");
        return dtDatos;
    }

    public Object[][] getPeluqueros() {
        String colName[] = {"nombre", "id", "idSistema"};
        String origen = " bdTerceros";
        Object dtDatos[][] = getDatos(colName, origen, "select nombre, id, idSistema from bdTerceros where cargo = 'Peluquero' ", " where cargo = 'Peluquero' ");
        return dtDatos;
    }

    public Object[][] getRegistrosAgendas(String fecha, String atiende) {
        String colName[] = {
            "hora", "Id", "nombre", "estado", "motivo", "observacion", "cliente", "cc", "descripcion"
        };
        String origen = " agendaMedico";
        Object dtDatos[][] = getDatos(colName, origen, "select hora, Id, nombre, estado, motivo, observacion, cliente, cc, descripcion "
                + "from " + origen + " where fecha = '" + fecha + "' and aCargo = '" + atiende + "' and estado <> 'Cancelada' ",
                " where fecha = '" + fecha + "' and aCargo='" + atiende + "' and estado <> 'Cancelada' ");
        return dtDatos;
    }

    public Object[][] getRegistrosPeluquerias(String fecha, String atiende) {
        String colName[] = {
            "hora", "Id", "nombre", "estado", "motivo", "observacion", "cc", "descripcion"
        };
        String origen = " agendaPeluqueria ";
        Object dtDatos[][] = getDatos(colName, origen, "select hora, Id, nombre, estado, motivo, observacion, cc, descripcion "
                + "from " + origen + " where fecha = '" + fecha + "' and aCargo = '" + atiende + "' and estado <> 'Cancelada' ",
                " where fecha = '" + fecha + "' and aCargo='" + atiende + "' and estado <> 'Cancelada' ");
        return dtDatos;
    }

    public Object[][] getRegistrosAgendasHora(String fecha, String atiende, String hora) {
        String colName[] = {
            "hora", "Id", "nombre", "estado", "motivo", "observacion", "cliente"
        };
        String origen = " Agenda";
        Object dtDatos[][] = getDatos(colName, origen, "select hora, Id, nombre, estado, motivo, observacion, cliente "
                + " from" + origen + " where fecha = '" + fecha + "' and aCargo ='" + atiende + "' and estado <> 'Cancelada' and hora = '" + hora + "' ",
                " where fecha ='" + fecha + "' and aCargo='" + atiende + "' and estado <> 'Cancelada' and hora = '" + hora + "' ");

        return dtDatos;
    }

    public DefaultTableModel getRegistrosCxcSepare(String factura) {
        String columNames[] = {
            "Factura", "Doc.Cliente", "Nom.Cliente", "Fecha Factura", "Fecha Vencimiento", "Valor", "Saldo", "Edad Cart.", "Estado", "Factura Interna", "cuotas"
        };
        String colName[] = {
            factura + "2", "cliente", "nombre", "fechaFactura", "fechaVencimiento", "valor", "saldo", "edadCartera", "estadoVencimiento", "factura", "cuotas"
        };
        String origen = " conCxcSepare";
        String sql = "select " + factura + " AS " + factura + "2, cliente, nombre, fechaFactura, fechaVencimiento, valor, saldo, edadCartera, estadoVencimiento,factura,terminal,cuotas from conCxcSepare ORDER BY orden";

        Object dtDatos[][] = GetTabla(colName, origen, (new StringBuilder()).append(sql).toString());
        DefaultTableModel datos = new DefaultTableModel(dtDatos, columNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        return datos;
    }

//    public DefaultTableModel getRegistrosDomicilios(String factura) {
//        String columNames[] = {
//            "Factura", "Doc.Cliente", "Nom.Cliente", "Fecha Factura", "Fecha Venc.", "Valor", "Saldo", "Edad", "Estado", "Factura Interna",
//            "cuotas", "Turno", "Domiciliario", "Sel"
//        };
//        String colName[] = {
//            factura + "2", "cliente", "nombre", "fechaFactura", "fechaVencimiento", "valor", "saldo", "edadCartera", "estadoVencimiento", "factura",
//            "cuotas", "turno", "vendedor",};
//        String origen = " conDomicilio";
//        String sql = "select " + factura + " AS " + factura + "2, cliente, nombre, fechaFactura, fechaVencimiento, valor, saldo, edadCartera, "
//                + "estadoVencimiento,factura,terminal,cuotas, turno, vendedor from conDomicilio ORDER BY orden";
//
//        Object dtDatos[][] = GetTabla(colName, origen, (new StringBuilder()).append(sql).toString());
//        DefaultTableModel datos = new DefaultTableModel(dtDatos, columNames) {
//            public boolean isCellEditable(int row, int column) {
//                return false;
//            }
//        };
//        return datos;
//    }
    public DefaultTableModel getRegistrosDomicilios(String factura) {
        String columNames[] = {
            "Factura", "Doc.Cliente", "Nom.Cliente", "Fecha Factura", "Fecha Venc.", "Valor", "Saldo", "Edad", "Estado",
            "Factura Interna", "cuotas", "Turno", "Domiciliario", "Sel"
        };
        String colName[] = {
            factura + "2", "cliente", "nombre", "fechaFactura", "fechaVencimiento", "valor", "saldo", "edadCartera", "estadoVencimiento", "factura",
            "cuotas", "turno", "vendedor", "Sel"};
        String origen = " conDomicilio";

        Object dtDatos2[][] = GetTabla(colName, origen, (new StringBuilder()).append("select facturaTerm AS " + factura + "2, cliente, nombre, fechaFactura, "
                + "fechaVencimiento, valor, saldo, edadCartera, estadoVencimiento, factura, cuotas, turno, vendedor, false as sel"
                + " from conDomicilio ORDER BY orden").toString(), new Integer[]{13});
        DefaultTableModel datos = new DefaultTableModel(dtDatos2, columNames) {
            public boolean isCellEditable(int row, int column) {
                if (column == 13) {
                    return true;
                }
                return false;
            }
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        return datos;
    }

    public Object[][] getRegistrosCuentaCobro1(String factura) {
        String colName[] = {
            "producto", "descripcion", "lista", "cantidad", "subtotal", "porcDescuento", "descuento", "porcIva", "iva", "total", "ubicacion", "referencia", "estado", "plu", "cant2"};
        String origen = " cuentaCobro";
        Object dtDatos[][] = getDatos(colName, origen, "select producto, descripcion, lista, cantidad, subtotal, porcDescuento, descuento, porcIva, iva, total, ubicacion,"
                + " referencia,estado, plu, cant2 from cuentaCobro where idFactura = '" + factura + "'", " where idFactura = '" + factura + "'");

        return dtDatos;
    }

    public Object[][] getRegistrosPlanSepare(String factura) {
        String colName[] = {
            "producto", "descripcion", "lista", "cantidad", "subtotal", "porcDescuento", "descuento", "porcIva", "iva", "total", "ubicacion",
            "referencia", "estado", "plu", "cant2", "imei", "idProd"};
        String origen = " planSepare";
        Object dtDatos[][] = getDatos(colName, origen, "select producto, descripcion, lista, cantidad, subtotal, porcDescuento, descuento, porcIva, "
                + "iva, total, ubicacion, referencia,estado, plu, cant2, imei, idProd from planSepare where idFactura = '" + factura + "' ",
                " where idFactura = '" + factura + "'");
        return dtDatos;
    }

    public void cambiarEstadoFactura(String estado, String id) {
        String instruccion_sql = "update bdFactura set estado2=? WHERE factura ='" + id + "' ;";
        Actualizar_Registro(new Object[]{"", estado}, null, instruccion_sql);
    }

    public void cambiarEstadoSepare(String estado, String id) {
        String instruccion_sql = "update bdPlanSepare set estado2=? WHERE idFactura ='" + id + "' ;";
        Actualizar_Registro(new Object[]{"", estado}, null, instruccion_sql);
    }

    //PEDIDOS
    public DefaultTableModel getRegistrosPedidos(String condicion) {
        String columNames[] = {
            "Pedido", "Fecha", "Cliente", "Total", "Seleccionar"
        };
        String colName[] = {
            "id", "fechaFactura", "cliente", "total", "sel"
        };
        String origen = " bdCaja WHERE id like 'PEDIDO%' AND estado = 'PENDIENTE'" + condicion;
        Object dtDatos[][] = GetTabla(colName, origen, (new StringBuilder()).append("select id, fechaFactura, cliente, total, false as sel from ").append(origen).toString(), new Integer[]{4});
        DefaultTableModel datos = new DefaultTableModel(dtDatos, columNames) {
            public boolean isCellEditable(int row, int column) {
                if (column == 4) {
                    return true;
                }
                return false;
            }

            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        return datos;
    }

    public boolean agregarReportePedido(Object[] vector) {
        boolean ok = false;
        String instruccion_sql = "insert into facturaReportePedido (Id, Fecha, Pedidos, Consulta) values (?,?,?,?);";
        ok = Agregar_Registro(vector, null, instruccion_sql);
        return ok;
    }

    public void cambiarEstadoPedido(String estado, String id) {
        String instruccion_sql = "update bdPedido set estado2=? WHERE idFactura ='" + id + "' ;";
        Actualizar_Registro(new Object[]{"", estado}, null, instruccion_sql);
    }

    public DefaultTableModel getReporteFacturaPedidos() {
        String columNames[] = {
            "Id", "Fecha", "Pedidos", "Consulta"
        };
        String colName[] = {
            "Id", "Fecha", "Pedidos", "Consulta"
        };
        String origen = " facturaReportePedido ";
        Object dtDatos[][] = GetTabla(colName, origen, (new StringBuilder()).append("select Id, Fecha, Pedidos, Consulta from ").append(origen).toString());
        DefaultTableModel datos = new DefaultTableModel(dtDatos, columNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        return datos;
    }

    //GUARDAR TERCEROS Y PRODUCTOS REFERIDOS
    public boolean agregarTercerosReferidos(Object[] referidos) {
        boolean ok = false;
        String instruccion_sql = "insert into bdReferidosTerceros(codigo, tercero, referido, fecha, usuario)"
                + " values(?,?,?,?,?);";
        ok = Agregar_Registro(referidos, null, instruccion_sql);
        return ok;
    }

    //BUSCAR LOS ABONOS REALIZADOS EN ALGUN CREDITO
    public DefaultTableModel getRegistrosAbonosRealizados(String contrato) {
        boolean opc = false;
        String columNames[] = {
            "Id", "Abonos"
        };
        String colName[] = {
            "id", "documentoAbono"
        };
        String origen = " bdCuota";
        Object dtDatos[][] = getDatos(colName, origen, "select id, documentoAbono from bdCuota where contrato='" + contrato + "' AND documentoAbono <> ''", " where contrato='" + contrato + "' AND documentoAbono <> ''");
        Vector datoss = new Vector();
        for (int i = 0; i < dtDatos.length; i++) {
            String aux[] = dtDatos[i][1].toString().split(";");
            for (int j = 0; j < aux.length; j++) {
                for (int k = 0; k < datoss.size(); k++) {
                    if (((Object[]) datoss.get(k))[1].equals(aux[j])) {
                        opc = true;
                        break;
                    }
                }
                if (!opc) {
                    datoss.add(new Object[]{dtDatos[i][0], aux[j]});
                }
                opc = false;
            }
        }
        DefaultTableModel datos = new DefaultTableModel(datoss.size(), 2) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        datos.setColumnIdentifiers(columNames);
        int cant = datos.getRowCount();
        for (int i = 0; i < cant; i++) {
            datos.removeRow(0);
        }
        for (int i = 0; i < datoss.size(); i++) {
            Object[] aux = (Object[]) datoss.get(i);
            ndCxc nodoCxc = getDatosCxcRecibo(aux[1].toString());
            if (!nodoCxc.getEstado().equals("CANCELADA")) {
                datos.addRow(aux);
            }
        }
        return datos;
    }

    public boolean agregarProductosReferidos(Object[] productos) {
        boolean ok = false;
        String instruccion_sql = "insert into bdReferidosProductos(codigo, producto, cantidad)"
                + " values(?,?,?);";
        ok = Agregar_Registro(productos, null, instruccion_sql);
        return ok;
    }

    public Object[][] getVendedores() {
        String colName[] = {"nombre"};
        String origen = " bdTerceros";
        Object dtDatos[][] = getDatos(colName, origen, "select nombre FROM bdTerceros where Cargo = 'Vendedor' AND activo=" + false + " ;", " where Cargo = 'Vendedor' AND activo=" + false + " ");
        return dtDatos;
    }

    public Object[][] getVendedores1() {
        String colName[] = {"nombre", "usuarioAsociado"};
        String origen = " bdTerceros";
        Object dtDatos[][] = getDatos(colName, origen, "select nombre, usuarioAsociado FROM bdTerceros where Cargo = 'Vendedor' AND activo=" + false + " ;", " where Cargo = 'Vendedor' AND activo=" + false + " ");
        return dtDatos;
    }

    public Object[][] getDomiciliarios() {
        String colName[] = {"nombre"};
        String origen = " bdTerceros";
        Object dtDatos[][] = getDatos(colName, origen, "select nombre FROM bdTerceros where Cargo = 'Domiciliario' AND activo=" + false + " ;", " where Cargo = 'Domiciliario' AND activo=" + false + " ");
        return dtDatos;
    }

    public Object[][] getTecnicos() {
        String colName[] = {"nombre"};
        String origen = " bdTerceros";
        Object dtDatos[][] = getDatos(colName, origen, "select nombre FROM bdTerceros where Cargo = 'Mecanico' AND activo=" + false + " ;", " where Cargo = 'Mecanico' AND activo=" + false + " ");
        return dtDatos;
    }

    public Object[][] getMecanicos() {
        String colName[] = {"nombre"};
        String origen = " bdTerceros";
        Object dtDatos[][] = getDatos(colName, origen, "select nombre FROM bdTerceros where Cargo = 'Mecanico' AND activo=" + false + " ;", " where Cargo = 'Mecanico' AND activo=" + false + " ");
        return dtDatos;
    }

    public String getNombreEmpleado(String id) {
        String colName[] = {"nombre"};
        String origen = " bdTerceros";
        Object dtDatos[][] = getDatos(colName, origen, "select nombre FROM bdTerceros where idSistema = '" + id + "';", " where idSistema ='" + id + "' ");
        return dtDatos[0][0].toString();
    }

    public String getVersion() {
        String colName[] = {"version"};
        String origen = " configuracion";
        Object dtDatos[][] = getDatos(colName, origen, "select version FROM configuracion ", "");
        return dtDatos[0][0].toString();
    }

    public int contadorFacturasAnuladas(String ini, String usuario) {
        String colName[] = {"contAnuladas"};
        String origen = " contadorFacturasAnuladas";
        Object dtDatos[][] = getDatos(colName, origen, "select contAnuladas from " + origen + " where " + ini + " AND usuarioAnula = '" + usuario + "'",
                " where " + ini + " AND usuarioAnula = '" + usuario + "'");

        return Integer.parseInt(dtDatos[0][0].toString());
    }

    public boolean modificarPlanSepareFechaAnulacion(String fechaAnulacion, String usuarioAnula, String id, String nota, boolean anula, String anulacion) {
        boolean ok = false;
        String instruccion_sql = "update bdPlanSepare set fechaAnulacion=?, usuarioAnula=?, notaAnulacion=?, anulada=?, anula=? where factura ='" + id + "' ;";
        ok = Actualizar_Registro(new Object[]{"", fechaAnulacion, usuarioAnula, nota, anula, anulacion}, null, instruccion_sql);
        return ok;
    }

    public boolean modificarPlantillaFechaAnulacion(String fechaAnulacion, String usuarioAnula, String id, String nota, boolean anula, String anulacion) {
        boolean ok = false;
        String instruccion_sql = "update bdCuentaCobro set fechaAnulacion=?, usuarioAnula=?, notaAnulacion=?, anulada=?, anula=? where idFactura ='" + id + "' ;";
        ok = Actualizar_Registro(new Object[]{"", fechaAnulacion, usuarioAnula, nota, anula, anulacion}, null, instruccion_sql);
        return ok;
    }

    public boolean modificarFacturaFechaAnulacion(String fechaAnulacion, String usuarioAnula, String id, String nota, boolean anula, String anulacion) {
        boolean ok = false;
        String instruccion_sql = "update bdFactura set fechaAnulacion=?, usuarioAnula=?, notaAnulacion=?, anulada=?, anula=? where factura ='" + id + "' ;";
        ok = Actualizar_Registro(new Object[]{"", fechaAnulacion, usuarioAnula, nota, anula, anulacion}, null, instruccion_sql);
        return ok;
    }

    public boolean modificarPedidoFechaAnulacion(String fechaAnulacion, String usuarioAnula, String id, String nota, boolean anula, String anulacion) {
        boolean ok = false;
        String instruccion_sql = "update bdPedido set fechaAnulacion=?, usuarioAnula=?, notaAnulacion=?, anulada=?, anula=?, estadoGeneral=? where factura ='" + id + "' ;";
        ok = Actualizar_Registro(new Object[]{"", fechaAnulacion, usuarioAnula, nota, anula, anulacion, "ANULADA"}, null, instruccion_sql);
        return ok;
    }
    
    public boolean modificarCotizacionFechaAnulacion(String fechaAnulacion, String usuarioAnula, String id, String nota, boolean anula, String anulacion) {
        boolean ok = false;
        String instruccion_sql = "update bdCotizacion set fechaAnulacion=?, usuarioAnula=?, notaAnulacion=?, anulada=?, anula=?, estadoGeneral=? where factura ='" + id + "' ;";
        ok = Actualizar_Registro(new Object[]{"", fechaAnulacion, usuarioAnula, nota, anula, anulacion, "ANULADA"}, null, instruccion_sql);
        return ok;
    }

    public boolean modificarOServicioFechaAnulacion(String fechaAnulacion, String usuarioAnula, String id, String nota,
            boolean anula, String anulacion) {
        boolean ok = false;
        String instruccion_sql = "update bdOServicio1 set fechaAnulacion=?, usuarioAnula=?, notaAnulacion=?, anulada=?, anula=?, estadoGeneral=? "
                + "where factura = '" + id + "' ;";
        ok = Actualizar_Registro(new Object[]{"", fechaAnulacion, usuarioAnula, nota, anula, anulacion, "ANULADA"}, null, instruccion_sql);
        return ok;
    }

    public boolean anularFacturaVerificadorFacturas(String id) {
        boolean ok = false;
        String instruccion_sql = "update bdVerificadorFacturas set anulada=? where factura = '" + id + "' ;";
        ok = Actualizar_Registro(new Object[]{"", true}, null, instruccion_sql);
        return ok;
    }

    public boolean modificarFechaUltimoPago(String id, String fecha, String cant) {
        boolean ok = false;
        String instruccion_sql = "update bdCuentaCobro set fechaUltimoPago=?, cantFacturados=? where idFactura = '" + id + "' ;";
        ok = Actualizar_Registro(new Object[]{"", fecha, cant}, null, instruccion_sql);
        return ok;
    }

    public boolean modificarFechaUltimoPago(String id, String cant) {
        boolean ok = false;
        String instruccion_sql = "update bdCuentaCobro set cantFacturados=? where idFactura = '" + id + "' ;";
        ok = Actualizar_Registro(new Object[]{"", cant}, null, instruccion_sql);
        return ok;
    }

    //CONSULTA QUE LLENA LA TABLA DE ALERTA CUMPLEAÑOS
    public DefaultTableModel getRegistrosAlertasCumpleanos(String fecha) {

        Object[] fecha2 = fecha.split("-");
        String fechaCumpleanos = fecha2[2] + "/" + fecha2[1] + "/";

        String columNames[] = {
            "id", "Nombre", "Fecha Nac", "Teléfono", "Celular", "Dirección", "Se Gestionó"
        };
        String colName[] = {
            "id", "nombre", "fecha", "telefono", "celular", "direccion", "gestiono"
        };
        String origen = " bdTerceros where fecha LIKE '" + fechaCumpleanos + "%' ";
        Object dtDatos[][] = GetTabla(colName, origen, "select id, nombre, fecha, telefono, celular, direccion, false as gestiono from bdTerceros where fecha LIKE '" + fechaCumpleanos + "%' ", new Integer[]{6});

        DefaultTableModel datos = new DefaultTableModel(dtDatos, columNames) {
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, true};

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        return datos;
    }

    public DefaultTableModel getRegistrosProductos2(String condicion) {
        String columNames[] = {
            "Codigo", "Descripción", "Costo", "L1", "U1", "R1", "L2", "U2", "R2", "L3", "U3", "R3", "L4", "U4", "R4", "Seleccionar"
        };
        String colName[] = {
            "Codigo", "Descripcion", "costo", "L1", "U1", "R1", "L2", "U2", "R2", "L3", "U3", "R3", "L4", "U4", "R4", "seleccionar"
        };
        String origen = "tablaModificarProductos " + condicion;
        Object dtDatos[][] = GetTabla(colName, origen, (new StringBuilder()).append("select Codigo,Descripcion,costo,L1,U1,R1,L2,U2,R2,L3,U3,R3,L4,U4,R4,seleccionar from tablaModificarProductos ").append(condicion).toString());
        Object dtDatos2[][] = new Object[dtDatos.length][16];

        for (int i = 0; i < dtDatos.length; i++) {
            for (int j = 0; j < 15; j++) {
                dtDatos2[i][j] = dtDatos[i][j];
            }
            dtDatos2[i][15] = true;
        }
        DefaultTableModel datos = new DefaultTableModel(dtDatos2, columNames) {
            public boolean isCellEditable(int row, int column) {
                if (column == 15) {
                    return true;
                }
                return false;
            }
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        return datos;
    }


//    public boolean modificarEstadoProductosOrden(String id, String estado, String producto) {
//        boolean ok = false;
//        String instruccion_sql = "update bdOServicio1 set estadoGeneral=? where idFactura='" + id + "' and producto='" + producto + "';";
//        ok = Actualizar_Registro(new Object[]{"", estado, producto}, null, instruccion_sql);
//        return ok;
//    }
//    
//    public boolean modificarEstadoProductosCotizacion(String id, String estado, String producto) {
//        boolean ok = false;
//        String instruccion_sql = "update bdOServicio1 set estadoGeneral=? where idFactura='" + id + "' and producto='" + producto + "';";
//        ok = Actualizar_Registro(new Object[]{"", estado, producto}, null, instruccion_sql);
//        return ok;
//    }
    public boolean modificarEstadosProcesos(String id, String estado, String producto, String tabla) {
        boolean ok = false;
        String instruccion_sql = "update " + tabla + " set estadoGeneral=? where idFactura='" + id + "' and producto ='" + producto + "';";
        ok = Actualizar_Registro(new Object[]{"", estado}, null, instruccion_sql);
        return ok;
    }
    
    public Object[][] getTiposVehiculos() {
        String colName[] = {
            "nombre"
        };
        String origen = " bdTipoVehiculo";
        Object dtDatos[][] = getDatos(colName, origen, "select nombre FROM bdTipoVehiculo", " ");

        return dtDatos;
    }

    public boolean agregarFechaBloqueo(String fecha, String nombre, String nota, String idTercero, String horas) {
        boolean ok = false;
        String instruccion_sql = "insert into bdBloqueoAgenda(fecha, nombre, nota, idTercero, horas) values (?,?,?,?,?);";
        ok = Agregar_Registro(new Object[]{fecha, nombre, nota, idTercero, horas}, null, instruccion_sql);
        return ok;
    }

    public boolean agregarTarjetas(String nombre, String comision) {
        boolean ok = false;
        String instruccion_sql = "insert into bdTarjetas(franquisia, comision) values (?,?);";
        ok = Agregar_Registro(new Object[]{nombre, comision}, null, instruccion_sql);
        return ok;
    }

    public boolean agregarDetalleOrdenServicioLaboratorio(String id, String cup, String nombre, BigDecimal precio) {
        boolean ok = false;
        String instruccion_sql = "insert into bdDetalleOrdenServicioLaboratorio(IdConsultaLaboratorio, cup, nombre, precio) values (?,?,?,?);";
        ok = Agregar_Registro(new Object[]{id, cup, nombre}, new Object[]{precio}, instruccion_sql);
        return ok;
    }

    public boolean agregarCocaina(String id, String Identificacion, String numeroOrden, String Remitido, String fecha,
            String Resultados, String tecnica, String observaciones) {
        boolean ok = false;
        String instruccion_sql = "insert into bdCocaina(IdCocaina, cedula, numeroOrden, remitido, fecha, resultados, tecnica, observaciones)"
                + " values (?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(new Object[]{id, Identificacion, numeroOrden, Remitido, fecha,
            Resultados, tecnica, observaciones}, null, instruccion_sql);
        return ok;
    }

    public boolean agregarHemoclasificacion(String id, String Identificacion, String numeroOrden, String Remitido, String fecha,
            String Resultados, String tecnica, String observaciones, String grupo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdHemoclasificacion(IdHemoclasificacion, cedula, numeroOrden, remitido, fecha, resultados, tecnica, observaciones, grupo)"
                + " values (?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(new Object[]{id, Identificacion, numeroOrden, Remitido, fecha,
            Resultados, tecnica, observaciones, grupo}, null, instruccion_sql);
        return ok;
    }

    public boolean agregarCannabinoides(String id, String Identificacion, String numeroOrden, String Remitido, String fecha,
            String Resultados, String tecnica, String observaciones) {
        boolean ok = false;
        String instruccion_sql = "insert into bdCannabinoides(IdCannabinoides, cedula, numeroOrden, remitido, fecha, resultados, tecnica, observaciones)"
                + " values (?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(new Object[]{id, Identificacion, numeroOrden, Remitido, fecha,
            Resultados, tecnica, observaciones}, null, instruccion_sql);
        return ok;
    }

    public boolean agregarAntiestreptolisina(String id, String Identificacion, String numeroOrden, String Remitido, String fecha,
            String Resultados, String tecnica, String observaciones) {
        boolean ok = false;
        String instruccion_sql = "insert into bdAntiestreptolisina(IdAntiestreptolisina, cedula, numeroOrden, remitido, fecha, resultados, tecnica, observaciones)"
                + " values (?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(new Object[]{id, Identificacion, numeroOrden, Remitido, fecha,
            Resultados, tecnica, observaciones}, null, instruccion_sql);
        return ok;
    }

    public boolean agregarHemograma(String id, String Identificacion, String numeroOrden, String Remitido, String fecha, String Resultados, String tecnica, String observaciones,
            String globujosRojos, String hemoglobina, String hematocrito, String sedimentacion, String anisocitosis, String macrocitosis, String microcitosis, String hipocromia,
            String policromatofilia, String poikilocitosis, String recuento, String morfologia, String leucocitos, String neutrofilos, String bandas,
            String linfocitos, String monocitos, String eosinofilos, String basofilos, String blastos) {
        boolean ok = false;
        String instruccion_sql = "insert into bdHemograma(IdHemograma, cedula, numeroOrden, remitido, fecha, resultados, tecnica, observaciones, "
                + "globulosRojos, hemoglobina, hematocrito, sedimentacion, anisocitosis, macrocitosis, microcitosis, hipocromia, policromatofilia, "
                + "poikilocitosis, recuento, morfologia, leucocitos, neutrofilos, bandas, linfocitos, monocitos, eosinofilos, basofilos, blastos)"
                + " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(new Object[]{id, Identificacion, numeroOrden, Remitido, fecha, Resultados, tecnica, observaciones, globujosRojos, hemoglobina, hematocrito,
            sedimentacion, anisocitosis, macrocitosis, microcitosis, hipocromia, policromatofilia, poikilocitosis, recuento, morfologia, leucocitos, neutrofilos, bandas,
            linfocitos, monocitos, eosinofilos, basofilos, blastos}, null, instruccion_sql);
        return ok;
    }

    public boolean agregarCoprologico(String id, String Identificacion, String numeroOrden, String Remitido, String fecha, String Resultados, String tecnica, String observaciones,
            String consistencia, String moco, String floraBacteriana, String almidones, String fibrasMusculares, String gotasDeGasa, String celulasVegetales, String esporasDeHongo,
            String leucocitos, String eritrocitos, String histoliticaTrofozoitos, String histoliticaQuistes, String coliTrofozoitos, String coliQuistes, String nanaTrofozoitos,
            String nanaQuistes,
            String lodamoebaTrofozoitos, String lodamoebaQuistes, String giardiaTrofozoitos, String giardiaQuistes, String trichomonaTrofozoitos, String trichomonaQuistes,
            String chilomastixTrofozoitos, String chilomastixQuistes, String balantidiumTrofozoitos, String balantidiumQuistes, String ascarisHuevos, String ascarisInfestacion,
            String tricocefalosHuevos, String tricocefalosInfestacion, String uncinariaHuevos, String uncinariaInfestacion, String teniaHuevos, String teniaInfestacion,
            String strongiloidesHuevos, String strongiloidesInfestacion, String larvasHuevos, String larvasInfestacion, String oxiurosHuevos, String oxiurosInfestacion) {
        boolean ok = false;
        String instruccion_sql = "insert into bdCoprologico(IdCoprologico, cedula, numeroOrden, remitido, fecha, resultados, tecnica, observaciones, consistencia, moco, floraBacteriana, almidones, "
                + "fibrasMusculares, gotasDeGasa, celulasVegetales, esporasDeHongo, leucocitos, eritrocitos, histoliticaTrofozoitos, histoliticaQuistes, coliTrofozoitos, coliQuistes,"
                + " nanaTrofozoitos, nanaQuistes, lodamoebaTrofozoitos, lodamoebaQuistes, giardiaTrofozoitos, giardiaQuistes, trichomonaTrofozoitos, trichomonaQuistes, "
                + "chilomastixTrofozoitos, chilomastixQuistes, balantidiumTrofozoitos, balantidiumQuistes, ascarisHuevos, ascarisInfestacion, tricocefalosHuevos, "
                + "tricocefalosInfestacion, uncinariaHuevos, uncinariaInfestacion, teniaHuevos, teniaInfestacion, strongiloidesHuevos, strongiloidesInfestacion, larvasHuevos, "
                + "larvasInfestacion, oxiurosHuevos, oxiurosInfestacion)"
                + " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(new Object[]{id, Identificacion, numeroOrden, Remitido, fecha, Resultados, tecnica, observaciones, consistencia, moco, floraBacteriana, almidones, fibrasMusculares,
            gotasDeGasa, celulasVegetales, esporasDeHongo, leucocitos, eritrocitos, histoliticaTrofozoitos, histoliticaQuistes, coliTrofozoitos, coliQuistes, nanaTrofozoitos,
            nanaQuistes, lodamoebaTrofozoitos, lodamoebaQuistes, giardiaTrofozoitos, giardiaQuistes, trichomonaTrofozoitos, trichomonaQuistes, chilomastixTrofozoitos,
            chilomastixQuistes, balantidiumTrofozoitos, balantidiumQuistes, ascarisHuevos, ascarisInfestacion, tricocefalosHuevos, tricocefalosInfestacion, uncinariaHuevos,
            uncinariaInfestacion, teniaHuevos, teniaInfestacion, strongiloidesHuevos, strongiloidesInfestacion, larvasHuevos, larvasInfestacion, oxiurosHuevos, oxiurosInfestacion}, null, instruccion_sql);
        return ok;
    }

    public boolean agregarCoprograma(String id, String Identificacion, String numeroOrden, String Remitido, String fecha, String Resultados, String tecnica, String observaciones, String consistencia, String moco, String floraBacteriana, String almidones, String fibrasMusculares, String gotasDeGasa, String celulasVegetales, String esporasDeHongo, String leucocitos,
            String eritrocitos, String histoliticaTrofozoitos, String histoliticaQuistes, String coliTrofozoitos, String coliQuistes, String nanaTrofozoitos, String nanaQuistes,
            String lodamoebaTrofozoitos, String lodamoebaQuistes, String giardiaTrofozoitos, String giardiaQuistes, String trichomonaTrofozoitos, String trichomonaQuistes,
            String chilomastixTrofozoitos, String chilomastixQuistes, String balantidiumTrofozoitos, String balantidiumQuistes, String ascarisHuevos, String ascarisInfestacion,
            String tricocefalosHuevos, String tricocefalosInfestacion, String uncinariaHuevos, String uncinariaInfestacion, String teniaHuevos, String teniaInfestacion,
            String strongiloidesHuevos, String strongiloidesInfestacion, String larvasHuevos, String larvasInfestacion, String oxiurosHuevos, String oxiurosInfestacion,
            String sangreOcultaHuevos, String sangreOcultaInfestacion, String phHuevos, String phInfestacion, String azucaresHuevos, String azucaresInfestacion, String gramHuevos,
            String gramInfestacion, String wrightHuevos, String wrightInfestacion) {
        boolean ok = false;
        String instruccion_sql = "insert into bdCoprograma(IdCoprograma, cedula, numeroOrden, remitido, fecha, resultados, tecnica, observaciones, consistencia, moco, floraBacteriana, almidones, "
                + "fibrasMusculares, gotasDeGasa, celulasVegetales, esporasDeHongo, leucocitos, eritrocitos, histoliticaTrofozoitos, histoliticaQuistes, coliTrofozoitos, coliQuistes,"
                + " nanaTrofozoitos, nanaQuistes, lodamoebaTrofozoitos, lodamoebaQuistes, giardiaTrofozoitos, giardiaQuistes, trichomonaTrofozoitos, trichomonaQuistes, "
                + "chilomastixTrofozoitos, chilomastixQuistes, balantidiumTrofozoitos, balantidiumQuistes, ascarisHuevos, ascarisInfestacion, tricocefalosHuevos, "
                + "tricocefalosInfestacion, uncinariaHuevos, uncinariaInfestacion, teniaHuevos, teniaInfestacion, strongiloidesHuevos, strongiloidesInfestacion, larvasHuevos, "
                + "larvasInfestacion, oxiurosHuevos, oxiurosInfestacion, sangreOcultaHuevos, sangreOcultaInfestacion, phHuevos, phInfestacion, "
                + "azucaresHuevos, azucaresInfestacion, gramHuevos, gramInfestacion, wrightHuevos, wrightInfestacion)"
                + " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(new Object[]{id, Identificacion, numeroOrden, Remitido, fecha, Resultados, tecnica, observaciones, consistencia, moco, floraBacteriana, almidones, fibrasMusculares,
            gotasDeGasa, celulasVegetales, esporasDeHongo, leucocitos, eritrocitos, histoliticaTrofozoitos, histoliticaQuistes, coliTrofozoitos, coliQuistes, nanaTrofozoitos,
            nanaQuistes, lodamoebaTrofozoitos, lodamoebaQuistes, giardiaTrofozoitos, giardiaQuistes, trichomonaTrofozoitos, trichomonaQuistes, chilomastixTrofozoitos,
            chilomastixQuistes, balantidiumTrofozoitos, balantidiumQuistes, ascarisHuevos, ascarisInfestacion, tricocefalosHuevos, tricocefalosInfestacion, uncinariaHuevos,
            uncinariaInfestacion, teniaHuevos, teniaInfestacion, strongiloidesHuevos, strongiloidesInfestacion, larvasHuevos, larvasInfestacion, oxiurosHuevos, oxiurosInfestacion,
            sangreOcultaHuevos, sangreOcultaInfestacion, phHuevos, phInfestacion, azucaresHuevos, azucaresInfestacion, gramHuevos, gramInfestacion, wrightHuevos, wrightInfestacion}, null, instruccion_sql);
        return ok;
    }

    public boolean agregarAcidoUrico(String id, String Identificacion, String numeroOrden, String Remitido, String fecha,
            String Resultados, String tecnica, String observaciones) {
        boolean ok = false;
        String instruccion_sql = "insert into bdAcidoUrico(IdAcido, cedula, numeroOrden, remitido, fecha, resultados, tecnica, observaciones)"
                + " values (?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(new Object[]{id, Identificacion, numeroOrden, Remitido, fecha,
            Resultados, tecnica, observaciones}, null, instruccion_sql);
        return ok;
    }

    public boolean agregarCitoquimico(String id, String Identificacion, String numeroOrden, String Remitido, String fecha, String Resultados,
            String tecnica, String observaciones, String color, String aspecto, String densidad, String ph, String proteinas, String glucosa,
            String cuerposCetonicos, String bilirrubinas, String sangreOculta, String urobilinogeno, String nitritos, String leucocitos,
            String epitelialesAltas, String epitelialesBajas, String leucocitos1, String eritrocitos, String filamentoMucosa, String filamentoMucosa1,
            String bacterias, String bacterias1, String acumulosLeucocitarios, String acumulosLeucocitarios1, String cristales, String cilindros, String otros
    ) {
        boolean ok = false;
        String instruccion_sql = "insert into bdCitoquimico(IdCitoquimico, cedula, numeroOrden, remitido, fecha, resultados, tecnica, observaciones, color,"
                + " aspecto, densidad, ph, proteinas, glucosa, cuerposCetonicos, bilirrubinas, sangreOculta, urobilinogeno, nitritos, leucocitos, "
                + "epitelialesAltas, epitelialesBajas, leucocitos1, eritrocitos, filamentoMucosa, filamentoMucosa1, bacterias, bacterias1, "
                + "acumulosLeucocitarios, acumulosLeucocitarios1, cristales, cilindros, otros)"
                + " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(new Object[]{id, Identificacion, numeroOrden, Remitido, fecha,
            Resultados, tecnica, observaciones, color, aspecto, densidad, ph, proteinas, glucosa, cuerposCetonicos, bilirrubinas, sangreOculta, urobilinogeno, nitritos, leucocitos, epitelialesAltas, epitelialesBajas, leucocitos1, eritrocitos, filamentoMucosa, filamentoMucosa1, bacterias, bacterias1, acumulosLeucocitarios, acumulosLeucocitarios1, cristales, cilindros, otros
        }, null, instruccion_sql);
        return ok;
    }

    public boolean agregarPerfil(String id, String Identificacion, String numeroOrden, String Remitido, String fecha, String Resultados,
            String tecnica, String observaciones, String Resultados1, String Resultados2, String Resultados3, String Resultados4, String Resultados5, String Resultados6) {
        boolean ok = false;
        String instruccion_sql = "insert into bdPerfil(IdPerfil, cedula, numeroOrden, remitido, fecha, resultados, tecnica, observaciones, resultado1, resultado2, "
                + "resultado3, resultado4, resultado5, resultado6)"
                + " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(new Object[]{id, Identificacion, numeroOrden, Remitido, fecha,
            Resultados, tecnica, observaciones, Resultados1, Resultados2, Resultados3, Resultados4, Resultados5, Resultados6
        }, null, instruccion_sql);
        return ok;
    }

    public boolean agregarColesterol(String id, String Identificacion, String numeroOrden, String Remitido, String fecha,
            String Resultados, String tecnica, String observaciones, String resultados1, String resultados2, String resultados3) {
        boolean ok = false;
        String instruccion_sql = "insert into bdColesterol(IdColesterol, cedula, numeroOrden, remitido, fecha, resultados, tecnica, observaciones, "
                + "resultados1, resultados2, resultados3)"
                + " values (?,?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(new Object[]{id, Identificacion, numeroOrden, Remitido, fecha,
            Resultados, tecnica, observaciones, resultados1, resultados2, resultados3}, null, instruccion_sql);
        return ok;
    }

    public boolean agregarGlicemia(String id, String Identificacion, String numeroOrden, String Remitido, String fecha,
            String Resultados, String tecnica, String observaciones) {
        boolean ok = false;
        String instruccion_sql = "insert into bdGlicemia(IdGlicemia, cedula, numeroOrden, remitido, fecha, resultados, tecnica, observaciones)"
                + " values (?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(new Object[]{id, Identificacion, numeroOrden, Remitido, fecha, Resultados, tecnica, observaciones}, null, instruccion_sql);
        return ok;
    }

    public boolean agregarDirecto(String id, String Identificacion, String numeroOrden, String Remitido, String fecha,
            String tipo, String tecnica, String observaciones, String resultados, String resultados1, String leucocitaria, String bacterias) {
        boolean ok = false;
        String instruccion_sql = "insert into bdDirecto(IdDirecto, cedula, numeroOrden, remitido, fecha, tipo, tecnica, observaciones, "
                + "resultados, resultados1, leucocitaria, bacterias)"
                + " values (?,?,?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(new Object[]{id, Identificacion, numeroOrden, Remitido, fecha,
            tipo, tecnica, observaciones, resultados, resultados1, leucocitaria, bacterias}, null, instruccion_sql);
        return ok;
    }

    public boolean agregarTiempo(String id, String Identificacion, String numeroOrden, String Remitido, String fecha,
            String Resultados, String tecnica, String observaciones, String resultados1, String resultados2, String resultados3, String resultados4) {
        boolean ok = false;
        String instruccion_sql = "insert into bdTiempos(IdTiempo, cedula, numeroOrden, remitido, fecha, resultados, tecnica, observaciones, "
                + "resultados1, resultados2, resultados3, resultados4)"
                + " values (?,?,?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(new Object[]{id, Identificacion, numeroOrden, Remitido, fecha,
            Resultados, tecnica, observaciones, resultados1, resultados2, resultados3, resultados4}, null, instruccion_sql);
        return ok;
    }

    public boolean agregarCreatinina(String id, String Identificacion, String numeroOrden, String Remitido, String fecha,
            String Resultados, String tecnica, String observaciones, String resultados1, String resultados2) {
        boolean ok = false;
        String instruccion_sql = "insert into bdCreatinina(IdCreatinina, cedula, numeroOrden, remitido, fecha, resultados, tecnica, observaciones, "
                + "resultados1, resultados2)"
                + " values (?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(new Object[]{id, Identificacion, numeroOrden, Remitido, fecha,
            Resultados, tecnica, observaciones, resultados1, resultados2}, null, instruccion_sql);
        return ok;
    }

    public boolean agregarAcidos(String id, String Identificacion, String numeroOrden, String Remitido, String fecha,
            String Resultados, String tecnica, String observaciones, String resultados1, String resultado2) {
        boolean ok = false;
        String instruccion_sql = "insert into bdAcidos(IdAcidos, cedula, numeroOrden, remitido, fecha, resultados, tecnica, observaciones, "
                + "resultados1, resultados2)"
                + " values (?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(new Object[]{id, Identificacion, numeroOrden, Remitido, fecha,
            Resultados, tecnica, observaciones, resultados1, resultado2}, null, instruccion_sql);
        return ok;
    }

    public boolean agregarEmbarazo(String id, String Identificacion, String numeroOrden, String Remitido, String fecha,
            String Resultados, String tecnica, String observaciones, String resultados1, String resultado2, String resultado3) {
        boolean ok = false;
        String instruccion_sql = "insert into bdEmbarazo(IdEmbarazo, cedula, numeroOrden, remitido, fecha, resultados, tecnica, observaciones, "
                + "resultados1, resultados2, resultados3)"
                + " values (?,?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(new Object[]{id, Identificacion, numeroOrden, Remitido, fecha,
            Resultados, tecnica, observaciones, resultados1, resultado2, resultado3}, null, instruccion_sql);
        return ok;
    }

    public boolean agregarSerologia(String id, String Identificacion, String numeroOrden, String Remitido, String fecha,
            String Resultados, String tecnica, String observaciones, String resultados1) {
        boolean ok = false;
        String instruccion_sql = "insert into bdSerologia(IdSerologia, cedula, numeroOrden, remitido, fecha, resultados, tecnica, observaciones, resultados1)"
                + " values (?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(new Object[]{id, Identificacion, numeroOrden, Remitido, fecha,
            Resultados, tecnica, observaciones, resultados1}, null, instruccion_sql);
        return ok;
    }

    public boolean agregarKoh(String id, String Identificacion, String numeroOrden, String Remitido, String fecha,
            String Resultados, String tecnica, String observaciones, String resultados1, String resultados2) {
        boolean ok = false;
        String instruccion_sql = "insert into bdKoh(IdKoh, cedula, numeroOrden, remitido, fecha, resultados, tecnica, observaciones, resultados1, resultados2)"
                + " values (?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(new Object[]{id, Identificacion, numeroOrden, Remitido, fecha,
            Resultados, tecnica, observaciones, resultados1, resultados2}, null, instruccion_sql);
        return ok;
    }

    public boolean agregarProteina(String id, String Identificacion, String numeroOrden, String Remitido, String fecha,
            String Resultados, String tecnica, String observaciones) {
        boolean ok = false;
        String instruccion_sql = "insert into bdProteina(IdProteina, cedula, numeroOrden, remitido, fecha, resultados, tecnica, observaciones)"
                + " values (?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(new Object[]{id, Identificacion, numeroOrden, Remitido, fecha,
            Resultados, tecnica, observaciones}, null, instruccion_sql);
        return ok;
    }

    public boolean agregarSangreOculta(String id, String Identificacion, String numeroOrden, String Remitido, String fecha,
            String Resultados, String tecnica, String observaciones, String resultados1) {
        boolean ok = false;
        String instruccion_sql = "insert into bdSangreOculta(IdSangre, cedula, numeroOrden, remitido, fecha, resultados, tecnica, observaciones, "
                + "resultados1)"
                + " values (?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(new Object[]{id, Identificacion, numeroOrden, Remitido, fecha,
            Resultados, tecnica, observaciones, resultados1}, null, instruccion_sql);
        return ok;
    }

    public boolean agregarHemoglobina(String id, String Identificacion, String numeroOrden, String Remitido, String fecha,
            String Resultados, String tecnica, String observaciones) {
        boolean ok = false;
        String instruccion_sql = "insert into bdHemoglobina(IdHemoglobina, cedula, numeroOrden, remitido, fecha, resultados, tecnica, observaciones)"
                + " values (?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(new Object[]{id, Identificacion, numeroOrden, Remitido, fecha,
            Resultados, tecnica, observaciones}, null, instruccion_sql);
        return ok;
    }

    public boolean agregarArticulo(String nombre, String tipo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdArticulos (nombreArticulo, tipo, estado) values (?,?,?);";
        ok = Agregar_Registro(new Object[]{nombre, tipo, "ACTIVO"}, null, instruccion_sql);
        return ok;
    }

    public boolean agregarServicio(String nombre, String dias) {
        boolean ok = false;
        String instruccion_sql = "insert into bdListadoOrden (nombre, dias) values (?,?);";
        ok = Agregar_Registro(new Object[]{nombre, dias}, null, instruccion_sql);
        return ok;
    }

    public boolean agregarLogActualizarPrecios(Object[] datos) {
        boolean ok = false;
        String instruccion_sql = "insert into logActualizarPrecios(usuario, fecha, porcentaje, tipo,condicion) values (?,?,?,?,?);";
        ok = Agregar_Registro(datos, null, instruccion_sql);
        return ok;
    }

    public boolean agregarCargo(String nombre, Boolean tipo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdCargo(nombreCargo, inmodificable) values (?,?);";
        ok = Agregar_Registro(new Object[]{nombre, tipo}, null, instruccion_sql);
        return ok;
    }

    public boolean agregarProductosUnificados(Object[] datos) {
        boolean ok = false;
        String instruccion_sql = "insert into bdProductosUnificados(productoPadre, productoUnificado, usuario) values (?,?,?);";
        ok = Agregar_Registro(datos, null, instruccion_sql);
        return ok;
    }

    public boolean agregarRecogidaParcial(Object[] datos, Object[] valores) {
        boolean ok = false;
        String instruccion_sql = "insert into bdRecogidaParcial(terminal, usuario, fecha,activo,total) values (?,?,?,?,?);";
        ok = Agregar_Registro(datos, valores, instruccion_sql);
        return ok;
    }

    public boolean agregarBaseCuadreDeCaja(Object[] datos, Object[] valores) {
        boolean ok = false;
        String instruccion_sql = "insert into bdBaseCuadreCaja(terminal, usuario, fecha, activo, 50mil, 20mil, 10mil, 5mil, 2mil, 1mil, "
                + "1000pesos, 500pesos, 200pesos, 100pesos, 50pesos, total) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(datos, valores, instruccion_sql);
        return ok;
    }

    public Object[][] getTarjetas() {
        String colName[] = {
            "franquisia", "comision"};
        String origen = " bdTarjetas";
        Object dtDatos[][] = getDatos(colName, origen, "select franquisia, comision from bdTarjetas", "");

        return dtDatos;
    }

    public DefaultTableModel getRegistrosCargos() {
        String columNames[] = {"Id", "Descripción", "inmodificable"};
        String colName[] = {"Id", "nombreCargo", "inmodificable"};
        String origen = " bdCargo";
        Object dtDatos[][] = GetTabla(colName, origen, (new StringBuilder()).append("select Id, nombreCargo, inmodificable from ").append(origen).toString());
        DefaultTableModel datos = new DefaultTableModel(dtDatos, columNames) {
            public boolean isCellEditable(int row, int column) {
                return true;
            }
        };
        return datos;
    }

    public Object[][] getRegistrosPrefacturas(String factura) {
        String colName[] = {
            "producto", "descripcion", "lista", "cantidad", "subtotal", "porcDescuento", "descuento", "porcIva", "iva", "total", "ubicacion1",
            "referencia", "estado", "plu", "cant2", "vendedor", "cliente", "subtotalGeneral", "descuentoGeneral", "ivaGeneral", "totalGeneral",
            "rango", "imei", "idProd"};
        String origen = " factura";
        Object dtDatos[][] = getDatos(colName, origen, "select producto, descripcion, lista, cantidad, subtotal, porcDescuento, descuento, porcIva, iva, total, "
                + "ubicacion1, referencia,estado, plu, cant2, vendedor, cliente, subtotalGeneral, descuentoGeneral, ivaGeneral, totalGeneral, rango,"
                + "imei, idProd from factura where factura = '" + factura + "'", " where factura = '" + factura + "'");
        return dtDatos;
    }

    public Object[][] getRegistrosPrefacturas(String factura, String terminal) {
        String colName[] = {
            "producto", "descripcion", "lista", "cantidad", "subtotal", "porcDescuento", "descuento", "porcIva", "iva", "total", "ubicacion1",
            "referencia", "estado", "plu", "cant2"};
        String origen = " factura";
        Object dtDatos[][] = getDatos(colName, origen, "select producto, descripcion, lista, cantidad, subtotal, porcDescuento, descuento, porcIva, iva, total, ubicacion1, referencia,estado, plu, cant2 from factura where factura = '" + factura + "' and terminal = '" + terminal + "'", " where factura = '" + factura + "' and terminal = '" + terminal + "'");

        return dtDatos;
    }

    public Object[][] getRegistrosCCobro(String factura) {
        String colName[] = {
            "producto", "descripcion", "lista", "cantidad", "subtotal", "porcDescuento", "descuento", "porcIva", "iva", "total", "cliente", "vendedor",
            "estado", "plu", "cant2", "subtotalGeneral", "descuentoGeneral", "ivaGeneral", "totalGeneral", "observacion", "estadoGeneral", "preparacion",
            "inicio", "hasta", "cantIncremento", "cantFacturados", "fechaUltimoPago"};
        String origen = " bdCuentaCobro ";
        Object dtDatos[][] = getDatos(colName, origen, "select producto, descripcion, lista, cantidad, subtotal, porcDescuento, descuento, porcIva, iva, "
                + "total, cliente, vendedor, estado, plu, cant2, subtotalGeneral, descuentoGeneral, ivaGeneral, totalGeneral, observacion, "
                + "estadoGeneral, preparacion, inicio, hasta, cantIncremento, cantFacturados, fechaUltimoPago from bdCuentaCobro where idFactura = '" + factura + "'",
                " where idFactura = '" + factura + "'");
        return dtDatos;
    }

    public Object[][] getRegistrosOrdenes(String factura) {
        String colName[] = {
            "producto", "descripcion", "lista", "cantidad", "subtotal", "porcDescuento", "descuento", "porcIva", "iva", "total", "cliente", "vendedor",
            "estado", "plu", "cant2", "subtotalGeneral", "descuentoGeneral", "ivaGeneral", "totalGeneral", "observacion", "estadoGeneral", "placaReal"};
        String origen = " ordenServicio";
        Object dtDatos[][] = getDatos(colName, origen, "select producto, descripcion, lista, cantidad, subtotal, porcDescuento, descuento, porcIva, iva, "
                + "total, cliente, vendedor, estado, plu, cant2, subtotalGeneral, descuentoGeneral, ivaGeneral, totalGeneral, observacion, "
                + "estadoGeneral, placaReal from ordenServicio where idFactura = '" + factura + "'", " where idFactura = '" + factura + "'");
        return dtDatos;
    }

    public Object[][] getRegistrosTraslados(String factura) {
        String colName[] = {
            "Id", "producto", "Descripcion", "cantidad", "lista", "imeil", "destino", "BodegaDestino", "lote"
        };
        String origen = " traslados";
        Object dtDatos[][] = getDatos(colName, origen, "select Id, producto, Descripcion, cantidad, lista, imeil, destino, BodegaDestino, lote "
                + " from traslados where Id = '" + factura + "' and estadoProd = 'PENDIENTE' ", " where Id = '" + factura + "' and estadoProd = 'PENDIENTE' ");
        return dtDatos;
    }

//    public Object[][] getRegistrosOrdenes1(String factura) {
//        String colName[] = {
//            "idFactura", "producto", "descripcion", "cantidad", "lista", "rango", "terminos", "notaAnulacion", "diasGarantia", "cliente", "estadoGeneral"
//        };
//        String origen = " ordenServicio";
//        Object dtDatos[][] = getDatos(colName, origen, "select  idFactura, producto, descripcion, cantidad, lista, rango, terminos, notaAnulacion, diasGarantia, cliente, estadoGeneral "
//                + " from ordenServicio where idFactura = '" + factura + "'", " where idFactura = '" + factura + "'");
//        return dtDatos;
//    }
    public Object[][] getRegistrosOrdenes1() {
        String colName[] = {
            "idFactura", "producto", "descripcion", "cantidad", "lista", "rango", "terminos", "notaAnulacion", "estadoGeneral"
        };
        String origen = " ordenServicio";
        Object dtDatos[][] = getDatos(colName, origen, "select  idFactura, producto, descripcion, cantidad, lista, rango, terminos, notaAnulacion, estadoGeneral"
                + " from ordenServicio ", "");
        return dtDatos;
    }

    public Object[][] getExamenesRealizados(String consulta) {
        String colName[] = {
            "cup", "nombre", "precio"};
        String origen = " bdDetalleOrdenServicioLaboratorio";
        Object dtDatos[][] = getDatos(colName, origen, "select cup, nombre, precio from bdDetalleOrdenServicioLaboratorio where IdConsultaLaboratorio = '" + consulta + "'", " where IdConsultaLaboratorio = '" + consulta + "'");
        return dtDatos;
    }

    public Object[][] getRegistrosPrefacturasTerminal(String factura, String terminal) {
        String colName[] = {
            "producto", "descripcion", "lista", "cantidad", "subtotal", "porcDescuento", "descuento", "porcIva", "iva", "total", "ubicacion1", "referencia", "estado", "plu", "cant2", "anulada"};
        String origen = " factura";
        Object dtDatos[][] = getDatos(colName, origen, "select producto, descripcion, lista, cantidad, subtotal, porcDescuento, descuento, porcIva, iva, total, ubicacion1, referencia,estado, plu, cant2, anulada from factura where idFactura = '" + factura + "' and terminal = '" + terminal + "'", " where idFactura = '" + factura + "' and terminal = '" + terminal + "'");

        return dtDatos;
    }

    public Object[][] getRegistrosPrePedidos(String factura) {
        String colName[] = {
            "producto", "descripcion", "lista", "cantidad", "subtotal", "porcDescuento", "descuento", "porcIva", "iva", "total", "ubicacion1",
            "referencia", "estado", "plu", "cant2", "preparacion", "rango", "idProd"};
        String origen = " pedidos";
        Object dtDatos[][] = getDatos(colName, origen, "select producto, descripcion, lista, cantidad, subtotal, porcDescuento, descuento, porcIva,"
                + " iva, total, ubicacion1, referencia,estado, plu, cant2, preparacion, rango, idProd from pedidos where idFactura = '" + factura + "'", " where idFactura = '" + factura + "'");

        return dtDatos;
    }

    public Object[][] getDatosNc(String id) {
        String colName[] = {
            "factura", "SumaNc", "total"
        };
        String origen = " ConsultaNc";
        Object dtDatos[][] = getDatos(colName, origen, "select factura, SumaNc, total from ConsultaNc where factura2 = '" + id + "'; ", " where factura2 = '" + id + "'");

        return dtDatos;
    }

    public Object[][] getMesasOcupadas() {

        String colName[] = {
            "Nombre", "Estado"
        };
        String origen = " bdMesas ";
        Object dtDatos[][] = getDatos(colName, origen, "select nombre, estado from bdMesas "
                + "where estado = 'OCUPADO'; ", " where estado = 'OCUPADO'; ");
        return dtDatos;
    }

    public Object[][] getDatosProductosGrupo(String grupo, Boolean conImpuestos) {

        String pvp = "L1";
        if (!conImpuestos) {
            pvp = "pvp";
        }

        String colName[] = {
            "Codigo", "Descripcion", "Grupo", pvp, "idSistema"
        };
        String origen = " productos1 ";
        Object dtDatos[][] = getDatos(colName, origen, "select Codigo, Descripcion, Grupo, " + pvp + ", idSistema from productos1 "
                + "where Grupo = '" + grupo + "' and activo = false ; ", " where Grupo = '" + grupo + "' and activo = false ");
        return dtDatos;
    }

    public Object[][] getDatosProductosDiscosteo(String cod) {
        String colName[] = {
            "producto", "cantidad", "codigo", "descripcion", "Grupo"
        };
        String origen = " productosDiscosteo ";
        Object dtDatos[][] = getDatos(colName, origen, "select producto, cantidad, codigo, descripcion, Grupo from productosDiscosteo where codigo = '" + cod + "' ; ", " where codigo = '" + cod + "'");
        return dtDatos;
    }

    public Object[][] getDatosProductosDiscosteo1(String cod) {
        String colName[] = {
            "producto", "cantidad", "codigo", "descripcion", "Grupo"
        };
        String origen = " productosDiscosteo1 ";
        Object dtDatos[][] = getDatos(colName, origen, "select producto, cantidad, codigo, descripcion, Grupo from productosDiscosteo1 where codigo = '" + cod + "'; ",
                " where codigo = '" + cod + "'");
        return dtDatos;
    }

    public Object[][] getDatosProductosUnificados(String id) {
        String colName[] = {
            "productoUnificado", "Descripcion", "referencia", "L1", "fisico", "ubicacion1", "Codigo"
        };
        String origen = " conProductosUnificados ";
//        System.out.println("select productoUnificado, Descripcion,referencia,L1,fisico,ubicacion1 from conProductosUnificados where productoPadre = '" + id + "'");
        Object dtDatos[][] = getDatos(colName, origen, "select productoUnificado, Descripcion, referencia,"
                + "L1,fisico,ubicacion1, Codigo from conProductosUnificados where productoPadre = '" + id + "'; ", " where productoPadre = '" + id + "'");

        return dtDatos;
    }

    // CONSULTA DE LOS PAGOS A PROVEEDORES -----------------------------------------------------
    public DefaultTableModel getRegistrosCxp() {
        String columNames[] = {
            "Ingreso", "Factura", "NIT", "Nom. Proveedor", "Fecha", "fechaVencimiento", "Valor", "Saldo", "edadCartera", "Estado"
        };
        String colName[] = {
            "ingreso", "tipo", "proveedor", "nombre", "fechaFactura", "fechaVencimiento", "valor", "saldo", "edadCartera", "estadoVenc"};

        String origen = "conCxp";
        Object dtDatos[][] = GetTabla(colName, origen, (new StringBuilder()).append("select ingreso, tipo, proveedor, nombre, "
                + "fechaFactura,fechaVencimiento,valor, saldo, edadCartera, estadoVenc from ").append(origen).toString());

        for (int i = 0; i < dtDatos.length; i++) {
            String aux = dtDatos[i][1].toString();
            dtDatos[i][1] = aux.replace("FACT-", "");
        }

        DefaultTableModel datos = new DefaultTableModel(dtDatos, columNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        return datos;
    }

    //Obtener el estado de una cuenta(Anulada o no anulada)
    public boolean getDocumentoAnulado(String tabla, String condicion) {
        String instruccion_sql = "select anulada from " + tabla + " " + condicion + " ; ";
        //columnas de la tabla que se
        String[] colName = {"anulada"};
        boolean[] cadena = {true};
        //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        if (data[0].toString().equals("0")) {
            return false;
        } else {
            return true;
        }
    }

    public String getEstadoOrdenServicio(String condicion) {
        String instruccion_sql = "select inactiva from bdOrdenServicio where orden = '" + condicion + "' ; ";
        //columnas de la tabla que se
        String[] colName = {"inactiva"};
        boolean[] cadena = {false};
        //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        if (!data[0].toString().equals("")) {
            return data[0].toString();
        } else {
            return "";
        }
    }

    public String getEstado(String tabla, String condicion) {
        String instruccion_sql = "select estado from " + tabla + " " + condicion + " ; ";
        //columnas de la tabla que se
        String[] colName = {"estado"};
        boolean[] cadena = {true};
        //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        if (!data[0].toString().equals("")) {
            return data[0].toString();
        } else {
            return "";
        }
    }

    //Obtener el estado de una cuenta(Anulada o no anulada)
    public String getIdAbono(String condicion) {
        String instruccion_sql = "select id from bdAbonos " + condicion + " ; ";
        //columnas de la tabla que se
        String[] colName = {"id"};
        boolean[] cadena = {true};
        //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        if (!data[0].toString().equals("")) {
            return data[0].toString();
        } else {
            return "";
        }
    }

    //Obtener el estado de una cuenta(Anulada o no anulada)
    public String getEstadoPago(String condicion) {
        String instruccion_sql = "select estado from bdCxp " + condicion + " ; ";
        //columnas de la tabla que se
        String[] colName = {"estado"};
        boolean[] cadena = {true};
        //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        if (!data[0].toString().equals("")) {
            return data[0].toString();
        } else {
            return "";
        }
    }

    public String getIdCxp(String condicion) {
        String instruccion_sql = "select ingreso from bdCxp " + condicion + " ; ";
        //columnas de la tabla que se
        String[] colName = {"ingreso"};
        boolean[] cadena = {true};
        //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        if (!data[0].toString().equals("")) {
            return data[0].toString();
        } else {
            return "";
        }
    }

    public String getIdEgreso(String condicion) {
        String instruccion_sql = "select id from bdEgreso " + condicion + " ; ";
        //columnas de la tabla que se
        String[] colName = {"id"};
        boolean[] cadena = {true};
        //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        if (!data[0].toString().equals("")) {
            return data[0].toString();
        } else {
            return "";
        }
    }

    public Object[][] getProductosCompra(String ingreso) {
        String colName[] = {
            "producto", "cantidad"
        };
        String origen = " bdCompra";
        Object dtDatos[][] = getDatos(colName, origen, "select producto, cantidad FROM bdCompra where ingreso = '" + ingreso + "'", " where ingreso = '" + ingreso + "'");

        return dtDatos;
    }

    public Object[][] getProductosAjuste(String ajuste) {
        String colName[] = {
            "producto", "cantidad", "idProd"
        };
        String origen = " bdTrasladosProd";
        Object dtDatos[][] = getDatos(colName, origen, "select producto, cantidad, idProd FROM bdTrasladosProd where traslado = '" + ajuste + "'", " where traslado = '" + ajuste + "'");
        return dtDatos;
    }

    public String tipoAjuste(String factura) {
        String instruccion_sql = "select origen from bdTraslados where id = '" + factura + "'; ";
        String[] colName = {"origen"};
        boolean[] cadena = {true};
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        return data[0].toString();
    }

    //    OBTENGO LOS REGISTROS DE LA CONSULTA DE PAGOS
    public Object[][] getRegistrosPagos(String ingreso) {

        String colName[] = {
            "ingreso", "tipo", "SumaDevalor"
        };
        String origen = " conPagos";
        Object dtDatos[][] = getDatos(colName, origen, "select ingreso, tipo, SumaDevalor FROM conPagos where ingreso = '" + ingreso + "'", " where ingreso = '" + ingreso + "'");

        return dtDatos;
    }

    // CUENTAS POR PAGAR
    public boolean agregarCxp(ndCxp nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdCxp(ingreso, tipo, estado, recibo, vencimiento, usuario,terminal, valor, plazo) values (?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosCxp(nodo), metodos.pasarValoresCxp(nodo), instruccion_sql);
        return ok;
    }

    // CANCELAR CxP
    public boolean cancelarCxp(String ingreso) {
        boolean ok = false;
        Object[][] ids = getIdsPagos(ingreso);

        for (Object[] id : ids) {
            String instruccion_sql = "update bdCxp set estado=? WHERE Id=" + id[0] + " AND estado <> 'ANULADO' ;";
            ok = Actualizar_Registro(new Object[]{"", "CANCELADA"}, null, instruccion_sql);
        }

        return ok;
    }

    public boolean modificarCargo(String nombre, String id) {
        boolean ok = false;
        String instruccion_sql = "update bdCargo set nombreCargo=? WHERE Id=" + id + " ;";
        ok = Actualizar_Registro(new Object[]{"", nombre}, null, instruccion_sql);
        return ok;
    }

    public boolean cambiarEstFact(String estado) {
        boolean ok = false;
        String instruccion_sql = "update bdConsecutivos set estado = ? WHERE Id = 'FACT' ;";
        ok = Actualizar_Registro(new Object[]{"", estado}, null, instruccion_sql);
        return ok;
    }

    public boolean usuarioActivo(String estado, String terminal, String usuario) {
        boolean ok = false;
        String instruccion_sql = "update bdUsuario set estado=?,terminal=? WHERE usuario='" + usuario + "' ;";
        ok = Actualizar_Registro(new Object[]{"", estado, terminal}, null, instruccion_sql);
        return ok;
    }

    public boolean usuarioActivo(String estado, String terminal) {
        boolean ok = false;
        String instruccion_sql = "update bdUsuario set estado=?,terminal=? WHERE usuario='" + Instancias.getInstancias().getUsuario() + "' ;";
        ok = Actualizar_Registro(new Object[]{"", estado, terminal}, null, instruccion_sql);
        return ok;
    }

    public Object[][] getUsuarioPlaca(String usuario) {
        String colName[] = {
            "usuario", "placa", "tipo", "marca", "modelo", "color", "chasis", "motor", "fechaCompra"
        };
        String origen = " bdPlacas";
        Object dtDatos[][] = getDatos(colName, origen, "select usuario, placa, tipo, marca, modelo, color, chasis, motor, fechaCompra from bdPlacas where placa = '" + usuario + "'", " where placa = '" + usuario + "'");
        return dtDatos;
    }

    public boolean actualizarPonderado(String producto, String ponderado) {
        boolean ok = false;
        String instruccion_sql = "update bdProductos set ponderado=? WHERE Codigo='" + producto + "' ;";
        ok = Actualizar_Registro(null, new Object[]{ponderado}, instruccion_sql);

        return ok;
    }

//    public boolean actualizarInventario(String producto, String inv) {
//        boolean ok = false;
//        String instruccion_sql = "update bdProductos set inventario=? WHERE Codigo='" + producto + "' ;";
//        ok = Actualizar_Registro(null, new Object[]{inv}, instruccion_sql);
//        return ok;
//    }
    public boolean devolucionTraslado(String traslado, String idProd, String dev, String campo) {
        boolean ok = false;
        String instruccion_sql = "update bdTrasladosBodegasProd set estado=?, " + campo + "=? WHERE idProd = '" + idProd + "' and traslado = '" + traslado + "' ;";
        ok = Actualizar_Registro(new Object[]{"", "FINALIZADO", dev}, null, instruccion_sql);
        return ok;
    }

    public boolean devolucionTraslado1(String traslado, String prod, String dev, String campo) {
        boolean ok = false;
        String instruccion_sql = "update bdTrasladosBodegasProd set estado=?, " + campo + "=? WHERE producto = '" + prod + "' and traslado = '" + traslado + "' ;";
        ok = Actualizar_Registro(new Object[]{"", "FINALIZADO", dev}, null, instruccion_sql);
        return ok;
    }

    public boolean anulacionPrestamo(String traslado) {
        boolean ok = false;
        String instruccion_sql = "update bdTrasladosBodegas set tipo=? WHERE Id = '" + traslado + "' ;";
        ok = Actualizar_Registro(new Object[]{"", "ANULADO"}, null, instruccion_sql);
        return ok;
    }

    public boolean devolucionTraslado(String ingreso, String tipo) {
        boolean ok = false;
        String instruccion_sql = "update bdTrasladosBodegas set devolucion=?, estado=?, tipo=? WHERE Id='" + ingreso + "' ;";
        ok = Actualizar_Registro(new Object[]{"", metodosGenerales.fecha(), "FINALIZADO", tipo}, null, instruccion_sql);
        return ok;
    }

    public Object[][] getIdsPagos(String ingreso) {

        String colName[] = {
            "Id"
        };
        String origen = " bdCxp";
        Object dtDatos[][] = getDatos(colName, origen, "select Id FROM bdCxp where ingreso = '" + ingreso + "'", " where ingreso = '" + ingreso + "'");

        return dtDatos;
    }

    public String getBodegaMovimiento(String conse, String base) {
        boolean ok = false;
        String instruccion_sql = "select bodega from " + base + " where Id = '" + conse + "' ; ";
        String[] colName = {"bodega"};
        boolean[] cadena = {true};
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        return data[0].toString();
    }

    public String getEstadoFact() {
        boolean ok = false;
        String instruccion_sql = "select estado from bdConsecutivos where Id = 'FACT' ; ";
        String[] colName = {"estado"};
        boolean[] cadena = {true};
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        return data[0].toString();
    }

    public String getTotalRecogida(String fecha, String usuario) {
        boolean ok = false;
        String instruccion_sql = "select suma from conRecogidaParcial where usuario = '" + usuario + "' and " + fecha + ";";
        String[] colName = {"suma"};
        boolean[] cadena = {true};
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        if (data[0] == null) {
            return "0";
        }
        return data[0].toString();
    }

    public boolean desactivarBasesDeCaja(String fecha, String usuario) {
        boolean ok = false;
        String instruccion_sql = "update bdBaseCuadreCaja set activo=? "
                + "where usuario='" + Instancias.getInstancias().getUsuario() + "' and fecha = '" + fecha + "' ;";
        ok = Activar_Registro(instruccion_sql);
        return ok;
    }

    public boolean desactivarBasesDeCaja(String fecha) {
        boolean ok = false;
        String instruccion_sql = "update bdBaseCuadreCaja set activo=? "
                + "where fecha = '" + fecha + "' ;";
        ok = Activar_Registro(instruccion_sql);
        return ok;
    }

    public boolean asignarCuadreABase(String cuadre, String fecha, String usuario) {
        boolean ok = false;
        String instruccion_sql = "update bdBaseCuadreCaja set idCuadre = ? "
                + "where usuario='" + Instancias.getInstancias().getUsuario() + "' and fecha = '" + fecha + "' ;";
        ok = Actualizar_Registro(new Object[]{"", cuadre}, null, instruccion_sql);
        return ok;
    }

    public boolean asignarCuadreABase(String cuadre, String fecha) {
        boolean ok = false;
        String instruccion_sql = "update bdBaseCuadreCaja set idCuadre = ? where fecha = '" + fecha + "' ;";
        ok = Actualizar_Registro(new Object[]{"", cuadre}, null, instruccion_sql);
        return ok;
    }

    public String getTotalBaseCuadreCaja(String fecha, String usuario) {
        boolean ok = false;
        String instruccion_sql = "select suma from conBaseCuadreCaja where usuario = '" + usuario + "' and " + fecha + ";";
        String[] colName = {"suma"};
        boolean[] cadena = {true};
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        if (data[0] == null) {
            return "0";
        }
        return data[0].toString();
    }

    public String getIngresoCxp(String factura) {
        String instruccion_sql = " select tipo,ingreso from bdCxp where tipo = '" + factura + "'; ";
        String[] colName = {"tipo", "ingreso"};
        boolean[] cadena = {true, true};
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        return data[1].toString();
    }

    public Object[] getPromedioFactura() {

        String instruccion_sql = " select cantidadFacturas,dias FROM promedioFactura; ";
        String colName[] = {"cantidadFacturas", "dias"};
        boolean[] cadena = {true, true};

        Object[] data = GetRegistro(colName, cadena, instruccion_sql);

        return data;
    }

    public boolean agregarCuota(ndCuota nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdCuota(id, contrato, numCuota, fecha, usuario, fechaAbono, documentoAbono, documentoMora, documentoAbonoMora, abonoCapital, abonoInteres, total, mora, totalAbono, saldoAtrasado, saldoMora) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosCuota(nodo), metodos.pasarValoresCuota(nodo), instruccion_sql);
        return ok;
    }

    public boolean modificarCuota(ndCuota nodo) {
        boolean ok = false;
        String instruccion_sql = "update bdCuota set contrato=?, numCuota=?, fecha=?, usuario=?, fechaAbono=?, documentoAbono=?, documentoMora=?, documentoAbonoMora=?, abonoCapital=?, abonoInteres=?, total=?, mora=?, totalAbono=?, saldoAtrasado=?, saldoMora=? where id='" + nodo.getId() + "' ;";
        ok = Actualizar_Registro(metodos.pasarDatosCuota(nodo), metodos.pasarValoresCuota(nodo), instruccion_sql);
        return ok;
    }

    public ndCuota getDatosCuota(String Id) {
        boolean ok = false;
        String instruccion_sql = " select id, contrato, numCuota, fecha, usuario, fechaAbono, documentoAbono, documentoMora, abonoCapital, abonoInteres, total, mora, totalAbono, saldoAtrasado ,saldoMora, documentoAbonoMora from bdCuota where id = '" + Id + "'; ";
        String[] colName = {"id", "contrato", "numCuota", "fecha", "usuario", "fechaAbono", "documentoAbono", "documentoMora", "abonoCapital", "abonoInteres", "total", "mora", "totalAbono", "saldoAtrasado", "saldoMora", "documentoAbonoMora"};
        boolean[] cadena = {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true};
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        ndCuota nodo = metodos.llenarCuota(data);
        return nodo;
    }

    public boolean agregarPrestamo(ndPrestamo nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdPrestamo(contrato, factura, usuario, fecha, cliente, convenio, tipoServicio, fechaAprovado, fechaDesenvolso, observaciones, factura2, "
                + " valorSolicitado, valorAprovado, cuotas, interes, totalInteres, total, cuotaInicial) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosPrestamo(nodo), metodos.pasarValoresPrestamo(nodo), instruccion_sql);
        return ok;
    }

    public boolean modificarPrestamo(ndPrestamo nodo) {
        boolean ok = false;
        String instruccion_sql = "update bdPrestamo set factura=?, usuario=?, fecha=?, cliente=?, convenio=?, tipoServicio=?, fechaAprovado=?, fechaDesenvolso=?, observaciones=?, valorSolicitado=?, valorAprovado=?, cuotas=?, interes=?, totalInteres=?, total=? where contrato='" + nodo.getContrato() + "' ;";
        ok = Actualizar_Registro(metodos.pasarDatosPrestamo(nodo), metodos.pasarValoresPrestamo(nodo), instruccion_sql);
        return ok;
    }

    public ndPrestamo getDatosPrestamo(String Contrato) {
        boolean ok = false;
        String instruccion_sql = " select contrato, factura, usuario, fecha, cliente, convenio, tipoServicio, fechaAprovado, fechaDesenvolso, observaciones, "
                + "valorSolicitado, valorAprovado, cuotas, interes, totalInteres, total, cuotaInicial, factura2 from bdPrestamo where factura2 = '" + Contrato + "'; ";
        String[] colName = {"contrato", "factura", "usuario", "fecha", "cliente", "convenio", "tipoServicio", "fechaAprovado", "fechaDesenvolso", "observaciones",
            "valorSolicitado", "valorAprovado", "cuotas", "interes", "totalInteres", "total", "cuotaInicial", "factura2"};
        boolean[] cadena = {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true};
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        ndPrestamo nodo = metodos.llenarPrestamo(data);
        return nodo;
    }

    public boolean agregarGrupo(String codigo, String nombre, String imagen, boolean descuento, boolean visualizar, String arancel) {
        boolean ok = false;
        String instruccion_sql = "insert into bdGrupo(codigo, nombre, imagen, descuento, visualizar, activo, arancel)"
                + " values(?,?,?,?,?,?,?);";
        ok = Agregar_Registro(new Object[]{codigo, nombre, imagen, descuento, visualizar, false, arancel}, null, instruccion_sql);
        return ok;
    }

    public boolean agregarSubGrupo(String codigo, String nombre, String grupo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdSubGrupo(codigo, nombre, grupo)"
                + " values(?,?,?);";

        ok = Agregar_Registro(new Object[]{codigo, nombre, grupo}, null, instruccion_sql);
        return ok;
    }

    public ndGuarderia getDatosGuarderia(String id) {
        boolean ok = false;
        String instruccion_sql = " select id, mascota, producto, fIngreso, fSalida, hIngreso, hSalida, totTiempo, totHoras, totValor, horas, dias, observacion, factura, "
                + "usuario, terminal, activo, cliente from bdGuarderia where id = '" + id + "'; ";
        String[] colName = {"id", "mascota", "producto", "fIngreso", "fSalida", "hIngreso", "hSalida", "totTiempo", "totHoras",
            "totValor", "horas", "dias", "observacion", "factura", "usuario", "terminal", "activo", "cliente"};
        boolean[] cadena = {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, false, true};
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        ndGuarderia nodo = metodos.llenarGuarderia(data);
        return nodo;
    }

    public boolean agregarPeluqueria(ndParqueadero1 nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdPeluqueria(id, placa, usuario, fIngreso, fSalida, hIngreso, hSalida, horas, valHora, total) values (?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosParqueadero(nodo), metodos.pasarValoresParqueadero(nodo), instruccion_sql);
        return ok;
    }

    public boolean modificarPeluqueria(ndParqueadero1 nodo) {
        boolean ok = false;
        String instruccion_sql = "update bdPeluqueria set placa=?, usuario=?, fIngreso=?, fSalida=?, hIngreso=?, hSalida=?, horas=?, valHora=?, total=? where id='" + nodo.getId() + "' ;";
        ok = Actualizar_Registro(metodos.pasarDatosParqueadero(nodo), metodos.pasarValoresParqueadero(nodo), instruccion_sql);
        return ok;
    }

    public Object[][] getConceptoAyuda(String id) {

        String colName[] = {
            "Id", "descripcion"
        };
        String origen = " bdConceptosAyudas";
        Object dtDatos[][] = getDatos(colName, origen, "select Id, descripcion FROM bdConceptosAyudas where Id = '"
                + id + "'", " where Id = '" + id + "'");

        return dtDatos;
    }

    public DefaultTableModel getRegistrosConceptosAyudas() {
        String columNames[] = {
            "Codigo", "Descripcion"
        };
        String colName[] = {
            "Id", "descripcion"
        };
        String origen = " bdConceptosAyudas";
        Object dtDatos[][] = GetTabla(colName, origen, (new StringBuilder()).append("select Id, descripcion from ").append(origen).toString());
        DefaultTableModel datos = new DefaultTableModel(dtDatos, columNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        return datos;
    }

    public ndParqueadero1 getDatosPeluqueria(String id) {
        boolean ok = false;
        String instruccion_sql = " select id, placa, usuario, fIngreso, fSalida, hIngreso, hSalida, horas, valHora, total from repPeluqueria where placa = '" + id + "'; ";
        String[] colName = {"id", "placa", "usuario", "fIngreso", "fSalida", "hIngreso", "hSalida", "horas", "valHora", "total"};
        boolean[] cadena = {true, true, true, true, true, true, true, true, true, true};
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        ndParqueadero1 nodo = metodos.llenarParqueadero(data);
        return nodo;
    }

    public Object[][] getFechas(String nit) {

        String colName[] = {
            "Id"
        };
        String origen = " bdFormato1";
        Object dtDatos[][] = getDatos(colName, origen, "select Id FROM bdFormato1 where nit = '" + nit + "'", " where nit = '" + nit + "'");

        return dtDatos;
    }

    public boolean modificarFechaNacimientoANull(String id) {
        boolean ok = false;
        String valor = null;
        String instruccion_sql = "update bdTerceros set nacimiento=? where id='" + id + "' ;";
        ok = Actualizar_Registro(new Object[]{"", valor}, null, instruccion_sql);
        return ok;
    }

    public boolean agregarTercero(ModeloContacto nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdTerceros(idSistema, Id, nombre, telefono, celular, direccion, ciudad, email, departamento, fecha, usuario, tipo, completa, pNombre, "
                + "sNombre, pApellido, sApellido, actividad, naturaleza, rut, activo, plazo, placas, pais, sexo, estado, ocupacion, regimen, afiliado, tipoTrabajador, "
                + "zona, nacimiento, sangre, eps,terminal, referido, vendedor, lista, nota, tipo2, rango, categoria, convenio, convenioActual, religion, "
                + "nombreMadre, nombrePadre, nombreResponsable, parentescoResponsable, telefonoResponsable, lugarNacimiento, barrio, nombreContacto, cargo, telefonoContacto,"
                + "web, rnt, tipoProveedor, ingreso, retiro, agendar, usuarioAsociado, arl, ccf, tipoTercero, codigoUsuario, responsableIva, cupo, salario) "
                + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosTercero(nodo), metodos.pasarValoresTercero(nodo), instruccion_sql);
        return ok;
    }

    public boolean modificarTercero(ModeloContacto nodo) {
        boolean ok = false;
        String instruccion_sql = "update bdTerceros set Id=?, nombre=?, telefono=?, celular=?, direccion=?, ciudad=?, email=?, departamento=?, fecha=?, usuario=?, tipo=?, "
                + "completa=?, pNombre=?, sNombre=?, pApellido=?, sApellido=?, actividad=?, naturaleza=?, rut=?, activo=?, plazo=?, placas=?, pais=?, sexo=?, estado=?, "
                + "ocupacion=?, regimen=?, afiliado=?, tipoTrabajador=?, zona=?, nacimiento=?, sangre=?, eps=?,terminal=?, referido=?, vendedor=?, lista=?, nota=?, "
                + "tipo2=?, rango=?, categoria=?, convenio=?, convenioActual=?, religion=?, "
                + "nombreMadre=?, nombrePadre=?, nombreResponsable=?, parentescoResponsable=?, telefonoResponsable=?, lugarNacimiento=?, barrio=?, "
                + "nombreContacto=?, cargo=?, telefonoContacto=?, web=?, rnt=?, tipoProveedor=?, ingreso=?, retiro=?, agendar=?, usuarioAsociado=?, arl=?, ccf=?, "
                + "tipoTercero=?, codigoUsuario=?, responsableIva=?, cupo=?, salario=?"
                + " where idSistema = '" + nodo.getIdSistema() + "' ;";
        ok = Actualizar_Registro(metodos.pasarDatosTercero(nodo), metodos.pasarValoresTercero(nodo), instruccion_sql);
        return ok;
    }

    public ModeloContacto getDatosTercero(String Id) {
        boolean ok = false;
        String instruccion_sql = " select idSistema, Id, nombre, telefono, celular, direccion, ciudad, email, departamento, fecha, usuario, tipo, completa, pNombre, sNombre, pApellido, "
                + "sApellido, actividad, naturaleza, rut, activo, plazo, cupo, placas, pais, sexo, estado, ocupacion, regimen, afiliado, tipoTrabajador, zona, nacimiento, "
                + "sangre, eps,terminal, referido, vendedor, lista, nota, tipo2, rango, categoria, convenio, convenioActual, religion, "
                + "nombreMadre, nombrePadre, nombreResponsable, parentescoResponsable, telefonoResponsable, lugarNacimiento, barrio, nombreContacto, cargo, telefonoContacto, "
                + "web, rnt, tipoProveedor, salario, ingreso, retiro, agendar, usuarioAsociado, arl, ccf, tipoTercero, codigoUsuario, responsableIva "
                + " from bdTerceros where Id = '" + Id + "' or idSistema = '" + Id + "'; ";
        String[] colName = {"idSistema", "Id", "nombre", "telefono", "celular", "direccion", "ciudad", "email", "departamento", "fecha", "usuario", "tipo", "completa", "pNombre",
            "sNombre", "pApellido", "sApellido", "actividad", "naturaleza", "rut", "activo", "plazo", "cupo", "placas", "pais", "sexo", "estado", "ocupacion", "regimen",
            "afiliado", "tipoTrabajador", "zona", "nacimiento", "sangre", "eps", "terminal", "referido", "vendedor", "lista", "nota",
            "tipo2", "rango", "categoria", "convenio", "convenioActual", "religion", "nombreMadre", "nombrePadre", "nombreResponsable", "parentescoResponsable",
            "telefonoResponsable", "lugarNacimiento", "barrio", "nombreContacto", "cargo", "telefonoContacto", "web", "rnt", "tipoProveedor", "salario", "ingreso", "retiro",
            "agendar", "usuarioAsociado", "arl", "ccf", "tipoTercero", "codigoUsuario", "responsableIva"
        };
        boolean[] cadena = {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, false, true, true, true,
            true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,
            true, true, true, true, true, true, true, true, true, true, true, true, false, true, true, true, true, true, false};
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        ModeloContacto nodo = metodos.llenarTercero(data);
        return nodo;
    }

    public boolean agregarComprobante(String anho, String descripcion, String comprobante, String modelo, String idComprobante, String nombre,
            String idCuenta, String cuenta, String descripcionCuenta, String idTercero, String relacionAmbito, String movimiento) {
        boolean ok = false;
        String instruccion_sql = "insert into bdComprobante (anho, descripcion, comprobante, modelo, idComprobante, nombre, idCuenta, cuenta, "
                + "descripcionCuenta, idTercero, relacionAmbito, movimiento) "
                + "values (?,?,?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(new Object[]{anho, descripcion, comprobante, modelo, idComprobante, nombre, idCuenta, cuenta,
            descripcionCuenta, idTercero, relacionAmbito, movimiento}, null, instruccion_sql);
        return ok;
    }

    public Object[][] getRegistrosComprobantes(String anho, String comprobante, String modeloContable) {
        String colName[] = {
            "anho", "descripcion", "comprobante", "modelo", "idComprobante", "nombre", "idCuenta", "cuenta", "descripcionCuenta", "idTercero", "relacionAmbito", "movimiento"
        };
        String origen = " bdComprobante";
        Object dtDatos[][] = getDatos(colName, origen, "select anho, descripcion, comprobante, modelo, idComprobante, nombre, idCuenta, cuenta, descripcionCuenta, "
                + "idTercero, relacionAmbito, movimiento FROM bdComprobante where anho = '" + anho + "' AND comprobante = '" + comprobante + "' AND modelo = '" + modeloContable + "' ",
                " where anho = '" + anho + "' AND comprobante = '" + comprobante + "' AND modelo = '" + modeloContable + "' ");

        return dtDatos;
    }

    /////////////////////// MASCOTAS ORGANIZAR CODIGO MASCOTAS ORGANIZAR CODIGO MASCOTAS ORGANIZAR CODIGO MASCOTAS ORGANIZAR CODIGO MASCOTAS ORGANIZAR CODIGO MASCOTAS ORGANIZAR CODIGO MASCOTAS ORGANIZAR CODIGO 
    /////////////////////// MASCOTAS ORGANIZAR CODIGO MASCOTAS ORGANIZAR CODIGO MASCOTAS ORGANIZAR CODIGO MASCOTAS ORGANIZAR CODIGO MASCOTAS ORGANIZAR CODIGO MASCOTAS ORGANIZAR CODIGO MASCOTAS ORGANIZAR CODIGO 
    public Object[][] getConsultas(String historia) {

        String colName[] = {
            "Id", "fecha"
        };
        String origen = " bdConsulta";
        Object dtDatos[][] = getDatos(colName, origen, "select Id, fecha FROM bdConsulta where historia = '" + historia + "'", " where historia = '" + historia + "'");

        return dtDatos;
    }

    public Object[][] getSeguimientos(String consulta) {

        String colName[] = {
            "Id", "fecha"
        };
        String origen = " bdSeguimiento";
        Object dtDatos[][] = getDatos(colName, origen, "select Id, fecha FROM bdSeguimiento where consulta = '" + consulta + "' ", " where consulta = '" + consulta + "' ");

        return dtDatos;
    }

    public Object[][] getConsultasClinica(String historia) {

        String colName[] = {
            "Id", "fecha"
        };
        String origen = " bdConsultaClinica";
        Object dtDatos[][] = getDatos(colName, origen, "select Id, fecha FROM bdConsultaClinica where historia = '" + historia + "' order by fecha, orden asc",
                " where historia = '" + historia + "'");

        return dtDatos;
    }

    public Object[][] getConsultasLaboratorio(String historia) {

        String colName[] = {
            "Id", "fecha"
        };
        String origen = " bdConsultaLaboratorio";
        Object dtDatos[][] = getDatos(colName, origen, "select Id, fecha FROM bdConsultaLaboratorio where historia = '" + historia + "' order by fecha, orden asc", " where historia = '" + historia + "'");

        return dtDatos;
    }

    public Object[][] getAntecedentes(String historia, String tipo) {

        String colName[] = {
            "antecedente", "observaciones"
        };
        String origen = " bdAntecedentes";
        Object dtDatos[][] = getDatos(colName, origen, "select antecedente, observaciones FROM bdAntecedentes where historia = '" + historia + "' AND tipo=" + tipo, " where historia = '" + historia + "' AND tipo=" + tipo);

        return dtDatos;
    }

    public Object[][] getSeguimientosClinica(String consulta) {

        String colName[] = {
            "Id", "fecha"
        };
        String origen = " bdSeguimientoClinica";
        Object dtDatos[][] = getDatos(colName, origen, "select Id, fecha FROM bdSeguimientoClinica where consulta = '" + consulta + "'", " where consulta = '" + consulta + "'");

        return dtDatos;
    }

    public Object[][] getProductos(String consulta) {

        String colName[] = {
            "Codigo", "Descripcion", "L1"
        };
        String origen = " bdProductos";
        Object dtDatos[][] = getDatos(colName, origen, "select Codigo, Descripcion, L1 FROM bdProductos where Grupo = '" + consulta + "'", " where Grupo = '" + consulta + "'");

        return dtDatos;
    }

    public Object[][] getVacunaciones(String mascota, String campo) {

        String colName[] = {
            "Id", "fecha"
        };
        String origen = " bdVacunacion";
        Object dtDatos[][] = getDatos(colName, origen, "select Id, fecha FROM bdVacunacion where mascota = '" + mascota + "' AND campo='" + campo + "'", " where mascota = '" + mascota + "' AND campo='" + campo + "'");

        return dtDatos;
    }

    public boolean agregarDetalleInventario(Object[] dato) {
        boolean ok = false;
        String instruccion_sql = "insert into bdDetalleInventario(idDetalle, codProducto, aumento, descuento, precioViejo, precioNuevo,"
                + "inventario, fecha, hora, usuario)"
                + " values(?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(dato, null, instruccion_sql);
        return ok;
    }

    public boolean agregarBanco(Object[] dato, Object[] dato1) {
        boolean ok = false;
        String instruccion_sql = "insert into bdBancos(nombre, tipo, numero, descripcion, fecha, usuario, monto)"
                + " values(?,?,?,?,?,?,?);";
        ok = Agregar_Registro(dato, dato1, instruccion_sql);
        return ok;
    }

    public boolean agregarMovimientoBanco(Object[] dato, Object[] dato1) {
        boolean ok = false;
        String instruccion_sql = "insert into bdMovimientosBancos(Id, cuenta, numero, nota, usuario, fecha, tipo, recibo, hora, nombreDoc, "
                + "saldoActual, valorMovimiento, saldoFinal)"
                + " values(?,?,?,?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(dato, dato1, instruccion_sql);
        return ok;
    }

    public boolean agregarMovimientoBancoPendiente(Object[] dato, Object[] dato1) {
        boolean ok = false;
        String instruccion_sql = "insert into bdMovimientosBancosPendientes(nota, usuario, fecha, tipo, valorMovimiento)"
                + " values(?,?,?,?,?);";
        ok = Agregar_Registro(dato, dato1, instruccion_sql);
        return ok;
    }

    public ndEgreso getDatosEgreso(String id) {
        String instruccion_sql = "select Id, nit, razon, telefono, direccion,total,subtotal,iva, cred1, cred2, deb1, deb2,cheque, banco, efectivo, "
                + "fecha, documento,usuario,terminal,estado,tipo, ingresoAsociado, egreso, modeloContable "
                + "from bdEgreso where Id = '" + id + "' or egreso = '" + id + "'  ;";
        //columnas de la tabla que se        
        String[] colName = {"Id", "nit", "razon", "telefono", "direccion", "total", "subtotal", "iva", "cred1", "cred2", "deb1", "deb2",
            "cheque", "banco", "efectivo", "fecha", "documento", "usuario", "terminal", "estado", "tipo", "ingresoAsociado", "egreso", "modeloContable"};
        boolean[] cadena = {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,
            true, true, true, true, true, true};
        //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
//        System.out.println(instruccion_sql);
        ndEgreso nodo = metodos.llenarEgreso(data);

        return nodo;
    }

    public boolean agregarNotasCredito(ndNotasCredito nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdNotasCredito(factura, producto, cantidad, NC,descripcion,plu,cant2,estado, porcIva, tercero, "
                + "preparacion, conseMesa, imei, idProd, porcImpoconsumo, lista, descuento, total, iva, subtotal, utilidad, porcDescuento, utilidad1, impoconsumo)"
                + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosNotasCredito(nodo), metodos.pasarValoresNotasCredito(nodo), instruccion_sql);
        return ok;
    }

    public boolean eliminarProductoUnificado(String prod, String prod2) {
        boolean ok = false;
        ok = eliminar_registro("bdProductosUnificados", "productoPadre='" + prod + "' AND productoUnificado='" + prod2 + "'");
        return ok;
    }

//    public Object[][] getRegistrosPreventa(String factura) {
//        String colName[] = {
//            "producto", "Descripcion", "lista", "cantidad", "subtotal", "descuento", "porIva", "valIva", "total"
//        };
//        String origen = " conPreventa";
//        Object dtDatos[][] = getDatos(colName, origen, "select producto, Descripcion, lista, cantidad, subtotal, descuento, porIva, valIva, total from conPreventa where factura = '" + factura + "'", " where factura = '" + factura + "'");
//
//        return dtDatos;
//    }
    public Object[][] getNotasCredito(String tipo) {

        String colName[] = {
            "factura", "producto", "cantidad", "NC", "descripcion", "plu", "cant2", "estado", "porcIva", "tercero", "preparacion", "conseMesa", "lista",
            "descuento", "total", "iva", "subtotal", "utilidad", "porcDescuento", "utilidad1"
        };
        String origen = " bdNotasCredito";
        Object dtDatos[][] = getDatos(colName, origen, "select factura, producto, cantidad, NC,descripcion,plu,cant2,estado, porcIva, tercero, preparacion, conseMesa, lista, descuento, total, "
                + "iva, subtotal, utilidad, porcDescuento, utilidad1 FROM bdNotasCredito where NC = '" + tipo + "' ", " where NC = '" + tipo + "'");
        return dtDatos;
    }

    public Object[][] getRegistrosFactura(String condicion) {
        String colName[] = {
            "tipo", "factura", "fechaFactura", "cliente", "nombre", "Id", "terminal"
        };
        String origen = " factura ";
//        System.out.println("select 'Factura' as tipo, factura, fechaFactura,cliente,nombre,Id,terminal from factura " + condicion);
        Object dtDatos[][] = getDatos(colName, origen, "select 'Factura' as tipo, factura, fechaFactura,cliente,nombre,Id,terminal from factura " + condicion, condicion);

        return dtDatos;
    }

    public Object[][] getRegistrosIngreso(String condicion) {
        String colName[] = {
            "tipo", "ingreso", "fechaFactura", "proveedor", "nombre", "terminal"
        };
        String origen = " ingreso ";
        Object dtDatos[][] = getDatos(colName, origen, "select 'Ingreso' as tipo, ingreso, fechaFactura,proveedor,nombre,terminal from ingreso " + condicion, condicion);

        return dtDatos;
    }

    public Object[][] getRegistrosCotiza(String condicion) {
        String colName[] = {
            "tipo", "Id", "fechaFactura", "cliente", "nombre", "terminal"
        };
        String origen = " cotiza ";
        Object dtDatos[][] = getDatos(colName, origen, "select 'Cotizacion' as tipo, Id, fechaFactura,cliente,nombre,terminal from cotiza " + condicion, condicion);

        return dtDatos;
    }

//                                     NUEVOS REGISTROS
/* ------------------------------------------------------------------------------------- */
    public boolean agregarAbono(ndGarantia nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdAbonos(id, cliente, vendedor, red, fechaFactura, fechaVencimiento, "
                + "comprobante, cotizacion, anula, anulada, credito, cxc, usuario, observacion, fechaAlerta, terminal, estado, factura, resolucion, placa, "
                + "garantia, diasGarantia, rango, terminos, notaAnulacion, conseMesa, modeloContable, efectivo, nc, cheque, targeta, total, descuentos, iva, subtotal, rtIva, "
                + "rtIca, rtFuente, otros, devuelta, copago)"
                + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosAbonos(nodo), metodos.pasarValoresGarantia(nodo), instruccion_sql);
        return ok;
    }

    public boolean agregarFactura(ndFactura nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdFactura(idFactura, cliente, vendedor, red, fechaFactura, fechaVencimiento, "
                + "comprobante, cotizacion, anulada, anula, credito, cxc, usuario, observacion, anulada1, anula1, credito1, cxc1, usuario1, "
                + "fechaAlerta, terminal, estadoGeneral, estado2, factura, resolucion, fechaAnulacion, cuadreAnulacion, usuarioAnula, placa, "
                + "garantia, diasGarantia, rango, terminos, notaAnulacion, conseMesa, producto, NC, concepto, descripcion, plu, estado, tercero, preparacion, "
                + "turno, franquisia, comision, imei, lote, idProd, mesFacturado, porcPropina, idCosteo, hora, sisteCredito, bodega, modeloContable, efectivoGeneral, "
                + "ncGeneral, chequeGeneral, targetaGeneral, totalGeneral, descuentoGeneral, "
                + "ivaGeneral, subtotalGeneral, rtIva, rtIca, rtFuente, otros, devuelta, copago, lista, cantidad, descuento, total, iva, subtotal, utilidad, porcDescuento, "
                + "cant2, porcIva, utilidad1, impuesto, impoGeneral, valorComision, totalFacturaComision, tarjetaCredito, totalPropina, porcImpo, impoconsumo, costo)"
                + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosFactura(nodo), metodos.pasarValoresFactura(nodo), instruccion_sql);
        return ok;
    }

    public boolean agregarOServicio1(ndOServicio1 nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdOServicio1(idFactura, cliente, vendedor, red, fechaFactura, fechaVencimiento, "
                + "comprobante, cotizacion, anulada, anula, credito, cxc, usuario, observacion, anulada1, anula1, credito1, cxc1, usuario1, "
                + "fechaAlerta, terminal, estadoGeneral, estado2, factura, resolucion, fechaAnulacion, cuadreAnulacion, usuarioAnula, placa, "
                + "garantia, diasGarantia, rango, terminos, notaAnulacion, conseMesa, producto, NC, concepto, descripcion, plu, estado, tercero, preparacion, bodega, "
                + "efectivoGeneral, ncGeneral, chequeGeneral, targetaGeneral, totalGeneral, descuentoGeneral, ivaGeneral, subtotalGeneral, rtIva, rtIca, "
                + "rtFuente, otros, devuelta, copago, lista, cantidad, descuento, total, iva, subtotal, utilidad, porcDescuento, cant2, porcIva, utilidad1)"
                + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosOServicio1(nodo), metodos.pasarValoresOServicio1(nodo), instruccion_sql);
        return ok;
    }

    public boolean agregarCuentaCobro(ndCongelada nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdCuentaCobro(idFactura, cliente, vendedor, red, fechaFactura, fechaVencimiento, "
                + "comprobante, cotizacion, anulada, anula, credito, cxc, usuario, observacion, anulada1, anula1, credito1, cxc1, usuario1, "
                + "fechaAlerta, terminal, estadoGeneral, estado2, factura, resolucion, fechaAnulacion, cuadreAnulacion, usuarioAnula, placa, "
                + "garantia, diasGarantia, rango, terminos, notaAnulacion, conseMesa, producto, NC, concepto, descripcion, plu, estado, tercero, preparacion, inicio, "
                + "hasta, cantIncremento, bodega, efectivoGeneral, ncGeneral, chequeGeneral, targetaGeneral, totalGeneral, descuentoGeneral, ivaGeneral, subtotalGeneral, rtIva, rtIca, "
                + "rtFuente, otros, devuelta, copago, lista, cantidad, descuento, total, iva, subtotal, utilidad, porcDescuento, cant2, porcIva, utilidad1, "
                + "impoconsumoGeneral, porcImpo, impoconsumo)"
                + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                + "?,?,?,?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosCongelada(nodo), metodos.pasarValoresCongelada(nodo), instruccion_sql);
        return ok;
    }

    public boolean agregarCongelada(ndCongelada nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdCongelada(idFactura, cliente, vendedor, red, fechaFactura, fechaVencimiento, "
                + "comprobante, cotizacion, anulada, anula, credito, cxc, usuario, observacion, anulada1, anula1, credito1, cxc1, usuario1, "
                + "fechaAlerta, terminal, estadoGeneral, estado2, factura, resolucion, fechaAnulacion, cuadreAnulacion, usuarioAnula, placa, "
                + "garantia, diasGarantia, rango, terminos, notaAnulacion, conseMesa, producto, NC, concepto, descripcion, plu, estado, tercero, "
                + "preparacion, turno, imei, idProd, bodega, efectivoGeneral, ncGeneral, chequeGeneral, targetaGeneral, totalGeneral, descuentoGeneral, ivaGeneral, "
                + "subtotalGeneral, rtIva, rtIca, rtFuente, otros, devuelta, copago, lista, cantidad, descuento, total, iva, subtotal, utilidad,"
                + "porcDescuento, cant2, porcIva, utilidad1, impoconsumoGeneral, porcImpo, impoconsumo)"
                + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?"
                + ",?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosCongelada(nodo), metodos.pasarValoresCongelada(nodo), instruccion_sql);
        return ok;
    }

    public boolean agregarPlanSepare(ndPlanSepare nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdPlanSepare(idFactura, cliente, vendedor, red, fechaFactura, fechaVencimiento, "
                + "comprobante, cotizacion, anulada, anula, credito, cxc, usuario, observacion, anulada1, anula1, credito1, cxc1, usuario1, "
                + "fechaAlerta, terminal, estadoGeneral, estado2, factura, resolucion, fechaAnulacion, cuadreAnulacion, usuarioAnula, placa, "
                + "garantia, diasGarantia, rango, terminos, notaAnulacion, conseMesa, producto, NC, concepto, descripcion, plu, estado, tercero, "
                + "preparacion, imei, idProd, bodega, efectivoGeneral, ncGeneral, chequeGeneral, targetaGeneral, totalGeneral, descuentoGeneral, ivaGeneral, subtotalGeneral, rtIva, rtIca, "
                + "rtFuente, otros, devuelta, copago, lista, cantidad, descuento, total, iva, subtotal, utilidad, porcDescuento, cant2, porcIva, utilidad1)"
                + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosPlanSepare(nodo), metodos.pasarValoresPlanSepare(nodo), instruccion_sql);
        return ok;
    }

    public boolean agregarPedido(ndPedido nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdPedido(idFactura, cliente, vendedor, red, fechaFactura, fechaVencimiento, "
                + "comprobante, cotizacion, anulada, anula, credito, cxc, usuario, observacion, anulada1, anula1, credito1, cxc1, usuario1, "
                + "fechaAlerta, terminal, estadoGeneral, estado2, factura, resolucion, fechaAnulacion, cuadreAnulacion, usuarioAnula, placa, "
                + "garantia, diasGarantia, rango, terminos, notaAnulacion, conseMesa, producto, NC, concepto, descripcion, plu, estado, tercero, preparacion, bodega, "
                + "idProd, detalle, efectivoGeneral, ncGeneral, chequeGeneral, targetaGeneral, totalGeneral, descuentoGeneral, ivaGeneral, subtotalGeneral, rtIva, rtIca, "
                + "rtFuente, otros, devuelta, copago, lista, cantidad, descuento, total, iva, subtotal, utilidad, porcDescuento, cant2, porcIva, utilidad1)"
                + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosPedido(nodo), metodos.pasarValoresPedido(nodo), instruccion_sql);
        return ok;
    }

    public boolean agregarCotizacion(ndCotizacion nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdCotizacion(idFactura, cliente, vendedor, red, fechaFactura, fechaVencimiento, "
                + "comprobante, cotizacion, anulada, anula, credito, cxc, usuario, observacion, anulada1, anula1, credito1, cxc1, usuario1, "
                + "fechaAlerta, terminal, estadoGeneral, estado2, factura, resolucion, fechaAnulacion, cuadreAnulacion, usuarioAnula, placa, "
                + "garantia, diasGarantia, rango, terminos, notaAnulacion, conseMesa, producto, NC, concepto, descripcion, plu, estado, tercero, preparacion, bodega,  "
                + "efectivoGeneral, ncGeneral, chequeGeneral, targetaGeneral, totalGeneral, descuentoGeneral, ivaGeneral, subtotalGeneral, rtIva, rtIca, "
                + "rtFuente, otros, devuelta, copago, lista, cantidad, descuento, total, iva, subtotal, utilidad, porcDescuento, cant2, porcIva, utilidad1)"
                + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosCotizacion(nodo), metodos.pasarValoresCotizacion(nodo), instruccion_sql);
        return ok;
    }

    public ndFactura getDatosFactura(String id) {
        String instruccion_sql = "select idFactura, cliente, vendedor, red, fechaFactura, fechaVencimiento, efectivoGeneral, ncGeneral, chequeGeneral, "
                + "targetaGeneral, totalGeneral, descuentoGeneral, ivaGeneral, subtotalGeneral, comprobante, cotizacion, anulada, anula, credito, cxc, "
                + "usuario, rtIva, rtIca, rtFuente, otros, observacion, anulada1, anula1, credito1, cxc1, usuario1, fechaAlerta, terminal, estadoGeneral, "
                + "estado2, devuelta, factura, resolucion, fechaAnulacion, cuadreAnulacion, usuarioAnula, copago, placa, garantia, diasGarantia, rango, "
                + "terminos, notaAnulacion, conseMesa, producto, lista, cantidad, descuento, total, iva, subtotal, NC, utilidad, concepto, porcDescuento, "
                + "descripcion, plu, cant2, estado, porcIva, tercero, utilidad1, preparacion, impuesto, turno, impoGeneral, franquisia, comision, "
                + "valorComision, totalFacturaComision, imei, lote, idProd, mesFacturado, tarjetaCredito, totalPropina, porcPropina, idCosteo, hora, "
                + "porcImpo, impoconsumo, sisteCredito, bodega, costo, modeloContable from bdFactura where factura = '" + id + "'; ";
        String[] colName = {
            "idFactura", "cliente", "vendedor", "red", "fechaFactura", "fechaVencimiento", "efectivoGeneral", "ncGeneral", "chequeGeneral",
            "targetaGeneral", "totalGeneral", "descuentoGeneral", "ivaGeneral", "subtotalGeneral", "comprobante", "cotizacion", "anulada",
            "anula", "credito", "cxc", "usuario", "rtIva", "rtIca", "rtFuente", "otros", "observacion", "anulada1", "anula1", "credito1", "cxc1",
            "usuario1", "fechaAlerta", "terminal", "estadoGeneral", "estado2", "devuelta", "factura", "resolucion", "fechaAnulacion", "cuadreAnulacion",
            "usuarioAnula", "copago", "placa", "garantia", "diasGarantia", "rango", "terminos", "notaAnulacion", "conseMesa", "producto", "lista",
            "cantidad", "descuento", "total", "iva", "subtotal", "NC", "utilidad", "concepto", "porcDescuento", "descripcion", "plu", "cant2", "estado",
            "porcIva", "tercero", "utilidad1", "preparacion", "impuesto", "turno", "impoGeneral", "franquisia", "comision", "valorComision", "totalFacturaComision",
            "imei", "lote", "idProd", "mesFacturado", "tarjetaCredito", "totalPropina", "porcPropina", "idCosteo", "hora", "porcImpo", "impoconsumo",
            "sisteCredito", "bodega", "costo", "modeloContable"};
        boolean[] cadena = {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, false, true, false, true,
            true, true, true, true, true, true, false, true, false, true, true, true, true, true, true, true, true, true, true, true,
            true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,
            true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,
            true, true, true, true, true, false, true, true, true};

        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        ndFactura nodo = metodos.llenarFactura(data);
        return nodo;
    }

    public ndOServicio1 getDatosOServicio1(String id) {

        String instruccion_sql = "select idFactura, cliente, vendedor, red, fechaFactura, fechaVencimiento, efectivoGeneral, ncGeneral, chequeGeneral, "
                + "targetaGeneral, totalGeneral, descuentoGeneral, ivaGeneral, subtotalGeneral, comprobante, cotizacion, anulada, anula, credito, cxc, "
                + "usuario, rtIva, rtIca, rtFuente, otros, observacion, anulada1, anula1, credito1, cxc1, usuario1, fechaAlerta, terminal, estadoGeneral, "
                + "estado2, devuelta, factura, resolucion, fechaAnulacion, cuadreAnulacion, usuarioAnula, copago, placa, garantia, diasGarantia, rango, "
                + "terminos, notaAnulacion, conseMesa, producto, lista, cantidad, descuento, total, iva, subtotal, NC, utilidad, concepto, porcDescuento, "
                + "descripcion, plu, cant2, estado, porcIva, tercero, utilidad1, preparacion, bodega from bdOServicio1 where idFactura = '" + id + "'; ";

        //columnas de la tabla que se
        String[] colName = {
            "idFactura", "cliente", "vendedor", "red", "fechaFactura", "fechaVencimiento", "efectivoGeneral", "ncGeneral", "chequeGeneral",
            "targetaGeneral", "totalGeneral", "descuentoGeneral", "ivaGeneral", "subtotalGeneral", "comprobante", "cotizacion", "anulada",
            "anula", "credito", "cxc", "usuario", "rtIva", "rtIca", "rtFuente", "otros", "observacion", "anulada1", "anula1", "credito1", "cxc1",
            "usuario1", "fechaAlerta", "terminal", "estadoGeneral", "estado2", "devuelta", "factura", "resolucion", "fechaAnulacion", "cuadreAnulacion",
            "usuarioAnula", "copago", "placa", "garantia", "diasGarantia", "rango", "terminos", "notaAnulacion", "conseMesa", "producto", "lista",
            "cantidad", "descuento", "total", "iva", "subtotal", "NC", "utilidad", "concepto", "porcDescuento", "descripcion", "plu", "cant2", "estado",
            "porcIva", "tercero", "utilidad1", "preparacion", "bodega"};
        boolean[] cadena = {true, true, true, true, true, true, true, true, true, true,
            true, true, true, true, true, true, false, true, false, true, true, true, true, true, true, true, false, true, false, true,
            true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,
            true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true};
        //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);

        ndOServicio1 nodo = metodos.llenarOServicio1(data);

        return nodo;
    }

    public ndPlanSepare getDatosPlanSepare(String id) {

        String instruccion_sql = "select idFactura, cliente, vendedor, red, fechaFactura, fechaVencimiento, efectivoGeneral, ncGeneral, chequeGeneral, "
                + "targetaGeneral, totalGeneral, descuentoGeneral, ivaGeneral, subtotalGeneral, comprobante, cotizacion, anulada, anula, credito, cxc, "
                + "usuario, rtIva, rtIca, rtFuente, otros, observacion, anulada1, anula1, credito1, cxc1, usuario1, fechaAlerta, terminal, estadoGeneral, "
                + "estado2, devuelta, factura, resolucion, fechaAnulacion, cuadreAnulacion, usuarioAnula, copago, placa, garantia, diasGarantia, rango, "
                + "terminos, notaAnulacion, conseMesa, producto, lista, cantidad, descuento, total, iva, subtotal, NC, utilidad, concepto, porcDescuento, "
                + "descripcion, plu, cant2, estado, porcIva, tercero, utilidad1, preparacion, imei, idProd, bodega from bdPlanSepare where idFactura = '" + id + "'; ";

        //columnas de la tabla que se
        String[] colName = {
            "idFactura", "cliente", "vendedor", "red", "fechaFactura", "fechaVencimiento", "efectivoGeneral", "ncGeneral", "chequeGeneral",
            "targetaGeneral", "totalGeneral", "descuentoGeneral", "ivaGeneral", "subtotalGeneral", "comprobante", "cotizacion", "anulada",
            "anula", "credito", "cxc", "usuario", "rtIva", "rtIca", "rtFuente", "otros", "observacion", "anulada1", "anula1", "credito1", "cxc1",
            "usuario1", "fechaAlerta", "terminal", "estadoGeneral", "estado2", "devuelta", "factura", "resolucion", "fechaAnulacion", "cuadreAnulacion",
            "usuarioAnula", "copago", "placa", "garantia", "diasGarantia", "rango", "terminos", "notaAnulacion", "conseMesa", "producto", "lista",
            "cantidad", "descuento", "total", "iva", "subtotal", "NC", "utilidad", "concepto", "porcDescuento", "descripcion", "plu", "cant2", "estado",
            "porcIva", "tercero", "utilidad1", "preparacion", "imei", "idProd", "bodega"};
        boolean[] cadena = {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, false, true, false, true,
            true, true, true, true, true, true, false, true, false, true, true, true, true, true, true, true, true, true, true, true,
            true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,
            true, true, true, true, true, true, true, true, true, true, true};
        //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);

        ndPlanSepare nodo = metodos.llenarPlanSepare(data);

        return nodo;
    }

    public ndCongelada getDatosCuentaCobro(String id) {
        String instruccion_sql = "select idFactura, cliente, vendedor, red, fechaFactura, fechaVencimiento, efectivoGeneral, ncGeneral, chequeGeneral, "
                + "targetaGeneral, totalGeneral, descuentoGeneral, ivaGeneral, subtotalGeneral, comprobante, cotizacion, anulada, anula, credito, cxc, "
                + "usuario, rtIva, rtIca, rtFuente, otros, observacion, anulada1, anula1, credito1, cxc1, usuario1, fechaAlerta, terminal, estadoGeneral, "
                + "estado2, devuelta, factura, resolucion, fechaAnulacion, cuadreAnulacion, usuarioAnula, copago, placa, garantia, diasGarantia, rango, "
                + "terminos, notaAnulacion, conseMesa, producto, lista, cantidad, descuento, total, iva, subtotal, NC, utilidad, concepto, porcDescuento, "
                + "descripcion, plu, cant2, estado, porcIva, tercero, utilidad1, preparacion, inicio, hasta, cantIncremento, impoconsumoGeneral, porcImpo, "
                + "impoconsumo, bodega from bdCuentaCobro where idFactura = '" + id + "'; ";
        String[] colName = {
            "idFactura", "cliente", "vendedor", "red", "fechaFactura", "fechaVencimiento", "efectivoGeneral", "ncGeneral", "chequeGeneral",
            "targetaGeneral", "totalGeneral", "descuentoGeneral", "ivaGeneral", "subtotalGeneral", "comprobante", "cotizacion", "anulada",
            "anula", "credito", "cxc", "usuario", "rtIva", "rtIca", "rtFuente", "otros", "observacion", "anulada1", "anula1", "credito1", "cxc1",
            "usuario1", "fechaAlerta", "terminal", "estadoGeneral", "estado2", "devuelta", "factura", "resolucion", "fechaAnulacion", "cuadreAnulacion",
            "usuarioAnula", "copago", "placa", "garantia", "diasGarantia", "rango", "terminos", "notaAnulacion", "conseMesa", "producto", "lista",
            "cantidad", "descuento", "total", "iva", "subtotal", "NC", "utilidad", "concepto", "porcDescuento", "descripcion", "plu", "cant2", "estado",
            "porcIva", "tercero", "utilidad1", "preparacion", "inicio", "hasta", "cantIncremento", "impoconsumoGeneral", "porcImpo", "impoconsumo", "bodega"};
        boolean[] cadena = {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, false, true, false, true,
            true, true, true, true, true, true, false, true, false, true, true, true, true, true, true, true, true, true, true, true, true, true,
            true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,
            true, true, true, true, true, true, true, true, true, true, true};
        //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        ndCongelada nodo = metodos.llenarCongelada(data);
        return nodo;
    }

    public ndCongelada getDatosCongelada(String id) {
        String instruccion_sql = "select idFactura, cliente, vendedor, red, fechaFactura, fechaVencimiento, efectivoGeneral, ncGeneral, chequeGeneral, "
                + "targetaGeneral, totalGeneral, descuentoGeneral, ivaGeneral, subtotalGeneral, comprobante, cotizacion, anulada, anula, credito, cxc, "
                + "usuario, rtIva, rtIca, rtFuente, otros, observacion, anulada1, anula1, credito1, cxc1, usuario1, fechaAlerta, terminal, estadoGeneral, "
                + "estado2, devuelta, factura, resolucion, fechaAnulacion, cuadreAnulacion, usuarioAnula, copago, placa, garantia, diasGarantia, rango, "
                + "terminos, notaAnulacion, conseMesa, producto, lista, cantidad, descuento, total, iva, subtotal, NC, utilidad, concepto, porcDescuento, "
                + "descripcion, plu, cant2, estado, porcIva, tercero, utilidad1, preparacion, turno, imei, idProd, impoconsumoGeneral, porcImpo, impoconsumo,"
                + "bodega from bdCongelada where idFactura = '" + id + "'; ";

        //columnas de la tabla que se
        String[] colName = {
            "idFactura", "cliente", "vendedor", "red", "fechaFactura", "fechaVencimiento", "efectivoGeneral", "ncGeneral", "chequeGeneral",
            "targetaGeneral", "totalGeneral", "descuentoGeneral", "ivaGeneral", "subtotalGeneral", "comprobante", "cotizacion", "anulada",
            "anula", "credito", "cxc", "usuario", "rtIva", "rtIca", "rtFuente", "otros", "observacion", "anulada1", "anula1", "credito1", "cxc1",
            "usuario1", "fechaAlerta", "terminal", "estadoGeneral", "estado2", "devuelta", "factura", "resolucion", "fechaAnulacion", "cuadreAnulacion",
            "usuarioAnula", "copago", "placa", "garantia", "diasGarantia", "rango", "terminos", "notaAnulacion", "conseMesa", "producto", "lista",
            "cantidad", "descuento", "total", "iva", "subtotal", "NC", "utilidad", "concepto", "porcDescuento", "descripcion", "plu", "cant2", "estado",
            "porcIva", "tercero", "utilidad1", "preparacion", "turno", "imei", "idProd", "impoconsumoGeneral", "porcImpo", "impoconsumo", "bodega"};
        boolean[] cadena = {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, false, true, false, true,
            true, true, true, true, true, true, false, true, false, true, true, true, true, true, true, true, true, true, true, true, true, true,
            true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,
            true, true, true, true, true, true, true, true, true, true, true};
        //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);

        ndCongelada nodo = metodos.llenarCongelada(data);

        return nodo;
    }

    public ndCongelada getDatosCongelada1(String id) {

        String instruccion_sql = "select idFactura, cliente, vendedor, red, fechaFactura, fechaVencimiento, efectivoGeneral, ncGeneral, chequeGeneral, "
                + "targetaGeneral, totalGeneral, descuentoGeneral, ivaGeneral, subtotalGeneral, comprobante, cotizacion, anulada, anula, credito, cxc, "
                + "usuario, rtIva, rtIca, rtFuente, otros, observacion, anulada1, anula1, credito1, cxc1, usuario1, fechaAlerta, terminal, estadoGeneral, "
                + "estado2, devuelta, factura, resolucion, fechaAnulacion, cuadreAnulacion, usuarioAnula, copago, placa, garantia, diasGarantia, rango, "
                + "terminos, notaAnulacion, conseMesa, producto, lista, cantidad, descuento, total, iva, subtotal, NC, utilidad, concepto, porcDescuento, "
                + "descripcion, plu, cant2, estado, porcIva, tercero, utilidad1, preparacion, turno, imei, idProd, impoconsumoGeneral, porcImpo, impoconsumo, "
                + "bodega from bdCongelada where conseMesa = '" + id + "'; ";
        String[] colName = {
            "idFactura", "cliente", "vendedor", "red", "fechaFactura", "fechaVencimiento", "efectivoGeneral", "ncGeneral", "chequeGeneral",
            "targetaGeneral", "totalGeneral", "descuentoGeneral", "ivaGeneral", "subtotalGeneral", "comprobante", "cotizacion", "anulada",
            "anula", "credito", "cxc", "usuario", "rtIva", "rtIca", "rtFuente", "otros", "observacion", "anulada1", "anula1", "credito1", "cxc1",
            "usuario1", "fechaAlerta", "terminal", "estadoGeneral", "estado2", "devuelta", "factura", "resolucion", "fechaAnulacion", "cuadreAnulacion",
            "usuarioAnula", "copago", "placa", "garantia", "diasGarantia", "rango", "terminos", "notaAnulacion", "conseMesa", "producto", "lista",
            "cantidad", "descuento", "total", "iva", "subtotal", "NC", "utilidad", "concepto", "porcDescuento", "descripcion", "plu", "cant2", "estado",
            "porcIva", "tercero", "utilidad1", "preparacion", "turno", "imei", "idProd", "impoconsumoGeneral", "porcImpo", "impoconsumo", "bodega"};
        boolean[] cadena = {true, true, true, true, true, true, true, true, true, true,
            true, true, true, true, true, true, false, true, false, true, true, true, true, true, true, true, false, true, false, true,
            true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,
            true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true};
        //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);

        ndCongelada nodo = metodos.llenarCongelada(data);

        return nodo;
    }

    public ndPedido getDatosPedido(String id) {
        String instruccion_sql = "select idFactura, cliente, vendedor, red, fechaFactura, fechaVencimiento, efectivoGeneral, ncGeneral, chequeGeneral, "
                + "targetaGeneral, totalGeneral, descuentoGeneral, ivaGeneral, subtotalGeneral, comprobante, cotizacion, anulada, anula, credito, cxc, "
                + "usuario, rtIva, rtIca, rtFuente, otros, observacion, anulada1, anula1, credito1, cxc1, usuario1, fechaAlerta, terminal, estadoGeneral, "
                + "estado2, devuelta, factura, resolucion, fechaAnulacion, cuadreAnulacion, usuarioAnula, copago, placa, garantia, diasGarantia, rango, "
                + "terminos, notaAnulacion, conseMesa, producto, lista, cantidad, descuento, total, iva, subtotal, NC, utilidad, concepto, porcDescuento, "
                + "descripcion, plu, cant2, estado, porcIva, tercero, utilidad1, preparacion, bodega, idProd, detalle from bdPedido where idFactura = '" + id + "'; ";

        //columnas de la tabla que se
        String[] colName = {
            "idFactura", "cliente", "vendedor", "red", "fechaFactura", "fechaVencimiento", "efectivoGeneral", "ncGeneral", "chequeGeneral",
            "targetaGeneral", "totalGeneral", "descuentoGeneral", "ivaGeneral", "subtotalGeneral", "comprobante", "cotizacion", "anulada",
            "anula", "credito", "cxc", "usuario", "rtIva", "rtIca", "rtFuente", "otros", "observacion", "anulada1", "anula1", "credito1", "cxc1",
            "usuario1", "fechaAlerta", "terminal", "estadoGeneral", "estado2", "devuelta", "factura", "resolucion", "fechaAnulacion", "cuadreAnulacion",
            "usuarioAnula", "copago", "placa", "garantia", "diasGarantia", "rango", "terminos", "notaAnulacion", "conseMesa", "producto", "lista",
            "cantidad", "descuento", "total", "iva", "subtotal", "NC", "utilidad", "concepto", "porcDescuento", "descripcion", "plu", "cant2", "estado",
            "porcIva", "tercero", "utilidad1", "preparacion", "bodega", "idProd", "detalle"};
        boolean[] cadena = {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, false, true, false, true,
            true, true, true, true, true, true, false, true, false, true, true, true, true, true, true, true, true, true, true, true,
            true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,
            true, true, true, true, true, true, true, true, true, true, true};
        //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);

        ndPedido nodo = metodos.llenarPedido(data);
        return nodo;
    }

    public ndCotizacion getDatosCotizacion(String id) {
        String instruccion_sql = "select idFactura, cliente, vendedor, red, fechaFactura, fechaVencimiento, efectivoGeneral, ncGeneral, chequeGeneral, "
                + "targetaGeneral, totalGeneral, descuentoGeneral, ivaGeneral, subtotalGeneral, comprobante, cotizacion, anulada, anula, credito, cxc, "
                + "usuario, rtIva, rtIca, rtFuente, otros, observacion, anulada1, anula1, credito1, cxc1, usuario1, fechaAlerta, terminal, estadoGeneral, "
                + "estado2, devuelta, factura, resolucion, fechaAnulacion, cuadreAnulacion, usuarioAnula, copago, placa, garantia, diasGarantia, rango, "
                + "terminos, notaAnulacion, conseMesa, producto, lista, cantidad, descuento, total, iva, subtotal, NC, utilidad, concepto, porcDescuento, "
                + "descripcion, plu, cant2, estado, porcIva, tercero, utilidad1, preparacion, bodega from bdCotizacion where idFactura = '" + id + "'; ";

        //columnas de la tabla que se
        String[] colName = {
            "idFactura", "cliente", "vendedor", "red", "fechaFactura", "fechaVencimiento", "efectivoGeneral", "ncGeneral", "chequeGeneral",
            "targetaGeneral", "totalGeneral", "descuentoGeneral", "ivaGeneral", "subtotalGeneral", "comprobante", "cotizacion", "anulada",
            "anula", "credito", "cxc", "usuario", "rtIva", "rtIca", "rtFuente", "otros", "observacion", "anulada1", "anula1", "credito1", "cxc1",
            "usuario1", "fechaAlerta", "terminal", "estadoGeneral", "estado2", "devuelta", "factura", "resolucion", "fechaAnulacion", "cuadreAnulacion",
            "usuarioAnula", "copago", "placa", "garantia", "diasGarantia", "rango", "terminos", "notaAnulacion", "conseMesa", "producto", "lista",
            "cantidad", "descuento", "total", "iva", "subtotal", "NC", "utilidad", "concepto", "porcDescuento", "descripcion", "plu", "cant2", "estado",
            "porcIva", "tercero", "utilidad1", "preparacion", "bodega"};
        boolean[] cadena = {true, true, true, true, true, true, true, true, true, true,
            true, true, true, true, true, true, false, true, false, true,
            true, true, true, true, true, true, false, true, false, true,
            true, true, true, true, true, true, true, true, true, true,
            true, true, true, true, true, true, true, true, true, true,
            true, true, true, true, true, true, true, true, true, true,
            true, true, true, true, true, true, true, true, true};
        //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);

        ndCotizacion nodo = metodos.llenarCotizacion(data);

        return nodo;
    }

    public ndGarantia getDatosAbonos(String id) {

        String instruccion_sql = "select id, cliente, vendedor, red, fechaFactura, fechaVencimiento, efectivo, nc, cheque, targeta, comprobante, "
                + "cotizacion, anula, anulada, credito, total, descuentos, iva, subtotal, cxc, usuario, rtIva, rtIca, rtFuente, otros, "
                + "observacion,fechaAlerta,terminal,devuelta,estado,factura, resolucion,copago, placa, garantia, diasGarantia, rango, terminos, "
                + "notaAnulacion, conseMesa, modeloContable from bdAbonos where id = '" + id + "' or factura = '" + id + "'; ";

        //columnas de la tabla que se
        String[] colName = {"id", "cliente", "vendedor", "red", "fechaFactura", "fechaVencimiento", "efectivo", "nc", "cheque",
            "targeta", "comprobante", "cotizacion", "anula", "anulada", "credito", "total", "descuentos", "iva", "subtotal", "cxc",
            "usuario", "rtIva", "rtIca", "rtFuente", "otros", "observacion", "fechaAlerta", "terminal", "devuelta", "estado", "factura",
            "resolucion", "copago", "placa", "garantia", "diasGarantia", "rango", "terminos", "notaAnulacion", "conseMesa", "modeloContable"};
        boolean[] cadena = {true, true, true, true, true, true, true, true, true, true, true, true, true, false, false, true, true,
            true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true};
        //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);

        ndGarantia nodo = metodos.llenarAbonos(data);

        return nodo;
    }

    public ndFactura getDatosCajaTerminal(String id) {

        String instruccion_sql = "select idFactura, cliente, vendedor, red, fechaFactura, fechaVencimiento, efectivoGeneral, ncGeneral, chequeGeneral, "
                + "targetaGeneral, totalGeneral, descuentoGeneral, ivaGeneral, subtotalGeneral, comprobante, cotizacion, anulada, anula, credito, cxc, "
                + "usuario, rtIva, rtIca, rtFuente, otros, observacion, anulada1, anula1, credito1, cxc1, usuario1, fechaAlerta, terminal, estadoGeneral, "
                + "estado2, devuelta, factura, resolucion, fechaAnulacion, cuadreAnulacion, usuarioAnula, copago, placa, garantia, diasGarantia, rango, "
                + "terminos, notaAnulacion, conseMesa, producto, lista, cantidad, descuento, total, iva, subtotal, NC, utilidad, concepto, porcDescuento, "
                + "descripcion, plu, cant2, estado, porcIva, tercero, utilidad1, preparacion, impuesto, turno, impoGeneral, franquisia, comision, "
                + "valorComision, totalFacturaComision, imei, lote, idProd, mesFacturado, tarjetaCredito, totalPropina, porcPropina, idCosteo, hora,"
                + "porcImpo, impoconsumo, sisteCredito, bodega, costo, modeloContable "
                + "from bdFactura where factura = '" + id + "'; ";

        String[] colName = {
            "idFactura", "cliente", "vendedor", "red", "fechaFactura", "fechaVencimiento", "efectivoGeneral", "ncGeneral", "chequeGeneral",
            "targetaGeneral", "totalGeneral", "descuentoGeneral", "ivaGeneral", "subtotalGeneral", "comprobante", "cotizacion", "anulada",
            "anula", "credito", "cxc", "usuario", "rtIva", "rtIca", "rtFuente", "otros", "observacion", "anulada1", "anula1", "credito1", "cxc1",
            "usuario1", "fechaAlerta", "terminal", "estadoGeneral", "estado2", "devuelta", "factura", "resolucion", "fechaAnulacion", "cuadreAnulacion",
            "usuarioAnula", "copago", "placa", "garantia", "diasGarantia", "rango", "terminos", "notaAnulacion", "conseMesa", "producto", "lista",
            "cantidad", "descuento", "total", "iva", "subtotal", "NC", "utilidad", "concepto", "porcDescuento", "descripcion", "plu", "cant2", "estado",
            "porcIva", "tercero", "utilidad1", "preparacion", "impuesto", "turno", "impoGeneral", "franquisia", "comision", "valorComision", "totalFacturaComision",
            "imei", "lote", "idProd", "mesFacturado", "tarjetaCredito", "totalPropina", "porcPropina", "idCosteo", "hora", "porcImpo", "impoconsumo",
            "sisteCredito", "bodega", "costo", "modeloContable"};
        boolean[] cadena = {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, false, true, false, true,
            true, true, true, true, true, true, false, true, false, true, true, true, true, true, true, true, true, true, true, true,
            true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,
            true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,
            true, true, true, true, true, false, true, true, true};
        //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);

        ndFactura nodo = metodos.llenarFactura(data);

        return nodo;
    }

    public boolean agregarCuadreCaja(ndCaja nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdCuadre(id, fecha, hora, cajero, usuario,terminal, "
                + "primeraFactura, ultimaFactura, primerAbono, ultimoAbono, "
                + "efectivo, tarjeta, cheque, nc, total, sistema, diferencia,gasto,recogida,base, "
                + "totalVenta, totalCredito, totalContado, ncRealizadas, efectivoSistema, chequeSistema, tarjetaSistema, ncSistema,"
                + "contContado, contCredito, contNcRecibidas, contNcRealizadas, contEfectivo, contCheque, contTarjeta, contAbonos, "
                + "totalAbonos, totalFactSepares, totalCuotaInicial, contCuotaInicial,contGastos, totalncReembolsadas, contNcReembolsadas,"
                + "gastosManuales, abonosCartera, abonosSepare, ventasDomi, itemDomi, propinas, sisteCredito)"
                + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosCaja(nodo), metodos.pasarValoresCaja(nodo), instruccion_sql);
        return ok;
    }

    public boolean agregarOServicio(ndOServicio nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdOServicio(id, placa, tipo, modelo, numeroChasis, fechaCompra, marca, km, numeroMotor, color, problema)"
                + " values(?,?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosOServicio(nodo), null, instruccion_sql);
        return ok;
    }

    public boolean modificarOServicio(ndOServicio nodo) {
        boolean ok = false;
        String instruccion_sql = "update bdOServicio set placa=?, tipo=?, modelo=?, numeroChasis=?, fechaCompra=?, marca=?, km=?, numeroMotor=?, color=?, problema=? "
                + " where id='" + nodo.getId() + "' ;";
        ok = Actualizar_Registro(metodos.pasarDatosOServicio(nodo), null, instruccion_sql);
        return ok;
    }

    public DefaultTableModel getRegistrosTipoVehiculos() {
        String columNames[] = {"nombre"};
        String colName[] = {"Nombre"};
        String origen = " bdTipoVehiculo ";
        Object dtDatos[][] = GetTabla(colName, origen, (new StringBuilder()).append("select nombre from ").append(origen).toString());
        DefaultTableModel datos = new DefaultTableModel(dtDatos, columNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        return datos;
    }

    public DefaultTableModel getRegistrosMarcas() {
        String columNames[] = {"nombre"};
        String colName[] = {"Nombre"};
        String origen = " bdMarca ";
        Object dtDatos[][] = GetTabla(colName, origen, (new StringBuilder()).append("select nombre from ").append(origen).toString());
        DefaultTableModel datos = new DefaultTableModel(dtDatos, columNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        return datos;
    }

    public DefaultTableModel getRegistrosTipoProducto() {
        String columNames[] = {"nombre"};
        String colName[] = {"Nombre"};
        String origen = " bdTipoProducto ";
        Object dtDatos[][] = GetTabla(colName, origen, (new StringBuilder()).append("select nombre from ").append(origen).toString());
        DefaultTableModel datos = new DefaultTableModel(dtDatos, columNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        return datos;
    }

    public DefaultTableModel getRegistrosMedidas(String condicion) {
        String columNames[] = {
            "nombre", "detalle"
        };
        String colName[] = {
            "Nombre", "detalle"
        };
        String origen = " bdMedida ";
        Object dtDatos[][] = getDatos(colName, origen, "select nombre, detalle from bdMedida " + condicion, condicion);
        DefaultTableModel datos = new DefaultTableModel(dtDatos, columNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        return datos;
    }

    public DefaultTableModel getRegistrosColores() {
        String columNames[] = {"nombre"};
        String colName[] = {"Nombre"};
        String origen = " bdColores ";
        Object dtDatos[][] = GetTabla(colName, origen, (new StringBuilder()).append("select nombre from ").append(origen).toString());
        DefaultTableModel datos = new DefaultTableModel(dtDatos, columNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        return datos;
    }

    public DefaultTableModel getRegistrosTallas() {
        String columNames[] = {"nombre"};
        String colName[] = {"Nombre"};
        String origen = " bdTalla ";
        Object dtDatos[][] = GetTabla(colName, origen, (new StringBuilder()).append("select nombre from ").append(origen).toString());
        DefaultTableModel datos = new DefaultTableModel(dtDatos, columNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        return datos;
    }

    public boolean agregarTalla(String talla) {
        boolean ok = false;
        String instruccion_sql = "insert into bdTalla (nombre) values (?);";
        ok = Agregar_Registro(new Object[]{talla}, null, instruccion_sql);
        return ok;
    }

    public boolean agregarColor(String medida) {
        boolean ok = false;
        String instruccion_sql = "insert into bdColores (nombre) values (?);";
        ok = Agregar_Registro(new Object[]{medida}, null, instruccion_sql);
        return ok;
    }

    public boolean agregarTipoProd(String tipo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdTipoProducto (nombre) values (?);";
        ok = Agregar_Registro(new Object[]{tipo}, null, instruccion_sql);
        return ok;
    }

    public boolean agregarTipoVehiculo(String tipo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdTipoVehiculo (nombre) values (?);";
        ok = Agregar_Registro(new Object[]{tipo}, null, instruccion_sql);
        return ok;
    }

    public boolean agregarMarca(String marca) {
        boolean ok = false;
        String instruccion_sql = "insert into bdMarca (nombre) values (?);";
        ok = Agregar_Registro(new Object[]{marca}, null, instruccion_sql);
        return ok;
    }

    public boolean agregarPermiso(String id, String tipo, String valor, String nota, String fecha, String hora, String estado, String usuario) {
        boolean ok = false;
        String instruccion_sql = "insert into bdPermisos (Id, tipoSolicitud, valorSolicitud, nota, fecha, hora, usuario, estado) "
                + "values (?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(new Object[]{id, tipo, valor, nota, fecha, hora, usuario, estado}, null, instruccion_sql);
        return ok;
    }

    public boolean agregarMedida(String medida, Boolean detalle) {
        boolean ok = false;
        String instruccion_sql = "insert into bdMedida (nombre, detalle) values (?,?);";
        ok = Agregar_Registro(new Object[]{medida, detalle}, null, instruccion_sql);
        return ok;
    }

    public boolean agregarVerificarFactura(String factura, String cliente, String fact, String terminal, BigDecimal total, String fechaFactura,
            String fechaVence, String fechaAlerta, String vendedor, String consecutivo, String placa, String turno) {
        boolean ok = false;
        String instruccion_sql = "insert into bdVerificadorFacturas(idFactura, cliente, factura, terminal, fechaFactura, fechaVencimiento, "
                + "fechaAlerta, vendedor, consecutivo, placa, turno, total)"
                + " values(?,?,?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(new Object[]{factura, cliente, fact, terminal, fechaFactura, fechaVence, fechaAlerta,
            vendedor, consecutivo, placa, turno}, new Object[]{total}, instruccion_sql);
        return ok;
    }

    public boolean agregarFormaPago(String id, String factura, String idFormaPago, String formaPago, BigDecimal valor, String relacion, String fecha,
            String hora, String usuario) {
        boolean ok = false;
        String instruccion_sql = "insert into bdFormaPago(id, factura, idFormaPago, formaPago, relacion, fecha, hora, usuario, valor)"
                + " values(?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(new Object[]{id, factura, idFormaPago, formaPago, relacion, fecha, hora, usuario},
                new Object[]{valor}, instruccion_sql);
        return ok;
    }

    public boolean agregarAntecedente(String historia, String observaciones, String tipo, String antecedente) {
        boolean ok = false;
        String instruccion_sql = "insert into bdAntecedentes(historia, observaciones, tipo, antecedente)"
                + " values(?,?,?,?);";

        ok = Agregar_Registro(new Object[]{historia, observaciones}, new Object[]{tipo, antecedente}, instruccion_sql);
        return ok;
    }

    public boolean agregarProdPlantilla(String prod, String cant, String cod, boolean us) {
        boolean ok = false;
        String instruccion_sql = "insert into bdPlantilla(proceso, producto, ultimo, tiempo)"
                + " values(?,?,?,?);";

        ok = Agregar_Registro(new Object[]{prod, cod, us}, new Object[]{cant}, instruccion_sql);
        return ok;
    }

    public boolean agregarCodEgreso(String egreso, String codigo) {
        boolean ok = false;
        String instruccion_sql = "insert into codsEgresos(concepto,codigoUsuario)"
                + " values(?,?);";

        ok = Agregar_Registro(new Object[]{egreso, codigo}, null, instruccion_sql);
        return ok;
    }

    public String getNombreClienteFactura(String factura) {
        System.out.println("lo ultimo: " + factura);
        String instruccion_sql = "SELECT bdTerceros.nombre FROM bdFactura JOIN bdTerceros ON bdFactura.cliente = bdTerceros.IdSistema "
                + "WHERE bdFactura.factura = '" + factura + "' GROUP BY bdTerceros.nombre; ";
        //columnas de la tabla que se
        String[] colName = {"nombre"};
        boolean[] cadena = {true};

        try {
            System.out.println("Nombre retornado: " + GetRegistro(colName, cadena, instruccion_sql)[0].toString());
            return GetRegistro(colName, cadena, instruccion_sql)[0].toString();
        } catch (Exception e) {
            return "Sin_nombre";
        }
    }

    public String getIDPago(String factura, String tipo) {
        String instruccion_sql = "select id from bdFormaPago where factura = '" + factura + "' and formaPago = '" + tipo + "';";
        //columnas de la tabla que se
        String[] colName = {"Id"};
        boolean[] cadena = {true};
        try {
            return GetRegistro(colName, cadena, instruccion_sql)[0].toString();
        } catch (Exception e) {
            return null;
        }
    }

    public String getDatosColores(String cod) {
        String instruccion_sql = "select Id"
                + " from bdColores where nombre = '" + cod + "';";
        //columnas de la tabla que se
        String[] colName = {"Id"};
        boolean[] cadena = {true};
        try {
            return GetRegistro(colName, cadena, instruccion_sql)[0].toString();
        } catch (Exception e) {
            return null;
        }
    }

    public String getDatosCargos(String cod) {
        String instruccion_sql = "select Id from bdCargo where nombreCargo = '" + cod + "';";
        //columnas de la tabla que se
        String[] colName = {"Id"};
        boolean[] cadena = {true};
        try {
            return GetRegistro(colName, cadena, instruccion_sql)[0].toString();
        } catch (Exception e) {
            return null;
        }
    }

    public String getDatosTalla(String cod) {
        String instruccion_sql = "select Id"
                + " from bdTalla where nombre = '" + cod + "';";
        //columnas de la tabla que se
        String[] colName = {"Id"};
        boolean[] cadena = {true};
        try {
            return GetRegistro(colName, cadena, instruccion_sql)[0].toString();
        } catch (Exception e) {
            return null;
        }
    }

    public String getDatosTipoVehiculo(String cod) {
        String instruccion_sql = "select Id, nombre"
                + " from bdTipoVehiculo where nombre = '" + cod + "';";
        //columnas de la tabla que se
        String[] colName = {"Id", "nombre"};
        boolean[] cadena = {true, true};
        try {
            return GetRegistro(colName, cadena, instruccion_sql)[0].toString();
        } catch (Exception e) {
            return null;
        }
    }

    public String getDatosMarcas(String cod) {
        String instruccion_sql = "select Id, nombre"
                + " from bdMarca where nombre = '" + cod + "';";
        //columnas de la tabla que se
        String[] colName = {"Id", "nombre"};
        boolean[] cadena = {true, true};
        try {
            return GetRegistro(colName, cadena, instruccion_sql)[0].toString();
        } catch (Exception e) {
            return null;
        }
    }

    public String getDatosTipoProducto(String cod) {
        String instruccion_sql = "select Id, nombre "
                + " from bdTipoProducto where nombre = '" + cod + "';";
        //columnas de la tabla que se
        String[] colName = {"Id", "nombre"};
        boolean[] cadena = {true, true};
        try {
            return GetRegistro(colName, cadena, instruccion_sql)[0].toString();
        } catch (Exception e) {
            return null;
        }
    }

    public String getDatosMedidas(String cod) {
        String instruccion_sql = "select Id, nombre, detalle"
                + " from bdMedida where nombre = '" + cod + "';";
        //columnas de la tabla que se
        String[] colName = {"Id", "nombre", "detalle"};
        boolean[] cadena = {true, true, false};
        try {
            return GetRegistro(colName, cadena, instruccion_sql)[0].toString();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean agregarGrupo(String grupo) {
        boolean ok = false;
        String instruccion_sql = "insert into codsEgresos(concepto)"
                + " values(?);";

        ok = Agregar_Registro(new Object[]{grupo}, null, instruccion_sql);
        return ok;
    }

    public boolean agregarCxc(ndCxc nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdCxc(factura, tipo, estado, recibo, plazo, vencimiento, usuario, terminal, cuotas, factura2, valor)"
                + " values(?,?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosCxc(nodo), metodos.pasarValoresCxc(nodo), instruccion_sql);
        return ok;
    }

    public boolean agregarRegistroAbono(Object[] abono) {
        boolean ok = false;
        String instruccion_sql = "insert into bdAbono(id, numFactura, cliente, comprobante, abonoLetras, fecha, abono, totalFactura, abonosAnteriores, saldo, abonoActual)"
                + " values(?,?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(new Object[]{abono[0], abono[1], abono[2], abono[3], abono[5], abono[8], abono[10]},
                new Object[]{abono[4], abono[6], abono[7], abono[9]}, instruccion_sql);
        return ok;
    }

    public boolean agregarAbonoGeneral(Object[] abono) {
        boolean ok = false;
        String instruccion_sql = "insert into bdAbonoGeneral(abonoGeneral, abono, fecha, usuario, tercero, valor, enLetra, saldoTotal)"
                + " values(?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(new Object[]{abono[0], abono[1], abono[2], abono[3], abono[4], abono[5], abono[6]}, new Object[]{abono[7]}, instruccion_sql);
        return ok;
    }

    public boolean agregarInforme(Object[] datos) {
        boolean ok = false;
        String instruccion_sql = "insert into bdFormato1(Id, nit, fecha, vencimiento)"
                + " values(?,?,?,?);";

        ok = Agregar_Registro(datos, null, instruccion_sql);
        return ok;
    }

    public boolean agregarProducto(ndProducto nodo, String tabla) {
        boolean ok = false;
        String instruccion_sql = "insert into " + tabla + " (idSistema, codigo, codigoBarras, Descripcion, Grupo, Sub_grupo, Proveedor, Usuario, unidad, "
                + "referencia, ubicacion1,descripcion2,ubicacion2,cantidad2,descripcion3,ubicacion3,cantidad3,descripcion4,ubicacion4,cantidad4, "
                + "descripcion5,ubicacion5,cantidad5, descripcion6,ubicacion6,cantidad6, descripcion7,ubicacion7, cantidad7, descripcion8,ubicacion8,"
                + "cantidad8, plu2,plu3,plu4,plu5, plu6, plu7, plu8, terminal, claseBuscador, manejaInventario, porcentaje, compras, ventas, nc, "
                + "ajusteEntrada, ajusteSalida, planSepare, pedidos, anulacion, inventarioInicial, ajusteInventario, fisicoInventario, armado, costeo,"
                + "ordenServicio, congelada, trasladoBod, trasladoInternoEntrada, trasladoInternoSalida, impoconsumo, descripcionIngles, cubicaje, peso,"
                + "codContable, codArancel, tipoProducto, cantMedida, marca, tipoProd, enTransito, codigo2, codigo3, codigo4, codigo5, codigo6, codigo7,"
                + "codigo8, lenteMarco, adesivo, color, empaque, composicion, rmb, indVentas, descripcionLarga, notaDebito, IVA, L1, L2, L3, L4, L5, L6, L7, L8, minimo, "
                + "costo, minima, maxima, IVAC, ponderado, impoconsumoVenta, impoconsumoCompra)"
                + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosProducto(nodo), metodos.pasarValoresProducto(nodo), instruccion_sql);
        return ok;
    }

    public boolean modificarProducto(ndProducto nodo, String tabla) {
        boolean ok = false;
        String instruccion_sql = "update " + tabla + " set codigo=?, codigoBarras=?, Descripcion=?, Grupo=?, Sub_grupo=?, Proveedor=?, Usuario=?, unidad=?, referencia=?, "
                + "ubicacion1=?, descripcion2=?, ubicacion2=?, cantidad2=?, "
                + "descripcion3=?, ubicacion3=?, cantidad3=?, descripcion4=?, ubicacion4=?, cantidad4=?, "
                + "descripcion5=?, ubicacion5=?, cantidad5=?, descripcion6=?, ubicacion6=?, cantidad6=?, "
                + "descripcion7=?, ubicacion7=?, cantidad7=?, descripcion8=?, ubicacion8=?, cantidad8=?, "
                + "plu2=?, plu3=?, plu4=?, plu5=?, plu6=?, plu7=?, plu8=?, terminal=?, claseBuscador=?, manejaInventario=?, porcentaje=?, compras=?, ventas=?, nc=?, "
                + "ajusteEntrada=?, ajusteSalida=?, planSepare=?, pedidos=?, anulacion=?, inventarioInicial=?, ajusteInventario=?, fisicoInventario=?, "
                + "armado=?, costeo=?, ordenServicio=?, congelada=?, trasladoBod=?, trasladoInternoEntrada=?, trasladoInternoSalida=?, impoconsumo=?, "
                + "descripcionIngles=?, cubicaje=?, peso=?, codContable=?, codArancel=?, tipoProducto=?, cantMedida=?, marca=?, tipoProd=?, enTransito=?, "
                + "codigo2=?, codigo3=?, codigo4=?, codigo5=?, codigo6=?, codigo7=?, codigo8=?, lenteMarco=?, adesivo=?, color=?, empaque=?, composicion=?, rmb=?, indVentas=?, "
                + "descripcionLarga=?, notaDebito=?, IVA=?, L1=?, L2=?, L3=?, L4=?, L5=?, L6=?, L7=?, L8=?, minimo=?, costo=?, minima=?, maxima=?, IVAC=?, ponderado=?, impoconsumoVenta=?, "
                + "impoconsumoCompra=? where idSistema ='" + nodo.getIdSistema() + "' ;";
        ok = Actualizar_Registro(metodos.pasarDatosProducto(nodo), metodos.pasarValoresProducto(nodo), instruccion_sql);
        return ok;
    }

    public ndProducto getDatosProducto(String cod, String tabla) {
        String instruccion_sql = "select idSistema, codigo, codigoBarras, Descripcion, Grupo, Sub_grupo, Proveedor, IVA, L1, L2, L3, L4, L5, L6, L7, L8, Usuario, minimo, unidad, "
                + "referencia,costo,minima, maxima,ubicacion1,descripcion2,ubicacion2,cantidad2,descripcion3,ubicacion3,cantidad3, descripcion4, ubicacion4,"
                + "cantidad4, descripcion5, ubicacion5, cantidad5, descripcion6, ubicacion6, cantidad6, descripcion7, ubicacion7, cantidad7, descripcion8, "
                + "ubicacion8, cantidad8, plu2, plu3, plu4, plu5, plu6, plu7, plu8, IVAC, ponderado, terminal, inventario, claseBuscador, manejaInventario, "
                + "porcentaje, compras, ventas, nc, ajusteEntrada, ajusteSalida, planSepare, pedidos, anulacion, inventarioInicial, ajusteInventario, fisicoInventario, "
                + "armado, costeo, ordenServicio, congelada, trasladoBod, trasladoInternoEntrada, trasladoInternoSalida, impoconsumo, descripcionIngles, cubicaje, peso, codContable, "
                + "codArancel, tipoProducto, cantMedida, marca, tipoProd, enTransito, codigo2, codigo3, codigo4, codigo5, codigo6, codigo7, codigo8, lenteMarco, "
                + "adesivo, color, empaque, composicion, rmb, indVentas, impoconsumoVenta, impoconsumoCompra, descripcionLarga, notaDebito from " + tabla + " where Codigo = '" + cod + "' OR codigoBarras = '" + cod + "' "
                + " OR idSistema = '" + cod + "' OR codigo2 = '" + cod + "' OR codigo3 = '" + cod + "' OR codigo4 = '" + cod + "' "
                + " OR codigo5 = '" + cod + "' OR codigo6 = '" + cod + "' OR codigo7 = '" + cod + "' OR codigo8 = '" + cod + "' ;";
//                + "from " + tabla + " where Codigo = '" + cod + "' OR (codigoBarras = '" + cod + "' and codigoBarras <> '') OR idSistema = '" + cod + "'; ";
        //columnas de la tabla que se
        String[] colName = {"idSistema", "codigo", "codigoBarras", "Descripcion", "Grupo", "Sub_grupo", "Proveedor", "IVA", "L1", "L2", "L3", "L4", "L5", "L6", "L7", "L8", "Usuario", "minimo",
            "unidad", "referencia", "costo", "minima", "maxima", "ubicacion1", "descripcion2", "ubicacion2", "cantidad2", "descripcion3", "ubicacion3",
            "cantidad3", "descripcion4", "ubicacion4", "cantidad4", "descripcion5", "ubicacion5", "cantidad5", "descripcion6", "ubicacion6", "cantidad6",
            "descripcion7", "ubicacion7", "cantidad7", "descripcion8", "ubicacion8", "cantidad8", "plu2", "plu3", "plu4", "plu5", "plu6", "plu7", "plu8",
            "IVAC", "ponderado", "terminal", "inventario", "claseBuscador", "manejaInventario", "porcentaje", "compras", "ventas", "nc", "ajusteEntrada",
            "ajusteSalida", "planSepare", "pedidos", "anulacion", "inventarioInicial", "ajusteInventario", "fisicoInventario", "armado", "costeo", "ordenServicio", "congelada",
            "trasladoBod", "trasladoInternoEntrada", "trasladoInternoSalida", "impoconsumo", "descripcionIngles", "cubicaje", "peso", "codContable", "codArancel", "tipoProducto",
            "cantMedida", "marca", "tipoProd", "enTransito", "codigo2", "codigo3", "codigo4", "codigo5", "codigo6", "codigo7", "codigo8", "lenteMarco", "adesivo",
            "color", "empaque", "composicion", "rmb", "indVentas", "impoconsumoVenta", "impoconsumoCompra", "descripcionLarga", "notaDebito"};
        boolean[] cadena = {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,
            true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, false, false, false, false, false,
            false, false, true, true, true, true, true, false, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,
            false, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true};
        //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        ndProducto nodo = metodos.llenarProducto(data);
        return nodo;
    }

    public boolean agregarProceso(ndProceso nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdProceso(codigo, nombre, und, observaciones)"
                + " values(?,?,?,?);";

        ok = Agregar_Registro(metodos.pasarDatosProceso(nodo), null, instruccion_sql);
        return ok;
    }

    public boolean agregarBodega(ndBodega nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdBodegas(codigo, nombre, direccion, telefono, usuario, ciudad, abreviado, filas, columnas, tipo)"
                + " values(?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosBodega(nodo), null, instruccion_sql);
        return ok;
    }

    public boolean modificarBodega(ndBodega nodo) {
        boolean ok = false;
        String instruccion_sql = "update bdBodegas set nombre=?, direccion=?, telefono=?, usuario=?, ciudad=?, abreviado=?, filas=?, columnas=?, tipo=? "
                + "where codigo = '" + nodo.getCodigo() + "' ;";
        ok = Actualizar_Registro(metodos.pasarDatosBodega(nodo), null, instruccion_sql);
        return ok;
    }

    public boolean agregarIngreso(ndIngreso nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdIngreso(id, proveedor, fechaFactura, fechaVencimiento,"
                + "tipo, concepto, credito, cxp, usuario,terminal, observaciones, hora, estado, bodega, modeloContable, total, descuentos, iva, "
                + "subtotal, rtIva, rtFuente, porRtFuente, impoconsumo, rtIca, efectivo, cheque, tarjeta, descFinanciero, descProntoPago)"
                + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosIngreso(nodo), metodos.pasarValoresIngreso(nodo), instruccion_sql);
        return ok;
    }

    public boolean modificarIngreso(ndIngreso nodo) {
        //LISTO
        boolean ok = false;
        String instruccion_sql = "update bdIngreso set proveedor=?, fechaFactura=?, fechaVencimiento=?,"
                + "tipo=?, concepto=?, credito=?, cxp=?, usuario=?,terminal=?, observaciones=?, hora=?, estado=?, bodega=?, modeloContable=?, total=?, descuentos=?, iva=?, "
                + "subtotal=?, rtIva=?, rtFuente=?, porRtFuente=?, impoconsumo=?, rtIca=?, efectivo=?, cheque=?, tarjeta=?, descFinanciero=?, descProntoPago=? "
                + "where Id='" + nodo.getId() + "' ;";

        ok = Actualizar_Registro(metodos.pasarDatosIngreso(nodo), metodos.pasarValoresIngreso(nodo), instruccion_sql);
        return ok;
    }

    public Object[][] getProductosInvInicialKardex1(String prod) {
        String colName[] = {
            "producto", "total", "costo"};
        String instruccion_sql = "select producto, total, costo from bdInventarioInicial where producto = '" + prod + "' ";
        Object dtDatos[][] = getDatos(colName, " bdInventarioInicial ", instruccion_sql, " where producto ='" + prod + "' ");

        return dtDatos;
    }

    public Object[][] getProductosInvInicialKardex(String prod) {
        String colName[] = {
            "ingreso", "cantidad", "valor", "descuento", "subtotal", "iva", "porIva", "total"};
        String instruccion_sql = "select ingreso, cantidad, valor, descuento, subtotal, iva, porIva, total from bdCompra where producto = '" + prod + "' and ingreso = 'ING-0' ";
        Object dtDatos[][] = getDatos(colName, " bdCompra ", instruccion_sql, " where producto ='" + prod + "'  and ingreso = 'ING-0' ");

        return dtDatos;
    }

    public Object[][] getProductosComprasKardex(String prod) {
        String colName[] = {
            "ingreso", "cantidad", "valor", "descuento", "subtotal", "iva", "porIva", "total"};
        String instruccion_sql = "select ingreso, cantidad, valor, descuento, subtotal, iva, porIva, total from bdCompra where producto = '" + prod + "' and ingreso <> 'ING-0' ";
        Object dtDatos[][] = getDatos(colName, " bdCompra ", instruccion_sql, " where producto ='" + prod + "'  and ingreso <> 'ING-0' ");
        return dtDatos;
    }

    public Object[][] getAjustesMovimientos(String prod) {
        String colName[] = {
            "Id", "origen", "tipo", "fecha", "valor", "usuario"};
        String instruccion_sql = "select Id, origen, tipo, fecha, valor, usuario from bdTraslados where Id = '" + prod + "' ";
        Object dtDatos[][] = getDatos(colName, " bdTraslados ", instruccion_sql, " where Id = '" + prod + "' ");
        return dtDatos;
    }

    public Object[][] getPrestamosMovimientos(String prod) {
        String colName[] = {
            "Id", "origen", "tipo", "fecha", "valor", "usuario"};
        String instruccion_sql = "select Id, origen, tipo, fecha, valor, usuario from bdTrasladosBodegas where Id = '" + prod + "' ";
        Object dtDatos[][] = getDatos(colName, " bdTrasladosBodegas ", instruccion_sql, " where Id = '" + prod + "' ");
        return dtDatos;
    }

    public Object[][] getVentasMovimientos(String prod, Boolean estado) {
        String colName[] = {
            "idFactura", "cliente", "nombre", "fechaFactura", "subtotalGeneral", "descuentoGeneral", "ivaGeneral", "totalGeneral", "usuario", "notaAnulacion"};
        String instruccion_sql = "select idFactura, cliente, nombre, fechaFactura, subtotalGeneral, descuentoGeneral, ivaGeneral, totalGeneral, "
                + "usuario, notaAnulacion from facturacion where factura = '" + prod + "' ";
        Object dtDatos[][] = getDatos(colName, " facturacion ", instruccion_sql, " where factura = '" + prod + "' ");
        return dtDatos;
    }

    public Object[][] getNotasCreditoMovimientos(String prod) {
        String colName[] = {
            "Id", "idCliente", "nombre", "fechaFactura", "total", "usuario"};
        String instruccion_sql = "select Id, idCliente, nombre, fechaFactura, total, usuario from notasCredito where Id = '" + prod + "' ";
        Object dtDatos[][] = getDatos(colName, " notasCredito ", instruccion_sql, " where Id = '" + prod + "' ");
        return dtDatos;
    }

    public Object[][] getSeparesMovimientos(String fact) {
        String colName[] = {
            "idFactura", "IdCliente", "nombre", "fechaFactura", "subtotalGeneral", "descuentoGeneral", "ivaGeneral", "totalGeneral", "usuario"};
        String instruccion_sql = "select idFactura, IdCliente, nombre, fechaFactura, subtotalGeneral, descuentoGeneral, ivaGeneral, totalGeneral, "
                + "usuario from separacion where idFactura = '" + fact + "' ";
        Object dtDatos[][] = getDatos(colName, " separacion ", instruccion_sql, " where idFactura = '" + fact + "' ");
        return dtDatos;
    }

    public Object[][] getCongeladasMovimientos(String fact) {
        String colName[] = {
            "idFactura", "idCliente", "nombre", "fechaFactura", "subtotalGeneral", "descuentoGeneral", "ivaGeneral", "totalGeneral", "usuario"};
        String instruccion_sql = "select idFactura, idCliente, nombre, fechaFactura, subtotalGeneral, descuentoGeneral, ivaGeneral, totalGeneral, "
                + "usuario from congeladas where idFactura = '" + fact + "' ";
        Object dtDatos[][] = getDatos(colName, " congeladas ", instruccion_sql, " where idFactura = '" + fact + "' ");
        return dtDatos;
    }

    public Object[][] getComprasMovimientos(String prod) {
        String colName[] = {
            "ingreso", "idTercero", "nombre", "fechaFactura", "sub", "descuentos", "IVAA", "tot", "usuario"};
        String instruccion_sql = "select ingreso, idTercero, nombre, fechaFactura, sub, descuentos, IVAA, tot, usuario "
                + "from ingreso where ingreso = '" + prod + "' ";
        Object dtDatos[][] = getDatos(colName, " ingreso ", instruccion_sql, " where ingreso ='" + prod + "' ");
        return dtDatos;
    }

    public Object[][] getProductosCongeladaImei(String imei) {
        String colName[] = {
            "idFactura", "cantidad", "lista", "descuento", "subtotal", "iva", "porcIva", "total"};
        String instruccion_sql = "select idFactura, cantidad, lista, descuento, subtotal, iva, porcIva, total from bdCongelada "
                + "where imei LIKE '" + imei + "%' and anulada = false ";
        Object dtDatos[][] = getDatos(colName, " bdCongelada ", instruccion_sql, " where imei LIKE '" + imei + "%' and anulada = false  ");
        return dtDatos;
    }

    public Object[][] getProductosVentasImei(String imei) {
        String colName[] = {
            "factura", "cantidad", "lista", "descuento", "subtotal", "iva", "porcIva", "total"};
        String instruccion_sql = "select factura, cantidad, lista, descuento, subtotal, iva, porcIva, total from bdFactura where imei LIKE '" + imei + "%' and anulada = false ";
        Object dtDatos[][] = getDatos(colName, " bdFactura ", instruccion_sql, " where imei LIKE"
                + " '" + imei + "%' and anulada = false  ");

        return dtDatos;
    }

    public Object[][] getProductosNotasCreditoImei(String imei) {
        String colName[] = {
            "NC", "cantidad", "lista", "descuento", "subtotal", "iva", "porcIva", "total"};
        String instruccion_sql = "select NC, cantidad, lista, descuento, subtotal, iva, porcIva, total from bdNotasCredito "
                + " where imei LIKE '" + imei + "%' ";
        Object dtDatos[][] = getDatos(colName, " bdNotasCredito ", instruccion_sql, " where imei = '" + imei + "' ");

        return dtDatos;
    }

    public Object[][] getProductosSeparesImei(String imei) {
        String colName[] = {
            "factura", "cantidad", "lista", "descuento", "subtotal", "iva", "porcIva", "total"};
        String instruccion_sql = "select factura, cantidad, lista, descuento, subtotal, iva, porcIva, total from bdPlanSepare where imei LIKE '" + imei + '%' + "' and anulada = false ";
        Object dtDatos[][] = getDatos(colName, " bdPlanSepare ", instruccion_sql, " where imei LIKE '" + imei + '%' + "' and anulada = false  ");

        return dtDatos;
    }

    public Object[][] getProductosVentasAnuladasImei(String imei) {
        String colName[] = {
            "factura", "cantidad", "lista", "descuento", "subtotal", "iva", "porcIva", "total"};
        String instruccion_sql = "select factura, cantidad, lista, descuento, subtotal, iva, porcIva, total from bdFactura where imei LIKE '" + imei + "%' and anulada = true ";
        Object dtDatos[][] = getDatos(colName, " bdFactura ", instruccion_sql, " where imei LIKE '" + imei + "%' and anulada = true  ");

        return dtDatos;
    }

    public Object[][] getProductosComprasImei(String imei) {
        String colName[] = {
            "id", "imei"};
        String instruccion_sql = "select id, imei from compraImei where imei = '" + imei + "' ";
        Object dtDatos[][] = getDatos(colName, " compraImei ", instruccion_sql, " where imei = '" + imei + "' ");

        return dtDatos;
    }

    public Object[][] getProductosPrestamos(String imei, String tipo) {
        String colName[] = {
            "traslado", "imei"};
        String instruccion_sql = "select traslado, imei from prestamosImei where imei = '" + imei + "' and tipo = '" + tipo + "' ";
        Object dtDatos[][] = getDatos(colName, " prestamosImei ", instruccion_sql, " where imei = '" + imei + "' and tipo = '" + tipo + "'  ");

        return dtDatos;
    }

    public Object[][] getProductosAjustes(String imei, String tipo) {
        String colName[] = {
            "traslado", "imei"};
        String instruccion_sql = "select traslado, imei from ajustesImei where imei LIKE '" + imei + "%' and tipo = '" + tipo + "' ";
        Object dtDatos[][] = getDatos(colName, " ajustesImei ", instruccion_sql, " where imei LIKE '" + imei + "%' and tipo = '" + tipo + "'  ");

        return dtDatos;
    }

    public Object[][] getProductosPrestamos(String imei) {
        String colName[] = {
            "traslado", "imeil"};
        String instruccion_sql = "select traslado, imeil from bdTrasladosBodegasProd where imeil LIKE '" + imei + "%' and estado = 'PENDIENTE' ";
        Object dtDatos[][] = getDatos(colName, " bdTrasladosBodegasProd ", instruccion_sql, " where imeil LIKE '" + imei + "%' and estado = 'PENDIENTE' ");

        return dtDatos;
    }

    public Object[][] getProductosAjustesKardex(String prod, String tabla, String bodega) {
        String colName[] = {
            "Id", "fecha", "cantidad", "lista", "total", "usuario"};

        String condicion = "";
        if (bodega.equals("123-22")) {
            condicion = " where producto = '" + prod + "' and (bodega = '" + bodega + "' or bodega = ''); ";
        } else {
            condicion = " where producto = '" + prod + "' and bodega = '" + bodega + "' ; ";
        }

        String instruccion_sql = "select Id, fecha, cantidad, lista, total, usuario from " + tabla + condicion;
        Object dtDatos[][] = getDatos(colName, tabla, instruccion_sql, condicion);

        return dtDatos;
    }

    public Object[][] getProductosAjustesKardex1(String prod, String tabla) {
        String colName[] = {
            "Id", "fecha", "cantidad", "lista", "total", "usuario"};
        String instruccion_sql = "select Id, fecha, cantidad, lista, total, usuario from " + tabla + " where producto = '" + prod + "' ";
        Object dtDatos[][] = getDatos(colName, tabla, instruccion_sql, " where producto = '" + prod + "' ");

        return dtDatos;
    }

    public Object[][] getProductosNotaCreditoKardex(String prod, String bodega) {
        String colName[] = {
            "factura", "idTercero", "nombre", "fecha", "lista", "cantidad", "subtotal", "descuento", "valIva", "porIva", "total", "usuario"};

        String condicion = "";
        if (bodega.equals("123-22")) {
            condicion = " where producto = '" + prod + "' and (bodega = '" + bodega + "' or bodega = ''); ";
        } else {
            condicion = " where producto = '" + prod + "' and bodega = '" + bodega + "' ; ";
        }

        String instruccion_sql = "select factura, idTercero, nombre, fecha, lista, cantidad, subtotal, descuento, valIva, porIva, total, usuario"
                + " from notasCredito1 " + condicion;
        Object dtDatos[][] = getDatos(colName, "notasCredito1", instruccion_sql, condicion);

        return dtDatos;
    }

    public Object[][] getProductosAnuladaKardex(String prod, String tabla, String campo, String bodega) {
        String colName[] = {
            "factura", campo, "nombre", "fechaFactura", "lista", "cantidad", "subtotal", "descuento", "iva", "porcIva", "total", "usuario"};

        String condicion = "";
        if (bodega.equals("123-22")) {
            condicion = " where producto = '" + prod + "' and (bodega = '" + bodega + "' or bodega = ''); ";
        } else {
            condicion = " where producto = '" + prod + "' and bodega = '" + bodega + "' ; ";
        }

        String instruccion_sql = "select factura, " + campo + ", nombre, fechaFactura, lista, cantidad, subtotal, descuento, iva, porcIva, total, usuario"
                + " from " + tabla + condicion;
        Object dtDatos[][] = getDatos(colName, tabla, instruccion_sql, condicion);
        return dtDatos;
    }

    public Object[][] getProductosTipoSepareKardex(String prod, String tabla, String campo, String bodega) {
        String colName[] = {
            "factura", campo, "nombre", "fechaFactura", "lista", "cantidad", "subtotal", "descuento", "iva", "porcIva", "total", "usuario"};

        String condicion = "";
        if (bodega.equals("123-22")) {
            condicion = " where producto = '" + prod + "' and anulada = false and estado2 = '' and (bodega = '" + bodega + "' or bodega = ''); ";
        } else {
            condicion = " where producto = '" + prod + "' and anulada = false and estado2 = '' and bodega = '" + bodega + "' ; ";
        }

        String instruccion_sql = "select factura, " + campo + ", nombre, fechaFactura, lista, cantidad, subtotal, descuento, iva, porcIva, total, usuario"
                + " from " + tabla + condicion;

        Object dtDatos[][] = getDatos(colName, tabla, instruccion_sql, condicion);

        return dtDatos;
    }

    public Object[][] getProductosCongeladaKardex(String prod, String tabla, String campo, String bodega) {
        String colName[] = {
            "idFactura", campo, "nombre", "fechaFactura", "lista", "cantidad", "subtotal", "descuento", "iva", "porcIva", "total", "usuario"};

        String condicion = "";
        if (bodega.equals("123-22")) {
            condicion = " where producto = '" + prod + "' and anulada = false and (bodega = '" + bodega + "' or bodega = ''); ";
        } else {
            condicion = " where producto = '" + prod + "' and anulada = false and bodega = '" + bodega + "' ; ";
        }

        String instruccion_sql = "select idFactura, " + campo + ", nombre, fechaFactura, lista, cantidad, subtotal, descuento, iva, porcIva, total, usuario"
                + " from " + tabla + condicion;
        Object dtDatos[][] = getDatos(colName, tabla, instruccion_sql, condicion);

        return dtDatos;
    }

    public Object[][] getProductosTipoFacturacionKardex(String prod, String tabla, String bodega) {
        String colName[] = {
            "factura", "cliente", "nombre", "fechaFactura", "lista", "cantidad", "subtotal", "descuento", "iva", "porcIva", "total", "usuario"};

        String condicion = "";
        if (bodega.equals("123-22")) {
            condicion = " where producto = '" + prod + "' and anulada = false and (bodega = '" + bodega + "' or bodega = '') ";
        } else {
            condicion = " where producto = '" + prod + "' and anulada = false and bodega = '" + bodega + "' ";
        }

        String instruccion_sql = "select factura, cliente, nombre, fechaFactura, lista, cantidad, subtotal, descuento, iva, porcIva, total, usuario"
                + " from " + tabla + condicion;
        Object dtDatos[][] = getDatos(colName, tabla, instruccion_sql, condicion);
        return dtDatos;
    }

    public Object[][] getProveedorIngreso(String prod, String bodega) {
        String colName[] = {
            "codigo", "nombre", "fechaFactura", "usuario"};

        String condicion = "";
        if (bodega.equals("123-22")) {
            condicion = " where ingreso = '" + prod + "' and (bodega = '" + bodega + "' or bodega = '') ";
        } else {
            condicion = " where ingreso = '" + prod + "' and bodega = '" + bodega + "' ";
        }

        String instruccion_sql = "select codigo, nombre, fechaFactura, usuario from ingresoGeneral " + condicion;

        Object dtDatos[][] = getDatos(colName, " ingresoGeneral ", instruccion_sql, condicion);
        return dtDatos;
    }

    public Object[][] getProductosCargarCompra(String ingreso) {
        String colName[] = {
            "producto", "cantidad", "descripcion", "plu", "cant2", "valor", "descuento", "subtotal", "tipoDescuento"};
        String instruccion_sql = "select producto, cantidad, descripcion, plu, cant2, valor, descuento, subtotal, tipoDescuento from bdCompra where ingreso='" + ingreso + "'";
        Object dtDatos[][] = getDatos(colName, " bdCompra ", instruccion_sql, " where ingreso='" + ingreso + "'");

        return dtDatos;
    }

    public Object[][] getProductosCargarCompraEx(String ingreso) {
        String colName[] = {
            "producto", "ref", "valor", "cantidad", "tienda", "arancel", "marca", "tipo", "cmb", "kg", "descripcion", "undMedida", "empaque",
            "precio1", "precio2", "precio3"};
        String instruccion_sql = "select producto, ref, valor, cantidad, tienda, arancel, marca, tipo, cmb, kg, descripcion, undMedida,"
                + " empaque from bdCompraEx where ingreso='" + ingreso + "'";
        Object dtDatos[][] = getDatos(colName, " bdCompraEx ", instruccion_sql, " where ingreso='" + ingreso + "'");

        return dtDatos;
    }

    public ndIngreso getDatosIngreso(String Id) {
        boolean ok = false;
        String instruccion_sql = "select id, proveedor, fechaFactura, fechaVencimiento, total, descuentos, iva, subtotal, tipo, concepto, "
                + "credito, cxp, usuario,terminal, rtIva, rtFuente, porRtFuente, observaciones, hora, impoconsumo, estado, rtIca, efectivo, "
                + "cheque, tarjeta, descFinanciero, descProntoPago, bodega, modeloContable "
                + " from bdIngreso where Id = '" + Id + "'; ";
        String[] colName = {"id", "proveedor", "fechaFactura", "fechaVencimiento", "total", "descuentos", "iva", "subtotal", "tipo", "concepto",
            "credito", "cxp", "usuario", "terminal", "rtIva", "rtFuente", "porRtFuente", "observaciones", "hora", "impoconsumo", "estado",
            "rtIca", "efectivo", "cheque", "tarjeta", "descFinanciero", "descProntoPago", "bodega", "modeloContable"};
        boolean[] cadena = {true, true, true, true, true, true, true, true, true, true, false, true, true, true, true, true, true, true,
            true, true, true, true, true, true, true, true, true, true, true};
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        ndIngreso nodo = metodos.llenarIngreso(data);
        return nodo;
    }

    public boolean agregarCosteo(String id, String fecha, String usuario, String unidades, String producto, String tipo, BigDecimal total, String bodega) {
        boolean ok = false;
        String instruccion_sql = "insert into bdCosteo(Id, fecha, usuario, producto, tipo, bodega, unidades, total)"
                + " values(?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(new Object[]{id, fecha, usuario, producto, tipo, bodega}, new Object[]{unidades, total}, instruccion_sql);
        return ok;
    }

    public boolean agregarCosteoProd(String id, String producto, String cantidad, String total) {
        boolean ok = false;
        String instruccion_sql = "insert into bdCosteoProd(documento, producto, cantidad, total)"
                + " values(?,?,?,?);";

        ok = Agregar_Registro(new Object[]{id, producto}, new Object[]{cantidad, total}, instruccion_sql);
        return ok;
    }

    public boolean agregarCompra(ndCompra nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdCompra(ingreso, producto, cantidad, porIva,descripcion,plu, cant2, bodega, usuario, tipoDescuento, "
                + "valor, descuento, total, iva, subtotal,valorPlu, impoconsumo, porcImpo)"
                + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosCompra(nodo), metodos.pasarValoresCompra(nodo), instruccion_sql);
        return ok;
    }

    public boolean modificarCompra(ndCompra nodo) {
        boolean ok = false;
        String instruccion_sql = "update bdCompra set ingreso=?, producto=?, cantidad=?, porIva=?,descripcion=?, plu=?, cant2=?, bodega=?, usuario=?, valor=?,"
                + " descuento=?, total=?, iva=?, subtotal=?, valorPlu=?, impoconsumo=? where producto ='" + nodo.getProducto() + "';";
        ok = Agregar_Registro(metodos.pasarDatosCompra(nodo), metodos.pasarValoresCompra(nodo), instruccion_sql);
        return ok;
    }

    public boolean agregarNc(ndNc nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdNc(Id, cliente, factura, anulada, usuario, fecha, vendedor,terminal, resolucion, "
                + "reembolsar, hora, bodega, observaciones, valor, restante)"
                + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        ok = Agregar_Registro(metodos.pasarDatosNC(nodo), metodos.pasarValoresNC(nodo), instruccion_sql);
        return ok;
    }

    public ndNc getDatosNotaCredito(String id) {
        String instruccion_sql = "Select Id, cliente, factura, valor, restante, anulada, usuario, fecha, vendedor,terminal, resolucion, "
                + "reembolsar, hora, bodega, observaciones from bdNc where id = '" + id + "'; ";
        //columnas de la tabla que se
        String[] colName = {"Id", "cliente", "factura", "valor", "restante", "anulada", "usuario", "fecha", "vendedor", "terminal",
            "resolucion", "reembolsar", "hora", "bodega", "observaciones"};
        boolean[] cadena = {true, true, true, true, true, false, true, true, true, true, true, false, true, true, true};
        //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        ndNc nodo = metodos.llenarNc(data);
        return nodo;
    }

    public boolean agregarTraslado(ndTraslado nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdTraslados(id, origen, destino, fecha, unidades, tipo, usuario, terminal, hora, bodega, modeloContable, valor)"
                + " values(?,?,?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosTraslado(nodo), metodos.pasarValoresTraslado(nodo), instruccion_sql);
        return ok;
    }

    public boolean agregarTrasladoInterno(ndTrasladoBodega nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdTrasladosInternos(id, origen, destino, fecha, unidades, tipo, devolucion, usuario, terminal, "
                + "diasPlazo, estado, hora, valor)"
                + " values(?,?,?,?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosTraslado(nodo), metodos.pasarValoresTraslado(nodo), instruccion_sql);
        return ok;
    }

    public boolean agregarTrasladoBodega(ndTrasladoBodega nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdTrasladosBodegas(id, origen, destino, fecha, unidades, tipo, devolucion, usuario, terminal, "
                + "diasPlazo, estado, hora, valor)"
                + " values(?,?,?,?,?,?,?,?,?,?,?,?,?);";

        ok = Agregar_Registro(metodos.pasarDatosTraslado(nodo), metodos.pasarValoresTraslado(nodo), instruccion_sql);
        return ok;
    }

    public ndTrasladoBodega getDatosTraslados(String cod) {
        String instruccion_sql = "select id, origen, destino, fecha, unidades, valor, tipo, devolucion, usuario, terminal, diasPlazo,"
                + "estado, hora from bdTrasladosBodegas where id = '" + cod + "'; ";
        //columnas de la tabla que se
        String[] colName = {"id", "origen", "destino", "fecha", "unidades", "valor", "tipo", "devolucion", "usuario", "terminal", "diasPlazo",
            "estado", "hora"};
        boolean[] cadena = {true, true, true, true, true, true, true, true, true, true, true, true, true};
        //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        ndTrasladoBodega nodo = metodos.llenarTrasladoBodega(data);

        return nodo;
    }

    public boolean agregarProductosAjustes(ndProductoAjustes nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdTrasladosProd(traslado, producto, cantidad, observacion, descripcion, plu, cantidad2, idProd, "
                + "imei, talla, color, porcIva, porcImpo, lista, total, subtotal, iva, costo, impoconsumo)"
                + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosProductosAjustes(nodo), metodos.pasarValoresProductosAjustes(nodo), instruccion_sql);
        return ok;
    }

    public boolean agregarTrasladoInterno(ndProdTraslBodega nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdTrasladosInternosProd(traslado, producto, imeil, observacion, lote, estado, facturados, "
                + "devueltos, idProd, plu, cantidad, lista, total, cant2, iva)"
                + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosTraslado(nodo), metodos.pasarValoresTraslado(nodo), instruccion_sql);
        return ok;
    }

    public boolean agregarProductosAdicionales(ndProdTraslBodega nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdProductosServiciosAdiciones(traslado, producto, imeil, observacion, lote, estado, facturados, "
                + "devueltos, idProd, plu, cantidad, lista, total, cant2, iva)"
                + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosTraslado(nodo), metodos.pasarValoresTraslado(nodo), instruccion_sql);
        return ok;
    }

    public boolean agregarTrasladoBodega(ndProdTraslBodega nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdTrasladosBodegasProd(traslado, producto, imeil, observacion, lote, estado, facturados, "
                + "devueltos, idProd, plu, cantidad, lista, total, cant2, iva)"
                + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosTraslado(nodo), metodos.pasarValoresTraslado(nodo), instruccion_sql);
        return ok;
    }

    public boolean agregarEgreso(ndEgreso nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdEgreso(Id, nit, razon, telefono, direccion, cred1, cred2, deb1, deb2, fecha, documento,"
                + "usuario,terminal,estado,tipo, ingresoAsociado, egreso, modeloContable, total, cheque, banco, efectivo,subtotal,iva)"
                + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosEgreso(nodo), metodos.pasarValoresEgreso(nodo), instruccion_sql);
        return ok;
    }

    public boolean agregarEgreso(ModeloDetalleEgreso nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdEgresoCods(egreso, codigo, descripcion, factura,codigoUsuario, valor,subtotal,iva)"
                + " values(?,?,?,?,?,?,?,?);";

        ok = Agregar_Registro(metodos.pasarDatosEgreso(nodo), metodos.pasarValoresEgreso(nodo), instruccion_sql);
        return ok;
    }

//                                     ACTUALAZACION DE REGISTROS
/* ------------------------------------------------------------------------------------- */
    public boolean aumentarConsecutivo(String id, int num) {
        boolean ok = false;
        if (Integer.parseInt(getNumConsecutivo(id)[0].toString()) < num) {
            String instruccion_sql = "update bdConsecutivos set numero=?" + " where Id='" + id + "' ;";
            ok = Actualizar_Registro(new Object[]{"", String.valueOf(num)}, null, instruccion_sql);
            if (id.equals("COTI") || id.equals("OSERV")) {
                actualizarEstadoFact("OFF", id);
            }
            return ok;
        }
        return true;
    }
    
    public boolean aumentarTurno(String turno) {
        boolean ok = false;
        String instruccion_sql = "update bdMaestra set turno1 = ? ";
        ok = Actualizar_Registro(new Object[]{"", turno}, null, instruccion_sql);
        return ok;
    }

    public boolean modificarCampoTercero(String id, String nombre, String campo) {
        boolean ok = false;
        String instruccion_sql = "update bdTerceros set " + campo + "=? where idSistema = '" + id + "' ;";
        ok = Actualizar_Registro(new Object[]{"", nombre}, null, instruccion_sql);
        return ok;
    }

    public boolean cambiarMesa(String factura, String conse) {
        boolean ok = false;
        String instruccion_sql = "update bdCongelada set conseMesa = ? where idFactura = '" + factura + "'; ";
        ok = Actualizar_Registro(new Object[]{"", conse}, null, instruccion_sql);
        return ok;
    }

    public boolean modificarCuadreSepare(String id, String doc, String estado) {
        boolean ok = false;
        String instruccion_sql = "update bdPlanSepare set red=?, estadoGeneral=? where idFactura ='" + id + "' ;";
        ok = Actualizar_Registro(new Object[]{"", doc, estado}, null, instruccion_sql);
        return ok;
    }

    public boolean modificarCuadreFactura(String id, String doc, String estado) {
        boolean ok = false;
        String instruccion_sql = "update bdFactura set red=?, estadoGeneral=? where factura ='" + id + "' ;";
        ok = Actualizar_Registro(new Object[]{"", doc, estado}, null, instruccion_sql);
        return ok;
    }

    public boolean modificarCuadreAbonos(String id, String doc, String estado) {
        boolean ok = false;
        String instruccion_sql = "update bdAbonos set red=?,estado=? where factura ='" + id + "' ;";
        ok = Actualizar_Registro(new Object[]{"", doc, estado}, null, instruccion_sql);
        return ok;
    }

    public boolean modificarCotizaciones(String id, String doc, String estado) {
        boolean ok = false;
        String instruccion_sql = "update bdCotizacion set red=?,estado=? where idFactura ='" + id + "' ;";
        ok = Actualizar_Registro(new Object[]{"", doc, estado}, null, instruccion_sql);
        return ok;
    }

    public boolean modificarOServicio1(String id, String doc, String estado) {
        boolean ok = false;
        String instruccion_sql = "update bdOServicio1 set red=?, estadoGeneral=? where idFactura='" + id + "' ;";
        ok = Actualizar_Registro(new Object[]{"", doc, estado}, null, instruccion_sql);
        return ok;
    }

    public boolean modificarOServicio1(String id, String doc, String estado, String prod) {
        boolean ok = false;
        String instruccion_sql = "update bdOServicio1 set red=?, estadoGeneral=? where idFactura='" + id + "' AND producto = '" + prod + "' ;";
        ok = Actualizar_Registro(new Object[]{"", doc, estado}, null, instruccion_sql);
        return ok;
    }

    public boolean modificarCuadreNotasCredito(String id, String doc, String estado) {
        boolean ok = false;
        String instruccion_sql = "update bdPlanSepare set red=?,estadoGeneral =? where factura ='" + id + "' ;";
        ok = Actualizar_Registro(new Object[]{"", doc, estado}, null, instruccion_sql);
        return ok;
    }

//    public boolean modificarInventario(String nombre, int total, String id) {
//        boolean ok = false;
//        String instruccion_sql = "update bdProductos set " + nombre + " =? where Codigo = '" + id + "' ;";
//        ok = Actualizar_Registro(new Object[]{"", total}, null, instruccion_sql);
//        return ok;
//    }
//    public boolean modificarInventario1(String nombre, String total, String id) {
//        boolean ok = false;
//        String instruccion_sql = "update bdProductos set " + nombre + " =? where Codigo = '" + id + "' ;";
//        ok = Actualizar_Registro(new Object[]{"", total}, null, instruccion_sql);
//        return ok;
//    }
    public boolean modificarInventario(String nombre, String total, String id, String base) {
        boolean ok = false;
        String instruccion_sql = "update " + base + " set " + nombre + " =? where idSistema = '" + id + "' ;";
        ok = Actualizar_Registro(new Object[]{"", total}, null, instruccion_sql);
        return ok;
    }

    public boolean modificarRedNc(String id, String doc) {
        boolean ok = false;
        String instruccion_sql = "update bdNc set cuadre=? where id='" + id + "' ;";
        ok = Actualizar_Registro(new Object[]{"", doc}, null, instruccion_sql);
        return ok;
    }

    public boolean modificarCuadreAnulacion(String id, String estado) {
        boolean ok = false;
        String instruccion_sql = "update bdFactura set cuadreAnulacion=? where id='" + id + "' ;";
        ok = Actualizar_Registro(new Object[]{"", estado}, null, instruccion_sql);
        return ok;
    }

    public Object[][] facturasAnuladasCuadre(String concepto, String usuario) {
        String colName[] = {"id"};
        String origen = " bdFactura ";
        Object dtDatos[][] = getDatos(colName, origen, "select id FROM bdFactura where" + concepto + " And (cuadreAnulacion = '' or cuadreAnulacion is Null) "
                + "AND usuarioAnula='" + usuario + "' ;", " where" + concepto + " And (cuadreAnulacion = '' or cuadreAnulacion is Null) AND usuarioAnula='" + usuario + "';");

        return dtDatos;
    }

    public Object[][] getDatosCongelada() {
        String colName[] = {"idFactura", "cliente", "totalGeneral", "conseMesa", "nombre", "telefono", "observacion", "producto"};
        String origen = " congeladas";
        Object dtDatos[][] = getDatos(colName, origen, "select idFactura, cliente, totalGeneral, conseMesa, nombre, "
                + "telefono, observacion, producto FROM congeladas", "");
        return dtDatos;
    }

    public Object[][] getDatosCongelada1() {
        String colName[] = {"idFactura", "totalGeneral", "conseMesa", "turno"};
        String origen = " bdcongelada ";
        Object dtDatos[][] = getDatos(colName, origen, "SELECT bdcongelada.idFactura AS idFactura, bdcongelada.conseMesa AS conseMesa, "
                + "bdcongelada.totalGeneral AS totalGeneral, bdcongelada.turno AS turno FROM bdcongelada GROUP BY bdcongelada.idFactura", "");
        return dtDatos;
    }

    public boolean modificarEstadoEgreso(String fecha, String estado) {
        boolean ok = false;
        String instruccion_sql = "update bdEgreso set estado=? where fecha = '" + fecha + "' AND "
                + "terminal='" + Instancias.getInstancias().getTerminal() + "' AND usuario='" + Instancias.getInstancias().getUsuario() + "'";
        ok = Actualizar_Registro(new Object[]{"", estado}, null, instruccion_sql);
        return ok;
    }

    public boolean modificarEstadoEgreso1(String fecha, String estado) {
        boolean ok = false;
        String instruccion_sql = "update bdEgreso set estado=? where fecha = '" + fecha + "' AND terminal='" + Instancias.getInstancias().getTerminal() + "' AND usuario='" + Instancias.getInstancias().getUsuario() + "'";
        ok = Actualizar_Registro(new Object[]{"", estado}, null, instruccion_sql);
        return ok;
    }

    public boolean modificarFechaHistorico(String fecha) {
        boolean ok = false;
        String instruccion_sql = "update bdInventario set fecha=?" + " where Id='historico' ;";
        ok = Actualizar_Registro(new Object[]{"", fecha}, null, instruccion_sql);
        return ok;
    }

    public boolean cancelarCxc(String factura) {
        boolean ok = false;

        Object[][] ids = getIdsAbonos(factura);

        for (Object[] id : ids) {
            String instruccion_sql = "update bdCxc set estado=? WHERE Id=" + id[0] + " and estado <> 'ANULADA';";
            ok = Actualizar_Registro(new Object[]{"", "CANCELADA"}, null, instruccion_sql);
        }

        return ok;
    }

    public Object[][] getConCompras(String producto) {

        String colName[] = {
            "producto", "cant", "mes", "ano", "valor", "valorSimplificado"
        };
        String origen = " conCompras3";
        Object dtDatos[][] = getDatos(colName, origen, "select producto,cant,mes,ano,valor,valorSimplificado FROM conCompras3 where producto = '" + producto + "' ORDER BY ano,mes", " where producto = '" + producto + "'");
        System.out.println("select producto,cant,mes,ano,valor,valorSimplificado FROM conCompras3 where producto = '" + producto + "' ORDER BY ano,mes");
        return dtDatos;
    }

    public Object[][] getArticulos(String tipo) {

        String colName[] = {
            "Id", "nombreArticulo", "tipo", "estado"
        };
        String origen = " bdArticulos";
        Object dtDatos[][] = getDatos(colName, origen, "select Id, nombreArticulo, tipo, estado FROM bdArticulos where tipo = '" + tipo + "' ", " where tipo = '" + tipo + "'");
        return dtDatos;
    }

    public Object[][] getListadoOrden() {

        String colName[] = {
            "Id", "nombre", "dias"
        };
        String origen = " bdListadoOrden";
        Object dtDatos[][] = getDatos(colName, origen, "select Id, nombre, dias FROM bdListadoOrden ", "");
        return dtDatos;
    }

    public Object[] getConVentas(String id, String mes, String ano) {

        String instruccion_sql = "select producto,cant,mes,ano from conVenta where producto = '" + id + "' AND mes='" + mes + "' AND ano = '" + ano + "'; ";
        String[] colName = {"producto", "cant", "mes", "ano"};
        boolean[] cadena = {true, true, true, true};
        //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);

        return data;
    }

    public Object[] getInformacionPermiso(String id) {
        String instruccion_sql = "select Id, tipoSolicitud, valorSolicitud, nota, fecha, hora, usuario, estado from bdPermisos "
                + "where Id = '" + id + "' ;";
        String[] colName = {"Id", "tipoSolicitud", "valorSolicitud", "nota", "fecha", "hora", "usuario", "estado"};
        boolean[] cadena = {true, true, true, true, true, true, true, true};
        //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        return data;
    }

    public Object[] getServicioOrden(String id) {

        String instruccion_sql = "select Id, nombre, dias from bdListadoOrden where nombre = '" + id + "'; ";
        String[] colName = {"Id", "nombre", "dias"};
        boolean[] cadena = {true, true, true};
        //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);

        return data;
    }

    public Object[][] getIdsAbonos(String factura) {
        String colName[] = {
            "Id"
        };
        String origen = " bdCxc";
        Object dtDatos[][] = getDatos(colName, origen, "select Id FROM bdCxc where factura2 = '" + factura + "'", " where factura2 = '" + factura + "' ");

        return dtDatos;
    }

    public Object[][] getVerificadorFactura(String factura) {
        String colName[] = {
            "idFactura"
        };
        String origen = " bdVerificadorFacturas";
        Object dtDatos[][] = getDatos(colName, origen, "select idFactura FROM bdVerificadorFacturas where factura = '" + factura + "' ",
                " where factura = '" + factura + "' ");
        return dtDatos;
    }

    public boolean modificarMaestra(ndMaestra nodo) {
        boolean ok = false;
        String instruccion_sql = "update bdMaestra set l1=?, l2=?, l4=?, c1=?, c2=?, c3=?, c4=?, c5=?, d1=?, d2=?, d3=?, d4=?, d5=?, d6=?, d7=?, pie=?, "
                + "legal=?,c6=?,c7=?,recogida=?,lector=?,tituloFactura=?, pvpSinIva=?, costoSinIva=?, ventasPredeterminado=?, mensajeUtilidad=?,consecutivosDiferentes=?,"
                + "resolucionIgual=?,diasAlertaResolucion=?,alertaFechaDias=?,alertaCantidadNumeracion=?,alertaPromedioDias=?, diasCobroMora=?, "
                + "porcentajeMora=?, generaOrdenMedica=?, imprimirOrdenMedica=?, imprimirFacturaOrdenMedica=?, horaInicioAgenda=?,horaFinAgenda=?, "
                + "intervaloAgenda=?, codPrestadorServicio=?, imprimirCuadreFiscal=?, ocultarInformacionCliente=?, visualizarTodasLasFacturas=?, "
                + "mostrarInformacionCuadre=?, descuentoMaximoVentas=?, tipoIdPrestadorServicio=?, otros=?, domicilios=?, limite=?, combinarProductos=?,"
                + "modificarNombre=?, impBolsa=?, valorBolsa=?, turno=?, turno1=?, anexoFacturacion=?, consecutivo=?, hora=?, pondNegativo=?, "
                + "anexoOrdenServicio=?, modificarPrecio=?, borrarCongelada=?, copiasFactura=?, copiasOServicio=?, copiasCotizacion=?, copiasPlanSepare=?, copiasPedido=?, "
                + "previsualizarFactura=?, previsualizarOServicio=?, previsualizarCotizacion=?, previsualizarPlanSepare=?, previsualizarPedido=?, numFactura=?, "
                + "numOServicio=?, numCotizacion=?, numPlanSepare=?, numPedido=?, pagoTerceros=?, facturarSinInventario=?, reimpresion=?, impresoraPos=?,"
                + "impresoraMediaCarta=?, impresoraCarta=?, verImpoconsumo=?, verRetenciones=?, imprimirCada=?, cantidadEstablecida=?, filas=?, "
                + "columnas=?, porcPropina=?, mostrarDevuelta=?, inicioHosp=?, finHosp=?, intervalosHosp=?, diasAutomaticos=?, ciudadBuscador=?, foco=?,"
                + "pvpImpoconsumo=?, costoImpoconsumo=?, impresionPorGrupo=?, copiasComanda=?, copiasPrefactura=?, previsualizarComanda=?, previsualizarPrefactura=?,"
                + "impresoraComanda=?, impresoraPrefactura=?, meserosFacturarMesas=?, soloMesas=?, borrarMesas=?, idUsuario=?, password=?, numFacturaIncremento=?,"
                + "responsabilidades=?, gmail=?, passGmail=? where Id=1;";
        ok = Actualizar_Registro(metodos.pasarDatosMaestra(nodo), null, instruccion_sql);
        return ok;
    }

    public Object[] getDatosMaestra() {
        String instruccion_sql = "select l1, l2, l4, c1, c2, c3, c4, c5, "
                + " d1, d2, d3, d4, d5, d6, d7, pie, legal,c6,c7,recogida,lector,tituloFactura, pvpSinIva, costoSinIva, ventasPredeterminado, mensajeUtilidad,"
                + "consecutivosDiferentes,resolucionIgual,diasAlertaResolucion, alertaFechaDias,alertaCantidadNumeracion,alertaPromedioDias,diasCobroMora, porcentajeMora, "
                + "generaOrdenMedica, imprimirOrdenMedica, imprimirFacturaOrdenMedica, horaInicioAgenda, horaFinAgenda, intervaloAgenda, codPrestadorServicio, "
                + "previsualizarFactura, imprimirCuadreFiscal, ocultarInformacionCliente, visualizarTodasLasFacturas,mostrarInformacionCuadre, descuentoMaximoVentas,"
                + "tipoIdPrestadorServicio, otros, domicilios, limite, combinarProductos, modificarNombre, impBolsa, valorBolsa, turno, turno1, anexoFacturacion, "
                + "copiasCotizacion, consecutivo, hora, pondNegativo, anexoOrdenServicio, modificarPrecio, borrarCongelada, copiasFactura, copiasOServicio, copiasCotizacion, "
                + "copiasPlanSepare, copiasPedido, previsualizarFactura, previsualizarOServicio, previsualizarCotizacion, previsualizarPlanSepare, previsualizarPedido, numFactura, "
                + "numOServicio, numCotizacion, numPlanSepare, numPedido, pagoTerceros, facturarSinInventario, reimpresion, impresoraPos, impresoraMediaCarta, impresoraCarta, "
                + "verImpoconsumo, verRetenciones, imprimirCada, cantidadEstablecida, filas, columnas, porcPropina, mostrarDevuelta, inicioHosp, finHosp,"
                + "intervalosHosp, diasAutomaticos, ciudadBuscador, foco, pvpImpoconsumo, costoImpoconsumo, impresionPorGrupo, copiasComanda, copiasPrefactura,"
                + "previsualizarComanda, previsualizarPrefactura, impresoraComanda, impresoraPrefactura, meserosFacturarMesas, soloMesas, borrarMesas,"
                + "idUsuario, password, numFacturaIncremento, responsabilidades, gmail, passGmail from bdMaestra where Id=1; ";
        //columnas de la tabla que se
        String[] colName = {"l1", "l2", "l4", "c1", "c2", "c3", "c4", "c5", "d1", "d2", "d3", "d4", "d5", "d6", "d7", "pie", "legal", "c6", "c7",
            "recogida", "lector", "tituloFactura", "pvpSinIva", "costoSinIva", "ventasPredeterminado", "mensajeUtilidad", "consecutivosDiferentes",
            "resolucionIgual", "diasAlertaResolucion", "alertaFechaDias", "alertaCantidadNumeracion", "alertaPromedioDias", "diasCobroMora", "porcentajeMora",
            "generaOrdenMedica", "imprimirOrdenMedica", "imprimirFacturaOrdenMedica", "horaInicioAgenda", "horaFinAgenda", "intervaloAgenda",
            "codPrestadorServicio", "imprimirCuadreFiscal", "ocultarInformacionCliente", "visualizarTodasLasFacturas",
            "mostrarInformacionCuadre", "descuentoMaximoVentas", "tipoIdPrestadorServicio", "otros", "domicilios", "limite", "combinarProductos",
            "modificarNombre", "impBolsa", "valorBolsa", "turno", "turno1", "anexoFacturacion", "consecutivo", "hora", "pondNegativo",
            "anexoOrdenServicio", "modificarPrecio", "borrarCongelada", "copiasFactura", "copiasOServicio", "copiasCotizacion", "copiasPlanSepare", "copiasPedido",
            "previsualizarFactura", "previsualizarOServicio", "previsualizarCotizacion", "previsualizarPlanSepare", "previsualizarPedido", "numFactura", "numOServicio",
            "numCotizacion", "numPlanSepare", "numPedido", "pagoTerceros", "facturarSinInventario", "reimpresion", "impresoraPos", "impresoraMediaCarta", "impresoraCarta",
            "verImpoconsumo", "verRetenciones", "imprimirCada", "cantidadEstablecida", "filas", "columnas", "porcPropina", "mostrarDevuelta",
            "inicioHosp", "finHosp", "intervalosHosp", "diasAutomaticos", "ciudadBuscador", "foco", "pvpImpoconsumo", "costoImpoconsumo", "impresionPorGrupo",
            "copiasComanda", "copiasPrefactura", "previsualizarComanda", "previsualizarPrefactura", "impresoraComanda", "impresoraPrefactura", "meserosFacturarMesas",
            "soloMesas", "borrarMesas", "idUsuario", "password", "numFacturaIncremento", "responsabilidades", "gmail", "passGmail"
        };
        boolean[] cadena = {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,
            true, false, false, true, false, false, false, false, false, false, true, true, true, true, true, true, false, false, false, true,
            true, true, true, false, false, false, false, true, true, true, true, true, false, false, false, true, false, true, true, false,
            false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, true, true, true, true, true,
            false, false, false, true, true, true, false, false, true, true, true, true, true, false, true, true, true, false, false, true, false, false, false,
            true, true, false, false, true, true, false, false, false, true, true, true, true, true, true};
        //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);

        return data;
    }

    public boolean actualizarPermisos(String id, String regimen, String impoconsumo, String dispositivoUSB,
            String ordenesServicio, String tipoOrden, String creditos, String planSepare, String pedidos,
            String tipoImpresion, String invBodega, String productosSerial, String embarcaciones, String congeladas,
            String medico, String veterinaria, String parqueadero, String agenda, String restaurante,
            String recordatorios, String laboratorio, String oftalmologia, String facturaLote, String PTM,
            String periodoPrueba, String diasFinalizacionPrueba, String numeroFacturas, String diasDespuesPrueba,
            String info) {
        boolean ok = false;
        String instruccion_sql = "update configuracion set regimen=?, impoconsumo=?, usb=?, ordenServ=?, servicioAutomotor=?, creditos=?, planSepare=?, "
                + "pedidos=?, tipoImpresion=?, invBodega=?, productosSerial=?, embarcaciones=?, congeladas=?, medico=?, veterinaria=?, parqueadero=?, "
                + "agenda=?, restaurante=?, recordatorios=?, laboratorio=?, oftalmologia=?, facturaLote=?, PTM=?, periodoPrueba=?, diasFinalizacionPrueba=?, "
                + "numeroFacturas=?, diasDespuesPrueba=?, info=?, actualizarPermisos=? where Id = '" + id + "' ;";
        ok = Actualizar_Registro(new Object[]{"", regimen, impoconsumo, dispositivoUSB, ordenesServicio, tipoOrden,
            creditos, planSepare, pedidos, tipoImpresion, invBodega, productosSerial, embarcaciones, congeladas,
            medico, veterinaria, parqueadero, agenda, restaurante, recordatorios, laboratorio, oftalmologia,
            facturaLote, PTM, periodoPrueba, diasFinalizacionPrueba, numeroFacturas, diasDespuesPrueba, info,
            true}, null, instruccion_sql);
        return ok;
    }

    //ventasFactura
    public boolean modificarConfiguracion(modeloConfiguracion modelo) {
        String instruccion_sql = "update configuracion set regimen=?, informacionLegal=?, tipoImpresion=?, nit=?, nombre=?, telefono=?, fechaInicio=?, "
                + "diasAntesAlertaBloqueo=?, diasDespuesGabelaBloqueo=?, numeroFEDisponibles=?, congeladas=?, medico=?, veterinaria=?, parqueadero=?, ordenServicio=?, creditos=?, "
                + "separe=?, pedido=?, agenda=?, restaurante=?, recordatorios=?, laboratorio=?, servicioAutomotor=?, oftalmologia=?, inventarioBodegas=?, "
                + "productosSerial=?, facturacionLote=?, usb=?, facturaElectronica=?, pruebasFacturacion=? where Id=1 ;";
        return Actualizar_Registro(modelo.pasarDatosConfiguracion(modelo), null, instruccion_sql);
    }

    public modeloConfiguracion getDatosConfiguracion() {
        modeloConfiguracion modelo = new modeloConfiguracion();
        String instruccion_sql = "select regimen, informacionLegal, tipoImpresion, nit, nombre, telefono, fechaInicio, diasAntesAlertaBloqueo,"
                + "diasDespuesGabelaBloqueo, numeroFEDisponibles, congeladas, medico, veterinaria, parqueadero, ordenServicio, creditos, separe, pedido, agenda, restaurante, "
                + "recordatorios, laboratorio, servicioAutomotor, oftalmologia, inventarioBodegas, productosSerial, facturacionLote, usb, facturaElectronica, pruebasFacturacion "
                + "from configuracion where Id=1; ";
        String[] colName = {"regimen", "informacionLegal", "tipoImpresion", "nit", "nombre", "telefono", "fechaInicio", "diasAntesAlertaBloqueo", "diasDespuesGabelaBloqueo",
            "numeroFEDisponibles", "congeladas", "medico", "veterinaria", "parqueadero", "ordenServicio", "creditos", "separe", "pedido", "agenda", "restaurante", "recordatorios",
            "laboratorio", "servicioAutomotor", "oftalmologia", "inventarioBodegas", "productosSerial", "facturacionLote", "usb", "facturaElectronica", "pruebasFacturacion"};
        boolean[] cadena = {true, true, true, true, true, true, true, true, true, true, false, false, false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false, false, false
        };
        return modelo.llenarConfiguracion(GetRegistro(colName, cadena, instruccion_sql));
    }

    public boolean modificarConfiguraciones(String nit, String nombre) {
        boolean ok = false;
        String instruccion_sql = "update configuracion set nit=?, nombre=? ;";
        ok = Actualizar_Registro(new Object[]{"", nit, nombre}, null, instruccion_sql);
        return ok;
    }

    public boolean cambiarDiasBloqueo(String anticipacion, String plazo) {
        boolean ok = false;
        String instruccion_sql = "update configuracion set diasAlertaBloqueo=?, diasBloqueo=? ;";
        ok = Actualizar_Registro(new Object[]{"", anticipacion, plazo}, null, instruccion_sql);
        return ok;
    }

    public boolean modificarProceso(ndProceso nodo) {
        boolean ok = false;
        String instruccion_sql = "update bdProceso set nombre=?, und=?, observaciones=?"
                + " where codigo='" + nodo.getCodigo() + "' ;";
        ok = Actualizar_Registro(metodos.pasarDatosProceso(nodo), null, instruccion_sql);
        return ok;
    }

    public boolean cambiarEstadoMesa(String mesa, String estado) {
        boolean ok = false;
        String instruccion_sql = "update bdMesas set estado=? "
                + " where nombre = '" + mesa + "' ;";
        ok = Actualizar_Registro(new Object[]{"", estado}, null, instruccion_sql);
        return ok;
    }

    public String getEstadoMesa(String id) {
        String instruccion_sql = "select estado from bdMesas where nombre = '" + id + "' ";
        String[] colName = {"estado"};
        boolean[] cadena = {true};
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        return data[0] != null ? data[0].toString() : "";
    }

    public String getUsuarioOrden(String id) {
        String instruccion_sql = "select usuario from bdConsultaClinica where Id = '" + id + "' ";
        //columnas de la tabla que se
        String[] colName = {"usuario"};
        boolean[] cadena = {true};
        //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        return data[0].toString();
    }

    public String estructuraExistente(String id) {
        String instruccion_sql = "select id from bdEstructura where codigo = '" + id + "' ";
        //columnas de la tabla que se
        String[] colName = {"id"};
        boolean[] cadena = {true};
        //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);

        if (data[0] == null) {
            return "";
        } else {
            return data[0].toString();
        }
    }

    public String imeiExistente1(String id) {
        String instruccion_sql = "select Id from bdDetalleProductos where imei = '" + id + "' ";
        //columnas de la tabla que se
        String[] colName = {"Id"};
        boolean[] cadena = {true};
        //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        return data[0].toString();
    }

    public String imeiExistente(String id) {
        String instruccion_sql = "select Id from bdDetalleProductos where imei = '" + id + "' and (estado = 'DISPONIBLE' or estado = 'PRESTADO') ";
        //columnas de la tabla que se
        String[] colName = {"Id"};
        boolean[] cadena = {true};
        //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        return data[0].toString();
    }

    public String ordenExistente(String id) {
        String instruccion_sql = "select idFactura from bdOServicio1 where idFactura = '" + id + "' ";
        //columnas de la tabla que se
        String[] colName = {"idFactura"};
        boolean[] cadena = {true};
        //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        return data[0].toString();
    }

    public String getIdSistemaProducto(String prod) {
        String instruccion_sql = "select idSistema from bdProductos where codigo = '" + prod + "' ";
        //columnas de la tabla que se
        String[] colName = {"idSistema"};
        boolean[] cadena = {true};
        //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        return data[0].toString();
    }

    public String getNombreProducto(String prod) {
        String instruccion_sql = "select descripcion from bdProductos where codigo = '" + prod + "' ";
        //columnas de la tabla que se
        String[] colName = {"descripcion"};
        boolean[] cadena = {true};
        //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        return data[0].toString();
    }

    public String sumaTotalesIngreso0() {

        String instruccion_sql = "select total"
                + " from sumaIng0; ";
        //columnas de la tabla que se
        String[] colName = {"total"};
        boolean[] cadena = {true};
        //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        return data[0].toString();
    }

    public String cantidadCongeladasMaestra() {
        String instruccion_sql = "select l1"
                + " from bdMaestra where id=1; ";
        //columnas de la tabla que se
        String[] colName = {"l1"};
        boolean[] cadena = {true};
        //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        return data[0].toString();
    }

    public boolean anularCompraDetalladoInventario(String id) {
        boolean ok = false;
        String instruccion_sql = "update bdDetalleProductos set estado=? where numIngreso = '" + id + "' ;";
        ok = Actualizar_Registro(new Object[]{"", "ANULADO"}, null, instruccion_sql);
        return ok;
    }

    public boolean descontarNc(String id, String num) {
        boolean ok = false;
        String instruccion_sql = "update bdNc set restante=?" + " where Id='" + id + "' ;";
        ok = Actualizar_Registro(null, new Object[]{num}, instruccion_sql);
        return ok;
    }

//    public boolean modificarEstadoDetalleProd2(String id) {
//        boolean ok = false;
//        String instruccion_sql = "update bdDetalleProductos set estado=? where Id = '" + id + "' ;";
//        ok = Actualizar_Registro(new Object[]{"", "DISPONIBLE"}, null, instruccion_sql);
//        return ok;
//    }
    public boolean modificarEstadoDetalleProd(String id, String ingreso) {
        boolean ok = false;
        String instruccion_sql = "update bdDetalleProductos set estado=?, numIngreso=? where Id = '" + id + "' ;";
        ok = Actualizar_Registro(new Object[]{"", "DISPONIBLE", ingreso}, null, instruccion_sql);
        return ok;
    }

    public boolean modificarEstadoDetalleProductos(String id, String estado) {
        boolean ok = false;
        String instruccion_sql = "update bdDetalleProductos set estado=? where Id = '" + id + "' ;";
        ok = Actualizar_Registro(new Object[]{"", estado}, null, instruccion_sql);
        return ok;
    }

    public boolean modificarCantidadesDetalleProductos(String id, BigDecimal cantidad) {
        boolean ok = false;
        String estado = "DISPONIBLE";
        if (cantidad.compareTo(BigDecimal.ONE) <= 0) {
            estado = "NO-DISPONIBLE";
        }

        String instruccion_sql = "update bdDetalleProductos set estado=?, cantidadDisponible=? where Id = " + id + " ;";
        ok = Actualizar_Registro(new Object[]{"", estado}, new Object[]{cantidad}, instruccion_sql);
        return ok;
    }

    public boolean modificarLista(String codigo, String lista, String num) {
        boolean ok = false;
        String instruccion_sql = "update bdProductos set L" + lista + "=?" + " where Codigo = '" + codigo + "' ;";
        ok = Actualizar_Registro(null, new Object[]{num}, instruccion_sql);
        return ok;
    }

    public boolean modificarListaProducto(String codigo, String num) {
        boolean ok = false;
        String instruccion_sql = "update bdProductos set L1=? where idSistema = '" + codigo + "' ;";
        ok = Actualizar_Registro(null, new Object[]{num}, instruccion_sql);
        return ok;
    }

    public boolean modificarCodificador(String id, Object[] vector) {
        boolean ok = false;
        String instruccion_sql = "update bdCodificador set Campo1=?, Campo2=?, Campo3=?, Campo4=?, Campo5=?, Campo6=?, Campo7=?, Campo8=?, Campo9=?, Campo10=?, Campo11=?, Campo12=?, Campo13=?, Campo14=?, Campo15=?, Campo16=?, Campo17=?, Campo18=?, Campo19=?, Campo20=?, Campo21=?, Campo22=?, Campo23=?, Campo24=?, Campo25=?, Campo26=?, Campo27=?, Campo28=?, Campo29=?, Campo30=?, Campo31=?, Campo32=?, Campo33=?, Campo34=?, Campo35=?, Campo36=?, Campo37=?, Campo38=?, Campo39=?, Campo40=?, Campo41=?, Campo42=?, Campo43=?, Campo44=?, Campo45=?, Campo46=?, Campo47=?, Campo48=?, Campo49=?, Campo50=?, Campo51=?, Campo52=?, Campo53=?, Campo54=?, Campo55=?, Campo56=?, Campo57=?, Campo58=?, Campo59=?, Campo60=?, Campo61=?, Campo62=?, Campo63=?, Campo64=?, Campo65=?, Campo66=?, Campo67=?, Campo68=?, Campo69=?, Campo70=?, Campo71=?, Campo72=?, Campo73=?, Campo74=?, Campo75=?, Campo76=?, Campo77=?, Campo78=?, Campo79=?, Campo80=?, Campo81=?, Campo82=?, Campo83=?, Campo84=?, Campo85=?, Campo86=?, Campo87=?, Campo88=?, Campo89=?, Campo90=?, Campo91=?, Campo92=?, Campo93=?, Campo94=?, Campo95=?, Campo96=?, Campo97=?, Campo98=?, Campo99=?, Campo100=?"
                + " where Id='" + id + "';";
        ok = Actualizar_Registro(vector, null, instruccion_sql);
        return ok;
    }

    public Object[][] getMultiempresas() {
        String columNames[] = {
            "id", "descripcion", "base"
        };
        String colName[] = {
            "id", "descripcion", "base"
        };
        String origen = " bdMultiEmpresa ";
        Object dtDatos[][] = GetTabla(colName, origen, (new StringBuilder()).append("select id, descripcion, base from ").append(origen).toString());
        return dtDatos;
    }

    public Object[][] getEstadoUsuarios() {
        String columNames[] = {
            "usuario", "nombre", "estado", "terminal"
        };
        String colName[] = {
            "usuario", "nombre", "estado", "terminal"
        };
        String origen = " bdUsuario ";
        Object dtDatos[][] = GetTabla(colName, origen, (new StringBuilder()).append("select usuario, nombre, estado, terminal from ").append(origen).toString());
        return dtDatos;
    }

    public Object[][] getProductos() {
        String columNames[] = {
            "Codigo"
        };
        String colName[] = {
            "Codigo"
        };
        String origen = " inventario";
        Object dtDatos[][] = GetTabla(colName, origen, (new StringBuilder()).append("select Codigo from ").append(origen).toString());

        return dtDatos;
    }

    public Object[][] getCostosProducto(String producto) {
        String colName[] = {
            "cantidad", "valor"};
        String origen = " repIngresos";
        Object dtDatos[][] = getDatos(colName, origen, "select cantidad, valor from repIngresos where producto = '" + producto + "' ORDER BY fechaFactura DESC", " where producto = '" + producto + "' ");

        return dtDatos;
    }

    public Object[][] getCostosProducto(String producto, String fecha) {
        String colName[] = {
            "cantidad", "valor"};
        String origen = " repIngresos";
        Object dtDatos[][] = getDatos(colName, origen, "select cantidad, valor from repIngresos where producto = '" + producto + "' And "
                + "(fechaFactura)<= '" + fecha + "' ORDER BY fechaFactura DESC", " where producto = '" + producto + "' ");
        return dtDatos;
    }

    public boolean modificarCosto(String producto, String costo) {
        boolean ok = false;
        String instruccion_sql = "update bdProductos set costo=?" + " where Codigo='" + producto + "' ;";
        ok = Actualizar_Registro(new Object[]{""}, new Object[]{costo}, instruccion_sql);
        return ok;
    }

//                                     ELIMINACION DE REGISTROS
/* ------------------------------------------------------------------------------------- */
    public boolean eliminarCliente(String Id) {
        boolean ok = false;
        String instruccion_sql = "update bdTerceros set activo=?"
                + " where Id='" + Id + "' ;";
        ok = Inactivar_Registro(instruccion_sql);
        return ok;
    }

    public boolean cambiarEstadoDeMedicamento() {
        boolean ok = false;
        String instruccion_sql = "update bdMedicamentosProcesosAlertas set estado=? ;";
        ok = Actualizar_Registro(new Object[]{"", "Pendiente"}, null, instruccion_sql);
        return ok;
    }

    public boolean cambiarEstadoDeMedicamento(String IdHosp, String cod, String hora) {
        boolean ok = false;
        String instruccion_sql = "update bdMedicamentosProcesosAlertas set estado=? where Codigo ='" + cod + "' and hora = '" + hora + "' ;";
        ok = Actualizar_Registro(new Object[]{"", "Realizado"}, null, instruccion_sql);
        return ok;
    }

    public boolean activarCliente(String Id) {
        boolean ok = false;
        String instruccion_sql = "update bdTerceros set activo=?"
                + " where Id='" + Id + "' ;";
        ok = Activar_Registro(instruccion_sql);
        return ok;
    }

    public boolean desactivarRecogidaParcial() {
        boolean ok = false;
        String instruccion_sql = "update bdRecogidaParcial set activo=? where terminal='" + Instancias.getInstancias().getTerminal() + "' AND usuario='" + Instancias.getInstancias().getUsuario() + "' ;";
        ok = Activar_Registro(instruccion_sql);
        return ok;
    }

    public boolean eliminarProducto(String Id) {
        boolean ok = false;
        String instruccion_sql = "update bdProductos set activo=?"
                + " where Codigo='" + Id + "' ;";
        ok = Inactivar_Registro(instruccion_sql);
        return ok;
    }

    public boolean activarProducto(String Id) {
        boolean ok = false;
        String instruccion_sql = "update bdProductos set activo=?"
                + " where Codigo='" + Id + "' ;";
        ok = Activar_Registro(instruccion_sql);
        return ok;
    }

    public boolean eliminarEstructura(String Id) {
        boolean ok = false;
        String instruccion_sql = "update bdEstructura set inactivo=?"
                + " where Id='" + Id + "' ;";
        ok = Inactivar_Registro(instruccion_sql);
        return ok;
    }

    public boolean activarEstructura(String Id) {
        boolean ok = false;
        String instruccion_sql = "update bdEstructura set inactivo=?"
                + " where Id='" + Id + "' ;";
        ok = Activar_Registro(instruccion_sql);
        return ok;
    }

    public Object[][] getHorasBloqueo() {
        String colName[] = {
            "fecha", "nombre", "nota", "idTercero", "horas"};
        String origen = " bdBloqueoAgenda";
        Object dtDatos[][] = getDatos(colName, origen, "select fecha, nombre, nota, idTercero, horas from bdBloqueoAgenda", "");

        return dtDatos;
    }

    public Object[][] facturasLote(String lote) {
        String colName[] = {"factura"};
        String origen = " bdFactura ";
        Object dtDatos[][] = getDatos(colName, origen, "select factura from bdFactura where cxc = '" + lote + "'", " where cxc = '" + lote + "'");
        return dtDatos;
    }

    public String getNumeroMesas() {
        String instruccion_sql = "select count(nombre) as nombre from bdMesas; ";
        //columnas de la tabla que se
        String[] colName = {"nombre"};
        boolean[] cadena = {true};
        //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        return data[0].toString();
    }

    public String estadoCxc2(String id) {
        String instruccion_sql = "select estado"
                + " from cuentasCobroPendientes where factura = '" + id + "' ; ";
        //columnas de la tabla que se
        String[] colName = {"estado"};
        boolean[] cadena = {true};
        //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        return data[0].toString();
    }

    public Object[][] getHorasBloqueoMedico(String medico) {
        String colName[] = {
            "fecha", "nombre", "nota", "idTercero", "horas"};
        String origen = " bdBloqueoAgenda";
        Object dtDatos[][] = getDatos(colName, origen, "select fecha, nombre, nota, idTercero, horas from bdBloqueoAgenda where nombre = '" + medico + "' or nombre = 'TODOS' ", " where nombre = '" + medico + "' or nombre = 'TODOS' ");

        return dtDatos;
    }

    public boolean anularLote(String lote) {
        boolean ok = false;
        String instruccion_sql = "update bdFactura set anulada=? where cxc = '" + lote + "' ;";
        ok = Inactivar_Registro(instruccion_sql);
        return ok;
    }

    public boolean anularEgreso(String Id) {
        boolean ok = false;
        String instruccion_sql = "update bdEgreso set anulada=? where egreso = '" + Id + "' ;";
        ok = Inactivar_Registro(instruccion_sql);
        return ok;
    }

    public boolean anularDocumento(String Id, String bd) {
        boolean ok = false;
        String instruccion_sql = "update " + bd + " set anulada=?"
                + " where id='" + Id + "' ;";
        ok = Inactivar_Registro(instruccion_sql);
        return ok;
    }

    public boolean anularAbono(String Id) {
        boolean ok = false;
        String instruccion_sql = "update bdAbonos set anulada=? where factura = '" + Id + "' ;";
        ok = Inactivar_Registro(instruccion_sql);
        return ok;
    }

    public boolean activarCuentaCobro(String id) {
        boolean ok = false;
        String instruccion_sql = "update bdCuentaCobro set anulada=? where idFactura = '" + id + "' ;";
        ok = Activar_Registro(instruccion_sql);
        return ok;
    }

    public boolean modificarRegistroCxcCuentacobro(String id, String estado) {
        boolean ok = false;
        String instruccion_sql = "update bdCxc set estado=? where factura = '" + id + "' ;";
        ok = Actualizar_Registro(new Object[]{"", estado}, null, instruccion_sql);
        return ok;
    }

    public boolean agregarLote(Object[] vector) {
        boolean ok = false;
        String instruccion_sql = "insert into bdLote (lote, cuentaCobroAnterior, cuentaCobroNueva, fecha, usuario, cuentaCobro) values (?,?,?,?,?,?);";
        ok = Agregar_Registro(vector, null, instruccion_sql);
        return ok;
    }

    public boolean modificarRegistroCxp1(String id, String estado) {
        boolean ok = false;
        String instruccion_sql = "update bdCxp set estado=? where recibo = '" + id + "' ;";
        ok = Actualizar_Registro(new Object[]{"", estado}, null, instruccion_sql);
        return ok;
    }

    public boolean modificarRegistroCxp(String condicion) {
        boolean ok = false;
        String instruccion_sql = "update bdCxp set estado=? " + condicion + " ;";
        ok = Actualizar_Registro(new Object[]{"", "PEND"}, null, instruccion_sql);
        return ok;
    }

    public boolean modificarRegistroCxp(String id, String estado) {
        boolean ok = false;
        String instruccion_sql = "update bdCxp set estado=? where ingreso= '" + id + "' ;";
        ok = Actualizar_Registro(new Object[]{"", estado}, null, instruccion_sql);
        return ok;
    }

    public boolean modificarRegistroCxcAbono(String id, String estado) {
        boolean ok = false;
        String instruccion_sql = "update bdCxc set estado=? where recibo = '" + id + "' ;";
        ok = Actualizar_Registro(new Object[]{"", estado}, null, instruccion_sql);
        return ok;
    }

    public boolean modificarEstadoCxcFacturaCanceladas(String id, String estado) {
        boolean ok = false;
        String instruccion_sql = "update bdCxc set estado=? where factura2 = '" + id + "' and tipo='ABONO' and estado='CANCELADA' ;";
        ok = Actualizar_Registro(new Object[]{"", estado}, null, instruccion_sql);
        return ok;
    }

    public boolean modificarEstadoCxcFactura(String id, String estado) {
        boolean ok = false;
        String instruccion_sql = "update bdCxc set estado=? where factura2 = '" + id + "' and (tipo='FACT' or tipo='SEPARE');";
        ok = Actualizar_Registro(new Object[]{"", estado}, null, instruccion_sql);
        return ok;
    }

    public String fechaDetalleInv(String id) {
        String instruccion_sql = "select fecha from bdDetalleInventario where idDetalle = '" + id + "'; ";
        //columnas de la tabla que se
        String[] colName = {"fecha"};
        boolean[] cadena = {true};
        //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        return data[0].toString();
    }

    public String facturaAbono(String id) {
        String instruccion_sql = "select factura from bdCxc where recibo = '" + id + "'; ";
        //columnas de la tabla que se
        String[] colName = {"factura"};
        boolean[] cadena = {true};
        //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        return data[0].toString();
    }

    public String estadoCxcPro(String id) {
        String instruccion_sql = "select estado from bdCxc where factura2 = '" + id + "' AND (tipo='FACT' OR tipo='SEPARE'); ";
        //columnas de la tabla que se
        String[] colName = {"estado"};
        boolean[] cadena = {true};
        //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        return data[0].toString();
    }

    public boolean eliminarIngresoEx(String id) {
        boolean ok = false;
        ok = eliminar_registro("bdIngresoEx", "id='" + id + "'");
        return ok;
    }

    public boolean eliminarIngreso(String id) {
        boolean ok = false;
        ok = eliminar_registro("bdIngreso", "id='" + id + "'");
        return ok;
    }

    public boolean eliminarCompraEx(String id) {
        boolean ok = false;
        ok = eliminar_registro("bdCompraEx", "ingreso='" + id + "'");
        return ok;
    }

    public boolean eliminarCompra(String id) {
        boolean ok = false;
        ok = eliminar_registro("bdCompra", "ingreso='" + id + "'");
        return ok;
    }

    public boolean eliminarEgreso(String id) {
        boolean ok = false;
        ok = eliminar_registro("bdEgreso", "Id='" + id + "'");
        return ok;
    }

    public boolean eliminarCodEgreso(String id) {
        boolean ok = false;
        ok = eliminar_registro("bdEgresoCods", "egreso='" + id + "'");
        return ok;
    }

    public boolean eliminarFactura(String id) {
        boolean ok = false;
        ok = eliminar_registro("bdFactura", "factura = '" + id + "'");
        return ok;
    }

    public boolean eliminarCuentaCobro(String id) {
        boolean ok = false;
        ok = eliminar_registro("bdCuentaCobro", "idFactura = '" + id + "'");
        return ok;
    }

    public boolean eliminarCotizacion(String id) {
        boolean ok = false;
        ok = eliminar_registro("bdCotizacion", "idFactura = '" + id + "'");
        return ok;
    }

    public boolean eliminarOServicio(String id) {
        boolean ok = false;
        ok = eliminar_registro("bdOServicio1", "idFactura = '" + id + "'");
        return ok;
    }

    public boolean eliminarMesa(String id) {
        boolean ok = false;
        ok = eliminar_registro("bdCongelada", "idFactura = '" + id + "'");
        return ok;
    }

    public boolean eliminarComanda(String id, String campo) {
        boolean ok = false;
        ok = eliminar_registro("bdComanda", campo + " = '" + id + "'");
        return ok;
    }

    public boolean eliminarGarantia(String id) {
        boolean ok = false;
        ok = eliminar_registro("bdGarantia", "id = '" + id + "'");
        return ok;
    }

    public boolean eliminarSepare(String id) {
        boolean ok = false;
        ok = eliminar_registro("bdPlanSepare", "idFactura = '" + id + "'");
        return ok;
    }

    public boolean eliminarPedido(String id) {
        boolean ok = false;
        ok = eliminar_registro("bdPedido", "idFactura = '" + id + "'");
        return ok;
    }

    public boolean eliminarNotasCredito(String id) {
        boolean ok = false;
        ok = eliminar_registro("bdNotasCredito", "factura='" + id + "'");
        return ok;
    }

    public boolean eliminarProdCosteo(String id) {
        boolean ok = false;
        ok = eliminar_registro("bdDisCosteo", "codigo='" + id + "'");
        return ok;
    }

    public boolean eliminarProdPlantilla(String id) {
        boolean ok = false;
        ok = eliminar_registro("bdPlantilla", "producto='" + id + "'");
        return ok;
    }

//                                     DATOS DE REGISTROS
/* ------------------------------------------------------------------------------------- */
    public String getSaldoCartera() {
        String instruccion_sql = "select valor"
                + " from repSaldo where tipo = 'ABONO'; ";
        //columnas de la tabla que se
        String[] colName = {"valor"};
        boolean[] cadena = {true};
        //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);

        instruccion_sql = "select valor"
                + " from repSaldo where tipo = 'FACT'; ";
        //columnas de la tabla que se
        colName[0] = "valor";
        cadena[0] = true;
        //se realiza la consulta
        Object[] data2 = GetRegistro(colName, cadena, instruccion_sql);

        return "0";
    }

    public Object getCarteraPendiente(String usu) {
        String instruccion_sql = "select valor"
                + " from carteraPendiente where cliente = '" + usu + "'; ";
        //columnas de la tabla que se
        String[] colName = {"valor"};
        boolean[] cadena = {true};
        //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);

        return data[0];
    }

    public Object getCarteraPendiente2(String usu) {
        String instruccion_sql = "select valor2 from carteraPendiente where cliente = '" + usu + "'; ";
        String[] colName = {"valor2"};
        boolean[] cadena = {true};
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        return data[0];
    }

    public Object[] getCarteraPendiente1(String usu) {
        String instruccion_sql = "select valor2, valor"
                + " from carteraPendiente where cliente = '" + usu + "'; ";
        //columnas de la tabla que se
        String[] colName = {"valor2", "valor"};
        boolean[] cadena = {true, true};
        //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        return data;
    }

    public boolean getProdActivo(String usu) {
        String instruccion_sql = "select activo"
                + " from bdProductos where Codigo = '" + usu + "' OR (codigoBarras = '" + usu + "' and codigoBarras <> '') OR idSistema = '" + usu + "'; ";
        //columnas de la tabla que se
        String[] colName = {"activo"};
        boolean[] cadena = {false};
        //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);

        return Boolean.parseBoolean(data[0].toString());
    }

    public Object[] getNumConsecutivo(String valor) {
        String instruccion_sql = "select numero from bdConsecutivos where Id = '" + valor + "'; ";
        //columnas de la tabla que se
        String[] colName = {"numero"};
        boolean[] cadena = {true};
        //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        return data;
    }

    public Object[] getCantFacturado(String valor) {
        String instruccion_sql = "select cantFacturados from bdCuentaCobro where idFactura = '" + valor + "'; ";
        //columnas de la tabla que se
        String[] colName = {"cantFacturados"};
        boolean[] cadena = {true};
        //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        return data;
    }

    public Object[] getNumConsecutivoFact1(String valor) {
        String instruccion_sql = "select numero, estado from bdConsecutivos where Id = '" + valor + "'; ";
        String[] colName = {"numero", "estado"};
        boolean[] cadena = {true, true};
        //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        actualizarEstadoFact("ON", valor);
        return data;
    }

    public void actualizarEstadoFact(String estado, String id) {
        String instruccion_sql = "update bdConsecutivos set estado=? WHERE Id='" + id + "' ;";
        Actualizar_Registro(new Object[]{"", estado}, null, instruccion_sql);
    }

    public boolean estaSinUtilizar(String valor) {
        String instruccion_sql = "select valor, restante"
                + " from bdNc where Id = '" + valor + "'; ";
        //columnas de la tabla que se
        String[] colName = {"valor", "restante"};
        boolean[] cadena = {true, true};
        //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        try {

            if (data[0].toString().equals(data[1].toString())) {
                return true;
            };

            return false;
        } catch (NullPointerException e) {
            return false;
        }
    }

    public boolean existeFacturaCompra(String proveedor, String factura) {
        String instruccion_sql = "select Id from bdIngreso where proveedor = '" + proveedor + "' and concepto = '" + factura + "'; ";
        //columnas de la tabla que se
        String[] colName = {"Id"};
        boolean[] cadena = {true};
        //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        if (data[0] != null) {
            return true;
        } else {
            return false;
        }
    }

    public String getFacturaNC(String valor) {
        String instruccion_sql = "select factura"
                + " from bdNc where Id = '" + valor + "'; ";
        //columnas de la tabla que se
        String[] colName = {"factura"};
        boolean[] cadena = {true};
        //se realiza la consulta
        String data = GetRegistro(colName, cadena, instruccion_sql)[0].toString();
        return data;
    }

    public String getLista(String producto, String lista) {
        String instruccion_sql = "select L" + lista
                + " from bdProductos where Codigo = '" + producto + "'; ";
        //columnas de la tabla que se
        String[] colName = {"L" + lista};
        boolean[] cadena = {true};
        //se realiza la consulta
        String data = GetRegistro(colName, cadena, instruccion_sql)[0].toString();
        return data;
    }

    public Object[] getInvProducto(String cod) {

        String instruccion_sql = "select Codigo, inventario"
                + " from bdProductos where Codigo = '" + cod + "'; ";
        //columnas de la tabla que se
        String[] colName = {"Codigo", "inventario"};
        boolean[] cadena = {true, true};
        //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);

        return data;
    }

    public ndGrupo getDatosGrupo2(String cod) {

        String instruccion_sql = "select codigo, nombre, imagen"
                + " from bdGrupo where codigo = '" + cod + "'; ";
        //columnas de la tabla que se
        String[] colName = {"codigo", "nombre", "imagen"};
        boolean[] cadena = {true, true, true};
        //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        ndGrupo nodo = metodos.llenarGrupo(data);

        return nodo;
    }

    public ndProceso getDatosProceso(String cod) {

        String instruccion_sql = "select codigo, nombre, und, observaciones"
                + " from bdProceso where codigo = '" + cod + "'; ";
        //columnas de la tabla que se
        String[] colName = {"codigo", "nombre", "und", "observaciones"};
        boolean[] cadena = {true, true, true, true};
        //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        ndProceso nodo = metodos.llenarProceso(data);

        return nodo;
    }

    public Object[] getDatosCodificador(String ID) {

        String instruccion_sql = "select Campo1, Campo2, Campo3, Campo4, Campo5, Campo6, Campo7, Campo8, Campo9, Campo10, Campo11, Campo12, Campo13, Campo14, Campo15, Campo16, Campo17, Campo18, Campo19, Campo20, Campo21, Campo22, Campo23, Campo24, Campo25, Campo26, Campo27, Campo28, Campo29, Campo30, Campo31, Campo32, Campo33, Campo34, Campo35, Campo36, Campo37, Campo38, Campo39, Campo40, Campo41, Campo42, Campo43, Campo44, Campo45, Campo46, Campo47, Campo48, Campo49, Campo50, Campo51, Campo52, Campo53, Campo54, Campo55, Campo56, Campo57, Campo58, Campo59, Campo60, Campo61, Campo62, Campo63, Campo64, Campo65, Campo66, Campo67, Campo68, Campo69, Campo70, Campo71, Campo72, Campo73, Campo74, Campo75, Campo76, Campo77, Campo78, Campo79, Campo80, Campo81, Campo82, Campo83, Campo84, Campo85, Campo86, Campo87, Campo88, Campo89, Campo90, Campo91, Campo92, Campo93, Campo94, Campo95, Campo96, Campo97, Campo98, Campo99, Campo100 from bdCodificador where Id='" + ID + "'; ";
        //columnas de la tabla que se
        String[] colName = {"Campo1", "Campo2", "Campo3", "Campo4", "Campo5", "Campo6", "Campo7", "Campo8", "Campo9", "Campo10", "Campo11", "Campo12", "Campo13", "Campo14", "Campo15", "Campo16", "Campo17", "Campo18", "Campo19", "Campo20", "Campo21", "Campo22", "Campo23", "Campo24", "Campo25", "Campo26", "Campo27", "Campo28", "Campo29", "Campo30", "Campo31", "Campo32", "Campo33", "Campo34", "Campo35", "Campo36", "Campo37", "Campo38", "Campo39", "Campo40", "Campo41", "Campo42", "Campo43", "Campo44", "Campo45", "Campo46", "Campo47", "Campo48", "Campo49", "Campo50", "Campo51", "Campo52", "Campo53", "Campo54", "Campo55", "Campo56", "Campo57", "Campo58", "Campo59", "Campo60", "Campo61", "Campo62", "Campo63", "Campo64", "Campo65", "Campo66", "Campo67", "Campo68", "Campo69", "Campo70", "Campo71", "Campo72", "Campo73", "Campo74", "Campo75", "Campo76", "Campo77", "Campo78", "Campo79", "Campo80", "Campo81", "Campo82", "Campo83", "Campo84", "Campo85", "Campo86", "Campo87", "Campo88", "Campo89", "Campo90", "Campo91", "Campo92", "Campo93", "Campo94", "Campo95", "Campo96", "Campo97", "Campo98", "Campo99", "Campo100"};
        boolean[] cadena = {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true};

        //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);

        return data;
    }

    public Object[] getDatosGrupo(String ID) {
        String instruccion_sql = "select nombre, descuento, visualizar, arancel, activo, impresora from bdGrupo where codigo ='" + ID + "'; ";
        String[] colName = {"nombre", "descuento", "visualizar", "arancel", "activo", "impresora"};
        boolean[] cadena = {true, false, false, true, false, true}; //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        return data;
    }

    public String getDatosGrupoNombre(String ID) {

        String instruccion_sql = "select nombre from bdGrupo where codigo='" + ID + "'; ";
        String[] colName = {"nombre"};
        boolean[] cadena = {true}; //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        return data[0].toString();
    }

    public String getGrupoProducto(String ID) {

        String instruccion_sql = "select Grupo from bdProductos where Codigo='" + ID + "'; ";
        String[] colName = {"Grupo"};
        boolean[] cadena = {true}; //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        return data[0].toString();
    }

    public String getDatosGrupoPorNombre(String nombre) {

        String instruccion_sql = "select codigo  from bdGrupo where nombre='" + nombre + "'; ";
        String[] colName = {"codigo"};
        boolean[] cadena = {true}; //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);

        return data[0].toString();
    }

    public String getDatosSubGrupo(String ID) {
        String instruccion_sql = "select nombre from bdSubGrupo where codigo='" + ID + "'; ";
        String[] colName = {"nombre"};
        boolean[] cadena = {true}; //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);

        return data[0].toString();
    }

    public String getRefenciaProducto(String ID) {
        String instruccion_sql = "select referencia from bdProductos where referencia ='" + ID + "'; ";
        String[] colName = {"referencia"};
        boolean[] cadena = {true}; //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);

        return data[0].toString();
    }

    public String getRefenciaProducto(String ID, String producto) {
        String instruccion_sql = "select referencia from bdProductos where referencia ='" + ID + "' And idSistema <> '" + producto + "'; ";
        String[] colName = {"referencia"};
        boolean[] cadena = {true}; //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        return data[0].toString();
    }

    public boolean modificarGrupo(String cod, String nom, String imagen, boolean descuento, boolean visualizar, String arancel) {
        boolean ok = false;
        String instruccion_sql = "update bdGrupo set nombre=?, imagen=?, descuento=?, visualizar=?, arancel=? where codigo='" + cod + "' ;";
        ok = Actualizar_Registro(new Object[]{"", nom, imagen, descuento, visualizar, arancel}, null, instruccion_sql);
        return ok;
    }

    public boolean modificarImpresoraGrupo(String cod, String impresora) {
        boolean ok = false;
        String instruccion_sql = "update bdGrupo set impresora=? where codigo = '" + cod + "' ;";
        ok = Actualizar_Registro(new Object[]{"", impresora}, null, instruccion_sql);
        return ok;
    }

    public boolean modificarSubGrupo(String cod, String nom, String grupo) {
        boolean ok = false;
        String instruccion_sql = "update bdSubGrupo set nombre=?, grupo=? where codigo='" + cod + "' ;";
        ok = Actualizar_Registro(new Object[]{"", nom, grupo}, null, instruccion_sql);
        return ok;
    }

    public String getNombreAntecedente(String id) {

        String instruccion_sql = "select dato from bdRegAntecedentes where Id=" + id + "; ";
        String[] colName = {"dato"};
        boolean[] cadena = {true}; //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);

        return data[0].toString();
    }

    public String getNombreCie(String id) {

        String instruccion_sql = "select descripcion from bdCies where Id='" + id + "'; ";
        String[] colName = {"descripcion"};
        boolean[] cadena = {true}; //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);

        return data[0].toString();
    }

    public Object[][] getDatosCie(String id) {
        String colName[] = {
            "id", "descripcion", "genero", "edadMin", "edadMax"
        };
        String origen = " bdCies";
        Object dtDatos[][] = getDatos(colName, origen, "select id,descripcion,genero,edadMin,edadMax FROM bdCies where Id = '" + id + "'", " where Id = '" + id + "'");
        return dtDatos;
    }

    public Object[][] getFacturasAbonos(String id) {
        String colName[] = {
            "factura"
        };
        String origen = " bdCxc ";
        Object dtDatos[][] = getDatos(colName, origen, "select factura FROM bdCxc where recibo = '" + id + "'", " where recibo = '" + id + "'");
        return dtDatos;
    }

    public String getNombreCup(String id) {

        String instruccion_sql = "select descripcion from bdCups where Id='" + id + "'; ";
        String[] colName = {"descripcion"};
        boolean[] cadena = {true}; //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);

        return data[0].toString();
    }
    
    public boolean agregarMutiempresas(String id, String descripcion, String base) {
        boolean ok = false;
        String instruccion_sql = "insert into bdMultiEmpresa(id, descripcion, base) values(?,?,?);";
        ok = Agregar_Registro(new Object[]{id, descripcion, base}, null, instruccion_sql);
        return ok;
    }

    public boolean agregarLogError(String id, String tipo, String resolucion, String fechaResolucion, String prefijo, String numeracionDel, String numeracionHasta,
            String fechaInicio, String tipoF, String diseno, String modeloContable) {
        boolean ok = false;
        String instruccion_sql = "insert into bdLogErroresDetalle(id, tipo, resolucion, fechaResolucion, prefijo, "
                + "numeracionDel, numeracionHasta, consecutivo, inicioResolucion, tipoF, diseno, modeloContable) values(?,?,?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(new Object[]{id, tipo, resolucion, fechaResolucion, prefijo, numeracionDel,
            numeracionHasta, "1", fechaInicio, tipoF, diseno, modeloContable}, null, instruccion_sql);
        return ok;
    }

    public Object[] getResolucionTerminales(String ter) {
        String instruccion_sql = " select id, tipo, resolucion, fechaResolucion, prefijo, numeracionDel, numeracionHasta, consecutivo, inicioResolucion, "
                + "tipoF, diseno, modeloContable FROM bdLogErroresDetalle where id = '" + ter + "'; ";
        String colName[] = {"id", "tipo", "resolucion", "fechaResolucion", "prefijo", "numeracionDel", "numeracionHasta", "consecutivo",
            "inicioResolucion", "tipoF", "diseno", "modeloContable"};
        boolean[] cadena = {true, true, true, true, true, true, true, true, true, true, true, true};
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        return data;
    }

    public boolean modificarUsuario(ndUsuario nodo) {
        boolean ok = false;
        String instruccion_sql = "update bdUsuario set nombre=?, contra=?, facturas=?, congeladas=?, cotizaciones=?, planSepares=?, creditos=?, pedidos=?, "
                + "ordenesServicio=?, anulaciones=?, reimpresion=?, reportesVentas=?, reporteVentasAnuladas=?, reporteCotizaciones=?, reportePlanSepares=?, reporteCreditos=?, "
                + "reporteOrdenesServicio=?, reporteIvasVentas=?, reporteDia=?, creacionProductos=?, ordenesCompras=?, compras=?, trasladosExternos=?, trasladosInternos=?, "
                + "ajustesEntrada=?, ajustesSalida=?, grupos=?, disenos=?, armados=?, inventarioInicial=?, trasladoDetalle=?, kardex=?, reporteProductos=?, reportePrestamos=?, "
                + "reporteCompras=?, reporteAjustes=?, reporteOrdenCompras=?, reporteInventario=?, reporteTrasladoInterno=?, reporteIvasCompras=?, reporteMovimientoSerial=?, "
                + "crearClientes=?, crearProveedor=?, crearEmpleado=?, bodegasInternas=?, clientesPrestamos=?, showRoom=?, reporteClientes=?, reporteProveedor=?, "
                + "reporteEmpleados=?, abonosCxC=?, abonosCxP=?, notasCredito=?, reporteCarteraCxC=?, reporteAbonosCxC=?, reporteCarteraCxP=?, reporteAbonosCxP=?, "
                + "reporteNotasCredito=?, codigos=?, egresos=?, bancos=?, cuadreCaja=?, reporteEgresos=?, reporteBancos=?, reporteCuadreCaja=?, registrarAgenda=?, "
                + "reportesAgenda=?, mascotas=?, historiaClinica=?, guarderia=?, peluqueria=?, hospitalizacion=?, alertas=?, reporteMascotas=?, reporteGuarderia=?, "
                + "reportePeluqueria=?, reporteHospitalizacion=?, formatos=?, ordenServicioMedico=?, historiaClinicaMedico=?, formatosMedico=?, convenio=?, notaEnfermeria=?, "
                + "medicamentos=?, reporteOrdenesMedicas=?, reporteFormulas=?, certificadoEstudiante=?, certificadoMedico=?, certificadoOyS=? "
                + "where usuario = '" + nodo.getUsuario() + "' ;";
        ok = Actualizar_Registro(metodos.pasarModUsuario(nodo), null, instruccion_sql);
        return ok;
    }

    public boolean agregarUsuario(ndUsuario nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdUsuario(nombre, usuario, contra, facturas, congeladas, cotizaciones, planSepares, creditos, pedidos, ordenesServicio, anulaciones, reimpresion, reportesVentas, "
                + "reporteVentasAnuladas, reporteCotizaciones, reportePlanSepares, reporteCreditos, reporteOrdenesServicio, reporteIvasVentas, reporteDia, creacionProductos, "
                + "ordenesCompras, compras, trasladosExternos, trasladosInternos, ajustesEntrada, ajustesSalida, grupos, disenos, armados, inventarioInicial, trasladoDetalle, "
                + "kardex, reporteProductos, reportePrestamos, reporteCompras, reporteAjustes, reporteOrdenCompras, reporteInventario, reporteTrasladoInterno, reporteIvasCompras, "
                + "reporteMovimientoSerial, crearClientes, crearProveedor, crearEmpleado, bodegasInternas, clientesPrestamos, showRoom, reporteClientes, reporteProveedor, "
                + "reporteEmpleados, abonosCxC, abonosCxP, notasCredito, reporteCarteraCxC, reporteAbonosCxC, reporteCarteraCxP, reporteAbonosCxP, reporteNotasCredito, codigos, "
                + "egresos, bancos, cuadreCaja, reporteEgresos, reporteBancos, reporteCuadreCaja, registrarAgenda, reportesAgenda, mascotas, historiaClinica, guarderia, "
                + "peluqueria, hospitalizacion, alertas, reporteMascotas, reporteGuarderia, reportePeluqueria, reporteHospitalizacion, formatos, ordenServicioMedico,"
                + "historiaClinicaMedico, formatosMedico, convenio, notaEnfermeria, medicamentos, reporteOrdenesMedicas, reporteFormulas, certificadoEstudiante, "
                + "certificadoMedico, certificadoOyS)"
                + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosUsuario(nodo), null, instruccion_sql);
        return ok;
    }

    public ndUsuario getDatosUsuario(String usuario) {

        String instruccion_sql = "select nombre, usuario, contra, facturas, congeladas, cotizaciones, planSepares, creditos, pedidos, ordenesServicio, anulaciones, reimpresion, reportesVentas, "
                + "reporteVentasAnuladas, reporteCotizaciones, reportePlanSepares, reporteCreditos, reporteOrdenesServicio, reporteIvasVentas, reporteDia, creacionProductos, "
                + "ordenesCompras, compras, trasladosExternos, trasladosInternos, ajustesEntrada, ajustesSalida, grupos, disenos, armados, inventarioInicial, trasladoDetalle, "
                + "kardex, reporteProductos, reportePrestamos, reporteCompras, reporteAjustes, reporteOrdenCompras, reporteInventario, reporteTrasladoInterno, reporteIvasCompras, "
                + "reporteMovimientoSerial, crearClientes, crearProveedor, crearEmpleado, bodegasInternas, clientesPrestamos, showRoom, reporteClientes, reporteProveedor, "
                + "reporteEmpleados, abonosCxC, abonosCxP, notasCredito, reporteCarteraCxC, reporteAbonosCxC, reporteCarteraCxP, reporteAbonosCxP, reporteNotasCredito, codigos, "
                + "egresos, bancos, cuadreCaja, reporteEgresos, reporteBancos, reporteCuadreCaja, registrarAgenda, reportesAgenda, mascotas, historiaClinica, guarderia, peluqueria,"
                + "hospitalizacion, alertas, reporteMascotas, reporteGuarderia, reportePeluqueria, reporteHospitalizacion, formatos, ordenServicioMedico, historiaClinicaMedico,"
                + "formatosMedico, convenio, notaEnfermeria, medicamentos, reporteOrdenesMedicas, reporteFormulas, certificadoEstudiante, certificadoMedico, certificadoOyS "
                + "from bdUsuario where usuario = '" + usuario + "'; ";
        //columnas de la tabla que se
        String[] colName = {"nombre", "usuario", "contra", "facturas", "congeladas", "cotizaciones", "planSepares", "creditos", "pedidos", "ordenesServicio", "anulaciones",
            "reimpresion", "reportesVentas", "reporteVentasAnuladas", "reporteCotizaciones", "reportePlanSepares", "reporteCreditos", "reporteOrdenesServicio", "reporteIvasVentas",
            "reporteDia", "creacionProductos", "ordenesCompras", "compras", "trasladosExternos", "trasladosInternos", "ajustesEntrada", "ajustesSalida", "grupos", "disenos", "armados",
            "inventarioInicial", "trasladoDetalle", "kardex", "reporteProductos", "reportePrestamos", "reporteCompras", "reporteAjustes", "reporteOrdenCompras", "reporteInventario",
            "reporteTrasladoInterno", "reporteIvasCompras", "reporteMovimientoSerial", "crearClientes", "crearProveedor", "crearEmpleado", "bodegasInternas", "clientesPrestamos",
            "showRoom", "reporteClientes", "reporteProveedor", "reporteEmpleados", "abonosCxC", "abonosCxP", "notasCredito", "reporteCarteraCxC", "reporteAbonosCxC", "reporteCarteraCxP",
            "reporteAbonosCxP", "reporteNotasCredito", "codigos", "egresos", "bancos", "cuadreCaja", "reporteEgresos", "reporteBancos", "reporteCuadreCaja", "registrarAgenda",
            "reportesAgenda", "mascotas", "historiaClinica", "guarderia", "peluqueria", "hospitalizacion", "alertas", "reporteMascotas", "reporteGuarderia", "reportePeluqueria",
            "reporteHospitalizacion", "formatos", "ordenServicioMedico", "historiaClinicaMedico", "formatosMedico", "convenio", "notaEnfermeria", "medicamentos",
            "reporteOrdenesMedicas", "reporteFormulas", "certificadoEstudiante", "certificadoMedico", "certificadoOyS"};
        boolean[] cadena = {true, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false};
        //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);

        ndUsuario nodo = metodos.llenarUsuario(data);

        return nodo;
    }

    public Object[] obtenerDatosUsuarioIniciado(String usuario) {
        String instruccion_sql = "select estado, terminal from bdUsuario where usuario = '" + usuario + "'; ";
        String[] colName = {"estado", "terminal"};
        boolean[] cadena = {true, true};

        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        return data;
    }

    public ndBodega getDatosBodega(String id) {

        String instruccion_sql = "select codigo, nombre, direccion, telefono, usuario, ciudad, abreviado, filas, columnas, tipo, base from bdBodegas where codigo = '" + id + "'; ";
        //columnas de la tabla que se
        String[] colName = {"codigo", "nombre", "direccion", "telefono", "usuario", "ciudad", "abreviado", "filas", "columnas", "tipo", "base"};
        boolean[] cadena = {true, true, true, true, true, true, true, true, true, true, true};
        //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        ndBodega nodo = metodos.llenarBodega(data);

        return nodo;
    }

    public Object[] getInvProd(String id) {

        String instruccion_sql = "select fisicoInventario from bdProductos where Codigo = '" + id + "'; ";
        //columnas de la tabla que se
        String[] colName = {"fisicoInventario"};
        boolean[] cadena = {true};
        //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);

        return data;
    }

    public Object[] getHistProd(String id) {

        String instruccion_sql = "select fisico from historico where Codigo = '" + id + "'; ";
        //columnas de la tabla que se
        String[] colName = {"fisico"};
        boolean[] cadena = {true};
        //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);

        return data;
    }

//    public Object[] getCostoProd(String id) {
//
//        String instruccion_sql = "select costoUnd from inventario where Codigo = '" + id + "'; ";
//        //columnas de la tabla que se
//        String[] colName = {"costoUnd"};
//        boolean[] cadena = {true};
//        //se realiza la consulta
//        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
//
//        return data;
//    }
//    public Object[] getCostoProd(String id) {
//        String instruccion_sql = "select ponderado from bdProductos where Codigo = '" + id + "'; ";
//        //columnas de la tabla que se
//        String[] colName = {"ponderado"};
//        boolean[] cadena = {true};
//        //se realiza la consulta
//        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
//
//        return data;
//    }
    public Object[][] getNotasEnfermeria(String id) {
        String colName[] = {
            "Id", "fecha"
        };
        String origen = " bdNotaEnfermeria ";
        Object dtDatos[][] = getDatos(colName, origen, "select Id, fecha FROM bdNotaEnfermeria where paciente = '"
                + id + "'", " where paciente = '" + id + "'");
        return dtDatos;
    }

    public ndOServicio getDatosOServicio(String id) {

        String instruccion_sql = "select id, placa, tipo, modelo, numeroChasis, fechaCompra, marca, km, numeroMotor, color, problema from bdOServicio where id = '" + id + "'; ";
        //columnas de la tabla que se
        String[] colName = {"id", "placa", "tipo", "modelo", "numeroChasis", "fechaCompra", "marca", "km", "numeroMotor", "color", "problema"};
        boolean[] cadena = {true, true, true, true, true, true, true, true, true, true, true};
        //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);

        ndOServicio nodo = metodos.llenarOServicio(data);

        return nodo;
    }

    public ndCxc getDatosCxc(String id) {
        String instruccion_sql = "select factura, tipo, estado, recibo, valor, plazo, vencimiento, usuario,terminal, cuotas, factura2 "
                + " from bdCxc where factura = '" + id + "' AND (tipo='FACT' OR tipo='SEPARE'); ";

        String[] colName = {"factura", "tipo", "estado", "recibo", "valor", "plazo", "vencimiento", "usuario", "terminal", "cuotas", "factura2"};
        boolean[] cadena = {true, true, true, true, true, true, true, true, true, false, true};
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);

        ndCxc nodo = metodos.llenarCxc(data);

        return nodo;
    }

    public ndCxc getDatosCxcRecibo(String id) {

        String instruccion_sql = "select factura, tipo, estado, recibo, valor, plazo, vencimiento, usuario, terminal, cuotas, factura2 "
                + "from bdCxc where recibo = '" + id + "'; ";
        //columnas de la tabla que se
        String[] colName = {"factura", "tipo", "estado", "recibo", "valor", "plazo", "vencimiento", "usuario", "terminal", "cuotas", "factura2"};
        boolean[] cadena = {true, true, true, true, true, true, true, true, true, false, true};
        //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);

        ndCxc nodo = metodos.llenarCxc(data);

        return nodo;
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
                                try {
//                                    Connection con = DriverManager.getConnection(url, props);
                                    SQLXML xmlVal = mysql.getConnection().createSQLXML();
                                    xmlVal.setString(datos[i].toString());
                                } catch (Exception exx) {
                                }
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

    private boolean Actualizar_Registro1(Object[] datos, String instruccion_sql) {
        boolean ok = false;
        try {
            int i = 1;
            PreparedStatement pstm = mysql.getConnection().prepareStatement(instruccion_sql);

            if (datos != null) {
                for (i = 1; i < datos.length; i++) {

//                                    Connection con = DriverManager.getConnection(url, props);
                    SQLXML xmlVal = mysql.getConnection().createSQLXML();
                    xmlVal.setString(datos[i].toString());
                    pstm.setString(i, xmlVal.getString());
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

    public Object[][] getDatosVitrina(String vitrina) {
        String colName[] = {
            "Id", "nombre", "capacidad", "estado"
        };
        String origen = " bdVitrina";
        Object dtDatos[][] = getDatos(colName, origen, "select Id, nombre, capacidad, estado from bdVitrina where Id = '" + vitrina + "'", " where Id = '" + vitrina + "'");

        return dtDatos;
    }

    public Object[][] getDatosProductosVitrina(String consulta) {
        String colName[] = {
            "idProd", "codigo", "descripcion", "estado", "idVitrina"
        };
        String origen = " bdDetalleVitrinas";
        Object dtDatos[][] = getDatos(colName, origen, "select idProd, codigo, descripcion, estado, idVitrina from bdDetalleVitrinas " + consulta, consulta);

        return dtDatos;
    }

    public Object[][] getDatosProductosVitrina1(String consulta) {
        String colName[] = {
            "idProd", "descripcion", "codigo"
        };
        String origen = " bdDetalleVitrinas";
        Object dtDatos[][] = getDatos(colName, origen, "select idProd, descripcion, codigo from bdDetalleVitrinas " + consulta, consulta);

        return dtDatos;
    }

    public Object[][] getDatosFacturaPrefijo(String factura) {
        String colName[] = {
            "terminal", "cliente", "nombre", "total", "fechaFactura", "factura"
        };
        String origen = " facturaPrefijo";
        Object dtDatos[][] = getDatos(colName, origen, "select terminal, cliente, nombre, total, fechaFactura, factura from facturaPrefijo where factura = '" + factura + "'", " where factura = '" + factura + "'");
        return dtDatos;
    }

    public Object[][] getProductosRelacionados(String prod, String principal) {
        String colName[] = {
            "idProducto", "prod", "nombre", "relacion", "nombreProd", "cant"
        };
        String origen = " bdRelacionados";
        Object dtDatos[][] = getDatos(colName, origen, "select idProducto, prod, nombre, relacion, nombreProd, cant from "
                + " bdRelacionados where prod = '" + prod + "' and idProducto = '" + principal + "' ",
                " where prod = '" + prod + "' and idProducto = '" + principal + "' ");

        return dtDatos;
    }

    public Object[][] getCodigosRelacionados(String prod, String condi) {
        String colName[] = {
            "idProducto", "codigo"
        };
        String origen = " bdCodigosRelacionados";
        Object dtDatos[][] = getDatos(colName, origen, "select idProducto, codigo from "
                + " bdCodigosRelacionados " + condi + " = '" + prod + "' ", condi + " = '" + prod + "' ");

        return dtDatos;
    }

    public DefaultTableModel getDatosFacturaPrefijoTabla(String factura) {
        String columNames[] = {
            "Terminal", "Cliente", "Nombre", "Total", "Fecha Factura"
        };
        String colName[] = {
            "terminal", "cliente", "nombre", "total", "fechaFactura"
        };
        String origen = " facturaPrefijo";
        Object dtDatos[][] = getDatos(colName, origen, "select terminal, cliente,nombre,total,fechaFactura from facturaPrefijo where factura = '" + factura + "'", " where factura = '" + factura + "'");
        for (int i = 0; i < dtDatos.length; i++) {
            dtDatos[i][3] = big.setMoneda(big.getBigDecimal(dtDatos[i][3]));
            dtDatos[i][4] = metodos.fecha(dtDatos[i][4].toString());
        }
        DefaultTableModel datos = new DefaultTableModel(dtDatos, columNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        return datos;
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

    public DefaultTableModel getRegistrosAntecedentes(int Tipo) {
        String columNames[] = {
            "Id", "Nombre"
        };
        String colName[] = {
            "Id", "dato"
        };
        String origen = " bdRegAntecedentes";
        Object dtDatos[][] = getDatos(colName, origen, "select Id, dato from bdRegAntecedentes where tipo = " + Tipo, " where tipo = " + Tipo);

        DefaultTableModel datos = new DefaultTableModel(dtDatos, columNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        return datos;
    }

    public DefaultTableModel getRegistrosReferidos(String condicion) {
        String columNames[] = {
            "Id", "Nombre", "Telefono", "celular", "Seleccione"
        };
        String colName[] = {
            "Id", "nombre", "telefono", "celular", "sel"
        };
        String origen = " referidos " + condicion;
//        Object dtDatos[][] = getDatos2(colName, origen, "select Id, nombre,telefono,celular,direccion, false as sel from bdTerceros " + condicion, condicion);
        Object dtDatos2[][] = GetTabla(colName, origen, (new StringBuilder()).append("select Id, nombre, telefono, celular, false as sel from" + origen + " ORDER BY nombre ").toString(), new Integer[]{4});

        DefaultTableModel datos = new DefaultTableModel(dtDatos2, columNames) {
            public boolean isCellEditable(int row, int column) {
                if (column == 4) {
                    return true;
                }
                return false;

            }

            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };

        return datos;
    }

    public Object[][] getPosicionesMesas() {
        String colName[] = {
            "ubicacion", "nombre"
        };
        String origen = " bdMesas";
        Object dtDatos[][] = GetTabla(colName, origen, (new StringBuilder()).append("select ubicacion, nombre from ").append(origen).toString());

        return dtDatos;
    }

    public Object[][] getExamenesLaboratorio() {
        String colName[] = {
            "codigo", "descripcion", "valor", "codProd"
        };
        String origen = " bdExamenesLaboratorio";
        Object dtDatos[][] = GetTabla(colName, origen, (new StringBuilder()).append("select codigo, descripcion, valor, codProd from ").append(origen).toString());

        return dtDatos;
    }

    public Object[][] getGruposFactura() {
        String colName[] = {
            "codigo", "nombre", "imagen", "impresora"
        };
        String origen = " bdGrupo";
        Object dtDatos[][] = GetTabla(colName, origen, (new StringBuilder()).append("select codigo, nombre, imagen, impresora from ").append(origen).toString());

        return dtDatos;
    }

    public Object[][] getRegistrosSubgrupos() {
        String colName[] = {
            "codigo", "nombre"
        };
        String origen = " bdSubgrupo ";
        Object dtDatos[][] = getDatos(colName, origen, "select codigo, nombre from bdSubgrupo ", "");

        return dtDatos;
    }

    public Object[][] getRegistrosGrupos() {
        String colName[] = {
            "codigo", "nombre", "imagen", "impresora"
        };
        String origen = " bdGrupo ";
        Object dtDatos[][] = getDatos(colName, origen, "select codigo, nombre, imagen, impresora from bdGrupo where activo = false ", " where activo = false ");

        return dtDatos;
    }

    public Object[][] getImpresoras() {
        String colName[] = {
            "nombre"
        };
        String origen = " bdImpresoras";
        Object dtDatos[][] = GetTabla(colName, origen, (new StringBuilder()).append("select nombre from ").append(origen).toString());

        return dtDatos;
    }

    public Object[][] getDatosExamenLaboratorio(String codigo) {
        String colName[] = {
            "codigo", "descripcion", "valor", "codProd"
        };
        String origen = " bdExamenesLaboratorio ";
        Object dtDatos[][] = getDatos(colName, origen, "select codigo, descripcion, valor, codProd from bdExamenesLaboratorio where codigo = '" + codigo + "' ", " where codigo = '" + codigo + "' ");

        return dtDatos;
    }

    public Object[][] getGruposVisualizarFactura() {
        String colName[] = {
            "codigo", "nombre", "imagen", "visualizar"
        };
        String origen = " bdGrupo ";
        Object dtDatos[][] = getDatos(colName, origen, "select codigo, nombre, imagen, visualizar from bdGrupo"
                + " where visualizar = true and activo = false", " where visualizar = true and activo = false");

        return dtDatos;
    }

    public DefaultTableModel getRegistrosGrupos2() {
        String columNames[] = {
            "Codigo", "Descripción"
        };
        String colName[] = {
            "codigo", "nombre"
        };
        String origen = " bdGrupo";
        Object dtDatos[][] = GetTabla(colName, origen, (new StringBuilder()).append("select codigo, nombre from bdGrupo ORDER BY nombre").toString());
        DefaultTableModel datos = new DefaultTableModel(dtDatos, columNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        return datos;
    }

    public Object[][] getRegistrosGrupos3() {
        String columNames[] = {
            "Codigo", "Descripción"
        };
        String colName[] = {
            "codigo", "nombre"
        };
        String origen = " bdGrupo";
        Object dtDatos[][] = GetTabla(colName, origen, (new StringBuilder()).append("select codigo, nombre from bdGrupo ORDER BY nombre").toString());
        return dtDatos;
    }

    public DefaultTableModel getRegistrosSubGrupos(String condicion) {
        String columNames[] = {
            "Codigo", "Descripción"
        };
        String colName[] = {
            "codigo", "nombre"
        };
        String origen = "bdSubGrupo " + condicion;
        Object dtDatos[][] = GetTabla(colName, origen, (new StringBuilder()).append("select codigo, nombre "
                + "from bdSubGrupo " + condicion + " ORDER BY nombre").toString());

        DefaultTableModel datos = new DefaultTableModel(dtDatos, columNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        return datos;
    }

    public DefaultTableModel getRegistrosProductos(boolean conIva, String consulta, String base) {

        String pvp = "pvp";
        if (conIva) {
            pvp = "L1";
        }

        String columNames[] = {
            "Codigo", "Descripcion", "Referencia", "PVP", "Inv", "ubicacion", "Sel", "Cant", "Grupo", "U. Costo", "idSistema", "Ver",
            "Usuario", "manejaInventario", "PLU", "Ind", "descripcionLarga"
        };
        String colName[] = {
            "Codigo", "Descripcion", "referencia", pvp, "inventario", "ubicacion1", "sel", "campoVacio", "Grupo", "ultimoCosto",
            "idSistema", "campoVacio1", "Usuario", "manejaInventario", "plu2", "indVentas", "descripcionLarga"
        };

        String origen = base;

        Object dtDatos2[][] = GetTabla(colName, origen, (new StringBuilder()).append(consulta).toString(), new Integer[]{6});

        DefaultTableModel datos = new DefaultTableModel(dtDatos2, columNames) {
            public boolean isCellEditable(int row, int column) {
                if (column == 6 || column == 7) {
                    return true;
                }
                return false;
            }

            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        return datos;
    }

    public DefaultTableModel getSerialesProductos(String consulta) {
        String columNames[] = {
            "Descripcion", "Imei / Serial", "Color"
        };
        String colName[] = {
            "descripcion", "imei", "color"
        };

        String origen = " bdDetalleProductos ";
        Object dtDatos2[][] = GetTabla(colName, origen, (new StringBuilder()).append("select descripcion, imei, color "
                + "from " + origen + " WHERE imei <> '' GROUP BY descripcion, imei, color, bodega ORDER BY descripcion ").toString());

        DefaultTableModel datos = new DefaultTableModel(dtDatos2, columNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        return datos;
    }

    public DefaultTableModel getRegistrosProblemas() {
        String columNames[] = {
            "Id", " ", "Sel"
        };
        String colName[] = {
            "Id", "problema", "sel"
        };
        String origen = " bdProblemas ";
        Object dtDatos2[][] = GetTabla(colName, origen, (new StringBuilder()).append("select Id, problema, false as sel from " + origen).toString(), new Integer[]{2});
        DefaultTableModel datos = new DefaultTableModel(dtDatos2, columNames) {
            public boolean isCellEditable(int row, int column) {
                if (column == 2) {
                    return true;
                }
                return false;
            }

            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        return datos;
    }

    public Object[][] getDatosCotizacionesPendientes(String condicion) {
        String colName[] = {"producto", "cantidad",};
        String sql = "SELECT producto, Sum(cotiza.cantidad) AS cantidad "
                + "FROM cotiza " + condicion
                + "GROUP BY producto";
        System.out.println(sql);
        Object dtDatos[][] = getDatos(colName, "( " + sql + ")", sql, "");

        return dtDatos;
    }

    public DefaultTableModel getRegistrosServicios() {
        String columNames[] = {
            "Codigo", "Descripción"
        };
        String colName[] = {
            "Codigo", "Descripcion"
        };
        String origen = " bdProductos";
        Object dtDatos[][] = getDatos(colName, origen, "select Codigo, Descripcion from bdProductos where Grupo='SERVICIO'", " where Grupo='SERVICIO'");

        DefaultTableModel datos = new DefaultTableModel(dtDatos, columNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        return datos;
    }

    public DefaultTableModel getRegistrosCups() {
        String columNames[] = {
            "Codigo", "Descripción"
        };
        String colName[] = {
            "Id", "descripcion"
        };
        String origen = " bdCups";
        Object dtDatos[][] = GetTabla(colName, origen, (new StringBuilder()).append("select Id, descripcion from ").append(origen).toString());
        DefaultTableModel datos = new DefaultTableModel(dtDatos, columNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        return datos;
    }

    public DefaultTableModel getRegistrosCies() {
        String columNames[] = {
            "Codigo", "Descripción"
        };
        String colName[] = {
            "Id", "descripcion"
        };
        String origen = " bdCies";
        Object dtDatos[][] = GetTabla(colName, origen, (new StringBuilder()).append("select Id, descripcion from ").append(origen).toString());
        DefaultTableModel datos = new DefaultTableModel(dtDatos, columNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        return datos;
    }

//    public DefaultTableModel getRegistrosEps() {
//        String columNames[] = {
//            "Codigo", "Razon social"
//        };
//        String colName[] = {
//            "Id", "nombre"
//        };
//        String origen = " conEps";
//        Object dtDatos[][] = GetTabla(colName, origen, (new StringBuilder()).append("select Id, nombre from ").append(origen).toString());
//        DefaultTableModel datos = new DefaultTableModel(dtDatos, columNames) {
//            public boolean isCellEditable(int row, int column) {
//                return false;
//            }
//        };
//        return datos;
//    }
    public DefaultTableModel getRegistrosProcesos() {
        String columNames[] = {
            "Codigo", "Nombre"
        };
        String colName[] = {
            "codigo", "nombre"
        };
        String origen = " bdProceso";
        Object dtDatos[][] = GetTabla(colName, origen, (new StringBuilder()).append("select codigo, nombre from ").append(origen).toString());
        DefaultTableModel datos = new DefaultTableModel(dtDatos, columNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        return datos;
    }

    public Object[][] getUsuarios() {
        String colName[] = {
            "usuario"
        };
        String origen = " bdUsuario";
        Object dtDatos[][] = GetTabla(colName, origen, (new StringBuilder()).append("select usuario from ").append(origen).toString());

        return dtDatos;
    }

    public Object[][] getRegistrosParqueadero() {
        String colName[] = {
            "placa", "hIngreso"
        };
        String origen = " repParqueadero";
        Object dtDatos[][] = GetTabla(colName, origen, (new StringBuilder()).append("select placa, hIngreso from ").append(origen).toString());

        return dtDatos;
    }

    public Object[][] getCuotasPrestamo(String contrato) {
        String colName[] = {
            "numCuota", "fecha", "total", "fechaAbono", "id", "mora", "totalAbono", "saldoAtrasado", "saldoMora", "usuario"
        };
        String origen = " bdCuota";
        Object dtDatos[][] = getDatos(colName, origen, "select numCuota, fecha, total, fechaAbono, id, mora,totalAbono,saldoAtrasado,"
                + "saldoMora,usuario from bdCuota where contrato = '" + contrato + "'", " where contrato = '" + contrato + "'");

        return dtDatos;
    }

    public String getEmpresaPricipal(String id) {
        String colName[] = {
            "base"
        };
        String origen = " bdMultiEmpresa";
        Object dtDatos[][] = getDatos(colName, origen, "select base from bdMultiEmpresa where id = '" + id + "' ", " where id = '" + id + "' ");

        try {
            return dtDatos[0][0].toString();
        } catch (Exception e) {
            return "";
        }
    }

    public String estadoCuotasCreditos(String contrato, String numCuota) {
        String colName[] = {
            "estado"
        };
        String origen = " estadoCuotasCreditos";
        Object dtDatos[][] = getDatos(colName, origen, "select estado from estadoCuotasCreditos where contrato = '" + contrato + "' and numCuota = " + numCuota + "", " where contrato = '" + contrato + "' and numCuota = " + numCuota + "");

        return dtDatos[0][0].toString();
    }

    public String getGrupo(String producto) {
        String colName[] = {
            "Grupo"
        };
        String origen = " bdProductos";
        Object dtDatos[][] = getDatos(colName, origen, "select Grupo from bdProductos where Codigo = '" + producto + "' ", " where Codigo = '" + producto + "' ");

        return dtDatos[0][0].toString();
    }

    public Object[][] getRegistrosPeluqueria() {
        String colName[] = {
            "placa", "fIngreso"
        };
        String origen = " repPeluqueria";
        Object dtDatos[][] = GetTabla(colName, origen, (new StringBuilder()).append("select placa, fIngreso from ").append(origen).toString());

        return dtDatos;
    }

    public DefaultTableModel getRegistrosTerceros(String consulta) {
        String columNames[] = {
            "Id", "nombre", "Dirección", "Teléfono", "idSistema", "Ciudad"
        };
        String colName[] = {
            "Id", "nombre", "direccion", "telefono", "idSistema", "Ciudad"
        };
        String origen = "bdTerceros" + consulta;
        Object dtDatos[][] = GetTabla(colName, origen, (new StringBuilder()).append("select Id, nombre, direccion, telefono, idSistema, ciudad from ").append(origen).toString());
        DefaultTableModel datos = new DefaultTableModel(dtDatos, columNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        return datos;
    }

    public DefaultTableModel getRegistrosTaxis() {
        String columNames[] = {
            "Vehiculo", "placa"
        };
        String colName[] = {
            "Vehiculo", "placa"
        };
        String origen = " bdTaxi";
        Object dtDatos[][] = GetTabla(colName, origen, (new StringBuilder()).append("select Vehiculo, placa from ").append(origen).toString());
        DefaultTableModel datos = new DefaultTableModel(dtDatos, columNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        return datos;
    }

    public DefaultTableModel getRegistrosEmpleados() {
        String columNames[] = {
            "Id", "nombre"
        };
        String colName[] = {
            "Id", "nombre"
        };
        String origen = " bdTerceros where tipoTercero = 'EMPLEADO' ";
        Object dtDatos[][] = GetTabla(colName, origen, (new StringBuilder()).append("select Id, nombre from ").append(origen).toString());
        DefaultTableModel datos = new DefaultTableModel(dtDatos, columNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        return datos;
    }

    public DefaultTableModel getRegistrosVitrinas() {
        String columNames[] = {
            "Id", "Nombre", "Capacidad"
        };
        String colName[] = {
            "Id", "nombre", "capacidad"
        };
        String origen = " bdVitrina ";
        Object dtDatos[][] = GetTabla(colName, origen, (new StringBuilder()).append("select Id, nombre, capacidad from ").append(origen).toString());
        DefaultTableModel datos = new DefaultTableModel(dtDatos, columNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        return datos;
    }

    public DefaultTableModel getRegistrosBodegas(String consulta) {
        String columNames[] = {
            "Codigo", "Nombre"
        };
        String colName[] = {
            "codigo", "nombre"
        };
        String origen = " bdBodegas " + consulta;
        Object dtDatos[][] = GetTabla(colName, origen, (new StringBuilder()).append("select codigo, nombre from ").append(origen).toString());
        DefaultTableModel datos = new DefaultTableModel(dtDatos, columNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        return datos;
    }

    public DefaultTableModel getRegistrosMascotas() {
        String columNames[] = {
            "Codigo", "Nombre"
        };
        String colName[] = {
            "codigo", "nombre"
        };
        String origen = " bdMascotas";
        Object dtDatos[][] = GetTabla(colName, origen, (new StringBuilder()).append("select codigo, nombre from ").append(origen).toString());
        DefaultTableModel datos = new DefaultTableModel(dtDatos, columNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        return datos;
    }

    public DefaultTableModel getRegistrosProveedores2(String condicion) {
        String columNames[] = {
            "Id", "Nombre", "Telefono", "Direccion"
        };
        String colName[] = {
            "Id", "nombre", "telefono", "direccion"
        };
        String origen = " bdTerceros ";
        System.out.println(condicion);
        Object dtDatos[][] = getDatos(colName, origen, "select Id, nombre, telefono, direccion from bdTerceros " + condicion, condicion);

        DefaultTableModel datos = new DefaultTableModel(dtDatos, columNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        return datos;
    }

    public Object[][] getCodsEgresos() {
        String colName[] = {
            "codigoUsuario", "concepto", "codigo", "activo"
        };
        String origen = " codsEgresos";
        Object dtDatos[][] = GetTabla(colName, origen, (new StringBuilder()).append("select codigoUsuario, concepto, "
                + "codigo, activo from ").append(origen).toString());

        return dtDatos;
    }

    public Object[][] getRazas() {
        String colName[] = {
            "raza", "tipo"
        };
        String origen = " bdRazas";
        Object dtDatos[][] = GetTabla(colName, origen, (new StringBuilder()).append("select raza, tipo from ").append(origen).toString());

        return dtDatos;
    }

    public Object[][] getBancos() {
        String colName[] = {
            "nombre", "numero", "monto",};
        String origen = " bdBancos";
        Object dtDatos[][] = GetTabla(colName, origen, (new StringBuilder()).append("select nombre, numero, monto "
                + "from ").append(origen).toString());
        return dtDatos;
    }

    public Object[][] getBancosEgreso(String egreso) {
        String colName[] = {
            "nombre", "numero", "monto"
        };
        String origen = " bdBancos";
        Object dtDatos[][] = getDatos(colName, origen, "select nombre, numero, monto from bdBancos "
                + "where egreso = '" + egreso + "' ", " where egreso = '" + egreso + "' ");
        return dtDatos;
    }

    public Object[][] getRegistrosGuarderia() {
        String colName[] = {
            "mascota", "producto", "fIngreso", "hIngreso", "Id"
        };
        String origen = " bdGuarderia";
        Object dtDatos[][] = getDatos(colName, origen, "select mascota, producto, fIngreso, hIngreso, Id from bdGuarderia "
                + " where activo = false ", " where activo = false ");
        return dtDatos;
    }

    public Object[][] getBancosPendientes() {
        String colName[] = {
            "Id", "valorMovimiento", "nota", "usuario", "fecha", "tipo"
        };
        String origen = " bdMovimientosBancosPendientes";
        Object dtDatos[][] = GetTabla(colName, origen, (new StringBuilder()).append("select Id, valorMovimiento, nota, usuario, fecha, tipo  from ").append(origen).toString());
        return dtDatos;
    }

    public Object[][] getMovimientoBanco(String Id) {
        String colName[] = {
            "valorMovimiento", "tipo", "cuenta", "numero"
        };
        String origen = " bdMovimientosBancos";
        Object dtDatos[][] = getDatos(colName, origen, "select valorMovimiento, tipo, cuenta, numero from bdMovimientosBancos where Id = '" + Id + "' ",
                " where Id = '" + Id + "' ");

        return dtDatos;
    }

    public Object[][] getMovimientosBancos(String numero) {
        String colName[] = {
            "fecha", "saldoActual", "valorMovimiento", "saldoFinal", "nota", "tipo", "recibo"
        };
        String origen = " bdMovimientosBancos";
        Object dtDatos[][] = getDatos(colName, origen, "select fecha, saldoActual, valorMovimiento, saldoFinal, nota, tipo, recibo from bdMovimientosBancos where numero = '" + numero + "' ", " where numero = '" + numero + "' ");

        return dtDatos;
    }

    public DefaultTableModel getRegistrosVentas(String factura, String id) {
        String columNames[] = {
            "Codigo", "Descripcion", "Valor/Unit", "Cant", "Subtotal", "Descuento", "%", "IVA", "INC", "idProd", "preparacion", "idCosteo", "Total", "Cant2", "idSistema"
        };
        String colName[] = {
            "Codigo", "descripcion", "lista", "cantidad", "subtotal", "descuento", "porcIva", "iva", "impoGeneral", "idProd", "preparacion", "idCosteo", "total", "cant2", "idSistema"
        };
        String origen = " bdFactura";
        Object dtDatos[][] = getDatos(colName, origen, "select bdProductos.Codigo, bdFactura.descripcion, bdFactura.lista, bdFactura.cantidad, bdFactura.subtotal, bdFactura.descuento, "
                + "bdFactura.porcIva, bdFactura.iva, bdFactura.impoGeneral, bdFactura.idProd, bdFactura.preparacion, bdFactura.idCosteo, bdFactura.total, bdFactura.cant2, idSistema "
                + "from bdFactura INNER JOIN bdProductos ON bdFactura.producto = bdProductos.idSistema "
                + "where " + id + "= '" + factura + "'", " where " + id + "= '" + factura + "'");
        DefaultTableModel datos = new DefaultTableModel(dtDatos, columNames) {
            public boolean isCellEditable(int row, int column) {
//                if (column == 3) {
//                    return true;
//                } else {  
                return false;
//                }
            }
        };
        return datos;
    }

    public DefaultTableModel getRegistrosVentasSepares(String factura, String id) {
        String columNames[] = {
            "Codigo", "Descripcion", "Valor/Unit", "Cant", "Subtotal", "Descuento", "%", "IVA", "Total", "Cant2", "idSistema", "idProd"
        };
        String colName[] = {
            "Codigo", "descripcion", "lista", "cantidad", "subtotal", "descuento", "porcIva", "iva", "total", "cant2", "idSistema", "idProd"
        };
        String origen = " bdPlanSepare";
        Object dtDatos[][] = getDatos(colName, origen, "select bdProductos.Codigo, bdPlanSepare.descripcion, bdPlanSepare.lista, bdPlanSepare.cantidad, bdPlanSepare.subtotal, "
                + "bdPlanSepare.descuento, bdPlanSepare.porcIva, bdPlanSepare.iva, bdPlanSepare.total, bdPlanSepare.cant2, idSistema, idProd "
                + "from bdPlanSepare INNER JOIN bdProductos ON bdPlanSepare.producto = bdProductos.idSistema "
                + "where " + id + "= '" + factura + "'", " where " + id + "= '" + factura + "'");
        DefaultTableModel datos = new DefaultTableModel(dtDatos, columNames) {
            public boolean isCellEditable(int row, int column) {
                if (column == 3) {
                    return true;
                } else {
                    return false;
                }
            }
        };
        return datos;
    }

    public DefaultTableModel getRegistrosPlantillas1(String factura) {
        String columNames[] = {
            "Codigo", "Descripcion", "Valor/Unit", "Cant", "Subtotal", "Descuento", "%", "IVA", "Total", "Cant2", "idSistema"
        };
        String colName[] = {
            "Codigo", "descripcion", "lista", "cantidad", "subtotal", "descuento", "porcIva", "iva", "total", "cant2", "idSistema"
        };
        String origen = " bdCuentaCobro";
        Object dtDatos[][] = getDatos(colName, origen, "select bdProductos.Codigo, bdCuentaCobro.descripcion, bdCuentaCobro.lista, bdCuentaCobro.cantidad, "
                + "bdCuentaCobro.subtotal, bdCuentaCobro.descuento, bdCuentaCobro.porcIva, bdCuentaCobro.iva, bdCuentaCobro.total, bdCuentaCobro.cant2, "
                + "idSistema from bdCuentaCobro INNER JOIN bdProductos ON bdCuentaCobro.producto = bdProductos.idSistema "
                + "where idFactura = '" + factura + "'", " where idFactura = '" + factura + "'");
        DefaultTableModel datos = new DefaultTableModel(dtDatos, columNames) {
            public boolean isCellEditable(int row, int column) {
                if (column == 3) {
                    return true;
                } else {
                    return false;
                }
            }
        };
        return datos;
    }

    public DefaultTableModel getRegistrosVentasPlu(String factura) {
        String columNames[] = {
            "Codigo", "Descripcion", "Valor/Unit", "Cantidad", "Subtotal", "Descuento", "% IVA", "IVA", "% Impo", "Impoconsumo", "Total", "Plu", "Cant2",
            "Cant", "Descuento", "imei", "idProd", "Cod", "codEstandar", "porcDescuento", "rango", "unidad", "manejaInventario"
        };
        String colName[] = {
            "producto", "descripcion", "lista", "cantidad", "subtotal", "descuento", "porcIva", "iva", "porcImpo", "impoconsumo", "total", "plu", "cant2",
            "cantidadInicial", "descuentoInicial", "imei", "idProd", "codigo", "codEstandar", "porcDescuento", "rango", "unidad", "manejaInventario"
        };
        String origen = " factura";
        Object dtDatos[][] = getDatos(colName, origen, "select producto, descripcion, lista, cantidad, subtotal, descuento, porcIva, iva, porcImpo, impoconsumo, "
                + "total, plu, cant2, cant2 as cantidadInicial, descuento as descuentoInicial, false as campoVacio, imei, idProd, codigo, codEstandar, "
                + "porcDescuento, rango, unidad, manejaInventario from factura where factura = '" + factura + "'", " where factura = '" + factura + "'");
        DefaultTableModel datos = new DefaultTableModel(dtDatos, columNames) {
            public boolean isCellEditable(int row, int column) {
                if (column == 3) {
                    return true;
                } else {
                    return false;
                }
            }
        };
        return datos;
    }

    public DefaultTableModel getProductosCosteo1(String factura) {
        String columNames[] = {
            "Codigo", "Descripción", "Cantidad", "Plu", "Cant2", "Cambio", "Costo P", "Ultimo C.", "Subtotal", "Alto", "Ancho"
        };

        String colName[] = {
            "producto", "Descripcion", "cantidad2", "plu", "cantidad", "opcionCambio", "nuevoPonderado", "ultimoCosto", "alto", "ancho"
        };

        String origen = " conDisCosteo where codigo = '" + factura + "'";
        Object dtDatos2[][] = GetTabla(colName, origen, (new StringBuilder()).append("select producto, Descripcion, cantidad2, plu, cantidad,"
                + " opcionCambio, nuevoPonderado, ultimoCosto, alto, ancho from conDisCosteo where codigo = '" + factura + "' ").toString(), new Integer[]{5});
        DefaultTableModel datos = new DefaultTableModel(dtDatos2, columNames) {
            public boolean isCellEditable(int row, int column) {
                if (column == 5 || column == 2 || column == 6 || column == 9 || column == 10) {
                    return true;
                }
                return false;
            }

            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        return datos;
    }

    public DefaultTableModel getProductosCosteo(String factura) {
        String columNames[] = {
            "Codigo", "Descripción", "Cantidad", "Plu", "Cant2", "Cambio", "Costo P", "Ultimo C.", "Subtotal"
        };

        String colName[] = {
            "producto", "Descripcion", "cantidad2", "plu", "cantidad", "opcionCambio", "nuevoPonderado", "ultimoCosto"
        };

        String origen = " conDisCosteo where codigo = '" + factura + "'";
        Object dtDatos2[][] = GetTabla(colName, origen, (new StringBuilder()).append("select producto, Descripcion, cantidad2, plu, cantidad, opcionCambio, nuevoPonderado, "
                + "ultimoCosto from conDisCosteo where codigo = '" + factura + "' ").toString(), new Integer[]{5});
        DefaultTableModel datos = new DefaultTableModel(dtDatos2, columNames) {
            public boolean isCellEditable(int row, int column) {
                if (column == 5 || column == 2) {
                    return true;
                }
                return false;
            }

            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        return datos;
    }

    public DefaultTableModel getProductosConvenio(String lista) {
        String columNames[] = {
            "Codigo", "Descripcion", "Precio", "Convenio"
        };
        String colName[] = {
            "Codigo", "Descripcion", "L1", "L" + lista
        };
        String origen = " bdProductos";
        Object dtDatos[][] = getDatos(colName, origen, "select Codigo, Descripcion, L1, L" + lista + " from bdProductos where Codigo<>'EFECTIVO'", " where Codigo<>'EFECTIVO'");
        DefaultTableModel datos = new DefaultTableModel(dtDatos, columNames) {
            public boolean isCellEditable(int row, int column) {
                if (column == 3) {
                    return true;
                } else {
                    return false;
                }
            }
        };
        return datos;
    }

    public Object[][] getCantidadesDiscosteo(String prod) {
        String colName[] = {
            "producto", "cantidad"
        };
        String origen = " bdDisCosteo";
        Object dtDatos[][] = getDatos(colName, origen, "select producto, cantidad from bdDisCosteo where codigo = '" + prod + "'",
                " where codigo = '" + prod + "'");

        return dtDatos;
    }

    public Object[][] getArmado(String prod) {
        String colName[] = {
            "producto", "total"
        };
        String origen = " bdCosteoProd";
        Object dtDatos[][] = getDatos(colName, origen, "select producto, total from bdCosteoProd where documento = '" + prod + "'",
                " where documento = '" + prod + "'");
        return dtDatos;
    }

    public Object[][] getArmado1(String prod) {
        String colName[] = {
            "producto", "unidades"
        };
        String origen = " bdCosteo";
        Object dtDatos[][] = getDatos(colName, origen, "select producto, unidades from bdCosteo where Id = '" + prod + "'",
                " where Id = '" + prod + "'");
        return dtDatos;
    }

//    public Object[][] getTodosProductos() {
//        String colName[] = {
//            "Codigo"
//        };
//        String origen = " bdProductos";
//        Object dtDatos[][] = getDatos(colName, origen, "select Codigo from bdProductos where Codigo<>'EFECTIVO'", " where Codigo<>'EFECTIVO'");
//
//        return dtDatos;
//    }
    public DefaultTableModel getPorcentajesConvenio(String lista) {
        String columNames[] = {
            "Codigo", "Descripcion", "Porcentaje", "Convenio"
        };
        String colName[] = {
            "Codigo", "Descripcion", "L1", "L" + lista
        };
        String origen = " bdProductos";
        Object dtDatos[][] = getDatos(colName, origen, "select Codigo, Descripcion, L1, L" + lista + " from bdProductos where Grupo='SERVICIO'", " where Grupo='SERVICIO'");
        DefaultTableModel datos = new DefaultTableModel(dtDatos, columNames) {
            public boolean isCellEditable(int row, int column) {
                if (column == 3) {
                    return true;
                } else {
                    return false;
                }
            }
        };
        return datos;
    }

    public Object[][] getRegistrosCotizas(String factura) {
        String colName[] = {
            "producto", "descripcion", "lista", "cantidad", "subtotal", "porcDescuento", "descuento", "porcIva", "iva", "total", "plu", "cant2",
            "cliente", "subtotalGeneral", "descuentoGeneral", "ivaGeneral", "totalGeneral", "rango", "estadoGeneral", "vendedor", "observacion", "bodega"
        };
        String origen = " cotizacion";
        Object dtDatos[][] = getDatos(colName, origen, "select producto, descripcion, lista, cantidad, subtotal, porcDescuento, descuento, porcIva, iva, "
                + "total, plu, cant2, cliente, subtotalGeneral, descuentoGeneral, ivaGeneral, totalGeneral, rango, estadoGeneral, vendedor, observacion, bodega "
                + "from cotizacion where idFactura = '" + factura + "'", " where idFactura = '" + factura + "'");

        return dtDatos;
    }

    public Object[][] getPlantilla(String factura) {
        String colName[] = {
            "proceso", "tiempo", "ultimo"};

        String origen = " bdPlantilla";
        Object dtDatos[][] = getDatos(colName, origen, "select proceso, tiempo, ultimo from bdPlantilla where producto = '" + factura + "'", " where producto = '" + factura + "'");

        return dtDatos;
    }

    public Object[][] getRegistrosAbonosCuentaCobro(String factura) {
        String colName[] = {
            "factura", "tipo", "SumaDevalor"
        };
        String origen = " abonosCuentaCobro";
        Object dtDatos[][] = getDatos(colName, origen, "select factura, tipo, SumaDevalor FROM abonosCuentaCobro where factura = '" + factura + "'", " where factura = '" + factura + "'");
        return dtDatos;
    }

    public Object[][] getRegistrosAbonos(String factura) {

        String colName[] = {
            "factura", "tipo", "SumaDevalor"
        };
        String origen = " abonosFacturacion";
        Object dtDatos[][] = getDatos(colName, origen, "select factura, tipo, SumaDevalor FROM abonosFacturacion where facturaTerm = '" + factura + "'", " where facturaTerm = '" + factura + "'");

        return dtDatos;
    }

    public Object[][] getRegistrosAbonosTerminal(String factura) {
        String colName[] = {
            "factura", "tipo", "SumaDevalor"
        };
        String origen = " conAbonos";
        Object dtDatos[][] = getDatos(colName, origen, "select factura, tipo, SumaDevalor FROM conAbonos where facturaTerm = '" + factura + "'", " where facturaTerm = '" + factura + "'");

        return dtDatos;
    }

    public Object[][] getNcCliente(String cliente) {
        String colName[] = {
            "restante", "Id", "factura"
        };
        String origen = " bdNc";
        Object dtDatos[][] = getDatos(colName, origen, "select restante, Id, factura FROM bdNc where cliente = '" + cliente + "' AND restante <> 0 AND reembolsar = false",
                " where cliente = '" + cliente + "' AND restante <> 0 AND reembolsar = false");

        return dtDatos;
    }

    public DefaultTableModel getRegistrosCxc(String factura, String condicion) {
        String columNames[] = {
            "Factura", "Doc.Cliente", "Nom.Cliente", "Fecha Factura", "Fecha Venc.", "Valor", "Saldo", "Edad Cart.", "Estado", "Factura Interna",
            "cuotas", "Conse.Manual", "Vendedor", "Placa"
        };
        String colName[] = {
            factura + "2", "cliente", "nombre", "fechaFactura", "fechaVencimiento", "valor", "saldo", "edadCartera", "estadoVencimiento", "factura", "cuotas", "turno",
            "vendedor", "placa"
        };
        String origen = " conCxc " + condicion;
        String sql = "select " + factura + " AS " + factura + "2, cliente, nombre, fechaFactura, fechaVencimiento, valor,saldo,edadCartera,estadoVencimiento, "
                + "saldo,factura,terminal,cuotas, turno, vendedor, placa from conCxc " + condicion + " ORDER BY orden, ordenId";
        Object dtDatos[][] = GetTabla(colName, origen, (new StringBuilder()).append(sql).toString());

        DefaultTableModel datos = new DefaultTableModel(dtDatos, columNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        return datos;
    }

    public DefaultTableModel getRegistrosCxpPorProveedor(String cliente) {

        String columNames[] = {
            "Ingreso", "Factura", "NIT", "Nom. Proveedor", "Fecha", "fechaVencimiento", "Valor", "Saldo", "edadCartera", "Estado", "Seleccionar"
        };

        String colName[] = {
            "ingreso", "tipo", "proveedor", "nombre", "fechaFactura", "fechaVencimiento", "valor", "saldo", "edadCartera", "estadoVenc", "sel"
        };

        String origen = " conCxp where proveedor = '" + cliente + "'";
        Object dtDatos2[][] = GetTabla(colName, origen, (new StringBuilder()).append("select ingreso, tipo, proveedor, nombre, fechaFactura, fechaVencimiento, valor, saldo, edadCartera, estadoVenc, false as sel from conCxp where proveedor = '" + cliente + "' ORDER BY fechaFactura").toString(), new Integer[]{10});
        DefaultTableModel datos = new DefaultTableModel(dtDatos2, columNames) {
            public boolean isCellEditable(int row, int column) {
                if (column == 10) {
                    return true;
                }
                return false;
            }

            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        return datos;
    }

    public DefaultTableModel getRegistrosCxcPorCliente(String factura, String cliente) {

        String columNames[] = {
            "Factura", "Factura", "Fecha Factura", "Fecha Venc.", "Valor", "Saldo", "Edad Cart.", "Estado", "Factura Interna", "Seleccionar"
        };
        String colName[] = {
            "factura2", "facturaTerm", "fechaFactura", "fechaVencimiento", "valor", "saldo", "edadCartera", "estadoVencimiento", "factura", "sel"
        };
        String origen = " conCxc where cliente = '" + cliente + "'";
        Object dtDatos2[][] = GetTabla(colName, origen, (new StringBuilder()).append("select factura AS factura2, facturaTerm, fechaFactura, fechaVencimiento, valor, saldo, edadCartera, estadoVencimiento, factura,false as sel from conCxc where cliente = '" + cliente + "' ORDER BY fechaFactura").toString(), new Integer[]{9});
        DefaultTableModel datos = new DefaultTableModel(dtDatos2, columNames) {
            public boolean isCellEditable(int row, int column) {
                if (column == 9) {
                    return true;
                }
                return false;
            }

            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        return datos;
    }

    public DefaultTableModel getRegistrosCxcDomicilioPorCliente(String factura, String cliente) {

        String columNames[] = {
            "Factura", "Factura", "Fecha Factura", "Fecha Venc.", "Valor", "Saldo", "Edad Cart.", "Estado", "Factura Interna", "Seleccionar"
        };
        String colName[] = {
            "factura2", "facturaTerm", "fechaFactura", "fechaVencimiento", "valor", "saldo", "edadCartera", "estadoVencimiento", "factura", "sel"
        };
        String origen = " conDomicilio where cliente = '" + cliente + "'";
        Object dtDatos2[][] = GetTabla(colName, origen, (new StringBuilder()).append("select factura AS factura2, facturaTerm, fechaFactura, fechaVencimiento, valor, saldo, edadCartera, estadoVencimiento, factura,false as sel from conDomicilio where cliente = '" + cliente + "' ORDER BY fechaFactura").toString(), new Integer[]{9});
        DefaultTableModel datos = new DefaultTableModel(dtDatos2, columNames) {
            public boolean isCellEditable(int row, int column) {
                if (column == 9) {
                    return true;
                }
                return false;
            }

            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        return datos;
    }

    public Object[][] getRegistrosFacturas(String condicion) {
        String colName[] = {
            "idFactura", "factura", "fechaFactura", "cliente", "nombre", "vendedor", "totalGeneral", "terminal", "turno", "modeloContable"
        };
        String origen = " facturacionTablas";
        Object dtDatos[][] = getDatos(colName, origen, "select idFactura, factura, fechaFactura, cliente, nombre, vendedor, totalGeneral, terminal, turno, modeloContable "
                + " from " + origen + " where anulada=false " + condicion + " order by ordenId Desc", " where anulada=false " + condicion);

        return dtDatos;
    }

    public Object[][] getRegistrosPlantillas(String condicion) {
        String colName[] = {
            "idFactura", "idFactura", "fechaFactura", "cliente", "nombre", "vendedor", "totalGeneral", "terminal"
        };

        String origen = " plantillas ";
        Object dtDatos[][] = getDatos(colName, origen, "SELECT idFactura, idFactura, fechaFactura, cliente, nombre, vendedor, totalGeneral, "
                + "terminal FROM " + origen + " where anulada=false " + condicion + " GROUP BY idFactura ", " where anulada=false " + condicion);

        return dtDatos;
    }

    public Object[][] getTodosRegistrosFacturas(String condicion) {
        String colName[] = {
            "idFactura", "cliente", "vendedor", "red", "fechaFactura", "fechaVencimiento", "efectivoGeneral", "ncGeneral", "chequeGeneral",
            "targetaGeneral", "totalGeneral", "descuentoGeneral", "ivaGeneral", "subtotalGeneral", "comprobante", "cotizacion", "anulada",
            "anula", "credito", "cxc", "usuario", "rtIva", "rtIca", "rtFuente", "otros", "observacion", "anulada1", "anula1", "credito1", "cxc1",
            "usuario1", "fechaAlerta", "terminal", "estadoGeneral", "estado2", "devuelta", "factura", "resolucion", "fechaAnulacion", "cuadreAnulacion",
            "usuarioAnula", "copago", "placa", "garantia", "diasGarantia", "rango", "terminos", "notaAnulacion", "conseMesa", "producto", "lista",
            "cantidad", "descuento", "total", "iva", "subtotal", "NC", "utilidad", "concepto", "porcDescuento", "descripcion", "plu", "cant2", "estado",
            "porcIva", "tercero", "utilidad1", "preparacion"
        };
        String origen = " bdFactura";
        //Object dtDatos[][] = GetTabla(colName, origen, (new StringBuilder()).append("select id, fechaFactura, cliente, vendedor from ").append(origen).toString());
        Object dtDatos[][] = getDatos(colName, origen, "select idFactura, cliente, vendedor, red, fechaFactura, fechaVencimiento, efectivoGeneral, ncGeneral, chequeGeneral, "
                + "targetaGeneral, totalGeneral, descuentoGeneral, ivaGeneral, subtotalGeneral, comprobante, cotizacion, anulada, anula, credito, cxc, "
                + "usuario, rtIva, rtIca, rtFuente, otros, observacion, anulada1, anula1, credito1, cxc1, usuario1, fechaAlerta, terminal, estadoGeneral, "
                + "estado2, devuelta, factura, resolucion, fechaAnulacion, cuadreAnulacion, usuarioAnula, copago, placa, garantia, diasGarantia, rango, "
                + "terminos, notaAnulacion, conseMesa, producto, lista, cantidad, descuento, total, iva, subtotal, NC, utilidad, concepto, porcDescuento, "
                + "descripcion, plu, cant2, estado, porcIva, tercero, utilidad1, preparacion from " + origen + " where factura = '" + condicion + "'", " where factura = '" + condicion + "'");
        return dtDatos;
    }

    public Object[][] getRegistrosSepares(String condicion) {
        String colName[] = {
            "factura", "idFactura", "fechaFactura", "idCliente", "nombre", "vendedor", "totalGeneral", "terminal"
        };
        String origen = " separacion";
        //Object dtDatos[][] = GetTabla(colName, origen, (new StringBuilder()).append("select id, fechaFactura, cliente, vendedor from ").append(origen).toString());
        Object dtDatos[][] = getDatos(colName, origen, "select factura, idFactura, fechaFactura, idCliente, nombre, vendedor, totalGeneral, terminal  from " + origen + " where anulada=false " + condicion, " where anulada=false " + condicion);
        return dtDatos;
    }

    public Object[][] getRegistrosFacturasTerminales(String condicion) {
        String colName[] = {
            "id", "factura", "fechaFactura", "cliente", "nombre", "vendedor", "total"
        };
        String origen = " cajaOrdenada";
        //Object dtDatos[][] = GetTabla(colName, origen, (new StringBuilder()).append("select id, fechaFactura, cliente, vendedor from ").append(origen).toString());
        Object dtDatos[][] = getDatos(colName, origen, "select id,factura, fechaFactura, cliente,nombre, vendedor, total  from " + origen + " where anulada=false " + condicion, " where anulada=false " + condicion);
        return dtDatos;
    }

    public Object[][] getRegistrosFactura(String terminal, String usuario, String fechaFactura) {
        String colName[] = {
            "idFactura", "efectivoGeneral", "targetaGeneral", "chequeGeneral", "ncGeneral", "totalGeneral", "factura", "cuotaInicial2",
            "terminal", "comprobante", "credito", "tarjetaCredito", "conseMesa", "totalPropina", "sisteCredito"
        };
        String origen = " facturacion";
        //Object dtDatos[][] = getDatos(colName, origen, "select restante, Id FROM bdNc where cliente = '" + cliente + "' AND restante <> 0", " where cliente = '" + cliente + "' AND restante <> 0");
        Object dtDatos[][] = getDatos(colName, origen, "select idFactura, efectivoGeneral, targetaGeneral, chequeGeneral, ncGeneral, totalGeneral, factura, cuotaInicial2, terminal, "
                + "comprobante, credito, tarjetaCredito, conseMesa, totalPropina, sisteCredito from " + origen + " where estadoGeneral ='PENDIENTE' AND anulada = false AND usuario='" + usuario + "' AND " + fechaFactura, " where estadoGeneral ='PENDIENTE' AND anulada = false AND usuario='" + usuario + "' AND " + fechaFactura);

        return dtDatos;
    }

    public Object[][] getRegistrosAbono(String terminal, String usuario, String fechaFactura) {
        String colName[] = {
            "factura", "efectivo", "targeta", "cheque", "nc", "total", "id", "cuotaInicial2", "terminal", "comprobante"
        };
        String origen = " abonos";
        //Object dtDatos[][] = getDatos(colName, origen, "select restante, Id FROM bdNc where cliente = '" + cliente + "' AND restante <> 0", " where cliente = '" + cliente + "' AND restante <> 0");
        Object dtDatos[][] = getDatos(colName, origen, "select factura, efectivo, targeta, cheque, nc, total, id, cuotaInicial2, terminal, comprobante from " + origen + " where estado='PENDIENTE' AND anulada = false AND usuario='" + usuario + "' AND " + fechaFactura, " where estado='PENDIENTE' AND anulada = false AND usuario='" + usuario + "' AND " + fechaFactura);

        return dtDatos;
    }

    public Object[][] getRegistrosNotasCredito(String terminal, String usuario, String fechaFactura) {
        String colName[] = {
            "factura", "efectivo", "targeta", "cheque", "nc", "total", "id", "cuotaInicial2", "terminal", "comprobante", "reembolsar"
        };
        String origen = " notasCredito";
        //Object dtDatos[][] = getDatos(colName, origen, "select restante, Id FROM bdNc where cliente = '" + cliente + "' AND restante <> 0", " where cliente = '" + cliente + "' AND restante <> 0");
        Object dtDatos[][] = getDatos(colName, origen, "select factura, efectivo, targeta, cheque, nc, total, id, cuotaInicial2, terminal, comprobante, reembolsar from " + origen + " where estado='PENDIENTE' AND anulada = false AND usuario='" + usuario + "' AND " + fechaFactura, " where estado='PENDIENTE' AND anulada = false AND usuario='" + usuario + "' AND " + fechaFactura);
        return dtDatos;
    }

    public Object[][] getRegistrosFacturaAdmin() {
        String colName[] = {
            "idFactura", "efectivoGeneral", "targetaGeneral", "chequeGeneral", "ncGeneral", "totalGeneral", "ordenId", "cuotaInicial2", "terminal", "comprobante", "fechaFactura", "usuario"
        };
        String origen = " facturacion";
        //Object dtDatos[][] = getDatos(colName, origen, "select restante, Id FROM bdNc where cliente = '" + cliente + "' AND restante <> 0", " where cliente = '" + cliente + "' AND restante <> 0");
        Object dtDatos[][] = getDatos(colName, origen, "select idFactura, efectivoGeneral, targetaGeneral, chequeGeneral, ncGeneral, totalGeneral, ordenId, cuotaInicial2, terminal, comprobante, fechaFactura, usuario from " + origen + " where estadoGeneral ='PENDIENTE' AND anulada = false ", " where estadoGeneral ='PENDIENTE' AND anulada = false ");

        return dtDatos;
    }

    public Object[][] getItemDomicilio(String prod, String fechaFactura) {
        String colName[] = {
            "total", "idFactura"
        };
        String origen = " bdFactura ";
        //Object dtDatos[][] = getDatos(colName, origen, "select restante, Id FROM bdNc where cliente = '" + cliente + "' AND restante <> 0", " where cliente = '" + cliente + "' AND restante <> 0");
        Object dtDatos[][] = getDatos(colName, origen, "select total, idFactura from " + origen + " where producto = '" + prod + "' AND estadoGeneral ='PENDIENTE' AND anulada = false AND " + fechaFactura,
                " where producto = '" + prod + "' AND estadoGeneral ='PENDIENTE' AND anulada = false AND " + fechaFactura);

        return dtDatos;
    }

    public Object[][] getRegistrosPlanSepare(String terminal, String usuario, String fechaFactura) {
        String colName[] = {
            "factura", "efectivoGeneral", "targetaGeneral", "chequeGeneral", "ncGeneral", "totalGeneral", "factura", "cuotaInicial2", "terminal", "comprobante"
        };
        String origen = " plansepareagrupado ";
        Object dtDatos[][] = getDatos(colName, origen, "select factura, efectivoGeneral, targetaGeneral, chequeGeneral, ncGeneral, totalGeneral, factura, cuotaInicial2, terminal, comprobante from " + origen + " where estado='PENDIENTE' AND anulada = false AND usuario='" + usuario + "' AND " + fechaFactura, " where estado='PENDIENTE' AND anulada = false AND usuario='" + usuario + "' AND " + fechaFactura);
        return dtDatos;
    }

    public Object[][] getRegistrosSepareAdmin() {
        String colName[] = {
            "factura", "efectivoGeneral", "targetaGeneral", "chequeGeneral", "ncGeneral", "totalGeneral", "ordenId", "cuotaInicial2", "terminal", "comprobante"
        };
        String origen = " planSepare";
        Object dtDatos[][] = getDatos(colName, origen, "select idFactura, efectivoGeneral, targetaGeneral, chequeGeneral, ncGeneral, totalGeneral, ordenId, cuotaInicial2, terminal, comprobante from " + origen + " where estado='PENDIENTE' ", " where estado='PENDIENTE' ");
        return dtDatos;
    }

    public Object[][] getRegistrosSepareAdmin(String terminal, String fechaFactura) {
        String colName[] = {
            "idFactura", "efectivoGeneral", "targetaGeneral", "chequeGeneral", "ncGeneral", "totalGeneral", "factura",
            "cuotaInicial2", "terminal", "comprobante"
        };
        String origen = " PlanSepareAgrupado ";
        Object dtDatos[][] = getDatos(colName, origen, "select idFactura, efectivoGeneral, targetaGeneral, chequeGeneral, ncGeneral, totalGeneral,"
                + " factura, cuotaInicial2, terminal, comprobante from " + origen + " where estado='PENDIENTE' AND " + fechaFactura, " where estado='PENDIENTE' AND " + fechaFactura);
        return dtDatos;
    }

    public Object[][] getRegistrosAbonosAdmin() {
        String colName[] = {
            "factura", "efectivo", "targeta", "cheque", "nc", "total", "id", "cuotaInicial2", "terminal", "comprobante"
        };
        String origen = " abonos";
        //Object dtDatos[][] = getDatos(colName, origen, "select restante, Id FROM bdNc where cliente = '" + cliente + "' AND restante <> 0", " where cliente = '" + cliente + "' AND restante <> 0");
        Object dtDatos[][] = getDatos(colName, origen, "select factura, efectivo, targeta, cheque, nc, total, anulada, credito, descuentos, id, terminal, comprobante, cuotaInicial2 from " + origen + " where estado='PENDIENTE' ", " where estado='PENDIENTE' ");

        return dtDatos;
    }

    public Object[][] getRegistrosAbonosAdmin(String terminal) {
        String colName[] = {
            "factura", "efectivo", "targeta", "cheque", "nc", "total", "id", "cuotaInicial2", "terminal", "comprobante"
        };
        String origen = " abonos";
        //Object dtDatos[][] = getDatos(colName, origen, "select restante, Id FROM bdNc where cliente = '" + cliente + "' AND restante <> 0", " where cliente = '" + cliente + "' AND restante <> 0");
        Object dtDatos[][] = getDatos(colName, origen, "select factura, efectivo, targeta, cheque, nc, total, anulada, credito, descuentos, id, terminal, comprobante, cuotaInicial2 from " + origen + " where estado='PENDIENTE' ", " where estado='PENDIENTE' ");

        return dtDatos;
    }

    public Object[][] getRegistrosNotasCreditoAdmin() {
        String colName[] = {
            "factura", "efectivo", "targeta", "cheque", "nc", "total", "id", "cuotaInicial2", "terminal", "comprobante", "reembolsar"
        };
        String origen = " notasCredito";
        //Object dtDatos[][] = getDatos(colName, origen, "select restante, Id FROM bdNc where cliente = '" + cliente + "' AND restante <> 0", " where cliente = '" + cliente + "' AND restante <> 0");
        Object dtDatos[][] = getDatos(colName, origen, "select factura, efectivo, targeta, cheque, nc, total, anulada, credito, descuentos, id, terminal, comprobante, cuotaInicial2 from " + origen + " where estado='PENDIENTE' ", " where estado='PENDIENTE' ");

        return dtDatos;
    }

    public Object[][] getRegistrosFacturaAdmin(String terminal, String fechaFactura) {

        String colName[] = {
            "factura", "efectivoGeneral", "targetaGeneral", "chequeGeneral", "ncGeneral", "totalGeneral", "factura", "cuotaInicial2",
            "terminal", "comprobante", "credito", "tarjetaCredito", "conseMesa", "totalPropina", "sisteCredito"
        };

        String origen = " facturacion";
        //Object dtDatos[][] = getDatos(colName, origen, "select restante, Id FROM bdNc where cliente = '" + cliente + "' AND restante <> 0", " where cliente = '" + cliente + "' AND restante <> 0");
        Object dtDatos[][] = getDatos(colName, origen, "select factura, efectivoGeneral, targetaGeneral, chequeGeneral, ncGeneral, totalGeneral, "
                + "factura, cuotaInicial2, terminal, comprobante, credito, tarjetaCredito, conseMesa, totalPropina, sisteCredito "
                + "from " + origen + " where estadoGeneral = 'PENDIENTE' AND " + fechaFactura + " AND anulada = false ", " where estadoGeneral ='PENDIENTE' AND " + fechaFactura + " AND anulada = false ");
        return dtDatos;
    }

    public Object[][] getRegistrosAbonoAdmin(String terminal, String fechaFactura) {
        String colName[] = {
            "factura", "efectivo", "targeta", "cheque", "nc", "total", "factura", "cuotaInicial2", "terminal", "comprobante"
        };
        String origen = " abonos";
        //Object dtDatos[][] = getDatos(colName, origen, "select restante, Id FROM bdNc where cliente = '" + cliente + "' AND restante <> 0", " where cliente = '" + cliente + "' AND restante <> 0");
        Object dtDatos[][] = getDatos(colName, origen, "select factura, efectivo, targeta, cheque, nc, total, anulada, credito, descuentos,factura,terminal,comprobante, cuotaInicial2 from " + origen + " where estado='PENDIENTE' AND anulada = false AND " + fechaFactura, " where estado='PENDIENTE' AND anulada = false AND " + fechaFactura);

        return dtDatos;
    }

    public Object[][] getRegistrosNotasCreditoAdmin(String terminal, String fechaFactura) {
        String colName[] = {
            "id", "efectivo", "targeta", "cheque", "nc", "total", "factura", "cuotaInicial2", "terminal", "comprobante", "reembolsar"
        };
        String origen = " notasCredito";
        //Object dtDatos[][] = getDatos(colName, origen, "select restante, Id FROM bdNc where cliente = '" + cliente + "' AND restante <> 0", " where cliente = '" + cliente + "' AND restante <> 0");
        Object dtDatos[][] = getDatos(colName, origen, "select id, efectivo, targeta, cheque, nc, total, factura, cuotaInicial2, terminal, comprobante, reembolsar  from " + origen + " where estado='PENDIENTE' AND anulada = false AND " + fechaFactura, " where estado='PENDIENTE' AND anulada = false AND " + fechaFactura);

        return dtDatos;
    }

    public String getReciboPTM(String fact) {
        String colName[] = {
            "recibo"};
        String origen = " bdRecibos";
        //Object dtDatos[][] = getDatos(colName, origen, "select restante, Id FROM bdNc where cliente = '" + cliente + "' AND restante <> 0", " where cliente = '" + cliente + "' AND restante <> 0");
        Object dtDatos[][] = getDatos(colName, origen, "select recibo from " + origen + " where factura = '" + fact + "'; ", " where factura = '" + fact + "' ");
        return dtDatos[0][0].toString();
    }

    public String gastosEgresosAdmin(String ini) {
        String colName[] = {
            "SumaDetotal"};
        String origen = " gastosEgresosAdmin";
        //Object dtDatos[][] = getDatos(colName, origen, "select restante, Id FROM bdNc where cliente = '" + cliente + "' AND restante <> 0", " where cliente = '" + cliente + "' AND restante <> 0");
        Object dtDatos[][] = getDatos(colName, origen, "select SumaDetotal from " + origen + " where " + ini + " ", " where " + ini + "");

        return dtDatos[0][0].toString();
    }

    public String montoBanco(String num) {
        String colName[] = {"monto"};
        String origen = " bdBancos";
        //Object dtDatos[][] = getDatos(colName, origen, "select restante, Id FROM bdNc where cliente = '" + cliente + "' AND restante <> 0", " where cliente = '" + cliente + "' AND restante <> 0");
        Object dtDatos[][] = getDatos(colName, origen, "select monto from " + origen + " where numero = '" + num + "' ", " where numero = '" + num + "'");

        return dtDatos[0][0].toString();
    }

    public String gastosEgresosContAdmin(String ini) {
        String colName[] = {
            "contGastos"};
        String origen = " gastosEgresosAdmin";
        //Object dtDatos[][] = getDatos(colName, origen, "select restante, Id FROM bdNc where cliente = '" + cliente + "' AND restante <> 0", " where cliente = '" + cliente + "' AND restante <> 0");
        Object dtDatos[][] = getDatos(colName, origen, "select contGastos from " + origen + " where " + ini + " ", " where " + ini + "");

        return dtDatos[0][0].toString();
    }

    public Object[][] getRegistrosCajaPendiente() {
        String colName[] = {
            "usuario", "terminal", "fecha", "tot"
        };
        String origen = " conCajaPendiente";
        //Object dtDatos[][] = getDatos(colName, origen, "select restante, Id FROM bdNc where cliente = '" + cliente + "' AND restante <> 0", " where cliente = '" + cliente + "' AND restante <> 0");

        Object dtDatos[][] = getDatos(colName, origen, "select usuario, terminal, fecha, tot from " + origen + " ", " ");

        return dtDatos;
    }

    public Object[][] getRegistrosCaja() {
        String colName[] = {
            "id", "efectivo", "targeta", "cheque", "nc", "total", "anulada", "credito", "descuentos"
        };
        String origen = " conCaja";
        //Object dtDatos[][] = getDatos(colName, origen, "select restante, Id FROM bdNc where cliente = '" + cliente + "' AND restante <> 0", " where cliente = '" + cliente + "' AND restante <> 0");

        Object dtDatos[][] = getDatos(colName, origen, "select id, efectivo, targeta, cheque, nc, total, anulada, credito, descuentos from " + origen + " ", " ");

        return dtDatos;
    }

    public String gastosEgresos(String ini, String usuario) {
        String colName[] = {
            "SumaDetotal"};
        String origen = " gastosEgresos";
        //Object dtDatos[][] = getDatos(colName, origen, "select restante, Id FROM bdNc where cliente = '" + cliente + "' AND restante <> 0", " where cliente = '" + cliente + "' AND restante <> 0");
        Object dtDatos[][] = getDatos(colName, origen, "select SumaDetotal from " + origen + " where " + ini + " AND usuario='" + usuario + "'", " where " + ini + " AND usuario='" + usuario + "'");

        return dtDatos[0][0].toString();
    }

    public String gastosEgresosCont(String ini, String usuario) {
        String colName[] = {
            "contGastos"};
        String origen = " gastosEgresos";
        //Object dtDatos[][] = getDatos(colName, origen, "select restante, Id FROM bdNc where cliente = '" + cliente + "' AND restante <> 0", " where cliente = '" + cliente + "' AND restante <> 0");
        Object dtDatos[][] = getDatos(colName, origen, "select contGastos from " + origen + " where " + ini + " AND usuario='" + usuario + "'", " where " + ini + " AND usuario='" + usuario + "'");

        return dtDatos[0][0].toString();
    }

    public DefaultTableModel getPendientesCuadre(String factura) {
        String columNames[] = {
            "Documento", "Fecha", "Efectivo", "Tarjeta", "Cheque", "NC"
        };
        String colName[] = {
            "id", "fechaFactura", "efectivo", "targeta", "cheque", "nc"
        };
        String origen = " bdCaja";
        Object dtDatos[][] = getDatos(colName, origen, "select id, fechaFactura, efectivo, targeta, cheque, nc from bdCaja where (id = '" + factura + "')"
                + " AND (id NOT LIKE 'COTI-*')", " where id = '" + factura + "') AND (id NOT LIKE 'COTI-*')");
        DefaultTableModel datos = new DefaultTableModel(dtDatos, columNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            public boolean isCellVisible(int row, int column) {
                if (column == 0) {
                    return false;
                } else {
                    return true;
                }
            }
        };
        return datos;
    }

    public Object[][] getRegistrosTrasladosBuscador(String cliente) {
        String colName[] = {
            "Id", "fecha", "usuario"
        };
        String origen = "bdTrasladosBodegas";
        Object dtDatos[][] = getDatos(colName, origen, "select Id, fecha, usuario from " + origen + " where destino LIKE  '" + cliente + "%'", " where destino LIKE '" + cliente + "%'");

        return dtDatos;
    }

    public Object[][] getRegistrosOServicioBuscador(String cliente) {
        String colName[] = {
            "id", "fechaFactura", "usuario"
        };
        String origen = "bdCaja";
        Object dtDatos[][] = getDatos(colName, origen, "select id, fechaFactura, usuario from " + origen + " where cliente = '" + cliente + "' AND id LIKE 'OSERV-%'", " where cliente = '" + cliente + "' AND id LIKE 'OSERV-%'");

        return dtDatos;
    }

    public Object[][] getRegistrosPedidosBuscador(String cliente) {
        String colName[] = {
            "id", "fechaFactura", "usuario"
        };
        String origen = "bdCaja";
        Object dtDatos[][] = getDatos(colName, origen, "select id, fechaFactura, usuario from " + origen + " where cliente = '" + cliente + "' AND id LIKE 'PEDIDO-%'", " where cliente = '" + cliente + "' AND id LIKE 'PEDIDO-%'");

        return dtDatos;
    }

    public Object[][] getRegistrosOServicioBuscadorPlaca(String cliente) {
        String colName[] = {
            "idFactura", "fechaFactura", "placa", "usuario"
        };
        String origen = "buscadorOServicios";
        Object dtDatos[][] = getDatos(colName, origen, "select idFactura, fechaFactura, placa, usuario from " + origen + " where placa LIKE '" + cliente + "%' ", " where placa LIKE '" + cliente + "%' ");

        return dtDatos;
    }

    //CONGELADAS   
    public Object[][] getProductosVenta(String factura) {
        String colName[] = {
            "producto", "cantidad", "descripcion", "plu", "cant2", "lista", "descuento", "preparacion", "conseMesa", "notaAnulacion", "rango",
            "porcDescuento", "imei", "idProd", "impoconsumo", "porcImpo"};
        String origen = " bdCongelada";
        Object dtDatos[][] = getDatos(colName, origen, "select producto, cantidad, descripcion, plu, cant2, lista, descuento, preparacion, "
                + "conseMesa, notaAnulacion, rango, porcDescuento, imei, idProd, impoconsumo, porcImpo from bdCongelada where idFactura = '" + factura + "'", " where idFactura = '" + factura + "'");

        return dtDatos;
    }

//    public Object[][] getProductosPreventaGeneral() {
//        String colName[] = {
//            "producto", "cantidad", "Descripcion", "plu", "cant2", "lista", "descuento"};
//        String origen = " conPreventa";
//        Object dtDatos[][] = getDatos(colName, origen, "select producto,cantidad,Descripcion,plu,cant2,lista,descuento from conPreventa ", " ");
//
//        return dtDatos;
//    }

    public Object[][] getTodasBodegas() {
        String colName[] = {
            "codigo", "abreviado"
        };
        String origen = "bdBodegas";
        Object dtDatos[][] = getDatos(colName, origen, "select codigo, abreviado from " + origen + " where tipo = 'INTERNA' ", " where tipo = 'INTERNA' ");

        return dtDatos;
    }

    public Object[][] getProductosPrecompra(String ingreso, String usuario) {

        String sql = "";
        if (!usuario.equals("")) {
            sql = " where ingreso = '" + ingreso + "' and usuario = '" + usuario + "'";
        } else {
            sql = " where ingreso = '" + ingreso + "'";
        }

        String colName[] = {
            "producto", "cantidad", "descripcion", "plu", "cant2", "valor", "descuento", "subtotal", "bodega", "tipoDescuento", "total"};
        String instruccion_sql = "select producto, cantidad, descripcion, plu, cant2, valor, descuento, subtotal, bodega, tipoDescuento, total from bdPreCompra " + sql;
        Object dtDatos[][] = getDatos(colName, " bdPreCompra ", instruccion_sql, sql);
        return dtDatos;
    }

    public boolean eliminarPonderado(String id) {
        boolean ok = false;
        ok = eliminar_registro(" bdUltimoPonderado ", " producto ='" + id + "'");
        return ok;
    }

    public boolean eliminarPrecompraDetalle(String id) {
        boolean ok = false;
        ok = eliminar_registro(" bdPreCompraDetalle ", "ingreso='" + id + "'");
        return ok;
    }

    public boolean eliminarPrecompra(String id, String usuario) {
        boolean ok = false;

        String sql = "";
        if (!usuario.equals("")) {
            sql = "ingreso = '" + id + "' and usuario = '" + usuario + "'";
        } else {
            sql = "ingreso = '" + id + "'";
        }

        ok = eliminar_registro(" bdPreCompra ", sql);
        return ok;
    }

    public boolean agregarPrecompra(ndCompra nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdPreCompra(ingreso, producto, cantidad, porIva,descripcion,plu, cant2, bodega, usuario, tipoDescuento, "
                + "valor, descuento, total, iva, subtotal, valorPlu, impoconsumo, porcImpo)"
                + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosCompra(nodo), metodos.pasarValoresCompra(nodo), instruccion_sql);
        return ok;
    }

    public boolean agregarReciboPTM(String recibo, String factura) {
        boolean ok = false;
        String instruccion_sql = "insert into bdRecibos (factura, recibo)"
                + " values(?,?);";
        ok = Agregar_Registro(new Object[]{factura, recibo}, null, instruccion_sql);
        return ok;
    }

    public boolean agregarMsgPermiso(String idPermiso, String mensaje, String usuario, String fecha, String hora) {
        boolean ok = false;
        String instruccion_sql = "insert into bdMsgPermisos (IdPermiso, mensaje, usuario, fecha, hora)"
                + " values(?,?,?,?,?);";
        ok = Agregar_Registro(new Object[]{idPermiso, mensaje, usuario, fecha, hora}, null, instruccion_sql);
        return ok;
    }

    public boolean agregarTrasladoDetal(String traslado, String codigo, String descripcion, String cant, String valor, String usuario, String terminal, String fecha, String hora) {
        boolean ok = false;
        String instruccion_sql = "insert into bdTrasladoDetal (traslado, codigo, descripcion, cant, valor, usuario, terminal, fecha, hora)"
                + " values(?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(new Object[]{traslado, codigo, descripcion, cant, valor, usuario, terminal, fecha, hora}, null, instruccion_sql);
        return ok;
    }

    public boolean agregarImpresionFactura(String idFactura, String factura2, BigDecimal valor, String impresora, String titulo, String obv, String tipo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdImpresionesSistema (idFactura, factura2, impresora, titulo, observaciones, tipo, valor)"
                + " values(?,?,?,?,?,?,?);";
        ok = Agregar_Registro(new Object[]{idFactura, factura2, impresora, titulo, obv, tipo}, new Object[]{valor}, instruccion_sql);
        return ok;
    }

    public boolean agregarTrasladoDetalProd(String traslado, String codigo, String cant, String valor, String unidades) {
        boolean ok = false;
        String instruccion_sql = "insert into bdTrasladoDetalProd (traslado, codigo, cant, valor, unidades)"
                + " values(?,?,?,?,?);";
        ok = Agregar_Registro(new Object[]{traslado, codigo, cant, valor, unidades}, null, instruccion_sql);
        return ok;
    }

    public boolean agregarVitrina(String id, String nombre, String capacidad) {
        boolean ok = false;
        String instruccion_sql = "insert into bdVitrina(Id, nombre, capacidad, estado)"
                + " values(?,?,?,?);";
        ok = Agregar_Registro(new Object[]{id, nombre, capacidad, true}, null, instruccion_sql);
        return ok;
    }

    public boolean agregarDetalleVitrina(String idVitrina, String idProd, String codigo, String descripcion, String estado, String usuario) {
        boolean ok = false;
        String instruccion_sql = "insert into bdDetalleVitrinas(idVitrina, idProd, codigo, descripcion, estado, usuario)"
                + " values(?,?,?,?,?,?);";
        ok = Agregar_Registro(new Object[]{idVitrina, idProd, codigo, descripcion, estado, usuario}, null, instruccion_sql);
        return ok;
    }

public boolean modificarCodEgreso(Object[] datos) {
        boolean ok = false;
        String instruccion_sql = "update codsEgresos set concepto=?, codigoUsuario=? where codigo=" + datos[0] + ";";
        ok = Actualizar_Registro(datos, null, instruccion_sql);
        return ok;
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

    public Object[][] getHistorialPermisos(String idPermiso) {
        String colName[] = {"mensaje", "usuario"};
        String instruccion_sql = "select mensaje, usuario from bdMsgPermisos where IdPermiso = '" + idPermiso + "' ";
        Object dtDatos[][] = getDatos(colName, " bdMsgPermisos ", instruccion_sql, " where IdPermiso = '" + idPermiso + "' ");
        return dtDatos;
    }

    public Object[][] getServiciosPTM1() {
        String colName[] = {"codigo"};
        String instruccion_sql = "select codigo from bdServiciosPTM1 ";
        Object dtDatos[][] = getDatos(colName, " bdServiciosPTM1 ", instruccion_sql, "");

        return dtDatos;
    }

    public Object[][] obtenerProductosDetallePorProducto(String cod) {
        String colName[] = {"producto", "descripcion", "cantidadDisponible", "imei", "lote", "fechaVencimiento", "temperatura", "color", "Id", "talla"};
        String instruccion_sql = "select producto, descripcion, cantidadDisponible, imei, lote, fechaVencimiento, temperatura, color, Id, talla "
                + "from bdDetalleProductos where producto = '" + cod + "' and estado = 'DISPONIBLE' ";
        Object dtDatos[][] = getDatos(colName, " bdDetalleProductos ", instruccion_sql, " where producto = '" + cod + "' and estado = 'DISPONIBLE' ");

        return dtDatos;
    }

    public Object[][] getProductosDetalle(String cod) {
        String colName[] = {"producto", "descripcion", "cantidad", "imei", "lote", "fechaVencimiento", "temperatura", "color", "Id", "talla"};
        String instruccion_sql = "select producto, descripcion, cantidadDisponible, imei, lote, fechaVencimiento, temperatura, color, Id, talla "
                + "from bdDetalleProductos where Id = " + cod + " ";
        Object dtDatos[][] = getDatos(colName, " bdDetalleProductos ", instruccion_sql, " where Id = " + cod + " ");

        return dtDatos;
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

    /* OFTAMOLOGIA */
    public ndHojaIngreso getDatosHojaIngreso(String Id) {
        //LISTO
        boolean ok = false;
        String instruccion_sql = " select Id, historia, fechaIngreso, identificacion, primerApellido, segundoApellido, "
                + "primerNombre, segundoNombre, fechaNacimiento, edad, sexo, ocupacion, estadoCivil, departamento, municipio, zona, direccion,"
                + "telefono, responsable, telefono1, parentesco, enfermedades, otrasEnfermedades, lentes, enfermedades1, lentes1, alergias, "
                + "otrasEnfermedadesOculares, otrasEnfermedadesGenerales, refraccionOD, refraccionOI, clOD, clOI, refSubjetivoOD, refSubjetivoOI, avOD, avOI,"
                + "forias, pupilas, mc, tonometriaOD, tonometriaOI, mmhgOD, mmhgOI, biomicroscopia, fondoDeOjo, cambioProcedimiento, cie, cup, orden, servicio, "
                + "cie1, cie2, cie3, cie4, cup1, cup2, cup3, cup4, hora, conducta, anexos "
                + "from bdConsultaOftalmologia where Id = '" + Id + "'; ";

        String[] colName = {"Id", "historia", "fechaIngreso", "identificacion", "primerApellido", "segundoApellido", "primerNombre", "segundoNombre", "fechaNacimiento",
            "edad", "sexo", "ocupacion", "estadoCivil", "departamento", "municipio", "zona", "direccion", "telefono", "responsable", "telefono1", "parentesco",
            "enfermedades", "otrasEnfermedades", "lentes", "enfermedades1", "lentes1", "alergias", "otrasEnfermedadesOculares", "otrasEnfermedadesGenerales",
            "refraccionOD", "refraccionOI", "clOD", "clOI", "refSubjetivoOD", "refSubjetivoOI", "avOD", "avOI", "forias", "pupilas", "mc", "tonometriaOD",
            "tonometriaOI", "mmhgOD", "mmhgOI", "biomicroscopia", "fondoDeOjo", "cambioProcedimiento", "cie", "cup", "orden",
            "servicio", "cie1", "cie2", "cie3", "cie4", "cup1", "cup2", "cup3", "cup4", "hora", "conducta", "anexos"
        };

        boolean[] cadena = {
            true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,
            true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,
            true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,
            true, true, true, true, true, true, true, true};
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);

        ndHojaIngreso nodo = metodos.llenarHojaIngreso(data);
        return nodo;
    }

    public boolean modificarHojaIngreso(ndHojaIngreso nodo) {
        boolean ok = false;
        String instruccion_sql = "update bdConsultaOftalmologia set historia=?, fechaIngreso=?, identificacion=?, primerApellido=?, segundoApellido=?, "
                + "primerNombre=?, segundoNombre=?, fechaNacimiento=?, edad=?, sexo=?, ocupacion=?, estadoCivil=?, departamento=?, municipio=?, zona=?, direccion=?,"
                + "telefono=?, responsable=?, telefono1=?, parentesco=?, enfermedades=?, otrasEnfermedades=?, lentes=?, enfermedades1=?, lentes1=?, alergias=?, "
                + "otrasEnfermedadesOculares=?, otrasEnfermedadesGenerales=?, refraccionOD=?, refraccionOI=?, clOD=?, clOI=?, refSubjetivoOD=?, refSubjetivoOI=?, avOD=?, avOI=?,"
                + "forias=?, pupilas=?, mc=?, tonometriaOD=?, tonometriaOI=?, mmhgOD=?, mmhgOI=?, biomicroscopia=?, "
                + "fondoDeOjo=?, cambioProcedimiento=?, cie=?, cup=?, orden=?, servicio=?, cie1=?, cie2=?, cie3=?, cie4=?, cup1=?, cup2=?, cup3=?, cup4=?, hora=?, conducta=?, anexos=? "
                + "where id='" + nodo.getId() + "' ;";
        ok = Actualizar_Registro(metodos.pasarDatosHojaIngreso(nodo), null, instruccion_sql);
        return ok;
    }

    public Object[][] getConsultasClinica1(String historia) {
        String colName[] = {
            "Id", "fechaIngreso"
        };
        String origen = " bdConsultaOftalmologia";
        Object dtDatos[][] = getDatos(colName, origen, "select Id, fechaIngreso FROM bdConsultaOftalmologia where Identificacion = '" + historia + "'", " where Identificacion = '" + historia + "'");

        return dtDatos;
    }

    public ndSeguimientoClinicaOf getDatosSeguimientoClinicaOf(String id) {
        String instruccion_sql = "select Id, fecha, usuario, evolucion, fotos, consulta, examen from bdSeguimientoClinica where Id = " + id + "; ";
        //columnas de la tabla que se
        String[] colName = {"Id", "fecha", "usuario", "evolucion", "fotos", "consulta", "examen"};
        boolean[] cadena = {true, true, true, true, true, true, true};
        //se realiza la consulta
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);

        ndSeguimientoClinicaOf nodo = metodos.llenarSeguimientoClinicaOf(data);

        return nodo;
    }

    public boolean agregarHojaIngreso(ndHojaIngreso nodo) {
        //LISTO
        boolean ok = false;
        String instruccion_sql = "insert into bdConsultaOftalmologia(id, historia, fechaIngreso, identificacion, primerApellido, segundoApellido, "
                + "primerNombre, segundoNombre, fechaNacimiento, edad, sexo, ocupacion, estadoCivil, departamento, municipio, zona, direccion,"
                + "telefono, responsable, telefono1, parentesco, enfermedades, otrasEnfermedades, lentes, enfermedades1, lentes1, alergias, "
                + "otrasEnfermedadesOculares, otrasEnfermedadesGenerales, refraccionOD, refraccionOI, clOD, clOI, refSubjetivoOD, refSubjetivoOI, avOD, avOI,"
                + "forias, pupilas, mc, tonometriaOD, tonometriaOI, mmhgOD, mmhgOI, biomicroscopia, fondoDeOjo, cambioProcedimiento, cie, cup, orden, servicio, "
                + "cie1, cie2, cie3, cie4, cup1, cup2, cup3, cup4, hora, conducta, anexos) "
                + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosHojaIngreso(nodo), null, instruccion_sql);
        return ok;
    }

    public String getNombreEmpleadoMedico(String usuario) {
        String sql = "select nombre, usuarioAsociado FROM bdTerceros where usuarioAsociado = '" + usuario + "'";
        String colName[] = {"nombre", "usuarioAsociado"};
        boolean[] cadena = {true, true};
        Object[] data = GetRegistro(colName, cadena, sql);
        try {
            if (data[0] == null) {
                return "";
            }
            if (data[0].toString().equals("null")) {
                return "";
            }
        } catch (Exception e) {
            return "";
        }
        return data[0].toString();
    }

    public ndPaquimetria getDatosPaquimetria(String paquimetria) {
        boolean ok = false;
        String instruccion_sql = " select Id, fecha, cedula, nombre, doctor, ojoIzquierdo, zonaOpticaIzq, ojoDerecho, zonaOpticaDer, dibujo from bdPaquimetria where Id = '" + paquimetria + "'; ";
        String[] colName = {"Id", "fecha", "cedula", "nombre", "doctor", "ojoIzquierdo", "zonaOpticaIzq", "ojoDerecho", "zonaOpticaDer", "dibujo"};
        boolean[] cadena = {true, true, true, true, true, true, true, true, true, true};
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        ndPaquimetria nodo = metodos.llenarPaquimetria(data);
        return nodo;
    }

    public boolean agregarPaquimetria(ndPaquimetria nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdPaquimetria(id, fecha, cedula, nombre, doctor, ojoIzquierdo, zonaOpticaIzq, ojoDerecho, zonaOpticaDer, dibujo) "
                + "values (?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosPaquimetria(nodo), null, instruccion_sql);
        return ok;
    }

    public ndBiometria getDatosBiometria(String biometria) {
        boolean ok = false;
        String instruccion_sql = " select Id, fecha, cedula, nombre, h, v, h1, v1, longitudAxial, od, oi, h2, v2, h3, v3, "
                + "longitudAxial1, od1, oi1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22"
                + "23, 24, 25, 26, 27, 28, 29, 30 from bdBiometria where Id = '" + biometria + "'; ";
        String[] colName = {"id", "fecha", "cedula", "nombre", "h", "v", "h1", "v1", "longitudAxial", "od", "oi", "h2", "v2", "h3", "v3",
            "longitudAxial1", "od1", "oi1", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15",
            "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"};
        boolean[] cadena = {true, true, true, true, true, true, true, true, true, true, true, true, true, true,
            true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,
            true, true, true, true, true, true, true, true, true, true, true, true, true};
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        ndBiometria nodo = metodos.llenarBiometria(data);
        return nodo;
    }

    public boolean agregarFormulaLentes(ndFormulaLentes nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdFormulaLentes(Id, fecha, paciente, od, oi, add, bifocal) values (?,?,?,?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosFormulaLentes(nodo), null, instruccion_sql);
        return ok;
    }

    public boolean agregarBiometria(ndBiometria nodo) {
        boolean ok = false;
        String instruccion_sql = "insert into bdBiometria(Id, fecha, cedula, nombre, h, v, h1, v1, "
                + "longitudAxial, od, oi, h2, v2, h3, v3, longitudAxial1, od1, oi1, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, "
                + "t13, t14, t15, t16, t17 ,t18, t19, t20, t21, t22, t23, t24, t25, t26, t27, t28, t29, t30)"
                + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        ok = Agregar_Registro(metodos.pasarDatosBiometria(nodo), null, instruccion_sql);
        return ok;
    }

    public ndMedicamentos getDatosMedicamentos1(String Id) {
        boolean ok = false;
        String instruccion_sql = " select Id, nombre, recomendacion, usuario from bdMedicamentos where Id = " + Id + "; ";
        String[] colName = {"Id", "nombre", "recomendacion", "usuario"};
        boolean[] cadena = {true, true, true, true};
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        ndMedicamentos nodo = metodos.llenarMedicamentos(data);
        return nodo;
    }

    public ndFormulaLentes getDatosFormulaLentes(String Id) {
        boolean ok = false;
        String instruccion_sql = " select Id, fecha, paciente, od, oi, add, bifocal from bdFormulaLentes where Id = '" + Id + "'; ";
        String[] colName = {"Id", "fecha", "paciente", "od", "oi", "add", "bifocal"};
        boolean[] cadena = {true, true, true, true, true, true, true};
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        ndFormulaLentes nodo = metodos.llenarFormulaLentes(data);
        return nodo;
    }

    public String getDatosMedicamentos2(String cod) {
        String instruccion_sql = "select Id, nombre, recomendacion, usuario "
                + " from bdMedicamentos where nombre = '" + cod + "'; ";
        //columnas de la tabla que se
        String[] colName = {"Id", "nombre", "recomendacion", "usuario"};
        boolean[] cadena = {true, true, true, true};
        try {
            return GetRegistro(colName, cadena, instruccion_sql)[0].toString();
        } catch (Exception e) {
            return null;
        }
    }

    public ndHistoriaClinica getDatosHistClinica1(String id) {
        boolean ok = false;
        String instruccion_sql = " select id, paciente, fecha, historia, menstruacion, citologia, resultadoCitologia, embarazos, partos, abortos, cesareas,consecutivo,hijosVivos,personal,familiar from bdHistoriaClinica where id = '" + id + "'; ";
        String[] colName = {"id", "paciente", "fecha", "historia", "menstruacion", "citologia", "resultadoCitologia", "embarazos", "partos", "abortos", "cesareas", "consecutivo", "hijosVivos", "personal", "familiar"};
        boolean[] cadena = {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true};
        Object[] data = GetRegistro(colName, cadena, instruccion_sql);
        ndHistoriaClinica nodo = metodos.llenarHistClinica(data);
        return nodo;
    }

    public boolean agregarMedicamento(String nombre) {
        boolean ok = false;
        String instruccion_sql = "insert into bdMedicamentos(nombre) values (?);";
        ok = Agregar_Registro(new Object[]{nombre}, null, instruccion_sql);
        return ok;
    }

    public Object[][] getRegistrosExamens3(String historia, String bd) {
        String colName[] = {
            "Id", "fecha", "usuario"
        };
        System.out.println("select Id, fecha, usuario from " + bd + " where historia = '" + historia + "'");
        Object dtDatos[][] = getDatos(colName, bd, "select Id, fecha, usuario from " + bd + " where historia = '" + historia + "'", " where historia = '" + historia + "'");

        return dtDatos;
    }

    public Object[][] getRegistrosAyuda(String historia, String bd) {
        String colName[] = {
            "diagnostico", "fecha", "concepto"
        };
        Object dtDatos[][] = getDatos(colName, bd, "select diagnostico, fecha, concepto from " + bd + " where Historial = '" + historia + "'", " where Historial = '" + historia + "'");

        return dtDatos;
    }

    public Object[][] getRegistrosFormulaLentes(String historia, String bd) {
        String colName[] = {
            "Id", "fecha", "nombre"
        };
        Object dtDatos[][] = getDatos(colName, bd, "select Id, fecha, nombre from " + bd + " where paciente = '" + historia + "'", " where paciente = '" + historia + "'");

        return dtDatos;
    }

    public Object[][] getRegistrosIncapacidad(String historia, String bd) {
        String colName[] = {
            "Id", "fechainicio", "fechavencimiento", "descripcion"
        };
        System.out.println("select Id, fechainicio, fechavencimiento, descripcion from " + bd + " where historial = '" + historia + "'");
        Object dtDatos[][] = getDatos(colName, bd, "select Id, fechainicio, fechavencimiento, descripcion from " + bd + " where historial = '" + historia + "'", " where historial = '" + historia + "'");

        return dtDatos;
    }

    public Object[][] getRegistrosBiometria(String historia, String bd) {
        String colName[] = {
            "Id", "fecha", "cedula"
        };
        Object dtDatos[][] = getDatos(colName, bd, "select Id, fecha, cedula from " + bd + " where cedula = '" + historia + "'", " where cedula = '" + historia + "'");

        return dtDatos;
    }

    public Object[][] getRegistrosPaquimetria(String historia, String bd) {
        String colName[] = {
            "Id", "fecha", "doctor"
        };
        Object dtDatos[][] = getDatos(colName, bd, "select Id, fecha, doctor from " + bd + " where cedula = '" + historia + "'", " where cedula = '" + historia + "'");

        return dtDatos;
    }
}
