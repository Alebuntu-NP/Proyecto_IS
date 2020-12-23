package ordenador;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Alberto García Gonzalez,Juan Moreno Galbarro,Alejandro Román Caballero
 */
// Terminado solo falta revision por parte del grupo
public class Departamento implements IDepartamento {

    private String nombre;
    private List<Miembro> listMiem;
    private List<Comision> listCom;

    public Departamento(String nombre) {

        this.nombre = nombre;
        this.listMiem = new ArrayList();
        this.listCom = new ArrayList();
    }

    // Aqui he cambiado el void por un int para asi ver al que error se refiere
    @Override
    public int miembroNuevo(Miembro m) {
        int res = 0;
        if (listMiem.contains(m)) {
            res = 0; // devolverá -1 si el miembro que estamos introduciendo ya existe.
        } else {
            listMiem.add(m);
            res = 1;
        }
        return res;

    }

    @Override
    public Miembro obtenerMiembro(String dni) {
        Miembro m = null;
        Miembro maux;
        Iterator it;
        it = listMiem.iterator();
        while (it.hasNext()) {
            maux = (Miembro) it.next();
            if (maux.getDni().equals(dni)) {
                m = maux;
            }
        }
        return m;
    }

    @Override
    public void eliminarMiembro(Miembro m) {
        listMiem.remove(m);
    }

    @Override
    public int comisionNueva(Comision c) {
        int res = 0;
        if (listCom.contains(c)) {
            res = -1;

        } else {
            listCom.add(c);

        }
        return res;
    }

    @Override
    public Comision obtenerComision(String nombre) {
        Comision c = null;
        Comision caux;
        Iterator it;
        it = listCom.iterator();
        while (it.hasNext()) {
            caux = (Comision) it.next();
            if (caux.getNombre().equals(nombre)) {
                c = caux;
            }
        }
        return c;
    }

    @Override
    public List<Comision> obtenerListaComision() {
        return listCom;
    }

}
