package dao.Ventas;

import Modelo.Ventas.CabeceraDocumento;
import Modelo.Ventas.DocumentoMovimiento;
import Modelo.Ventas.LineaProducto;
import Utilidades.Utilidades;
import clases.Cartera.ndCxc;
import clases.Ventas.ndFactura;
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
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoFactura {

    private static final Logger LOGGER = Logger.getLogger(DaoFactura.class.getName());
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

    public DocumentoMovimiento cargarPlanSepare(String id) {
        ndPlanSepare nodo = getDatosPlanSepare(id);
        if (nodo.getIdFactura() == null) return DocumentoMovimiento.vacio();
        Object[][] mat = getRegistrosPlanSepare(id);
        return new DocumentoMovimiento(parsearCabeceraPlanSepare(nodo), parsearLineasPlanSepare(mat));
    }

    public DocumentoMovimiento cargarPedido(String id) {
        ndPedido nodo = getDatosPedido(id);
        if (nodo.getIdFactura() == null) return DocumentoMovimiento.vacio();
        Object[][] mat = getRegistrosPedido(id);
        return new DocumentoMovimiento(parsearCabeceraPedido(nodo), parsearLineasPedido(mat));
    }

    public DocumentoMovimiento cargarMesa(String id) {
        Object[][] mat = getRegistrosMesa(id);
        if (mat.length == 0) return DocumentoMovimiento.vacio();
        return new DocumentoMovimiento(parsearCabeceraMesa(mat), parsearLineasMesa(mat));
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

    private CabeceraDocumento parsearCabeceraPlanSepare(ndPlanSepare nodo) {
        CabeceraDocumento cab = new CabeceraDocumento();
        cab.setClienteId(nodo.getCliente());
        cab.setVendedor(nodo.getVendedor());
        cab.setSubtotal(nodo.getSubtotalGeneral());
        cab.setTotalDescuentos(nodo.getDescuentoGeneral());
        cab.setTotalIva(nodo.getIvaGeneral());
        cab.setTotal(nodo.getTotalGeneral());
        cab.setEstadoGeneral(nodo.getEstadoGeneral());
        cab.setObservacion(nodo.getObservacion());
        return cab;
    }

    private List<LineaProducto> parsearLineasPlanSepare(Object[][] mat) {
        // Columnas: 0=producto,1=descripcion,2=lista,3=cantidad,4=subtotal,5=porcDescuento,
        //  6=descuento,7=porcIva,8=iva,9=total,10=ubicacion,11=referencia,12=estado,
        //  13=plu,14=cant2,15=imei,16=idProd
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
            linea.setImei(reg[15] != null ? reg[15].toString() : "");
            linea.setIdProd(reg[16] != null ? reg[16].toString() : "");
            lineas.add(linea);
        }
        return lineas;
    }

    private CabeceraDocumento parsearCabeceraPedido(ndPedido nodo) {
        CabeceraDocumento cab = new CabeceraDocumento();
        cab.setClienteId(nodo.getCliente());
        cab.setVendedor(nodo.getVendedor());
        cab.setSubtotal(nodo.getSubtotalGeneral());
        cab.setTotalDescuentos(nodo.getDescuentoGeneral());
        cab.setTotalIva(nodo.getIvaGeneral());
        cab.setTotal(nodo.getTotalGeneral());
        cab.setEstadoGeneral(nodo.getEstadoGeneral());
        cab.setObservacion(nodo.getObservacion());
        return cab;
    }

    private List<LineaProducto> parsearLineasPedido(Object[][] mat) {
        // Columnas: 0=producto,1=descripcion,2=lista,3=cantidad,4=subtotal,5=porcDescuento,
        //  6=descuento,7=porcIva,8=iva,9=total,10=ubicacion1,11=referencia,12=estado,
        //  13=plu,14=cant2,15=preparacion,16=rango,17=idProd
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
            linea.setPreparacion(reg[15] != null ? reg[15].toString() : "");
            linea.setRango(reg[16] != null ? reg[16].toString() : "");
            linea.setIdProd(reg[17] != null ? reg[17].toString() : "");
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

    private CabeceraDocumento parsearCabeceraMesa(Object[][] mat) {
        // Columnas: 0=cliente,1=vendedor,2=subtotalGeneral,3=descuentoGeneral,4=ivaGeneral,
        //  5=totalGeneral,6=estadoGeneral,7=observacion
        CabeceraDocumento cab = new CabeceraDocumento();
        cab.setClienteId(mat[0][0] != null ? mat[0][0].toString() : null);
        cab.setVendedor(mat[0][1] != null ? mat[0][1].toString() : null);
        cab.setSubtotal(mat[0][2].toString());
        cab.setTotalDescuentos(mat[0][3].toString());
        cab.setTotalIva(mat[0][4].toString());
        cab.setTotal(mat[0][5].toString());
        cab.setEstadoGeneral(mat[0][6] != null ? mat[0][6].toString() : null);
        cab.setObservacion(mat[0][7] != null ? mat[0][7].toString() : null);
        return cab;
    }

    private List<LineaProducto> parsearLineasMesa(Object[][] mat) {
        // Columnas: 8=producto,9=descripcion,10=lista,11=cantidad,12=cant2,13=porcDescuento,
        //  14=descuento,15=plu,16=preparacion,17=imei,18=idProd
        List<LineaProducto> lineas = new ArrayList<>();
        for (Object[] reg : mat) {
            int plu = Integer.parseInt(reg[15].toString());
            LineaProducto linea = new LineaProducto();
            linea.setCodigo(reg[8].toString());
            linea.setDescripcion(reg[9].toString());
            linea.setPrecio(reg[10].toString());
            linea.setPlu(plu);
            linea.setCantidad(plu == 1 ? toDouble(reg[11]) : toDouble(reg[12]));
            linea.setPorcDescuento(reg[13].toString());
            linea.setDescuento(reg[14].toString());
            linea.setPreparacion(reg[16] != null ? reg[16].toString() : "");
            linea.setImei(reg[17] != null ? reg[17].toString() : "");
            linea.setIdProd(reg[18] != null ? reg[18].toString() : "");
            lineas.add(linea);
        }
        return lineas;
    }

    private Object[][] getRegistrosMesa(String idFactura) {
        String[] columnas = {
            "cliente", "vendedor", "subtotalGeneral", "descuentoGeneral", "ivaGeneral", "totalGeneral",
            "estadoGeneral", "observacion",
            "producto", "descripcion", "lista", "cantidad", "cant2", "porcDescuento", "descuento", "plu",
            "preparacion", "imei", "idProd"
        };
        String sql = "SELECT cliente, vendedor, subtotalGeneral, descuentoGeneral, ivaGeneral, totalGeneral, "
                + "estadoGeneral, observacion, "
                + "producto, descripcion, lista, cantidad, cant2, porcDescuento, descuento, plu, "
                + "preparacion, imei, idProd "
                + "FROM bdCongelada WHERE idFactura = '" + idFactura + "'";
        return daoGenerales.obtenerDatosTabla(columnas, sql);
    }

    private Object[][] getRegistrosPlanSepare(String idFactura) {
        String[] columnas = {
            "producto", "descripcion", "lista", "cantidad", "subtotal", "porcDescuento", "descuento",
            "porcIva", "iva", "total", "ubicacion", "referencia", "estado", "plu", "cant2", "imei", "idProd"
        };
        String sql = "SELECT producto, descripcion, lista, cantidad, subtotal, porcDescuento, descuento, "
                + "porcIva, iva, total, ubicacion, referencia, estado, plu, cant2, imei, idProd "
                + "FROM planSepare WHERE idFactura = '" + idFactura + "'";
        return daoGenerales.obtenerDatosTabla(columnas, sql);
    }

    private Object[][] getRegistrosPedido(String idFactura) {
        String[] columnas = {
            "producto", "descripcion", "lista", "cantidad", "subtotal", "porcDescuento", "descuento",
            "porcIva", "iva", "total", "ubicacion1", "referencia", "estado", "plu", "cant2",
            "preparacion", "rango", "idProd"
        };
        String sql = "SELECT producto, descripcion, lista, cantidad, subtotal, porcDescuento, descuento, "
                + "porcIva, iva, total, ubicacion1, referencia, estado, plu, cant2, preparacion, rango, idProd "
                + "FROM pedidos WHERE idFactura = '" + idFactura + "'";
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
        String sql = "SELECT idFactura, cliente, vendedor, subtotalGeneral, descuentoGeneral, ivaGeneral, "
                + "totalGeneral, estadoGeneral, observacion FROM bdPlanSepare WHERE idFactura = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ndPlanSepare nodo = new ndPlanSepare();
                    nodo.setIdFactura(rs.getString("idFactura"));
                    nodo.setCliente(rs.getString("cliente"));
                    nodo.setVendedor(rs.getString("vendedor"));
                    nodo.setSubtotalGeneral(rs.getString("subtotalGeneral"));
                    nodo.setDescuentoGeneral(rs.getString("descuentoGeneral"));
                    nodo.setIvaGeneral(rs.getString("ivaGeneral"));
                    nodo.setTotalGeneral(rs.getString("totalGeneral"));
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
        String sql = "SELECT idFactura, cliente, vendedor, subtotalGeneral, descuentoGeneral, ivaGeneral, "
                + "totalGeneral, estadoGeneral, observacion FROM bdPedido WHERE idFactura = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ndPedido nodo = new ndPedido();
                    nodo.setIdFactura(rs.getString("idFactura"));
                    nodo.setCliente(rs.getString("cliente"));
                    nodo.setVendedor(rs.getString("vendedor"));
                    nodo.setSubtotalGeneral(rs.getString("subtotalGeneral"));
                    nodo.setDescuentoGeneral(rs.getString("descuentoGeneral"));
                    nodo.setIvaGeneral(rs.getString("ivaGeneral"));
                    nodo.setTotalGeneral(rs.getString("totalGeneral"));
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

    // -------------------------------------------------------------------------
    // Escritura
    // -------------------------------------------------------------------------

    public boolean agregarRegistro(ndFactura nodo) {
        String sql = "INSERT INTO bdFactura(idFactura, cliente, vendedor, red, fechaFactura, fechaVencimiento, "
                + "comprobante, cotizacion, anulada, anula, credito, cxc, usuario, observacion, anulada1, anula1, credito1, cxc1, usuario1, "
                + "fechaAlerta, terminal, estadoGeneral, estado2, factura, resolucion, fechaAnulacion, cuadreAnulacion, usuarioAnula, placa, "
                + "garantia, diasGarantia, rango, terminos, notaAnulacion, conseMesa, producto, NC, concepto, descripcion, plu, estado, tercero, preparacion, "
                + "turno, franquisia, comision, imei, lote, idProd, mesFacturado, porcPropina, idCosteo, hora, sisteCredito, bodega, modeloContable, "
                + "efectivoGeneral, ncGeneral, chequeGeneral, targetaGeneral, totalGeneral, descuentoGeneral, "
                + "ivaGeneral, subtotalGeneral, rtIva, rtIca, rtFuente, otros, devuelta, copago, lista, cantidad, descuento, total, iva, subtotal, utilidad, porcDescuento, "
                + "cant2, porcIva, utilidad1, impuesto, impoGeneral, valorComision, totalFacturaComision, tarjetaCredito, totalPropina, porcImpo, impoconsumo, costo) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, nodo.getIdFactura());
            pstmt.setString(2, nodo.getCliente());
            pstmt.setString(3, nodo.getVendedor());
            pstmt.setString(4, nodo.getRed());
            pstmt.setString(5, nodo.getFechaFactura());
            pstmt.setString(6, nodo.getFechaVencimiento());
            pstmt.setString(7, nodo.getComprobante());
            pstmt.setString(8, nodo.getCotizacion());
            pstmt.setBoolean(9, nodo.isAnulada());
            pstmt.setString(10, nodo.getAnula());
            pstmt.setBoolean(11, nodo.isCredito());
            pstmt.setString(12, nodo.getCxc());
            pstmt.setString(13, nodo.getUsuario());
            pstmt.setString(14, nodo.getObservacion());
            pstmt.setBoolean(15, nodo.isAnulada1());
            pstmt.setString(16, nodo.getAnula1());
            pstmt.setBoolean(17, nodo.isCredito1());
            pstmt.setString(18, nodo.getCxc1());
            pstmt.setString(19, nodo.getUsuario1());
            pstmt.setString(20, nodo.getFechaAlerta());
            pstmt.setString(21, nodo.getTerminal());
            pstmt.setString(22, nodo.getEstadoGeneral());
            pstmt.setString(23, nodo.getEstado2());
            pstmt.setString(24, nodo.getFactura());
            pstmt.setString(25, nodo.getResolucion());
            pstmt.setString(26, nodo.getFechaAnulacion());
            pstmt.setString(27, nodo.getCuadreAnulacion());
            pstmt.setString(28, nodo.getUsuarioAnula());
            pstmt.setString(29, nodo.getPlaca());
            pstmt.setString(30, nodo.getGarantia());
            pstmt.setString(31, nodo.getDiasGarantia());
            pstmt.setString(32, nodo.getRango());
            pstmt.setString(33, nodo.getTerminos());
            pstmt.setString(34, nodo.getNotaAnulacion());
            pstmt.setString(35, nodo.getConseMesa());
            pstmt.setString(36, nodo.getProducto());
            pstmt.setString(37, nodo.getNC());
            pstmt.setString(38, nodo.getConcepto());
            pstmt.setString(39, nodo.getDescripcion());
            pstmt.setString(40, nodo.getPlu());
            pstmt.setString(41, nodo.getEstado());
            pstmt.setString(42, nodo.getTercero());
            pstmt.setString(43, nodo.getPreparacion());
            pstmt.setString(44, nodo.getTurno());
            pstmt.setString(45, nodo.getFranquisia());
            pstmt.setString(46, nodo.getComision());
            pstmt.setString(47, nodo.getImei());
            pstmt.setString(48, nodo.getLote());
            pstmt.setString(49, nodo.getIdProd());
            pstmt.setString(50, nodo.getMesFacturado());
            pstmt.setString(51, nodo.getPorcPropina());
            pstmt.setString(52, nodo.getIdCosteo());
            pstmt.setString(53, nodo.getHora());
            pstmt.setBoolean(54, nodo.isSisteCredito());
            pstmt.setString(55, nodo.getBodega());
            pstmt.setString(56, nodo.getModeloContable());
            pstmt.setBigDecimal(57, Utilidades.convertirBigDecimal(nodo.getEfectivoGeneral()));
            pstmt.setBigDecimal(58, Utilidades.convertirBigDecimal(nodo.getNcGeneral()));
            pstmt.setBigDecimal(59, Utilidades.convertirBigDecimal(nodo.getChequeGeneral()));
            pstmt.setBigDecimal(60, Utilidades.convertirBigDecimal(nodo.getTargetaGeneral()));
            pstmt.setBigDecimal(61, Utilidades.convertirBigDecimal(nodo.getTotalGeneral()));
            pstmt.setBigDecimal(62, Utilidades.convertirBigDecimal(nodo.getDescuentoGeneral()));
            pstmt.setBigDecimal(63, Utilidades.convertirBigDecimal(nodo.getIvaGeneral()));
            pstmt.setBigDecimal(64, Utilidades.convertirBigDecimal(nodo.getSubtotalGeneral()));
            pstmt.setBigDecimal(65, Utilidades.convertirBigDecimal(nodo.getRtIva()));
            pstmt.setBigDecimal(66, Utilidades.convertirBigDecimal(nodo.getRtIca()));
            pstmt.setBigDecimal(67, Utilidades.convertirBigDecimal(nodo.getRtFuente()));
            pstmt.setBigDecimal(68, Utilidades.convertirBigDecimal(nodo.getOtros()));
            pstmt.setBigDecimal(69, Utilidades.convertirBigDecimal(nodo.getDevuelta()));
            pstmt.setBigDecimal(70, Utilidades.convertirBigDecimal(nodo.getCopago()));
            pstmt.setBigDecimal(71, Utilidades.convertirBigDecimal(nodo.getLista()));
            pstmt.setBigDecimal(72, Utilidades.convertirBigDecimal(nodo.getCantidad()));
            pstmt.setBigDecimal(73, Utilidades.convertirBigDecimal(nodo.getDescuento()));
            pstmt.setBigDecimal(74, Utilidades.convertirBigDecimal(nodo.getTotal()));
            pstmt.setBigDecimal(75, Utilidades.convertirBigDecimal(nodo.getIva()));
            pstmt.setBigDecimal(76, Utilidades.convertirBigDecimal(nodo.getSubtotal()));
            pstmt.setBigDecimal(77, Utilidades.convertirBigDecimal(nodo.getUtilidad()));
            pstmt.setBigDecimal(78, Utilidades.convertirBigDecimal(nodo.getPorcDescuento()));
            pstmt.setBigDecimal(79, Utilidades.convertirBigDecimal(nodo.getCant2()));
            pstmt.setBigDecimal(80, Utilidades.convertirBigDecimal(nodo.getPorcIva()));
            pstmt.setBigDecimal(81, Utilidades.convertirBigDecimal(nodo.getUtilidad1()));
            pstmt.setBigDecimal(82, Utilidades.convertirBigDecimal(nodo.getImpuestos()));
            pstmt.setBigDecimal(83, Utilidades.convertirBigDecimal(nodo.getImpoGeneral()));
            pstmt.setBigDecimal(84, Utilidades.convertirBigDecimal(nodo.getValorComision()));
            pstmt.setBigDecimal(85, Utilidades.convertirBigDecimal(nodo.getTotalFacturaComision()));
            pstmt.setBigDecimal(86, Utilidades.convertirBigDecimal(nodo.getTarjetaCredito()));
            pstmt.setBigDecimal(87, Utilidades.convertirBigDecimal(nodo.getTotalPropina()));
            pstmt.setBigDecimal(88, Utilidades.convertirBigDecimal(nodo.getPorcImpo()));
            pstmt.setBigDecimal(89, Utilidades.convertirBigDecimal(nodo.getImpoconsumo()));
            pstmt.setBigDecimal(90, Utilidades.convertirBigDecimal(nodo.getCosto()));
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al guardar registro de factura: " + nodo.getIdFactura(), e);
            return false;
        }
    }

    public boolean eliminarRegistro(String factura) {
        String sql = "DELETE FROM bdFactura WHERE factura = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, factura);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al eliminar registro de factura: " + factura, e);
            return false;
        }
    }

    /**
     * Devuelve el siguiente consecutivo (cadena) para el tipo indicado.
     * Delegado a la capa de SQL ya existente para mantener la lógica centralizada.
     */
    public String getNextConsecutivo(String clave) {
        String sql = "SELECT numero, estado FROM bdConsecutivos WHERE Id = ? FOR UPDATE";
        String numero = "";
        boolean previousAutoCommit = true;
        try {
            previousAutoCommit = conexion.getAutoCommit();
            conexion.setAutoCommit(false);

            try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
                stmt.setString(1, clave);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        numero = rs.getString("numero");
                    } else {
                        // No existe la fila
                        conexion.rollback();
                        return "";
                    }
                }
            }

            String update = "UPDATE bdConsecutivos SET estado = 'ON' WHERE Id = ?";
            try (PreparedStatement ustmt = conexion.prepareStatement(update)) {
                ustmt.setString(1, clave);
                ustmt.executeUpdate();
            }

            conexion.commit();
            return numero != null ? numero : "";
        } catch (SQLException e) {
            try {
                conexion.rollback();
            } catch (SQLException re) {
                System.err.println("Error during rollback: " + re.getMessage());
            }
            System.err.println("Error obteniendo consecutivo para " + clave + ": " + e.getMessage());
        } finally {
            try {
                conexion.setAutoCommit(previousAutoCommit);
            } catch (SQLException e) {
                System.err.println("Error restaurando autoCommit: " + e.getMessage());
            }
        }
        return "";
    }
}
