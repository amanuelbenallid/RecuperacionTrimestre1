package conector;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionDB {
	private static Connection conexion = null;

	private ConnectionDB() {
		try {
			if (conexion == null) {
				Properties prop = new Properties();
				try {
					prop.load(Thread.currentThread(). 
								getContextClassLoader().
									getResourceAsStream("conf.properties"));
					Class.forName(prop.getProperty("server.driver"));
					conexion = DriverManager.getConnection(prop.getProperty("server.url"),
									prop.getProperty("server.user"), 
										prop.getProperty("server.pass"));
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public static Connection conectarMySQL() {
		if (conexion == null) {
			new ConnectionDB();
		}

		return conexion;
	}

}
