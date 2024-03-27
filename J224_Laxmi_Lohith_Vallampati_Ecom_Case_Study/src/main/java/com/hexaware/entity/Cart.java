package com.hexaware.entity;

/**
 * Represents a cart entity that stores information about products added by a
 * customer.
 */
public class Cart {

	private int cartId;
	private int customerId;
	private int productId;
	private int quantity;

	/**
	 * Constructs a new Cart with specified cartId and customerId.
	 * 
	 * @param cartId     the unique identifier for the cart
	 * @param customerId the unique identifier for the customer associated with the
	 *                   cart
	 */
	public Cart(int cartId, int customerId) {
		this.cartId = cartId;
		this.customerId = customerId;
	}

	/**
	 * Default constructor for Cart class.
	 */
	public Cart() {
		super();
	}

	/**
	 * Retrieves the cart ID.
	 * 
	 * @return the cart ID
	 */
	public int getCartId() {
		return cartId;
	}

	/**
	 * Retrieves the customer ID associated with the cart.
	 * 
	 * @return the customer ID
	 */
	public int getCustomerId() {
		return customerId;
	}

	/**
	 * Retrieves the product ID.
	 * 
	 * @return the product ID
	 */
	public int getProductId() {
		return productId;
	}

	/**
	 * Retrieves the quantity of the product.
	 * 
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Sets the product ID.
	 * 
	 * @param productId the product ID to be set
	 */
	public void setProductId(int productId) {
		this.productId = productId;
	}

	/**
	 * Sets the quantity of the product.
	 * 
	 * @param quantity the quantity to be set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * Sets the cart ID.
	 * 
	 * @param cartId the cart ID to be set
	 */
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	/**
	 * Sets the customer ID associated with the cart.
	 * 
	 * @param customerId the customer ID to be set
	 */
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	/**
	 * Returns a string representation of the Cart object.
	 * 
	 * @return a string representation of the Cart object
	 */
	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", customerId=" + customerId + ", productId=" + productId + ", quantity="
				+ quantity + "]";
	}

}
