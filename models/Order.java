package models;

import java.time.OffsetDateTime;
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
    private final OffsetDateTime orderDate;
    private String paymentDetails;
    private String deliveryAddress;
    private boolean completed;

    public Order(List<FoodItem> items, String customerType)
    {
        this.orderId = UUID.randomUUID();
        this.customerType = customerType;
        this.items = items;
        this.status = "Pending";
        this.isVIP = "VIP".equalsIgnoreCase(customerType);
        this.orderDate = OffsetDateTime.now();
        this.completed = false;
    }

    public UUID getOrderId() { return orderId; }
    public String getCustomerType() { return customerType; }
    public List<FoodItem> getItems() { return items; }
    public String getStatus() { return status; }
    public void processRefund() {
        this.status = "Refunded";
        this.completed = true;
    }
    public boolean isVIP() { return isVIP; }
    public OffsetDateTime getOrderDate() { return orderDate; }

    public void updateStatus(String newStatus)
    {
        this.status = newStatus;
        if ("Completed".equalsIgnoreCase(newStatus)) {
            this.completed = true;
        }
    }

    public void setPaymentDetails(String paymentDetails)
    {
        this.paymentDetails = paymentDetails;
    }

    public void setDeliveryAddress(String deliveryAddress)
    {
        this.deliveryAddress = deliveryAddress;
    }

    public String getPaymentDetails()
    {
        return paymentDetails;
    }

    public String getDeliveryAddress()
    {
        return deliveryAddress;
    }

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

    public double getTotalPrice() {
        double total = 0.0;
        for (FoodItem item : items)
        {
            total += item.getPrice();
        }
        return total;
    }

    public boolean isCompleted() {
        return completed;
    }
}