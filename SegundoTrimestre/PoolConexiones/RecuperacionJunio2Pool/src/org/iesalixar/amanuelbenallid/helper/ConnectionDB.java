package org.iesalixar.amanuelbenallid.helper;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

/**
 * Clase que usa las properties para conectarse a la base de datos
 * 
 * @author Alberto
 *
 */
public class ConnectionDB {

	public DataSource 	dataSource;
	
	public ConnectionDB(){
		initDataSource();
		
	}

	private void initDataSource() {
		
		Properties properties = new Properties();
		try {
			properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("conf.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		BasicDataSource basicDataSource = new BasicDataSource();
		
		basicDataSource.setDriverClassName(	properties.getProperty("db.classForName"));
		
		basicDataSource.setUsername(		properties.getProperty("db.user"));
		basicDataSource.setPassword(		properties.getProperty("db.pass"));
		
		String url = properties.getProperty("db.url") + "//" + 
					 properties.getProperty("db.host") + ":" + 
					 properties.getProperty("db.port") + "/" + 
					 properties.getProperty("db.dbname") + "?useSSL=false&serverTimezone=UTC";
		
		basicDataSource.setUrl(url);
		basicDataSource.setMaxTotal(50);
		
		dataSource = basicDataSource;						
		
	}



}
