package utils;

import models.FoodItem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MenuManager implements Serializable {
    private static final long serialVersionUID = 1L;
    private static MenuManager instance;
    private final List<FoodItem> menu = new ArrayList<>();
    private static final String MENU_FILE = "menu.dat";

    public MenuManager() {
    }

    public static MenuManager getInstance() {
        if (instance == null) {
            synchronized (MenuManager.class) {
                if (instance == null) {
                    instance = new MenuManager();
                    instance.loadMenu();
                }
            }
        }
        return instance;
    }

    public synchronized void addItem(FoodItem item) {
        loadMenu();
        if (findItemByName(item.getName()) != null) {
            System.out.println("Item already exists on the menu: " + item.getName());
            return;
        }
        menu.add(item);
        saveMenu();



        // for debugging, plss remove
        System.out.println("Item added successfully: " + item);
        System.out.println("Current Menu:");
        for (FoodItem menuItem : menu) {
            System.out.println(menuItem);
        }



        System.out.println("Item added successfully: " + item);
    }

    public FoodItem findItemByName(String name) {
        loadMenu();
        for (FoodItem item : menu) {
            if (item.getName().equalsIgnoreCase(name))
                return item;
        }
        return null;
    }

    public void updateItem(String itemName, double newPrice, boolean availability) {
        loadMenu();
        FoodItem item = findItemByName(itemName);
        if (item == null) {
            System.out.println("Item not found on the menu: " + itemName);
            return;
        }
        item.setPrice(newPrice);
        item.setAvailability(availability);
        saveMenu();
        System.out.println("Item updated successfully: " + item);
    }

    public synchronized void removeItem(String itemName) {
        loadMenu();
        FoodItem item = findItemByName(itemName);
        if (item == null) {
            System.out.println("Item not found on the menu: " + itemName);
            return;
        }
        menu.remove(item);
        saveMenu();


        // for debugging, plss remove
        System.out.println("Item removed successfully: " + item);
        System.out.println("Current Menu:");



        for (FoodItem menuItem : menu) {
            System.out.println(menuItem);
        }

        System.out.println("Item removed successfully: " + itemName);
    }

    public void saveMenu() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(MENU_FILE))) {
            out.writeObject(menu);
        } catch (IOException e) {
            System.out.println("Error saving menu: " + e.getMessage());
        }
    }

    public void loadMenu() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(MENU_FILE))) {
            menu.clear();
            menu.addAll((List<FoodItem>) in.readObject());
        } catch (FileNotFoundException e) {
            System.out.println("Menu file not found, starting with an empty menu.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading menu: " + e.getMessage());
        }
    }

    public List<FoodItem> getMenu() {
        loadMenu();
        return new ArrayList<>(menu);
    }
}