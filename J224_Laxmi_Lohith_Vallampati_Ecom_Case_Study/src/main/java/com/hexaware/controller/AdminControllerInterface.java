
package com.hexaware.controller;

/**
 * AdminControllerInterface defines the contract for admin-related operations in
 * the controller layer.
 */
public interface AdminControllerInterface {

	/**
	 * Updates admin information.
	 *
	 * @param uid the unique identifier of the customer to be updated
	 */
	public void updateAdmin(int uid);
}
