package billing.management.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class Home extends JFrame {

    Home() {
        setLayout(null);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        SideMenu sideMenu = new SideMenu();
        System.out.print(height);
        sideMenu.setBounds(0, 0, 300, height);
        add(sideMenu);

        JLabel welcome = new JLabel("welcome");
        welcome.setBounds(400, 40, 500, 50);
        add(welcome);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setLocation(150, 50);
        setLocationRelativeTo(null);
        setVisible(true);
//        setResizable(false);

    }

    public static void main(String[] args) {
        new Home();
    }

}
