import Modele.Jeu;
import Controlleur.ControleurMediateur;

import Vues.CollecteurEvenements;



//import Vue.InterfaceMenu;

public class Gaufre {
    public static void main(String[] args) throws Exception {
        Jeu j = new Jeu(3, 3);
        CollecteurEvenements control = new ControleurMediateur(j);
        //InterfaceMenu.demarrer(J,control);
    }
}