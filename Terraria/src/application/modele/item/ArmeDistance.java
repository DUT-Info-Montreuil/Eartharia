package application.modele.item;

import java.util.Timer;

import application.modele.Acteur;
import application.modele.Environnement;
import application.modele.fonctionnalitees.timer.CoolDownBaton;

public abstract class ArmeDistance extends Arme {

	public ArmeDistance(int id, int degats, Acteur utilisateur) {
		super(id, degats, utilisateur);
	}
	public ArmeDistance(int id, int degats, int quantite, Acteur utilisateur) {
		super(id, degats,quantite, utilisateur);
	}

	public abstract Projectile tire(int y, int x, Environnement env) ;
	
	public void agit(int y, int x, Environnement env) {
		Projectile p =tire(y, x, env);
		this.getUtilisateur().getEnv().addListProjectiles(p);
	}

}
