package Dao.Ventas;

import Dao.Documentos.DaoDocumentos;
import Enums.TipoDocumento;

public class DaoPedido {

    private final DaoDocumentos daoDocumentos = new DaoDocumentos();

    public boolean modificarEstadoPedido(String estado, String facturaGenerada, String documento) {
        return daoDocumentos.actualizarEstadoPorFactura(TipoDocumento.PEDIDO, documento, estado, facturaGenerada);
    }
}
