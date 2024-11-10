package assettocorsa.classes;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

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
	private String nombre;
	private Double longitud;
	private String localizacion;
	private static final long serialVersionUID = 1L;

    @OneToMany(mappedBy = "circuito", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vuelta> vueltas;
	
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
	public Double getLongitud() {
		return this.longitud;
	}

	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}   
	public String getLocalizacion() {
		return this.localizacion;
	}

	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}

}
