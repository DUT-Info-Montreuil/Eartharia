package application.modele.item;

import application.modele.Acteur;
import application.modele.Environnement;

public class bDeFeu extends Projectile{

	public bDeFeu(String type, int xDest, int yDest, Acteur utilisateur) {
		super(type, xDest, yDest, 16, 16, utilisateur, 25);
	}

}