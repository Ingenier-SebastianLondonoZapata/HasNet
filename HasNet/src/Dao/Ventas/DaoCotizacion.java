package Dao.Ventas;

import Dao.Documentos.DaoDocumentos;
import Enums.TipoDocumento;

public class DaoCotizacion {

    private final DaoDocumentos daoDocumentos = new DaoDocumentos();

    public boolean modificarEstadoCotizacion(String estado, String idDocumento) {
        return daoDocumentos.actualizarEstadoPorFactura(TipoDocumento.COTIZACION, idDocumento, estado, null);
    }
}
