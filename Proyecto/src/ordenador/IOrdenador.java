package ordenador;

import java.util.Calendar;


/**
 *
 * @author Alberto García Gonzalez,Juan Moreno Galbarro,Alejandro Román Caballero
 */


public interface IOrdenador {

    public int addMiembro(String nombre, String apellidos, String dni, String direccion, int telefono, String email);

    public Miembro introducirDni(String dni);

    public void confirmarBaja();

    public int cambiarMiembro(String nombre, String apellidos, String direccion, int telefono, String email);

    public int addComision(String nombre, String descripcion);

    public Comision introducirComision(String nombre);

    public void introducirMiembro(String dni, String puesto);

    public void confirmarEliminacionMiembro();

    public void listarComisiones();

    public void addReunion(String titulo, Calendar fecha, Calendar horaInicio, Calendar horaFin, String lugar);

    public void addPuntoDia(String url, String titulo, String descripcion);

    public void confirmarReunion();

    public void listarReunionAnyo(int anyo);

    public Reunion introducirReunion(String titulo);

    public void introducirFechaConvocatoria(Calendar fecha_convocatoria);

    public void modificarHoraFin(Calendar horaFin);

    public PuntoOrdenDia introduceUrl(String url);

    public void modificaResolucion(String resolucion);

}
