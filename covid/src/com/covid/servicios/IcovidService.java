package com.covid.servicios;



import com.usuarios.model.covid;
import java.util.List;

public interface IcovidService {
	
	    public List<covid> findCovits(int currentPage, int numOfRecords);
	    public int getNumberOfRows();
	}


