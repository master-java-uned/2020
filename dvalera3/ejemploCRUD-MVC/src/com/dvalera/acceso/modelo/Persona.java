package com.dvalera.acceso.modelo;

/**
 * 
 * @autor: Diego Valera Hernandez
 * implementation class Persona
 * 
 */
public class Persona {
	private int id;
	private String nombre;
	private String apellidos;
	private String email;
	private String usuario;
	private String password;
	private String sesion;
	 	
	/**
	 * 
	 * Constructor class Persona todos sus atributos
	 * 
	 * @param id
	 * @param nombre
	 * @param apellidos
	 * @param emailusuario
	 * @param nombreusuario
	 * @param clave
	 * @param sesion
	 */
	public Persona(int id,String nombre, String apellidos, String emailusuario, String nombreusuario, String clave, String sesion) {
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = emailusuario;
		this.usuario = nombreusuario;
		this.password = clave;
		this.sesion = sesion;
	}
	
	/**
	 * 
	 * Constructor class Persona con cuatro atributos
	 * 
	 * @param nombre
	 * @param apellidos
	 * @param emailusuario
	 * @param sesion
	 */
	public Persona(String nombre, String apellidos, String emailusuario,String sesion) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = emailusuario;
		this.sesion = sesion;
	}
	 
	//getters y setters
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
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}	
    public String getEmail() {
	    return email;
	}
    public void setEmailUsuario(String emailusuario) {
	    this.email = emailusuario;
	}	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String nombreusuario) {
		this.usuario = nombreusuario;
	}
	public String getClave() {
		return password;
	}
	public void setClave(String clave) {
		this.password = clave;
	}	
	public String getSesion() {
		return sesion;
	}
	public void setSesion(String s) {
		this.sesion = s;
	}	 
}
