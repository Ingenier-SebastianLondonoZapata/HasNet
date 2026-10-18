package Modelo.Productos;

import Utilidades.Utilidades;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ModeloProducto {

    private String idSistema, codigo, codigoBarras, descripcion, grupo, subgrupo, proveedor, usuario, und, referencia, ubicacion1, costo, terminal, cantMedida, tipoProd, marca;
    private String iva, listaPrecio1, minimo, minima, maxima, ponderado, impoconsumoVenta;
    private String claseBuscador, porcentaje, descripcionLarga, cubicaje, peso, codContable, codArancel, tipoProducto,
            lenteMarco, adesivo, color, empaque, composicion, rmb, indVentas;
    private Boolean manejaInventario;

    private final Map<Integer, ModeloListaPrecio> listasPrecios = new TreeMap<>();

    private BigDecimal inventario, compras, ventas, notaCredito, ajusteEntrada, ajusteSalida, planSepare, pedidos, anulada, inventarioInicial,
            ajusteInventario, fisicoInventario, armado, costeo, ordenServicio, congelada, trasladoBod, trasladoInternoEntrada,
            trasladoInternoSalida, enTransito, notaDebito;

    public BigDecimal getNotaDebito() {
        return notaDebito == null ? BigDecimal.ZERO : notaDebito;
    }

    public void setNotaDebito(BigDecimal notaDebito) {
        this.notaDebito = notaDebito;
    }

    public String getDescripcionLarga() {
        return descripcionLarga;
    }

    public void setDescripcionLarga(String descripcionLarga) {
        this.descripcionLarga = descripcionLarga;
    }

    public String getImpoconsumoVenta() {
        return impoconsumoVenta;
    }

    public void setImpoconsumoVenta(String impoconsumoVenta) {
        this.impoconsumoVenta = impoconsumoVenta;
    }

    public String getIndVentas() {
        return indVentas;
    }

    public void setIndVentas(String indVentas) {
        this.indVentas = indVentas;
    }

    public String getComposicion() {
        return composicion;
    }

    public void setComposicion(String composicion) {
        this.composicion = composicion;
    }

    public String getRmb() {
        return rmb;
    }

    public void setRmb(String rmb) {
        this.rmb = rmb;
    }

    public String getLenteMarco() {
        return lenteMarco;
    }

    public void setLenteMarco(String lenteMarco) {
        this.lenteMarco = lenteMarco;
    }

    public String getAdesivo() {
        return adesivo;
    }

    public void setAdesivo(String adesivo) {
        this.adesivo = adesivo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getEmpaque() {
        return empaque;
    }

    public void setEmpaque(String empaque) {
        this.empaque = empaque;
    }















    public BigDecimal getEnTransito() {
        return enTransito == null ? BigDecimal.ZERO : enTransito;
    }

    public void setEnTransito(BigDecimal enTransito) {
        this.enTransito = enTransito;
    }

    public String getTipoProd() {
        return tipoProd;
    }

    public void setTipoProd(String tipoProd) {
        this.tipoProd = tipoProd;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCantMedida() {
        return cantMedida;
    }

    public void setCantMedida(String cantMedida) {
        this.cantMedida = cantMedida;
    }

    public String getIdSistema() {
        return idSistema;
    }

    public void setIdSistema(String idSistema) {
        this.idSistema = idSistema;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public BigDecimal getTrasladoInternoEntrada() {
        return trasladoInternoEntrada == null ? BigDecimal.ZERO : trasladoInternoEntrada;
    }

    public void setTrasladoInternoEntrada(BigDecimal trasladoInternoEntrada) {
        this.trasladoInternoEntrada = trasladoInternoEntrada;
    }

    public BigDecimal getTrasladoInternoSalida() {
        return trasladoInternoSalida == null ? BigDecimal.ZERO : trasladoInternoSalida;
    }

    public void setTrasladoInternoSalida(BigDecimal trasladoInternoSalida) {
        this.trasladoInternoSalida = trasladoInternoSalida;
    }

    public BigDecimal getTrasladoBod() {
        return trasladoBod == null ? BigDecimal.ZERO : trasladoBod;
    }

    public void setTrasladoBod(BigDecimal trasladoBod) {
        this.trasladoBod = trasladoBod;
    }

    public String getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }


    public String getCubicaje() {
        return cubicaje;
    }

    public void setCubicaje(String cubicaje) {
        this.cubicaje = cubicaje;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getCodContable() {
        return codContable;
    }

    public void setCodContable(String codContable) {
        this.codContable = codContable;
    }

    public String getCodArancel() {
        return codArancel;
    }

    public void setCodArancel(String codArancel) {
        this.codArancel = codArancel;
    }


    public BigDecimal getCongelada() {
        return congelada == null ? BigDecimal.ZERO : congelada;
    }

    public void setCongelada(BigDecimal congelada) {
        this.congelada = congelada;
    }

    public BigDecimal getOrdenServicio() {
        return ordenServicio == null ? BigDecimal.ZERO : ordenServicio;
    }

    public void setOrdenServicio(BigDecimal ordenServicio) {
        this.ordenServicio = ordenServicio;
    }

    public BigDecimal getCosteo() {
        return costeo == null ? BigDecimal.ZERO : costeo;
    }

    public void setCosteo(BigDecimal costeo) {
        this.costeo = costeo;
    }

    public BigDecimal getArmado() {
        return armado == null ? BigDecimal.ZERO : armado;
    }

    public void setArmado(BigDecimal armado) {
        this.armado = armado;
    }

    public BigDecimal getFisicoInventario() {
        return fisicoInventario == null ? BigDecimal.ZERO : fisicoInventario;
    }

    public void setFisicoInventario(BigDecimal fisicoInventario) {
        this.fisicoInventario = fisicoInventario;
    }

    public BigDecimal getCompras() {
        return compras == null ? BigDecimal.ZERO : compras;
    }

    public void setCompras(BigDecimal compras) {
        this.compras = compras;
    }

    public BigDecimal getVentas() {
        return ventas == null ? BigDecimal.ZERO : ventas;
    }

    public void setVentas(BigDecimal ventas) {
        this.ventas = ventas;
    }

    public BigDecimal getNotaCredito() {
        return notaCredito == null ? BigDecimal.ZERO : notaCredito;
    }

    public void setNotaCredito(BigDecimal notaCredito) {
        this.notaCredito = notaCredito;
    }

    public BigDecimal getAjusteEntrada() {
        return ajusteEntrada == null ? BigDecimal.ZERO : ajusteEntrada;
    }

    public void setAjusteEntrada(BigDecimal ajusteEntrada) {
        this.ajusteEntrada = ajusteEntrada;
    }

    public BigDecimal getAjusteSalida() {
        return ajusteSalida == null ? BigDecimal.ZERO : ajusteSalida;
    }

    public void setAjusteSalida(BigDecimal ajusteSalida) {
        this.ajusteSalida = ajusteSalida;
    }

    public BigDecimal getPlanSepare() {
        return planSepare == null ? BigDecimal.ZERO : planSepare;
    }

    public void setPlanSepare(BigDecimal planSepare) {
        this.planSepare = planSepare;
    }

    public BigDecimal getPedidos() {
        return pedidos == null ? BigDecimal.ZERO : pedidos;
    }

    public void setPedidos(BigDecimal pedidos) {
        this.pedidos = pedidos;
    }

    public BigDecimal getAnulada() {
        return anulada == null ? BigDecimal.ZERO : anulada;
    }

    public void setAnulada(BigDecimal anulada) {
        this.anulada = anulada;
    }

    public BigDecimal getInventarioInicial() {
        return inventarioInicial == null ? BigDecimal.ZERO : inventarioInicial;
    }

    public void setInventarioInicial(BigDecimal inventarioInicial) {
        this.inventarioInicial = inventarioInicial;
    }

    public BigDecimal getAjusteInventario() {
        return ajusteInventario == null ? BigDecimal.ZERO : ajusteInventario;
    }

    public void setAjusteInventario(BigDecimal ajusteInventario) {
        this.ajusteInventario = ajusteInventario;
    }

    public String getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(String porcentaje) {
        this.porcentaje = porcentaje;
    }









































    public Boolean getManejaInventario() {
        return manejaInventario;
    }

    public void setManejaInventario(Boolean manejaInventario) {
        this.manejaInventario = manejaInventario;
    }

    public String getClaseBuscador() {
        return claseBuscador;
    }

    public void setClaseBuscador(String claseBuscador) {
        this.claseBuscador = claseBuscador;
    }

    public ModeloProducto() {

    }

    public BigDecimal getInventario() {
        return inventario == null ? BigDecimal.ZERO : inventario;
    }

    public void setInventario(BigDecimal inventario) {
        this.inventario = inventario;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getPonderado() {
        return ponderado;
    }

    public void setPonderado(String ponderado) {
        this.ponderado = ponderado;
    }

    public String getUbicacion1() {
        return ubicacion1;
    }

    public void setUbicacion1(String ubicacion1) {
        this.ubicacion1 = ubicacion1;
    }



















    public String getMinima() {
        return minima;
    }

    public void setMinima(String minima) {
        this.minima = minima;
    }

    public String getMaxima() {
        return maxima;
    }

    public void setMaxima(String maxima) {
        this.maxima = maxima;
    }







    public String getUnd() {
        return und;
    }

    public void setUnd(String und) {
        this.und = und;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getSubgrupo() {
        return subgrupo;
    }

    public void setSubgrupo(String subgrupo) {
        this.subgrupo = subgrupo;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getIva() {
        return iva;
    }

    public void setIva(String iva) {
        this.iva = iva;
    }

    public String getL1() {
        return listaPrecio1;
    }

    public void setL1(String l1) {
        this.listaPrecio1 = l1;
    }

    public String getMinimo() {
        return minimo;
    }

    public void setMinimo(String minimo) {
        this.minimo = minimo;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getCosto() {
        return costo;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }

    public List<ModeloListaPrecio> getListas() {
        List<ModeloListaPrecio> activas = new ArrayList<>();
        for (ModeloListaPrecio lista : listasPrecios.values()) {
            if (!lista.esVacia()) {
                activas.add(lista);
            }
        }

        return activas;
    }

    public ModeloListaPrecio getLista(int numeroLista) {
        ModeloListaPrecio lista = listasPrecios.get(numeroLista);
        return lista == null || lista.esVacia() ? null : lista;
    }

    public void setLista(ModeloListaPrecio lista) {
        if (lista.getNumeroLista() <= ModeloListaPrecio.LISTA_PRINCIPAL) {
            throw new IllegalArgumentException("La lista " + lista.getNumeroLista() + " es la principal del producto (L1)");
        }

        listasPrecios.put(lista.getNumeroLista(), lista);
    }

    public void setListas(java.util.Collection<ModeloListaPrecio> nuevas) {
        listasPrecios.clear();
        for (ModeloListaPrecio lista : nuevas) {
            setLista(lista);
        }
    }

    public void eliminarLista(int numeroLista) {
        listasPrecios.remove(numeroLista);
    }

    public int siguienteNumeroLista() {
        int numero = ModeloListaPrecio.LISTA_PRINCIPAL + 1;
        while (getLista(numero) != null) {
            numero++;
        }

        return numero;
    }

    public BigDecimal getPrecioLista(int numeroLista) {
        BigDecimal precioPorDefecto = Utilidades.convertirBigDecimal(listaPrecio1);
        if (numeroLista == ModeloListaPrecio.LISTA_PRINCIPAL) {
            return precioPorDefecto;
        }

        ModeloListaPrecio lista = getLista(numeroLista);
        return lista == null || lista.getPrecio().signum() == 0 ? precioPorDefecto : lista.getPrecio();
    }

    public void inicializarTextosOpcionales() {
        cubicaje = valorOVacio(cubicaje);
        peso = valorOVacio(peso);
        codContable = valorOVacio(codContable);
        codArancel = valorOVacio(codArancel);
        tipoProducto = valorOVacio(tipoProducto);
        cantMedida = valorOVacio(cantMedida);
        marca = valorOVacio(marca);
        tipoProd = valorOVacio(tipoProd);
        lenteMarco = valorOVacio(lenteMarco);
        adesivo = valorOVacio(adesivo);
        color = valorOVacio(color);
        empaque = valorOVacio(empaque);
        composicion = valorOVacio(composicion);
        rmb = valorOVacio(rmb);
    }

    private static String valorOVacio(String valor) {
        return valor == null ? "" : valor;
    }

    public BigDecimal getCantidadLista(int numeroLista) {
        ModeloListaPrecio lista = numeroLista == ModeloListaPrecio.LISTA_PRINCIPAL ? null : getLista(numeroLista);
        return lista == null ? BigDecimal.ONE : lista.getCantidad();
    }

    public String getCodigoLista(int numeroLista) {
        if (numeroLista == ModeloListaPrecio.LISTA_PRINCIPAL) {
            return codigo;
        }

        ModeloListaPrecio lista = getLista(numeroLista);
        return lista == null ? null : lista.getCodigo();
    }

    public String getDescripcionLista(int numeroLista) {
        if (numeroLista == ModeloListaPrecio.LISTA_PRINCIPAL) {
            return descripcion;
        }

        ModeloListaPrecio lista = getLista(numeroLista);
        return lista == null || lista.getDescripcion() == null ? "" : lista.getDescripcion();
    }

    public String getUbicacionLista(int numeroLista) {
        if (numeroLista == ModeloListaPrecio.LISTA_PRINCIPAL) {
            return ubicacion1;
        }

        ModeloListaPrecio lista = getLista(numeroLista);
        return lista == null || lista.getUbicacion() == null ? "" : lista.getUbicacion();
    }

    public boolean isPluLista(int numeroLista) {
        ModeloListaPrecio lista = numeroLista == ModeloListaPrecio.LISTA_PRINCIPAL ? null : getLista(numeroLista);
        return lista != null && lista.isPlu();
    }

    public BigDecimal getPrecioLista(String nombreLista) {
        return getPrecioLista(numeroDeLista(nombreLista));
    }

    public static int numeroDeLista(String nombreLista) {
        if (nombreLista == null) {
            return ModeloListaPrecio.LISTA_PRINCIPAL;
        }

        String texto = nombreLista.trim().toUpperCase();
        if (texto.startsWith("L")) {
            texto = texto.substring(1);
        }

        try {
            int numero = Integer.parseInt(texto);
            return numero < 1 ? ModeloListaPrecio.LISTA_PRINCIPAL : numero;
        } catch (NumberFormatException e) {
            return ModeloListaPrecio.LISTA_PRINCIPAL;
        }
    }

    public List<ModeloListaPrecio> getListasPlu() {
        List<ModeloListaPrecio> plus = new ArrayList<>();
        for (ModeloListaPrecio lista : getListas()) {
            if (lista.isPlu()) {
                plus.add(lista);
            }
        }

        return plus;
    }

    public boolean tienePlu() {
        return !getListasPlu().isEmpty();
    }

    private ModeloListaPrecio listaParaEscribir(int numeroLista) {
        ModeloListaPrecio lista = listasPrecios.get(numeroLista);
        if (lista == null) {
            lista = new ModeloListaPrecio(numeroLista);
            listasPrecios.put(numeroLista, lista);
        }

        return lista;
    }
}
