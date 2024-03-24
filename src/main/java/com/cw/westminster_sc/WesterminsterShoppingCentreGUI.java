package com.cw.westminster_sc;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.*;

public class WesterminsterShoppingCentreGUI extends JFrame {
    private static Product selectedProduct;

    public WesterminsterShoppingCentreGUI() {
        super("Westminster Shopping Centre");

        // Main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(25, 25));

        // North part of main panel
        // North part of main panel with two rows
        JPanel northPanel = new JPanel(new GridLayout(2, 1));

        // Row 1: Shopping Cart button on the far right
        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton shoppingCartButton = new JButton("Shopping Cart");
        row1.add(shoppingCartButton);
        northPanel.add(row1);

        // Row 2: Text "Selection"
        JPanel row2 = new JPanel();
        row2.setLayout(new GridLayout(1, 3));

        // column1 in row2
        JPanel column1 = new JPanel();
        column1.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JLabel label21 = new JLabel("Select Product Category");
        column1.add(label21);
        row2.add(column1);

        // column2 in row2
        JPanel column2 = new JPanel();
        column2.setLayout(new FlowLayout(FlowLayout.CENTER));
        JComboBox comboBox22 = new JComboBox<>(new String[] { "All", "Electronics", "Clothing" });
        column2.add(comboBox22);
        row2.add(column2);

        // cloumn3 in row2
        row2.add(new JPanel());

        northPanel.add(row2);

        mainPanel.add(northPanel, BorderLayout.NORTH);

        // southernPanel at the bottom with BorderLayout
        JPanel southernPanel = new JPanel(new BorderLayout(40, 40));

        southernPanel.add(new JPanel(), BorderLayout.WEST);
        southernPanel.add(new JPanel(), BorderLayout.EAST);

        // middle of newPanel divided into two columns
        JPanel middlePanel = new JPanel(new GridLayout(1, 2));

        // first column in middlePanel
        JLabel label31 = new JLabel();
        middlePanel.add(label31);

        // Second column empty in the middlePanel
        middlePanel.add(new JPanel());

        southernPanel.add(middlePanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout());
        JButton addToCartButton = new JButton("Add to Shopping Cart");
        bottomPanel.add(addToCartButton);

        southernPanel.add(bottomPanel, BorderLayout.SOUTH);
        mainPanel.add(southernPanel, BorderLayout.SOUTH);

        // CENTER of main panel with BorderLayout

        ArrayList<Product> products = WestminsterShoppingManager.getSystemProducts();
        String dataArray[][] = displayDataOnTable(products, comboBox22);
        String columnName[] = { "ProductID", "Name", "Category", "Price", "Qty" };

        JTable table = new JTable();
        TableModel model = new DefaultTableModel(dataArray, columnName);

        table.setModel(model);
        JScrollPane scrollPane = new JScrollPane(table);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                // check if the row selection is valid and is not adjusting to prevent multiple
                if (!event.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                    int selectedRow = table.getSelectedRow();
                    String productId = (String) table.getModel().getValueAt(selectedRow, 0);
                    selectedProduct = getProductById(productId);
                    if (selectedProduct != null) {
                        System.out.println("\nSelected Product: " + selectedProduct);
                        String labelText = "<html> <h3>Selected product:</h3> Product ID: "
                                + selectedProduct.getProductID() + "<br/>" +
                                "Product Name: " + selectedProduct.getProductName() + "<br/>" +
                                "Product Category: " + selectedProduct.getClass().getSimpleName() + "<br/>" +
                                "Product Price: " + selectedProduct.getPrice();
                        if (selectedProduct.getClass().getSimpleName().equals("Electronics")) {
                            Electronics elecProduct = (Electronics) selectedProduct;
                            labelText += "<br/>" + "Product Brand : " + elecProduct.getBrand() + "<br/>" +
                                    "Warranty Period : " + elecProduct.getPeriod();
                        } else if (selectedProduct.getClass().getSimpleName().equals("Clothing")) {
                            Clothing clothProduct = (Clothing) selectedProduct;
                            labelText += "<br/>" + "Cloth Size : " + clothProduct.getSize() + "<br/>" +
                                    "Cloth Color : " + clothProduct.getColour();
                        }
                        labelText += "</html>";
                        label31.setText(labelText);

                    } else {
                        System.out.println("No product found with ID: " + productId);
                    }
                }
            }
        });

        // WEST of main panel with BorderLayout
        mainPanel.add(new JPanel(), BorderLayout.WEST);

        // EAST of main panel with BorderLayout
        mainPanel.add(new JPanel(), BorderLayout.EAST);

        add(mainPanel);

        shoppingCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the Register frame
                ShoppingCartGUI shoppingCart = new ShoppingCartGUI();

            }
        });

        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add the selected product to the shopping cart
                int selectedRow = table.getSelectedRow();

                if (selectedProduct != null) {
                    String productId = (String) table.getModel().getValueAt(selectedRow, 0);
                    Product selectedProduct = getProductById(productId);
                    updateQty(productId);
                    String dataArray[][] = displayDataOnTable(products, comboBox22);
                    table.setModel(new DefaultTableModel(dataArray, columnName));
                    ShoppingCart.addProduct(selectedProduct);
                    System.out.println("\nSelected product: " + selectedProduct);
                } else if (selectedRow != -1) {
                    System.out.println("No product Selected");
                } else {
                    System.out.println("No product for ID: ");
                }
            }
        });

        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                int qty = Integer.parseInt((String) table.getModel().getValueAt(row, 4));
                if (qty < 3) {
                    c.setBackground(Color.RED);
                } else {
                    c.setBackground(table.getBackground());
                }
                return c;
            }
        });
        setSize(800, 600);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        comboBox22.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Action performed");
                String dataArray[][] = displayDataOnTable(products, comboBox22);
                table.setModel(new DefaultTableModel(dataArray, columnName));
            }
        });

    }

    public Product getProductById(String productId) {
        for (Product product : WestminsterShoppingManager.getSystemProducts()) {
            if (product.getProductID().equals(productId)) {
                return product;
            }
        }
        return null; // return null if no product with the matching ID is found
    }

    public void updateQty(String productId) {
        ArrayList<Product> products = WestminsterShoppingManager.getSystemProducts();
        for (Product product : products) {
            if (product.getProductID().equals(productId)) {
                product.setItemNumbersAvailable(product.getItemNumbersAvailable() - 1);
            }
        }
    }

    public String[][] displayDataOnTable(ArrayList<Product> products, JComboBox comboBox22) {
        String selectedCategory = (String) comboBox22.getSelectedItem();
        ArrayList<String[]> data = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if (selectedCategory.equals("All") || product.getClass().getSimpleName().equals(selectedCategory)) {
                String[] productData = new String[5];
                productData[0] = product.getProductID();
                productData[1] = product.getProductName();
                productData[2] = product.getClass().getSimpleName();
                productData[3] = String.valueOf(product.getPrice());
                productData[4] = String.valueOf(product.getItemNumbersAvailable());
                data.add(productData);
            }
        }
        String[][] dataArray = data.toArray(new String[0][0]);

        return dataArray; // return null if no product with the matching ID is found
    }

}
