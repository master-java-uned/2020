package model;

import java.sql.SQLException;
import java.util.List;
import beans.covid.Covid;

/**
 * Interfaz para el DAO de usuario.
 * @author @kalua66
 *
 */
public interface CovidDAO 
{
	/**
	 * Obtener el registro de Covid ,pasando su pais.
	 * @param username El nombre del usuario.
	 * @return El registro de Covid.
	 */
	Covid getCovid(String country);
	
	/**
	 * Obtener todos Registros del Covid.
	 * @return Lista de Covid.
	 * @throws SQLException 
	 */
    List<Covid> getAllCovid() throws SQLException;
    
    /**
     * Inserta un registro de Covid.
     * @param covid Los datos de Covid a insertar.
     * @return Si hizo la operacion fue exitosa o no.
     */
    boolean insertCovid(Covid covid);
    
    
    /**
     * Elimina todo el registro de covid.
     * @return Si hizo la operacion fue exitosa o no.
     */
    boolean deleteAllCovid();
}