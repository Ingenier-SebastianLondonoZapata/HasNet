package dao.Ventas;

import Modelo.Ventas.CabeceraDocumento;
import Modelo.Ventas.DocumentoMovimiento;
import Modelo.Ventas.LineaProducto;
import clases.Cartera.ndCxc;
import clases.Ventas.ndOServicio;
import clases.Ventas.ndPedido;
import clases.Ventas.ndPlanSepare;
import dao.Generales.DaoGenerales;
import Utilidades.BaseDatos.MySql_connection;
import Utilidades.Constantes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoFactura {

    private final DaoGenerales daoGenerales = new DaoGenerales();
    private final Connection conexion = MySql_connection.getInstancia(Constantes.BASE_DATOS_PRINCIPAL).getConnection();

    // -------------------------------------------------------------------------
    // Métodos de carga que retornan modelos tipados (para cargarMovimiento)
    // -------------------------------------------------------------------------

    public DocumentoMovimiento cargarCotizacion(String idCotizacion) {
        Object[][] mat = getRegistrosCotizas(idCotizacion);
        if (mat.length == 0) return DocumentoMovimiento.vacio();
        return new DocumentoMovimiento(parsearCabeceraCotizacion(mat), parsearLineasCotizacion(mat));
    }

    public DocumentoMovimiento cargarOrdenServicio(String idOrden) {
        Object[][] mat = getRegistrosOrdenes(idOrden);
        if (mat.length == 0) return DocumentoMovimiento.vacio();
        return new DocumentoMovimiento(parsearCabeceraOrden(mat), parsearLineasOrden(mat));
    }

    public DocumentoMovimiento cargarMovimientoPrefactura(String idFactura) {
        Object[][] mat = getRegistrosPrefacturas(idFactura);
        if (mat.length == 0) return DocumentoMovimiento.vacio();
        return new DocumentoMovimiento(parsearCabeceraPrefactura(mat), parsearLineasPrefactura(mat));
    }

    // -------------------------------------------------------------------------
    // Parsers de cabecera — mapean columnas del Object[][] a CabeceraDocumento
    // -------------------------------------------------------------------------

    private CabeceraDocumento parsearCabeceraCotizacion(Object[][] mat) {
        // Columnas: 0=producto,1=descripcion,2=lista,3=cantidad,4=subtotal,5=porcDesc,
        //  6=descuento,7=porcIva,8=iva,9=total,10=plu,11=cant2,12=cliente,
        //  13=subtotalGeneral,14=descuentoGeneral,15=ivaGeneral,16=totalGeneral,
        //  17=rango,18=estadoGeneral,19=vendedor,20=observacion,21=bodega
        CabeceraDocumento cab = new CabeceraDocumento();
        cab.setClienteId(mat[0][12].toString());
        cab.setVendedor(mat[0][19] != null ? mat[0][19].toString() : null);
        cab.setSubtotal(mat[0][13].toString());
        cab.setTotalDescuentos(mat[0][14].toString());
        cab.setTotalIva(mat[0][15].toString());
        cab.setTotal(mat[0][16].toString());
        cab.setEstadoGeneral(mat[0][18].toString());
        cab.setObservacion(mat[0][20] != null ? mat[0][20].toString() : null);
        cab.setBodega(mat[0][21] != null ? mat[0][21].toString() : null);
        return cab;
    }

    private CabeceraDocumento parsearCabeceraOrden(Object[][] mat) {
        // Columnas: 0=producto,...,10=cliente,11=vendedor,12=estado,13=plu,14=cant2,
        //  15=subtotalGeneral,16=descuentoGeneral,17=ivaGeneral,18=totalGeneral,
        //  19=observacion,20=estadoGeneral,21=placaReal
        CabeceraDocumento cab = new CabeceraDocumento();
        cab.setClienteId(mat[0][10] != null ? mat[0][10].toString() : null);
        cab.setVendedor(mat[0][11] != null ? mat[0][11].toString() : null);
        cab.setSubtotal(mat[0][15].toString());
        cab.setTotalDescuentos(mat[0][16].toString());
        cab.setTotalIva(mat[0][17].toString());
        cab.setTotal(mat[0][18].toString());
        cab.setObservacion(mat[0][19] != null ? mat[0][19].toString() : null);
        cab.setEstadoGeneral(mat[0][20].toString());
        cab.setPlacaReal(mat[0][21] != null ? mat[0][21].toString() : null);
        return cab;
    }

    private CabeceraDocumento parsearCabeceraPrefactura(Object[][] mat) {
        // Columnas: 0=producto,...,12=estado,13=plu,14=cant2,15=vendedor,16=cliente,
        //  17=subtotalGeneral,18=descuentoGeneral,19=ivaGeneral,20=totalGeneral,
        //  21=rango,22=imei,23=idProd
        CabeceraDocumento cab = new CabeceraDocumento();
        cab.setVendedor(mat[0][15].toString());
        cab.setClienteId(mat[0][16].toString());
        cab.setSubtotal(mat[0][17].toString());
        cab.setTotalDescuentos(mat[0][18].toString());
        cab.setTotalIva(mat[0][19].toString());
        cab.setTotal(mat[0][20].toString());
        return cab;
    }

    // -------------------------------------------------------------------------
    // Parsers de líneas — mapean cada fila del Object[][] a LineaProducto
    // -------------------------------------------------------------------------

    private List<LineaProducto> parsearLineasCotizacion(Object[][] mat) {
        List<LineaProducto> lineas = new ArrayList<LineaProducto>();
        for (Object[] reg : mat) {
            int plu = Integer.parseInt(reg[10].toString());
            LineaProducto linea = new LineaProducto();
            linea.setCodigo(reg[0].toString());
            linea.setDescripcion(reg[1].toString());
            linea.setPrecio(reg[2].toString());
            linea.setPlu(plu);
            linea.setCantidad(plu == 1 ? toDouble(reg[3]) : toDouble(reg[11]));
            linea.setPorcDescuento(reg[5].toString());
            linea.setRango(reg[17] != null ? reg[17].toString() : "");
            lineas.add(linea);
        }
        return lineas;
    }

    private List<LineaProducto> parsearLineasOrden(Object[][] mat) {
        List<LineaProducto> lineas = new ArrayList<LineaProducto>();
        for (Object[] reg : mat) {
            int plu = Integer.parseInt(reg[13].toString());
            LineaProducto linea = new LineaProducto();
            linea.setCodigo(reg[0].toString());
            linea.setDescripcion(reg[1].toString());
            linea.setPrecio(reg[2].toString());
            linea.setPlu(plu);
            linea.setCantidad(plu == 1 ? toDouble(reg[3]) : toDouble(reg[14]));
            linea.setPorcDescuento(reg[5].toString());
            linea.setDescuento(reg[6].toString());
            linea.setPreparacion(reg[12] != null ? reg[12].toString() : "");
            linea.setImei("");
            linea.setIdProd("");
            lineas.add(linea);
        }
        return lineas;
    }

    private List<LineaProducto> parsearLineasPrefactura(Object[][] mat) {
        List<LineaProducto> lineas = new ArrayList<LineaProducto>();
        for (Object[] reg : mat) {
            int plu = Integer.parseInt(reg[13].toString());
            LineaProducto linea = new LineaProducto();
            linea.setCodigo(reg[0].toString());
            linea.setDescripcion(reg[1].toString());
            linea.setPrecio(reg[2].toString());
            linea.setPlu(plu);
            linea.setCantidad(plu == 1 ? toDouble(reg[3]) : toDouble(reg[14]));
            linea.setPorcDescuento(reg[5].toString());
            linea.setDescuento(reg[6].toString());
            linea.setPreparacion(reg[12] != null ? reg[12].toString() : "");
            linea.setRango(reg[21] != null ? reg[21].toString() : "");
            linea.setImei(reg[22] != null ? reg[22].toString() : "");
            linea.setIdProd(reg[23] != null ? reg[23].toString() : "");
            lineas.add(linea);
        }
        return lineas;
    }

    /** Convierte cualquier valor numérico a String en formato Double (ej: "10.0"). */
    private String toDouble(Object valor) {
        return String.valueOf(Double.parseDouble(valor.toString()));
    }

    // -------------------------------------------------------------------------
    // Consultas base — retornan Object[][] (se mantienen para compatibilidad)
    // -------------------------------------------------------------------------

    public Object[][] getRegistrosCotizas(String idFactura) {
        String[] columnas = {
            "producto", "descripcion", "lista", "cantidad", "subtotal", "porcDescuento", "descuento",
            "porcIva", "iva", "total", "plu", "cant2", "cliente", "subtotalGeneral", "descuentoGeneral",
            "ivaGeneral", "totalGeneral", "rango", "estadoGeneral", "vendedor", "observacion", "bodega"
        };
        String sql = "SELECT producto, descripcion, lista, cantidad, subtotal, porcDescuento, descuento, "
                + "porcIva, iva, total, plu, cant2, cliente, subtotalGeneral, descuentoGeneral, ivaGeneral, "
                + "totalGeneral, rango, estadoGeneral, vendedor, observacion, bodega "
                + "FROM cotizacion WHERE idFactura = '" + idFactura + "'";
        return daoGenerales.obtenerDatosTabla(columnas, sql);
    }

    public Object[][] getRegistrosOrdenes(String idFactura) {
        String[] columnas = {
            "producto", "descripcion", "lista", "cantidad", "subtotal", "porcDescuento", "descuento",
            "porcIva", "iva", "total", "cliente", "vendedor", "estado", "plu", "cant2",
            "subtotalGeneral", "descuentoGeneral", "ivaGeneral", "totalGeneral", "observacion",
            "estadoGeneral", "placaReal"
        };
        String sql = "SELECT producto, descripcion, lista, cantidad, subtotal, porcDescuento, descuento, "
                + "porcIva, iva, total, cliente, vendedor, estado, plu, cant2, subtotalGeneral, "
                + "descuentoGeneral, ivaGeneral, totalGeneral, observacion, estadoGeneral, placaReal "
                + "FROM ordenServicio WHERE idFactura = '" + idFactura + "'";
        return daoGenerales.obtenerDatosTabla(columnas, sql);
    }

    public Object[][] getRegistrosPrefacturas(String factura) {
        String[] columnas = {
            "producto", "descripcion", "lista", "cantidad", "subtotal", "porcDescuento", "descuento",
            "porcIva", "iva", "total", "ubicacion1", "referencia", "estado", "plu", "cant2",
            "vendedor", "cliente", "subtotalGeneral", "descuentoGeneral", "ivaGeneral", "totalGeneral",
            "rango", "imei", "idProd"
        };
        String sql = "SELECT producto, descripcion, lista, cantidad, subtotal, porcDescuento, descuento, "
                + "porcIva, iva, total, ubicacion1, referencia, estado, plu, cant2, vendedor, cliente, "
                + "subtotalGeneral, descuentoGeneral, ivaGeneral, totalGeneral, rango, imei, idProd "
                + "FROM factura WHERE factura = '" + factura + "'";
        return daoGenerales.obtenerDatosTabla(columnas, sql);
    }

    public Object[][] getEstadoVehiculo(String orden) {
        String[] columnas = {"idParte", "nombreParte", "problemasDerecha", "problemasIzquierda", "observaciones"};
        String sql = "SELECT idParte, nombreParte, problemasDerecha, problemasIzquierda, observaciones "
                + "FROM bdDetalleOrdenServicio WHERE ordenServicio = '" + orden + "'";
        return daoGenerales.obtenerDatosTabla(columnas, sql);
    }

    // -------------------------------------------------------------------------
    // Consultas de nodos únicos
    // -------------------------------------------------------------------------

    public ndOServicio getDatosOServicio(String id) {
        String sql = "SELECT id, placa, tipo, modelo, numeroChasis, fechaCompra, marca, km, numeroMotor, color, problema "
                + "FROM bdOServicio WHERE id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ndOServicio nodo = new ndOServicio();
                    nodo.setId(rs.getString("id"));
                    nodo.setPlaca(rs.getString("placa"));
                    nodo.setTipo(rs.getString("tipo"));
                    nodo.setModelo(rs.getString("modelo"));
                    nodo.setNumeroChasis(rs.getString("numeroChasis"));
                    nodo.setFechaCompra(rs.getString("fechaCompra"));
                    nodo.setMarca(rs.getString("marca"));
                    nodo.setKm(rs.getString("km"));
                    nodo.setNumeroMotor(rs.getString("numeroMotor"));
                    nodo.setColor(rs.getString("color"));
                    nodo.setProblema(rs.getString("problema"));
                    return nodo;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener datos de orden de servicio: " + e.getMessage());
        }
        return new ndOServicio();
    }

    public ndCxc getDatosCxc(String factura) {
        String sql = "SELECT factura, tipo, estado, recibo, valor, plazo, vencimiento, usuario, terminal, cuotas, factura2 "
                + "FROM bdCxc WHERE factura = ? AND (tipo = 'FACT' OR tipo = 'SEPARE')";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, factura);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ndCxc nodo = new ndCxc();
                    nodo.setFactura(rs.getString("factura"));
                    nodo.setTipo(rs.getString("tipo"));
                    nodo.setEstado(rs.getString("estado"));
                    nodo.setRecibo(rs.getString("recibo"));
                    nodo.setValor(rs.getString("valor"));
                    nodo.setPlazo(rs.getInt("plazo"));
                    nodo.setVencimiento(rs.getString("vencimiento"));
                    nodo.setUsuario(rs.getString("usuario"));
                    nodo.setTerminal(rs.getString("terminal"));
                    nodo.setCuotas(rs.getBoolean("cuotas"));
                    nodo.setFactura2(rs.getString("factura2"));
                    return nodo;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener datos de CXC: " + e.getMessage());
        }
        return new ndCxc();
    }

    public ndPlanSepare getDatosPlanSepare(String id) {
        String sql = "SELECT idFactura, estadoGeneral, observacion FROM bdPlanSepare WHERE idFactura = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ndPlanSepare nodo = new ndPlanSepare();
                    nodo.setIdFactura(rs.getString("idFactura"));
                    nodo.setEstadoGeneral(rs.getString("estadoGeneral"));
                    nodo.setObservacion(rs.getString("observacion"));
                    return nodo;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener datos de plan separe: " + e.getMessage());
        }
        return new ndPlanSepare();
    }

    public ndPedido getDatosPedido(String id) {
        String sql = "SELECT idFactura, estadoGeneral, observacion FROM bdPedido WHERE idFactura = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ndPedido nodo = new ndPedido();
                    nodo.setIdFactura(rs.getString("idFactura"));
                    nodo.setEstadoGeneral(rs.getString("estadoGeneral"));
                    nodo.setObservacion(rs.getString("observacion"));
                    return nodo;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener datos de pedido: " + e.getMessage());
        }
        return new ndPedido();
    }
}
