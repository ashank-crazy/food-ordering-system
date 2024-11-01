package Models;

import java.util.List;

public class Order
{
    private List<FoodItem> items;
    private double totalPrice;
    private String status;
    private String customerType;

    public Order(List<FoodItem> items, String customerType)
    {
        this.items = items;
        this.totalPrice = calculateTotal();
        this.status = "Order Received";
        this.customerType = customerType;
    }

    private double calculateTotal()
    {
        return items.stream().mapToDouble(FoodItem::getPrice).sum();
    }

    public List<FoodItem> getItems() { return items; }
    public double getTotalPrice() { return totalPrice; }
    public String getStatus() { return status; }

    public void updateStatus(String status) { this.status = status; }
    public String getCustomerType() { return customerType; }
}
