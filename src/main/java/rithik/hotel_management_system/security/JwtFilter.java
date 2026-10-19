package rithik.hotel_management_system.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.stereotype.Component;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import rithik.hotel_management_system.repository.UserRepository;
import rithik.hotel_management_system.model1.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


import java.io.IOException;
import java.util.Collections;


@Component
public class JwtFilter extends OncePerRequestFilter{
	private final JwtUtil jwtUtil;
	private final UserRepository userRepository;
	
	public JwtFilter(JwtUtil jwtUtil , UserRepository userRepository) {
		this.jwtUtil = jwtUtil;
		this.userRepository = userRepository;
	}
	@Override
	protected void doFilterInternal( 
			HttpServletRequest request,
			HttpServletResponse response,
			FilterChain filterChain)
	throws ServletException , IOException {
		
		if(request.getServletPath().equals("/login")){
			filterChain.doFilter(request, response);
			return;
		}
		
		String authHeader = request.getHeader("Authorization");
		if(authHeader == null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		String token = authHeader.substring(7);
		String email = jwtUtil.extractEmail(token);
		User user = userRepository.findByEmail(email)
				.orElseThrow(()  ->  new RuntimeException("User not found"));
		
		System.out.println("JWT EMAIL: " + email);
		System.out.println("USER ROLE: " + user.getRole());
		UsernamePasswordAuthenticationToken authentication =
				new UsernamePasswordAuthenticationToken(
						email,
						null,
						Collections.singletonList(
						new SimpleGrantedAuthority("ROLE_"+ user.getRole().name())
						)
						);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		filterChain.doFilter(request,response);
	}
}
