package ar.com.codoacodo.repository;

import java.sql.Connection;
import java.sql.DriverManager;

public class AdministradorDeConexiones {

	// tener un metodo estatico: un metodo que se puede usar sin crear una instancia de la clase
	// AdministradorDeConexiones.metodo()
	public static Connection getConnection() {
		String username = "root";
		String password = "Dan41019755Dan";
		String port = "3306";
		String host = "localhost";
		String dbName = "integrador_cac";
		
		String driverName = "com.mysql.cj.jdbc.Driver";
		
		//String dbUrl = "jdbc:mysql://"+host+":"+port+"/"+dbName + "?allowPublicKeyRetrieval=true&serverTimeZone=UTC&useSSL=false";  
		//String dbUrl = "jdbc:mysql://"+host+":"+port+"/"+dbName + "?serverTimeZone=UTC&useSSL=false";
		String dbUrl = "jdbc:mysql://"+host+":"+port+"/"+dbName + "?allowPublicKeyRetrieval=true&serverTimeZone=UTC&useSSL=false";
			
		try {
			Class.forName(driverName);
			return DriverManager.getConnection(dbUrl, username, password);
		}catch(Exception e) {
			throw new IllegalArgumentException("No se pudo conectar a la db: " + dbUrl);
		}
		
	}
	
}
