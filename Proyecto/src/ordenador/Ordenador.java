package ordenador;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Alberto García Gonzalez,Juan Moreno Galbarro,Alejandro Román Caballero
 */
public class Ordenador implements IOrdenador {

    private Miembro miembro;
    private Departamento departamento;
    private PuntoOrdenDia puntoOrdenDia;
    private Reunion reunion;
    private Comision comision;

    public static final String LETRAS_ROJAS = "\u001B[31m";
    public static final String LETRAS_DEFAULT = "\u001B[0m";

    public Ordenador(Departamento departamento) {
        this.departamento = departamento;
    }

    @Override
    public int addMiembro(String nombre, String apellidos, String dni, String direccion, int telefono, String email) {
        int resultadoAnadir = 0;
        try {
            //patron para validar el email
            Pattern patronEmail = Pattern.compile("([A-Za-z0-9]+(\\.?[A-Za-z0-9])*)+@(([A-Za-z]+)\\.([A-Za-z]+))+");
            //patron para validar dni 
            boolean nif = validarNIF(dni);

            if (patronEmail.matcher(email).find() == true && nif) {
                miembro = new Miembro(nombre, apellidos, dni, direccion, telefono, email);
                resultadoAnadir = departamento.miembroNuevo(miembro);
            } else {
                resultadoAnadir = -1;
                if (!nif && patronEmail.matcher(email).find() != true) {
                    System.out.println(LETRAS_ROJAS + "Introduzca un DNI y un email válido" + LETRAS_DEFAULT);
                } else if (!nif) {
                    System.out.println(LETRAS_ROJAS + "Introduzca un DNI válido" + LETRAS_DEFAULT);
                } else if (patronEmail.matcher(email).find() != true) {
                    System.out.println(LETRAS_ROJAS + "Introduzca un email válido" + LETRAS_DEFAULT);
                }
            }

        } catch (Exception e) {
            System.out.println("datos incorrectos");
            resultadoAnadir = -1;
        }

        return resultadoAnadir;
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
    public int cambiarMiembro(String nombre, String apellidos, String direccion, int telefono, String email) {
        int resultadoCambiar = 0;
        try {
            //patron para validar el email
            Pattern patronEmail = Pattern.compile("([A-Za-z0-9]+(\\.?[A-Za-z0-9])*)+@(([A-Za-z]+)\\.([A-Za-z]+))+");

            if (patronEmail.matcher(email).find() == true) {
                miembro.modificaMiembro(nombre, apellidos, direccion, telefono, email);
                resultadoCambiar = 0;
            } else {
                resultadoCambiar = -1;
                if (patronEmail.matcher(email).find() != true) {
                    System.out.println(LETRAS_ROJAS + "Introduzca un email válido" + LETRAS_DEFAULT);
                }
            }

        } catch (Exception e) {
            System.out.println("datos incorrectos");
            resultadoCambiar = -1;
        }
        return resultadoCambiar;
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
    public void listarReunionAnyo(int anyo) {

        List<Reunion> listR = comision.obtenerReunionAnual(anyo);

        Iterator it;

        it = listR.iterator();

        while (it.hasNext()) {
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
        puntoOrdenDia.cambioDatosResolucion(resolucion);
    }
    
    public boolean validarNIF(String nif) {

        boolean correcto = false;
        Pattern pattern = Pattern.compile("(\\d{1,8})([TRWAGMYFPDXBNJZSQVHLCKEtrwagmyfpdxbnjzsqvhlcke])");
        Matcher matcher = pattern.matcher(nif);
        if (matcher.matches()) {
            String letra = matcher.group(2);
            String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
            int index = Integer.parseInt(matcher.group(1));
            index = index % 23;
            String reference = letras.substring(index, index + 1);
            if (reference.equalsIgnoreCase(letra)) {
                correcto = true;
            } else {
                correcto = false;
            }
        } else {
            correcto = false;
        }
        return correcto;
    }

}
