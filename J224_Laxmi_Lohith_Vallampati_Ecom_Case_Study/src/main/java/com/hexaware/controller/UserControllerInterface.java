package com.hexaware.controller;

/**
 * UserControllerInterface defines the contract for user-related operations in
 * the controller layer.
 */
public interface UserControllerInterface {

	/**
	 * Adds a new customer.
	 *
	 * @return True if the customer was successfully added, false otherwise.
	 */
	public boolean addCustomer();

	/**
	 * Deletes a customer.
	 *
	 * @return True if the customer was successfully deleted, false otherwise.
	 */
	public boolean deleteCustomer();

	/**
	 * Updates customer information.
	 *
	 * @param uid The unique identifier of the customer to be updated.
	 */
	public void updateCustomer(int uid);
}
