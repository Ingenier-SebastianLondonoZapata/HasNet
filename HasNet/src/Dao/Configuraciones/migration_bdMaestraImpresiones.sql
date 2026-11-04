-- Crear tabla bdMaestraImpresiones y migrar datos desde bdMaestra
-- Ejecutar una sola vez antes de desplegar la nueva versión del código.

CREATE TABLE IF NOT EXISTS bdMaestraImpresiones (
    Id                      INT          NOT NULL DEFAULT 1,
    -- Previsualizar documentos antes de imprimir
    previsualizarFactura    TINYINT(1)   NOT NULL DEFAULT 0,
    previsualizarOServicio  TINYINT(1)   NOT NULL DEFAULT 0,
    previsualizarCotizacion TINYINT(1)   NOT NULL DEFAULT 0,
    previsualizarPlanSepare TINYINT(1)   NOT NULL DEFAULT 0,
    previsualizarPedido     TINYINT(1)   NOT NULL DEFAULT 0,
    previsualizarComanda    TINYINT(1)   NOT NULL DEFAULT 0,
    previsualizarPrefactura TINYINT(1)   NOT NULL DEFAULT 0,
    -- Preguntar número de copias al imprimir
    preguntarCopiasFactura    TINYINT(1) NOT NULL DEFAULT 0,
    preguntarCopiasOServicio  TINYINT(1) NOT NULL DEFAULT 0,
    preguntarCopiasCotizacion TINYINT(1) NOT NULL DEFAULT 0,
    preguntarCopiasPlanSepare TINYINT(1) NOT NULL DEFAULT 0,
    preguntarCopiasPedido     TINYINT(1) NOT NULL DEFAULT 0,
    preguntarCopiasComanda    TINYINT(1) NOT NULL DEFAULT 0,
    preguntarCopiasPrefactura TINYINT(1) NOT NULL DEFAULT 0,
    -- Número predeterminado de copias
    numeroCopiasFactura         INT          NOT NULL DEFAULT 0,
    numeroCopiasOrdenServicio   INT          NOT NULL DEFAULT 0,
    numeroCopiasCotizacion      INT          NOT NULL DEFAULT 0,
    numeroCopiasPlanSepare      INT          NOT NULL DEFAULT 0,
    numeroCopiasPedido          INT          NOT NULL DEFAULT 0,
    numeroCopiasComanda         INT          NOT NULL DEFAULT 0,
    numeroCopiasPrefactura      INT          NOT NULL DEFAULT 0,
    -- Impresoras asignadas por tipo de documento
    impresoraPos        VARCHAR(200) NOT NULL DEFAULT '',
    impresoraMediaCarta VARCHAR(200) NOT NULL DEFAULT '',
    impresoraCarta      VARCHAR(200) NOT NULL DEFAULT '',
    impresoraComanda    VARCHAR(200) NOT NULL DEFAULT '',
    impresoraPrefactura VARCHAR(200) NOT NULL DEFAULT '',
    -- Tipo de impresión predeterminado (pos / factura / facturaCompleta)
    tipoPredeterminado  VARCHAR(50)  NOT NULL DEFAULT 'factura',
    -- Opciones de impresión
    mostrarUbicacion    VARCHAR(5)   NOT NULL DEFAULT 'SI',
    imprimirHora        TINYINT(1)   NOT NULL DEFAULT 0,
    imprimirRetenciones TINYINT(1)   NOT NULL DEFAULT 0,
    imprimirImpoconsumo TINYINT(1)   NOT NULL DEFAULT 0,
    imprimirRecodiga    TINYINT(1)   NOT NULL DEFAULT 0,
    imprimirCuadreFiscal TINYINT(1)  NOT NULL DEFAULT 0,
    impresionPorGrupo   TINYINT(1)   NOT NULL DEFAULT 0,
    formatoImpresion    VARCHAR(100) NOT NULL DEFAULT '',
    -- Texto que aparece en los documentos impresos
    piePagina          TEXT,
    informacionLegal   TEXT,
    anexoFactura       TEXT,
    anexoOrdenServicio TEXT,
    PRIMARY KEY (Id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Migrar datos existentes de bdMaestra (solo si la tabla quedó vacía)
INSERT IGNORE INTO bdMaestraImpresiones (
    Id,
    previsualizarFactura, previsualizarOServicio, previsualizarCotizacion,
    previsualizarPlanSepare, previsualizarPedido, previsualizarComanda, previsualizarPrefactura,
    preguntarCopiasFactura, preguntarCopiasOServicio, preguntarCopiasCotizacion,
    preguntarCopiasPlanSepare, preguntarCopiasPedido, preguntarCopiasComanda, preguntarCopiasPrefactura,
    numeroCopiasFactura, numeroCopiasOrdenServicio, numeroCopiasCotizacion,
    numeroCopiasPlanSepare, numeroCopiasPedido, numeroCopiasComanda, numeroCopiasPrefactura,
    impresoraPos, impresoraMediaCarta, impresoraCarta, impresoraComanda, impresoraPrefactura,
    tipoPredeterminado, mostrarUbicacion,
    imprimirHora, imprimirRetenciones, imprimirImpoconsumo, imprimirRecodiga, imprimirCuadreFiscal,
    impresionPorGrupo, formatoImpresion,
    piePagina, informacionLegal, anexoFactura, anexoOrdenServicio
)
SELECT
    1,
    previsualizarFactura, previsualizarOServicio, previsualizarCotizacion,
    previsualizarPlanSepare, previsualizarPedido, previsualizarComanda, previsualizarPrefactura,
    copiasFactura, copiasOServicio, copiasCotizacion,
    copiasPlanSepare, copiasPedido, preguntarCopiasComanda, preguntarCopiasPrefactura,
    numFactura, numOServicio, numCotizacion, numPlanSepare, numPedido, copiasComanda, copiasPrefactura,
    impresoraPos, impresoraMediaCarta, impresoraCarta, impresoraComanda, impresoraPrefactura,
    l2, c6,
    hora, verRetenciones, verImpoconsumo, recogida, imprimirCuadreFiscal,
    impresionPorGrupo, imprimirCada,
    pie, legal, anexoFacturacion, anexoOrdenServicio
FROM bdMaestra
WHERE Id = 1;
