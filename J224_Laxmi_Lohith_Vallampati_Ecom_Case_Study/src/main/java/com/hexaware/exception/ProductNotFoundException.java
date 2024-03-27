package com.hexaware.exception;

/**
 * Custom exception class to represent the scenario where a product is not found.
 */
public class ProductNotFoundException extends Exception {
    
    /**
     * Constructs a new ProductNotFoundException with the specified detail message.
     * @param res the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public ProductNotFoundException(String res) {
        super(res);
    }

}
