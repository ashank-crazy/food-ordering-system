package main;

import Exceptions.InvalidLoginException;
import admin.Admin;
import customer.Customer;
import models.FoodItem;
import models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.MenuManager;
import utils.OrderManager;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ByteMeTest {

    private Customer customer;
    private MenuManager menuManager;
    private OrderManager orderManager;

    @BeforeEach
    void setUp() {
        ByteMe.Users = new HashMap<>();
        User admin = new Admin("Admin", "admin@example.com", "password", "admin");
        ByteMe.Users.put(admin.getEmail(), admin);

        menuManager = MenuManager.getInstance();
        orderManager = OrderManager.getInstance();
        customer = new Customer("customer", "john@example.com", "password", "customer");
    }

    @Test
    void testOrderingOutOfStockItems() {
        // Add an unavailable item to the menu
        FoodItem unavailableItem = new FoodItem("Unavailable Item", 10.0, "Category", false);
        menuManager.addItem(unavailableItem);

        // Simulate user input for adding the unavailable item to the cart
        String input = "Unavailable Item\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Attempt to add the unavailable item to the cart
        customer.addToCart();

        // Verify that the item was not added to the cart
        assertFalse(customer.getCartMap().containsKey(unavailableItem));
    }

    @Test
    void testInvalidLoginAttempts() {
        // Simulate invalid login attempts
        String input = "admin@example.com\nwrongpassword\nadmin@example.com\nwrongpassword\nadmin@example.com\nwrongpassword\nadmin@example.com\nwrongpassword\nadmin@example.com\nwrongpassword\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Attempt to login with invalid credentials
        Exception exception = assertThrows(InvalidLoginException.class, () -> {
            ByteMe.authenticate_user("admin");
        });

        // Verify the error message
        String expectedMessage = "Too many failed attempts to login.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}