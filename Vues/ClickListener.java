package Vues;

import java.awt.Color;
import java.awt.event.*;
//import javax.swing.*;

import javax.swing.JOptionPane;

import Modele.Joueur;

public class ClickListener implements ActionListener{
    InterfaceGraphique Graphique;

    public ClickListener(InterfaceGraphique G){
        Graphique=G;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        InterfaceMenu M = ((InterfaceMenu) Graphique);
        if(e.getSource() == Graphique.play){
            if(M.getColonne().getForeground().equals(Color.gray) || M.getLigne().getForeground().equals(Color.gray) || Integer.parseInt(M.getLigne().getText()) > 30 || Integer.parseInt(M.getColonne().getText()) > 50){
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
                InterfaceJeu.demarrer(M.J);
            }
        }else if(e.getSource() == Graphique.quitter) {
            M.fermer();
        }else if(e.getSource() == Graphique.charger){
            // TODO
        }// TODO : Buttons Jeu
    }

}