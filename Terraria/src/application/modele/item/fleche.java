package application.modele.item;

import application.modele.Acteur;

public class fleche extends Projectile{

	public fleche(String type, int xDest, int yDest, Acteur utilisateur) {
		super(type, xDest, yDest, 16, 16, utilisateur, 25);
	}
}
