package com.java.management.orderManagement;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.java.management.databaseConnectivity.ConnectionProvider;
import com.java.management.orderManagement.shipment.OrderShipment;

public class OrderImplementation {
	// creating the instance of MainMenuPrinter
	MainMenuPrinter mainMenuPrinter = new MainMenuPrinter();
	// creating the instance of OrderShipment
	OrderShipment orderShipment = new OrderShipment();
	// method call to create userId for a user
	int userId = orderShipment.createUserID();
	// creating the instance of BufferReader to take input from user
	BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

	// method to implent order and its details
	public void implementOrder() {
		// variable to store country and its code
		String country = null;
		int countryCode = 0;
		// try block
		try {
			// printing on console
			System.out.println("Please select your country");
			System.out.println("1.US\n2.UK\n3.France\n4.India");
			// taking input from user for country code
			countryCode = Integer.parseInt(bufferedReader.readLine());
			// assigning code to country
			if (countryCode == 1) {
				country = "US";
			} else if (countryCode == 2) {
				country = "UK";
			} else if (countryCode == 3) {
				country = "France";
			} else if (countryCode == 4) {
				country = "India";
			}
		}
		// catch-block to catch exception if occurs
		catch (Exception e) {
			e.printStackTrace();
		}
		// connecting with the database using our Connection Provider class
		Connection connection = ConnectionProvider.getConnection();
		PreparedStatement preparedStatement1;
		// try-block
		try {
			// preparing the sql query
			preparedStatement1 = connection.prepareStatement("truncate table user_order");
			// executing the sql query
			preparedStatement1.executeQuery();
		}
		// catch-block
		catch (SQLException e1) {
			e1.printStackTrace();
		}
		// creating the instance of Cart class
		Cart cart = new Cart();
		// creating the instance of DisplayCategoryData
		DisplayCategoryData displayCategoryData = new DisplayCategoryData();
		// method call
		mainMenuPrinter.printMenu();
		// infinite for-loop
		while (true) {
			// try-block
			try {
				// taking input from user
				int num = Integer.parseInt(bufferedReader.readLine());
				// Switch on user input
				switch (num) {
				case 1:
					// displaying all type of laptops
					displayCategoryData.display("laptop");
					// method call to print menu category
					mainMenuPrinter.printLaptop();
					// taking input from user
					int key = Integer.parseInt(bufferedReader.readLine());
					// switch case
					switch (key) {
					case 1: // to add dell laptop in cart
						cart.addProduct("Dell", "laptop", 0, country, userId);
						mainMenuPrinter.printMenu();
						break;
					case 2: // to add Acer laptop in cart
						cart.addProduct("Acer", "laptop", 0, country, userId);
						mainMenuPrinter.printMenu();
						break;
					case 3: // to add Apple laptop in bag
						cart.addProduct("Apple", "laptop", 0, country, userId);
						mainMenuPrinter.printMenu();
						break;
					}
					break;
				case 2:
					// displaying all watches
					displayCategoryData.display("Watch");
					mainMenuPrinter.printWatch();
					// taking input from user
					int key1 = Integer.parseInt(bufferedReader.readLine());
					switch (key1) {
					case 1: // to add Rado watch in cart
						cart.addProduct("Rado", "watch", 0, country, userId);
						mainMenuPrinter.printMenu();
						break;
					case 2: // to add Apple watch in cart
						cart.addProduct("Apple", "watch", 0, country, userId);
						mainMenuPrinter.printMenu();
						break;
					case 3: // to ad Rolex watch in cart
						cart.addProduct("Rolex", "watch", 0, country, userId);
						mainMenuPrinter.printMenu();
						break;
					}
					break;
				case 3: // to display all type of headphones
					displayCategoryData.display("headphone");
					// displaying main menu for shopping
					mainMenuPrinter.printHeadphone();
					// user input
					int key2 = Integer.parseInt(bufferedReader.readLine());
					// Switch
					switch (key2) {
					case 1: // to add boat headphone in the cart
						cart.addProduct("Boat", "headphone", 0, country, userId);
						mainMenuPrinter.printMenu();
						break;
					case 2: // to add Onepus headphone in the cart
						cart.addProduct("Oneplus", "headphone", 0, country, userId);
						mainMenuPrinter.printMenu();
						break;
					case 3: // to ad Noise headphone in the cart
						cart.addProduct("Noise", "headphone", 0, country, userId);
						mainMenuPrinter.printMenu();
						break;
					}
					break;
				case 4: // to place the order in the cart
						// creating the instance of place Order
					PlaceOrder placeOrder = new PlaceOrder();
					// placing the order
					placeOrder.orderProcess(userId, countryCode);
					break;

				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}
}
