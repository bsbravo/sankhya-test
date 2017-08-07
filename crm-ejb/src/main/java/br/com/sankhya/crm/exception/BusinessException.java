package br.com.sankhya.crm.exception;

/**
 * Representa as exceções lançadas por erro de validação na lógica de negócio.
 * 
 * @author Bruno Soares Bravo
 *
 */
public class BusinessException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BusinessException(String string) {
		super(string);
	}
	

}
