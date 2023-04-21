package Vues;
import java.awt.event.*;

public class GaufreListener extends MouseListener {
    // TODO: Déplacer ça dans un Controller pr respecter la structure MVC

    @Override
    public void mousePressed(MouseEvent e) {
        VueGaufre v = (VueGaufre) e.getComponent();
        int y = e.getX() / v.largeurCase;
        int x = e.getY() / v.hauteurCase;

        System.out.println("Clic en (" + x + "," + y + ")");

        if(v.G.coup(x, y)) {
            if(v.G.partieTerminee()) {
                v.IJ.currentPlayer.setText("Partie terminée! Gagnant: " + (v.G.joueurs[v.G.joueurCourant].getPlayerName()));
            } else {
                v.IJ.currentPlayer.setText("Le joueur " + (v.G.getPlayer(v.G.getPlayer()).getPlayerName()) + (v.G.getPlayer(v.G.getPlayer()).isAI() ? " (AI)" : "")  + " est en train de jouer");
            }
        } else {
            v.IJ.currentPlayer.setText("Impossible de jouer le coup (?)");
        }
        //System.out.println(v.G.niveau.toString());
        v.repaint();
    }
}