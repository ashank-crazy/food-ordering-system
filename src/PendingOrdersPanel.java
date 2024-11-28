import javax.swing.*;
import java.awt.*;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import models.FoodItem;
import models.Order;
import utils.OrderManager;

public class PendingOrdersPanel extends JPanel {
    private final OrderManager orderManager;
    private final DefaultTableModel tableModel;

    public PendingOrdersPanel(ByteMeGUI parent) {
        orderManager = OrderManager.getInstance();
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Pending Orders", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        add(titleLabel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new Object[]{"Order ID", "Items", "Status"}, 0);
        JTable ordersTable = new JTable(tableModel);
        ordersTable.setFont(new Font("Arial", Font.PLAIN, 18));
        ordersTable.setRowHeight(30);

        JTableHeader header = ordersTable.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 24));

        JScrollPane scrollPane = new JScrollPane(ordersTable);
        add(scrollPane, BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 22));
        backButton.setPreferredSize(new Dimension(100, 50));
        backButton.addActionListener(e -> parent.showInitialPanel());
        add(backButton, BorderLayout.SOUTH);

        updateOrders();
    }

    public void updateOrders() {
        List<Order> pendingOrders = orderManager.getPendingOrders();
        tableModel.setRowCount(0);
        for (Order order : pendingOrders) {
            StringBuilder items = new StringBuilder();
            for (FoodItem item : order.getItems()) {
                items.append(item.getName()).append(", ");
            }
            if (!items.isEmpty()) {
                items.setLength(items.length() - 2);
            }
            tableModel.addRow(new Object[]{order.getOrderId(), items.toString(), order.getStatus()});
        }
    }
}