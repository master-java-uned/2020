package com.dvalera.acceso.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.dvalera.acceso.modelo.Conexion;
import com.dvalera.acceso.modelo.Persona;

/*
 * @autor: Diego Valera Hernàndez
 * @email: www.d.valera3@hotmail.com
 * 
 * Implementatiòn class BaseDatos
 */
 
public class BaseDatos {
	private Conexion conexion;
	private Connection connection;
 
	/**
	 * constructor BaseDatos
	 * @param jdbcURL
	 * @param jdbcUsuario
	 * @param jdbcClave
	 * @throws SQLException
	 */
	public BaseDatos(String jdbcURL, String jdbcUsuario, String jdbcClave) throws SQLException {
		conexion = new Conexion(jdbcURL, jdbcUsuario, jdbcClave);
	}
 
	/**
	 * mètodo insertarUsuario crea un usuario en la base de datos a partir 
	 * del objeto Usuario recibido
	 * @param usuario
	 * @return
	 * @throws SQLException
	 */
	
	public boolean insertarUsuario(Persona persona) throws SQLException {
		PreparedStatement pstm = null;       
		boolean nuevo = false;
	    boolean rowInserted=false;
	    try {
		      conexion.conectar();
		      connection = conexion.getJdbcConnection();
		      String sql = "INSERT INTO persona VALUES (?, ?, ?, ?, ?, ?, ?)";
		      pstm = connection.prepareStatement(sql);	
		      pstm.setInt(1,persona.getId());
		      pstm.setString(2,persona.getNombre());
		      pstm.setString(3,persona.getApellidos());
		      pstm.setString(4,persona.getEmail());
		      pstm.setString(5,persona.getUsuario());
		      pstm.setString(6,persona.getClave());    
		      pstm.setString(7,persona.getSesion()); 
		      
		      nuevo = pstm.executeUpdate() > 0; 	
		      pstm.close();
		      conexion.desconectar();
		      
	          int rows = pstm.executeUpdate();//registros afectados
	          System.out.println("Registros agregados: " + rowInserted+" "+ rows+" ");		        
		        
		}catch (SQLException e) {
		            e.printStackTrace();	     
		}catch (Exception e2) {
		            e2.printStackTrace();
		}
		   
	   return nuevo;				
	}
	
    /**
     * mètodo listaNombreUsuarios
     * crea un ArrayList<Persona> llamado listaUsuarios que contiene 
	 * los nombres y apellidos y email de todos los usuarios que hay en la base de datos
	 * @return List<String> listaUsuarios
	 * @throws SQLException
	 */
	public List<Persona> listaNombreUsuarios() throws SQLException {	 
		String nombre = null;
		String apellidos = null;
		String email = null;
		String sesion = null;
		conexion.conectar();
		connection = conexion.getJdbcConnection();
		String sql = "SELECT nombre,apellidos,email,sesion FROM persona";		 
		Statement statement = connection.createStatement();
		ResultSet resulSet = statement.executeQuery(sql);		
		List<Persona> listaUsuarios = new ArrayList<Persona>();
		while (resulSet.next()) {
			 
			nombre = resulSet.getString("nombre");
			apellidos = resulSet.getString("apellidos");
			email = resulSet.getString("email");
			sesion = resulSet.getString("sesion");
			
			Persona persona = new Persona(nombre,apellidos,email,sesion);
			listaUsuarios.add(persona);
		}
		
	    return listaUsuarios;
	}
	
	/** 
 	 * @param t indica usuario en sesion(t==true)
 	 * @return List<Persona> es una lista con todos los usuarios en sesion 
 	 * @throws SQLException
 	 */
 	public List<Persona> usuariosSesion() throws SQLException {
		String nombre = null;
		String apellidos = null;
		String email = null;
		String sesion = null;
		conexion.conectar();
		connection = conexion.getJdbcConnection();
		String sql = "SELECT nombre,apellidos,email,sesion FROM persona";		 
		Statement statement = connection.createStatement();
		ResultSet resulSet = statement.executeQuery(sql);		
		List<Persona> listaUsuarios = new ArrayList<Persona>();
		while (resulSet.next()) {	
			sesion = resulSet.getString("sesion");
			if(sesion.equals("t")) {
			   nombre = resulSet.getString("nombre");
			   apellidos = resulSet.getString("apellidos");
			   email = resulSet.getString("email");	
			   Persona persona = new Persona(nombre,apellidos,email,sesion);
			   listaUsuarios.add(persona);
			}
		}
		
	    return listaUsuarios;
	}
	   
    /**
     * mètodo emailRegistrado comprueba si el usuario que està
     * logenadose, està utilizando un email registrado a su nombre 
     * devuelve un boolean indicandolo
     * @param String clave
	 * @return boolean existe
	 * @throws SQLException
	*/
    public boolean emailRegistrado(String email) throws SQLException {
    	boolean existe = false;
        try {
		   String sql = "SELECT * FROM persona WHERE email = '"+email+"' ";
		   conexion.conectar();
		   connection = conexion.getJdbcConnection();
		   PreparedStatement stm = connection.prepareStatement(sql);
		   ResultSet res = stm.executeQuery();
		   existe = res.next();		 	    
	       res.close();
		   conexion.desconectar();
        }catch(Exception ex) {
		   System.out.println(ex);
	    }

		return existe;
	}
      
