package Modele;

public class Jeu {
	boolean enCours;

	public Niveau niveau;
	public Pile return_pile;
	public Pile avance_pile;
	int joueurCourant;


	public Jeu(int l, int c) {
		reset(l, c);
	}

	public void reset(int l, int c) {
		enCours = true;

		this.niveau= new Niveau(l, c);
		this.return_pile = new Pile(l*c);
		this.avance_pile = new Pile(l*c);
		joueurCourant = 0;
	}

	public void reset_Niveau (){
		//on depile le niveau actuelle
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

	public boolean enCours() {
		return enCours;
	}

}