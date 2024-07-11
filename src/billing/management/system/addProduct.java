/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package billing.management.system;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import java.awt.Toolkit;
import java.util.Random;
import javax.swing.*;

public class addProduct extends JFrame {

    JLabel product,productId, productName, description, price, stock;
    JTextField idField, nameField, descriptionField, priceField, stockField;

    addProduct() {
       String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random ran = new Random();
        
        // Generate random text
        StringBuilder randomText = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            int randomIndex = ran.nextInt(characters.length());
            randomText.append(characters.charAt(randomIndex));
        }
        
        // Generate random number
        int randomNumber = ran.nextInt(9999);
        // Combine both parts
        String number = randomText.toString() + String.format("%04d", randomNumber);
        setLayout(null);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;
        
        //SideMenu
        SideMenu sideMenu = new SideMenu();
//        System.out.print(height);
        sideMenu.setBounds(0, 0, 300, height);
        add(sideMenu);
        
        int c = (width / 2) - 50;
        product = new JLabel("Add Product");
        product.setBounds(c, 80, 400, 30);
        product.setHorizontalAlignment(JLabel.CENTER);
        product.setFont(new Font("",Font.BOLD, 32));
        add(product);
        
        productId = new JLabel("Product id");
        productId.setBounds(400, 185, 100, 30);
        add(productId);

        idField = new JTextField(number);
        idField.setBounds(600, 185, 230, 30);
        add(idField);
        idField.setEditable(false);

        productName = new JLabel("Product name");
        productName.setBounds(400, 225, 100, 30);
        add(productName);

        nameField = new JTextField();
        nameField.setBounds(600, 225, 230, 30);
        add(nameField);

        description = new JLabel("Description");
        description.setBounds(400, 265, 100, 30);
        add(description);

        descriptionField = new JTextField();
        descriptionField.setBounds(600, 265, 230, 30);
        add(descriptionField);

        price = new JLabel("Price");
        price.setBounds(400, 305, 100, 30);
        add(price);

        priceField = new JTextField();
        priceField.setBounds(600, 305, 230, 30);
        add(priceField);

        stock = new JLabel("Stock");
        stock.setBounds(400, 345, 100, 30);
        add(stock);

        stockField = new JTextField();
        stockField.setBounds(600, 345, 230, 30);
        add(stockField);

        JButton addButton = new JButton("Add");
        addButton.setBounds(400, 450, 150, 30);
        add(addButton);

        JButton reset = new JButton("Reset");
        reset.setBounds(600, 450, 150, 30);
        add(reset);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                String productId = idField.getText();
                String productName = nameField.getText();
                String description = descriptionField.getText();
                String price = priceField.getText();
                String stock = stockField.getText();
                try{
                    Conn conn=new Conn();
                    String query="insert into product values('"+productId+"','"+productName+"','"+description+"','"+price+"','"+stock+"')";
                    conn.s.executeUpdate(query);
                    JOptionPane.showMessageDialog(null,"Product has been added");
                    setVisible(false);
                    new Product();
                }catch(Exception e){
                    e.printStackTrace();
                }

            }
        });
        
         reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                nameField.setText("");
                descriptionField.setText("");
                priceField.setText("");
                stockField.setText("");
                
            }
        });
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setLocation(150, 50);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public static void main(String[] args) {
        new addProduct();
    }
}
