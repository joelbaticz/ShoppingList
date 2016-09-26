package com.example.joel.shoppinglist;

public class ShoppingItem {

    private long id;
    private String name;
    private int quantity;
    private boolean isPurchased;

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public boolean isPurchased()
    {
        return isPurchased;
    }

    public void setPurchased(boolean purchased)
    {
        isPurchased = purchased;
    }

    public ShoppingItem(String name, int quantity, boolean isPurchased)
    {
        this.name=name;
        this.quantity=quantity;
        this.isPurchased=isPurchased;
    }
}
