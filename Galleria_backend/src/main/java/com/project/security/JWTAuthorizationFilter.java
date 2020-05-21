package com.project.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.beans.User;

import static com.project.security.SecurityConstants.HEADER_STRING;
import static com.project.security.SecurityConstants.SECRET;
import static com.project.security.SecurityConstants.TOKEN_PREFIX;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
	
	public JWTAuthorizationFilter(AuthenticationManager aManager){
			super(aManager);
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse rep, FilterChain chain) throws IOException, ServletException {
		String header = req.getHeader(HEADER_STRING);
		if ( header == null || !header.startsWith(TOKEN_PREFIX) ) {
			chain.doFilter(req, rep);
			return;
		}
		
		UsernamePasswordAuthenticationToken auth = getAuthentication(req);
		
		SecurityContextHolder.getContext().setAuthentication(auth);
		chain.doFilter(req, rep);
	}
	
	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest req){
		 String token = req.getHeader(HEADER_STRING);
		 if(token != null) {
			 String user = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
					 .build()
					 .verify(token.replace(TOKEN_PREFIX, ""))
					 .getSubject();
			 if ( user != null) {
				return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>()); 
			 }
			 return null;
		 }
		 return null;
	}
		 		 
}
	
	

