package utils;

import models.FoodItem;
import java.util.ArrayList;
import java.util.List;

public class MenuManager
{
    private List<FoodItem> menu = new ArrayList<>();

    public void addItem(FoodItem item)
    {
        menu.add(item);
    }

    public void updateItem(FoodItem item)
    {
        // Logic to update item in the menu
    }

    public List<FoodItem> getMenu() { return menu; }
}
