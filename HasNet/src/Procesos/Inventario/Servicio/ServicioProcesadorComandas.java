package Procesos.Inventario.Servicio;

import Utilidades.Contactos.Bodegas;

import Dao.Productos.DaoProducto;

import Modelo.Ventas.OpcionPreparacion;
import Utilidades.Ventas.ParserPreparacion;
import Utilidades.Utilidades;
import Modelo.Ventas.ModeloComanda;
import clases.Instancias;
import Modelo.Productos.ModeloProducto;
import java.math.BigDecimal;

public class ServicioProcesadorComandas {
    private final DaoProducto daoProducto = new DaoProducto();
    
    private final CargadorProducto cargadorProducto;
    
    public ServicioProcesadorComandas(CargadorProducto cargadorProducto) {
        this.cargadorProducto = cargadorProducto;
    }
    
    public ModeloComanda construirComanda(String codigoProducto, String nombreProducto, String preparacion,
            String idBodega, BigDecimal cantidad, String congelada,
            String factura, int turno, String pedido, String consecutivo, Instancias instancias) {
        
        if (preparacion == null || preparacion.isEmpty()) {
            return null;
        }
        
        String opciones = extraerComponente(preparacion, 1);
        String aderezos = extraerComponente(preparacion, 0);
        String observaciones = extraerComponente(preparacion, 2);
        
        String opcionesFormateo = procesarOpciones(opciones, idBodega);
        String ingredientesFormateo = procesarIngredientes(opciones, instancias);
        String aderzosFormateo = procesarAderezos(aderezos, idBodega);
        
        return new ModeloComanda(congelada, factura, codigoProducto, nombreProducto,
                opcionesFormateo, ingredientesFormateo, "", aderzosFormateo,
                cantidad, observaciones, turno, consecutivo);
    }
    
    public ModeloComanda construirComandaSimple(String codigoProducto, String nombreProducto, BigDecimal cantidad,
            String congelada, String factura, int turno, String consecutivo, String esProductoRegistradoEnAdicion) {
        return new ModeloComanda(congelada, factura, codigoProducto, nombreProducto,
                "", "", esProductoRegistradoEnAdicion, "", cantidad, "", turno, consecutivo);
    }
    
    private String extraerComponente(String preparacion, int indice) {
        try {
            String[] componentes = preparacion.split("; ");
            return indice < componentes.length ? componentes[indice].trim() : "";
        } catch (Exception e) {
            return "";
        }
    }
    
    private String procesarOpciones(String opciones, String idBodega) {
        if (opciones.isEmpty()) {
            return "";
        }
        
        StringBuilder opciones1 = new StringBuilder("Adiciones: ");
        
        for (OpcionPreparacion opcion : ParserPreparacion.opcionesDeSegmento(opciones)) {
            String principal = opcion.getPrincipal();
            Boolean esAdicion = opcion.esAdicion();
            
            if (esAdicion && (principal == null || principal.isEmpty() || principal.equals(" "))) {
                continue;
            }
            
            if (esAdicion && !principal.equals("") && !principal.equals(opcion.getCodigo())) {
                String estado = opcion.getEstado().trim();
                
                if (estado.equals("true")) {
                    ModeloProducto producto = cargadorProducto.cargar(opcion.getCodigo(), idBodega);
                    if (producto != null) {
                        opciones1.append(construirLineaOpcion(opcion, producto)).append(", ");
                    }
                }
            }
        }
        
        if (!opciones1.toString().equals("Adiciones: ")) {
            opciones1.setLength(opciones1.length() - 2);
        } else {
            opciones1.setLength(0);
        }
        
        return opciones1.toString();
    }
    
    private String construirLineaOpcion(OpcionPreparacion opcion, ModeloProducto producto) {
        if (producto.getGrupo() != null && producto.getGrupo().equals("GRP-02")) {
            return Utilidades.formatearCantidadVista(opcion.getCantidad()) + "-" + producto.getDescripcion();
        }
        
        return producto.getDescripcion();
    }
    
    private String procesarIngredientes(String opciones, Instancias instancias) {
        if (opciones.isEmpty()) {
            return "";
        }
        
        System.out.println("opciones: " + opciones);
        StringBuilder ingredientes = new StringBuilder("Sin: ");
        
        for (OpcionPreparacion opcion : ParserPreparacion.opcionesDeSegmento(opciones)) {
            String principal = opcion.getPrincipal();
            Boolean esAdicion = opcion.esAdicion();
            String estado = opcion.getEstado().trim();
            
            if (!esAdicion && (principal == null || principal.isEmpty() || principal.equals(" "))) {
                if (estado.equals("false")) {
                    ModeloProducto datosProducto = daoProducto.obtenerDatosProducto(opcion.getCodigo(), Bodegas.predeterminada());
                    ingredientes.append(datosProducto.getDescripcion()).append(", ");
                }
            }
        }
        
        if (!ingredientes.toString().equals("Sin: ")) {
            ingredientes.setLength(ingredientes.length() - 2);
        } else {
            ingredientes.setLength(0);
        }
        
        return ingredientes.toString();
    }
    
    private String procesarAderezos(String aderezos, String idBodega) {
        if (aderezos.isEmpty()) {
            return "";
        }
        
        StringBuilder aderzosFormateo = new StringBuilder("Aderezos: ");
        String[] codigosAderzos = aderezos.split(", ");
        
        for (String codigo : codigosAderzos) {
            ModeloProducto producto = cargadorProducto.cargar(codigo.trim(), idBodega);
            if (producto != null) {
                aderzosFormateo.append(producto.getDescripcion()).append(", ");
            }
        }
        
        if (!aderzosFormateo.toString().equals("Aderezos: ")) {
            aderzosFormateo.setLength(aderzosFormateo.length() - 2);
        } else {
            aderzosFormateo.setLength(0);
        }
        
        return aderzosFormateo.toString();
    }
}
