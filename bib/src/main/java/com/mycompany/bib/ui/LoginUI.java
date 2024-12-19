package com.mycompany.bib.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginUI extends JFrame {

    // Constructor to set up the login screen
    public LoginUI() {
        // Set up the frame
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on the screen

        // Create the panel and set the layout
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10)); // Adding spacing between components

        // Create the components
        JLabel userLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        
        // Set smaller size for text fields
        JTextField userText = new JTextField(15);  // Limit width of JTextField
        JPasswordField passwordText = new JPasswordField(15);  // Limit width of JPasswordField
        JButton loginButton = new JButton("Login");

        // Add the components to the panel
        panel.add(userLabel);
        panel.add(userText);
        panel.add(passwordLabel);
        panel.add(passwordText);
        panel.add(new JLabel()); // Empty cell for spacing
        panel.add(loginButton);

        // Add the panel to the frame
        add(panel);

        // Action listener for the login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userText.getText();
                char[] password = passwordText.getPassword();

                // Check if the entered username and password match the correct ones
                if (username.equals("zied") && new String(password).equals("zied")) {
                    JOptionPane.showMessageDialog(null, "Login successful!");

                    // If credentials are correct, proceed to the next part of your program
                    dispose(); // Close the login window
                    new MainMenuUI(); // Open the main menu UI
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // Main method to start the login UI
    public static void main(String[] args) {
        // Make sure the Swing UI runs on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Create the login window and make it visible
                new LoginUI().setVisible(true);
            }
        });
    }
}
