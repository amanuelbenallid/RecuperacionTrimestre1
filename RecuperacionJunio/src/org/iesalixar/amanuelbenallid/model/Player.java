package org.iesalixar.amanuelbenallid.model;

/**
 * Clase que tiene todos los atributos de los Jugadores, los getters y setter y
 * los constructores
 * 
 * @author Alberto
 *
 */
public class Player {

	private int equipo, id;
	private String nombre, apellidos, dni, direccion;

	public Player(int id, String nombre, String apellidos, String dni, String direccion, int equipo) {
		super();
		this.equipo = equipo;
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.direccion = direccion;
	}

	public int getEquipo() {
		return equipo;
	}

	public void setEquipo(int equipo) {
		this.equipo = equipo;
	}

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

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

}
