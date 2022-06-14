package application.modele.monstre;

import application.modele.Acteur;
import application.modele.Environnement;
import application.modele.acteur.Monstre;
import application.modele.acteur.Perso;

public class volant  extends Monstre{

	public volant(Environnement env, int x, int y,Perso p) {
		super(env, x, y, 1, 20, 10, 16, 16, p);
	}

	@Override
	public void agir() {
		try {
			mouvement(getPerso());
		} catch (Exception e) {}
		attaquer(getPerso());
	
	}


}
