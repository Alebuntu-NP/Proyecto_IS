package ordenador;

import java.util.Objects;

/**
 *
 * @author Alberto García Gonzalez,Juan Moreno Galbarro,Alejandro Román Caballero
 */
public class Miembro implements IMiembro {

    private String nombre;
    private String apellidos;
    private String dni;
    private String direccion;
    private int telefono;
    private String email;

    public Miembro(String nombre, String apellidos, String dni, String direccion, int telefono, String email) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
    }

    @Override
    public void modificaMiembro(String nombre, String apellidos, String direccion, int telefono, String email) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;

    }

    public String getDni() {
        return dni;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.dni);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Miembro other = (Miembro) obj;
        return Objects.equals(this.dni, other.dni);
    }

    @Override
    public String toString() {
        return "\nNombre: " + nombre + " Apellidos: " + apellidos + " DNI: " + dni + " Direccion: " + direccion + " Telefono: " + telefono + " Email: " + email+ "\n";
    }

}
