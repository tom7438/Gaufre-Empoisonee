package Controlleur;

import Modele.Jeu;
import Vues.CollecteurEvenements;

public class ControleurMediateur implements CollecteurEvenements {
	Jeu jeu;
	Joueur[][] joueurs;
	int [] typeJoueur;
	int joueurCourant;
	final int lenteurAttente = 50;
	int decompte;

	public ControleurMediateur(Jeu j) {
		jeu = j;
		joueurs = new Joueur[2][2];
		typeJoueur = new int[2];
		for (int i = 0; i < joueurs.length; i++) {
			joueurs[i][0] = new Humain(i, j.niveau);
			joueurs[i][1] = new IA(i, j.niveau);
			typeJoueur[i] = 0;
		}

		//Cette partie sera a deplacé une fois qu'on pourra cliqué sur une interface
		//Pour tester l'IA
		typeJoueur[0] = 0;
		typeJoueur[1] = 1;
		//boolean fini = false;

		while(!jeu.niveau.estTermine()){
			System.out.println("Plateau :\n" + jeu.niveau.toString());
			

			System.out.println("C'est au tour du joueur " + joueurCourant);
			if (joueurs[joueurCourant][typeJoueur[joueurCourant]].jeu())//ICI donnez 2 arguments a jeu pour pouvoir récupérer lignes et colonnes avec clicsouris
				changeJoueur();
			else
				break;
		}

		System.out.println("Le joueur " + joueurCourant + " a perdu");
		//

	}



	public void joue(){
		tictac();
	}
	// @Override
	// public void clicSouris(int l, int c) {
	// 	// Lors d'un clic, on le transmet au joueur courant.
	// 	// Si un coup a effectivement été joué (humain, coup valide), on change de joueur.
	// 	if (joueurs[joueurCourant][typeJoueur[joueurCourant]].jeu(l, c))
	// 		changeJoueur();
	// }

	void changeJoueur() {
		//joueurCourant = (joueurCourant + 1) % joueurs.length;
		joueurCourant = (joueurCourant + 1) % 2;
		decompte = lenteurAttente;

		//joue();
	}

	public void tictac() {
		if (jeu.enCours()) {
			if (decompte == 0) {
				int type = typeJoueur[joueurCourant];
				// Lorsque le temps est écoulé on le transmet au joueur courant.
				// Si un coup a été joué (IA) on change de joueur.
				if (joueurs[joueurCourant][type].tempsEcoule()) {
					changeJoueur();
				} else {
				// Sinon on indique au joueur qui ne réagit pas au temps (humain) qu'on l'attend.
					System.out.println("On vous attend, joueur " + joueurs[joueurCourant][type].num());
					decompte = lenteurAttente;
				}
			} else {
				decompte--;
			}
		}
	}

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
