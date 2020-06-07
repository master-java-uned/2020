<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>CovidDivStats - Register</title>
        <link href="css/styles.css?1" rel="stylesheet" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/js/all.min.js"></script>
    </head>
    <body class="bg-primary">
        <div id="layoutAuthentication">
            <div id="layoutAuthentication_content">
                <main>
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-lg-7">
                                <div class="card shadow-lg border-0 rounded-lg mt-5">
                                    <div class="card-header">
                                    	<h3 class="text-center font-weight-light my-4">Create Account</h3>
                                    </div>
                                    <div class="card-body">
                                        <form action="register" method="post">
                                             <div class="form-group">
                                            	<label class="small mb-1" for="inputUsername">User</label>
                                            	<input class="form-control input-username" id="inputUsername" name="username" type="text" aria-describedby="userHelp" placeholder="Enter user"/>
                                            </div>
                                            <div class="form-group">
                                            	<label class="small mb-1" for="inputEmailAddress">Email</label>
                                            	<input class="form-control" id="inputEmailAddress" type="email" name="email" aria-describedby="emailHelp" placeholder="Enter email address"/>
                                            </div>
                                            <div class="form-row">
                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                    	<label class="small mb-1" for="inputPassword">Password</label>
                                                    	<input class="form-control" id="inputPassword" type="password" name="password" placeholder="Enter password"/>
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                    	<label class="small mb-1" for="inputConfirmPassword">Confirm Password</label>
                                                    	<input class="form-control" id="inputConfirmPassword" type="password" name="confirm_password" placeholder="Confirm password"/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group mt-4 mb-0">
                                            	<input class="btn btn-primary btn-block" type="submit" name="submit" value="Create Account"/>
                                            </div>
                                        </form>
                                    </div>
                                    <div class="card-footer text-center">
                                        <div class="small"><a href="login.jsp">Have an account? Go to login</a></div>
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
