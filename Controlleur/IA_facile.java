package Controlleur;

import java.util.Random;
import Modele.Niveau;


class IA_facile extends Joueur {
	Random r;

	public IA_facile(int num, Niveau n) {
		super(num, n);
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

		System.out.println("L'IA a joué le coup : (" + i + "," + j + ")");
		//System.out.println("LAAAAAAA");

		if (ok == 0){
            return true;
        }
		return false;

	}
}