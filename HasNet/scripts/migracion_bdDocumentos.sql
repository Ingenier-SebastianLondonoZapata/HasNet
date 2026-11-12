SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE IF NOT EXISTS bdDocumentos (
    idDocumento INT(11) NOT NULL AUTO_INCREMENT,
    tipoDocumento VARCHAR(30) NOT NULL,
    idFactura VARCHAR(255) NOT NULL,
    factura VARCHAR(255) NOT NULL,
    cliente VARCHAR(255) NULL,
    vendedor VARCHAR(50) NULL,
    red VARCHAR(255) NULL,
    fechaFactura DATE NULL,
    horaFactura TIME NULL,
    fechaVencimiento DATE NULL,
    fechaAlerta DATE NULL,
    efectivoGeneral DECIMAL(18,3) NULL,
    ncGeneral DECIMAL(18,3) NULL,
    chequeGeneral DECIMAL(18,3) NULL,
    targetaGeneral DECIMAL(18,3) NULL,
    tarjetaCredito DECIMAL(18,3) NULL,
    totalGeneral DECIMAL(18,3) NULL,
    descuentoGeneral DECIMAL(18,3) NULL,
    ivaGeneral DECIMAL(18,3) NULL,
    subtotalGeneral DECIMAL(18,3) NULL,
    impoconsumoGeneral DECIMAL(18,3) NULL,
    totalPropina DECIMAL(18,3) NULL,
    porcPropina VARCHAR(255) NULL,
    devuelta DECIMAL(18,3) NULL,
    rtIva DECIMAL(18,0) NULL,
    rtIca DECIMAL(18,0) NULL,
    rtFuente DECIMAL(18,0) NULL,
    otros DECIMAL(18,0) NULL,
    copago INT(11) NULL,
    comprobante VARCHAR(255) NULL,
    cotizacion VARCHAR(255) NULL,
    credito TINYINT(1) NOT NULL DEFAULT 0,
    cxc VARCHAR(255) NULL,
    usuario VARCHAR(255) NULL,
    observacion LONGTEXT NULL,
    terminal VARCHAR(255) NULL,
    estadoGeneral VARCHAR(255) NULL,
    estado2 VARCHAR(255) NULL,
    resolucion VARCHAR(255) NULL,
    anulada TINYINT(1) NOT NULL DEFAULT 0,
    anula VARCHAR(255) NULL,
    fechaAnulacion DATE NULL,
    cuadreAnulacion VARCHAR(255) NULL,
    usuarioAnula VARCHAR(255) NULL,
    notaAnulacion LONGTEXT NULL,
    placa VARCHAR(255) NULL,
    garantia VARCHAR(255) NULL,
    diasGarantia VARCHAR(255) NULL,
    terminos LONGTEXT NULL,
    conseMesa VARCHAR(255) NULL,
    turno VARCHAR(255) NULL,
    congelada VARCHAR(255) NULL,
    bodega VARCHAR(40) NULL,
    modeloContable VARCHAR(50) NULL,
    franquisia VARCHAR(255) NULL,
    comision VARCHAR(255) NULL,
    valorComision INT(11) NULL,
    totalFacturaComision INT(11) NULL,
    mesFacturado VARCHAR(255) NULL,
    idCosteo VARCHAR(255) NULL,
    sisteCredito TINYINT(1) NOT NULL DEFAULT 0,
    vehiculoTipo VARCHAR(255) NULL,
    vehiculoModelo VARCHAR(255) NULL,
    vehiculoNumeroChasis VARCHAR(255) NULL,
    vehiculoFechaCompra DATETIME NULL,
    vehiculoMarca VARCHAR(255) NULL,
    vehiculoKm VARCHAR(255) NULL,
    vehiculoNumeroMotor VARCHAR(255) NULL,
    vehiculoColor VARCHAR(255) NULL,
    vehiculoProblema VARCHAR(255) NULL,
    periodicidad VARCHAR(255) NULL,
    inicio DATE NULL,
    hasta DATE NULL,
    cantIncremento VARCHAR(255) NULL,
    fechaUltimoPago DATE NULL,
    cantFacturados INT(11) NULL,
    PRIMARY KEY (idDocumento),
    UNIQUE KEY uk_bdDocumentos_tipo_factura (tipoDocumento, factura),
    KEY idx_bdDocumentos_idFactura (idFactura),
    KEY idx_bdDocumentos_factura (factura),
    KEY idx_bdDocumentos_cliente (cliente),
    KEY idx_bdDocumentos_fechaFactura (fechaFactura),
    KEY idx_bdDocumentos_estadoGeneral (estadoGeneral),
    KEY idx_bdDocumentos_estado2 (estado2),
    KEY idx_bdDocumentos_cxc (cxc),
    KEY idx_bdDocumentos_placa (placa),
    KEY idx_bdDocumentos_conseMesa (conseMesa),
    KEY idx_bdDocumentos_tipo_fecha (tipoDocumento, fechaFactura),
    KEY idx_bdDocumentos_tipo_red (tipoDocumento, red),
    KEY idx_bdDocumentos_tipo_cliente (tipoDocumento, cliente),
    KEY idx_bdDocumentos_cuadreAnulacion (cuadreAnulacion)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS bdDocumentosDetalle (
    idDetalle INT(11) NOT NULL AUTO_INCREMENT,
    idDocumento INT(11) NOT NULL,
    linea INT(11) NOT NULL DEFAULT 0,
    producto VARCHAR(255) NULL,
    descripcion VARCHAR(255) NULL,
    lista DECIMAL(18,3) NULL,
    cantidad DOUBLE NULL,
    cant2 DOUBLE NULL,
    plu INT(11) NULL,
    descuento DECIMAL(18,3) NULL,
    porcDescuento DOUBLE NULL,
    subtotal DECIMAL(18,3) NULL,
    iva DECIMAL(18,3) NULL,
    porcIva INT(11) NULL,
    impoconsumo DECIMAL(18,3) NULL,
    porcImpo INT(11) NULL,
    total DECIMAL(18,3) NULL,
    utilidad DECIMAL(18,3) NULL,
    utilidad1 INT(11) NULL,
    costo DECIMAL(18,3) NULL,
    estado VARCHAR(255) NULL,
    tercero VARCHAR(255) NULL,
    preparacion VARCHAR(255) NULL,
    rango VARCHAR(255) NULL,
    lote VARCHAR(255) NULL,
    idProd VARCHAR(255) NULL,
    detalle VARCHAR(255) NULL,
    consecutivoLinea VARCHAR(255) NULL,
    redLinea VARCHAR(255) NULL,
    estadoGeneralLinea VARCHAR(255) NULL,
    estado2Linea VARCHAR(255) NULL,
    PRIMARY KEY (idDetalle),
    KEY idx_bdDocumentosDetalle_documento (idDocumento),
    KEY idx_bdDocumentosDetalle_producto (producto),
    KEY idx_bdDocumentosDetalle_idProd (idProd),
    CONSTRAINT fk_bdDocumentosDetalle_bdDocumentos FOREIGN KEY (idDocumento)
        REFERENCES bdDocumentos (idDocumento) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP PROCEDURE IF EXISTS eliminarColumnaSiExiste;
DELIMITER $$
CREATE PROCEDURE eliminarColumnaSiExiste(IN tabla VARCHAR(64), IN columna VARCHAR(64))
BEGIN
    IF EXISTS (SELECT 1 FROM information_schema.columns
               WHERE table_schema = DATABASE() AND table_name = tabla AND column_name = columna) THEN
        SET @sentencia = CONCAT('ALTER TABLE ', tabla, ' DROP COLUMN ', columna);
        PREPARE ejecutar FROM @sentencia;
        EXECUTE ejecutar;
        DEALLOCATE PREPARE ejecutar;
    END IF;
END$$
DELIMITER ;

CALL eliminarColumnaSiExiste('bdDocumentosDetalle', 'imei');
CALL eliminarColumnaSiExiste('bdDocumentosDetalle', 'impuesto');
DROP PROCEDURE IF EXISTS eliminarColumnaSiExiste;

DROP PROCEDURE IF EXISTS eliminarLlaveForanea;
DELIMITER $$
CREATE PROCEDURE eliminarLlaveForanea(IN tabla VARCHAR(64), IN llave VARCHAR(64))
BEGIN
    IF EXISTS (SELECT 1 FROM information_schema.table_constraints
               WHERE constraint_schema = DATABASE() AND table_name = tabla
                 AND constraint_name = llave AND constraint_type = 'FOREIGN KEY') THEN
        SET @sentencia = CONCAT('ALTER TABLE ', tabla, ' DROP FOREIGN KEY ', llave);
        PREPARE ejecutar FROM @sentencia;
        EXECUTE ejecutar;
        DEALLOCATE PREPARE ejecutar;
    END IF;
END$$
DELIMITER ;

CALL eliminarLlaveForanea('bddetalleinventario', 'fk_bdDetalleInventario_bdFactura1');
CALL eliminarLlaveForanea('bdlavado', 'fk_bdFactura_bdLavado1');
CALL eliminarLlaveForanea('bdnc', 'fk_bdFactura_bdNc1');
CALL eliminarLlaveForanea('bdnc', 'fk_bdVerificadorFacturas_bdNc1');
DROP PROCEDURE IF EXISTS eliminarLlaveForanea;

DROP PROCEDURE IF EXISTS eliminarTablaSiExiste;
DELIMITER $$
CREATE PROCEDURE eliminarTablaSiExiste(IN tabla VARCHAR(64))
BEGIN
    IF EXISTS (SELECT 1 FROM information_schema.tables
               WHERE table_schema = DATABASE() AND table_name = tabla AND table_type = 'BASE TABLE') THEN
        SET @sentencia = CONCAT('DROP TABLE ', tabla);
        PREPARE ejecutar FROM @sentencia;
        EXECUTE ejecutar;
        DEALLOCATE PREPARE ejecutar;
    END IF;
END$$
DELIMITER ;

CALL eliminarTablaSiExiste('bdfactura');
CALL eliminarTablaSiExiste('bdcotizacion');
CALL eliminarTablaSiExiste('bdplansepare');
CALL eliminarTablaSiExiste('bdpedido');
CALL eliminarTablaSiExiste('bdoservicio1');
CALL eliminarTablaSiExiste('bdcongelada');
CALL eliminarTablaSiExiste('bdcuentacobro');
CALL eliminarTablaSiExiste('bdverificadorfacturas');
DROP PROCEDURE IF EXISTS eliminarTablaSiExiste;

DROP VIEW IF EXISTS bdverificadorfacturas;
DROP VIEW IF EXISTS `congeladas1`;
DROP VIEW IF EXISTS `factura2`;
DROP VIEW IF EXISTS `facturaagrupada`;
DROP VIEW IF EXISTS `repabonos1`;
DROP VIEW IF EXISTS `totalizadocongelada`;
DROP VIEW IF EXISTS `totalizadooservicio`;
DROP VIEW IF EXISTS `oservicio3`;
DROP VIEW IF EXISTS `totalizadopedidos`;
DROP VIEW IF EXISTS `pedidos1`;
DROP VIEW IF EXISTS `totalizadoplansepares`;
DROP VIEW IF EXISTS `plansepare1`;
DROP VIEW IF EXISTS `totalizadoventas`;
DROP VIEW IF EXISTS `consultacuentaspagadas3`;
DROP VIEW IF EXISTS `factura`;
DROP VIEW IF EXISTS `factura1`;
DROP VIEW IF EXISTS `totalizadoajusteentrada`;
DROP VIEW IF EXISTS `totalizadoajustesalida`;
DROP VIEW IF EXISTS `ajustesentrada`;
DROP VIEW IF EXISTS `ajustessalida`;
DROP VIEW IF EXISTS `ac2laboratorio`;
DROP VIEW IF EXISTS `ac2oftalmologia`;
DROP VIEW IF EXISTS `cuadrecajafiscalventas`;
DROP VIEW IF EXISTS `cuadrecajafiscalpagos`;
DROP VIEW IF EXISTS `cuadrecajafiscalivas`;
DROP VIEW IF EXISTS `cuadrecajafiscalimpo`;
DROP VIEW IF EXISTS `ivadiscriminadocuadres`;
DROP VIEW IF EXISTS `impodiscriminadocuadres`;
DROP VIEW IF EXISTS `totalescuadrecontado`;
DROP VIEW IF EXISTS `totalescuadrecredito`;
DROP VIEW IF EXISTS `totalanulacioncuadre`;
DROP VIEW IF EXISTS `totalescuadresepare`;
DROP VIEW IF EXISTS `totalesrtfuentecuadre`;
DROP VIEW IF EXISTS `totalesrtvivacuadre`;
DROP VIEW IF EXISTS `totalimpocuadre`;
DROP VIEW IF EXISTS `totalivacuadre`;
DROP VIEW IF EXISTS `cotizacion`;
DROP VIEW IF EXISTS `plansepare`;
DROP VIEW IF EXISTS `facturaiva`;
DROP VIEW IF EXISTS `abonosfacturacion`;
DROP VIEW IF EXISTS `carterapendiente`;
DROP VIEW IF EXISTS `concxc`;
DROP VIEW IF EXISTS `concxcsepare`;
DROP VIEW IF EXISTS `condomicilio`;
DROP VIEW IF EXISTS `consultanc`;
DROP VIEW IF EXISTS `plansepareagrupado`;
DROP VIEW IF EXISTS `repabonos`;
DROP VIEW IF EXISTS `repabonossepare`;
DROP VIEW IF EXISTS `repcartera2`;
DROP VIEW IF EXISTS `repcarteracuentacobro`;
DROP VIEW IF EXISTS `repcarterapendientecuentacobro`;
DROP VIEW IF EXISTS `repcarterapendientesepares`;
DROP VIEW IF EXISTS `repcarterasepares`;
DROP VIEW IF EXISTS `reppendientescuentacobro`;
DROP VIEW IF EXISTS `reppendientessepares`;
DROP VIEW IF EXISTS `separacion`;
DROP VIEW IF EXISTS `facturacion`;
DROP VIEW IF EXISTS `abononuevo`;
DROP VIEW IF EXISTS `repcart`;
DROP VIEW IF EXISTS `bdcongelada`;
DROP VIEW IF EXISTS `bdcotizacion`;
DROP VIEW IF EXISTS `bdcuentacobro`;
DROP VIEW IF EXISTS `bdfactura`;
DROP VIEW IF EXISTS `bdoservicio1`;
DROP VIEW IF EXISTS `bdpedido`;
DROP VIEW IF EXISTS `bdplansepare`;
DROP VIEW IF EXISTS `consultacuentaspagadas2`;
DROP VIEW IF EXISTS `consultacuentaspagadas`;
DROP VIEW IF EXISTS `contadorfacturasanuladas`;
DROP VIEW IF EXISTS `facturaciontablas`;
DROP VIEW IF EXISTS `oservicio1`;
DROP VIEW IF EXISTS `repcartera3`;
DROP VIEW IF EXISTS `repcartera`;
DROP VIEW IF EXISTS `repcotizas`;
DROP VIEW IF EXISTS `repordendiario`;
DROP VIEW IF EXISTS `reppendientes`;
DROP VIEW IF EXISTS `repplantillasdiario`;
DROP VIEW IF EXISTS `repseparesdiario`;
DROP VIEW IF EXISTS `repventascreditos`;
DROP VIEW IF EXISTS `totalizadoanuladas`;
DROP VIEW IF EXISTS `vwfacturasdocumento`;
DROP VIEW IF EXISTS `repivasventas`;
DROP VIEW IF EXISTS `repimpoventas`;
DROP VIEW IF EXISTS `totalizadonotascredito`;
DROP VIEW IF EXISTS `notascredito1`;

CREATE OR REPLACE VIEW `anulacion` AS select d.idFactura AS `idFactura`,cast(substr(d.idFactura,6,100) as signed) AS `ordenId`,d.cliente AS `cliente`,d.vendedor AS `vendedor`,d.red AS `red`,d.fechaFactura AS `fechaFactura`,d.fechaVencimiento AS `fechaVencimiento`,d.efectivoGeneral AS `efectivoGeneral`,d.ncGeneral AS `ncGeneral`,d.chequeGeneral AS `chequeGeneral`,d.targetaGeneral AS `targetaGeneral`,d.totalGeneral AS `totalGeneral`,d.descuentoGeneral AS `descuentoGeneral`,d.ivaGeneral AS `ivaGeneral`,d.subtotalGeneral AS `subtotalGeneral`,d.comprobante AS `comprobante`,d.cotizacion AS `cotizacion`,d.anulada AS `anulada`,d.anula AS `anula`,d.credito AS `credito`,d.cxc AS `cxc`,d.usuario AS `usuario`,d.rtIva AS `rtIva`,d.rtIca AS `rtIca`,d.rtFuente AS `rtFuente`,d.otros AS `otros`,d.observacion AS `observacion`,CAST(0 AS UNSIGNED) AS `anulada1`,'' AS `anula1`,CAST(0 AS UNSIGNED) AS `credito1`,'' AS `cxc1`,'' AS `usuario1`,d.fechaAlerta AS `fechaAlerta`,d.terminal AS `terminal`,d.estadoGeneral AS `estadoGeneral`,d.estado2 AS `estado2`,d.devuelta AS `devuelta`,d.factura AS `factura`,d.resolucion AS `resolucion`,d.fechaAnulacion AS `fechaAnulacion`,d.cuadreAnulacion AS `cuadreAnulacion`,d.usuarioAnula AS `usuarioAnula`,d.copago AS `copago`,d.placa AS `placa`,d.garantia AS `garantia`,d.diasGarantia AS `diasGarantia`,dd.rango AS `rango`,d.terminos AS `terminos`,d.notaAnulacion AS `notaAnulacion`,d.conseMesa AS `conseMesa`,dd.producto AS `producto`,dd.lista AS `lista`,dd.cantidad AS `cantidad`,dd.descuento AS `descuento`,dd.total AS `total`,dd.iva AS `iva`,dd.subtotal AS `subtotal`,'' AS `NC`,dd.utilidad AS `utilidad`,'' AS `concepto`,dd.porcDescuento AS `porcDescuento`,dd.descripcion AS `descripcion`,dd.plu AS `plu`,dd.cant2 AS `cant2`,dd.estado AS `estado`,dd.porcIva AS `porcIva`,dd.tercero AS `tercero`,dd.utilidad1 AS `utilidad1`,`bdterceros`.`nombre` AS `nombre`,`bdterceros`.`telefono` AS `telefono`,`bdcxc`.`plazo` AS `plazo`,`bdterceros`.`direccion` AS `direccion`,if(isnull(`bdprestamo`.`cuotaInicial`),0,`bdprestamo`.`cuotaInicial`) AS `cuotaInicial2`,d.factura AS `id2`,`bdproductos`.`ubicacion1` AS `ubicacion1`,`bdproductos`.`referencia` AS `referencia`,d.cotizacion AS `documento2`,d.impoconsumoGeneral AS `impoconsumoGeneral`,`bdproductos`.`Codigo` AS `Codigo`,`bdterceros`.`id` AS `IdCliente`,dd.detalle AS `detalle`,d.horaFactura AS `hora`,d.bodega AS `bodega` from ((`bdproductos` join (((`bdDocumentos` `d` join `bdDocumentosDetalle` `dd` on(((`dd`.`idDocumento` = `d`.`idDocumento`) and d.tipoDocumento IN ('facturacion', 'notaDebito')))) left join `bdprestamo` on((d.idFactura = `bdprestamo`.`factura`))) left join `bdcxc` on((d.factura = `bdcxc`.`factura2`))) on((`bdproductos`.`idSistema` = dd.producto))) left join `bdterceros` on((d.cliente = `bdterceros`.`idSistema`))) where (d.anulada = 1) order by cast(substr(d.idFactura,6,100) as signed);

CREATE OR REPLACE VIEW `congeladas` AS (select d.idFactura AS `idFactura`,d.cliente AS `cliente`,d.vendedor AS `vendedor`,d.red AS `red`,TIMESTAMP(d.fechaFactura, COALESCE(d.horaFactura, '00:00:00')) AS `fechaFactura`,CAST(d.fechaVencimiento AS DATETIME) AS `fechaVencimiento`,d.efectivoGeneral AS `efectivoGeneral`,d.ncGeneral AS `ncGeneral`,d.chequeGeneral AS `chequeGeneral`,d.targetaGeneral AS `targetaGeneral`,d.totalGeneral AS `totalGeneral`,d.descuentoGeneral AS `descuentoGeneral`,d.ivaGeneral AS `ivaGeneral`,d.subtotalGeneral AS `subtotalGeneral`,d.comprobante AS `comprobante`,d.cotizacion AS `cotizacion`,d.anulada AS `anulada`,d.anula AS `anula`,d.credito AS `credito`,d.cxc AS `cxc`,d.usuario AS `usuario`,d.rtIva AS `rtIva`,d.rtIca AS `rtIca`,d.rtFuente AS `rtFuente`,d.otros AS `otros`,d.observacion AS `observacion`,CAST(0 AS UNSIGNED) AS `anulada1`,'' AS `anula1`,CAST(0 AS UNSIGNED) AS `credito1`,'' AS `cxc1`,'' AS `usuario1`,CAST(d.fechaAlerta AS DATETIME) AS `fechaAlerta`,d.terminal AS `terminal`,d.estadoGeneral AS `estadoGeneral`,d.estado2 AS `estado2`,d.devuelta AS `devuelta`,d.factura AS `factura`,d.resolucion AS `resolucion`,CAST(d.fechaAnulacion AS DATETIME) AS `fechaAnulacion`,d.cuadreAnulacion AS `cuadreAnulacion`,d.usuarioAnula AS `usuarioAnula`,d.copago AS `copago`,d.placa AS `placa`,d.garantia AS `garantia`,d.diasGarantia AS `diasGarantia`,dd.rango AS `rango`,d.terminos AS `terminos`,COALESCE(dd.consecutivoLinea, d.notaAnulacion) AS `notaAnulacion`,d.conseMesa AS `conseMesa`,dd.producto AS `producto`,dd.lista AS `lista`,dd.cantidad AS `cantidad`,dd.descuento AS `descuento`,dd.total AS `total`,dd.iva AS `iva`,dd.subtotal AS `subtotal`,'' AS `NC`,dd.utilidad AS `utilidad`,'' AS `concepto`,dd.porcDescuento AS `porcDescuento`,dd.descripcion AS `descripcion`,dd.plu AS `plu`,dd.cant2 AS `cant2`,dd.estado AS `estado`,dd.porcIva AS `porcIva`,dd.tercero AS `tercero`,dd.utilidad1 AS `utilidad1`,dd.preparacion AS `preparacion`,d.bodega AS `bodega`,`bdterceros`.`nombre` AS `nombre`,`bdterceros`.`telefono` AS `telefono`,`bdterceros`.`id` AS `IdTercero` from ((`bdDocumentos` `d` join `bdDocumentosDetalle` `dd` on(((`dd`.`idDocumento` = `d`.`idDocumento`) and d.tipoDocumento IN ('mesa')))) left join `bdterceros` on((d.cliente = `bdterceros`.`idSistema`))));

CREATE OR REPLACE VIEW `cotiza` AS (select d.idFactura AS `idFactura`,dd.producto AS `producto`,dd.lista AS `lista`,dd.cantidad AS `cantidad`,dd.descuento AS `descuento`,dd.total AS `total`,dd.iva AS `iva`,dd.subtotal AS `subtotal`,'' AS `NC`,dd.utilidad AS `utilidad`,'' AS `concepto`,dd.porcDescuento AS `porcDescuento`,dd.descripcion AS `descripcion`,dd.plu AS `plu`,dd.cant2 AS `cant2`,dd.estado AS `estado`,dd.porcIva AS `porcIva`,dd.tercero AS `tercero`,dd.utilidad1 AS `utilidad1`,dd.preparacion AS `preparacion` from (`bdDocumentos` `d` join `bdDocumentosDetalle` `dd` on(((`dd`.`idDocumento` = `d`.`idDocumento`) and d.tipoDocumento IN ('cotizacion')))));

CREATE OR REPLACE VIEW `liquidarlavadero` AS select `bdlavado`.`id` AS `id`,`bdlavado`.`fechaRealizado` AS `fechaRealizado`,`bdlavado`.`nombre` AS `nombre`,`bdlavadoproductos`.`servicio` AS `servicio`,`bdlavado`.`placa` AS `placa`,`bdlavado`.`tipoVehiculo` AS `tipoVehiculo`,`bdlavado`.`marca` AS `marca`,`bdlavado`.`liquidado` AS `estadoLavado`,`bdlavadoproductos`.`valor` AS `valor`,`bdlavadoproductos`.`codServicio` AS `codServicio`,`bdlavadoproductos`.`empleado` AS `empleado`,`bdlavadoproductos`.`liquidado` AS `liquidado`,`bdterceros`.`nombre` AS `trabajador`,(`bdlavadoproductos`.`valor` - `bdlavadoproductos`.`pago`) AS `utilidad`,`bdlavadoproductos`.`pago` AS `pago`,if(d.credito,'CREDITO','CONTADO') AS `credito2`,`bdproductos`.`Sub_grupo` AS `Sub_grupo`,`bdlavado`.`cliente` AS `cliente`,1 AS `num` from ((((`bdlavado` join `bdlavadoproductos` on((`bdlavado`.`id` = `bdlavadoproductos`.`IdLavado`))) left join `bdterceros` on((`bdterceros`.`idSistema` = `bdlavadoproductos`.`empleado`))) left join (`bdDocumentos` `d` join `bdDocumentosDetalle` `dd` on(((`dd`.`idDocumento` = `d`.`idDocumento`) and d.tipoDocumento IN ('facturacion', 'notaDebito')))) on((d.factura = `bdlavado`.`factura`))) join `bdproductos` on((`bdlavadoproductos`.`codServicio` = `bdproductos`.`idSistema`)));

CREATE OR REPLACE VIEW `ordenservicio` AS select d.idFactura AS `idFactura`,d.cliente AS `cliente`,d.vendedor AS `vendedor`,COALESCE(dd.redLinea, d.red) AS `red`,TIMESTAMP(d.fechaFactura, COALESCE(d.horaFactura, '00:00:00')) AS `fechaFactura`,CAST(d.fechaVencimiento AS DATETIME) AS `fechaVencimiento`,d.efectivoGeneral AS `efectivoGeneral`,d.ncGeneral AS `ncGeneral`,d.chequeGeneral AS `chequeGeneral`,d.targetaGeneral AS `targetaGeneral`,d.totalGeneral AS `totalGeneral`,d.descuentoGeneral AS `descuentoGeneral`,d.ivaGeneral AS `ivaGeneral`,d.subtotalGeneral AS `subtotalGeneral`,d.comprobante AS `comprobante`,d.cotizacion AS `cotizacion`,d.anulada AS `anulada`,d.anula AS `anula`,d.credito AS `credito`,d.cxc AS `cxc`,d.usuario AS `usuario`,d.rtIva AS `rtIva`,d.rtIca AS `rtIca`,d.rtFuente AS `rtFuente`,d.otros AS `otros`,d.observacion AS `observacion`,CAST(0 AS UNSIGNED) AS `anulada1`,'' AS `anula1`,CAST(0 AS UNSIGNED) AS `credito1`,'' AS `cxc1`,'' AS `usuario1`,CAST(d.fechaAlerta AS DATETIME) AS `fechaAlerta`,d.terminal AS `terminal`,COALESCE(dd.estadoGeneralLinea, d.estadoGeneral) AS `estadoGeneral`,COALESCE(dd.estado2Linea, d.estado2) AS `estado2`,d.devuelta AS `devuelta`,d.factura AS `factura`,d.resolucion AS `resolucion`,CAST(d.fechaAnulacion AS DATETIME) AS `fechaAnulacion`,d.cuadreAnulacion AS `cuadreAnulacion`,d.usuarioAnula AS `usuarioAnula`,d.copago AS `copago`,d.placa AS `placa`,d.garantia AS `garantia`,d.diasGarantia AS `diasGarantia`,dd.rango AS `rango`,d.terminos AS `terminos`,d.notaAnulacion AS `notaAnulacion`,d.conseMesa AS `conseMesa`,dd.producto AS `producto`,dd.lista AS `lista`,dd.cantidad AS `cantidad`,dd.descuento AS `descuento`,dd.total AS `total`,dd.iva AS `iva`,dd.subtotal AS `subtotal`,'' AS `NC`,dd.utilidad AS `utilidad`,'' AS `concepto`,dd.porcDescuento AS `porcDescuento`,dd.descripcion AS `descripcion`,dd.plu AS `plu`,dd.cant2 AS `cant2`,dd.estado AS `estado`,dd.porcIva AS `porcIva`,dd.tercero AS `tercero`,dd.utilidad1 AS `utilidad1`,dd.preparacion AS `preparacion`,if((d.vehiculoTipo = NULL),`bdplacas`.`tipo`,d.vehiculoTipo) AS `tipo`,`bdplacas`.`modelo` AS `modelo`,`bdplacas`.`marca` AS `marca`,`bdplacas`.`color` AS `color`,`bdterceros`.`nombre` AS `nombre`,`bdterceros`.`id` AS `Id`,`bdterceros`.`direccion` AS `direccion`,`bdterceros`.`telefono` AS `telefono`,`bdterceros`.`celular` AS `celular`,`bdoservicio`.`placa` AS `placaReal`,`bdoservicio`.`problema` AS `problema`,`bdoservicio`.`km` AS `km`,`bdoservicio`.`fechaCompra` AS `fechaCompra`,`bdoservicio`.`numeroChasis` AS `chasis`,`bdoservicio`.`numeroMotor` AS `motor` from (((`bdDocumentos` `d` join `bdDocumentosDetalle` `dd` on(((`dd`.`idDocumento` = `d`.`idDocumento`) and d.tipoDocumento IN ('orden')))) left join (`bdoservicio` left join `bdplacas` on((`bdoservicio`.`placa` = `bdplacas`.`placa`))) on((d.idFactura = `bdoservicio`.`id`))) left join `bdterceros` on((d.cliente = `bdterceros`.`idSistema`)));

CREATE OR REPLACE VIEW `oservicio` AS select d.idFactura AS `idFactura`,d.cliente AS `cliente`,`bdterceros`.`nombre` AS `nombre`,`bdterceros`.`direccion` AS `direccion`,`bdterceros`.`telefono` AS `telefono`,d.vendedor AS `vendedor`,TIMESTAMP(d.fechaFactura, COALESCE(d.horaFactura, '00:00:00')) AS `fechaFactura`,CAST(d.fechaVencimiento AS DATETIME) AS `fechaVencimiento`,d.subtotalGeneral AS `sub`,d.descuentoGeneral AS `descuentos`,d.ivaGeneral AS `IVAA`,d.efectivoGeneral AS `efectivo`,'' AS `nc`,d.chequeGeneral AS `cheque`,d.targetaGeneral AS `targeta`,dd.producto AS `producto`,dd.lista AS `lista`,dd.cantidad AS `cantidad`,dd.subtotal AS `subtotal`,dd.descuento AS `descuento`,dd.iva AS `iva`,dd.total AS `total`,`bdcxc`.`plazo` AS `plazo`,`bdcxc`.`tipo` AS `tipo`,d.anulada AS `anulada`,d.rtFuente AS `rtFuente`,d.rtIva AS `rtIva`,dd.cant2 AS `cant2`,left(d.observacion,1000) AS `observacion2`,`bdgrupo`.`nombre` AS `grupo`,left(`bdproductos`.`Descripcion`,1000) AS `descripcion2`,`bdproductos`.`ubicacion1` AS `ubicacion1`,d.devuelta AS `devuelta`,(dd.total - dd.utilidad) AS `utilidad2`,d.factura AS `factura`,d.terminal AS `terminal`,`bdproductos`.`IVA` AS `IVAProducto`,if((d.credito = 1),'CREDITO','CONTADO') AS `credito2`,d.placa AS `placa`,`bdplacas`.`tipo` AS `tipoVehiculo`,`bdplacas`.`modelo` AS `modelo`,`bdplacas`.`marca` AS `marca`,`bdplacas`.`color` AS `color`,`bdplacas`.`chasis` AS `chasis`,`bdplacas`.`motor` AS `motor`,`bdplacas`.`fechaCompra` AS `fechaCompra`,d.totalGeneral AS `totalGeneral`,`bdmaestraimpresiones`.`anexoOrdenServicio` AS `anexoOrdenServicio`,d.notaAnulacion AS `notaAnulacion`,d.terminos AS `terminos`,dd.rango AS `rango`,d.diasGarantia AS `diasGarantia`,dd.porcIva AS `porcIva`,d.usuario AS `usuario`,COALESCE(dd.estado2Linea, d.estado2) AS `estado2`,d.bodega AS `bodega`,`bdterceros`.`id` AS `idCliente` from ((((((`bdDocumentos` `d` join `bdDocumentosDetalle` `dd` on(((`dd`.`idDocumento` = `d`.`idDocumento`) and d.tipoDocumento IN ('orden')))) left join `bdcxc` on((d.idFactura = `bdcxc`.`factura`))) left join `bdplacas` on((`bdplacas`.`placa` = d.placa))) left join `bdmaestraimpresiones` on((`bdmaestraimpresiones`.`Id` = dd.plu))) left join `bdterceros` on((d.cliente = `bdterceros`.`idSistema`))) left join (`bdproductos` left join `bdgrupo` on((`bdproductos`.`grupo` = `bdgrupo`.`codigo`))) on((dd.producto = `bdproductos`.`idSistema`))) where ((d.idFactura like 'OSERV-%') and ((`bdcxc`.`tipo` = 'FACT') or isnull(`bdcxc`.`tipo`)));

CREATE OR REPLACE VIEW `pedidos` AS select d.idFactura AS `idFactura`,cast(substr(d.idFactura,8,100) as signed) AS `ordenId`,d.cliente AS `cliente`,d.vendedor AS `vendedor`,d.red AS `red`,TIMESTAMP(d.fechaFactura, COALESCE(d.horaFactura, '00:00:00')) AS `fechaFactura`,CAST(d.fechaVencimiento AS DATETIME) AS `fechaVencimiento`,d.efectivoGeneral AS `efectivoGeneral`,d.ncGeneral AS `ncGeneral`,d.chequeGeneral AS `chequeGeneral`,d.targetaGeneral AS `targetaGeneral`,d.totalGeneral AS `totalGeneral`,d.descuentoGeneral AS `descuentoGeneral`,d.ivaGeneral AS `ivaGeneral`,d.subtotalGeneral AS `subtotalGeneral`,d.comprobante AS `comprobante`,d.cotizacion AS `cotizacion`,d.anulada AS `anulada`,d.anula AS `anula`,d.credito AS `credito`,d.cxc AS `cxc`,d.usuario AS `usuario`,d.rtIva AS `rtIva`,d.rtIca AS `rtIca`,d.rtFuente AS `rtFuente`,d.otros AS `otros`,d.observacion AS `observacion`,CAST(0 AS UNSIGNED) AS `anulada1`,'' AS `anula1`,CAST(0 AS UNSIGNED) AS `credito1`,'' AS `cxc1`,'' AS `usuario1`,CAST(d.fechaAlerta AS DATETIME) AS `fechaAlerta`,d.terminal AS `terminal`,d.estadoGeneral AS `estadoGeneral`,d.estado2 AS `estado2`,d.devuelta AS `devuelta`,d.factura AS `factura`,d.resolucion AS `resolucion`,CAST(d.fechaAnulacion AS DATETIME) AS `fechaAnulacion`,d.cuadreAnulacion AS `cuadreAnulacion`,d.usuarioAnula AS `usuarioAnula`,d.copago AS `copago`,d.placa AS `placa`,d.garantia AS `garantia`,d.diasGarantia AS `diasGarantia`,dd.rango AS `rango`,d.terminos AS `terminos`,d.notaAnulacion AS `notaAnulacion`,d.conseMesa AS `conseMesa`,dd.producto AS `producto`,dd.lista AS `lista`,dd.cantidad AS `cantidad`,dd.descuento AS `descuento`,dd.total AS `total`,dd.idProd AS `idProd`,dd.iva AS `iva`,dd.subtotal AS `subtotal`,'' AS `NC`,dd.utilidad AS `utilidad`,'' AS `concepto`,dd.porcDescuento AS `porcDescuento`,dd.descripcion AS `descripcion`,dd.plu AS `plu`,dd.cant2 AS `cant2`,dd.estado AS `estado`,dd.porcIva AS `porcIva`,dd.tercero AS `tercero`,dd.utilidad1 AS `utilidad1`,d.garantia AS `nombre`,d.bodega AS `bodega`,`bdterceros`.`telefono` AS `telefono`,`bdterceros`.`direccion` AS `direccion`,`bdterceros`.`id` AS `IdTercero`,d.factura AS `id2`,`bdproductos`.`ubicacion1` AS `ubicacion1`,`bdproductos`.`referencia` AS `referencia`,`bdproductos`.`Codigo` AS `Codigo`,dd.preparacion AS `preparacion` from (((`bdDocumentos` `d` join `bdDocumentosDetalle` `dd` on(((`dd`.`idDocumento` = `d`.`idDocumento`) and d.tipoDocumento IN ('pedido')))) join `bdproductos` on((dd.producto = `bdproductos`.`idSistema`))) left join `bdterceros` on((d.cliente = `bdterceros`.`idSistema`))) order by cast(substr(d.idFactura,8,100) as signed);

CREATE OR REPLACE VIEW `reporden` AS (select d.factura AS `factura`,substr(d.idFactura,7.2) AS `documento`,substr(d.factura,7.2) AS `documento2`,dd.producto AS `producto`,dd.descripcion AS `Descripcion`,dd.cant2 AS `cantidad`,dd.lista AS `lista`,dd.subtotal AS `subtotal`,dd.iva AS `iva`,dd.total AS `total`,TIMESTAMP(d.fechaFactura, COALESCE(d.horaFactura, '00:00:00')) AS `fechaFactura`,d.cliente AS `cliente`,`bdproductos`.`Proveedor` AS `Proveedor`,d.vendedor AS `vendedor`,d.anulada AS `anulada`,d.credito AS `credito`,d.descuentoGeneral AS `descuentos`,(dd.total - (dd.utilidad * dd.cantidad)) AS `utilidad`,(dd.total - (`bdultimoponderado`.`nuevoPonderado` * dd.cantidad)) AS `utilidad2`,d.terminal AS `terminal`,d.targetaGeneral AS `targeta`,dd.descuento AS `descuento`,`bdoservicio`.`placa` AS `d5`,COALESCE(dd.estadoGeneralLinea, d.estadoGeneral) AS `estado`,d.garantia AS `garantia`,d.diasGarantia AS `diasGarantia`,dd.rango AS `rango`,d.terminos AS `terminos`,d.notaAnulacion AS `notaAnulacion`,`bdproductos`.`Codigo` AS `Codigo` from ((((`bdDocumentos` `d` join `bdDocumentosDetalle` `dd` on(((`dd`.`idDocumento` = `d`.`idDocumento`) and d.tipoDocumento IN ('orden')))) left join `bdoservicio` on((`bdoservicio`.`id` = d.idFactura))) left join `bdproductos` on((dd.producto = `bdproductos`.`Codigo`))) left join `bdultimoponderado` on((`bdproductos`.`Codigo` = `bdultimoponderado`.`producto`))) where (d.anulada = 0));

CREATE OR REPLACE VIEW `repplantillas` AS select d.idFactura AS `idFactura`,cast(substr(d.idFactura,8,100) as signed) AS `documento`,dd.producto AS `producto`,dd.descripcion AS `Descripcion`,dd.cant2 AS `cantidad`,dd.lista AS `lista`,dd.subtotal AS `subtotal`,dd.iva AS `iva`,dd.total AS `total`,d.fechaFactura AS `fechaFactura`,d.cliente AS `cliente`,`bdproductos`.`Proveedor` AS `Proveedor`,d.vendedor AS `vendedor`,d.anulada AS `anulada`,d.credito AS `credito`,d.descuentoGeneral AS `descuentoGeneral`,(dd.total - (dd.utilidad * dd.cantidad)) AS `utilidad`,(dd.total - (`bdultimoponderado`.`nuevoPonderado` * dd.cantidad)) AS `utilidad2`,d.terminal AS `terminal`,d.targetaGeneral AS `targeta`,dd.descuento AS `descuentos`,`bdproductos`.`Codigo` AS `Codigo`,`bdterceros`.`id` AS `idTercero`,d.ivaGeneral AS `ivaGeneral`,d.totalGeneral AS `totalGeneral`,d.subtotalGeneral AS `subtotalGeneral`,`bdterceros`.`nombre` AS `nombre`,d.observacion AS `observacion`,d.rtFuente AS `rtFuente`,d.rtIva AS `rtIva`,d.otros AS `otros`,dd.porcIva AS `porcIva`,dd.descuento AS `descuento` from ((((`bdDocumentos` `d` join `bdDocumentosDetalle` `dd` on(((`dd`.`idDocumento` = `d`.`idDocumento`) and d.tipoDocumento IN ('cuentaCobro')))) left join `bdultimoponderado` on((`bdultimoponderado`.`producto` = dd.producto))) left join `bdproductos` on((dd.producto = `bdproductos`.`idSistema`))) left join `bdterceros` on((d.cliente = `bdterceros`.`idSistema`)));

CREATE OR REPLACE VIEW `repsepares` AS select d.factura AS `factura`,cast(substr(d.idFactura,8.2) as signed) AS `documento`,cast(substr(d.factura,6.2) as signed) AS `documento2`,dd.producto AS `producto`,dd.descripcion AS `Descripcion`,dd.cant2 AS `cantidad`,dd.lista AS `lista`,dd.subtotal AS `subtotal`,dd.iva AS `iva`,dd.total AS `total`,d.fechaFactura AS `fechaFactura`,d.cliente AS `cliente`,`bdproductos`.`Proveedor` AS `Proveedor`,d.vendedor AS `vendedor`,d.anulada AS `anulada`,d.credito AS `credito`,d.descuentoGeneral AS `descuentoGeneral`,(dd.total - (dd.utilidad * dd.cantidad)) AS `utilidad`,(dd.total - (`bdultimoponderado`.`nuevoPonderado` * dd.cantidad)) AS `utilidad2`,d.terminal AS `terminal`,d.targetaGeneral AS `targeta`,dd.descuento AS `descuentos`,`bdproductos`.`Codigo` AS `Codigo`,`bdterceros`.`id` AS `idTercero`,d.ivaGeneral AS `ivaGeneral`,d.totalGeneral AS `totalGeneral`,d.subtotalGeneral AS `subtotalGeneral`,`bdterceros`.`nombre` AS `nombre`,d.observacion AS `observacion`,d.rtFuente AS `rtFuente`,d.rtIva AS `rtIva`,d.otros AS `otros`,dd.porcIva AS `porcIva`,dd.descuento AS `descuento` from ((((`bdDocumentos` `d` join `bdDocumentosDetalle` `dd` on(((`dd`.`idDocumento` = `d`.`idDocumento`) and d.tipoDocumento IN ('separe')))) left join `bdultimoponderado` on((`bdultimoponderado`.`producto` = dd.producto))) left join `bdproductos` on((dd.producto = `bdproductos`.`idSistema`))) left join `bdterceros` on((d.cliente = `bdterceros`.`idSistema`)));


CREATE OR REPLACE VIEW `bdclickppal2` AS (
SELECT `bdordenservicio`.`id` AS `orden`,`bdordenservicio`.`codigoIps` AS `codigoIps`,`bdordenservicio`.`autorizacion` AS `autorizacion`,`bdterceros`.`id` AS `Id`,`bdordenservicio`.`fecha` AS `fecha`,`bdordenservicio`.`actoQuirurgico` AS `actoQuirurgico`,`bdordenservicio`.`cup` AS `cup`,`bdordenservicio`.`cantidad` AS `cantidad`,`bdordenservicio`.`cantidadRealizada` AS `cantidadRealizada`,`bdordenservicio`.`copago` AS `copago`,`bdordenservicio`.`cie` AS `cie`,`bdterceros`.`telefono` AS `telefono`,`bdterceros`.`celular` AS `celular`,`bdterceros`.`nacimiento` AS `nacimiento`,`bdordenservicio`.`valor` AS `valor`,`bdterceros`.`nombre` AS `nombre`,`bdordenservicio`.`cup` AS `codigoTarifa`,`bdproductos`.`Descripcion` AS `procedimientoAutorizado`,`bdterceros`.`eps` AS `eps`,`bdterceros`.`ciudad` AS `ciudad`,`bdterceros`.`departamento` AS `departamento`,`bdterceros`.`pNombre` AS `pNombre`,`bdterceros`.`sNombre` AS `sNombre`,`bdterceros`.`pApellido` AS `pApellido`,`bdterceros`.`sApellido` AS `sApellido`,IF((`bdterceros`.`tipo` LIKE '%dula de extranjer%'),'CE',IF((`bdterceros`.`tipo` LIKE '%dula%'),'CC',IF((`bdterceros`.`tipo` = 'Pasaporte'),'PA',IF((`bdterceros`.`tipo` = 'Registro civil'),'RC',IF((`bdterceros`.`tipo` = 'Tarjeta de identidad'),'TI',IF((`bdterceros`.`tipo` LIKE '%Adulto sin identificaci%'),'AS',IF((`bdterceros`.`tipo` LIKE '%Menor sin identificaci%'),'MS',IF((`bdterceros`.`tipo` LIKE '%nico de identificaci%'),'NU','')))))))) AS `tipoIdentidad`,`bdterceros`.`tipo` AS `tipoIdentidadDesc`,IF((`bdepsprecargados`.`codigoProteccionSocial` = '000001'),'',`bdepsprecargados`.`codigoProteccionSocial`) AS `codigoEntidadAdministradora`,IF((`bdterceros`.`regimen` = 'Contributivo'),'1',IF((`bdterceros`.`regimen` = 'Subsidiado'),'2',IF((`bdterceros`.`regimen` = 'Vinculado'),'3',IF((`bdterceros`.`regimen` = 'Particular'),'4',IF((`bdterceros`.`regimen` = 'Otro'),'5',''))))) AS `TipoUsuario`,`bdterceros`.`regimen` AS `tipoUsuarioDesc`,CAST(((CAST(NOW() AS DATE) - `bdterceros`.`nacimiento`) / 365) AS SIGNED) AS `Edad`,IF(((YEAR(CAST(NOW() AS DATE)) - YEAR(`bdterceros`.`nacimiento`)) = 0),IF(((MONTH(CAST(NOW() AS DATE)) - MONTH(`bdterceros`.`nacimiento`)) = 0),'3','2'),'1') AS `unidadMedidaEdad`,IF((`bdterceros`.`sexo` = 'Masculino'),'M','F') AS `sexo`,IF((`bdterceros`.`zona` = 'Urbana'),'U','R') AS `zona`,`bdterceros`.`zona` AS `zonaDesc`,`bdmaestra`.`codPrestadorServicio` AS `codPrestadorServicio`,`bdmaestra`.`nombreEmpresa` AS `razonSocial`,`bdmaestra`.`tipoIdPrestadorServicio` AS `tipoPrestadorServicio`,`bdmaestra`.`nitEmpresa` AS `IdPrestadorServicio`,IF((`bdepsprecargados`.`codigoProteccionSocial` = '000001'),'',`bdepsprecargados`.`nombre`) AS `nombreEntidadAdministradora`,`bdordenservicio`.`poliza` AS `nroPoliza`,`bdordenservicio`.`comision` AS `valorComision`,`bdordenservicio`.`descuento` AS `valorDescuento`,`bdepsprecargados`.`contrato` AS `numeroContrato`,`bdordenservicio`.`tipo` AS `tipoOrden`,IF((`bdordenservicio`.`concepto` = 'Consultas'),'01',IF((`bdordenservicio`.`concepto` LIKE 'Procedimientos de diagn%'),'02',IF((`bdordenservicio`.`concepto` LIKE '%uticos no quir%'),'03',IF((`bdordenservicio`.`concepto` LIKE 'Procedimientos terap%'),'04',IF((`bdordenservicio`.`concepto` LIKE 'Procedimientos de promoci%'),'05',IF((`bdordenservicio`.`concepto` = 'Estancias'),'06',IF((`bdordenservicio`.`concepto` = 'Honorarios'),'07',IF((`bdordenservicio`.`concepto` = 'Derechos de sala'),'08',IF((`bdordenservicio`.`concepto` = 'Materiales e insumos'),'09',IF((`bdordenservicio`.`concepto` = 'Banco de sangre'),'10',IF((`bdordenservicio`.`concepto` LIKE '%tesis y%'),'11',IF((`bdordenservicio`.`concepto` = 'Medicamentos POS'),'12',IF((`bdordenservicio`.`concepto` = 'Medicamentos no POS'),'13',IF((`bdordenservicio`.`concepto` = 'Traslado de pacientes'),'14','')))))))))))))) AS `codigoConcepto`,`bdordenservicio`.`concepto` AS `conceptoDesc`,IF((`bdordenservicio`.`codigoConsulta` = 'Consulta de Medicina General electiva'),'890201',IF((`bdordenservicio`.`codigoConsulta` = 'Consulta de Medicina Especializada electiva'),'890202',IF((`bdordenservicio`.`codigoConsulta` = 'Consulta de Medicina General de control'),'890301',IF((`bdordenservicio`.`codigoConsulta` = 'Consulta de Medicina Especializada de control'),'890302',IF((`bdordenservicio`.`codigoConsulta` = 'Consulta odontologica especializada de control'),'890304',IF((`bdordenservicio`.`codigoConsulta` = 'Consulta odontologica general de control'),'890303',IF((`bdordenservicio`.`codigoConsulta` = 'Consulta de odontologia general'),'890703',IF((`bdordenservicio`.`codigoConsulta` = 'Consulta de odontologia especializada'),'890704','')))))))) AS `codConsulta`,`bdordenservicio`.`codigoConsulta` AS `codigoConsultaDesc`,IF((`bdordenservicio`.`tipo` = 'procedimiento'),IF((`bdordenservicio`.`finalidad` LIKE 'Diagn%'),'1',IF((`bdordenservicio`.`finalidad` LIKE 'Terap%'),'2',IF((`bdordenservicio`.`finalidad` LIKE 'Protecci%'),'3',IF((`bdordenservicio`.`finalidad` LIKE '%n temprana de enfermedad general'),'4',IF((`bdordenservicio`.`finalidad` LIKE '%n temprana de enfermedad profesional'),'5',''))))),IF((`bdordenservicio`.`finalidad` LIKE '%n del parto (puerperio)'),'01',IF((`bdordenservicio`.`finalidad` LIKE '%n del reci%'),'02',IF((`bdordenservicio`.`finalidad` LIKE '%n en planificaci%'),'03',IF((`bdordenservicio`.`finalidad` LIKE 'No aplica'),'10',IF((`bdordenservicio`.`finalidad` LIKE 'Detecci%n de alteraci%n del%'),'05',IF((`bdordenservicio`.`finalidad` LIKE 'Detecci%n de alteraciones del embarazo'),'06',IF((`bdordenservicio`.`finalidad` LIKE 'Detecci%n de alteraciones del adulto'),'07',IF((`bdordenservicio`.`finalidad` LIKE 'Detecci%n de alteraciones de agudeza visual'),'08',IF((`bdordenservicio`.`finalidad` LIKE 'Detecci%n de enfermedad profesional'),'09',IF((`bdordenservicio`.`finalidad` LIKE '%n de alteraciones de%'),'04',''))))))))))) AS `finalidadd`,`bdordenservicio`.`finalidad` AS `finalidadDesc`,IF((`bdordenservicio`.`causa` = 'Accidente de trabajo'),'01',IF((`bdordenservicio`.`causa` LIKE 'Accidente de tr%nsito'),'02',IF((`bdordenservicio`.`causa` LIKE 'Accidente r%bico'),'03',IF((`bdordenservicio`.`causa` LIKE 'Accidente of%dico'),'04',IF((`bdordenservicio`.`causa` = 'Otro tipo de accidente'),'05',IF((`bdordenservicio`.`causa` LIKE 'Evento catastr%fico'),'06',IF((`bdordenservicio`.`causa` LIKE 'Lesi%n por agresi%n'),'07',IF((`bdordenservicio`.`causa` LIKE 'Lesi%n auto infligida'),'08',IF((`bdordenservicio`.`causa` LIKE 'Sospecha de maltrato f%sico'),'09',IF((`bdordenservicio`.`causa` = 'Sospecha de abuso sexual'),'10',IF((`bdordenservicio`.`causa` = 'Sospecha de violencia sexual'),'11',IF((`bdordenservicio`.`causa` = 'Sospecha de maltrato emocional'),'12',IF((`bdordenservicio`.`causa` = 'Enfermedad general'),'13',IF((`bdordenservicio`.`causa` = 'Enfermedad profesional'),'14','15')))))))))))))) AS `causaExterna`,`bdordenservicio`.`causa` AS `causaExternaDesc`,`bdordenservicio`.`cuota` AS `valorCuotaModeradora`,IF((`bdordenservicio`.`ambito` = 'Ambulatorio'),'1',IF((`bdordenservicio`.`ambito` = 'Hospitalario'),'2','3')) AS `ambitoProcedimiento`,`bdordenservicio`.`ambito` AS `ambitoProcedimientoDesc`,IF((`bdordenservicio`.`personal` LIKE 'M%dico (A) especialista'),'1',IF((`bdordenservicio`.`personal` LIKE 'M%dico (a) general'),'2',IF((`bdordenservicio`.`personal` = 'Enfermera (o)'),'3',IF((`bdordenservicio`.`personal` LIKE 'Auxiliar de enfermer%a'),'4',IF((`bdordenservicio`.`personal` = 'Otro'),'5',''))))) AS `personalAtiende`,`bdordenservicio`.`personal` AS `personalDesc`,`bdordenservicio`.`Estado` AS `Estado`,`bdregiones`.`codigoDepartamento` AS `codigoDepartamento`,`bdregiones`.`codigoMunicipio` AS `codigoMunicipio`,`bdordenservicio`.`estado2` AS `estado2`,`bdordenservicio`.`orden` AS `facturaNumero` FROM (`bdmaestra` JOIN ((((`bdordenservicio` JOIN `bdterceros` ON((`bdordenservicio`.`paciente` = `bdterceros`.`idSistema`))) JOIN `bdepsprecargados` ON((`bdterceros`.`eps` = `bdepsprecargados`.`Id`))) JOIN `bdproductos` ON((`bdordenservicio`.`acobrar` = `bdproductos`.`idSistema`))) JOIN `bdregiones` ON(((`bdterceros`.`departamento` = `bdregiones`.`nombreDepartamento`) AND (`bdterceros`.`ciudad` = `bdregiones`.`nombreMunicipio`))))));

CREATE OR REPLACE VIEW `bdclickppal` AS (
SELECT
  `bdclickppal2`.`orden`                       AS `orden`,
  `bdclickppal2`.`codigoIps`                   AS `codigoIps`,
  `bdclickppal2`.`autorizacion`                AS `autorizacion`,
  `bdclickppal2`.`Id`                          AS `Id`,
  `bdclickppal2`.`fecha`                       AS `fecha`,
  `bdclickppal2`.`actoQuirurgico`              AS `actoQuirurgico`,
  `bdclickppal2`.`cup`                         AS `cup`,
  `bdclickppal2`.`cantidad`                    AS `cantidad`,
  `bdclickppal2`.`cantidadRealizada`           AS `cantidadRealizada`,
  `bdclickppal2`.`copago`                      AS `copago`,
  `bdclickppal2`.`cie`                         AS `cie`,
  `bdclickppal2`.`telefono`                    AS `telefono`,
  `bdclickppal2`.`celular`                     AS `celular`,
  `bdclickppal2`.`nacimiento`                  AS `nacimiento`,
  `bdclickppal2`.`valor`                       AS `valor`,
  `bdclickppal2`.`nombre`                      AS `nombre`,
  `bdclickppal2`.`codigoTarifa`                AS `codigoTarifa`,
  `bdclickppal2`.`procedimientoAutorizado`     AS `procedimientoAutorizado`,
  `bdclickppal2`.`eps`                         AS `eps`,
  `bdclickppal2`.`ciudad`                      AS `ciudad`,
  `bdclickppal2`.`departamento`                AS `departamento`,
  `bdclickppal2`.`pNombre`                     AS `pNombre`,
  `bdclickppal2`.`sNombre`                     AS `sNombre`,
  `bdclickppal2`.`pApellido`                   AS `pApellido`,
  `bdclickppal2`.`sApellido`                   AS `sApellido`,
  `bdclickppal2`.`tipoIdentidad`               AS `tipoIdentidad`,
  `bdclickppal2`.`tipoIdentidadDesc`           AS `tipoIdentidadDesc`,
  `bdclickppal2`.`codigoEntidadAdministradora` AS `codigoEntidadAdministradora`,
  `bdclickppal2`.`TipoUsuario`                 AS `TipoUsuario`,
  `bdclickppal2`.`tipoUsuarioDesc`             AS `tipoUsuarioDesc`,
  `bdclickppal2`.`Edad`                        AS `Edad`,
  `bdclickppal2`.`unidadMedidaEdad`            AS `unidadMedidaEdad`,
  `bdclickppal2`.`sexo`                        AS `sexo`,
  `bdclickppal2`.`zona`                        AS `zona`,
  `bdclickppal2`.`zonaDesc`                    AS `zonaDesc`,
  `bdclickppal2`.`codPrestadorServicio`        AS `codPrestadorServicio`,
  `bdclickppal2`.`razonSocial`                 AS `razonSocial`,
  `bdclickppal2`.`tipoPrestadorServicio`       AS `tipoPrestadorServicio`,
  `bdclickppal2`.`IdPrestadorServicio`         AS `IdPrestadorServicio`,
  `bdclickppal2`.`nombreEntidadAdministradora` AS `nombreEntidadAdministradora`,
  `bdclickppal2`.`nroPoliza`                   AS `nroPoliza`,
  `bdclickppal2`.`valorComision`               AS `valorComision`,
  `bdclickppal2`.`valorDescuento`              AS `valorDescuento`,
  `bdclickppal2`.`numeroContrato`              AS `numeroContrato`,
  `bdclickppal2`.`tipoOrden`                   AS `tipoOrden`,
  `bdclickppal2`.`codigoConcepto`              AS `codigoConcepto`,
  `bdclickppal2`.`conceptoDesc`                AS `conceptoDesc`,
  `bdclickppal2`.`codConsulta`                 AS `codConsulta`,
  `bdclickppal2`.`codigoConsultaDesc`          AS `codigoConsultaDesc`,
  `bdclickppal2`.`finalidadd`                  AS `finalidadd`,
  `bdclickppal2`.`finalidadDesc`               AS `finalidadDesc`,
  `bdclickppal2`.`causaExterna`                AS `causaExterna`,
  `bdclickppal2`.`causaExternaDesc`            AS `causaExternaDesc`,
  IF(ISNULL(`bdconsultaclinica`.`tipoDiagnostico`),'1',IF((`bdconsultaclinica`.`tipoDiagnostico` = 'Impresi%'),'1',IF((`bdconsultaclinica`.`tipoDiagnostico` = 'Confirmado nuevo'),2,3))) AS `tipoDiagnosticoPrincipal`,
  IF((`bdclickppal2`.`tipoOrden` = 'procedimiento'),'',`bdconsultaclinica`.`cie2`) AS `codDiagnosticoReacionado1`,
  IF((`bdclickppal2`.`tipoOrden` = 'procedimiento'),'',`bdconsultaclinica`.`cie3`) AS `codDiagnosticoReacionado2`,
  IF((`bdclickppal2`.`tipoOrden` = 'procedimiento'),'',`bdconsultaclinica`.`cie4`) AS `codDiagnosticoReacionado3`,
  `bdclickppal2`.`valorCuotaModeradora`        AS `valorCuotaModeradora`,
  `bdclickppal2`.`ambitoProcedimiento`         AS `ambitoProcedimiento`,
  `bdclickppal2`.`ambitoProcedimientoDesc`     AS `ambitoProcedimientoDesc`,
  `bdclickppal2`.`personalAtiende`             AS `personalAtiende`,
  `bdclickppal2`.`personalDesc`                AS `personalDesc`,
  IF((`bdclickppal2`.`tipoOrden` = 'procedimiento'),`bdconsultaclinica`.`cie2`,'') AS `DiagnosticoRelacionado`,
  IF((`bdclickppal2`.`tipoOrden` = 'procedimiento'),`bdconsultaclinica`.`cie4`,'') AS `complicaciones`,
  `bdclickppal2`.`Estado`                      AS `Estado`,
  `bdclickppal2`.`codigoDepartamento`          AS `codigoDepartamento`,
  `bdclickppal2`.`codigoMunicipio`             AS `codigoMunicipio`,
  `bdclickppal2`.`estado2`                     AS `estado2`,
  `bdclickppal2`.`facturaNumero`               AS `facturaNumero`
FROM (`bdclickppal2`
   LEFT JOIN `bdconsultaclinica`
     ON ((`bdconsultaclinica`.`orden` = `bdclickppal2`.`orden`))));

CREATE OR REPLACE VIEW `ripslaboratorio1` AS (select `ordenof`.`id` AS `orden`,`ordenof`.`codigoIps` AS `codigoIps`,`ordenof`.`autorizacion` AS `autorizacion`,`bdterceros`.`id` AS `Id`,`ordenof`.`fecha` AS `fecha`,`ordenof`.`actoQuirurgico` AS `actoQuirurgico`,`ordenof`.`cup` AS `cup`,`ordenof`.`cantidad` AS `cantidad`,`ordenof`.`cantidadRealizada` AS `cantidadRealizada`,`ordenof`.`copago` AS `copago`,`ordenof`.`cie` AS `cie`,`bdterceros`.`telefono` AS `telefono`,`bdterceros`.`celular` AS `celular`,`bdterceros`.`nacimiento` AS `nacimiento`,`ordenof`.`valor` AS `valor`,`bdterceros`.`nombre` AS `nombre`,`ordenof`.`cup` AS `codigoTarifa`,`bdproductos`.`Descripcion` AS `procedimientoAutorizado`,`bdterceros`.`eps` AS `eps`,`bdterceros`.`ciudad` AS `ciudad`,`bdterceros`.`departamento` AS `departamento`,`bdterceros`.`pNombre` AS `pNombre`,`bdterceros`.`sNombre` AS `sNombre`,`bdterceros`.`pApellido` AS `pApellido`,`bdterceros`.`sApellido` AS `sApellido`,if((`bdterceros`.`tipo` like '*dula de extranjer*'),'CE',if((`bdterceros`.`tipo` like '*dula*'),'CC',if((`bdterceros`.`tipo` = 'Pasaporte'),'PA',if((`bdterceros`.`tipo` = 'Registro civil'),'RC',if((`bdterceros`.`tipo` = 'Tarjeta de identidad'),'TI',if((`bdterceros`.`tipo` like '*Adulto sin identificaci*'),'AS',if((`bdterceros`.`tipo` like '*Menor sin identificaci*'),'MS',if((`bdterceros`.`tipo` like '*nico de identificaci*'),'NU','')))))))) AS `tipoIdentidad`,`bdterceros`.`tipo` AS `tipoIdentidadDesc`,if((`bdepsprecargados`.`codigoProteccionSocial` = '000001'),'',`bdepsprecargados`.`codigoProteccionSocial`) AS `codigoEntidadAdministradora`,if((`bdterceros`.`regimen` = 'Contributivo'),'1',if((`bdterceros`.`regimen` = 'Subsidiado'),'2',if((`bdterceros`.`regimen` = 'Vinculado'),'3',if((`bdterceros`.`regimen` = 'Particular'),'4',if((`bdterceros`.`regimen` = 'Otro'),'5',''))))) AS `TipoUsuario`,`bdterceros`.`regimen` AS `tipoUsuarioDesc`,cast(((cast(now() as date) - `bdterceros`.`nacimiento`) / 365) as signed) AS `Edad`,if(((year(cast(now() as date)) - year(`bdterceros`.`nacimiento`)) = 0),if(((month(cast(now() as date)) - month(`bdterceros`.`nacimiento`)) = 0),'3','2'),'1') AS `unidadMedidaEdad`,if((`bdterceros`.`sexo` = 'Masculino'),'M','F') AS `sexo`,if((`bdterceros`.`zona` = 'Urbana'),'U','R') AS `zona`,`bdterceros`.`zona` AS `zonaDesc`,`bdmaestra`.`codPrestadorServicio` AS `codPrestadorServicio`,`bdmaestra`.`nombreEmpresa` AS `razonSocial`,`bdmaestra`.`tipoIdPrestadorServicio` AS `tipoPrestadorServicio`,`bdmaestra`.`nitEmpresa` AS `IdPrestadorServicio`,if((`bdepsprecargados`.`codigoProteccionSocial` = '000001'),'',`bdepsprecargados`.`nombre`) AS `nombreEntidadAdministradora`,`ordenof`.`poliza` AS `nroPoliza`,`ordenof`.`comision` AS `valorComision`,`ordenof`.`descuento` AS `valorDescuento`,`bdepsprecargados`.`contrato` AS `numeroContrato`,`ordenof`.`tipo` AS `tipoOrden`,if((`ordenof`.`concepto` = 'Consultas'),'01',if((`ordenof`.`concepto` like 'Procedimientos de diagn*'),'02',if((`ordenof`.`concepto` like '*uticos no quir*'),'03',if((`ordenof`.`concepto` like 'Procedimientos terap*'),'04',if((`ordenof`.`concepto` like 'Procedimientos de promoci*'),'05',if((`ordenof`.`concepto` = 'Estancias'),'06',if((`ordenof`.`concepto` = 'Honorarios'),'07',if((`ordenof`.`concepto` = 'Derechos de sala'),'08',if((`ordenof`.`concepto` = 'Materiales e insumos'),'09',if((`ordenof`.`concepto` = 'Banco de sangre'),'10',if((`ordenof`.`concepto` like '*tesis y*'),'11',if((`ordenof`.`concepto` = 'Medicamentos POS'),'12',if((`ordenof`.`concepto` = 'Medicamentos no POS'),'13',if((`ordenof`.`concepto` = 'Traslado de pacientes'),'14','')))))))))))))) AS `codigoConcepto`,`ordenof`.`concepto` AS `conceptoDesc`,if((`ordenof`.`codigoConsulta` = 'Consulta de Medicina General electiva'),'890201',if((`ordenof`.`codigoConsulta` = 'Consulta de Medicina Especializada electiva'),'890202',if((`ordenof`.`codigoConsulta` = 'Consulta de Medicina General de control'),'890301',if((`ordenof`.`codigoConsulta` = 'Consulta de Medicina Especializada de control'),'890302',if((`ordenof`.`codigoConsulta` = 'Consulta odontologica especializada de control'),'890304',if((`ordenof`.`codigoConsulta` = 'Consulta odontologica general de control'),'890303',if((`ordenof`.`codigoConsulta` = 'Consulta de odontologia general'),'890703',if((`ordenof`.`codigoConsulta` = 'Consulta de odontologia especializada'),'890704','')))))))) AS `codConsulta`,`ordenof`.`codigoConsulta` AS `codigoConsultaDesc`,if((`ordenof`.`tipo` = 'procedimiento'),if((`ordenof`.`finalidad` like 'Diagn*'),'1',if((`ordenof`.`finalidad` like 'Terap*'),'2',if((`ordenof`.`finalidad` like 'Protecci*'),'3',if((`ordenof`.`finalidad` like '*n temprana de enfermedad general'),'4',if((`ordenof`.`finalidad` like '*n temprana de enfermedad profesional'),'5',''))))),if((`ordenof`.`finalidad` like '*n del parto (puerperio)'),'01',if((`ordenof`.`finalidad` like '*n del reci*'),'02',if((`ordenof`.`finalidad` like '*n en planificaci*'),'03',if((`ordenof`.`finalidad` like 'No aplica'),'10',if((`ordenof`.`finalidad` like 'Detecci*n de alteraci*n del*'),'05',if((`ordenof`.`finalidad` like 'Detecci*n de alteraciones del embarazo'),'06',if((`ordenof`.`finalidad` like 'Detecci*n de alteraciones del adulto'),'07',if((`ordenof`.`finalidad` like 'Detecci*n de alteraciones de agudeza visual'),'08',if((`ordenof`.`finalidad` like 'Detecci*n de enfermedad profesional'),'09',if((`ordenof`.`finalidad` like '*n de alteraciones de*'),'04',''))))))))))) AS `finalidadd`,`ordenof`.`finalidad` AS `finalidadDesc`,if((`ordenof`.`causa` = 'Accidente de trabajo'),'01',if((`ordenof`.`causa` like 'Accidente de tr*nsito'),'02',if((`ordenof`.`causa` like 'Accidente r*bico'),'03',if((`ordenof`.`causa` like 'Accidente of*dico'),'04',if((`ordenof`.`causa` = 'Otro tipo de accidente'),'05',if((`ordenof`.`causa` like 'Evento catastr*fico'),'06',if((`ordenof`.`causa` like 'Lesi*n por agresi*n'),'07',if((`ordenof`.`causa` like 'Lesi*n auto infligida'),'08',if((`ordenof`.`causa` like 'Sospecha de maltrato f*sico'),'09',if((`ordenof`.`causa` = 'Sospecha de abuso sexual'),'10',if((`ordenof`.`causa` = 'Sospecha de violencia sexual'),'11',if((`ordenof`.`causa` = 'Sospecha de maltrato emocional'),'12',if((`ordenof`.`causa` = 'Enfermedad general'),'13',if((`ordenof`.`causa` = 'Enfermedad profesional'),'14','15')))))))))))))) AS `causaExterna`,`ordenof`.`causa` AS `causaExternaDesc`,`ordenof`.`cuota` AS `valorCuotaModeradora`,if((`ordenof`.`ambito` = 'Ambulatorio'),'1',if((`ordenof`.`ambito` = 'Hospitalario'),'2','3')) AS `ambitoProcedimiento`,`ordenof`.`ambito` AS `ambitoProcedimientoDesc`,if((`ordenof`.`personal` like 'M*dico (A) especialista'),'1',if((`ordenof`.`personal` like 'M*dico (a) general'),'2',if((`ordenof`.`personal` = 'Enfermera (o)'),'3',if((`ordenof`.`personal` like 'Auxiliar de enfermer*a'),'4',if((`ordenof`.`personal` = 'Otro'),'5',''))))) AS `personalAtiende`,`ordenof`.`personal` AS `personalDesc`,`ordenof`.`Estado` AS `Estado`,`bdregiones`.`codigoDepartamento` AS `codigoDepartamento`,`bdregiones`.`codigoMunicipio` AS `codigoMunicipio`,`ordenof`.`estado2` AS `estado2`,`ordenof`.`orden` AS `facturaNumero` from (`bdmaestra` join ((`bdproductos` join `bdordenserviciolaboratorio` `ordenof` on((`bdproductos`.`Codigo` = `ordenof`.`acobrar`))) join ((`bdterceros` join `bdepsprecargados` on((`bdterceros`.`eps` = `bdepsprecargados`.`Id`))) join `bdregiones` on(((`bdterceros`.`departamento` = `bdregiones`.`nombreDepartamento`) and (`bdterceros`.`ciudad` = `bdregiones`.`nombreMunicipio`)))) on((`ordenof`.`paciente` = `bdterceros`.`id`)))));

CREATE OR REPLACE VIEW `ripsoftalmologia1` AS (select `ordenof`.`id` AS `orden`,`ordenof`.`codigoIps` AS `codigoIps`,`ordenof`.`autorizacion` AS `autorizacion`,`bdterceros`.`id` AS `Id`,`ordenof`.`fecha` AS `fecha`,`ordenof`.`actoQuirurgico` AS `actoQuirurgico`,`ordenof`.`cup` AS `cup`,`ordenof`.`cantidad` AS `cantidad`,`ordenof`.`cantidadRealizada` AS `cantidadRealizada`,`ordenof`.`copago` AS `copago`,`ordenof`.`cie` AS `cie`,`bdterceros`.`telefono` AS `telefono`,`bdterceros`.`celular` AS `celular`,`bdterceros`.`nacimiento` AS `nacimiento`,`ordenof`.`valor` AS `valor`,`bdterceros`.`nombre` AS `nombre`,`ordenof`.`cup` AS `codigoTarifa`,`bdproductos`.`Descripcion` AS `procedimientoAutorizado`,`bdterceros`.`eps` AS `eps`,`bdterceros`.`ciudad` AS `ciudad`,`bdterceros`.`departamento` AS `departamento`,`bdterceros`.`pNombre` AS `pNombre`,`bdterceros`.`sNombre` AS `sNombre`,`bdterceros`.`pApellido` AS `pApellido`,`bdterceros`.`sApellido` AS `sApellido`,if((`bdterceros`.`tipo` like '*dula de extranjer*'),'CE',if((`bdterceros`.`tipo` like '*dula*'),'CC',if((`bdterceros`.`tipo` = 'Pasaporte'),'PA',if((`bdterceros`.`tipo` = 'Registro civil'),'RC',if((`bdterceros`.`tipo` = 'Tarjeta de identidad'),'TI',if((`bdterceros`.`tipo` like '*Adulto sin identificaci*'),'AS',if((`bdterceros`.`tipo` like '*Menor sin identificaci*'),'MS',if((`bdterceros`.`tipo` like '*nico de identificaci*'),'NU','')))))))) AS `tipoIdentidad`,`bdterceros`.`tipo` AS `tipoIdentidadDesc`,if((`bdepsprecargados`.`codigoProteccionSocial` = '000001'),'',`bdepsprecargados`.`codigoProteccionSocial`) AS `codigoEntidadAdministradora`,if((`bdterceros`.`regimen` = 'Contributivo'),'1',if((`bdterceros`.`regimen` = 'Subsidiado'),'2',if((`bdterceros`.`regimen` = 'Vinculado'),'3',if((`bdterceros`.`regimen` = 'Particular'),'4',if((`bdterceros`.`regimen` = 'Otro'),'5',''))))) AS `TipoUsuario`,`bdterceros`.`regimen` AS `tipoUsuarioDesc`,cast(((cast(now() as date) - `bdterceros`.`nacimiento`) / 365) as signed) AS `Edad`,if(((year(cast(now() as date)) - year(`bdterceros`.`nacimiento`)) = 0),if(((month(cast(now() as date)) - month(`bdterceros`.`nacimiento`)) = 0),'3','2'),'1') AS `unidadMedidaEdad`,if((`bdterceros`.`sexo` = 'Masculino'),'M','F') AS `sexo`,if((`bdterceros`.`zona` = 'Urbana'),'U','R') AS `zona`,`bdterceros`.`zona` AS `zonaDesc`,`bdmaestra`.`codPrestadorServicio` AS `codPrestadorServicio`,`bdmaestra`.`nombreEmpresa` AS `razonSocial`,`bdmaestra`.`tipoIdPrestadorServicio` AS `tipoPrestadorServicio`,`bdmaestra`.`nitEmpresa` AS `IdPrestadorServicio`,if((`bdepsprecargados`.`codigoProteccionSocial` = '000001'),'',`bdepsprecargados`.`nombre`) AS `nombreEntidadAdministradora`,`ordenof`.`poliza` AS `nroPoliza`,`ordenof`.`comision` AS `valorComision`,`ordenof`.`descuento` AS `valorDescuento`,`bdepsprecargados`.`contrato` AS `numeroContrato`,`ordenof`.`tipo` AS `tipoOrden`,if((`ordenof`.`concepto` = 'Consultas'),'01',if((`ordenof`.`concepto` like 'Procedimientos de diagn*'),'02',if((`ordenof`.`concepto` like '*uticos no quir*'),'03',if((`ordenof`.`concepto` like 'Procedimientos terap*'),'04',if((`ordenof`.`concepto` like 'Procedimientos de promoci*'),'05',if((`ordenof`.`concepto` = 'Estancias'),'06',if((`ordenof`.`concepto` = 'Honorarios'),'07',if((`ordenof`.`concepto` = 'Derechos de sala'),'08',if((`ordenof`.`concepto` = 'Materiales e insumos'),'09',if((`ordenof`.`concepto` = 'Banco de sangre'),'10',if((`ordenof`.`concepto` like '*tesis y*'),'11',if((`ordenof`.`concepto` = 'Medicamentos POS'),'12',if((`ordenof`.`concepto` = 'Medicamentos no POS'),'13',if((`ordenof`.`concepto` = 'Traslado de pacientes'),'14','')))))))))))))) AS `codigoConcepto`,`ordenof`.`concepto` AS `conceptoDesc`,if((`ordenof`.`codigoConsulta` = 'Consulta de Medicina General electiva'),'890201',if((`ordenof`.`codigoConsulta` = 'Consulta de Medicina Especializada electiva'),'890202',if((`ordenof`.`codigoConsulta` = 'Consulta de Medicina General de control'),'890301',if((`ordenof`.`codigoConsulta` = 'Consulta de Medicina Especializada de control'),'890302',if((`ordenof`.`codigoConsulta` = 'Consulta odontologica especializada de control'),'890304',if((`ordenof`.`codigoConsulta` = 'Consulta odontologica general de control'),'890303',if((`ordenof`.`codigoConsulta` = 'Consulta de odontologia general'),'890703',if((`ordenof`.`codigoConsulta` = 'Consulta de odontologia especializada'),'890704','')))))))) AS `codConsulta`,`ordenof`.`codigoConsulta` AS `codigoConsultaDesc`,if((`ordenof`.`tipo` = 'procedimiento'),if((`ordenof`.`finalidad` like 'Diagn*'),'1',if((`ordenof`.`finalidad` like 'Terap*'),'2',if((`ordenof`.`finalidad` like 'Protecci*'),'3',if((`ordenof`.`finalidad` like '*n temprana de enfermedad general'),'4',if((`ordenof`.`finalidad` like '*n temprana de enfermedad profesional'),'5',''))))),if((`ordenof`.`finalidad` like '*n del parto (puerperio)'),'01',if((`ordenof`.`finalidad` like '*n del reci*'),'02',if((`ordenof`.`finalidad` like '*n en planificaci*'),'03',if((`ordenof`.`finalidad` like 'No aplica'),'10',if((`ordenof`.`finalidad` like 'Detecci*n de alteraci*n del*'),'05',if((`ordenof`.`finalidad` like 'Detecci*n de alteraciones del embarazo'),'06',if((`ordenof`.`finalidad` like 'Detecci*n de alteraciones del adulto'),'07',if((`ordenof`.`finalidad` like 'Detecci*n de alteraciones de agudeza visual'),'08',if((`ordenof`.`finalidad` like 'Detecci*n de enfermedad profesional'),'09',if((`ordenof`.`finalidad` like '*n de alteraciones de*'),'04',''))))))))))) AS `finalidadd`,`ordenof`.`finalidad` AS `finalidadDesc`,if((`ordenof`.`causa` = 'Accidente de trabajo'),'01',if((`ordenof`.`causa` like 'Accidente de tr*nsito'),'02',if((`ordenof`.`causa` like 'Accidente r*bico'),'03',if((`ordenof`.`causa` like 'Accidente of*dico'),'04',if((`ordenof`.`causa` = 'Otro tipo de accidente'),'05',if((`ordenof`.`causa` like 'Evento catastr*fico'),'06',if((`ordenof`.`causa` like 'Lesi*n por agresi*n'),'07',if((`ordenof`.`causa` like 'Lesi*n auto infligida'),'08',if((`ordenof`.`causa` like 'Sospecha de maltrato f*sico'),'09',if((`ordenof`.`causa` = 'Sospecha de abuso sexual'),'10',if((`ordenof`.`causa` = 'Sospecha de violencia sexual'),'11',if((`ordenof`.`causa` = 'Sospecha de maltrato emocional'),'12',if((`ordenof`.`causa` = 'Enfermedad general'),'13',if((`ordenof`.`causa` = 'Enfermedad profesional'),'14','15')))))))))))))) AS `causaExterna`,`ordenof`.`causa` AS `causaExternaDesc`,`ordenof`.`cuota` AS `valorCuotaModeradora`,if((`ordenof`.`ambito` = 'Ambulatorio'),'1',if((`ordenof`.`ambito` = 'Hospitalario'),'2','3')) AS `ambitoProcedimiento`,`ordenof`.`ambito` AS `ambitoProcedimientoDesc`,if((`ordenof`.`personal` like 'M*dico (A) especialista'),'1',if((`ordenof`.`personal` like 'M*dico (a) general'),'2',if((`ordenof`.`personal` = 'Enfermera (o)'),'3',if((`ordenof`.`personal` like 'Auxiliar de enfermer*a'),'4',if((`ordenof`.`personal` = 'Otro'),'5',''))))) AS `personalAtiende`,`ordenof`.`personal` AS `personalDesc`,`ordenof`.`Estado` AS `Estado`,`bdregiones`.`codigoDepartamento` AS `codigoDepartamento`,`bdregiones`.`codigoMunicipio` AS `codigoMunicipio`,`ordenof`.`estado2` AS `estado2`,`ordenof`.`orden` AS `facturaNumero` from (`bdmaestra` join ((`bdproductos` join `bdordenserviciooftalmologia` `ordenof` on((`bdproductos`.`Codigo` = `ordenof`.`acobrar`))) join ((`bdterceros` join `bdepsprecargados` on((`bdterceros`.`eps` = `bdepsprecargados`.`Id`))) join `bdregiones` on(((`bdterceros`.`ciudad` = `bdregiones`.`nombreMunicipio`) and (`bdterceros`.`departamento` = `bdregiones`.`nombreDepartamento`)))) on((`ordenof`.`paciente` = `bdterceros`.`id`)))));

CREATE OR REPLACE VIEW `ajustesimei` AS select `bdtrasladosdetalle`.`traslado` AS `traslado`,`bdtrasladosdetalle`.`detalle` AS `imei`,`bdtraslados`.`tipo` AS `tipo` from (`bdtrasladosdetalle` left join `bdtraslados` on((`bdtraslados`.`Id` = `bdtrasladosdetalle`.`traslado`))) where (`bdtrasladosdetalle`.`detalle` <> '');

SET FOREIGN_KEY_CHECKS = 1;
