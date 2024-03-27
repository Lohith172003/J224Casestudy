
/*package com.hexaware.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil{
	
	static Connection con;
	
	public static Connection getDBConn()
	{
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "#Lohith2003");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return con;

	}
	
	public static void main(String[] args)
	{
		System.out.println(getDBConn());
	}
	
}

*/

package com.hexaware.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The DBUtil class provides utility methods for database operations.
 */
public class DBUtil {

	static Connection con;

	/**
	 * Retrieves a database connection.
	 * 
	 * @return a Connection object representing the database connection
	 */
	public static Connection getDBConn() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "#Lohith2003");
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return con;

	}

	/**
	 * Main method to test the database connection.
	 * 
	 * @param args command-line arguments
	 */
	public static void main(String[] args) {
		System.out.println(getDBConn());
	}

}
