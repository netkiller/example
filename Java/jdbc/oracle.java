package test;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class oracle {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("-------- Oracle JDBC Connection Testing ------");

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

		} catch (ClassNotFoundException e) {

			System.out.println("Where is your Oracle JDBC Driver?");
			e.printStackTrace();
			return;

		}

		System.out.println("Oracle JDBC Driver Registered!");

		Connection connection = null;

		try {

			connection = DriverManager.getConnection("jdbc:oracle:thin:@172.30.9.195:1522:orcl", "cf", "123123");

		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;

		}

		if (connection != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}

		Statement stmt;
		ResultSet rs;
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from testvv");
			while (rs.next()) {
				//System.out.println(rs.getString("id"));
				System.out.printf("%s, %s, %s\r\n", rs.getString(1), rs.getString(2), rs.getString(3));
			}
			
			rs.close();
			stmt.close();
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
		}
		
	}

}
