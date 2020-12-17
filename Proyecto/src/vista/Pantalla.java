package vista;

import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;
import ordenador.Ordenador;
import poo.io.IO;

/**
 *
 * @author Alberto García Gonzalez,Juan Moreno Galvarro,Alejandro Román Caballero
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

        int fallo = 0;

        System.out.println("Introduzca los datos del Miembro");
        System.out.print("Dni: ");
        String dni = IO.readLine();
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

        fallo = controlador.addMiembro(nombre, apellidos, dni, direccion, telefono, email);
        System.out.println("\n\n");

        if (fallo == -1) {
            System.out.println("El miembro con DNI " + dni + "ya existe");

        } else {
            System.out.println("El miembro se ha registrado correctamente.");
        }

    }

    private void mostrarBajaMiembro() {
       
        String dni;
        System.out.print("Introduzca dni del Miembro: ");
        dni = IO.readLine();
        if (controlador.introducirDni(dni) != null) {

            System.out.println(controlador.introducirDni(dni).toString());
            int opc = 0;
            while (opc != 1) {
                System.out.println("\t1. Confimar eliminacion");
                System.out.println("\t2. No eliminar miembro");
                opc = (int) IO.readNumber();

                switch (opc) {
                    case 1:
                        controlador.confirmarEliminacionMiembro();
                        System.out.println("Miembro eliminado correctamente.");
                        break;
                    case 2:
                        System.out.println("No se ha eliminado el miembro.");
                        break;
                    default:

                        System.out.println("Opcion invalida.");
                        break;

                }
            }
        } else {

            System.out.println("No podemos eliminar el cliente con el dni " + dni + ". Actualmente no existe.");
        }
    }

    private void mostrarModificacionMiembro() {

        int fallo = -1;

        

        System.out.print("Introduzca dni del Miembro: ");
        String dni = IO.readLine();
        if (controlador.introducirDni(dni) != null) {

            while (fallo == -1) {
                try {

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

                    controlador.cambiarMiembro(nombre, apellidos, direccion, telefono, email);
                    fallo = 0;
                    System.out.println("El Miembro se ha modificado correctamente.");
                    System.out.println("\n\n");
                } catch (InputMismatchException ex) {
                    System.out.println("Debe ingresar los datos correctos.");
                }

            }

        } else {

            System.out.println("El cliente seleccionado no existe.");
        }
    }

    private void mostrarConsultaMiembro() {
     
        String dni;
        System.out.println("Introduzca dni del Miembro: ");
        dni = IO.readLine();
        if (controlador.introducirDni(dni) != null) {

            System.out.println(controlador.introducirDni(dni).toString());

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
        int fallo = -1;
        while (fallo == -1) {
            try {

                Scanner s = new Scanner(System.in);

                System.out.println("Introduzca los datos de la Comisión");
                System.out.print("Nombre: ");
                nombre = s.nextLine();
                System.out.print("Descripcion: ");
                descripcion = s.nextLine();

                fallo = controlador.addComision(nombre, descripcion);
                System.out.println("\n\n");
            } catch (InputMismatchException ex) {
                System.out.println("Debe ingresar los datos correctos.");
            }

            if (fallo == -2) {
                System.out.println("La Comisión ya existe");

            } else {

                System.out.println("La Comisión se ha registrado correctamente.");

            }

        }
    }

    private void mostrarIncluirMiembroEnComision() {

        Scanner s = new Scanner(System.in);
        String dni;
        String nombre;
        String puesto;
        System.out.println("Introduzca nombre de la Comision: ");
        nombre = s.nextLine();
        if (controlador.introducirComision(nombre) != null) {

            System.out.println(controlador.introducirComision(nombre).toString());

            System.out.println("Introduzca dni del Miembro: ");

            dni = s.nextLine();

            if (controlador.introducirDni(dni) != null) {

                System.out.println(controlador.introducirDni(dni).toString());

                System.out.println("Introduzca puesto del Miembro: ");

                puesto = s.nextLine();

                controlador.introducirMiembro(dni, puesto);

                System.out.println("Miembro ha sido dado de alta en una comision.");

            } else {

                System.out.println("El Miembro no existe.");
            }

        } else {

            System.out.println("La Comision no existe.");
        }

    }

    private void mostrarEliminarMiembroDeComision() {

        Scanner s = new Scanner(System.in);
        String dni;
        String nombre;
        System.out.println("Introduzca nombre de la Comision: ");
        nombre = s.nextLine();
        if (controlador.introducirComision(nombre) != null) {

            System.out.println(controlador.introducirComision(nombre).toString());

            System.out.println("Introduzca dni del Miembro: ");

            dni = s.nextLine();

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

        Scanner s = new Scanner(System.in);
        String nombre;
        System.out.println("Introduzca nombre de la Comision: ");
        nombre = s.nextLine();
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
            System.out.println("\n\n\n\tMenú Gestionar Comisión:\n");
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
    // Hay completar lo que queda

    private void mostrarCrearReunion() {
        Scanner s = new Scanner(System.in);
        int tiempo = 0;
        Calendar fecha = Calendar.getInstance();
        Calendar horaInicio = Calendar.getInstance();
        Calendar horaFin = Calendar.getInstance();
        String titulo, lugar, nombre, url, descripcion;

        System.out.println("Introduzca nombre de la Comision: ");
        nombre = s.nextLine();
        if (controlador.introducirComision(nombre) != null) {

            System.out.println(controlador.introducirComision(nombre).toString());
            System.out.print("Titulo: ");
            titulo = s.nextLine();
            System.out.print("Lugar: ");
            lugar = s.nextLine();

            System.out.print("Tiempo: ");
            tiempo = s.nextInt();
            horaInicio.set(Calendar.HOUR, 0);
            horaInicio.set(Calendar.MINUTE, 0);
            horaInicio.set(Calendar.SECOND, 0);
            horaFin.set(Calendar.HOUR, 0);
            horaFin.set(Calendar.MINUTE, 0);
            horaFin.set(Calendar.SECOND, 0);
            fecha.set(0, 0, 0);
            controlador.addReunion(titulo, fecha, horaInicio, horaFin, lugar);
            System.out.print("Url: ");
            url = s.nextLine();
            System.out.print("Descripcion: ");
            descripcion = s.nextLine();
            controlador.addPuntoDia(url, titulo, descripcion);
            controlador.confirmarReunion();

        } else {

            System.out.println("La Comision no existe.");
        }

    }

    private void mostrarConsultarReunion() {

        Scanner s = new Scanner(System.in);
        String nombre, titulo;
        int anyo;

        System.out.println("Introduzca nombre de la Comision: ");
        nombre = s.nextLine();
        if (controlador.introducirComision(nombre) != null) {

            System.out.println(controlador.introducirComision(nombre).toString());

            System.out.print("Titulo: ");
            titulo = s.nextLine();
            System.out.print("Año de la reunion: ");
            anyo = s.nextInt();

            if (controlador.introducirReunionAnual(titulo, anyo) != null) {

                System.out.println(controlador.introducirReunionAnual(titulo, anyo));

            } else {

                System.out.println("No existe la reunion introducida.");

            }
        } else {

            System.out.println("La Comision no existe.");
        }

    }

    private void mostrarRealizarConvocatoria() {

        Scanner s = new Scanner(System.in);
        String nombre, titulo;
        Calendar fecha_convocatoria = Calendar.getInstance();
        System.out.print("Nombre de la Comision: ");
        nombre = s.nextLine();
        controlador.introducirComision(nombre);

        if (controlador.introducirComision(nombre) != null) {

            System.out.println(controlador.introducirComision(nombre).toString());

            System.out.print("Titulo: ");
            titulo = s.nextLine();

            if (controlador.introducirReunion(titulo) != null) {

                controlador.introducirFechaConvocatoria(fecha_convocatoria);
                System.out.println("Convocatoria Realizada");
                System.out.println(controlador.introducirReunion(titulo));
            } else {

                System.out.println("No existe la reunion introducida.");

            }
        } else {

            System.out.println("La Comision no existe.");
        }

    }

    private void mostrarCompletarReunion() {

        Scanner s = new Scanner(System.in);
        String nombre, titulo;

        System.out.println("Introduzca nombre de la Comision: ");
        nombre = s.nextLine();
        if (controlador.introducirComision(nombre) != null) {

            System.out.println(controlador.introducirComision(nombre).toString());

            System.out.print("Titulo: ");
            titulo = s.nextLine();

            if (controlador.introducirReunion(titulo) != null) {

                System.out.println(controlador.introducirReunion(titulo));
                /*
                controlador.modificaHoraFin(horaFin);
                controlador.introduceUrl(url);
                controlador.modificaResolucion(resolucion);
                 */

            } else {

                System.out.println("No existe la reunion introducida.");

            }

        } else {

            System.out.println("La Comision no existe.");
        }

    }

}
