package com.java.management.Currency;

import java.text.NumberFormat;
import java.util.Locale;

/** This class is to format the currency according to Country **/
public class InternationalCurrency {
	/** method to format the currency **/
	public String printCurrency(int totalAmount) {
		// returning currency for India
		return "\u20B9" + totalAmount;
	}

	/** Overloaded method for the countries other than India **/
	public String printCurrency(Locale locale, int totalAmount) {
		// formatting the currency according to Contry
		NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
		String currency = formatter.format(totalAmount);
		// returning the formatted currency
		return currency;
	}
}
