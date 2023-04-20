package Modele

import java.util.ArrayList;
import java.util.List;

public class Niveau {
    public static final int VIDE = 0;
    public static final int MORCEAU = 1;
    public static final int MORCEAU_EMPOISONNE = -1;
    public int lignes;
    public int colonnes;
    public int[][] contenu;

    public Niveau(int lignes, int colonnes) {
        if (lignes < 1 || colonnes < 1) {
            throw new IllegalArgumentException("Le nombre de lignes et de colonnes doit être supérieur à 0"
        }
        this.lignes = lignes;
        this.colonnes = colonnes;

        initContenu();
    }

    public Niveau(Niveau n) {
        this.lignes = n.lignes;
        this.colonnes = n.colonnes;
        this.contenu = n.getContenu();
    }

    public void initContenu() {
        contenu = new int[lignes][colonnes];
        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                if (i == 0 && j == 0)
                    contenu[i][j] = MORCEAU_EMPOISONNE;
                else
                    contenu[i][j] = MORCEAU;
            }
        }
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

    public void ajouterLigne() {
        // Ajouter une ligne au niveau
        int[][] newContenu = new int[lignes + 1][colonnes];
        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                // Nouvelle ligne
                if (i == ligne)
                    newContenu[i][colonne] = MORCEAU
                else
                    newContenu[i][j] = contenu[i][j];
            }
        }
        contenu = newContenu;
    }

    public void ajouterColonne() {
        // Ajouter une colonne au niveau
        int[][] newContenu = new int[lignes][colonnes + 1];
        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                // Nouvelle colonne
                if (j == colonne)
                    newContenu[i][colonne] = MORCEAU
                else
                    newContenu[i][j] = contenu[i][j];
            }
        }
        contenu = newContenu;
    }

    public void modifierTailleGaufre(int ligne, int colonne) {
        if (ligne < 0 || ligne >= lignes || colonne < 0 || colonne >= colonnes) {
            throw new IllegalArgumentException("Nouvelle taille de gaufre non valide");
        }
        for(int i=this.lignes; i<ligne; i++) {
            ajouterLigne();
        }
        for(int i=this.colonnes; i<colonne; i++) {
            ajouterColonne();
        }
    }

    public boolean effacerRectangle(int caseL, caseC) {
        if (caseL < 0 || caseL >= lignes || caseC < 0 || caseC >= colonnes) {
            throw new IllegalArgumentException("Les coordonnées sont invalides");
        }
        // Effacer le rectangle de sommet supérieur gauche (caseL, caseC)
        for (int i = caseL; i < lignes; i++) {
            for (int j = caseC; j < colonnes; j++) {
                contenu[i][j] = VIDE;
            }
        }
        return true;
    }

    public void aMorceau(int ligne, int colonne) {
        if (ligne < 0 || ligne >= lignes || colonne < 0 || colonne >= colonnes) {
            throw new IllegalArgumentException("Les coordonnées sont invalides");
        }
        return contenu[ligne][colonne] == MORCEAU;
    }

    public void estTermine() {
        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                if (contenu[i][j] == MORCEAU)
                    return false;
            }
        }
        return true;
    }
}