package com.hexaware.main;

import com.hexaware.controller.*;
import com.hexaware.exception.CustomerNotFoundException;
import com.hexaware.exception.OrderNotFoundException;
import com.hexaware.exception.ProductNotFoundException;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * The E-commerce class represents the main entry point for the e-commerce
 * platform. It provides options for user registration, user login, and admin
 * login.
 */
public class Ecommerce {

	/**
	 * Main method to start the e-commerce application.
	 */
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws SQLException, ProductNotFoundException, CustomerNotFoundException, OrderNotFoundException {

		UserControllerInterface userController = new UserController();
		AdminControllerInterface adminController = new AdminController();
		ProductControllerInterface productController = new ProductController();
		OrderControllerInterface orderController = new OrderController();
		CartController cartController = new CartController();
		UserLogin userLogin = new UserLogin();
		AdminLogin adminLogin = new AdminLogin();

		int loginOption;

		System.out.println("Welcome to our exciting e-commerce platform! Are you ready to explore?");
		System.out.println("1. Register as a New User");
		System.out.println("2. User Login");
		System.out.println("3. Admin Login");

		loginOption = sc.nextInt();

		switch (loginOption) {
		case 1:
			System.out.println("New registration!!");
			userController.addCustomer();
			break;
		case 2:
			System.out.println("User login:");
			System.out.print("Enter your email: ");
			String email = sc.next();
			System.out.print("Enter your password: ");
			String password = sc.next();
			int userId = userLogin.authenticateUser(email, password);
			if (userId != 0) {

				int uchoice;
				
				boolean exit = false;
				while (true) {
					System.out.println("\n1. Update Details");
					System.out.println("2. Products");
					System.out.println("3. Cart");
					System.out.println("4. Place Order");
					System.out.println("5. YourOrders");
					System.out.println("6. Specific Order");
					System.out.println("7. LogOut");

					System.out.print("Enter your Choice : ");
					uchoice = sc.nextInt();
					switch (uchoice) {
					case 1:
						userController.updateCustomer(userId);
						break;
					case 2:
						productController.viewProduct();
						break;
					case 3:
						boolean main_page = false;
						while (true) {
							System.out.println("1. Your Cart");
							System.out.println("2. Add To Cart");
							System.out.println("3. Remove From Cart");
							System.out.println("4. Empty the Cart");
							System.out.println("5. Modify Quantity In Cart");
							System.out.println("6. Back to Main page");
							System.out.print("Enter your choice : ");
							int cart_choice = sc.nextInt();

							switch (cart_choice) {
							case 1:
								cartController.viewCart(userId);
								break;
							case 2:
								cartController.addItemToCart(userId);
								break;
							case 3:
								cartController.removeItemFromCart(userId);
								break;
							case 4:
								cartController.clearCart(userId);
								break;
							case 5:
								cartController.updateItemQuantity(userId);
								break;
							case 6:
								main_page = true;
								break;
							default:
								System.out.println("Wrong Choice");
							}
							if (main_page)
								break;
						}
						break;
					case 4:
						if (cartController.isCartEmpty(userId))
							System.out.println("\nYour cart is empty. Please add items to your cart before placing an order.");
						else {
							orderController.placeOrder(userId);
						}

						break;
					case 5:
						orderController.getOrders(userId);
						break;
					case 6:
						orderController.getOrder();
						break;
					case 7:
						exit = true;
						break;
					default:
						System.out.println("wrong choice !!");
					}

					if (exit)
						break;
				}

			}
			break;
		case 3:

			System.out.println("Admin login:");
			System.out.print("Enter your email: ");
			String aEmail = sc.next();
			System.out.print("Enter your password: ");
			String aPassword = sc.next();
			int adminId = adminLogin.authenticateAdmin(aEmail, aPassword);
			if (adminId != 0) {
				boolean exit = false;
				while (true) {
					System.out.println("Admin functionalities...");
					System.out.println("1. Update admin");
					System.out.println("2. Add Product");
					System.out.println("3. Delete Product");
					System.out.println("4. Update Product");
					System.out.println("5. Delete Customer");
					System.out.println("6. Orders By Customer");
					System.out.println("7. products avialable");
					System.out.println("8. Log out");
					System.out.println("enter your choice : ");
					int achoice = sc.nextInt();

					switch (achoice) {

					case 1:
						adminController.updateAdmin(adminId);
						break;
					case 2:
						System.out.println(productController.addProduct());
						break;
					case 3:
						System.out.println(productController.deleteProduct());
						break;
					case 4:
						productController.updateProduct();
						break;
					case 5:
						userController.deleteCustomer();
						break;
					case 6:
						System.out.println("enter the userId : ");
						orderController.getOrders(sc.nextInt());
						break;
					case 7:
						productController.viewProduct();
						break;
					case 8:
						exit = true;
						break;
					default:
						System.out.println("wrong choice !!");
					}

					if (exit)
						break;
				}
			}
			break;
		default:
			System.out.println("Invalid option selected. Please try again.");
		}
		System.out.println("THANK YOU!!");
		sc.close();
	}
}
