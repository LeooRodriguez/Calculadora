package Logica;

public interface PluginFunction {

	/**
	 * Devuelve el nombre del plugins.
	 * @return String nombre del plugins.
	 */
	public String getPluginName();
	
	/*
	 * Setea los parametros del plugins.
	 */
	public void setParameters (double num1, double num2);

	/**
	 * Devuelve el resultado del plugins.
	 * @return Entero resultado de la operación del plugins.
	 */
	public double getResult();

	/**Indica si ocurrio un error
	 * @return Verdadero si hay error, falso caso contrario.
	 * */
	public boolean hasError();
}
