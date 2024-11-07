package assettocorsa.classes;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Circuito
 *
 */
@Entity

public class Circuito implements Serializable {
	
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Id	
	private int id;
	private String Nombre;
	private String Longitud;
	private String Localizacion;
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
		return this.Nombre;
	}

	public void setNombre(String Nombre) {
		this.Nombre = Nombre;
	}   
	public String getLongitud() {
		return this.Longitud;
	}

	public void setLongitud(String Longitud) {
		this.Longitud = Longitud;
	}   
	public String getLocalizacion() {
		return this.Localizacion;
	}

	public void setLocalizacion(String Localizacion) {
		this.Localizacion = Localizacion;
	}
   
}
