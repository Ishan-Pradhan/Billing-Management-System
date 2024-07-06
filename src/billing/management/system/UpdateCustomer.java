/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package billing.management.system;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

public class UpdateCustomer extends JFrame {
    JLabel customer, customerIdLabel, cusNameLabel, addressLabel, emailLabel, phoneLabel;
    JTextField idField, nameField, addressField, emailField, phoneField;

    public UpdateCustomer(String customerId, String cusName, String address, String email, String phone) {
        setLayout(null);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        // SideMenu (Assuming this is a custom component)
        SideMenu sideMenu = new SideMenu();
        sideMenu.setBounds(0, 0, 300, height);
        add(sideMenu);

        int centerX = (width / 2) - 200;

        customer = new JLabel("Update Customer");
        customer.setBounds(centerX, 80, 400, 30);
        customer.setHorizontalAlignment(JLabel.CENTER);
        customer.setFont(new Font("", Font.BOLD, 32));
        add(customer);

        customerIdLabel = new JLabel("Customer ID");
        customerIdLabel.setBounds(centerX, 140, 100, 30);
        add(customerIdLabel);

        idField = new JTextField(customerId);
        idField.setBounds(centerX + 120, 140, 200, 30);
        idField.setEditable(false); // Assuming customer ID cannot be edited
        add(idField);

        cusNameLabel = new JLabel("Customer Name");
        cusNameLabel.setBounds(centerX, 180, 100, 30);
        add(cusNameLabel);

        nameField = new JTextField(cusName);
        nameField.setBounds(centerX + 120, 180, 200, 30);
        add(nameField);

        addressLabel = new JLabel("Address");
        addressLabel.setBounds(centerX, 220, 100, 30);
        add(addressLabel);

        addressField = new JTextField(address);
        addressField.setBounds(centerX + 120, 220, 200, 30);
        add(addressField);

        emailLabel = new JLabel("Email");
        emailLabel.setBounds(centerX, 260, 100, 30);
        add(emailLabel);

        emailField = new JTextField(email);
        emailField.setBounds(centerX + 120, 260, 200, 30);
        add(emailField);

        phoneLabel = new JLabel("Phone");
        phoneLabel.setBounds(centerX, 300, 100, 30);
        add(phoneLabel);

        phoneField = new JTextField(phone);
        phoneField.setBounds(centerX + 120, 300, 200, 30);
        add(phoneField);

        JButton updateButton = new JButton("Update");
        updateButton.setBounds(centerX, 350, 100, 30);
        add(updateButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.setBounds(centerX + 120, 350, 100, 30);
        add(deleteButton);

        // Action listener for Update button (to update customer data)
        updateButton.addActionListener(e -> {
            String updatedCustomerId = idField.getText().trim();
            String updatedCusName = nameField.getText().trim();
            String updatedAddress = addressField.getText().trim();
            String updatedEmail = emailField.getText().trim();
            String updatedPhone = phoneField.getText().trim();

            try {
                Conn c = new Conn();
                String query = "UPDATE customer SET customerName='" + updatedCusName + "', address='" + updatedAddress + "', email='" + updatedEmail + "', phone='" + updatedPhone + "' WHERE customerId='" + updatedCustomerId + "'";
                
                int rowsUpdated = c.s.executeUpdate(query);

                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(this, "Customer updated successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to update customer!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
            setVisible(false);
            new Customer();

        
        });
        
        // Action listener for Delete button (to delete customer record)
        deleteButton.addActionListener(e -> {
            String deleteCustomerId = idField.getText().trim();

            // Perform delete operation using normal query
            try {
                Conn c = new Conn();
                String query = "DELETE FROM customer WHERE customerId='" + deleteCustomerId + "'";

                int rowsDeleted = c.s.executeUpdate(query);

                if (rowsDeleted > 0) {
                    JOptionPane.showMessageDialog(this, "Customer deleted successfully!");
                    setVisible(false);
                    new Customer();
                   
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to delete customer!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(width, height);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        // Example usage:
        String customerId = "1";
        String cusName = "John Doe";
        String address = "123 Main St";
        String email = "john.doe@example.com";
        String phone = "123-456-7890";
        
        new UpdateCustomer(customerId, cusName, address, email, phone);
    }
}