    /**
     * mètodo passwordRegistrado comprueba si el password esta registrado,
     * devuelve un boolean indicandolo
     * @param String emailusuario es el email a comprobar si està registrado a algùn usuario registrado
	 * @return boolean existe
	 * @throws SQLException
	*/
    public boolean passwordRegistrado(String password) throws SQLException {
    	boolean existe = false;
        try {
		   String sql = "SELECT * FROM persona WHERE password = '"+password+"' ";
		   conexion.conectar();
		   connection = conexion.getJdbcConnection();
		   PreparedStatement stm = connection.prepareStatement(sql);
		   ResultSet res = stm.executeQuery();
		   existe = res.next();		 	    
	       res.close();
		   conexion.desconectar();
        }catch(Exception ex) {
		   System.out.println(ex);
	    }

		return existe;
	}
    
    /**
     * metodo para desconectar a un usuario de la sesion
     * @param nombreusuario
     * @return salir de tipo booelano, indoica si ha habido exito o no
     * @throws SQLException
    */
    public boolean sesionActivar(Persona persona, String email) throws SQLException {
    	boolean activar = false; 
    	conexion.conectar();
		connection = conexion.getJdbcConnection();
	    String sql = "UPDATE persona SET id=?, nombre=?, apellidos=?, email=?, usuario=?, password=?, sesion=? WHERE email='"+email+"' "; 
		PreparedStatement pstm = connection.prepareStatement(sql);
		pstm.setInt(1,persona.getId());
	    pstm.setString(2,persona.getNombre());
	    pstm.setString(3,persona.getApellidos());
	    pstm.setString(4,persona.getEmail());
	    pstm.setString(5,persona.getUsuario());
	    pstm.setString(6,persona.getClave());    
	    pstm.setString(7,persona.getSesion()); //activo sesion
		activar =  pstm.executeUpdate() > 0;
	    pstm.close();
	    conexion.desconectar();
	    return activar;
    }
    
    /**
	 * metodo para desconectar a un usuario de la sesion
	 * @param persona
	 * @param nombreusuario
	 * @return salir de tipo booelano, indica si ha habido exito 
	 * @throws SQLException
     */
    public boolean sesionDesactivar(Persona persona, String email) throws SQLException {
    	boolean activar = false; 
    	persona.setSesion("f"); 
    	conexion.conectar();
		connection = conexion.getJdbcConnection();
	    String sql = "UPDATE persona SET id=?, nombre=?, apellidos=?, email=?, usuario=?, password=?, sesion=? WHERE email='"+email+"' "; 
		PreparedStatement pstm = connection.prepareStatement(sql);
		pstm.setInt(1,persona.getId());
	    pstm.setString(2,persona.getNombre());
	    pstm.setString(3,persona.getApellidos());
	    pstm.setString(4,persona.getEmail());
	    pstm.setString(5,persona.getUsuario());
	    pstm.setString(6,persona.getClave());    
	    pstm.setString(7,persona.getSesion()); //activo sesion
		activar =  pstm.executeUpdate() > 0;
	    pstm.close();
	    conexion.desconectar();
	    return activar;
    }
    
    /**
     * mètodo obtenerPorEmail recibe el email de usuario y busca 
     * si existe, si es cierto devuelve un objeto del tipo Persona
     * @param String nombreusuario
     * @return usuario
     * @throws SQLException
	*/
 	public Persona obtenerPorEmail(String email) throws SQLException {
 		Persona usuario = null;
 		try {
		  conexion.conectar();
		  connection = conexion.getJdbcConnection();
		  Statement stm = connection.createStatement();
		  String sql = "SELECT * FROM persona WHERE email= '"+email+"' ";
		  ResultSet res = stm.executeQuery(sql);
		  if (res.next()) {
 			usuario = new Persona(res.getInt("id"), res.getString("nombre"), res.getString("apellidos"), res.getString("email"), res.getString("usuario"), res.getString("password"),res.getString("sesion"));
 		  }
		  res.close();
		  conexion.desconectar();
		 
		}catch(Exception ex) {
		  System.out.println(ex);
		}
 		  
 		return usuario;
 	}
 		 			 
 	/**
     * mètodo eliminarUsuario recibe el email de usuario si
     * esta registrado lo elimina por completo
     * devolviendo un boolean si ha sido exitosa la acciòn 
     * @param String email
     * @return boolean eliminar
     * @throws SQLException
	*/
	public boolean eliminarUsuario(String email) throws SQLException {
		boolean eliminar = false;
		String sql = "DELETE FROM persona WHERE email='"+email+"' ";
		conexion.conectar();
		connection = conexion.getJdbcConnection();
		Statement stm = connection.createStatement();
		eliminar = stm.executeUpdate(sql) > 0;
		stm.close();
		conexion.desconectar();
 
		return eliminar;
	} 
	
}	


