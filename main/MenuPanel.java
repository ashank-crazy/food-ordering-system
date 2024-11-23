package main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;
import models.FoodItem;
import utils.MenuManager;

public class MenuPanel extends JPanel {
    private MenuManager menuManager;
    private DefaultTableModel tableModel;
    private JTable menuTable;

    public MenuPanel() {
        menuManager = MenuManager.getInstance();
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Menu", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        add(titleLabel, BorderLayout.NORTH);

        String[] columnNames = {"Food Item", "Price (Rs)", "Availability"};
        tableModel = new DefaultTableModel(columnNames, 0);
        menuTable = new JTable(tableModel);

        JTableHeader header = menuTable.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 20));

        menuTable.setFont(new Font("Arial", Font.PLAIN, 18));
        menuTable.setRowHeight(30);
        JScrollPane scrollPane = new JScrollPane(menuTable);
        add(scrollPane, BorderLayout.CENTER);

        refreshMenu();
    }

    public void refreshMenu() {
        List<FoodItem> menuItems = menuManager.getMenu();
        tableModel.setRowCount(0);
        for (FoodItem item : menuItems) {
            Object[] row = {item.getName(), item.getPrice(), item.isAvailable() ? "Available" : "Unavailable"};
            tableModel.addRow(row);
        }
    }
}