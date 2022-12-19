package models;

public class Direccion {
    public int id;
    public int id_empleado;
    public String calle;
    public String ciudad;
    public String pais;

    public Direccion(int id, int id_empleado, String calle, String ciudad, String pais) {
        this.id = id;
        this.id_empleado = id_empleado;
        this.calle = calle;
        this.ciudad = ciudad;
        this.pais = pais;
    }

    public int getId() {
        return id;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public String getCalle() {
        return calle;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    @Override
    public String toString() {
        return "Direccion{"+
                "id='"+id+'\''+
                ", id empleado='"+id_empleado+'\''+
                ", calle='"+calle+'\''+
                ", ciudad='"+ciudad+'\''+
                ", pais='"+pais;
    }
}
