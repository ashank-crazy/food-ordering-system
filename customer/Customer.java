package customer;

import models.Order;
import models.FoodItem;
import models.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Customer extends User {
    protected List<FoodItem> cart = new ArrayList<>();

    public Customer(String name, String email, String password, String type) {
        super(name, email, password, type);
    }

    public void browseMenu(Customer verified_customer) {
        Scanner scanner = new Scanner(System.in);
        String query = "";

        while(!query.equals("5"))
        {
            verified_customer.browseMenu();
            query = scanner.nextLine();

            switch (query)
            {
                case "1":
                    verified_customer.viewAllItems();
                    break;
                case "2":
                    verified_customer.search();
                    break;
                case "3":
                    verified_customer.filterByCategory();
                    break;
                case "4":
                    verified_customer.sortByPrice();
                    break;
                case "5":
                    return;
                default:
                    System.out.println("\nPlease choose a valid option");
                    break;
            }
        }
    }

    public void cartOperations(Customer verified_customer) {
        Scanner scanner = new Scanner(System.in);
        String query = "";

        while(!query.equals("6"))
        {
            verified_customer.cartOperationsMenu();
            query = scanner.nextLine();

            switch (query)
            {
                case "1":
                    verified_customer.addToCart();
                    break;
                case "2":
                    verified_customer.updateFoodItem();
                    break;
                case "3":
                    verified_customer.removeFromCart();
                    break;
                case "4":
                    verified_customer.viewTotal();
                    break;
                case "5":
                    verified_customer.checkout();
                    break;
                case "6":
                    return;
                default:
                    System.out.println("\nPlease choose a valid option");
                    break;
            }
        }
    }

    public void orderTracking(Customer verified_customer) {
        Scanner scanner = new Scanner(System.in);
        String query = "";

        while(!query.equals("4"))
        {
            verified_customer.orderTrackingMenu();
            query = scanner.nextLine();

            switch (query)
            {
                case "1":
                    verified_customer.viewOrderStatus();
                    break;
                case "2":
                    verified_customer.cancelOrder();
                    break;
                case "3":
                    verified_customer.viewOrderHistory();
                    break;
                case "4":
                    return;
                default:
                    System.out.println("\nPlease choose a valid option");
                    break;
            }
        }
    }

    public void itemReviews(Customer verified_customer)
    {
        Scanner scanner = new Scanner(System.in);
        String query = "";

        while(!query.equals("3"))
        {
            verified_customer.itemReviewsMenu();
            query = scanner.nextLine();

            switch (query)
            {
                case "1":
                    verified_customer.provideReview();
                    break;
                case "2":
                    verified_customer.viewReviews();
                    break;
                case "3":
                    return;
                default:
                    System.out.println("\nPlease choose a valid option");
                    break;
            }
        }
    }

    public void addToCart()
    {
//        cart.add(item);
    }

    public void removeFromCart()
    {
//        cart.remove(item);
    }

    public Order checkout()
    {
        Order newOrder = new Order(cart, "Regular");
        System.out.println("Regular order placed: " + newOrder);
        cart.clear();
        return newOrder;
    }

    @Override
    public void showMenu() {
        System.out.println();
        System.out.println("-------------------------------------------------------");
        System.out.println("-                   Customer Menu                     -");
        System.out.println("-------------------------------------------------------");
        System.out.println("1. Browse Menu");
        System.out.println("2. Cart Operations");
        System.out.println("3. Order Tracking");
        System.out.println("4. Item Reviews");
        System.out.println("5. Upgrade to VIP");
        System.out.println("6. Logout");
        System.out.println("-------------------------------------------------------");
    }

    public void browseMenu() {
        System.out.println();
        System.out.println("-------------------------------------------------------");
        System.out.println("-                     Browse Menu                     -");
        System.out.println("-------------------------------------------------------");
        System.out.println("1. View All Items");
        System.out.println("2. Search");
        System.out.println("3. Filter By Category");
        System.out.println("4. Sort By Price");
        System.out.println("5. Go Back");
        System.out.println("-------------------------------------------------------");
    }

    private void cartOperationsMenu() {
        System.out.println();
        System.out.println("-------------------------------------------------------");
        System.out.println("-                 Cart Operations Menu                -");
        System.out.println("-------------------------------------------------------");
        System.out.println("1. Add To Cart");
        System.out.println("2. Update Item");
        System.out.println("3. Remove Item");
        System.out.println("4. View Total");
        System.out.println("5. Checkout");
        System.out.println("6. Go Back");
        System.out.println("-------------------------------------------------------");
    }

    private void orderTrackingMenu() {
        System.out.println();
        System.out.println("-------------------------------------------------------");
        System.out.println("-                  Order Tracking Menu                -");
        System.out.println("-------------------------------------------------------");
        System.out.println("1. View Order Status");
        System.out.println("2. Cancel Order");
        System.out.println("3. Order History");
        System.out.println("4. Go Back");
        System.out.println("-------------------------------------------------------");
    }

    private void itemReviewsMenu() {
        System.out.println();
        System.out.println("-------------------------------------------------------");
        System.out.println("-                   Item Review Menu                  -");
        System.out.println("-------------------------------------------------------");
        System.out.println("1. Provide Review");
        System.out.println("2. View Reviews");
        System.out.println("3. Go Back");
        System.out.println("-------------------------------------------------------");
    }

    private void viewAllItems() {
    }

    public void updateFoodItem() {
    }

    public void search() {
    }

    public void sortByPrice() {
    }

    public void filterByCategory() {
    }

    public void viewTotal() {
    }

    public void viewOrderStatus() {
    }

    public void cancelOrder() {
    }

    public void viewOrderHistory() {
    }

    public void provideReview() {
    }

    public void viewReviews() {
    }

    public void upgradeToVIP(Customer verified_customer)
    {
        if (verified_customer instanceof VIPCustomer)
        {
            System.out.println("Already a VIP customer.");
            return;
        }

        VIPCustomer vipCustomer = new VIPCustomer(getName(), getEmail(), getPassword(), "VIP");
        vipCustomer.cart = this.cart;
        System.out.println("Congratulations ! \nYou have successfully upgraded to VIP !");
        System.out.println("The Orders you place now onwards will be prioritized\n" +
                            "Unfortunately we cannot do anything about older orders");
    }
}