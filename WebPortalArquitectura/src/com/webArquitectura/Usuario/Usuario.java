package com.webArquitectura.Usuario;

/**
 * Clase Usuario declarada como abstracta para que hereden los diferentes tipos
 * de usuario que se encuentran en la aplicacion.
 * 
 * @author aamor
 *
 */
public abstract class Usuario {

	private int id;
	private String nombre;
	private String apellido;
	private String calle;
	private String ciudad;
	private String usuario;
	private String password;

	/**
	 * Constructor de la clase Usuario con los siguientes parametros:
	 * 
	 * @param id
	 * @param nombre
	 * @param apellido
	 * @param calle
	 * @param ciudad
	 * @param usuario
	 * @param password
	 */
	public Usuario(int id, String nombre, String apellido, String calle, String ciudad, String usuario,
			String password) {

		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.calle = calle;
		this.ciudad = ciudad;
		this.usuario = usuario;
		this.password = password;
	}

	/**
	 * Constructor sobrecargado con los mismos parametros que el anterior pero sin
	 * id.
	 * 
	 * @param nombre
	 * @param apellido
	 * @param calle
	 * @param ciudad
	 * @param usuario
	 * @param contrasena
	 */
	public Usuario(String nombre, String apellido, String calle, String ciudad, String usuario, String contrasena) {

		this.nombre = nombre;
		this.apellido = apellido;
		this.calle = calle;
		this.ciudad = ciudad;
		this.usuario = usuario;
		this.password = contrasena;
	}

	/**
	 * Metodos getters y setters.
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getcontrasena() {
		return password;
	}

	public void setcontrasena(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", calle=" + calle + ", ciudad="
				+ ciudad + ", usuario=" + usuario + ", contrasena=" + password + "]";
	}
}
