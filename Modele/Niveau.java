package Modele;

public class Niveau {
    public static final int VIDE = 0;
    public static final int MORCEAU = 1;
    public static final int MORCEAU_EMPOISONNE = -1;
    public int lignes;
    public int colonnes;
    public int[][] contenu;

    final static int LIGNES_PAR_DEFAUT = 8;
    final static int COLONNES_PAR_DEFAUT = 6;

    public Niveau(int lignes, int colonnes) {
        if (lignes < 1 || colonnes < 1) {
            throw new IllegalArgumentException("Le nombre de lignes et de colonnes doit être supérieur à 0");
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
        for (int i = 0; i < lignes + 1; i++) {
            for (int j = 0; j < colonnes; j++) {
                // Nouvelle ligne
                if (i == lignes)
                    newContenu[i][colonnes] = MORCEAU;
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
            for (int j = 0; j < colonnes + 1; j++) {
                // Nouvelle colonne
                if (j == colonnes)
                    newContenu[i][colonnes] = MORCEAU;
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

    public boolean effacerRectangle(int caseL, int caseC) {
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

    public boolean aMorceau(int ligne, int colonne) {
        if (ligne < 0 || ligne >= lignes || colonne < 0 || colonne >= colonnes) {
            throw new IllegalArgumentException("Les coordonnées sont invalides");
        }
        return contenu[ligne][colonne] == MORCEAU;
    }

    public boolean aMorceauEmpoisonne(int ligne, int colonne) {
        return (ligne == 0 && colonne == 0);
    }

    public boolean estTermine() {
        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                if (contenu[i][j] == MORCEAU)
                    return false;
            }
        }
        return true;
    }
    @Override
    public String toString() {
        String s = "";
        
        s += "  " ;
        for(int i=0; i< colonnes; i++){
            s += i + " ";
        }
        
        s += "\n";

        for (int i = 0; i < lignes; i++) {
                s += i +" ";
            for (int j = 0; j < colonnes; j++) {
                if(contenu[i][j] == MORCEAU_EMPOISONNE)
                    s += "X ";
                else if(contenu[i][j] == MORCEAU)
                    s += "O ";
                else if(contenu[i][j] == VIDE)
                    s += "  ";
            }
            s += "\n";
        }
        return s;
    }

    public int jouer(int caseChoisieL, int caseChoisieC) {
        if(this.aMorceau(caseChoisieL, caseChoisieC)) {
            this.effacerRectangle(caseChoisieL, caseChoisieC);
            return 0;
        } else if(this.aMorceauEmpoisonne(caseChoisieL, caseChoisieC)) {
            if (!this.aMorceau(1,0) && !this.aMorceau(0, 1)){
                System.out.println("Vous avez mangé le morceau empoisonné");
            }
            this.effacerRectangle(0,0);
            // System.out.println("Vous avez perdu");
            // System.out.println("Fin du jeu");
            // System.out.println("Merci d'avoir joué connard");
            return 1;
        } else {
            //System.out.println("Il n'y a pas de morceau à cette case");
            return 2;
        }
    }
}