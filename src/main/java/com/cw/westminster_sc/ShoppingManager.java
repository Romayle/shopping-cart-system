package com.cw.westminster_sc;

public interface ShoppingManager {
    public void addNewProduct(Product product);
    public void deleteProduct(String id);
    public void printProductList();
    public void saveTofile();

}
