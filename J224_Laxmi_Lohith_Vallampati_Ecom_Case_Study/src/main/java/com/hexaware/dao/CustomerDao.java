
/*package com.hexaware.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hexaware.entity.Customer;
import com.hexaware.exception.CustomerNotFoundException;
import com.hexaware.util.DBUtil;

public class CustomerDao {

	Connection con;
	PreparedStatement ps;
	Statement stmt;
	ResultSet rs;

	public boolean createUser(Customer customer) {
		boolean res = false;
		try {
			con = DBUtil.getDBConn();
			String query = "INSERT INTO Customers (name, email, password) VALUES (?, ?, ?)";
			ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, customer.getName().toUpperCase());
			ps.setString(2, customer.getEmail());
			ps.setString(3, customer.getPassword());

			int noOfRowsInserted = ps.executeUpdate();

			if (noOfRowsInserted > 0) {
				rs = ps.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					System.out.println("Hey there! Delighted to have you join our e-commerce world!");
					System.out.println("This is Your Id: " + id);
					res = true;
				} else {
					System.out.println("Failed to retrieve userId");
				}
			} else {
				System.out.println("No rows inserted");
			}
		} catch (SQLException e) {
			if (e.getSQLState().equals("23000") && e.getMessage().contains("Duplicate entry")) {
				System.out.println("Customer registration with this email address is not possible as it is already in use.");
			} else {
				System.out.println("Error while creating user: " + e.getMessage());
			}
		} finally {
			closeResources();

		}
		return res;
	}

	public boolean deleteUser(int customerId) throws CustomerNotFoundException {
		con = DBUtil.getDBConn();
		boolean res = false;
		try {
			ps = con.prepareStatement("DELETE FROM Customers WHERE customer_id = ?");
			ps.setInt(1, customerId);
			int noOfRowsInserted = ps.executeUpdate();

			if (noOfRowsInserted > 0) {
				System.out.println("Customer bearing the ID: " + customerId + " has been successfully deleted.");
				res = true;
			} else {
				throw new CustomerNotFoundException("Customer wiht Id : " + customerId + " not exists.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return res;
	}

	public void updateUser(int customerId, String newName, String newEmail, String newPassword) {
		try {
			Connection con = DBUtil.getDBConn();
			StringBuilder query = new StringBuilder("UPDATE Customers SET ");
			List<String> updates = new ArrayList<>();

			if (newName != null) {
				updates.add("name = ?");
			}
			if (newEmail != null) {
				updates.add("email = ?");
			}
			if (newPassword != null) {
				updates.add("password = ?");
			}

			if (!updates.isEmpty()) {
				query.append(String.join(", ", updates));
				query.append(" WHERE customer_id = ?");

				PreparedStatement ps = con.prepareStatement(query.toString());
				int paramIndex = 1;

				if (newName != null) {
					ps.setString(paramIndex++, newName);
				}
				if (newEmail != null) {
					ps.setString(paramIndex++, newEmail);
				}
				if (newPassword != null) {
					ps.setString(paramIndex++, newPassword);
				}

				ps.setInt(paramIndex, customerId);

				int noOfRowsUpdated = ps.executeUpdate();
				System.out.println(noOfRowsUpdated + " rows updated");

			} else {
				System.out.println("No fields to update");
			}
		} catch (SQLException e) {
			if (e.getSQLState().equals("23000") && e.getMessage().contains("Duplicate entry")) {
				System.out.println("The new email address is you given is already in use.");
			} else {
				System.out.println("Error while updating user: " + e.getMessage());
			}
		} finally {
			closeResources();
		}

	}

	public Customer getUserByEmail(String email) {
		try {
			Connection con = DBUtil.getDBConn();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM Customers WHERE email = ?");
			ps.setString(1, email);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int customerId = rs.getInt("customer_id");
				String name = rs.getString("name");
				String password = rs.getString("password");
				return new Customer(customerId, name, email, password);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return null;
	}

	private void closeResources() {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}


*/

package com.hexaware.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hexaware.entity.Customer;
import com.hexaware.exception.CustomerNotFoundException;
import com.hexaware.util.DBUtil;

/**
 * UserDao handles database operations related to customers.
 */
public class CustomerDao {

	Connection con;
	PreparedStatement ps;
	Statement stmt;
	ResultSet rs;

