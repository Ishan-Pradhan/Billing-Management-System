/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package billing.management.system;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class Product extends JFrame {
    public String ProductId, productName, description, price, stock;
    public JTable table;

    public Product() {
        setLayout(null);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        // SideMenu
        SideMenu sideMenu = new SideMenu();
        sideMenu.setBounds(0, 0, 300, height);
        add(sideMenu);
        
        // Search components
        JLabel searchLabel = new JLabel("Search:");
        searchLabel.setBounds(400, 40, 60, 30);
        add(searchLabel);

        JTextField searchField = new JTextField();
        searchField.setBounds(480, 40, 150, 30);
        add(searchField);

        JButton searchButton = new JButton("Search by ID");
        searchButton.setBounds(650, 40, 120, 30);
        add(searchButton);

        JButton searchByNameButton = new JButton("Search by Name");
        searchByNameButton.setBounds(770, 40, 140, 30);
        add(searchByNameButton);

//         Fetch data from database
        ArrayList<Object[]> dataList = new ArrayList<>();
        try {
            Conn c = new Conn();
            String query = "select * from product";
            ResultSet rs = c.s.executeQuery(query);
            while (rs.next()) {
                ProductId = rs.getString("productId");
                productName = rs.getString("productName");
                description = rs.getString("description");
                price = rs.getString("price");
                stock = rs.getString("stock");
                dataList.add(new Object[]{ProductId, productName, description, price,stock});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        // Convert list to array
        Object[][] data = new Object[dataList.size()][];
        data = dataList.toArray(data);

        // Column names
        String[] columns = {"Product ID", "Name", "Description", "Price", "Stock"};

        // Create a table model
        DefaultTableModel model = new DefaultTableModel(data, columns);

        table = new JTable(model); // Initializing table
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(400, 80, 900, 350);
        add(scrollPane);

        // Add selection listener to table
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        // Populate fields with selected customer data
                        ProductId = table.getValueAt(selectedRow, 0).toString();
                        productName = table.getValueAt(selectedRow, 1).toString();
                        description = table.getValueAt(selectedRow, 2).toString();
                        price = table.getValueAt(selectedRow, 3).toString();
                        stock = table.getValueAt(selectedRow, 4).toString();

                        // Open UpdateCustomer window with selected data
                        UpdateProduct updateProduct = new UpdateProduct(ProductId, productName, description, price, stock);
                        updateProduct.setVisible(true);
                    }
                }
            }
        });

        // Search by ID button action listener
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String searchId = searchField.getText().trim();
                searchProductsById(searchId);
            }
        });

        // Search by Name button action listener
        searchByNameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String searchName = searchField.getText().trim();
                searchProductsByName(searchName);
            }
        });

        JButton addProduct = new JButton("Add Product");
        addProduct.setBounds(400, 450, 150, 30);
        add(addProduct);
        
        addProduct.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new addProduct().setVisible(true);
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void searchProductsById(String ProductId) {
        // Perform search by customer ID logic
        try {
            Conn c = new Conn();
            String query = "select * from product where productId = '" + ProductId + "'";
            ResultSet rs = c.s.executeQuery(query);
            displaySearchResults(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchProductsByName(String productName) {
        // Perform search by customer name logic
        try {
            Conn c = new Conn();
            String query = "select * from product where productName like '%" + productName + "%'";
            ResultSet rs = c.s.executeQuery(query);
            displaySearchResults(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displaySearchResults(ResultSet rs) throws SQLException {
        // Display search results in the table
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear existing rows

        while (rs.next()) {
            ProductId = rs.getString("productId");
            productName = rs.getString("productName");
            description = rs.getString("description");
            price = rs.getString("price");
            stock = rs.getString("stock");
            model.addRow(new Object[]{ProductId, productName, description, price, stock});
        }
    }

    public static void main(String[] args) {
        new Product();
    }
}
