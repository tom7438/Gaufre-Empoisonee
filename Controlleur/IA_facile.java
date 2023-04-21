package Controlleur;

import java.util.Random;
import Modele.Niveau;
import Controlleur.ControleurMediateur;


class IA_facile extends Joueur {
	Random r;

	public IA_facile(int type, Niveau n) {
		super(type, n);
		r = new Random();
	}

	@Override
    boolean jeu(){  
		int i, j;
		int ok;
		do{
			i = r.nextInt(niveau.getLigne());
			j = r.nextInt(niveau.getColonne());
			ok = niveau.jouer(i, j);
		}while(ok == 2); //pas de morceau sur cette case

		//System.out.println("c'est le coup " +numero_coup);

		System.out.println("L'IA a joué le coup : (" + i + "," + j + ")");

		if (ok == 0){
            return true;
        }
		return false;

	}
}