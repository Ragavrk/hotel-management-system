package rithik.hotel_management_system.config;

import  rithik.hotel_management_system.security.JwtFilter;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import org.springframework.web.bind.annotation.GetMapping;


@Configuration
public class SecurityConfig {
	
	private final JwtFilter jwtFilter ;
	
	public SecurityConfig(JwtFilter jwtFilter) {
		this.jwtFilter = jwtFilter;
	}
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.csrf(csrf -> csrf.disable())
			.addFilterBefore(jwtFilter , UsernamePasswordAuthenticationFilter.class)
			.authorizeHttpRequests(auth -> auth
			        .requestMatchers("/users", "/login", "/test").permitAll()

			        .requestMatchers(org.springframework.http.HttpMethod.POST, "/bookings")
			        .hasAnyRole("CUSTOMER", "ADMIN")

			        .requestMatchers("/bookings/my")
			        .hasAnyRole("CUSTOMER", "ADMIN")
			        
			        .requestMatchers(
			                org.springframework.http.HttpMethod.PATCH,
			                "/bookings/*/cancel")
			        .hasAnyRole("CUSTOMER", "ADMIN")
			        
			        .requestMatchers(org.springframework.http.HttpMethod.GET, "/bookings")
			        .hasRole("ADMIN")
			        
			        .requestMatchers(org.springframework.http.HttpMethod.GET, "/bookings/user/**")
			        .hasRole("ADMIN")

			        .requestMatchers(org.springframework.http.HttpMethod.GET, "/bookings/*")
			        .authenticated()

			        .anyRequest()
			        .hasRole("ADMIN")
			);
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		
	}
	

}
