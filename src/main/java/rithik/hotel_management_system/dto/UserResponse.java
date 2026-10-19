package rithik.hotel_management_system.dto;

import rithik.hotel_management_system.model1.User;

public class UserResponse {
	
	private Long id;
	private String email;
	private User.Role role;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public User.Role getRole() {
		return role;
	}
	public void setRole(User.Role role) {
		this.role = role;
	}
	
	

}
