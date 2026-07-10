package dao.Configuraciones;

import clases.ndMaestra;
import Utilidades.BaseDatos.MySql_connection;
import Utilidades.Constantes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoMaestra {

    private final Connection conexion = MySql_connection.getInstancia(Constantes.BASE_DATOS_PRINCIPAL).getConnection();

    public ndMaestra obtenerMaestra() {
        String sql = "SELECT l1, l2, l4, c1, c2, c3, c4, c5, d1, d2, d3, d4, d5, d6, d7, pie, legal, "
                + "c6, c7, recogida, lector, tituloFactura, pvpSinIva, costoSinIva, ventasPredeterminado, mensajeUtilidad, "
                + "consecutivosDiferentes, resolucionIgual, diasAlertaResolucion, alertaFechaDias, alertaCantidadNumeracion, alertaPromedioDias, "
                + "diasCobroMora, porcentajeMora, generaOrdenMedica, imprimirOrdenMedica, imprimirFacturaOrdenMedica, "
                + "horaInicioAgenda, horaFinAgenda, intervaloAgenda, codPrestadorServicio, "
                + "previsualizarFactura, imprimirCuadreFiscal, ocultarInformacionCliente, visualizarTodasLasFacturas, "
                + "mostrarInformacionCuadre, descuentoMaximoVentas, tipoIdPrestadorServicio, otros, domicilios, limite, "
                + "combinarProductos, modificarNombre, impBolsa, valorBolsa, turno, turno1, anexoFacturacion, "
                + "copiasCotizacion, consecutivo, hora, pondNegativo, anexoOrdenServicio, modificarPrecio, borrarCongelada, "
                + "copiasFactura, copiasOServicio, copiasPlanSepare, copiasPedido, "
                + "previsualizarOServicio, previsualizarCotizacion, previsualizarPlanSepare, previsualizarPedido, "
                + "numFactura, numOServicio, numCotizacion, numPlanSepare, numPedido, "
                + "pagoTerceros, facturarSinInventario, reimpresion, impresoraPos, impresoraMediaCarta, impresoraCarta, "
                + "verImpoconsumo, verRetenciones, imprimirCada, cantidadEstablecida, filas, columnas, "
                + "porcPropina, mostrarDevuelta, inicioHosp, finHosp, intervalosHosp, diasAutomaticos, ciudadBuscador, foco, "
                + "pvpImpoconsumo, costoImpoconsumo, impresionPorGrupo, copiasComanda, copiasPrefactura, "
                + "previsualizarComanda, previsualizarPrefactura, impresoraComanda, impresoraPrefactura, "
                + "meserosFacturarMesas, soloMesas, borrarMesas, idUsuario, password, numFacturaIncremento, "
                + "responsabilidades, gmail, passGmail FROM bdMaestra WHERE Id = 1";

        try (PreparedStatement stmt = conexion.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                ndMaestra m = new ndMaestra();
                m.setL1(rs.getString("l1"));
                m.setL2(rs.getString("l2"));
                m.setL4(rs.getString("l4"));
                m.setC1(rs.getString("c1"));
                m.setC2(rs.getString("c2"));
                m.setC3(rs.getString("c3"));
                m.setC4(rs.getString("c4"));
                m.setC5(rs.getString("c5"));
                m.setD1(rs.getString("d1"));
                m.setD2(rs.getString("d2"));
                m.setD3(rs.getString("d3"));
                m.setD4(rs.getString("d4"));
                m.setD5(rs.getString("d5"));
                m.setD6(rs.getString("d6"));
                m.setD7(rs.getString("d7"));
                m.setPie(rs.getString("pie"));
                m.setLegal(rs.getString("legal"));
                m.setC6(rs.getString("c6"));
                m.setC7(rs.getString("c7"));
                m.setRecogida(rs.getBoolean("recogida"));
                m.setLector(rs.getBoolean("lector"));
                m.setTituloFactura(rs.getString("tituloFactura"));
                m.setPvpSinIva(rs.getBoolean("pvpSinIva"));
                m.setCostoSinIva(rs.getBoolean("costoSinIva"));
                m.setVentasPredeterminado(rs.getBoolean("ventasPredeterminado"));
                m.setMensajeUtilidad(rs.getBoolean("mensajeUtilidad"));
                m.setConsecutivosDiferentes(rs.getBoolean("consecutivosDiferentes"));
                m.setResolucionIgual(rs.getBoolean("resolucionIgual"));
                m.setDiasAlertaResolucion(rs.getString("diasAlertaResolucion"));
                m.setAlertaFechaDias(rs.getString("alertaFechaDias"));
                m.setAlertaCantidadNumeracion(rs.getString("alertaCantidadNumeracion"));
                m.setAlertaPromedioDias(rs.getString("alertaPromedioDias"));
                m.setDiasCobrarMora(rs.getString("diasCobroMora"));
                m.setPorcentajeMora(rs.getString("porcentajeMora"));
                m.setGeneraOrdenMedica(rs.getBoolean("generaOrdenMedica"));
                m.setImprimirOrdenMedica(rs.getBoolean("imprimirOrdenMedica"));
                m.setImprimirFacturaOrdenMedica(rs.getBoolean("imprimirFacturaOrdenMedica"));
                m.setHoraInicioAgenda(rs.getString("horaInicioAgenda"));
                m.setHoraFinAgenda(rs.getString("horaFinAgenda"));
                m.setIntervaloAgenda(rs.getString("intervaloAgenda"));
                m.setCodigoPrestadorServicio(rs.getString("codPrestadorServicio"));
                m.setPrevisualizarFactura(rs.getBoolean("previsualizarFactura"));
                m.setImprimirCuadreFiscal(rs.getBoolean("imprimirCuadreFiscal"));
                m.setOcultarInformacionCliente(rs.getBoolean("ocultarInformacionCliente"));
                m.setVisualizarTodasLasFacturas(rs.getBoolean("visualizarTodasLasFacturas"));
                m.setMostrarInformacionCuadre(rs.getBoolean("mostrarInformacionCuadre"));
                m.setDescuentoMaximoVentas(rs.getString("descuentoMaximoVentas"));
                m.setTipoPrestadorServicio(rs.getString("tipoIdPrestadorServicio"));
                m.setOtros(rs.getString("otros"));
                m.setDomicilios(rs.getString("domicilios"));
                m.setLimite(rs.getString("limite"));
                m.setCombinarProductos(rs.getBoolean("combinarProductos"));
                m.setModificarNombre(rs.getBoolean("modificarNombre"));
                m.setImpBolsa(rs.getBoolean("impBolsa"));
                m.setValorBolsa(rs.getString("valorBolsa"));
                m.setTurno(rs.getBoolean("turno"));
                m.setTurno1(rs.getString("turno1"));
                m.setAnexoFacturacion(rs.getString("anexoFacturacion"));
                m.setCopiasCotizacion(rs.getBoolean("copiasCotizacion"));
                m.setConsecutivo(rs.getBoolean("consecutivo"));
                m.setHora(rs.getBoolean("hora"));
                m.setPondNegativo(rs.getBoolean("pondNegativo"));
                m.setAnexoOrdenServicio(rs.getString("anexoOrdenServicio"));
                m.setModificarPrecio(rs.getBoolean("modificarPrecio"));
                m.setBorrarCongelada(rs.getBoolean("borrarCongelada"));
                m.setCopiasFactura(rs.getBoolean("copiasFactura"));
                m.setCopiasOServicio(rs.getBoolean("copiasOServicio"));
                m.setCopiasPlanSepare(rs.getBoolean("copiasPlanSepare"));
                m.setCopiasPedido(rs.getBoolean("copiasPedido"));
                m.setPrevisualizarOServicio(rs.getBoolean("previsualizarOServicio"));
                m.setPrevisualizarCotizacion(rs.getBoolean("previsualizarCotizacion"));
                m.setPrevisualizarPlanSepare(rs.getBoolean("previsualizarPlanSepare"));
                m.setPrevisualizarPedido(rs.getBoolean("previsualizarPedido"));
                m.setNumFactura(rs.getString("numFactura"));
                m.setNumOServicio(rs.getString("numOServicio"));
                m.setNumCotizacion(rs.getString("numCotizacion"));
                m.setNumPlanSepare(rs.getString("numPlanSepare"));
                m.setNumPedido(rs.getString("numPedido"));
                m.setPagosTerceros(rs.getBoolean("pagoTerceros"));
                m.setFacturarSeparado(rs.getBoolean("facturarSinInventario"));
                m.setReimpresion(rs.getBoolean("reimpresion"));
                m.setImpresoraPos(rs.getString("impresoraPos"));
                m.setImpresoraMediaCarta(rs.getString("impresoraMediaCarta"));
                m.setImpresoraCarta(rs.getString("impresoraCarta"));
                m.setMostrarImpoconsumo(rs.getBoolean("verImpoconsumo"));
                m.setMostrarRetenciones(rs.getBoolean("verRetenciones"));
                m.setImprimirCada(rs.getString("imprimirCada"));
                m.setCantidadEstablecida(rs.getString("cantidadEstablecida"));
                m.setFilas(rs.getString("filas"));
                m.setColumnas(rs.getString("columnas"));
                m.setPorcPropina(rs.getString("porcPropina"));
                m.setMostrarDevuelta(rs.getBoolean("mostrarDevuelta"));
                m.setInicioHosp(rs.getString("inicioHosp"));
                m.setFinHosp(rs.getString("finHosp"));
                m.setIntervalosHosp(rs.getString("intervalosHosp"));
                m.setDiasAutomaticos(rs.getBoolean("diasAutomaticos"));
                m.setCiudadBuscador(rs.getBoolean("ciudadBuscador"));
                m.setFoco(rs.getString("foco"));
                m.setPvpImpoconsumo(rs.getBoolean("pvpImpoconsumo"));
                m.setCostoImpoconsumo(rs.getBoolean("costoImpoconsumo"));
                m.setImpresionPorGrupo(rs.getBoolean("impresionPorGrupo"));
                m.setCopiasComanda(rs.getString("copiasComanda"));
                m.setCopiasPrefactura(rs.getString("copiasPrefactura"));
                m.setPrevisualizarComanda(rs.getBoolean("previsualizarComanda"));
                m.setPrevisualizarPrefactura(rs.getBoolean("previsualizarPrefactura"));
                m.setImpresoraComanda(rs.getString("impresoraComanda"));
                m.setImpresoraPrefactura(rs.getString("impresoraPrefactura"));
                m.setFacturarMesas(rs.getBoolean("meserosFacturarMesas"));
                m.setSoloMesas(rs.getBoolean("soloMesas"));
                m.setBorrarMesas(rs.getBoolean("borrarMesas"));
                m.setIdCliente(rs.getString("idUsuario"));
                m.setPassword(rs.getString("password"));
                m.setNumFacturaIncremento(rs.getString("numFacturaIncremento"));
                m.setResponsabilidadesFiscales(rs.getString("responsabilidades"));
                m.setGmail(rs.getString("gmail"));
                m.setPasswordGmail(rs.getString("passGmail"));
                return m;
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener datos maestros: " + e.getMessage());
        }
        return null;
    }
}
