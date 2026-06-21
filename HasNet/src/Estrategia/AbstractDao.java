package Estrategia;

import Utilidades.BaseDatos.MySql_connection;
import Utilidades.Constantes;
import java.sql.Connection;

public abstract class AbstractDao {

    protected Connection getConnection() {

        return MySql_connection.getInstancia(Constantes.BASE_DATOS_PRINCIPAL).getConnection();
    }
}
