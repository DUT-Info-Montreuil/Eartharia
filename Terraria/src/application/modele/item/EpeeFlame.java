package application.modele.item;

import application.modele.Acteur;
import application.modele.Environnement;

public class EpeeFlame extends Epee{

	public EpeeFlame(Acteur utilisateur) {
		super(59, 30, utilisateur);
		// TODO Auto-generated constructor stub
	}
	public EpeeFlame(Acteur acteur,int quantite) {
		super(59, quantite, 30,acteur);
	}
	@Override
	public void agit(int y, int x, Environnement env) {
		Projectile p = new Projectile("boule",x, y,16,16,getUtilisateur(),25);
		this.getUtilisateur().getEnv().addListProjectiles(p);
		super.attaque();
	}
}