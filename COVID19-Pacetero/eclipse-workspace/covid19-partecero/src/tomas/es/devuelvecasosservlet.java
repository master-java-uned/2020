package tomas.es;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class devuelvecasosservlet
 */
@WebServlet("/devuelvecasosservlet")
public class devuelvecasosservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public devuelvecasosservlet() {
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
        out.println("<h1>Mensaje desde método doGet desde MyServlet</h1>");
        Connection con =null;
         ResultSet rs = null;
		 PreparedStatement  pst= null;  
         //  CreateStatement pst = null;
          
           
           try {
               
               //Guardamos los datos enviados desde index
               String usuario = request.getParameter("usuario");
               String contraseña = request.getParameter("password");
               //Establecemos la conexion
               con = (Connection) Conexion.getConexion();
               System.out.println("la conexion es " + con);
               String FilePath = request.getParameter("country-area");
               System.out.println(FilePath);
               String consulta= "SELECT sum(cases) as casostotales, sum(deaths) as muertestotales FROM datos WHERE countriesAndTerritories=?";
               System.out.println(consulta);      
               pst = (PreparedStatement) con.prepareStatement(consulta);
               pst.setString(1, FilePath);
               
               rs = (ResultSet) pst.executeQuery();
    
               String casos="";
               String muertes="";
               while(rs.next()){
                   //En caso de existir una coincidencia
                  
                   //Y reemplazamos los atributos de dicho Usuario
                   casos = rs.getString("casostotales");
                   muertes = rs.getString("muertestotales");
                   System.out.println(casos);
                   System.out.println(muertes);
               }
    
               
                   //Para el usuario existente:
                   //Reemplazamos atributos que luego obtendremos desde las páginas .jsp
                   request.setAttribute("casos", casos);
                   request.setAttribute("muertes", muertes);
                   request.setAttribute("country-area",FilePath);
                   //Mandamos estos atributos a la página bienvenida.jsp
                   this.getServletContext().getRequestDispatcher("/bienvenida.jsp").forward(request,response); 
                   
                   
               con.close();
              
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
	}

}
