package repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteDaoManagerImpl implements DAOManager{
    //final String RUTABASE = Path.of("src/db/examen.db").toString();
    String host = "database-1.cf3lbfs1lyrq.us-east-1.rds.amazonaws.com";
    String bbdd = "empresaLaura";

    String dbUrl = "jdbc:mariadb://"+host+"/"+bbdd;
    String usuario = "laura";
    String passwd = "password";
    private Connection conexion;
    private EmpleadoDAO empleados = null;
    private DepartamentoDAO departamentos = null;
    private DireccionDAO direcciones = null;

    public SQLiteDaoManagerImpl() {
        try {
            conexion = DriverManager.getConnection(dbUrl, usuario, passwd);
        } catch (Exception e) {
            e.getMessage();
        }
    }
    public void close() {
        try {
            conexion.close();
        } catch (SQLException e) {
            System.exit(0);
        }
    }
    @Override
    public EmpleadoDAO getEmpleadoDAO() {
        if (empleados == null) {
            empleados = new SQLiteEmpleadoDAOImpl(conexion);
        }
        return empleados;
    }
    @Override
    public DepartamentoDAO getDepartamentoDAO() {
        if(departamentos == null) {
            departamentos = new SQLiteDepartamentoDAOImpl(conexion);
        }
        return departamentos;
    }
    public DireccionDAO getDireccionDAO() {
        if(direcciones == null) {
            direcciones = new SQLiteDireccionDAOImpl(conexion);
        }
        return direcciones;
    }
}
