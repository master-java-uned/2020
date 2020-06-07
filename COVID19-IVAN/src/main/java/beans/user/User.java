package beans.user;


/**
 * Clase que contendra los datos del usuario.
 * 
 * @author @kalua66
 *
 */
public class User 
{
	/**
	 *  Identificador del usuario.
	 */
	private int id;
	
	/**
	 *  Nombre del usuario.
	 */
	private String name;
	
	/**
	 *  Email del usuario.
	 */
	private String email;
	
	/**
	 *  Codigo de validacion del usuario.
	 */
	private String validation;
	
	/**
	 *  Un enum con el nivel de acceso que tiene.
	 */
	private Access level;

	
	/**
	 * El constructor de la clase con 2 parametros, este caso es llamado cuando sabemos que 
	 * solo tiene acceso de cliente en cual solo debemos saber su ID y su nombre de usuario.
	 * 
	 * @param id El identificador del usuario.
	 * @param name El nombre del usuario.
	 */
	public User(int id, String name) 
	{
		this.id = id;
		this.name = name;
		this.level = Access.CLIENT;
	}
	
	/**
	 * El constructor de la clase con 3 parametros, este caso le dejamos tambien 
	 * aparte del ID y nombre establecer el nivel de acceso mediante el parametro level.
	 * 
	 * @param id El identificador del usuario.
	 * @param name El nombre del usuario.
	 * @param level El nivel de acceso que tiene el usuario, siendo: 
	 *  CLIENTE->0 para ver la web sin poder administar nada.
	 *  ADMIN->1 donde puede administar.
	 */
	public User(int id, String name, Access level) 
	{
		this.id = id;
		this.name = name;
		this.level = level;
	}
	
	/**
	 * 
	 * El constructor de la clase con todos los parametros de las clase. 
	 * Para tener un control total del ella.
	 * 
	 * @param id El identificador del usuario.
	 * @param name El nombre del usuario.
	 * @param email El email del usuario.
	 * @param validation La validacion, es un token que contiene la BD, y que es mandando
	 *  por email al usuario, y se debe comprobar que coinciden para pasar la validacion. 
	 *  Cuando la validacion es vacia, se entiende que ya paso dicha validacion.
	 * @param level El nivel de acceso que tiene el usuario, siendo: 
	 *  CLIENTE->0 para ver la web sin poder administar nada.
	 *  ADMIN->1 donde puede administar.
	 *  ADMIN->9 Control total.
	 */
	public User(int id, String name, String email, String validation, Access level) 
	{
		this.id = id;
		this.name = name;
		this.email = email;
		this.validation = validation;
		this.level = level;
	}

	/**
	 * @return El identificador del usuario.
	 */
	public int getID() 
	{
		return id;
	}
	
	/**
	 * @return El nombre del usuario.
	 */
	public String getName() 
	{
		return name;
	}
	
	/**
	 * @param name Establece el nombre del usuario.
	 */
	public void setName(String name) 
	{
		this.name = name;
	}
	
	/**
	 * @return El nivel de acceso que tiene.
	 */
	public Access getAccess() 
	{
		return level;
	}
	
	/**
	 * @param level Establece el nivel de acceso que tiene.
	 */
	public void setLevel(Access level) 
	{
		this.level = level;
	}

	/**
	 * @return el email del usuario.
	 */
	public String getEmail() 
	{
		return email;
	}

	/**
	 * @param Establece el email del usuario.
	 */
	public void setEmail(String email) 
	{
		this.email = email;
	}

	/**
	 * @return la validacion que tiene la cuenta.
	 */
	public String getValidation() 
	{
		return validation;
	}

	/**
	 * @param validation Establece la validacion que tiene la cuenta.
	 */
	public void setValidation(String validation) 
	{
		this.validation = validation;
	}

	/**
	 *  Enum que controlara el nivel de acceso.
	 *  Puediendo ser CLIENTE-> 0 , ADMIN-> 1, ROOT-> 9
	 */
	public enum Access 
	{
		CLIENT(0), ADMIN(1), ROOT(9);
		
		/**
		 * Identificador del acceso.
		 */
		private int id;
		
		/**
		 * Se establece el acceso por id.
		 * @param id
		 */
		private Access(int id) 
		{
			this.id = id;
		}
		
		/**
		 * @return El identificador del acceso.
		 */
		public int getID() 
		{
			return id;
		}
		
		/**
		 * 0 -> CLIENT , 1 -> ADMIN , 9 -> ROOT
		 * @return El acceso pasandole el ID
		 * @param id El identificador del acceso
		 */
		public static Access getByID(int id) 
		{
			switch(id) 
			{
				case 0:
					return Access.CLIENT;
				case 1:
					return Access.ADMIN;
				case 9:
					return Access.ROOT;
			}
			return Access.CLIENT;
		}
	}
}