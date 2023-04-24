package Controlleur;

import Modele.Jeu;
import Vues.CollecteurEvenements;
import Vues.VueGaufre;

import static Modele.Niveau.MORCEAU_EMPOISONNE;

public class ControleurMediateur implements CollecteurEvenements {
    Jeu jeu;
    int decompte;
    final int lenteurAttente = 50;
	final int attendre = 2; //nombres de secondes d'attente pour l'IA avant de jouer


    public ControleurMediateur(Jeu j) {
        jeu = j;

        // while(!jeu.niveau.estTermine()){
		// 	System.out.println("Plateau :\n" + jeu.niveau.toString());


		// 	System.out.println("C'est au tour du joueur " + jeu.joueurCourant);
		// 	//if (jeu.joueurs[jeu.joueurCourant].jeu())//ICI donnez 2 arguments a jeu pour pouvoir récupérer lignes et colonnes avec clicsouris
		// 		//jeu.changeJoueur();
        // }

        // System.out.println("Le joueur " + jeu.joueurCourant + " a perdu");
        // //

    }

	public void attends(){
		try {
			Thread.sleep(attendre*1000); // Attendre 5 secondes
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void tictac(VueGaufre v) {
		//System.out.println("ICCI");
		
		if (v.IJ.J.enCours && !v.IJ.J.partieTerminee){
			//System.out.println("En cours : "+v.IJ.J.enCours+" partie terminee : "+v.IJ.J.partieTerminee);
			
			if (decompte == 0) {
				int type = v.IJ.J.type_joueur(v.IJ.J.joueurCourant);
				// Lorsque le temps est écoulé on le transmet au joueur courant.
				// Si un coup a été joué (IA) on change de joueur.
				switch(type){
					case -1:

						if (v.IJ.J.niveau.estTermine()){ //Pour vérifier qu'il ne reste pas que le morceau empoisoné pour l'humain, si c'est le cas, il a perdu
							v.IJ.J.setEnCours(false) ; //Pourquoi pas : v.IJ.J.enCours =false directement ?
							v.IJ.J.partieTerminee = true;
							v.IJ.J.joueurGagnant = (v.IJ.J.joueurCourant + 1) %2;
							System.out.println("ICIIIIIII");
						}else{
							System.out.println("On vous attend, joueur " + v.IJ.J.joueurCourant);
							decompte = lenteurAttente;
						}
						break;
					case 0:
							
						attends();
						//System.out.println("Jouer l'ia facile");
						IA_facile IA_facile = new IA_facile (v.IJ.J.joueurs[v.IJ.J.joueurCourant], v.IJ.J.niveau);
						if (!IA_facile.jeu()){ //cas ou on mange le morceau empoisonne donc joueur suivant gagnant
							v.IJ.J.setEnCours(false) ;
							v.IJ.J.partieTerminee = true;
							v.IJ.J.joueurGagnant = (v.IJ.J.joueurCourant +1) % 2;
							//v.IJ.J.changeJoueur();

						} else {
							if(!v.IJ.J.niveau.estTermine()) {
								v.IJ.J.changeJoueur();
							}
							else{
								v.IJ.J.partieTerminee = true;
								v.IJ.J.setEnCours(false) ;
								v.IJ.J.joueurGagnant = v.IJ.J.joueurCourant;
							}
							//v.IJ.J.changeJoueur();
						}
						break;
					case 1:
						attends();

						IA_moyen IA_moy = new IA_moyen (v.IJ.J.joueurs[v.IJ.J.joueurCourant], v.IJ.J.niveau);
						if (!IA_moy.jeu()){	//cas ou on mange le morceau empoisonne donc joueur suivant gagnant
							
							v.IJ.J.setEnCours(false) ;
							v.IJ.J.partieTerminee = true;
							v.IJ.J.joueurGagnant = (v.IJ.J.joueurCourant +1) %2;
							//v.IJ.J.changeJoueur();

						}
						 else {
							if(!v.IJ.J.niveau.estTermine()) {
								v.IJ.J.changeJoueur();
							}else{
								//System.out.println("ICIIII");
								v.IJ.J.partieTerminee = true;
								v.IJ.J.setEnCours(false) ;
								v.IJ.J.joueurGagnant = v.IJ.J.joueurCourant;
							}
							//v.IJ.J.changeJoueur();
						}
						break;
                    // case 2:
					// 	//TODO IA diff
                    default:
                        //TODO
                }
                //v.repaint();

                //System.out.println("Le joueur " + jeu.joueurCourant);


            } else {
                decompte--;
            }
        } else {
            v.IJ.J.partieTerminee = true;
        }
    }



// @Override
    // public void tictac(VueGaufre v) {
    //     //System.out.println("ICCI");
    //     boolean changer = false;

    //     if (v.IJ.J.enCours && !v.IJ.J.partieTerminee) {
    //         changer = false;
    //         //System.out.println("En cours : "+v.IJ.J.enCours+" partie terminee : "+v.IJ.J.partieTerminee);
    //         if (decompte == 0) {
    //             int type = v.IJ.J.type_joueur(v.IJ.J.joueurCourant);
    //             // Lorsque le temps est écoulé on le transmet au joueur courant.
    //             // Si un coup a été joué (IA) on change de joueur.
    //             switch (type) {
    //                 case -1:
    //                     if (v.IJ.J.niveau.estTermine()) { //Pour vérifier qu'il ne reste pas que le morceau empoisoné pour l'humain, si c'est le cas, il a perdu
    //                         v.IJ.J.setEnCours(false); //Pourquoi pas : v.IJ.J.enCours =false directement ?
    //                         v.IJ.J.partieTerminee = true;
    //                         v.IJ.J.joueurGagnant = v.IJ.J.joueurCourant;
    //                     } else {
    //                         System.out.println("On vous attend, joueur " + v.IJ.J.joueurCourant);
    //                         decompte = lenteurAttente;
    //                     }
    //                     break;
    //                 case 0:
    //                     //System.out.println("Jouer l'ia facile");
    //                     IA_facile IA_facile = new IA_facile(v.IJ.J.joueurs[v.IJ.J.joueurCourant], v.IJ.J.niveau);
    //                     if (!IA_facile.jeu()) {
    //                         v.IJ.J.setEnCours(false);
    //                         v.IJ.J.partieTerminee = true;
    //                         v.IJ.J.joueurGagnant = v.IJ.J.joueurCourant;
    //                     } else {
    //                         if(!v.IJ.J.niveau.estTermine()) {
    //                             v.IJ.J.changeJoueur();
    //                             changer = true;
    //                         }
    //                         //decompte = lenteurAttente;
    //                     }
    //                     if (v.IJ.J.niveau.estTermine()) {
    //                         v.IJ.J.partieTerminee = true;
    //                         if(v.IJ.J.niveau.contenu[0][0] != MORCEAU_EMPOISONNE) {
    //                             v.IJ.J.joueurGagnant = (v.IJ.J.joueurCourant + 1) % 2;
    //                         } else {
    //                             v.IJ.J.joueurGagnant = v.IJ.J.joueurCourant;
    //                         }
    //                         v.IJ.currentPlayer.setText("Partie terminée! Gagnant: " + (v.G.joueurs[v.G.joueurGagnant].getPlayerName()));
    //                     } else {
    //                         if(!v.IJ.J.partieTerminee){
    //                             if(!changer)
    //                                 v.IJ.J.changeJoueur();
    //                             v.IJ.currentPlayer.setText("Le joueur " + (v.G.getPlayer(v.G.getPlayer()).getPlayerName()) + (v.G.getPlayer(v.G.getPlayer()).isAI() ? " (AI)" : "") + " est en train de jouer");
    //                         }
    //                     }
    //                     v.repaint();
    //                     break;
    //                 case 1:
    //                     IA_moyen IA_moy = new IA_moyen(v.IJ.J.joueurs[v.IJ.J.joueurCourant], v.IJ.J.niveau);
    //                     if (!IA_moy.jeu()) {
    //                         v.IJ.J.setEnCours(false);
    //                         v.IJ.J.partieTerminee = true;
    //                         v.IJ.J.joueurGagnant = v.IJ.J.joueurCourant;
    //                     } else {
    //                         v.IJ.J.changeJoueur();
    //                     }
    //                     break;
	// case 2:
	// 				// 	//TODO IA diff
    //                 default:
    //                     //TODO
    //             }
    //             //v.repaint();

    //             //System.out.println("Le joueur " + jeu.joueurCourant);


    //         } else {
    //             decompte--;
    //         }
    //     } else {
    //         v.IJ.J.partieTerminee = true;
    //     }
    // }





    // @Override
    // public void clicSouris(int l, int c) {
	// 	// Lors d'un clic, on le transmet au joueur courant.
	// 	// Si un coup a effectivement été joué (humain, coup valide), on change de joueur.
	// 	if (joueurs[joueurCourant][typeJoueur[joueurCourant]].jeu(l, c))
	// 		changeJoueur();
    // }


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
