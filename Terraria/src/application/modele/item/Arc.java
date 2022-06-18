package application.modele.item;

import application.modele.Acteur;
import application.modele.Environnement;

public class Arc extends ArmeDistance{

	public Arc(Acteur acteur) {
		super(16, 20,acteur);
	}
	
	public Arc(Acteur acteur,int quantite) {
		super(16, quantite, 20,acteur);
	}

	public Projectile tire(int y, int x, Environnement env) {
		Projectile p = new Projectile("fleche",x, y,16,16,getUtilisateur(),getDegats());
		return p;
	}
 }
