package br.com.sankhya.crm.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.com.sankhya.crm.model.Cliente;
import br.com.sankhya.crm.service.ClienteService;

/**
 * Classe utilizada pelas views do JSF para converter o código do cliente na
 * URL em um objeto do tipo Cliente.
 * 
 * @author Bruno Soares Bravo
 *
 */
@FacesConverter(value = "clienteConverter")
public class ClienteConverter implements Converter {

	@Inject
	private ClienteService clienteService;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {

		if (value == null) {
			return null;
		}

		if (value.isEmpty() || !value.matches("[0-9]+")) {
			JSFUtil.redirecionarPagina("/cliente/list");
			return null;

		}

		Long codigo = Long.valueOf(value);

		Cliente cliente = clienteService.pesquisar(codigo);

		if (cliente == null) {
			JSFUtil.redirecionarPagina("/cliente/list");
		}

		return cliente;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
	
		if (value == null) {
			return "";
		}

		if (!(value instanceof Cliente)) {
			throw new ConverterException("O valor não é válido para uma instância de Cliente: " + value);
		}

		Long codigo = ((Cliente) value).getCodigo();

		return (codigo != null) ? String.valueOf(codigo) : null;
	}

}
