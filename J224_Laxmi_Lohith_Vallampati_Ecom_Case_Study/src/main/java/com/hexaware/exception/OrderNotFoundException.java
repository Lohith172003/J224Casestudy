package com.hexaware.exception;

/**
 * Custom exception class to represent the scenario where an order is not found.
 */
public class OrderNotFoundException extends Exception {
    
    /**
     * Constructs a new OrderNotFoundException with the specified detail message.
     * @param response the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public OrderNotFoundException(String response) {
        super(response);
    }

}
