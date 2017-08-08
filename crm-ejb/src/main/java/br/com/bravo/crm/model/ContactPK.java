package br.com.bravo.crm.model;

import java.io.Serializable;

/**
 * Representa a chave primária composta da tabela de Contatos.
 * 
 * @author Bruno Soares Bravo
 *
 */
public class ContactPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long code;
	private Long customer;

	public ContactPK() {
		
	}
	
	public ContactPK(Long code, Long customer) {
		this.code = code;
		this.customer = customer;
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public Long getCustomer() {
		return customer;
	}

	public void setCustomer(Long customerCode) {
		this.customer = customerCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result
				+ ((customer == null) ? 0 : customer.hashCode());
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
		ContactPK other = (ContactPK) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		return true;
	}
	
	
	

	
	
	
	

}
