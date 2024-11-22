package main;

import models.FoodItem;
import utils.MenuManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MenuPanel extends JPanel {
    private MenuManager menuManager;

    public MenuPanel() {
        menuManager = MenuManager.getInstance();
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Canteen Menu", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        JTextArea menuTextArea = new JTextArea();
        menuTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(menuTextArea);
        add(scrollPane, BorderLayout.CENTER);

        List<FoodItem> menuItems = menuManager.getMenu();
        for (FoodItem item : menuItems) {
            menuTextArea.append(item.getName() + " - " + item.getPrice() + " Rs - " + (item.isAvailable() ? "Available" : "Unavailable") + "\n");
        }
    }
}