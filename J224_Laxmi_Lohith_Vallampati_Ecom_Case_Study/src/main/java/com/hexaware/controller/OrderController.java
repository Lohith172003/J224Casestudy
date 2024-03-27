package com.hexaware.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import com.hexaware.dao.OrderDao;
import com.hexaware.exception.OrderNotFoundException;

public class OrderController implements OrderControllerInterface {

	OrderDao od = new OrderDao();
	static Scanner sc = new Scanner(System.in);

	@Override
	public boolean placeOrder(int cid) {

		System.out.println("Enter the shipping address : ");
		String address = sc.next();

		return od.placeOrder(cid, address);

	}

	@Override
	public void getOrders(int cid) {

		od.customerOrders(cid);
	}

	@Override
	public void getOrder()  {

//		List<OrderItem> order = new ArrayList<>();
		Map<Integer, Integer> order = new HashMap<>();
		System.out.print("enter the Order Id : ");
		int oid = sc.nextInt();
		try {
			order = od.specificOrder(oid);
			System.out.println("-------------------------");
			System.out.println("OREDER ID : " + oid);
			System.out.println("-------------------------");
			System.out.printf("| %-10s | %-8s |\n", "Product ID", "Quantity");
			System.out.println("-------------------------");

			for (Map.Entry<Integer, Integer> entry : order.entrySet()) {
				System.out.printf("| %-10d | %-8d |\n", entry.getKey(), entry.getValue());
			}
			System.out.println("-------------------------");
		} catch (OrderNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
