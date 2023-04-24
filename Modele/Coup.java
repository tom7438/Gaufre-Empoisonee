package Modele;

import java.util.Scanner;

public class Coup {
    public int caseChoisieL;
    public int caseChoisieC;
    public int joueurCourant;

    public Niveau niveau;
    public Coup(Niveau n) {
        this.niveau = n;
        Scanner Scanner = new Scanner(System.in);
        do {
            System.out.println("Choisir les coordonnées de la case à jouer (l, c)");
            this.caseChoisieL = Scanner.nextInt();
            this.caseChoisieC = Scanner.nextInt();
        } while(this.caseChoisieL < 0 || this.caseChoisieL >= n.getLigne() || this.caseChoisieC < 0 || this.caseChoisieC >= n.getColonne());

    }

}
