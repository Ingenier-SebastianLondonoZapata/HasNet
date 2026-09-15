package Impresiones.ImpresionesRestaurante;

import Impresiones.GeneradorReporteBase;
import clases.Instancias;
import clases.metodosGenerales;
import java.util.HashMap;
import java.util.Map;

public class GeneradorReporteRestaurante extends GeneradorReporteBase {

    public GeneradorReporteRestaurante(Instancias instancias) {
        super(instancias);
    }

    public void verComanda(String condicion, String factura, String observaciones, String facturaTerm, String mesa,
            boolean previsualizar, String impresora, String vendedor) {

        if (vendedor.equals("Seleccione un vendedor")) {
            vendedor = "";
        }

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("factura", facturaTerm.replace("FACT-", ""));
        parametros.put("numFactura", factura);
        parametros.put("observaciones", observaciones);
        parametros.put("hora", metodosGenerales.fechaHora());
        parametros.put("vendedor", vendedor);
        parametros.put("mesa", mesa);
        parametros.put("sql", condicion);
        parametros.put("urlImagen", logo());
        ejecutarEnImpresora("comanda", parametros, !previsualizar, impresora);
    }
}
