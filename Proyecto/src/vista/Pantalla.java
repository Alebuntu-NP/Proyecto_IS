package vista;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ordenador.Ordenador;
import poo.io.IO;

/**
 *
 * @author Alberto García Gonzalez,Juan Moreno Galbarro,Alejandro Román Caballero
 */
public class Pantalla {

    private final Ordenador controlador;
    public static final String LETRAS_ROJAS = "\u001B[31m";
    public static final String LETRAS_DEFAULT = "\u001B[0m";

    public Pantalla(Ordenador controlador) {
        this.controlador = controlador;
    }

    public void mostrarOpciones() {

        int opc;
        do {
            System.out.println("\n\n\n\tMenú Principal:\n");
            System.out.println("\t1. Gestionar Miembro");
            System.out.println("\t2. Gestionar Comisión");
            System.out.println("\t3. Gestionar Reunión");
            System.out.println("\t----------------------");
            System.out.println("\t0. Salir");
            System.out.println("\n\n--> Introduzca una opción: ");
            opc = (int) IO.readNumber();
            while (opc < 0 || opc > 3) {
                System.out.println(LETRAS_ROJAS + "\n\n--> Introduzca una opción válida: " + LETRAS_DEFAULT);
                opc = (int) IO.readNumber();
                System.out.println("\n");
            }
            if (opc != 0) {
                realizarOpcion(opc);
            }
        } while (opc != 0);
    }

    private void realizarOpcion(int opc) {
        switch (opc) {
            case 1:
                pantallaMenuMiembro();
                break;
            case 2:
                pantallaMenuComision();
                break;
            case 3:
                pantallaMenuReunion();
                break;

        }
    }

    private void pantallaMenuMiembro() {

        int opc;

        do {
            System.out.println("\n\n\n\tMenú Gestionar Miembro:\n");
            System.out.println("\t1. Añadir Miembro");
            System.out.println("\t2. Eliminar Miembro");
            System.out.println("\t3. Modificar Miembro");
            System.out.println("\t4. Consultar Miembro");
            System.out.println("\t---------------------");
            System.out.println("\t0. Volver");
            System.out.print("\n\n--> Introduzca una opción: ");
            opc = (int) IO.readNumber();
            while (opc < 0 || opc > 4) {
                System.out.print(LETRAS_ROJAS + "--> Introduzca una opción valida: " + LETRAS_DEFAULT);
                opc = (int) IO.readNumber();
                System.out.println("\n");
            }
            if (opc != 0) {
                realizarOpcionGestionMiembro(opc);
                System.out.println("");
                System.out.println("Pulsa cualquier tecla para ir al menú");
                IO.readLine();
            }
        } while (opc != 0);
    }

    private void realizarOpcionGestionMiembro(int opc) {
        System.out.println("Opción elegida: " + opc);

        switch (opc) {
            case 1:
                mostrarAltaMiembro();
                break;
            case 2:
                mostrarBajaMiembro();
                break;
            case 3:
                mostrarModificacionMiembro();
                break;
            case 4:
                mostrarConsultaMiembro();
                break;

        }
    }

    private void mostrarAltaMiembro() {

        int fallo;
        do {
            System.out.println("Introduzca los datos del Miembro");
            System.out.print("DNI: ");
            String dni = IO.readLine();
            System.out.print("Nombre: ");
            String nombre = IO.readLine();
            System.out.print("Apellidos: ");
            String apellidos = IO.readLine();
            System.out.print("Dirección: ");
            String direccion = IO.readLine();
            System.out.print("Teléfono: ");
            int telefono = (int) IO.readNumber();
            System.out.print("Email: ");
            String email = IO.readLine();

            fallo = controlador.addMiembro(nombre, apellidos, dni, direccion, telefono, email);
            System.out.println("");

            if (fallo == 0) {
                System.out.println(LETRAS_ROJAS + "El miembro con DNI " + dni + " ya existe" + LETRAS_DEFAULT);

            } else if (fallo == 1) {
                System.out.println("El miembro se ha registrado correctamente.");
            }
        } while (fallo == -1);

    }


