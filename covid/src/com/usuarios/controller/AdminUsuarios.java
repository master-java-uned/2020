package com.usuarios.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 

import com.usuarios.dao.UsuariosDao;
import com.usuarios.model.usuarios;

 
/**
 * Servlet implementation class Adminusuarios
 */
@WebServlet("/adminusuarios")
public class AdminUsuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	UsuariosDao usuarioDAO;
 
	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		try {
 
			usuarioDAO = new UsuariosDao(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

 
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminUsuarios() {
		super();
		// TODO Auto-generated constructor stub
	}
 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Hola Servlet..");
		String action = request.getParameter("action");
		System.out.println(action);
		try {
			switch (action) {
			case "index":
				index(request, response);
				break;
			case "nuevo":
				nuevo(request, response);
				break;
			case "register":
				System.out.println("entro");
				registrar(request, response);
				break;
			case "mostrar":
				mostrar(request, response);
				break;
			case "showedit":
				showEditar(request, response);
				break;	
			case "editar":
				editar(request, response);
				break;
			case "eliminar":
				eliminar(request, response);
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Hola Servlet..");
		doGet(request, response);
	}
	
	private void index (HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		//mostrar(request, response);
		RequestDispatcher dispatcher= request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}
 
	private void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		usuarios usuario = new usuarios(0, request.getParameter("nombre"), request.getParameter("password"), request.getParameter("userid"));
		usuarioDAO.insertar(usuario);
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}
	
	private void nuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/register.jsp");
		dispatcher.forward(request, response);
	}
	
	
	private void mostrar(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException , ServletException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/mostrar.jsp");
		List<usuarios> listaUsuarios= usuarioDAO.listarUsuarios();
		request.setAttribute("lista", listaUsuarios);
		dispatcher.forward(request, response);
	}	
	
	private void showEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		usuarios usuario = usuarioDAO.obtenerPorId(Integer.parseInt(request.getParameter("id")));
		request.setAttribute("usuario", usuario);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/editar.jsp");
		dispatcher.forward(request, response);
	}
	
	private void editar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
	//	Articulo articulo = new Articulo(Integer.parseInt(request.getParameter("id")), request.getParameter("codigo"), request.getParameter("nombre"), request.getParameter("descripcion"), Double.parseDouble(request.getParameter("existencia")), Double.parseDouble(request.getParameter("precio")));
	//	articuloDAO.actualizar(articulo);
	//	index(request, response);
		
		usuarios usuario = new usuarios(Integer.parseInt(request.getParameter("id")), request.getParameter("nombre"), request.getParameter("password"), request.getParameter("userid"));
	    usuarioDAO.actualizar(usuario);
	    index(request, response);
		
	}
	
	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
	//	Articulo articulo = articuloDAO.obtenerPorId(Integer.parseInt(request.getParameter("id")));
	//	articuloDAO.eliminar(articulo);
	//	RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
	//	dispatcher.forward(request, response);
		
		usuarios usuario = usuarioDAO.obtenerPorId(Integer.parseInt(request.getParameter("id")));
		usuarioDAO.eliminar(usuario);
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
		
	}
}