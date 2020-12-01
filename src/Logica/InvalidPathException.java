package Logica;
/**
 * Modela una excepción de ruta inválida.
 * @author Leonardo Rodríguez.
 *
 */
public class InvalidPathException extends Exception{
	/**
	 * Inicializa una InvalidPathException indicando el origen del error.
	 * @param s Es el mensaje que describe el origen de la excepción.
	 */
	public InvalidPathException(String s) {
		super(s);
	}

}
