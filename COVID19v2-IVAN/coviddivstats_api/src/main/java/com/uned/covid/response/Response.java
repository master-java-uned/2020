package com.uned.covid.response;

import java.io.Serializable;
import lombok.Data;

@Data
public class Response<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private int status;
	private String code;
	private String message;
	private T data;

	public Response(int status, String code, String message, T data) {
		this.status = status;
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public Response(int status, String code, String message) {
		this.status = status;
		this.code = code;
		this.message = message;
	}
}