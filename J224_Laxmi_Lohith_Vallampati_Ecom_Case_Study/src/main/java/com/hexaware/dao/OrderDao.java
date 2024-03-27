/*
package com.hexaware.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.hexaware.exception.OrderNotFoundException;
import com.hexaware.util.*;

public class OrderDao {

	Connection con;
	PreparedStatement ps;
	Statement stmt;
	ResultSet rs;

	public boolean placeOrder(int cid, String address) {

		boolean res = false;
		con = DBUtil.getDBConn();
		try {
			String query = "insert into orders(customer_id,shipping_address)values(?,?)";
			ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, cid);
			ps.setString(2, address);

			int noOfRows = ps.executeUpdate();
			if (noOfRows > 0) {
				rs = ps.getGeneratedKeys();
				if (rs.next()) {
					int oid = rs.getInt(1);

					PreparedStatement tps = con.prepareStatement("SELECT total_price from orders where order_id=?");
					tps.setInt(1, oid);

					ResultSet trs = tps.executeQuery();
					if (trs.next()) {
						System.out.println("----------------------------");
						System.out.println("Order placed succesfully...");
						System.out.println("your order id : " + oid);
						System.out.println("Total amount  : " + trs.getFloat(1));
						System.out.println("----------------------------");
						
						res = true;
					}
					if (tps != null)
						tps.close();
					if (trs != null)
						tps.close();

				}

			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			closeResources();
		}
		return res;

	}

	public void customerOrders(int cid) {
		try {
			con = DBUtil.getDBConn();
			ps = con.prepareStatement("Select * from orders where customer_id=?");
			ps.setInt(1, cid);

			rs = ps.executeQuery();

			if (!rs.isBeforeFirst()) {
				// No orders found for the customer
				System.out.println("You haven't ordered anything yet.");
				System.out.println("Place your order now!");
			} else {
				// Orders found for the customer
				while (rs.next()) {
					System.out.println("Order ID    : " + rs.getInt(1));
					System.out.println("Order Date  : " + rs.getString(3));
					System.out.println("Address     : " + rs.getString(5));
					System.out.println("Total Price : " + rs.getFloat(4));
					System.out.println("------------------------------\n");
				}
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeResources();

		}

	}

	public Map<Integer, Integer> specificOrder(int oid) throws OrderNotFoundException {

		Map<Integer, Integer> order = new HashMap<>();

		con = DBUtil.getDBConn();
		try {
			ps = con.prepareStatement("select product_id,quantity from order_items WHERE order_id =?",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setInt(1, oid);

			rs = ps.executeQuery();

			if (!rs.next()) {
				throw new OrderNotFoundException("courier wiht id " + oid + " not found");
			}
			rs.beforeFirst();
			while (rs.next()) {

				int pid = rs.getInt(1);
				int quantity = rs.getInt(2);

				order.put(pid, quantity);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return order;

	}

	private void closeResources() {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
*/

package com.hexaware.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.hexaware.exception.OrderNotFoundException;
import com.hexaware.util.*;

/**
 * OrderDao handles database operations related to orders.
 */
public class OrderDao {

	Connection con;
	PreparedStatement ps;
	Statement stmt;
	ResultSet rs;

	/**
	 * Places an order for a customer.
	 *
	 * @param cid     the ID of the customer placing the order
	 * @param address the shipping address for the order
	 * @return true if the order is successfully placed, false otherwise
	 */
	public boolean placeOrder(int cid, String address) {

		boolean res = false;
		con = DBUtil.getDBConn();
		try {
			String query = "insert into orders(customer_id,shipping_address)values(?,?)";
			ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, cid);
			ps.setString(2, address);

			int noOfRows = ps.executeUpdate();
			if (noOfRows > 0) {
				rs = ps.getGeneratedKeys();
				if (rs.next()) {
					int oid = rs.getInt(1);

					PreparedStatement tps = con.prepareStatement("SELECT total_price from orders where order_id=?");
					tps.setInt(1, oid);

					ResultSet trs = tps.executeQuery();
					if (trs.next()) {
						System.out.println("----------------------------");
						System.out.println("Order placed succesfully...");
						System.out.println("your order id : " + oid);
						System.out.println("Total amount  : " + trs.getFloat(1));
						System.out.println("----------------------------");

						res = true;
					}
					if (tps != null)
						tps.close();
					if (trs != null)
						tps.close();

				}

			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			closeResources();
		}
		return res;

	}

	/**
	 * Retrieves orders for a specific customer.
	 *
	 * @param cid the ID of the customer
	 */
	public void customerOrders(int cid) {
		try {
			con = DBUtil.getDBConn();
			ps = con.prepareStatement("Select * from orders where customer_id=?");
			ps.setInt(1, cid);

			rs = ps.executeQuery();

			if (!rs.isBeforeFirst()) {
				// No orders found for the customer
				System.out.println("You haven't ordered anything yet.");
				System.out.println("Place your order now!");
			} else {
				// Orders found for the customer
				while (rs.next()) {
					System.out.println("Order ID    : " + rs.getInt(1));
					System.out.println("Order Date  : " + rs.getString(3));
					System.out.println("Address     : " + rs.getString(5));
					System.out.println("Total Price : " + rs.getFloat(4));
					System.out.println("------------------------------\n");
				}
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();

		}

	}

	/**
	 * Retrieves the products and quantities associated with a specific order.
	 *
	 * @param oid the ID of the order
	 * @return a map containing product IDs as keys and quantities as values
	 * @throws OrderNotFoundException if the order is not found
	 */
	public Map<Integer, Integer> specificOrder(int oid) throws OrderNotFoundException {

		Map<Integer, Integer> order = new HashMap<>();

		con = DBUtil.getDBConn();
		try {
			ps = con.prepareStatement("select product_id,quantity from order_items WHERE order_id =?",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setInt(1, oid);

			rs = ps.executeQuery();

			if (!rs.next()) {
				throw new OrderNotFoundException("courier wiht id " + oid + " not found");
			}
			rs.beforeFirst();
			while (rs.next()) {

				int pid = rs.getInt(1);
				int quantity = rs.getInt(2);

				order.put(pid, quantity);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return order;

	}

	private void closeResources() {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
