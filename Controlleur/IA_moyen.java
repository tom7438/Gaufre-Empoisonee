package Controlleur;

import java.util.Random;

import javax.sound.sampled.FloatControl.Type;

import Modele.Niveau;


class IA_moyen extends Joueur {
	Random r;

	public IA_moyen(int type, Niveau n) {
		super(type, n);
		r = new Random();
	}

	@Override
    boolean jeu(){  
		int i, j;
		int ok;
		do{
			i = r.nextInt(niveau.getLigne());
			j = r.nextInt(niveau.getColonne());
			if (!(i == 0 && j == 0 && (niveau.aMorceau(i+1,0) || niveau.aMorceau(0, i+1)))){//si l'IA mange l'empoisonné alors 
				ok = niveau.jouer(i, j);
			}
			ok = 2;
		}while(ok == 2); //pas de morceau sur cette case
	
		System.out.println("L'IA a joué le coup : (" + i + "," + j + ")");

		if (ok == 0){
            return true;
        }
		
		return false;

	}

}