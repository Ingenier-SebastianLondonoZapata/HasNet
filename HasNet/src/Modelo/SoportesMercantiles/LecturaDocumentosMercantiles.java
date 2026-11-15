package Modelo.SoportesMercantiles;

import java.util.ArrayList;
import java.util.List;

public class LecturaDocumentosMercantiles {

    private final List<DocumentoMercantil> documentos = new ArrayList<>();
    private final List<String> avisos = new ArrayList<>();

    public List<DocumentoMercantil> getDocumentos() {
        return documentos;
    }

    public List<String> getAvisos() {
        return avisos;
    }
}
