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
    final String FINDBYID = "SELECT * FROM empleados WHERE id = ?";
    final String SAVE = "INSERT INTO empleados (id, nombre, apellido, email, sueldo) VALUES (?, ?, ?, ?, ?)";
    final String UPDATE = "UPDATE empleados SET nombre = ?, apellido = ?, email = ?, sueldo = ? WHERE id = ?";
    final String DELETE = "DELETE FROM empleados WHERE id = ?";
    final String INCREMENT = "UPDATE empleados SET sueldo = (sueldo+sueldo*0.1) WHERE sueldo <  ?";
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
            sentencia.setInt(1, empleado.getId());
            sentencia.setString(2, empleado.getNombre());
            sentencia.setString(3, empleado.getApellido());
            sentencia.setString(4, empleado.getEmail());
            sentencia.setDouble(5, empleado.getSueldo());
            sentencia.executeUpdate();
        } catch (Exception e) {
            e.getMessage();
            System.exit(0);
        }
    }

    @Override
    public void update(Empleado empleado) {
        try(PreparedStatement sentencia = conexion.prepareStatement(UPDATE)){
            sentencia.setInt(5, empleado.getId());
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
            sentencia.setDouble(5, empleado.getSueldo());
            sentencia.executeUpdate();
        } catch (Exception e) {
            e.getMessage();
        }
    }
    private Empleado convertToEmpleado(ResultSet rs) throws SQLException {
        return new Empleado(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getString("apellido"),
                rs.getString("email"),
                rs.getDouble("sueldo"));
    }
}
