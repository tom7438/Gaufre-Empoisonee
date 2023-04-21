package Controlleur;

import Modele.Niveau;
import Modele.Coup;


class Humain extends Joueur{

    Humain (int type, Niveau n){
        super(type,n);
    }

    @Override
    boolean jeu(int num){   
        int ok;
        do{
         Coup p = new Coup(niveau);
         ok = niveau.jouer(p.caseChoisieL, p.caseChoisieC);
         if (ok == 2)
            System.out.println("Il n'y a pas de morceau à cette case");
        }while(ok == 2);    
        if (ok == 0){
            return true;
        }
        return false;

    }
}