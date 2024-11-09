package assettocorsa.dao;

import assettocorsa.classes.Vuelta;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class VueltaDAO {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("assettocorsa");

    // Crear una nueva vuelta
    public void crear(Vuelta vuelta) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(vuelta);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Leer una vuelta por ID
    public Vuelta leer(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Vuelta.class, id);
        } finally {
            em.close();
        }
    }

    // Listar todos las vueltas
    public List<Vuelta> listar() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("from Vuelta", Vuelta.class).getResultList();
        } finally {
            em.close();
        }
    }

    // Actualizar una vuelta existente
    public void actualizar(Vuelta vuelta) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(vuelta);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Eliminar una vuelta por ID
    public void eliminar(int id) {
        EntityManager em = emf.createEntityManager();
        try {
        	Vuelta vuelta = em.find(Vuelta.class, id);
            if (vuelta != null) {
                em.getTransaction().begin();
                em.remove(vuelta);
                em.getTransaction().commit();
            }
        } finally {
            em.close();
        }
    }
}
