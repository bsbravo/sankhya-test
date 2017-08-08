package br.com.bravo.crm.model;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import br.com.bravo.crm.exception.BusinessException;
import br.com.bravo.crm.util.Constants;

/**
 * Define os tipos possíveis para porte de um Cliente.
 * Implementa validação de limite de crédito por porte do Cliente.
 * 
 * @author Bruno Soares Bravo
 *
 */
public enum CustomerSize {
	
	MICRO(1l) {
		@Override
		public void validarLimiteCredito(BigDecimal limiteCredito) throws BusinessException {
			if(limiteCredito.compareTo(BigDecimal.ZERO) != 0) {
				throw new BusinessException("Micro empresas não podem ter limite de crédito.");
			}
		}
	}, SMALL(2l) {
		@Override
		public void validarLimiteCredito(BigDecimal limiteCredito) throws BusinessException {
			if(limiteCredito.compareTo(Constants.LIMIT_SMALL_CUSTOMER) > 0)
				throw new BusinessException("Empresas de porte pequeno não podem ter limite maior do que " + df.format(Constants.LIMIT_SMALL_CUSTOMER) );
			
		}
	}, MEDIUM(3l) {
		@Override
		public void validarLimiteCredito(BigDecimal limiteCredito) throws BusinessException {
			if(limiteCredito.compareTo(Constants.LIMIT_MEDIUM_CUSTOMER) > 0)
				throw new BusinessException("Empresas de porte médio não podem ter limite maior do que " + df.format(Constants.LIMIT_MEDIUM_CUSTOMER ));
			
		}
	}, LARGE(4l) {
		@Override
		public void validarLimiteCredito(BigDecimal limiteCredito) throws BusinessException {
			if(limiteCredito.compareTo(Constants.LIMIT_LARGE_CUSTOMER) > 0)
				throw new BusinessException("Empresas de porte grande não podem ter limite maior do que " + df.format(Constants.LIMIT_LARGE_CUSTOMER ));
			
		}
	};
	
	
	private static final DecimalFormat df = new DecimalFormat("¤ ###,###,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));
	
	private Long porteCliente;
	
	private CustomerSize(Long porteCliente) {
		this.porteCliente = porteCliente;
	}
	
	public static CustomerSize getInstance(Long porteCliente) throws BusinessException
	{
	    for (CustomerSize type : CustomerSize.values()) {
	        if(type.porteCliente == porteCliente)
	            return type;
	    }
	    
	    throw new BusinessException("Porte da empresa inválido");
	    
	}
	
	public abstract void validarLimiteCredito(BigDecimal limiteCredito) throws BusinessException;
	
}
