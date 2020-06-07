package controler;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import beans.user.User;
import model.UserDAOImpl;

/**
 * Clase encargargada de devolver los datos de los usuarios.
 * @author @kalua66
 *
 */
@WebServlet("/manage-user")
public class ManageUserServlet extends HttpServlet
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
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		
		/**
		 * Creo un objeto para acceder a los datos de lo usuarios,
		 * los guardo en la session y redirijo.
		 */
		UserDAOImpl userDAO = new UserDAOImpl();
		List<User> users = userDAO.getAllUsers();
			
		session.setAttribute("AllUsers",users);
		
		session.setAttribute("actual-page","manage-user");
		request.getRequestDispatcher("manage-user.jsp").forward(request,response);
	}
}