package estrategiainventario;

import DAO.Inventario.DaoInventario;
import java.math.BigDecimal;

public abstract class AbstractProcesadorMovimiento implements ProcesadorMovimiento {

    protected final DaoInventario daoInventario = new DaoInventario();

    protected BigDecimal obtenerValor(String valor) {
        if (valor == null || valor.trim().isEmpty()) {
            return BigDecimal.ZERO;
        }

        return new BigDecimal(valor.trim().replace(",", "."));
    }

    protected String convertir(BigDecimal valor) {
        return valor.stripTrailingZeros().toPlainString().replace(".", ",");
    }
}
