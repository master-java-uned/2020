package tomas.es;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Driver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tomas.es.Conexion;
/**
 * Servlet implementation class HolaUsuarioServlet
 */
@WebServlet("/HolaUsuarioServlet")
public class HolaUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HolaUsuarioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h1>Pagina de entrada</h1>");
        Connection con=null;
        try {
            boolean existeUsuario = false;
            //Guardamos los datos enviados desde index
            String usuario = request.getParameter("usuario");
            String contraseña = request.getParameter("password");
            //Establecemos la conexion
            con = (Connection) Conexion.getConexion();
            System.out.println("la conexion es " + con);
            String consulta = "Select * from Usuarios where usuario=? && contraseña=?";
            ResultSet rs = null;
            PreparedStatement pst = null;
            pst = (PreparedStatement) con.prepareStatement(consulta);
            pst.setString(1, usuario);
            pst.setString(2, contraseña);
            rs = (ResultSet) pst.executeQuery();
 
            String nombre="";
            String rol="";
            while(rs.next()){
                //En caso de existir una coincidencia
                existeUsuario = true;
                //Y reemplazamos los atributos de dicho Usuario
                nombre = rs.getString("usuario");
                rol = rs.getString("rol");
            }
 
            if(existeUsuario){
                //Para el usuario existente:
                //Reemplazamos atributos que luego obtendremos desde las páginas .jsp
                request.setAttribute("nombre", nombre);
                request.setAttribute("rol", rol);
                HttpSession sesion=request.getSession();
                sesion.setAttribute("usuario", nombre);
                //Mandamos estos atributos a la página bienvenida.jsp
                if (rol.equals("empleado"))
                {
                	//request.getRequestDispatcher("/bienvenida.jsp").forward(request, response);
                	response.sendRedirect("bienvenida.jsp");
                }
                else
                {
                	response.sendRedirect("bienvenidaadministrador.jsp");
                	//request.getRequestDispatcher("/bienvenidaadministrador.jsp").forward(request, response);
                }
                
            }else{
                //De lo contrario vamos a la página errorLogin.jsp
                request.getRequestDispatcher("errorlogin.jsp").forward(request, response);
            }
            out.close();
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException|NullPointerException ex) {
            out.println("Fallo SQL");
            request.setAttribute("errorMessage", (String)ex.getMessage());
            request.setAttribute("errorCause", ex.getCause());
            request.setAttribute("errorLocation", (String)this.getServletName());

            request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
            }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h1>Mensaje desde método doPost desde MyServlet</h1>");
	}

}
