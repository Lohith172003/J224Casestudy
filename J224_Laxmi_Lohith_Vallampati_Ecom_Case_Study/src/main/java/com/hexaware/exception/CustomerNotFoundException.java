package com.hexaware.exception;

/**
 * Custom exception class to represent the scenario where a customer is not found.
 */
public class CustomerNotFoundException extends Exception {

    /**
     * Constructs a new CustomerNotFoundException with the specified detail message.
     * @param response the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public CustomerNotFoundException(String response) {
        super(response);
    }
}
