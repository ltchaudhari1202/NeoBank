package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.neosoft.model.Customer;
import com.neosoft.model.InsufficientBalanceException;
import com.neosoft.model.InvalidAgeException;
import com.neosoft.model.Transaction;
import com.neosoft.model.UserNotFoundException;
import com.neosoft.service.AdminOperationsImpl;
import com.neosoft.service.CommonInterface;
import com.neosoft.service.CustomerOperationsImpl;

class Login implements CommonInterface {
	Scanner sc = new Scanner(System.in);
	int choice;

	public void adminLogin() throws UserNotFoundException {
		System.out.println("-----Admin Login------");

		String name = userName;
		String password = this.password;
		System.out.println("Enter Name:");
		String enteredName = sc.next();
		System.out.println("Enter Password:");
		String enteredPassword = sc.next();

		if (enteredName.equals(name) && enteredPassword.equals(password)) {
			System.out.println("Login Successfully");
			do {
				System.out.println("Select the operation that you want to perform");
				System.out.println("1. Account Opening");
				System.out.println("2. Amount Deposit");
				System.out.println("3. Amount Withdraw");
				System.out.println("4. Delete Account");
				System.out.println("5. Show all Customers");
				System.out.println("6. Logout");
				System.out.println("7. Exit");
				choice = sc.nextInt();
				AdminOperationsImpl admin = new AdminOperationsImpl();
				switch (choice) {
				case 1:

					try {
						admin.openAccount();
					} catch (InvalidAgeException e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
						System.out.println("Age should be greater than 18");
					}

					break;

				case 2:

					try {
						admin.depositMoney();
					} catch (UserNotFoundException e1) {
						// TODO Auto-generated catch block
						//e1.printStackTrace();
						System.out.println("User credentials not found");
					}

					break;

				case 3:

					try {
						admin.withdrawMoney();
					} catch (UserNotFoundException | InsufficientBalanceException e1) {
						// TODO Auto-generated catch block
						//e1.printStackTrace();
						System.out.println("User credentials not found");
						System.out.println("Insufficient balance");
					}

					break;

				case 4:
					try {
						admin.deleteCustomerAccount();
					} catch (UserNotFoundException e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
						System.out.println("User credentials not found");
					}

					break;

				case 5:
					admin.showAllCustomers();
					break;

				case 6:
					System.out.println("Logout Successfully");
					adminLogin();

					break;

				case 7:
					break;

				}

			} while (choice != 7);
		}

		else {
			//System.out.println("Invalid Credentials");
			throw new UserNotFoundException();

		}

	}

	public void customerLogin() throws UserNotFoundException {
		System.out.println("--------Customer Login--------");
		
		System.out.println("Enter account number:");
		long enteredAcountNumber = sc.nextLong();
		System.out.println("Enter password:");
		int age = sc.nextInt();
		LinkedList<Customer> customerList = NeoBankMain.custList;
		//System.out.println(customerList);

		List<Customer> matchingObjects = customerList.stream()
				.filter(item -> enteredAcountNumber == item.getAccountNumber()).collect(Collectors.toList());

		if (!matchingObjects.isEmpty()) {
			Customer customer2 = matchingObjects.get(0);

			if (enteredAcountNumber == customer2.getAccountNumber() && age == customer2.getAge()) {
				System.out.println("Login successfully");
				do {
					System.out.println("Enter the choice that you want to perform:");
					System.out.println("1. View Account");
					System.out.println("2. View all Transaction");
					System.out.println("3. Transfer money");
					System.out.println("4. View last 5 transaction");
					System.out.println("5. Logout");
					System.out.println("6. Exit");
					choice = sc.nextInt();
					CustomerOperationsImpl custImpl = new CustomerOperationsImpl();
					switch (choice) {
					case 1:
						try {
							custImpl.viewAccount();
						} catch (UserNotFoundException e) {
							// TODO Auto-generated catch block
							//e.printStackTrace();
							System.out.println("User credentials not found");
						}
						break;

					case 2:
						try {
							custImpl.viewAllTransaction();
						} catch (UserNotFoundException e1) {
							// TODO Auto-generated catch block
							//e1.printStackTrace();
							System.out.println("User credentials not found");
						}
						break;

					case 3:
						try {
							custImpl.transferMoney();
						} catch (UserNotFoundException e) {
							// TODO Auto-generated catch block
							//e.printStackTrace();
							System.out.println("User credentials not found");
						}
						break;

					case 4:
						custImpl.viewLastTransaction();
						break;

					case 5:
						System.out.println("Logout successfully");
						customerLogin();
						break;

					case 6:
						
						break;
					}

				} while (choice != 6);

			} else {
				//System.out.println("Invalid credentials");
				throw new UserNotFoundException();
			}
		}

	}
}

public class NeoBankMain {
	public static LinkedList<Customer> custList = new LinkedList<>();
	public static LinkedList<Transaction> transactionList = new LinkedList<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Login login = new Login();

		while (true) {
			System.out.println("--------Welcome to Neosoft Bank----------");
			System.out.println("1.Login operation for admin");
			System.out.println("2.Login operation for user");
			System.out.println("3.Exit");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:

				try {
					login.adminLogin();
				} catch (UserNotFoundException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					System.out.println("User credentials not found");
				}

				break;

			case 2:
				try {
					login.customerLogin();
				} catch (UserNotFoundException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					System.out.println("User credentials not found");
				}
				break;

			case 3:
				System.out.println("Exit Successfully");
				break;

			}
		}

	}

}
