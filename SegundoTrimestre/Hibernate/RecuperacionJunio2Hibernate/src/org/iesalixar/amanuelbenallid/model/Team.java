package org.iesalixar.amanuelbenallid.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Clase que tiene todos los atributos de los Equipos, los getters y setter y
 * los constructores
 * 
 * @author Alberto
 *
 */
@Entity
@Table(name="Team")
public class Team implements Serializable {
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
	@Column( name="ciudad")
	private String ciudad;
	@Column( name="pais")
	private String pais;
	@OneToMany( mappedBy="equipo", fetch=FetchType.EAGER)
	Set<Player> jugadores;
	
	

	public Team(Long id, String nombre, String ciudad, String pais) {
		super();
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.pais = pais;
		this.id = id;
	}
	public Team(){
		
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Set<Player> getJugadores() {
		return jugadores;
	}
	public void setJugadores(Set<Player> jugadores) {
		this.jugadores = jugadores;
	}
	@Override
	public String toString() {
		return "Team [nombre=" + nombre + ", ciudad=" + ciudad + ", pais=" + pais + ", id=" + id + "]";
	}

}
