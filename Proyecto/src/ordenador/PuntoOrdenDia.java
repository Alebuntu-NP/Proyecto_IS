package ordenador;

import java.util.Objects;

/**
 *
 * @author Alberto García Gonzalez,Juan Moreno Galbarro,Alejandro Román Caballero
 */
public class PuntoOrdenDia implements IPuntoOrdenDia {

    private String url;
    private String titulo;
    private String descripcion;
    private String resolucion = null;

    public PuntoOrdenDia(String url, String titulo, String descripcion) {
        this.url = url;
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    @Override
    public void cambioDatosResolucion(String resolucion) {

        this.resolucion = resolucion;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.url);
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
        final PuntoOrdenDia other = (PuntoOrdenDia) obj;
        return Objects.equals(this.url, other.url);
    }

    @Override
    public String toString() {
        return "PuntoOrdenDia{" + "url=" + url + ", titulo=" + titulo + ", descripcion=" + descripcion + ", resolucion=" + resolucion + '}';
    }

}
