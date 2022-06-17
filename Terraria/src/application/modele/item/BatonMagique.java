package application.modele.item;

import java.util.Timer;

import application.modele.Acteur;
import application.modele.Environnement;
import application.modele.fonctionnalitees.timer.CoolDownBaton;
import application.modele.fonctionnalitees.timer.Saut;

public class BatonMagique extends Arme{

	private boolean chargement;
	public BatonMagique(Acteur acteur) {
		super(16, 30,acteur);
		setSize(true);
	}
	
	public BatonMagique(Acteur acteur,int quantite) {
		super(16, quantite, 30,acteur);
		setSize(true);
	}

	@Override
	public void agit(int y, int x, Environnement env) {
		Projectile p;
		if(isBig()) {
			p = new Projectile("bouleCharger",x, y,32,32,getUtilisateur(),50);
			new Timer().schedule(new CoolDownBaton(this), 3000);
		}
		else {
			p = new Projectile("boule",x, y,16,16,getUtilisateur(),25);
		}
		setSize(false);
		this.getUtilisateur().getEnv().addListProjectiles(p);
	}

	public boolean isBig() {
		return chargement;
	}

	public void setSize(boolean chargement) {
		this.chargement = chargement;
	}
	
 }
