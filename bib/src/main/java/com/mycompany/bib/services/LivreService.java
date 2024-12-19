/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bib.services;

import com.mycompany.bib.models.Livre;
import com.mycompany.bib.db.LivreBD;
import java.util.List;

public class LivreService {
    private LivreBD livreBD;

    public LivreService() {
        this.livreBD = new LivreBD();
    }

    public void ajouterLivre(Livre livre) {
        livreBD.ajouterLivre(livre);
    }

    public void modifierLivre(Livre livre) {
        livreBD.mettreAJourLivre(livre);
    }

    public void supprimerLivre(int id) {
        livreBD.supprimerLivre(id);
    }

    public Livre chercherLivre(int id) {
        return livreBD.obtenirLivreParId(id);
    }

    public List<Livre> listerLivres() {
        return livreBD.obtenirTousLesLivres();
    }
}

