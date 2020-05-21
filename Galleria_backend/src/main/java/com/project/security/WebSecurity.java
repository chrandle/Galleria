package com.project.security;


import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
// user service import here?
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.context.annotation.Bean;


import com.project.security.JWTAuthenticationFilter;
import com.project.security.JWTAuthorizationFilter;
import static com.project.security.SecurityConstants.SIGN_UP_URL;


@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
	private UserDetailServiceImpl userDetailService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public WebSecurity(UserDetailServiceImpl userDetailService, BCryptPasswordEncoder pwEncoder) {
		super();
		this.userDetailService = userDetailService;
		this.pwEncoder = pwEncoder;
	}
	
	@Override
	protected void configure (HttpSecurity http) throws Exception {
		/* 
		 	cors -> Cross Origin Resource Sharing filter for intercepting and adding headers to cross-origin requests
		 	
		 	crsrf -> adds support for handling Cross-Site Request Forgery attacks
		 	
		 	antMatcher -> HttpSecurity configuration only invoked when matching the provided ant pattern
			
			sessionCreationPolicy.STATELESS -> Spring Security will never create an HttpSession, and will not use it to obtain SecurityContext
		*/
		
		http.cors().and().csrf().disable().authorizeRequests()
			.antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
				.anyRequest().authenticated()
				.and()
				.addFilter(new JWTAuthenticationFilter(authenticationManager()))
				.addFilter(new JWTAuthorizationFilter( authenticationManager()))
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	protected void configure (AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).PasswEncoder(bCryptPasswordEncoder);
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		//  /** -> ant pattern for zero or more path segments
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}
	
	
	
}
