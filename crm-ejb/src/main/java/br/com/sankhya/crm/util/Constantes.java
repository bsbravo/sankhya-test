package br.com.sankhya.crm.util;

import java.math.BigDecimal;

/**
 * Define as constantes utilizadas pelo sistema.
 * 
 * @author Bruno Soares Bravo
 *
 */
public class Constantes {
	
	public static final BigDecimal LIMITE_PEQUENO_PORTE = new BigDecimal(10000);
	public static final BigDecimal LIMITE_MEDIO_PORTE  = new BigDecimal(50000);
	public static final BigDecimal LIMITE_GRANDE_PORTE = new BigDecimal(500000);
	
	public static final Long CLIENTE_PESSOA_FISICA = 1l;
	public static final Long CLIENTE_PESSOA_JURIDICA = 2l;

}
