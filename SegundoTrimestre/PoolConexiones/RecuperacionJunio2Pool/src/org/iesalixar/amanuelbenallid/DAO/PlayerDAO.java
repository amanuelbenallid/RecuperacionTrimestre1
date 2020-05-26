package org.iesalixar.amanuelbenallid.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.iesalixar.amanuelbenallid.helper.ConnectionDB;
import org.iesalixar.amanuelbenallid.model.Player;
/**
 * Clase que tiene todos los métodos para el CRUD de los jugadores
 * @author Alberto
 *
 */
public class PlayerDAO {
	/**
	 * Este método lee todos los jugadores de la base de datos
	 * @return List<Player> Devuelve una lista que contiene los jugadores
	 */
	public static List<Player> readPlayer() {

		List<Player> jugadores = null;
		try {
			
			ConnectionDB pool = new ConnectionDB();
			Connection con = null;
			con = pool.dataSource.getConnection();

			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery("select * from jugador");
			jugadores = new ArrayList<Player>();

			while (rs.next())
				jugadores.add(new Player(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getInt(6)));

			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jugadores;
	}

	/**
	 * Este método sirve para crear un nuevo jugador
	 * @param nombre recibe el nombre del jugador
	 * @param apellidos recibe los apellidos del jugador
	 * @param dni recibe el DNI del jugador
	 * @param direccion recibe la dirección del jugador
	 * @param equipo recibe la ID del equipo al que pertenece el jugador
	 * @return devuelve true si lo consigue crear y false si no
	 */
	public static boolean createPlayer(String nombre, String apellidos, String dni, String direccion, Integer equipo) {
		boolean insert=false;
		
		try {
			ConnectionDB pool = new ConnectionDB();
			Connection con = null;
			con = pool.dataSource.getConnection();
			PreparedStatement stmt = con
					.prepareStatement("INSERT INTO jugador (nombre , apellidos ,dni, direccion, equipo ) Values('"
							+ nombre + "','" + apellidos + "','" + dni + "','" + direccion + "'," + equipo + ")");
			stmt.executeUpdate();
			insert=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return insert;

	}

	/**
	 * Este método cambia el equipo en el que juega un jugador
	 * @param idjugador recibe la ID del jugador
	 * @param equiponuevo recibe la ID del equipo que se le quiere poner al jugador
	 * @return devuelve true si lo consigue actualizar y false si no
	 */
	public static boolean updatePlayer(Integer idjugador, Integer equiponuevo) {
		boolean update = false;
		
		try {
			ConnectionDB pool = new ConnectionDB();
			Connection con = null;
			con = pool.dataSource.getConnection();
			PreparedStatement stmt = con.prepareStatement("UPDATE jugador SET equipo=" +equiponuevo + " WHERE id=" + idjugador + "");
			stmt.executeUpdate();
			update =true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return update;

	}

	/**
	 * Este método borra un jugador que se le pasa por parámetros la ID
	 * @param idjugador recibe la ID del jugador que se va a borrar
	 * @return devuelve true si lo consigue borrar y false si no
	 */
	public static boolean deletePlayer(Integer idjugador) {
		boolean delete = false;
		
		try {
			ConnectionDB pool = new ConnectionDB();
			Connection con = null;
			con = pool.dataSource.getConnection();		
			PreparedStatement stmt = con
					.prepareStatement("Delete from jugador where id ="+idjugador);
			stmt.executeUpdate();
			delete=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return delete;
	}
}
