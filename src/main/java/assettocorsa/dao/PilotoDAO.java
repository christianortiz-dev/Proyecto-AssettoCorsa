package assettocorsa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import assettocorsa.classes.Piloto;

public class PilotoDAO {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("assettocorsa");

    // Crear un nuevo circuito
    public void crear(Piloto piloto) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(piloto);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Leer un circuito por ID
    public Piloto leer(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Piloto.class, id);
        } finally {
            em.close();
        }
    }

    // Listar todos los circuitos
    public List<Piloto> listar() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("from Piloto", Piloto.class).getResultList();
        } finally {
            em.close();
        }
    }

    // Actualizar un circuito existente
    public void actualizar(Piloto piloto) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(piloto);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Eliminar un circuito por ID
    public void eliminar(int id) {
        EntityManager em = emf.createEntityManager();
        try {
        	Piloto piloto = em.find(Piloto.class, id);
            if (piloto != null) {
                em.getTransaction().begin();
                em.remove(piloto);
                em.getTransaction().commit();
            }
        } finally {
            em.close();
        }
    }
}
