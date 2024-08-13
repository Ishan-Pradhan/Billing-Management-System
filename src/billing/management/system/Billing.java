package billing.management.system;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.*;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.swing.*;
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
        try {
            // Retrieve and parse values from the text fields
            int price = Integer.parseInt(priceField.getText());
            int quantity = Integer.parseInt(quantityField.getText());
            int totalPrice = price * quantity;

            // Get selected values from the choices
            String customerName = custname.getSelectedItem();
            String productName = prodname.getSelectedItem();

            // Add a new row to the table
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.addRow(new Object[]{customerName, productName, price, quantity, totalPrice});

            // Calculate the new total
            int rowCount = model.getRowCount();
            int currentTotal = 0;
            for (int i = 0; i < rowCount; i++) {
                Object value = model.getValueAt(i, 4); // Column index 4 for "Total"
                if (value instanceof Number) {
                    currentTotal += ((Number) value).intValue();
                }
            }
            totalField.setText(String.valueOf(currentTotal));

            // Calculate the final amount considering the discount
            int discountPercent = discountAmtField.getText().isEmpty() ? 0 : Integer.parseInt(discountAmtField.getText());
            double discountAmount = (discountPercent / 100.0) * currentTotal;
            double finalAmount = currentTotal - discountAmount;
            finalAmtField.setText(String.format("%.2f", finalAmount));
        } catch (NumberFormatException ex) {
            // Handle the case where parsing fails
            JOptionPane.showMessageDialog(null, "Please enter valid numbers for price and quantity.");
        }
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
        scrollPane.setBounds(400, 500, 440, 180);
        add(scrollPane);

        //Calculation detail
        total = new JLabel("Total");
        total.setBounds(900, 500, 100, 30);
        add(total);

        totalField = new JTextField();
        totalField.setBounds(1100, 500, 230, 30);
        add(totalField);

        discountAmt = new JLabel("Discount");
        discountAmt.setBounds(900, 540, 100, 30);
        add(discountAmt);

        discountAmtField = new JTextField();
        discountAmtField.setBounds(1100, 540, 100, 30);
        add(discountAmtField);

        JButton disButton = new JButton("calculate");
        disButton.setBounds(1220, 540, 110, 30);
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
        finalAmt.setBounds(900, 580, 100, 30);
        add(finalAmt);

        finalAmtField = new JTextField();
        finalAmtField.setBounds(1100, 580, 230, 30);
        add(finalAmtField);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setLocationRelativeTo(null);
        setVisible(true);

        JButton print = new JButton("Print");
        print.setBounds(1100, 620, 100, 30);
        add(print);
        
