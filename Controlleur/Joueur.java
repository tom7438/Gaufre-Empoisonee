package Controlleur;

import Modele.Jeu;

public class Joueur {
    int numeroJoueur;
    Jeu plateau;
    public Joueur(int n, Jeu p) {
        numeroJoueur = n;
        plateau = p;
    }
}
