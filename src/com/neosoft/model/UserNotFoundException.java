package com.neosoft.model;

public class UserNotFoundException extends Exception {
	private String description;
	
	public UserNotFoundException() {
		description="User Not Found";
		
	}
	public UserNotFoundException(String description) {
		this.description=description;
	}
	@Override
	public String toString() {
		return "UserNotFoundException [description=" + description + "]";
	}
	
	

}
