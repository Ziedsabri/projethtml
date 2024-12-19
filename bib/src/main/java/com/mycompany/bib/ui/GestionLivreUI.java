/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bib.ui;

import com.mycompany.bib.models.Livre;
import com.mycompany.bib.services.LivreService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestionLivreUI extends JFrame {

    private LivreService livreService;

    public GestionLivreUI() {
        // Set up the frame
        setTitle("Gestion Livre");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        livreService = new LivreService();

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));

        JButton ajouterButton = new JButton("Ajouter Livre");
        JButton modifierButton = new JButton("Modifier Livre");
        JButton supprimerButton = new JButton("Supprimer Livre");
        JButton afficherButton = new JButton("Afficher Livres");

        panel.add(ajouterButton);
        panel.add(modifierButton);
        panel.add(supprimerButton);
        panel.add(afficherButton);

        add(panel);

        ajouterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Prompt the user for the Livre details
                String titre = JOptionPane.showInputDialog(null, "Enter Livre Titre:");
                String auteur = JOptionPane.showInputDialog(null, "Enter Livre Auteur:");
                String anneeString = JOptionPane.showInputDialog(null, "Enter Livre Annee (Year):");
                String genre = JOptionPane.showInputDialog(null, "Enter Livre Genre:");

                try {
                    int annee = Integer.parseInt(anneeString);  // Convert the input string to an integer
                    if (titre != null && auteur != null && genre != null) {
                        Livre livre = new Livre(titre, auteur, annee, genre); // Create new Livre object
                        livreService.ajouterLivre(livre);
                        JOptionPane.showMessageDialog(null, "Livre added successfully!");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid year!");
                }
            }
        });

        modifierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idString = JOptionPane.showInputDialog(null, "Enter Livre ID to modify:");
                try {
                    int id = Integer.parseInt(idString);
                    Livre livre = livreService.chercherLivre(id);
                    if (livre != null) {
                        // Prompt for new fields
                        String newTitre = JOptionPane.showInputDialog(null, "Enter new Titre:", livre.getTitre());
                        String newAuteur = JOptionPane.showInputDialog(null, "Enter new Auteur:", livre.getAuteur());
                        String newAnneeString = JOptionPane.showInputDialog(null, "Enter new Annee (Year):", livre.getAnnee());
                        String newGenre = JOptionPane.showInputDialog(null, "Enter new Genre:", livre.getGenre());

                        try {
                            int newAnnee = Integer.parseInt(newAnneeString); // Convert the input string to an integer
                            livre.setTitre(newTitre);
                            livre.setAuteur(newAuteur);
                            livre.setAnnee(newAnnee);
                            livre.setGenre(newGenre);

                            livreService.modifierLivre(livre);
                            JOptionPane.showMessageDialog(null, "Livre updated successfully!");
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Please enter a valid year!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Livre not found!");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Livre ID!");
                }
            }
        });

        supprimerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idString = JOptionPane.showInputDialog(null, "Enter Livre ID to delete:");
                try {
                    int id = Integer.parseInt(idString);
                    livreService.supprimerLivre(id);
                    JOptionPane.showMessageDialog(null, "Livre deleted successfully!");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Livre ID!");
                }
            }
        });

        afficherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder livresList = new StringBuilder("List of Livres:\n");
                for (Livre livre : livreService.listerLivres()) {
                    livresList.append("ID: ").append(livre.getId())
                            .append(", Titre: ").append(livre.getTitre())
                            .append(", Auteur: ").append(livre.getAuteur())
                            .append(", Annee: ").append(livre.getAnnee())
                            .append(", Genre: ").append(livre.getGenre()).append("\n");
                }
                JOptionPane.showMessageDialog(null, livresList.toString());
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GestionLivreUI();
            }
        });
    }
}
