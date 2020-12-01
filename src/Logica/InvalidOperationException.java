package Logica;

/**
 * Modela una excepción de operación inválida.
 * @author Leonardo Rodríguez.
 *
 */
public class InvalidOperationException extends Exception{
	/**
	 * Inicializa una InvalidOperationException indicando el origen del error.
	 * @param s Es el mensaje que describe el origen de la excepción.
	 */
	public InvalidOperationException(String s) {
		super(s);
	}

}