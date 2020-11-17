package com.uned.covid.json;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.uned.covid.entity.Covid;


public class CovidMapCountryRest 
{
	@JsonProperty("dateRep")
	List<String> dateRep;
	
	@JsonProperty("deaths")
	List<Integer> deaths;
	
	@JsonProperty("cases")
	List<Integer> cases;	
	
	public CovidMapCountryRest(List<Covid> covids) {
		
		dateRep = new ArrayList<String>();
		deaths = new ArrayList<Integer>();
		cases = new ArrayList<Integer>();
		
		for(Covid covid: covids) {
						
			if(covid.getGeoId() != null && covid.getGeoId() != "") {
				dateRep.add(covid.getDateRep());
				deaths.add(covid.getDeaths());
				cases.add(covid.getCases());
			 }
		}		
	}	
}
