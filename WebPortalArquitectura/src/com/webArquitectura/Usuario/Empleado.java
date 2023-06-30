package com.webArquitectura.Usuario;

/**
 * Clase Empleado que extiende de Usuario, dado que un usuario puede ser Cliente, Empleado.
 * 
 * @author aamor
 *
 */
public class Empleado extends Usuario{

	/**
	 * Constructor de la clase Empleado que recibe los siguientes parametros:
	 * 
	 * @param id
	 * @param nombre
	 * @param apellido
	 * @param calle
	 * @param ciudad
	 * @param usuario
	 * @param password
	 */
	public Empleado(int id, String nombre, String apellido, String calle, String ciudad, String usuario, String password) {
		super(id, nombre, apellido, calle, ciudad, usuario, password);	
	}
}
