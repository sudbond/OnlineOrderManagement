package com.java.management.orderManagement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.java.management.databaseConnectivity.ConnectionProvider;

/** This class is to cancel the order **/
public class OrderCancellation {
	// method to cancel the order
	public void cancelOrder() {
		System.out.println("Please enter your orderId");
		// creating instance of BufferReaderClass to take input from user
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		// variable to store Order id 
		int orderId = 0;
		try {
			// taking orderId from user
			orderId = Integer.parseInt(bufferedReader.readLine());
		} catch (NumberFormatException | IOException e1) {

			e1.printStackTrace();
		}
		// variable to store the name of user associated with orderId
		String name = null;
		Connection connection = ConnectionProvider.getConnection();
		try {
			// preparing the sql query to delete the related items associated with orderId
			PreparedStatement preparedStatement = connection
					.prepareStatement("delete from order_history where orderId = ?");
			preparedStatement.setInt(1, orderId);
			preparedStatement.executeUpdate();
			// sql query to get the name of user associated with orderId
			PreparedStatement preparedStatement2 = connection
					.prepareStatement("select customer_name from order_history_id where orderId = ?");
			//setting value in the query
			preparedStatement2.setInt(1, orderId);
			// executing the query
			ResultSet resultSet = preparedStatement2.executeQuery();
			if (resultSet.next()) {
				// getting and storing the name of user
				name = resultSet.getString(1);
			}
			// priting on the console
			System.out.println("Mr. " + name + ", Your order has been cancelled");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
