package util.tag;

import java.io.IOException;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.tagext.TagSupport;
import beans.covid.Covid;


/**
 * Clase encargarda de ayudar a mantener un formato limpio 
 * en la vista, en este caso al printar los datos del covid totales de casos y muertes.
 * 
 * @author @kalua66
 *
 */
public class QuickFactsTag extends TagSupport 
{
	private static final long serialVersionUID = 1L;
	
	String totalConfirmed = "-";
	String totalDeceased = "-";
	
	@SuppressWarnings("null")
	public int doStartTag() 
	{
		boolean viewCountry = false;
		/**
		 * Recuperamos la session.
		 */
		HttpSession session = this.pageContext.getSession();
		
		if(session != null && session.getAttribute("actual-page") != null) 
		{
			if(session.getAttribute("actual-page").equals("view"))
			{
				viewCountry = true;
			}
		}
		
		
		/**
		 *  Obtenemos los datos del covid
		 */
		Object totalData = session.getAttribute("totalData");
		  
		Covid covidMax = null;
			
		if(totalData != null)
		{
			String atributeTotalData = "totalData";
			
			if(viewCountry)
			{
				atributeTotalData = "totalDataCountry";
			}
			
			covidMax = (Covid) session.getAttribute(atributeTotalData);

			totalConfirmed = covidMax.getCasesFormated();
			totalDeceased = covidMax.getDeathsFormated();
		}

		try 
		{
			this.pageContext.getOut().println("<div class=\"sb-sidenav-menu-heading\">Quick Facts</div>");
			if(viewCountry)
			{
				this.pageContext.getOut().println("<a class='name-country-flag'>" + covidMax.getCountriesAndTerritories() + "</a>");
				this.pageContext.getOut().println("<img class=\"flag-icon h4 d-block mx-auto rounded\"style='width: 68px;height: 36px;' width=\"68px\" height=\"36px\" src=\"https://cdn.jsdelivr.net/gh/hjnilsson/country-flags@latest/svg/"+covidMax.getCountryterritoryCode().toLowerCase()+".svg\">");
			}
			this.pageContext.getOut().println("<a class=\"nav-title\">TOTAL CONFIRMED</a>");
			this.pageContext.getOut().println("<a class=\"confirmed\">" + totalConfirmed + "</a>");
			this.pageContext.getOut().println("<a class=\"nav-title\">TOTAL DECEASED</a>");
			this.pageContext.getOut().println("<a class=\"deceased\">" + totalDeceased + "</a>");
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		covidMax = null;
		totalData = null;
		
		return EVAL_BODY_INCLUDE;
	}
}