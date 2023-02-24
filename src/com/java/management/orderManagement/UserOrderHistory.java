package com.java.management.orderManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.java.management.databaseConnectivity.ConnectionProvider;

/** This class is to track all orders **/
public class UserOrderHistory {
	// creating the connection with database
	Connection connection = ConnectionProvider.getConnection();

	// updating the user order to order_history table
	public void updateUserOrderHistory(int orderId) {

		try {
			// preparing the query
			PreparedStatement preparedStatement = connection.prepareStatement("select * from user_order");
			// executing the query
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				// preparing the query to add cart product to user-history table
				PreparedStatement preparedStatement2 = connection.prepareStatement(
						"Insert into order_history(PRODUCT_NAME,PRICE,REGION,ORDERID) VALUES(?,?,?,?)");
				// setting values in the query
				preparedStatement2.setString(1, resultSet.getString(1));
				preparedStatement2.setInt(2, resultSet.getInt(2));
				preparedStatement2.setString(3, resultSet.getString(4));
				preparedStatement2.setInt(4, orderId);
				// executing the query
				preparedStatement2.executeQuery();
			}
			// printing on the console
			System.out.println("order history updated successfully");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/** method to show all orders history **/
	public void showUserOrderHistory() {
		try {
			// preparing the sql query
			PreparedStatement preparedStatement = connection.prepareStatement("select * from order_history");
			// executing the query
			ResultSet resultSet = preparedStatement.executeQuery();
			// printing the order_history table content on console
			System.out.println("---------------------------------------------------------");
			System.out.println("Product Name\tPrice\t\tRegion\t\tOrder ID");
			System.out.println("---------------------------------------------------------");
			while (resultSet.next()) {
				System.out.println(resultSet.getString(1) + "\t\t" + resultSet.getInt(2) + "\t\t"
						+ resultSet.getString(3) + "\t\t" + resultSet.getInt(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
