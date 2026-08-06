package dao.Productos;

import Enums.enumBodegas;
import Estrategia.AbstractDao;
import Modelo.Productos.ResultadoBusquedaProducto;
import Modelo.Productos.ResultadoPluProducto;
import Utilidades.BaseDatos.ValidadorTabla;
import Utilidades.Utilidades;
import clases.productos.ndProducto;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoProducto extends AbstractDao {

    private static final Logger LOGGER = Logger.getLogger(DaoProducto.class.getName());

    private static final String COLUMNA_CODIGO_EFECTIVO = "codigoEfectivo";

    private static final String COLUMNAS = "idSistema, codigo, codigoBarras, Descripcion, Grupo, Sub_grupo, Proveedor, IVA, L1, L2, L3, L4, L5, L6, L7, L8, Usuario, minimo, "
            + "unidad, referencia, costo, minima, maxima, ubicacion1, descripcion2, ubicacion2, cantidad2, descripcion3, ubicacion3, cantidad3, descripcion4, ubicacion4, "
            + "cantidad4, descripcion5, ubicacion5, cantidad5, descripcion6, ubicacion6, cantidad6, descripcion7, ubicacion7, cantidad7, descripcion8, ubicacion8, cantidad8, "
            + "plu2, plu3, plu4, plu5, plu6, plu7, plu8, IVAC, ponderado, terminal, inventario, claseBuscador, manejaInventario, porcentaje, compras, ventas, nc, ajusteEntrada, "
            + "ajusteSalida, planSepare, pedidos, anulacion, inventarioInicial, ajusteInventario, fisicoInventario, armado, costeo, ordenServicio, congelada, trasladoBod, "
            + "trasladoInternoEntrada, trasladoInternoSalida, impoconsumo, descripcionIngles, cubicaje, peso, codContable, codArancel, tipoProducto, cantMedida, marca, tipoProd, "
            + "enTransito, codigo2, codigo3, codigo4, codigo5, codigo6, codigo7, codigo8, lenteMarco, adesivo, color, empaque, composicion, rmb, indVentas, impoconsumoVenta, "
            + "impoconsumoCompra, descripcionLarga, notaDebito";

    private static final String SQL_PLANTILLA = "SELECT " + COLUMNAS + ", r." + COLUMNA_CODIGO_EFECTIVO + " "
            + "FROM %s p "
            + "CROSS JOIN (SELECT COALESCE((SELECT idProducto FROM bdCodigosRelacionados WHERE codigo = ? LIMIT 1), ?) AS " + COLUMNA_CODIGO_EFECTIVO + ") r "
            + "WHERE p.Codigo = r." + COLUMNA_CODIGO_EFECTIVO + " OR p.codigoBarras = r." + COLUMNA_CODIGO_EFECTIVO + " OR p.idSistema = r." + COLUMNA_CODIGO_EFECTIVO + " "
            + "OR p.codigo2 = r." + COLUMNA_CODIGO_EFECTIVO + " OR p.codigo3 = r." + COLUMNA_CODIGO_EFECTIVO + " OR p.codigo4 = r." + COLUMNA_CODIGO_EFECTIVO + " "
            + "OR p.codigo5 = r." + COLUMNA_CODIGO_EFECTIVO + " OR p.codigo6 = r." + COLUMNA_CODIGO_EFECTIVO + " OR p.codigo7 = r." + COLUMNA_CODIGO_EFECTIVO + " OR p.codigo8 = r." + COLUMNA_CODIGO_EFECTIVO + " "
            + "LIMIT 1";

    public ResultadoBusquedaProducto buscarPorCodigo(String codigo) {
        if (codigo == null || codigo.isEmpty()) {
            return new ResultadoBusquedaProducto(new ndProducto(), codigo);
        }

        String sql = String.format(SQL_PLANTILLA, ValidadorTabla.validar(enumBodegas.TipoBodega.BODEGA_PRINCIPAL.getNombreTabla()));

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, codigo);
            ps.setString(2, codigo);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new ResultadoBusquedaProducto(mapearProducto(rs), rs.getString(COLUMNA_CODIGO_EFECTIVO));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al buscar el producto por codigo " + codigo, e);
        }

        return new ResultadoBusquedaProducto(new ndProducto(), codigo);
    }

    private ndProducto mapearProducto(ResultSet rs) throws SQLException {
        ndProducto nodo = new ndProducto();

        nodo.setIdSistema(rs.getString("idSistema"));
        nodo.setCodigo(rs.getString("codigo"));
        nodo.setCodigoBarras(rs.getString("codigoBarras"));
        nodo.setDescripcion(rs.getString("Descripcion"));
        nodo.setGrupo(rs.getString("Grupo"));
        nodo.setSubgrupo(rs.getString("Sub_grupo"));
        nodo.setProveedor(rs.getString("Proveedor"));
        nodo.setIva(rs.getString("IVA"));
        nodo.setL1(rs.getString("L1"));
        nodo.setL2(rs.getString("L2"));
        nodo.setL3(rs.getString("L3"));
        nodo.setL4(rs.getString("L4"));
        nodo.setL5(rs.getString("L5"));
        nodo.setL6(rs.getString("L6"));
        nodo.setL7(rs.getString("L7"));
        nodo.setL8(rs.getString("L8"));
        nodo.setUsuario(rs.getString("Usuario"));
        nodo.setMinimo(rs.getString("minimo"));
        nodo.setUnd(rs.getString("unidad"));
        nodo.setReferencia(rs.getString("referencia"));
        nodo.setCosto(rs.getString("costo"));
        nodo.setMinima(rs.getString("minima"));
        nodo.setMaxima(rs.getString("maxima"));
        nodo.setUbicacion1(rs.getString("ubicacion1"));
        nodo.setDescripcion2(rs.getString("descripcion2"));
        nodo.setUbicacion2(rs.getString("ubicacion2"));
        nodo.setCantidad2(rs.getString("cantidad2"));
        nodo.setDescripcion3(rs.getString("descripcion3"));
        nodo.setUbicacion3(rs.getString("ubicacion3"));
        nodo.setCantidad3(rs.getString("cantidad3"));
        nodo.setDescripcion4(rs.getString("descripcion4"));
        nodo.setUbicacion4(rs.getString("ubicacion4"));
        nodo.setCantidad4(rs.getString("cantidad4"));
        nodo.setDescripcion5(rs.getString("descripcion5"));
        nodo.setUbicacion5(rs.getString("ubicacion5"));
        nodo.setCantidad5(rs.getString("cantidad5"));
        nodo.setDescripcion6(rs.getString("descripcion6"));
        nodo.setUbicacion6(rs.getString("ubicacion6"));
        nodo.setCantidad6(rs.getString("cantidad6"));
        nodo.setDescripcion7(rs.getString("descripcion7"));
        nodo.setUbicacion7(rs.getString("ubicacion7"));
        nodo.setCantidad7(rs.getString("cantidad7"));
        nodo.setDescripcion8(rs.getString("descripcion8"));
        nodo.setUbicacion8(rs.getString("ubicacion8"));
        nodo.setCantidad8(rs.getString("cantidad8"));
        nodo.setPlu2(rs.getBoolean("plu2"));
        nodo.setPlu3(rs.getBoolean("plu3"));
        nodo.setPlu4(rs.getBoolean("plu4"));
        nodo.setPlu5(rs.getBoolean("plu5"));
        nodo.setPlu6(rs.getBoolean("plu6"));
        nodo.setPlu7(rs.getBoolean("plu7"));
        nodo.setPlu8(rs.getBoolean("plu8"));
        nodo.setIvaC(rs.getString("IVAC"));
        nodo.setPonderado(rs.getString("ponderado"));
        nodo.setTerminal(rs.getString("terminal"));
        nodo.setInventario(rs.getString("inventario"));
        nodo.setClaseBuscador(rs.getString("claseBuscador"));
        nodo.setManejaInventario(rs.getBoolean("manejaInventario"));
        nodo.setPorcentaje(rs.getString("porcentaje"));
        nodo.setCompras(rs.getString("compras"));
        nodo.setVentas(rs.getString("ventas"));
        nodo.setNc(rs.getString("nc"));
        nodo.setAjusteEntrada(rs.getString("ajusteEntrada"));
        nodo.setAjusteSalida(rs.getString("ajusteSalida"));
        nodo.setPlanSepare(rs.getString("planSepare"));
        nodo.setPedidos(rs.getString("pedidos"));
        nodo.setAnulada(rs.getString("anulacion"));
        nodo.setInventarioInicial(rs.getString("inventarioInicial"));
        nodo.setAjusteInventario(rs.getString("ajusteInventario"));
        nodo.setFisicoInventario(rs.getString("fisicoInventario"));
        nodo.setArmado(rs.getString("armado"));
        nodo.setCosteo(rs.getString("costeo"));
        nodo.setOrdenServicio(rs.getString("ordenServicio"));
        nodo.setCongelada(rs.getString("congelada"));
        nodo.setTrasladoBod(rs.getString("trasladoBod"));
        nodo.setTrasladoInternoEntrada(rs.getString("trasladoInternoEntrada"));
        nodo.setTrasladoInternoSalida(rs.getString("trasladoInternoSalida"));
        nodo.setImpoconsumo(rs.getBoolean("impoconsumo"));
        nodo.setDescripcionIngles(rs.getString("descripcionIngles"));
        nodo.setCubicaje(rs.getString("cubicaje"));
        nodo.setPeso(rs.getString("peso"));
        nodo.setCodContable(rs.getString("codContable"));
        nodo.setCodArancel(rs.getString("codArancel"));
        nodo.setTipoProducto(rs.getString("tipoProducto"));
        nodo.setCantMedida(rs.getString("cantMedida"));
        nodo.setMarca(rs.getString("marca"));
        nodo.setTipoProd(rs.getString("tipoProd"));
        nodo.setEnTransito(rs.getString("enTransito"));
        nodo.setCodigo2(rs.getString("codigo2"));
        nodo.setCodigo3(rs.getString("codigo3"));
        nodo.setCodigo4(rs.getString("codigo4"));
        nodo.setCodigo5(rs.getString("codigo5"));
        nodo.setCodigo6(rs.getString("codigo6"));
        nodo.setCodigo7(rs.getString("codigo7"));
        nodo.setCodigo8(rs.getString("codigo8"));
        nodo.setLenteMarco(rs.getString("lenteMarco"));
        nodo.setAdesivo(rs.getString("adesivo"));
        nodo.setColor(rs.getString("color"));
        nodo.setEmpaque(rs.getString("empaque"));
        nodo.setComposicion(rs.getString("composicion"));
        nodo.setRmb(rs.getString("rmb"));
        nodo.setIndVentas(rs.getString("indVentas"));
        nodo.setImpoconsumoVenta(rs.getString("impoconsumoVenta"));
        nodo.setImpoconsumoCompra(rs.getString("impoconsumoCompra"));
        nodo.setDescripcionLarga(rs.getString("descripcionLarga"));
        nodo.setNotaDebito(rs.getString("notaDebito"));

        return nodo;
    }

    public boolean productoConPlu(ndProducto nodoProducto) {
        if (nodoProducto.isPlu2() || nodoProducto.isPlu3() || nodoProducto.isPlu4() || nodoProducto.getPlu5()
                || nodoProducto.getPlu6() || nodoProducto.getPlu7() || nodoProducto.getPlu8()) {
            return true;
        }

        return false;
    }

    public ResultadoPluProducto obtenerDatosPluProducto(ndProducto nodoProducto, int plu) {

        BigDecimal fisicoInventarioActual = Utilidades.convertirBigDecimal(nodoProducto.getFisicoInventario());

        switch (plu) {
            case 2:
                fisicoInventarioActual = fisicoInventarioActual.divide(Utilidades.convertirBigDecimal(nodoProducto.getCantidad2()), 4, RoundingMode.HALF_UP);
                return new ResultadoPluProducto(
                        Utilidades.convertirBigDecimal(nodoProducto.getCantidad2()),
                        fisicoInventarioActual,
                        Utilidades.convertirBigDecimal(nodoProducto.getL2()),
                        nodoProducto.getCodigo2(),
                        nodoProducto.getDescripcion2(),
                        "L2");
            case 3:
                fisicoInventarioActual = fisicoInventarioActual.divide(Utilidades.convertirBigDecimal(nodoProducto.getCantidad3()), 4, RoundingMode.HALF_UP);
                return new ResultadoPluProducto(
                        Utilidades.convertirBigDecimal(nodoProducto.getCantidad3()),
                        fisicoInventarioActual,
                        Utilidades.convertirBigDecimal(nodoProducto.getL3()),
                        nodoProducto.getCodigo3(),
                        nodoProducto.getDescripcion3(),
                        "L3");
            case 4:
                fisicoInventarioActual = fisicoInventarioActual.divide(Utilidades.convertirBigDecimal(nodoProducto.getCantidad4()), 4, RoundingMode.HALF_UP);
                return new ResultadoPluProducto(
                        Utilidades.convertirBigDecimal(nodoProducto.getCantidad4()),
                        fisicoInventarioActual,
                        Utilidades.convertirBigDecimal(nodoProducto.getL4()),
                        nodoProducto.getCodigo4(),
                        nodoProducto.getDescripcion4(),
                        "L4");
            case 5:
                fisicoInventarioActual = fisicoInventarioActual.divide(Utilidades.convertirBigDecimal(nodoProducto.getCantidad5()), 4, RoundingMode.HALF_UP);
                return new ResultadoPluProducto(
                        Utilidades.convertirBigDecimal(nodoProducto.getCantidad5()),
                        fisicoInventarioActual,
                        Utilidades.convertirBigDecimal(nodoProducto.getL5()),
                        nodoProducto.getCodigo5(),
                        nodoProducto.getDescripcion5(),
                        "L5");
            case 6:
                fisicoInventarioActual = fisicoInventarioActual.divide(Utilidades.convertirBigDecimal(nodoProducto.getCantidad6()), 4, RoundingMode.HALF_UP);
                return new ResultadoPluProducto(
                        Utilidades.convertirBigDecimal(nodoProducto.getCantidad6()),
                        fisicoInventarioActual,
                        Utilidades.convertirBigDecimal(nodoProducto.getL6()),
                        nodoProducto.getCodigo6(),
                        nodoProducto.getDescripcion6(),
                        "L6");
            case 7:
                fisicoInventarioActual = fisicoInventarioActual.divide(Utilidades.convertirBigDecimal(nodoProducto.getCantidad7()), 4, RoundingMode.HALF_UP);
                return new ResultadoPluProducto(
                        Utilidades.convertirBigDecimal(nodoProducto.getCantidad7()),
                        fisicoInventarioActual,
                        Utilidades.convertirBigDecimal(nodoProducto.getL7()),
                        nodoProducto.getCodigo7(),
                        nodoProducto.getDescripcion7(),
                        "L7");
            case 8:
                fisicoInventarioActual = fisicoInventarioActual.divide(Utilidades.convertirBigDecimal(nodoProducto.getCantidad8()), 4, RoundingMode.HALF_UP);
                return new ResultadoPluProducto(
                        Utilidades.convertirBigDecimal(nodoProducto.getCantidad8()),
                        fisicoInventarioActual,
                        Utilidades.convertirBigDecimal(nodoProducto.getL8()),
                        nodoProducto.getCodigo8(),
                        nodoProducto.getDescripcion8(),
                        "L8");
            default:
                return new ResultadoPluProducto(
                        BigDecimal.ONE,
                        fisicoInventarioActual,
                        Utilidades.convertirBigDecimal(nodoProducto.getL1()),
                        nodoProducto.getCodigo(),
                        nodoProducto.getDescripcion(),
                        "L1");
        }
    }
}
