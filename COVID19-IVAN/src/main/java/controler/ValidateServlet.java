package controler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import beans.form.UserForm;
import model.UserDAOImpl;

/**
 * Clase encargargada de validar el codigo que se
 *  manda por email al usuario.
 *
 * @author @kalua66
 *
 */
@WebServlet("/validate")
public class ValidateServlet extends HttpServlet
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request, response);
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
		String codeValidation = request.getParameter("validation");

		System.out.println("codeValidation:" + codeValidation);
		
		HttpSession session = request.getSession();
		
		UserForm userForm = new UserForm((String) session.getAttribute("username"),"");
		UserDAOImpl user = new UserDAOImpl();
		boolean validacion = user.CheckValidationCode(userForm.getUsername(), codeValidation);
		
		System.out.println("validacion:" + validacion);
		
		if(validacion) 
		{
			session.setAttribute("validate",true);
			session.setAttribute("actual-page","index");
		}
		   
		request.getRequestDispatcher("validate.jsp").forward(request, response);
	}
}