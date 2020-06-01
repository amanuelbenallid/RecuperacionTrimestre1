package org.iesalixar.amanuelbenallid.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase que tiene todos los atributos de los Jugadores, los getters y setter y
 * los constructores
 * 
 * @author Alberto
 *
 */
@Entity
@Table(name="Player")
public class Player implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column( name="id")
	private Long id;
	@Column( name="nombre")
	private String nombre;
	@Column( name="dni")
	private String dni;
	@Column( name="apellidos")
	private String apellidos;
	@Column( name="direccion")
	private String direccion;
	@ManyToOne
	@JoinColumn( name="equipo")
	private Team equipo;

	public Player(Long id, String nombre, String apellidos, String dni, String direccion, int equipo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.direccion = direccion;
	}
	public Player() {
		
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
	
	public Team getEquipo() {
		return equipo;
	}
	public void setEquipo(Team equipo) {
		this.equipo = equipo;
	}
	
	@Override
	public String toString() {
		return "Player [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", dni="
				+ dni + ", direccion=" + direccion + "]";
	}

}
