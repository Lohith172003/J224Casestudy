package com.hexaware.entity;
/**
 * Represents a product with an ID, name, price, description, and stock quantity.
 * @version 1
 */
public class Product {
	
	private int productId;
	private String name;
	private float price;
	private String description;
	private int stockQuantity;
	
	public Product(int productId, String name, float price, String description, int stockQuantity) {
		super();
		this.productId = productId;
		this.name = name;
		this.price = price;
		this.description = description;
		this.stockQuantity = stockQuantity;
	}
	
	public Product()
	{
		super();
	}

	public int getProductId() {
		return productId;
	}

	public String getName() {
		return name;
	}

	public float getPrice() {
		return price;
	}

	public String getDescription() {
		return description;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", name=" + name + ", price=" + price + ", description=" + description
				+ ", stockQuantity=" + stockQuantity + "]";
	}
	
	
	
	
	
	
	
	
	

}
