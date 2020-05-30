package tomas.es;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.cj.jdbc.Driver;
/**
 *
 * @author 
 */
public class Conexion {
    private static Connection cn = null;
    private static String URL = "jdbc:mysql://localhost:3306/practica?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static String usuario = "root";
    private static String contrasena = "root";
   
    public static Connection getConexion() throws SQLException,ClassNotFoundException, InstantiationException, IllegalAccessException {
    	try {
			Class clase=Class.forName("com.mysql.cj.jdbc.Driver");
			Driver driver = (Driver) clase.newInstance();
			DriverManager.registerDriver(driver);
			System.out.println("register activado");
			 cn = DriverManager.getConnection(URL, usuario, contrasena);
			 System.out.println("el valor de cn es " +cn);
		} catch ( SQLException|ClassNotFoundException| InstantiationException| IllegalAccessException e) {
			// TODO Auto-generated catch block
			System.out.println(" error producido");
			e.printStackTrace();
		}
    	finally {
    		return cn;
    	}
        
        
    }
}