package Modele;

import java.util.Scanner;
public class Jeu {
	boolean enCours;
	Niveau niveau;
	int joueurCourant = 0;
	Pile return_pile;
	Pile avance_pile;
	int numTour = 0;

	public Jeu(int l, int c) {
		reset(l, c);
	}

	public void reset(int l, int c) {
		enCours = true;
		niveau = new Niveau(l, c);
		return_pile = new Pile( l* c);
		avance_pile = new Pile(l*c);
		
		
		Scanner scanner = new Scanner(System.in);
		// Affiche du plateau initial
		// Boucle de jeu
		while(!niveau.estTermine()) {
			System.out.println("Joueur " + (joueurCourant + 1));

			System.out.println("Plateau :\n" + niveau.toString());

			System.out.println("Tour " + numTour);

			System.out.println("Contenu de la pile de retour : " + return_pile.taille());



			// On est au premier tour
			if (numTour==0){
				Coup p = new Coup(niveau);
				if (niveau.jouer(p.caseChoisieL, p.caseChoisieC) == 1)
					break;
				else {
					//on cree un nouveau niveau equivalent a n avec une adresse differente et on l'empile
					Niveau n2 = niveau.clone();
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
					reset_Niveau();
				}
				//On veut continuer
				else{
					Coup p = new Coup(niveau);
					if (niveau.jouer(p.caseChoisieL, p.caseChoisieC) == 1)
						break;
					else {
						//on cree un nouveau niveau equivalent a n avec une adresse differente et on l'empile
						Niveau n2 = niveau.clone();
						return_pile.empiler(n2);
						joueurCourant = (joueurCourant + 1) % 2;
						//on depile toute la pile avance
						while(!avance_pile.estVide()){
							avance_pile.depiler();
						}
						numTour++;
					}

					
				}
			}
		}
		//metAJour();
	}

	public void reset_Niveau (){
		//on depile le niveau actuelle
		Niveau n3 = this.return_pile.depiler();
		//Si la pile est vide on remet le niveau initial
		if (this.return_pile.estVide()){
			this.niveau = new Niveau(n3.getLigne(), n3.getColonne());
			this.joueurCourant = (this.joueurCourant + 1) % 2;
			this.numTour--;
		}
		else{
		//on charge le niveau precedent
		this.niveau = this.return_pile.sommet();
		//on empile le niveau depile dans la pile avance
		this.avance_pile.empiler(n3);
		this.joueurCourant = (this.joueurCourant + 1) % 2;
		this.numTour--;
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
		this.numTour++;
	}

	public boolean enCours() {
		return enCours;
	}

}