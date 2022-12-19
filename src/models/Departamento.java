package models;

public class Departamento {
    public int id;
    public String nombre;

    public Departamento(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Departamento{"+
                "id='"+this.id+'\''+
                ", nombre='"+nombre+'}';
    }
}
