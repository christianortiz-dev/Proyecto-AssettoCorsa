package assettocorsa.classes;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Coche
 *
 */
@Entity

public class Coche implements Serializable {
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Id
	
	private int id;
	private String marca;
	private String modelo;
	private int potencia;
	private static final long serialVersionUID = 1L;

	public Coche() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getMarca() {
		return this.marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}   
	public String getModelo() {
		return this.modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}   
	public int getPotencia() {
		return this.potencia;
	}

	public void setPotencia(int potencia) {
		this.potencia = potencia;
	}
   
}
