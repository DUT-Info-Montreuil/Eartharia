package application.modele.monstre;

import java.util.Iterator;
import java.util.Timer;

import application.modele.Acteur;
import application.modele.Environnement;
import application.modele.acteur.Monstre;
import application.modele.acteur.Perso;
import application.modele.acteur.Pnj;
import application.modele.fonctionnalitees.Tour;

public class Sol extends Monstre {

	public Sol(Environnement env, int x, int y,Perso p) {
		super(env, x, y,1 , 20, 10, 16, 16, p);

	}
	
	public void mouvementSol (Perso p) throws Exception {
		try {
			super.tombe(16);
		} catch (Exception e) {}
		super.mouvement(p);
	}	
	@Override
	public void agir() {
		try {
			mouvementSol(getPerso());
		} catch (Exception e) {}
		attaquer(getPerso());
	}
}
