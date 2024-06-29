package billing.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Home extends JFrame {

    Home() {
        setLayout(null);

         SideMenu sideMenu = new SideMenu();
        sideMenu.setBounds(0, 0, 300, 630);
        add(sideMenu);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1120, 630);
        setLocation(150, 50);
        setVisible(true);

    }

    public static void main(String[] args) {
        new Home();
    }

}
