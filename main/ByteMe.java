package main;

import Admin.Admin;
import Customer.Customer;
import Customer.VIPCustomer;
import Models.FoodItem;
import Utils.MenuManager;
import Utils.OrderManager;

import java.util.Scanner;

public class ByteMe {
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        MenuManager menuManager = new MenuManager();
        OrderManager orderManager = new OrderManager();
        Admin admin = new Admin(menuManager, orderManager);

        System.out.println("Welcome to Byte Me! Choose an option:");
        System.out.println("1. Customer");
        System.out.println("2. Admin");

        int choice = scanner.nextInt();
        if (choice == 1)
        {
            Customer customer = new VIPCustomer();  // For demonstration, creating VIP Customer
            // Additional Customer functionalities
        }
        else if (choice == 2)
        {
            // Admin functionalities
        }
        scanner.close();
    }
}
