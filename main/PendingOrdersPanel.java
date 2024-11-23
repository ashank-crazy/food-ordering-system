package main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;
import models.FoodItem;
import models.Order;
import utils.OrderManager;

public class PendingOrdersPanel extends JPanel {
    private OrderManager orderManager;
    private DefaultTableModel tableModel;
    private JTable ordersTable;

    public PendingOrdersPanel() {
        orderManager = OrderManager.getInstance();
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Pending Orders", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        add(titleLabel, BorderLayout.NORTH);

        String[] columnNames = {"Order ID", "Items", "Status"};
        tableModel = new DefaultTableModel(columnNames, 0);
        ordersTable = new JTable(tableModel);

        JTableHeader header = ordersTable.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 20));

        ordersTable.setFont(new Font("Arial", Font.PLAIN, 18));
        ordersTable.setRowHeight(30);
        JScrollPane scrollPane = new JScrollPane(ordersTable);
        add(scrollPane, BorderLayout.CENTER);

        refreshOrders();
    }

    public void refreshOrders() {
        List<Order> pendingOrders = orderManager.getPendingOrders();
        tableModel.setRowCount(0);
        for (Order order : pendingOrders) {
            StringBuilder items = new StringBuilder();
            for (FoodItem item : order.getItems()) {
                items.append(item.getName()).append(", ");
            }
            if (items.length() > 0) {
                items.setLength(items.length() - 2);
            }
            Object[] row = {order.getOrderId(), items.toString(), order.getStatus()};
            tableModel.addRow(row);
        }
    }
}