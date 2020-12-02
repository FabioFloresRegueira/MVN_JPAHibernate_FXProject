package Dao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import Model.Cliente;

public class ClienteJpaDAO {

	private static ClienteJpaDAO instance;

	protected EntityManager em;

	public static ClienteJpaDAO getInstance() {

		if (instance == null) {

			instance = new ClienteJpaDAO();

		}

		return instance;

	}

	private ClienteJpaDAO() {
		em = getEntityManager();
	}

	private EntityManager getEntityManager() {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("crudHibernatePU");

		if (em == null) {

			em = emf.createEntityManager();

		}

		return em;
	}

	// ********************************************** //
	//  PERSISTENCIA - ( TABELA CLIENTE ) 
	// ********************************************** //

	public Cliente getById(final int id) {
		return em.find(Cliente.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> listarCliente() {
		return em.createQuery("FROM " + Cliente.class.getName()).getResultList();
	}

	// Salvar novo
	public void persist(Cliente cliente) {
		try {
			em.getTransaction().begin();
			em.persist(cliente);
			em.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			em.getTransaction().rollback();
		}
	}

	// salvar a edicao
	public void merge(Cliente cliente) {
		try {
			em.getTransaction().begin();
			em.merge(cliente);
			em.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			em.getTransaction().rollback();
		}
	}

	// Exclusão
	public void remover(Cliente cliente) {
		try {
			em.getTransaction().begin();
			cliente = em.find(Cliente.class, cliente.getId());
			em.remove(cliente);
			em.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			em.getTransaction().rollback();
		}
	}
	
	public void removerById(final int id) {
		try {
			Cliente cliente = getById(id);
			remover(cliente);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
