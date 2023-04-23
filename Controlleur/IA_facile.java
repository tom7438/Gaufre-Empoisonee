package Controlleur;

import java.util.Random;

import Modele.Joueur;
import Modele.Niveau;


public class IA_facile extends Joueur {
	Random r;

	public IA_facile(String name, boolean AI, int diff, Niveau n) {
		super(name, AI, diff, n);
		r = new Random();
	}

    public IA_facile(Joueur joueur, Niveau niv) {
		super(joueur.getPlayerName(), true, joueur.getDifficulteIA(), niv);
		r = new Random();
    }

    public boolean jeu(){
		int i, j;
		int ok;
		do{
			i = r.nextInt(niveau.getLigne());
			j = r.nextInt(niveau.getColonne());
			ok = niveau.jouer(i, j);
		}while(ok == 2); //pas de morceau sur cette case

		
		if (ok == 0){
			System.out.println("L'IA_facile a joué le coup : (" + i + "," + j + ")");
            return true;
        }
		else if(ok == 1 && (niveau.aMorceau(i+1,0) || niveau.aMorceau(0, i+1))){
			System.out.println("L'IA facile a mangé la case (0,0) alors qu'il lui restait des possibilités");
		}
		

		return false; //On a mangé le morceau empisonné

	}

	@Override
	public boolean tempsEcoule() {
        return true;

    }
}