package br.com.bravo.crm.util;

import java.math.BigDecimal;

/**
 * Define as constantes utilizadas pelo sistema.
 * 
 * @author Bruno Soares Bravo
 *
 */
public class Constants {
	
	public static final BigDecimal LIMIT_SMALL_CUSTOMER = new BigDecimal(10000);
	public static final BigDecimal LIMIT_MEDIUM_CUSTOMER  = new BigDecimal(50000);
	public static final BigDecimal LIMIT_LARGE_CUSTOMER = new BigDecimal(500000);
	
	public static final Long CUSTOMER_PERSON = 1l;
	public static final Long CUSTOMER_BUSINESS = 2l;

}
