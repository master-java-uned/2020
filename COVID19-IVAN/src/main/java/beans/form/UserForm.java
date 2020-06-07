package beans.form;


/**
 * Clase encargarda de recoger los datos de un 
 * formulario y disponer de sus validaciones.
 * 
 * @author @kalua66
 *
 */
public class UserForm 
{
	/**
	 * Nombre del usuario
	 */
	private String username;
	
	/**
	 * Email del usuario
	 */
	private String email;
	
	/**
	 * La contraseña del usuario
	 */
	private String password;
	
	/**
	 * La confirmacion de la contraseña.
	 */
	private String confirmPassword;
	
	/**
	 * Un enum con la operacion del formulario.
	 */
	private Form form;
	
	
	/**
	 * 
	 * Constructor de la clase pasando unicamente un string,
	 *  que entederemos que es el email, para el caso del 
	 *  olvido de la contraseña (CASO AUN NO CONTEMPLADO).
	 * 
	 * @param email El email.
	 */
	public UserForm(String email) 
	{
		this.email = email;
		this.form = Form.FORGOT;
	}
	
	/**
	 * 
	 * Constructor de la clase, en el que se pasa el nombre 
	 * de usuario y password, entemos en esta caso que esta 
	 * haciendo un login que hacemos un login.
	 * 
	 * @param username El nombre de usuario.
	 * @param email El email.
	 * @param password La contraseña.
	 */
	public UserForm(String username, String password) 
	{
		this.username = username;
		this.password = password;
		this.form = Form.LOGIN;
	}
	
	/**
	 * 
	 * Constructor de la clase. Pasando en este caso 4 string, 
	 * para el usuario, email, contraseña, y confirmacion de la
	 *  contraseña, entendemos que esta haciendo un registro.
	 * 
	 * @param username El usuario.
	 * @param email El email.
	 * @param password La contraseña.
	 * @param confirmPassword La confirmacion de la contraseña.
	 */
	public UserForm(String username,String email, String password, String confirmPassword) 
	{
		this.username = username;
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.form = Form.REGISTER;
	}
	
	/**
	 * 
	 * Constructor completo de la clase.
	 * 
	 * @param username El usuario.
	 * @param email El email.
	 * @param password La contraseña.
	 * @param confirmPassword La confirmacion de la contraseña.
	 * @param form La operacion del formulario.
	 */
	public UserForm(String username,String email, String password, String confirmPassword,Form form) 
	{
		this.username = username;
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.form = form;
	}
	
	/**
	 * @return El usuario.
	 */
	public String getUsername() 
	{
		return username;
	}
	
	/**
	 * @return El email.
	 */
	public String getEmail() 
	{
		return email;
	}

	/**
	 * @return La contraseña.
	 */
	public String getPassword() 
	{
		return password;
	}

	/**
	 * @return La confirmacion de la contraseña.
	 */
	public String getConfirmPassword() 
	{
		return confirmPassword;
	}

	/**
	 * @return La operacion con el formulario.
	 */
	public Form getForm() 
	{
		return form;
	}
	
	/**
	 * Enum con las operaciones del Formulario: LOGIN, REGISTER, FORGOT
	 */
	public enum Form 
	{
		LOGIN, REGISTER, FORGOT
	}
		
	/**
	 * Comprobamos que los campos del formulario no vengan vacios.
	 * @return El estado de la operacion.
	 */
	private FormStatus checkFieldsEmpty() 
	{
		if (getForm() == Form.LOGIN || getForm() == Form.REGISTER) 
		{
			if (getUsername() == null || getUsername().length() == 0 )
			{
				return new FormStatus("Username cannot be empty");
		    }
			
			if (getPassword() == null || getPassword().length() == 0)
			{
				return new FormStatus("Password cannot be empty");
		    }
		}
		
		if (getForm() == Form.REGISTER) 
		{
			
			if (getEmail () == null || getEmail().length() == 0)
			{
				return new FormStatus("Email cannot be empty");
			}
			
			if (getConfirmPassword () == null || getConfirmPassword().length() == 0)
			{
				return new FormStatus("Confirm Password cannot be empty");
			}
		}
		
		if (getForm() == Form.FORGOT) 
		{
			if (getEmail () == null || getEmail().length() == 0)
			{
				return new FormStatus("Email cannot be empty");
			}
		}
		
		return new FormStatus(true);
	}
	
	/**
	 * Comprobamos si el email que se pasa por 
	 * parametro es un email valido mediante unas expresiones regulares.
	 * 
	 * @param email El email
	 * @return Si es un email valido o no
	 */
	private boolean isValidEmailAddress(String email) 
	{
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
	}
	
	/**
	 * Realizamos la validaciones pertinentes.
	 * 
	 * @return El estado de la operacion.
	 */
	public FormStatus validate() 
	{
		// Comprobamos que no tenga campos vacios
		FormStatus form = checkFieldsEmpty();
		if(form.isSuccess() == false) return form;
		
		switch(getForm()) 
		{
			case LOGIN:
				// Comprobar si el usuario existe en la base de datos y coincide la contrase�a.
				// return new FormStatus("The user and/or password are incorrect");
				break;
			case REGISTER:
				if(isValidEmailAddress(getEmail()) == false)
				 {
					 return new FormStatus("The email is incorrect");
				 }
				 
				 if (getPassword().equals(getConfirmPassword()) == false)
				 {
					 return new FormStatus("The password doesn't match");
				 }
				 
				 // Comprobar que el usuario no existe ya en la base de datos.
				 // return new FormStatus("User already exists");
				 break;
			case FORGOT:
				// Comprobar si el email existe en la base de datos
				// return new FormStatus("An email will be sent to that account if it exists in our records.");
				break;
		}
		return new FormStatus(true);
	}
}