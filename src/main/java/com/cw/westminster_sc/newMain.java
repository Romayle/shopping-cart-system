package com.cw.westminster_sc;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.Buffer;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.cw.westminster_sc.helper.HelperUtil;

public class newMain {
    static WestminsterShoppingManager manager;
    private static ShoppingCart cart;
    private static Scanner scanner;
    static boolean mainFlage = true;
    static boolean subFlag = true;

    public static void main(String[] args) {
        manager = new WestminsterShoppingManager();
        cart = new ShoppingCart();
        manager.loadFromFile();
        scanner = new Scanner(System.in);
        HelperUtil.setScanner(scanner);

        HelperUtil.printMsg(
                "Welcome to Westminster Shopping Center");

        while (true) {
            if(mainFlage == false){
                mainFlage=true;  // always reset the flag to acess sub menu
            }
            HelperUtil.printMsg("""
                    Menu
                    "1. Manager
                    "2. Customer
                    "3. Exit
                    """);
            System.out.print("Please Enter your Selection from above : ");
            try {
                int userSelection = scanner.nextInt();
                scanner.nextLine();
                switch (userSelection) {
                    case 1 -> managersOption();
                    case 2 -> customersOption();
                    case 3 -> System.exit(0);
                    default -> {
                        HelperUtil.printMsg("Please Enter a valid Integer for the given of 1 to 3 Range");
                        continue;
                    }

                }
            } catch (InputMismatchException e) {
                HelperUtil.printMsg("Please Enter a valid Integer");
                scanner.next();
                continue;
            }
        }

    }

    private static void customersOption() {
        System.out.println("""
            Welcome to the Customer Menu
            1. Login
            2. Register
            3. Go Back
            """);
            System.out.print("Please Enter your Selection from above : ");
            while(true){
                try {
                    int customerSelection = scanner.nextInt();
                    scanner.nextLine();
                    switch (customerSelection) {
                        case 1 -> customerLogin();
                        case 2 -> customerRegister();
                        case 3 -> System.exit(0);
                        default -> {
                            HelperUtil.printMsg("Please Enter a valid Integer for the given of 1 to 3 Range");
                            continue;
                        }
    
                    }
                } catch (InputMismatchException e) {
                    HelperUtil.printMsg("Please Enter a valid Integer");
                    scanner.next();
                    continue;
                }
            }
            
    }

    public static void customerLogin() {
        String loginUserName = HelperUtil.getStringInput("Enter the Username");
        String loginPassword = HelperUtil.getStringInput("Enter the Password");
        WesterminsterShoppingCentreGUI westerminsterShoppingCentreGUI = new WesterminsterShoppingCentreGUI();
    }

    public static void customerRegister() {
        String registerUserName = HelperUtil.getStringInput("Enter the Username");
        String registerPassword = HelperUtil.getStringInput("Enter the Password");
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("customer.txt", true));
            writer.append(registerUserName + " : " + registerPassword + "\n");
            writer.close();
        }catch(Exception e){
            System.out.println("Error : couldn't locae file");
            e.printStackTrace();
        }
        WesterminsterShoppingCentreGUI westerminsterShoppingCentreGUI = new WesterminsterShoppingCentreGUI();
    }

    public static void managersOption() {
        while (mainFlage) {
            if(subFlag == false) {
                subFlag=true;  // always reset the flag to acess sub menu
            }
            managersMenu();
            try {
                Scanner scanner = new Scanner(System.in);
                int selection = scanner.nextInt();
                scanner.nextLine();
                switch (selection) {
                    case 1 -> {
                        while (subFlag) {
                            
                            subMenu();
                            int subSelection = scanner.nextInt();
                            try {
                                switch (subSelection) {
                                    case 1 -> managerAddsElectronic();
                                    case 2 -> managerAddsClothing();
                                    case 3 -> subFlag = false;
                                    default -> HelperUtil.printMsg("Enter 1 2 or 3");
                                }
                            } catch (InputMismatchException e) {
                                HelperUtil.printMsg("Please Enter a valid Integer for the given of 1 to 3 Range");
                            }
                        }
                    }
                    case 2 -> {
                        String id = HelperUtil.getStringInput("Enter the Product ID to delete");
                        manager.deleteProduct(id);
                    }
                    case 3 -> manager.printProductList();
                    case 4 -> manager.saveTofile();
                    case 5 -> mainFlage = false;

                    default -> HelperUtil.printMsg("Enter 1 2 3 4 or 5");
                }
            } catch (InputMismatchException e) {
                HelperUtil.printMsg("Please Enter a valid Intege and not mixed characters");
            }
        }
    }

    public static void managersOptionV2() {

    }

    public static void managersMenu() {
        HelperUtil.printMsg(
                "What would you like to do:\n\n1. Add a new Product\n2. Delete a Product\n3. Print the list of the products\n4. Save in a file\n5. Go Back");
    }

    public static void subMenu() {
        HelperUtil.printMsg("\nSelect 1 for Electronics\nSelect 2 for Clothing\nSelect 3 to GO BACK\n");
    }

    public static void managerAddsElectronic() {
        Scanner scanner = new Scanner(System.in);

        // get inputs from user for cloth properties and to products
        String id = HelperUtil.getStringInput("Enter the Product ID");
        String name = HelperUtil.getStringInput("Enter the Product Name");
        Double price = HelperUtil.getDoubleInput("Enter the Product Price");
        Integer quantity = HelperUtil.getIntegerInput("Enter the Product Quantity");
        String brand = HelperUtil.getStringInput("Enter the Product Brand");
        String period = HelperUtil.getStringInput("Enter the Product Period");
        Electronics electronics = new Electronics(id, name, quantity, price, brand, period);
        manager.addNewProduct(electronics);

        if(subFlag == false) {
            subFlag=true;  // always reset the flag to acess sub menu
        }
    }

    public static void managerAddsClothing() {

        // get inputs from user for cloth properties and to products
        String id = HelperUtil.getStringInput("Enter the Product ID");
        String name = HelperUtil.getStringInput("Enter the Product Name");
        Double price = HelperUtil.getDoubleInput("Enter the Product Price");
        Integer quantity = HelperUtil.getIntegerInput("Enter the Product Quantity");
        Integer size = HelperUtil.getIntegerInput("Enter the Product Size");
        String colour = HelperUtil.getStringInput("Enter the Product Colour");
        Clothing clothing = new Clothing(id, name, quantity, price, size, colour);
        manager.addNewProduct(clothing);

        if(subFlag == false) {
            subFlag=true;  // always reset the flag to acess sub menu
        }
    }

}
