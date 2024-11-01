package Customer;

import Models.Order;

public class VIPCustomer extends Customer
{
    @Override
    public Order checkout()
    {
        return new Order(cart, "VIP");
    }
}
