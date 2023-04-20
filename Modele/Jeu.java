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
		// Boucle de jeu
		while(!n.estTermine()) {
			System.out.println("Plateau :\n" + n.toString());
			Coup p = new Coup(n);
			if (n.jouer(p.caseChoisieL, p.caseChoisieC) == 1)
				break;
			else {
				joueurCourant = (joueurCourant + 1) % 2;
			}
		}
		//metAJour();
	}

	public boolean enCours() {
		return enCours;
	}

}