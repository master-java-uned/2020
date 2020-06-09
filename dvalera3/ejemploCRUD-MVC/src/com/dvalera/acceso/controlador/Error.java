package com.dvalera.acceso.controlador;

import java.io.IOException;
/**   
 * Implementation class Error
 * @author d¡ego valera hernandez
 *
 */
public class Error {
	String error = "";
	
	/**
	 * Constructor vacio de la class Error
	 */
	public Error() {}
	
    //mètodo mensajeError recibe un indice de tipo int, e indexa al error ocurrido	 
	public String mensajeError(int indice) throws IOException {		 
		try {
		  switch (indice) {
			case 1:
				error="Està logeado";
				break;
			case 2:
				error="Esta direccion de correo ya fue registrada.";
				break;
			case 3:
				error="Registro correcto.";
				break;
			case 4:
				error="Usuario no existe."; 
				break;
			case 5:
				error="No se ha podido activar sesion.";
				break;
			case 6:
			    error="No coincide su password.";
			    break;
			case 7:
			    error="No coincide su email.";
			    break;
			case 8:
				error="No està logeado."; 
			    break;
			case 9:
				error="Se ha desconectado."; 
				break;
			case 10:
			    error="Esta es la Lista de Usuarios.";
			    break;
			case 11:
			    error="Lista de Usuarios vacia.";
			    break;
			case 12:
			    error="Lista de Usuarios en Sesiòn.";
			    break;
			case 13:
			    error="No se ha podido procesar la descarga.";
			    break;
			case 14:
			    error="Descarga completada con èxito.";
			    break;
			case 15:
			    error="No existe ningun fichero.";
			    break;
			case 16:
			    error="Lista de Ficheros.";
			    break;
			case 17:
			    error="Fichero no existe.";
			    break;
			default:
				break;
		  }			
		} catch (Exception e) {
			e.printStackTrace();
		}
	   return error;
	}

}
