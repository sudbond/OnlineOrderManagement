package com.java.management.Date;

import java.time.LocalDate;

/** This class is for getting the Date **/
public class DateFormatter {
	// this method will return LocalDate
	public LocalDate getDate(int days) {
		// to get today's date
		LocalDate date = LocalDate.now();
		// to get today's date + days
		LocalDate date1 = date.plusDays(days);
		// returning the date
		return date1;
	}
}
