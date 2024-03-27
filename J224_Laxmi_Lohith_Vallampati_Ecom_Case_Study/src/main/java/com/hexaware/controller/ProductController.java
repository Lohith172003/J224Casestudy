package com.hexaware.controller;

import java.util.Scanner;
import com.hexaware.dao.ProductDao;
import com.hexaware.entity.Product;

/**
 * Controller class for managing products.
 */
public class ProductController implements ProductControllerInterface {
	private ProductDao productDao = new ProductDao();
	static Scanner sc = new Scanner(System.in);

	@Override
	public boolean addProduct() {
		Product product = new Product();

		System.out.println("Enter the product name: ");
		product.setName(sc.nextLine());
		System.out.println("Enter the product description: ");
		product.setDescription(sc.nextLine());
		System.out.println("Enter the price: ");
		product.setPrice(sc.nextFloat());
		System.out.println("Enter the stock quantity: ");
		product.setStockQuantity(sc.nextInt());

		return productDao.addProduct(product);
	}

	@Override
	public boolean deleteProduct() {

		System.out.println("Enter the product ID to delete: ");
		int productId = sc.nextInt();
		try {
			return productDao.deleteProduct(productId);
		} catch (Exception e) {
			System.out.println("Error while deleting product: " + e.getMessage());
		}

		return false;

	}

	@Override
	public void updateProduct() {
		float newPrice = 0;
		int addQuantity = 0;
		int productId;
		try {
			System.out.print("enter the product Id : ");
			productId = sc.nextInt();
			System.out.print("Would you like to update the price? (y/n): ");
			char priceChoice = sc.next().charAt(0);
			if (Character.toLowerCase(priceChoice) == 'y') {
				while (true) {
					System.out.print("Enter the new price: ");
					newPrice = sc.nextFloat();
					if (newPrice > 0)
						break;
					else
						System.out.println("Price should be greater than zero.");
				}

			}

			System.out.print("Would you like to add quantity? (y/n): ");
			char quantityChoice = sc.next().charAt(0);
			if (Character.toLowerCase(quantityChoice) == 'y') {
				System.out.print("Enter the quantity to add: ");
				addQuantity = sc.nextInt();
				productDao.updateProduct(productId, newPrice, addQuantity);
			}
		} catch (Exception e) {
			System.out.println("Error while creating user: " + e.getMessage());
		}

	}

	@Override
	public void viewProduct() {
		productDao.getProducts();
	}
}
