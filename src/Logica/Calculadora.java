package Logica;


import java.io.File;
import java.util.*;

import javax.swing.JOptionPane;

public class Calculadora {
/**
 * Gran parte de este c�digo fue sacado de la pagina:https://javaranch.com/journal/200607/Plugins.html
 */
	private ArrayList plugins;
	private File directory;
	private ArrayList<String> pluginsNames;

	public Calculadora () {
		directory=new File("./plugins");
		plugins= new ArrayList();
		pluginsNames= new ArrayList();
	}
	/**
	 * Carga los plugins en una lista y carga los nombres de dichos plugins en otra lista.
	 */
	public void getPlugins() throws InvalidPathException{
		ClassLoader cl;
		String[] files;
		Class c;
		Class[] intf;
		PluginFunction pf;
		try {
			cl = new PluginClassLoader(directory);
			files = directory.list();
			if(files!=null) {
				for (int i=0; i<files.length; i++) {
					if (! files[i].endsWith(".class"))
						continue;
					c = cl.loadClass(files[i].substring(0, files[i].indexOf(".")));
					intf = c.getInterfaces();
					for (int j=0; j<intf.length; j++) {
						if (intf[j].getName().contentEquals("Logica.PluginFunction")) {
							pf = (PluginFunction) c.newInstance();
							pluginsNames.add(pf.getPluginName());
							plugins.add(pf);
							continue;
						}
					}
				}
			}
			else {
				throw new InvalidPathException("ERROR: Ruta inv�lida");
			}
		} catch (Exception ex) {
			throw new InvalidPathException("ERROR: Ruta inv�lida");

		}
	}
	/**
	 * Calcula un resultado en base a un plugins.
	 * @param nombrePlug Nombre del complemento a calcular el resultado.
	 * @param num1 Entero a utilizar en la operaci�n.
	 * @param num2 Entero a utilizar en la operaci�n.
	 * @return Double resultado de calcular la operaci�n de dicho plugins.
	 */
	public double runPlugins(String nombrePlug,int num1,int num2)throws InvalidOperationException{
		double toRet=0;
		int cantidad=plugins.size();
		PluginFunction pf;
		boolean esta=false;
		int i=0;
		String nombre;
		while (i<cantidad&&!esta) {
			pf=(PluginFunction) plugins.get(i);
			nombre=pf.getPluginName();
			if(nombre.equals(nombrePlug)) {
				pf.setParameters(num1,num2);
				if(pf.hasError()) {
					throw new InvalidOperationException("ERROR: Operaci�n inv�lida con los operandos ingresados ");
				}
				else {
					toRet=pf.getResult();
					esta=true;
				}
			}
			i++;
		}
		return toRet;
	}

	/**
	 * Retorna la lista de nombres de plugins.
	 * @return
	 */
	public ArrayList<String> getNames(){
		return pluginsNames;
	}
	/**
	 * Limpia la lista de nombres de plugins.
	 * 
	 */
	public void limpiarNombres() {
		pluginsNames.clear();

	}
}
