package ordenador;

import java.util.List;
/**
 *
 * @author Alberto García Gonzalez,Juan Moreno Galbarro,Alejandro Román Caballero
 */

public interface IDepartamento {

    public int miembroNuevo(Miembro m);

    public Miembro obtenerMiembro(String dni);

    public void eliminarMiembro(Miembro m);

    public int comisionNueva(Comision c);

    public Comision obtenerComision(String nombre);

    public List<Comision> obtenerListaComision();

}
