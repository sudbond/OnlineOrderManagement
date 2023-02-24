package com.java.management.orderManagement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/** to show all categories **/
public class ShowAllCategory {
	// enum type category
	enum Category {
		LAPTOP, WATCH, HEADPHONES;
	}

	// method to show All category data
	public void showAllCategory() {
		System.out.println("All Categories");
		//
		DisplayCategoryData displayCategoryData = new DisplayCategoryData();
		// printing all category on console using for each loop
		for (Category category : Category.values()) {
			System.out.println(category);
		}
		// printing on console
		System.out.println("Press 1 to see all laptops");
		System.out.println("Press 2 to see all Watches");
		System.out.println("Press 3 to see all Headphones");
		System.out.println("Press 0 to exit");
		// creating the instance of BufferReader to take input from user
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		// infinite while loop
		while (true) {
			int num;
			try {
				// taking input from user
				num = Integer.parseInt(bufferedReader.readLine());
				// Switch case on user input
				switch (num) {
				case 1: // to display laptop data
					displayCategoryData.display("laptop");
					break;
				case 2: // to display watch
					displayCategoryData.display("watch");
					break;
				case 3: // to display headphones
					displayCategoryData.display("headphone");
					break;

				case 0: // to exit from the application
					System.exit(0);
					break;
				}
				// catch block
			} catch (NumberFormatException | IOException e) {
				e.printStackTrace();
			}
		}

	}
}
