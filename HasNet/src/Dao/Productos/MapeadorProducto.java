package Dao.Productos;

import Modelo.Productos.ModeloProducto;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class MapeadorProducto {

    private MapeadorProducto() {
    }

    public static ModeloProducto mapear(ResultSet rs) throws SQLException {
        ModeloProducto nodo = new ModeloProducto();

        nodo.setIdSistema(rs.getString("idSistema"));
        nodo.setCodigo(rs.getString("codigo"));
        nodo.setCodigoBarras(rs.getString("codigoBarras"));
        nodo.setDescripcion(rs.getString("Descripcion"));
        nodo.setGrupo(rs.getString("Grupo"));
        nodo.setSubgrupo(rs.getString("Sub_grupo"));
        nodo.setProveedor(rs.getString("Proveedor"));
        nodo.setIva(rs.getString("IVA"));
        nodo.setL1(rs.getString("L1"));
        nodo.setUsuario(rs.getString("Usuario"));
        nodo.setMinimo(rs.getString("minimo"));
        nodo.setUnd(rs.getString("unidad"));
        nodo.setReferencia(rs.getString("referencia"));
        nodo.setCosto(rs.getString("costo"));
        nodo.setMinima(rs.getString("minima"));
        nodo.setMaxima(rs.getString("maxima"));
        nodo.setUbicacion1(rs.getString("ubicacion1"));
        nodo.setPonderado(rs.getString("ponderado"));
        nodo.setTerminal(rs.getString("terminal"));
        nodo.setClaseBuscador(rs.getString("claseBuscador"));
        nodo.setManejaInventario(rs.getBoolean("manejaInventario"));
        nodo.setPorcentaje(rs.getString("porcentaje"));
        nodo.setCubicaje(rs.getString("cubicaje"));
        nodo.setPeso(rs.getString("peso"));
        nodo.setCodContable(rs.getString("codContable"));
        nodo.setCodArancel(rs.getString("codArancel"));
        nodo.setTipoProducto(rs.getString("tipoProducto"));
        nodo.setCantMedida(rs.getString("cantMedida"));
        nodo.setMarca(rs.getString("marca"));
        nodo.setTipoProd(rs.getString("tipoProd"));
        nodo.setLenteMarco(rs.getString("lenteMarco"));
        nodo.setAdesivo(rs.getString("adesivo"));
        nodo.setColor(rs.getString("color"));
        nodo.setEmpaque(rs.getString("empaque"));
        nodo.setComposicion(rs.getString("composicion"));
        nodo.setRmb(rs.getString("rmb"));
        nodo.setIndVentas(rs.getString("indVentas"));
        nodo.setImpoconsumoVenta(rs.getString("impoconsumoVenta"));
        nodo.setDescripcionLarga(rs.getString("descripcionLarga"));

        return nodo;
    }
}
