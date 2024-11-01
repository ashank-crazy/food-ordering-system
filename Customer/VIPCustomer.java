package Customer;

import Models.Order;

public class VIPCustomer extends Customer
{
    public VIPCustomer(String name, String email, String password, String type) {
        super(name, email, password, type);
    }

    @Override
    public Order checkout()
    {
        return new Order(cart, "VIP");
    }
}
