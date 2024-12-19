/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package com.mycompany.bib.db;

import com.mycompany.bib.models.Emprunts;
import com.mycompany.bib.models.Livre;
import com.mycompany.bib.models.Adherent;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpruntBD {

    private DatabaseConnection dbConnection;

    public EmpruntBD() {
        dbConnection = new DatabaseConnection();
    }

    public boolean ajouterEmprunt(Emprunts emprunt) {
        String requete = "INSERT INTO emprunts (id,idl,cin, dateEmprunt, dateRetour) VALUES (?, ?,?, ?, ?)";
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(requete)) {
            preparedStatement.setInt(1, emprunt.getId());
            preparedStatement.setInt(2, emprunt.getLivre().getId());
            preparedStatement.setString(3, emprunt.getAdherent().getcin());
            preparedStatement.setDate(4, new java.sql.Date(emprunt.getDateEmprunt().getTime()));
            preparedStatement.setDate(5, new java.sql.Date(emprunt.getDateRetour().getTime()));

            int lignesAffectees = preparedStatement.executeUpdate();
            return lignesAffectees > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean modifierEmprunt(Emprunts emprunt) {
        String requete = "UPDATE emprunts SET idl = ?, cin = ?, dateEmprunt = ?, dateRetour = ? WHERE id = ?";
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(requete)) {

            preparedStatement.setInt(1, emprunt.getLivre().getId());
            preparedStatement.setString(2, emprunt.getAdherent().getcin());
            preparedStatement.setDate(3, new java.sql.Date(emprunt.getDateEmprunt().getTime()));
            preparedStatement.setDate(4, new java.sql.Date(emprunt.getDateRetour().getTime()));
            preparedStatement.setInt(5, emprunt.getId());

            int lignesAffectees = preparedStatement.executeUpdate();
            return lignesAffectees > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean supprimerEmprunt(int id) {
        String requete = "DELETE FROM emprunts WHERE id = ?";
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(requete)) {

            preparedStatement.setInt(1, id);
            int lignesAffectees = preparedStatement.executeUpdate();
            return lignesAffectees > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Emprunts> obtenirTousLesEmprunts() {
        List<Emprunts> emprunts = new ArrayList<>();
        String requete = "SELECT * FROM emprunts";
        try (Connection connection = dbConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(requete)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int livreId = resultSet.getInt("idl");  // Assuming that livreId is stored in the `id` column.
                String adherentCin = resultSet.getString("cin");
                Date dateEmprunt = resultSet.getDate("dateEmprunt");
                Date dateRetour = resultSet.getDate("dateRetour");

                // Retrieve the livre and adherent objects based on their respective IDs/CIN
                Livre livre = new Livre(livreId, "Title", "Author", 2024, "Fiction");
                Adherent adherent = new Adherent(adherentCin, "John", "Doe", "john.doe@example.com", "123456789");

                Emprunts emprunt = new Emprunts(livre, adherent, dateEmprunt, dateRetour);
                emprunts.add(emprunt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emprunts;
    }

    public Emprunts obtenirEmpruntParId(int id) {
        Emprunts emprunt = null;
        String requete = "SELECT * FROM emprunts WHERE id = ?";
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(requete)) {

            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int livreId = resultSet.getInt("idl");
                    String adherentCin = resultSet.getString("cin");
                    Date dateEmprunt = resultSet.getDate("dateEmprunt");
                    Date dateRetour = resultSet.getDate("dateRetour");

                    Livre livre = new Livre(livreId, "Title", "Author", 2024, "Fiction");
                    Adherent adherent = new Adherent(adherentCin, "John", "Doe", "john.doe@example.com", "123456789");

                    emprunt = new Emprunts( livre, adherent, dateEmprunt, dateRetour);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emprunt;
    }
}
