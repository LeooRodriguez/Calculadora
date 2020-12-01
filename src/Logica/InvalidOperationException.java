package Logica;

/**
 * Modela una excepci�n de operaci�n inv�lida.
 * @author Leonardo Rodr�guez.
 *
 */
public class InvalidOperationException extends Exception{
	/**
	 * Inicializa una InvalidOperationException indicando el origen del error.
	 * @param s Es el mensaje que describe el origen de la excepci�n.
	 */
	public InvalidOperationException(String s) {
		super(s);
	}

}