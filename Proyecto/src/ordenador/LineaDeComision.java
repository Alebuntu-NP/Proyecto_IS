package ordenador;

import java.util.Calendar;
import java.util.Date;
/**
 *
 * @author Alberto García Gonzalez,Juan Moreno Galbarro,Alejandro Román Caballero
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
        
        String LDC;
        String fa = fechaAlta.get(Calendar.DATE) + "/"+(fechaAlta.get(Calendar.MONTH)+1)+"/"+fechaAlta.get(Calendar.YEAR);
        LDC = "DNI del Miembro: "+ miembro.getDni() + " Fecha de Alta: " + fa + " Fecha de Baja: ";
        String fb;
        
        if(fechaBaja == null){
            LDC += null;
        }
        else{
           fb = fechaBaja.get(Calendar.DATE) + "/"+(fechaBaja.get(Calendar.MONTH)+1)+"/"+fechaBaja.get(Calendar.YEAR);
           LDC += fb;
        }
        
        return  LDC +" Puesto: " + puesto;
    }
    
    

}
