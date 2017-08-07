package br.com.sankhya.crm.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Mapeamento da tabela para salvar Clientes cadastrados no
 * sistema.
 * 
 * 
 * @author Bruno Soares Bravo
 *
 */
@Entity
public class Cliente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private Long codigo;
	private List<Contato> contatos = new ArrayList<Contato>();
	private String nome;
	private String logradouro;
	private Long numeroLogradouro;
	private String telefone;
	private Long tipoPessoa;
	private String cpfCnpj;
	private String cidade;
	private String estado;
	private String email;
	private Long porte;
	private BigDecimal limiteCredito;
	private Date dataCadastro;

	
	@Id
	@GeneratedValue
	@Column(name="CODIGO")
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	@Column(name="NOME")
	@NotEmpty
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name="LOGRADOURO")
	@NotEmpty
	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	@Column(name="NUMERO_LOGRADOURO")
	@NotNull
	public Long getNumeroLogradouro() {
		return numeroLogradouro;
	}

	public void setNumeroLogradouro(Long numeroLogradouro) {
		this.numeroLogradouro = numeroLogradouro;
	}

	@NotEmpty
	@Pattern(regexp = "[0-9]\\d*", message="Deve conter apenas números.")
	@Column(name="TELEFONE")
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Column(name="TIPO_PESSOA")
	public Long getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(Long tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	@NotEmpty
	@Pattern(regexp = "[0-9]\\d*", message="Deve conter apenas números.")
	@Column(name="CPF_CNPJ")
	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	@NotEmpty
	@Column(name="CIDADE")
	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	@Size(min = 2, max = 2 ,message="Sigla, ex: MG, GO...")
	@Column(name="ESTADO")
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@NotNull
	@NotEmpty
	@Email
	@Column(name="EMAIL")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name="PORTE")
	public Long getPorte() {
		return porte;
	}

	public void setPorte(Long porte) {
		this.porte = porte;
	}

	@Column(name="LIMITE_CREDITO")
	public BigDecimal getLimiteCredito() {
		return limiteCredito;
	}

	public void setLimiteCredito(BigDecimal limiteCredito) {
		this.limiteCredito = limiteCredito;
	}

	@Column(name="DATA_CADASTRO")
	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	@OneToMany(mappedBy="cliente", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	public List<Contato> getContatos() {
		return contatos;
	}

	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Cliente other = (Cliente) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
	
	
	

}
