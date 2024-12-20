package models;

import java.io.Serializable;
import java.util.Map;

public abstract class User implements Systeminterface, Serializable
{
    private final String email;
    private final String password;
    private final String type;
    private final String name;
    private static final long serialVersionUID = 1L;

    public User(String name, String email, String password, String type)
    {
        this.name = name;
        this.email = email;
        this.password = password;
        this.type = type;
    }

    public String getEmail()
    {
        return email;
    }

    public boolean authenticate(String password, String type)
    {
        return this.password.equals(password) && this.type.equals(type);
    }

    public String getPassword()
    {
        return password;
    }

    public String getType()
    {
        return type;
    }

    public abstract void showMenu();

    protected String getName() {
        return name;
    }

    public abstract Map<FoodItem, Integer> getCartMap();

    public String getUserId() {
        return email;
    }
}