package Logica;

import java.io.*;

public class PluginClassLoader extends ClassLoader {
	/**
	 * Este código fue sacado de la pagina:https://javaranch.com/journal/200607/Plugins.html
	 */
	private File directory;

	public PluginClassLoader (File dir) {
		directory = dir;
	}

	public Class loadClass (String name) throws ClassNotFoundException { 
		return loadClass(name, true); 
	}


	public Class loadClass (String classname, boolean resolve) throws ClassNotFoundException {
		Class c;
		byte[]classbytes;
		int length;
		File f;
		String filename;
		DataInputStream in;

		try {

			c = findLoadedClass(classname);

			if (c == null) {
				try {
					c = findSystemClass(classname); 
				}
				catch (Exception ex) {

				}
			}

			if (c == null) {
				filename = classname.replace('.',File.separatorChar)+".class";
				f = new File(directory, filename);
				length = (int) f.length();
				classbytes = new byte[length];
				in = new DataInputStream(new FileInputStream(f));
				in.readFully(classbytes);
				in.close();
				c = defineClass(null, classbytes, 0, length);
			}

			if (resolve) 
				resolveClass(c);

			return c;
		}
		catch (Exception ex) {
			throw new ClassNotFoundException(ex.toString());
		}
	}
}
