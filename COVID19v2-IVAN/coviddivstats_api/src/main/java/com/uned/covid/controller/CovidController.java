package com.uned.covid.controller;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uned.covid.entity.Covid;
import com.uned.covid.exception.ControlException;
import com.uned.covid.json.CovidMapCountryRest;
import com.uned.covid.json.CovidMapRest;
import com.uned.covid.json.CovidRest;
import com.uned.covid.json.DataImport;
import com.uned.covid.json.MaxDataRest;
import com.uned.covid.response.Response;
import com.uned.covid.service.CovidService;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api/" + "v${app.version}")
public class CovidController {

	@Autowired
	CovidService covidService;

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/covid/{" + "covidId"+ "}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<CovidRest> getCovidById(@PathVariable Long covidId) throws ControlException {
		
		return new Response<>(HttpStatus.OK.value(), Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase(), covidService.getCovidById(covidId));
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/covid/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<List<MaxDataRest>> getCovids() throws ControlException {
		
		return new Response<>(HttpStatus.OK.value(),Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase(), covidService.getCovids());
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/covid/map", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<CovidMapRest> getCovidsMap() throws ControlException {
		
		return new Response<>(HttpStatus.OK.value(),Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase(), covidService.getCovidsMap());
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/covid/chart/{" + "country"+ "}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<CovidMapCountryRest> getCovidsChart(@PathVariable String country) throws ControlException {
		
		System.out.println("getCovidsChart:"+country);
		return new Response<>(HttpStatus.OK.value(),Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase(), covidService.getCovidsChart(country));
	}
	
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/covid/chart", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<CovidMapCountryRest> getCovidsChart() throws ControlException {
		
		return new Response<>(HttpStatus.OK.value(),Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase(), covidService.getCovidsChart());
	}
	
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/covid/country/{" + "country"+ "}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<List<CovidRest>> getCovidsByCountry(@PathVariable String country) throws ControlException {
		
		return new Response<>(HttpStatus.OK.value(),Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase(),  covidService.getCovidByCountry(country));
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/covid/max", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<MaxDataRest> getMaxData() throws ControlException {
		return new Response<>(HttpStatus.OK.value(),Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase(),  covidService.getMaxData());
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/covid/max/{" + "country"+ "}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<MaxDataRest> getMaxByCountry(@PathVariable String country) throws ControlException {
		return new Response<>(HttpStatus.OK.value(),Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase(),  covidService.getMaxData(country));
	}		
	
	@PostMapping("/upload")
    public Response<String> singleFileUpload(@RequestParam("file") MultipartFile file,RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return new Response<>(HttpStatus.FORBIDDEN.value(),Integer.toString(HttpStatus.FORBIDDEN.value()), HttpStatus.FORBIDDEN.getReasonPhrase(), "no file!");
        }
        try {

            byte[] bytes = file.getBytes();            
            System.out.println("file.getOriginalFilename():" + file.getOriginalFilename());            
            Path path = Paths.get("uploads/update-covid.json");
            Files.write(path, bytes);
            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Response<>(HttpStatus.OK.value(),Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase(), "upload!");
    }
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/clear", method = RequestMethod.GET)
	public Response<String> clear() throws ControlException {
	
		// borro toda la tabla de covid
		covidService.clear();		
		
        return new Response<>(HttpStatus.OK.value(),Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase(), "Clear!");
	}	
	
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public Response<String> update() throws ControlException {
	
		// borro toda la tabla de covid
		covidService.clear();		
		
		ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        TypeReference<DataImport> typeReference = new TypeReference<DataImport>(){};

        try {
        	DataImport dataImport = objectMapper.readValue(new File("uploads/update-covid.json"), typeReference);
        	List<Covid> covids = Arrays.asList(dataImport.records);
        	covidService.save(covids);
			System.out.println("covid Saved!");
		} catch (IOException e){
			System.out.println("Unable to save covid: " + e.getMessage());
		}
   
        return new Response<>(HttpStatus.OK.value(),Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase(), "Update!");
	}	
}