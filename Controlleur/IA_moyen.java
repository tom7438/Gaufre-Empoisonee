package Controlleur;

import java.util.Random;

import Modele.Joueur;
import Modele.Niveau;


class IA_moyen extends Joueur {
	Random r;

	public IA_moyen(String name, boolean AI, int diff, Niveau n) {
		super(name, AI, diff, n);
		r = new Random();
	}

    public boolean jeu(){
		int i, j;
		int ok;
		do{
			do{
				i = r.nextInt(this.niveau.getLigne());
				j = r.nextInt(niveau.getColonne());
				ok = niveau.jouer(i, j);
			}while(ok == 2); //pas de morceau sur cette case

			if(i != 0 || j != 0 ) 
				break;//Sinon on boucle jusqu'a avoir un endroit non empoisonné
				
		}while (niveau.aMorceau(i+1,0) || niveau.aMorceau(0, i+1)); //si l'IA mange l'empoisonné alors 
	
		System.out.println("L'IA a joué le coup : (" + i + "," + j + ")");

		if (ok == 0){
            return true;
        }
		
		return false;

	}

}