package billing.management.system;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame {

    JTextField tfusername;
    JPasswordField tfpassword;

    Login() {
        setTitle("Admin login");
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/login icon.jpg"));
        Image i2 = i1.getImage().getScaledInstance(290, 320, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(350, 50, 300, 300);
        add(image);

        
        
        JLabel MainTitle = new JLabel("Billing Management System");
        MainTitle.setBounds(140, 10, 400, 30);
        MainTitle.setHorizontalAlignment(JLabel.CENTER);
        MainTitle.setFont(new Font("",Font.BOLD, 20));
        add(MainTitle);
        
        JLabel LoginTitle = new JLabel("LOGIN");
        LoginTitle.setBounds(40, 80, 100, 30);
        LoginTitle.setFont(new Font("",Font.BOLD, 20));
        add(LoginTitle);
        
        JLabel lblusername = new JLabel("Username");
        lblusername.setBounds(40, 115, 100, 30);
        add(lblusername);

        tfusername = new JTextField();
        tfusername.setBounds(40, 145, 230, 30);
        add(tfusername);

        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(40, 185, 100, 30);
        add(lblpassword);

        tfpassword = new JPasswordField();
        tfpassword.setBounds(40, 215, 230, 30);
        add(tfpassword);

        JButton login = new JButton("Login");
        login.setBounds(40, 270, 100, 30);
        add(login);

        login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                try {
                    String username = tfusername.getText();
                    String password = tfpassword.getText();

                    String query = "select * from login where username='" + username + "' and password='" + password + "'";

                    Conn c = new Conn();
                    ResultSet rs = c.s.executeQuery(query);
                    if (rs.next()) {
                     setVisible(false);
                     new Home();
                        //next class
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid username or password");
//                setVisible(false);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        JButton close = new JButton("Close");
        close.setBounds(170, 270, 100, 30);
        add(close);

        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                setVisible(false);
            }
        });

        setSize(700, 400);
        setLocation(450, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }

    public static void main(String args[]) {
         try {
            // Set JTattoo Look and Feel
            UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Login();
        
    }
}