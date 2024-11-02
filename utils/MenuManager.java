package utils;

import models.FoodItem;
import java.util.ArrayList;
import java.util.List;

public class MenuManager {
    private static MenuManager instance;
    private final List<FoodItem> menu = new ArrayList<>();

    public MenuManager() {}

    public static MenuManager getInstance() {
        if (instance == null) {
            instance = new MenuManager();
        }
        return instance;
    }

    public void addItem(FoodItem item) {
        if (findItemByName(item.getName()) != null) {
            System.out.println("Item already exists on the menu: " + item.getName());
            return;
        }
        menu.add(item);
        System.out.println("Item added successfully: " + item);
    }

    public FoodItem findItemByName(String name) {
        for (FoodItem item : menu) {
            if (item.getName().equalsIgnoreCase(name))
                return item;
        }
        return null;
    }

    public void updateItem(String itemName, double newPrice, boolean availability) {
        FoodItem item = findItemByName(itemName);
        if (item == null) {
            System.out.println("Item not found on the menu: " + itemName);
            return;
        }
        item.setPrice(newPrice);
        item.setAvailability(availability);
        System.out.println("Item updated successfully: " + item);
    }

    public void removeItem(String itemName) {
        FoodItem item = findItemByName(itemName);
        if (item == null) {
            System.out.println("Item not found on the menu: " + itemName);
            return;
        }
        menu.remove(item);
        System.out.println("Item removed successfully: " + itemName);
    }

    public List<FoodItem> getMenu() {
        return new ArrayList<>(menu);
    }
}