package org.iesalixar.amanuelbenallid.model;

import java.util.Date;

import org.apache.log4j.Logger;

/**
 * Clase que tiene todos los atributos de los Usuarios, los getters y setter y
 * los constructores
 * 
 * @author Alberto
 *
 */
public class User {
	final static Logger logger = Logger.getLogger(User.class);

	private String username, password, fullname, role;
	private Integer user_id;
	private Date fechanac = new Date();

	public User(Integer user_id, String username, String password, String fullname, Date fechanac, String role) {
		this.user_id = user_id;
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.role = role;
		this.fechanac = fechanac;

	}

	public Date getFechanac() {
		return fechanac;
	}

	public void setFechanac(Date fechanac) {
		this.fechanac = fechanac;
	}

	public User() {

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

}
