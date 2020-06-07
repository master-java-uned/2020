<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import ="java.util.ArrayList"%>
<%@ page import ="java.util.List"%>
<%@ page import ="beans.covid.Covid"%>

<%@ taglib uri="WEB-INF/covidCountryTags.tld" prefix="table-view" %>
<%@ taglib uri="WEB-INF/quickFactsTags.tld" prefix="header" %>
<%@ taglib uri="WEB-INF/dataChartCountryTags.tld" prefix="script-data" %>
<%@ taglib uri="WEB-INF/checkLoginTags.tld" prefix="page-require" %>
<%@ taglib uri="WEB-INF/adminMenuTags.tld" prefix="menu" %>

<page-require:user-auth/>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <link rel="shortcut icon" href="images/favicon.png" type="image/png"/>
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>CovidDivStats 1.0</title>
        <link href="css/styles.css" rel="stylesheet" />
        <link href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css" rel="stylesheet" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"></script>
        <link href="css/jquery-jvectormap-2.0.5.css" rel="stylesheet" />

    </head>
    <body class="sb-nav-fixed">
        <nav class="sb-topnav navbar navbar-expand navbar-dark btn-primary">
            <a class="navbar-brand" href="index.html">Covid Stats</a><button class="btn btn-link btn-sm order-1 order-lg-0" id="sidebarToggle"><i class="fas fa-bars"></i></button>
            <!-- Navbar Search-->
            <form class="d-none d-md-inline-block form-inline ml-auto mr-0 mr-md-3 my-2 my-md-0">
               
            </form>
            <!-- Navbar-->
            <ul class="navbar-nav ml-auto ml-md-0">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="userDropdown" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="userDropdown">
                        <a class="dropdown-item" href="logout.jsp">Logout</a>
                    </div>
                </li>
            </ul>
        </nav>
        <div id="layoutSidenav">
            <div id="layoutSidenav_nav">
                <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                    <div class="sb-sidenav-menu">
                        <div class="nav">
                        	                        	
 							<header:quick-facts/>
 							
                            <div class="sb-sidenav-menu-heading">GENERAL</div>
                            
                            <a class="nav-link" href="./relogin">
                                <span class="sb-nav-link-icon"><i class="fas fa-rss"></i></span>
                                Stats
                            </a>
                            
                            <a class="nav-link collapse current" href="#" data-toggle="collapse" data-target="#collapseLayouts" aria-expanded="true" aria-controls="collapseLayouts">
                                <span class="sb-nav-link-icon">
                                    <i class="fas fa-globe-europe"></i></span>
                                Countries
                                <span class="sb-sidenav-collapse-arrow">
                                    <i class="fas fa-angle-down"></i>
                                </span>
                            </a>
                            <div class="collapse show" id="collapseLayouts" aria-labelledby="headingOne" data-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav">
                                	<a class="nav-link" href="./view?country=United_States_of_America">EEUU</a>
                                	<a class="nav-link" href="./view?country=Brazil">Brazil</a>
                                	<a class="nav-link" href="./view?country=France">France</a>
                                	<a class="nav-link" href="./view?country=Spain">Spain</a>
                                	<a class="nav-link" href="./view?country=Italy">Italy</a>
                                	<a class="nav-link" href="./view?country=Germany">Germany</a>
                                	<a class="nav-link" href="./view?country=China">China</a>
                                </nav>
                            </div>
                            <a class="nav-link" href="#">
                                <span class="sb-nav-link-icon"><i class="fas fa-table"></i></span>
                                Record
                            </a>
                            <a class="nav-link" href="#">
                                <span class="sb-nav-link-icon"><i class="fas fa-chart-area"></i></span>
                                Graphics
                            </a>
                            
							<menu:admin/> 
							                            
                        </div>
                    </div>
                    <div class="sb-sidenav-footer">
                        <div class="small">Logged in as:</div>
                        	<% out.println(session.getAttribute("username"));%>
                    </div>
                </nav>
            </div>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid">
                        <h1 class="mt-4"></h1>
                      	
                        <div class="row my-charts">
                        	<div class="col-xl-6">
                                <div class="card mb-4">
                                    <div class="card-header"><i class="fas fa-notes-medical"></i> Confirmed</div>
                                    <div class="card-body">
                                        <canvas id="myAreaChart" width="100%" height="40"></canvas>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-6">
                                <div class="card mb-4">
                                    <div class="card-header"><i class="fas fa-cross" style="margin-left:2px;"></i> Deceased</div>
                                    <div class="card-body">
                                        <canvas id="myAreaChart2" width="100%" height="40"></canvas>
                                    </div>
                                </div>
                            </div>
                            <!-- 
                            <div class="col-xl-6">
                                <div class="card mb-4">
                                    <div class="card-header"><i class="fas fa-notes-medical"></i> Confirmed /  <i class="fas fa-cross" style="margin-left:2px;"></i> Deceased</div>
                                    <div class="card-body">
                                        <canvas id="myBarChart2" width="100%" height="40"></canvas>
                                    </div>
                                </div>
                            </div>
                             -->
                        </div>
                        <div class="card mb-4">
                            <div class="card-header"><i class="fas fa-table mr-1"></i> PAIS </div>
                            <div class="card-body">
                                <div class="table-responsive">
                                
                                      <table-view:country><span></span></table-view:country>
                                      
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
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
        <div class="modal show" id="loadingModal" role="dialog">
		    <div class="modal-dialog">
				<div class="modal-content">
		        
			        <div class="modal-body">
			        	<div class="spinner-border text-dark" role="status">
							<span class="sr-only">Loading...</span>
						</div>
			        </div>
		        
				</div>
			</div>
		</div>
		
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
        <script src="js/scripts.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js"></script>
		<script src="js/jquery-jvectormap-2.0.5.min.js"></script>
		<script src="js/jquery-jvectormap-world-mill.js"></script>
		<script src="js/index.js"></script>
		
		<script-data:country-chart/>
		
    </body>
</html>