package main;

import models.User;
import admin.Admin;
import customer.*;
import Exceptions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ByteMe {
    static HashMap<String, User> Users = new HashMap<>();
    static ArrayList<Customer> Customers = new ArrayList<>();
    static ArrayList<Admin> Admins = new ArrayList<>();

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        while(true)
        {
            mainmenu1();
            String select = scanner.nextLine();

            switch (select)
            {
                case "1":
                    mainmenu2();
                    loginmenu();
                    break;
                case "2":
                    mainmenu3();
                    signupmenu();
                    break;
                case "3":
                    System.out.println("Successfully exited");
                    return;
                default:
                    System.out.println("Please choose a valid option");
                    break;
            }
        }
    }

    private static void loginmenu()
    {
        try {
            Scanner scanner = new Scanner(System.in);
            String select = scanner.nextLine();

            switch (select) {
                case "1":
                    authenticate_user("customer");
                    break;
                case "2":
                    authenticate_user("admin");
                    break;
                case "3":
                    return;
                default:
                    System.out.println("\nPlease choose a valid option");
                    break;
            }
        }
        catch (Exception e)
        {
            System.out.println("Error 101 : Login menu " + e.getMessage());
        }
    }

    private static void signupmenu() {
        Scanner scanner = new Scanner(System.in);
        String select = scanner.nextLine();
        String email = "";
        String password = "";
        String name = "";

        try {
            if (select.equals("1") || select.equals("2")) {
                System.out.println("\nEnter your email id");
                email = scanner.nextLine();

                if (Users.containsKey(email)) {
                    System.out.println("\nUser with this email already exists. Please try logging in");
                    return;
                }

                System.out.println("\nChoose your Password");
                password = scanner.nextLine();
            }

            switch (select) {
                case "1":
                    Customer newCustomer = new Customer(name, email, password, "customer");
                    Customers.add(newCustomer);
                    Users.put(email, newCustomer);
                    System.out.println("\nSignup Successful ! you may login now");
                    break;
                case "2":
                    Admin newAdmin = new Admin(name, email, password, "admin");
                    Admins.add(newAdmin);
                    Users.put(email, newAdmin);
                    System.out.println("\nSignup Successful ! you may login now");
                    break;
                case "3":
                    return;
                default:
                    System.out.println("\nPlease choose a valid option");
                    break;
            }
        }
        catch (Exception e)
        {
            System.out.println("Error 102 : Signup menu " + e.getMessage());
        }
    }

    public static void authenticate_user(String userType) throws InvalidLoginException
    {
        try {
            Scanner scanner = new Scanner(System.in);
            int attempts = 5;

            while (attempts > 0) {
                System.out.println("Enter your email id (or type 'back' to go to the previous menu): ");
                String email = scanner.nextLine();

                if (email.equalsIgnoreCase("back"))
                    return;

                System.out.println("Enter your password: ");
                String password = scanner.nextLine();

                if (Users.containsKey(email)) {
                    User user = Users.get(email);

                    if (user.authenticate(password, userType)) {
                        System.out.println("Login Successful");

                        if (user instanceof Customer verified_customer)
                            handleCustomerMenu(verified_customer);
                        else if (user instanceof Admin verified_admin)
                            handleAdminMenu(verified_admin);
                        return;
                    } else
                        System.out.println("Incorrect Password, Try Again");
                } else
                    System.out.println("Incorrect Email, Try again");

                attempts--;
                System.out.println("Attempts remaining: " + attempts);
            }

            throw new InvalidLoginException("\nToo many failed attempts to login.");
        }
        catch (InvalidLoginException e)
        {
            System.out.println(e.getMessage());
        }
        catch (Exception e)
        {
            System.out.println("Error 103 : user authentication " + e.getMessage());
        }
    }

    private static void handleAdminMenu(Admin verified_admin) {
        Scanner scanner = new Scanner(System.in);
        String query = "";

        while(!query.equals("4"))
        {
            verified_admin.showMenu();
            query = scanner.nextLine();

            switch(query)
            {
                case "1":
                    verified_admin.menuManagement(verified_admin);
                    break;
                case "2":
                    verified_admin.orderManagement(verified_admin);
                    break;
                case "3":
                    verified_admin.generateSalesReport();
                    break;
                case "4":
                    System.out.println("\nSuccessfully Logged Out ! ");
                    return;
                default:
                    System.out.println("\nPlease choose a valid option");
                    break;
            }
        }
    }

    private static void handleCustomerMenu(Customer verified_customer) {
        Scanner scanner = new Scanner(System.in);
        String query = "";

        while(!query.equals("6"))
        {
            verified_customer.showMenu();
            query = scanner.nextLine();

            switch(query)
            {
                case "1":
                    verified_customer.browseMenu(verified_customer);
                    break;
                case "2":
                    verified_customer.cartOperations(verified_customer);
                    break;
                case "3":
                    verified_customer.orderTracking(verified_customer);
                    break;
                case "4":
                    verified_customer.itemReviews(verified_customer);
                    break;
                case "5":
                    verified_customer.upgradeToVIP(verified_customer);
                    break;
                case "6":
                    System.out.println("\nSuccessfully Logged Out ! ");
                    return;
                default:
                    System.out.println("\nPlease choose a valid option");
                    break;
            }
        }
    }

    public static void mainmenu3()
    {
        System.out.println("-------------------------------------------------------");
        System.out.println("-                    SignUp Menu                      -");
        System.out.println("-------------------------------------------------------");
        System.out.println("1. Signup as Customer");
        System.out.println("2. Signup as Administrator");
        System.out.println("3. Go Back");
        System.out.println("-------------------------------------------------------");
    }

    public static void mainmenu2()
    {
        System.out.println("-------------------------------------------------------");
        System.out.println("-                     Login Menu                      -");
        System.out.println("-------------------------------------------------------");
        System.out.println("1. Login as Customer");
        System.out.println("2. Login as Administrator");
        System.out.println("3. Go Back");
        System.out.println("-------------------------------------------------------");
    }

    public static void mainmenu1()
    {
        System.out.println("-------------------------------------------------------");
        System.out.println("-                      Main Menu                      -");
        System.out.println("-------------------------------------------------------");
        System.out.println("Welcome to Byte Me!");
        System.out.println("1. Login");
        System.out.println("2. Signup");
        System.out.println("3. Exit");
        System.out.println("-------------------------------------------------------");
    }
}