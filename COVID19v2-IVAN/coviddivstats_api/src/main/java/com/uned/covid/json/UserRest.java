package com.uned.covid.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRest {

	@JsonProperty("id")
	private long id;

	@JsonProperty("username")
	private String username;

	@JsonProperty("email")
	private String email;

	@JsonProperty("validation")
	private String validation;

	@JsonProperty("level")
	private int level;
	
	@JsonProperty("token")
	private String token;
}