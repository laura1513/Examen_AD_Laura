package models;

public class Empleado {
    private String nombre;
    private int id;
    private String apellido;
    private String email;
    private double sueldo;

    public Empleado(int id, String nombre, String apellido, String email, double sueldo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.sueldo = sueldo;
    }

    //Getters
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getEmail() { return email; }
    public double getSueldo() { return sueldo; }

    //Setters
    public void setId(int id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public void setEmail(String email) { this.email = email; }
    public void setSueldo(double sueldo) { this.sueldo = sueldo; }

    @Override
    public String toString() {
        return "Empleado {"+
                "id='"+id+'\''+
                ", nombre='"+nombre+'\''+
                ", apellidos='"+apellido+'\''+
                ", email='"+email+'\''+
                ", sueldo='"+sueldo+
                '}';
    }
}
