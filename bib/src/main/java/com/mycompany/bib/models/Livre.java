/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bib.models;

/**
 *
 * @author USER
 */
public class Livre {
    private static int i=0;
    private int id;
    private String titre;
    private String auteur;
    private int annee;
    private String genre;

  public Livre( int id,String titre, String auteur, int annee, String genre) {
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.annee = annee;
        this.genre = genre;
    }
    public Livre(String titre, String auteur, int annee, String genre) {
        this.id = i++;
        this.titre = titre;
        this.auteur = auteur;
        this.annee = annee;
        this.genre = genre;
    }

   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Livre{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", auteur='" + auteur + '\'' +
                ", annee=" + annee +
                ", genre='" + genre + '\'' +
                '}';
    }
}