package com.usuarios.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.usuarios.model.Conexion;
import com.usuarios.model.usuarios;

public class UsuariosDao {

	private Conexion con;
	private Connection connection;
 
	public UsuariosDao(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException {
		System.out.println(jdbcURL);
		con = new Conexion(jdbcURL, jdbcUsername, jdbcPassword);
	}
	
	public boolean login (usuarios usuario) throws SQLException {
		ResultSet resulSet=null;
		String sql =
	               "select * from usuarios where userid='"
	                        + usuario.getUserid()
	                        + "' AND password='"
	                        + usuario.getPassword()
	                        + "'";
		con.conectar();
		connection = con.getJdbcConnection();
		Statement statement = connection.createStatement();
		resulSet = statement.executeQuery(sql);
		if(resulSet.next()) {
			statement.close();
			con.desconectar();
			return true;
		}
		else {
			statement.close();
			con.desconectar();
			return false;
		}
		
	}
	
	// insertar artículo
	public boolean insertar(usuarios usuario) throws SQLException {
		String sql = "INSERT INTO usuarios (id, nombre, password, userid) VALUES (?,?,?,?)";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, null);
		statement.setString(2, usuario.getNombre());
		statement.setString(3, usuario.getPassword());
		statement.setString(4, usuario.getUserid());
		boolean rowInserted = statement.executeUpdate() > 0;
		
		statement.close();
		con.desconectar();
		return rowInserted;
	}
	
	// listar todos los usuarios
	public List<usuarios> listarUsuarios() throws SQLException {
 
		List<usuarios> listausuarios = new ArrayList<usuarios>();
		String sql = "SELECT * FROM usuarios";
		con.conectar();
		connection = con.getJdbcConnection();
		Statement statement = connection.createStatement();
		ResultSet resulSet = statement.executeQuery(sql);
 
		while (resulSet.next()) {
			int id = resulSet.getInt("id");
			String nombre = resulSet.getString("nombre");
			String password = resulSet.getString("password");
			String userid = resulSet.getString("userid");
			usuarios user = new usuarios (id, nombre, password, userid);
			
			listausuarios.add(user);
		}
		con.desconectar();
		return listausuarios;
	}
	
	// obtener por id
		public usuarios obtenerPorId(int id) throws SQLException {
			
			usuarios user = null;
	 
			String sql = "SELECT * FROM usuarios WHERE id= ? ";
			con.conectar();
			connection = con.getJdbcConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
	 
			ResultSet res = statement.executeQuery();
			if (res.next()) {
				
				user = new usuarios(res.getInt("id"), res.getString("nombre"), res.getString("password"), res.getString("userid"));
				
			}
			res.close();
			con.desconectar();
	 
			return user;
		}
	
		
		// actualizar
		public boolean actualizar(usuarios user) throws SQLException {
			boolean rowActualizar = false;
			String sql = "UPDATE usuarios SET nombre=?, password=?, userid=? WHERE id=?";
			con.conectar();
			connection = con.getJdbcConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, user.getNombre());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getUserid());
			statement.setInt(4, user.getId());
			
	 
			rowActualizar = statement.executeUpdate() > 0;
			statement.close();
			con.desconectar();
			return rowActualizar;
		}
		
		//eliminar
		public boolean eliminar(usuarios user) throws SQLException {
			boolean rowEliminar = false;
			String sql = "DELETE FROM usuarios WHERE ID=?";
			con.conectar();
			connection = con.getJdbcConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, user.getId());
			
	 
			rowEliminar = statement.executeUpdate() > 0;
			statement.close();
			con.desconectar();
	 
			return rowEliminar;
		}
}
