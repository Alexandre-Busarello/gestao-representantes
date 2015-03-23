package application.controle.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
    // strings de conexão
    private String databaseURL = "jdbc:postgresql://localhost/representante";
    private String usuario = "adm";
    private String senha = "123456";
    private  String driverName = "org.postgresql.Driver";	
	
    public Connection getConnection() {
    	Connection c = null;
    	try {
        	Class.forName(driverName).newInstance();
            c = DriverManager.getConnection(databaseURL, usuario, senha);
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } catch (Exception e) {
        	System.out.println("Problemas ao tentar conectar com o banco de dados: " + e);
        }
		return c;
    }
    
}
