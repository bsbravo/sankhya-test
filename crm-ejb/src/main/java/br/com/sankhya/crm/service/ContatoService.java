package br.com.sankhya.crm.service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.sankhya.crm.dao.ContatoDAO;
import br.com.sankhya.crm.model.Contato;

/**
 * Implementa a camada de serviços relacionada aos Contatos.
 * 
 * @author Bruno Soares Bravo
 *
 */
@Stateless
public class ContatoService {

    @Inject
    private Logger log;

    @Inject
    private ContatoDAO contatoDAO;

    public void salvar(Contato contato) throws Exception {
        log.info("Salvando contato...  " + contato.getNome());
        contatoDAO.salvar(contato);
    }
    
    public void excluir(Contato contato) throws Exception {
    	log.info("Removendo contato " + contato.getCodigo() + ", cliente " + contato.getCliente().getCodigo());
    	contatoDAO.excluir(contato);
    	
    }
    
	public List<Contato> pesquisarPorCodigoCliente(Long codigoCliente) {
		return contatoDAO.pesquisarPorCodigoCliente(codigoCliente);
	}
	
	public Contato pesquisarPorPK(Long codigo, Long codigoCliente) {
		return contatoDAO.pesquisar(codigo, codigoCliente);
	}
    
}
