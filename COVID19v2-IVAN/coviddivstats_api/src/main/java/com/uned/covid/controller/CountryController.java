package com.uned.covid.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.uned.covid.exception.ControlException;
import com.uned.covid.json.CountryRest;
import com.uned.covid.response.Response;
import com.uned.covid.service.CovidService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api/" + "v${app.version}")
public class CountryController {

	@Autowired
	CovidService covidService;

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/country/{" + "country"+ "}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<CountryRest> getCovidsByCountry(@PathVariable String country) throws ControlException {
		
		return new Response<>(HttpStatus.OK.value(),Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase(),  covidService.getDataByCountry(country));
	}	
	
}