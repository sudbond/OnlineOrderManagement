package com.java.management.orderManagement;

import java.sql.ResultSet;
import java.sql.SQLException;
/**This class is to add value in cart**/
public class Cart {
	/** method to add product in cart **/
	public void addProduct(String productName, String tableName, int price, String region, int orderId) {
		// creating the instance of SearchDatabase class
		SearchDatabase searchDatabase = new SearchDatabase();
		// searching the database and storing the value in Resultset type ofvariable
		ResultSet resultSet = searchDatabase.searchValue(productName, tableName);
		// creating the instance of AddData class
		AddData addData = new AddData();
		// variable to store the name of the product;
		String name = null;

		try {
			if (resultSet.next()) {
				// getting the name and price of product using resultset
				name = resultSet.getString(1);
				price = resultSet.getInt(2);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		//method call
		// add details of product to a database table
		addData.addDataToTable(name, price, region, orderId);

	}
}
