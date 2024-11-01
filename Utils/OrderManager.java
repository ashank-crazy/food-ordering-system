package Utils;

import Models.Order;
import java.util.PriorityQueue;

public class OrderManager
{
    private PriorityQueue<Order> orderQueue = new PriorityQueue<>((o1, o2) -> {
        if (o1.getCustomerType().equals("VIP") && o2.getCustomerType().equals("Regular")) return -1;
        else if (o1.getCustomerType().equals("Regular") && o2.getCustomerType().equals("VIP")) return 1;
        return 0;
    });

    public void processOrder(Order order)
    {
        orderQueue.add(order);
    }

    public void viewPendingOrders()
    {
        orderQueue.forEach(order -> System.out.println("Order Status: " + order.getStatus()));
    }
}
