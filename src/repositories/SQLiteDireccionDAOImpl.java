package repositories;

import models.Direccion;
import models.Empleado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLiteDireccionDAOImpl implements DireccionDAO{
    final String FINDALL = "SELECT * FROM direcciones";
    final String FINDBYID = "SELECT * FROM direcciones WHERE id = ?";
    final String SAVE = "INSERT INTO direcciones (empleado_id, calle, ciudad, pais) VALUES (?, ?, ?, ?)";
    final String UPDATE = "UPDATE direcciones SET empleado_id = ?, calle = ?, ciudad = ?, pais = ? WHERE id = ?";
    final String DELETE = "DELETE FROM direcciones WHERE id = ?";
    private Connection conexion = null;

    public SQLiteDireccionDAOImpl(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public List<Direccion> findAll() {
        List<Direccion> direccions = new ArrayList<>();
        try (PreparedStatement sentencia = conexion.prepareStatement(FINDALL)) {
            ResultSet rs = sentencia.executeQuery();
            while (rs.next())  {
                direccions.add(convertToDireccion(rs));
            }
        } catch (Exception e) {
            e.getMessage();
            System.exit(0);
        }
        return direccions;
    }
    @Override
    public Direccion findById(String id) {
        Direccion direccion = null;
        try (PreparedStatement sentencia = conexion.prepareStatement(FINDBYID)) {
            sentencia.setString(1, id);
            ResultSet rs = sentencia.executeQuery();
            if(rs.next()) {
                direccion = convertToDireccion(rs);
            }
        } catch (Exception e) {
            e.getMessage();
            System.exit(0);
        }
        return direccion;
    }

    @Override
    public void save(Direccion direccion) {
        try(PreparedStatement sentencia = conexion.prepareStatement(SAVE)){
            sentencia.setInt(1, direccion.getId_empleado());
            sentencia.setString(2, direccion.getCalle());
            sentencia.setString(3, direccion.getCiudad());
            sentencia.setString(4, direccion.getPais());
            sentencia.executeUpdate();
        } catch (Exception e) {
            e.getMessage();
            System.exit(0);
        }
    }

    @Override
    public void update(Direccion direccion) {
        try(PreparedStatement sentencia = conexion.prepareStatement(UPDATE)){
            sentencia.setInt(5, direccion.getId());
            sentencia.setInt(1, direccion.getId_empleado());
            sentencia.setString(2, direccion.getCalle());
            sentencia.setString(3, direccion.getCiudad());
            sentencia.setString(4, direccion.getPais());
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
    public void increment(Direccion direccion) {
    }
    private Direccion convertToDireccion(ResultSet rs) throws SQLException {
        return new Direccion(
                rs.getInt("id"),
                rs.getInt("empleado_id"),
                rs.getString("calle"),
                rs.getString("ciudad"),
                rs.getString("pais"));
    }
}