    private void mostrarBajaMiembro() {

        String dni;
        System.out.print("Introduzca dni del Miembro: ");
        dni = IO.readLine();
        if (controlador.introducirDni(dni) != null) {
            System.out.println(controlador.introducirDni(dni).toString());
            int opc = 0;
            while (opc != 1 && opc !=2) {
                System.out.println("\t1. Confimar eliminación");
                System.out.println("\t2. No eliminar miembro");
                opc = (int) IO.readNumber();
                System.out.println("");

                switch (opc) {
                    case 1:
                        controlador.confirmarBaja();
                        System.out.println("Miembro eliminado correctamente.");
                        break;
                    case 2:
                        System.out.println("No se ha eliminado el miembro.");
                        break;
                    default:
                        System.out.println(LETRAS_ROJAS + "Opción inválida." + LETRAS_DEFAULT);
                        System.out.println("");
                        break;

                }
            }
        } else {

            System.out.println(LETRAS_ROJAS + "No se puede eliminar el miembro con el dni " + dni + ". Actualmente no existe." + LETRAS_DEFAULT);
        }
    }

    private void mostrarModificacionMiembro() {

        int fallo;

        System.out.print("Introduzca dni del Miembro: ");
        String dni = IO.readLine();
        if (controlador.introducirDni(dni) != null) {
            do {
                System.out.println("Introduzca los nuevos datos del miembro.");
                System.out.print("Nombre: ");
                String nombre = IO.readLine();
                System.out.print("Apellidos: ");
                String apellidos = IO.readLine();
                System.out.print("Dirección: ");
                String direccion = IO.readLine();
                System.out.print("Telefono: ");
                int telefono = (int) IO.readNumber();
                System.out.print("Email: ");
                String email = IO.readLine();

                fallo = controlador.cambiarMiembro(nombre, apellidos, direccion, telefono, email); //la variable se pondrá a 0 si el cliente se ha modificado correctamente, o a -1 si ha habido algun fallo
                System.out.println("");
            } while (fallo == -1);

            System.out.println("El miembro se ha modificado correctamente");
        } else {
            System.out.println(LETRAS_ROJAS + "El cliente seleccionado no existe." + LETRAS_DEFAULT);
        }
    }

    private void mostrarConsultaMiembro() {

        String dni;
        System.out.println("Introduzca dni del Miembro: ");
        dni = IO.readLine();
        if (controlador.introducirDni(dni) != null) {

            System.out.print(controlador.introducirDni(dni).toString());

        } else {

            System.out.println(LETRAS_ROJAS + "El Miembro no existe." + LETRAS_DEFAULT);
        }
    }

    private void pantallaMenuComision() {

        int opc;

        do {
            System.out.println("\n\n\n\tMenú Gestionar Comisión:\n");
            System.out.println("\t1. Añadir Comisión");
            System.out.println("\t2. Incluir Miembro en Comisión");
            System.out.println("\t3. Eliminar Miembro de Comisión");
            System.out.println("\t4. Listar Comisiones");
            System.out.println("\t---------------------");
            System.out.println("\t0. Volver");
            System.out.print("\n\n--> Introduzca una opción: ");
            opc = (int) IO.readNumber();
            while (opc < 0 || opc > 4) {
                System.out.print(LETRAS_ROJAS + "--> Introduzca una opción valida: " + LETRAS_DEFAULT);
                opc = (int) IO.readNumber();
                System.out.println("\n");
            }
            if (opc != 0) {
                realizarOpcionGestionComision(opc);
                System.out.println("");
                System.out.println("Pulsa cualquier tecla para ir al menú");
                IO.readLine();

            }
        } while (opc != 0);
    }

    private void realizarOpcionGestionComision(int opc) {
        System.out.println("Opción elegida: " + opc);

        switch (opc) {
            case 1:
                mostrarAltaComision();
                break;
            case 2:
                mostrarIncluirMiembroEnComision();
                break;
            case 3:
                mostrarEliminarMiembroDeComision();
                break;
            case 4:
                mostrarListarComisiones();
                break;
        }
    }

    private void mostrarAltaComision() {
        String nombre, descripcion;
        int fallo;

        System.out.println("Introduzca los datos de la Comisión");
        System.out.print("Nombre: ");
        nombre = IO.readLine();
        System.out.print("Descripcion: ");
        descripcion = IO.readLine();

        fallo = controlador.addComision(nombre, descripcion);
        System.out.println("");

        if (fallo == -1) {
            System.out.println(LETRAS_ROJAS + "La comisión ya existe" + LETRAS_DEFAULT);
        } else {
            System.out.println("La comisión se ha registrado correctamente.");

        }
    }

