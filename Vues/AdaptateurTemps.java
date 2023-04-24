/*
 * Morpion pédagogique

 * Copyright (C) 2016 Guillaume Huard

 * Ce programme est libre, vous pouvez le redistribuer et/ou le
 * modifier selon les termes de la Licence Publique Générale GNU publiée par la
 * Free Software Foundation (version 2 ou bien toute autre version ultérieure
 * choisie par vous).

 * Ce programme est distribué car potentiellement utile, mais SANS
 * AUCUNE GARANTIE, ni explicite ni implicite, y compris les garanties de
 * commercialisation ou d'adaptation dans un but spécifique. Reportez-vous à la
 * Licence Publique Générale GNU pour plus de détails.

 * Vous devez avoir reçu une copie de la Licence Publique Générale
 * GNU en même temps que ce programme ; si ce n'est pas le cas, écrivez à la Free
 * Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307,
 * États-Unis.

 * Contact: Guillaume.Huard@imag.fr
 *          Laboratoire LIG
 *          700 avenue centrale
 *          Domaine universitaire
 *          38401 Saint Martin d'Hères
 */
package Vues;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdaptateurTemps implements ActionListener {
	CollecteurEvenements control;
	//InterfaceJeu IJ;
	VueGaufre v ;

	AdaptateurTemps(CollecteurEvenements c, VueGaufre vueGaufre) {
		control = c;
		v = vueGaufre;	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		control.tictac(v);
		refresh(v);
		

		v.repaint();
		//v.IJ.rafraichir();
	}

	public void refresh(VueGaufre v){
		if(v.G.partieTerminee()) {
			v.IJ.currentPlayer.setText("Partie terminée! Gagnant: " + (v.G.getPlayer(v.G.getPlayer()).getPlayerName()));
		} else {
			v.IJ.currentPlayer.setText("Le joueur " + (v.G.getPlayer(v.G.getPlayer()).getPlayerName()) + (v.G.getPlayer(v.G.getPlayer()).isAI() ? " (AI)" : "")  + " est en train de jouer");
		}
	}
}