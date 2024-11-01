package Customer;

import Models.Order;
import Models.FoodItem;
import Models.User;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
    protected List<FoodItem> cart = new ArrayList<>();

    public Customer(String name, String email, String password, String type) {
        super(name, email, password, type);
    }

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

    @Override
    public void displayMenu() {

    }

    @Override
    public void showMenu() {

    }
}
