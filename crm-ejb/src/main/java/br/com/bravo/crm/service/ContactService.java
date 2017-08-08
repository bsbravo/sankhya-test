package br.com.bravo.crm.service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.bravo.crm.dao.ContactDAO;
import br.com.bravo.crm.model.Contact;;

/**
 * Implementa a camada de serviços relacionada aos Contatos.
 * 
 * @author Bruno Soares Bravo
 *
 */
@Stateless
public class ContactService {

    @Inject
    private Logger log;

    @Inject
    private ContactDAO contactDAO;

    public void save(Contact contato) throws Exception {
        log.info("Saving...  " + contato.getName());
        contactDAO.save(contato);
    }
    
    public void delete(Contact contact) throws Exception {
    	log.info("Deleting contact " + contact.getCode() + ", cliente " + contact.getCustomer().getCode());
    	contactDAO.delete(contact);
    	
    }
    
	public List<Contact> searchByCustomerCode(Long codigoCliente) {
		return contactDAO.searchByCustomerCode(codigoCliente);
	}
	
	public Contact search(Long contactCode, Long customerCode) {
		return contactDAO.search(contactCode, customerCode);
	}
    
}
