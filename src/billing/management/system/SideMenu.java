package billing.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SideMenu extends JPanel {

    public SideMenu() {
        setLayout(null);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/ray.jpg"));
        Image i2 = i1.getImage().getScaledInstance(560, height, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 300, height);
        add(image);

        ImageIcon homeicon = new ImageIcon(ClassLoader.getSystemResource("icons/home.png"));
        Image homeicon2 = homeicon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        ImageIcon homeicon3 = new ImageIcon(homeicon2);

        ImageIcon customericon = new ImageIcon(ClassLoader.getSystemResource("icons/customer.png"));
        Image customericon2 = customericon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        ImageIcon customericon3 = new ImageIcon(customericon2);

        ImageIcon producticon = new ImageIcon(ClassLoader.getSystemResource("icons/product.png"));
        Image producticon2 = producticon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        ImageIcon producticon3 = new ImageIcon(producticon2);

        ImageIcon billingicon = new ImageIcon(ClassLoader.getSystemResource("icons/invoice.png"));
        Image billingicon2 = billingicon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        ImageIcon billingicon3 = new ImageIcon(billingicon2);

        ImageIcon historyicon = new ImageIcon(ClassLoader.getSystemResource("icons/file.png"));
        Image historyicon2 = historyicon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        ImageIcon historyicon3 = new ImageIcon(historyicon2);

        ImageIcon logouticon = new ImageIcon(ClassLoader.getSystemResource("icons/power-off.png"));
        Image logouticon2 = logouticon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        ImageIcon logouticon3 = new ImageIcon(logouticon2);
        
        

        JButton homeButton = new JButton(" Home", homeicon3);
        homeButton.setBounds(70, 160, 150, 40);
        image.add(homeButton);

        JButton customerButton = new JButton(" Customer", customericon3);
        customerButton.setBounds(70, 230, 150, 40);
        image.add(customerButton);

        JButton productButton = new JButton(" Product", producticon3);
        productButton.setBounds(70, 300, 150, 40);
        image.add(productButton);

        JButton billingButton = new JButton(" Billing", billingicon3);
        billingButton.setBounds(70, 370, 150, 40);
        image.add(billingButton);

        JButton historyButton = new JButton(" History", historyicon3);
        historyButton.setBounds(70, 440, 150, 40);
        image.add(historyButton);

        JButton logoutButton = new JButton(" Logout", logouticon3);
        logoutButton.setBounds(70, 510, 150, 40);
        image.add(logoutButton);
        
        homeButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                setVisible(false);
                new Home().setVisible(true);
            }
        });
        
        customerButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                setVisible(false);
                new Customer().setVisible(true);
            }
        });

        setPreferredSize(new Dimension(300, height));
        add(image);
    }
}
