package application.modele.item;

import application.modele.Acteur;
import application.modele.Environnement;

public class BatonMagique extends Arme{

	public BatonMagique(int id, int degats,Acteur acteur) {
		super(id, degats,acteur);
	}
	
	public BatonMagique(int id, int quantite, int degats, Acteur acteur) {
		super(id, quantite, degats,acteur);
	}

	@Override
	public void agit(int y, int x, Environnement env) {
		Projectile p = new Projectile(11, x, y,getUtilisateur());
		this.getUtilisateur().getEnv().addListProjectiles(p);
	}
	
 }
