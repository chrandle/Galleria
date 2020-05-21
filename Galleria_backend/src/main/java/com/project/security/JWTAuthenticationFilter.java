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
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.beans.User;
import static com.project.security.SecurityConstants.EXPIRATION_TIME;
import static com.project.security.SecurityConstants.HEADER_STRING;
import static com.project.security.SecurityConstants.SECRET;
import static com.project.security.SecurityConstants.TOKEN_PREFIX;

public class JWTAuthenticationFilter 
	extends UsernamePasswordAuthenticationFilter {
	
	private AuthenticationManager aManager;

	public JWTAuthenticationFilter(AuthenticationManager aManager) {
		super();
		this.aManager = aManager;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse rep)
	 throws AuthenticationException {
		try {
			User credentials = new ObjectMapper().readValue(req.getInputStream(), User.class);
				return aManager.authenticate(
					new UsernamePasswordAuthenticationToken(
						credentials.getUsername(), 
						credentials.getPassword(),
						new ArrayList<>()
					)
				);	
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	@Override 
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res,
			FilterChain chain, Authentication auth) throws AuthenticationException, ServletException {
	
		String token = JWT.create().withSubject(((User)auth.getPrincipal()).getUsername())
			.withExpiresAt(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
			.sign(HMAC512(SECRET.getBytes()));
		res.addHeader(HEADER_STRING,TOKEN_PREFIX+token);
	}

	
}
	


