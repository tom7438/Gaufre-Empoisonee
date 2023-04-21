package Controlleur;

import Modele.Jeu;
import Vues.CollecteurEvenements;

public class ControleurMediateur implements CollecteurEvenements {
	Jeu jeu;

	public ControleurMediateur(Jeu j) {
		jeu = j;

		while(!jeu.niveau.estTermine()){
			System.out.println("Plateau :\n" + jeu.niveau.toString());


			System.out.println("C'est au tour du joueur " + jeu.joueurCourant);
			if (jeu.joueurs[jeu.joueurCourant].jeu())//ICI donnez 2 arguments a jeu pour pouvoir récupérer lignes et colonnes avec clicsouris
				jeu.changeJoueur();
			else
				break;
		}

		System.out.println("Le joueur " + jeu.joueurCourant + " a perdu");
		//

	}



	// @Override
	// public void clicSouris(int l, int c) {
	// 	// Lors d'un clic, on le transmet au joueur courant.
	// 	// Si un coup a effectivement été joué (humain, coup valide), on change de joueur.
	// 	if (joueurs[joueurCourant][typeJoueur[joueurCourant]].jeu(l, c))
	// 		changeJoueur();
	// }




	// @Override
	// public void changeJoueur(int j, int t) {
	// 	System.out.println("Nouveau type " + t + " pour le joueur " + j);
	// 	typeJoueur[j] = t;
	// }

	// @Override
	// public void changeTaille(int t) {
	// 	System.out.println("Nouvelle taille " + t);
	// 	jeu.reset(t);
	// }
}
