package com.java.management.orderManagement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.java.management.orderManagement.sort.OrderSorting;
/**This class is for Managing order, This class is to call all methods**/
public class OrderManagement {
// DRIVER METHOD
	public static void main(String[] args) {
		// printing on console
		System.out.println("If you are a user, press 1");
		System.out.println("if You are Store Manager, press 2");
		// getting the BufferReader class instance to take input from user
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		// variable to store user input
		int choice = 0;
		// try block
		try {
			// taking input from user
			choice = Integer.parseInt(bufferedReader.readLine());
			// catch block if any Exception occurs
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
		// if block
		if (choice == 1) {
			// printing on console
			System.out.println("if you want to order something, press 1");
			System.out.println("if you want to cancel your previous order, press 2");
			// variable to store user input
			int choice2 = 0;
			// try block
			try {
				choice2 = Integer.parseInt(bufferedReader.readLine());
			} 
			// catch-block
			catch (NumberFormatException | IOException e) {
				e.printStackTrace();
			}// nested-if
			if (choice2 == 1) {
				//printing on console
				System.out.println("if you want to show all category press 1");
				System.out.println("if you want to buy directly, press 2");
				int button = 0;
				// try block
				try {
					button = Integer.parseInt(bufferedReader.readLine());
				} catch (NumberFormatException | IOException e) {
					e.printStackTrace();
				}
				// if of nested-if
				if (button == 1) {
					new ShowAllCategory().showAllCategory();
				} 
				// else of nested-if
				else if (button == 2) {
					new OrderImplementation().implementOrder();
				}
				// else if for outer if
			} else if (choice2 == 2) {
				new OrderCancellation().cancelOrder();
			}
		} else if (choice == 2) {
			// printing on console
			System.out.println("if you want to see all orders history, press 1");
			System.out.println("if you want to sort order by price , press 2");
			// variable to store user intput
			int choice3 = 0;
			// try block
			try {
				choice3 = Integer.parseInt(bufferedReader.readLine());
			} 
			//catch block
			catch (NumberFormatException | IOException e) {
				e.printStackTrace();
			}
			// nested if in else-if
			if (choice3 == 1) {
				new UserOrderHistory().showUserOrderHistory();
			}
			// nested else-if of else-if
			else if (choice3 == 2) {
				new OrderSorting().sortOrderByPrice();
			}
		}

	}
}
