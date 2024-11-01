package models;

import java.util.List;
import java.util.UUID;

public class Order
{
    private final UUID orderId;
    private final String customerType;
    private final List<FoodItem> items;
    private String status;
    private String specialRequest;
    private final boolean isVIP;

    public Order(List<FoodItem> items, String customerType)
    {
        this.orderId = UUID.randomUUID();
        this.customerType = customerType;
        this.items = items;
        this.status = "Pending";
        this.isVIP = "VIP".equalsIgnoreCase(customerType);
    }

    public UUID getOrderId() { return orderId; }
    public String getCustomerType() { return customerType; }
    public List<FoodItem> getItems() { return items; }
    public String getStatus() { return status; }
    public void processRefund() { this.status = "Refunded"; }
    public boolean isVIP() { return isVIP; }

    public void updateStatus(String newStatus) { this.status = newStatus; }

    @Override
    public String toString()
    {
        return "Order{" +
                "orderId=" + orderId +
                ", customerType='" + customerType + '\'' +
                ", items=" + items +
                ", status='" + status + '\'' +
                ", isVIP=" + isVIP +
                '}';
    }
}