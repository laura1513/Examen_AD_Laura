package repositories;

import models.Empleado;

import java.nio.channels.Pipe;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLiteEmpleadoDAOImpl implements EmpleadoDAO{
    final String FINDALL = "SELECT * FROM empleados";
    /*
    SELECT *
    FROM empleados
    INNER JOIN direcciones
    ON direcciones.empleado_id = empleados.id
    */
    final String FINDBYID = "SELECT * FROM empleados WHERE id = ?";
    /*
    SELECT *
    FROM empleados
    INNER JOIN direcciones
    ON empleados.id = direcciones.empleado_id
    WHERE id = ?*/
    final String SAVE = "INSERT INTO empleados (ombre, apellido, email, sueldo) VALUES (?, ?, ?, ?)";
    /*
    INSERT INTO empleados (id, nombre, apellido, email, sueldo, direccion)
    VALUES (?, ?, ?, ?, ?, ?)*/
    final String UPDATE = "UPDATE empleados SET nombre = ?, apellido = ?, email = ?, sueldo = ? WHERE nombre = ?";
    /*
    UPDATE empleados
    INNER JOIN direcciones
    ON empleados.id = direcciones.empleado_id
    SET nombre = ?, apellido = ?, email = ?, sueldo = ?, direccion = ?
    WHERE id = ?
     */
    final String DELETE = "DELETE FROM empleados WHERE id = ?";
    /*
    DELETE FROM empleados
    INNER JOIN direcciones
    ON direcciones.empleado_id = empleados.id
    WHERE id = ?
     */
    final String INCREMENT = "UPDATE empleados SET sueldo = (sueldo+sueldo*0.1) WHERE sueldo < ?";
    private Connection conexion = null;

    public SQLiteEmpleadoDAOImpl(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public List<Empleado> findAll() {
        List<Empleado> empleados = new ArrayList<>();
        try (PreparedStatement sentencia = conexion.prepareStatement(FINDALL)) {
            ResultSet rs = sentencia.executeQuery();
            while (rs.next())  {
                empleados.add(convertToEmpleado(rs));
            }
        } catch (Exception e) {
            e.getMessage();
            System.exit(0);
        }
        return empleados;
    }
    @Override
    public Empleado findById(String id) {
        Empleado empleado = null;
        try (PreparedStatement sentencia = conexion.prepareStatement(FINDBYID)) {
            sentencia.setString(1, id);
            ResultSet rs = sentencia.executeQuery();
            if(rs.next()) {
                empleado = convertToEmpleado(rs);
            }
        } catch (Exception e) {
            e.getMessage();
            System.exit(0);
        }
        return empleado;
    }

    @Override
    public void save(Empleado empleado) {
        try(PreparedStatement sentencia = conexion.prepareStatement(SAVE)){
            sentencia.setString(1, empleado.getNombre());
            sentencia.setString(2, empleado.getApellido());
            sentencia.setString(3, empleado.getEmail());
            sentencia.setDouble(4, empleado.getSueldo());
            sentencia.executeUpdate();
        } catch (Exception e) {
            e.getMessage();
            System.exit(0);
        }
    }

    @Override
    public void update(Empleado empleado) {
        try(PreparedStatement sentencia = conexion.prepareStatement(UPDATE)){
            sentencia.setString(5, empleado.getNombre());
            sentencia.setString(1, empleado.getNombre());
            sentencia.setString(2, empleado.getApellido());
            sentencia.setString(3, empleado.getEmail());
            sentencia.setDouble(4, empleado.getSueldo());
            sentencia.executeUpdate();
        } catch (Exception e) {
            e.getMessage();
            System.exit(0);
        }
    }

    @Override
    public void deleteById(String id) {
        try (PreparedStatement sentencia = conexion.prepareStatement(DELETE)) {
            sentencia.setString(1, id);
            sentencia.executeUpdate();
        } catch ( Exception e ) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    public void increment(Empleado empleado) {
        try (PreparedStatement sentencia = conexion.prepareStatement(INCREMENT)) {
            sentencia.setDouble(1, empleado.getSueldo());
            sentencia.executeUpdate();
        } catch (Exception e) {
            e.getMessage();
        }
    }
    private Empleado convertToEmpleado(ResultSet rs) throws SQLException {
        return new Empleado(
                rs.getString("nombre"),
                rs.getString("apellido"),
                rs.getString("email"),
                rs.getDouble("sueldo"));
    }
}
