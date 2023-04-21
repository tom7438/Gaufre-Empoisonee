package Controlleur;

import Modele.Jeu;
import Vues.CollecteurEvenements;

public class ControleurMediateur implements CollecteurEvenements {
	Jeu jeu;
	Joueur[] joueurs;
	int [] typeJoueur;
	int joueurCourant;
	final int lenteurAttente = 50;
	int decompte;
	public int numero_coup=1;

	public ControleurMediateur(Jeu j) {
		jeu = j;
		joueurs = new Joueur[2];

		joueurs[0] = new Humain(0, j.niveau); //DEFINIR le type du joueur
		joueurs[1] = new IA_facile(1, j.niveau); 
		//joueurs[1] = new IA_moyen(1, j.niveau); 

		//System.out.println("Le type " +joueurs[0].type()); 
		joueurCourant = 0;

		while(!jeu.niveau.estTermine()){
			System.out.println("Plateau :\n" + jeu.niveau.toString());
			

			System.out.println("C'est au tour du joueur " + joueurCourant);
			if (joueurs[joueurCourant].jeu()){//ICI donnez 2 arguments a jeu pour pouvoir récupérer lignes et colonnes avec clicsouris
				changeJoueur();
				numero_coup += 1;
			}
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
				if (joueurs[joueurCourant].tempsEcoule()) {
					changeJoueur();
				} else {
				// Sinon on indique au joueur qui ne réagit pas au temps (humain) qu'on l'attend.
					System.out.println("On vous attend, joueur " + joueurs[joueurCourant].num());
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
