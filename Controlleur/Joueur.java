package Controlleur;

import Modele.Niveau;

abstract class Joueur {
	Niveau niveau;
	int type;

	// Le joueur connait son numéro, cela lui permet d'inspecter le plateau en
	// sachant
	// repérer ses pions et évaluer où il en est
	Joueur(int n, Niveau p) {
		type = n;
		niveau = p;
	}

	int type() {
		return type;
	}

	// Méthode appelée pour tous les joueurs une fois le temps écoulé
	// Si un joueur n'est pas concerné, il lui suffit de l'ignorer
	boolean tempsEcoule() {
		return false;
	}

	// Méthode appelée pour tous les joueurs lors d'un clic sur le plateau
	// Si un joueur n'est pas concerné, il lui suffit de l'ignorer
	boolean jeu() {
		return false;
	}
}
