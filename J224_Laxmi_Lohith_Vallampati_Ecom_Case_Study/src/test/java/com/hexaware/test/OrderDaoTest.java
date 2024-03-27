package com.hexaware.test;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.hexaware.dao.OrderDao;

public class OrderDaoTest {

	OrderDao od;

	@Before
	public void setUp() {

		od = new OrderDao();

	}

	@Test
	public void testAddToCart() {

		assertTrue(od.placeOrder(4, "kphb"));
	}

	@After
	public void tearDown() {
		od = null;
	}

}
