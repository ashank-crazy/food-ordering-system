package customer;

import models.Order;
import models.FoodItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VIPCustomer extends Customer
{
    public VIPCustomer(String name, String email, String password, String type)
    {
        super(name, email, password, type);
    }

    @Override
    public void checkout()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter payment details:");
        String paymentDetails = scanner.nextLine();

        System.out.println("Enter delivery address:");
        String deliveryAddress = scanner.nextLine();

        Order vipOrder = new Order(new ArrayList<>(cartMap.keySet()), "VIP");
        vipOrder.setPaymentDetails(paymentDetails);
        vipOrder.setDeliveryAddress(deliveryAddress);

        vipOrder.updateStatus("VIP Order Placed");

        System.out.println("VIP order placed: " + vipOrder);

        orderHistory.add(vipOrder);
        cartMap.clear();
    }
}