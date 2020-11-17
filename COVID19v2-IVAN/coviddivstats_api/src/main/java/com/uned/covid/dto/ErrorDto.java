package com.uned.covid.dto;

import java.io.Serializable;
import lombok.Data;
import lombok.NonNull;

@Data
public class ErrorDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NonNull private String name;
	@NonNull private String value;
}