package Dao.Cartera;

import Dao.Documentos.ConsultaCartera;
import Estrategia.AbstractDao;
import Modelo.Cartera.CuentaPendiente;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoCartera extends AbstractDao {

    public List<CuentaPendiente> cuentasPendientesPorCliente(String cliente) throws SQLException {
        return consultarCuentasPendientes(ConsultaCartera.cuentasPendientes(
                "(" + ConsultaCartera.CONSECUTIVO_NORMAL + ") AND t.id = ?", "FACT", true, ConsultaCartera.EDAD_DIAS), cliente);
    }

    public List<CuentaPendiente> cuentasPendientesDomicilioPorCliente(String cliente) throws SQLException {
        return consultarCuentasPendientes(ConsultaCartera.cuentasPendientes(
                "(" + ConsultaCartera.CONSECUTIVO_DOMICILIO + ") AND t.id = ?", "FACT", false, ConsultaCartera.EDAD_NUMERICA), cliente);
    }

    private List<CuentaPendiente> consultarCuentasPendientes(String consultaBase, String cliente) throws SQLException {
        String sql = "SELECT x.facturaTerm, x.factura, x.fechaFactura, x.fechaVencimiento, x.valor, x.saldo, x.edadCartera, x.estadoVencimiento "
                + "FROM (" + consultaBase + ") x ORDER BY x.fechaFactura";

        List<CuentaPendiente> cuentas = new ArrayList<>();
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, cliente);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    CuentaPendiente cuenta = new CuentaPendiente();
                    cuenta.setFactura(rs.getString("facturaTerm"));
                    cuenta.setFacturaInterna(rs.getString("factura"));
                    Date fechaFactura = rs.getDate("fechaFactura");
                    cuenta.setFechaFactura(fechaFactura == null ? null : fechaFactura.toLocalDate());
                    Date fechaVencimiento = rs.getDate("fechaVencimiento");
                    cuenta.setFechaVencimiento(fechaVencimiento == null ? null : fechaVencimiento.toLocalDate());
                    cuenta.setValor(rs.getBigDecimal("valor"));
                    cuenta.setSaldo(rs.getBigDecimal("saldo"));
                    cuenta.setEdadCartera(rs.getString("edadCartera"));
                    cuenta.setEstadoVencimiento(rs.getString("estadoVencimiento"));
                    cuentas.add(cuenta);
                }
            }
        }
        return cuentas;
    }
}
