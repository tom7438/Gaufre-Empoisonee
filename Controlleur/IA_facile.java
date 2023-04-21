package Controlleur;

import java.util.Random;

import Modele.Joueur;
import Modele.Niveau;


public class IA_facile extends Joueur {
	Random r;

	public IA_facile(String name, boolean AI, int diff, Niveau n) {
		super(name, AI, diff, n);
		r = new Random();
	}

    public boolean jeu(){
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