package ordenador;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Alberto García Gonzalez,Juan Moreno Galvarro,Alejandro Román Caballero
 */
public class Comision implements IComision {

    private String nombre;
    private String descripcion;
    private List<LineaDeComision> listLdc;
    private List<Reunion> listR;

    public Comision(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.listLdc = new ArrayList();
        this.listR = new ArrayList();

    }

    @Override
    public void crearLineaDeComision(String puesto, Miembro m) {
        Calendar fechaAlta = Calendar.getInstance();
        Calendar fechaBaja = null;
        LineaDeComision ldc;
        //Te dice si el miembro esta en la comisión, damos por hehcho que no lo está de primeras
        boolean encontrado = false;
        Iterator it;
        it = listLdc.iterator();
        while (it.hasNext() && !encontrado) {
            ldc = (LineaDeComision) it.next();
            // Si está el miembro, cambiamos encontrado a true
            if (ldc.getMiembro() == m) {
                encontrado = true;
                //Miramos si el miembro encontrado está dado de baja o no
                //Si esta dado de alta
                if (ldc.getFechaBaja() == null) {
                    System.out.println("El miembro esta dado de alta en la comisión");
                } else {
                    //Si esta dado de baja 
                    encontrado = false;
                }
            }
        }
        //Si el miembro no esta dado de alta en una comision 
        if (!encontrado) {
            ldc = new LineaDeComision(fechaAlta, fechaBaja, puesto, m);
            this.listLdc.add(ldc);
        }

    }

    @Override
    public void borrarMiembro(Miembro m) {

        LineaDeComision ldc;
        Calendar fechaActual = Calendar.getInstance();
        boolean encontrado = false;
        Iterator it;
        it = listLdc.iterator();
        while (it.hasNext()) {
            ldc = (LineaDeComision) it.next();
            if (ldc.getMiembro() == m) {
                //Miramos si el miembro encontrado está dado de baja o no
                //Si esta dado de alta
                if (ldc.getFechaBaja() == null) {
                    encontrado = true;
                    ldc.setFechaBaja(fechaActual);
                } else {
                    //Si esta dado de baja 
                    System.out.println("El miembro ya está dado de baja");
                }
            }
        }
        if(!encontrado){
            System.out.println("El miembro no existe en l a comisión");
        }
        
    }

    @Override
    public void reunionNueva(Reunion r) {

        listR.add(r);

    }

    @Override
    public Reunion obtenerReunionAnual(String titulo, int anyo) {

        Reunion r = null;
        Iterator it;

        it = listR.iterator();

        while (it.hasNext()) {

            r = (Reunion) it.next();
            if (r.getTitulo().equals(titulo) && r.getFecha().get(Calendar.YEAR) == anyo) {

                return r;

            }

        }

        return r;

    }

    @Override
    public Reunion obtenerReunion(String titulo) {
        Reunion r = null;
        Iterator it;

        it = listR.iterator();

        while (it.hasNext()) {

            r = (Reunion) it.next();
            if (r.getTitulo().equals(titulo)) {

                return r;

            }

        }

        return r;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.nombre);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Comision other = (Comision) obj;
        return Objects.equals(this.nombre, other.nombre);
    }

    @Override
    public String toString() {
        return "Comision{" + "nombre=" + nombre + ", descripcion=" + descripcion + ", listLdc=" + listLdc + ", listR=" + listR + '}';
    }

}
