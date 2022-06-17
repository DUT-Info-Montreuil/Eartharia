package application.modele.item;

import application.modele.Acteur;
import application.modele.Environnement;

public class arc extends Arme{
	
	public arc(int id, int degats, Acteur utilisateur) {
		super(id, degats, utilisateur);
	}

	@Override
	public void agit(int y, int x, Environnement env) {
		Projectile p = new fleche("fleche", x, y, env.getPerso());
		env.addListProjectiles(p);
		
	}

}

