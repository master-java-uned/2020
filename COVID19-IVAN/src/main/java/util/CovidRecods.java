package util;

import java.util.List;
import beans.covid.Covid;

/**
 * Clase encargarda de recuperar el JSon de covid, 
 * que tiene la misma estructura: siendo una lista records de Covid.
 * 
 * @author @kalua66
 *
 */
public class CovidRecods 
{
	/**
	 * Lista de covid, la declaro publica porque no se si privada
	 * me pondria algun problema el paso de datos del Json al objeto.
	 */
	public List<Covid> records;
}