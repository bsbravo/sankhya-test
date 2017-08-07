package br.com.sankhya.crm.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import br.com.sankhya.crm.model.Cliente;

/**
 * Realiza as operações no banco de dados relacionadas ao Cliente.
 * 
 * Utiliza JPA 2.
 * 
 * @author Bruno Soares Bravo
 */
@Stateless
public class ClienteDAO {

	@Inject
	private EntityManager em;

	public void salvar(Cliente cliente)  {

		em.merge(cliente);

	}

	public void excluir(Cliente cliente) throws Exception {

		Cliente clienteRemover = em.getReference(Cliente.class,	cliente.getCodigo());
		em.remove(clienteRemover);
	
	}

	public List<Cliente> pesquisarPorNome(String nome) {
	
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Cliente> criteria = cb.createQuery(Cliente.class)	.distinct(true);
		Root<Cliente> cliente = criteria.from(Cliente.class);

		cliente.fetch("contatos", JoinType.LEFT);
		criteria.select(cliente).where(cb.like(cliente.<String> get("nome"), "%" + nome + "%"));

		return em.createQuery(criteria).getResultList();
		
	}

	public List<Cliente> pesquisar() {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Cliente> criteria = cb.createQuery(Cliente.class).distinct(true);
		Root<Cliente> cliente = criteria.from(Cliente.class);

		cliente.fetch("contatos", JoinType.LEFT);
		criteria.select(cliente).orderBy(cb.asc(cliente.get("nome")));

		return em.createQuery(criteria).getResultList();
		
	}
	
	public Cliente pesquisar(Long id) {
		
		return em.find(Cliente.class, id);
	
	}

}
