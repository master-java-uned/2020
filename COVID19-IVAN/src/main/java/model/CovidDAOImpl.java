package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import beans.covid.Covid;
import database.DataBase;

/**
 * Clase encargada de implementar los metodos 
 * de la interfaz para acceso a datos del Covid.
 * 
 * @author @kalua66
 *
 */
public class CovidDAOImpl implements CovidDAO
{
	/**
	 * Aqui mapeare la relacion de pais con el nombre 
	 * de pais formateado para visualizar.
	 */
	private HashMap<String, String> countryNames = new HashMap<String, String>();
	/**
	 * Aqui mapeare la relacion del codigo de pais con el codigo de 
	 * pais de 2 caracteres para mostrar la imagen bandera.
	 */
	private HashMap<String, String> countryCode = new HashMap<String, String>();
	
	/**
	 * Constructor de la clase vacio.
	 * Mapeo los nombres y el codigo en la inicializacion.
	 */
	public CovidDAOImpl() 
	{
		setCountryNames();
		setCountryCodes();
	}

	/**
	 * Establezco los codigos para menejarlos.
	 */
	private void setCountryCodes() 
	{
		countryCode.put("Cote_dIvoire","CI");
		countryCode.put("San_Marino","SM");
		countryCode.put("Burkina_Faso","BF");
		countryCode.put("Andorra","AD");
		countryCode.put("Bosnia_and_Herzegovina","BH");
		countryCode.put("Bahrain","BH");
		countryCode.put("Singapore","SG");
		countryCode.put("Bangladesh","BD");
		countryCode.put("Belgium","BE");
		countryCode.put("Burkina Faso","BF");
		countryCode.put("Bulgaria","BG");
		countryCode.put("Bosnia and Herz.","BA");
		countryCode.put("Brunei","BN");
		countryCode.put("Bolivia","BO");
		countryCode.put("Japan","JP");
		countryCode.put("Burundi","BI");
		countryCode.put("Benin","BJ");
		countryCode.put("Bhutan","BT");
		countryCode.put("Jamaica","JM");
		countryCode.put("Botswana","BW");
		countryCode.put("Brazil","BR");
		countryCode.put("Bahamas","BS");
		countryCode.put("Belarus","BY");
		countryCode.put("Belize","BZ");
		countryCode.put("Russia","RU");
		countryCode.put("Rwanda","RW");
		countryCode.put("Serbia","RS");
		countryCode.put("Timor-Leste","TL");
		countryCode.put("Turkmenistan","TM");
		countryCode.put("Tajikistan","TJ");
		countryCode.put("Romania","RO");
		countryCode.put("Guinea-Bissau","GW");
		countryCode.put("Guatemala","GT");
		countryCode.put("Greece","GR");
		countryCode.put("Eq. Guinea","GQ");
		countryCode.put("Guyana","GY");
		countryCode.put("Georgia","GE");
		countryCode.put("United_Kingdom","GB");
		countryCode.put("Gabon","GA");
		countryCode.put("Guinea","GN");
		countryCode.put("Gambia","GM");
		countryCode.put("Greenland","GL");
		countryCode.put("Ghana","GH");
		countryCode.put("Oman","OM");
		countryCode.put("Tunisia","TN");
		countryCode.put("Jordan","JO");
		countryCode.put("Croatia","HR");
		countryCode.put("Haiti","HT");
		countryCode.put("Hungary","HU");
		countryCode.put("Honduras","HN");
		countryCode.put("Puerto_Rico","PR");
		countryCode.put("Palestine","PS");
		countryCode.put("Portugal","PT");
		countryCode.put("Paraguay","PY");
		countryCode.put("Panama","PA");
		countryCode.put("Papua New Guinea","PG");
		countryCode.put("Peru","PE");
		countryCode.put("Pakistan","PK");
		countryCode.put("Philippines","PH");
		countryCode.put("Poland","PL");
		countryCode.put("Zambia","ZM");
		countryCode.put("W. Sahara","EH");
		countryCode.put("Estonia","EE");
		countryCode.put("Egypt","EG");
		countryCode.put("South_Africa","ZA");
		countryCode.put("Ecuador","EC");
		countryCode.put("Italy","IT");
		countryCode.put("Vietnam","VN");
		countryCode.put("Solomon Is.","SB");
		countryCode.put("Ethiopia","ET");
		countryCode.put("Somalia","SO");
		countryCode.put("Zimbabwe","ZW");
		countryCode.put("Spain","ES");
		countryCode.put("Eritrea","ER");
		countryCode.put("Montenegro","ME");
		countryCode.put("Moldova","MD");
		countryCode.put("Madagascar","MG");
		countryCode.put("Morocco","MA");
		countryCode.put("Uzbekistan","UZ");
		countryCode.put("Myanmar","MM");
		countryCode.put("Mali","ML");
		countryCode.put("Mongolia","MN");
		countryCode.put("North_Macedonia","MK");
		countryCode.put("Malawi","MW");
		countryCode.put("Mauritania","MR");
		countryCode.put("Uganda","UG");
		countryCode.put("Malaysia","MY");
		countryCode.put("Mexico","MX");
		countryCode.put("Israel","IL");
		countryCode.put("France","FR");
		countryCode.put("Somaliland","XS");
		countryCode.put("Finland","FI");
		countryCode.put("Fiji","FJ");
		countryCode.put("Falkland Is.","FK");
		countryCode.put("Nicaragua","NI");
		countryCode.put("Netherlands","NL");
		countryCode.put("Norway","NO");
		countryCode.put("Namibia","NA");
		countryCode.put("Vanuatu","VU");
		countryCode.put("New Caledonia","NC");
		countryCode.put("Niger","NE");
		countryCode.put("Nigeria","NG");
		countryCode.put("New_Zealand","NZ");
		countryCode.put("Nepal","NP");
		countryCode.put("Kosovo","XK");
		countryCode.put("Côte d'Ivoire","CI");
		countryCode.put("Switzerland","CH");
		countryCode.put("Colombia","CO");
		countryCode.put("China","CN");
		countryCode.put("Cameroon","CM");
		countryCode.put("Chile","CL");
		countryCode.put("N. Cyprus","XC");
		countryCode.put("Canada","CA");
		countryCode.put("Congo","CG");
		countryCode.put("Central African Rep.","CF");
		countryCode.put("Dem. Rep. Congo","CD");
		countryCode.put("Czech_Republic","CZ");
		countryCode.put("Cyprus","CY");
		countryCode.put("Costa_Rica","CR");
		countryCode.put("Cuba","CU");
		countryCode.put("Swaziland","SZ");
		countryCode.put("Syria","SY");
		countryCode.put("Kyrgyzstan","KG");
		countryCode.put("Kenya","KE");
		countryCode.put("S. Sudan","SS");
		countryCode.put("Suriname","SR");
		countryCode.put("Cambodia","KH");
		countryCode.put("El Salvador","SV");
		countryCode.put("Slovakia","SK");
		countryCode.put("South_Korea","KR");
		countryCode.put("Slovenia","SI");
		countryCode.put("Dem. Rep. Korea","KP");
		countryCode.put("Kuwait","KW");
		countryCode.put("Senegal","SN");
		countryCode.put("Sierra Leone","SL");
		countryCode.put("Kazakhstan","KZ");
		countryCode.put("Saudi_Arabia","SA");
		countryCode.put("Sweden","SE");
		countryCode.put("Sudan","SD");
		countryCode.put("Dominican_Republic","DO");
		countryCode.put("Djibouti","DJ");
		countryCode.put("Denmark","DK");
		countryCode.put("Germany","DE");
		countryCode.put("Yemen","YE");
		countryCode.put("Algeria","DZ");
		countryCode.put("United_States_of_America","US");
		countryCode.put("Uruguay","UY");
		countryCode.put("Lebanon","LB");
		countryCode.put("Lao PDR","LA");
		countryCode.put("Taiwan","TW");
		countryCode.put("Trinidad and Tobago","TT");
		countryCode.put("Turkey","TR");
		countryCode.put("Sri Lanka","LK");
		countryCode.put("Latvia","LV");
		countryCode.put("Lithuania","LT");
		countryCode.put("Luxembourg","LU");
		countryCode.put("Liberia","LR");
		countryCode.put("Lesotho","LS");
		countryCode.put("Thailand","TH");
		countryCode.put("Fr. S. Antarctic Lands","TF");
		countryCode.put("Togo","TG");
		countryCode.put("Chad","TD");
		countryCode.put("Libya","LY");
		countryCode.put("United_Arab_Emirates","AE");
		countryCode.put("Venezuela","VE");
		countryCode.put("Afghanistan","AF");
		countryCode.put("Iraq","IQ");
		countryCode.put("Iceland","IS");
		countryCode.put("Iran","IR");
		countryCode.put("Armenia","AM");
		countryCode.put("Albania","AL");
		countryCode.put("Angola","AO");
		countryCode.put("Argentina","AR");
		countryCode.put("Australia","AU");
		countryCode.put("Austria","AT");
		countryCode.put("India","IN");
		countryCode.put("Tanzania","TZ");
		countryCode.put("Azerbaijan","AZ");
		countryCode.put("Ireland","IE");
		countryCode.put("Indonesia","ID");
		countryCode.put("Ukraine","UA");
		countryCode.put("Qatar","QA");
		countryCode.put("Mozambique","MZ");
		countryCode.put("Cases_on_an_international_conveyance_Japan","JP");
		
	}
	
