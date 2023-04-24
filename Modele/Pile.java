package Modele;


public class Pile {
    //On crée une pile du niveau
    private Niveau[] pile;
    // On crée un sommet de la pile
    private int sommet;
    // On crée une taille de la pile
    private int taille;

    //On crée une pile de taille taille
    public Pile(int taille) {
        this.taille = taille;
        this.pile = new Niveau[taille];
        this.sommet = -1;
    }

    //On verifie si la pile est vide
    public boolean estVide() {
        return sommet == -1;
    }

    //On verifie si la pile est pleine
    public boolean estPleine() {
        return sommet == taille - 1;
    }

    //On empile un niveau dans la pile
    public void empiler(Niveau n) {
        if (estPleine()) {
            throw new RuntimeException("La pile est pleine");
        }
        sommet++;
        pile[sommet] = n.clone();
    }

    //On depile un niveau de la pile
    public Niveau depiler() {
        if (estVide()) {
            throw new RuntimeException("La pile est vide");
        }
        Niveau n = pile[sommet];
        sommet--;
        return n;
    }

    //On retourne le sommet de la pile
    public Niveau sommet() {
        if (estVide()) {
            throw new RuntimeException("La pile est vide");
        }
        return pile[sommet];
    }

    //On retourne la taille de la pile
    public int taille() {
        return sommet + 1;
    }

    //On vide la pile
    public void vider() {
        while (!estVide()) {
            depiler();
        }
        sommet = -1;
    }

    //On renvooie le niveau i de la pile
    public Niveau getNiveau(int i) {
        if (!estVide()){
            return pile[i];
        }
        return null;
    }

}
