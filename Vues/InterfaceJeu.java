package Vues;

import Modele.Jeu;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;

public class InterfaceJeu extends InterfaceGraphique{
    public Jeu J;
    public JLabel currentPlayer;
    JPanel controlsPanel;
    JPanel jPanel2;
    JPanel jPanel3;
    JPanel gaufrePanel;
    JPanel annulerRefairePanel;
    JPanel sauvegarderNouvellePartiePanel;
    JPanel quitterPanel;
    CollecteurEvenements control;
    Timer chrono;

    public InterfaceJeu(Jeu j, CollecteurEvenements c) {
        this.J = j;
        control = c;
    }

    public static void demarrer(Jeu j, CollecteurEvenements control) {
        SwingUtilities.invokeLater(new InterfaceJeu(j, control));
    }

    @Override
    public void run() {
        frame = new JFrame("Gaufre empoisonnée !");
        frame.setSize(700, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Taille minimum de la fenêtre (pour le redimensionnement)
        frame.setMinimumSize(new Dimension(700,450));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        //frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        // Vérification des noms et taille
        System.out.println("Taille de la gauffre : (" + J.niveau.getLigne() + "," + J.niveau.getColonne() + ")");

        // Création du panel de contrôle
        controlsPanel = new JPanel();
        // Taille minimale du panel de contrôle
        controlsPanel.setMinimumSize(new Dimension(300, 0));
        jPanel2 = new JPanel();
        jPanel3 = new JPanel();
        gaufrePanel = new JPanel();
        currentPlayer = new JLabel("");

        ClickListener click = new ClickListener(this, 1);

        charger = createButton("Charger");
        annuler = createButton("<");
        refaire = createButton(">");
        sauvegarder = createButton("Sauvegarder");
        sauvegarder.setMinimumSize(new Dimension(150, 50));
        nouvellePartie = createButton("Nouvelle Partie");
        nouvellePartie.setMinimumSize(new Dimension(150, 50));
        menuPrincipal = createButton("Menu Principal");
        quitter = createButton("Exit");
        score = new JLabel("Score : ");
        score.setHorizontalAlignment(JLabel.CENTER);

        // Ajout des listeners
        charger.addActionListener(click);
        annuler.addActionListener(click);
        refaire.addActionListener(click);
        sauvegarder.addActionListener(click);
        nouvellePartie.addActionListener(click);
        menuPrincipal.addActionListener(click);
        //quitter.addActionListener(click);

        jPanel3.setLayout(new BorderLayout());
        gaufrePanel.setLayout(new BorderLayout());

        // Init du jeu
        VueGaufre vueGaufre = new VueGaufre(J, this);
        vueGaufre.addMouseListener(new GaufreListener());
        gaufrePanel.add(vueGaufre);

        jPanel3.add(gaufrePanel, BorderLayout.CENTER);

        // Panel de contrôle
        controlsPanel.setPreferredSize(new Dimension(300, 316));
        controlsPanel.setLayout(new GridLayout(4, 1, 0, 20));

        controlsPanel.add(score);

        annulerRefairePanel = new JPanel();
        annulerRefairePanel.setLayout(new BoxLayout(annulerRefairePanel, BoxLayout.X_AXIS));
        annulerRefairePanel.add(Box.createRigidArea(new Dimension(10,0))); // Espacement à gauche des boutons
        annulerRefairePanel.add(annuler);
        annulerRefairePanel.add(Box.createRigidArea(new Dimension(50,0))); // Espacement entre les boutons
        annulerRefairePanel.add(refaire);
        annulerRefairePanel.add(Box.createRigidArea(new Dimension(10,0))); // Espacement à droite des boutons
        annulerRefairePanel.setMinimumSize(new Dimension(400, 50));
        controlsPanel.add(annulerRefairePanel);

        sauvegarderNouvellePartiePanel = new JPanel(new GridLayout(1, 2));
        sauvegarderNouvellePartiePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        sauvegarderNouvellePartiePanel.setLayout(new BoxLayout(sauvegarderNouvellePartiePanel, BoxLayout.X_AXIS));
        sauvegarderNouvellePartiePanel.add(Box.createRigidArea(new Dimension(10,0))); // Espacement à gauche des boutons
        sauvegarderNouvellePartiePanel.add(sauvegarder);
        sauvegarderNouvellePartiePanel.add(Box.createRigidArea(new Dimension(10,0))); // Espacement entre les boutons
        sauvegarderNouvellePartiePanel.add(nouvellePartie);
        sauvegarderNouvellePartiePanel.add(Box.createRigidArea(new Dimension(10,0))); // Espacement à droite des boutons
        controlsPanel.add(sauvegarderNouvellePartiePanel);

        quitter.addMouseListener(new MouseInputAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                chrono.stop();
                fermer();
            }
        });
        quitter.setMinimumSize(new Dimension(50, 300));
        quitter.setPreferredSize(new Dimension(50, 30));
        quitterPanel = new JPanel(new GridLayout(1, 2));
        quitterPanel.setLayout(new BoxLayout(quitterPanel, BoxLayout.X_AXIS));
        quitterPanel.add(Box.createRigidArea(new Dimension(10,0))); // Espacement à gauche des boutons
        quitterPanel.add(quitter);
        quitterPanel.add(Box.createRigidArea(new Dimension(50,0))); // Espacement entre les boutons
        quitterPanel.add(menuPrincipal);
        quitterPanel.add(Box.createRigidArea(new Dimension(10,0))); // Espacement à droite des boutons
        controlsPanel.add(quitterPanel);


