package com.hexaware.controller;

import com.hexaware.dao.CustomerDao;
import com.hexaware.entity.Customer;
import com.hexaware.exception.CustomerNotFoundException;

/**
 * Handles user login functionality.
 */
public class UserLogin {

	/**
	 * Authenticates the user based on the provided email and password.
	 * 
	 * @param email    The user's email.
	 * @param password The user's password.
	 * @return True if the user is authenticated, false otherwise.
	 * @throws CustomerNotFoundException If the user is not found in the database.
	 */
	public int authenticateUser(String email, String password) throws CustomerNotFoundException {
		// Check if email and password are provided
		if (email.isEmpty() || password.isEmpty()) {
			System.out.println("Email and password are required.");
			return 0;
		}

		// Query the database to retrieve the user's record based on email
		CustomerDao userDao = new CustomerDao();
		Customer user = userDao.getUserByEmail(email);

		// Verify if user exists and password matches
		if (user != null && user.getPassword().equals(password)) {
			System.out.println("Login successful. Welcome, " + user.getName() + "!");
			return user.getCustomerId();
		} else {
			System.out.println("Invalid email or password.");
			throw new CustomerNotFoundException("User with the given credentials not found.");
		}
	}
}
