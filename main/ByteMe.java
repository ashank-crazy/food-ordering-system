package main;

import Customer.VIPCustomer;
import Models.User;
import Utils.MenuManager;
import Utils.OrderManager;
import Admin.Admin;
import Customer.*;

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

    private static void loginmenu() {
        Scanner scanner = new Scanner(System.in);
        String select = scanner.nextLine();

        switch(select)
        {
            case "1":
                authenticate_user("Student");
                break;
            case "2":
                authenticate_user("Admin");
                break;
            case "3":
                return;
            default:
                System.out.println("Please choose a valid option");
                break;
        }
    }

    private static void signupmenu() {
        Scanner scanner = new Scanner(System.in);
        String select = scanner.nextLine();
        String email = "";
        String password = "";
        String name = "";

        if(select.equals("1") || select.equals("2"))
        {
            System.out.println("Enter your email id");
            email = scanner.nextLine();

            if(Users.containsKey(email))
            {
                System.out.println("User with this email already exists. Please try logging in");
                return;
            }

            System.out.println("Choose your Password");
            password = scanner.nextLine();
        }

        switch(select)
        {
            case "1":
                Customer newCustomer = new Customer(name, email, password, "Customer");
                Customers.add(newCustomer);
                Users.put(email, newCustomer);
                System.out.println("Signup Successful ! you may login now");
                break;
            case "2":
                Admin newAdmin = new Admin(name, email, password, "Admin");
                Admins.add(newAdmin);
                Users.put(email, newAdmin);
                System.out.println("Signup Successful ! you may login now");
                break;
            case "3":
                return;
            default:
                System.out.println("Please choose a valid option");
                break;
        }
    }

    public static void authenticate_user(String userType)
    {
        Scanner scanner = new Scanner(System.in);
        int attempts = 5;

        while (attempts > 0)
        {
            System.out.println("Enter your email id (or type 'back' to go to the previous menu): ");
            String email = scanner.nextLine();

            if (email.equalsIgnoreCase("back"))
                return;

            System.out.println("Enter your password: ");
            String password = scanner.nextLine();

            if (Users.containsKey(email))
            {
                User user = Users.get(email);

                if (user.authenticate(password, userType))
                {
                    System.out.println("Login Successful");

                    if (user instanceof Customer verified_customer)
                        handleCustomerMenu(verified_customer);
                    else if (user instanceof Admin verified_admin)
                        handleAdminMenu(verified_admin);
                    return;
                }
                else
                    System.out.println("Incorrect Password, Try Again");
            }
            else
                System.out.println("Incorrect Email, Try again");

            attempts--;
            System.out.println("Attempts remaining: " + attempts);
        }

        System.out.println("Too many failed attempts");
    }

    private static void handleAdminMenu(User verifiedAdmin) {
    }

    private static void handleCustomerMenu(User verifiedCustomer) {
    }

    public static void mainmenu3()
    {
        System.out.println("-------------------------------------------------------");
        System.out.println("-                    SignUp Menu                      -");
        System.out.println("-------------------------------------------------------");
        System.out.println("1. Signup as Student");
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
