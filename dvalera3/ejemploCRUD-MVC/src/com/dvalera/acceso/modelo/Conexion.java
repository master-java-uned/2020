package com.dvalera.acceso.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * @autor: Diego Valera Hernandez
 * @email: d.valera3@hotmail.com
 * 
 * Implementatiòn class Conexiòn
 */

public class Conexion {
    private Connection jdbcConnection=null;
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    
    /**
     * Constructor class Conexiòn
     * @param jdbcURL
     * @param jdbcUsername
     * @param jdbcPassword
     */
    public Conexion(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}
   
	public void conectar() throws SQLException {
		 
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {        	                
            	Class.forName("com.mysql.cj.jdbc.Driver");            
                jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);               
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
        }
    }
     
    public void desconectar() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }

	public Connection getJdbcConnection() {
		return jdbcConnection;
	}  

}
