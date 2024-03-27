/*package com.hexaware.dao;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

import com.hexaware.entity.Cart;
import com.hexaware.exception.ProductNotFoundException;
import com.hexaware.util.DBUtil;

public class CartDao {

	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	public boolean addToCart(Cart cartItem) throws ProductNotFoundException {
		boolean res = false;
		con = DBUtil.getDBConn();
		String response = null;
		try {

			int pid = cartItem.getProductId();
			CallableStatement cstmt = con.prepareCall("{?=call add_to_cart(?,?,?,?)}");
			cstmt.registerOutParameter(1, java.sql.Types.VARCHAR);
			cstmt.setInt(2, 0);
			cstmt.setInt(3, cartItem.getCustomerId());
			cstmt.setInt(4, pid);
			cstmt.setInt(5, cartItem.getQuantity());

			cstmt.execute();
			response = cstmt.getString(1);

			if (response.startsWith("Only")) {
				System.out.println("For Product id: " + pid + " " + response + ". so, it couldn't be added to Cart.");
			} else {
				System.out.println("\nSuccessfully added to cart");
				res = true;
			}

		} catch (SQLException e) {
			if (e instanceof SQLIntegrityConstraintViolationException) {
				throw new ProductNotFoundException("Product with ID " + cartItem.getProductId() + " not found");
			} else {
				System.out.println("Error while updating product: " + e.getMessage());
			}
		} finally {
			closeResources();
		}
		
		return res;
	}

	public void updateCartItem(int uid, int pid, int qChange) throws ProductNotFoundException {
		con = DBUtil.getDBConn();
		String response = null;
		try {
			CallableStatement cstmt = con.prepareCall("{?= call update_cart_quantity (?,?,?,?)}");
			cstmt.registerOutParameter(1, java.sql.Types.VARCHAR);
			cstmt.setInt(2, 0);
			cstmt.setInt(3, uid);
			cstmt.setInt(4, pid);
			cstmt.setInt(5, qChange);

			cstmt.execute();

			response = cstmt.getString(1);

			if (response.startsWith("Only ")) {
				System.out.println("\nFor Product id : " + pid + " " + response);
			} else if (response.startsWith("has")) {
				System.out.println("\nProduct with Id : " + pid + " " + response);
			} else if (response.startsWith("Quantity")) {
				System.out.println("\nQuantity Updated for product id : " + pid);
			} else {
				
				
				throw new ProductNotFoundException("product id not found\n");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void removeFromCart(int uid, int pid) throws ProductNotFoundException {
		con = DBUtil.getDBConn();

		try {
			ps = con.prepareStatement("DELETE from cart WHERE customer_id = ? and product_id = ?");
			ps.setInt(1, uid);
			ps.setInt(2, pid);
			int removed = ps.executeUpdate();
			if (removed > 0) {
				System.out.println("\nProduct with id : " + pid + " is removed from your cart.");
			} else {
				
				throw new ProductNotFoundException("Product with Id : " + pid + " is not in your cart.");
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public void ClearAll(int uid) {
		con = DBUtil.getDBConn();
		try {
			ps = con.prepareStatement("DELETE from cart WHERE customer_id =?");
			ps.setInt(1, uid);
			int noOfRows = ps.executeUpdate();
			if (noOfRows > 0) {
				System.out.println("Your shopping cart has been refreshed and is now ready for new goodies!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Map<Integer, Integer> getFromCartDb(int customerId) {
		Map<Integer, Integer> yourCart = new HashMap<>();
		con = DBUtil.getDBConn();
		try {
			String query = "SELECT product_id, quantity FROM cart WHERE customer_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, customerId);
			rs = ps.executeQuery();
			while (rs.next()) {
				int productId = rs.getInt("product_id");
				int quantity = rs.getInt("quantity");
				yourCart.put(productId, quantity);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return yourCart;
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

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

import com.hexaware.entity.Cart;
import com.hexaware.exception.ProductNotFoundException;
import com.hexaware.util.DBUtil;

/**
 * The CartDao class provides methods to perform database operations related to
 * cart entities.
 */
