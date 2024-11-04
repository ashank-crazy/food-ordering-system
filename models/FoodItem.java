package models;

public class FoodItem
{
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

}