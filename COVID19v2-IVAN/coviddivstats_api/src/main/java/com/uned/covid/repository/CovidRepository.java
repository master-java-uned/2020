package com.uned.covid.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.uned.covid.entity.Covid;

@Repository
public interface CovidRepository extends JpaRepository<Covid, Long> {

	// HIBERnate
	
	Optional<Covid> findById(Long id);

	List<Covid> findByCountryterritoryCode(String countryterritoryCode);
/*
	@Query(value = "SELECT `countriesAndTerritories`,`countryterritoryCode`,sum(cases)'cases' ,sum(`deaths`) 'deaths' FROM `covid` WHERE dateRep > ? AND  dateRep < ? group by countriesAndTerritories HAVING SUM(`cases`) > ? ORDER BY sum(cases) DESC", nativeQuery = true)
*/	
	@Query(value = "SELECT id,day,month,year,geoid,daterep,popdata2019,continentexp,countriesandterritories,countryterritoryCode,sum(cases)'cases' ,sum(`deaths`) 'deaths',`cumulative14daysper100000` FROM `covid` group by countriesAndTerritories ORDER BY sum(cases) DESC LIMIT :countries", nativeQuery = true)
	List<Covid> getCovidsGroupsByCountry(@Param("countries")int countries);
	
	@Query(value = "SELECT id,day,month,year,geoid,daterep,popdata2019,continentexp,countriesandterritories,countryterritoryCode,cases ,deaths,`cumulative14daysper100000` FROM `covid` WHERE `countryterritoryCode`=:countryterritoryCode and cases>1 ORDER BY year , MONTH, day", nativeQuery = true)
	List<Covid> getCovidsGroupsByCountry(@Param("countryterritoryCode")String countryterritoryCode);
	
	@Query(value = "SELECT * FROM covid WHERE countryterritoryCode = :countryterritoryCode LIMIT 1", nativeQuery = true)
	Covid findOneBycountryterritoryCode(@Param("countryterritoryCode")String countryterritoryCode);
	
	@Query(value = "SELECT id,day,month,year,geoid,daterep,popdata2019,continentexp,countriesandterritories,countryterritoryCode,sum(cases)'cases' ,sum(`deaths`) 'deaths',`cumulative14daysper100000`  FROM `covid`", nativeQuery = true)
	Covid findTotalValues();
	
	@Query(value = "SELECT id,day,month,year,geoid,daterep,popdata2019,continentexp,countriesAndTerritories,countryterritoryCode,sum(cases)'cases' ,sum(`deaths`) 'deaths',`cumulative14daysper100000`  FROM `covid` WHERE `countryterritoryCode`= :countryterritoryCode", nativeQuery = true)
	Covid findTotalValuesByCountry(@Param("countryterritoryCode")String countryterritoryCode);
	
	
	@Query(value = "SELECT id,day,month,year,geoid,popdata2019,continentexp,countriesandterritories,countryterritoryCode,`cumulative14daysper100000`,`daterep`, SUM(`cases`) AS cases ,SUM(`deaths`) AS deaths FROM `covid` GROUP BY `daterep` ORDER BY `year` , `month`, `day`", nativeQuery = true)
	List<Covid> getCovidsByDays();
	

}
