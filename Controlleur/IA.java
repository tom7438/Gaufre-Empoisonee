package Controlleur;

import java.util.Random;
import Modele.Niveau;


//ICI j'ai copié l'IA moyenne, TODO : savoir comment changer de niveau d'IA
class IA extends Joueur {
	Random r;

	public IA(int num, Niveau n) {
		super(num, n);
		r = new Random();
	}

	@Override
    boolean jeu(int num){
		int i, j;
		int ok;
		do{
			do{
				i = r.nextInt(niveau.getLigne());
				j = r.nextInt(niveau.getColonne());
				ok = niveau.jouer(i, j);
			}while(ok == 2); //pas de morceau sur cette case

			if(i != 0 || j != 0 ) 
				break;//Sinon on boucle jusqu'a avoir un endroit non empoisonné
				
		}while (niveau.aMorceau(i+1,0) || niveau.aMorceau(0, i+1)); //si l'IA mange l'empoisonné alors 
	
		System.out.println("L'IA a joué le coup : (" + i + "," + j + ")");
		System.out.println("ICIIIII " + i + "," + j);

		return ok == 0;

	}

}