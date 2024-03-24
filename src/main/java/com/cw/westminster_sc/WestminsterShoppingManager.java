package com.cw.westminster_sc;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.cw.westminster_sc.helper.HelperUtil;

public class WestminsterShoppingManager implements ShoppingManager {
    private static ArrayList<Product> systemProducts;
    

    public WestminsterShoppingManager() {
        systemProducts = new ArrayList<>();
    }

    public void addNewProduct(Product product) {
        if (systemProducts.size() < 50) {
            systemProducts.add(product);
        } else {
            HelperUtil.printMsg("The system is full");
        }
    } 

    public void deleteProduct(String id) {
        if (systemProducts.size() < 1) {
            HelperUtil.printMsg("The system is already empty");
        }else {
            for (Product p : systemProducts) {
                if (p.getProductID().equals(id)) {
                    p.toString();
                    systemProducts.remove(p);
                    HelperUtil.printMsg("Remaining Prodcuts in the System: " + systemProducts.size());
                    break;
                }
            }

            // if the product is not in the system msg will be shown
            HelperUtil.printMsg("Product with ID : " + id + " , is not in the system");
        }
    } 

    public void printProductList() {
        //sort all prodcts by product ID
        systemProducts.sort((p1, p2) -> p1.getProductID().compareTo(p2.getProductID()));
        
        //print all products
        HelperUtil.printMsg("All Products in the System");
        for (Product p : systemProducts) {
            System.out.println(p.toString());
            System.out.println("---------------------------------------------------");
        }
        
    } 

    public void saveTofile() {
        HelperUtil.printMsg("Saving all Products to the file");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("products.txt"));
            for (Product product : systemProducts) {
                writer.write(product.toString());
                writer.newLine();
                writer.newLine();
            }
            HelperUtil.printMsg("All Products are saved to the file");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile() {
        HelperUtil.printMsg("Loading all Products from the file");
        try {
            BufferedReader reader = new BufferedReader(new FileReader("products.txt"));
            String line = "";
            while((line = reader.readLine()) != null){
                line = line.strip();
                if(line == ""){
                    continue;
                }
                else if(line.equals("Electronics")){
                    String id = reader.readLine().split("=")[1].strip();
                    String name = reader.readLine().split("=")[1].strip();
                    int availableNumber = Integer.parseInt(reader.readLine().split("=")[1].strip());
                    double price = Double.parseDouble(reader.readLine().split("=")[1].strip());
                    String brand = reader.readLine().split("=")[1].strip();
                    String warranty = reader.readLine().split("=")[1].strip();
                    Electronics electronic = new Electronics(id, name, availableNumber, price, brand, warranty);
                    systemProducts.add(electronic);
                }else if(line.equals("Clothing")){
                    String id = reader.readLine().split("=")[1].strip();
                    String name = reader.readLine().split("=")[1].strip();
                    int availableNumber = Integer.parseInt(reader.readLine().split("=")[1].strip());
                    double price = Double.parseDouble(reader.readLine().split("=")[1].strip());
                    String size = reader.readLine().split("=")[1].strip();
                    String color = reader.readLine().split("=")[1].strip();
                    Clothing clothing = new Clothing(id, name, availableNumber, price, Integer.parseInt(size), color);
                    systemProducts.add(clothing);
                }
            }
            HelperUtil.printMsg("All Products are loaded from file");
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Product> getSystemProducts() {
        return systemProducts;
    }

    
}
