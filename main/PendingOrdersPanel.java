package main;

import models.FoodItem;
import models.Order;
import utils.OrderManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PendingOrdersPanel extends JPanel {
    private OrderManager orderManager;

    public PendingOrdersPanel() {
        orderManager = OrderManager.getInstance();
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Pending Orders", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        JTextArea ordersTextArea = new JTextArea();
        ordersTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(ordersTextArea);
        add(scrollPane, BorderLayout.CENTER);

        List<Order> pendingOrders = orderManager.getPendingOrders();
        for (Order order : pendingOrders) {
            ordersTextArea.append("Order ID: " + order.getOrderId() + "\n");
            ordersTextArea.append("Items: \n");
            for (FoodItem item : order.getItems()) {
                ordersTextArea.append("  - " + item.getName() + "\n");
            }
            ordersTextArea.append("Status: " + order.getStatus() + "\n\n");
        }
    }
}