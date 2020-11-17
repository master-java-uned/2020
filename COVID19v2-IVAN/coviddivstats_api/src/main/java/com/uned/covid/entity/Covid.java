package com.uned.covid.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "covid")
public class Covid {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private long id;

	@Column(name = "daterep")
	private String dateRep;

	@Column(name = "day")
	private int day;

	@Column(name = "month")
	private int month;

	@Column(name = "year")
	private int year;

	@Column(name = "cases")
	private int cases;

	@Column(name = "deaths")
	private int deaths;

	@Column(name = "countriesandterritories")
	private String countriesAndTerritories;

	@Column(name = "geoid")
	private String geoId;

	@Column(name = "countryterritorycode")
	private String countryterritoryCode;

	@Column(name = "popdata2019")
	private String popData2019;

	@Column(name = "continentexp")
	private String continentExp;
	
	@Column(name = "cumulative14daysper100000")
	private String cumulative14daysper100000;
}