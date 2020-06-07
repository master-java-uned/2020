package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import beans.form.UserForm;
import beans.form.UserStatus;
import beans.user.User;
import beans.user.User.Access;
import database.DataBase;

/**
 * Clase encargada de implementar los metodos 
 * de la interfaz para acceso a datos de usuario.
 * 
 * @author @kalua66
 *
 */
public class UserDAOImpl implements UserDAO
{
	/**
	 * Constructor de la clase vacio
	 */
	public UserDAOImpl() 
	{
		
	}
	
	/**
	 * Compruebo el codigo de validacion que se me pasa con el 
	 * que tiene en la base de datos para comprobar que pone el mismo.
	 * @param username El nombre de usuario
	 * @param code El codigo de validacion
	 * @return Si la operacion se valido correctamente o no.
	 */
	public boolean CheckValidationCode(String username,String code) 
	{
		ResultSet resultSet = ReadUser(username);

		try 
		{
			if (resultSet == null || !resultSet.isBeforeFirst())
			{
				return false;
			}
			else
			{
				if (resultSet.next())
				{
					String validation = resultSet.getString("validation");
					
					System.out.println("result#validation:"+validation);
					
					if(validation.trim().equals(code.trim())) 
					{
						System.out.println("result# CODIGO CORRECTO");
						
						updateUsuarioValidacion(username,"");
						
						return true;
					}
					else
					{
						return false;
					}
				}
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
			
		return false;
	}     
	
	/**
	 * Realizo una actualizacion del campo validacion 
	 * de la tabla user de la base de datos.
	 * @param username El nombre de usuario
	 * @param validacion El codigo de validacion, siendo 
	 * vacio ("") cuando ya se valido correctamente.
	 * @return El numero de filas afectadas.
	 */
	public int updateUsuarioValidacion(String username,String validacion) 
	{
		int affectRow = 0;
		System.out.println("validaUsuario()");
		try
		{
			String sql = "UPDATE user SET validation = ? WHERE username = ?";
			PreparedStatement statement = DataBase.getConnection().prepareStatement(sql);
			statement.setString(1, validacion);
			statement.setString(2, username);
			affectRow = statement.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return affectRow;
	}
	
	/**
	 * Comprueba un login con los datos pasados del formulario.
	 * @param userForm Los datos del formulario.
	 * @return El resultado de la operacion en un objeto UserStatus.
	 */
	public UserStatus login(UserForm userForm) 
	{
		/**
		 * Recupero el usuario
		 */
		ResultSet resultSet = ReadUser(userForm.getUsername());

		try 
		{
			if (resultSet == null || !resultSet.isBeforeFirst())
			{
				return new UserStatus(null, "USER_ERROR", "EL USUARIO NO EXISTE");
			}
			else
			{
				if (resultSet.next())
				{
					int id = resultSet.getInt("id");
					String username = resultSet.getString("username");
					String password = resultSet.getString("password");
					String validation = resultSet.getString("validation");
					int level = resultSet.getInt("level");

					DataBase.closeConnection();
					
					System.out.println("userNameDB:" + username + " " + "passwordDB:" + password);

					if (userForm.getUsername().equals(username) && userForm.getPassword().equals(password))
					{
						User user = new User(id, username, User.Access.getByID(level));
						
						System.out.println("validation:#" + validation + "#");
						
						if (validation.equals(""))
						{
							System.out.println("LOGIN_CORRECT");
							return new UserStatus(user, "LOGIN_CORRECT", "LOGIN CORRECTO");
							
						}
						else
						{
							System.out.println("USER_VALIDATION");
							return new UserStatus(user, "USER_VALIDATION", "FALTA VALIDAR LA CUENTA");
						}
					}
					else
					{
						return new UserStatus(null, "PASSWORD_ERROR", "EL PASSWORD NO COINCIDE");
					}
				}
			}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * Sirve para crear un hash aleatorio, pasando la longitud.
	 * @param count La longitud.
	 * @return El hash.
	 */
	private String createHash(int count)
	{
		char[] chars = "ABCDEFGHJKMNPQRSTUVWXYZ123456789".toCharArray();
		StringBuilder sb = new StringBuilder(count);
		Random random = new Random();
		
		for (int i = 0; i < count; i++) 
		{
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}
		return sb.toString();
	}

	/**
	 * Leo todos los usuario y los devuelvo en un resulset para trabajarlos fuera de aqui.
	 * @return El resulset.
	 */
	private ResultSet readAllUser()
	{
		ResultSet resultSet = null;

		try
		{
			String sql = "SELECT id, username, email, validation, level FROM user";
			Statement statement = DataBase.getConnection().createStatement();
			resultSet = statement.executeQuery(sql);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return resultSet;
	}

	/**
	 * Creo un usario en la base de datos con los parametros que se le pase.
	 * @param username El nombre de usuario.
	 * @param email El email.
	 * @param password El password.
	 * @param validation La validacion.
	 * @param level El nivel de acceso.
	 * @return El id del usuario, -1 en caso de no ser creado.
	 */	
	private int CreateUser(String username, String email, String password, String validation, int level) 
	{
		int idUser = -1;
		try
		{
			String sql = "INSERT INTO user (username, email, password, validation, level ) values (?, ?, ?, ?, ?)";
			PreparedStatement statement = DataBase.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			statement.setString(1, username);
			statement.setString(2, email);
			statement.setString(3, password);
			statement.setString(4, validation);
			statement.setInt(5, level);
			statement.executeUpdate();
			
			ResultSet rs = statement.getGeneratedKeys();
			
			if (rs.next()) 
			{
				idUser= rs.getInt(1);
			}	
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}	
		return idUser;
    }
    
	/**
	 * Leo el usuario del que se me pasa el nombre usuario y lo devuelvo en un resulset para trabajarlo fuera de aqui.
	 * @param username El nombre de usuario.
	 * @return El resulset.
	 */
    private ResultSet ReadUser(String username)
    {
    	ResultSet resultSet = null;

		try
		{
			String sql = "SELECT id, username, email, password, validation, level  FROM user WHERE username = ?";
			PreparedStatement statement = DataBase.getConnection().prepareStatement(sql);
			statement.setString(1, username);
			resultSet = statement.executeQuery();

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return resultSet;
	}
	
	/**
	 * Obtener el usuario ,pasando su nombre de usuario.
	 * @param username El nombre del usuario.
	 * @return El Usuario.
	 */
    @Override
    public User getUser(String username) 
	{
		ResultSet resultSet = ReadUser(username);

		try 
		{
			if (resultSet == null || !resultSet.isBeforeFirst())
			{
				return null;
			}
			else
			{
				if (resultSet.next())
				{
					int id = resultSet.getInt("id");
					String email = resultSet.getString("email");
					String validation = resultSet.getString("validation");
					int level = resultSet.getInt("level");
						
					return new User(id,username, email, validation,Access.getByID(level));
				}
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
				
		return null;
	}

	/**
	 * Obtener todos usuarios.
	 * @return Lista de Usuarios.
	 */
	@Override
	public List<User> getAllUsers()
	{
		ResultSet resultSet = readAllUser();
		List<User> users = new ArrayList<User>();
			
		try 
		{
			while (resultSet.next())
			{
				int id = resultSet.getInt("id");
				String username = resultSet.getString("username");
				String email = resultSet.getString("email");
				String validation = resultSet.getString("validation");
				int level = resultSet.getInt("level");
						
				System.out.println(username);
				User user = new User (id,username, email, validation, User.Access.getByID(level));
				users.add(user);
			}			
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
			
		return users;
	}
	
    /**
     * Inserta un usario.
     * @param user El usuario a insertar.
     * @param userForm El formulario donde metio los datos.
     * @return Si hizo la operacion fue exitosa o no en un Objeto UserStatus.
     */
	@Override
	public UserStatus insertUser(User user, UserForm userForm) 
	{
		String validationToken = createHash(6);
		int id = CreateUser(user.getName(), user.getEmail(), userForm.getPassword(), validationToken, user.getAccess().getID());
			
		if(id == -1)
		{
			return new UserStatus(null,"REGISTER_ERROR","ERROR AL HACER LA INSERCION");
		}
		else
		{
			return new UserStatus(new User(id,userForm.getUsername(),user.getAccess()),"REGISTER_CORRECT", validationToken);
		}
	}
		
	/**
     * Actualiza un Usuario.
     * @param user El usuario a actualizar.
     * @return Si hizo la operacion fue exitosa o no.
     */
	@Override
	public boolean updateUser(User user) 
	{
		// Sin implementar aun
		return false;
	}
	
	/**
     * Elimina un usuario.
     * @param id El usuario a eliminar.
     * @return Si hizo la operacion fue exitosa o no.
     */
	@Override
	public boolean deleteUser(int id)
	{
		try
		{
			String sql = "DELETE FROM `user` WHERE id = ?";
			PreparedStatement statement = DataBase.getConnection().prepareStatement(sql);
			statement.setInt(1, id);
			statement.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}
}