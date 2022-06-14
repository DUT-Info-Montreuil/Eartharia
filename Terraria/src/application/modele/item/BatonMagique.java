package application.modele.item;

import application.modele.Acteur;
import application.modele.Environnement;

public class BatonMagique extends Arme{

	public BatonMagique(Acteur acteur) {
		super(16, 30,acteur);
	}
	
	public BatonMagique(Acteur acteur,int quantite) {
		super(16, quantite, 30,acteur);
	}

	@Override
	public void agit(int y, int x, Environnement env) {
		Projectile p = new Projectile(11, (double)x, (double)y,getUtilisateur());
		this.getUtilisateur().getEnv().addListProjectiles(p);
	}
	
 }
