package com.cw.westminster_sc;
import java.util.ArrayList;

public class ShoppingCart {
    private static ArrayList<Product> cartItems;
    private static double totalPrice = 0.0;

    public ShoppingCart() {
        cartItems = new ArrayList<Product>();
    }

    public static void addProduct(Product product) {
        cartItems.add(product);
        totalPrice += product.getPrice();
    }

    public void removeProduct(Product product) {
        for (Product p : cartItems) {
            if (p.getProductID().equals(product.getProductID())) {
                totalPrice -= product.getPrice();
                cartItems.remove(p);
            }
        }
    }

    public static ArrayList<Product> getCartItems() {
        return cartItems;
    }

    public static void setCartItems(ArrayList<Product> cartItems) {
        ShoppingCart.cartItems = cartItems;
    }

    public static double getTotalPrice() {
        return totalPrice;
    }

    public static void setTotalPrice(double totalPrice) {
        ShoppingCart.totalPrice = totalPrice;
    }

    
    
    
    
}
