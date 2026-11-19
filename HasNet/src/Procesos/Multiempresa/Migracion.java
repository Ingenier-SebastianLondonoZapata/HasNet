package Procesos.Multiempresa;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class Migracion {

    private final String id;
    private final List<String> sentencias;

    public Migracion(String id, String... sentencias) {
        if (id == null || !id.matches("[A-Za-z0-9_.-]{1,100}")) {
            throw new IllegalArgumentException("Identificador de migración inválido: " + id);
        }
        if (sentencias.length == 0) {
            throw new IllegalArgumentException("La migración " + id + " no tiene sentencias");
        }
        this.id = id;
        this.sentencias = Collections.unmodifiableList(Arrays.asList(sentencias));
    }

    public String getId() {
        return id;
    }

    public List<String> getSentencias() {
        return sentencias;
    }
}
