package Dao.Ventas;

import java.math.BigDecimal;
import Modelo.Ventas.CabeceraDocumento;
import Modelo.Ventas.DocumentoMovimiento;
import Modelo.Ventas.LineaProducto;
import Utilidades.Utilidades;
import clases.Cartera.ndCxc;
import Dao.Generales.DaoGenerales;
import Dao.Documentos.ConsultaDocumentos;
import Dao.Documentos.ConsultaFactura;
import Dao.Documentos.ConsultaVista;
import Dao.Documentos.DaoDocumentos;
import Modelo.Documentos.ModeloDocumento;
import Enums.TipoDocumento;
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

    public String obtenerTurnoFactura(String factura) {
        String sql = "SELECT d.turno AS turno FROM bdDocumentos d WHERE " + ConsultaDocumentos.filtroTipos(DaoDocumentos.TIPOS_FACTURA)
                + " AND d.factura = ? AND d.turno IS NOT NULL AND TRIM(d.turno) <> '' AND TRIM(d.turno) <> '0' LIMIT 1";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, factura);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() ? rs.getString("turno").trim() : "";
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al obtener el turno de la factura " + factura, e);
            return "";
        }
    }

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

    public DocumentoMovimiento cargarMovimientoPrefactura(String factura) {
        Object[][] mat = getRegistrosPrefacturas(factura);
        if (mat.length == 0) return DocumentoMovimiento.vacio();
        return new DocumentoMovimiento(parsearCabeceraPrefactura(mat), parsearLineasPrefactura(mat));
    }

    public DocumentoMovimiento cargarPlanSepare(String id) {
        ModeloDocumento documento = new DaoDocumentos().obtenerPorFactura(TipoDocumento.PLAN_SEPARE, id);
        if (documento == null) return DocumentoMovimiento.vacio();
        Object[][] mat = getRegistrosPlanSepare(id);
        return new DocumentoMovimiento(parsearCabecera(documento), parsearLineasPlanSepare(mat));
    }

    public DocumentoMovimiento cargarPedido(String id) {
        ModeloDocumento documento = new DaoDocumentos().obtenerPorFactura(TipoDocumento.PEDIDO, id);
        if (documento == null) return DocumentoMovimiento.vacio();
        Object[][] mat = getRegistrosPedido(id);
        return new DocumentoMovimiento(parsearCabecera(documento), parsearLineasPedido(mat));
    }

    public DocumentoMovimiento cargarDocumentoConvertible(String tipoDocumento, String id) {
        if ("pedido".equals(tipoDocumento)) {
            return cargarPedido(id);
        }
        if ("cotizacion".equals(tipoDocumento)) {
            return cargarCotizacion(id);
        }
        if ("orden".equals(tipoDocumento)) {
            return cargarOrdenServicio(id);
        }
        if ("separe".equals(tipoDocumento)) {
            return cargarPlanSepare(id);
        }
        return null;
    }

    public DocumentoMovimiento cargarMesa(String id) {
        Object[][] mat = getRegistrosMesa(id);
        if (mat.length == 0) return DocumentoMovimiento.vacio();
        return new DocumentoMovimiento(parsearCabeceraMesa(mat), parsearLineasMesa(mat));
    }

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
        //  21=rango,22=detalle,23=idProd
        CabeceraDocumento cab = new CabeceraDocumento();
        cab.setVendedor(mat[0][15].toString());
        cab.setClienteId(mat[0][16].toString());
        cab.setSubtotal(mat[0][17].toString());
        cab.setTotalDescuentos(mat[0][18].toString());
        cab.setTotalIva(mat[0][19].toString());
        cab.setTotal(mat[0][20].toString());
        return cab;
    }

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
            linea.setDetalle("");
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
            linea.setDetalle(reg[22] != null ? reg[22].toString() : "");
            linea.setIdProd(reg[23] != null ? reg[23].toString() : "");
            lineas.add(linea);
        }
        return lineas;
    }

    private CabeceraDocumento parsearCabecera(ModeloDocumento documento) {
        CabeceraDocumento cab = new CabeceraDocumento();
        cab.setClienteId(documento.getCliente());
        cab.setVendedor(documento.getVendedor());
        cab.setSubtotal(documento.getSubtotal().toPlainString());
        cab.setTotalDescuentos(documento.getDescuento().toPlainString());
        cab.setTotalIva(documento.getIva().toPlainString());
        cab.setTotal(documento.getTotal().toPlainString());
        cab.setEstadoGeneral(documento.getEstadoGeneral());
        cab.setObservacion(documento.getObservacion());
        return cab;
    }

    private List<LineaProducto> parsearLineasPlanSepare(Object[][] mat) {
        // Columnas: 0=producto,1=descripcion,2=lista,3=cantidad,4=subtotal,5=porcDescuento,
        //  6=descuento,7=porcIva,8=iva,9=total,10=ubicacion,11=referencia,12=estado,
        //  13=plu,14=cant2,15=detalle,16=idProd
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
            linea.setDetalle(reg[15] != null ? reg[15].toString() : "");
            linea.setIdProd(reg[16] != null ? reg[16].toString() : "");
            lineas.add(linea);
        }
        return lineas;
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
        return BigDecimal.valueOf(Double.parseDouble(valor.toString())).toPlainString().replace('.', ',');
    }

    // -------------------------------------------------------------------------
    // Consultas base — retornan Object[][] (se mantienen para compatibilidad)
    // -------------------------------------------------------------------------

    public Object[][] getRegistrosCotizas(String factura) {
        String[] columnas = {
            "producto", "descripcion", "lista", "cantidad", "subtotal", "porcDescuento", "descuento",
            "porcIva", "iva", "total", "plu", "cant2", "cliente", "subtotalGeneral", "descuentoGeneral",
            "ivaGeneral", "totalGeneral", "rango", "estadoGeneral", "vendedor", "observacion", "bodega"
        };
        String sql = ConsultaVista.COTIZACION.consulta(ConsultaVista.COTIZACION.columnas(columnas),
                "d.factura = '" + factura.replace("'", "''") + "'", "ORDER BY dd.idDetalle");
        return daoGenerales.obtenerDatosTabla(columnas, sql);
    }

    public Object[][] getRegistrosOrdenes(String factura) {
        String[] columnas = {
            "producto", "descripcion", "lista", "cantidad", "subtotal", "porcDescuento", "descuento",
            "porcIva", "iva", "total", "cliente", "vendedor", "estado", "plu", "cant2",
            "subtotalGeneral", "descuentoGeneral", "ivaGeneral", "totalGeneral", "observacion",
            "estadoGeneral", "placaReal"
        };
        String sql = "SELECT producto, descripcion, lista, cantidad, subtotal, porcDescuento, descuento, "
                + "porcIva, iva, total, cliente, vendedor, estado, plu, cant2, subtotalGeneral, "
                + "descuentoGeneral, ivaGeneral, totalGeneral, observacion, estadoGeneral, placaReal "
                + "FROM ordenServicio WHERE factura = '" + factura + "'";
        return daoGenerales.obtenerDatosTabla(columnas, sql);
    }

    public Object[][] getRegistrosPrefacturas(String factura) {
        String[] columnas = {
            "producto", "descripcion", "lista", "cantidad", "subtotal", "porcDescuento", "descuento",
            "porcIva", "iva", "total", "ubicacion1", "referencia", "estado", "plu", "cant2",
            "vendedor", "cliente", "subtotalGeneral", "descuentoGeneral", "ivaGeneral", "totalGeneral",
            "rango", "detalle", "idProd"
        };
        String sql = ConsultaFactura.consulta(ConsultaFactura.columnas(columnas),
                "d.factura = '" + factura.replace("'", "''") + "'", "ORDER BY dd.idDetalle");
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
        //  14=descuento,15=plu,16=preparacion,17=detalle,18=idProd
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
            linea.setDetalle(reg[17] != null ? reg[17].toString() : "");
            linea.setIdProd(reg[18] != null ? reg[18].toString() : "");
            linea.setRango(reg[19] != null ? reg[19].toString() : "");
            lineas.add(linea);
        }
        return lineas;
    }

    private Object[][] getRegistrosMesa(String factura) {
        String[] columnas = {
            "cliente", "vendedor", "subtotalGeneral", "descuentoGeneral", "ivaGeneral", "totalGeneral",
            "estadoGeneral", "observacion",
            "producto", "descripcion", "lista", "cantidad", "cant2", "porcDescuento", "descuento", "plu",
            "preparacion", "detalle", "idProd", "rango"
        };
        String sql = "SELECT " + ConsultaDocumentos.columnas(TipoDocumento.MESA, columnas) + " FROM" + ConsultaDocumentos.ORIGEN
                + "WHERE " + ConsultaDocumentos.filtroTipo(TipoDocumento.MESA)
                + " AND d.factura = '" + factura.replace("'", "''") + "' ORDER BY dd.idDetalle";
        return daoGenerales.obtenerDatosTabla(columnas, sql);
    }

    private Object[][] getRegistrosPlanSepare(String factura) {
        String[] columnas = {
            "producto", "descripcion", "lista", "cantidad", "subtotal", "porcDescuento", "descuento",
            "porcIva", "iva", "total", "ubicacion", "referencia", "estado", "plu", "cant2", "detalle", "idProd"
        };
        String sql = ConsultaVista.PLAN_SEPARE.consulta(ConsultaVista.PLAN_SEPARE.columnas(columnas),
                "d.factura = '" + factura.replace("'", "''") + "'", "ORDER BY dd.idDetalle");
        return daoGenerales.obtenerDatosTabla(columnas, sql);
    }

    private Object[][] getRegistrosPedido(String factura) {
        String[] columnas = {
            "producto", "descripcion", "lista", "cantidad", "subtotal", "porcDescuento", "descuento",
            "porcIva", "iva", "total", "ubicacion1", "referencia", "estado", "plu", "cant2",
            "preparacion", "rango", "idProd"
        };
        String sql = "SELECT producto, descripcion, lista, cantidad, subtotal, porcDescuento, descuento, "
                + "porcIva, iva, total, ubicacion1, referencia, estado, plu, cant2, preparacion, rango, idProd "
                + "FROM pedidos WHERE factura = '" + factura + "'";
        return daoGenerales.obtenerDatosTabla(columnas, sql);
    }

    // -------------------------------------------------------------------------
    // Consultas de nodos únicos
    // -------------------------------------------------------------------------

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

    // -------------------------------------------------------------------------
    // Escritura
    // -------------------------------------------------------------------------

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
