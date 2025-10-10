package clases.productos;

public class ndProducto {

    private String idSistema, codigo, codigoBarras, descripcion, grupo, subgrupo, proveedor, usuario, und, referencia, ubicacion1, costo, descripcion2, ubicacion2, cantidad2,
            descripcion3, ubicacion3, cantidad3, descripcion4, ubicacion4, cantidad4, terminal, cantMedida, tipoProd, marca;
    private String descripcion5, ubicacion5, cantidad5, descripcion6, ubicacion6, cantidad6, descripcion7, ubicacion7, cantidad7, descripcion8, ubicacion8, cantidad8;
    private String iva, l1, l2, l3, l4, minimo, minima, maxima, ivaC, ponderado, inventario, l5, l6, l7, l8, impoconsumoVenta, impoconsumoCompra;

    // NUMEROS
    private Boolean plu2, plu3, plu4, plu5, plu6, plu7, plu8;
    private String claseBuscador, porcentaje, descripcionIngles, descripcionLarga, cubicaje, peso, codContable, codArancel, tipoProducto, codigo2, codigo3, codigo4,
            codigo5, codigo6, codigo7, codigo8, lenteMarco, adesivo, color, empaque, composicion, rmb, indVentas;
    private Boolean manejaInventario, impoconsumo;

    //Control del inventario
    private String compras, ventas, nc, ajusteEntrada, ajusteSalida, planSepare, pedidos, anulada, inventarioInicial,
            ajusteInventario, fisicoInventario, armado, costeo, ordenServicio, congelada, trasladoBod, trasladoInternoEntrada,
            trasladoInternoSalida, enTransito, notaDebito;

    public String getNotaDebito() {
        return notaDebito;
    }

