package Controlleur;

import java.util.Random;
import Modele.Jeu;


class IA extends Joueur {
	Random r;

	public IA(int n, Jeu p) {
		super(n, p);
		r = new Random();
	}
/*
	@Override
	boolean tempsEcoule() {
		// Pour cette IA, on selectionne aléatoirement une case libre
		int i, j;

        do{
            i = r.nextInt(plateau.hauteur());
            j = r.nextInt(plateau.largeur());

        }while((i==0 && j == 0) || !plateau.libre(i, j));
        
		plateau.jouer(i, j);

		return true;
	}
 */
}