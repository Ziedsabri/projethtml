/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bib.db;

import com.mycompany.bib.models.Livre;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivreBD {

    // Utilisation de la classe DatabaseConnection pour obtenir la connexion à la base de données
    private DatabaseConnection dbConnection;

    public LivreBD() {
        dbConnection = new DatabaseConnection(); // Connexion à la base de données
    }

    // Ajouter un livre dans la base de données
    public boolean ajouterLivre(Livre livre) {
        String requete = "INSERT INTO livres (id,titre, auteur, annee, genre) VALUES (?,?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = dbConnection.getConnection(); // Obtenir la connexion de la classe DatabaseConnection
            preparedStatement = connection.prepareStatement(requete);
            preparedStatement.setInt(1, livre.getId());
            preparedStatement.setString(2, livre.getTitre());
            preparedStatement.setString(3, livre.getAuteur());
            preparedStatement.setInt(4, livre.getAnnee());
            preparedStatement.setString(5, livre.getGenre());  // Set genre

            int lignesAffectees = preparedStatement.executeUpdate();
            return lignesAffectees > 0;  // Retourne true si des lignes ont été affectées
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            // Fermeture sécurisée des ressources
            fermer(connection, preparedStatement, null);
        }
    }

    // Obtenir tous les livres de la base de données
    public List<Livre> obtenirTousLesLivres() {
        List<Livre> livres = new ArrayList<>();
        String requete = "SELECT * FROM livres";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = dbConnection.getConnection(); // Obtenir la connexion
            statement = connection.createStatement();
            resultSet = statement.executeQuery(requete);

            while (resultSet.next()) {
                Livre livre = new Livre(
                    resultSet.getInt("id"),
                    resultSet.getString("titre"),
                    resultSet.getString("auteur"),
                    resultSet.getInt("annee"),
                    resultSet.getString("genre")  // Get genre from the result set
                );
                livres.add(livre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fermeture sécurisée des ressources
            fermer(connection, statement, resultSet);
        }
        return livres;
    }

    // Obtenir un livre par son ID
    public Livre obtenirLivreParId(int id) {
        Livre livre = null;
        String requete = "SELECT * FROM livres WHERE id = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = dbConnection.getConnection(); // Obtenir la connexion
            preparedStatement = connection.prepareStatement(requete);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                livre = new Livre(
                    resultSet.getInt("id"),
                    resultSet.getString("titre"),
                    resultSet.getString("auteur"),
                    resultSet.getInt("annee"),
                    resultSet.getString("genre")  // Get genre from the result set
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fermeture sécurisée des ressources
            fermer(connection, preparedStatement, resultSet);
        }
        return livre;
    }

    // Mettre à jour un livre dans la base de données
    public boolean mettreAJourLivre(Livre livre) {
        String requete = "UPDATE livres SET titre = ?, auteur = ?, annee = ?, genre = ? WHERE id = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = dbConnection.getConnection(); // Obtenir la connexion
            preparedStatement = connection.prepareStatement(requete);
            preparedStatement.setString(1, livre.getTitre());
            preparedStatement.setString(2, livre.getAuteur());
            preparedStatement.setInt(3, livre.getAnnee());
            preparedStatement.setString(4, livre.getGenre()); // Set genre
            preparedStatement.setInt(5, livre.getId());

            int lignesAffectees = preparedStatement.executeUpdate();
            return lignesAffectees > 0;  // Retourne true si des lignes ont été mises à jour
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            // Fermeture sécurisée des ressources
            fermer(connection, preparedStatement, null);
        }
    }

    // Supprimer un livre par son ID
    public boolean supprimerLivre(int id) {
        String requete = "DELETE FROM livres WHERE id = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = dbConnection.getConnection(); // Obtenir la connexion
            preparedStatement = connection.prepareStatement(requete);
            preparedStatement.setInt(1, id);

            int lignesAffectees = preparedStatement.executeUpdate();
            return lignesAffectees > 0;  // Retourne true si des lignes ont été supprimées
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            // Fermeture sécurisée des ressources
            fermer(connection, preparedStatement, null);
        }
    }

    // Méthode utilitaire pour fermer les ressources
    private void fermer(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
