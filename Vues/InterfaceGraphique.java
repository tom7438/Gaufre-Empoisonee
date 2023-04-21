package Vues;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

abstract class InterfaceGraphique implements Runnable {
    JButton play;
    JButton charger;
    JButton annuler;
    JButton refaire;
    JButton sauvegarder;
    JButton nouvellePartie;
    JButton menuPrincipal;
    JButton rejouer;
    JButton quitter;
    JFrame frame;

    public JButton createButton(String s) {
        JButton button = new JButton(s);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setPreferredSize(new Dimension(120, 50));
        button.setFocusable(false);
        return button;
    }

    public JTextField createText(String s) {
        JTextField T1 = new JTextField(s, 50);
        T1.getFont().deriveFont(Font.ITALIC);
        T1.setForeground(Color.gray);
        T1.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (T1.getText().equals(s)) {
                    T1.setText("");
                    T1.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (T1.getText().isEmpty()) {
                    T1.setForeground(Color.GRAY);
                    T1.setText(s);
                }
            }
        });
        return T1;
    }

    public void fermer() {
        this.frame.setVisible(false);
        this.frame.dispose();
    }
}
