package com.cw.westminster_sc;

public abstract class Product {
    protected String productID;
    protected String productName;
    protected int itemNumbersAvailable;
    protected double price;

    public Product(String id, String name, int availableNumber, double price) {
        this.productID = id;
        this.productName = name;
        this.itemNumbersAvailable = availableNumber;
        this.price = price;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getItemNumbersAvailable() {
        return itemNumbersAvailable;
    }

    public void setItemNumbersAvailable(int itemNumbersAvailable) {
        this.itemNumbersAvailable = itemNumbersAvailable;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProductID() {
        return productID;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product \nproductID=" + productID + "\nproductName=" + productName + "\nitemNumbersAvailable="
                + itemNumbersAvailable + "\n price=" + price;
    }

}
