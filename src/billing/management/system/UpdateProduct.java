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

public class UpdateProduct extends JFrame {
    JLabel product, productIdLabel, productNameLabel, descriptionLabel, priceLabel, stockLabel;
    JTextField idField, nameField, descriptionField, priceField, stockField;

    public UpdateProduct(String productId, String cusName, String address, String email, String phone) {
        setLayout(null);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        // SideMenu (Assuming this is a custom component)
        SideMenu sideMenu = new SideMenu();
        sideMenu.setBounds(0, 0, 300, height);
        add(sideMenu);

        int centerX = (width / 2) - 200;

        product = new JLabel("Update Product");
        product.setBounds(centerX, 80, 400, 30);
        product.setHorizontalAlignment(JLabel.CENTER);
        product.setFont(new Font("", Font.BOLD, 32));
        add(product);

        productIdLabel = new JLabel("Product ID");
        productIdLabel.setBounds(centerX, 140, 100, 30);
        add(productIdLabel);

        idField = new JTextField(productId);
        idField.setBounds(centerX + 120, 140, 200, 30);
        idField.setEditable(false); // Assuming product ID cannot be edited
        add(idField);

        productNameLabel = new JLabel("Product Name");
        productNameLabel.setBounds(centerX, 180, 100, 30);
        add(productNameLabel);

        nameField = new JTextField(cusName);
        nameField.setBounds(centerX + 120, 180, 200, 30);
        add(nameField);

        descriptionLabel = new JLabel("Description");
        descriptionLabel.setBounds(centerX, 220, 100, 30);
        add(descriptionLabel);

        descriptionField = new JTextField(address);
        descriptionField.setBounds(centerX + 120, 220, 200, 30);
        add(descriptionField);

        priceLabel = new JLabel("Price");
        priceLabel.setBounds(centerX, 260, 100, 30);
        add(priceLabel);

        priceField = new JTextField(email);
        priceField.setBounds(centerX + 120, 260, 200, 30);
        add(priceField);

        stockLabel = new JLabel("Stock");
        stockLabel.setBounds(centerX, 300, 100, 30);
        add(stockLabel);

        stockField = new JTextField(phone);
        stockField.setBounds(centerX + 120, 300, 200, 30);
        add(stockField);

        JButton updateButton = new JButton("Update");
        updateButton.setBounds(centerX, 350, 100, 30);
        add(updateButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.setBounds(centerX + 120, 350, 100, 30);
        add(deleteButton);

        // Action listener for Update button (to update product data)
        updateButton.addActionListener(e -> {
            String updatedProductId = idField.getText().trim();
            String updatedProName = nameField.getText().trim();
            String updatedDescription = descriptionField.getText().trim();
            String updatedPrice = priceField.getText().trim();
            String updatedStock = stockField.getText().trim();

            try {
                Conn c = new Conn();
                String query = "UPDATE product SET product_name='" + updatedProName + "', product_description='" + updatedDescription + "', product_price='" + updatedPrice + "', product_stock='" + updatedStock + "' WHERE product_id='" + updatedProductId + "'";
                
                int rowsUpdated = c.s.executeUpdate(query);

                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(this, "Product updated successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to update product!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
            setVisible(false);
            new Product();

        
        });
        
        // Action listener for Delete button (to delete product record)
        deleteButton.addActionListener(e -> {
            String deleteProductId = idField.getText().trim();

            // Perform delete operation using normal query
            try {
                Conn c = new Conn();
                String query = "DELETE FROM product WHERE product_id='" + deleteProductId + "'";

                int rowsDeleted = c.s.executeUpdate(query);

                if (rowsDeleted > 0) {
                    JOptionPane.showMessageDialog(this, "Product deleted successfully!");
                    setVisible(false);
                    new Product();
                   
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to delete product!");
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
        String productId = "1";
        String cusName = "John Doe";
        String address = "123 Main St";
        String email = "john.doe@example.com";
        String phone = "123-456-7890";
        
        new UpdateProduct(productId, cusName, address, email, phone);
    }
}
