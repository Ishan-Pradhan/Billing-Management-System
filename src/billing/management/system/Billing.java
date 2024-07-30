package billing.management.system;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.*;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class Billing extends JFrame {

    JLabel curdate, curdateField, curHour, curHourField, billing, customerId, cusName, address, email, phone, productId, productName, description, price, quantity, total, discountAmt, finalAmt;
    JTextField idField, nameField, addressField, emailField, phoneField, pidField, pnameField, pdescriptionField, priceField, quantityField, totalField, discountAmtField, finalAmtField;
    public JTable table;
    Choice custname, prodname;

    public Billing() {

        //for current date
        curdate = new JLabel("Date: ");
        curdate.setBounds(1100, 85, 100, 30);
        add(curdate);

        curdateField = new JLabel();
        curdateField.setBounds(1150, 85, 100, 30);
        add(curdateField);

        curHour = new JLabel("Time: ");
        curHour.setBounds(1100, 100, 100, 30);
        add(curHour);

        curHourField = new JLabel();
        curHourField.setBounds(1150, 100, 100, 30);
        add(curHourField);

        SimpleDateFormat dFormat = new SimpleDateFormat("dd-MM-YYYY");
        Date date = new Date();
        curdateField.setText(dFormat.format(date));

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        curHourField.setText(dtf.format(now));

        setLayout(null);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        SideMenu sideMenu = new SideMenu();
        sideMenu.setBounds(0, 0, 300, height);
        add(sideMenu);

        int c = (width / 2) - 50;
        billing = new JLabel("Billing");
        billing.setBounds(c, 50, 400, 40);
        billing.setHorizontalAlignment(JLabel.CENTER);
        billing.setFont(new Font("", Font.BOLD, 32));
        add(billing);

        cusName = new JLabel("Customer name");
        cusName.setBounds(400, 185, 100, 30);
        add(cusName);

        custname = new Choice();
        custname.setBounds(600, 185, 130, 30);
        add(custname);
        // for customer name to be seen in choice 
        try {
            Conn con = new Conn();
            String query = "select * from customer";
            ResultSet rs = con.s.executeQuery(query);

            while (rs.next()) {
                custname.add(rs.getString("customerName"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        //button right after customer name choice
        JButton addBtn = new JButton("Enter");
        addBtn.setBounds(750, 185, 80, 20);
        add(addBtn);

        addBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Conn c = new Conn();
                    String query = "SELECT * FROM customer WHERE customerName = '" + custname.getSelectedItem() + "'";
                    ResultSet rs = c.s.executeQuery(query);
                    while (rs.next()) {
                        idField.setText(rs.getString("customerId"));
                        addressField.setText(rs.getString("Address"));
                        phoneField.setText(rs.getString("Phone"));
                        emailField.setText(rs.getString("Email"));
                    }
                } catch (SQLException ae) {
                    ae.printStackTrace();
                }

            }
        });

//        nameField = new JTextField();
//        nameField.setBounds(600, 185, 230, 30);
//        add(nameField);
        customerId = new JLabel("Customer id");
        customerId.setBounds(400, 235, 100, 30);
        add(customerId);

        idField = new JTextField();
        idField.setBounds(600, 235, 230, 30);
        add(idField);
        idField.setEditable(false);

        address = new JLabel("Address");
        address.setBounds(400, 285, 100, 30);
        add(address);

        addressField = new JTextField();
        addressField.setBounds(600, 285, 230, 30);
        add(addressField);
        addressField.setEditable(false);

        phone = new JLabel("Phone");
        phone.setBounds(400, 335, 100, 30);
        add(phone);

        phoneField = new JTextField();
        phoneField.setBounds(600, 335, 230, 30);
        add(phoneField);
        phoneField.setEditable(false);

        email = new JLabel("Email");
        email.setBounds(400, 385, 100, 30);
        add(email);

        emailField = new JTextField();
        emailField.setBounds(600, 385, 230, 30);
        add(emailField);
        emailField.setEditable(false);

        //Product field
        productName = new JLabel("Product name");
        productName.setBounds(900, 185, 100, 30);
        add(productName);

        prodname = new Choice();
        prodname.setBounds(1100, 185, 130, 30);
        add(prodname);

        // for product name to be seen in choice 
        try {
            Conn con = new Conn();
            String query = "select * from product";
            ResultSet rs = con.s.executeQuery(query);

            while (rs.next()) {
                prodname.add(rs.getString("productName"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        JButton productaddBtn = new JButton("Enter");
        productaddBtn.setBounds(1250, 185, 80, 20);
        add(productaddBtn);

        productaddBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Conn c = new Conn();
                    String query = "SELECT * FROM product WHERE productName = '" + prodname.getSelectedItem() + "'";
                    ResultSet rs = c.s.executeQuery(query);
                    while (rs.next()) {
                        pidField.setText(rs.getString("productId"));
                        priceField.setText(rs.getString("price"));
                        pdescriptionField.setText(rs.getString("description"));
                        quantityField.setText("1");
                    }
                } catch (SQLException ae) {
                    ae.printStackTrace();
                }

            }
        });

//        pnameField = new JTextField();
//        pnameField.setBounds(1100, 185, 230, 30);
//        add(pnameField);
        productId = new JLabel("Product id");
        productId.setBounds(900, 235, 100, 30);
        add(productId);

        pidField = new JTextField();
        pidField.setBounds(1100, 235, 230, 30);
        add(pidField);
        pidField.setEditable(false);

        price = new JLabel("Price");
        price.setBounds(900, 285, 100, 30);
        add(price);

        priceField = new JTextField();
        priceField.setBounds(1100, 285, 230, 30);
        add(priceField);
        priceField.setEditable(false);

        description = new JLabel("Description");
        description.setBounds(900, 335, 100, 30);
        add(description);

        pdescriptionField = new JTextField();
        pdescriptionField.setBounds(1100, 335, 230, 30);
        add(pdescriptionField);
        pdescriptionField.setEditable(false);

        quantity = new JLabel("Quantity");
        quantity.setBounds(900, 385, 100, 30);
        add(quantity);

        quantityField = new JTextField();
        quantityField.setBounds(1100, 385, 230, 30);
        add(quantityField);

        //table add button
        JButton addButton = new JButton("Add");
        addButton.setBounds(400, 450, 150, 30);
        add(addButton);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int price = Integer.parseInt(priceField.getText());
                int quantity = Integer.parseInt(quantityField.getText());

                int totalprice = price * quantity;

                String Name = custname.getSelectedItem();
                String Product = prodname.getSelectedItem();
                int Price = price;
                int Quantity = quantity;
                int Total = totalprice;

                //Table 
                ArrayList<Object[]> dataList = new ArrayList<>();
                Object[][] data = new Object[dataList.size()][];
                data = dataList.toArray(data);

                // Column names
                String[] columns = {"Name", "Product", "Price", "Quantity", "Total"};

                // Create a table model
                DefaultTableModel model = new DefaultTableModel(data, columns);

                table = new JTable(model); // Initializing table
                model.addRow(new Object[]{Name, Product, price, Quantity, Total});
                totalField.setText("" + Total);
                JScrollPane scrollPane = new JScrollPane(table);
//        scrollPane.setBounds(500,80,900,350);
                scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                scrollPane.setBounds(400, 500, 600, 280);
                add(scrollPane);

            }
        });

        ArrayList<Object[]> dataList = new ArrayList<>();
        Object[][] data = new Object[dataList.size()][];
