package admin;

import models.FoodItem;
import models.Order;
import models.User;
import utils.MenuManager;
import utils.OrderManager;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public class Admin extends User implements Serializable
{
    private static final long serialVersionUID = 1L;
    MenuManager menuManager = MenuManager.getInstance();
    private final OrderManager orderManager = OrderManager.getInstance();

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
                    verified_admin.updateOrderStatus(scanner);
                    break;
                case "3":
                    verified_admin.processRefunds(scanner);
                    break;
                case "4":
                    verified_admin.handleSpecialRequests(scanner);
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
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter item name:");
        String name = scanner.nextLine();

        if (menuManager.findItemByName(name) != null) {
            System.out.println("Item already exists. Try updating it instead.");
            return;
        }

        System.out.println("Enter item price:");
        double price = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Enter item category:");
        String category = scanner.nextLine();

        System.out.println("Is the item available? (true/false):");
        boolean available = scanner.nextBoolean();

        FoodItem newItem = new FoodItem(name, price, category, available);
        menuManager.addItem(newItem);
        menuManager.saveMenu();
    }

    public void updateFoodItem()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter item name to update:");
        String name = scanner.nextLine();

        FoodItem item = menuManager.findItemByName(name);
        if (item == null) {
            System.out.println("Item not found on the menu. Try adding it first.");
            return;
        }

        System.out.println("Enter new price:");
        double price = scanner.nextDouble();

        System.out.println("Is the item available? (true/false):");
        boolean available = scanner.nextBoolean();

        menuManager.updateItem(name, price, available);
        menuManager.saveMenu();
    }

    public void removeItem()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter item name to remove:");
        String name = scanner.nextLine();

        if (menuManager.findItemByName(name) == null) {
            System.out.println("Item not found on the menu.");
            return;
        }

        for (Order order : orderManager.getPendingOrders())
        {
            System.out.println(order);
            if (order.containsItem(name))
            {
                orderManager.denyOrder(order.getOrderID());
            }
        }

        menuManager.removeItem(name);
        System.out.println("WARNING !\nOrders containing the item will be denied.");
        menuManager.saveMenu();
    }

    public void viewPendingOrders()
    {
        List<Order> pendingOrders = orderManager.getPendingOrders();
        if (pendingOrders.isEmpty()) {
            System.out.println("No pending orders at the moment.");
        } else {
            System.out.println("Pending Orders:");
            for (Order order : pendingOrders) {
                System.out.println(order);
            }
        }
    }

    public void updateOrderStatus(Scanner scanner)
    {
        System.out.println("Enter the order ID to update:");
        int orderId;
        try {
            orderId = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter new status (e.g., 'Preparing', 'Out for Delivery', 'Completed'):");
            String newStatus = scanner.nextLine();
            orderManager.updateOrderStatus(orderId, newStatus);
            System.out.println("Order status updated successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid order ID format. Please enter a valid order ID.");
        } catch (NoSuchElementException e) {
            System.out.println("No order found with the given ID.");
        }
    }

    public void processRefunds(Scanner scanner)
    {
        System.out.println("Enter the order ID to refund:");
        int refundOrderId;
        try {
            refundOrderId = Integer.parseInt(scanner.nextLine());
            orderManager.processRefund(refundOrderId);
            System.out.println("Refund processed successfully for order ID: " + refundOrderId);
        } catch (NumberFormatException e) {
            System.out.println("Invalid order ID format. Please enter a valid integer order ID.");
        }
    }

    public void handleSpecialRequests(Scanner scanner) {
        System.out.println("Enter the order ID to view special requests:");
        int specialRequestOrderId;

        try {
            specialRequestOrderId = Integer.parseInt(scanner.nextLine());
            orderManager.getSpecialRequests(specialRequestOrderId);
        } catch (NumberFormatException e) {
            System.out.println("Invalid order ID format. Please enter a valid integer order ID.");
        }
    }

    public void generateSalesReport()
    {
        LocalDate today = LocalDate.now();
        List<Order> orders = orderManager.getOrdersForDate(today);
        double totalSales = Order.getTotalForToday(today);
        Map<String, Integer> itemCountMap = new HashMap<>();

        for (Order order : orders)
        {
            for (FoodItem item : order.getItems())
            {
                itemCountMap.put(item.getName(), itemCountMap.getOrDefault(item.getName(), 0) + 1);
            }
        }

        String mostPopularItem = null;
        int highestCount = 0;
        for (Map.Entry<String, Integer> entry : itemCountMap.entrySet())
        {
            if (entry.getValue() > highestCount)
            {
                highestCount = entry.getValue();
                mostPopularItem = entry.getKey();
            }
        }

        System.out.println("-------------------------------------------------------");
        System.out.println("-                  Daily Sales Report                 -");
        System.out.println("-------------------------------------------------------");
        System.out.println("Date: " + today);
        System.out.println("Total Sales: Rs " + String.format("%.2f", totalSales));
        System.out.println("Total Orders: " + orders.size());
        System.out.println("Most Popular Item: " + (mostPopularItem != null ? mostPopularItem : "N/A"));
        System.out.println("-------------------------------------------------------");
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
        System.out.println("3. Remove Food Item");
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