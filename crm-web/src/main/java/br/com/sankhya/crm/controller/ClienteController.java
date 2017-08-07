package br.com.sankhya.crm.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sankhya.crm.model.Cliente;
import br.com.sankhya.crm.service.ClienteService;
import br.com.sankhya.crm.util.Constantes;
import br.com.sankhya.crm.util.JSFUtil;

/**
 * Controller das telas para listagem de clientes e inclusão/edição de um
 * cliente.
 * 
 * @author Bruno Soares Bravo
 *
 */
@Named
@ViewScoped
public class ClienteController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private ClienteService clienteService;

	private Cliente cliente;
	private List<Cliente> clientes;

	/**
	 * Realiza pesquisa dos clientes cadastrados no sistema para serem exibidos na
	 * DataTable. Se for preenchido o campo "nome" na tela este campo é utilizado
	 * como filtro.
	 * 
	 */
	public void pesquisar() {
		
		try {

			if (cliente.getNome().isEmpty()) {
				clientes = clienteService.pesquisar();
			} else {
				clientes = clienteService.pesquisarPorNome(cliente.getNome());
			}

		} catch (Exception e) {
			JSFUtil.exibirMensagemErro(e);
		}

	}
	
	/**
	 * Realiza o cadastro de novo cliente ou atualiza o cliente conforme os
	 * dados preenchidos no formulário.
	 * 
	 */
	public String salvar() {
		
		try {
			
			boolean novoCliente = cliente.getCodigo() == null;

			clienteService.salvar(cliente);
			
        	if(novoCliente) {
        		JSFUtil.exibirMensagem("Cliente cadastrado com sucesso!");
        	} else {
        		JSFUtil.exibirMensagem("Cliente atualizado com sucesso!");
        	}
			
			return "list";
			
		} catch (Exception e) {
			JSFUtil.exibirMensagemErro(e);
		}

		return null;

	}

	/**
	 * Exclui do banco de dados o cliente selecionado na DataTable.
	 * 
	 * @param cliente
	 */
	public String excluir(Cliente cliente) {

		try {
			
			clienteService.excluir(cliente);
			pesquisar();
			
			JSFUtil.exibirMensagem("Cliente removido com sucesso!");
			
		} catch (Exception e) {

			JSFUtil.exibirMensagemErro(e);

		}

		return null;
	}

	@PostConstruct
	private void initNewMember() {
		
		clientes = clienteService.pesquisar();

		cliente = new Cliente();
		cliente.setTipoPessoa(Constantes.CLIENTE_PESSOA_FISICA);

	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

}
