package com.dvalera.acceso.controlador;

import java.io.*;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.servlet.annotation.MultipartConfig;
import java.io.File;
import java.util.ArrayList;
import org.apache.poi.xssf.usermodel.XSSFCell;
import com.dvalera.acceso.dao.BaseDatos;
import com.dvalera.acceso.modelo.Persona;
import com.dvalera.acceso.dao.Fichero;
 
 /**
  * 
  * @author d¡ego valera hernandez
  *
  */
@WebServlet("/gestServidor")
@MultipartConfig
public class GestServidor extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BaseDatos bd;
	File directorio;
	String miemail = null;
	Error error; 
 
    /**
     * Conexion inicial a Base de Datos con conector usuario y contraseña 
     * del administrador
     */
	public void init() {
		String jdbcURL = "jdbc:mysql://localhost:3306/persona?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String jdbcUsername = "root";
		String jdbcPassword = "dvalera";
		 
		try {
			 bd = new BaseDatos(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (Exception e) {
			 System.out.println(e);
		}
	}
 
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GestServidor() {
		super();
		// TODO Auto-generated constructor stub
	}
 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		 
		String action = request.getParameter("action");
		error = new Error();
		try {
			switch (action) {
			case "index":
				index(request, response);
				break;
			case "registrar":
				registrar(request, response);
				break;
			case "sesionRegistrar":
				sesionRegistrar(request, response);
				break;
			case "login":
				login(request, response);
				break;
			case "sesionLogin":
				sesionLogin(request, response);
				break;
			case "listaUsuarios":
			    listaUsuarios(request, response);
			    break;
			case "listaUsuariosSesion":
			    listaUsuariosSesion(request, response);
			    break;
			case "listaFicheros":
			    listaFicheros(request, response);
			    break;
			case "descargar":
				descargar(request, response);
				break;
			case "sesionDescargar":
				sesionDescargar(request, response);
				break;
			case "leer":
				leer(request, response);
				break;
			case "eliminar":
				eliminar(request, response);
				break;
			case "sesionEliminar":
				sesionEliminar(request, response);
				break;				 	 
			case "logout":
				logout(request, response);
				break;
			case "sesionLogout":
				sesionLogout(request, response);
				break;
			default:
				break;
			}			
		} catch (SQLException e) {
			e.getStackTrace();
		}				
	}
 
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Hola Servlet..");
		doGet(request, response);
	}
	
	/**
	 * mètodo index recibe paràmetros peticion y respuesta,y redirige la acciòn a la 
	 * interfaz de usuario
	 * @param request
	 * @param response
	 * @throws SQLException
	 * @throws ServletException
	 * @throws IOException
	 */
	private void index(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}
	
	/**
	 * mètodo que comprueba si el usuario està logeado, en caso
	 * afirmativo no es posible registrarse desde una sesiòn iniciada
	 */
	public void sesionRegistrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    HttpSession respuesta = request.getSession(true);
	    if(miemail==null) {
	       response.sendRedirect("registrar.jsp");
	    }
	    else {
	       respuesta.setAttribute("error", error.mensajeError(1));
	       RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
	  	   dispatcher.forward(request, response);
	    }
	}
	
	/**
	 * mètodo registrar recibe paràmetros peticion y respuesta,y redirige la acciòn a la 
	 * la pagina registrar.
	 * Si el registro ha sido correcto se le envia a la pàgina index, y desde allì se puede logear si lo 
	 * desea, y entrar para beneficiarse de nuevas opciones que le ofrece la pàgina index  
	 */
	private void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    HttpSession respuesta = request.getSession(true);
	        //Declaro e inicio las variables
	    String nombre = request.getParameter("nombre");
	    String apellidos = request.getParameter("apellidos");
	    String nombreUsuario = request.getParameter("usuario");
	    String emailUsuario = request.getParameter("email");
	    String password = request.getParameter("password");
	    String sesion = "f";
	    try {
             if(bd.emailRegistrado(emailUsuario)){
                 respuesta.setAttribute("error", error.mensajeError(2));
                 RequestDispatcher dispatcher = request.getRequestDispatcher("registrar.jsp");
                 dispatcher.forward(request, response);
             }else {  
                        //Llegado a este punto significa que todo esta correcto, por lo tanto ingreso a la DB
            	 Persona persona = new Persona(0, nombre, apellidos, emailUsuario, nombreUsuario, password, sesion);
                 if(bd.insertarUsuario(persona)) {    
                	 respuesta.setAttribute("error", error.mensajeError(3));         
                 } 
             }
        }catch (Exception e) { 
              System.out.println("Ocurrio la siguiente exception: " +e); 
        } 
	    RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
	    dispatcher.forward(request, response);       
	 }
	
	/**
	 * mètodo sesionLogin si està logeado no se puede logear denuevo y lo
	 * redirijo a index
	 */
	public void sesionLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    HttpSession respuesta = request.getSession(true);
	    if(miemail==null) {
	       response.sendRedirect("login.jsp");
	    }
	    else {
	       respuesta.setAttribute("error", error.mensajeError(1));
	       RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
	  	   dispatcher.forward(request, response);
	    }
	}
	/**
	 * mètodo login recibe paràmetros peticion y respuesta,y en el caso,
	 * de que se haya logeado bien, se le redirige la acciòn a la 
	 * interfaz de usuario(index), incluyendo en la pàgina nuevas opciones, en favor de usurio logeado,
	 * en caso contrario se le envia un mensaje indicandole que no ha sido posible la acciòn 
	 */
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    HttpSession respuesta = request.getSession(true);
        String email = request.getParameter("email");
        String password = request.getParameter("password");
	    try {                 
	    	 if(bd.emailRegistrado(email)){
	              //Significa que el email coincide
	    		if(bd.passwordRegistrado(password)) {
	    			//significa que usurio esta registrado
	    			Persona usuario = bd.obtenerPorEmail(email);
	                if(usuario!=null) {
	    			//activo sesion a usuario
	                	usuario.setSesion("t");	                	 
	                    if(bd.sesionActivar(usuario,email)) {	                      
	                	   String nombreusuario = usuario.getUsuario();
	                             //guardo email y nombre de usuario para comprobar si esta logeado en las vistas correspondientes	                      
	                	   respuesta.setAttribute("sessionUsuario", nombreusuario);
	                       respuesta.setAttribute("sessionEmail", email); 
	                       respuesta.setAttribute("sessionPassword", password);
	                       miemail = email;	                       
	                  }else {
	                	   respuesta.setAttribute("error", error.mensajeError(4));
	                       RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
	               	       dispatcher.forward(request, response);
	                  } 
	              }else {
	            	   respuesta.setAttribute("error", error.mensajeError(5));
                       RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
              	       dispatcher.forward(request, response);
	              }
	    		}else {
		            respuesta.setAttribute("error", error.mensajeError(6));
	                RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
	            	dispatcher.forward(request, response);
		          }
	         }else {
	             respuesta.setAttribute("error", error.mensajeError(7));
                 RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            	 dispatcher.forward(request, response);
	         }
	     
	    }catch (Exception e) {
	             System.out.println(e);
	    }
	   respuesta.setAttribute("error", error.mensajeError(1));
	   RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
	   dispatcher.forward(request, response);     
	 }
	/**
	 * metodo sesionlogout comprueba si el el usuario tiene permisos para realizar 
	 * la acciòn
	 */
	public void sesionLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    HttpSession respuesta = request.getSession(true);
		if(miemail!=null) {
	       response.sendRedirect("logout.jsp");
	    }
	    else {
	       respuesta.setAttribute("error", error.mensajeError(8));
	       RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
	  	   dispatcher.forward(request, response);
	    }
	}
	
	/**
   	 * mètodo logout cierra la sesion del usuario
     */
    public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
   	     response.setContentType("text/html;charset=ISO-8859-1");
    	 HttpSession respuesta = request.getSession();
    	 String email = request.getParameter("email");
    	 Persona persona = bd.obtenerPorEmail(email);
         try {
		    if(bd.sesionDesactivar(persona,email)) {
		    	respuesta.setAttribute("error", error.mensajeError(9));
		    	RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		  		dispatcher.forward(request, response);
	   		    //Cerrar sesion
	   	        respuesta.setAttribute("sessionEmail", null);
	   	        miemail=null;
	   	        respuesta.invalidate();
	        }else {
	        	respuesta.setAttribute("error", error.mensajeError(7));
	        	RequestDispatcher dispatcher = request.getRequestDispatcher("logout.jsp");
	     		dispatcher.forward(request, response);
            }
		 
         }catch (Exception ex) {
        	 System.out.println(ex);
         }
    }
       		        	
	/**
	 * mètodo listaNombreUsuarios crea un array de strings con los nombres de usuario registrados,
	 * y su email
	 */
	 private void listaUsuarios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		HttpSession respuesta = request.getSession(true);
		response.setContentType("text/html;charset=UTF=8");
	    List<Persona> lUsuarios = bd.listaNombreUsuarios();
	    if(lUsuarios!=null && lUsuarios.size()>0) {
	    	request.setAttribute("lista", lUsuarios);
	    	respuesta.setAttribute("error", error.mensajeError(10));
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("listar.jsp");
	 	    dispatcher.forward(request, response);
		}
	    else {
	    	respuesta.setAttribute("error", error.mensajeError(11));
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
	 	    dispatcher.forward(request, response);
	    }
	} 
	 
    /**
	 * mètodo listaUsuariosSesion crea un array de strings con los nombres de usuarios 
	 * que estàn actualmente en sesiòn
	 */
	private void listaUsuariosSesion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		HttpSession respuesta = request.getSession(true);
		response.setContentType("text/html;charset=UTF=8");
	    List<Persona> lUsuarios = bd.usuariosSesion();
	    if(lUsuarios!=null) {	    	 
	    	respuesta.setAttribute("error", error.mensajeError(12));
	    	request.setAttribute("lista", lUsuarios);
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("conectados.jsp");
	 	    dispatcher.forward(request, response);
		}
	    else {
	    	respuesta.setAttribute("error", error.mensajeError(11));
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
	  	    dispatcher.forward(request, response);
	    }
	} 
	
	 /**
	  * mètodo sesionDescargar, comprueba si tiene permiso(està logeado) para realizar la descarga
	  * de un fichero XLS
	  */
	 public void sesionDescargar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		 HttpSession respuesta = request.getSession(true);
		 if(miemail!=null) {
			       response.sendRedirect("descargar.jsp");
       }
	    else {
	    	respuesta.setAttribute("error", error.mensajeError(1));
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
	  	    dispatcher.forward(request, response);
	    }
    }

	/**
	 * metodo descargar, descarga el fichero seleccionado XLS
	 * en la ruta especificada(C:\\ejemploCRUD-MVC\\ficherosXLS\\"+fileName)
	 */
	private void descargar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 	response.setContentType("text/html;charset=ISO-8859-1");
		HttpSession respuesta = request.getSession(true);
	    Part filePart = request.getPart("file");
	    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); //MSIE fix.
	    File directorio = new java.io.File("C:\\ejemploCRUD-MVC\\ficherosXLS\\"+fileName);
	    directorio.getParentFile().mkdirs(); 
	 	if (!directorio.exists()) {
	 	       directorio.createNewFile();
	 	} 
	    InputStream fileContent = filePart.getInputStream();
	    response.setHeader("Content-Disposition","attachment;filename=\"" + fileName + "\"");
	    try {
	    	 Fichero fichero = new Fichero();	        
	    	 OutputStream out = new FileOutputStream(directorio);	      
	         BufferedInputStream infile = new BufferedInputStream(fileContent);         
	         fichero.descargar(out, infile);	      
	    }catch(Exception ex) {
	    	respuesta.setAttribute("error",  error.mensajeError(13));
	    	System.out.println(ex);
	    }
	    respuesta.setAttribute("error", error.mensajeError(14));
	 	RequestDispatcher dispatcher = request.getRequestDispatcher("descargar.jsp");
		dispatcher.forward(request, response); 
	}
	
	 /**
		 * mètodo listaFicheros crea un array de strings con los nombres ficheros descargados
		 * para realizar la accion debe estar logeado el usuario
	  */
      private void listaFicheros(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
    	  HttpSession respuesta = request.getSession(true);
    	  if(miemail != null) {	      
    	     File directorio = new java.io.File("C:\\ejemploCRUD-MVC\\ficherosXLS\\");
  	         directorio.getParentFile().mkdirs(); 
  	 	     if (!directorio.exists()) {
  	 	       directorio.createNewFile();
  	 	     } 
		     Fichero fichero = new Fichero();
		     String[] lFicheros = fichero.retunFicheros(directorio);
		     if(lFicheros == null) {
		    	respuesta.setAttribute("error", error.mensajeError(15));
		     }
		     else {
			    respuesta.setAttribute("error", error.mensajeError(16));
		    	respuesta.setAttribute("array", lFicheros);
		     }		                                         		    	
	         RequestDispatcher dispatcher = request.getRequestDispatcher("ficherosXLS.jsp");
	         dispatcher.forward(request, response);
    	  }else {
    		 respuesta.setAttribute("error", error.mensajeError(8));
    		 RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
 	         dispatcher.forward(request, response);
    	  }
	  } 
	  
	/**
	 * mètodo leer recibe el fichero a leer desde la vista, si lo encuntra en el directorio
	 * lo envia en una estructura de datos tipo lista doble para ser leido desde la ventana leer
	 */	
	 private void leer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		  response.setContentType("text/html;charset=ISO-8859-1");
		  HttpSession respuesta = request.getSession(true);
	      String mifile = request.getParameter("file");
	      File directorio = new java.io.File("C:\\ejemploCRUD-MVC\\ficherosXLS\\"+mifile);
	      directorio.getParentFile().mkdirs(); 
	 	  if (!directorio.exists()) {
	 	       directorio.createNewFile();
	 	  } 
		  Fichero fichero = new Fichero();
		 
	      List<List<XSSFCell>> listaXLS = new ArrayList<List<XSSFCell>>();
	      listaXLS = fichero.leer(directorio);
	      if(listaXLS==null) {
	    	  respuesta.setAttribute("error", error.mensajeError(17)); 
	    	  RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
	  		  dispatcher.forward(request, response); 
	      }
	      respuesta.setAttribute("listaXLS", listaXLS);
	      respuesta.setAttribute("error", "Fichero "+directorio.getName());
		}catch(Exception ex) {
			 System.out.println(ex);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("leer.jsp");
		dispatcher.forward(request, response); 
	 }
	 
	 /**
	  * metodo sesionEliminar comprueba si el usuario esta logeado, para
	  * poder darse de baja en el sistema
	  */
	 public void sesionEliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		HttpSession respuesta = request.getSession(true);
		if(miemail!=null) {
		       response.sendRedirect("eliminar.jsp");
		}
		else {
			respuesta.setAttribute("error", error.mensajeError(8));
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
	  	    dispatcher.forward(request, response);
		       
		}
	 }
 	    
    /**
   	 * mètodo eliminar, usuario se da de baja
	 */
    private void eliminar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
    	response.setContentType("text/html;charset=ISO-8859-1");
    	HttpSession respuesta = request.getSession(true);
		String email = request.getParameter("email");
		if(bd.eliminarUsuario(email)) {
	   		    //Cerrar sesion
	   	    respuesta.setAttribute("sessionEmail", null);
	   	    miemail=null;
	   	    respuesta.invalidate();
	    }else {
	    	respuesta.setAttribute("error", error.mensajeError(8));
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("mensaje.jsp");
			dispatcher.forward(request, response);
        }
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);		
	}
}  
    
   
    