package com.cw.westminster_sc;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import javax.swing.*;

public class ShoppingCartGUI extends JFrame {
    public ShoppingCartGUI() {
        super("Shopping Cart");
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(20, 20));

        ArrayList<Product> cartItems = ShoppingCart.getCartItems();
        String data[][] = new String[cartItems.size()][3];
        for (int i = 0; i < cartItems.size(); i++) {
            Product product = cartItems.get(i);
            data[i][0] = product.getProductName();
            data[i][1] = "1";
            data[i][2] = String.valueOf(product.getPrice());
        }

        String columnName[] = { "Product", "Quantity", "Price" };

        JTable table = new JTable();
        TableModel model = new DefaultTableModel(data, columnName);
        table.setModel(model);
        JScrollPane scrollPane = new JScrollPane(table);
        mainPanel.add(new JPanel(), BorderLayout.NORTH);
        mainPanel.add(new JPanel(), BorderLayout.WEST);
        mainPanel.add(new JPanel(), BorderLayout.EAST);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(1, 3));
        southPanel.add(new JPanel());
        JPanel southPanelCenter = new JPanel();
        southPanelCenter.setLayout(new GridLayout(4, 1, 0, 10));
        southPanelCenter.add(new JLabel("Total"));

        southPanelCenter.add(new JLabel("First Purchase Discount (10%)"));

        southPanelCenter.add(new JLabel("Three Items in same Category Discount (20%)"));

        southPanelCenter.add(new JLabel("Final Total"));

        southPanel.add(southPanelCenter);
        JPanel southPanelRight = new JPanel();
        southPanelRight.setLayout(new GridLayout(4, 1, 0, 10));
        Double discount10 = ShoppingCart.getTotalPrice() * 0.1;
        Double discount20 = 0.0;

        southPanelRight.add(new JLabel("Rs." + ShoppingCart.getTotalPrice()));
        southPanelRight.add(new JLabel("Rs." + discount10));
        if (ShoppingCart.getCartItems().size() >= 3) {

            List<String> itemIDs = ShoppingCart.getCartItems().stream().map(product -> product.getClass().getSimpleName()).toList();

            boolean discount20Flag = itemIDs.stream()
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                    .values()
                    .stream()
                    .anyMatch(count -> count >= 3);
            ;

            if (discount20Flag) {
                discount20 = ShoppingCart.getTotalPrice() * 0.2;
            }

        }
        southPanelRight.add(new JLabel("Rs." + discount20));
        southPanelRight.add(new JLabel("Rs. " + (ShoppingCart.getTotalPrice() - (discount10+ discount20))));
        southPanel.add(southPanelRight);
        mainPanel.add(southPanel, BorderLayout.SOUTH);

        add(mainPanel);

        setSize(800, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only the Register frame
        setVisible(true);
    }
}
