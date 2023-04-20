package Controlleur;

import Modele.Jeu;

class Humain extends Joueur{

    Humain (int n, Jeu p){
        super(n,p);
    }

	boolean jeu(int i, int j) {
		// A adapter selon le jeu,
		// Un coup peut être constitué de plusieurs passages par cette fonction, ex :
		// - selection d'un pièce + surlignage des coups possibles
		// - selection de la destination
		// Autrement dit une machine à état peut aussi être gérée par un objet de cette
		// classe. Dans le cas du morpion, un clic suffit.
		if (plateau.libre(i, j)) {
			plateau.jouer(i, j);
			return true;
		} else {
			return false;
		}
	}
}