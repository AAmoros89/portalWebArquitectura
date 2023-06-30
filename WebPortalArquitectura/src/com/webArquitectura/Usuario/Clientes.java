package com.webArquitectura.Usuario;

/**
 * Clase Clientes que extiende de Usuario, dado que un usuario puede ser Cliente, Empleado.
 * 
 * @author aamor
 *
 */
public class Clientes extends Usuario {

	/**
	 * Constructor de la clase Clientes que recibe como parametros:
	 * 
	 * @param id
	 * @param nombre
	 * @param apellido
	 * @param calle
	 * @param ciudad
	 * @param usuario
	 * @param password
	 */
	public Clientes(int id, String nombre, String apellido, String calle, String ciudad, String usuario,
			String password) {

		super(id, nombre, apellido, calle, ciudad, usuario, password);
	}

	/**
	 * Constructor de la clase Clientes que recibe como parametros los mismos que el anterior menos el id.
	 * 
	 * @param nombre
	 * @param apellido
	 * @param calle
	 * @param ciudad
	 * @param usuario
	 * @param contrasena
	 */
	public Clientes(String nombre, String apellido, String calle, String ciudad, String usuario, String contrasena) {

		super(nombre, apellido, calle, ciudad, usuario, contrasena);
	}
}
