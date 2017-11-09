package org.code;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");

		// Get standard date and time
		java.util.Date utilDate = new java.util.Date();
		long javaTime = utilDate.getTime();
		System.out.println("The Java Date is:" + utilDate.toString());

		// Get and display SQL DATE
		java.sql.Date sqlDate = new java.sql.Date(javaTime);
		System.out.println("The SQL DATE is: " + sqlDate.toString());

		// Get and display SQL TIME
		java.sql.Time sqlTime = new java.sql.Time(javaTime);
		System.out.println("The SQL TIME is: " + sqlTime.toString());
		
		// Get and display SQL TIMESTAMP
		java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(javaTime);
		System.out.println("The SQL TIMESTAMP is: " + sqlTimestamp.toString());
	}
}
