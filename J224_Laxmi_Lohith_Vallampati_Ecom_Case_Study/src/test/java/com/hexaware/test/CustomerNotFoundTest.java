package com.hexaware.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.hexaware.dao.CustomerDao;
import com.hexaware.exception.CustomerNotFoundException;

public class CustomerNotFoundTest {
	CustomerDao cd;

	@Before
	public void setUp() {

		cd = new CustomerDao();
	}

	@Test(expected = CustomerNotFoundException.class)
	public void testAddToCart() throws CustomerNotFoundException {

		cd.deleteUser(88);
	}

	@After
	public void tearDown() {
		cd = null;
	}
}
