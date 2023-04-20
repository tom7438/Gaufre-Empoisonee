import Modele.Jeu;
import Controlleur.ControleurMediateur;

import Vues.CollecteurEvenements;

import java.util.Scanner;


//import Vue.InterfaceMenu;

public class Gaufre {
    public static void main(String[] args) throws Exception {
        // Demander le nombre de lignes et de colonnes
        Scanner Scanner = new Scanner(System.in);
        System.out.println("Entrez le nombre de lignes");
        int nblignes = Scanner.nextInt();
        System.out.println("Entrez le nombre de colonnes");
        int nbcolonnes = Scanner.nextInt();
        Jeu j = new Jeu(nblignes, nbcolonnes);
        CollecteurEvenements control = new ControleurMediateur(j);
        //InterfaceMenu.demarrer(J,control);
    }
}