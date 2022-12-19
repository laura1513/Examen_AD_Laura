import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
    public static void main(String[] args) {
        Path rutaBaseDatos = Path.of("src/db/examen.db");
        try (Connection conexion = DriverManager.getConnection("jdbc:sqlite:"+rutaBaseDatos.toString())){

        } catch (Exception e) {
            System.err.println(e.getClass().getName() +": "+e.getMessage());
            System.exit(0);
        }
    }
}