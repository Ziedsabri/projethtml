package com.mycompany.bib.ui;

import com.mycompany.bib.models.Adherent;
import com.mycompany.bib.services.AdherentService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestionAdherentUI extends JFrame {

    private AdherentService adherentService;

    public GestionAdherentUI() {
        // Set up the frame
        setTitle("Gestion Adherent");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        adherentService = new AdherentService();

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));

        JButton ajouterButton = new JButton("Ajouter Adherent");
        JButton modifierButton = new JButton("Modifier Adherent");
        JButton supprimerButton = new JButton("Supprimer Adherent");
        JButton afficherButton = new JButton("Afficher Adherents");

        panel.add(ajouterButton);
        panel.add(modifierButton);
        panel.add(supprimerButton);
        panel.add(afficherButton);

        add(panel);

        // Action listener to add Adherent
        ajouterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Prompt the user for the Adherent details
                String cinString = JOptionPane.showInputDialog(null, "Enter Adherent CIN:");
                String nom = JOptionPane.showInputDialog(null, "Enter Adherent Nom:");
                String prenom = JOptionPane.showInputDialog(null, "Enter Adherent Prenom:");
                String email = JOptionPane.showInputDialog(null, "Enter Adherent Email:");
                String numTel = JOptionPane.showInputDialog(null, "Enter Adherent NumTel:");

                if (cinString != null && nom != null && prenom != null && email != null && numTel != null) {
                    // No parsing required as CIN is a String
                    Adherent adherent = new Adherent(cinString, nom, prenom, email, numTel); // Create new Adherent object
                    boolean t=adherentService.ajouterAdherent(adherent);
                    if(t){
                    JOptionPane.showMessageDialog(null, "Adherent added successfully!");
                    }
                    else{
                         JOptionPane.showMessageDialog(null, "Adherent not added successfully!");
                    }
                }
            }
        });

        // Action listener to modify Adherent
        modifierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cinString = JOptionPane.showInputDialog(null, "Enter Adherent CIN to modify:");
                if (cinString != null) {
                    Adherent adherent = adherentService.obtenirAdherentParCin(cinString);
                    if (adherent != null) {
                        // Prompt for new fields
                        String newNom = JOptionPane.showInputDialog(null, "Enter new Nom:", adherent.getNom());
                        String newPrenom = JOptionPane.showInputDialog(null, "Enter new Prenom:", adherent.getPrenom());
                        String newEmail = JOptionPane.showInputDialog(null, "Enter new Email:", adherent.getEmail());
                        String newNumTel = JOptionPane.showInputDialog(null, "Enter new NumTel:", adherent.getNumTel());

                        adherent.setNom(newNom);
                        adherent.setPrenom(newPrenom);
                        adherent.setEmail(newEmail);
                        adherent.setNumTel(newNumTel);

                        adherentService.modifierAdherent(adherent);
                        JOptionPane.showMessageDialog(null, "Adherent updated successfully!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Adherent not found!");
                    }
                }
            }
        });

        // Action listener to delete Adherent
        supprimerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cinString = JOptionPane.showInputDialog(null, "Enter Adherent CIN to delete:");
                if (cinString != null) {
                    adherentService.supprimerAdherent(cinString);
                    JOptionPane.showMessageDialog(null, "Adherent deleted successfully!");
                }
            }
        });

        // Action listener to display all Adherents
        afficherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder adherentsList = new StringBuilder("List of Adherents:\n");
                for (Adherent adherent : adherentService.obtenirTousLesAdherents()) {
                    adherentsList.append("CIN: ").append(adherent.getcin())
                            .append(", Nom: ").append(adherent.getNom())
                            .append(", Prenom: ").append(adherent.getPrenom())
                            .append(", Email: ").append(adherent.getEmail())
                            .append(", NumTel: ").append(adherent.getNumTel()).append("\n");
                }
                JOptionPane.showMessageDialog(null, adherentsList.toString());
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GestionAdherentUI();
            }
        });
    }
}
