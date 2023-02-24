package com.java.management.orderManagement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

import com.java.management.Currency.InternationalCurrency;
import com.java.management.databaseConnectivity.ConnectionProvider;
import com.java.management.orderManagement.shipment.OrderShipment;

/** This class is to process the order in the cart according to the country **/
public class PlaceOrder {
	// creating connection with database
	Connection connection = ConnectionProvider.getConnection();
	// creating instance of BufferReader to take input from user
	BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

	// method to process the order
	public void orderProcess(int userId, int countryCode) {
		// variable to store the formatted currency w.r.t country
		String formattedCurrency = null;
		// creating the instance of InternationalCurrency class
		InternationalCurrency internationalCurrency = new InternationalCurrency();
		// variable to store the total value of cart
		int total = 0;
		try {
			// sql query to calculate total value of cart
			PreparedStatement preparedStatement = connection
					.prepareStatement("select sum(price*quantity) as total_price from user_order");
			// ececuting the query prepared
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				// storing the result of above query in variable total
				total = resultSet.getInt(1);
			}
			// variable to store code of Country
			Locale locale = null;

			// Swithch cases to get the respective country currency
			switch (countryCode) {
			case 1:
				locale = Locale.US;
				formattedCurrency = internationalCurrency.printCurrency(locale, (total / 90));
				break;
			case 2:
				locale = Locale.UK;
				formattedCurrency = internationalCurrency.printCurrency(locale, (total / 99));

				break;
			case 3:
				locale = Locale.FRANCE;
				formattedCurrency = internationalCurrency.printCurrency(locale, total / 89);
				break;

			case 4:
				formattedCurrency = internationalCurrency.printCurrency(total);
				break;

			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// printing the items of cart for the user and total amount
			PreparedStatement preparedStatement = connection.prepareStatement("select * from user_order");
			ResultSet resultSet = preparedStatement.executeQuery();
			System.out.println("The final order details are");
			System.out.println("------------------------------------");
			System.out.println("Product\t\tPrice\t\tQuantity");
			System.out.println("------------------------------------");
			while (resultSet.next()) {
				System.out
						.println(resultSet.getString(1) + "\t\t" + resultSet.getInt(2) + "\t\t" + resultSet.getInt(3));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("------------------------------------");
		System.out.println("Total Amount\t\t" + formattedCurrency);
		System.out.println("------------------------------------");
		// process the order
		System.out.println("Press 1 to process and payment");
		try {
			int choice = Integer.parseInt(bufferedReader.readLine());
			if (choice == 1) {
				// creating the instance of Ordershipment to ship the order
				OrderShipment orderShipment = new OrderShipment();
				// shipping the order
				orderShipment.shipmentStatus(userId);
			}
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}

	}
}
