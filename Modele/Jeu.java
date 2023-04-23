package Modele;

import Controlleur.*;

public class Jeu {
	boolean enCours;
	boolean partieTerminee;
	public Niveau niveau;

	// Partie joueurs
	public Joueur[] joueurs;
	int [] typeJoueur;
	public int joueurCourant;
	final int lenteurAttente = 50;
	int decompte;
	int [] score;
	int joueurGagnant;

	public void reset(int l, int c) {
		enCours = true;
		this.niveau= new Niveau(l, c);
		joueurCourant = 0;
	}

	/**
	 * Crée une nouvelle partie de taille par défaut
	 */
	public void nouvellePartie() {
		nouvellePartie(Niveau.LIGNES_PAR_DEFAUT, Niveau.COLONNES_PAR_DEFAUT, new Joueur("Player 1", false, 0, niveau), new Joueur("Player 2", false, 0, niveau));
	}

	public void nouvellePartie(int l, int c, Joueur Joueur1, Joueur Joueur2) {
		reset(l, c);
		if(score == null) {
			score = new int[2];
		}
		joueurs = new Joueur[2];
		joueurs[0] = Joueur1;
		joueurs[1] = Joueur2;
	}

	public boolean enCours() {
		return enCours;
	}
	public boolean partieTerminee() {
		return partieTerminee;
	}

	public boolean coup(int l, int c) {
		if (niveau == null) {
			throw new IllegalStateException("Aucun niveau auquel jouer");
		}
		enCours = true;

		if (!niveau.aMorceau(l, c)) {
			return false;
		}
		niveau.effacerRectangle(l, c);

		if (niveau.estTermine()) {
			partieTerminee = true;
			joueurGagnant = joueurCourant;
		} else {
			changeJoueur();
		}
		return true;
	}
	/**
	 * Effectue le changement de joueur
	 */
	public void changeJoueur() {
		//joueurCourant = (joueurCourant + 1) % joueurs.length;
		joueurCourant = (joueurCourant + 1) % 2;
		decompte = lenteurAttente;
	}

	public void tictac() {
		System.out.println("ICIII");
		if (this.enCours()) {
			if (decompte == 0) {
				int type = typeJoueur[joueurCourant];
				// Lorsque le temps est écoulé on le transmet au joueur courant.
				// Si un coup a été joué (IA) on change de joueur.
				if (joueurs[joueurCourant].tempsEcoule()) {
					changeJoueur();
				} else {
					// Sinon on indique au joueur qui ne réagit pas au temps (humain) qu'on l'attend.
					System.out.println("On vous attend, joueur " + joueurs[joueurCourant].getPlayerName());
					decompte = lenteurAttente;
				}
			} else {
				decompte--;
			}
		}
	}

	public int type_joueur(int num_j){
		return joueurs[num_j].difficulteIA;
	}

	public Joueur getPlayer(int i) {
		return joueurs[i % 2];
	}

	/**
	 * Retourne le joueur qui joue
	 * @return 0 ou 1
	 */
	public int getPlayer() {
		return joueurCourant;
	}

	public void joue(){
		tictac();
	}

	public void incrementerScore(int i){
		score[joueurGagnant] += i;
	}

	public void initialiserScore(){
		score[0] = 0;
		score[1] = 0;
	}

	public int getScore(int numeroJoueur){
		return score[numeroJoueur];
	}

	public void setJoueurCourant(int i){
		joueurCourant = i;
	}

	public int getJoueurPerdant(){
		return joueurGagnant == 1 ? 0 : 1;
	}

	public void setPartieTerminee() {
		partieTerminee = false;
	}
}