package controler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import beans.covid.Covid;
import model.CovidDAOImpl;
import util.CovidRecods;


/**
 * Clase encargargada de procesar los archivos que se
 * van a subir para actulizar la base de datos.
 *
 * @author @kalua66
 *
 */
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet 
{
	/**
	 * url donde se subiran los ficheros
	 */
	private final String UPLOAD_DIRECTORY = "/upload/";
	
    private static final long serialVersionUID = 1L;
    
	/**
	 * Peticion por metodo GET. Redirecionada a metodo Post !
	 * 
	 * @param request La peticion.
	 * @param response La respuesta.
	 * @throws ServletException 
	 * @throws IOException 
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request, response);
    }
    
	/**
	 * Peticion por metodo POST.
	 * 
	 * @param request La peticion.
	 * @param response La respuesta.
	 * @throws ServletException 
	 * @throws IOException 
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	HttpSession session = request.getSession();
    	/**
    	 * Nombre del fichero que lo almaceno 
    	 * para luego leer dicho fichero.
    	 */
        String nombreFile = "";
		
        /**
         * Manejo la subida de los archivos.
         */
        if (ServletFileUpload.isMultipartContent(request))
        {
            try
            {
                List <FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                for (FileItem item: multiparts)
                {
                    if (!item.isFormField())
                    {
                    	String serverPath= getServletContext().getRealPath("/");
                    	
                        String name = new File(item.getName()).getName();
                        
                        nombreFile = serverPath + UPLOAD_DIRECTORY + File.separator + name;
                        
                        item.write(new File(nombreFile)); 
                    }
                }
                System.out.println("File uploaded successfully.");
            } 
            catch (Exception ex)
            {
            	System.err.println("File upload failed due to : " + ex);
            }
        }
        else
        {
            System.err.println("Sorry this servlet only handles file upload request.");
        }
	
        /**
         * Si es xlsx lo tratamos de una forma.
         */
        if(nombreFile.endsWith(".xlsx")) 
        {
        	try 
	        {
	        	readAndSaveXLSX(nombreFile);
			}
	        catch (IOException e1)
	        {
				e1.printStackTrace();
			}
	        catch (ParseException e1)
	        {
				e1.printStackTrace();
			}
       }
       
        /**
         * Si es json lo tratamos de otra forma.
         */ 
       if(nombreFile.endsWith(".json")) 
       {
	       readAndSaveJSON(nombreFile);
       }
       
       
       /**
        * Redirigimos a un relogin, no puedo usar el login ya que esta en ajax.
        */
       session.setAttribute("actual-page","index");
       request.getRequestDispatcher("./relogin").forward(request,response);
    }

    /**
     * Se encarga de procesar el Json de covid, e introducirlo en la base de datos.
     * 
     * @param file Archivo
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
	public void readAndSaveJSON(String file) throws JsonParseException, JsonMappingException, IOException 
	{
		ObjectMapper objectMapper = new ObjectMapper();

		InputStream input = new FileInputStream(new File(file));
		
		CovidRecods record = objectMapper.readValue(input, CovidRecods.class);
		
		CovidDAOImpl covidDAO = new CovidDAOImpl();

		/**
		 * Limpio datos anteriores
		 */
		covidDAO.deleteAllCovid();
		
		/**
		 * Lo recorro y los voy insertando en la base de datos.
		 */
		for (Covid covid : record.records) 
		{
			System.out.println(covid.getDay());
			if(covid != null)
		    {
		    	covidDAO.insertCovid(covid);
		    }
		}
	}
	

	/**
	 * Se encarga de procesar el xlsx de covid, e introducirlo en la base de datos.
	 * 
	 * @param file
	 * @throws IOException
	 * @throws ParseException
	 */
	public void readAndSaveXLSX(String file) throws IOException, ParseException 
	{
		FileInputStream inputStream = new FileInputStream(new File(file));
		
		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet firstSheet = workbook.getSheetAt(0);
		Iterator<Row> iterator = firstSheet.iterator();
		
		int countRow = 0;
		CovidDAOImpl covidDAO = new CovidDAOImpl();

		/**
		 * Limpio datos anteriores
		 */
		covidDAO.deleteAllCovid();
		
		Covid covid = null;
		
		/**
		 * Recorro cada fila
		 */
		while (iterator.hasNext()) 
		{
		    Row nextRow = iterator.next();
		    Iterator<Cell> cellIterator = nextRow.cellIterator();
		    
		    int countCell = 0;
		    
		    if(countRow > 0) 
		    {
		    	covid = new Covid();
		    	
		    	/**
				 * Recorro cada columna, y dependiendo el tipo 
				 * que sea lo manejo de una forma o de otra.
				 */
			    while (cellIterator.hasNext()) 
			    {
			        Cell cell = cellIterator.next();
			         
			        switch (countCell) 
			        {
				    	case 0:
				    		covid.setDateRep(cell.toString());
				        	break;
				        case 1:
				            covid.setDay((int)cell.getNumericCellValue());
				            break;
				        case 2:
				        	covid.setMonth((int)cell.getNumericCellValue());
				            break;
				        case 3:
				        	covid.setYear((int)cell.getNumericCellValue());
				            break;
				        case 4:
				        	covid.setCases((int)cell.getNumericCellValue());
				            break;
				        case 5:
				        	covid.setDeaths((int)cell.getNumericCellValue());
				            break;
				            
				        case 6:
				            covid.setCountriesAndTerritories(cell.getStringCellValue());
				            break;
				        case 7:
				        	covid.setGeoId(cell.getStringCellValue());
				            break;
				        case 8:
				        	covid.setCountryterritoryCode(cell.getStringCellValue());
				            break;
				        case 9:
				        	 if(cell.toString().trim() != "") 
				        	 {
				        		 try 
				        		 {
									 covid.setPopData2018(Double.valueOf(cell.getNumericCellValue()).longValue() + "");
				        		 } 
				        		 catch (Exception e) 
				        		 {
									 covid.setPopData2018("");
				        		 }
				        	 }
				        	 else 
				        	 {
				        		 covid.setPopData2018("");
				        	 }
				             
				            break;
				        case 10:
					    		covid.setContinentExp(cell.toString());
				            break;
			        }
			        countCell++;
			    }
			    if(covid != null)
			    {
			    	covidDAO.insertCovid(covid);
			    }
		    }
		    countRow++;
		}
		inputStream.close();
	}
}