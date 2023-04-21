package Controlleur;

import Modele.Joueur;
import Modele.Niveau;
import Modele.Coup;


public class Humain extends Joueur {

    public Humain(String name, boolean AI, int diff, Niveau n){
        super(name, AI, diff, n);
    }

    @Override
    public boolean jeu(){
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