    private void mostrarIncluirMiembroEnComision() {

        System.out.println("Introduzca nombre de la Comisión: ");
        String nombre = IO.readLine();
        if (controlador.introducirComision(nombre) != null) {

            System.out.println(controlador.introducirComision(nombre).toString());
            System.out.println("\nIntroduzca DNI del Miembro: ");
            String dni = IO.readLine();

            if (controlador.introducirDni(dni) != null) {

                System.out.println(controlador.introducirDni(dni).toString());
                System.out.println("\nIntroduzca puesto del Miembro: ");
                String puesto = IO.readLine();
                controlador.introducirMiembro(dni, puesto);

            } else {
                System.out.println(LETRAS_ROJAS + "El Miembro no existe." + LETRAS_DEFAULT);
            }

        } else {
            System.out.println(LETRAS_ROJAS + "La comisión no existe." + LETRAS_DEFAULT);
        }

    }

    private void mostrarEliminarMiembroDeComision() {

        String dni;
        String nombre;
        System.out.println("Introduzca nombre de la Comision: ");
        nombre = IO.readLine();
        if (controlador.introducirComision(nombre) != null) {

            System.out.println(controlador.introducirComision(nombre).toString());

            System.out.println("Introduzca dni del Miembro: ");

            dni = IO.readLine();

            if (controlador.introducirDni(dni) != null) {

                System.out.println(controlador.introducirDni(dni).toString());

                controlador.confirmarEliminacionMiembro();

                System.out.println("Miembro dado de baja en la comision correctamentamente.");

            } else {

                System.out.println(LETRAS_ROJAS + "El Miembro no existe." + LETRAS_DEFAULT);
            }

        } else {

            System.out.println(LETRAS_ROJAS + "La Comision no existe." + LETRAS_DEFAULT);
        }

    }

    private void mostrarListarComisiones() {

        System.out.println("Lista de Comisiones");
        controlador.listarComisiones();

    }

    private void pantallaMenuReunion() {

        int opc;

        do {
            System.out.println("\n\n\n\tMenú Gestionar Reunión:\n");
            System.out.println("\t1. Crear Reunión");
            System.out.println("\t2. Consultar Reunión");
            System.out.println("\t3. Realizar Convocatoria");
            System.out.println("\t4. Completar Reunión");
            System.out.println("\t---------------------");
            System.out.println("\t0. Volver");
            System.out.print("\n\n--> Introduzca una opción: ");
            opc = (int) IO.readNumber();
            while (opc < 0 || opc > 4) {
                System.out.print(LETRAS_ROJAS + "--> Introduzca una opción valida: " + LETRAS_DEFAULT);
                opc = (int) IO.readNumber();
                System.out.println("\n");
            }
            if (opc != 0) {
                realizarOpcionGestionReunion(opc);
                System.out.println("");
                System.out.println("Pulsa cualquier tecla para ir al menú");
                IO.readLine();
            }
        } while (opc != 0);
    }

    private void realizarOpcionGestionReunion(int opc) {
        System.out.println("Opción elegida: " + opc);

        switch (opc) {
            case 1:
                mostrarCrearReunion();
                break;
            case 2:
                mostrarConsultarReunion();
                break;
            case 3:
                mostrarRealizarConvocatoria();
                break;
            case 4:
                mostrarCompletarReunion();
                break;

        }
    }

