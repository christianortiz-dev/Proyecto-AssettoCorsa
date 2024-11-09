package assettocorsa.classes;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Vuelta
 *
 */
@Entity
public class Vuelta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String tiempo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "piloto_id")
    private Piloto piloto;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coche_id")
    private Coche coche;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "circuito_id")
    private Circuito circuito;

    private static final long serialVersionUID = 1L;

    public Vuelta() {
        super();
    }

    // Getters y setters
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public Piloto getPiloto() {
        return piloto;
    }

    public void setPiloto(Piloto piloto) {
        this.piloto = piloto;
    }

    public Coche getCoche() {
        return coche;
    }

    public void setCoche(Coche coche) {
        this.coche = coche;
    }

    public Circuito getCircuito() {
        return circuito;
    }

    public void setCircuito(Circuito circuito) {
        this.circuito = circuito;
    }
}

