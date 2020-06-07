<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	if(session == null || session.getAttribute("login") == null || session.getAttribute("login").equals(false)) 
	{
    	response.sendRedirect("./login.jsp");
	}
	if(session.getAttribute("validate").equals(true)) 
	{
		response.sendRedirect("./relogin");
	}
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>CovidDivStats - Login</title>
        <link href="css/styles.css" rel="stylesheet" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/js/all.min.js"></script>
    </head>
    <body class="bg-primary">
    	<div id="layoutAuthentication">
            <div id="layoutAuthentication_content">
                <main>
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-lg-5">
                                <div class="card shadow-lg border-0 rounded-lg mt-5">
                                    <div class="card-header">
                                    	<h3 class="text-center font-weight-light my-4">Validate Account</h3>
                                    </div>
                                    <div class="card-body">
                                        <form action="validate" method="post">
                                            <div class="form-group">
                                            	<label class="small mb-1" >User: </label>
                                            	<b>
                                            	<% 
                                            		out.println(session.getAttribute("username"));
                                            	%>
                                            	</b>
                                            </div>
                                            <div class="form-group">
                                            	<label class="small mb-1" for="inputPassword">Code</label>
                                            	<input class="form-control input-validation" id="inputValidation" name="validation" type="text" placeholder="Enter code validation"/>
                                            </div>
                                            
                                            <div class="form-group d-flex align-items-center justify-content-between mt-4 mb-0">
                                            	<a class="small resendcode">Resend code</a>
                                            	<input class="btn btn-primary" name="submit" type="submit" value="Validate"/>
                                            </div>
                                        </form>
                                    </div>
                                    <div class="card-footer text-center">
                                        <div class="small">
                                        	<a href="logout.jsp">Is not you ? Log out!</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
            </div>
            <div id="layoutAuthentication_footer">
                <footer class="py-4 bg-light mt-auto">
                    <div class="container-fluid">
                        <div class="d-flex align-items-center justify-content-between small">
                            <div class="text-muted">Programa Modular Diseño y Desarrollo entornos web</div>
                            <div>
                                <a href="#">Privacy Policy</a>
                                &middot;
                                <a href="#">Terms &amp; Conditions</a>
                            </div>
                        </div>
                    </div>
                </footer>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
        <script src="js/scripts.js"></script>
    </body>
</html>