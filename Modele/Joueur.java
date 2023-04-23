package Modele;

public class Joueur {

    boolean isAI;
    String name;
    public Niveau niveau;

    int difficulteIA; // 0 = facile, 1 = moyen, 2 difficile

    public Joueur(String N, boolean AI, int diff, Niveau n) {
        this.name = N;
        this.isAI = AI;
        difficulteIA=diff;
        this.niveau = n;
    }

    // Getters

    public String getPlayerName() {
        return this.name;
    }

    public boolean isAI() {
        return isAI;
    }

    public int getDifficulteIA(){
        return difficulteIA;
    }

    public boolean tempsEcoule() {
        return false;
    }

    public boolean jeu(){
        System.out.println("ICIIII");
        return false;
    }


}