	/**
	 * Establezco los codigos para menejarlos.
	 */
	private void setCountryNames() 
	{
		countryNames.put("United_States_of_America", "EEUU");
		countryNames.put("United_Kingdom", "UK");
		countryNames.put("South_Korea", "South Korea");
		countryNames.put("Czech_Republic","Czech Republic");
		countryNames.put("Saudi_Arabia","Saudi Arabia");
		countryNames.put("South_Africa","South Africa");
		countryNames.put("Dominican_Republic","Dominican Republic");
		countryNames.put("United_Arab_Emirates","Arab Emirates");
		countryNames.put("New_Zealand","New Zealand");
		countryNames.put("Cases_on_an_international_conveyance_Japan","Japan - International Conveyance");
		countryNames.put("Bosnia_and_Herzegovina","Bosnia and Herzegovina");
		countryNames.put("North_Macedonia","Macedonia");
		countryNames.put("Puerto_Rico","Puerto Rico");
		countryNames.put("Costa_Rica","Costa Rica");
		countryNames.put("Burkina_Faso","Burkina Faso");
		countryNames.put("San_Marino","San Marino");
		countryNames.put("Cote_dIvoire","Ivory Coast");	
	}

	/**
	 * Obtengo todos los datos de covid, por paises, pasando el minimo de casso y fecha minima y maxima.
	 * 
	 * @param minCases Minimo de casos
	 * @param minDate Fecha minima
	 * @param maxDate Fecha maxima
	 * @return Una lista de Covid
	 * @throws SQLException
	 */
	public List<Covid> getAllByCountries(int minCases,String minDate,String maxDate) throws SQLException
	{
		String sql = "SELECT `countriesAndTerritories`,`countryterritoryCode`,sum(cases)'totalcases' ,sum(`deaths`) 'totaldeaths' FROM `covid` WHERE dateRep > ? AND  dateRep < ? group by countriesAndTerritories HAVING SUM(`cases`) > ? ORDER BY sum(cases) DESC";
		
		PreparedStatement statement = DataBase.getConnection().prepareStatement(sql);
		statement.setString(1, minDate);
		statement.setString(2, maxDate);
		statement.setInt(3, minCases);
		ResultSet rs = statement.executeQuery();
		
		 List<Covid> list = new ArrayList<Covid>();

		    while (rs.next()) 
		    {
		    	Covid covid = new Covid();
		    	covid.setCountriesAndTerritories(formatCountryName(rs.getString("countriesAndTerritories")));
		    	covid.setCountryterritoryCode(formatCountryCode(rs.getString("countriesAndTerritories")));
		    	covid.setCases(rs.getInt("totalcases"));
		    	covid.setDeaths(rs.getInt("totaldeaths"));
		        list.add(covid);
		    }
		
		return list;
	}
	
