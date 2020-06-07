<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	if(session != null) 
	{
		session.invalidate();
	}

	response.sendRedirect("./login.jsp");
	
%>