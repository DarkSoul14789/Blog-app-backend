package com.shubh.blog.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
//		1) get token
		String requestToken = request.getHeader("Authorization");
		System.out.println(requestToken);
		
		String username = null;
		
		String token = null;
		
		if(requestToken!=null && requestToken.startsWith("Bearer")) {
			
			token = requestToken.substring(7);
			
			try {
				username = this.jwtTokenHelper.getUsernameFromToken(token);	
			}
			catch(IllegalArgumentException e) {
				System.out.println("Unable to get Jwt token: " + e.getMessage());
			}
			catch(ExpiredJwtException e) {
				System.out.println("Jwt token has expired: " + e.getMessage());
			}
			catch(MalformedJwtException e) {
				System.out.println("Invalid jwt token: " + e.getLocalizedMessage());
			}
			
			
		}else {
			System.out.println("Jwt token does not begin with Bearer");
		}
		
//		2) Validate the token
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
			
			if(this.jwtTokenHelper.validateToken(token, userDetails)) {
//				Token validated
//				Perform authentication
				UsernamePasswordAuthenticationToken usernamePasswordAuthToken = 
						new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthToken.setDetails(
						new WebAuthenticationDetailsSource().buildDetails(request)
						);
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthToken);
				
			}else {
				System.out.println("Invalid Jwt token");
			}
			
		}else {
			System.out.println("Username is null or context is not null");
		}
		
		filterChain.doFilter(request, response);
		
	}

}
