-- IMPORTANTE: ejecutar como SCRIPT COMPLETO (DBeaver: 'Ejecutar script SQL', Alt+X).
-- No usar 'Ejecutar sentencia' (Ctrl+Enter): las variables @... y el PREPARE
-- deben correr en orden, en la misma sesión.
--
-- Depura bdMaestraImpresiones: elimina las columnas que NO están en el INSERT
-- de DaoMaestra.guardarImpresiones (las únicas que lee y escribe el sistema).
-- Ejecutar sobre la base de datos de la empresa (la que tiene bdMaestraImpresiones).
-- Recomendado: hacer copia de seguridad de la tabla antes de ejecutar.

-- Columnas que usa el sistema (DaoMaestra.guardarImpresiones / obtenerImpresiones)
SET @columnas_usadas = 'Id,previsualizarFactura,previsualizarOServicio,previsualizarCotizacion,previsualizarPlanSepare,previsualizarPedido,previsualizarComanda,previsualizarPrefactura,preguntarCopiasFactura,preguntarCopiasOServicio,preguntarCopiasCotizacion,preguntarCopiasPlanSepare,preguntarCopiasPedido,preguntarCopiasComanda,preguntarCopiasPrefactura,numeroCopiasFactura,numeroCopiasOrdenServicio,numeroCopiasCotizacion,numeroCopiasPlanSepare,numeroCopiasPedido,numeroCopiasComanda,numeroCopiasPrefactura,impresoraPos,impresoraMediaCarta,impresoraCarta,impresoraComanda,impresoraPrefactura,tipoPredeterminado,mostrarUbicacion,imprimirHora,imprimirRetenciones,imprimirImpoconsumo,imprimirRecodiga,imprimirCuadreFiscal,impresionPorGrupo,formatoImpresion,piePagina,informacionLegal,anexoFactura,anexoOrdenServicio';

-- 1) VISTA PREVIA: columnas que se van a eliminar (revisar antes de seguir)
SELECT COLUMN_NAME AS columna_a_eliminar, COLUMN_TYPE AS tipo
FROM information_schema.COLUMNS
WHERE TABLE_SCHEMA = DATABASE()
  AND TABLE_NAME = 'bdMaestraImpresiones'
  AND FIND_IN_SET(COLUMN_NAME, @columnas_usadas) = 0
ORDER BY ORDINAL_POSITION;

-- 2) ELIMINACIÓN: arma y ejecuta un solo ALTER TABLE con las columnas sobrantes.
--    Si no sobra ninguna, solo muestra un mensaje y no modifica la tabla.
SET @drops = (
    SELECT GROUP_CONCAT(CONCAT('DROP COLUMN `', COLUMN_NAME, '`') ORDER BY ORDINAL_POSITION SEPARATOR ', ')
    FROM information_schema.COLUMNS
    WHERE TABLE_SCHEMA = DATABASE()
      AND TABLE_NAME = 'bdMaestraImpresiones'
      AND FIND_IN_SET(COLUMN_NAME, @columnas_usadas) = 0
);

SET @sql = IF(@drops IS NULL,
    'SELECT ''bdMaestraImpresiones no tiene columnas sobrantes'' AS resultado',
    CONCAT('ALTER TABLE bdMaestraImpresiones ', @drops));

PREPARE depurar FROM @sql;
EXECUTE depurar;
DEALLOCATE PREPARE depurar;

-- 3) VERIFICACIÓN: debe devolver 40 columnas
SELECT COUNT(*) AS columnas_restantes
FROM information_schema.COLUMNS
WHERE TABLE_SCHEMA = DATABASE()
  AND TABLE_NAME = 'bdMaestraImpresiones';
