/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package billing.management.system;

import java.awt.Image;
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

