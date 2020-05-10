package com.covid19.updateservice.model;

import java.util.ArrayList;
import java.util.List;
import com.covid19.updateservice.model.CountryCovidInformation;
public class Records {
    private List<CountryCovidInformation> records;
    public Records() {
        records = new ArrayList<>();
    }
    public List<CountryCovidInformation> getRecords() {
        return records;
    }

    public void setRecords(List<CountryCovidInformation> records) {
        this.records = records;
    }
}
