package com.hexaware.controller;

/**
 * The ProductControllerInterface interface provides methods to manage products
 * in the e-commerce platform.
 */
public interface ProductControllerInterface {

	/**
	 * Adds a new product to the inventory.
	 * 
	 * @return True if the product is successfully added, false otherwise.
	 */
	public boolean addProduct();

	/**
	 * Deletes a product from the inventory.
	 * 
	 * @return True if the product is successfully deleted, false otherwise.
	 */
	public boolean deleteProduct();

	/**
	 * Updates an existing product in the inventory.
	 */
	public void updateProduct();

	/**
	 * Displays the products in the inventory.
	 */
	public void viewProduct();
}
