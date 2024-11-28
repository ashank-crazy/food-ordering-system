package customer;

import models.FoodItem;
import models.Order;
import utils.OrderManager;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class VIPCustomer extends Customer
{
    private final OrderManager orderManager = OrderManager.getInstance();

    public VIPCustomer(String name, String email, String password, String type)
    {
        super(name, email, password, type);
    }

    @Override
    public void checkout() {

        if(cartMap.isEmpty())
        {
            System.out.println("Cart is empty!");
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

        if (response.equalsIgnoreCase("yes"))
        {
            System.out.println("Enter your special request:");
            String request = scanner.nextLine();
            currentOrder = new Order(new ArrayList<>(cartMap.keySet()), "VIP");
            orderManager.setSpecialRequests(currentOrder.getOrderId(), request);
        }
        else {
            currentOrder = new Order(new ArrayList<>(cartMap.keySet()), "VIP");
        }

        System.out.println("Enter payment details:");
        String paymentDetails = scanner.nextLine();

        System.out.println("Enter delivery address:");
        String deliveryAddress = scanner.nextLine();

        currentOrder = new Order(new ArrayList<>(cartMap.keySet()), "VIP");
        currentOrder.updateTotalForDay(temp);
        currentOrder.setPaymentDetails(paymentDetails);
        currentOrder.setDeliveryAddress(deliveryAddress);
        currentOrder.updateStatus("VIP Order Placed");
        orderManager.addOrder(currentOrder);
        System.out.println("VIP order placed: " + currentOrder);
        orderHistory.add(currentOrder);
        cartMap.clear();
    }
}