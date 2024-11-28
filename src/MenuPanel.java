import javax.swing.*;
import java.awt.*;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import models.FoodItem;
import utils.MenuManager;

public class MenuPanel extends JPanel {
    private final MenuManager menuManager;
    private final DefaultTableModel tableModel;

    public MenuPanel(ByteMeGUI parent) {
        menuManager = MenuManager.getInstance();
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Menu", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        add(titleLabel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new Object[]{"Name", "Price", "Availability"}, 0);
        JTable menuTable = new JTable(tableModel);
        menuTable.setFont(new Font("Arial", Font.PLAIN, 18));
        menuTable.setRowHeight(30);

        JTableHeader header = menuTable.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 24));

        JScrollPane scrollPane = new JScrollPane(menuTable);
        add(scrollPane, BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 22));
        backButton.setPreferredSize(new Dimension(100, 50));
        backButton.addActionListener(e -> parent.showInitialPanel());
        add(backButton, BorderLayout.SOUTH);

        updateMenu();
    }

    public void updateMenu() {
        List<FoodItem> menuItems = menuManager.getMenu();
        tableModel.setRowCount(0);
        for (FoodItem item : menuItems) {
            tableModel.addRow(new Object[]{item.getName(), item.getPrice(), item.isAvailable() ? "Available" : "Unavailable"});
        }
    }
}