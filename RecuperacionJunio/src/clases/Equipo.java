package clases;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conector.ConnectionDB;

public class Equipo {

	private String nombre,ciudad,pais;
	private int id;
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
	public Equipo( int id,String nombre, String ciudad, String pais) {
		super();
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.pais = pais;
		this.id = id;
	}
	@Override
	public String toString() {
		return "Equipo [nombre=" + nombre + ", ciudad=" + ciudad + ", pais=" + pais + ", id=" + id + "]";
	}
	
	public static List<Equipo> readEquipo() {

        List<Equipo> equipos= null;
        try {
            Connection con = ConnectionDB.conectarMySQL();
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("select * from equipo");
            equipos= new ArrayList<Equipo>();

            while (rs.next())
                equipos.add(new Equipo(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4)));

            rs.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return equipos;
    }
}
