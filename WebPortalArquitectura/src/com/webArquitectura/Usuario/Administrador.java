package com.webArquitectura.Usuario;

/**
 * Clase Administrador que extiende de Empleado dado que un Empleado puede ser de dos tipos (Administrador o Arquitecto)
 * 
 * @author aamor
 *
 */
public class Administrador extends Empleado{

	public Administrador(int id, String nombre, String apellido, String calle, String ciudad, String usuario, String password) {
		super(id, nombre, apellido, calle, ciudad, usuario, password);
	}
}
