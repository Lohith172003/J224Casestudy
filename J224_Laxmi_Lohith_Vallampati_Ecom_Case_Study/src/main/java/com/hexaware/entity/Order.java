package com.hexaware.entity;
/**
 * Represents an order entity in the system.
 */
public class Order {
	private int orderId;
	private int customerId;
	private String orderDate;
	private float totalPrice;
	private String shippingAddress;
  /**
   * Constructs an Order object with the specified attributes.
   * @param orderId The ID of the order.
   * @param customerId The ID of the customer who placed the order.
   * @param orderDate The date when the order was placed.
   * @param totalPrice The total price of the order.
   * @param shippingAddress The shipping address for the order.
   */
	public Order(int orderId, int customerId, String orderDate, float totalPrice, String shippingAddress) {
		super();
		this.orderId = orderId;
		this.customerId = customerId;
		this.orderDate = orderDate;
		this.totalPrice = totalPrice;
		this.shippingAddress = shippingAddress;
	}
	public Order() {
		super();
	}
	public int getOrderId() {
		return orderId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public float getTotalPrice() {
		return totalPrice;
	}
	public String getShippingAddress() {
		return shippingAddress;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}
	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", customerId=" + customerId + ", orderDate=" + orderDate + ", totalPrice="
				+ totalPrice + ", shippingAddress=" + shippingAddress + "]";
	}
	
	

}
