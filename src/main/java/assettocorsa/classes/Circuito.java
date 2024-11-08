package assettocorsa.classes;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Circuito implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String nombre;
    private String localizacion;
    private String longitud;

    private static final long serialVersionUID = 1L;

    public Circuito() {
        super();
    }   
    
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLongitud() {
        return this.longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getLocalizacion() {
        return this.localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }
}
