use ecommerce;
-- drop table customers;
-- drop table products;
-- drop table cart;
-- drop table orders;
-- drop table order_items;

CREATE TABLE customers (
    customer_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    password VARCHAR(255)
);


CREATE TABLE admin (
    admin_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    password VARCHAR(255)
);

CREATE TABLE products (
    product_id INT PRIMARY KEY AUTO_INCREMENT,
    NAME VARCHAR(255),
    price DECIMAL(10, 2) NOT NULL,
    DESCRIPTION TEXT,
    stockQuantity INT
);

CREATE TABLE cart (
    cartId INT PRIMARY KEY auto_increment,
    customer_id INT,
    product_id INT,
    quantity INT,
    placed boolean DEFAULT 1, 
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id),
    FOREIGN KEY (product_id) REFERENCES products(product_id) 
);

-- Here I  included another column named 'placed' which will be updated when the order being  placed and if sufficient quantity not available
-- So, that we can restrict that to not be  insert oreder_items table and also while pricing the total.

CREATE TABLE orders (
    order_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT,
    order_date DATE DEFAULT (current_date()),
    total_price DECIMAL(30, 2),
    shipping_address VARCHAR(255),
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);



CREATE TABLE order_items (
    order_item_id INT PRIMARY KEY AUTO_INCREMENT,
    order_id INT,
    product_id INT,
    quantity INT,
    FOREIGN KEY (order_id) REFERENCES orders(order_id),
    FOREIGN KEY (product_id) REFERENCES products(product_id)
);

--  Trigger that calculate totla amount for order   
-- drop trigger calculate_order_total;
DELIMITER $$

CREATE TRIGGER calculate_order_total
BEFORE INSERT ON orders
FOR EACH ROW
BEGIN
	 DECLARE total DECIMAL(10, 2);     
     SELECT SUM( p.price * IF( c.quantity<=p.stockQuantity, c.quantity,0 ) )
     INTO total
     FROM cart c
     JOIN products p ON c.product_id = p.product_id
     WHERE c.customer_id = NEW.customer_id;

	-- set/ calculate the total_price column in the orders table
		SET NEW.total_price = total;    
     
      update cart c 
      join products p on c.product_id = p.product_id 
      set placed = 0 where c.quantity>p.stockQuantity;
     
END $$
DELIMITER ;

-- triger that inserts order items from cart to ordet_items table
DELIMITER $$
CREATE TRIGGER insert_to_order_items
AFTER INSERT ON orders
FOR EACH ROW
BEGIN

		INSERT INTO order_items (order_id, product_id, quantity)
		SELECT NEW.order_id, c.product_id, c.quantity
		FROM cart c
		WHERE c.customer_id = NEW.customer_id
        AND c.placed =1;
END $$
DELIMITER ;

-- triger that deletet the records from the cart

DELIMITER $$
CREATE TRIGGER delete_from_cart
AFTER INSERT ON orders
FOR EACH ROW
FOLLOWS insert_to_order_items
BEGIN 
		DELETE FROM cart WHERE customer_id = NEW.customer_id;
END $$
DELIMITER ;

-- tirger that updates the stockquantity in product table.
DELIMITER $$
CREATE TRIGGER update_stock
AFTER INSERT ON orders
FOR EACH ROW
FOLLOWS delete_from_cart
BEGIN
    -- Update stock quantity in the products table based on the products included in the order
    UPDATE products p
    JOIN order_items oi ON p.product_id = oi.product_id
    SET p.stockQuantity = p.stockQuantity - oi.quantity
    WHERE oi.order_id = NEW.order_id;
END $$
DELIMITER ;


-- ---------------------------------------------- CART MANAGEMENT

-- function to add products to cart
DELIMITER $$
CREATE FUNCTION add_to_cart(response varchar(50),cid int , pid int,qunatity_needed int)
RETURNS varchar(50)
DETERMINISTIC
BEGIN
		DECLARE available_quantity int;
			SELECT stockquantity into available_quantity FROM products WHERE product_id=pid;
            
        IF qunatity_needed > available_quantity THEN
			SET response = concat('Only ', available_quantity, ' units available');
		ELSE
			insert into cart (customer_id,product_id,quantity) values (cid,pid,qunatity_needed);
            
			SET response ='added to cart';
		END IF;
        RETURN response;
END $$
DELIMITER ;

-- select add_to_cart(@response,1,8,9999) as response;

