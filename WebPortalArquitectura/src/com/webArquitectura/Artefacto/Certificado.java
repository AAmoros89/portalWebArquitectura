package com.webArquitectura.Artefacto;

/**
 * Clase Certificado que se utilizara cuando un usuario quiera dar de alta un
 * nuevo certificado
 * 
 * @author aamor
 *
 */
public class Certificado {

	private int id;

	private int idUsuario;
	private String fechaSolicitud;
	private int idArquitecto;
	private String tipo;
	private String direccion;
	private String fechaVisita;
	private int presupuesto;
	private String fechaEmision;
	private String categoria;
	private String estado;

	/**
	 * Constructor sobrecargado de la clase Certificado con los siguientes
	 * parametros:
	 * 
	 * @param id
	 * @param idUsuario
	 * @param fechaSolicitud
	 * @param tipo
	 * @param direccion
	 * @param fechaVisita
	 * @param estado
	 */
	public Certificado(int id, int idUsuario, String fechaSolicitud, String tipo, String direccion, String fechaVisita,
			String estado) {

		this.id = id;
		this.idUsuario = idUsuario;
		this.fechaSolicitud = fechaSolicitud;
		this.tipo = tipo;
		this.direccion = direccion;
		this.fechaVisita = fechaVisita;
		this.estado = estado;
	}

	/**
	 * Constructor sobrecargado de la clase Certificado con los siguientes
	 * parametros:
	 * 
	 * @param id
	 * @param idUsuario
	 * @param fechaSolicitud
	 * @param fechaVisita
	 * @param estado
	 */
	public Certificado(int id, int idUsuario, String fechaSolicitud, String fechaVisita, String estado) {

		this.id = id;
		this.idUsuario = idUsuario;
		this.fechaSolicitud = fechaSolicitud;
		this.fechaVisita = fechaVisita;
		this.estado = estado;
	}

	/**
	 * Constructor sobrecargado de la clase Certificado con los siguientes
	 * parametros:
	 * 
	 * @param id
	 * @param idUsuario
	 * @param fechaSolicitud
	 * @param idArquitecto
	 * @param estado
	 */
	public Certificado(int id, int idUsuario, String fechaSolicitud, int idArquitecto, String estado) {

		this.id = id;
		this.idUsuario = idUsuario;
		this.fechaSolicitud = fechaSolicitud;
		this.idArquitecto = idArquitecto;
		this.estado = estado;
	}

	/**
	 * Constructor sobrecargado de la clase Certificado con los siguientes
	 * parametros:
	 * 
	 * @param id
	 * @param idUsuario
	 * @param fechaSolicitud
	 * @param idArquitecto
	 * @param presupuesto
	 * @param fechaEmision
	 * @param categoria
	 * @param estado
	 */
	public Certificado(int id, int idUsuario, String fechaSolicitud, int idArquitecto, int presupuesto,
			String fechaEmision, String categoria, String estado) {

		this.id = id;
		this.idUsuario = idUsuario;
		this.fechaSolicitud = fechaSolicitud;
		this.idArquitecto = idArquitecto;
		this.presupuesto = presupuesto;
		this.fechaEmision = fechaEmision;
		this.categoria = categoria;
		this.estado = estado;
	}

	/**
	 * Constructor sobrecargado de la clase Certificado con los siguientes
	 * parametros:
	 * 
	 * @param id
	 * @param idUsuario
	 * @param fechaSolicitud
	 * @param idArquitecto
	 * @param presupuesto
	 * @param estado
	 */
	public Certificado(int id, int idUsuario, String fechaSolicitud, int idArquitecto, int presupuesto, String estado) {

		this.id = id;
		this.idUsuario = idUsuario;
		this.fechaSolicitud = fechaSolicitud;
		this.idArquitecto = idArquitecto;
		this.presupuesto = presupuesto;
		this.estado = estado;
	}

	/**
	 * Constructor sobrecargado de la clase Certificado con los siguientes
	 * parametros:
	 * 
	 * @param id
	 * @param idUsuario
	 * @param fechaSolicitud
	 * @param idArquitecto
	 * @param tipo
	 * @param direccion
	 * @param fechaVisita
	 * @param presupuesto
	 * @param fechaEmision
	 * @param categoria
	 * @param estado
	 */
	public Certificado(int id, int idUsuario, String fechaSolicitud, int idArquitecto, String tipo, String direccion,
			String fechaVisita, int presupuesto, String fechaEmision, String categoria, String estado) {

		this.id = id;
		this.idUsuario = idUsuario;
		this.fechaSolicitud = fechaSolicitud;
		this.idArquitecto = idArquitecto;
		this.tipo = tipo;
		this.direccion = direccion;
		this.fechaVisita = fechaVisita;
		this.presupuesto = presupuesto;
		this.fechaEmision = fechaEmision;
		this.categoria = categoria;
		this.estado = estado;
	}

	/**
	 * Se generan los getters y setters
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(String fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public int getIdArquitecto() {
		return idArquitecto;
	}

	public void setIdArquitecto(int idArquitecto) {
		this.idArquitecto = idArquitecto;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getFechaVisita() {
		return fechaVisita;
	}

	public void setFechaVisita(String fechaVisita) {
		this.fechaVisita = fechaVisita;
	}

	public int getPresupuesto() {
		return presupuesto;
	}

	public void setPresupuesto(int presupuesto) {
		this.presupuesto = presupuesto;
	}

	public String getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
