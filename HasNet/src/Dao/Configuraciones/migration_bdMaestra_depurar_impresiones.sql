-- IMPORTANTE: ejecutar como SCRIPT COMPLETO (DBeaver: 'Ejecutar script SQL', Alt+X).
-- No usar 'Ejecutar sentencia' (Ctrl+Enter): las variables @... y el PREPARE
-- deben correr en orden, en la misma sesión.
--
-- Depura bdMaestra: elimina las columnas de impresión que ya se migraron a
-- bdMaestraImpresiones (ver migration_bdMaestraImpresiones.sql). El sistema
-- ya no las lee ni las escribe: la impresión se maneja con DaoMaestra
-- guardarImpresiones / obtenerImpresiones sobre bdMaestraImpresiones.
--
-- Ejecutar en cada base de datos de empresa, DESPUÉS de
-- migration_bdMaestraImpresiones.sql. Recomendado: copia de seguridad antes.
--
-- Protección: si bdMaestraImpresiones no existe o está vacía, NO se borra
-- nada (los datos de impresión solo están en bdMaestra).

-- Columnas viejas de impresión en bdMaestra (origen de la migración)
SET @columnas_impresion = 'previsualizarFactura,previsualizarOServicio,previsualizarCotizacion,previsualizarPlanSepare,previsualizarPedido,previsualizarComanda,previsualizarPrefactura,copiasFactura,copiasOServicio,copiasCotizacion,copiasPlanSepare,copiasPedido,preguntarCopiasComanda,preguntarCopiasPrefactura,numFactura,numOServicio,numCotizacion,numPlanSepare,numPedido,copiasComanda,copiasPrefactura,impresoraPos,impresoraMediaCarta,impresoraCarta,impresoraComanda,impresoraPrefactura,l2,c6,hora,verRetenciones,verImpoconsumo,recogida,imprimirCuadreFiscal,impresionPorGrupo,imprimirCada,pie,legal,anexoFacturacion,anexoOrdenServicio';

-- 1) VISTA PREVIA: columnas de bdMaestra que se van a eliminar
SELECT COLUMN_NAME AS columna_a_eliminar, COLUMN_TYPE AS tipo
FROM information_schema.COLUMNS
WHERE TABLE_SCHEMA = DATABASE()
  AND TABLE_NAME = 'bdMaestra'
  AND FIND_IN_SET(COLUMN_NAME, @columnas_impresion) > 0
ORDER BY ORDINAL_POSITION;

-- 2) PROTECCIÓN: bdMaestraImpresiones debe existir y tener la fila Id = 1
SET @existe_impresiones = (
    SELECT COUNT(*) FROM information_schema.TABLES
    WHERE TABLE_SCHEMA = DATABASE() AND TABLE_NAME = 'bdMaestraImpresiones'
);

SET @sql = IF(@existe_impresiones = 0,
    'SELECT 0 INTO @filas_impresiones',
    'SELECT COUNT(*) INTO @filas_impresiones FROM bdMaestraImpresiones WHERE Id = 1');
PREPARE revisar FROM @sql;
EXECUTE revisar;
DEALLOCATE PREPARE revisar;

-- 3) ELIMINACIÓN: un solo ALTER TABLE con las columnas que existan
SET @drops = (
    SELECT GROUP_CONCAT(CONCAT('DROP COLUMN `', COLUMN_NAME, '`') ORDER BY ORDINAL_POSITION SEPARATOR ', ')
    FROM information_schema.COLUMNS
    WHERE TABLE_SCHEMA = DATABASE()
      AND TABLE_NAME = 'bdMaestra'
      AND FIND_IN_SET(COLUMN_NAME, @columnas_impresion) > 0
);

SET @sql = CASE
    WHEN @filas_impresiones = 0 THEN
        'SELECT ''NO SE BORRÓ NADA: bdMaestraImpresiones no existe o está vacía. Ejecute primero migration_bdMaestraImpresiones.sql'' AS resultado'
    WHEN @drops IS NULL THEN
        'SELECT ''bdMaestra ya no tiene columnas de impresión'' AS resultado'
    ELSE
        CONCAT('ALTER TABLE bdMaestra ', @drops)
END;

PREPARE depurar FROM @sql;
EXECUTE depurar;
DEALLOCATE PREPARE depurar;

-- 4) VERIFICACIÓN: debe devolver 0
SELECT COUNT(*) AS columnas_impresion_restantes
FROM information_schema.COLUMNS
WHERE TABLE_SCHEMA = DATABASE()
  AND TABLE_NAME = 'bdMaestra'
  AND FIND_IN_SET(COLUMN_NAME, @columnas_impresion) > 0;
