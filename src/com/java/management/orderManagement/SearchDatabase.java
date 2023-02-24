package com.java.management.orderManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.java.management.databaseConnectivity.ConnectionProvider;

/** This class is to search the product name in the database **/
public class SearchDatabase {
	// searching the product in the table passed as arguments
	public ResultSet searchValue(String productName, String tableName) {
		try {
			// creating connection with database
			Connection connection = ConnectionProvider.getConnection();
			// preparing the sql query
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from " + tableName + " where name = ?");
			// setting value in the query
			preparedStatement.setString(1, productName);
			// executing the query
			ResultSet resultSet = preparedStatement.executeQuery();
			// returning the resultSet
			return resultSet;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
