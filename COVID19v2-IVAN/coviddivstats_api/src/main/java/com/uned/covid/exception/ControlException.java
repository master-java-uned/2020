package com.uned.covid.exception;

import java.util.ArrayList;
import java.util.List;
import com.uned.covid.dto.ErrorDto;
import lombok.Getter;

@Getter
public class ControlException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final String code;

	private final int responseCode;

	private final List<ErrorDto> errorList = new ArrayList<>();

	public ControlException(String code, int responseCode, String message) {		
		super(message);
		this.code = code;
		this.responseCode = responseCode;
	}

	public ControlException(String code, int responseCode, String message, List<ErrorDto> errorList) {
		super(message);
		this.code = code;
		this.responseCode = responseCode;
		this.errorList.addAll(errorList);
	}
}