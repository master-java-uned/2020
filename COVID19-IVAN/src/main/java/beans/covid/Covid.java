package beans.covid;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 *  Clase que contendra los datos del Covid.
 *  Se ignoran las propiedades del json en caso de que sean desconocidas,
 *  para que si el json de importacion cambia, se pueda importar igualmente.
 *  
 * @author @kalua66
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Covid 
{
	/**
	 * Fecha del registro
	 */
	private String dateRep;
	/**
	 * Dia del registro
	 */
	private int day;
	/**
	 * Mes del registro
	 */
	private int month;
	/**
	 * Año del registro
	 */
	private int year;
	/**
	 * Casos confirmados
	 */
	private int cases;
	/**
	 * Muertes
	 */
	private int deaths;
	/**
	 * Pais
	 */
	private String countriesAndTerritories;
	/**
	 * Identificador de geolocalizacion
	 */
	private String geoId;
	/**
	 * Codigo del pais
	 */
	private String countryterritoryCode;
	/**
	 * Poblacion
	 */
	private String popData2018;
	
	/**
	 * Continente
	 */
	private String continentExp;
	
	
	/**
	 * Contructor vacio
	 */
	public Covid() 
	{
		
	}	
	
	/**
	 * 
	 * Constructor con todos los parametros de la clase.
	 * 
	 * @param dateRep Fecha del registro
	 * @param day Dia del registro
	 * @param month Mes del registro
	 * @param year Año del registro
	 * @param cases Casos confirmados
	 * @param deaths Muertes
	 * @param countriesAndTerritories Pais
	 * @param geoId Localizacion
	 * @param countryterritoryCode Codigo del pais
	 * @param popData2018 Poblacion
	 * @param continentExp Continente
	 */
	public Covid(String dateRep, int day, int month, int year, int cases, int deaths, String countriesAndTerritories,
				 String geoId, String countryterritoryCode, String popData2018, String continentExp) 
	{
		this.dateRep = dateRep;
		this.day = day;
		this.month = month;
		this.year = year;
		this.cases = cases;
		this.deaths = deaths;
		this.countriesAndTerritories = countriesAndTerritories;
		this.geoId = geoId;
		this.countryterritoryCode = countryterritoryCode;
		this.popData2018 = popData2018;
		this.continentExp = continentExp;
	}
	/**
	 * @return La fecha.
	 */
	public String getDateRep()
	{
		return dateRep;
	}
	/**
	 * @param dateRep Establece la fecha.
	 */
	public void setDateRep(String dateRep)
	{
		this.dateRep = dateRep;
	}
	/**
	 * @return El dia.
	 */
	public int getDay()
	{
		return day;
	}
	/**
	 * @param day Establece el dia.
	 */
	public void setDay(int day)
	{
		this.day = day;
	}
	/**
	 * @return El mes.
	 */
	public int getMonth()
	{
		return month;
	}
	/**
	 * @param month Establece el mes.
	 */
	public void setMonth(int month)
	{
		this.month = month;
	}
	/**
	 * @return El año.
	 */
	public int getYear()
	{
		return year;
	}
	/**
	 * @param year Establece el año.
	 */
	public void setYear(int year)
	{
		this.year = year;
	}
	/**
	 * @return Los casos.
	 */
	public int getCases()
	{
		return cases;
	}
	
	/**
	 * @return Los casos formateados en formato español, usando el punto separador y coma decimal.
	 */
	public String getCasesFormated() 
	{
		NumberFormat nf = NumberFormat.getNumberInstance(new Locale("es","ES"));
		nf.setMaximumFractionDigits(2);
		DecimalFormat df = (DecimalFormat)nf;
		
		 return df.format(cases);
	}

	/**
	 * @return Las muertes formateados en formato español, usando el punto separador y coma decimal.
	 */
	public String getDeathsFormated() 
	{
		NumberFormat nf = NumberFormat.getNumberInstance(new Locale("es","ES"));
		nf.setMaximumFractionDigits(2);
		DecimalFormat df = (DecimalFormat)nf;
		
		 return df.format(deaths);
	}
	
	/**
	 * @param cases Establece los casos.
	 */
	public void setCases(int cases) 
	{
		this.cases = cases;
	}
	
	/**
	 * @return Las muertes
	 */
	public int getDeaths() 
	{
		return deaths;
	}
	
	/**
	 * @return El continente 
	 */
	public String getContinentExp() 
	{
		return continentExp;
	}
	
	/**
	 * @param continentExp Establece el continente
	 */
	public void setContinentExp(String continentExp) 
	{
		this.continentExp = continentExp;
	}
	
	/**
	 * @param deaths Establece las muertes
	 */
	public void setDeaths(int deaths)
	{
		this.deaths = deaths;
	}
	
	/**
	 * @return El pais
	 */
	public String getCountriesAndTerritories() 
	{
		return countriesAndTerritories;
	}
	
	/**
	 * @param countriesAndTerritories Establece el pais
	 */
	public void setCountriesAndTerritories(String countriesAndTerritories)
	{
		this.countriesAndTerritories = countriesAndTerritories;
	}
	
	/**
	 * @return El identifcador de localizacion
	 */
	public String getGeoId() 
	{
		return geoId;
	}
	
	/**
	 * @param geoId Establecer el identificador de la localizacion
	 */
	public void setGeoId(String geoId) 
	{
		this.geoId = geoId;
	}
	
	/**
	 * @return El codigo del pais
	 */
	public String getCountryterritoryCode() 
	{
		return countryterritoryCode;
	}
	
	/**
	 * @param countryterritoryCode Establece el codigo del pais
	 */
	public void setCountryterritoryCode(String countryterritoryCode) 
	{
		this.countryterritoryCode = countryterritoryCode;
	}
	
	/**
	 * @return La poblacion
	 */
	public String getPopData2018() 
	{
		return popData2018;
	}
	
	/**
	 * @param popData2018 Establece la poblacion
	 */
	public void setPopData2018(String popData2018) 
	{
		this.popData2018 = popData2018;
	}

	@Override
	public String toString() 
	{
		return "Covid [dateRep=" + dateRep + ", day=" + day + ", month=" + month + ", year=" + year + ", cases=" + cases
				+ ", deaths=" + deaths + ", countriesAndTerritories=" + countriesAndTerritories + ", geoId=" + geoId
				+ ", countryterritoryCode=" + countryterritoryCode + ", popData2018=" + popData2018 + "]";
	}
}