	/**
	 * Creates a new user in the database.
	 *
	 * @param customer the Customer object containing user details
	 * @return true if the user is successfully created, false otherwise
	 */
	public boolean createUser(Customer customer) {
		boolean res = false;
		try {
			con = DBUtil.getDBConn();
			String query = "INSERT INTO Customers (name, email, password) VALUES (?, ?, ?)";
			ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, customer.getName().toUpperCase());
			ps.setString(2, customer.getEmail());
			ps.setString(3, customer.getPassword());

			int noOfRowsInserted = ps.executeUpdate();

			if (noOfRowsInserted > 0) {
				rs = ps.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					System.out.println("Hey there! Delighted to have you join our e-commerce world!");
					System.out.println("This is Your Id: " + id);
					res = true;
				} else {
					System.out.println("Failed to retrieve userId");
				}
			} else {
				System.out.println("No rows inserted");
			}
		} catch (SQLException e) {
			if (e.getSQLState().equals("23000") && e.getMessage().contains("Duplicate entry")) {
				System.out.println("Customer registration with this email address is not possible as it is already in use.");
			} else {
				System.out.println("Error while creating user: " + e.getMessage());
			}
		} finally {
			closeResources();

		}
		return res;
	}

	/**
	 * Deletes a user from the database.
	 *
	 * @param customerId the ID of the customer to be deleted
	 * @return true if the user is successfully deleted, false otherwise
	 * @throws CustomerNotFoundException if the customer is not found
	 */
	public boolean deleteUser(int customerId) throws CustomerNotFoundException {
		con = DBUtil.getDBConn();
		boolean res = false;
		try {
			ps = con.prepareStatement("DELETE FROM Customers WHERE customer_id = ?");
			ps.setInt(1, customerId);
			int noOfRowsInserted = ps.executeUpdate();

			if (noOfRowsInserted > 0) {
				System.out.println("Customer bearing the ID: " + customerId + " has been successfully deleted.");
				res = true;
			} else {
				throw new CustomerNotFoundException("Customer wiht Id : " + customerId + " not exists.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return res;
	}

	/**
	 * Updates user information in the database.
	 *
	 * @param customerId  the ID of the customer to be updated
	 * @param newName     the new name to update (null if not updating)
	 * @param newEmail    the new email to update (null if not updating)
	 * @param newPassword the new password to update (null if not updating)
	 */
	public void updateUser(int customerId, String newName, String newEmail, String newPassword) {
		try {
			Connection con = DBUtil.getDBConn();
			StringBuilder query = new StringBuilder("UPDATE Customers SET ");
			List<String> updates = new ArrayList<>();

			if (newName != null) {
				updates.add("name = ?");
			}
			if (newEmail != null) {
				updates.add("email = ?");
			}
			if (newPassword != null) {
				updates.add("password = ?");
			}

			if (!updates.isEmpty()) {
				query.append(String.join(", ", updates));
				query.append(" WHERE customer_id = ?");

				PreparedStatement ps = con.prepareStatement(query.toString());
				int paramIndex = 1;

				if (newName != null) {
					ps.setString(paramIndex++, newName);
				}
				if (newEmail != null) {
					ps.setString(paramIndex++, newEmail);
				}
				if (newPassword != null) {
					ps.setString(paramIndex++, newPassword);
				}

				ps.setInt(paramIndex, customerId);

				int noOfRowsUpdated = ps.executeUpdate();
				System.out.println(noOfRowsUpdated + " rows updated");

			} else {
				System.out.println("No fields to update");
			}
		} catch (SQLException e) {
			if (e.getSQLState().equals("23000") && e.getMessage().contains("Duplicate entry")) {
				System.out.println("The new email address is you given is already in use.");
			} else {
				System.out.println("Error while updating user: " + e.getMessage());
			}
		} finally {
			closeResources();
		}

	}

	/**
	 * Method to retrieve user information from the database based on email.
	 * 
	 * @param email The email of the user to retrieve.
	 * @return The Customer object containing user information if found, otherwise
	 *         null.
	 */
	public Customer getUserByEmail(String email) {
		try {
			Connection con = DBUtil.getDBConn();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM Customers WHERE email = ?");
			ps.setString(1, email);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int customerId = rs.getInt("customer_id");
				String name = rs.getString("name");
				String password = rs.getString("password");
				return new Customer(customerId, name, email, password);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return null;
	}

	private void closeResources() {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
