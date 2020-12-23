package vista;

import java.util.Calendar;
import java.util.Scanner;
import ordenador.Ordenador;
import poo.io.IO;

/**
 *
 * @author Alberto García Gonzalez,Juan Moreno Galbarro,Alejandro Román
 * Caballero
 */
public class Pantalla {

    private final Ordenador controlador;

    public Pantalla(Ordenador controlador) {
        this.controlador = controlador;
    }

    public void mostrarOpciones() {
        Scanner s = new Scanner(System.in);
        int opc;
        do {
            System.out.println("\n\n\n\tMenú Principal:\n");
            System.out.println("\t1. Gestionar Miembro");
            System.out.println("\t2. Gestionar Comisión");
            System.out.println("\t3. Gestionar Reunión");
            System.out.println("\t----------------------");
            System.out.println("\t\t0. Salir");
            System.out.println("\n\n--> Introduzca una opción: ");
            opc = s.nextInt();
            while (opc < 0 || opc > 3) {
                System.out.println("\n\n--> Introduzca una opción válida: ");
                opc = s.nextInt();
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
        Scanner s = new Scanner(System.in);
        int opc;

        do {
            System.out.println("\n\n\n\tMenú Gestionar Miembro:\n");
            System.out.println("\t1. Añadir Miembro");
            System.out.println("\t2. Eliminar Miembro");
            System.out.println("\t3. Modificar Miembro");
            System.out.println("\t4. Consultar Miembro");
            System.out.println("\t---------------------");
            System.out.println("\t\t0. Volver");
            System.out.print("\n\n--> Introduzca una opción: ");
            opc = s.nextInt();
            while (opc < 0 || opc > 4) {
                System.out.print("--> Introduzca una opción valida: ");
                opc = s.nextInt();
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
                System.out.println("El miembro con DNI " + dni + "ya existe");

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
            while (opc != 1) {
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

                        System.out.println("Opción inválida.");
                        break;

                }
            }
        } else {

            System.out.println("No podemos eliminar el cliente con el dni " + dni + ". Actualmente no existe.");
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
            System.out.println("El cliente seleccionado no existe.");
        }
    }

    private void mostrarConsultaMiembro() {

        String dni;
        System.out.println("Introduzca dni del Miembro: ");
        dni = IO.readLine();
        if (controlador.introducirDni(dni) != null) {

            System.out.print(controlador.introducirDni(dni).toString());

        } else {

            System.out.println("El Miembro no existe.");
        }
    }

    private void pantallaMenuComision() {
        Scanner s = new Scanner(System.in);
        int opc;

        do {
            System.out.println("\n\n\n\tMenú Gestionar Comisión:\n");
            System.out.println("\t1. Añadir Comisión");
            System.out.println("\t2. Incluir Miembro en Comisión");
            System.out.println("\t3. Eliminar Miembro de Comisión");
            System.out.println("\t4. Consultar Comisión");
            System.out.println("\t5. Listar Comisiones");
            System.out.println("\t---------------------");
            System.out.println("\t\t0. Volver");
            System.out.print("\n\n--> Introduzca una opción: ");
            opc = s.nextInt();
            while (opc < 0 || opc > 5) {
                System.out.print("--> Introduzca una opción valida: ");
                opc = s.nextInt();
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
                mostrarConsultaComision();
                break;
            case 5:
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
            System.out.println("La comisión ya existe");
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
                System.out.println("El Miembro no existe.");
            }

        } else {
            System.out.println("La comisión no existe.");
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

                System.out.println("El Miembro no existe.");
            }

        } else {

            System.out.println("La Comision no existe.");
        }

    }

    private void mostrarConsultaComision() {

        String nombre;
        System.out.println("Introduzca nombre de la Comision: ");
        nombre = IO.readLine();
        if (controlador.introducirComision(nombre) != null) {
            System.out.println(controlador.introducirComision(nombre).toString());
        } else {
            System.out.println("La Comision no existe.");
        }
    }

    private void mostrarListarComisiones() {

        System.out.println("Lista de Comisiones");
        controlador.listarComisiones();

    }

    private void pantallaMenuReunion() {
        Scanner s = new Scanner(System.in);
        int opc;

        do {
            System.out.println("\n\n\n\tMenú Gestionar Reunión:\n");
            System.out.println("\t1. Crear Reunión");
            System.out.println("\t2. Consultar Reunión");
            System.out.println("\t3. Realizar Convocatoria");
            System.out.println("\t4. Completar Reunión");
            System.out.println("\t---------------------");
            System.out.println("\t\t0. Volver");
            System.out.print("\n\n--> Introduzca una opción: ");
            opc = s.nextInt();
            while (opc < 0 || opc > 4) {
                System.out.print("--> Introduzca una opción valida: ");
                opc = s.nextInt();
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

        int tiempo;
        int anyo, mes, dia;
        Calendar fecha = Calendar.getInstance();
        Calendar horaInicio;
        Calendar horaFin;
        String titulo, lugar, nombre, url, descripcion;

        System.out.println("Introduzca nombre de la Comisión: ");
        nombre = IO.readLine();
        if (controlador.introducirComision(nombre) != null) {

            //METIENDO DATOS DE LA REUNION
            System.out.println(controlador.introducirComision(nombre).toString());
            System.out.print("Título: ");
            titulo = IO.readLine();
            if (controlador.introducirReunion(titulo) == null) {
                System.out.print("Lugar: ");
                lugar = IO.readLine();
                System.out.println("Introduzca la fecha de la reunión:");
                System.out.print("Año: ");
                anyo = (int) IO.readNumber();
                System.out.print("Mes: ");
                mes = (int) IO.readNumber();
                System.out.print("Día: ");
                dia = (int) IO.readNumber();
                fecha.set(anyo, mes, dia);
                horaInicio = fecha;
                horaFin = fecha;
                System.out.println("Introduzca la hora de comienzo de la reunión:");
                System.out.print("Hora: ");
                tiempo = (int) IO.readNumber();
                horaInicio.set(Calendar.HOUR, tiempo);
                System.out.print("Minuto: ");
                tiempo = (int) IO.readNumber();
                horaInicio.set(Calendar.MINUTE, tiempo);
                horaInicio.set(Calendar.SECOND, 0);
                System.out.println("Introduzca la hora a la que debe terminar la reunión:");
                System.out.print("Hora: ");
                tiempo = (int) IO.readNumber();
                horaFin.set(Calendar.HOUR, tiempo);
                System.out.print("Minuto: ");
                tiempo = (int) IO.readNumber();
                horaFin.set(Calendar.MINUTE, tiempo);
                horaFin.set(Calendar.SECOND, 0);
                controlador.addReunion(titulo, fecha, horaInicio, horaFin, lugar);
                int parada = 2;

                //METIENDO PUNTOS DEL ORDEN DEL DIA
                while (parada == 2) {
                    System.out.println("Introduzca los datos del punto del orden del dia");
                    System.out.print("URL: ");
                    url = IO.readLine();
                    if (controlador.introduceUrl(url) == null) {
                        System.out.print("Descripción: ");
                        descripcion = IO.readLine();
                        controlador.addPuntoDia(url, titulo, descripcion);
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
                                    System.out.println("Opción inválida.");
                                    break;

                            }
                        } while (parada != 1 && parada != 2);
                    } else {
                        System.out.println("El punto de orden del día ya existe");
                    }
                }
            } else {
                System.out.println("La reunión ya existe");
            }

        } else {

            System.out.println("La Comisión no existe.");
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
                System.out.println("No existe la reunión introducida.");
            }
        } else {
            System.out.println("La Comisión no existe.");
        }

    }

