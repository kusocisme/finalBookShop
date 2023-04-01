package com.DTOs.BusinessDtos;

public class RegisterDTO {
	String username;
	String password;
	String reEnter;
	String code;
	String email;
	String errorMessage;
	String role;
	int roleId;
	
	public RegisterDTO() {}
	
	public RegisterDTO(String code) {
		super();
		this.code = code;
	}
	public RegisterDTO(String email, String username, String password, String reEnter) {
		super();
		this.email = email;
		this.username = username;
		this.password = password;
		this.reEnter = reEnter;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getReEnter() {
		return reEnter;
	}

	public void setReEnter(String reEnter) {
		this.reEnter = reEnter;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
}
