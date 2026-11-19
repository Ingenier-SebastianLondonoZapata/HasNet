package Procesos.Multiempresa;

import Dao.Multiempresa.DaoEmpresas;
import Modelo.Multiempresa.Empresa;
import Modelo.Multiempresa.OpcionesCopiaEmpresa;
import Utilidades.BaseDatos.MySql_connection;
import Utilidades.Constantes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreadorEmpresa {

    private static final Logger LOGGER = Logger.getLogger(CreadorEmpresa.class.getName());

    private static final String PREFIJO_BASE_DATOS = "bdhasnet_";
    private static final String BODEGA_POR_DEFECTO = "BODEGA-1";
    private static final String TERCERO_POR_DEFECTO = "TERC-00000001";

    private static final List<String> CATALOGOS = Arrays.asList(
            "bdcies", "bdmedicamentos", "bdregiones", "bdrazas", "bdvacunas", "bdepsprecargados", "bdconsecutivoseps",
            "bdmedida", "bdtipovehiculo", "bdmonedas", "bdcargo", "bdimpresoras", "bdcamposordenpredeterminada",
            "bdmaestra", "bdmaestraimpresiones", "bdmaestraparqueadero", "bdconsecutivos");

    private static final List<String> CATALOGOS_PRODUCTOS = Arrays.asList("bdmarca", "bdtalla", "bdcolores", "bdtipoproducto");

    private final DaoEmpresas daoEmpresas = new DaoEmpresas();

    public Empresa crear(Empresa datos, OpcionesCopiaEmpresa opciones, String usuario, Consumer<String> progreso) throws SQLException {
        String principal = Constantes.BASE_DATOS_PRINCIPAL;
        String origenDatos = MySql_connection.getBaseDatosActual();

        Empresa empresa = new Empresa();
        empresa.setId(daoEmpresas.siguienteId());
        empresa.setNombre(datos.getNombre().trim().toUpperCase(Locale.ROOT));
        empresa.setNit(datos.getNit().trim());
        empresa.setTipoIdentificacion(datos.getTipoIdentificacion());
        empresa.setBaseDatos(PREFIJO_BASE_DATOS + empresa.getId().toLowerCase(Locale.ROOT));
        String destino = empresa.getBaseDatos();

        boolean baseCreada = false;
        try (Connection conexion = MySql_connection.crearConexionPrincipal()) {
            if (EsquemaEmpresa.existeBaseDatos(conexion, destino)) {
                throw new SQLException("Ya existe una base de datos con el nombre " + destino);
            }

            informar(progreso, "Creando la base de datos");
            try (Statement sentencia = conexion.createStatement()) {
                sentencia.execute("CREATE DATABASE " + EsquemaEmpresa.id(destino) + " DEFAULT CHARACTER SET " + EsquemaEmpresa.juegoCaracteres(conexion, principal));
            }
            baseCreada = true;

            informar(progreso, "Creando las tablas");
            for (String tabla : EsquemaEmpresa.tablas(conexion, principal)) {
                EsquemaEmpresa.crearTabla(conexion, principal, destino, tabla);
            }

            informar(progreso, "Creando las vistas");
            EsquemaEmpresa.crearVistas(conexion, principal, destino, EsquemaEmpresa.vistas(conexion, principal));
            ActualizadorEsquema.heredarMigraciones(conexion, destino);

            informar(progreso, "Copiando la configuración base");
            for (String tabla : CATALOGOS) {
                EsquemaEmpresa.copiarDatos(conexion, principal, destino, tabla, null);
            }
            EsquemaEmpresa.copiarDatos(conexion, principal, destino, "bdbodegas", "codigo = '" + BODEGA_POR_DEFECTO + "'");
            prepararConfiguracion(conexion, destino, empresa);
            DatosBaseEmpresa.asegurar(conexion, destino);

            if (opciones.isGrupos()) {
                informar(progreso, "Copiando grupos y subgrupos");
                EsquemaEmpresa.copiarDatos(conexion, origenDatos, destino, "bdgrupo", null);
                EsquemaEmpresa.copiarDatos(conexion, origenDatos, destino, "bdsubgrupo", null);
            }

            if (opciones.isProductos()) {
                informar(progreso, "Copiando productos");
                copiarProductos(conexion, origenDatos, destino, usuario);
            }

            if (opciones.isContactos()) {
                informar(progreso, "Copiando contactos");
                EsquemaEmpresa.copiarDatos(conexion, origenDatos, destino, "bdterceros", null);
            } else {
                EsquemaEmpresa.copiarDatos(conexion, principal, destino, "bdterceros", "idSistema = '" + TERCERO_POR_DEFECTO + "'");
            }

            if (opciones.isDisenos()) {
                informar(progreso, "Copiando diseños");
                EsquemaEmpresa.copiarDatos(conexion, origenDatos, destino, "bddiscosteo", null);
            }

            informar(progreso, "Registrando la empresa");
            daoEmpresas.registrar(empresa, usuario);
            return empresa;
        } catch (SQLException | RuntimeException ex) {
            if (baseCreada) {
                eliminarBase(destino);
            }
            throw ex instanceof SQLException ? (SQLException) ex : new SQLException(ex.getMessage(), ex);
        }
    }

    private void prepararConfiguracion(Connection conexion, String destino, Empresa empresa) throws SQLException {
        try (Statement sentencia = conexion.createStatement()) {
            sentencia.executeUpdate("UPDATE " + EsquemaEmpresa.tabla(destino, "bdconsecutivos") + " SET numero = '1'");
        }
        try (PreparedStatement maestra = conexion.prepareStatement("UPDATE " + EsquemaEmpresa.tabla(destino, "bdmaestra")
                + " SET nitEmpresa = ?, nombreEmpresa = ?, regimenEmpresa = NULL, direccionEmpresa = NULL, telefonoEmpresa = NULL, "
                + "responsabilidadesFiscales = NULL, bodega = ?")) {
            maestra.setString(1, empresa.getNit());
            maestra.setString(2, empresa.getNombre());
            maestra.setString(3, BODEGA_POR_DEFECTO);
            maestra.executeUpdate();
        }
    }

    private void copiarProductos(Connection conexion, String origen, String destino, String usuario) throws SQLException {
        for (String tabla : CATALOGOS_PRODUCTOS) {
            EsquemaEmpresa.copiarDatos(conexion, origen, destino, tabla, null);
        }
        EsquemaEmpresa.copiarDatos(conexion, origen, destino, "bdproductos", null);
        EsquemaEmpresa.copiarDatos(conexion, origen, destino, "bdproductoslistas", null);
        EsquemaEmpresa.copiarDatos(conexion, origen, destino, "bdproductosunificados", null);

        try (PreparedStatement costos = conexion.prepareStatement("INSERT INTO " + EsquemaEmpresa.tabla(destino, "bdultimoponderado")
                + " (producto, ultimoCosto, ponderadoAntiguo, cantidadAntigua, cantidadEntrante, nuevoPonderado, nuevaCantidad, ingreso, usuario, fecha) "
                + "SELECT producto, ultimoCosto, 0, 0, 0, ultimoCosto, 0, 'CREACION EMPRESA', ?, NOW() FROM "
                + EsquemaEmpresa.tabla(origen, "bdultimoponderado"))) {
            costos.setString(1, usuario);
            costos.executeUpdate();
        }
    }

    private void eliminarBase(String destino) {
        try (Connection conexion = MySql_connection.crearConexionPrincipal(); Statement sentencia = conexion.createStatement()) {
            sentencia.execute("DROP DATABASE IF EXISTS " + EsquemaEmpresa.id(destino));
        } catch (SQLException | RuntimeException ex) {
            LOGGER.log(Level.SEVERE, "No se pudo eliminar la base de datos incompleta " + destino, ex);
        }
    }

    private static void informar(Consumer<String> progreso, String mensaje) {
        if (progreso != null) {
            progreso.accept(mensaje);
        }
    }
}
