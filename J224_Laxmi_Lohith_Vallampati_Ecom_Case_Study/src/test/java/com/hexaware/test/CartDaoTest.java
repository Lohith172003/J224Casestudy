package com.hexaware.test;

import static org.junit.Assert.assertTrue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.hexaware.dao.CartDao;
import com.hexaware.entity.Cart;
import com.hexaware.exception.ProductNotFoundException;

public class CartDaoTest {

	CartDao cd;
	Cart c;

	@Before
	public void setUp() {

		cd = new CartDao();
		c = new Cart();
	}

	@Test
	public void testAddToCart() {

		c.setCustomerId(4);
		c.setProductId(1);
		c.setQuantity(1);

		try {
			assertTrue(cd.addToCart(c));
		} catch (ProductNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@After
	public void tearDown() {
		cd = null;
		c=null;
	}

}
