package br.com.bravo.crm.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.com.bravo.crm.model.Customer;
import br.com.bravo.crm.service.CustomerService;

/**
 * Classe utilizada pelas views do JSF para converter o código do cliente na
 * URL em um objeto do tipo Cliente.
 * 
 * @author Bruno Soares Bravo
 *
 */
@FacesConverter(value = "customerConverter")
public class CustomerConverter implements Converter {

	@Inject
	private CustomerService customerService;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {

		if (value == null) {
			return null;
		}

		if (value.isEmpty() || !value.matches("[0-9]+")) {
			JSFUtil.redirectPage("/cliente/list");
			return null;

		}

		Long codigo = Long.valueOf(value);

		Customer cliente = customerService.pesquisar(codigo);

		if (cliente == null) {
			JSFUtil.redirectPage("/cliente/list");
		}

		return cliente;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
	
		if (value == null) {
			return "";
		}

		if (!(value instanceof Customer)) {
			throw new ConverterException("O valor não é válido para uma instância de Cliente: " + value);
		}

		Long codigo = ((Customer) value).getCode();

		return (codigo != null) ? String.valueOf(codigo) : null;
	}

}
