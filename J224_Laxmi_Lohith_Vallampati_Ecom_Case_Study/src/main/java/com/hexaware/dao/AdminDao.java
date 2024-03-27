package com.hexaware.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.hexaware.entity.Customer;
import com.hexaware.util.DBUtil;

/**
 * The AdminDao class provides methods to perform database operations related to
 * admin entities.
 */
public class AdminDao {

	Connection con;
	PreparedStatement ps;
	Statement stmt;
	ResultSet rs;

	/**
	 * Creates a new admin in the database.
	 *
	 * @param admin the Customer object containing admin details
	 * @return true if the admin is successfully created, false otherwise
	 */
	public boolean createAdmin(Customer admin) {
		boolean res = false;
		try {
			con = DBUtil.getDBConn();
			String query = "INSERT INTO admin (name, email, password) VALUES (?, ?, ?)";
			ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, admin.getName());
			ps.setString(2, admin.getEmail());
			ps.setString(3, admin.getPassword());

			int noOfRowsInserted = ps.executeUpdate();

			if (noOfRowsInserted > 0) {
				rs = ps.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					System.out.println("Hey there! Delighted to have you join our e-commerce world!");
					System.out.println("This is Your Id: " + id);
					res = true;
				} else {
					System.out.println("Failed to retrieve adminId");
				}
			} else {
				System.out.println("No rows inserted");
			}
		} catch (SQLException e) {
			if (e.getSQLState().equals("23000") && e.getMessage().contains("Duplicate entry")) {
				System.out.println("Admin registration with this email address is not possible as it is already in use.");
			} else {
				System.out.println("Error while creating admin: " + e.getMessage());
			}
		} finally {
			closeResources();
		}
		return res;
	}

	/**
	 * Retrieves admin information from the database based on email.
	 * 
	 * @param email The email of the admin to retrieve.
	 * @return The Customer object containing admin information if found, otherwise
	 *         null.
	 */
	public Customer getAdminByEmail(String email) {
		try {
			Connection con = DBUtil.getDBConn();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM admin WHERE email = ?");
			ps.setString(1, email);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int customerId = rs.getInt("admin_id");
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

	/**
	 * Updates admin information in the database.
	 * 
	 * @param adminId     The ID of the admin to be updated.
	 * @param newName     The new name of the admin.
	 * @param newEmail    The new email of the admin.
	 * @param newPassword The new password of the admin.
	 */
	public void updateAdmin(int adminId, String newName, String newEmail, String newPassword) {

		try {
			Connection con = DBUtil.getDBConn();
			StringBuilder query = new StringBuilder("UPDATE Admin SET ");
			if (newName != null) {
				query.append("name = ?, ");
			}
			if (newEmail != null) {
				query.append("email = ?, ");
			}
			if (newPassword != null) {
				query.append("password = ?, ");
			}

			query.delete(query.length() - 2, query.length());
			query.append(" WHERE admin_id = ?");

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

			ps.setInt(paramIndex, adminId);

			int noOfRowsUpdated = ps.executeUpdate();
			System.out.println(noOfRowsUpdated + " rows updated");

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
