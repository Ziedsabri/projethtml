package com.mycompany.bib.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenuUI extends JFrame {

    public MainMenuUI() {
        // Set up the frame
        setTitle("Main Menu");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create the panel and set the layout
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));

        // Create buttons for each menu option
        JButton gestionAdherentButton = new JButton("Gestion Adherent");
        JButton gestionLivreButton = new JButton("Gestion Livre");
        JButton gestionEmpruntButton = new JButton("Gestion Emprunt");

        // Add buttons to the panel
        panel.add(gestionAdherentButton);
        panel.add(gestionLivreButton);
        panel.add(gestionEmpruntButton);

        // Add the panel to the frame
        add(panel);

        // Add action listeners for each button
        gestionAdherentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GestionAdherentUI();
                dispose();
            }
        });

        gestionLivreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GestionLivreUI();
                dispose();
            }
        });

        gestionEmpruntButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GestionEmpruntUI();
                dispose();
            }
        });

        // Make the window visible
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainMenuUI();
            }
        });
    }
}