print.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        String name = custname.getSelectedItem();
        String address = addressField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        String total = totalField.getText();
        String discount = discountAmtField.getText();
        String finalAmount = finalAmtField.getText();

        String path = "D://";
        com.itextpdf.text.Document doc = new com.itextpdf.text.Document();
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(path + name + ".pdf"));
            doc.open();

            Paragraph title = new Paragraph("Billing Management System",
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, Font.BOLD, BaseColor.BLACK));
            title.setAlignment(Element.ALIGN_CENTER);
            doc.add(title);

            doc.add(new Paragraph("\n"));

            Paragraph dateTime = new Paragraph("Date: " + curdateField.getText() + "  |  Time: " + curHourField.getText(),
                    FontFactory.getFont(FontFactory.HELVETICA, 12));
            dateTime.setAlignment(Element.ALIGN_RIGHT);
            doc.add(dateTime);

            doc.add(new Paragraph("\n"));

            Paragraph buyerDetails = new Paragraph("Buyer Details:\n"
                    + "Name: " + name + "\n"
                    + "Address: " + address + "\n"
                    + "Email: " + email + "\n"
                    + "Phone: " + phone,
                    FontFactory.getFont(FontFactory.HELVETICA, 12));
            doc.add(buyerDetails);

            doc.add(new Paragraph("\n"));

            PdfPTable pdfTable = new PdfPTable(5); // 5 columns

            pdfTable.addCell(new PdfPCell(new Paragraph("Product Name", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12))));
            pdfTable.addCell(new PdfPCell(new Paragraph("Quantity", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12))));
            pdfTable.addCell(new PdfPCell(new Paragraph("Price", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12))));
            pdfTable.addCell(new PdfPCell(new Paragraph("Total", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12))));
            pdfTable.addCell(new PdfPCell(new Paragraph("Discount", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12))));

            DefaultTableModel model = (DefaultTableModel) table.getModel();
            int totalAmount = 0;
            StringBuilder allProducts = new StringBuilder();
            for (int i = 0; i < model.getRowCount(); i++) {
                String productName = model.getValueAt(i, 1).toString(); // Product Name
                String quantityStr = model.getValueAt(i, 3).toString(); // Quantity
                String priceStr = model.getValueAt(i, 2).toString(); // Price
                int quantityInt = Integer.parseInt(quantityStr);
                int priceInt = Integer.parseInt(priceStr);
                int totalItems = quantityInt * priceInt;
                totalAmount += totalItems;

                // Format product name with quantity
                String formattedProductName = productName + "(" + quantityInt + ")";
                allProducts.append(formattedProductName).append(", ");

                pdfTable.addCell(formattedProductName);
                pdfTable.addCell(quantityStr);
                pdfTable.addCell(priceStr);
                pdfTable.addCell(String.valueOf(totalItems));
                pdfTable.addCell(""); // Discount cell will be empty in the table
            }

            // Add accumulated product details as a single cell
            pdfTable.addCell(allProducts.toString().replaceAll(", $", ""));
            pdfTable.addCell(""); // Empty cell for quantity
            pdfTable.addCell(""); // Empty cell for price
            pdfTable.addCell(String.valueOf(totalAmount));
            pdfTable.addCell(""); // Empty cell for discount

            int discountPercent = discountAmtField.getText().isEmpty() ? 0 : Integer.parseInt(discountAmtField.getText());
            double discountAmount = (discountPercent / 100.0) * totalAmount;
            double finalAmt = totalAmount - discountAmount;

            pdfTable.addCell("Discount:");
            pdfTable.addCell("");
            pdfTable.addCell("");
            pdfTable.addCell(String.valueOf(discountAmount));
            pdfTable.addCell("");

            pdfTable.addCell("Final Amount:");
            pdfTable.addCell("");
            pdfTable.addCell("");
            pdfTable.addCell(String.format("%.2f", finalAmt));
            pdfTable.addCell("");

            doc.add(pdfTable);

            doc.add(new Paragraph("\n"));

            Paragraph thankYou = new Paragraph("Thank you for your purchase!",
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12));
            thankYou.setAlignment(Element.ALIGN_CENTER);
            doc.add(thankYou);

            doc.close();

            // Update product stock and history
            Conn c = new Conn();
            for (int i = 0; i < model.getRowCount(); i++) {
                String productName = model.getValueAt(i, 1).toString();
                int quantityInt = Integer.parseInt(model.getValueAt(i, 3).toString());

                String updateProductQuery = "UPDATE product SET stock = stock - " + quantityInt +
                        " WHERE productName = '" + productName + "'";
                c.s.executeUpdate(updateProductQuery);
            }

            // Insert into history with all products in one entry
            String historyQuery = "INSERT INTO history (customerName, productName, total, date) VALUES ('"
                    + name + "', '" + allProducts.toString().replaceAll(", $", "") + "', "
                    + totalAmount + ", '"
                    + curdateField.getText() + "')";
            c.s.executeUpdate(historyQuery);

            JOptionPane.showMessageDialog(null, "Bill Generated Successfully");
            setVisible(true);

        } catch (Exception ee) {
            JOptionPane.showMessageDialog(null, "Error generating bill: " + ee.getMessage());
        }
    }
});

    }

    public static void main(String[] args) {
        new Billing();
    }
}
