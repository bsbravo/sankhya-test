package br.com.sankhya.crm.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.sankhya.crm.model.Cliente;
import br.com.sankhya.crm.model.Contato;
import br.com.sankhya.crm.model.ContatoPK;

/**
 * Realiza as operações no banco de dados relacionadas aos Contatos de um
 * Cliente. 
 * 
 * Utiliza JPA 2.
 * 
 * @author Bruno Soares Bravo
 *
 */
@Stateless
public class ContatoDAO {

	@Inject
    private EntityManager em;
    
	/**
	 * Insere/Atualiza um contato no banco de dados.
	 * 
	 * Caso seja uma operação de inserção de novo contato
	 * gera um novo código de contato sequencial por Cliente.
	 * 
	 * 
	 * @param contato
	 * @throws Exception
	 */
	public void salvar(Contato contato) throws Exception {
        
        if(isNew(contato)) {

        	Long codigoCliente = contato.getCliente().getCodigo();
        	Long novoCodigoContato = gerarCodigoContato(codigoCliente);
        	contato.setCodigo(novoCodigoContato);
        	
        }
        
        Cliente cliente = em.getReference(Cliente.class, contato.getCliente().getCodigo());
        contato.setCliente(cliente);
        
        em.merge(contato);
    }

	private boolean isNew(Contato contato) {
		return contato.getCodigo() == null;
	}
    
	private Long gerarCodigoContato(Long codigoCliente) {

		Long max = (Long) em
				.createQuery(
						"select max(c.codigo) from Contato c where c.cliente.codigo = :codigoCliente")
				.setParameter("codigoCliente", codigoCliente).getSingleResult();

		return max == null ? 1 : max + 1;

	}

    public void excluir(Contato contato) throws Exception {
    	
    	ContatoPK contatoPK = new ContatoPK(contato.getCodigo(), contato.getCliente().getCodigo());
    	Contato contatoParaRemover = em.getReference(Contato.class, contatoPK);

    	em.remove(contatoParaRemover);
    }
    
	public List<Contato> pesquisarPorCodigoCliente(Long codigoCliente) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Contato> criteria = cb.createQuery(Contato.class);
		Root<Contato> contato = criteria.from(Contato.class);

		criteria.select(contato)
				.where(cb.equal(contato.get("cliente"), codigoCliente))
				.orderBy(cb.asc(contato.get("nome")));
		
		return em.createQuery(criteria).getResultList();

	}
    
    public Contato pesquisar(Long codigo, Long codigoCliente) {
    	ContatoPK contatoPK = new ContatoPK(codigo, codigoCliente);
    	return em.find(Contato.class, contatoPK);
    }

}
