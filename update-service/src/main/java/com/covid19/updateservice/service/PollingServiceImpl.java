package com.covid19.updateservice.service;

import com.covid19.updateservice.model.CountryCovidInformation;
import com.covid19.updateservice.model.Records;
import com.google.gson.Gson;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class PollingServiceImpl implements PollingService {

    private Records countries = new Records();

    @Scheduled (fixedDelay = 30000)
    public void run(){
        String uri ="https://opendata.ecdc.europa.eu/covid19/casedistribution/json/";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Records> response = restTemplate.getForEntity(uri,Records.class);
        System.out.println("response" + response);
        if (response.getStatusCode().value() == 200) {
          countries = response.getBody();
            if (countries != null) {
                for (int i=0;i < countries.getRecords().size();i++) {


                }
                System.out.println("**Countries" +  countries.getRecords().size());
            }

        }
    }

    public String outputMapData(){
        String outputData = new Gson().toJson(countries.getRecords() );
//        String outputData = new Gson().toJson("hola mundo" );
        System.out.println("**Countries in outputData" +  countries.getRecords().size());
//        System.out.println("**Countries Json in outputData**" +  outputData);
        return outputData;
    }


}
