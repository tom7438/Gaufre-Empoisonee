package Controlleur;

import Modele.Niveau;

public class IA_Difficile2 extends Joueur{

    IA_Difficile2(int n, Niveau p) {
        super(n, p);
        //TODO Auto-generated constructor stub
    }

    @Override
    boolean jeu(int num){
        int ok;
        if(num%2 == 1){
            if (niveau.colonnes == 3 && niveau.lignes == 2){
                if(niveau.aMorceau(1,2)){
                    ok = niveau.jouer(1,2);
                } else if(!niveau.aMorceau(0,1) && niveau.aMorceau(1, 0)){
                    ok = niveau.jouer(1, 0);
                } else if(!niveau.aMorceau(1, 0) && niveau.aMorceau(0, 1)){
                    ok = niveau.jouer(0, 1);
                } else if(!niveau.aMorceau(1, 1) && niveau.aMorceau(0, 2)){
                    ok = niveau.jouer(0, 2);
                } else {
                    ok = niveau.jouer(1, 1);
                }
                return true;
            }if (niveau.lignes == 3 && niveau.colonnes == 2){
                if(niveau.aMorceau(2,1)){
                    ok = niveau.jouer(2,1);
                } else if(!niveau.aMorceau(1,0) && niveau.aMorceau(0,1)){
                    ok = niveau.jouer(0,1);
                } else if(!niveau.aMorceau(0,1) && niveau.aMorceau(1,0)){
                    ok = niveau.jouer(1,0);
                } else if(!niveau.aMorceau(1, 1) && niveau.aMorceau(2,0)){
                    ok = niveau.jouer(2,0);
                } else {
                    ok = niveau.jouer(1, 1);
                }
                return true;
            } else if(niveau.colonnes == niveau.lignes){
                return false ;
            } else {
                return false;
            }
        }else{
            if (niveau.colonnes == 3 && niveau.lignes == 2){
                if(niveau.aMorceau(0,2)){
                    ok = niveau.jouer(0,2);
                } else if(!niveau.aMorceau(1,1)){
                    ok = niveau.jouer(1, 1);
                } else if(!niveau.aMorceau(0, 1)){
                    ok = niveau.jouer(0, 1);
                } else if(!niveau.aMorceau(1, 0)){
                    ok = niveau.jouer(1, 0);
                } else {
                    return false;
                } return true;
            }if (niveau.lignes == 3 && niveau.colonnes == 2){
                if(niveau.aMorceau(2,0)){
                    ok = niveau.jouer(2,0);
                } else if(!niveau.aMorceau(1,1)){
                    ok = niveau.jouer(1, 1);
                } else if(!niveau.aMorceau(1, 0)){
                    ok = niveau.jouer(1, 0);
                } else if(!niveau.aMorceau(0, 1)){
                    ok = niveau.jouer(0, 1);
                } else {
                    return false;
                } return true;
            } else if(niveau.colonnes == niveau.lignes){
                return false;
            } else {
                return false;
            }
        }
    }
}