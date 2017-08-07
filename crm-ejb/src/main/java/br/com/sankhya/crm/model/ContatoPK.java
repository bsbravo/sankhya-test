package br.com.sankhya.crm.model;

import java.io.Serializable;

/**
 * Representa a chave primária composta da tabela de Contatos.
 * 
 * @author Bruno Soares Bravo
 *
 */
public class ContatoPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long codigo;
	private Long cliente;

	public ContatoPK() {
		
	}
	
	public ContatoPK(Long codigo, Long cliente) {
		this.codigo = codigo;
		this.cliente = cliente;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Long getCliente() {
		return cliente;
	}

	public void setCliente(Long codigoCliente) {
		this.cliente = codigoCliente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result
				+ ((cliente == null) ? 0 : cliente.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContatoPK other = (ContatoPK) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		return true;
	}
	
	
	

	
	
	
	

}
