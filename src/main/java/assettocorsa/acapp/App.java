package assettocorsa.acapp;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import assettocorsa.classes.Coche;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("assettocorsa");
    	EntityManager em = emf.createEntityManager();	
    	
    	Coche c = new Coche("Ford", "Focus", "120cv", "Turismo");
    	Coche c2 = new Coche("BMW", "M3 GT3", "494cv", "GT-3");
    	Coche c92 = new Coche("Mercedes-Benz", "190E EVO II", "470cv", "SuperTurismo");
    	Coche c4 = new Coche("Audi", "Quattro S1 E2", "550cv", "Rally");
    	
    	em.getTransaction().begin();
    	
    	em.persist(c);
    	em.persist(c2);
    	em.persist(c92);
    	em.persist(c4);
    	
    	em.getTransaction().commit();
    	
    	em.close();
    	emf.close();    	
    	
        System.out.println( "Hello World!" );
    }
}
