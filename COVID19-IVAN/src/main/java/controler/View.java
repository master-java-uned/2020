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


@WebServlet("/view")
public class View extends HttpServlet
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
		
		
		String country = "";
		
		if(request.getParameter("country") == null || request.getParameter("country") == "" ) 
		{
			request.getRequestDispatcher("relogin").forward(request,response);
		}
		/**
		 * Comprobamos que no este logueado ya !
		 */
		if(session.getAttribute("username") != null && session.getAttribute("login") != null && (boolean) session.getAttribute("login") == true) 
		{
			/**
			 * Esta ya logueado.
			 */
			System.out.println("Ya esta logueado.");

			country = request.getParameter("country");
		}
		else 
		{
			request.getRequestDispatcher("relogin").forward(request,response);
		}
		

			/**
			 *  Creamos un objeto para manejar los datos del Covid desde la base de datos
			 */
			CovidDAOImpl covidDAO = new CovidDAOImpl();
								       
			try 
			{
				/**
				 *  Pido los datos en total agrupados por pais
				 */
				List<Covid> listCovid = covidDAO.getAllByCountry(country);
						
				/**
				 *  Paso dichos datos a la session para printarlos en la vista
				 */
				session.setAttribute("actual-page","view");

				session.setAttribute("listCountry",listCovid);
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
				Covid totalData = covidDAO.getMaxCasesAndDeath(country);
				
				/**
				 *  Paso dichos datos a la session para printarlos en la vista
				 */
				System.out.println("totalDataCountry:"+totalData);
				
				session.setAttribute("totalDataCountry",totalData);
				
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		
			request.getRequestDispatcher("view.jsp?country="+country).forward(request,response);	
		
	}
}