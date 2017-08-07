package br.com.sankhya.crm.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.sankhya.crm.dao.ClienteDAO;
import br.com.sankhya.crm.exception.BusinessException;
import br.com.sankhya.crm.model.Cliente;
import br.com.sankhya.crm.model.PorteCliente;

/**
 * Implementa a lógica de negócio para as operações relacionadas aos Clientes.
 * 
 * @author Bruno Soares Bravo
 *
 */
@Stateless
public class ClienteService {

    @Inject
    private Logger log;

    @Inject
    private ClienteDAO clienteDAO;
    

	/**
	 * Realiza operação de inserção/atualização de um Cliente.
	 * 
	 * O campo "limiteCredito" do Cliente é validado de acordo com o porte de
	 * empresa (micro, pequena, média, grande).
	 * 
	 * @param cliente
	 * @throws BusinessException
	 *             Limite de Crédito informado para o Cliente não é compatível
	 *             com o Porte do Cliente.
	 */
    public void salvar(Cliente cliente) throws BusinessException {
    	
    	
    	if(cliente.getLimiteCredito() == null) {
    		cliente.setLimiteCredito(BigDecimal.ZERO);
    	}
    	
    	validarAntesSalvar(cliente);
    	
        log.info("Salvando cliente " + cliente.getNome());
    
        if(cliente.getCodigo() == null) {
        	cliente.setDataCadastro(new Date());
        }
        
        clienteDAO.salvar(cliente);
    }
    
    private void validarAntesSalvar(Cliente cliente) throws BusinessException {
		
		PorteCliente porteCliente = PorteCliente.getInstance(cliente.getPorte());
		
		porteCliente.validarLimiteCredito(cliente.getLimiteCredito());
		
	}

    /**
     * Realiza exclusão do Cliente.
     * 
     * @param cliente
     * @throws Exception
     */
	public void excluir(Cliente cliente) throws Exception {

		log.info("Removendo cliente " + cliente.getCodigo());
    	clienteDAO.excluir(cliente);
    	
    }
    
    public List<Cliente> pesquisarPorNome(String nome) {
    	return clienteDAO.pesquisarPorNome(nome);
    }
    
    public List<Cliente> pesquisar() {
    	return clienteDAO.pesquisar();
    }

	public Cliente pesquisar(Long codigo) {
		return clienteDAO.pesquisar(codigo);
	}
    
}
