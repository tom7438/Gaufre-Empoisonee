package Controlleur;

import Modele.Niveau;
import java.util.Random;


public class IA_Difficile extends Joueur{

    Random r;

    IA_Difficile(int n, Niveau p) {
        super(n, p);
        r = new Random();
    }

    @Override
    boolean jeu(int num){
        int i,j;

        int nb_lignes = 0, nb_colonnes =0;

        System.out.println("Numero de coup " +num);
        if (niveau.getLigne() == niveau.getColonne()){
            if(num == 1){
                i = 1;
                j = 1;
                niveau.jouer(i,j); 
                return true;
            }
            else if(num == 2) {
                if(niveau.aMorceau(1, 1)){
                    niveau.jouer(1,1); 
                    return true;
                }
                num =3;
            }
            if (num >= 3){
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
            }
        }
        return false;
    }
}
