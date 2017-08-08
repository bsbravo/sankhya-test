package br.com.bravo.crm.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.bravo.crm.model.Customer;
import br.com.bravo.crm.model.Contact;
import br.com.bravo.crm.model.ContactPK;

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
public class ContactDAO {

	@Inject
    private EntityManager em;
    
	/**
	 * Insere/Atualiza um contato no banco de dados.
	 * 
	 * Caso seja uma operação de inserção de novo contato
	 * gera um novo código de contato sequencial por Cliente.
	 * 
	 * 
	 * @param contact
	 * @throws Exception
	 */
	public void save(Contact contact) throws Exception {
        
        if(isNew(contact)) {

        	Long customerCode = contact.getCustomer().getCode();
        	Long newContactCode = buildContactCode(customerCode);
        	contact.setCode(newContactCode);
        	
        }
        
        Customer customer = em.getReference(Customer.class, contact.getCustomer().getCode());
        contact.setCustomer(customer);
        
        em.merge(contact);
    }

	private boolean isNew(Contact constact) {
		return constact.getCode() == null;
	}
    
	private Long buildContactCode(Long customerCode) {

		Long max = (Long) em
				.createQuery(
						"select max(c.code) from Contact c where c.customer.code = :customerCode")
				.setParameter("customerCode", customerCode).getSingleResult();

		return max == null ? 1 : max + 1;

	}

    public void delete(Contact contact) throws Exception {
    	
    	ContactPK contatoPK = new ContactPK(contact.getCode(), contact.getCustomer().getCode());
    	Contact contatoParaRemover = em.getReference(Contact.class, contatoPK);

    	em.remove(contatoParaRemover);
    }
    
	public List<Contact> searchByCustomerCode(Long customerCode) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Contact> criteria = cb.createQuery(Contact.class);
		Root<Contact> contact = criteria.from(Contact.class);

		criteria.select(contact)
				.where(cb.equal(contact.get("customer"), customerCode))
				.orderBy(cb.asc(contact.get("name")));
		
		return em.createQuery(criteria).getResultList();

	}
    
    public Contact search(Long contactCode, Long customerCode) {
    	ContactPK contactPK = new ContactPK(contactCode, customerCode);
    	return em.find(Contact.class, contactPK);
    }

}