//        data = dataList.toArray(data);

        // Column names
        String[] columns = {"Name", "Product", "Price", "Quantity", "Total"};

        // Create a table model
        DefaultTableModel model = new DefaultTableModel(data, columns);

        table = new JTable(model); // Initializing table
        JScrollPane scrollPane = new JScrollPane(table);
//        scrollPane.setBounds(500,80,900,350);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(400, 500, 600, 280);
        add(scrollPane);

        //Calculation detail
        total = new JLabel("Total");
        total.setBounds(1100, 550, 100, 30);
        add(total);

        totalField = new JTextField();
        totalField.setBounds(1200, 550, 230, 30);
        add(totalField);

        discountAmt = new JLabel("Discount");
        discountAmt.setBounds(1100, 615, 100, 30);
        add(discountAmt);

        discountAmtField = new JTextField();
        discountAmtField.setBounds(1200, 615, 100, 30);
        add(discountAmtField);

        JButton disButton = new JButton("calculate");
        disButton.setBounds(1320, 615, 110, 30);
        add(disButton);

        disButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int discountpercent = Integer.parseInt(discountAmtField.getText());

                int totalamt = Integer.parseInt(totalField.getText());
                double disamt = (discountpercent / 100.0) * totalamt;
                System.out.println("" + disamt);
                double finalamount = (totalamt - disamt);

                finalAmtField.setText(String.format("%.3f", finalamount));
            }
        });

        finalAmt = new JLabel("Final Amount");
        finalAmt.setBounds(1100, 680, 100, 30);
        add(finalAmt);

        finalAmtField = new JTextField();
        finalAmtField.setBounds(1200, 680, 230, 30);
        add(finalAmtField);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setLocationRelativeTo(null);
        setVisible(true);

        JButton print = new JButton("Print");
        print.setBounds(1100, 670, 130, 30);
        add(print);
        print.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = custname.getSelectedItem();
                String address = addressField.getText();
                String email = emailField.getText();
                String phone = phoneField.getText();
                String productname = prodname.getSelectedItem();
                String quantity = quantityField.getText();
                String total = totalField.getText();
                String discount = discountAmtField.getText();
                String finalAmount = finalAmtField.getText();

                String path = "D://";
                com.itextpdf.text.Document doc = new com.itextpdf.text.Document();
                try {
                    PdfWriter.getInstance(doc, new FileOutputStream(path + "" + name + ".pdf"));
                    doc.open();
                    Paragraph paragraph1 = new Paragraph("                 Billing Management System\n             ");
                    doc.add(paragraph1);
                    Paragraph paragraph2 = new Paragraph("Date & Time: " + curdateField.getText() + "  " + curHourField.getText() + " \n Buyer Details:\n Name: " + name + "\n Address " + address + " \n Email " + email + " \n Phone " + phone + " \n Product Name " + productname + "\n  Quantity " + quantity + "\n  Total " + total + "\n  Discount " + discount + "\n  Final Amount " + finalAmount);
                    doc.add(paragraph2);

                    JOptionPane.showMessageDialog(null, "Bill Generated");
                    setVisible(true);

                } catch (Exception ee) {
                    JOptionPane.showMessageDialog(null, e);
                }
                doc.close();
            }
        });

        JButton reset = new JButton("Reset");
        reset.setBounds(1300, 750, 130, 30);
        add(reset);
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                discountAmtField.setText("");
                finalAmtField.setText("");

            }
        });
    }

    public static void main(String[] args) {
        new Billing();
    }
}
