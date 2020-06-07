package com.usuarios.model;

public class usuarios {

	private int id;
	private String nombre;
	private String password;
	private String userid;
	private boolean valido=false;
	
	
	
	public boolean isValido() {
		return valido;
	}
	public void setValido(boolean valido) {
		this.valido = valido;
	}
	
	public usuarios() {
		
	}
	
	public usuarios (int id, String nombre, String password, String userid) {
		this.id = id;
		this.nombre = nombre;
		this.password = password;
		this.userid = userid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	
	
}
