/*
package com.hexaware.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hexaware.entity.Product;
import com.hexaware.exception.ProductNotFoundException;
import com.hexaware.util.DBUtil;


public class ProductDao {

	Connection con;
	PreparedStatement ps;
	Statement stmt;
	ResultSet rs;

	
	public boolean addProduct(Product p) {
		boolean res = false;
		try {
			con = DBUtil.getDBConn();
			ps = con.prepareStatement("INSERT INTO Products (name, price, description,stockQuantity) VALUES (?, ?, ?, ?)");

			if (p.getName() == null || p.getDescription() == null) {
				System.out.println("One or more fields are null. Cannot insert product.");
				return false;
			}
			if (p.getPrice() == 0 || p.getStockQuantity() == 0) {
				System.out.println("Price of Stock is zero. Cannot insert product.");
				return false;
			}
			ps.setString(1, p.getName());
			ps.setFloat(2, p.getPrice());
			ps.setString(3, p.getDescription());
			ps.setInt(4, p.getStockQuantity());

			int noOfRowsInserted = ps.executeUpdate();

			if (noOfRowsInserted > 0) {
				System.out.println("Product inserted successfully.");
				res = true;

			} else {
				System.out.println("Failed to insert product.");
			}
		} catch (SQLException e) {
			System.out.println("Error while adding product: " + e.getMessage());
		}
		return res;
	}

	
	public boolean deleteProduct(int pid) throws ProductNotFoundException {
		boolean res = false;
		try {
			con = DBUtil.getDBConn();
			ps = con.prepareStatement("DELETE FROM Products Where product_id = ?");
			ps.setInt(1, pid);

			int noOfRowsDeleted = ps.executeUpdate();
			if (noOfRowsDeleted > 0) {
				System.out.println("Product Deleted successfully.");
				res = true;

			} else {

				throw new ProductNotFoundException("There is no product with id : " + pid);
			}

		} catch (SQLException e) {
			System.out.println("Error while deleting product: " + e.getMessage());
		} finally {
			closeResources();
		}
		return res;
	}

	
	public void updateProduct(int pid, float newPrice, int additionalQuantity) throws ProductNotFoundException {
		try {
			con = DBUtil.getDBConn();
			StringBuilder query = new StringBuilder("UPDATE Products SET ");
			List<Object> updates = new ArrayList<>();

			boolean modified = false;

			// Update price if new price is valid
			if (newPrice > 0) {
				query.append("price = ?, ");
				updates.add(newPrice);
				modified = true;
			}

			// Update quantity if it's different
			if (additionalQuantity != 0) {
				query.append("stockQuantity = stockQuantity + ?, ");
				updates.add(additionalQuantity);
				modified = true;
			}

			// Remove the trailing comma and space
			query.setLength(query.length() - 2);

			if (modified) {
				query.append(" WHERE product_id = ?");
				PreparedStatement ps = con.prepareStatement(query.toString());

				// Set parameter values
				int paramIndex = 1;
				for (Object attribute : updates) {
					ps.setObject(paramIndex, attribute);
					paramIndex++;
				}
				ps.setInt(paramIndex, pid);

				int noOfRowsUpdated = ps.executeUpdate();
				if (noOfRowsUpdated > 0)
					System.out.println("\nproduct details updated successfully.");
				else
					throw new ProductNotFoundException("There is no product with id : " + pid);
			}
		} catch (SQLException e) {
			System.out.println("Error while updating product: " + e.getMessage());
		} finally {
			closeResources();
		}
	}

	public void getProducts() {
		con = DBUtil.getDBConn();

		try {
			ps = con.prepareStatement("select * from products ");
			rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getInt(1) + " : " + rs.getString(2) + " : " + rs.getFloat(3) + " : " + rs.getString(4)
						+ " : " + rs.getInt(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeResources();
		}
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
import java.util.ArrayList;
import java.util.List;

import com.hexaware.entity.Product;
import com.hexaware.exception.ProductNotFoundException;
import com.hexaware.util.DBUtil;

/**
 * ProductDao handles database operations related to products.
 */
public class ProductDao {

	Connection con;
	PreparedStatement ps;
	Statement stmt;
	ResultSet rs;

