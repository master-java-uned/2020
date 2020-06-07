package beans.form;

import beans.user.User;


/**
 * Clase que utilizo para retornar es estado de una operacion 
 * que afectar al usuario, como Login, Registro, Validacion
 * 
 * @author @kalua66
 *
 */
public class UserStatus 
{
	/**
	 * El objeto usuario
	 */
	private User user;
	
	/**
	 * El estado como codigo ("ERROR_LOGIN", "LOGIN_OK"...)
	 */
	private String status;
	
	/**
	 * La descripcion o el dato que complementa el status
	 */
	private String data;
	
	/**
	 * 
	 * Constructor de la clase, pasando el usuario, y el estado.
	 * 
	 * @param user El usuario
	 * @param status El estado
	 */
	public UserStatus(User user, String status)
	{
		this.user = user;
		this.status = status;
		this.data = "";
	}

	/**
	 * 
	 * Constructor completo de la clase, pasando el usuario, el estado, y la descripcion de dicho estado.
	 * 
	 * @param user El usuario
	 * @param status El estado
	 * @param data La descripcion
	 */
	public UserStatus(User user, String status, String data)
	{
		this.user = user;
		this.status = status;
		this.data = data;
	}
	
	/**
	 * @return La descripcion
	 */
	public String getData()
	{
		return data;
	}

	/**
	 * @param data Establece la descripcion
	 */
	public void setData(String data)
	{
		this.data = data;
	}

	/**
	 * @return El Usuario
	 */
	public User getUser()
	{
		return user;
	}

	/**
	 * @return El estado
	 */
	public String getStatus()
	{
		return status;
	}
}