package Admin;

import Models.FoodItem;
import Models.Order;
import Utils.MenuManager;
import Utils.OrderManager;

public class Admin
{
    private MenuManager menuManager;
    private OrderManager orderManager;

    public Admin(MenuManager menuManager, OrderManager orderManager)
    {
        this.menuManager = menuManager;
        this.orderManager = orderManager;
    }

    public void addFoodItem(FoodItem item)
    {
        menuManager.addItem(item);
    }

    public void updateFoodItem(FoodItem item, double newPrice, boolean availability)
    {
        item.setPrice(newPrice);
        item.setAvailability(availability);
        menuManager.updateItem(item);
    }

    public void viewPendingOrders()
    {
        orderManager.viewPendingOrders();
    }

    public void updateOrderStatus(Order order, String status)
    {
        order.updateStatus(status);
        orderManager.processOrder(order);
    }
}
