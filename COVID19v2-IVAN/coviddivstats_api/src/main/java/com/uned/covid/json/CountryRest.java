package com.uned.covid.json;

import com.uned.covid.entity.Covid;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data @NoArgsConstructor
public class CountryRest {
	@NonNull private String countryterritoryCode;
	@NonNull private String geoId;
	@NonNull private String countriesAndTerritories;
	@NonNull private String continentExp;
	
	public CountryRest(Covid covid) {
		this.countryterritoryCode = covid.getCountryterritoryCode();
		this.geoId= covid.getGeoId();
		this.countriesAndTerritories= covid.getCountriesAndTerritories();
		this.continentExp= covid.getContinentExp();
	}
}