package Controlleur;

import Modele.Jeu;
import Vues.CollecteurEvenements;
import Vues.VueGaufre;


public class ControleurMediateur implements CollecteurEvenements {
    Jeu jeu;
    int decompte;
    final int lenteurAttente = 50;
	final int attendre = 1; //nombres de secondes d'attente pour l'IA avant de jouer

    public int numero_coup=1;

    public ControleurMediateur(Jeu j) {
        jeu = j;
    }

	public void attends(){
		try {
			Thread.sleep(attendre*1000); // Attendre 2 secondes
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

    private void setFinpartie(VueGaufre v, int joueur){
        v.IJ.J.setEnCours(false) ;
		v.IJ.J.partieTerminee = true;
        v.IJ.J.joueurGagnant = (v.IJ.J.joueurCourant +joueur) % 2;
    }

	@Override
	public void tictac(VueGaufre v) {
		if (v.IJ.J.enCours && !v.IJ.J.partieTerminee){
			if (decompte == 0) {
				int type = v.IJ.J.type_joueur(v.IJ.J.joueurCourant);

				switch(type){
					case -1:
						if (v.IJ.J.niveau.estTermine()){ //Pour vérifier qu'il ne reste pas que le morceau empoisoné pour l'humain, si c'est le cas, il a perdu
                            setFinpartie(v, 1); //Valeur de joueur = 1 si le gagnant est le joueur suivant, 0 si c'est le joueur courant
						}else{
                            //System.out.println("Niveau courant \n" + v.IJ.J.niveau.toString()); //Affichage pour montrez le fonctionnement de annulé coup.
							System.out.println("On vous attend, joueur " + v.IJ.J.joueurCourant);
							decompte = lenteurAttente;
						}
						break;
					case 0:
						attends();
						IA_facile IA_facile = new IA_facile (v.IJ.J.joueurs[v.IJ.J.joueurCourant], v.IJ.J.niveau);
						if (!IA_facile.jeu()){ //cas ou on mange le morceau empoisonne donc joueur suivant gagnant
                            setFinpartie(v, 1); //Valeur de joueur = 1 si le gagnant est le joueur suivant, 0 si c'est le joueur courant
						} 
                        else{
							if(!v.IJ.J.niveau.estTermine())
								v.IJ.J.changeJoueur();
							else
                                setFinpartie(v, 0);
						}
						break;
					case 1:
						attends();

						IA_moyen IA_moy = new IA_moyen (v.IJ.J.joueurs[v.IJ.J.joueurCourant], v.IJ.J.niveau);
						if (!IA_moy.jeu())	//cas ou on mange le morceau empoisonne donc joueur suivant gagnant
                            setFinpartie(v, 1); //Valeur de joueur = 1 si le gagnant est le joueur suivant, 0 si c'est le joueur courant
						else {
							if(!v.IJ.J.niveau.estTermine()) {
								v.IJ.J.changeJoueur();
							}else
                                setFinpartie(v, 0); //Valeur de joueur = 1 si le gagnant est le joueur suivant, 0 si c'est le joueur courant
						}
						break;
                    case 2:
                        attends();
                        IA_Difficile IA_diff = new IA_Difficile (v.IJ.J.joueurs[v.IJ.J.joueurCourant], v.IJ.J.niveau);
                        if (!IA_diff.jeu()){ //cas ou on mange le morceau empoisonne donc joueur suivant gagnant
                            setFinpartie(v, 1); //Valeur de joueur = 1 si le gagnant est le joueur suivant, 0 si c'est le joueur courant
                        } 
                        else{
                            if(!v.IJ.J.niveau.estTermine())
                                v.IJ.J.changeJoueur();
                            else
                                setFinpartie(v, 0);
                        }
                        break;
					// 	//TODO IA diff
                    default:
                        //TODO
                }
            } else {
                decompte--;
            }
        } else {
            v.IJ.J.partieTerminee = true;
        }
    }
}
