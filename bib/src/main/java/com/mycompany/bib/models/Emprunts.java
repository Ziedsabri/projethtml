/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bib.models;

/**
 *
 * @author USER
 */
import java.util.Date;

public class Emprunts {
    private static int  j=0;
    private int id;
    private Livre livre;
    private Adherent adherent;
    private Date dateEmprunt;
    private Date dateRetour;

  
    public Emprunts( Livre livre, Adherent adherent, Date dateEmprunt, Date dateRetour) {
        this.id = j++;
        this.livre = livre;
        this.adherent = adherent;
        this.dateEmprunt = dateEmprunt;
        this.dateRetour = dateRetour;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }

    public Adherent getAdherent() {
        return adherent;
    }

    public void setAdherent(Adherent adherent) {
        this.adherent = adherent;
    }

    public Date getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(Date dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public Date getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(Date dateRetour) {
        this.dateRetour = dateRetour;
    }

    @Override
    public String toString() {
        return "Emprunt{" +
                "id=" + id +
                ", livre=" + livre +
                ", adherent=" + adherent +
                ", dateEmprunt=" + dateEmprunt +
                ", dateRetour=" + dateRetour +
                '}';
    }
}
