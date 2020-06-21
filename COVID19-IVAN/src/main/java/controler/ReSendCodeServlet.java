package controler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import beans.form.UserForm;
import beans.user.User;
import model.UserDAOImpl;
import util.MailUtil;


/**
 * Clase encargargada de enviar el codigo de verificacion 
 * del email con el que se registro.
 *
 * @author @kalua66
 *
 */
@WebServlet("/resend")
public class ReSendCodeServlet extends HttpServlet
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
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException
	{
		HttpSession session = req.getSession();
		
		if(session.getAttribute("username") != null) 
		{
			UserForm userForm = new UserForm((String) session.getAttribute("username"),"");
			UserDAOImpl userDAO = new UserDAOImpl();
			User userResend = userDAO.getUser(userForm.getUsername());
			
			/**
			 * Enviamos el email.
			 */
			MailUtil.sendEmail(userResend.getEmail(),"Account Verification", "Code Verification: <b>"+userResend.getValidation()+"</b>");

			res.getWriter().print("Code was sent to: "+userResend.getEmail());
		}
	}
}