package models;

import utils.OrderManager;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;

public class Order
{
    private final OrderManager orderManager = OrderManager.getInstance();
    private static int idCounter = 1;
    private final int orderId;
    private final String customerType;
    private final List<FoodItem> items;
    private String status;
    private String specialRequest;
    private final boolean isVIP;
    private final OffsetDateTime orderDate;
    private String paymentDetails;
    private String deliveryAddress;
    private boolean completed;
    public static HashMap<LocalDate, Double> totalForDay = new HashMap<>();

    public Order(List<FoodItem> items, String customerType)
    {
        orderId = idCounter++;
        this.customerType = customerType;
        this.items = items;
        this.status = "Pending";
        this.isVIP = "VIP".equalsIgnoreCase(customerType);
        this.orderDate = OffsetDateTime.now();
        this.completed = false;
    }

    public int getOrderId() { return orderId; }
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

    public void updateTotalForDay(double temp)
    {
        LocalDate today = LocalDate.now();
        totalForDay.put(today, totalForDay.getOrDefault(today, 0.0) + temp);

    }

    public static double getTotalForToday(LocalDate date)
    {
        return totalForDay.getOrDefault(date, 0.0);
    }

    public boolean isCompleted() {
        return completed;
    }

    public int getOrderID()
    {
        return orderId;
    }

    public boolean containsItem(String name) {
        for (FoodItem item : items) {
            if (item.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }
}