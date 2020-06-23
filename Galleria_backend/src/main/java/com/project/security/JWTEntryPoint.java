package com.project.security;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JWTEntryPoint implements AuthenticationEntryPoint, Serializable {

	/**
	 *  Serial Version ID (Generated via Spring Tool Suite IDE)
	 */
	private static final long serialVersionUID = -4142917862497817433L;

	// Should reject every unauthenticated attempt with response code 401
	@Override
	public void commence(HttpServletRequest req, HttpServletResponse resp, 
			AuthenticationException authExc ) throws IOException{
		resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "unauthorized");
	}
	
}
