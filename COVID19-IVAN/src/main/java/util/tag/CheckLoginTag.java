package util.tag;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.tagext.TagSupport;


/**
 * Clase encargarda de ayudar a mantener un formato limpio 
 * en la vista, en este caso comprobar que ya hizo login.
 * 
 * @author @kalua66
 *
 */
public class CheckLoginTag extends TagSupport 
{
	private static final long serialVersionUID = 1L;

	public int doStartTag() 
	{
		HttpSession session = this.pageContext.getSession();
		
		HttpServletResponse response = (HttpServletResponse)this.pageContext.getResponse();

		if(session == null || session.getAttribute("login") == null || session.getAttribute("login").equals(false)) 
		{
			try 
			{
				response.sendRedirect("./login.jsp");
			} 
			catch (IOException e) 
			{
				
			}
		}
		return SKIP_BODY;
	}
}