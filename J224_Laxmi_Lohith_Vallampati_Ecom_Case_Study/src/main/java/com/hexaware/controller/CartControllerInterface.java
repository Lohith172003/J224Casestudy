package com.hexaware.controller;

/**
 * CartControllerInterface defines the contract for cart-related operations in the controller layer.
 */
public interface CartControllerInterface {

    /**
     * Adds an item to the cart for the specified user.
     * 
     * @param userId The ID of the user.
     * @return True if the item is successfully added to the cart, false otherwise.
     */
    boolean addItemToCart(int userId);

    /**
     * Checks if the cart is empty for the specified user.
     * 
     * @param userId The ID of the user.
     * @return True if the cart is empty, false otherwise.
     */
    boolean isCartEmpty(int userId);

    /**
     * Displays the contents of the cart for the specified user.
     * 
     * @param userId The ID of the user.
     */
    void viewCart(int userId);

    /**
     * Clears the cart for the specified user.
     * 
     * @param userId The ID of the user.
     */
    void clearCart(int userId);

    /**
     * Removes an item from the cart for the specified user.
     * 
     * @param userId The ID of the user.
     */
    void removeItemFromCart(int userId);
}
