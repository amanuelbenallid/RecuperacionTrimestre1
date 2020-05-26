package org.iesalixar.amanuelbenallid.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.iesalixar.amanuelbenallid.controller.Login;
import org.iesalixar.amanuelbenallid.helper.ConnectionDB;
import org.iesalixar.amanuelbenallid.model.Team;
/**
 * Clase que tiene todos los métodos para el CRUD de los equipos
 * @author Alberto
 *
 */
public class TeamDAO {
	final static Logger logger = Logger.getLogger(Login.class);

	/**
	 * Este método lee todos los equipos que hay en la base de datos
	 * 
	 * @return List<Team> Devuelve una lista con todos los Equipos.
	 */
	public static List<Team> readTeam() {

		List<Team> equipos = null;
		try {
			ConnectionDB pool = new ConnectionDB();
			Connection con = null;
			con = pool.dataSource.getConnection();		
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery("select * from equipo");
			equipos = new ArrayList<Team>();

			while (rs.next())
				equipos.add(new Team(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));

			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return equipos;
	}

	/**
	 * Este método borra el equipo que se le pasa por parametros la ID, y pone como
	 * nulo el equipo de los jugadores que tenían el quipo eliminado.
	 * 
	 * @param idequipo recibe la ID del equipo que se va a borrar
	 * @return devuelve true si lo consigue borrar y false si no
	 */
	public static boolean deleteTeam(Integer idequipo) {
		boolean delete = false;

		try {
			ConnectionDB pool = new ConnectionDB();
			Connection con = null;
			con = pool.dataSource.getConnection();		
			PreparedStatement stm = con.prepareStatement("update jugador set equipo =null where equipo=" + idequipo);
			stm.executeUpdate();
			logger.info("Los jugadores que tenían el equipo " + idequipo + " ahora no tienen equipo");
			PreparedStatement stmt = con.prepareStatement("Delete from equipo where id =" + idequipo);
			stmt.executeUpdate();
			delete = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return delete;
	}

	/**
	 * Este método actualiza cualquier campo de los equipos, menos la ID
	 * 
	 * @param id Recibe la ID del equipo que se va a actualizar
	 * @param nombre recibe el nombre nuevo o el mismo que tenía en caso de que no se haya cambiado
	 * @param ciudad recibe la ciudad nueva o la misma en caso de que no se haya cambiado
	 * @param pais recibe el país nuevo o el mismo en caso de que no haya cambiado
	 * @return devuelve true si lo consigue actualizar y false si no
	 */
	public static boolean updateTeam(Integer id, String nombre, String ciudad, String pais) {
		boolean update = false;
		
		try {
			ConnectionDB pool = new ConnectionDB();
			Connection con = null;
			con = pool.dataSource.getConnection();		
			PreparedStatement stmt = con.prepareStatement("UPDATE equipo SET nombre='" + nombre + "', ciudad='" + ciudad
					+ "', pais='" + pais + "' WHERE id=" + id);
			stmt.executeUpdate();
			update = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return update;
	}

	/**
	 * Este método sirve para crear un nuevo equipo
	 * @param nombre recibe el nombre del equipo a crear 
	 * @param ciudad recibe la ciudad del equipo que va a crear
	 * @param pais recibe el pais del equipo que va a crear
	 * @return devuelve true si lo consigue crear y false si no
	 */
	public static boolean createTeam(String nombre, String ciudad, String pais) {
		boolean insert = false;
		try {
			ConnectionDB pool = new ConnectionDB();
			Connection con = null;
			con = pool.dataSource.getConnection();		
			PreparedStatement stmt = con.prepareStatement("INSERT INTO equipo (nombre , ciudad ,pais ) Values('" + nombre
					+ "','" + ciudad + "','" + pais + "')");
			stmt.executeUpdate();
			insert = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return insert;
	}
}
