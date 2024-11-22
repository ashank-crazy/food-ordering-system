package main;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import models.FoodItem;
import utils.MenuManager;

public class MenuPanel extends JPanel {
    private MenuManager menuManager;
    private JTextArea menuTextArea;

    public MenuPanel() {
        menuManager = MenuManager.getInstance();
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Canteen Menu", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        menuTextArea = new JTextArea();
        menuTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(menuTextArea);
        add(scrollPane, BorderLayout.CENTER);

        refreshMenu();
    }

    public void refreshMenu() {
        List<FoodItem> menuItems = menuManager.getMenu();
        menuTextArea.setText("");
        for (FoodItem item : menuItems) {
            menuTextArea.append(item.getName() + " - " + item.getPrice() + " Rs - " + (item.isAvailable() ? "Available" : "Unavailable") + "\n");
        }
    }
}