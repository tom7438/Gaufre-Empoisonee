package Controlleur;

import Modele.Niveau;
import Modele.Joueur;

import java.util.Random;

public class IA_Difficile extends Joueur{
    Random r;

    public IA_Difficile(String name, boolean AI, int diff, Niveau n) {
		super(name, AI, diff, n);
		r = new Random();
	}

    public IA_Difficile(Joueur joueur, Niveau niv) {
		super(joueur.getPlayerName(), true, joueur.getDifficulteIA(), niv);
		r = new Random();
    }

    int [] trouver_dimension(){
        int [] dim = new int[2];
        int i = 0, j = 0;
        while(i < niveau.lignes){
            if(niveau.aMorceau(i, 0)){
                dim[0] = i;
            }
            i++;
        }
        while(j < niveau.colonnes){
            if(niveau.aMorceau(0, j)){
                dim[1] = j;
            }
            j++;
        }
        return dim;
    }

    @Override
    public boolean jeu() {
        int i, j, ok, nb_lignes, nb_colonnes;
        //System.out.println("Numéro de coup " + num);
        int[] dim = trouver_dimension();
        System.out.println("Dimension " + dim[0] + "," + dim[1]);
        nb_lignes = dim[0];
        nb_colonnes = dim[1];


        /////////resolution du cas 3x2
        if (nb_colonnes == 2 && nb_lignes == 1) {
            if (niveau.aMorceau(1, 2)) {
                i = 1;
                j = 2;
                System.out.println("L'IA difficile a joué le coup (" + i + "," + j + ")");
                ok = niveau.jouer(1, 2);
            } else if (!niveau.aMorceau(1, 2) && niveau.aMorceau(0, 2) && niveau.aMorceau(1, 1)) {
                i = r.nextInt(2);
                if (i == 0) {
                    j = 2;
                } else {
                    j = 1;
                }
                System.out.println("L'IA difficile a joué le coup (" + i + "," + j + ")");
                ok = niveau.jouer(i, j);
            } else if (!niveau.aMorceau(0, 1) && niveau.aMorceau(1, 0)) {
                i = 1;
                j = 0;
                System.out.println("L'IA difficile a joué le coup (" + i + "," + j + ")");
                ok = niveau.jouer(1, 0);
            } else if (!niveau.aMorceau(1, 0) && niveau.aMorceau(0, 1)) {
                i = 0;
                j = 1;
                System.out.println("L'IA difficile a joué le coup (" + i + "," + j + ")");
                ok = niveau.jouer(0, 1);
            } else if (!niveau.aMorceau(1, 1) && niveau.aMorceau(0, 2)) {
                i = 0;
                j = 2;
                System.out.println("L'IA difficile a joué le coup (" + i + "," + j + ")");
                ok = niveau.jouer(0, 2);
            } else if (!niveau.aMorceau(0, 2) && niveau.aMorceau(1, 1)) {
                i = 1;
                j = 1;
                System.out.println("L'IA difficile a joué le coup (" + i + "," + j + ")");
                ok = niveau.jouer(1, 1);
            } else {
                //joue un coup aléatoire (normalement, on n'arrive jamais ici)
                do {
                    i = r.nextInt(nb_lignes+1);
                    j = r.nextInt(nb_colonnes+1);
                } while (j == 0 && i == 0 || !niveau.aMorceau(i, j));
                System.out.println("L'IA difficile a joué le (aléatoire) coup (" + i + "," + j + ")");
                ok = niveau.jouer(i, j);
            }
        }

        /////////resolution du cas 2x3
        else if (nb_lignes == 2 && nb_colonnes == 1) {
            if (niveau.aMorceau(2, 1)) {
                i = 2;
                j = 1;
                System.out.println("L'IA difficile a joué le coup (" + i + "," + j + ")");
                ok = niveau.jouer(2, 1);
            } else if (!niveau.aMorceau(2, 1) && niveau.aMorceau(2, 0) && niveau.aMorceau(1, 1)) {
                j = r.nextInt(2);
                if (j == 0) {
                    i = 2;
                } else {
                    i = 1;
                }
                System.out.println("L'IA difficile a joué le coup (" + i + "," + j + ")");
                ok = niveau.jouer(i, j);
            } else if (niveau.aMorceau(0, 1) && !niveau.aMorceau(1, 0)) {
                i = 0;
                j = 1;
                System.out.println("L'IA difficile a joué le coup (" + i + "," + j + ")");
                ok = niveau.jouer(0, 1);
            } else if (niveau.aMorceau(1, 0) && !niveau.aMorceau(0, 1)) {
                i = 1;
                j = 0;
                System.out.println("L'IA difficile a joué le coup (" + i + "," + j + ")");
                ok = niveau.jouer(1, 0);
            } else if (!niveau.aMorceau(1, 1) && niveau.aMorceau(2, 0)) {
                i = 2;
                j = 0;
                System.out.println("L'IA difficile a joué le coup (" + i + "," + j + ")");
                ok = niveau.jouer(2, 0);
            } else if (!niveau.aMorceau(2, 0) && niveau.aMorceau(1, 1)) {
                i = 1;
                j = 1;
                System.out.println("L'IA difficile a joué le coup (" + i + "," + j + ")");
                ok = niveau.jouer(1, 1);
            } else {
                //joue un coup aléatoire
                do {
                    i = r.nextInt(nb_lignes+1);
                    j = r.nextInt(nb_colonnes+1);
                } while (j == 0 && i == 0 || !niveau.aMorceau(i, j));
                System.out.println("L'IA difficile a joué le coup (" + i + "," + j + ")");
                ok = niveau.jouer(i, j);
            }
        }

        /////////resolution du cas n*n
        else if (nb_lignes == nb_colonnes && niveau.aMorceau(1, 1)) {
            ok = niveau.jouer(1, 1);
        }

        /////////resolution des cas 0*0, 0*k et k*0
        else if ((nb_colonnes == 0 && nb_lignes == 0) || (nb_lignes == 0 && nb_colonnes == 1 && !niveau.aMorceau(0, 1)) || (nb_lignes == 1 && nb_colonnes == 0 && !niveau.aMorceau(1, 0))) { //si les deux coins sont vides
            i = 0;
            j = 0;
            System.out.println("L'IA difficile a joué le coup (" + i + "," + j + ")");
            ok = niveau.jouer(i, j);
        } else if (nb_lignes == 0 && nb_colonnes >= 1) {
            i = 0;
            j = 1;
            System.out.println("L'IA difficile a joué le coup (" + i + "," + j + ")");
            ok = niveau.jouer(i, j);
        } else if (nb_lignes >= 1 && nb_colonnes == 0) {
            i = 1;
            j = 0;
            System.out.println("L'IA difficile a joué le coup (" + i + "," + j + ")");
            ok = niveau.jouer(i, j);
        }

        /////////resolution des cas k*n et n*k
        else if (nb_lignes > nb_colonnes && !niveau.aMorceau(1, 1)){
            System.out.println("L'IA difficile a joué le coup (" + (nb_colonnes+1) + "," + 0 +")" );
            ok = niveau.jouer(nb_colonnes+1, 0);
        } else if (nb_lignes < nb_colonnes && !niveau.aMorceau(1, 1)) {
            System.out.println("L'IA difficile a joué le coup (" + 0 + "," + (nb_lignes+1) + ")");
            ok = niveau.jouer(0, nb_lignes+1);
        }

        /////////resolution du rest
        else {
            do {
                i = r.nextInt(nb_lignes+1);
                j = r.nextInt(nb_colonnes+1);
            } while (j == 0 && i == 0 || !niveau.aMorceau(i, j));
            System.out.println("L'IA difficile a joué le coup (aléatoire) (" + i + "," + j + ")");
            ok = niveau.jouer(i, j);
        }
        return ok == 0;
    }
}
