package ordenador;

import java.util.List;

/**
 *
 * @author Alberto García Gonzalez,Juan Moreno Galbarro,Alejandro Román Caballero
 */


// Revisar que estos metodos esta igual al diagrama de clases y a los DSS
public interface IComision {

    public void crearLineaDeComision(String puesto,Miembro m);

    public void borrarMiembro(Miembro m);

    public void reunionNueva(Reunion r);

    public List<Reunion> obtenerReunionAnual(int anyo);

    public Reunion obtenerReunion(String titulo);
}
