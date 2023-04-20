package Modele

import java.util.ArrayList;
import java.util.List;

public class Niveau {
    public static final int VIDE = 0;
    public static final int MORCEAU = 1;
    public static final int MORCEAU_EMPOSONNE = -1;
    public int lignes;
    public int colonnes;
    public int[][] contenu;

    public Niveau(int lignes, int colonnes) {
        if(lignes < 1 || colonnes < 1) {
            throw new IllegalArgumentException("Le nombre de lignes et de colonnes doit être supérieur à 0"
        }
        this.lignes = lignes;
        this.colonnes = colonnes;

        initContenu();

    }

    void initContenu() {
        contenu = new int[lignes][colonnes];
        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                if(i == 0 && j == 0)
                    contenu[i][j] = MORCEAU_EMPOSONNE;
                else
                    contenu[i][j] = MORCEAU;
            }
        }
    }

    public Niveau(Niveau n) {
        this.lignes = n.lignes;
        this.colonnes = n.colonnes;
        this.contenu = n.getContenu();
    }

    public int[][] getContenu() {
        return this.contenu;
    }

    public int getLigne() {
        return this.lignes;
    }

    public int getColonne() {
        return this.colonnes;
    }

    void ajouterLigne() {
        // TODO: implement here
    }

    void aMorceau(int ligne, int colonne) {
        if(ligne < 0 || ligne >= lignes || colonne < 0 || colonne >= colonnes) {
            throw new IllegalArgumentException("Les coordonnées sont invalides");
        }

        return contenu[ligne][colonne] == MORCEAU;
    }
}