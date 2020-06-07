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
public class DataChartCountryTag extends TagSupport 
{
	private static final long serialVersionUID = 1L;
	
	public int doStartTag() 
	{
		/**
		 * Recuperamos la session.
		 */
		HttpSession session = this.pageContext.getSession();
		

		int contador = 0;
		String dates = "";
		String cases = "";
		String deaths = "";

		
		Object listCovid = session.getAttribute("listCountry");

		if(listCovid != null)
		{	
		
			@SuppressWarnings("unchecked")
			List<Covid> listCovids = (List<Covid>) listCovid;
			contador = 0;
			for (Covid covid: listCovids)
			{
				if(contador != 0)
				{
							dates = dates + ",";
							cases = cases + ",";
							deaths = deaths + ",";
				}

				dates = dates + "'" + covid.getDateRep().replace("2020/", "") + "'";
				cases = cases + covid.getCases();
				deaths = deaths + covid.getDeaths();
			
				contador++;
			}
		}

		try 
		{
			this.pageContext.getOut().println("<script>");
			
				this.pageContext.getOut().println("var casesDates = ["+dates+"];");
				this.pageContext.getOut().println("var deaths = ["+deaths+"];");
				this.pageContext.getOut().println("var cases = ["+cases+"];");
				
				this.pageContext.getOut().println("setAreaChart(\"bar\",casesDates, cases,\"myAreaChart\",\"cases\",\"#c7a219\",\"#601919\",\"#ffcc40\",\"#ffcc40\",\"#ffcc40\");");
				this.pageContext.getOut().println("setAreaChart(\"bar\",casesDates, deaths,\"myAreaChart2\",\"deaths\",\"#853232\",\"#facf31\",\"#ffcc40\",\"#ffcc40\",\"#ffcc40\");");
				
			this.pageContext.getOut().println("</script>");
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		return SKIP_BODY;
	}
}