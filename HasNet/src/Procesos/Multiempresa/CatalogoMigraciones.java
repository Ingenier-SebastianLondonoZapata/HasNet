package Procesos.Multiempresa;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class CatalogoMigraciones {

    private static final List<Migracion> MIGRACIONES = Collections.unmodifiableList(Arrays.<Migracion>asList(
    ));

    private CatalogoMigraciones() {
    }

    public static List<Migracion> todas() {
        return MIGRACIONES;
    }
}
