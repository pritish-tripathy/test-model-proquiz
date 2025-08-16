package com.quizpro.model;

import javax.persistence.*;

@Entity
@Table(name = "myusers")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	private String username;
	private String email;
	private String password;
	private String otp;
	private String city;
	private String role;

	public User(Long userId, String username, String email, String password, String otp, String city, String role) {
		this.userId = userId;
		this.username = username;
		this.email = email;
		this.password = password;
		this.otp = otp;
		this.city = city;
		this.role = role;
	}

	public User() {}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
