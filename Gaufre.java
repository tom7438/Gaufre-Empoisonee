import Modele.Jeu;
import Controlleur.ControleurMediateur;

import Vues.*;

import java.util.Scanner;


//import Vue.InterfaceMenu;

public class Gaufre {
    public static void main(String[] args) throws Exception {
        Jeu j = new Jeu();
        InterfaceMenu.demarrer(j);
    }
}