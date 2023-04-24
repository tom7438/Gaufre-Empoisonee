package Vues;

import Modele.Jeu;
import Modele.Niveau;

import javax.swing.*;
import java.awt.*;

public class VueGaufre extends JComponent {
    public Jeu G;
    Niveau N;
    public InterfaceJeu IJ;
    public int largeurCase, hauteurCase;

    public VueGaufre(Jeu J, InterfaceJeu JI){
        N = J.niveau;
        G = J;
        IJ = JI;
    }

    // TODO : mettre des images à la place
    @Override
    public void paintComponent(Graphics g) {
        largeurCase = (this.getWidth())/this.N.getColonne();
        hauteurCase = (this.getHeight())/this.N.getLigne();

        largeurCase = Math.min(largeurCase, hauteurCase);

        for (int l = 0; l < N.getLigne(); l++) {
            for (int c = 0; c < N.getColonne(); c++) {
                int x = c * largeurCase;
                int y = l * hauteurCase;

                if(N.aMorceau(l, c) || N.aMorceauEmpoisonne(l, c)) {
                    g.setColor(Color.ORANGE);
                    g.fillRect(x, y, largeurCase, hauteurCase);
                    g.setColor(new Color(160,90,50));
                    g.drawRect(x, y, largeurCase, hauteurCase);
                }

                if (l == 0 && c == 0) {
                    g.setColor(new Color(10, 70, 10));
                    g.fillOval(x+5, y+5, largeurCase-10, hauteurCase-10);
                }
            }
        }
    }
}
