-- IMPORTANTE: ejecutar como SCRIPT COMPLETO (DBeaver: 'Ejecutar script SQL', Alt+X).
-- No usar 'Ejecutar sentencia' (Ctrl+Enter): las variables @... y el PREPARE
-- deben correr en orden, en la misma sesión.
--
-- Depura bdMaestra: elimina las columnas de alertas que el sistema ya no usa
-- (alertaFechaDias, alertaCantidadNumeracion, alertaPromedioDias). Se quitaron
-- de DaoMaestra (obtenerGeneral / guardarDatosGenerales) y de ModeloMaestraGeneral.
--
-- Ejecutar en cada base de datos de empresa. Recomendado: copia de seguridad antes.
-- Se puede ejecutar más de una vez: solo borra las columnas que existan.

SET @columnas_alertas = 'alertaFechaDias,alertaCantidadNumeracion,alertaPromedioDias';

-- 1) VISTA PREVIA: columnas que se van a eliminar
SELECT COLUMN_NAME AS columna_a_eliminar, COLUMN_TYPE AS tipo
FROM information_schema.COLUMNS
WHERE TABLE_SCHEMA = DATABASE()
  AND TABLE_NAME = 'bdMaestra'
  AND FIND_IN_SET(COLUMN_NAME, @columnas_alertas) > 0
ORDER BY ORDINAL_POSITION;

-- 2) ELIMINACIÓN: un solo ALTER TABLE con las columnas que existan
SET @drops = (
    SELECT GROUP_CONCAT(CONCAT('DROP COLUMN `', COLUMN_NAME, '`') ORDER BY ORDINAL_POSITION SEPARATOR ', ')
    FROM information_schema.COLUMNS
    WHERE TABLE_SCHEMA = DATABASE()
      AND TABLE_NAME = 'bdMaestra'
      AND FIND_IN_SET(COLUMN_NAME, @columnas_alertas) > 0
);

SET @sql = IF(@drops IS NULL,
    'SELECT ''bdMaestra ya no tiene las columnas de alertas'' AS resultado',
    CONCAT('ALTER TABLE bdMaestra ', @drops));

PREPARE depurar FROM @sql;
EXECUTE depurar;
DEALLOCATE PREPARE depurar;

-- 3) VERIFICACIÓN: debe devolver 0
SELECT COUNT(*) AS columnas_alertas_restantes
FROM information_schema.COLUMNS
WHERE TABLE_SCHEMA = DATABASE()
  AND TABLE_NAME = 'bdMaestra'
  AND FIND_IN_SET(COLUMN_NAME, @columnas_alertas) > 0;
