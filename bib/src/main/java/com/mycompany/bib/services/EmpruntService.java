/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bib.services;

import com.mycompany.bib.db.EmpruntBD;
import com.mycompany.bib.models.Emprunts;
import java.util.List;

public class EmpruntService {

    private EmpruntBD empruntBD;

    public EmpruntService() {
        empruntBD = new EmpruntBD();
    }

    public boolean ajouterEmprunt(Emprunts emprunt) {
        return empruntBD.ajouterEmprunt(emprunt);
    }

    public boolean modifierEmprunt(Emprunts emprunt) {
        return empruntBD.modifierEmprunt(emprunt);
    }

    public boolean supprimerEmprunt(int id) {
        return empruntBD.supprimerEmprunt(id);
    }

    public List<Emprunts> obtenirTousLesEmprunts() {
        return empruntBD.obtenirTousLesEmprunts();
    }

    public Emprunts obtenirEmpruntParId(int id) {
        return empruntBD.obtenirEmpruntParId(id);
    }
}