	public List<Covid> getAllByCountries(int minCases,String minDate) throws SQLException
	{
		String sql = "SELECT `countriesAndTerritories`,`countryterritoryCode`,sum(cases)'totalcases' ,sum(`deaths`) 'totaldeaths' FROM `covid` WHERE dateRep > ? group by countriesAndTerritories HAVING SUM(`cases`) > ? ORDER BY sum(cases) DESC";
		
		PreparedStatement statement = DataBase.getConnection().prepareStatement(sql);
		statement.setString(1, minDate);
		statement.setInt(2, minCases);
		ResultSet rs = statement.executeQuery();
		
		 List<Covid> list = new ArrayList<Covid>();

		    while (rs.next()) 
		    {
		    	Covid covid = new Covid();
		    	covid.setCountriesAndTerritories(formatCountryName(rs.getString("countriesAndTerritories")));
		    	covid.setCountryterritoryCode(formatCountryCode(rs.getString("countriesAndTerritories")));
		    	covid.setCases(rs.getInt("totalcases"));
		    	covid.setDeaths(rs.getInt("totaldeaths"));
		        list.add(covid);
		    }
		
		return list;
	}
	
	/**
	 * Obtengo los datos de covid, por pais, pasando el pais que quierp.
	 * 
	 * @param countryName Pais
	 * @return Una lista de Covid
	 * @throws SQLException
	 */
	public List<Covid> getAllByCountry(String countryName) throws SQLException
	{
		// String sql = "SELECT `dateRep`,`cases`,`deaths`,`countryterritoryCode` FROM `covid` WHERE countriesAndTerritories = ? ORDER BY dateRep";
		String sql = "select `dateRep`,`cases`,`deaths`,`countryterritoryCode` from  covid where covid.countriesAndTerritories = ? and covid.dateRep >= (select MIN(dateRep) from covid where (cases > 0 or deaths > 0) and countriesAndTerritories = ? order by covid.dateRep) order by covid.dateRep";
		
		PreparedStatement statement = DataBase.getConnection().prepareStatement(sql);
		statement.setString(1, countryName);
		statement.setString(2, countryName);
		
		ResultSet rs = statement.executeQuery();
		
		 List<Covid> list = new ArrayList<Covid>();

		    while (rs.next()) 
		    {
		    	Covid covid = new Covid();
		    	covid.setCountriesAndTerritories(formatCountryName(countryName));
		    	covid.setDateRep(rs.getString("dateRep"));
		    	covid.setCases(rs.getInt("cases"));
		    	covid.setDeaths(rs.getInt("deaths"));
		        list.add(covid);
		    }
		
		return list;
	}
	
