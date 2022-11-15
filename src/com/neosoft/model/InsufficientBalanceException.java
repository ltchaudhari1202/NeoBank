package com.neosoft.model;

public class InsufficientBalanceException extends Exception {
	private String description;


	   // constructor
	   public InsufficientBalanceException(){
		    description = "Insufficient balance";
	   }

	   public InsufficientBalanceException(String description){
	      // parameterized constructor
		   this.description=description;
	      
	   }

	@Override
	public String toString() {
		return "InsufficientBalanceException:"+description;
	}
	   
	}