        jPanel3.add(controlsPanel, BorderLayout.EAST);

        this.frame.getContentPane().add(jPanel3, BorderLayout.CENTER);

        jPanel2.setBackground(new Color(102, 102, 102));
        jPanel2.setPreferredSize(new Dimension(690, 30));
        jPanel2.setLayout(new BorderLayout());

        currentPlayer.setHorizontalAlignment(SwingConstants.CENTER);
        currentPlayer.setText("Le joueur " + J.getPlayer(J.getPlayer()).getPlayerName() + " joue");

        jPanel2.add(currentPlayer, BorderLayout.CENTER);

        this.frame.getContentPane().add(jPanel2, BorderLayout.NORTH);

        chrono = new Timer( 100, new AdaptateurTemps(control, vueGaufre));
		chrono.start();

        this.frame.setVisible(true);
    }

    public void setScoreLabel(int score1, int score2) {
        if (score1 > score2) {
            this.score.setText("<html>Joueur 1 : " + score1 + "<br>Joueur 2 : " + score2 + "<br>Joueur 1 gagne !</html>");
        } else if (score1 < score2) {
            this.score.setText("<html>Joueur 1 : " + score1 + "<br>Joueur 2 : " + score2 + "<br>Joueur 2 gagne !</html>");
        } else {
            this.score.setText("<html>Joueur 1 : " + score1 + "<br>Joueur 2 : " + score2 + "<br>Match nul !</html>");
        }
    }

    public void rafraichir() {
        // Mettre à jour le plateau de jeu
        this.frame.getContentPane().removeAll();
        VueGaufre vueGaufre = new VueGaufre(J, this);
        vueGaufre.addMouseListener(new GaufreListener());
        gaufrePanel.add(vueGaufre);
        jPanel3.add(gaufrePanel, BorderLayout.CENTER);

        ClickListener click = new ClickListener(this, 1);

        // Ajout des listeners
        charger.addActionListener(click);
        annuler.addActionListener(click);
        refaire.addActionListener(click);
        sauvegarder.addActionListener(click);
        nouvellePartie.addActionListener(click);
        menuPrincipal.addActionListener(click);

        quitter.addMouseListener(new MouseInputAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                chrono.stop();
                fermer();
            }
        });

        // Vider le panel de controle pour remettre le score à jour
        controlsPanel.removeAll();

        controlsPanel.add(score);
        controlsPanel.add(annulerRefairePanel);
        controlsPanel.add(sauvegarderNouvellePartiePanel);
        controlsPanel.add(quitterPanel);

        jPanel3.add(controlsPanel, BorderLayout.EAST);
        this.frame.getContentPane().add(jPanel3, BorderLayout.CENTER);

        // Mettre à jour le joueur courant
        jPanel2.removeAll();
        currentPlayer.removeAll();
        currentPlayer.setText("Le joueur " + J.getPlayer(J.getPlayer()).getPlayerName() + " joue");
        jPanel2.add(currentPlayer, BorderLayout.CENTER);
        this.frame.getContentPane().add(jPanel2, BorderLayout.NORTH);
    }


}
