package com.hexaware.test;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.hexaware.dao.ProductDao;
import com.hexaware.entity.Product;

public class ProductDaoTest {

	ProductDao pdao;
	Product product;

	@Before
	public void setUp() {
		pdao = new ProductDao();
		product = new Product();

	}

	@Test
	public void testAddProduct() {

		product.setName("toy car");

		product.setPrice(200);

		product.setDescription("plasitc toy");

		product.setStockQuantity(200);

		assertTrue(pdao.addProduct(product));
	}

	@After
	public void tearDown() {
		pdao = null;
	}
}
