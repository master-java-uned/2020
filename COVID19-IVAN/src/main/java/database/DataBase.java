package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * 
 * Clase encargada de hacer el puente con la base de datos.
 * 
 * @author @kalua66
 *
 */
public class DataBase 
{
	/**
	 * La conexion de la base de datos, la declaramos statica, para que solo haya una instancia, utilizando Singleton.
	 */
	private static Connection connection = null;
	
	
	/**
	 * Con esta variable permito cambiar entre la ruta de 
	 * host de la base de datos por la localhost y la linkada.
	 */
	private static boolean runInDocker = true;
	
	/**
	 * Abrimos la conexion con la base de datos, o nos devuelve la conexion en caso de ya estar abierta.
	 * @return la conexion
	 * @throws SQLException
	 */
	private static Connection openConnection() throws SQLException
	{

		if(connection == null || connection.isClosed()) 
		{
			try
			{
				String base = "jdbc:mysql";
				String host = "" ;
				int port = 3306;
				String schema = "covid";
				String user = "coviduser";
				String password = "123456";
				String driver = "com.mysql.cj.jdbc.Driver";
				String param = "?allowPublicKeyRetrieval=true&useSSL=false";

				String url = "";
				
				if(runInDocker) 
				{
					host = "db" +":" + port;
					url = base +"://"  + host + "/" + schema + param;
				}
				else 
				{
					host = "localhost" ;
					url = base +"://"  + host +":" + port+ "/" + schema + param;
				}
				
				System.out.println("url: " + url);
				
		
				Class.forName(driver);

				connection = DriverManager.getConnection(url , user, password);	
			} 
			catch (SQLException e)
			{
				System.out.println("Error al abrir la conexion: " + e.getMessage());
			}
			catch (Exception e)
			{
				System.out.println("Error al abrir la conexion: " + e.getMessage());
			}
		}
		return connection;
	}
	
	/**
	 * Cerramos la conexion con la base de datos.
	 * @throws SQLException 
	 */
	public static void closeConnection() throws SQLException
	{
		if(connection != null || !connection.isClosed()) 
		{
			connection.close();
			connection = null;
		}
	}
	
	/**
	 * @return La conexion con la base de datos, sino esta abierta, la abre.
	 * @throws SQLException 
	 */
	public static Connection getConnection() throws SQLException 
	{
		if(connection == null || connection.isClosed()) 
		{
			return openConnection();
		}
		else 
		{
			return connection;
		}
	}
}