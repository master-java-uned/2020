package util.tag;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.tagext.TagSupport;
import beans.covid.Covid;

/**
 * Clase encargarda de ayudar a mantener un formato limpio 
 * en la vista, en este caso al printar los datos del covid para el chart y map.
 * 
 * @author @kalua66
 *
 */
public class DataMapChartTag extends TagSupport 
{
	private static final long serialVersionUID = 1L;
	
	public int doStartTag() 
	{
		/**
		 * Recuperamos la session.
		 */
		HttpSession session = this.pageContext.getSession();
		
		String casesD = "";
		String casesN = "";
		int contador = 0;
		String countries = "";
		String cases = "";
		String deaths = "";
		int firstCases = 0;
		int maxDeaths = 0;
		
		Object listCovid = session.getAttribute("listAllCountries");

		if(listCovid != null)
		{	
		
			@SuppressWarnings("unchecked")
			List<Covid> listCovids = (List<Covid>) listCovid;
			contador = 0;
			for (Covid covid: listCovids)
			{
				if(covid.getCases() > 0 && covid.getCountryterritoryCode() != null && covid.getCountryterritoryCode() != "")
				{
					// Para el mapa
					if(contador > 0)
					{
						casesD = casesD +",";
						casesN = casesN +",";
					}
					
					casesD = casesD +"\""+covid.getCountryterritoryCode()+"\":" + covid.getCases();
					
					casesN = casesN + covid.getCases();
					
				// Para la grafica 
					if(contador <= 4)
					{
						
						if(contador != 0)
						{
							countries = countries + ",";
							cases = cases + ",";
							deaths = deaths + ",";
						}
						else
						{
							firstCases = covid.getCases();
							if(firstCases > 100000)
							{
								firstCases =(int) (firstCases / 100000);
								firstCases++;
								firstCases = firstCases * 100000;
							}
							
						}
						countries = countries + "'" + covid.getCountriesAndTerritories() + "'";
						cases = cases + covid.getCases();
						deaths = deaths + covid.getDeaths();
						
						if(maxDeaths < covid.getDeaths())
						{
							maxDeaths = covid.getDeaths();
						}
					}
				}
				contador++;
			}
		
			if(maxDeaths >10000 )
			{
				maxDeaths =(int) maxDeaths / 10000 ;
				maxDeaths++;
				maxDeaths = maxDeaths * 10000;
			}
		}

		try 
		{
			this.pageContext.getOut().println("<script>");
			
				this.pageContext.getOut().println("var casesData = {"+casesD+"};");
				this.pageContext.getOut().println("var casesNum = ["+casesN+"];");
				this.pageContext.getOut().println("var countries = ["+countries+"];");
				this.pageContext.getOut().println("var deaths = ["+deaths+"];");
				this.pageContext.getOut().println("var cases = ["+cases+"];");
			
				this.pageContext.getOut().println("CreateMap(casesData);");
				this.pageContext.getOut().println("setMyBarChart2(countries, deaths, cases);");
				
				this.pageContext.getOut().println("</script>");
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		return SKIP_BODY;
	}
}