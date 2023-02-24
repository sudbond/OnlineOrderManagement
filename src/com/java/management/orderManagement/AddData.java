package com.java.management.orderManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.java.management.databaseConnectivity.ConnectionProvider;

/** This class is to add bought product to user_order table **/
public class AddData {
	// method to add data to user_order
	public void addDataToTable(String name, int price, String region, int orderId) {
		try {
			int quantity = 1;
			// creating connection with database
			Connection connection = ConnectionProvider.getConnection();
			// preparing sql query
			PreparedStatement preparedStatement = connection.prepareStatement(
					"insert into user_order(Product,price,quantity,region,orderId) values(?,?,?,?,?)");

			// setting the values in the query
			preparedStatement.setString(1, name);
			preparedStatement.setInt(2, price);
			preparedStatement.setInt(3, quantity);
			preparedStatement.setString(4, region);
			preparedStatement.setInt(5, orderId);
			// executing the prepared query
			preparedStatement.executeUpdate();
			System.out.println("Item added in the cart successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
