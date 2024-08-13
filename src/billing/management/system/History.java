package billing.management.system;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class History extends JFrame {

    JLabel fromDateLabel, toDateLabel;
    JTextField fromDateField, toDateField;
    JButton searchButton;
    JTable historyTable;

    public History() {
        setTitle("Purchase History");

        setLayout(null);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        SideMenu sideMenu = new SideMenu();
        sideMenu.setBounds(0, 0, 300, height);
        add(sideMenu);

        JLabel title = new JLabel("Purchase History");
        title.setBounds(500, 50, 400, 40);
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setHorizontalAlignment(JLabel.CENTER);
        add(title);

        fromDateLabel = new JLabel("From Date (dd-MM-yyyy):");
        fromDateLabel.setBounds(400, 150, 200, 30);
        add(fromDateLabel);

        fromDateField = new JTextField();
        fromDateField.setBounds(600, 150, 150, 30);
        add(fromDateField);

        toDateLabel = new JLabel("To Date (dd-MM-yyyy):");
        toDateLabel.setBounds(800, 150, 200, 30);
        add(toDateLabel);

        toDateField = new JTextField();
        toDateField.setBounds(1000, 150, 150, 30);
        add(toDateField);

        searchButton = new JButton("Search");
        searchButton.setBounds(1200, 150, 100, 30);
        add(searchButton);

        // Automatically set the current date in the date fields
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date currentDate = new Date();
        String formattedDate = dateFormat.format(currentDate);
        fromDateField.setText(formattedDate);
        toDateField.setText(formattedDate);

        // Set up the table to display the history
        historyTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(historyTable);
        scrollPane.setBounds(400, 200, 900, 400);
        add(scrollPane);

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String fromDate = fromDateField.getText();
                String toDate = toDateField.getText();

                try {
                    DefaultTableModel model = new DefaultTableModel(new String[]{"Customer Name", "Products", "Total Amount", "Date"}, 0);
                    historyTable.setModel(model);

                    Conn con = new Conn();
                    String query = "SELECT * FROM history WHERE STR_TO_DATE(date, '%d-%m-%Y') BETWEEN STR_TO_DATE('" + fromDate + "', '%d-%m-%Y') AND STR_TO_DATE('" + toDate + "', '%d-%m-%Y')";
                    ResultSet rs = con.s.executeQuery(query);

                    while (rs.next()) {
                        String customerName = rs.getString("customerName");
                        String productName = rs.getString("productName");
                        String total = rs.getString("total");
                        String date = rs.getString("date");

                        model.addRow(new Object[]{customerName, productName, total, date});
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Please enter valid dates.");
                }
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new History();
    }
}
