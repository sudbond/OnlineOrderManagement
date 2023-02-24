package com.java.management.databaseConnectivity;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/** This class is to establish connection with the database **/
public class ConnectionProvider {
	// static variable to store the connection
	public static Connection connection = null;

	/** method to connect with database **/
	public static Connection getConnection() {
		if (connection == null) {
			// creating the instance of Properties class
			Properties properties = new Properties();
			// creating the instance of FileInputSteam class
			FileInputStream fileInputStream = null;
			try {
				// getting the details of driver, url, username, passoword from the file
				fileInputStream = new FileInputStream(
						"C:\\Users\\Sudeep\\my-java-workspace\\OnlineOrderManagement\\src\\com\\java\\management\\databaseConnectivity\\DatabaseProperties.properties");
				properties.load(fileInputStream);
				// retrieving the details from using getProperty method of Property class

				String url = properties.getProperty("url");
				String username = properties.getProperty("username");
				String password = properties.getProperty("password");
				/*
				 * Note:- From JDBC 4.0/Java 1.6, There is not longer require to load and
				 * register driver class
				 */
				// Establishing connection bewtwwn app and database
				Connection connection = DriverManager.getConnection(url, username, password);
				// returning the connection
				return connection;
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return connection;
	}
}
