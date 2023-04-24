package Modele;


import java.util.logging.Logger;

public class Jeu {

	Logger logger;
	public boolean enCours;
	public boolean partieTerminee;
	public Niveau niveau;

	// Partie joueurs
	public Joueur[] joueurs;
	int [] typeJoueur;
	public int joueurCourant;
	final int lenteurAttente = 50;
	int decompte;
	int [] score;
	public int joueurGagnant;
	public Pile return_pile;
	public Pile avance_pile;
	public boolean retour = false;
	public boolean avance = false;

	public void reset(int l, int c) {
		enCours = true;

		this.niveau= new Niveau(l, c);
		logger = MyLogger.getLogger();
		logger.info("nouvelle partie de taille " + l + " par " + c);
		this.return_pile = new Pile(l*c);
		this.avance_pile = new Pile(l*c);
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

	public void setEnCours(boolean b) {
        enCours = b;
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

		// On empile le niveau actuelle dans la pile retour
		Niveau n2 = niveau.clone();
		this.return_pile.empiler(n2);
		// On vide la pile avance
		this.avance_pile.vider(); 
		
		if (niveau.estTermine()) {
			partieTerminee = true;
			enCours = false;
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

	
	public void reset_Niveau (){
		//on depile le niveau actuelle
		System.out.println("Tentative d'annulation de niveau");
		Niveau n3 = this.return_pile.depiler();
		//Si la pile est vide on remet le niveau initial
		if (this.return_pile.estVide()){
			this.niveau = new Niveau(n3.getLigne(), n3.getColonne());
			this.joueurCourant = (this.joueurCourant + 1) % 2;
		}
		else{
		//on charge le niveau precedent
		this.niveau = this.return_pile.sommet();
		//on empile le niveau depile dans la pile avance
		this.avance_pile.empiler(n3);
		this.joueurCourant = (this.joueurCourant + 1) % 2;
		}
	}
	
	public void avance_Niveau(){
		//on depile le niveau actuelle
		Niveau n3 = this.avance_pile.depiler();
		//Si la pile est vide on ne fait rien
		if (this.avance_pile.estVide()){
			return;
		}
		//on charge le niveau precedent
		this.niveau = this.avance_pile.sommet();
		//on empile le niveau depile dans la pile avance
		this.return_pile.empiler(n3);
		this.joueurCourant = (this.joueurCourant + 1) % 2;
	}
}