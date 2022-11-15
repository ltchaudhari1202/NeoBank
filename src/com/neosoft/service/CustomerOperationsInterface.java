package com.neosoft.service;

import com.neosoft.model.UserNotFoundException;

public interface CustomerOperationsInterface {
	public void viewAccount() throws UserNotFoundException;
	
	public void viewAllTransaction() throws UserNotFoundException;
	
	public void transferMoney() throws UserNotFoundException;
	
	public void viewLastTransaction();
	
	

}
