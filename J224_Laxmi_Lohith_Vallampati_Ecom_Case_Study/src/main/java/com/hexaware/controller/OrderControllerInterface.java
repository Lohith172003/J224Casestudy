package com.hexaware.controller;

/**
 * OrderControllerInterface defines the contract for order-related operations in
 * the controller layer.
 */
public interface OrderControllerInterface {

	/**
	 * Places an order for the specified customer.
	 * 
	 * @param cid The ID of the customer.
	 * @return True if the order is successfully placed, false otherwise.
	 */
	boolean placeOrder(int cid);

	/**
	 * Retrieves the orders for the specified customer.
	 * 
	 * @param cid The ID of the customer.
	 */
	void getOrders(int cid);

	/**
	 * Retrieves a specific order.
	 */
	void getOrder();
}
