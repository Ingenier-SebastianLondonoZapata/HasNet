package Dao.Configuraciones;

import Modelo.Maestra.ModeloMaestraGeneral;
import Modelo.Maestra.ModeloMaestraImpresiones;
import Utilidades.BaseDatos.MySql_connection;
import Utilidades.Constantes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoMaestra {

    private final Connection conexion = MySql_connection.getInstancia(Constantes.BASE_DATOS_PRINCIPAL).getConnection();

    /**
     * La pestaña de bodegas de la maestra no tiene un Guardar propio, así que el cambio se escribe al seleccionarlo.
     */
    public boolean actualizarBodega(String codigo) {
        try (PreparedStatement stmt = conexion.prepareStatement("UPDATE bdMaestra SET bodega = ? WHERE Id = 1")) {
            stmt.setString(1, codigo == null ? "" : codigo.trim());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al guardar la bodega predeterminada: " + e.getMessage());
            return false;
        }
    }

    private int parseCopias(String valor) {
        try {
            return Math.max(0, Integer.parseInt(valor == null ? "" : valor.trim()));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public boolean guardar(ModeloMaestraGeneral general, ModeloMaestraImpresiones impresiones) {
        return guardarDatosGenerales(general) && guardarImpresiones(impresiones);
    }

    private boolean guardarDatosGenerales(ModeloMaestraGeneral g) {
        String sql = "UPDATE bdMaestra SET "
                + "configuracionRegimen=?, alertaUtilidad=?, tipoDescuento=?, contrasena=?, diasAlertaCuadre=?, "
                + "nitEmpresa=?, nombreEmpresa=?, regimenEmpresa=?, direccionEmpresa=?, telefonoEmpresa=?, "
                + "usarLector=?, tituloFactura=?, preciosConImpuesto=?, "
                + "ventasPredeterminado=?, mensajeUtilidad=?, consecutivosDiferentes=?, "
                + "alertaFechaDias=?, alertaCantidadNumeracion=?, alertaPromedioDias=?, "
                + "diasCobroMora=?, porcentajeMora=?, "
                + "generaOrdenMedica=?, imprimirOrdenMedica=?, imprimirFacturaOrdenMedica=?, "
                + "horaInicioAgenda=?, horaFinAgenda=?, intervaloAgenda=?, codPrestadorServicio=?, "
                + "ocultarInformacionCliente=?, visualizarTodasLasFacturas=?, "
                + "mostrarInformacionCuadre=?, descuentoMaximoVentas=?, tipoIdPrestadorServicio=?, "
                + "limiteProductosPorFactura=?, combinarProductos=?, modificarNombre=?, "
                + "turno=?, turnoConsecutivo=?, consecutivoAdicional=?, "
                + "modificarPrecio=?, borrarCongelada=?, facturarSinInventario=?, "
                + "cantidadEstablecida=?, filas=?, columnas=?, porcPropina=?, mostrarDevuelta=?, "
                + "inicioHosp=?, finHosp=?, intervalosHosp=?, diasAutomaticos=?, ciudadBuscador=?, foco=?, "
                + "facturarMesas=?, soloMesas=?, borrarMesas=?, "
                + "password=?, responsabilidadesFiscales=? "
                + "WHERE Id = 1";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            int i = 1;
            stmt.setString(i++, g.getConfiguracionRegimen());
            stmt.setString(i++, g.getAlertaUtilidad());
            stmt.setString(i++, g.getTipoDescuento());
            stmt.setString(i++, g.getContrasena());
            stmt.setInt(i++, g.getDiasAlertaCuadre());
            stmt.setString(i++, g.getNitEmpresa());
            stmt.setString(i++, g.getNombreEmpresa());
            stmt.setString(i++, g.getRegimenEmpresa());
            stmt.setString(i++, g.getDireccionEmpresa());
            stmt.setString(i++, g.getTelefonoEmpresa());
            stmt.setBoolean(i++, g.isUsarLector());
            stmt.setString(i++, g.getTituloFactura());
            stmt.setBoolean(i++, g.isPreciosConImpuesto());
            stmt.setBoolean(i++, g.isVentasPredeterminado());
            stmt.setBoolean(i++, g.isMensajeUtilidad());
            stmt.setBoolean(i++, g.isConsecutivosDiferentes());
            stmt.setString(i++, g.getAlertaFechaDias());
            stmt.setString(i++, g.getAlertaCantidadNumeracion());
            stmt.setString(i++, g.getAlertaPromedioDias());
            stmt.setInt(i++, g.getDiasCobroMora());
            stmt.setString(i++, g.getPorcentajeMora());
            stmt.setBoolean(i++, g.isGeneraOrdenMedica());
            stmt.setBoolean(i++, g.isImprimirOrdenMedica());
            stmt.setBoolean(i++, g.isImprimirFacturaOrdenMedica());
            stmt.setString(i++, g.getHoraInicioAgenda());
            stmt.setString(i++, g.getHoraFinAgenda());
            stmt.setString(i++, g.getIntervaloAgenda());
            stmt.setString(i++, g.getCodPrestadorServicio());
            stmt.setBoolean(i++, g.isOcultarInformacionCliente());
            stmt.setBoolean(i++, g.isVisualizarTodasLasFacturas());
            stmt.setBoolean(i++, g.isMostrarInformacionCuadre());
            stmt.setInt(i++, g.getDescuentoMaximoVentas());
            stmt.setString(i++, g.getTipoIdPrestadorServicio());
            stmt.setInt(i++, g.getLimiteProductosPorFactura());
            stmt.setBoolean(i++, g.isCombinarProductos());
            stmt.setBoolean(i++, g.isModificarNombre());
            stmt.setBoolean(i++, g.isTurno());
            stmt.setInt(i++, g.getTurnoConsecutivo());
            stmt.setBoolean(i++, g.isConsecutivoAdicional());
            stmt.setBoolean(i++, g.isModificarPrecio());
            stmt.setBoolean(i++, g.isBorrarCongelada());
            stmt.setBoolean(i++, g.isFacturarSinInventario());
            stmt.setInt(i++, g.getCantidadEstablecida());
            stmt.setInt(i++, g.getFilas());
            stmt.setInt(i++, g.getColumnas());
            stmt.setInt(i++, g.getPorcPropina());
            stmt.setBoolean(i++, g.isMostrarDevuelta());
            stmt.setString(i++, g.getInicioHosp());
            stmt.setString(i++, g.getFinHosp());
            stmt.setString(i++, g.getIntervalosHosp());
            stmt.setBoolean(i++, g.isDiasAutomaticos());
            stmt.setBoolean(i++, g.isCiudadBuscador());
            stmt.setString(i++, g.getFoco());
            stmt.setBoolean(i++, g.isFacturarMesas());
            stmt.setBoolean(i++, g.isSoloMesas());
            stmt.setBoolean(i++, g.isBorrarMesas());
            stmt.setString(i++, g.getPassword());
            stmt.setString(i++, g.getResponsabilidadesFiscales());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al guardar datos generales maestros: " + e.getMessage());
            return false;
        }
    }

    private boolean guardarImpresiones(ModeloMaestraImpresiones imp) {
        String sql = "INSERT INTO bdMaestraImpresiones ("
                + "Id, "
                + "previsualizarFactura, previsualizarOServicio, previsualizarCotizacion, "
                + "previsualizarPlanSepare, previsualizarPedido, previsualizarComanda, previsualizarPrefactura, "
                + "preguntarCopiasFactura, preguntarCopiasOServicio, preguntarCopiasCotizacion, "
                + "preguntarCopiasPlanSepare, preguntarCopiasPedido, preguntarCopiasComanda, preguntarCopiasPrefactura, "
                + "numeroCopiasFactura, numeroCopiasOrdenServicio, numeroCopiasCotizacion, "
                + "numeroCopiasPlanSepare, numeroCopiasPedido, numeroCopiasComanda, numeroCopiasPrefactura, "
                + "impresoraPos, impresoraMediaCarta, impresoraCarta, impresoraComanda, impresoraPrefactura, "
                + "tipoPredeterminado, mostrarUbicacion, "
                + "imprimirHora, imprimirRetenciones, imprimirImpoconsumo, imprimirRecodiga, imprimirCuadreFiscal, "
                + "impresionPorGrupo, formatoImpresion, "
                + "piePagina, informacionLegal, anexoFactura, anexoOrdenServicio"
                + ") VALUES (1, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
                + " ON DUPLICATE KEY UPDATE "
                + "previsualizarFactura=VALUES(previsualizarFactura), previsualizarOServicio=VALUES(previsualizarOServicio), "
                + "previsualizarCotizacion=VALUES(previsualizarCotizacion), previsualizarPlanSepare=VALUES(previsualizarPlanSepare), "
                + "previsualizarPedido=VALUES(previsualizarPedido), previsualizarComanda=VALUES(previsualizarComanda), "
                + "previsualizarPrefactura=VALUES(previsualizarPrefactura), "
                + "preguntarCopiasFactura=VALUES(preguntarCopiasFactura), preguntarCopiasOServicio=VALUES(preguntarCopiasOServicio), "
                + "preguntarCopiasCotizacion=VALUES(preguntarCopiasCotizacion), preguntarCopiasPlanSepare=VALUES(preguntarCopiasPlanSepare), "
                + "preguntarCopiasPedido=VALUES(preguntarCopiasPedido), preguntarCopiasComanda=VALUES(preguntarCopiasComanda), "
                + "preguntarCopiasPrefactura=VALUES(preguntarCopiasPrefactura), "
                + "numeroCopiasFactura=VALUES(numeroCopiasFactura), numeroCopiasOrdenServicio=VALUES(numeroCopiasOrdenServicio), "
                + "numeroCopiasCotizacion=VALUES(numeroCopiasCotizacion), numeroCopiasPlanSepare=VALUES(numeroCopiasPlanSepare), "
                + "numeroCopiasPedido=VALUES(numeroCopiasPedido), numeroCopiasComanda=VALUES(numeroCopiasComanda), numeroCopiasPrefactura=VALUES(numeroCopiasPrefactura), "
                + "impresoraPos=VALUES(impresoraPos), impresoraMediaCarta=VALUES(impresoraMediaCarta), "
                + "impresoraCarta=VALUES(impresoraCarta), impresoraComanda=VALUES(impresoraComanda), "
                + "impresoraPrefactura=VALUES(impresoraPrefactura), "
                + "tipoPredeterminado=VALUES(tipoPredeterminado), mostrarUbicacion=VALUES(mostrarUbicacion), "
                + "imprimirHora=VALUES(imprimirHora), imprimirRetenciones=VALUES(imprimirRetenciones), "
                + "imprimirImpoconsumo=VALUES(imprimirImpoconsumo), imprimirRecodiga=VALUES(imprimirRecodiga), "
                + "imprimirCuadreFiscal=VALUES(imprimirCuadreFiscal), impresionPorGrupo=VALUES(impresionPorGrupo), "
                + "formatoImpresion=VALUES(formatoImpresion), "
                + "piePagina=VALUES(piePagina), informacionLegal=VALUES(informacionLegal), "
                + "anexoFactura=VALUES(anexoFactura), anexoOrdenServicio=VALUES(anexoOrdenServicio)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            int i = 1;
            stmt.setBoolean(i++, imp.isPrevisualizarFactura());
            stmt.setBoolean(i++, imp.isPrevisualizarOServicio());
            stmt.setBoolean(i++, imp.isPrevisualizarCotizacion());
            stmt.setBoolean(i++, imp.isPrevisualizarPlanSepare());
            stmt.setBoolean(i++, imp.isPrevisualizarPedido());
            stmt.setBoolean(i++, imp.isPrevisualizarComanda());
            stmt.setBoolean(i++, imp.isPrevisualizarPrefactura());
            stmt.setBoolean(i++, imp.isPreguntarCopiasFactura());
            stmt.setBoolean(i++, imp.isPreguntarCopiasOServicio());
            stmt.setBoolean(i++, imp.isPreguntarCopiasCotizacion());
            stmt.setBoolean(i++, imp.isPreguntarCopiasPlanSepare());
            stmt.setBoolean(i++, imp.isPreguntarCopiasPedido());
            stmt.setBoolean(i++, imp.isPreguntarCopiasComanda());
            stmt.setBoolean(i++, imp.isPreguntarCopiasPrefactura());
            stmt.setInt(i++, imp.getNumeroCopiasFactura());
            stmt.setInt(i++, imp.getNumeroCopiasOrdenServicio());
            stmt.setInt(i++, imp.getNumeroCopiasCotizacion());
            stmt.setInt(i++, imp.getNumeroCopiasPlanSepare());
            stmt.setInt(i++, imp.getNumeroCopiasPedido());
            stmt.setInt(i++, imp.getNumeroCopiasComanda());
            stmt.setInt(i++, imp.getNumeroCopiasPrefactura());
            stmt.setString(i++, imp.getImpresoraPos());
            stmt.setString(i++, imp.getImpresoraMediaCarta());
            stmt.setString(i++, imp.getImpresoraCarta());
            stmt.setString(i++, imp.getImpresoraComanda());
            stmt.setString(i++, imp.getImpresoraPrefactura());
            stmt.setString(i++, imp.getTipoPredeterminado());
            stmt.setString(i++, imp.getMostrarUbicacion());
            stmt.setBoolean(i++, imp.isImprimirHora());
            stmt.setBoolean(i++, imp.isImprimirRetenciones());
            stmt.setBoolean(i++, imp.isImprimirImpoconsumo());
            stmt.setBoolean(i++, imp.isImprimirRecodiga());
            stmt.setBoolean(i++, imp.isImprimirCuadreFiscal());
            stmt.setBoolean(i++, imp.isImpresionPorGrupo());
            stmt.setString(i++, imp.getFormatoImpresion());
            stmt.setString(i++, imp.getPiePagina());
            stmt.setString(i++, imp.getInformacionLegal());
            stmt.setString(i++, imp.getAnexoFactura());
            stmt.setString(i++, imp.getAnexoOrdenServicio());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al guardar impresiones maestras: " + e.getMessage());
            return false;
        }
    }

    public ModeloMaestraGeneral obtenerGeneral() {
        String sql = "SELECT configuracionRegimen, alertaUtilidad, tipoDescuento, contrasena, diasAlertaCuadre, "
                + "nitEmpresa, nombreEmpresa, regimenEmpresa, direccionEmpresa, telefonoEmpresa, "
                + "usarLector, tituloFactura, preciosConImpuesto, ventasPredeterminado, mensajeUtilidad, "
                + "consecutivosDiferentes, alertaFechaDias, alertaCantidadNumeracion, alertaPromedioDias, "
                + "diasCobroMora, porcentajeMora, "
                + "generaOrdenMedica, imprimirOrdenMedica, imprimirFacturaOrdenMedica, "
                + "horaInicioAgenda, horaFinAgenda, intervaloAgenda, codPrestadorServicio, "
                + "ocultarInformacionCliente, visualizarTodasLasFacturas, mostrarInformacionCuadre, "
                + "descuentoMaximoVentas, tipoIdPrestadorServicio, limiteProductosPorFactura, "
                + "combinarProductos, modificarNombre, "
                + "turno, turnoConsecutivo, consecutivoAdicional, modificarPrecio, borrarCongelada, "
                + "facturarSinInventario, cantidadEstablecida, filas, columnas, porcPropina, mostrarDevuelta, "
                + "inicioHosp, finHosp, intervalosHosp, diasAutomaticos, ciudadBuscador, foco, "
                + "facturarMesas, soloMesas, borrarMesas, "
                + "password, responsabilidadesFiscales "
                + "FROM bdMaestra WHERE Id = 1";
        try (PreparedStatement stmt = conexion.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            if (!rs.next()) return null;
            ModeloMaestraGeneral g = new ModeloMaestraGeneral();
            g.setConfiguracionRegimen(rs.getString("configuracionRegimen"));
            g.setAlertaUtilidad(rs.getString("alertaUtilidad"));
            g.setTipoDescuento(rs.getString("tipoDescuento"));
            g.setContrasena(rs.getString("contrasena"));
            g.setDiasAlertaCuadre(rs.getInt("diasAlertaCuadre"));
            g.setNitEmpresa(rs.getString("nitEmpresa"));
            g.setNombreEmpresa(rs.getString("nombreEmpresa"));
            g.setRegimenEmpresa(rs.getString("regimenEmpresa"));
            g.setDireccionEmpresa(rs.getString("direccionEmpresa"));
            g.setTelefonoEmpresa(rs.getString("telefonoEmpresa"));
            g.setUsarLector(rs.getBoolean("usarLector"));
            g.setTituloFactura(rs.getString("tituloFactura"));
            g.setPreciosConImpuesto(rs.getBoolean("preciosConImpuesto"));
            g.setVentasPredeterminado(rs.getBoolean("ventasPredeterminado"));
            g.setMensajeUtilidad(rs.getBoolean("mensajeUtilidad"));
            g.setConsecutivosDiferentes(rs.getBoolean("consecutivosDiferentes"));
            g.setAlertaFechaDias(rs.getString("alertaFechaDias"));
            g.setAlertaCantidadNumeracion(rs.getString("alertaCantidadNumeracion"));
            g.setAlertaPromedioDias(rs.getString("alertaPromedioDias"));
            g.setDiasCobroMora(rs.getInt("diasCobroMora"));
            g.setPorcentajeMora(rs.getString("porcentajeMora"));
            g.setGeneraOrdenMedica(rs.getBoolean("generaOrdenMedica"));
            g.setImprimirOrdenMedica(rs.getBoolean("imprimirOrdenMedica"));
            g.setImprimirFacturaOrdenMedica(rs.getBoolean("imprimirFacturaOrdenMedica"));
            g.setHoraInicioAgenda(rs.getString("horaInicioAgenda"));
            g.setHoraFinAgenda(rs.getString("horaFinAgenda"));
            g.setIntervaloAgenda(rs.getString("intervaloAgenda"));
            g.setCodPrestadorServicio(rs.getString("codPrestadorServicio"));
            g.setOcultarInformacionCliente(rs.getBoolean("ocultarInformacionCliente"));
            g.setVisualizarTodasLasFacturas(rs.getBoolean("visualizarTodasLasFacturas"));
            g.setMostrarInformacionCuadre(rs.getBoolean("mostrarInformacionCuadre"));
            g.setDescuentoMaximoVentas(rs.getInt("descuentoMaximoVentas"));
            g.setTipoIdPrestadorServicio(rs.getString("tipoIdPrestadorServicio"));
            g.setLimiteProductosPorFactura(rs.getInt("limiteProductosPorFactura"));
            g.setCombinarProductos(rs.getBoolean("combinarProductos"));
            g.setModificarNombre(rs.getBoolean("modificarNombre"));
            g.setTurno(rs.getBoolean("turno"));
            g.setTurnoConsecutivo(rs.getInt("turnoConsecutivo"));
            g.setConsecutivoAdicional(rs.getBoolean("consecutivoAdicional"));
            g.setModificarPrecio(rs.getBoolean("modificarPrecio"));
            g.setBorrarCongelada(rs.getBoolean("borrarCongelada"));
            g.setFacturarSinInventario(rs.getBoolean("facturarSinInventario"));
            g.setCantidadEstablecida(rs.getInt("cantidadEstablecida"));
            g.setFilas(rs.getInt("filas"));
            g.setColumnas(rs.getInt("columnas"));
            g.setPorcPropina(rs.getInt("porcPropina"));
            g.setMostrarDevuelta(rs.getBoolean("mostrarDevuelta"));
            g.setInicioHosp(rs.getString("inicioHosp"));
            g.setFinHosp(rs.getString("finHosp"));
            g.setIntervalosHosp(rs.getString("intervalosHosp"));
            g.setDiasAutomaticos(rs.getBoolean("diasAutomaticos"));
            g.setCiudadBuscador(rs.getBoolean("ciudadBuscador"));
            g.setFoco(rs.getString("foco"));
            g.setFacturarMesas(rs.getBoolean("facturarMesas"));
            g.setSoloMesas(rs.getBoolean("soloMesas"));
            g.setBorrarMesas(rs.getBoolean("borrarMesas"));
            g.setPassword(rs.getString("password"));
            g.setResponsabilidadesFiscales(rs.getString("responsabilidadesFiscales"));
            return g;
        } catch (SQLException e) {
            System.err.println("Error al obtener datos generales maestros: " + e.getMessage());
            return null;
        }
    }

    public ModeloMaestraImpresiones obtenerImpresiones() {
        String sql = "SELECT "
                + "previsualizarFactura, previsualizarOServicio, previsualizarCotizacion, "
                + "previsualizarPlanSepare, previsualizarPedido, previsualizarComanda, previsualizarPrefactura, "
                + "preguntarCopiasFactura, preguntarCopiasOServicio, preguntarCopiasCotizacion, "
                + "preguntarCopiasPlanSepare, preguntarCopiasPedido, preguntarCopiasComanda, preguntarCopiasPrefactura, "
                + "numeroCopiasFactura, numeroCopiasOrdenServicio, numeroCopiasCotizacion, "
                + "numeroCopiasPlanSepare, numeroCopiasPedido, numeroCopiasComanda, numeroCopiasPrefactura, "
                + "impresoraPos, impresoraMediaCarta, impresoraCarta, impresoraComanda, impresoraPrefactura, "
                + "tipoPredeterminado, mostrarUbicacion, "
                + "imprimirHora, imprimirRetenciones, imprimirImpoconsumo, imprimirRecodiga, imprimirCuadreFiscal, "
                + "impresionPorGrupo, formatoImpresion, "
                + "piePagina, informacionLegal, anexoFactura, anexoOrdenServicio "
                + "FROM bdMaestraImpresiones WHERE Id = 1";
        try (PreparedStatement stmt = conexion.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            if (!rs.next()) return new ModeloMaestraImpresiones();
            ModeloMaestraImpresiones imp = new ModeloMaestraImpresiones();
            imp.setPrevisualizarFactura(rs.getBoolean("previsualizarFactura"));
            imp.setPrevisualizarOServicio(rs.getBoolean("previsualizarOServicio"));
            imp.setPrevisualizarCotizacion(rs.getBoolean("previsualizarCotizacion"));
            imp.setPrevisualizarPlanSepare(rs.getBoolean("previsualizarPlanSepare"));
            imp.setPrevisualizarPedido(rs.getBoolean("previsualizarPedido"));
            imp.setPrevisualizarComanda(rs.getBoolean("previsualizarComanda"));
            imp.setPrevisualizarPrefactura(rs.getBoolean("previsualizarPrefactura"));
            imp.setPreguntarCopiasFactura(rs.getBoolean("preguntarCopiasFactura"));
            imp.setPreguntarCopiasOServicio(rs.getBoolean("preguntarCopiasOServicio"));
            imp.setPreguntarCopiasCotizacion(rs.getBoolean("preguntarCopiasCotizacion"));
            imp.setPreguntarCopiasPlanSepare(rs.getBoolean("preguntarCopiasPlanSepare"));
            imp.setPreguntarCopiasPedido(rs.getBoolean("preguntarCopiasPedido"));
            imp.setPreguntarCopiasComanda(rs.getBoolean("preguntarCopiasComanda"));
            imp.setPreguntarCopiasPrefactura(rs.getBoolean("preguntarCopiasPrefactura"));
            imp.setNumeroCopiasFactura(rs.getInt("numeroCopiasFactura"));
            imp.setNumeroCopiasOrdenServicio(rs.getInt("numeroCopiasOrdenServicio"));
            imp.setNumeroCopiasCotizacion(rs.getInt("numeroCopiasCotizacion"));
            imp.setNumeroCopiasPlanSepare(rs.getInt("numeroCopiasPlanSepare"));
            imp.setNumeroCopiasPedido(rs.getInt("numeroCopiasPedido"));
            imp.setNumeroCopiasComanda(rs.getInt("numeroCopiasComanda"));
            imp.setNumeroCopiasPrefactura(rs.getInt("numeroCopiasPrefactura"));
            imp.setImpresoraPos(rs.getString("impresoraPos"));
            imp.setImpresoraMediaCarta(rs.getString("impresoraMediaCarta"));
            imp.setImpresoraCarta(rs.getString("impresoraCarta"));
            imp.setImpresoraComanda(rs.getString("impresoraComanda"));
            imp.setImpresoraPrefactura(rs.getString("impresoraPrefactura"));
            imp.setTipoPredeterminado(rs.getString("tipoPredeterminado"));
            imp.setMostrarUbicacion(rs.getString("mostrarUbicacion"));
            imp.setImprimirHora(rs.getBoolean("imprimirHora"));
            imp.setImprimirRetenciones(rs.getBoolean("imprimirRetenciones"));
            imp.setImprimirImpoconsumo(rs.getBoolean("imprimirImpoconsumo"));
            imp.setImprimirRecodiga(rs.getBoolean("imprimirRecodiga"));
            imp.setImprimirCuadreFiscal(rs.getBoolean("imprimirCuadreFiscal"));
            imp.setImpresionPorGrupo(rs.getBoolean("impresionPorGrupo"));
            imp.setFormatoImpresion(rs.getString("formatoImpresion"));
            imp.setPiePagina(rs.getString("piePagina"));
            imp.setInformacionLegal(rs.getString("informacionLegal"));
            imp.setAnexoFactura(rs.getString("anexoFactura"));
            imp.setAnexoOrdenServicio(rs.getString("anexoOrdenServicio"));
            return imp;
        } catch (SQLException e) {
            System.err.println("Error al obtener impresiones maestras: " + e.getMessage());
            return new ModeloMaestraImpresiones();
        }
    }
}
