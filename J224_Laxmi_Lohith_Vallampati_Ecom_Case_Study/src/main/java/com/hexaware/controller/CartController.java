package com.hexaware.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.hexaware.dao.CartDao;
import com.hexaware.entity.Cart;
import com.hexaware.exception.ProductNotFoundException;

public class CartController implements CartControllerInterface {

	private Cart cart;
	private CartDao cartDao;

	static Scanner sc = new Scanner(System.in);

	public CartController() {
		cart = new Cart();
		cartDao = new CartDao();
	}

	@Override
	public boolean addItemToCart(int userId) {
		boolean res = false;
		cart.setCustomerId(userId);
		System.out.print("Enter product Id: ");
		cart.setProductId(sc.nextInt());
		System.out.print("Enter quantity: ");
		cart.setQuantity(sc.nextInt());

		try {
			res =cartDao.addToCart(cart);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error while adding product: " + e.getMessage());
		}
		
		return res;
	}
	public void updateItemQuantity(int userId) {
		System.out.print("Enter product Id: ");
		int productId = sc.nextInt();
		System.out.print("Enter quantity you want to alter (+n/-n): ");
		int quantityChange = sc.nextInt();

		try {
			cartDao.updateCartItem(userId, productId, quantityChange);
		} catch (Exception e) {
			System.out.println("Error while updating the product " + e.getMessage());
		}

	}
	@Override
	public void removeItemFromCart(int userID) {
		System.out.print("Enter product Id: ");
		int productId = sc.nextInt();

		try {
			cartDao.removeFromCart(userID, productId);
		} catch (ProductNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void clearCart(int UserID) {
		cartDao.ClearAll(UserID);
	}
	@Override
	public void viewCart(int UserID) {
		Map<Integer, Integer> myCart = new HashMap<>();
		myCart = cartDao.getFromCartDb(UserID);
		if (myCart.isEmpty()) {
			System.out
					.println("Your cart is feeling lonely! Let's add some excitement and fill it up with amazing goodies!!\n");
		} else {
			System.out.println("-------------------------");
			System.out.printf("| %-10s | %-8s |\n", "Product ID", "Quantity");
			System.out.println("-------------------------");

			for (Map.Entry<Integer, Integer> entry : myCart.entrySet()) {

				System.out.printf("| %-10d | %-8d |\n", entry.getKey(), entry.getValue());
			}

			System.out.println("-------------------------");
		}

	}
	@Override
	public boolean isCartEmpty(int uid) {
		Map<Integer, Integer> myCart = new HashMap<>();
		myCart = cartDao.getFromCartDb(uid);
		return myCart.isEmpty();

	}

}
