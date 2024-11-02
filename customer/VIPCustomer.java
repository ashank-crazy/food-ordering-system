package customer;

import models.Order;
import utils.OrderManager;

import java.util.ArrayList;
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
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter payment details:");
        String paymentDetails = scanner.nextLine();

        System.out.println("Enter delivery address:");
        String deliveryAddress = scanner.nextLine();

        currentOrder = new Order(new ArrayList<>(cartMap.keySet()), "VIP");
        currentOrder.setPaymentDetails(paymentDetails);
        currentOrder.setDeliveryAddress(deliveryAddress);
        currentOrder.updateStatus("VIP Order Placed");

        orderManager.addOrder(currentOrder);
        System.out.println("VIP order placed: " + currentOrder);
        orderHistory.add(currentOrder);
        cartMap.clear();
    }
}