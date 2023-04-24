import Modele.Jeu;

import Modele.MyLogger;


import Vues.*;

import java.util.logging.Logger;



//import Vue.InterfaceMenu;

public class Gaufre {
    public static void main(String[] args) throws Exception {
        // Mise en place du logger
        Logger logger = MyLogger.getLogger();

        logger.info("Nouvelle partie");
        Jeu j = new Jeu();
        InterfaceMenu.demarrer(j);

    }
}