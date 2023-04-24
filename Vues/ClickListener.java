package Vues;

import java.awt.Color;
import java.awt.event.*;
//import javax.swing.*;

import javax.swing.JOptionPane;

import Controlleur.ControleurMediateur;
import Modele.Jeu;
import Modele.Joueur;
import Modele.Niveau;

public class ClickListener implements ActionListener {
    InterfaceGraphique Graphique;
    int numeroInterface;


    public ClickListener(InterfaceGraphique G, int numInterface) {
        Graphique = G;
        numeroInterface = numInterface;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (numeroInterface == 0) {
            InterfaceMenu M = ((InterfaceMenu) Graphique);
            if (e.getSource() == Graphique.play) {
                if (M.getColonne().getForeground().equals(Color.gray) || M.getLigne().getForeground().equals(Color.gray) || Integer.parseInt(M.getLigne().getText()) > 30 || Integer.parseInt(M.getColonne().getText()) > 50) {
                    JOptionPane.showMessageDialog(M.frame, "Lis bien bouffon, la taille est incorrecte là");
                } else {
                    // Passe à l'interface Jeu
                    int l = Integer.parseInt(M.getLigne().getText());
                    int c = Integer.parseInt(M.getColonne().getText());

                    Joueur J1 = M.getJ1(), J2 = M.getJ2();

                    if (J1 == null || J2 == null) {
                        JOptionPane.showMessageDialog(M.frame, "Joueurs incorrects oww!");
                        return;
                    }

                    M.J.nouvellePartie(l, c, J1, J2);
                    M.fermer();
                    CollecteurEvenements control = new ControleurMediateur(M.J);

                    InterfaceJeu.demarrer(M.J, control, M);
                }
            }
        } else if (numeroInterface == 1) {
            InterfaceJeu IJ = ((InterfaceJeu) Graphique);
            if (e.getSource() == Graphique.charger) {
                // TODO
            } else if (e.getSource() == Graphique.menuPrincipal) {
                IJ.fermer();
                // Ouvrir l'interface de menu principale
                IJ.menu.frame.setVisible(true);
            } else if(e.getSource() == Graphique.nouvellePartie) {
                // Incrémenter le score
                if(IJ.J.partieTerminee())
                    IJ.J.incrementerScore(1);

                // Afficher le score sur l'interface de jeu
                IJ.setScoreLabel(IJ.J.getScore(0), IJ.J.getScore(1));

                // Prendre le joueur qui commence comme le perdant
                int perdant = IJ.J.getJoueurPerdant();

                // Lancer une nouvelle partie
                IJ.J.setPartieTerminee();
                IJ.J.nouvellePartie(IJ.J.niveau.getLigne(), IJ.J.niveau.getColonne(), IJ.J.getPlayer(0), IJ.J.getPlayer(1));
                IJ.J.setJoueurCourant(IJ.J.getJoueurPerdant());
                IJ.currentPlayer.setText("Le joueur " + IJ.J.getPlayer(IJ.J.getPlayer()).getPlayerName() + " commence !");
                // Rafraichir l'interface
                IJ.rafraichir();
            } else if(e.getSource() == Graphique.sauvegarder) {
                // TODO
            } else if(e.getSource() == Graphique.annuler) {
                //On dépile le niveau au sommet de la pile
                if (IJ.J.return_pile.estVide())
                    System.out.println("Aucun coup a annuler");
                else{
                    IJ.J.reset_Niveau();
                    IJ.J.retour = true;
                    //Niveau na = (IJ.J.reset_Niveau());
                    //IJ.J.avance_pile.empiler(na);
                    IJ.rafraichir_niveau();
                }
            } else if(e.getSource() == Graphique.refaire) {
                if (IJ.J.avance_pile.estVide())
                    System.out.println("Aucun coup a refaire");
                else{
                    //TODO Fonctionne pas
                    //IJ.J.avance_Niveau();
                }
            }
        }
        if (e.getSource() == Graphique.quitter) {
            Graphique.fermer();
        }
    }
}