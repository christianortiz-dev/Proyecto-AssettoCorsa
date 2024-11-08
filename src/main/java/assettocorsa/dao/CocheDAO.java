package assettocorsa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import assettocorsa.classes.Coche;

public class CocheDAO {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("assettocorsa");

    // Crear un nuevo circuito
    public void crear(Coche coche) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(coche);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Leer un circuito por ID
    public Coche leer(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Coche.class, id);
        } finally {
            em.close();
        }
    }

    // Listar todos los circuitos
    public List<Coche> listar() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("from Circuito", Coche.class).getResultList();
        } finally {
            em.close();
        }
    }

    // Actualizar un circuito existente
    public void actualizar(Coche coche) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(coche);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Eliminar un circuito por ID
    public void eliminar(int id) {
        EntityManager em = emf.createEntityManager();
        try {
        	Coche coche = em.find(Coche.class, id);
            if (coche != null) {
                em.getTransaction().begin();
                em.remove(coche);
                em.getTransaction().commit();
            }
        } finally {
            em.close();
        }
    }
}
