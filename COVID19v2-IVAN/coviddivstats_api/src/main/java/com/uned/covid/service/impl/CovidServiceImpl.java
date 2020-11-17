package com.uned.covid.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uned.covid.entity.Covid;
import com.uned.covid.exception.ControlException;
import com.uned.covid.exception.NotFoundException;
import com.uned.covid.json.CountryRest;
import com.uned.covid.json.CovidMapCountryRest;
import com.uned.covid.json.CovidMapRest;
import com.uned.covid.json.CovidRest;
import com.uned.covid.json.MaxDataRest;
import com.uned.covid.repository.CovidRepository;
import com.uned.covid.service.CovidService;

@Service
public class CovidServiceImpl implements CovidService {

	@Autowired
	CovidRepository covidRepository;

	public static final ModelMapper modelMapper = new ModelMapper();

	public CovidRest getCovidById(Long covidId) throws ControlException {
		return modelMapper.map(getCovidEntity(covidId), CovidRest.class);
	}

	private Covid getCovidEntity(Long covidId) throws ControlException {
		return covidRepository.findById(covidId)
				.orElseThrow(() -> new NotFoundException("SNOT-404-1", "COVID_NOT_FOUND"));
	}

	public List<MaxDataRest> getCovids() throws ControlException {
		//final List<Covid> covidEntities = covidRepository.findAll();
		final List<Covid> covidEntities = covidRepository.getCovidsGroupsByCountry(20);
		return covidEntities.stream().map(service -> modelMapper.map(service, MaxDataRest.class))
				.collect(Collectors.toList());
	}
	
	public CovidMapRest getCovidsMap() throws ControlException {
		//final List<Covid> covidEntities = covidRepository.findAll();
		final List<Covid> covidEntities = covidRepository.getCovidsGroupsByCountry(5000);
		
		CovidMapRest covidMapRest = new CovidMapRest(covidEntities);		
		
		return covidMapRest;
	}	
	
	public CovidMapCountryRest getCovidsChart() throws ControlException {

		final List<Covid> covidEntities = covidRepository.getCovidsByDays();
		
		CovidMapCountryRest covidMapRest = new CovidMapCountryRest(covidEntities);		
		
		return covidMapRest;
	}	
	
	
	public CovidMapCountryRest getCovidsChart(String country) throws ControlException {

		final List<Covid> covidEntities = covidRepository.getCovidsGroupsByCountry(country);
		
		System.out.println(covidEntities.size());
		
		CovidMapCountryRest covidMapRest = new CovidMapCountryRest(covidEntities);		
		
		return covidMapRest;
	}	
	

	public List<CovidRest> getCovidByCountry(String countryterritoryCode) throws ControlException {
		final List<Covid> covidEntities = covidRepository.findByCountryterritoryCode(countryterritoryCode);
		return covidEntities.stream().map(service -> modelMapper.map(service, CovidRest.class))
				.collect(Collectors.toList());
	}

	public CountryRest getDataByCountry(String countryterritoryCode) throws ControlException {
		return new CountryRest(covidRepository.findOneBycountryterritoryCode(countryterritoryCode));
	}
	
	public MaxDataRest getMaxData() throws ControlException {
		return new MaxDataRest(covidRepository.findTotalValues());
	}
	
	public MaxDataRest getMaxData(String countryterritoryCode) throws ControlException {
		return new MaxDataRest(covidRepository.findTotalValuesByCountry(countryterritoryCode),true);
	}
	
	public Iterable<Covid> list() {
	     return covidRepository.findAll();
	}
	
	public Iterable<Covid> save(List<Covid> covids) {
        return covidRepository.saveAll(covids);
    }
	
	public void clear() {
        covidRepository.deleteAll();;
    }
}