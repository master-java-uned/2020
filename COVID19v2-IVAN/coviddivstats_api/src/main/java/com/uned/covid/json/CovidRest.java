package com.uned.covid.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.uned.covid.entity.Covid;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CovidRest {

	@JsonProperty("id")
	private long id;

	@JsonProperty("dateRep")
	private String dateRep;

	@JsonProperty("day")
	private int day;

	@JsonProperty("month")
	private int month;

	@JsonProperty("year")
	private int year;

	@JsonProperty("cases")
	private int cases;

	@JsonProperty("deaths")
	private int deaths;

	@JsonProperty("countriesAndTerritories")
	private String countriesAndTerritories;

	@JsonProperty("geoId")
	private String geoId;

	@JsonProperty("countryterritoryCode")
	private String countryterritoryCode;

	@JsonProperty("popData2019")
	private String popData2019;

	@JsonProperty("continentExp")
	private String continentExp;
	
	public CovidRest(Covid covid) {
		this.dateRep = covid.getDateRep();
		this.day = covid.getDay();
		this.month = covid.getMonth();
		this.year = covid.getYear();
		this.cases = covid.getCases();
		this.deaths = covid.getDeaths();
		this.countriesAndTerritories = covid.getCountriesAndTerritories();
		this.geoId = covid.getGeoId();
		this.popData2019 = covid.getPopData2019();
		this.continentExp = covid.getContinentExp();
	}
}
