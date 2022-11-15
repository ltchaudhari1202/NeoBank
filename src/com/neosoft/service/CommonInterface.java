package com.neosoft.service;

import com.neosoft.model.UserNotFoundException;

public interface CommonInterface {
	public String userName="admin";
	public String password="admin";
	public void adminLogin() throws UserNotFoundException;
	public void customerLogin() throws UserNotFoundException;

}
