-- Agrega el estado a las resoluciones para poder inactivarlas sin borrarlas.
-- Ejecutar una sola vez antes de desplegar la nueva versión del código.
-- 1 = ACTIVA, 0 = INACTIVA. Las resoluciones existentes quedan activas.

ALTER TABLE bdResoluciones
    ADD COLUMN estado TINYINT(1) NOT NULL DEFAULT 1;
