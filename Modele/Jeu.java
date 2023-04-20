package Modele;

public class Jeu {
	boolean enCours;
	int[][] plateau;
	int joueurCourant;

	public Jeu(int l, int c) {
		reset(l, c);
	}

	public void reset(int l, int c) {
		plateau = new int[l][c];
		enCours = true;
		Niveau n = new Niveau(l, c);
		joueurCourant = 0;
		// Affiche du plateau initial
		System.out.println("Plateau initial :\n" + n.toString());
		//metAJour();
	}

    public int valeur(int i, int j) {
		return plateau[i][j];
	}

	public boolean enCours() {
		return enCours;
	}

	public int largeur() {
		return plateau[0].length;
	}

	public int hauteur() {
		return plateau.length;
	}
}