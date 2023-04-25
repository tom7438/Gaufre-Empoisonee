package Controlleur;

import Modele.Niveau;
import java.util.LinkedList;
//faire une ia probabiliste

public class IA_Difficile extends Joueur {
    public static class arbre_naire{
        int n_ligne;
        int n_colonne;
        int nb_coup;
        float proba_victoire;
        int[][] plateau;
        LinkedList<arbre_naire> fils;
        arbre_naire(int n_ligne, int n_colonne, float proba_victoire, int nb_coup, int[][] plateau){
            this.n_ligne = n_ligne;
            this.n_colonne = n_colonne;
            this.proba_victoire = proba_victoire;
            this.nb_coup = nb_coup;
            fils = new LinkedList<>();
            this.plateau = plateau;
        }
    }

    arbre_naire racine;

    IA_Difficile(int n, Niveau p) {
        super(n, p);
    }

    void remplir_arbre(arbre_naire a){
        arbre_naire fils;
        //cas ou on a une feuille
        if(a.plateau[a.n_ligne][a.n_colonne] == 2){
            if(a.nb_coup % 2 == 0){
                a.proba_victoire = 1;
            }else{
                a.proba_victoire = 0;
            }
        //cas ou on a un noeud
        } else {
            for (int l = 0; l < niveau.lignes; l++) { //on parcourt le plateau
                for (int c = 0; c < niveau.colonnes; c++) {

                    if (a.plateau[l][c] == 1) { //si on trouve un morceau
                        fils = new arbre_naire(l, c, 0, a.nb_coup + 1, a.plateau); //on cree un nouveau noeud


                        if(a.n_ligne < fils.n_ligne){ //on regarde si le morceau est a droite ou a gauche
                            for(int x = a.n_ligne; x <= fils.n_ligne; x++){
                                if(a.n_colonne < fils.n_colonne) { //on regarde si le morceau est en haut ou en bas
                                    for (int y = a.n_colonne; y <= fils.n_colonne; y++) {
                                        if (a.plateau[x][y] == 1) { //on enleve les morceaux entre les deux
                                            fils.plateau[x][y] = 0; //on enleve les morceaux entre les deux
                                        }
                                    }
                                }else{
                                    for (int y = a.n_colonne; y >= fils.n_colonne; y--) {
                                        if (a.plateau[x][y] == 1) { //on enleve les morceaux entre les deux
                                            fils.plateau[x][y] = 0; //on enleve les morceaux entre les deux
                                        }
                                    }
                                }
                            }
                        }else{
                            for (int x = a.n_ligne; x >= fils.n_ligne; x--) {
                                if(a.n_colonne < fils.n_colonne) {
                                    for (int y = a.n_colonne; y <= fils.n_colonne; y++) {
                                        if (a.plateau[x][y] == 1) {
                                            fils.plateau[x][y] = 0;
                                        }
                                    }
                                }else{
                                    for (int y = a.n_colonne; y >= fils.n_colonne; y--) {
                                        if (a.plateau[x][y] == 1) {
                                            fils.plateau[x][y] = 0;
                                        }
                                    }
                                }
                            }
                        }
                        a.fils.add(fils);
                        remplir_arbre(fils);
                        //TODO :mise a jour des valeurs
                    }
                }
            }
        }
    }

    //fonction qui remonte l'arbre en mettant la valeur
    void evaluer_noeud(arbre_naire a) {
        if (a.fils.isEmpty()) { // si le nœud est une feuille
            if (a.nb_coup % 2 == 0) { // si c'est le tour de l'IA
                a.proba_victoire = 0; // assigner une valeur minimale
            } else { // si c'est le tour de l'adversaire
                a.proba_victoire = 1; // assigner une valeur maximale
            }
        } else { // si le nœud n'est pas une feuille
            float max_valeur = -1;
            for (arbre_naire fils : a.fils) { // parcourir les fils du nœud
                evaluer_noeud(fils); // évaluer le fils
                if (fils.proba_victoire > max_valeur) { // si le fils a une valeur supérieure à la valeur maximale actuelle
                    max_valeur = fils.proba_victoire; // mettre à jour la valeur maximale
                }
            }
            a.proba_victoire = 1 - max_valeur; // assigner la valeur du nœud en fonction de la stratégie de l'IA
        }
    }

    @Override
    boolean jeu(int num) {
        int[][] plateau = new int[niveau.lignes][niveau.colonnes];
        for (int i = 0; i < niveau.lignes; i++) {
            for (int j = 0; j < niveau.colonnes; j++) {
                if (niveau.aMorceau(i, j)) {
                    plateau[i][j] = 1; 
                }
                else {
                    plateau[i][j] = 0;
                }
            }
        }
        plateau[0][0] = 2;
        racine = new arbre_naire(niveau.lignes - 1, niveau.colonnes - 1, 1, 0, plateau);
        remplir_arbre(racine);
        evaluer_noeud(racine);
        int i =0 , j = 0, ok;
        float max = -1;
        for(int x = 0; x < racine.fils.size(); x++){
            if(racine.fils.get(x).proba_victoire > max){
                max = racine.fils.get(x).proba_victoire;
                i = racine.fils.get(x).n_ligne;
                j = racine.fils.get(x).n_colonne;
            }
        }
        ok = niveau.jouer(i,j);
        return ok == 0;
    }
}