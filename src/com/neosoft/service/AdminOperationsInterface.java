package com.neosoft.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.neosoft.model.Customer;
import com.neosoft.model.InsufficientBalanceException;
import com.neosoft.model.InvalidAgeException;
import com.neosoft.model.UserNotFoundException;

public interface AdminOperationsInterface {
	
	public void openAccount() throws InvalidAgeException;
	public void showAllCustomers();
	public void deleteCustomerAccount() throws UserNotFoundException;
	public void depositMoney() throws UserNotFoundException;
	public void withdrawMoney() throws UserNotFoundException,InsufficientBalanceException;

}
