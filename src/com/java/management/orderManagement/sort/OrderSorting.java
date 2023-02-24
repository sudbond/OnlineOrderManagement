package com.java.management.orderManagement.sort;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.java.management.databaseConnectivity.ConnectionProvider;

/** This class is to sort the order history by price **/
public class OrderSorting {
	// method for sorting
	public void sortOrderByPrice() {
		// establishing the connection with database
		Connection connection = ConnectionProvider.getConnection();
		try {
			// query to sort price value in ascending order
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from order_history order by price");
			// executing the query
			ResultSet resultSet = preparedStatement.executeQuery();
			// printing on the console
			System.out.println("Product\t\tPrice");
			// printing the result of query on console
			while (resultSet.next()) {

				System.out.println(resultSet.getString(1) + "\t\t" + resultSet.getInt(2));
			}
			// catch block
		} catch (SQLException e) {
			// printing the error traces on console
			e.printStackTrace();

		}
	}
}