    public void setNotaDebito(String notaDebito) {
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

    public String getImpoconsumoCompra() {
        return impoconsumoCompra;
    }

    public void setImpoconsumoCompra(String impoconsumoCompra) {
        this.impoconsumoCompra = impoconsumoCompra;
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

    public String getCodigo2() {
        return codigo2;
    }

    public void setCodigo2(String codigo2) {
        this.codigo2 = codigo2;
    }

    public String getCodigo3() {
        return codigo3;
    }

    public void setCodigo3(String codigo3) {
        this.codigo3 = codigo3;
    }

    public String getCodigo4() {
        return codigo4;
    }

    public void setCodigo4(String codigo4) {
        this.codigo4 = codigo4;
    }

    public String getCodigo5() {
        return codigo5;
    }

    public void setCodigo5(String codigo5) {
        this.codigo5 = codigo5;
    }

    public String getCodigo6() {
        return codigo6;
    }

    public void setCodigo6(String codigo6) {
        this.codigo6 = codigo6;
    }

    public String getCodigo7() {
        return codigo7;
    }

    public void setCodigo7(String codigo7) {
        this.codigo7 = codigo7;
    }

    public String getCodigo8() {
        return codigo8;
    }

    public void setCodigo8(String codigo8) {
        this.codigo8 = codigo8;
    }

    public String getEnTransito() {
        return enTransito;
    }

    public void setEnTransito(String enTransito) {
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

    public String getTrasladoInternoEntrada() {
        return trasladoInternoEntrada;
    }

    public void setTrasladoInternoEntrada(String trasladoInternoEntrada) {
        this.trasladoInternoEntrada = trasladoInternoEntrada;
    }

    public String getTrasladoInternoSalida() {
        return trasladoInternoSalida;
    }

    public void setTrasladoInternoSalida(String trasladoInternoSalida) {
        this.trasladoInternoSalida = trasladoInternoSalida;
    }

    public String getTrasladoBod() {
        return trasladoBod;
    }

    public void setTrasladoBod(String trasladoBod) {
        this.trasladoBod = trasladoBod;
    }

    public String getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public String getDescripcionIngles() {
        return descripcionIngles;
    }

    public void setDescripcionIngles(String descripcionIngles) {
        this.descripcionIngles = descripcionIngles;
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

    public Boolean getImpoconsumo() {
        return impoconsumo;
    }

    public void setImpoconsumo(Boolean impoconsumo) {
        this.impoconsumo = impoconsumo;
    }

    public String getCongelada() {
        return congelada;
    }

    public void setCongelada(String congelada) {
        this.congelada = congelada;
    }

    public String getOrdenServicio() {
        return ordenServicio;
    }

    public void setOrdenServicio(String ordenServicio) {
        this.ordenServicio = ordenServicio;
    }

    public String getCosteo() {
        return costeo;
    }

    public void setCosteo(String costeo) {
        this.costeo = costeo;
    }

    public String getArmado() {
        return armado;
    }

    public void setArmado(String armado) {
        this.armado = armado;
    }

    public String getFisicoInventario() {
        return fisicoInventario;
    }

    public void setFisicoInventario(String fisicoInventario) {
        this.fisicoInventario = fisicoInventario;
    }

    public String getCompras() {
        return compras;
    }

    public void setCompras(String compras) {
        this.compras = compras;
    }

    public String getVentas() {
        return ventas;
    }

    public void setVentas(String ventas) {
        this.ventas = ventas;
    }

    public String getNc() {
        return nc;
    }

    public void setNc(String nc) {
        this.nc = nc;
    }

    public String getAjusteEntrada() {
        return ajusteEntrada;
    }

    public void setAjusteEntrada(String ajusteEntrada) {
        this.ajusteEntrada = ajusteEntrada;
    }

    public String getAjusteSalida() {
        return ajusteSalida;
    }

    public void setAjusteSalida(String ajusteSalida) {
        this.ajusteSalida = ajusteSalida;
    }

    public String getPlanSepare() {
        return planSepare;
    }

    public void setPlanSepare(String planSepare) {
        this.planSepare = planSepare;
    }

    public String getPedidos() {
        return pedidos;
    }

    public void setPedidos(String pedidos) {
        this.pedidos = pedidos;
    }

    public String getAnulada() {
        return anulada;
    }

    public void setAnulada(String anulada) {
        this.anulada = anulada;
    }

    public String getInventarioInicial() {
        return inventarioInicial;
    }

    public void setInventarioInicial(String inventarioInicial) {
        this.inventarioInicial = inventarioInicial;
    }

    public String getAjusteInventario() {
        return ajusteInventario;
    }

    public void setAjusteInventario(String ajusteInventario) {
        this.ajusteInventario = ajusteInventario;
    }

    public String getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(String porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getL5() {
        return l5;
    }

    public void setL5(String l5) {
        this.l5 = l5;
    }

    public String getL6() {
        return l6;
    }

    public void setL6(String l6) {
        this.l6 = l6;
    }

    public String getL7() {
        return l7;
    }

    public void setL7(String l7) {
        this.l7 = l7;
    }

    public String getL8() {
        return l8;
    }

    public void setL8(String l8) {
        this.l8 = l8;
    }

    public String getDescripcion5() {
        return descripcion5;
    }

    public void setDescripcion5(String descripcion5) {
        this.descripcion5 = descripcion5;
    }

    public String getUbicacion5() {
        return ubicacion5;
    }

    public void setUbicacion5(String ubicacion5) {
        this.ubicacion5 = ubicacion5;
    }

    public String getCantidad5() {
        return cantidad5;
    }

    public void setCantidad5(String cantidad5) {
        this.cantidad5 = cantidad5;
    }

    public String getDescripcion6() {
        return descripcion6;
    }

    public void setDescripcion6(String descripcion6) {
        this.descripcion6 = descripcion6;
    }

    public String getUbicacion6() {
        return ubicacion6;
    }

    public void setUbicacion6(String ubicacion6) {
        this.ubicacion6 = ubicacion6;
    }

    public String getCantidad6() {
        return cantidad6;
    }

    public void setCantidad6(String cantidad6) {
        this.cantidad6 = cantidad6;
    }

    public String getDescripcion7() {
        return descripcion7;
    }

    public void setDescripcion7(String descripcion7) {
        this.descripcion7 = descripcion7;
    }

    public String getUbicacion7() {
        return ubicacion7;
    }

    public void setUbicacion7(String ubicacion7) {
        this.ubicacion7 = ubicacion7;
    }

    public String getCantidad7() {
        return cantidad7;
    }

    public void setCantidad7(String cantidad7) {
        this.cantidad7 = cantidad7;
    }

    public String getDescripcion8() {
        return descripcion8;
    }

    public void setDescripcion8(String descripcion8) {
        this.descripcion8 = descripcion8;
    }

    public String getUbicacion8() {
        return ubicacion8;
    }

    public void setUbicacion8(String ubicacion8) {
        this.ubicacion8 = ubicacion8;
    }

    public String getCantidad8() {
        return cantidad8;
    }

    public void setCantidad8(String cantidad8) {
        this.cantidad8 = cantidad8;
    }

    public Boolean getPlu5() {
        return plu5;
    }

    public void setPlu5(Boolean plu5) {
        this.plu5 = plu5;
    }

    public Boolean getPlu6() {
        return plu6;
    }

    public void setPlu6(Boolean plu6) {
        this.plu6 = plu6;
    }

    public Boolean getPlu7() {
        return plu7;
    }

    public void setPlu7(Boolean plu7) {
        this.plu7 = plu7;
    }

    public Boolean getPlu8() {
        return plu8;
    }

    public void setPlu8(Boolean plu8) {
        this.plu8 = plu8;
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

    public ndProducto() {

    }

    public String getInventario() {
        return inventario;
    }

    public void setInventario(String inventario) {
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

    public String getIvaC() {
        return ivaC;
    }

    public void setIvaC(String ivaC) {
        this.ivaC = ivaC;
    }

    public String getUbicacion1() {
        return ubicacion1;
    }

    public void setUbicacion1(String ubicacion1) {
        this.ubicacion1 = ubicacion1;
    }

    public String getDescripcion2() {
        return descripcion2;
    }

    public void setDescripcion2(String descripcion2) {
        this.descripcion2 = descripcion2;
    }

    public String getUbicacion2() {
        return ubicacion2;
    }

    public void setUbicacion2(String ubicacion2) {
        this.ubicacion2 = ubicacion2;
    }

    public String getCantidad2() {
        return cantidad2;
    }

    public void setCantidad2(String cantidad2) {
        this.cantidad2 = cantidad2;
    }

    public String getDescripcion3() {
        return descripcion3;
    }

    public void setDescripcion3(String descripcion3) {
        this.descripcion3 = descripcion3;
    }

    public String getUbicacion3() {
        return ubicacion3;
    }

    public void setUbicacion3(String ubicacion3) {
        this.ubicacion3 = ubicacion3;
    }

    public String getCantidad3() {
        return cantidad3;
    }

    public void setCantidad3(String cantidad3) {
        this.cantidad3 = cantidad3;
    }

    public String getDescripcion4() {
        return descripcion4;
    }

    public void setDescripcion4(String descripcion4) {
        this.descripcion4 = descripcion4;
    }

    public String getUbicacion4() {
        return ubicacion4;
    }

    public void setUbicacion4(String ubicacion4) {
        this.ubicacion4 = ubicacion4;
    }

    public String getCantidad4() {
        return cantidad4;
    }

    public void setCantidad4(String cantidad4) {
        this.cantidad4 = cantidad4;
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

    public Boolean isPlu2() {
        return plu2;
    }

    public void setPlu2(Boolean plu2) {
        this.plu2 = plu2;
    }

    public Boolean isPlu3() {
        return plu3;
    }

    public void setPlu3(Boolean plu3) {
        this.plu3 = plu3;
    }

    public Boolean isPlu4() {
        return plu4;
    }

    public void setPlu4(Boolean plu4) {
        this.plu4 = plu4;
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
        return l1;
    }

    public void setL1(String l1) {
        this.l1 = l1;
    }

    public String getL2() {
        return l2;
    }

    public void setL2(String l2) {
        this.l2 = l2;
    }

    public String getL3() {
        return l3;
    }

    public void setL3(String l3) {
        this.l3 = l3;
    }

    public String getL4() {
        return l4;
    }

    public void setL4(String l4) {
        this.l4 = l4;
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

}
