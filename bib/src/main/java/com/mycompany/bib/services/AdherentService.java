/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bib.services;

import com.mycompany.bib.db.AdherentBD;
import com.mycompany.bib.models.Adherent;
import java.util.List;

public class AdherentService {

    private AdherentBD adherentBD;

    public AdherentService() {
        adherentBD = new AdherentBD();
    }

    // Ajouter un adhérent
    public boolean ajouterAdherent(Adherent adherent) {
        return adherentBD.ajouterAdherent(adherent);
    }

    // Modifier un adhérent
    public boolean modifierAdherent(Adherent adherent) {
        return adherentBD.modifierAdherent(adherent);
    }

    // Supprimer un adhérent
    public boolean supprimerAdherent(String cin) {
        return adherentBD.supprimerAdherent(cin);
    }

    // Obtenir tous les adhérents
    public List<Adherent> obtenirTousLesAdherents() {
        return adherentBD.obtenirTousLesAdherents();
    }

    // Obtenir un adhérent par son CIN
    public Adherent obtenirAdherentParCin(String cin) {
        return adherentBD.obtenirAdherentParCin(cin);
    }
}
