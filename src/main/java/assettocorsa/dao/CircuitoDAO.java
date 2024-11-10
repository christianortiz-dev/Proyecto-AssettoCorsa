package assettocorsa.dao;

import assettocorsa.classes.Circuito;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class CircuitoDAO {
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("assettocorsa");

	// Crear un nuevo circuito
	public void crear(Circuito circuito) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(circuito);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	// Leer un circuito por ID
	public Circuito leer(int id) {
		EntityManager em = emf.createEntityManager();
		try {
			return em.find(Circuito.class, id);
		} finally {
			em.close();
		}
	}

	// Listar todos los circuitos
	public List<Circuito> listar() {
		EntityManager em = emf.createEntityManager();
		try {
			return em.createQuery("from Circuito", Circuito.class).getResultList();
		} finally {
			em.close();
		}
	}

	// Actualizar un circuito existente
	public void actualizar(Circuito circuito) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(circuito);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	// Eliminar un circuito por ID
	public void eliminar(int id) {
		EntityManager em = emf.createEntityManager();
		try {
			Circuito circuito = em.find(Circuito.class, id);
			if (circuito != null) {
				em.getTransaction().begin();
				em.remove(circuito);
				em.getTransaction().commit();
			}
		} finally {
			em.close();
		}
	}
}
