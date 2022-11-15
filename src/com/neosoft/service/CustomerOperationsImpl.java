package com.neosoft.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.neosoft.model.Customer;
import com.neosoft.model.Transaction;
import com.neosoft.model.UserNotFoundException;

import main.NeoBankMain;

public class CustomerOperationsImpl implements CustomerOperationsInterface {

	//long accNo;
	LinkedList<Customer> cust = NeoBankMain.custList;
	LinkedList<Transaction> trans = NeoBankMain.transactionList;
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	Scanner sc = new Scanner(System.in);
	Customer customer;
	Transaction transaction;

	@Override
	public void viewAccount() throws UserNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("Enter Account number:");
		long accNo = sc.nextLong();

		Optional<Customer> optional = cust.stream().filter(item -> accNo == item.getAccountNumber()).findFirst();

		if (!optional.isEmpty()) {
			Customer customer = optional.get();
			System.out.println("Customer Account Details:");
			System.out.println("Name:" + customer.getName());
			System.out.println("Account number:" + customer.getAccountNumber());
			System.out.println("Balance:" + customer.getBalance());
			System.out.println("Age:" + customer.getAge());
		} else {
			//System.out.println("Invalid credentials");
			throw new UserNotFoundException();
		}
	}

	@Override
	public void viewAllTransaction() throws UserNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("Enter account number:");
		long accNo = sc.nextLong();
		
//		Optional<Transaction> optional = trans.stream().
//				filter(item -> accNo == item.getAccountNumber()).
//				findFirst();
//
//		if (!optional.isEmpty()) {
//				System.out.println(optional);
//			}

		for(Transaction tx:NeoBankMain.transactionList) {
			if(tx.getAccountNumber()==accNo) {
				System.out.println(tx);
			}
			
		
			
		}

		

	}

	@Override
	public void transferMoney() throws UserNotFoundException {
		// TODO Auto-generated method stub
		boolean flag = false;
		System.out.println("Enter account number of payer:");
		long accNoOfPayer = sc.nextLong();

		List<Customer> matchingObjects = cust.stream().filter(item -> accNoOfPayer == item.getAccountNumber())
				.collect(Collectors.toList());

		if (!matchingObjects.isEmpty()) {
			flag = true;
			Customer customer = matchingObjects.get(0);
			System.out.println("Enter amount to be transfer:");
			BigDecimal amt = sc.nextBigDecimal();
			customer.setBalance(customer.getBalance().subtract(amt));
			transaction = new Transaction(accNoOfPayer, "Withdraw", amt, customer.getBalance());
			NeoBankMain.transactionList.add(transaction);
			System.out.println("Amount credited from account " + accNoOfPayer + " Rs: " + amt);

			System.out.println("Enter account number of receiver:");
			long accNoOfReceiver = sc.nextLong();
			List<Customer> matchingObjects1 = cust.stream().filter(item -> accNoOfReceiver == item.getAccountNumber())
					.collect(Collectors.toList());

			if (!matchingObjects1.isEmpty()) {
				flag = true;
				Customer customer1 = matchingObjects1.get(0);

				customer1.setBalance(customer1.getBalance().add(amt));
				transaction = new Transaction(accNoOfPayer, "Deposit", amt, customer1.getBalance());
				NeoBankMain.transactionList.add(transaction);
				System.out.println("Amount deposited to account " + accNoOfReceiver + " Rs: " + amt);
				System.out.println("Avaliable balance: " + customer1.getBalance());
			}

		}
		if (flag == false) {
			//System.out.println("User not found");
			throw new UserNotFoundException();
		}
	}

	@Override
	public void viewLastTransaction() {
		// TODO Auto-generated method stub
		System.out.println("Enter account number:");
		long accNo=sc.nextLong();
		
		LinkedList<Transaction>tr=new LinkedList<>();
		for(Transaction tx:trans) {
			tr.add(tx);
		}
		if(tr.size()>5) {
			Collections.reverse(tr);
			System.out.println(tr.subList(0,5));
			//System.out.println(tr.subList(tr.size()-5, tr.size()));
		}else {
			Collections.reverse(tr);
//			System.out.println(tr);
			Iterator<Transaction>itr=tr.iterator();
			while(itr.hasNext()) {
				Transaction tx=itr.next();
				System.out.println(tx);
			}
		}

		

	}

}