public class CartDao {

	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	/**
	 * Adds an item to the cart.
	 *
	 * @param cartItem the Cart object containing item details
	 * @return true if the item is successfully added to the cart, false otherwise
	 * @throws ProductNotFoundException if the product is not found
	 */
	public boolean addToCart(Cart cartItem) throws ProductNotFoundException {
		boolean res = false;
		con = DBUtil.getDBConn();
		String response = null;
		try {
			int pid = cartItem.getProductId();
			CallableStatement cstmt = con.prepareCall("{?=call add_to_cart(?,?,?,?)}");
			cstmt.registerOutParameter(1, java.sql.Types.VARCHAR);
			cstmt.setInt(2, 0);
			cstmt.setInt(3, cartItem.getCustomerId());
			cstmt.setInt(4, pid);
			cstmt.setInt(5, cartItem.getQuantity());

			cstmt.execute();
			response = cstmt.getString(1);

			if (response.startsWith("Only")) {
				System.out.println("For Product id: " + pid + " " + response + ". so, it couldn't be added to Cart.");
			} else {
				System.out.println("\nSuccessfully added to cart");
				res = true;
			}

		} catch (SQLException e) {
			if (e instanceof SQLIntegrityConstraintViolationException) {
				throw new ProductNotFoundException("Product with ID " + cartItem.getProductId() + " not found");
			} else {
				System.out.println("Error while updating product: " + e.getMessage());
			}
		} finally {
			closeResources();
		}

		return res;
	}

	/**
	 * Updates the quantity of a cart item.
	 *
	 * @param uid     the user ID
	 * @param pid     the product ID
	 * @param qChange the quantity change (can be positive or negative)
	 * @throws ProductNotFoundException if the product is not found
	 */
	public void updateCartItem(int uid, int pid, int qChange) throws ProductNotFoundException {
		con = DBUtil.getDBConn();
		String response = null;
		try {
			CallableStatement cstmt = con.prepareCall("{?= call update_cart_quantity (?,?,?,?)}");
			cstmt.registerOutParameter(1, java.sql.Types.VARCHAR);
			cstmt.setInt(2, 0);
			cstmt.setInt(3, uid);
			cstmt.setInt(4, pid);
			cstmt.setInt(5, qChange);

			cstmt.execute();

			response = cstmt.getString(1);

			if (response.startsWith("Only ")) {
				System.out.println("\nFor Product id : " + pid + " " + response);
			} else if (response.startsWith("has")) {
				System.out.println("\nProduct with Id : " + pid + " " + response);
			} else if (response.startsWith("Quantity")) {
				System.out.println("\nQuantity Updated for product id : " + pid);
			} else {
				throw new ProductNotFoundException("product id not found\n");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Removes an item from the cart.
	 *
	 * @param uid the user ID
	 * @param pid the product ID to be removed from the cart
	 * @throws ProductNotFoundException if the product is not found in the cart
	 */
	public void removeFromCart(int uid, int pid) throws ProductNotFoundException {
		con = DBUtil.getDBConn();

		try {
			ps = con.prepareStatement("DELETE from cart WHERE customer_id = ? and product_id = ?");
			ps.setInt(1, uid);
			ps.setInt(2, pid);
			int removed = ps.executeUpdate();
			if (removed > 0) {
				System.out.println("\nProduct with id : " + pid + " is removed from your cart.");
			} else {
				throw new ProductNotFoundException("Product with Id : " + pid + " is not in your cart.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Clears the entire cart for a given user.
	 *
	 * @param uid the user ID whose cart is to be cleared
	 */
	public void ClearAll(int uid) {
		con = DBUtil.getDBConn();
		try {
			ps = con.prepareStatement("DELETE from cart WHERE customer_id =?");
			ps.setInt(1, uid);
			int noOfRows = ps.executeUpdate();
			if (noOfRows > 0) {
				System.out.println("Your shopping cart has been refreshed and is now ready for new goodies!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Retrieves the items in the cart for a given user.
	 *
	 * @param customerId the user ID
	 * @return a map containing the product IDs and their corresponding quantities
	 *         in the cart
	 */
	public Map<Integer, Integer> getFromCartDb(int customerId) {
		Map<Integer, Integer> yourCart = new HashMap<>();
		con = DBUtil.getDBConn();
		try {
			String query = "SELECT product_id, quantity FROM cart WHERE customer_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, customerId);
			rs = ps.executeQuery();
			while (rs.next()) {
				int productId = rs.getInt("product_id");
				int quantity = rs.getInt("quantity");
				yourCart.put(productId, quantity);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return yourCart;
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
