package rithik.hotel_management_system.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import rithik.hotel_management_system.model1.User;
import rithik.hotel_management_system.service.UserService;
import rithik.hotel_management_system.dto.UserResponse;
import rithik.hotel_management_system.dto.LoginRequest;
import org.springframework.http.ResponseEntity;
import rithik.hotel_management_system.dto.LoginResponse;

import  org.springframework.web.bind.annotation.GetMapping;

@RestController
public class UserController {
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
		
	}
	
	@PostMapping("/users")
	public UserResponse createUser(@RequestBody User user) {
		User savedUser = userService.saveUser(user);
		UserResponse response = new UserResponse();
		response.setId(savedUser.getId());
		response.setEmail(savedUser.getEmail());
		response.setRole(savedUser.getRole());
		return response;
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){
		String token= userService.loginUser(
				request.getEmail(),
				request.getPassword()
				);
		LoginResponse response = new  LoginResponse();
		response.setToken(token);
		
		return ResponseEntity.ok(response);
		
		
	}

	@GetMapping("/test")
	public String test() {
		return "JWT Authentication is working !";
	}
	
}
