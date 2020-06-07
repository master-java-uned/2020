package controler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import model.CovidDAOImpl;

/**
 * Clase encargargada de devolver el numero total
 * de rows contenidas en la base de datos.
 * @author @kalua66
 *
 */
@WebServlet("/get-size-covid")
public class GetSizeCovidServlet extends HttpServlet
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
		/**
		 *  Creo un mapa de string donde luego metere la devolucion de AJAX
		 */
		Map<String, String> data = new HashMap<>();		
				        
		/**
		 *  Creamos un objeto para manejar los datos del Covid desde la base de datos
		 */
		CovidDAOImpl covidDAO = new CovidDAOImpl();
								       
		try 
		{
			/**
			 *  Pido los datos, y los paso a json
			 */
			String rows = covidDAO.getRowsCovid() + "";
			data.put("data", rows);			
			String json = new Gson().toJson(data);

			/**
			 *  Pongo el tipo de devolucion a Json y UTF8 y lo printo, para que lo recupere el AJAX
			 */
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
}