import Modele.Jeu;
//import Vue.InterfaceMenu;

public class Gaufre {
    public static void main(String[] args) throws Exception {
        Jeu J = new Jeu();
        Jeu J = new Jeu(3);
        CollecteurEvenements control = new ControleurMediateur(j);
        //InterfaceMenu.demarrer(J);
    }
}