package com.hexaware.controller;

import com.hexaware.dao.AdminDao;
import com.hexaware.entity.Customer;

/**
 * Handles admin login functionality.
 */
public class AdminLogin {

	/**
	 * Authenticates the user based on the provided email and password.
	 * 
	 * @param email    The admin's email.
	 * @param password The admin's password.
	 * @return The customer ID if the admin is authenticated, 0 otherwise.
	 */
	public int authenticateAdmin(String email, String password) {
		// Check if email and password are provided
		if (email.isEmpty() || password.isEmpty()) {
			System.out.println("Email and password are required.");
			return 0;
		}

		// Query the database to retrieve the user's record based on email
		AdminDao adminDao = new AdminDao();
		Customer admin = adminDao.getAdminByEmail(email);

		// Verify if user exists and password matches
		if (admin != null && admin.getPassword().equals(password)) {
			System.out.println("Login successful. Welcome, " + admin.getName() + "!");
			return admin.getCustomerId();
		} else {
			System.out.println("Invalid email or password. Please try again.");
			return 0;
		}
	}
}
