package billing.management.system;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Home extends JFrame {

    JLabel totalProductsLabel, totalCustomersLabel;
    JLabel totalProductsIconLabel, totalCustomersIconLabel;

    Home() {
        setLayout(null);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        SideMenu sideMenu = new SideMenu();
        sideMenu.setBounds(0, 0, 300, height);
        add(sideMenu);

        JLabel welcome = new JLabel("Welcome to Billing Management System");
        welcome.setBounds(400, 40, 600, 50);
        welcome.setFont(new Font("Tahoma", Font.PLAIN, 24));
        add(welcome);

        JPanel totalProductsPanel = new JPanel();
        totalProductsPanel.setBounds(400, 120, 250, 100);
        totalProductsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        totalProductsPanel.setLayout(null);

        ImageIcon totalProductsIcon = new ImageIcon(ClassLoader.getSystemResource("icons/product.png"));
        Image totalProductsImg = totalProductsIcon.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        totalProductsIcon = new ImageIcon(totalProductsImg);
        totalProductsIconLabel = new JLabel(totalProductsIcon);
        totalProductsIconLabel.setBounds(10, 25, 50, 50);
        totalProductsPanel.add(totalProductsIconLabel);

        totalProductsLabel = new JLabel("Total Products: ");
        totalProductsLabel.setBounds(70, 25, 170, 50);
        totalProductsPanel.add(totalProductsLabel);

        add(totalProductsPanel);

        JPanel totalCustomersPanel = new JPanel();
        totalCustomersPanel.setBounds(700, 120, 250, 100);
        totalCustomersPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        totalCustomersPanel.setLayout(null);

        // Load the customer icon
        ImageIcon totalCustomersIcon = new ImageIcon(ClassLoader.getSystemResource("icons/customer.png"));
        Image totalCustomersImg = totalCustomersIcon.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        totalCustomersIcon = new ImageIcon(totalCustomersImg);
        totalCustomersIconLabel = new JLabel(totalCustomersIcon);
        totalCustomersIconLabel.setBounds(10, 25, 50, 50);
        totalCustomersPanel.add(totalCustomersIconLabel);

        totalCustomersLabel = new JLabel("Total Customers: ");
        totalCustomersLabel.setBounds(70, 25, 170, 50);
        totalCustomersPanel.add(totalCustomersLabel);

        add(totalCustomersPanel);

        // Fetch total products and customers
        fetchTotalProducts();
        fetchTotalCustomers();

        // Add the sales chart panel
        JPanel chartPanel = createSalesChartPanel();
        chartPanel.setBounds(400, 250, 550, 350);
        add(chartPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void fetchTotalProducts() {
        try {
            Conn c = new Conn();
            String query = "select count(*) as total from product";
            ResultSet rs = c.s.executeQuery(query);
            if (rs.next()) {
                int totalProducts = rs.getInt("total");
                totalProductsLabel.setText("Total Products: " + totalProducts);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void fetchTotalCustomers() {
        try {
            Conn c = new Conn();
            String query = "select count(*) as total from customer";
            ResultSet rs = c.s.executeQuery(query);
            if (rs.next()) {
                int totalCustomers = rs.getInt("total");
                totalCustomersLabel.setText("Total Customers: " + totalCustomers);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private JPanel createSalesChartPanel() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Fetch sales data from the history table
        try {
            Conn c = new Conn();
            String query = "SELECT date, SUM(total) as sales FROM history GROUP BY date";
            ResultSet rs = c.s.executeQuery(query);

            while (rs.next()) {
                String date = rs.getString("date");
                double sales = rs.getDouble("sales");
                dataset.addValue(sales, "Sales", date);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Create a bar chart
        JFreeChart barChart = ChartFactory.createBarChart(
                "Sales History",
                "Date",
                "Sales (in Rs.)",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false
        );

        // Customize the plot
        CategoryPlot plot = barChart.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, Color.BLUE); // Set the color of the bars to blue

        // Wrap the chart in a panel
        return new ChartPanel(barChart);
    }

    public static void main(String[] args) {
        new Home();
    }
}
