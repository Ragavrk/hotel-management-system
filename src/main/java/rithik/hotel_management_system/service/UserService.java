package rithik.hotel_management_system.service;

import org.springframework.stereotype.Service;
import rithik.hotel_management_system.repository.UserRepository;
import rithik.hotel_management_system.model1.User;
import rithik.hotel_management_system.model1.User.Role;

import org.springframework.security.crypto.password.PasswordEncoder;
import rithik.hotel_management_system.security.JwtUtil;



@Service
public class UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;
	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, 
							JwtUtil jwtUtil ) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtUtil = jwtUtil;
		
	}
	public User saveUser(User user ) {
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		user.setRole(Role.CUSTOMER);
		return userRepository.save(user);
		
	}
	
	
	public User createAdmin(User user) {
		String encodedPassword =
				passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		user.setRole(Role.ADMIN);
		
		return userRepository.save(user);
	}
	
	public String loginUser(String email, String password) {
		User user = userRepository.findByEmail(email)
				.orElseThrow(()-> new RuntimeException("User not found"));
		if(!passwordEncoder.matches(password, user.getPassword())) {
			throw new RuntimeException("Invalid Password");
		}
		
		String token = jwtUtil.createToken(user.getEmail());
		return token ;
	}
}
