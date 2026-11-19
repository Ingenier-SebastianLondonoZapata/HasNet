package Procesos.Multiempresa;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

final class SincronizadorEsquema {

    private static final Logger LOGGER = Logger.getLogger(SincronizadorEsquema.class.getName());
    private static final String CLAVE_HUELLA = "huellaEstructura";

    private SincronizadorEsquema() {
    }

    static int sincronizar(Connection conexion, String origen, String destino) throws SQLException {
        try (Statement sentencia = conexion.createStatement()) {
            sentencia.execute("SET SESSION sql_mode = 'STRICT_ALL_TABLES,NO_ENGINE_SUBSTITUTION'");
        }

        EstructuraBaseDatos fuente = EstructuraBaseDatos.leer(conexion, origen);
        String huella = fuente.huella();
        if (huella.equals(ActualizadorEsquema.leerControl(conexion, destino, CLAVE_HUELLA))) {
            return 0;
        }
        EstructuraBaseDatos actual = EstructuraBaseDatos.leer(conexion, destino);

        int errores = 0;
        for (Map.Entry<String, String> entrada : fuente.tablas.entrySet()) {
            String clave = entrada.getKey();
            String tabla = entrada.getValue();
            if (!actual.tablas.containsKey(clave)) {
                errores += ejecutar(conexion, "CREATE TABLE IF NOT EXISTS " + EsquemaEmpresa.tabla(destino, tabla) + " LIKE " + EsquemaEmpresa.tabla(origen, tabla));
                continue;
            }
            errores += sincronizarColumnas(conexion, destino, tabla, fuente.columnas.get(clave), actual.columnas.get(clave));
            errores += sincronizarIndices(conexion, destino, tabla, fuente.indices.get(clave), actual.indices.get(clave));
        }
        errores += sincronizarVistas(conexion, origen, destino, fuente, actual);

        if (errores == 0) {
            ActualizadorEsquema.guardarControl(conexion, destino, CLAVE_HUELLA, huella);
        } else {
            LOGGER.log(Level.WARNING, "La base {0} quedó con {1} diferencia(s) sin resolver; se reintentará en el próximo inicio de sesión",
                    new Object[]{destino, errores});
        }
        return errores;
    }

    private static int sincronizarColumnas(Connection conexion, String destino, String tabla,
            Map<String, EstructuraBaseDatos.Columna> esperadas, Map<String, EstructuraBaseDatos.Columna> existentes) {
        int errores = 0;
        for (Map.Entry<String, EstructuraBaseDatos.Columna> entrada : esperadas.entrySet()) {
            EstructuraBaseDatos.Columna esperada = entrada.getValue();
            EstructuraBaseDatos.Columna existente = existentes.get(entrada.getKey());
            String prefijo = "ALTER TABLE " + EsquemaEmpresa.tabla(destino, tabla);
            if (existente == null) {
                if (!esperada.autoIncremento) {
                    errores += ejecutar(conexion, prefijo + " ADD COLUMN " + EsquemaEmpresa.identificador(esperada.nombre) + " " + esperada.definicion());
                }
            } else if (!esperada.igualA(existente)) {
                errores += ejecutar(conexion, prefijo + " MODIFY COLUMN " + EsquemaEmpresa.identificador(existente.nombre) + " " + esperada.definicion());
            }
        }
        return errores;
    }

    private static int sincronizarIndices(Connection conexion, String destino, String tabla,
            Map<String, EstructuraBaseDatos.Indice> esperados, Map<String, EstructuraBaseDatos.Indice> existentes) {
        int errores = 0;
        for (Map.Entry<String, EstructuraBaseDatos.Indice> entrada : esperados.entrySet()) {
            EstructuraBaseDatos.Indice esperado = entrada.getValue();
            if (esperado.esPrimario()) {
                continue;
            }
            EstructuraBaseDatos.Indice existente = existentes.get(entrada.getKey());
            String prefijo = "ALTER TABLE " + EsquemaEmpresa.tabla(destino, tabla);
            if (existente == null) {
                errores += ejecutar(conexion, prefijo + " ADD " + esperado.definicion());
            } else if (!esperado.firma().equals(existente.firma())) {
                errores += ejecutar(conexion, prefijo + " DROP INDEX " + EsquemaEmpresa.identificador(existente.nombre) + ", ADD " + esperado.definicion());
            }
        }
        return errores;
    }

    private static int sincronizarVistas(Connection conexion, String origen, String destino, EstructuraBaseDatos fuente, EstructuraBaseDatos actual) {
        Map<String, String> cambiadas = new LinkedHashMap<>();
        for (String clave : fuente.vistas.keySet()) {
            String esperada = fuente.vistaSinCalificar(clave);
            if (!esperada.equals(actual.vistaSinCalificar(clave))) {
                cambiadas.put(fuente.nombresVistas.get(clave), fuente.vistas.get(clave));
            }
        }
        if (cambiadas.isEmpty()) {
            return 0;
        }
        try {
            EsquemaEmpresa.crearVistas(conexion, origen, destino, cambiadas);
            return 0;
        } catch (SQLException ex) {
            LOGGER.log(Level.WARNING, "No se pudieron actualizar vistas en " + destino, ex);
            return 1;
        }
    }

    private static int ejecutar(Connection conexion, String sql) {
        try (Statement sentencia = conexion.createStatement()) {
            sentencia.execute(sql);
            LOGGER.log(Level.INFO, "Estructura actualizada: {0}", sql);
            return 0;
        } catch (SQLException ex) {
            LOGGER.log(Level.WARNING, "No se pudo aplicar: " + sql, ex);
            return 1;
        }
    }
}
