package customer;

import models.Order;
import models.FoodItem;
import models.User;
import utils.MenuManager;
import utils.OrderManager;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Customer extends User implements Serializable {
    protected Map<FoodItem, Integer> cartMap = new HashMap<>();
    protected List<Order> orderHistory = new ArrayList<>();
    protected Order currentOrder;
    MenuManager menuManager = MenuManager.getInstance();
    private transient OrderManager orderManager;
    private static final Map<FoodItem, String> itemReviews = new HashMap<>();
    private static final long serialVersionUID = 1L;

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        orderManager = OrderManager.getInstance();
    }

    public Customer(String name, String email, String password, String type) {
        super(name, email, password, type);
        this.currentOrder = null;
        loadCustomerData();
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
        FoodItem item = menuManager.findItemByName(itemName);

        if (item == null)
        {
            System.out.println("Item not found.");
            return;
        }

        if(!item.isAvailable())
        {
            System.out.println("Item is currently unavailable.");
            return;
        }

        System.out.println("Item found: " + item.getName() + " - " + item.getPrice() + " Rs");
        System.out.println("Enter quantity:");
        int quantity = scanner.nextInt();
        cartMap.merge(item, quantity, Integer::sum);
        System.out.println("The cost of " + quantity + " " + item.getName() + " would be " + (item.getPrice() * quantity) + " Rs");
        System.out.println("Added " + quantity + " of " + item.getName() + " to the cart.");
    }

    public void removeFromCart()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the item name to remove from the cart:");
        String itemName = scanner.nextLine();
        FoodItem item = menuManager.findItemByName(itemName);

        if (item == null || !cartMap.containsKey(item)) {
            System.out.println("Item not in cart.");
            return;
        }

        cartMap.remove(item);
        System.out.println(item.getName() + " has been removed from the cart.");
    }

    public void checkout() {
        if (cartMap.isEmpty()) {
            System.out.println("Cart is empty.\n");
            return;
        }

        double temp = 0;
        System.out.println("Items in cart:");
        for (Map.Entry<FoodItem, Integer> entry : cartMap.entrySet()) {
            FoodItem item = entry.getKey();
            int quantity = entry.getValue();
            System.out.println(item.getName() + " - Quantity: " + quantity + " - Cost: " + (item.getPrice() * quantity) + " Rs");
            temp += item.getPrice() * quantity;
        }

        System.out.println("Do you have any Special Requests ? (yes/no)");
        Scanner scanner = new Scanner(System.in);
        String response = scanner.nextLine();

        if (response.equalsIgnoreCase("yes")) {
            System.out.println("Enter your special request:");
            String request = scanner.nextLine();
            currentOrder = new Order(new ArrayList<>(cartMap.keySet()), "Regular");
            orderManager.setSpecialRequests(currentOrder.getOrderId(), request);
        } else {
            currentOrder = new Order(new ArrayList<>(cartMap.keySet()), "Regular");
        }

        System.out.println("\nEnter payment details:");
        String paymentDetails = scanner.nextLine();

        System.out.println("Enter delivery address:");
        String deliveryAddress = scanner.nextLine();

        currentOrder = new Order(new ArrayList<>(cartMap.keySet()), "Regular");
        currentOrder.updateTotalForDay(temp);
        currentOrder.setPaymentDetails(paymentDetails);
        currentOrder.setDeliveryAddress(deliveryAddress);
        currentOrder.updateStatus("Order Placed");
        orderManager.addOrder(currentOrder);
        System.out.println("Regular order placed: " + currentOrder);
        orderHistory.add(currentOrder);
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

    public void viewAllItems()
    {
        List<FoodItem> items = menuManager.getMenu();
        if (items.isEmpty()) {
            System.out.println("The menu is currently empty.");
            return;
        }
        System.out.println("Complete Menu:");
        for (FoodItem item : items) {
            System.out.println(item.getName() + " - " + item.getPrice() + " Rs - " + (item.isAvailable() ? "Available" : "Unavailable"));
        }
    }

    public void updateFoodItem() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the item name to update the quantity:");
        String itemName = scanner.nextLine();
        FoodItem item = menuManager.findItemByName(itemName);

        if (item == null || !cartMap.containsKey(item)) {
            System.out.println("Item not in cart.");
            return;
        }

        System.out.println("Item found: \nItem : " + item.getName() + " | Price : " + item.getPrice() + " Rs | Quantity : " + cartMap.get(item) + "\n");
        System.out.println("Enter new quantity:");
        int quantity = scanner.nextInt();

        if (quantity > 0) {
            cartMap.put(item, quantity);
            System.out.println("Updated quantity of " + item.getName() + " to " + quantity + ".");
        } else {
            cartMap.remove(item);
            System.out.println(item.getName() + " removed from the cart.");
        }
    }

    public void search() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter item name or keyword:");
        String keyword = scanner.nextLine().toLowerCase();

        List<FoodItem> results = menuManager.getMenu().stream()
                .filter(item -> item.getName().toLowerCase().contains(keyword))
                .collect(Collectors.toList());

        if (results.isEmpty()) {
            System.out.println("No items found matching your search.");
        } else {
            System.out.println("Search Results:");
            results.forEach(item -> System.out.println(item.getName() + " - " + item.getPrice() + " Rs"));
        }
    }

    public void sortByPrice()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Sort by price:\n1. Ascending\n2. Descending");
        String sortOrder = scanner.nextLine();

        List<FoodItem> sortedItems = new ArrayList<>(menuManager.getMenu());
        if (sortOrder.equals("1"))
            sortedItems.sort(Comparator.comparingDouble(FoodItem::getPrice));
        else if (sortOrder.equals("2"))
            sortedItems.sort(Comparator.comparingDouble(FoodItem::getPrice).reversed());
        else
        {
            System.out.println("Invalid option. Defaulting to ascending order.");
            sortedItems.sort(Comparator.comparingDouble(FoodItem::getPrice));
        }

        System.out.println("Sorted Menu:");
        sortedItems.forEach(item -> System.out.println(item.getName() + " - " + item.getPrice() + " Rs"));
    }

    public void filterByCategory()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter category:");
        String category = scanner.nextLine().toLowerCase();

        List<FoodItem> filteredItems = menuManager.getMenu().stream()
                .filter(item -> item.getCategory().toLowerCase().equals(category))
                .collect(Collectors.toList());

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

    public void viewOrderStatus()
    {
        if (currentOrder == null) {
            System.out.println("No active orders to display status for.");
            return;
        }
        System.out.println("Current order status: " + currentOrder.getStatus());
    }

    public void cancelOrder()
    {
        if (currentOrder == null) {
            System.out.println("No current order to cancel.");
            return;
        }

        if(orderManager.getOrderStatus(currentOrder.getOrderId()).equalsIgnoreCase("prepared") || orderManager.getOrderStatus(currentOrder.getOrderId()).equalsIgnoreCase("processed"))
        {
            System.out.println("Order cannot be canceled as it is already " + orderManager.getOrderStatus(currentOrder.getOrderId()));
            return;
        }

        orderManager.cancelOrder(currentOrder);
        currentOrder = null;
        System.out.println("Order has been canceled.");
    }

    public void viewOrderHistory()
    {
        if (orderHistory.isEmpty()) {
            System.out.println("No order history.");
            return;
        }
        System.out.println("Order History:");
        for (Order order : orderHistory) {
            System.out.println(order);
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to reorder any item? (yes/no)");
        String response = scanner.nextLine();

        if (response.equalsIgnoreCase("yes")) {
            reorder();
        }
    }

    public void reorder()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the order ID to reorder:");
        int orderId = scanner.nextInt();
        scanner.nextLine();

        Order orderToReorder = null;
        for (Order order : orderHistory) {
            if (order.getOrderId() == orderId) {
                orderToReorder = order;
                break;
            }
        }

        if (orderToReorder == null) {
            System.out.println("Order not found in history.");
            return;
        }

        currentOrder = new Order(orderToReorder.getItems(), "Regular");
//        currentOrder.updateTotalForDay(orderToReorder.getTotalForDay());
        currentOrder.setPaymentDetails(orderToReorder.getPaymentDetails());
        currentOrder.setDeliveryAddress(orderToReorder.getDeliveryAddress());
        currentOrder.updateStatus("Order Placed");
        orderManager.addOrder(currentOrder);
        System.out.println("Reorder placed: " + currentOrder);
        orderHistory.add(currentOrder);
    }

    public void provideReview()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the item name to review:");
        String itemName = scanner.nextLine();
        FoodItem item = menuManager.findItemByName(itemName);

        if (item == null) {
            System.out.println("Item not found.");
            return;
        }

        System.out.println("Enter your review:");
        String review = scanner.nextLine();
        itemReviews.put(item, review);
        System.out.println("Review added for " + item.getName() + ".");
    }

    public void viewReviews()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the item name to view reviews:");
        String itemName = scanner.nextLine();
        FoodItem item = menuManager.findItemByName(itemName);

        if (item == null || !itemReviews.containsKey(item)) {
            System.out.println("No reviews found for this item.");
            return;
        }

        System.out.println("Review for " + item.getName() + ":");
        System.out.println("- " + itemReviews.get(item));
    }

    public VIPCustomer upgradeToVIP(Customer verified_customer)
    {
        if (verified_customer instanceof VIPCustomer) {
            System.out.println("Already a VIP customer.");
            return (VIPCustomer) verified_customer;
        }

        VIPCustomer vipCustomer = new VIPCustomer(getName(), getEmail(), getPassword(), "VIP");
        vipCustomer.cartMap.putAll(verified_customer.cartMap);
        vipCustomer.orderHistory.addAll(verified_customer.orderHistory);
        if (verified_customer.currentOrder != null) {
            vipCustomer.currentOrder = verified_customer.currentOrder;
        }

        System.out.println("Congratulations!\nYou have successfully upgraded to VIP!");
        System.out.println("Your orders will now be prioritized.");

        return vipCustomer;
    }

    public void loadCustomerData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("customers.dat"))) {
            Object data = ois.readObject();
            if (data instanceof List) {
                @SuppressWarnings("unchecked")
                List<Customer> customers = (List<Customer>) data;
                Optional<Customer> matchingCustomer = customers.stream()
                        .filter(c -> c.getEmail().equals(this.getEmail()))
                        .findFirst();

                if (matchingCustomer.isPresent()) {
                    Customer customer = matchingCustomer.get();
                    this.cartMap = customer.cartMap;
                    this.orderHistory = customer.orderHistory;
                    this.currentOrder = customer.currentOrder;
                    System.out.println("Customer data loaded successfully.");
                } else {
                    System.out.println("No matching customer found in customers.dat.");
                }
            } else {
                System.out.println("Invalid data format in customers.dat.");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Failed to load customer data or no data exists.");
        }
    }

    public void saveCustomerData() {
        List<Customer> customers = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("customers.dat"))) {
            Object data = ois.readObject();
            if (data instanceof List) {
                        customers = (List<Customer>) data;
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No previous data found. Starting fresh.");
        }

        customers.removeIf(c -> c.getEmail().equals(this.getEmail()));
        customers.add(this);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("customers.dat"))) {
            oos.writeObject(customers);
            System.out.println("Customer data saved successfully.");
        } catch (IOException e) {
            System.out.println("Failed to save customer data.");
        }
    }

    public Map<FoodItem, Integer> getCartMap() {
        return cartMap;
    }

    public static Map<FoodItem, String> getItemReviews() {
        return itemReviews;
    }

    public static void setItemReviews(FoodItem item, String review) {
        itemReviews.put(item, review);
    }
}