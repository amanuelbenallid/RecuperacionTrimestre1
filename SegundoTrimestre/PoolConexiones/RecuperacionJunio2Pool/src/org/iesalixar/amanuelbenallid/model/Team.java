package org.iesalixar.amanuelbenallid.model;

/**
 * Clase que tiene todos los atributos de los Equipos, los getters y setter y
 * los constructores
 * 
 * @author Alberto
 *
 */
public class Team {

	private String nombre, ciudad, pais;
	private int id;

	public Team(int id, String nombre, String ciudad, String pais) {
		super();
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.pais = pais;
		this.id = id;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Equipo [nombre=" + nombre + ", ciudad=" + ciudad + ", pais=" + pais + ", id=" + id + "]";
	}

}
