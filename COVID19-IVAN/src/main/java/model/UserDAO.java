package model;

import java.util.List;

import beans.form.UserForm;
import beans.form.UserStatus;
import beans.user.User;

/**
 * Interfaz para el DAO de usuario.
 * @author @kalua66
 *
 */
public interface UserDAO 
{
	/**
	 * Obtener el usuario ,pasando su nombre de usuario.
	 * @param username El nombre del usuario.
	 * @return El Usuario.
	 */
	User getUser(String username);
	
	/**
	 * Obtener todos usuarios.
	 * @return Lista de Usuarios.
	 */
    List<User> getAllUsers();
    
    /**
     * Inserta un usario.
     * @param user El usuario a insertar.
     * @param userForm El formulario donde metio los datos.
     * @return Si hizo la operacion fue exitosa o no en un Objeto UserStatus.
     */
    UserStatus insertUser(User user, UserForm userForm);
    
    /**
     * Actualiza un Usuario.
     * @param user El usuario a actualizar.
     * @return Si hizo la operacion fue exitosa o no.
     */
    boolean updateUser(User user);
    
    /**
     * Elimina un usuario.
     * @param id El usuario a eliminar.
     * @return Si hizo la operacion fue exitosa o no.
     */
    boolean deleteUser(int id);
}