package com.vti.configuration;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint{

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		System.out.println("Auth entry point handle exception: " + authException.getMessage());
		
		  if( authException instanceof InsufficientAuthenticationException) {
			  System.out.println( authException.getMessage());
			  
//		      if( authException.getCause() instanceof InvalidTokenException ){
//		    	  response.getOutputStream().println(
//		            "{ "
//		                + "\"message\": \"Token has expired\","
//		                + "\"type\": \"Unauthorized\","
//		                + "\"status\": 401"
//		                + "}");
//		      }
		    }
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: Unauthorized!");
		//response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
	}

}
