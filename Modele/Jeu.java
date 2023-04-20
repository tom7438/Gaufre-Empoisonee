package Modele

public class Jeu {
    public Jeu() {
        System.out.println("Jeu");
    }
}
package Modele;

public class Jeu {
	boolean enCours;
	int[][] plateau;
	int joueurCourant;

	public Jeu(int n) {
		reset(n);
	}

	public void reset(int n) {
		plateau = new int[n][n];
		enCours = true;
		for (int i = 0; i < plateau.length; i++)
			for (int j = 0; j < plateau[0].length; j++)
				plateau[i][j] = 1;
        //plateau[0][0] = 2;// definis la case empoisonée
		joueurCourant = 0;
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