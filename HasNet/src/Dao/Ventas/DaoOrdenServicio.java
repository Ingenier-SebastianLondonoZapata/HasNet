package Dao.Ventas;

import Dao.Documentos.DaoDocumentos;
import Enums.TipoDocumento;

public class DaoOrdenServicio {

    private final DaoDocumentos daoDocumentos = new DaoDocumentos();

    public boolean modificarEstadoOrden(String estado, String idDocumento) {
        return daoDocumentos.actualizarEstadoPorFactura(TipoDocumento.ORDER_SERVICIO, idDocumento, estado, null);
    }
}
