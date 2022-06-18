package application.modele.item;

import java.util.Timer;

import application.modele.Acteur;
import application.modele.Environnement;
import application.modele.fonctionnalitees.timer.CoolDownBaton;
import application.modele.fonctionnalitees.timer.Saut;

public class BatonMagique extends ArmeDistance{

	private boolean chargement;
	public BatonMagique(Acteur acteur) {
		super(16, 50,acteur);
		setSize(true);
	}
	
	public BatonMagique(Acteur acteur,int quantite) {
		super(16, quantite, 50,acteur);
		setSize(true);
	}

	public Projectile tire(int y, int x, Environnement env) {
		Projectile p;
		if(isBig()) {
			p = new Projectile("bouleCharger",x, y,32,32,getUtilisateur(),getDegats());
			new Timer().schedule(new CoolDownBaton(this), 3000);
		}
		else {
			p = new Projectile("boule",x, y,16,16,getUtilisateur(),getDegats()/2);
		}
		setSize(false);
		return p;
	}

	public boolean isBig() {
		return chargement;
	}

	public void setSize(boolean chargement) {
		this.chargement = chargement;
	}
	
 }
