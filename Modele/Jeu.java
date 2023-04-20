package Modele;

import java.util.Scanner;
public class Jeu {
	boolean enCours;
	Niveau niveau;
	int joueurCourant;

	public Jeu(int l, int c) {
		reset(l, c);
	}

	public void reset(int l, int c) {
		enCours = true;
		Pile return_pile = new Pile(l * c);
		Pile avance_pile = new Pile(l * c);
		Niveau n = new Niveau(l, c);
		joueurCourant = 0;
		int numTour = 0;
		Scanner scanner = new Scanner(System.in);
		// Affiche du plateau initial
		// Boucle de jeu
		while(!n.estTermine()) {
			System.out.println("Joueur " + (joueurCourant + 1));

			System.out.println("Plateau :\n" + n.toString());

			System.out.println("Tour " + numTour);
			// On est au premier tour
			if (numTour==0){
				Coup p = new Coup(n);
				if (n.jouer(p.caseChoisieL, p.caseChoisieC) == 1)
					break;
				else {
					//on cree un nouveau niveau equivalent a n avec une adresse differente et on l'empile
					Niveau n2 = n.clone();
					return_pile.empiler(n2);
					joueurCourant = (joueurCourant + 1) % 2;
				}
				numTour++;	
			}
			else{
				System.out.println("voulez vous retourner un morceau ? (o/n)");
				char reponse = scanner.next().charAt(0);
				// On veut retourner en arriere
				if(reponse == 'o' ){
					//on depile le niveau actuelle
					Niveau n3 = return_pile.depiler();
					//on charge le niveau precedent
					n = return_pile.sommet();
					//on empile le niveau depile dans la pile avance
					avance_pile.empiler(n3);
					//on change le joueur courant
					joueurCourant = (joueurCourant + 1) % 2;
					// on change le tour
					numTour--;
				}
				//On veut continuer
				else{
					Coup p = new Coup(n);
					if (n.jouer(p.caseChoisieL, p.caseChoisieC) == 1)
						break;
					else {
						//on cree un nouveau niveau equivalent a n avec une adresse differente et on l'empile
						Niveau n2 = n.clone();
						return_pile.empiler(n2);
						joueurCourant = (joueurCourant + 1) % 2;
						//on depile toute la pile avance
						while(!avance_pile.estVide()){
							avance_pile.depiler();
						}
					}

					numTour++;
				}
			}
		}
		//metAJour();
	}

	public boolean enCours() {
		return enCours;
	}

}