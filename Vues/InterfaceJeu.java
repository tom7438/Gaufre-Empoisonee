package Vues;

import Modele.Jeu;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class InterfaceJeu extends InterfaceGraphique{
    Jeu J;
    private JButton play;
    private JButton charger;
    private JButton annuler;
    private JButton refaire;
    private JButton sauvegarder;
    private JButton nouvellePartie;
    private JButton menuPrincipal;
    private JButton quitter;
    private JLabel score;

    private JPanel controlsPanel;
    public JLabel currentPlayer;
    private JPanel gaufrePanel;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    public InterfaceJeu(Jeu j) {
        this.J = j;
    }

    public static void demarrer(Jeu j) {
        SwingUtilities.invokeLater(new InterfaceJeu(j));
    }

    @Override
    public void run() {
        frame = new JFrame("Gaufre empoisonnée !");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Taille minimum de la fenêtre (pour le redimensionnement)
        frame.setMinimumSize(new Dimension(800,600));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        //frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        // Vérification des noms et taille
        System.out.println("Taille de la gauffre : (" + J.niveau.getLigne() + "," + J.niveau.getColonne() + ")");

        // Création du panel de contrôle
        controlsPanel = new JPanel();
        jPanel1 = new JPanel();
        jPanel2 = new JPanel();
        jPanel3 = new JPanel();
        gaufrePanel = new JPanel();
        currentPlayer = new JLabel("");

        charger = createButton("Charger");
        annuler = createButton("<");
        refaire = createButton(">");
        sauvegarder = createButton("Sauvegarder");
        nouvellePartie = createButton("Nouvelle\nPartie");
        menuPrincipal = createButton("Menu Principal");
        quitter = createButton("Exit");
        // TODO : score si plusieurs parties
        score = new JLabel("Score : ");
        score.setHorizontalAlignment(JLabel.CENTER);

        jPanel3.setLayout(new BorderLayout());
        gaufrePanel.setLayout(new BorderLayout());

        // Init du jeu
        VueGaufre vueGaufre = new VueGaufre(J, this);
        vueGaufre.addMouseListener(new GaufreListener());
        gaufrePanel.add(vueGaufre);

        jPanel3.add(gaufrePanel, BorderLayout.CENTER);

        // Panel de contrôle
        controlsPanel.setPreferredSize(new Dimension(200, 316));
        controlsPanel.setLayout(new GridLayout(4, 1, 0, 20));

        controlsPanel.add(score);

        JPanel annulerRefairePanel = new JPanel();
        annulerRefairePanel.setLayout(new BoxLayout(annulerRefairePanel, BoxLayout.X_AXIS));
        annulerRefairePanel.add(Box.createRigidArea(new Dimension(10,0))); // Espacement à gauche des boutons
        annulerRefairePanel.add(annuler);
        annulerRefairePanel.add(Box.createRigidArea(new Dimension(50,0))); // Espacement entre les boutons
        annulerRefairePanel.add(refaire);
        annulerRefairePanel.add(Box.createRigidArea(new Dimension(10,0))); // Espacement à droite des boutons
        controlsPanel.add(annulerRefairePanel);

        JPanel sauvegarderNouvellePartiePanel = new JPanel(new GridLayout(1, 2));
        sauvegarderNouvellePartiePanel.add(sauvegarder);
        sauvegarderNouvellePartiePanel.add(nouvellePartie);
        controlsPanel.add(sauvegarderNouvellePartiePanel);

        quitter.addMouseListener(new MouseInputAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                fermer();
            }
        });
        controlsPanel.add(quitter);

        // Taille minimale du panel de contrôle
        controlsPanel.setMinimumSize(new Dimension(200, 316));

        jPanel3.add(controlsPanel, BorderLayout.EAST);

        this.frame.getContentPane().add(jPanel3, BorderLayout.CENTER);

        jPanel2.setBackground(new Color(102, 102, 102));
        jPanel2.setPreferredSize(new Dimension(690, 30));
        jPanel2.setLayout(new BorderLayout());

        currentPlayer.setHorizontalAlignment(SwingConstants.CENTER);
        currentPlayer.setText("Le joueur " + J.getPlayer(J.getPlayer()).getPlayerName() + " joue");

        jPanel2.add(currentPlayer, BorderLayout.CENTER);

        this.frame.getContentPane().add(jPanel2, BorderLayout.NORTH);

        this.frame.setVisible(true);
    }
}
