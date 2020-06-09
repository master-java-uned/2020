package util.tag;

import java.io.IOException;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.tagext.TagSupport;


/**
 * Clase encargarda de ayudar a mantener un formato limpio 
 * en la vista, para mostrar el menu del admin.
 * 
 * @author @kalua66
 *
 */
public class AdminMenuTag extends TagSupport 
{
	private static final long serialVersionUID = 1L;

	@Override
	public int doStartTag() 
	{
		HttpSession session = this.pageContext.getSession();
		
		if(session != null && session.getAttribute("level") != null && ((int)session.getAttribute("level") >= 1)) 
		{
			try 
			{
				this.pageContext.getOut().println("<div class=\"sb-sidenav-menu-heading\">ADMIN PANEL</div>");
				this.pageContext.getOut().println("<a class=\"nav-link\" href=\"update-data.jsp\">");
					this.pageContext.getOut().println("<span class=\"sb-nav-link-icon\"><i class=\"fas fa-virus\"></i></span>");
					this.pageContext.getOut().println("Update covid data");
				this.pageContext.getOut().println("</a>");
				this.pageContext.getOut().println("<a class=\"nav-link\" href=\"./manage-user\">");
				this.pageContext.getOut().println("<span class=\"sb-nav-link-icon\"><i class=\"fas fa-user-lock\"></i></i></span>");
				this.pageContext.getOut().println("User management");
				this.pageContext.getOut().println("</a>");
			} 
			catch (IOException e) 
			{
				
			}
		}
		return SKIP_BODY;
	}
}