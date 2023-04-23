package Controlleur;

import Modele.Jeu;
import Vues.CollecteurEvenements;

public class ControleurMediateur implements CollecteurEvenements {
	Jeu jeu;
	int decompte;

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

	@Override
	public void tictac() {
		//System.out.println("ICCI");
		if (jeu.enCours()) {
			if (decompte == 0) {
				int type = jeu.type_joueur(jeu.joueurCourant);
				// Lorsque le temps est écoulé on le transmet au joueur courant.
				// Si un coup a été joué (IA) on change de joueur.
				switch(type){
					case -1:
						break;
					case 0:
						//System.out.println("Jouer l'ia facile");
						IA_facile IA = new IA_facile (jeu.joueurs[jeu.joueurCourant], jeu.niveau);
						IA.jeu();
						jeu.changeJoueur();
					default:
						//TODO
				}
				// if (joueurs[joueurCourant][type].tempsEcoule()) {
				// 	changeJoueur();
				// } else {
				// // Sinon on indique au joueur qui ne réagit pas au temps (humain) qu'on l'attend.
				// 	System.out.println("On vous attend, joueur " + joueurs[joueurCourant][type].num());
				// 	decompte = lenteurAttente;
				// }
			} else {
				decompte--;
			}
		}
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
