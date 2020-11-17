package com.uned.covid.service;

import java.util.List;

import com.uned.covid.entity.Covid;
import com.uned.covid.exception.ControlException;
import com.uned.covid.json.CountryRest;
import com.uned.covid.json.CovidMapCountryRest;
import com.uned.covid.json.CovidMapRest;
import com.uned.covid.json.CovidRest;
import com.uned.covid.json.MaxDataRest;

public interface CovidService {

	CovidRest getCovidById(Long covidId) throws ControlException;

	public List<MaxDataRest> getCovids() throws ControlException;
	
	public List<CovidRest> getCovidByCountry(String country) throws ControlException;
	
	public CountryRest getDataByCountry(String country) throws ControlException;
	
	public MaxDataRest getMaxData() throws ControlException;
	
	public MaxDataRest getMaxData(String country) throws ControlException;

	public Iterable<Covid> save(List<Covid> covids);
	
	public Iterable<Covid> list();
	
	public CovidMapRest getCovidsMap() throws ControlException;

	public CovidMapCountryRest getCovidsChart(String country) throws ControlException;
	
	public CovidMapCountryRest getCovidsChart() throws ControlException;
	
	public void clear();
}