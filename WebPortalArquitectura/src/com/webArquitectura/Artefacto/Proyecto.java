package com.webArquitectura.Artefacto;

/**
 * Clase Proyecto que se utilizara cuando un usuario quiera dar de alta un nuevo
 * proyecto
 * 
 * @author aamor
 *
 */
public class Proyecto {

	// Se declaran las diferentes variables que tendran un proyecto.
	private int id;
	private String nombre;
	private int idUsuario;
	private String fecha;
	private int idArquitecto;
	private String duracionPrevista;
	private String tipo;
	private String direccion;
	private int superficie_t;
	private int superficie_e;
	private int plantas;
	private int habitaciones;
	private int banos;
	private String finalidad;
	private int superficie_r;
	private String fechaRealizacion;
	private int presupuesto;
	private String estado;

	/**
	 * Constructor sobrecargado de la clase Proyecto con los siguientes parametros:
	 * 
	 * @param id
	 * @param nombre
	 * @param idUsuario
	 * @param fecha
	 * @param tipo
	 */
	public Proyecto(int id, String nombre, int idUsuario, String fecha, String tipo) {

		this.id = id;
		this.nombre = nombre;
		this.idUsuario = idUsuario;
		this.fecha = fecha;
		this.tipo = tipo;
	}

	/**
	 * Constructor sobrecargado de la clase Proyecto con los siguientes parametros:
	 * 
	 * @param id
	 * @param direccion
	 * @param superficie_t
	 * @param superficie_e
	 * @param plantas
	 * @param habitaciones
	 * @param banos
	 * @param estado
	 */
	public Proyecto(int id, String direccion, int superficie_t, int superficie_e, int plantas, int habitaciones,
			int banos, String estado) {

		this.id = id;
		this.direccion = direccion;
		this.superficie_t = superficie_t;
		this.superficie_e = superficie_e;
		this.plantas = plantas;
		this.habitaciones = habitaciones;
		this.banos = banos;
		this.estado = estado;
	}

	/**
	 * Constructor sobrecargado de la clase Proyecto con los siguientes parametros:
	 * 
	 * @param id
	 * @param direccion
	 * @param superficie_t
	 * @param superficie_e
	 * @param finalidad
	 * @param estado
	 */
	public Proyecto(int id, String direccion, int superficie_t, int superficie_e, String finalidad, String estado) {

		this.id = id;
		this.direccion = direccion;
		this.superficie_t = superficie_t;
		this.superficie_e = superficie_e;
		this.finalidad = finalidad;
		this.estado = estado;
	}

	/**
	 * Constructor sobrecargado de la clase Proyecto con los siguientes parametros:
	 * 
	 * @param id
	 * @param direccion
	 * @param superficie_r
	 * @param estado
	 */
	public Proyecto(int id, String direccion, int superficie_r, String estado) {

		this.id = id;
		this.direccion = direccion;
		this.superficie_r = superficie_r;
		this.estado = estado;
	}

	/**
	 * Constructor sobrecargado de la clase Proyecto con los siguientes parametros:
	 * 
	 * @param id
	 * @param nombre
	 * @param idUsuario
	 * @param fecha
	 * @param idArquitecto
	 * @param duracionPrevista
	 * @param tipo
	 * @param direccion
	 * @param superficie_t
	 * @param superficie_e
	 * @param plantas
	 * @param habitaciones
	 * @param banos
	 * @param finalidad
	 * @param superficie_r
	 * @param presupuesto
	 * @param fechaRealizacion
	 * @param estado
	 */
	public Proyecto(int id, String nombre, int idUsuario, String fecha, int idArquitecto, String duracionPrevista,
			String tipo, String direccion, int superficie_t, int superficie_e, int plantas, int habitaciones, int banos,
			String finalidad, int superficie_r, int presupuesto, String fechaRealizacion, String estado) {

		this.id = id;
		this.nombre = nombre;
		this.idUsuario = idUsuario;
		this.fecha = fecha;
		this.idArquitecto = idArquitecto;
		this.duracionPrevista = duracionPrevista;
		this.tipo = tipo;
		this.direccion = direccion;
		this.superficie_t = superficie_t;
		this.superficie_e = superficie_e;
		this.plantas = plantas;
		this.habitaciones = habitaciones;
		this.banos = banos;
		this.finalidad = finalidad;
		this.superficie_r = superficie_r;
		this.presupuesto = presupuesto;
		this.fechaRealizacion = fechaRealizacion;
		this.estado = estado;
	}

