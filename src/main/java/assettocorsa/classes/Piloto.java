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
	private int Licencia;
	private String Nombre;
	private String Apellido;
	private static final long serialVersionUID = 1L;

	public Piloto() {
		super();
	}   
	public int getLicencia() {
		return this.Licencia;
	}

	public void setLicencia(int licencia) {
		this.Licencia = licencia;
	}   
	public String getNombre() {
		return this.Nombre;
	}

	public void setNombre(String Nombre) {
		this.Nombre = Nombre;
	}   
	public String getApellido() {
		return this.Apellido;
	}

	public void setApellido(String Apellido) {
		this.Apellido = Apellido;
	}
   
}
