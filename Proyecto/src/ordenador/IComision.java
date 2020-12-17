package ordenador;

/**
 *
 * @author Alberto García Gonzalez,Juan Moreno Galvarro,Alejandro Román Caballero
 */


// Revisar que estos metodos esta igual al diagrama de clases y a los DSS
public interface IComision {

    public void crearLineaDeComision(String puesto,Miembro m);

    public void borrarMiembro(Miembro m);

    public void reunionNueva(Reunion r);

    public Reunion obtenerReunionAnual(String titulo, int anyo);

    public Reunion obtenerReunion(String titulo);
}
