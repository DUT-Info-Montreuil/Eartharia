package application.modele.acteur;

import application.modele.Acteur;
import application.modele.Environnement;

public abstract class Monstre extends Acteur{
	private String idMonstre;
	public static int compteur = 0;

	public Monstre(Environnement env, int x, int y,int vitesse, int hp, int atq, int xBox, int yBox) {
		super(env, x, y,vitesse, hp, atq, xBox, yBox);
		this.idMonstre = "M" + compteur;

	}
	public void recevoirDegat(int degat) {
		 this.setHp(this.getHp() - degat); 
	}
	public abstract void agir();
	
	public String getId () {
		return this.idMonstre;
	}
	

}
