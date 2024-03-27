package com.hexaware.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.hexaware.dao.CartDao;
import com.hexaware.entity.Cart;
import com.hexaware.exception.ProductNotFoundException;

public class ProductNotFoundTest {

	CartDao cd;
	Cart c;

	@Before
	public void setUp() {

		cd = new CartDao();
		c = new Cart();
	}

	@Test(expected = ProductNotFoundException.class)
	public void testAddToCart() throws ProductNotFoundException {

		c.setCustomerId(4);
		c.setProductId(121);
		c.setQuantity(1);

		cd.addToCart(c);
	}

	@After
	public void tearDown() {
		cd = null;
		c = null;
	}

}
