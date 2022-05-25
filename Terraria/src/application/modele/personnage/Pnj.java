package application.modele.personnage;

import application.modele.Acteur;
import application.modele.Environnement;

public class Pnj extends Acteur {

	public Pnj(Environnement env, int x, int y, int hp) {
		super(env, x, y, hp,4,16,16);
	}

}
