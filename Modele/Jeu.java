package Modele;

public class Jeu {
	boolean enCours;
	public Niveau niveau;
	int joueurCourant;

	public Jeu(int l, int c) {
		reset(l, c);
	}

	public void reset(int l, int c) {
		enCours = true;
		this.niveau= new Niveau(l, c);
		joueurCourant = 0;
	}

	public boolean enCours() {
		return enCours;
	}

}