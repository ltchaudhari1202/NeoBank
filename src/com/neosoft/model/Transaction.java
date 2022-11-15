package com.neosoft.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Transaction {
	private long accountNumber;
	private LocalDateTime date=LocalDateTime.now();
	private String transactionType;
	private BigDecimal amount;
	private BigDecimal balance;
	
	
	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Transaction(long accountNumber,  String transactionType, BigDecimal amount,
			BigDecimal balance) {
		super();
		this.accountNumber = accountNumber;
		
		this.transactionType = transactionType;
		this.amount = amount;
		this.balance = balance;
	}


	public long getAccountNumber() {
		return accountNumber;
	}


	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}


	public LocalDateTime getDate() {
		return date;
	}


	public void setDate(LocalDateTime date) {
		this.date = date;
	}


	public String getTransactionType() {
		return transactionType;
	}


	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}


	public BigDecimal getAmount() {
		return amount;
	}


	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}


	public BigDecimal getBalance() {
		return balance;
	}


	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}


	@Override
	public String toString() {
		return "Transaction [accountNumber=" + accountNumber + ", date=" + date + ", transactionType=" + transactionType
				+ ", amount=" + amount + ", balance=" + balance + "]";
	}
	
	
	

}