	/**
	 * Constructor sobrecargado de la clase Proyecto con los siguientes parametros:
	 * 
	 * @param id
	 * @param nombre
	 * @param idUsuario
	 * @param fecha
	 * @param idArquitecto
	 * @param duracionPrevista
	 * @param tipo
	 * @param presupuesto
	 * @param fechaRealizacion
	 * @param estado
	 */
	public Proyecto(int id, String nombre, int idUsuario, String fecha, int idArquitecto, String duracionPrevista,
			String tipo, int presupuesto, String fechaRealizacion, String estado) {

		this.id = id;
		this.nombre = nombre;
		this.idUsuario = idUsuario;
		this.fecha = fecha;
		this.idArquitecto = idArquitecto;
		this.duracionPrevista = duracionPrevista;
		this.tipo = tipo;
		this.presupuesto = presupuesto;
		this.fechaRealizacion = fechaRealizacion;
		this.estado = estado;
	}

	/**
	 * Constructor sobrecargado de la clase Proyecto con los siguientes parametros:
	 * 
	 * @param id
	 * @param nombre
	 * @param idUsuario
	 * @param fecha
	 * @param idArquitecto
	 * @param estado
	 */
	public Proyecto(int id, String nombre, int idUsuario, String fecha, int idArquitecto, String estado) {

		this.id = id;
		this.nombre = nombre;
		this.idUsuario = idUsuario;
		this.fecha = fecha;
		this.idArquitecto = idArquitecto;
		this.estado = estado;
	}

	/**
	 * Metodos getters y setters
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

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public int getidArquitecto() {
		return idArquitecto;
	}

	public void setidArquitecto(int idArquitecto) {
		this.idArquitecto = idArquitecto;
	}

	public String getDuracionPrevista() {
		return duracionPrevista;
	}

	public void setDuracionPrevista(String duracionPrevista) {
		this.duracionPrevista = duracionPrevista;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getIdArquitecto() {
		return idArquitecto;
	}

	public void setIdArquitecto(int idArquitecto) {
		this.idArquitecto = idArquitecto;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getSuperficie_t() {
		return superficie_t;
	}

	public void setSuperficie_t(int superficie_t) {
		this.superficie_t = superficie_t;
	}

	public int getSuperficie_e() {
		return superficie_e;
	}

	public void setSuperficie_e(int superficie_e) {
		this.superficie_e = superficie_e;
	}

	public int getPlantas() {
		return plantas;
	}

	public void setPlantas(int plantas) {
		this.plantas = plantas;
	}

	public int getHabitaciones() {
		return habitaciones;
	}

	public void setHabitaciones(int habitaciones) {
		this.habitaciones = habitaciones;
	}

	public int getbanos() {
		return banos;
	}

	public void setbanos(int banos) {
		this.banos = banos;
	}

	public String getFinalidad() {
		return finalidad;
	}

	public void setFinalidad(String finalidad) {
		this.finalidad = finalidad;
	}

	public int getSuperficie_r() {
		return superficie_r;
	}

	public void setSuperficie_r(int superficie_r) {
		this.superficie_r = superficie_r;
	}

	public String getFechaRealizacion() {
		return fechaRealizacion;
	}

	public void setFechaRealizacion(String fechaRealizacion) {
		this.fechaRealizacion = fechaRealizacion;
	}

	public int getPresupuesto() {
		return presupuesto;
	}

	public void setPresupuesto(int presupuesto) {
		this.presupuesto = presupuesto;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}