    private void mostrarCrearReunion() {

        int anyo, mes, dia;
        Calendar horaInicio = Calendar.getInstance();
        Calendar horaFin = Calendar.getInstance();
        String titulo_reunion, lugar, nombre, url, titulo_pod, descripcion;

        System.out.println("Introduzca nombre de la Comisión: ");
        nombre = IO.readLine();
        if (controlador.introducirComision(nombre) != null) {

            //METIENDO DATOS DE LA REUNION
            System.out.println(controlador.introducirComision(nombre).toString());
            System.out.print("Título: ");
            titulo_reunion = IO.readLine();
            if (controlador.introducirReunion(titulo_reunion) == null) {
                System.out.print("Lugar: ");
                lugar = IO.readLine();
                System.out.println("Introduzca la fecha de la reunión:");
                System.out.print("Año: ");
                anyo = (int) IO.readNumber();
                System.out.print("Mes: ");
                mes = (int) IO.readNumber();
                System.out.print("Día: ");
                dia = (int) IO.readNumber();
                System.out.println("Introduzca la hora de comienzo de la reunión:");
                System.out.print("Hora: ");
                int h = (int) IO.readNumber();
                System.out.print("Minuto: ");
                int m = (int) IO.readNumber();
                horaInicio.set(anyo, mes, dia, h, m, 0);
                do {
                    System.out.println("Introduzca la hora a la que debe terminar la reunión:");
                    System.out.print("Hora: ");
                    h = (int) IO.readNumber();
                    System.out.print("Minuto: ");
                    m = (int) IO.readNumber();
                    horaFin.set(anyo, mes, dia, h, m, 0);
                    if (horaFin.before(horaInicio)) {
                        System.out.println(LETRAS_ROJAS + "La hora de finalización de la reunión no puede ser anterior a la de inicio" + LETRAS_DEFAULT);
                    } else if (horaFin.equals(horaInicio)) {
                        System.out.println(LETRAS_ROJAS + "La hora de finalización de la reunión no puede ser igual a la de inicio" + LETRAS_DEFAULT);
                    }
                } while (horaFin.before(horaInicio) || horaFin.equals(horaInicio));
                controlador.addReunion(titulo_reunion, horaInicio, horaInicio, horaFin, lugar);
                int parada = 2;

                //METIENDO PUNTOS DEL ORDEN DEL DIA
                while (parada == 2) {
                    System.out.println("Introduzca los datos del punto del orden del dia");
                    System.out.print("URL: ");
                    url = IO.readLine();

                    if (controlador.introduceUrl(url) == null) {
                        System.out.print("Titulo del punto de orden del dia: ");
                        titulo_pod = IO.readLine();
                        System.out.print("Descripción: ");
                        descripcion = IO.readLine();
                        controlador.addPuntoDia(url, titulo_pod, descripcion);
                        do {
                            System.out.println("\t1. Confimar Reunión");
                            System.out.println("\t2. Añadir algún punto más");
                            parada = (int) IO.readNumber();
                            System.out.println("");
                            switch (parada) {
                                case 1:
                                    controlador.confirmarReunion();
                                    System.out.println("Reunión creada satisfactoriamente.");
                                    break;
                                case 2:
                                    System.out.println("Añadir punto del día.");
                                    break;
                                default:
                                    System.out.println(LETRAS_ROJAS + "Opción inválida." + LETRAS_DEFAULT);
                                    break;

                            }
                        } while (parada != 1 && parada != 2);
                    } else {
                        System.out.println(LETRAS_ROJAS + "El punto de orden del día ya existe" + LETRAS_DEFAULT);
                    }
                }
            } else {
                System.out.println(LETRAS_ROJAS + "La reunión ya existe" + LETRAS_DEFAULT);
            }

        } else {

            System.out.println(LETRAS_ROJAS + "La Comisión no existe." + LETRAS_DEFAULT);
        }

    }

    private void mostrarConsultarReunion() {

        String nombre, titulo;
        int anyo;

        System.out.println("Introduzca nombre de la Comisión: ");
        nombre = IO.readLine();
        if (controlador.introducirComision(nombre) != null) {
            System.out.println(controlador.introducirComision(nombre).toString());
            System.out.print("Año de la reunión: ");
            anyo = (int) IO.readNumber();
            controlador.listarReunionAnyo(anyo);
            System.out.print("Indica un título de la lista: ");
            titulo = IO.readLine();
            if (controlador.introducirReunion(titulo) != null) {
                System.out.println(controlador.introducirReunion(titulo));
            } else {
                System.out.println(LETRAS_ROJAS + "No existe la reunión introducida." + LETRAS_DEFAULT);
            }
        } else {
            System.out.println(LETRAS_ROJAS + "La Comisión no existe." + LETRAS_DEFAULT);
        }

    }

