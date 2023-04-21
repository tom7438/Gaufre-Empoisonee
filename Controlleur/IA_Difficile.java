package Controlleur;

import Modele.Niveau;

public class IA_Difficile extends Joueur{

    IA_Difficile(int n, Niveau p) {
        super(n, p);
        //TODO Auto-generated constructor stub
    }

    @Override
    boolean jeu(int num){
        int i,j;
        int max = 0;

        int info_max = -1; //ici on va mettre a 1 si le max est sur la ligne, 0 si c'est sur la colonne

        System.out.println("Numero de coup " +num);
        if (niveau.getLigne() == niveau.getColonne()){
            if(num == 1){
                i = 1;
                j = 1;
                niveau.jouer(i,j); 
                return true;
            }
            if (num >= 3){
                for(i=niveau.getLigne() -1; i==1; i--){
                    if (niveau.aMorceau(i, 0)){
                        max = i;
                        info_max = 1;
                        System.out.println("Max_lignes : " +max);

                        break;
                    }
                }

                for(i=niveau.getColonne()-1; i==1; i--){
                    if (niveau.aMorceau(0, i)){
                        if (i>max)
                            max = i;
                            info_max = 0;
                            System.out.println("Max_colonnes : " +max);

                        break;
                    }
                    if (i<max) //Inutile d'aller chercher un max plus bas
                        break; 
                }


                if (info_max == 1){
                    niveau.jouer(max, 0);
                    return true;
                }
                else{
                    niveau.jouer(0, max);
                    return true;
                }
            }

        }
        return false;

    }


    
}
