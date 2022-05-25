package application.modele.acteur;

import application.modele.Acteur;
import application.modele.Environnement;
import application.modele.fonctionnalitees.Box;
import application.modele.fonctionnalitees.CollisionException;
import application.modele.fonctionnalitees.LimiteMapException;

public class Perso extends Acteur{

	public Perso(Environnement env, int x, int y) {
		
		super(env, x, y,16, 200, 30, 20,20);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void agir() {}

}
