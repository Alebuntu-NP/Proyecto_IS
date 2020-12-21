package ordenador;

import java.util.Calendar;
import java.util.Date;
/**
 *
 * @author Alberto García Gonzalez,Juan Moreno Galvarro,Alejandro Román Caballero
 */
public class LineaDeComision implements ILineaDeComision {

    private Calendar fechaAlta;
    private Calendar fechaBaja;
    private String puesto;
    private Miembro miembro;

    public LineaDeComision(Calendar fechaAlta, Calendar fechaBaja, String puesto, Miembro miembro) {
        this.fechaAlta = fechaAlta;
        this.fechaBaja = fechaBaja;
        this.puesto = puesto;
        this.miembro = miembro;
    }
    
    
    public void setFechaBaja(Calendar fechaActual) {
        
        this.fechaBaja= fechaActual;

    }

    public Miembro getMiembro() {
        return miembro;
    }

    public Calendar getFechaAlta() {
        return fechaAlta;
    }

    public Calendar getFechaBaja() {
        return fechaBaja;
    }

    public String toString() {
        return "DNI del Miembro: "+ miembro.getDni() + " Fecha de Alta: " + fechaAlta.getTime() + " Fecha de Baja: " + fechaBaja.getTime() + " Puesto: " + puesto;
    }
    
    

}
