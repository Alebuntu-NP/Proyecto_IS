package ordenador;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Alberto García Gonzalez,Juan Moreno Galvarro,Alejandro Román Caballero
 */
public class Reunion implements IReunion {

    private String titulo;
    private Calendar fecha;
    private Calendar fecha_convocatoria = null;
    private Calendar horaInicio;
    private Calendar horaFin;
    private String lugar;
    private List<Miembro> listM;
    private List<PuntoOrdenDia> listPod;

    public Reunion(String titulo, Calendar fecha, Calendar horaInicio, Calendar horaFin, String lugar) {
        this.titulo = titulo;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.lugar = lugar;
        this.listM = new ArrayList();
        this.listPod = new ArrayList();
    }

    @Override
    public void puntoOrdenDiaNuevo(PuntoOrdenDia pod) {
        listPod.add(pod);
    }

    @Override
    public void setFechaConvocatoria(Calendar fecha_convocatoria) {

        this.fecha_convocatoria = fecha_convocatoria;

    }

    @Override
    public void cambiaDatosHoraFin(Calendar horaFin) {

        this.horaFin = horaFin;
    }

    @Override
    public PuntoOrdenDia obtenerPuntoOrdenDia(String url) {

        PuntoOrdenDia pod;
        Iterator it;

        it = listPod.iterator();

        while (it.hasNext()) {

            pod = (PuntoOrdenDia) it.next();
            if (pod.getUrl().equals(url)) {

                return pod;

            }

        }
        pod = null;
        return pod;
    }

    public String getTitulo() {
        return titulo;
    }

    public Calendar getFecha() {
        return fecha;
    }

}