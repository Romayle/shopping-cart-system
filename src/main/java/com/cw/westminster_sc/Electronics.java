package com.cw.westminster_sc;

public class Electronics extends Product{
    private String brand;
    private String period;

    public Electronics(String id, String name, int availableNumber, double price, String brand, String period) {
        super(id, name, availableNumber, price);
        this.brand = brand;
        this.period = period;
    }

    

    public String getBrand() {
        return brand;
    }



    public void setBrand(String brand) {
        this.brand = brand;
    }



    public String getPeriod() {
        return period;
    }



    public void setPeriod(String period) {
        this.period = period;
    }



    @Override
    public String toString() {
        return "Electronics \nproductID=" + productID + "\nproductName=" + productName + "\nitemNumbersAvailable="
        + itemNumbersAvailable + "\nprice=" + price + "\nbrand=" + brand + "\nperiod=" + period;
    }

    
}
