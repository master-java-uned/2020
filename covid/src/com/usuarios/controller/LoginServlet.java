package com.usuarios.controller;



import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.usuarios.dao.UsuariosDao;
import com.usuarios.model.usuarios;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	UsuariosDao usuarioDAO;
	 
	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		try {
 
			usuarioDAO = new UsuariosDao(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (Exception e) { /*-?|JSalacar_Review|carlosl.sanchez|c0|*/
			// TODO: handle exception
		} /*-|JSalacar_Review|carlosl.sanchez|c0|?*/
	}
	
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		try
		{	    

		     usuarios user = new usuarios();
		     
		     user.setUserid(request.getParameter("un"));
		     user.setPassword(request.getParameter("pw"));
		     
             
		     boolean valido = usuarioDAO.login(user);
			   		    
		     if (valido)
		     {
			        
		          HttpSession session = request.getSession(true);	    
		          session.setAttribute("currentSessionUser",user); 
		          response.sendRedirect("index.jsp"); //logged-in page      		
		     }
			        
		     else 
		          response.sendRedirect("invalidLogin.jsp"); //error page 
		} 
				
				
		catch (Throwable theException) 	     /*-?|JSalacar_Review|carlosl.sanchez|c1|*/
		{
		     System.out.println(theException); 
		}
		       } /*-|JSalacar_Review|carlosl.sanchez|c1|?*/
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
