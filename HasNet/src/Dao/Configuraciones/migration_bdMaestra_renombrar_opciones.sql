-- IMPORTANTE: ejecutar como SCRIPT COMPLETO (DBeaver: 'Ejecutar script SQL', Alt+X).
-- No usar 'Ejecutar sentencia' (Ctrl+Enter): las variables @... y el PREPARE
-- deben correr en orden, en la misma sesión.
--
-- Renombra columnas de bdMaestra con nombres que no describían su uso:
--   ocultarInformacionCliente -> validarCupoCredito  ("Validar el cupo del credito")
--   ciudadBuscador            -> moduloPermisos      ("Módulo de solicitudes de permisos")
--   borrarCongelada           -> borrarProductos     ("Borrar productos en mesas/congeladas")
--
-- Ejecutar en cada base de datos de empresa ANTES de desplegar el código que
-- ya usa los nombres nuevos (DaoMaestra). Se conserva el tipo, NULL/NOT NULL
-- y el valor por defecto de cada columna, y los datos no se pierden.
-- Se puede ejecutar más de una vez: si la columna ya tiene el nombre nuevo,
-- no se modifica.

-- 1) VISTA PREVIA: columnas a renombrar y su definición actual
SELECT COLUMN_NAME AS columna_actual, COLUMN_TYPE AS tipo, IS_NULLABLE AS acepta_nulos, COLUMN_DEFAULT AS por_defecto
FROM information_schema.COLUMNS
WHERE TABLE_SCHEMA = DATABASE()
  AND TABLE_NAME = 'bdMaestra'
  AND COLUMN_NAME IN ('ocultarInformacionCliente', 'ciudadBuscador', 'borrarCongelada');

-- 2) RENOMBRAR: arma un CHANGE COLUMN por cada columna que aún tenga el
--    nombre viejo (y cuyo nombre nuevo no exista)
SET @cambios = (
    SELECT GROUP_CONCAT(
        CONCAT('CHANGE COLUMN `', c.COLUMN_NAME, '` `', r.nuevo, '` ', c.COLUMN_TYPE,
               IF(c.IS_NULLABLE = 'NO', ' NOT NULL', ' NULL'),
               IF(c.COLUMN_DEFAULT IS NULL, '', CONCAT(' DEFAULT ', QUOTE(c.COLUMN_DEFAULT))))
        SEPARATOR ', ')
    FROM information_schema.COLUMNS c
    JOIN (
        SELECT 'ocultarInformacionCliente' AS viejo, 'validarCupoCredito' AS nuevo
        UNION ALL SELECT 'ciudadBuscador', 'moduloPermisos'
        UNION ALL SELECT 'borrarCongelada', 'borrarProductos'
    ) r ON r.viejo = c.COLUMN_NAME
    WHERE c.TABLE_SCHEMA = DATABASE()
      AND c.TABLE_NAME = 'bdMaestra'
      AND NOT EXISTS (
          SELECT 1 FROM information_schema.COLUMNS n
          WHERE n.TABLE_SCHEMA = DATABASE() AND n.TABLE_NAME = 'bdMaestra' AND n.COLUMN_NAME = r.nuevo
      )
);

SET @sql = IF(@cambios IS NULL,
    'SELECT ''No hay columnas por renombrar (ya tienen el nombre nuevo)'' AS resultado',
    CONCAT('ALTER TABLE bdMaestra ', @cambios));

PREPARE renombrar FROM @sql;
EXECUTE renombrar;
DEALLOCATE PREPARE renombrar;

-- 3) VERIFICACIÓN: deben aparecer los tres nombres nuevos y ninguno viejo
SELECT COLUMN_NAME AS columna, COLUMN_TYPE AS tipo
FROM information_schema.COLUMNS
WHERE TABLE_SCHEMA = DATABASE()
  AND TABLE_NAME = 'bdMaestra'
  AND COLUMN_NAME IN ('validarCupoCredito', 'moduloPermisos', 'borrarProductos',
                      'ocultarInformacionCliente', 'ciudadBuscador', 'borrarCongelada');
