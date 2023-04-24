import Modele.Jeu;

import Vues.CollecteurEvenements;

import java.util.Scanner;


//import Vue.InterfaceMenu;

public class Gaufre {
    public static void main(String[] args) throws Exception {
        boolean rejouer = true;
        while(rejouer) {
            // Demander le nombre de lignes et de colonnes
            Scanner Scanner = new Scanner(System.in);
            System.out.println("Entrez le nombre de lignes");
            int nblignes = Scanner.nextInt();
            System.out.println("Entrez le nombre de colonnes");
            int nbcolonnes = Scanner.nextInt();
            Jeu j = new Jeu(nblignes, nbcolonnes);
            CollecteurEvenements control = new ControleurMediateur(j);
            //InterfaceMenu.demarrer(J,control);
            System.out.println("Voulez-vous rejouer ? (O/N)");
            char res = Scanner.hasNext() ? Scanner.next().charAt(0) : 'N';
            rejouer = (res == 'O' || res == 'o');
        }
        System.out.println("Au revoir !");
    }
}