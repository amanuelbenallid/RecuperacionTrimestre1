package org.iesalixar.amanuelbenallid.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.iesalixar.amanuelbenallid.helper.ConnectionDB;
import org.iesalixar.amanuelbenallid.model.User;
/**
 * Clase que tiene todos los métodos para el CRUD de los usuario
 * @author Alberto
 *
 */
public class UserDAO {
	/**
	 * Este método comprueba los datos de acceso para el login
	 * @param username recibe el nombre de usuario
	 * @param password recibe la contraseña
	 * @return devuelve true en caso de que el usuario se encuentre en la base de datos, y false si no
	 */
	public static boolean checkUser(String username, String password) {
		boolean found = false;
		Connection c = ConnectionDB.conectarMySQL();
		try {
			PreparedStatement stmt = c
					.prepareStatement("select username, password from usuario where username=? and password=?");
			stmt.setString(1, username);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			found = rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return found;
	}

	/**
	 * Este método comprueba si el usuario es administrador o usuario normal
	 * @param username recibe el nombre de usuario
	 * @param password recibe la contraseña del usuario
	 * @return devuelve una cadena de texto con el role del usuario
	 */
	public static String checkRole(String username, String password) {
		String role = null;
		Connection c = ConnectionDB.conectarMySQL();
		try {
			PreparedStatement stmt = c.prepareStatement(
					"Select role FROM usuario WHERE username ='" + username + "' and password='" + password + "'");
			ResultSet rs = stmt.executeQuery();

			while (rs.next())

				role = rs.getString(1);
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return role;
	}

	/**
	 * Este método sirve para registrar nuevos usuarios, por defecto como usuarios normales
	 * @param username recibe el nombre de usuario
	 * @param password recibe la contraseña
	 * @param fullname recibe el nombre completo del usuario
	 * @param fechanac recibe la fecha de nacimiento del usuario
	 * @param role recibe el role que se le va a dar al usuario aunque por defecto es usuario normal
	 * @return devuelve true si lo consigue crear y false si no
	 */
	public static boolean createUser(String username, String password, String fullname, String fechanac, String role) {
		boolean insert = false;
		Connection c = ConnectionDB.conectarMySQL();
		try {
			PreparedStatement stmt = c
					.prepareStatement("INSERT INTO usuario (username,password,fullname, dateOfBirth,role ) Values('"
							+ username + "','" + password + "','" + fullname + "','" + fechanac + "','" + role + "')");
			stmt.executeUpdate();
			insert=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return insert;
	}

	/**
	 * Este método borra un usuario de la base de datos
	 * @param idusuario recibe la ID del usuario que se va a borrar
	 * @return devuelve true si lo consigue borrar y false si no
	 */
	public static boolean deleteUser(Integer idusuario) {
		boolean delete = false;
		Connection c = ConnectionDB.conectarMySQL();
		try {
			PreparedStatement stmt = c.prepareStatement("Delete from usuario where id =" + idusuario);
			stmt.executeUpdate();
			delete=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return delete;

	}

	/**
	 * Este método lee todos los usuarios de la base de datos
	 * @return List<User> Devuelve una lista con todos los usuarios
	 */
	public static List<User> readUser() {

		List<User> usuarios = null;
		try {
			Connection con = ConnectionDB.conectarMySQL();
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery("select * from usuario");
			usuarios = new ArrayList<User>();

			while (rs.next())
				usuarios.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getString(6)));

			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return usuarios;
	}

	/**
	 * Este método cambia el rol de los usuarios, puede cambiar de admin a user y de user a admin
	 * @param user_id recibe el ID del usuario al que se le va a cambiar el rol
	 * @param roleantiguo recibe el antiguo rol que tenía el usuario
	 * @return devuelve una cadena con el valor del nuevo rol que tiene el usuario
	 */
	public static String updateRole(Integer user_id, String roleantiguo) {
		String newRole = null;
		Connection c = ConnectionDB.conectarMySQL();
	    try {
	        if(roleantiguo.equals("user")){
	            PreparedStatement stmt = c.prepareStatement("UPDATE usuario SET role= 'admin' WHERE id=" + user_id + "");
	        stmt.executeUpdate();
	        newRole = "admin";
	        }else {
	            PreparedStatement stmt = c.prepareStatement("UPDATE usuario SET role= 'user' WHERE id=" + user_id + "");
	        stmt.executeUpdate();
	        newRole = "user";
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return newRole;
	}
	
}
