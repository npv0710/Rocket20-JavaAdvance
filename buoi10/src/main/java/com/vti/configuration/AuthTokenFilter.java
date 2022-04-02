package com.vti.configuration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vti.service.IAccountService;
import com.vti.utils.JwtUtils;

import io.jsonwebtoken.JwtException;

public class AuthTokenFilter extends OncePerRequestFilter{

	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private IAccountService acService;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		try {
			String token = getTokenFromRequest(request);
			if (token != null && jwtUtils.validateJwtToken(token)) {
				String username = jwtUtils.getUsernameFromToken(token);
				
				UserDetails userDetails = acService.loadUserByUsername(username);
				
				UsernamePasswordAuthenticationToken userAuth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				userAuth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(userAuth);
			}
			
			filterChain.doFilter(request, response);
		}catch (Exception ex) {
			System.out.println("Exception in auth filter: ");
			System.out.println(ex.getMessage());
			String msg = "";
			
			throw ex;
			
//			if (ex instanceof JwtException) {
//	             msg = ex.getMessage();
//	        }
//			
//			Map<String, Object> errorDetails = new HashMap<>();
//	        errorDetails.put("message", "Invalid token");
//	        
//	        response.setCharacterEncoding("UTF-8");
//	        response.setContentType(MediaType.APPLICATION_JSON.getType());
//	        
//	        mapper.writeValue(response.getWriter(), errorDetails);
	        
	        //response.getWriter().write(JSON.toJSONString(Resp.error(msg)));
	        
//	        response.getOutputStream().println(
//		            "{ "
//		                + "\"message\": \"Token has expired\","
//		                + "\"type\": \"Unauthorized\","
//		                + "\"status\": 401"
//		                + "}");
	        
	        //response.getWriter().write(JSON.toJSONString(Resp.error(msg)));
	        
	        //response.sendError(HttpServletResponse.SC_UNAUTHORIZED, msg);
	        //return;
		}
		
		
	}
	
	private String getTokenFromRequest(HttpServletRequest request) {
		String header = request.getHeader("Authorization");
		
		if (StringUtils.hasText(header) && header.startsWith("Bearer")) {
			return header.substring(7, header.length());
		}
		
		return null;
	}

}
