import models.Departamento;
import models.Empleado;
import repositories.DepartamentoDAO;
import repositories.SQLiteDaoManagerImpl;

public class Main {
    public static void main(String[] args) {
        SQLiteDaoManagerImpl empresa = new SQLiteDaoManagerImpl();

        System.out.println("\nMostrar todos");
        empresa.getDepartamentoDAO().findAll().forEach(System.out::println);

        System.out.println("\nMostrar solo uno");
        System.out.println(empresa.getDepartamentoDAO().findById("1"));

        System.out.println("\nInsertar un departamento");
        Departamento social = new Departamento("Social");
        empresa.getDepartamentoDAO().save(social);
        empresa.getDepartamentoDAO().findAll().forEach(System.out::println);

        System.out.println("\nActualizar un departamento");
        social.setNombre("Humanidades");
        empresa.getDepartamentoDAO().update(social);
        empresa.getDepartamentoDAO().findAll().forEach(System.out::println);

        System.out.println("\nBorrar un departamento");
        empresa.getDepartamentoDAO().deleteById("Humanidades");
        empresa.getDepartamentoDAO().findAll().forEach(System.out::println);
        //-------------------------------------------------------------------
        System.out.println("\nMostrar todos");
        empresa.getEmpleadoDAO().findAll().forEach(System.out::println);

        System.out.println("\nMostrar solo uno");
        System.out.println(empresa.getEmpleadoDAO().findById("1"));

        System.out.println("\nInsertar un empleado");
        Empleado harry = new Empleado("Harry", "Styles", "harrystyles@gmail.com", 89.99);
        empresa.getEmpleadoDAO().save(harry);
        empresa.getEmpleadoDAO().findAll().forEach(System.out::println);

        System.out.println("\nActualizar un empleado");
        harry.setNombre("Roberto");
        empresa.getEmpleadoDAO().update(harry);
        empresa.getEmpleadoDAO().findAll().forEach(System.out::println);

        System.out.println("\nBorrar un empleado");
        empresa.getEmpleadoDAO().deleteById("999");
        empresa.getEmpleadoDAO().findAll().forEach(System.out::println);

        empresa.close();
    }
}