-- function to update quantity of products in cart

DELIMITER $$
CREATE FUNCTION update_cart_quantity (response varchar(100), cid int, pid int, quantity_alterd int)
RETURNS varchar(100)
DETERMINISTIC
BEGIN
    DECLARE available_quantity int;
    DECLARE old_quantity int;
    DECLARE new_quantity int;
    
    SELECT stockquantity INTO available_quantity FROM products WHERE product_id = pid;
    SELECT quantity INTO old_quantity FROM cart WHERE product_id = pid AND customer_id = cid;
    
    SET new_quantity = old_quantity + quantity_alterd;
    
    IF new_quantity <= 0 THEN
        -- Delete the entry from the cart when new quantity becomes zero or negative
        DELETE FROM cart WHERE product_id = pid AND customer_id = cid;
        SET response = 'has been removed from cart';
    ELSEIF quantity_alterd < 0 THEN
        -- Update quantity in cart when the change is negative (i.e., reducing quantity)
        UPDATE cart SET quantity = new_quantity WHERE product_id = pid AND customer_id = cid;
        SET response = 'Quantity updated';
    ELSE
        -- Check if available quantity is sufficient for the requested change
        IF quantity_alterd <= available_quantity - old_quantity THEN
            -- Update quantity in cart when sufficient quantity is available
            UPDATE cart SET quantity = new_quantity WHERE product_id = pid AND customer_id = cid;
            SET response = 'Quantity updated';
        ELSE
            -- Insufficient quantity available
            SET response = CONCAT('Only ', available_quantity - old_quantity, ' units Left as you already have ',old_quantity,' in your cart.');
        END IF;
    END IF;
    RETURN response;
END $$
DELIMITER ;
-- ====================================================================================================================================================
-- record into customers

INSERT INTO customers (customer_id, name, email, password) VALUES
(1, 'Rohith','rohith@gmail.com', 'password123'),
(2, 'Lohith', 'lohith@gmail.com', 'password456'),
(3, 'varma', 'varma@gmail.com', 'password789'),
(4, 'sanjay', 'sanjay@gmail.com', 'passwordabc'),
(5, 'brundha ', 'brundha@gmail.com', 'passworddef'),
(6, 'divya', 'divya@gmail.com', 'passwordghi'),
(7, 'navya', 'navi@gmail.com', 'passwordjkl'),
(8, 'james', 'james@gmail.com', 'passwordmno'),
(9, 'revi', 'revi@gmail.com', 'passwordpqr'),
(10, 'rachin', 'rachin@gmail.com', 'passwordstu'),
(11, 'root', 'root@gmail.com', 'passwordvwx'),
(12, 'cutting', 'cutting@gmail.com', 'passwordyz');
-- =====================================================================================================================

-- record to products
INSERT INTO products (product_id, name, price, description, stockQuantity) VALUES
(1, 'Saree', 999.99, 'Traditional Indian attire', 50),
(2, 'Kurta', 599.99, 'Ethnic Indian wear', 30),
(3, 'Lehenga', 1499.99, 'Indian wedding attire', 20),
(4, 'Sherwani', 1999.99, 'Men''s wedding attire', 15),
(5, 'Salwar Kameez', 799.99, 'Traditional women''s attire', 25),
(6, 'Jewellery Set', 1299.99, 'Indian accessories', 40),
(7, 'Dhoti Kurta', 899.99, 'Men''s traditional wear', 35),
(8, 'Anarkali Suit', 999.99, 'Women''s traditional outfit', 30),
(9, 'FaceCrea,', 899.99, 'Ethnic wear for women', 20),
(10, 'Kurta Pyjama Set', 799.99, 'Men''s ethnic wear', 25),
(11, 'Dupatta', 299.99, 'Women''s traditional scarf', 60),
(12, 'ring', 399.99, 'Indian wedding rings', 50),
(13, 'Watch', 199.99, 'Titan gift pack', 70),
(14, 'Shirt', 99.99, 'US Polo Shirt', 80),
(15, 'loafers', 149.99, 'Loadfer for men', 90),
(16, 'joggers', 199.99, 'Joggers with Chenille Flower Patches', 100),
(17, 'hoodies', 49.99, 'WinterHoodies', 120),
(18, 'HeadPhones', 299.99, 'Boult', 25),
(19, 'EarPods', 499.99, 'One+nord3', 75),
(20, 'Laptop', 99.99, 'LenovoIdeapad', 50);
