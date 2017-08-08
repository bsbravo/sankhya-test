package br.com.bravo.crm.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.bravo.crm.dao.CustomerDAO;
import br.com.bravo.crm.exception.BusinessException;
import br.com.bravo.crm.model.Customer;
import br.com.bravo.crm.model.CustomerSize;

/**
 * Implementa a lógica de negócio para as operações relacionadas aos Clientes.
 * 
 * @author Bruno Soares Bravo
 *
 */
@Stateless
public class CustomerService {

    @Inject
    private Logger log;

    @Inject
    private CustomerDAO customerDAO;
    

	/**
	 * Realiza operação de inserção/atualização de um Cliente.
	 * 
	 * O campo "limiteCredito" do Cliente é validado de acordo com o porte de
	 * empresa (micro, pequena, média, grande).
	 * 
	 * @param customer
	 * @throws BusinessException
	 *             Limite de Crédito informado para o Cliente não é compatível
	 *             com o Porte do Cliente.
	 */
    public void save(Customer customer) throws BusinessException {
    	
    	
    	if(customer.getCreditLimit() == null) {
    		customer.setCreditLimit(BigDecimal.ZERO);
    	}
    	
    	validateBeforeSave(customer);
    	
        log.info("Salvando cliente " + customer.getName());
    
        if(customer.getCode() == null) {
        	customer.setCreationDate(new Date());
        }
        
        customerDAO.save(customer);
    }
    
    private void validateBeforeSave(Customer cliente) throws BusinessException {
		
		CustomerSize porteCliente = CustomerSize.getInstance(cliente.getSize());
		
		porteCliente.validarLimiteCredito(cliente.getCreditLimit());
		
	}

    /**
     * Realiza exclusão do Cliente.
     * 
     * @param customer
     * @throws Exception
     */
	public void delete(Customer customer) throws Exception {
		log.info("deleting customer " + customer.getCode());
    	customerDAO.delete(customer);
    }
    
    public List<Customer> searchByName(String name) {
    	return customerDAO.searchByName(name);
    }
    
    public List<Customer> search() {
    	return customerDAO.search();
    }

	public Customer pesquisar(Long code) {
		return customerDAO.search(code);
	}
    
}
