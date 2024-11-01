package Customer;

import Models.Order;
import Models.FoodItem;
import java.util.ArrayList;
import java.util.List;

public class Customer
{
    protected List<FoodItem> cart = new ArrayList<>();

    public void addToCart(FoodItem item)
    {
        cart.add(item);
    }

    public void removeFromCart(FoodItem item)
    {
        cart.remove(item);
    }

    public Order checkout()
    {
        return new Order(cart, "Regular");
    }
}
