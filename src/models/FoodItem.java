package models;

import java.io.Serializable;
import java.util.Objects;

public class FoodItem implements Serializable
{
    private static final long serialVersionUID = 1L;
    private final String name;
    private double price;
    private final String category;
    private boolean available;

    public FoodItem(String name, double price, String category, boolean available)
    {
        this.name = name;
        this.price = price;
        this.category = category;
        this.available = available;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }
    public boolean isAvailable() { return available; }

    public void setPrice(double price) { this.price = price; }
    public void setAvailability(boolean available) { this.available = available; }

    @Override
    public String toString()
    {
        return "FoodItem{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", available=" + available +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        FoodItem foodItem = (FoodItem) obj;
        return name.equalsIgnoreCase(foodItem.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name.toLowerCase());
    }
}