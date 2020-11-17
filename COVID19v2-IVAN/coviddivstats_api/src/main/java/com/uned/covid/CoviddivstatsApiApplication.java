package com.uned.covid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.uned.covid.filter.JWTAuthorizationFilter;

@SpringBootApplication
public class CoviddivstatsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoviddivstatsApiApplication.class, args);
	}
	
	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		public void configure(HttpSecurity http) throws Exception {
			http.csrf().disable()
			.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
			.authorizeRequests()
			.anyRequest()
			.permitAll();			
		}		
	}	
}
























//
//  			http.csrf().disable()
//				.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
//				.authorizeRequests()
//				.antMatchers(HttpMethod.POST, "/user").permitAll()
//				.antMatchers(HttpMethod.GET, "/swagger-ui.html").permitAll()
//				.antMatchers(HttpMethod.GET, "/api/**").permitAll()
//				.anyRequest().permitAll();
//				.anyRequest().authenticated();

 











