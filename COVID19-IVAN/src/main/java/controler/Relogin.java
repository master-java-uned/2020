package controler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import beans.covid.Covid;
import beans.form.FormStatus;
import beans.form.UserForm;
import beans.form.UserStatus;
import model.CovidDAOImpl;
import model.UserDAOImpl;


@WebServlet("/relogin")
public class Relogin extends HttpServlet
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
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		/**
		 *  Recuperamos la sesion
		 */
		HttpSession session = request.getSession();
		
		UserForm userForm = null;
		
		boolean isLogin = false;
		
		/**
		 * Comprobamos que no este logueado ya !
		 */
		if(session.getAttribute("username") != null && session.getAttribute("login") != null && (boolean) session.getAttribute("login") == true) 
		{
			/**
			 * Esta ya logueado.
			 */
			System.out.println("Ya esta logueado.");
			isLogin = true;
		}
		else 
		{
			/**
			 *  Creo un objecto UseForm con los datos del formulario
			 */
			userForm = new UserForm(request.getParameter("username"),request.getParameter("password"));
		}
		
		/**
		 * Si ya esta logueado
		 */
		if(isLogin) 
		{
		
			/**
			 *  Creamos un objeto para manejar los datos del Covid desde la base de datos
			 */
			CovidDAOImpl covidDAO = new CovidDAOImpl();
								       
			try 
			{
				/**
				 *  Pido los datos en total agrupados por pais
				 */
				List<Covid> listCovid = covidDAO.getAllByCountries(1);
						
				/**
				 *  Paso dichos datos a la session para printarlos en la vista
				 */
				session.setAttribute("listAllCountries",listCovid);
				System.out.println("size:"+listCovid.size());
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
				
			try {
				/**
				 *  Pido los datos total de casos y muertes
				 */
				Covid totalData = covidDAO.getMaxCasesAndDeath();
				
				/**
				 *  Paso dichos datos a la session para printarlos en la vista
				 */
				System.out.println("totalData:"+totalData);
				
				session.setAttribute("totalData",totalData);
				
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			
			session.setAttribute("actual-page","index");
			
			request.getRequestDispatcher("index.jsp").forward(request,response);	
		}
		else /** Sino esta logueado */
		{

			/**
			 *  Valido el formulario
			 */
			FormStatus form = userForm.validate();
		
			/**
			 *  Si la validacion es correcta
			 */
			if(form.isSuccess()) 
			{
				/**
				 *  Creamos un objeto para manegar el usuario contra la base de datos
				 */
				UserDAOImpl userDAO = new UserDAOImpl();
				
				/**
				 *  Hacemos login y recuperamos el status de ese login
				 */
				UserStatus userStatus = userDAO.login(userForm);
				
				/**
				 *  Controlamos el status para cada caso
				 */
				switch (userStatus.getStatus()) 
				{
					/**
					 *  En caso de ser USER_ERROR, añadimos el error y apuntamos el dato del error.			
					 */
					case "USER_ERROR":
						session.setAttribute("actual-page","login");
						request.getRequestDispatcher("login.jsp").forward(request,response);
						break;
					/**
					 *  En caso de ser USER_VALIDATION, sabremos que ese usuario le falta validad el correo
					 *  Añadimos a la session su usuario con sus datos recuperados de la base de datos, y apuntamos que
					 *  el login es correcto y validacion no. Tambien apuntamos la redireccion que realizara el front, al ser por AJAX.
					 */ 
					case "USER_VALIDATION":
						session.setAttribute("username", userStatus.getUser().getName());
						session.setAttribute("userid", userStatus.getUser().getID());
						session.setAttribute("level", userStatus.getUser().getAccess().getID());
						session.setAttribute("login", true);
						session.setAttribute("validate", false);
						session.setAttribute("actual-page","validation");
							
						request.getRequestDispatcher("validation.jsp").forward(request,response);
						break;
					/**
					 *  En caso de ser LOGIN_CORRECT, sabremos que ese usuario es correcto y el login es correcto
					 *  Añadimos a la session su usuario con sus datos recuperados de la base de datos, y apuntamos
					 *  que la validacion es correcta y el login tambien.	
					 */ 
					case "LOGIN_CORRECT":
						session.setAttribute("username", userStatus.getUser().getName());
						session.setAttribute("userid", userStatus.getUser().getID());
						session.setAttribute("level", userStatus.getUser().getAccess().getID());
						session.setAttribute("login", true);
						session.setAttribute("validate", true);					
						session.setAttribute("actual-page","index");   
						/**
						 *  Creamos un objeto para manejar los datos del Covid desde la base de datos
						 */
						CovidDAOImpl covidDAO = new CovidDAOImpl();
									       
						try 
						{
							/**
							 *  Pido los datos en total agrupados por pais
							 */
							List<Covid> listCovid = covidDAO.getAllByCountries(1);
							
							/**
							 *  Paso dichos datos a la session para printarlos en la vista
							 */
							session.setAttribute("listAllCountries",listCovid);
						}
						catch (SQLException e) 
						{
							e.printStackTrace();
						}
						
						request.getRequestDispatcher("index.jsp").forward(request,response);
						
						break;
					/**
					 *  En caso de ser PASSWORD_ERROR, añadimos el error y apuntamos el dato del error.
					 */
					case "PASSWORD_ERROR":
						session.setAttribute("actual-page","login");   
						request.getRequestDispatcher("login.jsp").forward(request,response);
						break;
				}
			}
			else
			{
				/**
				 *  Si no paso la primera validacion de formulario basada en comprobacion de inputs vacios.
				 *  Apunto como error y meto la descripcion en el data
				 */
				request.getRequestDispatcher("login.jsp").forward(request,response);
			}
		}	
	}
}