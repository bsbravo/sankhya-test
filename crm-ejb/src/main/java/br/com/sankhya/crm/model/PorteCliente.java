package br.com.sankhya.crm.model;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import br.com.sankhya.crm.exception.BusinessException;
import br.com.sankhya.crm.util.Constantes;

/**
 * Define os tipos possíveis para porte de um Cliente.
 * Implementa validação de limite de crédito por porte do Cliente.
 * 
 * @author Bruno Soares Bravo
 *
 */
public enum PorteCliente {
	
	MICRO(1l) {
		@Override
		public void validarLimiteCredito(BigDecimal limiteCredito) throws BusinessException {
			if(limiteCredito.compareTo(BigDecimal.ZERO) != 0) {
				throw new BusinessException("Micro empresas não podem ter limite de crédito.");
			}
		}
	}, PEQUENO(2l) {
		@Override
		public void validarLimiteCredito(BigDecimal limiteCredito) throws BusinessException {
			if(limiteCredito.compareTo(Constantes.LIMITE_PEQUENO_PORTE) > 0)
				throw new BusinessException("Empresas de porte pequeno não podem ter limite maior do que " + df.format(Constantes.LIMITE_PEQUENO_PORTE) );
			
		}
	}, MEDIO(3l) {
		@Override
		public void validarLimiteCredito(BigDecimal limiteCredito) throws BusinessException {
			if(limiteCredito.compareTo(Constantes.LIMITE_MEDIO_PORTE) > 0)
				throw new BusinessException("Empresas de porte médio não podem ter limite maior do que " + df.format(Constantes.LIMITE_MEDIO_PORTE ));
			
		}
	}, GRANDE(4l) {
		@Override
		public void validarLimiteCredito(BigDecimal limiteCredito) throws BusinessException {
			if(limiteCredito.compareTo(Constantes.LIMITE_GRANDE_PORTE) > 0)
				throw new BusinessException("Empresas de porte grande não podem ter limite maior do que " + df.format(Constantes.LIMITE_GRANDE_PORTE ));
			
		}
	};
	
	
	private static final DecimalFormat df = new DecimalFormat("¤ ###,###,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));
	
	private Long porteCliente;
	
	private PorteCliente(Long porteCliente) {
		this.porteCliente = porteCliente;
	}
	
	public static PorteCliente getInstance(Long porteCliente) throws BusinessException
	{
	    for (PorteCliente type : PorteCliente.values()) {
	        if(type.porteCliente == porteCliente)
	            return type;
	    }
	    
	    throw new BusinessException("Porte da empresa inválido");
	    
	}
	
	public abstract void validarLimiteCredito(BigDecimal limiteCredito) throws BusinessException;
	
}
