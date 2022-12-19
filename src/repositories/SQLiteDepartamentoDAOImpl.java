package repositories;

import models.Departamento;
import models.Empleado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLiteDepartamentoDAOImpl implements DepartamentoDAO{
    final String FINDALL = "SELECT * FROM departamentos";
    final String FINDBYID = "SELECT * FROM departamentos WHERE id = ?";
    final String SAVE = "INSERT INTO departamentos (nombre) VALUES (?)";
    final String UPDATE = "UPDATE departamentos SET nombre = ? WHERE nombre = ?";
    final String DELETE = "DELETE FROM departamentos WHERE id = ?";
    private Connection conexion = null;

    public SQLiteDepartamentoDAOImpl(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public List<Departamento> findAll() {
        List<Departamento> departamentos = new ArrayList<>();
        try (PreparedStatement sentencia = conexion.prepareStatement(FINDALL)) {
            ResultSet rs = sentencia.executeQuery();
            while (rs.next())  {
                departamentos.add(convertToDepartamento(rs));
            }
        } catch (Exception e) {
            e.getMessage();
            System.exit(0);
        }
        return departamentos;
    }
    @Override
    public Departamento findById(String id) {
        Departamento departamento = null;
        try (PreparedStatement sentencia = conexion.prepareStatement(FINDBYID)) {
            sentencia.setString(1, id);
            ResultSet rs = sentencia.executeQuery();
            if(rs.next()) {
                departamento = convertToDepartamento(rs);
            }
        } catch (Exception e) {
            e.getMessage();
            System.exit(0);
        }
        return departamento;
    }

    @Override
    public void save(Departamento departamento) {
        try(PreparedStatement sentencia = conexion.prepareStatement(SAVE)){
            sentencia.setString(1, departamento.getNombre());
            sentencia.executeUpdate();
        } catch (Exception e) {
            e.getMessage();
            System.exit(0);
        }
    }

    @Override
    public void update(Departamento departamento) {
        try(PreparedStatement sentencia = conexion.prepareStatement(UPDATE)){
            sentencia.setString(2, departamento.getNombre());
            sentencia.setString(1, departamento.getNombre());

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
    @Override
    public void increment(Departamento departamento) {
        return;
    }
    private Departamento convertToDepartamento(ResultSet rs) throws SQLException {
        return new Departamento(
                rs.getString("nombre"));
    }
}
