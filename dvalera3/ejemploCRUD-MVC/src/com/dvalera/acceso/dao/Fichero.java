package com.dvalera.acceso.dao;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 
 * @author d¡ego valera hernandez
 *
 */
public class Fichero {
	List<List<XSSFCell>> cellDataList;
	List<XSSFCell> primeraFila;
	
	/**
	 * Constructor class Fichero
	 */
	public Fichero() {
		cellDataList = new ArrayList<List<XSSFCell>>();	
		primeraFila = new ArrayList<XSSFCell>();	
	}
	/**
	 * metodo descargar, descarga un fichero y lo guarda en la ruta donde
	 * se encuentra el directorio que contiene los ficheros descargados
	 * del sitpo xlsx
	 * @param bfout para escribir en el fichero de destino
	 * @param infile, flujo de salida donde se va a escribir el fichero
	 * @throws IOException excepcion del tipo entrada salida en el casso de que
	 * encuentre algùn problema se activa
	 */
	public void descargar(OutputStream out, BufferedInputStream infile) throws IOException {
		 
		try {		       
		        byte[] tmp = new byte[8192];
		        int c;
		        while ((c = infile.read(tmp)) > 0) {
		                out.write(tmp, 0, c);
		        }
		        out.flush();
		        infile.close();
		}catch(Exception ex) {
		    	System.out.println(ex);
		}
	}	
	 
	/**
	 * metodo leer que devuelve el fichero xls seleccionado
	 * @param String fileXLS
	 * @return cellDataList arrayList doble que contiene el fichero
	 * @throws IOException
	 */	  
	 public List<List<XSSFCell>> leer(File ficheroXLS) throws IOException {		 
	 	if(ficheroXLS.exists()) {
	 	    try{
	 		    FileInputStream fileInputStream = new FileInputStream(ficheroXLS);
	 		    XSSFWorkbook workBook = new XSSFWorkbook(fileInputStream);
	 		    XSSFSheet hssfSheet = workBook.getSheetAt(0);
	 		    Iterator<Row> rowIterator = hssfSheet.rowIterator();
	 		           //paso la tabla xls a array list
	 		           /*filas*/
	 		    while (rowIterator.hasNext()){
	 		        XSSFRow hssfRow = (XSSFRow) rowIterator.next();
	 		        Iterator<Cell> iterator = hssfRow.cellIterator();
	 		        List<XSSFCell> cellTempList = new ArrayList<XSSFCell>();
	 		           /*columnas*/
	 		        while(iterator.hasNext()){	 		        	 
	 		            XSSFCell hssfCell = (XSSFCell) iterator.next();
	 		            cellTempList.add(hssfCell);
	 		            cellTempList.add(hssfCell);
	 		        }      //inserto una fila completa 		     
	 		           cellDataList.add(cellTempList);	 		       
	 		    } 
	 		    workBook.close();
	 		 }catch(Exception e){
	 		     e.printStackTrace();
	 		 }
	 	 }
	 	return cellDataList;
	  }
	 
	/**
	 * metodo retunFile devuelve una lista con todos los ficheros contenidos
	 * en el directorio que se le pasa como parametro 
	 * @param directorio, del tipo File es el directorio donde se encuentran los ficheros 
	 * @return, un String[] con el nombre de los ficheros 
	 */
	public String[] retunFicheros(File directorio) {
		return directorio.list();
	}
			
}
