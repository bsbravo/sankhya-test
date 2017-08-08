package br.com.bravo.crm.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import br.com.bravo.crm.model.Customer;

/**
 * Realiza as operações no banco de dados relacionadas ao Cliente.
 * 
 * Utiliza JPA 2.
 * 
 * @author Bruno Soares Bravo
 */
@Stateless
public class CustomerDAO {

	@Inject
	private EntityManager em;

	public void save(Customer customer)  {

		em.merge(customer);

	}

	public void delete(Customer customer) throws Exception {

		Customer customerToDelete = em.getReference(Customer.class, customer.getCode());
		em.remove(customerToDelete);
	
	}

	public List<Customer> searchByName(String name) {
	
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Customer> criteria = cb.createQuery(Customer.class)	.distinct(true);
		Root<Customer> cliente = criteria.from(Customer.class);

		cliente.fetch("contatos", JoinType.LEFT);
		criteria.select(cliente).where(cb.like(cliente.<String> get("name"), "%" + name + "%"));

		return em.createQuery(criteria).getResultList();
		
	}

	public List<Customer> search() {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Customer> criteria = cb.createQuery(Customer.class).distinct(true);
		Root<Customer> cliente = criteria.from(Customer.class);

		cliente.fetch("contacts", JoinType.LEFT);
		criteria.select(cliente).orderBy(cb.asc(cliente.get("name")));

		return em.createQuery(criteria).getResultList();
		
	}
	
	public Customer search(Long id) {
		
		return em.find(Customer.class, id);
	
	}

}
