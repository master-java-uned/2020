package com.covid.servicios;

import com.usuarios.model.covid;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

public class covidservice implements IcovidService{

	@Override
    public List<covid> findCovits(int currentPage, int recordsPerPage)  {

        List<covid> covids = null;
        
        int start = currentPage * recordsPerPage - recordsPerPage;
        
        try {
            String sql = "SELECT * FROM covid LIMIT ?, ?";
            
            SimpleDriverDataSource ds = new SimpleDriverDataSource();
            ds.setDriver(new com.mysql.cj.jdbc.Driver());
            ds.setUrl("jdbc:mysql://db:3306/covid_users");
            ds.setUsername("root");
            ds.setPassword("Malamen2019");
            
            JdbcTemplate jtm = new JdbcTemplate(ds);
            covids = jtm.query(sql, new Object[] {start, recordsPerPage}, 
                    new BeanPropertyRowMapper(covid.class));
            
        } catch (SQLException ex) {
            Logger.getLogger(covidservice.class.getName()).log(Level.SEVERE, 
                null, ex);
        }
        
        return covids;
    }    
    
    @Override
    public int getNumberOfRows() {
        
        int numOfRows = 0;
        
        try {
            String sql = "SELECT COUNT(fecha) FROM covid";
            
            SimpleDriverDataSource ds = new SimpleDriverDataSource();
            ds.setDriver(new com.mysql.cj.jdbc.Driver());
            ds.setUrl("jdbc:mysql://db:3306/covid_users");
            ds.setUsername("root");
            ds.setPassword("Malamen2019");
            
            JdbcTemplate jtm = new JdbcTemplate(ds);
            numOfRows = jtm.queryForObject(sql, Integer.class);
            
        } catch (SQLException ex) {
            Logger.getLogger(covidservice.class.getName()).log(Level.SEVERE, 
                null, ex);
        }
        
        return numOfRows;
    }
	
}
