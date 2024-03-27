package com.hexaware.entity;

/**
 * This customer class represents a customer with an ID, name, email, and
 * password.
 * 
 * @version 1
 */
public class Customer {

	int customerId;
	String name;
	String email;
	String password;

	/**
	 * Constructs a new Customer object with provided details.
	 * 
	 * @param customerId The ID of the customer.
	 * @param name       The name of the customer.
	 * @param email      The email address of the customer.
	 * @param password   The password of the customer.
	 */
	public Customer(int customerId, String name, String email, String password) {
		super();
		this.customerId = customerId;
		this.name = name;
		this.email = email;
		this.password = password;
	}

	/**
	 * Default constructor for Customer class.
	 */
	public Customer() {
		super();
	}

	/**
	 * Retrieves the customer ID.
	 * 
	 * @return the customer ID
	 */
	public int getCustomerId() {
		return customerId;
	}

	/**
	 * Sets the customer ID.
	 * 
	 * @param customerId the customer ID to be set
	 */
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	/**
	 * Retrieves the name of the customer.
	 * 
	 * @return the name of the customer
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the customer.
	 * 
	 * @param name the name of the customer to be set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Retrieves the email address of the customer.
	 * 
	 * @return the email address of the customer
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email address of the customer.
	 * 
	 * @param email the email address of the customer to be set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Retrieves the password of the customer.
	 * 
	 * @return the password of the customer
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password of the customer.
	 * 
	 * @param password the password of the customer to be set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Returns a string representation of the Customer object.
	 * 
	 * @return a string representation of the Customer object
	 */
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", name=" + name + ", email=" + email + ", password=" + password
				+ "]";
	}
}
