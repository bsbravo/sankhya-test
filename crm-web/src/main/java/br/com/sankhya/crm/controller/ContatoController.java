package br.com.sankhya.crm.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sankhya.crm.model.Cliente;
import br.com.sankhya.crm.model.Contato;
import br.com.sankhya.crm.service.ContatoService;
import br.com.sankhya.crm.util.JSFUtil;

/**
 * Classe controller das telas para listagem de contatos
 * e inclusão/edição de um contato.
 * 
 * @author Bruno Soares Bravo
 *
 */
@Named
@ViewScoped
public class ContatoController implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private ContatoService contatoService;
	
    private Cliente cliente;
    private Contato contato;
    private List<Contato> contatos;
    private String codigoContato;

	/**
	 * Realiza o cadastro de novo contato ou atualiza o contato conforme os
	 * dados preenchidos no formulário.
	 * 
	 */
    public String salvar() {
        
    	try {
        
    		boolean novoContato = contato.getCodigo() == null;
    		
        	contatoService.salvar(contato);
        	
        	if(novoContato) {
        		JSFUtil.exibirMensagem("Contato cadastrado com sucesso!");
        	} else {
        		JSFUtil.exibirMensagem("Contato atualizado com sucesso!");
        	}

        	return "list?faces-redirect=true&includeViewParams=true";
        
    	} catch (Exception e) {
            JSFUtil.exibirMensagemErro(e);
        }
        
        return "save?faces-redirect=true&includeViewParams=true";
        
    }
    
	/**
	 * Exclui do banco de dados o contato selecionado na DataTable.
	 * 
	 * @param contato
	 */
	public String excluir(Contato contato) {

		try {
			
			contatoService.excluir(contato);
			loadContatos();
			
			JSFUtil.exibirMensagem("Contato removido com sucesso!");
			
		} catch (Exception e) {
			JSFUtil.exibirMensagemErro(e);
		}

		return "list?faces-redirect=true&includeViewParams=true";
	}

	/**
	 * Carrega para exibição na DataTable os contatos relacionados com o Cliente
	 * selecionado.
	 */
	public void loadContatos() {

		if(cliente != null) {
			contatos = contatoService.pesquisarPorCodigoCliente(cliente.getCodigo());
		
		} else {
			JSFUtil.redirecionarPagina("/cliente/list");
		}
	}

	//http://java.dzone.com/articles/bookmarkability-jsf-2?page=0,1
	//https://blogs.oracle.com/enterprisetechtips/entry/post_redirect_get_and_jsf
	/**
	 * Converte os parametros da requisicao 
	 * (ex:save.jsf?codigoCliente=1&codigoContato=1) em um objeto to tipo "Contato"
	 */
	public void loadContato() {
		
		//
		//  Sistema redireciona navegação caso usuário entre uma URL com codigoCliente ou codigoUsuario inválidos.
		//
		if(cliente == null) {
			JSFUtil.redirecionarPagina("/cliente/list");
			return;
		}
		
		if (codigoContato != null && !codigoContato.matches("[0-9]+")) {
			JSFUtil.redirecionarPagina("/cliente/list");
			return;
		}

		// Carrega novo contato para Inserção
		if (codigoContato == null) {
			contato = new Contato();
			contato.setCliente(cliente);
			return;
		}


		// Busca contato para Edição
		contato = contatoService.pesquisarPorPK(Long.valueOf(codigoContato), cliente.getCodigo());

		if (contato == null) {
			JSFUtil.redirecionarPagina("/cliente/list");
		}

	}
	
	public List<Contato> getContatos() {
		return contatos;
	}

	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}


	public String getCodigoContato() {
		return codigoContato;
	}

	public void setCodigoContato(String codigoContato) {
		this.codigoContato = codigoContato;

	}
    
}
