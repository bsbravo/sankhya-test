package br.com.bravo.crm.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.bravo.crm.model.Customer;
import br.com.bravo.crm.model.Contact;
import br.com.bravo.crm.service.ContactService;
import br.com.bravo.crm.util.JSFUtil;

/**
 * Classe controller das telas para listagem de contatos
 * e inclusão/edição de um contato.
 * 
 * @author Bruno Soares Bravo
 *
 */
@Named
@ViewScoped
public class ContactController implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private ContactService contactService;
	
    private Customer customer;
    private Contact contact;
    private List<Contact> contacts;
    private String contactCode;

	/**
	 * Realiza o cadastro de novo contato ou atualiza o contato conforme os
	 * dados preenchidos no formulário.
	 * 
	 */
    public String salvar() {
        
    	try {
        
    		boolean novoContato = contact.getCode() == null;
    		
        	contactService.save(contact);
        	
        	if(novoContato) {
        		JSFUtil.showMessage("successfully saved!");
        	} else {
        		JSFUtil.showMessage("successfully updated!");
        	}

        	return "list?faces-redirect=true&includeViewParams=true";
        
    	} catch (Exception e) {
            JSFUtil.showErrorMessage(e);
        }
        
        return "save?faces-redirect=true&includeViewParams=true";
        
    }
    
	/**
	 * Exclui do banco de dados o contato selecionado na DataTable.
	 * 
	 * @param contato
	 */
	public String delete(Contact contato) {

		try {
			
			contactService.delete(contato);
			loadContatos();
			
			JSFUtil.showMessage("Contato removido com sucesso!");
			
		} catch (Exception e) {
			JSFUtil.showErrorMessage(e);
		}

		return "list?faces-redirect=true&includeViewParams=true";
	}

	/**
	 * Carrega para exibição na DataTable os contatos relacionados com o Cliente
	 * selecionado.
	 */
	public void loadContatos() {

		if(customer != null) {
			contacts = contactService.searchByCustomerCode(customer.getCode());
		
		} else {
			JSFUtil.redirectPage("/cliente/list");
		}
	}

	//http://java.dzone.com/articles/bookmarkability-jsf-2?page=0,1
	//https://blogs.oracle.com/enterprisetechtips/entry/post_redirect_get_and_jsf
	/**
	 * Converte os parametros da requisicao 
	 * (ex:save.jsf?codigoCliente=1&codigoContato=1) em um objeto to tipo "Contato"
	 */
	public void loadContact() {
		
		//
		//  Sistema redireciona navegação caso usuário entre uma URL com codigoCliente ou codigoUsuario inválidos.
		//
		if(customer == null) {
			JSFUtil.redirectPage("/cliente/list");
			return;
		}
		
		if (contactCode != null && !contactCode.matches("[0-9]+")) {
			JSFUtil.redirectPage("/cliente/list");
			return;
		}

		// Carrega novo contato para Inserção
		if (contactCode == null) {
			contact = new Contact();
			contact.setCustomer(customer);
			return;
		}


		// Busca contato para Edição
		contact = contactService.search(Long.valueOf(contactCode), customer.getCode());

		if (contact == null) {
			JSFUtil.redirectPage("/cliente/list");
		}

	}
	
	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContatos(List<Contact> contatos) {
		this.contacts = contatos;
	}

	public Customer getCliente() {
		return customer;
	}

	public void setCliente(Customer cliente) {
		this.customer = cliente;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContato(Contact contact) {
		this.contact = contact;
	}


	public String getContactCode() {
		return contactCode;
	}

	public void setContactCode(String contactCode) {
		this.contactCode = contactCode;

	}
    
}