	/**
	 * Adds a new product to the database.
	 *
	 * @param p the Product object containing product details
	 * @return true if the product is successfully added, false otherwise
	 */
	public boolean addProduct(Product p) {
		boolean res = false;
		try {
			con = DBUtil.getDBConn();
			ps = con.prepareStatement("INSERT INTO Products (name, price, description,stockQuantity) VALUES (?, ?, ?, ?)");

			if (p.getName() == null || p.getDescription() == null) {
				System.out.println("One or more fields are null. Cannot insert product.");
				return false;
			}
			if (p.getPrice() == 0 || p.getStockQuantity() == 0) {
				System.out.println("Price of Stock is zero. Cannot insert product.");
				return false;
			}
			ps.setString(1, p.getName());
			ps.setFloat(2, p.getPrice());
			ps.setString(3, p.getDescription());
			ps.setInt(4, p.getStockQuantity());

			int noOfRowsInserted = ps.executeUpdate();

			if (noOfRowsInserted > 0) {
				System.out.println("Product inserted successfully.");
				res = true;

			} else {
				System.out.println("Failed to insert product.");
			}
		} catch (SQLException e) {
			System.out.println("Error while adding product: " + e.getMessage());
		}
		return res;
	}

	/**
	 * Deletes a product from the database.
	 *
	 * @param pid the ID of the product to be deleted
	 * @return true if the product is successfully deleted, false otherwise
	 * @throws ProductNotFoundException if the product with the given ID is not
	 *                                  found
	 */
	public boolean deleteProduct(int pid) throws ProductNotFoundException {
		boolean res = false;
		try {
			con = DBUtil.getDBConn();
			ps = con.prepareStatement("DELETE FROM Products Where product_id = ?");
			ps.setInt(1, pid);

			int noOfRowsDeleted = ps.executeUpdate();
			if (noOfRowsDeleted > 0) {
				System.out.println("Product Deleted successfully.");
				res = true;

			} else {

				throw new ProductNotFoundException("There is no product with id : " + pid);
			}

		} catch (SQLException e) {
			System.out.println("Error while deleting product: " + e.getMessage());
		} finally {
			closeResources();
		}
		return res;
	}

	/**
	 * Updates product information in the database.
	 *
	 * @param pid                the ID of the product to be updated
	 * @param newPrice           the new price of the product (0 if not updating)
	 * @param additionalQuantity the additional quantity of the product (0 if not
	 *                           updating)
	 * @throws ProductNotFoundException if the product with the given ID is not
	 *                                  found
	 */
	public void updateProduct(int pid, float newPrice, int additionalQuantity) throws ProductNotFoundException {
		try {
			con = DBUtil.getDBConn();
			StringBuilder query = new StringBuilder("UPDATE Products SET ");
			List<Object> updates = new ArrayList<>();

			boolean modified = false;

			// Update price if new price is valid
			if (newPrice > 0) {
				query.append("price = ?, ");
				updates.add(newPrice);
				modified = true;
			}

			// Update quantity if it's different
			if (additionalQuantity != 0) {
				query.append("stockQuantity = stockQuantity + ?, ");
				updates.add(additionalQuantity);
				modified = true;
			}

			// Remove the trailing comma and space
			query.setLength(query.length() - 2);

			if (modified) {
				query.append(" WHERE product_id = ?");
				PreparedStatement ps = con.prepareStatement(query.toString());

				// Set parameter values
				int paramIndex = 1;
				for (Object attribute : updates) {
					ps.setObject(paramIndex, attribute);
					paramIndex++;
				}
				ps.setInt(paramIndex, pid);

				int noOfRowsUpdated = ps.executeUpdate();
				if (noOfRowsUpdated > 0)
					System.out.println("\nproduct details updated successfully.");
				else
					throw new ProductNotFoundException("There is no product with id : " + pid);
			}
		} catch (SQLException e) {
			System.out.println("Error while updating product: " + e.getMessage());
		} finally {
			closeResources();
		}
	}

	/**
	 * Retrieves all products from the database.
	 */
	public void getProducts() {
		con = DBUtil.getDBConn();

		try {
			ps = con.prepareStatement("select * from products ");
			rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getInt(1) + " : " + rs.getString(2) + " : " + rs.getFloat(3) + " : " + rs.getString(4)
						+ " : " + rs.getInt(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
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
