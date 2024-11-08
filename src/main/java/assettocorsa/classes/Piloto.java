package assettocorsa.classes;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Piloto
 *
 */
@Entity

public class Piloto implements Serializable {
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Id
	
	private int licencia;
	private String nombre;
	private String apellido;
	private static final long serialVersionUID = 1L;

	public Piloto() {
		super();
	}   
	public int getLicencia() {
		return this.licencia;
	}

	public void setLicencia(int licencia) {
		this.licencia = licencia;
	}   
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}   
	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
   
}
