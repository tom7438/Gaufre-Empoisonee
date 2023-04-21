import Modele.Jeu;
import Controlleur.ControleurMediateur;

import Modele.MyLogger;
import Vues.CollecteurEvenements;

import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


//import Vue.InterfaceMenu;

public class Gaufre {
    public static void main(String[] args) throws Exception {
        boolean rejouer = true;

        // Mise en place du logger
        Logger logger = MyLogger.getLogger();

        while(rejouer) {
            logger.info("Nouvelle partie");
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