package Impresiones.Documentos;

@FunctionalInterface
public interface ImpresionDocumento {

    void generar(int numeroCopia, boolean imprimirDirecto);
}
