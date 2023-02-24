package com.java.management.orderManagement.shipment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.java.management.Date.DateFormatter;
import com.java.management.databaseConnectivity.ConnectionProvider;
import com.java.management.orderManagement.UserOrderHistory;

public class OrderShipment {
	BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

	/** method to create userId for every new user **/
	public int createUserID() {
		// variable to store user id
		int userId = 0;
		// creating connection with database
		Connection connection = ConnectionProvider.getConnection();
		// getting the name of user
		System.out.println("Enter your name");
		// variable to store the name of user
		String userName = null;
		try {
			// taking input from user for username
			userName = bufferedReader.readLine();
		} catch (IOException e) {

			e.printStackTrace();
		}
		try {
			// getting the auto incremented orderID
			String returnCols[] = { "orderId" };
			String query = "insert into Order_History_Id(customer_name) values(?)";
			// preparing the sqlQuery
			PreparedStatement preparedStatement = connection.prepareStatement(query, returnCols);
			preparedStatement.setString(1, userName);
			// executing the query
			preparedStatement.executeUpdate();

			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				// storing the OrderId in the variable in UserId
				userId = resultSet.getInt(1);
			}
			// printing the userID on console
			System.out.println("Your user id is = " + userId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// returning the user id
		return userId;

	}

	/** method to get the shipping status **/
	public void shipmentStatus(int userId) {
		// storing the cart hisory to UserOrderHistory
		new UserOrderHistory().updateUserOrderHistory(userId);
		// creating the instance of DateFormatter class to get dates for shipping
		// and delivering the products
		DateFormatter dateFormatter = new DateFormatter();
		// printing on the console
		System.out.println("You have successfully placed your order");
		// method call to getDate() method
		System.out.println("Your order will be shipped on " + dateFormatter.getDate(1));
		System.out.println("Your order will be delivered on " + dateFormatter.getDate(3));
		// printing on the console
		System.out.println("Thankyou for shopping with us");
		System.out.println("You can cancel your order anytime before delivery");
		// getting out of the application
		System.exit(0);
	}

}
