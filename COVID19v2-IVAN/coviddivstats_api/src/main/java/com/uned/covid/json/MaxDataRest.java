package com.uned.covid.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.uned.covid.entity.Covid;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MaxDataRest {
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
	
	public MaxDataRest(Covid covid) {
		this.cases =covid.getCases();
		this.deaths =covid.getDeaths();
	}
	
	public MaxDataRest(Covid covid,boolean country) {
		this.cases =covid.getCases();
		this.deaths =covid.getDeaths();
		this.countriesAndTerritories =covid.getCountriesAndTerritories();
		this.geoId =covid.getGeoId();
		this.countryterritoryCode =covid.getCountryterritoryCode();
	}
}
