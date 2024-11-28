import javax.swing.*;
import java.awt.*;

public class ByteMeGUI {
    private final CardLayout cardLayout;
    private final JPanel mainPanel;
    private final MenuPanel menuPanel;
    private final PendingOrdersPanel pendingOrdersPanel;

    public ByteMeGUI() {
        JFrame frame = new JFrame("Canteen Management");
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        menuPanel = new MenuPanel(this);
        pendingOrdersPanel = new PendingOrdersPanel(this);
        mainPanel.add(createInitialPanel(), "Initial");
        mainPanel.add(menuPanel, "Menu");
        mainPanel.add(pendingOrdersPanel, "PendingOrders");

        frame.add(mainPanel, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setVisible(true);

        cardLayout.show(mainPanel, "Initial");
    }

    private JPanel createInitialPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JButton menuButton = new JButton("Menu");
        JButton pendingOrdersButton = new JButton("Pending Orders");

        // to handle font size of "Menu" and "Pending Orders" buttons
        menuButton.setFont(new Font("Arial", Font.BOLD, 24));
        pendingOrdersButton.setFont(new Font("Arial", Font.BOLD, 24));

        menuButton.setPreferredSize(new Dimension(300, 150));
        pendingOrdersButton.setPreferredSize(new Dimension(300, 150));

        menuButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));
        pendingOrdersButton.addActionListener(e -> cardLayout.show(mainPanel, "PendingOrders"));

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(menuButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(pendingOrdersButton, gbc);

        return panel;
    }

    public void showInitialPanel() {
        cardLayout.show(mainPanel, "Initial");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ByteMeGUI::new);
    }
}