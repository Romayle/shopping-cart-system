package com.cw.westminster_sc;

public class Clothing extends Product{
    private int size;
    private String colour;

    public Clothing(String id, String name, int availableNumber, double price, int size, String colour) {
        super(id, name, availableNumber, price);
        this.size = size;
        this.colour = colour;
    }

    

    public int getSize() {
        return size;
    }



    public void setSize(int size) {
        this.size = size;
    }



    public String getColour() {
        return colour;
    }



    public void setColour(String colour) {
        this.colour = colour;
    }



    @Override
    public String toString() {
        return "Clothing \nproductID=" + productID + "\nproductName=" + productName + "\nitemNumbersAvailable="
        + itemNumbersAvailable + "\nprice=" + price + "\nsize=" + size + "\ncolour=" + colour;
    }

    
}
