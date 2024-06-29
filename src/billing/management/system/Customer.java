/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package billing.management.system;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author isan_
 */
public class Customer extends JFrame {
    Customer(){
        setLayout(null);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        SideMenu sideMenu = new SideMenu();
        System.out.print(height);
        sideMenu.setBounds(0, 0, 300, height);
        add(sideMenu);

        JLabel welcome = new JLabel("welcome customer");
        welcome.setBounds(400, 40, 500, 50);
        add(welcome);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setLocation(150, 50);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public static void main(String[] args) {
        new Home();
    }
    }

