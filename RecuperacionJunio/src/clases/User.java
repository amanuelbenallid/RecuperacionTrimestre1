package clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.apache.log4j.Logger;
import conector.ConnectionDB;


public class User {
	final static Logger logger = Logger.getLogger(User.class);

	private String username,password,fullname,role;
	private Integer user_id;
	private Date fechanac =new Date();

	public User(Integer user_id,String username ,String password,String fullname ,String role,Date fechanac) {
		this.user_id=user_id;
		this.username=username;
		this.password=password;
		this.fullname=fullname;
		this.role=role;
		this.fechanac=fechanac;
		
		
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
	public static boolean checkUser(String username, String password) {
		boolean found = false;
		Connection c = ConnectionDB.conectarMySQL();
		try {
			PreparedStatement stmt = c.prepareStatement("select username, password from usuario where username=? and password=?");
			stmt.setString(1, username);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			found = rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return found;
	}
	public static String checkRole(String username ,String password) {
		String role=null;
		Connection c = ConnectionDB.conectarMySQL();
		try {
			PreparedStatement stmt = c.prepareStatement("Select role FROM usuario WHERE username ='"+username+"' and password='"+password+"'");  
			ResultSet rs=stmt.executeQuery();
			
			while(rs.next())
			
				role=rs.getString(1);
			rs.close();
				
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	  
		return role;
	}
	
	public static void createuser(String username,String password ,String fullname ,String email,String role) {
		
		Connection c = ConnectionDB.conectarMySQL();
		try {
			PreparedStatement stmt = c.prepareStatement("INSERT INTO user (username , password ,fullname, email, role ) Values('"+username+"','"+password+"','"+ fullname +"','"+email+"','"+role+"')");  
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static boolean autenticar(Integer user_id ) {
		boolean comprado=false;
		Connection c = ConnectionDB.conectarMySQL();
		try {
			PreparedStatement stmt = c.prepareStatement("Update user Set userstate=(userstate+1) where user_id="+user_id);  
			
			stmt.executeUpdate();
			comprado=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return comprado;
	}

}