	public List<Covid> getAllByCountries(int minCases) throws SQLException
	{
		String sql = "SELECT `countriesAndTerritories`,`countryterritoryCode`,sum(cases)'totalcases' ,sum(`deaths`) 'totaldeaths'  FROM `covid` group by countriesAndTerritories HAVING SUM(`cases`) > ? ORDER BY sum(cases) DESC";
		
		
		
		PreparedStatement statement = DataBase.getConnection().prepareStatement(sql);
		statement.setInt(1, minCases);
		ResultSet rs = statement.executeQuery();
		
		 List<Covid> list = new ArrayList<Covid>();

		    while (rs.next()) 
		    {
		    	Covid covid = new Covid();
		    	covid.setCountriesAndTerritories(formatCountryName(rs.getString("countriesAndTerritories")));
		    	covid.setCountryterritoryCode(formatCountryCode(rs.getString("countriesAndTerritories")));
		    	covid.setCases(rs.getInt("totalcases"));
		    	covid.setDeaths(rs.getInt("totaldeaths"));
		        list.add(covid);
		    }
		
		return list;
	}
		
	public Covid getMaxCasesAndDeath() throws SQLException
	{
		String sql = "SELECT sum(cases)'totalcases' ,sum(`deaths`) 'totaldeaths'  FROM `covid`";
		
		PreparedStatement statement = DataBase.getConnection().prepareStatement(sql);
		ResultSet rs = statement.executeQuery();
		
		 Covid covid = null;
		if (rs.next()) 
		{
		   covid = new Covid();
		   covid.setCases(rs.getInt("totalcases"));
		   covid.setDeaths(rs.getInt("totaldeaths"));
		}
		
		return covid;
	}
	
	
	public Covid getMaxCasesAndDeath(String country) throws SQLException
	{
		String sql = "SELECT `countriesAndTerritories`,`countryterritoryCode`,sum(cases)'totalcases' ,sum(`deaths`) 'totaldeaths'  FROM `covid` WHERE `countriesAndTerritories` = ?";
		
		PreparedStatement statement = DataBase.getConnection().prepareStatement(sql);
		statement.setString(1, country);
		ResultSet rs = statement.executeQuery();
		
		 Covid covid = null;
		if (rs.next()) 
		{
		   covid = new Covid();
		   covid.setCountriesAndTerritories(formatCountryName(rs.getString("countriesAndTerritories")));
		   covid.setCountryterritoryCode(formatCountryCode(rs.getString("countriesAndTerritories")));
		   covid.setCases(rs.getInt("totalcases"));
		   covid.setDeaths(rs.getInt("totaldeaths"));
		}
		
		return covid;
	}
	
