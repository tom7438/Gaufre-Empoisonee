package Modele;

public class Jeu {
	boolean enCours;
	Niveau niveau;
	int joueurCourant;

	public Jeu(int l, int c) {
		reset(l, c);
	}

	public void reset(int l, int c) {
		enCours = true;
		Niveau n = new Niveau(l, c);
		joueurCourant = 0;
		// Affiche du plateau initial
		System.out.println("Plateau initial :\n" + n.toString());
		// Boucle de jeu
		while(!n.estTermine()) {
			Coup p = new Coup(n);
			n.jouer(p.caseChoisieL, p.caseChoisieC);
			// Affiche du plateau après le premier coup
			System.out.println("Plateau après le premier coup :\n" + n.toString());
		}
		//metAJour();
	}

	public boolean enCours() {
		return enCours;
	}

}