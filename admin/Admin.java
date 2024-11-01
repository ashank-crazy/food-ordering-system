package admin;

import models.User;
import utils.MenuManager;
import utils.OrderManager;

import java.util.Scanner;

public class Admin extends User
{
    private MenuManager menuManager = new MenuManager();
    private final OrderManager orderManager = new OrderManager();

    public Admin(String name, String email, String password, String type)
    {
        super(name, email, password, type);
    }

    public void menuManagement(Admin verified_admin)
    {
        Scanner scanner = new Scanner(System.in);
        String query = "";

        while(!query.equals("4"))
        {
            verified_admin.menuManagementMenu();
            query = scanner.nextLine();

            switch (query)
            {
                case "1":
                    verified_admin.addFoodItem();
                    break;
                case "2":
                    verified_admin.updateFoodItem();
                    break;
                case "3":
                    verified_admin.removeItem();
                    break;
                case "4":
                    return;
                default:
                    System.out.println("\nPlease choose a valid option");
                    break;
            }
        }
    }

    public void orderManagement(Admin verified_admin)
    {
        Scanner scanner = new Scanner(System.in);
        String query = "";

        while(!query.equals("5"))
        {
            verified_admin.orderManagementMenu();
            query = scanner.nextLine();

            switch (query)
            {
                case "1":
                    verified_admin.viewPendingOrders();
                    break;
                case "2":
                    verified_admin.updateOrderStatus();
                    break;
                case "3":
                    verified_admin.processRefunds();
                    break;
                case "4":
                    verified_admin.handleSpecialRequests();
                    break;
                case "5":
                    return;
                default:
                    System.out.println("\nPlease choose a valid option");
                    break;
            }
        }
    }

    public void addFoodItem()
    {
//        menuManager.addItem(item);
    }

    public void updateFoodItem()
    {
//        item.setPrice(newPrice);
//        item.setAvailability(availability);
//        menuManager.updateItem(item);
    }

    public void viewPendingOrders()
    {
        orderManager.viewPendingOrders();
    }

    public void updateOrderStatus()
    {
//        order.updateStatus(status);
//        orderManager.processOrder(order);
    }

    public void removeItem() {
    }

    public void processRefunds() {
    }

    public void handleSpecialRequests() {
    }

    public void generateSalesReport(Admin verified_admin) {
    }

    @Override
    public void showMenu() {
        System.out.println();
        System.out.println("-------------------------------------------------------");
        System.out.println("-                    Admin Menu                       -");
        System.out.println("-------------------------------------------------------");
        System.out.println("1. Menu Management");
        System.out.println("2. Order Management");
        System.out.println("3. Generate Sales Report");
        System.out.println("4. Logout");
        System.out.println("-------------------------------------------------------");
    }

    public void menuManagementMenu() {
        System.out.println();
        System.out.println("-------------------------------------------------------");
        System.out.println("-                 Menu Management Menu                -");
        System.out.println("-------------------------------------------------------");
        System.out.println("1. Add Food Item");
        System.out.println("2. Update Food Item");
        System.out.println("3. Return Food Item");
        System.out.println("4. Go Back");
        System.out.println("-------------------------------------------------------");
    }

    public void orderManagementMenu() {
        System.out.println();
        System.out.println("-------------------------------------------------------");
        System.out.println("-                 Order Management Menu               -");
        System.out.println("-------------------------------------------------------");
        System.out.println("1. View Pending Orders");
        System.out.println("2. Update Order Status");
        System.out.println("3. Process Refunds");
        System.out.println("4. Handle Special Requests");
        System.out.println("5. Go Back");
        System.out.println("-------------------------------------------------------");
    }
}