    private void mostrarRealizarConvocatoria() {

        String nombre, titulo;
        Calendar fecha_convocatoria = Calendar.getInstance();
        System.out.print("Nombre de la Comision: ");
        nombre = IO.readLine();
        if (controlador.introducirComision(nombre) != null) {
            System.out.println(controlador.introducirComision(nombre).toString());
            System.out.print("Título: ");
            titulo = IO.readLine();
            if (controlador.introducirReunion(titulo) != null) {
                if (controlador.introducirReunion(titulo).getFecha_convocatoria() == null) {
                    controlador.introducirFechaConvocatoria(fecha_convocatoria);
                    System.out.println("Convocatoria Realizada");
                    System.out.println(controlador.introducirReunion(titulo));
                } else {
                    System.out.println("Esta reunión ya tiene una convocatoria asignada");
                }
            } else {
                System.out.println("No existe la reunión introducida.");
            }
        } else {
            System.out.println("La Comisión no existe.");
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

            System.out.print("Título: ");
            titulo = IO.readLine();

            if (controlador.introducirReunion(titulo) != null) {

                System.out.println(controlador.introducirReunion(titulo));

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
                                    System.out.println("Opción inválida.");
                                    break;

                            }
                        } while (parada != 1 && parada != 2);

                    } else {
                        System.out.println("No existe un punto de orden del día con esa URL");
                    }
                }
            } else {
                System.out.println("No existe la reunión introducida.");
            }
        } else {
            System.out.println("La Comisión no existe.");
        }
    }
}
