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
import java.io.FileInputStream;

import java.io.*;
import java.util.Iterator;
import java.util.Vector;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*; 

import org.apache.poi.ss.usermodel.Row;  
import org.apache.poi.xssf.usermodel.XSSFSheet;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFCell;
import java.sql.*;

/**
 * Servlet implementation class CargarExcelServlet
 */
@WebServlet("/CargarExcelServlet")
public class CargarExcelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CargarExcelServlet() {
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
        Connection con=null;
        Vector cellVectorHolder = new Vector();
		
		String[] cadenas=new String[11];
		int[] numeros= new int[11];
        try {
        	boolean existeUsuario = false;
            //Guardamos los datos enviados desde index
            String FilePath = request.getParameter("archivo");
            
            //Establecemos la conexion
            con = (Connection) Conexion.getConexion();
            System.out.println(FilePath);
            System.out.println("la conexion es " + con);
            String consulta="truncate table datos";
 					    ResultSet rs = null;
 					   
 			          //  CreateStatement pst = null;
 			            PreparedStatement pst = (PreparedStatement) con.prepareStatement(consulta);
 			            
 			            pst.execute();
 			            
            FilePath = "C:\\Users\\tomas\\Desktop\\test\\"+FilePath;
			  //  String FilePath = Common_Things.document_upload_path+"/"+name;
			System.out.println("Antes");
			InputStream fs = new FileInputStream(new File(FilePath));
			    
			     /** Create a POIFSFileSystem object**/
				// POIFSFileSystem myFileSystem = new POIFSFileSystem(fs);
			    
			     
			System.out.println("cargado sistema de archivos");
				 /** Create a workbook using the File System**/
			XSSFWorkbook myWorkBook = new XSSFWorkbook(fs);
			System.out.println("Cargado");

		        // Workbook wb = Workbook.getWorkbook(fs);
		    System.out.println("archivo cargado");
            System.out.println("Libro");
		         // TO get the access to the sheet
		        // Sheet sh = wb.getSheet("Sheet1");
		         /** Get the first sheet from workbook**/
			XSSFSheet mySheet = myWorkBook.getSheetAt(0);
			System.out.println("Hoja");
	         	// To get the number of rows present in sheet
	             
	         	

       		// To get the number of columns present in sheet
       		// int totalNoOfCols = sh.getColumns();
			/** We now need something to iterate through the cells.**/
			Iterator<Row> rowIter = mySheet.rowIterator();
			System.out.println("cargado iterador");
			while(rowIter.hasNext()){
		        // for (int row = 0; row < 8000; row++) {
			//System.out.println("fila recorrida");	
           /* String consulta = "Select * from Usuarios where usuario=? && contraseña=?";
            ResultSet rs = null;
            PreparedStatement pst = null;
            pst = (PreparedStatement) con.prepareStatement(consulta);
            pst.setString(1, usuario);
            pst.setString(2, contraseña);
            rs = (ResultSet) pst.executeQuery(*/
			 XSSFRow myRow = (XSSFRow) rowIter.next();
		     
 			 Iterator cellIter = myRow.cellIterator();
 			 //Vector cellStoreVector=new Vector();
 			 int i=0;
 			 int j=0;
 			
 			 while(cellIter.hasNext()){
	              XSSFCell myCell = (XSSFCell) cellIter.next();
	              CellType tipo=myCell.getCellType();
	              //cellStoreVector.addElement(myCell);
	              if (tipo==CellType.STRING)
	              {
	            	  
	            	  cadenas[i]=myCell.getStringCellValue();
	            	 // System.out.println(cadenas[i]);
	            	 // System.out.println(i);
	                  i=i+1;
	              }
		              else if(tipo==CellType.NUMERIC)
		              {
		            	  numeros[j]=(int)myCell.getNumericCellValue();
		            	 // System.out.println(numeros[j]);
		            	 // System.out.println(j);
		            	  j=j+1;
		              }
 			}
  			//cellVectorHolder.addElement(cellStoreVector);
  			consulta="insert into datos(dateRep,day,month,year,cases,deaths,countriesAndTerritories,geoId,countryterritoryCode,popData2018,continentExp)" + 
 					" values(?,?,?,?,?,?,?,?,?,?,?)";
 					    rs = null;
 					   
 			          //  CreateStatement pst = null;
 			            pst = (PreparedStatement) con.prepareStatement(consulta);
 			            pst.setInt(1, numeros[0]);
 			            pst.setInt(2, numeros[1]);
 			            pst.setInt(3, numeros[2]);
 			            pst.setInt(4, numeros[3]);
 			            pst.setInt(5, numeros[4]);
 			            pst.setInt(6, numeros[5]);
 			            pst.setString(7, cadenas[0]);
 			            pst.setString(8, cadenas[1]);
 			            pst.setString(9, cadenas[2]);
 			            pst.setInt(10, numeros[6]);
 			            pst.setString(11, cadenas[3]);
 			            pst.execute();
				}
			 con.close();
			 request.setAttribute("archivocreado", FilePath);
             
             //Mandamos estos atributos a la página bienvenida.jsp
             this.getServletContext().getRequestDispatcher("/bienvenidaadministrador.jsp").forward(request,response); 
             out.close();
        }
        catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException|NullPointerException ex) {
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
