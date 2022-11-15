package com.neosoft.model;

public class InvalidAgeException extends Exception {
	private String description;
	public InvalidAgeException() {
		description="Invalid Age";
	}
	public InvalidAgeException(String description) {
		this.description=description;
	}
	@Override
	public String toString() {
		return "InvalidAgeException [description=" + description + "]";
	}
	
	

}
