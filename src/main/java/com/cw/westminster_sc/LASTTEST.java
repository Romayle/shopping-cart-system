package com.cw.westminster_sc;

import java.awt.*;
import javax.swing.*;

public class LASTTEST extends JFrame {
    public LASTTEST() {
        super("Westminster Shopping Centre");
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 1));

        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(2, 1));
        JPanel panel11 = new JPanel();
        panel11.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton button1 = new JButton("Shopping Cart");
        panel11.add(button1);
        JPanel panel12 = new JPanel();
        panel12.setLayout(new GridLayout(1, 3, 50, 0));
        JPanel panel121 = new JPanel();
        panel121.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JPanel panel122 = new JPanel();
        panel122.setLayout(new FlowLayout(FlowLayout.LEFT));
        JPanel panel123 = new JPanel();
        panel12.add(panel121);
        JLabel label21 = new JLabel("Select Product Category");
        panel121.add(label21);
        JComboBox comboBox21 = new JComboBox<>(new String[] { "All", "Electronics", "Clothing" });
        panel122.add(comboBox21);
        panel12.add(panel122);
        panel12.add(panel123);
        panel1.add(panel11);
        panel1.add(panel12);
        mainPanel.add(panel1);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel2.add(new JLabel("Table"));
        mainPanel.add(panel2);

        JPanel panel3 = new JPanel();
        panel3.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 0));
        JPanel panel31 = new JPanel();
        panel31.setLayout(new GridLayout(7, 1, 0, 10));
        JLabel label31 = new JLabel("Select Product - Details");
        panel31.add(label31);
        JLabel label32 = new JLabel("Product Id: B001");
        panel31.add(label32);
        JLabel label33 = new JLabel("Category: Clothing");
        panel31.add(label33);
        JLabel label34 = new JLabel("Name: White Shirt");
        panel31.add(label34);
        JLabel label35 = new JLabel("Size: S");
        panel31.add(label35);
        JLabel label36 = new JLabel("Colour: White");
        panel31.add(label36);
        JLabel label37 = new JLabel("Items Available: 6");
        panel31.add(label37);
        panel3.add(panel31);
        JPanel panel32 = new JPanel();
        panel3.add(panel32);
        mainPanel.add(panel3);

        // JPanel panel4 = new JPanel();
        JPanel panel4 = new JPanel(new BorderLayout());
        panel4.setPreferredSize(new Dimension(0, 1000));

        JButton button2 = new JButton("Add to Shopping Cart");
        JPanel panel41 = new JPanel();
    
        panel41.add(button2);
        
        
        panel4.add(panel41, BorderLayout.SOUTH);
        mainPanel.add(panel4);

        panel1.setBackground(Color.BLUE);
        panel2.setBackground(Color.YELLOW);
        panel3.setBackground(Color.RED);
        panel4.setBackground(Color.GREEN);

        add(mainPanel);
    }
}
