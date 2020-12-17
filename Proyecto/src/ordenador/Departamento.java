package ordenador;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Alberto García Gonzalez,Juan Moreno Galvarro,Alejandro Román Caballero
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
        int res;
        if (listMiem.contains(m)) {
            res = -2;

        } else {
            if (listMiem.add(m)) {
                res = 0;
            } else {
                
                res = -1;

            }

        }
        return res;

    }

    @Override
    public Miembro obtenerMiembro(String dni) {
        Miembro m = null;
        Iterator it;

        it = listMiem.iterator();

        while (it.hasNext()) {

            m = (Miembro) it.next();
            if (m.getDni().equals(dni)) {

                return m;

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
        int res;
        if (listCom.contains(c)) {
            res = -2;

        } else {
            if (listCom.add(c)) {
                res = 0;
            } else {
                res = -1;

            }

        }
        return res;
    }

    @Override
    public Comision obtenerComision(String nombre) {
        Comision c = null;
        Iterator it;

        it = listCom.iterator();

        while (it.hasNext()) {

            c = (Comision) it.next();
            if (c.getNombre().equals(nombre)) {

                return c;

            }

        }

        return c;
    }

    @Override
    public List<Comision> obtenerListaComision() {
        return listCom;
    }

}
