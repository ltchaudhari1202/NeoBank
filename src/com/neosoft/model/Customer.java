package com.neosoft.model;

import java.math.BigDecimal;
import java.util.Set;

public class Customer {
	private String name;
	private long accountNumber;
	private BigDecimal balance;
	private int age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber() {
		this.accountNumber = accountNumber;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Customer(String name, long accountNumber, BigDecimal balance, int age) {
		super();
		this.name = name;
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.age = age;
	}
	@Override
	public String toString() {
		return "Customer [name=" + name + ", accountNumber=" + accountNumber + ", balance=" + balance + ", age=" + age
				+ "]";
	}
	
	
	
	
	
	

}
