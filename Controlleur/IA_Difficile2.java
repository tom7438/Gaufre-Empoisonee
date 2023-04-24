package Controlleur;

import Modele.Niveau;

import java.util.Random;

public class IA_Difficile2 extends Joueur{
    Random r;

    IA_Difficile2(int n, Niveau p) {
        super(n, p);
        r = new Random();
    }

    @Override
    boolean jeu(int num){
        int i,j,ok, nb_lignes = 0, nb_colonnes =0;
        System.out.println("Numero de coup " +num);
        if(num%2 == 1){
            if (niveau.colonnes == 3 && niveau.lignes == 2){
                if(num == 1){
                    i = 1;
                    j = 2;
                    System.out.println("L'IA difficile a joué le coup (" + i + "," + j +")");
                    ok = niveau.jouer(1,2);
                } else if(num == 3 && !niveau.aMorceau(0, 1) && niveau.aMorceau(1, 0)){
                    i = 1;
                    j = 0;
                    System.out.println("L'IA difficile a joué le coup (" + i + "," + j +")");
                    ok = niveau.jouer(1, 0);
                } else if(num == 3 && !niveau.aMorceau(1, 0) && niveau.aMorceau(0, 1)){
                    i = 0;
                    j = 1;
                    System.out.println("L'IA difficile a joué le coup (" + i + "," + j +")");
                    ok = niveau.jouer(0, 1);
                } else if(num == 3 && !niveau.aMorceau(1, 1) && niveau.aMorceau(0, 2)){
                    i = 0;
                    j = 2;
                    System.out.println("L'IA difficile a joué le coup (" + i + "," + j +")");
                    ok = niveau.jouer(0, 2);
                } else if(num == 3 && !niveau.aMorceau(0, 2) && niveau.aMorceau(1, 1)){
                    i = 1;
                    j = 1;
                    System.out.println("L'IA difficile a joué le coup (" + i + "," + j +")");
                    ok = niveau.jouer(1, 1);
                } else if (num == 5 && !niveau.aMorceau(0,1) && niveau.aMorceau(1,0)){
                    i = 1;
                    j = 0;
                    System.out.println("L'IA difficile a joué le coup (" + i + "," + j +")");
                    ok = niveau.jouer(1, 0);
                } else if (num == 5 && !niveau.aMorceau(1,0) && niveau.aMorceau(0,1)){
                    i = 0;
                    j = 1;
                    System.out.println("L'IA difficile a joué le coup (" + i + "," + j +")");
                    ok = niveau.jouer(0, 1);
                }else{
                    //joue un coup aléatoire
                    do {
                        i = r.nextInt(niveau.getLigne());
                        j = r.nextInt(niveau.getColonne());
                    } while (j == 0 && i == 0 || !niveau.aMorceau(i, j));
                        System.out.println("L'IA difficile a joué le coup (" + i + "," + j +")");
                        ok = niveau.jouer(i, j);
                }
            }else if (niveau.lignes == 3 && niveau.colonnes == 2){
                if(num == 1) {
                    i = 2;
                    j = 1;
                    System.out.println("L'IA difficile a joué le coup (" + i + "," + j +")");
                    ok = niveau.jouer(2, 1);
                } else if (num == 3 && niveau.aMorceau(0, 1) && !niveau.aMorceau(1, 0)) {
                    i = 0;
                    j = 1;
                    System.out.println("L'IA difficile a joué le coup (" + i + "," + j +")");
                    ok = niveau.jouer(0, 1);
                } else if (num == 3 && niveau.aMorceau(1, 0) && !niveau.aMorceau(0, 1)) {
                    i = 1;
                    j = 0;
                    System.out.println("L'IA difficile a joué le coup (" + i + "," + j +")");
                    ok = niveau.jouer(1, 0);
                } else if (num == 3 && !niveau.aMorceau(1, 1) && niveau.aMorceau(2, 0)) {
                    i = 2;
                    j = 0;
                    System.out.println("L'IA difficile a joué le coup (" + i + "," + j +")");
                    ok = niveau.jouer(2, 0);
                } else if (num == 3 && !niveau.aMorceau(2, 0) && niveau.aMorceau(1, 1)) {
                    i = 1;
                    j = 1;
                    System.out.println("L'IA difficile a joué le coup (" + i + "," + j +")");
                    ok = niveau.jouer(1, 1);
                } else if (num == 5 && !niveau.aMorceau(0, 1) && niveau.aMorceau(1, 0)) {
                    i = 1;
                    j = 0;
                    System.out.println("L'IA difficile a joué le coup (" + i + "," + j +")");
                    ok = niveau.jouer(1, 0);
                } else if (num == 5 && !niveau.aMorceau(1, 0) && niveau.aMorceau(0, 1)) {
                    i = 0;
                    j = 1;
                    System.out.println("L'IA difficile a joué le coup (" + i + "," + j +")");
                    ok = niveau.jouer(0, 1);
                } else {
                    //joue un coup aléatoire
                    do {
                        i = r.nextInt(niveau.getLigne());
                        j = r.nextInt(niveau.getColonne());
                    } while (j == 0 && i == 0 || !niveau.aMorceau(i, j));
                        System.out.println("L'IA difficile a joué le coup (" + i + "," + j +")");
                        ok = niveau.jouer(i, j);
                }
            } else if(niveau.colonnes == niveau.lignes){
                if(num == 1){
                    i = 1;
                    j = 1;
                    niveau.jouer(i,j);
                    return true;
                }
                //System.out.println("Nombre de lignes " +(niveau.getLigne() -1));
                for(i=niveau.getLigne() -1; i!=0; i--){
                    if (niveau.aMorceau(i, 0)){
                        nb_lignes = i; //ligne max avec un morceau;
                        break;
                    }
                }

                for(i=niveau.getColonne()-1; i!=0; i--){
                    if (niveau.aMorceau(0, i)){
                        nb_colonnes = i;
                        break;
                    }
                }

                if (nb_lignes > nb_colonnes){
                    System.out.println("L'IA difficile a joué le coup (" + (nb_colonnes + 1) + "," + 0 +")" );
                    niveau.jouer(nb_colonnes +1, 0);
                }
                else if (nb_lignes < nb_colonnes){
                    System.out.println("L'IA difficile a joué le coup (" + 0 + "," + (nb_lignes + 1) +")");
                    niveau.jouer(0, nb_lignes +1);
                }
                else{
                    i = r.nextInt(2);
                    if (i == 0){
                        System.out.println("L'IA difficile a joué le coup (" + 0 + "," + nb_lignes +")");
                        niveau.jouer(0, nb_lignes );
                    }
                    else{
                        System.out.println("L'IA difficile a joué le coup (" + 0 + "," + nb_colonnes +")");
                        niveau.jouer(nb_colonnes, 0);
                    }
                }
                return true;
            } else {
                return false;
            }
        }else{
            if (niveau.lignes == 3 && niveau.colonnes == 2){
                if (num ==2) {
                    j = r.nextInt(2);
                    if (j == 0) {
                        i = 2;
                    } else {
                        i = 1;
                    }
                    System.out.println("L'IA difficile a joué le coup (" + i + "," + j +")");
                    ok = niveau.jouer(i, j);
                } else {
                    do {
                        i = r.nextInt(niveau.getLigne());
                        j = r.nextInt(niveau.getColonne());
                    } while (!niveau.aMorceau(i, j));
                        System.out.println("L'IA difficile a joué le coup (" + i + "," + j +")");
                        ok = niveau.jouer(i, j);
                }
            } else if (niveau.lignes == 2 && niveau.colonnes == 3){
                if (num ==2) {
                    i = r.nextInt(2);
                    if (i == 0) {
                        j = 2;
                    } else {
                        j = 1;
                    }
                    System.out.println("L'IA difficile a joué le coup (" + i + "," + j +")");
                    ok = niveau.jouer(i, j);
                } else {
                    do {
                        i = r.nextInt(niveau.getLigne());
                        j = r.nextInt(niveau.getColonne());
                    } while (!niveau.aMorceau(i, j));
                        System.out.println("L'IA difficile a joué le coup (" + i + "," + j +")");
                        ok = niveau.jouer(i, j);
                }
            } else if(niveau.colonnes == niveau.lignes){
                if (niveau.aMorceau(1, 1)){
                    i = 1;
                    j = 1;
                    niveau.jouer(i,j);
                    return true;
                }
                // si il y a plus de morceaux sur la ligne 0 que sur la colonne 0 ou inversement on egalise
                for(i = 0; i < niveau.getLigne(); i++){
                    if (niveau.aMorceau(i, 0)){
                        nb_lignes++;
                    }
                }
                for(i = 0; i < niveau.getColonne(); i++){
                    if (niveau.aMorceau(0, i)){
                        nb_colonnes++;
                    }
                } if (nb_lignes > nb_colonnes){
                    System.out.println("L'IA difficile a joué le coup (" + (nb_colonnes + 1) + "," + 0 +")" );
                    ok = niveau.jouer(nb_colonnes +1, 0);
                } else if (nb_lignes < nb_colonnes){
                    System.out.println("L'IA difficile a joué le coup (" + 0 + "," + (nb_lignes + 1) +")");
                    ok = niveau.jouer(0, nb_lignes +1);
                } else{
                    i = r.nextInt(2);
                    if (i == 0){
                        System.out.println("L'IA difficile a joué le coup (" + 0 + "," + nb_lignes +")");
                        ok = niveau.jouer(0, nb_lignes );
                    }
                    else{
                        System.out.println("L'IA difficile a joué le coup (" + 0 + "," + nb_colonnes +")");
                        ok = niveau.jouer(nb_colonnes, 0);
                    }
                }
            } else {
                return false;
            }
        }
        return ok == 0;
    }
}
