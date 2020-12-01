package Logica;
/**
 * Modela una excepci�n de ruta inv�lida.
 * @author Leonardo Rodr�guez.
 *
 */
public class InvalidPathException extends Exception{
	/**
	 * Inicializa una InvalidPathException indicando el origen del error.
	 * @param s Es el mensaje que describe el origen de la excepci�n.
	 */
	public InvalidPathException(String s) {
		super(s);
	}

}
