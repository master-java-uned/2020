package controler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import beans.form.FormStatus;
import beans.form.UserForm;


/**
 * Clase utlizada para recuperar la 
 * contraseña cuando se olvida(aun sin implementar)
 * 
 * @author @kalua66
 *
 */
@WebServlet("/forgot")
public class ForgotServlet extends HttpServlet
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
		UserForm userForm = new UserForm(request.getParameter("email")); /*-?|dolivera3-review|carlosl.sanchez|c5|*/
		
		FormStatus form = userForm.validate();
		if(form.isSuccess()) 
		{
			System.out.println("email:" + userForm.getEmail());
		}
		else 
		{
			System.out.println("Fallo en el registro: " + form.getError());
		}
	} /*-|dolivera3-review|carlosl.sanchez|c5|?*/
}