package org.iesalixar.amanuelbenallid.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.log4j.Logger;

/**
 * Clase que tiene todos los atributos de los Usuarios, los getters y setter y
 * los constructores
 * 
 * @author Alberto
 *
 */
@Entity
@Table(name="User")
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(User.class);
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column( name="user_id")
	private Long user_id;
	@Column( name="username")
	private String username;
	@Column( name="password")
	private String password;
	@Column( name="fullname")
	private String fullname;
	@Column( name="role")
	private String role;
	@Column( name="fechanac")
	private Date fechanac = new Date();

	public User(Long user_id, String username, String password, String fullname, Date fechanac, String role) {
		this.user_id = user_id;
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.role = role;
		this.fechanac = fechanac;

	}
	public User() {
		
	}
	public Date getFechanac() {
		return fechanac;
	}

	public void setFechanac(Date fechanac) {
		this.fechanac = fechanac;
	}


	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", fullname=" + fullname + ", role=" + role
				+ ", user_id=" + user_id + ", fechanac=" + fechanac + "]";
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

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

}
