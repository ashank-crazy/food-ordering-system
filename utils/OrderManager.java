package utils;

import models.Order;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.UUID;
import java.util.ArrayList;

public class OrderManager
{
    private final Queue<Order> orderQueue;

    public OrderManager()
    {
        orderQueue = new PriorityQueue<>((o1, o2) -> {
            if (o1.isVIP() && !o2.isVIP()) return -1;
            if (!o1.isVIP() && o2.isVIP()) return 1;
            return 0;
        });
    }

    public void addOrder(Order order)
    {
        orderQueue.add(order);
        System.out.println("Order added: " + order);
    }

    public void viewPendingOrders()
    {
        System.out.println("Pending Orders:");
        for (Order order : orderQueue)
            System.out.println(order);
    }

    public void updateOrderStatus(UUID orderId, String newStatus)
    {
        for (Order order : orderQueue)
        {
            if (order.getOrderId().equals(orderId))
            {
                order.updateStatus(newStatus);
                System.out.println("Order status updated: " + order);
                return;
            }
        }
        System.out.println("Order not found with ID: " + orderId);
    }

    public void processRefund(UUID orderId)
    {
        for (Order order : orderQueue)
        {
            if (order.getOrderId().equals(orderId))
            {
                order.processRefund();
                orderQueue.remove(order);
                return;
            }
        }
        System.out.println("Order not found with ID: " + orderId);
    }

    public void handleSpecialRequest(UUID orderId, String request)
    {
        for (Order order : orderQueue)
        {
            if (order.getOrderId().equals(orderId))
            {
                order.updateStatus("Special request: " + request);
                System.out.println("Special request updated for order ID: " + orderId);
                return;
            }
        }
        System.out.println("Order not found with ID: " + orderId);
    }

    public ArrayList<Order> getOrdersForDate(LocalDate today) {
        ArrayList<Order> ordersForDate = new ArrayList<>();

        for (Order order : orderQueue) {
            if (order.getOrderDate().isEqual(OffsetDateTime.from(today))) {
                ordersForDate.add(order);
            }
        }

        return ordersForDate;
    }
}