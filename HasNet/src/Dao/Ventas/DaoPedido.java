package Dao.Ventas;

import Dao.Documentos.DaoDocumentos;
import Enums.TipoDocumento;

public class DaoPedido {

    private final DaoDocumentos daoDocumentos = new DaoDocumentos();

    public boolean modificarEstadoPedido(String estado, String idFacturaGenerada, String idDocumento) {
        return daoDocumentos.actualizarEstadoPorFactura(TipoDocumento.PEDIDO, idDocumento, estado, idFacturaGenerada);
    }
}
