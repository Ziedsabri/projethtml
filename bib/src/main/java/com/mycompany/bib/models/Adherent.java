/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bib.models;

/**
 *
 * @author USER
 */
public class Adherent {
    private String cin;
    private String nom;
    private String prenom;
    private String email;
    private String numTel;

   
    public Adherent(String cin, String nom, String prenom, String email, String numTel) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.numTel = numTel;
    }

    
    public String getcin() {
        return cin;
    }

    public void setcin(String cin) {
        this.cin = cin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    @Override
    public String toString() {
        return "Adherent{" +
                "cin=" + cin +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", numTel='" + numTel + '\'' +
                '}';
    }
}
