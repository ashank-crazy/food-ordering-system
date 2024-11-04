package utils;

import models.Order;

import java.time.LocalDate;
import java.util.*;

public class OrderManager
{
    private static OrderManager instance;
    private final Queue<Order> orderQueue;
    private static Map<Integer, String> specialRequests = new HashMap<>();

    public OrderManager()
    {
        orderQueue = new PriorityQueue<>((o1, o2) -> {
            if (o1.isVIP() && !o2.isVIP()) return -1;
            if (!o1.isVIP() && o2.isVIP()) return 1;
            return 0;
        });
    }

    public static OrderManager getInstance() {
        if (instance == null) {
            instance = new OrderManager();
        }
        return instance;
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

    public void updateOrderStatus(int orderId, String newStatus)
    {
        for (Order order : new ArrayList<>(orderQueue))
        {
            if (order.getOrderId() == orderId)
            {
                order.updateStatus(newStatus);
                System.out.println("Order status updated: " + order);
                return;
            }
        }
        System.out.println("Order not found with ID: " + orderId);
    }

    public void processRefund(int orderId)
    {
        for (Order order : new ArrayList<>(orderQueue))
        {
            if (order.getOrderId() == orderId)
            {
                order.processRefund();
                orderQueue.remove(order);
                return;
            }
        }
        System.out.println("Order not found with ID: " + orderId);
    }

    public void getSpecialRequests(int orderId)
    {
        if (specialRequests.containsKey(orderId)) {
            System.out.println("Special Request: " + specialRequests.get(orderId));
        } else {
            System.out.println("No special requests found for this order.");
        }
    }

    public void setSpecialRequests(int orderId, String request)
    {
        specialRequests.put(orderId, request);
        System.out.println("Special request added for order ID: " + orderId);
    }

    public ArrayList<Order> getOrdersForDate(LocalDate today)
    {
        ArrayList<Order> ordersForDate = new ArrayList<>();

        for (Order order : orderQueue)
        {
            if (order.getOrderDate().toLocalDate().isEqual(today))
            {
                ordersForDate.add(order);
            }
        }
        return ordersForDate;
    }

    public List<Order> getPendingOrders() {
        List<Order> pendingOrders = new ArrayList<>();
        for (Order order : orderQueue) {
            if (!order.isCompleted()) {
                pendingOrders.add(order);
            }
        }
        return pendingOrders;
    }

    public void cancelOrder(Order currentOrder)
    {
        Iterator<Order> iterator = orderQueue.iterator();
        while (iterator.hasNext())
        {
            Order order = iterator.next();
            if (order.getOrderId() == currentOrder.getOrderId())
            {
                order.updateStatus("Cancelled");
                iterator.remove();
                System.out.println("Order cancelled: " + order);
                return;
            }
        }
        System.out.println("Order not found for cancellation: " + currentOrder.getOrderId());
    }
}