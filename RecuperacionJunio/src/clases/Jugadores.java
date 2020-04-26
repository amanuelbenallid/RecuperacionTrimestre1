package clases;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conector.ConnectionDB;

public class Jugadores {

	private int equipo,id;
	private String nombre,apellidos,dni,direccion;
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
	public Jugadores( int id, String nombre, String apellidos, String dni, String direccion,int equipo) {
		super();
		this.equipo = equipo;
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.direccion = direccion;
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
	public static List<Jugadores> readJugadores() {

        List<Jugadores> jugadores= null;
        try {
            Connection con = ConnectionDB.conectarMySQL();
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("select * from jugador");
            jugadores= new ArrayList<Jugadores>();

            while (rs.next())
                jugadores.add(new Jugadores(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6)));

            rs.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return jugadores;
    }
}
