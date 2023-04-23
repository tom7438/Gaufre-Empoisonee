package Vues;

import javax.swing.*;

import Controlleur.IA_facile;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Modele.*;

public class InterfaceMenu extends InterfaceGraphique {

    private JTextField T1;
    private JTextField T2;

    JTextField Ligne;
    JTextField Colonne;

    private JRadioButton[] b1;
    private JRadioButton[] b2;
    ButtonGroup GroupJ2;
    ButtonGroup GroupJ1;

    Jeu J;

    private InterfaceMenu(Jeu jeu) {
        this.J = jeu;
    }

    public static void demarrer(Jeu J) {
        SwingUtilities.invokeLater(new InterfaceMenu(J));
    }

    @Override
    public void run() {
        frame = new JFrame("Menu");
        frame.setSize(800, 280);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(790,275));
        frame.setVisible(true);

        Box boxFinal = Box.createVerticalBox();
        play = createButton("Jouer");
        charger = createButton("Charger une partie");
        quitter = createButton("Exit");

        ClickListener click = new ClickListener(this, 0);

        play.addActionListener(click);
        charger.addActionListener(click);
        quitter.addActionListener(click);

        // Ligne Joueur1
        JPanel pannelJ1 = new JPanel(new GridLayout(1,6));
        JLabel J1 = new JLabel(" Joueur 1");
        T1 = createText("Nom du joueur 1");
        J1.setLabelFor(T1);

        GroupJ1 = new ButtonGroup();
        b1 = new JRadioButton[4];
        b1[0] = new JRadioButton("Humain", false);
        b1[0].setActionCommand("-1");
        b1[1] = new JRadioButton("IA facile", true);
        b1[1].setActionCommand("0");
        b1[2] = new JRadioButton("IA moyenne", false);
        b1[2].setActionCommand("1");
        b1[3] = new JRadioButton("IA difficile", false);
        b1[3].setActionCommand("2");
        GroupJ1.add(b1[0]);GroupJ1.add(b1[1]);
        GroupJ1.add(b1[2]);GroupJ1.add(b1[3]);

        pannelJ1.add(J1);
        pannelJ1.add(T1);
        pannelJ1.add(b1[0]);
        pannelJ1.add(b1[1]);
        pannelJ1.add(b1[2]);
        pannelJ1.add(b1[3]);

        pannelJ1.setMaximumSize(new Dimension(1500,100));

        boxFinal.add(pannelJ1);

        // Ligne Joueur2
        JPanel pannelJ2 = new JPanel(new GridLayout(1,6));
        JLabel J2 = new JLabel(" Joueur 2 ");
        T2 = createText("Nom de joueur 2");
        J1.setLabelFor(T2);

        GroupJ2 = new ButtonGroup();
        b2 = new JRadioButton[4];
        b2[0] = new JRadioButton("Humain", false);
        b2[0].setActionCommand("-1");
        b2[1] = new JRadioButton("IA facile", true);
        b2[1].setActionCommand("0");
        b2[2] = new JRadioButton("IA moyenne", false);
        b2[2].setActionCommand("1");
        b2[3] = new JRadioButton("IA difficile", false);
        b2[3].setActionCommand("2");
        GroupJ2.add(b2[0]);GroupJ2.add(b2[1]);
        GroupJ2.add(b2[2]);GroupJ2.add(b2[3]);

        pannelJ2.add(J2);
        pannelJ2.add(T2);
        pannelJ2.add(b2[0]);
        pannelJ2.add(b2[1]);
        pannelJ2.add(b2[2]);
        pannelJ2.add(b2[3]);

        pannelJ2.setMaximumSize(new Dimension(1500,100));

        boxFinal.add(pannelJ2);

        // Ligne pour la taille de la gauffre
        Box SizePanel = Box.createVerticalBox();

        // Texte
        Box name = Box.createHorizontalBox();
        JLabel JSize = new JLabel("Taille de la gaufre (max 30 lignes et 50 colonnes)");
        name.add(JSize);
        SizePanel.add(name);

        // Box contenant les deux zones de texte (ligne(s) et colonne(s))
        Box Size = Box.createHorizontalBox();
        Ligne = createText("Ligne(s)");
        Ligne.setSize(new Dimension(100, 60));
        Ligne.setMaximumSize(new Dimension(100, 60));

        Colonne = createText("Colonne(s)");
        Colonne.setSize(new Dimension(100, 60));
        Colonne.setMaximumSize(new Dimension(100, 60));

        Size.add(Box.createRigidArea(new Dimension(310, 0)));
        Size.add(Ligne);
        Size.add(Box.createRigidArea(new Dimension(10, 0)));
        Size.add(Colonne);
        Size.add(Box.createRigidArea(new Dimension(310, 0)));

        SizePanel.add(Size);

        boxFinal.add(SizePanel);


        Box box1 = Box.createHorizontalBox();
        box1.add(play);
        boxFinal.add(box1);
        boxFinal.add(Box.createRigidArea(new Dimension(1,20)));

        Box box2 = Box.createHorizontalBox();
        box2.add(charger);
        boxFinal.add(box2);
        boxFinal.add(Box.createRigidArea(new Dimension(1,20)));

        Box box3 = Box.createHorizontalBox();
        box3.add(quitter);
        boxFinal.add(box3);

        this.frame.add(boxFinal);
    }

    // Permet de récupérer la taille de la gauffre
    public JTextField getLigne(){
        return Ligne;
    }

    public JTextField getColonne(){
        return Colonne;
    }

    // Permet de récupérer le Joueur/IA
    public Joueur getJ1() {
        if (T1.getText().compareTo("Nom du joueur 1") == 0) return null;
        if (this.AI(1)){
            return new IA_facile(T1.getText(), this.AI(1), this.getDifficult(1), J.niveau);
        }
        return new Joueur(T1.getText(), this.AI(1), this.getDifficult(1), J.niveau);
    }

    public Joueur getJ2() {
        if (T2.getText().compareTo("Nom du joueur 2") == 0) return null;
        return new Joueur(T2.getText(), this.AI(2), this.getDifficult(2), J.niveau);
    }

    public boolean AI(int joueur) {
        if (joueur == 1) {
            return !b1[0].isSelected();
        } else if (joueur == 2) {
            return !b2[0].isSelected();
        }
        return false;
    }

    public int getDifficult(int joueur) {
        if (joueur < 1 || joueur > 2) return -1;
        return Integer.parseInt((joueur == 1 ? this.GroupJ1 : this.GroupJ2).getSelection().getActionCommand());
    }

}