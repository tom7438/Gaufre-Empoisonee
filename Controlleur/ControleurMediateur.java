package Controlleur;

import Modele.Jeu;
import Vues.CollecteurEvenements;
import java.util.Scanner;


public class ControleurMediateur implements CollecteurEvenements {
	Jeu jeu;
	Joueur[] joueurs;
	int [] typeJoueur;
	int joueurCourant;
	final int lenteurAttente = 50;
	int decompte;
	int niveau_IA = 1; //1 pour facile 2 pour moyen 3 pour difficile
	int type_joueur =1; //0 pour Humain et 1 pour IA

	public int numero_coup=1;

	public ControleurMediateur(Jeu j) {
		jeu = j;
		joueurs = new Joueur[2];


		//affiche_type_default();
		for (int i=0; i<joueurs.length; i++){
			int type = demande_type(i);
			if (type == -1){
				joueurs[i] = new IA_facile(1, j.niveau); //par default;

			}
			else if(type == 0){
				joueurs[i] =new Humain(type, j.niveau);
			}
			else {
				niveau_IA = demande_niveau_IA();
				switch (niveau_IA){
					case 1:
						joueurs[i] = new IA_facile(1, j.niveau);
						break;
					case 2:
						joueurs[i] = new IA_moyen(2, j.niveau); 
						break;
					case 3:
						joueurs[i] = new IA_Difficile(3, j.niveau);
						break;

				}
			}
		}
		//type 1 = IA;
		//type 0 = Humain;
		// joueurs[0] = new IA_Difficile(1, j.niveau); 
		// joueurs[1] = new Humain(0, j.niveau); //DEFINIR le type du joueur
		//joueurs[1] = new IA_moyen(1, j.niveau); 

		//System.out.println("Le type " +joueurs[0].type()); 
		if(joueur0commence() == 1){
			joueurCourant = 1;
		}else{
			joueurCourant = 0;
		}

		while(!jeu.niveau.estTermine()){
			System.out.println("Plateau :\n" + jeu.niveau.toString());
			
			//On demande si le joeur veut retourner en arriere
			System.out.println("Voulez vous retourner en arriere ? (O/N)");
			Scanner s = new Scanner(System.in);
			String reponse = s.nextLine();
			if(reponse.equals("O")){
				jeu.reset_Niveau();
				changeJoueur();
				numero_coup -= 1;
				continue;
			}


			System.out.println("C'est au tour du joueur " + joueurCourant);
			if (joueurs[joueurCourant].jeu(numero_coup)){//ICI donnez 2 arguments a jeu pour pouvoir récupérer lignes et colonnes avec clicsouris
				//On sauvegarde le niveau après le coup
				jeu.return_pile.empiler(jeu.niveau.clone());
				changeJoueur();
				numero_coup += 1;
				//On vide la pile avancer
				jeu.avance_pile.vider();
			}
			else
				break;
		}

		System.out.println("Plateau :\n" + jeu.niveau.toString());
		System.out.println("Le joueur " + joueurCourant + " a perdu");
		//

	}


	// private void affiche_type_default(){
	// 	for (int i =0; i<2; i++){
	// 		String s ="";
	// 		if(joueurs[i].type == 0){
	// 			s += "Type 0 : HUMAIN ";
	// 		}
	// 		else{
	// 			s+="Type 1 : IA";
	// 		}
	// 		System.out.println("Le type du joueur " +i+ " est "  +s );
	// 	}
	// }

	public int joueur0commence(){
		Scanner s = new Scanner(System.in);
		int joueur;
		do{
			System.out.println("Indiquez si le joueur 0 commence ou le joueur 1");
			joueur = s.nextInt();
		}
		while(joueur < 0 || joueur > 1);
		return joueur;
	}

	public int demande_type(int num_joueur){
		Scanner s = new Scanner(System.in);
		int type;
		do{
		System.out.println("Entrez le numéro du type du joueur: " + num_joueur +"\n -1 = Par default || 0 = Humain || 1 = IA");
		type = s.nextInt();
		}
		while(type < -1 || type > 1);
		return type;
	}

	public int demande_niveau_IA(){
		Scanner s = new Scanner(System.in);
		int type;
		do{
		System.out.println("Entrez le niveau de l'IA \n 1 = Facile || 2 = Moyen || 3 = Difficile");
		type = s.nextInt();
		}
		while(type < 0 || type > 3);
		return type;
	}

	public void joue(){
		tictac();
	}
	// @Override
	// public void clicSouris(int l, int c) {
	// 	// Lors d'un clic, on le transmet au joueur courant.
	// 	// Si un coup a effectivement été joué (humain, coup valide), on change de joueur.
	// 	if (joueurs[joueurCourant][typeJoueur[joueurCourant]].jeu(l, c))
	// 		changeJoueur();
	// }

	void changeJoueur() {
		//joueurCourant = (joueurCourant + 1) % joueurs.length;
		joueurCourant = (joueurCourant + 1) % 2;
		decompte = lenteurAttente;

		//joue();
	}

	public void tictac() {
		if (jeu.enCours()) {
			if (decompte == 0) {
				int type = typeJoueur[joueurCourant];
				// Lorsque le temps est écoulé on le transmet au joueur courant.
				// Si un coup a été joué (IA) on change de joueur.
				if (joueurs[joueurCourant].tempsEcoule()) {
					changeJoueur();
				} else {
				// Sinon on indique au joueur qui ne réagit pas au temps (humain) qu'on l'attend.
					System.out.println("On vous attend, joueur " + joueurs[joueurCourant].num());
					decompte = lenteurAttente;
				}
			} else {
				decompte--;
			}
		}
	}

	// @Override
	// public void changeJoueur(int j, int t) {
	// 	System.out.println("Nouveau type " + t + " pour le joueur " + j);
	// 	typeJoueur[j] = t;
	// }

	// @Override
	// public void changeTaille(int t) {
	// 	System.out.println("Nouvelle taille " + t);
	// 	jeu.reset(t);
	// }
}
