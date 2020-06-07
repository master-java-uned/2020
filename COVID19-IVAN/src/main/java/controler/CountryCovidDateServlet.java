package controler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import beans.covid.Covid;
import model.CovidDAOImpl;

/**
 * Clase utlizada para devolver datos deel covid por fecha y pais.
 * 
 * @author @kalua66
 *
 */
@WebServlet("/covidbydate")
public class CountryCovidDateServlet extends HttpServlet 
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
		int minimunCases = 1;
		/**
		 * Obtengo las fechas.
		 */
		String initialDate = "2019/12/29";
		String maxdate = request.getParameter("maxdate");

		/**
		 * Aqui mapeare los datos.
		 */
		Map<String, Integer> data = new LinkedHashMap<>();
				  
		/**
		 * Objeto para acceder a los datos del covid.
		 */
		CovidDAOImpl covidDAO = new CovidDAOImpl();
		
		try 
		{
			/**
			 * Hago peticion de covid por pais pasando el 
			 * numero minimo de caos en el pais y las fechas.
			 */
			List<Covid> listCovid = covidDAO.getAllByCountries(minimunCases, initialDate, maxdate);
			
			/**
			 * Mapeo el codigo del pais con el numero de casos.
			 */
			for (Covid covid : listCovid) 
			{
				data.put(covid.getCountryterritoryCode(), covid.getCases());
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		/**
		 * Lo paso a json
		 */
	    String json = new Gson().toJson(data);

	    /**
	     * Paso en json.
	     */
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(json);
	}
}