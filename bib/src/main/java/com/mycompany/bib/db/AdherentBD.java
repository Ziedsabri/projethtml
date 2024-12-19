/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bib.db;

import com.mycompany.bib.models.Adherent;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdherentBD {

    private DatabaseConnection dbConnection;

    public AdherentBD() {
        dbConnection = new DatabaseConnection();
    }

    // Ajouter un adhérent
    public boolean ajouterAdherent(Adherent adherent) {
        String requete = "INSERT INTO adherents (cin, nom, prenom, email, numTel) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(requete)) {

            preparedStatement.setString(1, adherent.getcin());
            preparedStatement.setString(2, adherent.getNom());
            preparedStatement.setString(3, adherent.getPrenom());
            preparedStatement.setString(4, adherent.getEmail());
            preparedStatement.setString(5, adherent.getNumTel());

            int lignesAffectees = preparedStatement.executeUpdate();
            return lignesAffectees > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Modifier un adhérent
    public boolean modifierAdherent(Adherent adherent) {
        String requete = "UPDATE adherents SET nom = ?, prenom = ?, email = ?, numTel = ? WHERE cin = ?";
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(requete)) {

            preparedStatement.setString(1, adherent.getNom());
            preparedStatement.setString(2, adherent.getPrenom());
            preparedStatement.setString(3, adherent.getEmail());
            preparedStatement.setString(4, adherent.getNumTel());
            preparedStatement.setString(5, adherent.getcin());

            int lignesAffectees = preparedStatement.executeUpdate();
            return lignesAffectees > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Supprimer un adhérent
    public boolean supprimerAdherent(String cin) {
        String requete = "DELETE FROM adherents WHERE cin = ?";
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(requete)) {

            preparedStatement.setString(1, cin);
            int lignesAffectees = preparedStatement.executeUpdate();
            return lignesAffectees > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Obtenir tous les adhérents
    public List<Adherent> obtenirTousLesAdherents() {
        List<Adherent> adherents = new ArrayList<>();
        String requete = "SELECT * FROM adherents";
        try (Connection connection = dbConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(requete)) {

            while (resultSet.next()) {
                String cin = resultSet.getString("cin");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String email = resultSet.getString("email");
                String numTel = resultSet.getString("numTel");

                Adherent adherent = new Adherent(cin, nom, prenom, email, numTel);
                adherents.add(adherent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adherents;
    }

    // Obtenir un adhérent par son CIN
    public Adherent obtenirAdherentParCin(String cin) {
        Adherent adherent = null;
        String requete = "SELECT * FROM adherents WHERE cin = ?";
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(requete)) {

            preparedStatement.setString(1, cin);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String nom = resultSet.getString("nom");
                    String prenom = resultSet.getString("prenom");
                    String email = resultSet.getString("email");
                    String numTel = resultSet.getString("numTel");

                    adherent = new Adherent(cin, nom, prenom, email, numTel);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adherent;
    }
}

