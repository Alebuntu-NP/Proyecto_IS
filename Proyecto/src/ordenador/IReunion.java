
package ordenador;

import java.util.Calendar;

/**
 *
 * @author Alberto García Gonzalez,Juan Moreno Galvarro,Alejandro Román Caballero
 */

// Revisar que estos metodos esta igual al diagrama de clases y a los DSS
public interface IReunion {
    
    public void puntoOrdenDiaNuevo(PuntoOrdenDia pod);
    
    public void setFechaConvocatoria(Calendar fecha_convocatoria);
    
    public void cambiaDatosHoraFin(Calendar horaFin);
    
    public PuntoOrdenDia obtenerPuntoOrdenDia(String url);
}
