package customer;

import models.Order;
import models.FoodItem;
import models.User;

import java.util.*;

public class Customer extends User {
    protected List<FoodItem> cart = new ArrayList<>();
    private final List<FoodItem> menuItems = new ArrayList<>();
    protected Map<FoodItem, Integer> cartMap = new HashMap<>();

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
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the item name to add to the cart:");
        String itemName = scanner.nextLine();
        FoodItem item = findMenuItemByName(itemName);

        if (item == null)
        {
            System.out.println("Item not found.");
            return;
        }

        System.out.println("Enter quantity:");
        int quantity = scanner.nextInt();

        cartMap.merge(item, quantity, Integer::sum);
        System.out.println("Added " + quantity + " of " + item.getName() + " to the cart.");
    }

    public void removeFromCart()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the item name to remove from the cart:");
        String itemName = scanner.nextLine();
        FoodItem item = findMenuItemByName(itemName);

        if (item == null || !cartMap.containsKey(item))
        {
            System.out.println("Item not in cart.");
            return;
        }

        cartMap.remove(item);
        System.out.println(item.getName() + " has been removed from the cart.");
    }

    public void checkout()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter payment details:");
        String paymentDetails = scanner.nextLine();

        System.out.println("Enter delivery address:");
        String deliveryAddress = scanner.nextLine();

        Order newOrder = new Order(new ArrayList<>(cartMap.keySet()), "Regular");
        newOrder.setPaymentDetails(paymentDetails);
        newOrder.setDeliveryAddress(deliveryAddress);

        System.out.println("Order placed: " + newOrder);
        cartMap.clear();
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
        if (menuItems.isEmpty()) {
            System.out.println("The menu is currently empty.");
            return;
        }
        System.out.println("Complete Menu:");
        for (FoodItem item : menuItems) {
            System.out.println(item.getName() + " - " + item.getPrice() + " Rs - " + (item.isAvailable() ? "Available" : "Unavailable"));
        }
    }

    public void updateFoodItem()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the item name to update the quantity:");
        String itemName = scanner.nextLine();
        FoodItem item = findMenuItemByName(itemName);

        if (item == null || !cartMap.containsKey(item))
        {
            System.out.println("Item not in cart.");
            return;
        }

        System.out.println("Enter new quantity:");
        int quantity = scanner.nextInt();

        if (quantity > 0)
        {
            cartMap.put(item, quantity);
            System.out.println("Updated quantity of " + item.getName() + " to " + quantity + ".");
        }
        else
        {
            cartMap.remove(item);
            System.out.println(item.getName() + " removed from the cart.");
        }
    }

    public void search() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter item name or keyword:");
        String keyword = scanner.nextLine().toLowerCase();

        List<FoodItem> results = menuItems.stream()
                .filter(item -> item.getName().toLowerCase().contains(keyword))
                .toList();

        if (results.isEmpty()) {
            System.out.println("No items found matching your search.");
        } else {
            System.out.println("Search Results:");
            results.forEach(item -> System.out.println(item.getName() + " - " + item.getPrice() + " Rs"));
        }
    }

    public void sortByPrice() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Sort by price:\n1. Ascending\n2. Descending");
        String sortOrder = scanner.nextLine();

        List<FoodItem> sortedItems = new ArrayList<>(menuItems);
        if (sortOrder.equals("1")) {
            sortedItems.sort(Comparator.comparingDouble(FoodItem::getPrice));
        } else if (sortOrder.equals("2")) {
            sortedItems.sort(Comparator.comparingDouble(FoodItem::getPrice).reversed());
        } else {
            System.out.println("Invalid option. Defaulting to ascending order.");
            sortedItems.sort(Comparator.comparingDouble(FoodItem::getPrice));
        }

        System.out.println("Sorted Menu:");
        sortedItems.forEach(item -> System.out.println(item.getName() + " - " + item.getPrice() + " Rs"));
    }

    public void filterByCategory() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter category:");
        String category = scanner.nextLine().toLowerCase();

        List<FoodItem> filteredItems = menuItems.stream()
                .filter(item -> item.getCategory().toLowerCase().equals(category))
                .toList();

        if (filteredItems.isEmpty()) {
            System.out.println("No items found in this category.");
        } else {
            System.out.println("Items in category '" + category + "':");
            filteredItems.forEach(item -> System.out.println(item.getName() + " - " + item.getPrice() + " Rs"));
        }
    }

    public void viewTotal()
    {
        double total = cartMap.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();

        System.out.println("Total price of items in cart: " + total + " Rs");
    }

    private FoodItem findMenuItemByName(String name)
    {
        return menuItems.stream()
                .filter(item -> item.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
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