package com.uned.covid.json;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.uned.covid.entity.Covid;


public class CovidMapRest 
{
	@JsonProperty("gdpData")
	Map<String, Integer> map = new HashMap<String, Integer>();
	
	public CovidMapRest(List<Covid> covids) {
		
		for(Covid covid: covids) {
		
			 if(covid.getGeoId() != null && covid.getGeoId() != "") {
				 map.put(covid.getGeoId(), covid.getCases());
			 }
		}
		
	}	
}
