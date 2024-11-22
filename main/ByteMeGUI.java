package main;

import main.PendingOrdersPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ByteMeGUI {
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private MenuPanel menuPanel;
    private PendingOrdersPanel pendingOrdersPanel;

    public ByteMeGUI() {
        frame = new JFrame("Byte Me - Canteen Management");
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        menuPanel = new MenuPanel();
        pendingOrdersPanel = new PendingOrdersPanel();
        mainPanel.add(menuPanel, "Menu");
        mainPanel.add(pendingOrdersPanel, "PendingOrders");

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.add(createNavigationPanel(), BorderLayout.NORTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setVisible(true);
    }

    private JPanel createNavigationPanel() {
        JPanel panel = new JPanel();
        JButton menuButton = new JButton("Menu");
        JButton pendingOrdersButton = new JButton("Pending Orders");

        menuButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));
        pendingOrdersButton.addActionListener(e -> cardLayout.show(mainPanel, "PendingOrders"));

        panel.add(menuButton);
        panel.add(pendingOrdersButton);

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ByteMeGUI::new);
    }
}