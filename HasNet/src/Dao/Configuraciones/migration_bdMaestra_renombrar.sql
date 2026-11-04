-- Migración: renombrar columnas bdMaestra para alinear con ModeloMaestraGeneral
-- Ejecutar ANTES de desplegar el código Java actualizado.

ALTER TABLE bdMaestra
    -- Columnas con nombre críptico → nombre descriptivo
    CHANGE COLUMN c2 configuracionRegimen VARCHAR(100),
    CHANGE COLUMN c3 alertaUtilidad VARCHAR(10),
    CHANGE COLUMN c4 tipoDescuento VARCHAR(30),
    CHANGE COLUMN c5 contrasena VARCHAR(50),
    CHANGE COLUMN d1 nitEmpresa VARCHAR(100),
    CHANGE COLUMN d2 nombreEmpresa VARCHAR(200),
    CHANGE COLUMN d3 regimenEmpresa VARCHAR(100),
    CHANGE COLUMN d6 direccionEmpresa VARCHAR(200),
    CHANGE COLUMN d7 telefonoEmpresa VARCHAR(50),

    -- Renombres semánticos
    CHANGE COLUMN lector usarLector TINYINT(1),
    CHANGE COLUMN pvpSinIva preciosConImpuesto TINYINT(1),
    CHANGE COLUMN meserosFacturarMesas facturarMesas TINYINT(1),
    CHANGE COLUMN consecutivo consecutivoAdicional TINYINT(1),
    CHANGE COLUMN responsabilidades responsabilidadesFiscales TEXT,
    CHANGE COLUMN idUsuario idCliente VARCHAR(100),
    CHANGE COLUMN passGmail passwordGmail VARCHAR(200),

    -- Renombres con cambio de tipo a INT (almacenan enteros, no texto)
    CHANGE COLUMN c7 diasAlertaCuadre INT,
    CHANGE COLUMN turno1 turnoConsecutivo INT,
    CHANGE COLUMN limite limiteProductosPorFactura INT,
    CHANGE COLUMN descuentoMaximoVentas descuentoMaximoVentas INT,
    CHANGE COLUMN cantidadEstablecida cantidadEstablecida INT,
    CHANGE COLUMN filas filas INT,
    CHANGE COLUMN columnas columnas INT,
    CHANGE COLUMN porcPropina porcPropina INT,
    CHANGE COLUMN diasCobroMora diasCobroMora INT;

-- Eliminar columnas que siempre tuvieron valores constantes
-- y ya no se actualizan desde ningún path de guardado activo.
ALTER TABLE bdMaestra
    DROP COLUMN otros,
    DROP COLUMN domicilios,
    DROP COLUMN impBolsa,
    DROP COLUMN valorBolsa,
    DROP COLUMN pondNegativo,
    DROP COLUMN pagoTerceros,
    DROP COLUMN reimpresion,
    DROP COLUMN numFacturaIncremento;
