package controler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.CovidDAOImpl;


/**
 * Clase utlizada para borrar los datos del covid de la base de datos.
 * 
 * @author @kalua66
 *
 */
@WebServlet("/delete-covid")
public class DeleteServlet extends HttpServlet
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
		 *  Recuperamos la sesion.
		 */
		HttpSession session = request.getSession();
				        
		/**
		 *  Creamos un objeto para manejar los datos del Covid, en este caso borrarlos desde la base de datos.
		 */
		CovidDAOImpl covidDAO = new CovidDAOImpl();
		covidDAO.deleteAllCovid();
		
		/**
		 * Borramos de la session los siguientes datos.
		 */
		session.removeAttribute("listAllCountries");
		session.removeAttribute("totalData");
	}
}