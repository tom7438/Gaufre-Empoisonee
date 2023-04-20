package Modele

import java.util.ArrayList;
import java.util.List;

public class Niveau {
    public int lignes;
    public int colonnes;
    public List<Integer> contenu;

    public Niveau(int lignes, int colonnes) {
        if(lignes < 1 || colonnes < 1) {
            throw new IllegalArgumentException("Le nombre de lignes et de colonnes doit être supérieur à 0"
        }
        this.lignes = lignes;
        this.colonnes = colonnes;

        contenu = new ArrayList<Integer>(lignes);

        for(int i = 0; i < lignes; i++) {
            contenu.add(colonnes);
        }
    }

    public Niveau(Niveau n) {
        this.lignes = n.lignes;
        this.colonnes = n.colonnes;
        this.contenu = n.getContenu();
    }

    public List<Integer> getContenu() {
        return this.contenu;
    }

    public int getLignes() {
        return this.lignes;
    }

    public int getColonnes() {
        return this.colonnes;
    }

    void ajouterLigne() {
        this.lignes++;
        this.contenu.add(colonnes);
    }
}