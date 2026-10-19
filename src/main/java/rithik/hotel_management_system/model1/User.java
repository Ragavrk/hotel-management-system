package rithik.hotel_management_system.model1;

import  jakarta.persistence.Entity;
import  jakarta.persistence.Id;
import  jakarta.persistence.GeneratedValue;
import  jakarta.persistence.GenerationType;
import  jakarta.persistence.Column;
import  jakarta.persistence.Enumerated;
import  jakarta.persistence.EnumType;


@Entity
public class User {	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long Id;
	
	@Column(nullable = false , unique = true)
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String name;
	
	
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Role getRole() {
		return role;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Role role;
	public enum Role{
		ADMIN,
		CUSTOMER
	}
	
}
