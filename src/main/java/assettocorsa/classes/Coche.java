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
	private String Marca;
	private String Modelo;
	private String Potencia;
	private String Tipo;
	private static final long serialVersionUID = 1L;

	public Coche(String marca, String modelo, String potencia, String tipo) {
		super();
		Marca = marca;
		Modelo = modelo;
		Potencia = potencia;
		Tipo = tipo;
	}
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
		return this.Marca;
	}

	public void setMarca(String marca) {
		this.Marca = marca;
	}   
	public String getModelo() {
		return this.Modelo;
	}

	public void setModelo(String modelo) {
		this.Modelo = modelo;
	}   
	public String getPotencia() {
		return this.Potencia;
	}

	public void setPotencia(String potencia) {
		this.Potencia = potencia;
	}   
	public String getTipo() {
		return this.Tipo;
	}

	public void setTipo(String Tipo) {
		this.Tipo = Tipo;
	}
   
}
