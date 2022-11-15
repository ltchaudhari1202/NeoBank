package com.neosoft.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.neosoft.model.Customer;
import com.neosoft.model.InsufficientBalanceException;
import com.neosoft.model.InvalidAgeException;
import com.neosoft.model.Transaction;
import com.neosoft.model.UserNotFoundException;

import main.NeoBankMain;

public class AdminOperationsImpl implements AdminOperationsInterface {

	LinkedList<Customer> cust = NeoBankMain.custList;
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	Scanner sc = new Scanner(System.in);
	static long accountNumber = 1000;
	Customer customer;
	Transaction transaction;
	//long accNo;
	BigDecimal amt;

	@Override
	public void openAccount() throws InvalidAgeException {
		// TODO Auto-generated method stub

		System.out.println("Enter the Name:");
		String name = sc.nextLine();
		// System.out.println("Enter the Account Number:");
		long accountNumber = this.accountNumber++;
		System.out.println("Enter Balance:");
		BigDecimal balance = sc.nextBigDecimal();

		System.out.println("Enter Age:");
		int age = sc.nextInt();
		customer = new Customer(name, accountNumber, balance, age);
		System.out.println(customer);
		if (age < 18) {
			//System.out.println("Age is invalid");
			throw new InvalidAgeException();

		} else {
			boolean addcust = cust.add(customer);
			if (addcust)
				System.out.println("Account created successfully");
		}

	}

	@Override
	public void showAllCustomers() {
		// TODO Auto-generated method stub



		System.out.println("Customer list:");
		for (Customer cust : cust) {
			System.out.println(cust);
		}

	}

	@Override
	public void deleteCustomerAccount() throws UserNotFoundException {
		// TODO Auto-generated method stub
		// long deleteAccountNumber;

		customer = new Customer();
		System.out.println("Enter the account number that you want to be deleted:");
		long accNo = sc.nextLong();
		for(Customer customer:cust) {
			if(accNo==customer.getAccountNumber()) {
				int index=cust.indexOf(customer);
				cust.remove(index);
				System.out.println("Account deleted successfully");
			}
			else {
				throw new UserNotFoundException();
			}
		}

	

	}

	@Override
	public void depositMoney() throws UserNotFoundException{
		// TODO Auto-generated method stub
		System.out.println("Enter the account number for depositing money:");
		long accNo = sc.nextLong();
		System.out.println("Enter the amount to be deposit:");
		amt = sc.nextBigDecimal();
		Optional<Customer> optional = cust.stream().filter(item -> accNo == item.getAccountNumber()).findFirst();

		if (!optional.isEmpty()) {
			Customer customer1 = optional.get();

			BigDecimal newBalance = customer1.getBalance().add(amt);
			customer1.setBalance(newBalance);
			transaction = new Transaction(accNo, "Deposit", amt, customer1.getBalance());
			System.out.println("Deposited");
			System.out.println("Avaliable balance:" + customer1.getBalance());
			NeoBankMain.transactionList.add(transaction);
			for (Transaction tx : NeoBankMain.transactionList); 
//			{
//				System.out.println(tx);
//			}

		} else {
			//System.out.println("Invalid account credentials");
			throw new UserNotFoundException();
		}
	}

	@Override
	public void withdrawMoney() throws UserNotFoundException,InsufficientBalanceException {
		// TODO Auto-generated method stub
		System.out.println("Enter tha account for withdrawing money:");
		long accNo = sc.nextLong();
		System.out.println("Enter the amount to be withdraw:");
		amt = sc.nextBigDecimal();
		Optional<Customer> optional = cust.stream().filter(item -> accNo == item.getAccountNumber()).findFirst();

		if (optional.isPresent()) {
			Customer customer2 = optional.get();
			BigDecimal avaliableBalance = customer2.getBalance();
			avaliableBalance.doubleValue();
			if (amt.compareTo(avaliableBalance) < 0) {
				BigDecimal newBalance = customer2.getBalance().subtract(amt);
				customer2.setBalance(newBalance);
				transaction = new Transaction(accNo, "Withdraw", amt, customer2.getBalance());
				System.out.println("Withdraw");
				System.out.println("Avaliable balance:" + customer2.getBalance());
				NeoBankMain.transactionList.add(transaction);
				for (Transaction tx : NeoBankMain.transactionList);
//				{
//					System.out.println(tx);
//				}

			} else {
				//System.out.println("Invalid amount");
				throw new InsufficientBalanceException();
			}
//			
		} else {
			//System.out.println("Invalid account credentials");
			throw new UserNotFoundException();

		}

	}

}
