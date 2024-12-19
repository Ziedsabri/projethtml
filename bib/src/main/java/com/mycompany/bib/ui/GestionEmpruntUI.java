package com.mycompany.bib.ui;

import com.mycompany.bib.models.Emprunts;
import com.mycompany.bib.models.Livre;
import com.mycompany.bib.models.Adherent;
import com.mycompany.bib.services.EmpruntService;
import com.mycompany.bib.services.LivreService;
import com.mycompany.bib.services.AdherentService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GestionEmpruntUI extends JFrame {

    private EmpruntService empruntService;
    private LivreService livreService;
    private AdherentService adherentService;

    public GestionEmpruntUI() {
        // Set up the frame
        setTitle("Gestion Emprunt");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        empruntService = new EmpruntService();
        livreService = new LivreService();
        adherentService = new AdherentService();

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));

        JButton ajouterButton = new JButton("Ajouter Emprunt");
        JButton modifierButton = new JButton("Modifier Emprunt");
        JButton supprimerButton = new JButton("Supprimer Emprunt");
        JButton afficherButton = new JButton("Afficher Emprunts");

        panel.add(ajouterButton);
        panel.add(modifierButton);
        panel.add(supprimerButton);
        panel.add(afficherButton);

        add(panel);

        // Action listener to add Emprunt
        ajouterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Prompt the user for the Emprunt details
                String livreIdString = JOptionPane.showInputDialog(null, "Enter Livre ID:");
                String adherentCinString = JOptionPane.showInputDialog(null, "Enter Adherent CIN:");
                String dateEmpruntString = JOptionPane.showInputDialog(null, "Enter Emprunt Date (yyyy-MM-dd):");
                String dateRetourString = JOptionPane.showInputDialog(null, "Enter Retour Date (yyyy-MM-dd):");

                try {
                    int livreId = Integer.parseInt(livreIdString);
                    String adherentCin = adherentCinString; // CIN as String

                    Livre livre = livreService.chercherLivre(livreId);
                    Adherent adherent = adherentService.obtenirAdherentParCin(adherentCin);

                    if (livre != null && adherent != null) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Date dateEmprunt = sdf.parse(dateEmpruntString);
                        Date dateRetour = sdf.parse(dateRetourString);

                        Emprunts emprunt = new Emprunts(livre, adherent, dateEmprunt, dateRetour); // New Emprunt
                        empruntService.ajouterEmprunt(emprunt);
                        JOptionPane.showMessageDialog(null, "Emprunt added successfully!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid Livre or Adherent.");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Please enter valid information!");
                }
            }
        });

        // Action listener to modify Emprunt
        modifierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String empruntIdString = JOptionPane.showInputDialog(null, "Enter Emprunt ID to modify:");
                try {
                    int empruntId = Integer.parseInt(empruntIdString);
                    Emprunts emprunt = empruntService.obtenirEmpruntParId(empruntId);
                    if (emprunt != null) {
                        String newDateEmpruntString = JOptionPane.showInputDialog(null, "Enter new Emprunt Date (yyyy-MM-dd):", new SimpleDateFormat("yyyy-MM-dd").format(emprunt.getDateEmprunt()));
                        String newDateRetourString = JOptionPane.showInputDialog(null, "Enter new Retour Date (yyyy-MM-dd):", new SimpleDateFormat("yyyy-MM-dd").format(emprunt.getDateRetour()));

                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Date newDateEmprunt = sdf.parse(newDateEmpruntString);
                        Date newDateRetour = sdf.parse(newDateRetourString);

                        emprunt.setDateEmprunt(newDateEmprunt);
                        emprunt.setDateRetour(newDateRetour);

                        empruntService.modifierEmprunt(emprunt);
                        JOptionPane.showMessageDialog(null, "Emprunt updated successfully!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Emprunt not found!");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Emprunt ID or date format!");
                }
            }
        });

        // Action listener to delete Emprunt
        supprimerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String empruntIdString = JOptionPane.showInputDialog(null, "Enter Emprunt ID to delete:");
                try {
                    int empruntId = Integer.parseInt(empruntIdString);
                    empruntService.supprimerEmprunt(empruntId);
                    JOptionPane.showMessageDialog(null, "Emprunt deleted successfully!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Emprunt ID!");
                }
            }
        });

        // Action listener to display all Emprunts
        afficherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder empruntsList = new StringBuilder("List of Emprunts:\n");
                for (Emprunts emprunt : empruntService.obtenirTousLesEmprunts()) {
                    empruntsList.append("ID: ").append(emprunt.getId())
                            .append(", Livre: ").append(emprunt.getLivre().getTitre())
                            .append(", Adherent: ").append(emprunt.getAdherent().getNom())
                            .append(", Emprunt Date: ").append(new SimpleDateFormat("yyyy-MM-dd").format(emprunt.getDateEmprunt()))
                            .append(", Retour Date: ").append(new SimpleDateFormat("yyyy-MM-dd").format(emprunt.getDateRetour()))
                            .append("\n");
                }
                JOptionPane.showMessageDialog(null, empruntsList.toString());
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GestionEmpruntUI();
            }
        });
    }
}
