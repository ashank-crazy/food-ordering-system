package Admin;

import Models.FoodItem;
import Models.Order;
import Models.User;
import Utils.MenuManager;
import Utils.OrderManager;

public class Admin extends User
{
    private MenuManager menuManager = new MenuManager();
    private OrderManager orderManager = new OrderManager();

    public Admin(String name, String email, String password, String type)
    {
        super(name, email, password, type);
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

    @Override
    public void displayMenu() {

    }

    @Override
    public void showMenu() {

    }
}
