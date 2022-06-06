package application.modele.acteur;

import application.modele.Acteur;
import application.modele.Environnement;

public class Pnj extends Acteur {
	public static int compteur = 0;
	private String idActeur;
	
	public Pnj(Environnement env, int x, int y, int hp) {
		super(env, x, y,0, hp, 0,36,36);
		
	}

	@Override
	public void agir() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attaquer(Acteur a) {
		// TODO Auto-generated method stub
		
	}

}
