package controler;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.form.FormStatus;
import beans.form.UserForm;
import beans.form.UserStatus;
import beans.user.User;
import model.UserDAOImpl;
import util.MailUtil;

/**
 * Clase encargargada de manejar el registro
 * del email con el que se registro.
 *
 * @author @kalua66
 *
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet
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
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		/**
		 * Creo un objeto con los datos del formulario.
		 */
		UserForm userForm = new UserForm(req.getParameter("username"),req.getParameter("email"),req.getParameter("password"),req.getParameter("confirm_password"));
		/**
		 * Valido el formulario. Se me devuelve el estado de la operacion.
		 */
		FormStatus form = userForm.validate();
		
		/**
		 * Si pasa la validacion.
		 */
		if(form.isSuccess()) 
		{
			/**
			 * Creo un objeto para manejar la base de datos.
			 */
			UserDAOImpl user = new UserDAOImpl();
			/**
			 * Hago un insercion de usuario. Se me devuelve un estado 
			 * del usuario, para saber si se registro o no.
			 */
			UserStatus registroStatus = user.insertUser(new User(0, userForm.getUsername(), userForm.getEmail(), "", User.Access.CLIENT),userForm);
			
			/**
			 * Controlo el estado de la operacion
			 */
			switch (registroStatus.getStatus()) 
			{
				case "REGISTER_ERROR":
					System.out.println("Fallo en el registro: " + registroStatus.getData());
					
					break;
	
				case "REGISTER_CORRECT":
					System.out.println("Registro ok code verification: " + registroStatus.getData() + " USER ID:"+ registroStatus.getUser().getID());
					
					/**
					 * Mando mail para validad la cuenta.
					 */
					MailUtil.sendEmail(userForm.getEmail(),"Account Verification", "Code Verification: <b>"+registroStatus.getData()+"</b>");
					
					req.getRequestDispatcher("login.jsp").forward(req,res);
					break;
				
			}
			System.out.println("username:" + userForm.getUsername() +" email:" + userForm.getEmail() +  " password:" + userForm.getPassword()+ " confirmPassword:" + userForm.getConfirmPassword());
		}
		else 
		{
			System.err.println("Fallo en el registro: " + form.getError());
		}
	}
}