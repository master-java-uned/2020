package controler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.UserDAOImpl;

/**
 * Clase utlizada para borrar un usuario.
 * 
 * @author @kalua66
 *
 */
@WebServlet("/delete-user")
public class DeleteUserServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;	
	
	/**
	 * Peticion por metodo GET. Redirecionada a metodo Post !
	 * 
	 * @param request La peticion.
	 * @param response La respuesta.
	 * @throws ServletException 
	 * @throws IOException 
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request,response);	
	}
	
	/**
	 * Peticion por metodo POST.
	 * 
	 * @param request La peticion.
	 * @param response La respuesta.
	 * @throws ServletException 
	 * @throws IOException 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		/**
		 *  Obtengo el id de usuario del pasado para borrar.
		 */	
		int userID = Integer.parseInt(request.getParameter("userID"));
		
		/**
		 *  Creamos un objeto para manejar los datos del Usuario, en este caso borrarlos desde la base de datos.
		 */
		UserDAOImpl userDAO = new UserDAOImpl();
		userDAO.deleteUser(userID);
		
		/**
		 * Redirijo para mostros a los usuarios
		 */
		request.getRequestDispatcher("manage-user").forward(request,response);
	}
}