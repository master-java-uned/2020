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
import model.CovidDAOImpl;


/**
 * Clase utlizada para devolver todos los valores del covid por pais.
 * 
 * @author @kalua66
 *
 */
@WebServlet("/allcountriescovid")
public class AllCountryCovidServlet extends HttpServlet 
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
		 * Recupero la sesion
		 */
        HttpSession session = request.getSession();
        
        /**
         * Creo objeto para acceder a los datos del Covid.
         * Los agrego a la sesion
         */
        CovidDAOImpl covidDAO = new CovidDAOImpl(); /*-?|Carlos Luis Sánchez Bocanegra|carlosl.sanchez|c2|?*/
       
		try 
		{
			List<Covid> listCovid = covidDAO.getAllByCountries(1);
			session.setAttribute("listAllCountries",listCovid);
		}
		catch (SQLException e) 
		{
			e.printStackTrace(); /*-?|Carlos Luis Sánchez Bocanegra|carlosl.sanchez|c1|?*/
		}
        
		/**
		 * Redirijo.
		 */
        request.getRequestDispatcher("covidcountries.jsp").forward(request,response);
    }
}