    private void mostrarRealizarConvocatoria() {

        String nombre, titulo;
        Calendar fecha_convocatoria = Calendar.getInstance();
        System.out.print("Nombre de la Comisión: ");
        nombre = IO.readLine();
        if (controlador.introducirComision(nombre) != null) {
            System.out.println(controlador.introducirComision(nombre).toString());
            System.out.print("Introduzca el titulo de la Reunión: ");
            titulo = IO.readLine();
            if (controlador.introducirReunion(titulo) != null) {
                if (controlador.introducirReunion(titulo).getFecha_convocatoria() == null) {
                    if (controlador.introducirReunion(titulo).getFecha().after(fecha_convocatoria)) {
                        controlador.introducirFechaConvocatoria(fecha_convocatoria);
                        System.out.println("Convocatoria Realizada");
                        System.out.println(controlador.introducirReunion(titulo));
                    } else {
                        System.out.println(LETRAS_ROJAS + "La fecha de la convocatoria no puede ser anterior a la fecha de reunion." + LETRAS_DEFAULT);
                    }
                } else {
                    System.out.println(LETRAS_ROJAS + "Esta reunión ya tiene una convocatoria asignada" + LETRAS_DEFAULT);
                }
            } else {
                System.out.println(LETRAS_ROJAS + "No existe la reunión introducida." + LETRAS_DEFAULT);
            }
        } else {
            System.out.println(LETRAS_ROJAS + "La Comisión no existe." + LETRAS_DEFAULT);
        }

    }

    private void mostrarCompletarReunion() {

        // Revisar el caso de uso de completar reunion.
        String nombre, titulo, url, resolucion;
        Calendar horaFin = Calendar.getInstance();
        System.out.println("Introduzca nombre de la Comisión: ");
        nombre = IO.readLine();
        if (controlador.introducirComision(nombre) != null) {

            System.out.println(controlador.introducirComision(nombre).toString());

            System.out.print("Introduzca el titulo de la Reunión: ");
            titulo = IO.readLine();

            if (controlador.introducirReunion(titulo) != null) {

                System.out.println(controlador.introducirReunion(titulo));

                if (controlador.introducirReunion(titulo).getHoraInicio().before(horaFin)) {
                    controlador.modificarHoraFin(horaFin);

                    int parada = 2;
                    while (parada == 2) {
                        System.out.println("Introduzca los datos del punto del orden del dia");
                        System.out.print("URL: ");
                        url = IO.readLine();
                        if (controlador.introduceUrl(url) != null) {
                            System.out.print("Resolución: ");
                            resolucion = IO.readLine();
                            controlador.modificaResolucion(resolucion);
                            do {
                                System.out.println("\t1. Terminar Reunión");
                                System.out.println("\t2. Añadir Resolución");
                                parada = (int) IO.readNumber();
                                System.out.println("");
                                switch (parada) {
                                    case 1:
                                        System.out.println("Reunión terminada satisfactoriamente.");
                                        break;
                                    case 2:
                                        System.out.println("Añadir Resolución.");
                                        break;
                                    default:
                                        System.out.println(LETRAS_ROJAS + "Opción inválida." + LETRAS_DEFAULT);
                                        break;
                                }
                            } while (parada != 1 && parada != 2);

                        } else {
                            System.out.println(LETRAS_ROJAS + "No existe un punto de orden del día con esa URL" + LETRAS_DEFAULT);
                        }
                    }
                } else {
                    System.out.println(LETRAS_ROJAS + "La reunión todavía no ha comenzado, por lo que no se puede dar por acabada" + LETRAS_DEFAULT);
                }
            } else {
                System.out.println(LETRAS_ROJAS + "No existe la reunión introducida." + LETRAS_DEFAULT);
            }
        } else {
            System.out.println(LETRAS_ROJAS + "La Comisión no existe." + LETRAS_DEFAULT);
        }
    }

   

    public void activa_campo_prueba() {

        miembro_prueba("a", "e", "77873658M", "e", 789, "a@a.com");

    }

    // Estas pruebas son en un estado ideal donde no ha habido fallos
    public void miembro_prueba(String nombre, String apellidos, String dni, String direccion, int telefono, String email) {

        controlador.addMiembro(nombre, apellidos, dni, direccion, telefono, email);

    }

    public void comision_prueba(String nombre, String descripcion) {
        controlador.addComision(nombre, descripcion);

    }

}
