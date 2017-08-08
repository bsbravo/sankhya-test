package br.com.bravo.crm.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.bravo.crm.model.Customer;
import br.com.bravo.crm.service.CustomerService;
import br.com.bravo.crm.util.Constants;
import br.com.bravo.crm.util.JSFUtil;

/**
 * Controller das telas para listagem de clientes e inclusão/edição de um
 * cliente.
 * 
 * @author Bruno Soares Bravo
 *
 */
@Named
@ViewScoped
public class CustomerController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private CustomerService customerService;

	private Customer customer;
	private List<Customer> customers;

	/**
	 * Realiza pesquisa dos clientes cadastrados no sistema para serem exibidos na
	 * DataTable. Se for preenchido o campo "nome" na tela este campo é utilizado
	 * como filtro.
	 * 
	 */
	public void search() {
		
		try {

			if (customer.getName().isEmpty()) {
				customers = customerService.search();
			} else {
				customers = customerService.searchByName(customer.getName());
			}

		} catch (Exception e) {
			JSFUtil.showErrorMessage(e);
		}

	}
	
	/**
	 * Realiza o cadastro de novo cliente ou atualiza o cliente conforme os
	 * dados preenchidos no formulário.
	 * 
	 */
	public String save() {
		
		try {
			
			boolean newCustomer = customer.getCode() == null;

			customerService.save(customer);
			
        	if(newCustomer) {
        		JSFUtil.showMessage("Cliente cadastrado com sucesso!");
        	} else {
        		JSFUtil.showMessage("Cliente atualizado com sucesso!");
        	}
			
			return "list";
			
		} catch (Exception e) {
			JSFUtil.showErrorMessage(e);
		}

		return null;

	}

	/**
	 * Exclui do banco de dados o cliente selecionado na DataTable.
	 * 
	 * @param cliente
	 */
	public String excluir(Customer cliente) {

		try {
			
			customerService.delete(cliente);
			search();
			
			JSFUtil.showMessage("Cliente removido com sucesso!");
			
		} catch (Exception e) {

			JSFUtil.showErrorMessage(e);

		}

		return null;
	}

	@PostConstruct
	private void initNewMember() {
		
		customers = customerService.search();

		customer = new Customer();
		customer.setTypeCustomer(Constants.CUSTOMER_PERSON);

	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setClientes(List<Customer> customers) {
		this.customers = customers;
	}

}