	public int getRowsCovid() throws SQLException
	{
		String sql = "SELECT Count(*) as totalRow FROM `covid`";
		
		PreparedStatement statement = DataBase.getConnection().prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		if (rs.next()) 
		{
		   return rs.getInt("totalRow");

		}
		
		return 0;
	}
		
	private List<Covid> convertToList(ResultSet rs) throws SQLException 
	{
	    List<Covid> list = new ArrayList<Covid>();

	    while (rs.next()) 
	    {
	    	Covid covid = new Covid();
	    	covid.setDateRep(rs.getString("dateRep"));
	    	covid.setDay(rs.getInt("day"));
	    	covid.setMonth(rs.getInt("month"));
	    	covid.setYear(rs.getInt("year"));
	    	covid.setCases(rs.getInt("cases"));
	    	covid.setDeaths(rs.getInt("deaths"));
	    	covid.setCountriesAndTerritories(formatCountryName(rs.getString("countriesAndTerritories")));
	    	covid.setGeoId(rs.getString("geoId"));
	    	covid.setCountryterritoryCode(rs.getString("countryterritoryCode"));
	    	covid.setPopData2018(rs.getString("popData2018"));

	        list.add(covid);
	    }

	    return list;
	}
		
	public boolean insertCovid(Covid covid)
	{
		boolean state = false;
		try
		{
			String sql = "INSERT INTO `covid` (dateRep, day, month, year, cases, deaths, countriesAndTerritories,	geoId, countryterritoryCode, popData2018 ) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement statement = DataBase.getConnection().prepareStatement(sql);
			statement.setString(1, formatDateManual(covid.getDateRep()));
			statement.setInt(2, covid.getDay());
			statement.setInt(3,covid.getMonth());
			statement.setInt(4, covid.getYear());
			statement.setInt(5, covid.getCases());
			statement.setInt(6, covid.getDeaths());
			statement.setString(7, covid.getCountriesAndTerritories());
			statement.setString(8, covid.getGeoId());
			statement.setString(9, covid.getCountryterritoryCode());
			statement.setString(10, covid.getPopData2018());
			state = statement.execute();
			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return state;
	}
    
	public String formatCountryName(String name) 
	{
		if(countryNames.containsKey(name))
		{
			return countryNames.get(name);
		}
		return name;
	}
	
	public String formatCountryCode(String name) 
	{
		if(countryCode.containsKey(name))
		{
			return countryCode.get(name);
		}
		return "NO >>" + name;
	}
	
	public String formatDateManual(String date)
	{
		date = date.replace(".","");
		date = date.replace("ene","01");
		date = date.replace("feb","02");
		date = date.replace("mar","03");
		date = date.replace("abr","04");
		date = date.replace("may","05");
		date = date.replace("jun","06");	
		date = date.replace("jul","07");
		date = date.replace("ago","08");
		date = date.replace("sep","09");
		date = date.replace("oct","10");
		date = date.replace("nov","11");
		date = date.replace("dic","12");		
		date = date.replaceAll("-","/");
		
		String[] tok = date.split("/");
		date = tok[2]+"/"+ tok[1]+"/"+ tok[0];
		System.out.println(date);
		return date;
	}
	
	public String formatDate(String datestr) throws ParseException 
	{
		SimpleDateFormat  dt = new SimpleDateFormat("d-MMM.-yyyy", new Locale("es_ES"));
		Date date = dt.parse(datestr); 
		
		SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
		return dt1.format(date);
	}
	
	
	@Override
	public List<Covid> getAllCovid() throws SQLException
	{
		String sql = "SELECT  `dateRep`, `day`, `month`, `year`, `cases`, `deaths`, `countriesAndTerritories`,	`geoId`, `countryterritoryCode`, `popData2018` FROM `covid` ";
		Statement statement = DataBase.getConnection().createStatement();
		ResultSet rs = statement.executeQuery(sql);
		
		return convertToList(rs);
	}

	@Override
	public Covid getCovid(String country) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteAllCovid() 
	{
		boolean state = false;
		try
		{
			String sql = "DELETE FROM `covid`";
			Statement statement = DataBase.getConnection().createStatement();
			state = statement.execute(sql);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return state;
	}
}