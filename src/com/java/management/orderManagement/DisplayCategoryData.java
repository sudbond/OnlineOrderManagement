package com.java.management.orderManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.java.management.databaseConnectivity.ConnectionProvider;

/** This class is to display content associated with a table **/
public class DisplayCategoryData {
	// method to show all data of table
	public void display(String tableName) {
		try {
			// creating the connection with the database
			Connection connection = ConnectionProvider.getConnection();
			// sql query
			String queryString = "select * from " + tableName;
			// creating and executing sql query
			PreparedStatement preparedStatement = connection.prepareStatement(queryString);
			ResultSet resultSet = preparedStatement.executeQuery();
			// printing the query result on console
			System.out.println("--------------------------------------");
			System.out.println("Product\t\t" + "|\t\t" + "Price");
			System.out.println("--------------------------------------");
			while (resultSet.next()) {
				// printing the result
				System.out.println(resultSet.getString(1) + "\t\t\t\t" + resultSet.getInt(2));
			}
			// catch block
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
