package ordenador;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Alberto García Gonzalez,Juan Moreno Galvarro,Alejandro Román Caballero
 */
public class Ordenador implements IOrdenador {

    private Miembro miembro;
    private Departamento departamento;
    private PuntoOrdenDia puntoOrdenDia;
    private Reunion reunion;
    private Comision comision;

    public Ordenador(Departamento departamento) {
        this.departamento = departamento;
    }

    @Override
    public int addMiembro(String nombre, String apellidos, String dni, String direccion, int telefono, String email) {
        miembro = new Miembro(nombre, apellidos, dni, direccion, telefono, email);
        return departamento.miembroNuevo(miembro);

    }

    @Override
    public Miembro introducirDni(String dni) {

        miembro = departamento.obtenerMiembro(dni);
        return miembro;
    }

    @Override
    public void confirmarBaja() {
        departamento.eliminarMiembro(miembro);
    }

    @Override
    public void cambiarMiembro(String nombre, String apellidos, String direccion, int telefono, String email) {

        miembro.modificaMiembro(nombre, apellidos, direccion, telefono, email);
    }

    @Override
    public int addComision(String nombre, String descripcion) {
        comision = new Comision(nombre, descripcion);
        return departamento.comisionNueva(comision);
        
        
    }

    @Override
    public Comision introducirComision(String nombre) {

        comision = departamento.obtenerComision(nombre);
        return comision;
    }

    @Override
    public void introducirMiembro(String dni, String puesto) {

        miembro = introducirDni(dni);
        comision.crearLineaDeComision(puesto, miembro);

    }

    @Override
    public void confirmarEliminacionMiembro() {
        comision.borrarMiembro(miembro);
    }

    @Override
    public void listarComisiones() {

        List<Comision> listCom = departamento.obtenerListaComision();

        Iterator it;

        it = listCom.iterator();

        while (it.hasNext()) {
            System.out.println(it.next().toString());

        }

    }

    @Override
    public void addReunion(String titulo, Calendar fecha, Calendar horaInicio, Calendar horaFin, String lugar) {

        reunion = new Reunion(titulo, fecha, horaInicio, horaFin, lugar);

    }

    @Override
    public void addPuntoDia(String url, String titulo, String descripcion) {

        puntoOrdenDia = new PuntoOrdenDia(url, titulo, descripcion);

        reunion.puntoOrdenDiaNuevo(puntoOrdenDia);

    }

    @Override
    public void confirmarReunion() {

        comision.reunionNueva(reunion);

    }

    @Override
    public void listarReunionAnyo( int anyo) {

        List<Reunion> listR = comision.obtenerReunionAnual(anyo);

        Iterator it;
        
        it = listR.iterator();
        
        while(it.hasNext()){
            System.out.println(it.next());
        }
        
    }

    @Override
    public Reunion introducirReunion(String titulo) {

        reunion = comision.obtenerReunion(titulo);
        return reunion;
    }

    @Override
    public void introducirFechaConvocatoria(Calendar fecha_convocatoria) {

        reunion.setFechaConvocatoria(fecha_convocatoria);

    }

    @Override
    public void modificarHoraFin(Calendar horaFin) {

        reunion.cambiaDatosHoraFin(horaFin);
    }

    @Override
    public PuntoOrdenDia introduceUrl(String url) {

        puntoOrdenDia = reunion.obtenerPuntoOrdenDia(url);
        return puntoOrdenDia;
    }

    @Override
    public void modificaResolucion(String resolucion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
