package application.modele.acteur;

import application.modele.Acteur;
import application.modele.Environnement;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Monstre extends Acteur{
	private Perso perso;
	public Monstre(Environnement env, int x, int y,int vitesse, int hp, int atq, int xBox, int yBox, Perso p) {
		super(env, x, y,vitesse, hp, atq, xBox, yBox);
		this.perso = p;
	}
	public abstract void agir();
	
	public boolean proximiteMouvement (Perso p) { 
		return Math.abs(this.getX() - p.getX()) <=16;
	}
	public boolean proximiteAttaque (Perso p) { 
		return Math.abs(this.getX() - p.getX()) <=16;		
	}
	public void mouvement (Perso p) throws Exception {
		if(!proximiteMouvement(p) ) {
			try {
				if (this.env.getTemp() % 30 == 0)
					this.droite();
				if (this.env.getTemp() % 30 == 3) 
					this.gauche();
			} catch (Exception e) {}
		}else 
			throw new Exception() ;
	}
	public void attaquer(Perso p) {
		if(this.env.getTemp() %40 == 0) {
			if(proximiteAttaque(p)) {
			p.recevoirDegat(this.getDegatAttaque());
			}
		}
	}
	public Perso getPerso() {
		return this.perso;
	}
}
