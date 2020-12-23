
package ordenador;

import java.util.Calendar;

/**
 *
 * @author Alberto García Gonzalez,Juan Moreno Galbarro,Alejandro Román Caballero
 */


public interface IReunion {
    
    public void puntoOrdenDiaNuevo(PuntoOrdenDia pod);
    
    public void setFechaConvocatoria(Calendar fecha_convocatoria);
    
    public void cambiaDatosHoraFin(Calendar horaFin);
    
    public PuntoOrdenDia obtenerPuntoOrdenDia(String url);
}
