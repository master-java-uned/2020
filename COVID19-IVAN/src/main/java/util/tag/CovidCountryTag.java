package util.tag;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.tagext.TagSupport;
import beans.covid.Covid;

/**
 * Clase encargarda de ayudar a mantener un formato limpio 
 * en la vista, en este caso al printar los datos del covid del pais en formato tabla.
 * 
 * @author @kalua66
 *
 */
public class CovidCountryTag extends TagSupport 
{
	List<Covid> listCovids = null;
	private static final long serialVersionUID = 1L;

	private int cuenta = 0;

	@SuppressWarnings("unchecked")
	public int doStartTag() 
	{
		/**
		 * Recuperamos la session.
		 */
		HttpSession session = this.pageContext.getSession();
		
		/**
		 *  Obtenemos todos los datos del covid
		 */
		Object listCovid = session.getAttribute("listCountry");

		if (listCovid != null) 
		{
			listCovids = (List<Covid>) listCovid;
			cuenta = 0;
		}

		try 
		{
			this.pageContext.getOut().println("<table class=\"table table-bordered\" id=\"dataTable\" width=\"100%\" cellspacing=\"0\">");
				this.pageContext.getOut().println("<thead>");
					this.pageContext.getOut().println("<tr>");
						this.pageContext.getOut().println("<th>Date</th>");
						this.pageContext.getOut().println("<th>Confirmed</th>");
						this.pageContext.getOut().println("<th>Deacesed</th>");
					this.pageContext.getOut().println("</tr>");
				this.pageContext.getOut().println("</thead>");
				this.pageContext.getOut().println("<tfoot>");
					this.pageContext.getOut().println("<tr>");
						this.pageContext.getOut().println("<th>Date</th>");
						this.pageContext.getOut().println("<th>Confirmed</th>");
						this.pageContext.getOut().println("<th>Deacesed</th>");
					this.pageContext.getOut().println("</tr>");
				this.pageContext.getOut().println("</tfoot>");
				this.pageContext.getOut().println("<tbody>");
		}
		catch (IOException e) 
		{

		}
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public int doAfterBody() 
	{
		try 
		{
			this.pageContext.getOut().println("<tr>");
				this.pageContext.getOut().println("<td>" + listCovids.get(cuenta).getDateRep() + "</td>");
				this.pageContext.getOut().println("<td>" + listCovids.get(cuenta).getCasesFormated() + "</td>");
				this.pageContext.getOut().println("<td>" + listCovids.get(cuenta).getDeathsFormated() + "</td>");
			this.pageContext.getOut().println("</tr>");
		} 
		catch (Exception e)
		{

		}
		this.cuenta++;
		
		if(this.listCovids!=null) 
		{
			if (this.cuenta >= this.listCovids.size()) 
			{
				try 
				{
					this.pageContext.getOut().println("</tbody>");
					this.pageContext.getOut().println("</table>");
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
				
				return SKIP_BODY;
			}
		}
		else 
		{
			return SKIP_BODY;
		}
		return EVAL_BODY_AGAIN;
	}
}