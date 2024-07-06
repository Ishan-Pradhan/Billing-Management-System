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

public class AddCustomer extends JFrame {

    JLabel customer,customerId, cusName, address, email, phone;
    JTextField idField, nameField, addressField, emailField, phoneField;

    AddCustomer() {
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
        customer = new JLabel("Add Customer");
         customer.setBounds(c, 80, 400, 30);
        customer.setHorizontalAlignment(JLabel.CENTER);
        customer.setFont(new Font("",Font.BOLD, 32));
        add(customer);
        
        customerId = new JLabel("Customer id");
        customerId.setBounds(400, 185, 100, 30);
        add(customerId);

        idField = new JTextField(number);
        idField.setBounds(600, 185, 230, 30);
        add(idField);
        idField.setEditable(false);

        cusName = new JLabel("Customer name");
        cusName.setBounds(400, 225, 100, 30);
        add(cusName);

        nameField = new JTextField();
        nameField.setBounds(600, 225, 230, 30);
        add(nameField);

        address = new JLabel("Address");
        address.setBounds(400, 265, 100, 30);
        add(address);

        addressField = new JTextField();
        addressField.setBounds(600, 265, 230, 30);
        add(addressField);

        email = new JLabel("Email");
        email.setBounds(400, 305, 100, 30);
        add(email);

        emailField = new JTextField();
        emailField.setBounds(600, 305, 230, 30);
        add(emailField);

        phone = new JLabel("Phone");
        phone.setBounds(400, 345, 100, 30);
        add(phone);

        phoneField = new JTextField();
        phoneField.setBounds(600, 345, 230, 30);
        add(phoneField);

        JButton addButton = new JButton("Add");
        addButton.setBounds(400, 450, 150, 30);
        add(addButton);

        JButton reset = new JButton("Reset");
        reset.setBounds(600, 450, 150, 30);
        add(reset);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                String customerId = idField.getText();
                String cusName = nameField.getText();
                String address = addressField.getText();
                String email = emailField.getText();
                String phone = phoneField.getText();
                try{
                    Conn conn=new Conn();
                    String query="insert into customer values('"+customerId+"','"+cusName+"','"+address+"','"+email+"','"+phone+"')";
                    conn.s.executeUpdate(query);
                    JOptionPane.showMessageDialog(null,"Customer has been added");
                    setVisible(false);
                    new Customer();
                }catch(Exception e){
                    e.printStackTrace();
                }

            }
        });
        
         reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                nameField.setText("");
                addressField.setText("");
                emailField.setText("");
                phoneField.setText("");
                
            }
        });
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setLocation(150, 50);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public static void main(String[] args) {
        new AddCustomer();
    }
}
